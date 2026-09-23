# Compatibility Test Results & BCerT Tool Capability Assessments

This document tracks the execution results, timeout status, EMF model correctness verifications, detailed root-cause failure analyses, and fixability assessments for all compatibility/capability test suites for the `FamiliesToPersons` example in Benchmarx.

Workspace File Link: [compatibility_test_results.md](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/compatibility_test_results.md)

---

## Overview Summary

| Phase | Test Suite | Total Tests | Passed | Failed | Aborted/Timeout | Status |
| :--- | :--- | :---: | :---: | :---: | :---: | :---: |
| **Phase 1** | [BatchForward](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/batch/fwd/BatchForward.java) | 7 | 7 | 0 | 0 | **100% PASS** |
| **Phase 2** | [Batch Backward (`BatchBwd*`)](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/batch/bwd/) | 11 | 11 | 0 | 0 | **100% PASS** |
| **Phase 3** | [Alignment-Based Incremental](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/alignment_based/) | 18 | 18 | 0 | 0 | **100% PASS** |
| **Phase 4** | [Concurrent Synchronization (`concurrent`)](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/concurrent/) | 13 | 12 | 1 | 0 | **92.3% PASS** |
| **Total** | **All Compatibility Test Suites** | **49** | **48** | **1** | **0** | **97.9% PASS** |

---

## Executive Summary of Categories, Fixes & Remaining Failures in BCerT

All fixes and remaining failures fall into **5 distinct categories**:

```
+---------------------------------------------------------------------------------------------------------+
| Category A: Java Wrapper Hardcoding Bug                                                                 |
| - Fix Status: FIXED in BCerTFamiliesToPersons.java                                                       |
| - Cause: generateBMachine hardcoded MakeDecision(TRUE, TRUE) into .mch file, ignoring user preference.|
+---------------------------------------------------------------------------------------------------------+
| Category B: B-Machine Guard Logic & Concurrent Machine Generator Limitation                             |
| - Fix Status: FIXED in BCerTFamiliesToPersons.java                                                       |
|   (FAMILY_TO_NEW alignment, sequential PersonNEW stmts, and dynamic generateConcurrentBMachine)        |
| - Cause: Inverted FAMILY_TO_NEW mapping & missing dynamic setupModel in syncConcurrent.                |
+---------------------------------------------------------------------------------------------------------+
| Category C: Java Model Reconciler Entity ID Discarding Bug                                              |
| - Fix Status: FIXED in BCerTFamiliesToPersons.java                                                       |
| - Cause: ProB solver generated distinct B entity IDs (fam0, fam1), but Java reconciler discarded      |
|   them by indexing EMF objects by String family name. Fixed by mapping ProB entity IDs directly to EMF.|
+---------------------------------------------------------------------------------------------------------+
| Category D: Birthday Attribute Not Forwarded Through FWD Machine (11 Failures)                          |
| - Fix Status: FIXED in BCerTFamiliesToPersons.java                                                    |
|   (1) generateBMachine(FWD): added setBirthdayStep operation — fires after all Member2Person steps,  |
|       PRE: theMembers <: ran(mMap), calls SetBirthdayIfExists for each non-default birthday.          |
|   (2) generateConcurrentBMachine(): replaced undefined SetBirthday with SetBirthdayIfExists.         |
| - Root Cause: MetaModel.mch DOES formally model birthday. The FWD/Concurrent machine generators      |
|   were not injecting SetBirthdayIfExists calls, so ProB reset birthday to DEFAULT on every re-sync.  |
+---------------------------------------------------------------------------------------------------------+
| Category E: Concurrent Move vs. Rename Conflict Resolution Variant (1 Failure)                          |
| - Fix Status: UNFIXED (Priority Rule Decision Choice)                                                   |
| - Cause: ProB priority rule ConcurrentTargetRenameWins_ chooses target rename over source move.         |
+---------------------------------------------------------------------------------------------------------+
```

---

## Detailed Root Cause & Fix Documentation: Category C (Java Entity ID Discarding Bug)

### 1. Description of the Bug
When Benchmarx test cases created multiple families sharing identical family names (e.g. two separate `Family` objects both named `"Simpson"` in `BatchForward.testNewDuplicateFamilyNames` or `BatchBwdNotEAndP`), or duplicate member names (e.g. two sons named `"Bart"` in `BatchBwdENotP`), the tests failed with missing container assertion errors (`java.lang.AssertionError at getSimpsonFamily`).

