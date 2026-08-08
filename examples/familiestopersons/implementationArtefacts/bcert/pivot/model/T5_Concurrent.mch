MACHINE   T5_Concurrent

INCLUDES  MetaModel

DEFINITIONS
  SET_PREF_MAX_OPERATIONS == 1 ;
  CSP_GUIDE_FILE == "Concurrent.csp" ;
  "definitions.def" ;
  "LibraryStrings.def" ;
  "match.def" ;
  "BatchBwd.def";
  "concurrent.def";

SETS
  RULE = {
    ConcurrentDeleteMatched_,
    ConcurrentMatchMemberPerson_,
    ConcurrentTargetRenameWins_,

    Member2Person_,
    ForwardDelete_,
    ForwardRename_,

    Person2MemberExistingFamily_,
    Person2MemberNewFamily_,
    BackwardDelete_,
    BackwardRenameMemberName_
  }

INITIALISATION
  Reset

OPERATIONS

/*
 * Priority-based rule selection for concurrent propagation.
 *
 * The strategy enforces a deterministic resolution of inconsistencies:
 *
 * 1. ConcurrentDeleteMatched:
 *    Cleans obsolete mappings when both source and target elements disappeared.
 *
 * 2. ConcurrentMatchMemberPerson:
 *    Reuses existing elements by creating missing mappings when names match.
 *
 * 3. ConcurrentTargetRenameWins:
 *    Resolves rename conflicts by privileging the target (Person) side
 *    and propagating the change backward.
 *
 * If no concurrent rule applies, the system falls back to standard
 * forward or backward transformations:
 *
 * - FWD: delete > rename > create (Member2Person)
 * - BWD: delete > rename > create (Person2Member*)
 *
 * The direction is explicitly set (SetFWD / SetBWD), and one applicable
 * rule is selected non-deterministically.
 *
 * This ordering guarantees that inconsistencies are resolved before
 * applying structural transformations.
 */
appliedRule <-- propagate =
  SELECT  HasConcurrentDeleteMatched
  THEN    ConcurrentDeleteMatched || appliedRule := ConcurrentDeleteMatched_
  WHEN    HasConcurrentMatchMemberPerson
  THEN    ConcurrentMatchMemberPerson || appliedRule := ConcurrentMatchMemberPerson_
  WHEN    HasConcurrentTargetRenameWins
  THEN    SetBWD ; ConcurrentTargetRenameWins ; appliedRule := ConcurrentTargetRenameWins_
  ELSE
    CHOICE
      SetFWD ;
      SELECT  HasForwardDelete
      THEN    ForwardDelete || appliedRule := ForwardDelete_
      WHEN    HasForwardRename
      THEN    ForwardRename || appliedRule := ForwardRename_
      ELSE    Member2Person || appliedRule := Member2Person_
      END
    OR
      SetBWD ;
      SELECT  HasBackwardDelete
      THEN    BackwardDelete || appliedRule := BackwardDelete_
      WHEN    HasBackwardRename
      THEN    BackwardRenameMemberName || appliedRule := BackwardRenameMemberName_
      ELSE
        CHOICE
          Person2MemberExistingFamily || appliedRule := Person2MemberExistingFamily_
        OR
          Person2MemberNewFamily || appliedRule := Person2MemberNewFamily_
        END
      END
    END
  END;

fwd = BEGIN SetFWD END ;
bwd = BEGIN SetBWD END ;
sync = BEGIN SetSync END;
unsync = BEGIN SetUnSync END;

makeDecisionEAndP =
  BEGIN
    MakeDecision(TRUE, TRUE)
  END;

makeDecisionENotP =
  BEGIN
    MakeDecision(TRUE, FALSE)
  END;

makeDecisionNotENotP =
  BEGIN
    MakeDecision(FALSE, FALSE)
  END;

nameChangeOfLisaInFamily(mm) =
  PRE
    mm : FamilyMember &
    mm : theMembers &
    mm : dom(theDaughters) &
    Families_FamilyMember_name(mm) = "Lisa" &
    Families_Family_name(theDaughters(mm)) = "Simpson"
  THEN
    SetFamilyMemberName(mm, "Mary")
  END;

