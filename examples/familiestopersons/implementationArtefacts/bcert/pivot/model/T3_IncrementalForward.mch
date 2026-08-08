MACHINE
  T3_IncrementalForward

INCLUDES
  MetaModel

DEFINITIONS
  SET_PREF_MAX_OPERATIONS == 1 ;
  CSP_GUIDE_FILE == "IncrementalForward.csp" ;
  "definitions.def" ;
  "LibraryStrings.def" ;
  "match.def" 

SETS
  RULE = {
    Member2Person_,
    ForwardDelete_,
    ForwardRename_,
    ForwardRepairGender_
  }

VARIABLES
    postSel
INVARIANT
    postSel : NAT
INITIALISATION
    Reset ||
    postSel := 1
OPERATIONS

appliedRule <-- propagate =
  CHOICE
    Member2Person || appliedRule := Member2Person_  /* Creates a person from a member */
  OR
    ForwardDelete || appliedRule := ForwardDelete_  /* Deletes a person when the corresponding member is deleted */
  OR
    ForwardRename || appliedRule := ForwardRename_
  OR
    ForwardRepairGender || appliedRule := ForwardRepairGender_
  END;

sync = BEGIN SetSync END;

unsync = BEGIN SetUnSync END;

idleDelta = BEGIN skip END;

moveMaggieAndChangeRole(mm, ff) =
  PRE
    mm : FamilyMember &
    mm : theMembers &
    mm : dom(theDaughters) &
    Families_FamilyMember_name(mm) = "Maggie" &
    Families_Family_name(theDaughters(mm)) = "Simpson" &
    FatherName(theDaughters(mm)) = {"Homer"} &
    MotherName(theDaughters(mm)) = {"Marge"} &

    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Flanders"
  THEN
    MoveDaughterAsSon(mm, ff)
  END;

hippocraticDelta =
  VAR aFamily IN
    aFamily <-- FamilyNEW("Van Houten")
  END;

/* renames family Simpson to Family Bouvier */ 
renameSimpsonToBouvier(ff) =
  PRE
    ff : Family &
    families(ff) = fmlRoot &
    Families_Family_name(ff) = "Simpson" &
    FatherName(ff) = {"Homer"} &
    MotherName(ff) = {"Marge"}
  THEN
    RenameFamily(ff, "Bouvier")
  END;

deleteFatherHomer(mm) =
  PRE
    mm : FamilyMember &
    mm : theMembers &
    mm : dom(theFather) &
    Families_FamilyMember_name(mm) = "Homer" &
    Families_Family_name(theFather(mm)) = "Simpson" &
    MotherName(theFather(mm)) = {"Marge"}
  THEN
    DeleteFamilyMemberOnly(mm)
  END;

createFatherHomer(ff) =
  PRE
    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Simpson" &
    MotherName(ff) = {"Marge"} &
    ff /: ran(theFather)
  THEN
    AddFather(ff, "Homer")
  END;

moveLisa(mm, ff) =
  PRE
    mm : FamilyMember &
    mm : theMembers &
    mm : dom(theDaughters) &
    Families_FamilyMember_name(mm) = "Lisa" &
    Families_Family_name(theDaughters(mm)) = "Simpson" &
    FatherName(theDaughters(mm)) = {"Homer"} &
    MotherName(theDaughters(mm)) = {"Marge"} &

    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Flanders"
  THEN
    MoveMemberAsMother(mm, ff)
  END;

moveMarge(mm, ff) =
  PRE
    mm : FamilyMember &
    mm : theMembers &
    mm : dom(theMother) &
    Families_FamilyMember_name(mm) = "Marge" &
    Families_Family_name(theMother(mm)) = "Simpson" &
    FatherName(theMother(mm)) = {"Homer"} &

    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Skinner"
  THEN
    MoveMemberAsMother(mm, ff)
  END;

/* Commun preamble: Skinner, Flanders/Rod, Simpson/Bart father */

createInitialFamilies =
  VAR Skinner, Flanders, Simpson IN
    Skinner <-- FamilyNEW("Skinner");
    Flanders <-- FamilyNEW("Flanders");
    AddSon(Flanders,"Rod");
    Simpson <-- FamilyNEW("Simpson");
    AddFather(Simpson,"Bart")
  END;

/* Target idle edits : birthdays */

setBirthdayOfRod =
  BEGIN
    SetBirthday("Flanders, Rod", "2013-10-01T10:11:12.000+0100")
  END;

setBirthdayOfFatherBart =
  BEGIN
    SetBirthdayOfDefaultPerson("Simpson, Bart", "2013-03-09T10:11:12.000+0100")
  END;

setBirthdayOfYoungerBart =
  BEGIN
    SetBirthdayOfDefaultPerson("Simpson, Bart", "2013-03-11T10:11:12.000+0100")
  END;

changeAllBirthdays =
  BEGIN
    SetBirthday("Flanders, Rod", "2013-10-01T10:11:12.000+0100");
    SetBirthdayOfDefaultPerson("Simpson, Bart", "2013-03-10T10:11:12.000+0100");
    SetBirthday("Simpson, Homer", "2013-01-09T10:11:12.000+0100");
    SetBirthday("Simpson, Marge", "2013-02-09T10:11:12.000+0100");
    SetBirthday("Simpson, Lisa", "2013-03-08T10:11:12.000+0100");
    SetBirthday("Simpson, Maggie", "2013-03-07T10:11:12.000+0100")
  END;