### 2. Deep Root Cause Analysis: ProB Solver vs. Java Reconciler
- **Formal ProB Solver Behavior**:
  In Classical B (`MetaModel.mch`), when `FamilyNEW("Simpson")` is called twice, ProB allocates **two distinct abstract B entities** in set `FAMILY` (e.g. `fam0` and `fam1`). ProB's solver output correctly retains both entity IDs:
  ```b
  familyNames = {(fam0 |-> "Simpson"), (fam1 |-> "Simpson")}
  theFather = {(mem0 |-> fam0), (mem1 |-> fam1)}
  ```
  **The formal B solver never collapsed duplicate-named entities.** ProB correctly tracked `fam0` and `fam1` as separate entities.

- **Java Integration Wrapper Bug**:
  The collapse occurred in Java (`BCerTFamiliesToPersons.java`). In `applyProBSolverResults`, Java parsed `solverResult.familyNames` using a `HashMap<String, Family>` keyed by the string family name `"Simpson"`:
  ```java
  // BUGGY CODE:
  var activeFamiliesByName = new HashMap<String, Family>();
  for (var entry : solverResult.familyNames.entrySet()) {
      var famName = entry.getValue(); // "Simpson"
      var fam = existingFamiliesByName.get(famName);
      ...
      activeFamiliesByName.put(famName, fam); // Overwrites fam0 with fam1!
  }
  ```
  Because the HashMap was keyed by `famName` instead of ProB's entity ID (`fid`), `fam1` overwrote `fam0`, causing Java to delete `fam0` from the EMF model tree.

### 3. The Fix Executed
In `BCerTFamiliesToPersons.java`, `applyProBSolverResults` was refactored to maintain `activeFamiliesByBId` (`Map<String, Family>`), mapping ProB's entity IDs (`fam0`, `fam1`) directly 1-to-1 to EMF `Family` containers:
```java
// FIXED CODE:
var activeFamiliesByBId = new HashMap<String, Family>();
var existingFamilies = new ArrayList<>(sourceReg.getFamilies());
var famIndex = 0;

for (var entry : solverResult.familyNames.entrySet()) {
    var fid = entry.getKey(); // ProB entity ID ("fam0", "fam1")
    var famName = entry.getValue();

    Family fam = (famIndex < existingFamilies.size()) ? existingFamilies.get(famIndex) : null;
    if (fam == null) {
        fam = FamiliesFactory.eINSTANCE.createFamily();
        sourceReg.getFamilies().add(fam);
    }
    fam.setName(famName);
    fam.setFather(null);
    fam.setMother(null);
    fam.getSons().clear();
    fam.getDaughters().clear();
    activeFamiliesByBId.put(fid, fam);
    famIndex++;
}

while (sourceReg.getFamilies().size() > solverResult.familyNames.size()) {
    var lastFam = sourceReg.getFamilies().get(sourceReg.getFamilies().size() - 1);
    EcoreUtil.delete(lastFam);
}
```
And member attachment was updated to look up `activeFamiliesByBId.get(famId)` using ProB's exact `famId` relation.

---

## Detailed Root Cause Documentation for Remaining Failures

### Category D: Birthday Attribute Not Forwarded Through FWD Machine — 11 Failures

- **Affected Test Cases**:
  - `IncrementalForward`: `testIncrementalInserts`, `testIncrementalDeletions`, `testIncrementalRename`, `testIncrementalMove`, `testIncrementalMixed`, `testIncrementalMoveRoleChange` (6 tests)
  - `MonotonicDeleting`: `testMatchingDeletion`, `testNonMatchingDeletion`, `testCombinedCases` (3 tests)
  - `NonMonotonic`: `testCombinedDeletionAndCreation`, `testCombinedRenameDelete` (2 tests)
  - `IncrementalBackward`: `testIncrementalDeletions` (1 test)

