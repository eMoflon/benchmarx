MACHINE   T4_IncrementalBackward

INCLUDES  MetaModel

DEFINITIONS
  SET_PREF_MAX_OPERATIONS == 1 ;
  SET_PREF_MAXINT == 10 ;
  CSP_GUIDE_FILE == "IncrementalBackward.csp" ;
  "definitions.def" ;
  "LibraryStrings.def" ;
  "match.def" ;
  "BatchBwd.def"

SETS
  RULE = {
    Person2MemberExistingFamily_,
    Person2MemberNewFamily_,
    BackwardDelete_,
    BackwardRenameMemberName_,
    BackwardMoveMaleToExistingFamily_,
    BackwardMoveFemaleToExistingFamily_,
    BackwardMoveMaleToNewFamily_,
    BackwardMoveFemaleToNewFamily_
  }

VARIABLES
  postSel

INVARIANT
  postSel : NAT

INITIALISATION
  Reset || postSel := 1

OPERATIONS

appliedRule <-- propagate =
  CHOICE
    Person2MemberExistingFamily || appliedRule := Person2MemberExistingFamily_
  OR
    Person2MemberNewFamily || appliedRule := Person2MemberNewFamily_
  OR
    BackwardDelete || appliedRule := BackwardDelete_
  OR
    BackwardRenameMemberName || appliedRule := BackwardRenameMemberName_
  OR
    BackwardMoveMaleToExistingFamily || appliedRule := BackwardMoveMaleToExistingFamily_
  OR
    BackwardMoveFemaleToExistingFamily || appliedRule := BackwardMoveFemaleToExistingFamily_
  OR
    BackwardMoveMaleToNewFamily || appliedRule := BackwardMoveMaleToNewFamily_
  OR
    BackwardMoveFemaleToNewFamily || appliedRule := BackwardMoveFemaleToNewFamily_
  END;

makeDecisionEAndP =
  BEGIN
    MakeDecision(TRUE, TRUE)
  END;

makeDecisionENotP =
  BEGIN
    MakeDecision(TRUE, FALSE)
  END;

makeDecisionNotEAndP =
  BEGIN
    MakeDecision(FALSE, TRUE)
  END;

makeDecisionNotENotP =
  BEGIN
    MakeDecision(FALSE, FALSE)
  END;

idleDelta = BEGIN skip END ;

sync = BEGIN SetSync END;

unsync = BEGIN SetUnSync END;

createBart =
  BEGIN
    PersonNEW("Simpson, Bart", TRUE)
  END;

createLisa =
  BEGIN
    PersonNEW("Simpson, Lisa", FALSE)
  END;

createHomer =
  BEGIN
    PersonNEW("Simpson, Homer", TRUE)
  END;

createMaggie =
  BEGIN
    PersonNEW("Simpson, Maggie", FALSE)
  END;

createSeymour =
  BEGIN
    PersonNEW("Skinner, Seymour", TRUE)
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

setBirthdayOfMaggie =
  BEGIN
    SetBirthday("Simpson, Maggie", "2013-03-07T10:11:12.000+0100")
  END;

setBirthdaysOfSimpson =
  BEGIN
    SetBirthday("Simpson, Homer", "2013-01-09T10:11:12.000+0100");
    SetBirthday("Simpson, Maggie", "2013-03-07T10:11:12.000+0100")
  END;

firstNameChangeOfBart(pp) =
  PRE
    pp : thePersons &
    pp : Male &
    Persons_Person_name(pp) = "Simpson, Bart" &
    birthday(pp) = "2013-03-10T10:11:12.000+0100"
  THEN
    SetPersonName(pp, "Simpson, Bartholomew")
  END;

fullNameChangeOfOtherBart(pp) =
  PRE
    pp : thePersons &
    pp : Male &
    Persons_Person_name(pp) = "Simpson, Bart" &
    birthday(pp) = "2013-03-11T10:11:12.000+0100"
  THEN
    SetPersonName(pp, "Skinner, Seymour")
  END;

fullNameChangeOfFatherBart(pp) =
  PRE
    pp : thePersons &
    pp : Male &
    Persons_Person_name(pp) = "Simpson, Bart" &
    birthday(pp) = "2013-03-09T10:11:12.000+0100"
  THEN
    SetPersonName(pp, "Flanders, Todd")
  END;

familyNameChangeOfLisa(pp) =
  PRE
    pp : thePersons &
    pp : Female &
    Persons_Person_name(pp) = "Simpson, Lisa"
  THEN
    SetPersonName(pp, "Flanders, Lisa")
  END;

fullNameChangeOfMarge(pp) =
  PRE
    pp : thePersons &
    pp : Female &
    Persons_Person_name(pp) = "Simpson, Marge"
  THEN
    SetPersonName(pp, "Flanders, Maude")
  END;

createRod =
  BEGIN
    PersonNEW("Flanders, Rod", TRUE)
  END;

createMarge =
  BEGIN
    PersonNEW("Simpson, Marge", FALSE)
  END;

setBirthdayOfRod =
  BEGIN
    SetBirthday("Flanders, Rod", "2013-10-01T10:11:12.000+0100")
  END;

changeAllBirthdays =
  BEGIN
    SetBirthday("Flanders, Rod", "2013-10-01T10:11:12.000+0100");
    SetBirthday("Simpson, Homer", "2013-01-09T10:11:12.000+0100");
    SetBirthday("Simpson, Marge", "2013-02-09T10:11:12.000+0100");
    SetBirthday("Simpson, Lisa", "2013-03-08T10:11:12.000+0100");
    SetBirthday("Simpson, Maggie", "2013-03-07T10:11:12.000+0100");
    SetBirthdayOfDefaultPerson("Simpson, Bart", "2013-03-10T10:11:12.000+0100")
  END;

