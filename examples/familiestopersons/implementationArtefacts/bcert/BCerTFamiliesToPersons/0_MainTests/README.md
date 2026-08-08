# Rule-based Test Cases (Families to/from Persons)

These test cases are designed to validate the rule-based transformations specified in the B method by isolating each transformation rule in minimal configurations.

Each test case consists of:
- a **family model** (`*.Fam.xmi`)
- a **person model** (`*.Per.xmi`)
- a **pivot file** (`*.pivot`) defining the synchronization context

File names correspond to the expected transformation rule to be triggered.  
The objective is to isolate and validate the behavior of each rule independently.

Some configurations may exhibit **non-determinism**, reflecting multiple valid synchronization strategies.

To start, open a terminal in this directory and run: `generate_rule_cases.sh`.

---

## Initialization

### 0_empty
- **Input**: Empty family model (no families, no members)
- **Output**: Empty person model (no persons)
- **Effect**: No transformation rule is applicable
- **Expected behavior**: The system remains in a stable state with no enabled operations

- **Note**:
  This configuration represents the minimal valid setup of the transformation environment.
  It is mainly used to:
  - initialize the animator,
  - inspect the pivot structure,
  - and set up the workspace (Execution, State, and Trace views).
 
## Forward Rules

### Member2Person
- **Input**: A family with members (father, mother, children)
- **Output**: Empty person model
- **Effect**: Creates one `Person` from a `Member`
- **Expected rule**: Member2person

---

### ForwardRename
- **Input**: A family with a member (e.g., Homer)
- **Output**: A mapped person with an outdated name
- **Effect**: Renames the corresponding `Person` to match the `Member`
- **Expected rule**: ForwardRename

---

### ForwardDelete
- **Input**: Family without a specific member
- **Output**: A mapped `Person` still present
- **Effect**: Deletes the corresponding `Person`
- **Expected rule**: ForwardDelete

---

## Backward Rules

### Person2MemberExistingFamily
- **Input**: Existing family (Simpson)
- **Output**: Four Simpson `Person` without corresponding member
- **Effect**: Creates four `Member` inside the existing Simpson family
- **Expected rule**: Person2MemberExistingFamily

---

### Person2MemberNewFamily
- **Input**: No family
- **Output**: Three `Person` without corresponding member: one Skinner/Seymour and two Simpson/Homer
- **Configuration**: `PREFER_FAMILY_TO_NEW = true`, `PREFER_PARENT_TO_CHILD = true`
- **Effect**: Creates one family per distinct family name and inserts the corresponding members as parents
- **Expected rule**: Person2MemberNewFamily
- **Variant**: change `PREFER_FAMILY_TO_NEW = false` and run the transformation again. In this configuration, a new family may be created for each unmatched person, potentially leading to duplicated family names.

---

### BackwardRenameMemberName
- **Input**: One family (Simpson) with a member (e.g., Homer) and a mapped `Person` whose name has been modified (e.g., `Simpson, HomerX`)
- **Configuration**: `PREFER_FAMILY_TO_NEW = true`, `PREFER_PARENT_TO_CHILD = true`
  - `PREFER_FAMILY_TO_NEW`: no effect in this case (no family creation involved)
  - `PREFER_PARENT_TO_CHILD`: no effect in this case (no structural relocation, only renaming)
- **Effect**: Updates the `FamilyMember` name to match the `Person` name
- **Expected rule**: BackwardRenameMemberName

- **Note**:
  The animator may non-deterministically propose both: **BackwardRenameMemberName** and **ConcurrentTargetRenameWins**. This is expected because both rules share equivalent guards in this configuration (same mapping, same inconsistency on names, same backward strategy, and `sync = false`). In this minimal test case, both rules produce the same observable effect: propagating the `Person` name back to the `FamilyMember`.

  BackwardRenameMemberName represents the standard backward propagation,
  while ConcurrentTargetRenameWins represents a conflict-resolution strategy
  prioritizing the target-side rename. Here, no actual conflict exists, so both behave identically.

---

### BackwardDelete
- **Input**: One family (Simpson) with a member (e.g., Homer) and an existing mapping, but no corresponding `Person` in the target model
- **Configuration**: `PREFER_FAMILY_TO_NEW = true`, `PREFER_PARENT_TO_CHILD = true`
  - `PREFER_FAMILY_TO_NEW`: no effect (no family creation)
  - `PREFER_PARENT_TO_CHILD`: no effect (no structural move, only deletion)
- **Effect**: Deletes the corresponding `FamilyMember` to restore consistency with the target model
- **Expected rule**: BackwardDelete

- **Note**:
  This rule is triggered when a mapped `Person` has been removed from the target model
  while the corresponding `FamilyMember` still exists in the source model.

  The transformation restores consistency by propagating the deletion backward,
  ensuring that no dangling mapping remains.

---

## Move Rules

