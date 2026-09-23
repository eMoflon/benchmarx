MACHINE TempBatchBwd
INCLUDES MetaModel
DEFINITIONS
  SET_PREF_MAX_OPERATIONS == 100000 ;
  "definitions.def" ;
  "LibraryStrings.def" ;
  "BatchBwd.def" ;
  "concurrent.def"
VARIABLES
  setupStep
INVARIANT
  setupStep : INT
INITIALISATION
  Reset ||
  setupStep := 0
OPERATIONS
setup_init =
  PRE setupStep = 0 & pvtRoot /: dom(strategie) THEN
    SetBWD || SetUnSync || MakeDecision(TRUE, TRUE) || setupStep := 1
  END ;
setup_fam_1 =
  PRE setupStep = 1 THEN
    BEGIN VAR aFam1 IN aFam1 <-- FamilyNEW("Skinner") END || setupStep := 2 END
  END ;
setup_fam_2 =
  PRE setupStep = 2 THEN
    BEGIN VAR aFam2 IN aFam2 <-- FamilyNEW("Flanders") ; AddSon(aFam2, "Rod") END || setupStep := 3 END
  END ;
setup_fam_3 =
  PRE setupStep = 3 THEN
    BEGIN VAR aFam3 IN aFam3 <-- FamilyNEW("Simpson") ; AddFather(aFam3, "Bart") END || setupStep := 4 END
  END ;
setup_fam_4 =
  PRE setupStep = 4 THEN
    BEGIN VAR aFam4 IN aFam4 <-- FamilyNEW("Simpson") ; AddFather(aFam4, "Homer") ; AddSon(aFam4, "Bart") ; AddSon(aFam4, "Bart") ; AddDaughter(aFam4, "Lisa") ; AddDaughter(aFam4, "Maggie") END || setupStep := 5 END
  END ;
setup_person_1 =
  PRE setupStep = 5 THEN
    BEGIN PersonNEW("Flanders, Rod", TRUE) ; SetBirthday("Flanders, Rod", "2013-10-01") ; RegisterMapping("Flanders, Rod", "Flanders", "Rod") || setupStep := 6 END
  END ;
setup_person_2 =
  PRE setupStep = 6 THEN
    BEGIN PersonNEW("Simpson, Bart", TRUE) ; SetBirthday("Simpson, Bart", "2013-03-09") ; RegisterMapping("Simpson, Bart", "Simpson", "Bart") || setupStep := 7 END
  END ;
setup_person_3 =
  PRE setupStep = 7 THEN
    BEGIN PersonNEW("Simpson, Homer", TRUE) ; SetBirthday("Simpson, Homer", "2013-01-09") ; RegisterMapping("Simpson, Homer", "Simpson", "Homer") || setupStep := 8 END
  END ;
setup_person_4 =
  PRE setupStep = 8 THEN
    BEGIN PersonNEW("Simpson, Bart", TRUE) ; SetBirthday("Simpson, Bart", "2013-03-10") ; RegisterMapping("Simpson, Bart", "Simpson", "Bart") || setupStep := 9 END
  END ;
setup_person_5 =
  PRE setupStep = 9 THEN
    BEGIN PersonNEW("Simpson, Bart", TRUE) ; SetBirthday("Simpson, Bart", "2013-03-11") ; RegisterMapping("Simpson, Bart", "Simpson", "Bart") || setupStep := 10 END
  END ;
setup_person_6 =
  PRE setupStep = 10 THEN
    BEGIN PersonNEW("Simpson, Lisa", FALSE) ; SetBirthday("Simpson, Lisa", "2013-03-08") ; RegisterMapping("Simpson, Lisa", "Simpson", "Lisa") || setupStep := 11 END
  END ;
setup_person_7 =
  PRE setupStep = 11 THEN
    BEGIN PersonNEW("Simpson, Maggie", FALSE) ; SetBirthday("Simpson, Maggie", "2013-03-07") ; RegisterMapping("Simpson, Maggie", "Simpson", "Maggie") || setupStep := 12 END
  END ;
transformStep =
  PRE
    setupStep = 12 &
    ((Person /= {} & thePersons /<: dom(mapped)) or
    (theMembers /<: ran(mMap)) or
    HasConcurrentTargetRenameWins)
  THEN
    SELECT
      HasConcurrentTargetRenameWins
    THEN
      ConcurrentTargetRenameWins
    WHEN
      #(mm,pp).(
        mm : theMembers & mm /: ran(mMap) &
        pp : thePersons & pp /: dom(mapped) &
        (
          Persons_Person_name(pp) = STRING_CONC([Families_Family_name(familyOf(mm)), ", ", Families_FamilyMember_name(mm)]) or
          #(map).(map : Mapping & pMap(map) = pp & mMap(map) = mm)
        )
      )
    THEN
      ConcurrentMatchMemberPerson
    WHEN
      #(mm).(
        mm : theMembers & mm /: ran(mMap) &
        not(#pp.(
          pp : thePersons & pp /: dom(mapped) &
          (
            Persons_Person_name(pp) = STRING_CONC([Families_Family_name(familyOf(mm)), ", ", Families_FamilyMember_name(mm)]) or
            #(map).(map : Mapping & pMap(map) = pp & mMap(map) = mm)
          )
        ))
      )
    THEN
      BackwardDeleteFamilyMember
    WHEN
      FAMILY_TO_NEW(pvtRoot) = TRUE &
      #(pp,ff).(
        pp : Person &
        pp : thePersons &
        pp /: dom(mapped) &
        ff : Family &
        ff : families~[{fmlRoot}] &
        Families_Family_name(ff) = PersonFamilyName(pp)
      )
    THEN
      Person2MemberExistingFamily
    ELSE
      Person2MemberNewFamily
    END
  END
END