setBirthdayOfYoungerBart =
  BEGIN
    SetBirthdayOfDefaultPerson("Simpson, Bart", "2013-03-11T10:11:12.000+0100")
  END;

setBirthdayOfFatherBart =
  BEGIN
    SetBirthdayOfDefaultPerson("Simpson, Bart", "2013-03-09T10:11:12.000+0100")
  END;

hippocraticDelta =
  BEGIN
    SetBirthday("Flanders, Rod", "2013-03-09T10:11:12.000+0100");
    SetBirthday("Simpson, Homer", "2013-03-09T10:11:12.000+0100");
    SetBirthday("Simpson, Marge", "2013-03-09T10:11:12.000+0100")
  END;

ok <-- assertPrecondition =
  SELECT Pivot_name(pvtRoot) = "testIncrementalInsertsFixedConfig" THEN
    ok <-- AssertPreCondition("Pre_IncrBwd")
  WHEN Pivot_name(pvtRoot) = "testIncrementalInsertsDynamicConfig" THEN
    ok <-- AssertPreCondition("Pre_IncrBwd")
  WHEN Pivot_name(pvtRoot) = "testIncrementalDeletions" THEN
    ok <-- AssertPreCondition("Pre_IncrBwdFatherChild")
  WHEN Pivot_name(pvtRoot) = "testIncrementalRenamingDynamic" THEN
    ok <-- AssertPreCondition("Pre_IncrBwdRenameDynamic")
  WHEN Pivot_name(pvtRoot) = "testIncrementalMixedDynamic" THEN
    ok <-- AssertPreCondition("Pre_IncrBwd")
  WHEN Pivot_name(pvtRoot) = "testIncrementalOperational" THEN
    ok <-- AssertPreCondition("Pre_IncrBwdOp")
  ELSE
    ok := FALSE
  END;

ok <-- assertPostcondition =
  SELECT Pivot_name(pvtRoot) = "testIncrementalInsertsFixedConfig" THEN
    SELECT postSel = 1 THEN
      ok <-- AssertPostCondition("Post_IncrBwdInsertion1") ||
      postSel := 2
    WHEN postSel = 2 THEN
      ok <-- AssertPostCondition("Post_IncrBwdInsertion2")
    ELSE
      ok := FALSE
    END
  WHEN Pivot_name(pvtRoot) = "testIncrementalInsertsDynamicConfig" THEN
    SELECT postSel = 1 THEN
        ok <-- AssertPostCondition("Post_IncrBwdInsertion1") ||
        postSel := 2
    WHEN postSel = 2 THEN
        ok <-- AssertPostCondition("Post_IncrBwdInsertion2") ||
        postSel := 3
    WHEN postSel = 3 THEN
        ok <-- AssertPostCondition("Post_IncrBwdInsertion3") ||
        postSel := 4
    WHEN postSel = 4 THEN
        ok <-- AssertPostCondition("Post_IncrBwdInsertion4") ||
        postSel := 5
    WHEN postSel = 5 THEN
        ok <-- AssertPostCondition("Post_IncrBwdInsertion5") ||
        postSel := 6
    WHEN postSel = 6 THEN
        ok <-- AssertPostCondition("Post_IncrBwdInsertion6")
    ELSE
        ok := FALSE
    END
  WHEN Pivot_name(pvtRoot) = "testIncrementalDeletions" THEN
    ok <-- AssertPostCondition("Post_IncrBwdDeletion")
  WHEN Pivot_name(pvtRoot) = "testIncrementalRenamingDynamic" THEN
    ok <-- AssertPostCondition("Post_IncrBwdRenameDynamic")
  WHEN Pivot_name(pvtRoot) = "testIncrementalMixedDynamic" THEN
    ok <-- AssertPostCondition("Post_IncrBwdMixed")
  WHEN Pivot_name(pvtRoot) = "testIncrementalOperational" THEN
    ok <-- AssertPostCondition("Post_IncrBwdOperational")
  WHEN Pivot_name(pvtRoot) = "testStability" THEN
    SELECT postSel = 1 THEN
        ok <-- AssertPostCondition("Post_Stability") ||
        postSel := 2
    WHEN postSel = 2 THEN
        ok <-- AssertPostCondition("Post_Stability")
    ELSE
        ok := FALSE
    END
  WHEN Pivot_name(pvtRoot) = "testHippocraticness" THEN
    SELECT postSel = 1 THEN
        ok <-- AssertPostCondition("Post_Hippocraticness") ||
        postSel := 2
    WHEN postSel = 2 THEN
        ok <-- AssertPostCondition("Post_Hippocraticness2")
    ELSE
        ok := FALSE
    END
  ELSE
    ok := FALSE
  END;

ok <-- testIncrementalInsertsFixedConfig =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalInsertsFixedConfig" &
      strategie(pvtRoot) = BWD
    )
  END;

ok <-- testIncrementalInsertsDynamicConfig =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalInsertsDynamicConfig" &
      strategie(pvtRoot) = BWD
    )
  END;

ok <-- testIncrementalDeletions =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalDeletions" &
      strategie(pvtRoot) = BWD
    )
  END;

ok <-- testIncrementalRenamingDynamic =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalRenamingDynamic" &
      strategie(pvtRoot) = BWD
    )
  END;

ok <-- testIncrementalMixedDynamic =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalMixedDynamic" &
      strategie(pvtRoot) = BWD
    )
  END;

ok <-- testIncrementalOperational =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalOperational" &
      strategie(pvtRoot) = BWD
    )
  END;

ok <-- testStability =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testStability" &
      strategie(pvtRoot) = BWD
    )
  END;

ok <-- testHippocraticness =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testHippocraticness" &
      strategie(pvtRoot) = BWD
    )
  END

END