### BackwardMoveMaleToExistingFamily
- **Input**: Two families (e.g., Simpson and Flanders), where a male member (e.g., Bart) belongs to one family in the source model but is associated with another family in the `Person` model (e.g., `Flanders, Bart`)
- **Configuration**: `PREFER_FAMILY_TO_NEW = true`, `PREFER_PARENT_TO_CHILD = true`
  - `PREFER_FAMILY_TO_NEW`: no effect (target family already exists)
  - `PREFER_PARENT_TO_CHILD`: controls whether the moved member is inserted as a parent (`father`) or as a child (`son`)
- **Effect**: Moves the corresponding male `FamilyMember` to the existing target family to match the `Person` model
- **Expected rule**: BackwardMoveMaleToExistingFamily
- **Variant**:
  If the target family does not exist, this rule is not applicable.
  In that case, the transformation falls back to `BackwardMoveMaleToNewFamily`,
  which creates a new family before moving the member.

- **Note**:
  The rule is triggered when a mapped male `Person` refers to a different family than the one
  currently associated with the corresponding `FamilyMember`.

  The transformation restores consistency by reassigning the member to the correct family.
  The role (father or son) depends on the `PREFER_PARENT_TO_CHILD` configuration.

---

### BackwardMoveFemaleToExistingFamily
- Same as above for female members

---

### BackwardMoveMaleToNewFamily
- **Input**: One family (e.g., Simpson) with a mapped male member (e.g., Bart), while the corresponding `Person` refers to a non-existing family (e.g., `Flanders, Bart`)
- **Configuration**: `PREFER_FAMILY_TO_NEW = true`, `PREFER_PARENT_TO_CHILD = true`
  - `PREFER_FAMILY_TO_NEW`: creates a new family only if no existing family with the target name is available
  - `PREFER_PARENT_TO_CHILD`: controls whether the moved male member is inserted as a parent (`father`) or as a child (`son`)
- **Effect**: Creates the missing target family and moves the corresponding male `FamilyMember` into it
- **Expected rule**: BackwardMoveMaleToNewFamily

- **Variant**:
  If `PREFER_FAMILY_TO_NEW = false`, a new family may be created even if a family with the same name already exists, potentially leading to duplicated family names.

---

### BackwardMoveFemaleToNewFamily
- Same as above for female members

---

## Concurrent Rules

### ConcurrentMatchMemberPerson
- **Input**: One family (Simpson) with a member (e.g., Homer) and one existing `Person` with the same name (e.g., `Simpson, Homer`), but no mapping between them
- **Configuration**: `PREFER_FAMILY_TO_NEW = true`, `PREFER_PARENT_TO_CHILD = true`
  - `PREFER_FAMILY_TO_NEW`: no effect
  - `PREFER_PARENT_TO_CHILD`: no effect
- **Effect**: Creates a mapping between the existing `FamilyMember` and the existing `Person`
- **Expected rule**: ConcurrentMatchMemberPerson

- **Note**:
  The animator may also propose `Member2Person`, since the source member is not mapped yet.
  If `Member2Person` is selected, a new `Person` is created and mapped to the member.
  If `ConcurrentMatchMemberPerson` is selected, the existing matching `Person` is reused and only the mapping is created.

---

### ConcurrentTargetRenameWins
- **Input**: One family (Simpson) with a member (e.g., Lisa) and a mapped `Person` whose name has been modified inconsistently (e.g., `Simpson, Marie`)
- **Configuration**: `PREFER_FAMILY_TO_NEW = true`, `PREFER_PARENT_TO_CHILD = true`
  - `PREFER_FAMILY_TO_NEW`: no effect (no family creation)
  - `PREFER_PARENT_TO_CHILD`: no effect (no structural move)
- **Effect**: Propagates the `Person` name back to the `FamilyMember`
- **Expected rule**: ConcurrentTargetRenameWins

- **Note**:
  This rule resolves a concurrent rename conflict where the `Person` name has been modified
  while the corresponding `FamilyMember` still has the old name.

  The rule gives priority to the target model (Person side) and enforces backward propagation.

  In this configuration, the animator may also propose `BackwardRenameMemberName`,
  since both rules share equivalent guards (same mapping, same inconsistency, same strategy).

  In this minimal test case, both rules produce the same observable effect:
  updating the `FamilyMember` name to match the `Person`.

  The difference is conceptual: `BackwardRenameMemberName`is a standard backward propagation and `ConcurrentTargetRenameWins`: explicit conflict-resolution policy favoring the target

---

### ConcurrentDeleteMatched
- **Input**: Empty family model, empty person model, and one remaining empty mapping
- **Configuration**: `PREFER_FAMILY_TO_NEW = true`, `PREFER_PARENT_TO_CHILD = true`
  - `PREFER_FAMILY_TO_NEW`: no effect
  - `PREFER_PARENT_TO_CHILD`: no effect
- **Effect**: Removes the obsolete mapping after both mapped elements have disappeared
- **Expected rule**: ConcurrentDeleteMatched

- **Note**:
  This rule cleans up traceability information when both sides of a previously mapped pair
  have already been deleted.

---

## Notes

- All scenarios are **minimal** they are intended for:
  - debugging
  - rule validation
  - understanding how BCerT works

---