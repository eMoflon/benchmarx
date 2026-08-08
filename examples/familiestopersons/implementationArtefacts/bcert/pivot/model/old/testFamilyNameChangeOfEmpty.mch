MACHINE
  testFamilyNameChangeOfEmpty
INCLUDES
  pivot
DEFINITIONS
    SET_PREF_MAX_OPERATIONS == 1 ;
    SET_PREF_SHOW_EVENTB_ANY_VALUES == TRUE ;
    SET_PREF_INVARIANT_CHECKING == TRUE ;
    SET_PREF_DEFAULT_SETSIZE == 1000 ;
    
PROMOTES
  Member2Person

OPERATIONS

/* 
 * tool.noPrecondition();
 * util.assertPostcondition("RootElementFamilies", "RootElementPersons");
 */
noPrecondition = 
	BEGIN
		Reset 
    END ;

ok <-- assertPostcondition = 
	SELECT
		state = check
		& rrExpected : FAMILYREGISTER
	THEN
		skip /*ok := SameFamilies(rrExpected, fmlRoot) assertPostcondition */
      	|| state := end
    END 
 
/* 
 * tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSimpsonFamily));
 * util.assertPrecondition("Pre_NameChangeFamilyEmpty", "Pre_NameChangePersonEmpty");
 * tool.performAndPropagateSourceEdit(srcEdit(helperFamily::renameEmptySimpsonToBouvier));
 * util.assertPostcondition("NameChangeFamilyEmpty", "NameChangePersonEmpty");
 */

END