familyNameChangeOfLisaToVanHouten(pp) =
  PRE
    pp : thePersons &
    pp : Female &
    Persons_Person_name(pp) = "Simpson, Lisa"
  THEN
    SetPersonName(pp, "Van Houten, Lisa")
  END;

moveLisaToFlandersAsDaughter(mm, ff) =
  PRE
    mm : FamilyMember &
    mm : theMembers &
    mm : dom(theDaughters) &
    Families_FamilyMember_name(mm) = "Lisa" &
    Families_Family_name(theDaughters(mm)) = "Simpson" &

    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Flanders"
  THEN
    MoveDaughterAsDaughter(mm, ff)
  END;

createNewFamilyFlandersWithMembers =
  VAR aFamily IN
    aFamily <-- FamilyNEW("Flanders");
    AddFather(aFamily, "Ned");
    AddMother(aFamily, "Maude");
    AddSon(aFamily, "Rod");
    AddSon(aFamily, "Todd")
  END;

setBirthdayOfLisa =
  BEGIN
    SetBirthdayIfExists("Simpson, Lisa", "2013-03-08T10:11:12.000+0100")
  END;

createInitialFamilies =
  VAR Skinner, Flanders, Simpson IN
    Skinner <-- FamilyNEW("Skinner");
    Flanders <-- FamilyNEW("Flanders");
    AddSon(Flanders,"Rod");
    Simpson <-- FamilyNEW("Simpson");
    AddFather(Simpson,"Bart")
  END;

createNewFamilySimpsonWithMembers =
  VAR aFamily IN
    aFamily <-- FamilyNEW("Simpson");
    AddFather(aFamily, "Homer");
    AddMother(aFamily, "Marge");
    AddSon(aFamily, "Bart");
    AddDaughter(aFamily, "Lisa");
    AddDaughter(aFamily, "Maggie")
  END;

setBirthdayOfRod =
  BEGIN
    SetBirthdayIfExists("Flanders, Rod", "2013-10-01T11:11:12.000+0200")
  END;

setBirthdayOfFatherBart =
  BEGIN
    SetBirthdayOfDefaultPerson("Simpson, Bart", "2013-03-09T10:11:12.000+0100")
  END;

setBirthdayOfYoungerBart =
  BEGIN
    SetBirthdayOfDefaultPerson("Simpson, Bart", "2013-03-11T10:11:12.000+0100")
  END;

createFatherNed(ff) =
  PRE
    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Flanders" &
    ff /: ran(theFather)
  THEN
    AddFather(ff, "Ned")
  END;

nameChangeOfLisa(pp) =
  PRE
    pp : thePersons &
    pp : Female &
    Persons_Person_name(pp) = "Simpson, Lisa"
  THEN
    SetPersonName(pp, "Simpson, Marie")
  END;

deletePersonLisa(pp) =
  PRE
    pp : thePersons &
    pp : Female &
    Persons_Person_name(pp) = "Simpson, Lisa"
  THEN
    DeletePersonOnly(pp)
  END;

deleteLisa(mm) =
  PRE
    mm : FamilyMember &
    mm : theMembers &
    mm : dom(theDaughters) &
    Families_FamilyMember_name(mm) = "Lisa" &
    Families_Family_name(theDaughters(mm)) = "Simpson"
  THEN
    DeleteFamilyMemberOnly(mm)
  END;

deleteFatherHomer(mm) =
  PRE
    mm : FamilyMember &
    mm : theMembers &
    mm : dom(theFather) &
    Families_FamilyMember_name(mm) = "Homer" &
    Families_Family_name(theFather(mm)) = "Simpson"
  THEN
    DeleteFamilyMemberOnly(mm)
  END;

deleteRodAsSon(mm) =
  PRE
    mm : FamilyMember &
    mm : theMembers &
    mm : dom(theSons) &
    Families_FamilyMember_name(mm) = "Rod" &
    Families_Family_name(theSons(mm)) = "Flanders"
  THEN
    DeleteFamilyMemberOnly(mm)
  END;

