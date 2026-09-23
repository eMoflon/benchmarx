MACHINE TempBatchFwd
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
    Family = {}
  THEN
    SetFWD ||
    SetUnSync ||
    MakeDecision(TRUE, TRUE) ||
    VAR aFamily IN
      aFamily <-- FamilyNEW("Skinner") ;
      aFamily <-- FamilyNEW("Flanders") ;
      AddSon(aFamily, "Rod") ;
      aFamily <-- FamilyNEW("Simpson") ;
      AddFather(aFamily, "Bart") ;
      aFamily <-- FamilyNEW("Simpson") ;
      AddFather(aFamily, "Homer") ;
      AddMother(aFamily, "Marge") ;
      AddSon(aFamily, "Bart") ;
      AddSon(aFamily, "Bart") ;
      AddDaughter(aFamily, "Lisa") ;
      AddDaughter(aFamily, "Maggie")
    END
  END ;
transformStep =
  PRE
    Family /= {} &
    theMembers /<: ran(mMap)
  THEN
    Member2Person
  END ;
setBirthdayStep =
  PRE
    Family /= {} &
    theMembers <: ran(mMap) &
    ran(birthday) = {"DEFAULT"}
  THEN
    BEGIN
      SetSonBirthdayIfExists("Flanders", "Rod", "2013-10-01") ;
      SetFatherBirthdayIfExists("Simpson", "Bart", "2013-03-09") ;
      SetFatherBirthdayIfExists("Simpson", "Homer", "2013-01-09") ;
      SetMotherBirthdayIfExists("Simpson", "Marge", "2013-02-09") ;
      SetSonBirthdayIfExists("Simpson", "Bart", "2013-03-10") ;
      SetDaughterBirthdayIfExists("Simpson", "Lisa", "2013-03-08") ;
      SetDaughterBirthdayIfExists("Simpson", "Maggie", "2013-03-07")
    END
  END
END