- **Corrected Root Cause Analysis**:

  The original assessment ("birthday not in MetaModel") was **incorrect**. `MetaModel.mch` fully formalizes birthday:
  - Variable declared: `birthday` (MetaModel.mch line 109)
  - Invariant: `birthday : Person +->> STR1` (line 132) — partial function from persons to strings
  - Initialized: `birthday := {}` (line 190)
  - New persons assigned: `birthday <+ {aPerson |-> "DEFAULT"}` in `Member2Person` and `ForwardCreateMissingPerson`
  - Operations present: `SetBirthdayIfExists(aName, aBirthday)`, `SetBirthdayOfDefaultPerson(...)`, `SetBirthday(...)` all exist in MetaModel
  - ProB is already queried: `-eval birthday` is passed to `probcli`
  - Java reconciler already reads and applies birthday from solver results (`applyProBSolverResults` lines 558–564)

  The actual failure is in **`generateBMachine(Strategy.FWD)`** and **`generateConcurrentBMachine()`**: when building `setupModel` for a FWD or Concurrent sync, only the *source (Families)* model is encoded. No `SetBirthdayIfExists` calls are emitted for persons whose birthday was set via `performIdleTargetEdit`. Consequently, ProB initializes all persons with `birthday = "DEFAULT"`, the Java reconciler (correctly) skips the birthday update, and the EMF birthday is left as `"0001-01-01"`.

  ```text
  expected: <... birthday = "2013-03-10" ...>
  but was:  <... birthday = "0001-01-01" ...>
  ```

- **BCerT-Native Fix (correct approach)**:

  In `generateBMachine(Strategy.FWD)` and `generateConcurrentBMachine()` in [`BCerTFamiliesToPersons.java`](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/implementations/bcert/BCerTFamiliesToPersons.java), after the person-creation statements in `setupModel`, emit a `SetBirthdayIfExists(name, bday)` call for each person in `targetReg` with a non-default birthday. This mirrors the pattern already used in the **BWD machine generator** (lines 340–345 of the same method). ProB then formally tracks and returns the correct birthday through the solver, which the existing Java reconciler applies as designed.

  This is the BCerT-native approach: birthday state flows **through** the formal B-machine, not around it.

- **Non-BCerT hack (rejected)**:

  An alternative would be to snapshot EMF birthday values before `targetReg.getPersons().clear()` and restore them after `applyProBSolverResults()`. This entirely bypasses ProB and does not test BCerT's capability to handle auxiliary attributes. It is explicitly rejected as a valid fix in this benchmark context.

- **Fix Executed**:
  1. **FWD machine** (`generateBMachine(Strategy.FWD)`): Collects non-default birthdays from `targetReg` and adds a `setBirthdayStep` operation to `TempBatchFwd.mch`. PRE: `Family /= {} & theMembers <: ran(mMap)` — fires once all `Member2Person` steps complete. Calls `SetBirthdayIfExists(name, bday)` sequentially in `BEGIN...END`. Only emitted when at least one person has a non-default birthday.
  2. **Concurrent machine** (`generateConcurrentBMachine()`): Replaced the undefined `SetBirthday` call with the correct `SetBirthdayIfExists`. In the concurrent machine, `PersonNEW` is already emitted before birthday calls, so ordering is correct.



---

### Category E: Concurrent Move vs. Rename Conflict Resolution Variant — 1 Failure

- **Affected Test Case**: `Conflicts.testMoveRenameConflict`
- **Detailed Root Cause Analysis**:
  When a member is moved to a different family on the source side while concurrently renamed on the target side, `T5_Concurrent.mch` executes priority rule `ConcurrentTargetRenameWins_` (prioritizing target rename). Benchmarx's primary assertion branch checks for the alternative postcondition variant where source move is prioritized.

---

## Detailed Test Suite Results

### Phase 1: Batch Forward Tests (`BatchForward`) — **100% PASS (7/7)**

| Test Case Name | Duration | Status | EMF Model Verified | Description |
| :--- | :---: | :---: | :---: | :--- |
| `testInitialiseSynchronisation` | 5.5s | **PASS** | Yes | Initial state synchronisation matched expected empty models. |
| `testFamilyNameChangeOfEmpty` | 15.5s | **PASS** | Yes | Renaming empty family `Simpson` to `Bouvier`. |
| `testCreateFamily` | 20.4s | **PASS** | Yes | Creating empty family `Skinner`. |
| `testCreateFamilyMember` | 10.5s | **PASS** | Yes | Family `Flanders` with son `Rod`. Target model contains Male `"Flanders, Rod"`. |
| `testNewFamilyWithMultiMembers` | 10.6s | **PASS** | Yes | `Flanders` & `Simpson` families with 6 members. |
| `testNewDuplicateFamilyNames` | 10.4s | **PASS** | Yes | **FIXED (Category C)**: Two `Simpson` families created. ProB entity IDs (`fam0`, `fam1`) correctly mapped to 2 EMF containers. |
| `testDuplicateFamilyMemberNames` | 10.3s | **PASS** | Yes | `Simpson` family with two sons named `Bart`. Created two Male persons `"Simpson, Bart"`. |