deleteHomer(pp) =
  PRE
    pp : thePersons &
    pp : Male &
    Persons_Person_name(pp) = "Simpson, Homer"
  THEN
    DeletePersonOnly(pp)
  END;

deleteMaggie(pp) =
  PRE
    pp : thePersons &
    pp : Female &
    Persons_Person_name(pp) = "Simpson, Maggie"
  THEN
    DeletePersonOnly(pp)
  END;

deleteMarge(pp) =
  PRE
    pp : thePersons &
    pp : Female &
    Persons_Person_name(pp) = "Simpson, Marge"
  THEN
    DeletePersonOnly(pp)
  END;

createSonBart(ff) =
  PRE
    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Simpson" &
    FatherName(ff) = {"Homer"}
  THEN
    AddSon(ff, "Bart")
  END;

createSonBartInCompleteSimpson(ff) =
  PRE
    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Simpson" &
    FatherName(ff) = {"Homer"} &
    MotherName(ff) = {"Marge"}
  THEN
    AddSon(ff, "Bart")
  END;

createSeymour =
  BEGIN
    PersonNEW("Skinner, Seymour", TRUE)
  END;

setBirthdayOfSeymour =
  BEGIN
    SetBirthday("Skinner, Seymour", "2013-01-09T10:11:12.000+0100")
  END;

createSimpsonFamily =
  VAR ff IN
    ff <-- FamilyNEW("Simpson")
  END;

createFatherHomer(ff) =
  PRE
    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Simpson" &
    ff /: ran(theFather)
  THEN
    AddFather(ff, "Homer")
  END;

createHomer =
  BEGIN
    PersonNEW("Simpson, Homer", TRUE)
  END;

createBart =
  BEGIN
    PersonNEW("Simpson, Bart", TRUE)
  END;

changeAllBirthdays =
  BEGIN
    SetBirthdayIfExists("Simpson, Homer", "2013-01-09T10:11:12.000+0100");
    SetBirthdayIfExists("Simpson, Marge", "2013-02-09T10:11:12.000+0100");
    SetBirthdayIfExists("Simpson, Bart", "2013-03-10T10:11:12.000+0100");
    SetBirthdayIfExists("Simpson, Lisa", "2013-03-08T10:11:12.000+0100");
    SetBirthdayIfExists("Simpson, Maggie", "2013-03-07T10:11:12.000+0100");
    SetBirthdayIfExists("Flanders, Rod", "2013-10-01T11:11:12.000+0200")
  END;

ok <-- assertPrecondition =
    SELECT Pivot_name(pvtRoot) = "testDeleteRenameConflict" THEN
        ok <-- AssertPreCondition("Pre_Conflict")
    WHEN Pivot_name(pvtRoot) = "testMoveDeleteConflict" THEN
        ok <-- AssertPreCondition("Pre_Conflict")
    WHEN Pivot_name(pvtRoot) = "testMoveRenameConflict" THEN
        ok <-- AssertPreCondition("Pre_Conflict")
    WHEN Pivot_name(pvtRoot) = "testRenameRenameConflict" THEN
        ok <-- AssertPreCondition("Pre_Conflict")
    ELSE
        ok := FALSE
    END;

