MACHINE   RoundTrip

INCLUDES  MetaModel

DEFINITIONS
  SET_PREF_MAX_OPERATIONS == 1 ;
  CSP_GUIDE_FILE == "RoundTrip.csp" ;
  "definitions.def" ;
  "LibraryStrings.def" ;
  "match.def" ;
  "BatchBwd.def"

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
        BackwardRenameMemberName_,
        BackwardMoveMaleToExistingFamily_,
        BackwardMoveFemaleToExistingFamily_,
        BackwardMoveMaleToNewFamily_,
        BackwardMoveFemaleToNewFamily_
    } 

INITIALISATION
  Reset 

OPERATIONS

appliedRule <-- propagate =
  CHOICE
    Member2Person || appliedRule := Member2Person_
  OR
    ForwardDelete || appliedRule := ForwardDelete_
  OR
    ForwardRename || appliedRule := ForwardRename_
  OR
    ConcurrentTargetRenameWins || appliedRule := ConcurrentTargetRenameWins_
  OR
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
  END ;

fwd = BEGIN SetFWD END ;
bwd = BEGIN SetBWD END ;
sync = BEGIN SetSync END;
unsync = BEGIN SetUnSync END;

makeDecisionENotP =
  BEGIN
    MakeDecision(TRUE, FALSE)
  END;

/* 
 * scenario ops 
 */

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

createSonBartInCompleteSimpson(ff) =
  PRE
    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Simpson" &
    FatherName(ff) = {"Homer"} 
  THEN
    AddSon(ff, "Bart")
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

changeAllBirthdays =
  BEGIN
    SetBirthdayIfExists("Simpson, Homer", "2013-01-09T10:11:12.000+0100");
    SetBirthdayIfExists("Simpson, Marge", "2013-02-09T10:11:12.000+0100");
    SetBirthdayOfDefaultPerson("Simpson, Bart", "2013-03-10T10:11:12.000+0100");
    SetBirthdayIfExists("Simpson, Lisa", "2013-03-08T10:11:12.000+0100");
    SetBirthdayIfExists("Simpson, Maggie", "2013-03-07T10:11:12.000+0100");
    SetBirthdayIfExists("Flanders, Rod", "2013-10-01T11:11:12.000+0200")
  END;

/* 
 * roundtrip edits 
 */

firstNameChangeOfHomer(pp) =
  PRE
    pp : thePersons &
    pp : Male &
    Persons_Person_name(pp) = "Simpson, Homer"
  THEN
    SetPersonName(pp, "Simpson, HomerX")
  END;

renameFlandersFamilyToBouvier(ff) =
  PRE
    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Flanders"
  THEN
    RenameFamily(ff, "Bouvier")
  END;

createSeymour =
  BEGIN
    PersonNEW("Skinner, Seymour", TRUE)
  END;

createSonTodd(ff) =
  PRE
    ff : Family &
    ff : families~[{fmlRoot}] &
    Families_Family_name(ff) = "Flanders"
  THEN
    AddSon(ff, "Todd")
  END;

deleteMarge(pp) =
  PRE
    pp : thePersons &
    pp : Female &
    Persons_Person_name(pp) = "Simpson, Marge"
  THEN
    DeletePersonOnly(pp)
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

/*
 * assertions
 */
 
ok <-- assertPrecondition =
  SELECT
    Pivot_name(pvtRoot) = "testRoundtripEdit" or
    Pivot_name(pvtRoot) = "testRoundtripAdd" or
    Pivot_name(pvtRoot) = "testRoundtripDelete"
  THEN
    ok <-- AssertPreCondition("Pre_IncrFwd")
  ELSE
    ok := FALSE
  END;

ok <-- assertPostcondition =
    SELECT Pivot_name(pvtRoot) = "testRoundtripEdit" THEN
        ok <-- AssertPostCondition("FamilyAfterRoundtripEdit")
    WHEN Pivot_name(pvtRoot) = "testRoundtripAdd" THEN
        ok <-- AssertPostCondition("FamilyAfterRoundtripAdd")
    WHEN Pivot_name(pvtRoot) = "testRoundtripDelete" THEN
        ok <-- AssertPostCondition("FamilyAfterRoundtripDelete")
    ELSE
        ok := FALSE
    END  ;

ok <-- testRoundtripEdit =
  BEGIN
    ok := bool(Pivot_name(pvtRoot) = "testRoundtripEdit")
  END;

ok <-- testRoundtripAdd =
  BEGIN
    ok := bool(Pivot_name(pvtRoot) = "testRoundtripAdd")
  END;

ok <-- testRoundtripDelete =
  BEGIN
    ok := bool(Pivot_name(pvtRoot) = "testRoundtripDelete")
  END

END