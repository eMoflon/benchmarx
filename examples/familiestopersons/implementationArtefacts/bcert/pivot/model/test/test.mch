MACHINE test
DEFINITIONS 
  CSP_GUIDE_FILE == "test.csp"
VARIABLES 
	nom, ok
INVARIANT 
	nom : STRING
	& ok : BOOL
INITIALISATION
nom := "toto" ;
ok := bool(nom="toti")
OPERATIONS
tesOP = BEGIN skip END
END