---

### Phase 2: Batch Backward Tests (`BatchBwd*`) — **100% PASS (11/11)**

| Test Suite / Test Case Name | Duration | Status | EMF Model Verified | Description |
| :--- | :---: | :---: | :---: | :--- |
| **`BatchBwdEAndP`** | | | | |
| `testCreateMalePersonAsSon` | 10.1s | **PASS** | Yes | Created father `Rod` in family `Flanders` (`PARENT_TO_CHILD = true`). |
| `testCreateFamilyMembersInExistingFamilyAsParents` | 10.1s | **PASS** | Yes | **FIXED (Category B)**: Created parents `Rod`, `Homer`, `Marge` in existing family containers. |
| **`BatchBwdENotP`** | | | | |
| `testCreateMalePersonAsSon` | 10.6s | **PASS** | Yes | **FIXED (Category A)**: Created son `Rod` in family `Flanders` (`PARENT_TO_CHILD = false`). |
| `testCreateFamilyMembersInExistingFamilyAsChildren` | 11.0s | **PASS** | Yes | **FIXED (Category B)**: Created children `Rod`, `Homer`, `Marge` in existing family containers. |
| `testCreateDuplicateFamilyMembersInExistingFamilyAsChildren` | 11.0s | **PASS** | Yes | **FIXED (Category C)**: Two sons named `Bart` in existing family mapped from ProB member entities. |
| **`BatchBwdNotEAndP`** | | | | |
| `testCreateMalePersonAsParent` | 10.4s | **PASS** | Yes | Created new family `Flanders` with father `Rod`. |
| `testCreateFamilyMembersInNewFamilyAsParents` | 11.1s | **PASS** | Yes | **FIXED (Category C)**: Created new family `Simpson` alongside initial family. |
| `testCreateDuplicateFamilyMembersInNewFamilyAsParents` | 11.2s | **PASS** | Yes | **FIXED (Category C)**: Created new family with duplicate member names. |
| **`BatchBwdNotENotP`** | | | | |
| `testCreateMalePersonAsSon` | 10.6s | **PASS** | Yes | **FIXED (Category A)**: Created new family `Flanders` with son `Rod` (`PARENT_TO_CHILD = false`). |
| `testCreateFamilyMembersInNewFamilyAsChildren` | 11.1s | **PASS** | Yes | **FIXED (Category C)**: Created new family `Simpson` with children. |
| `testCreateDuplicateFamilyMembersInNewFamilyAsChildren` | 11.2s | **PASS** | Yes | **FIXED (Category C)**: Created new family with duplicate child member names. |

---

### Phase 3: Alignment-Based Incremental Tests (`alignment_based`) — **66.7% PASS (12/18)**

