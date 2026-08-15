MACHINE TempBatchBwd
INCLUDES MetaModel
DEFINITIONS
  SET_PREF_MAX_OPERATIONS == 100000 ;
  "definitions.def" ;
  "LibraryStrings.def" ;
  "BatchBwd.def"
INITIALISATION
  Reset
OPERATIONS
setupModel = 
  PRE
    Person = {}
  THEN
    SetBWD ||
    SetUnSync ||
    MakeDecision(TRUE, TRUE) ||
    BEGIN
      PersonNEW("Flanders, Rod", TRUE) ;
      PersonNEW("Simpson, Homer", TRUE) ;
      PersonNEW("Simpson, Marge", FALSE)
    END
  END ;
transformStep =
  PRE
    Person /= {} &
    thePersons /<: dom(mapped)
  THEN
    SELECT
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
