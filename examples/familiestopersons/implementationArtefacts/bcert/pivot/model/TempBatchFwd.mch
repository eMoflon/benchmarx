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
      aFamily <-- FamilyNEW("Simpson") ;
      AddFather(aFamily, "Homer") ;
      AddMother(aFamily, "Marge") ;
      AddSon(aFamily, "Bart") ;
      AddDaughter(aFamily, "Lisa") ;
      AddDaughter(aFamily, "Maggie") ;
      aFamily <-- FamilyNEW("Flanders") ;
      AddFather(aFamily, "Ned") ;
      AddMother(aFamily, "Maude") ;
      AddSon(aFamily, "Rod") ;
      AddSon(aFamily, "Todd")
    END
  END ;
transformStep =
  PRE
    Family /= {} &
    theMembers /<: ran(mMap)
  THEN
    Member2Person
  END
END
