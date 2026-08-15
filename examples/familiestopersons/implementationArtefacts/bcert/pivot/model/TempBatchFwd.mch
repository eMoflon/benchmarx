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
    MakeDecision(TRUE, TRUE)
  END ;
transformStep =
  PRE
    Family /= {} &
    theMembers /<: ran(mMap)
  THEN
    Member2Person
  END
END