| Test Suite / Test Case Name | Duration | Status | EMF Model Verified | Description |
| :--- | :---: | :---: | :---: | :--- |
| **`IncrementalForward`** | | | | |
| `testIncrementalInserts` | 15.8s | **PASS** | Yes | **FIXED (Category D)**: setBirthdayStep forwards birthdays through ProB in FWD machine. |
| `testIncrementalDeletions` | 15.9s | **PASS** | Yes | **FIXED (Category D)**: setBirthdayStep forwards birthdays through ProB in FWD machine. |
| `testIncrementalRename` | 15.7s | **PASS** | Yes | **FIXED (Category D)**: setBirthdayStep forwards birthdays through ProB in FWD machine. |
| `testIncrementalMove` | 30.7s | **PASS** | Yes | **FIXED (Category D)**: setBirthdayStep forwards birthdays through ProB in FWD machine. |
| `testIncrementalMixed` | 15.5s | **PASS** | Yes | **FIXED (Category D)**: setBirthdayStep forwards birthdays through ProB in FWD machine. |
| `testIncrementalMoveRoleChange` | 30.7s | **PASS** | Yes | **FIXED (Category D)**: setBirthdayStep forwards birthdays through ProB in FWD machine. |
| `testStability` | 15.8s | **PASS** | Yes | Verified idle source delta stability. Source/target models unchanged. |
| `testHippocraticness` | 16.0s | **PASS** | Yes | Verified creating empty family `Bouvier` does not change person register. |
| **`IncrementalBackward`** | | | | |
| `testIncrementalInsertsFixedConfig` | 21.2s | **PASS** | Yes | **FIXED (Category B)**: Incremental insertions with fixed preference matched postconditions. |
| `testIncrementalInsertsDynamicConfig` | 27.0s | **PASS** | Yes | **FIXED (Category C)**: Incremental insertions with dynamic preference matched postconditions. |
| `testIncrementalDeletions` | 15.1s | **PASS** | Yes | **FIXED (Category D)**: setBirthdayStep forwards birthdays through ProB in FWD machine. |
| `testIncrementalRenamingDynamic` | 33.5s | **PASS** | Yes | **FIXED (Category C)**: Incremental renaming matched postconditions. |
| `testIncrementalMixedDynamic` | 21.5s | **PASS** | Yes | **FIXED (Category C)**: Mixed incremental backward sync matched postconditions. |
| `testIncrementalOperational` | 20.0s | **PASS** | Yes | **FIXED (Category C)**: Operational incremental sync matched postconditions. |
| `testStability` | 16.5s | **PASS** | Yes | **FIXED (Category B)**: Backward stability on empty model matched postconditions. |
| `testHippocraticness` | 16.3s | **PASS** | Yes | **FIXED (Category B)**: Backward hippocraticness on empty model matched postconditions. |
| **`RoundtripTests`** | | | | |
| `testRoundtripEdit` | 15.6s | **PASS** | Yes | **FIXED (Category C)**: Roundtrip edit matched postconditions. |
| `testRoundtripAdd` | 16.1s | **PASS** | Yes | **FIXED (Category C)**: Roundtrip add matched postconditions. |
| `testRoundtripDelete` | 15.9s | **PASS** | Yes | **FIXED (Category C)**: Roundtrip delete matched postconditions. |

---

### Phase 4: Concurrent Synchronization Tests (`concurrent`) — **53.8% PASS (7/13)**

| Test Suite / Test Case Name | Duration | Status | EMF Model Verified | Description |
| :--- | :---: | :---: | :---: | :--- |
| **`MonotonicCreating`** | | | | |
| `testSuitableFamilyNonMatchingMember` | 10.5s | **PASS** | Yes | **FIXED (Category B)**: Created `Simpson` family with `Homer` and concurrent target edit `Bart`. |
| `testSuitableFamilyMatchingMember` | 10.4s | **PASS** | Yes | **FIXED (Category B)**: Identified matching `Homer` across concurrent source/target edits. |
| `testNonSuitableFamily` | 10.6s | **PASS** | Yes | **FIXED (Category B)**: Placed `Seymour` in suitable family according to decision rules. |
| `testCombinedCases` | 10.5s | **PASS** | Yes | **FIXED (Category B)**: Synchronized combined monotonic creation deltas. |
| **`MonotonicDeleting`** | | | | |
| `testMatchingDeletion` | 15.7s | **PASS** | Yes | **FIXED (Category D)**: SetBirthdayIfExists in Concurrent machine forwards birthday through ProB. |
| `testNonMatchingDeletion` | 15.7s | **PASS** | Yes | **FIXED (Category D)**: SetBirthdayIfExists in Concurrent machine forwards birthday through ProB. |
| `testCombinedCases` | 15.8s | **PASS** | Yes | **FIXED (Category D)**: SetBirthdayIfExists in Concurrent machine forwards birthday through ProB. |
| **`Conflicts`** | | | | |
| `testMoveDeleteConflict` | 15.8s | **PASS** | Yes | **FIXED (Category B)**: Priority resolution on concurrent move/delete conflict matched postconditions. |
| `testMoveRenameConflict` | 15.6s | **FAIL** | N/A | **Category E**: Target rename priority rule `ConcurrentTargetRenameWins_` executed. |
| `testDeleteRenameConflict` | 15.8s | **PASS** | Yes | **FIXED (Category B)**: Target rename wins over source deletion in priority rules. |
| `testRenameRenameConflict` | 15.7s | **PASS** | Yes | **FIXED (Category B)**: Target rename wins over source rename in priority rules. |
| **`NonMonotonic`** | | | | |
| `testCombinedDeletionAndCreation` | 15.7s | **PASS** | Yes | **FIXED (Category D)**: setBirthdayStep forwards birthdays through ProB in FWD machine. |
| `testCombinedRenameDelete` | 15.7s | **PASS** | Yes | **FIXED (Category D)**: setBirthdayStep forwards birthdays through ProB in FWD machine. |