/* Source edits */

createNewFamilySimpsonWithMembers =
  VAR aFamily IN
    aFamily <-- FamilyNEW("Simpson");
    AddFather(aFamily, "Homer");
    AddMother(aFamily, "Marge");
    AddSon(aFamily, "Bart");
    AddDaughter(aFamily, "Lisa");
    AddDaughter(aFamily, "Maggie")
  END;

createSonBart(ff) =
  PRE
    ff : Family &
    families(ff) = fmlRoot &
    Families_Family_name(ff) = "Simpson" &
    FatherName(ff) = {"Homer"} &
    MotherName(ff) = {"Marge"}
  THEN
    AddSon(ff, "Bart")
  END;

createFatherNed =
  BEGIN
    FatherNEW("Flanders", "Ned")
  END;

createMotherMaude =
  BEGIN
    MotherNEW("Flanders", "Maude")
  END;

createSonTodd =
  BEGIN
    SonNEW("Flanders", "Todd")
  END;

deleteFirstSonBart(mm) =
  PRE
    mm : FamilyMember &
    mm : dom(theSons) &
    Families_FamilyMember_name(mm) = "Bart" &
    Families_Family_name(theSons(mm)) = "Simpson" &
    FatherName(theSons(mm)) = {"Homer"} &
    MotherName(theSons(mm)) = {"Marge"}
  THEN
    DeleteFamilyMemberOnly(mm)
  END;

/* Pre and Post assertions */

ok <-- assertPrecondition =
  SELECT Pivot_name(pvtRoot) = "testIncrementalInserts" THEN
    ok <-- AssertPreCondition("Pre_IncrFwd")
  WHEN Pivot_name(pvtRoot) = "testIncrementalDeletions" THEN
    ok <-- AssertPreCondition("Pre_IncrFwdForDeletion")
  WHEN Pivot_name(pvtRoot) = "testIncrementalRename" THEN
    ok <-- AssertPreCondition("Pre_IncrFwd")
  WHEN Pivot_name(pvtRoot) = "testIncrementalMove" THEN
    ok <-- AssertPreCondition("Pre_IncrFwd")
  WHEN Pivot_name(pvtRoot) = "testIncrementalMixed" THEN
    ok <-- AssertPreCondition("Pre_IncrFwd")
  WHEN Pivot_name(pvtRoot) = "testIncrementalMoveRoleChange" THEN
    ok <-- AssertPreCondition("Pre_IncrFwd")
  ELSE
    ok := FALSE
  END;

ok <-- assertPostcondition =
  SELECT Pivot_name(pvtRoot) = "testIncrementalInserts" THEN
    ok <-- AssertPostCondition("Post_IncrFwdInsert")
  WHEN Pivot_name(pvtRoot) = "testIncrementalDeletions" THEN
    ok <-- AssertPostCondition("Post_IncrFwdDeletion")
  WHEN Pivot_name(pvtRoot) = "testIncrementalRename" THEN
    ok <-- AssertPostCondition("Post_IncrFwdRename")
  WHEN Pivot_name(pvtRoot) = "testIncrementalMove" THEN
    ok <-- AssertPostCondition("Post_IncrFwdMove")
  WHEN Pivot_name(pvtRoot) = "testIncrementalMixed" THEN
    ok <-- AssertPostCondition("Post_IncrFwdMixed")
  WHEN Pivot_name(pvtRoot) = "testIncrementalMoveRoleChange" THEN
    ok <-- AssertPostCondition("Post_IncrFwdMoveRoleChange")
  WHEN Pivot_name(pvtRoot) = "testStability" THEN
    SELECT postSel = 1 THEN
        ok <-- AssertPostCondition("Post_StabilityStep1")
        || postSel := 2
    WHEN postSel = 2 THEN
        ok <-- AssertPostCondition("Post_StabilityStep2")
    ELSE
        ok := FALSE
    END
  WHEN Pivot_name(pvtRoot) = "testHippocraticness" THEN
    SELECT postSel = 1 THEN
        ok <-- AssertPostCondition("Post_HippocraticnessStep1") ||
        postSel := 2
    WHEN postSel = 2 THEN
        ok <-- AssertPostCondition("Post_HippocraticnessStep2")
    ELSE
        ok := FALSE
    END
  ELSE
    ok := FALSE
  END;

/* Test selector */

ok <-- testIncrementalInserts =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalInserts" &
      strategie(pvtRoot) = FWD
    )
  END ;

ok <-- testIncrementalDeletions =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalDeletions" &
      strategie(pvtRoot) = FWD
    )
  END ;

ok <-- testIncrementalRename =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalRename" &
      strategie(pvtRoot) = FWD
    )
  END;

ok <-- testIncrementalMove =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalMove" &
      strategie(pvtRoot) = FWD
    )
  END ;

ok <-- testIncrementalMixed =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalMixed" &
      strategie(pvtRoot) = FWD
    )
  END ;

ok <-- testIncrementalMoveRoleChange =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testIncrementalMoveRoleChange" &
      strategie(pvtRoot) = FWD
    )
  END ;

ok <-- testStability =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testStability" &
      strategie(pvtRoot) = FWD
    )
  END;

ok <-- testHippocraticness =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testHippocraticness" &
      strategie(pvtRoot) = FWD
    )
  END

END