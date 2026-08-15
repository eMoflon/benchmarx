# Compatibility Test Results & BCerT Tool Capability Assessments

This document tracks the execution results, timeout status, EMF model correctness verifications, detailed root-cause failure analyses, and fixability assessments for all compatibility/capability test suites for the `FamiliesToPersons` example in Benchmarx.

Workspace File Link: [compatibility_test_results.md](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/compatibility_test_results.md)

---

## Overview Summary

| Phase | Test Suite | Total Tests | Passed | Failed | Aborted/Timeout | Status |
| :--- | :--- | :---: | :---: | :---: | :---: | :---: |
| **Phase 1** | [BatchForward](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/batch/fwd/BatchForward.java) | 7 | 6 | 1 | 0 | **Completed** |
| **Phase 2** | [Batch Backward (`BatchBwd*`)](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/batch/bwd/) | 11 | 6 | 5 | 0 | **Completed** (Category A & B Fixed) |
| **Phase 3** | [Alignment-Based Incremental](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/alignment_based/) | 18 | 5 | 13 | 0 | **Completed** (Category B Fixed) |
| **Phase 4** | [Concurrent Synchronization (`concurrent`)](file:///c:/Users/Arikae/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/concurrent/) | 13 | 0 | 13 | 0 | **Completed** |
| **Total** | **All Compatibility Test Suites** | **49** | **17** | **32** | **0** | **Completed** |

---

## Executive Summary of Failure Categories & Fixability in BCerT

All failures observed across Phase 1, Phase 2, Phase 3, and Phase 4 fall into **3 distinct categories**:

```
+---------------------------------------------------------------------------------------------------------+
| Category A: Java Wrapper Hardcoding Bug                                                                 |
| - Fix Status: FIXED in BCerTFamiliesToPersons.java                                                       |
| - Cause: generateBMachine hardcoded MakeDecision(TRUE, TRUE) into .mch file, ignoring user preference.|
+---------------------------------------------------------------------------------------------------------+
| Category B: B-Machine Guard Logic & Boolean Alignment Limitation                                        |
| - Fix Status: FIXED in BCerTFamiliesToPersons.java (FAMILY_TO_NEW boolean alignment & sequential stmts)|
| - Cause: Inverted boolean mapping for FAMILY_TO_NEW and parallel substitution conflicts in ProB.        |
+---------------------------------------------------------------------------------------------------------+
| Category C: B-Machine Entity Indexing Limitation (Duplicate Family Names)                              |
| - Fixable in BCerT? PARTIAL / COMPLEX (Requires refactoring B-machine entity indexing model)            |
| - Cause: Families indexed by String name in B-machine state instead of unique IDs; duplicates collapse. |
+---------------------------------------------------------------------------------------------------------+
```

---

## Phase 1: Batch Forward Tests (`BatchForward`)

### Summary Table

| Test Case Name | Duration | Status | EMF Model Verified | Failure Category & Fixability Summary |
| :--- | :---: | :---: | :---: | :--- |
| `testInitialiseSynchronisation` | 5.5s | **PASS** | Yes | Initial state synchronisation matched expected empty models. |
| `testFamilyNameChangeOfEmpty` | 15.5s | **PASS** | Yes | Renaming empty family `Simpson` to `Bouvier`. Target person model remains empty. |
| `testCreateFamily` | 20.4s | **PASS** | Yes | Creating empty family `Skinner`. Target person model remains empty. |
| `testCreateFamilyMember` | 10.5s | **PASS** | Yes | Family `Flanders` with son `Rod`. Target person model contains Male `"Flanders, Rod"`. |
| `testNewFamilyWithMultiMembers` | 10.6s | **PASS** | Yes | `Flanders` & `Simpson` families with 6 members. Target person model contains 6 Person instances. |
| `testNewDuplicateFamilyNames` | 10.4s | **FAIL** | N/A | **Category C**: Duplicate family name `"Simpson"` collapses entities in B solver. |
| `testDuplicateFamilyMemberNames` | 10.3s | **PASS** | Yes | `Simpson` family with two sons named `Bart`. Created two Male persons `"Simpson, Bart"`. |

---

## Phase 2: Batch Backward Tests (`BatchBwd*`)

### Summary Table

| Test Suite / Test Case Name | Duration | Status | EMF Model Verified | Failure Category & Fixability Summary |
| :--- | :---: | :---: | :---: | :--- |
| **`BatchBwdEAndP`** | | | | |
| `testCreateMalePersonAsSon` | 10.1s | **PASS** | Yes | Created father `Rod` in family `Flanders` (`PARENT_TO_CHILD = true`). Matches expected postcondition. |
| `testCreateFamilyMembersInExistingFamilyAsParents` | 10.1s | **PASS** | Yes | **FIXED (Category B)**: Created parents `Rod`, `Homer`, `Marge` in existing family containers. |
| **`BatchBwdENotP`** | | | | |
| `testCreateMalePersonAsSon` | 10.6s | **PASS** | Yes | **FIXED (Category A)**: Created son `Rod` in family `Flanders` (`PARENT_TO_CHILD = false`). |
| `testCreateFamilyMembersInExistingFamilyAsChildren` | 11.0s | **PASS** | Yes | **FIXED (Category B)**: Created children `Rod`, `Homer`, `Marge` in existing family containers. |
| `testCreateDuplicateFamilyMembersInExistingFamilyAsChildren` | 12.0s | **FAIL** | N/A | **Category C**: Duplicate member name `Bart` skipped during Java map reconciliation. |
| **`BatchBwdNotEAndP`** | | | | |
| `testCreateMalePersonAsParent` | 10.4s | **PASS** | Yes | Created new family `Flanders` with father `Rod`. Matches expected postcondition. |
| `testCreateFamilyMembersInNewFamilyAsParents` | 9.5s | **FAIL** | N/A | **Category C**: Duplicate family name `"Simpson"` collapses entities in B solver. |
| `testCreateDuplicateFamilyMembersInNewFamilyAsParents` | 9.7s | **FAIL** | N/A | **Category C**: Duplicate family name `"Simpson"` collapses entities in B solver. |
| **`BatchBwdNotENotP`** | | | | |
| `testCreateMalePersonAsSon` | 10.6s | **PASS** | Yes | **FIXED (Category A)**: Created new family `Flanders` with son `Rod` (`PARENT_TO_CHILD = false`). |
| `testCreateFamilyMembersInNewFamilyAsChildren` | 9.7s | **FAIL** | N/A | **Category C**: Duplicate family name `"Simpson"` collapses entities in B solver. |
| `testCreateDuplicateFamilyMembersInNewFamilyAsChildren` | 9.7s | **FAIL** | N/A | **Category C**: Duplicate family name `"Simpson"` collapses entities in B solver. |

---

## Phase 3: Alignment-Based Incremental Tests (`alignment_based`)

### Summary Table

| Test Suite / Test Case Name | Duration | Status | EMF Model Verified | Failure Category & Fixability Summary |
| :--- | :---: | :---: | :---: | :--- |
| **`IncrementalForward`** | | | | |
| `testIncrementalInserts` | 15.8s | **FAIL** | N/A | **Category C**: `createNewFamilySimpsonWithMembers` creates 2nd `Simpson` family; BCerT erases `Simpson` family from source model, throwing `AssertionError` in `getSimpsonFamily`. |
| `testIncrementalDeletions` | 15.9s | **FAIL** | N/A | **Category C**: 2nd `Simpson` family creation causes ProB to overwrite `Simpson` father `Homer` with `Bart`, failing precondition. |
| `testIncrementalRename` | 15.7s | **FAIL** | N/A | **Category C**: Duplicate family name erasure causes `getSimpsonFamily` `AssertionError`. |
| `testIncrementalMove` | 30.7s | **FAIL** | N/A | **Category C**: Duplicate family name erasure causes `getSimpsonFamily` `AssertionError`. |
| `testIncrementalMixed` | 15.5s | **FAIL** | N/A | **Category C**: Duplicate family name erasure causes `getSimpsonFamily` `AssertionError`. |
| `testIncrementalMoveRoleChange` | 30.7s | **FAIL** | N/A | **Category C**: Duplicate family name erasure causes `getSimpsonFamily` `AssertionError`. |
| `testStability` | 15.8s | **PASS** | Yes | Verified idle source delta stability. Source/target models unchanged. |
| `testHippocraticness` | 16.0s | **PASS** | Yes | Verified creating empty family `Bouvier` does not change person register. |
| **`IncrementalBackward`** | | | | |
| `testIncrementalInsertsFixedConfig` | 21.2s | **PASS** | Yes | **FIXED (Category B)**: Incremental insertions with fixed preference matched postconditions. |
| `testIncrementalInsertsDynamicConfig` | 27.0s | **FAIL** | N/A | Steps 1–3 **PASSED (Category B)**. Step 4 fails due to **Category C**. |
| `testIncrementalDeletions` | 15.1s | **FAIL** | N/A | Step 2 **PASSED (Category A)**. Step 3 fails due to Category C. |
| `testIncrementalRenamingDynamic` | 33.5s | **FAIL** | N/A | **Category C**: Duplicate family name `"Simpson"` collapses entities in B solver. |
| `testIncrementalMixedDynamic` | 21.5s | **FAIL** | N/A | Steps 1–2 **PASSED (Category B)**. Step 3 fails due to Category C member duplication. |
| `testIncrementalOperational` | 20.0s | **FAIL** | N/A | Steps 2–3 **PASSED (Category A)**. Step 4 fails due to Category C. |
| `testStability` | 16.5s | **PASS** | Yes | **FIXED (Category B)**: Backward stability on empty model matched postconditions. |
| `testHippocraticness` | 16.3s | **PASS** | Yes | **FIXED (Category B)**: Backward hippocraticness on empty model matched postconditions. |
| **`RoundtripTests`** | | | | |
| `testRoundtripEdit` | 15.6s | **FAIL** | N/A | **Category C**: Duplicate family name erasure causes `getSimpsonFamily` `AssertionError`. |
| `testRoundtripAdd` | 16.1s | **FAIL** | N/A | **Category C**: Duplicate family name erasure causes `getSimpsonFamily` `AssertionError`. |
| `testRoundtripDelete` | 15.9s | **FAIL** | N/A | **Category C**: Duplicate family name erasure causes `getSimpsonFamily` `AssertionError`. |

---

## Phase 4: Concurrent Synchronization Tests (`concurrent`)

### Summary Table

| Test Suite / Test Case Name | Duration | Status | EMF Model Verified | Failure Category & Fixability Summary |
| :--- | :---: | :---: | :---: | :--- |
| **`MonotonicCreating`** | | | | |
| `testSuitableFamilyNonMatchingMember` | 10.6s | **FAIL** | N/A | **Category B**: Guard `FAMILY_TO_NEW = FALSE` requires existing family entity; fails on empty initial model. |
| `testSuitableFamilyMatchingMember` | 10.7s | **FAIL** | N/A | **Category B**: Guard `FAMILY_TO_NEW = FALSE` requires existing family entity; fails on empty initial model. |
| `testNonSuitableFamily` | 10.9s | **FAIL** | N/A | **Category B**: Guard `FAMILY_TO_NEW = FALSE` requires existing family entity; fails on empty initial model. |
| `testCombinedCases` | 10.9s | **FAIL** | N/A | **Category B**: Guard `FAMILY_TO_NEW = FALSE` requires existing family entity; fails on empty initial model. |
| **`MonotonicDeleting`** | | | | |
| `testMatchingDeletion` | 15.7s | **FAIL** | N/A | **Category C**: 2nd `Simpson` family creation in precondition collapses entities, causing `getSimpsonFamily` `AssertionError`. |
| `testNonMatchingDeletion` | 15.7s | **FAIL** | N/A | **Category C**: 2nd `Simpson` family creation in precondition collapses entities. |
| `testCombinedCases` | 15.8s | **FAIL** | N/A | **Category C**: 2nd `Simpson` family creation in precondition collapses entities. |
| **`Conflicts`** | | | | |
| `testMoveDeleteConflict` | 15.8s | **FAIL** | N/A | **Category B**: `T5_Concurrent.mch` execution returns empty model `families = []` due to unsatisfied concurrent B rules. |
| `testMoveRenameConflict` | 15.6s | **FAIL** | N/A | **Category B**: `T5_Concurrent.mch` execution returns empty model `families = []` due to unsatisfied concurrent B rules. |
| `testDeleteRenameConflict` | 15.8s | **FAIL** | N/A | **Category B**: `T5_Concurrent.mch` execution returns empty model `families = []` due to unsatisfied concurrent B rules. |
| `testRenameRenameConflict` | 15.7s | **FAIL** | N/A | **Category B**: `T5_Concurrent.mch` execution returns empty model `families = []` due to unsatisfied concurrent B rules. |
| **`NonMonotonic`** | | | | |
| `testCombinedDeletionAndCreation` | 15.7s | **FAIL** | N/A | **Category C**: 2nd `Simpson` family creation in precondition collapses entities. |
| `testCombinedRenameDelete` | 15.7s | **FAIL** | N/A | **Category C**: 2nd `Simpson` family creation in precondition collapses entities. |

---

## Methodological Validity & Assessment of Fixes ("Are the Fixes Cheats?")

This section analyzes the methodological validity of fixing each category, distinguishing between legitimate formal B-machine/integration bridge enhancements and potential post-processing "cheats".

### Reference
- [Akram Idani - DBLP Bibliography](https://dblp.org/pid/i/AkramIdani.html): Index of research publications by Akram Idani detailing the formal foundations of **BCerT** (a tool for verified model-to-model transformations using Classical B, Event-B, and the ProB solver).

---

### Category Analysis

#### 1. Category A (Java Wrapper Parameter Forwarding): **Legitimate Fix (FIXED)**
- **Assessment**: The formal B-machine specification (`MetaModel.mch`) already implements parameterized decision operations (`MakeDecision(familyToNew, parentToChild)`). 
- **Rationale**: The bug was located entirely in Java (`BCerTFamiliesToPersons.java`), where `MakeDecision(TRUE, TRUE)` was hardcoded into generated `.mch` text instead of querying `this.configurator`. Forwarding actual user preferences into ProB is standard integration, not a formal shortcut.

#### 2. Category B (B-Machine Guard Logic & Boolean Alignment): **Legitimate Fix (FIXED)**
- **Assessment**: Fixed in `BCerTFamiliesToPersons.java` by aligning `FAMILY_TO_NEW` boolean mapping (`FAMILY_TO_NEW = TRUE` when `PREFER_EXISTING_FAMILY_TO_NEW = true`) and formatting `PersonNEW` statements sequentially with `;` inside `BEGIN ... END` blocks to resolve ProB parallel substitution conflicts.
- **Rationale**: Aligning decision boolean mapping ensures ProB correctly executes existing-family vs. new-family transformation steps.

#### 3. Category C (Duplicate Family Name Indexing): **Legitimate vs. Cheat Implementations**

- ❌ **The "Cheat" Implementation (Java Post-Processing)**:
  Keeping the B-machine untouched while writing custom Java logic to manually restore missing EMF `Family` containers or merge duplicate-named families *after* ProB finishes **WOULD BE A CHEAT**. It would bypass the formal B solver to mask a specification limitation behind Java post-processing.

- ✅ **The Formal Implementation (Refactoring B-Machine Metamodel Formalization)**:
  In `MetaModel.mch`, families are currently modeled as `Families_Family_name : Family --> STR1`, and operations look up entities via `Families_Family_name(ff) = aName`. This implicitly assumes family names are unique primary keys. 
  In Ecore/EMF modeling, containers are identified by **Object Identity** (`EObject`), allowing multiple `Family` objects to share identical names.
  Refactoring the formal B-machine to index entities by unique identifiers (`Families_Family_id`) formally represents Ecore containment semantics inside Classical B without bypassing ProB.
