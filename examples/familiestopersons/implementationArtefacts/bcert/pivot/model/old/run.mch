MACHINE run
INCLUDES pivot

DEFINITIONS
    SET_PREF_MAX_OPERATIONS == 1 ;
    SET_PREF_SHOW_EVENTB_ANY_VALUES == FALSE ;
    SET_PREF_INVARIANT_CHECKING == FALSE ;
    SET_PREF_DEFAULT_SETSIZE == 10 ;

OPERATIONS

addPerson = 
ANY pp, pr, na WHERE 
	pp : PERSON & pp /: Person 
	& pr : PersonRegister
	& na : STRING
THEN
	Female_NEW(pp) ;
	AddPersons(pr,pp) ;
	Persons_Person_SetName(pp, na)
END

END