ok <-- assertPostcondition =
  SELECT Pivot_name(pvtRoot) = "testSuitableFamilyNonMatchingMember" THEN
    ok <-- AssertPostCondition("Post_BasicConcurrentEdit1")
  WHEN Pivot_name(pvtRoot) = "testSuitableFamilyMatchingMember" THEN
    ok <-- AssertPostCondition("Post_BasicConcurrentEdit2")
  WHEN Pivot_name(pvtRoot) = "testNonSuitableFamily" THEN
    ok <-- AssertPostCondition("Post_BasicConcurrentEdit3")
  WHEN Pivot_name(pvtRoot) = "testCombinedCases" THEN
    ok <-- AssertAnyPostCondition(
        "Post_BasicConcurrentEdit4",
        "Post_BasicConcurrentEdit5"
    )
  WHEN Pivot_name(pvtRoot) = "testMatchingDeletion" THEN
  ok <-- AssertPostCondition("Post_ConcMatchingDeletion")
  WHEN Pivot_name(pvtRoot) = "testNonMatchingDeletion" THEN
    ok <-- AssertPostCondition("Post_ConcNonMatchingDeletion")
  WHEN Pivot_name(pvtRoot) = "testCombinedDeletionCases" THEN
    ok <-- AssertPostCondition("Post_ConcCombinedDeletionCases")
  WHEN Pivot_name(pvtRoot) = "testCombinedDeletionAndCreation" THEN
    ok <-- AssertPostCondition("Post_ConcCombinedNonMonotonicCases")
  WHEN Pivot_name(pvtRoot) = "testCombinedRenameDelete" THEN
    ok <-- AssertPostCondition("Post_ConcCombinedNonMonotonicRenameDelete")
  WHEN Pivot_name(pvtRoot) = "testDeleteRenameConflict" THEN
    ok <-- AssertAnyPostCondition(
        "Post_DeleteRenameConflict_1",
        "Post_DeleteRenameConflict_2"
    )
  WHEN Pivot_name(pvtRoot) = "testMoveDeleteConflict" THEN
    ok <-- AssertAnyPostCondition3(
        "Post_MoveDeleteConflict_1",
        "Post_MoveDeleteConflict_2",
        "Post_MoveDeleteConflict_3"
    )
  WHEN Pivot_name(pvtRoot) = "testMoveRenameConflict" THEN
    ok <-- AssertAnyPostCondition(
        "Post_MoveRenameConflict_1",
        "Post_MoveRenameConflict_2"
    )
  WHEN Pivot_name(pvtRoot) = "testRenameRenameConflict" THEN
    ok <-- AssertAnyPostCondition3(
        "Post_RenameRenameConflict_1",
        "Post_RenameRenameConflict_2",
        "Post_RenameRenameConflict_3"
    )
  ELSE
    ok := FALSE
  END;

ok <-- testSuitableFamilyMatchingMember =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testSuitableFamilyMatchingMember"
    )
  END;

ok <-- testSuitableFamilyNonMatchingMember =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testSuitableFamilyNonMatchingMember"
    )
  END ;

ok <-- testNonSuitableFamily =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testNonSuitableFamily"
    )
  END;

ok <-- testCombinedCases =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCombinedCases"
    )
  END;

ok <-- testMatchingDeletion =
BEGIN
   ok := bool(
      Pivot_name(pvtRoot) = "testMatchingDeletion"
   )
END;

ok <-- testNonMatchingDeletion =
BEGIN
   ok := bool(
      Pivot_name(pvtRoot) = "testNonMatchingDeletion"
   )
END;

ok <-- testCombinedDeletionCases =
BEGIN
   ok := bool(
      Pivot_name(pvtRoot) = "testCombinedDeletionCases"
   )
END;

ok <-- testCombinedDeletionAndCreation =
BEGIN
   ok := bool(
      Pivot_name(pvtRoot) = "testCombinedDeletionAndCreation"
   )
END;

ok <-- testCombinedRenameDelete =
BEGIN
   ok := bool(
      Pivot_name(pvtRoot) = "testCombinedRenameDelete"
   )
END;

ok <-- testDeleteRenameConflict =
BEGIN
   ok := bool(
      Pivot_name(pvtRoot) = "testDeleteRenameConflict"
   )
END;

ok <-- testMoveDeleteConflict =
BEGIN
   ok := bool(
      Pivot_name(pvtRoot) = "testMoveDeleteConflict"
   )
END;

ok <-- testMoveRenameConflict =
BEGIN
   ok := bool(
      Pivot_name(pvtRoot) = "testMoveRenameConflict"
   )
END;

ok <-- testRenameRenameConflict =
BEGIN
   ok := bool(
      Pivot_name(pvtRoot) = "testRenameRenameConflict"
   )
END

END