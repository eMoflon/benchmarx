MACHINE
  TestBackwardStep1
INCLUDES
  pivot
DEFINITIONS
	SET_PREF_MAX_OPERATIONS == 1 ;
    SET_PREF_SHOW_EVENTB_ANY_VALUES == TRUE ;
    SET_PREF_INVARIANT_CHECKING == TRUE ;
    SET_PREF_DEFAULT_SETSIZE == 1000 ;
INITIALISATION
  Reset
OPERATIONS

test =
BEGIN
   FamilyNEW("Flanders");
   PersonNEW("Flanders, Rod", TRUE);
   IF FAMILY_TO_NEW(pvtRoot)=TRUE THEN
      Person2MemberExistingFamily
   ELSE
      Person2MemberNewFamily
   END
END

END