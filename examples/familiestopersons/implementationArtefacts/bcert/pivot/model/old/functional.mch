MACHINE 
	
	functional

SETS
	
	Strategy = {BWD,FWD};
	FAMILY;
	FAMILYMEMBER;
	FAMILYREGISTER;
	PIVOT;
	PRECONDITION;
	POSTCONDITION;
	MAPPING;
	PERSON;
	PERSONREGISTER
	STR1

DEFINITIONS
	 "definitions.def" ;
	 
CONSTANTS
	
	FamilyRegister,
	Pivot,
	PreCondition,
	PostCondition,
	PersonRegister,
	
	outputPre,
	inputPre,
	outputPost,
	inputPost

PROPERTIES
	
	FamilyRegister : FIN(FAMILYREGISTER) &
	Pivot : FIN(PIVOT) &
	PreCondition : FIN(PRECONDITION) &
	PostCondition : FIN(POSTCONDITION) &
	PersonRegister : FIN(PERSONREGISTER) &
	
	outputPre : PreCondition --> PersonRegister &
	inputPre : PreCondition --> FamilyRegister &
	outputPost : PostCondition --> PersonRegister &
	inputPost : PostCondition --> FamilyRegister

VARIABLES
	
	Family,
	FamilyMember,
	Mapping,
	Person,
	Male,
	Female,
	theFather,
	theMother,
	theSons,
	theDaughters,
	families,
	familyModel,
	personModel,
	pre,
	post,
	mMap,
	pMap,
	mapping,
	persons,
	strategie,
	FAMILY_TO_NEW,
	PARENT_TO_CHILD,
	
	famName,
	firstName,
	fullName

INVARIANT
	
	Family 	: FIN(FAMILY) &
	FamilyMember : FIN(FAMILYMEMBER) &
	Mapping : FIN(MAPPING) &
	Person 	: FIN(PERSON) &
	Male 	<: Person &
	Female 	<: Person &
	theFather : FamilyMember >+> Family &
	theMother : FamilyMember >+> Family &
	theSons : FamilyMember +-> Family &
	theDaughters : FamilyMember +-> Family &
	families : Family --> FamilyRegister & /* not mondatory in the call */
	familyModel : Pivot --> FamilyRegister &
	personModel : Pivot --> PersonRegister &
	pre 	: PreCondition +-> Pivot &
	post 	: PostCondition +-> Pivot &
	
	mMap 	: Mapping --> FamilyMember &
	pMap 	: Mapping --> Person &
	
	mapping : Mapping --> Pivot &
	persons : Person --> PersonRegister & /* not mondatory in the call */
	
	strategie : Pivot --> Strategy &
	FAMILY_TO_NEW : Pivot --> BOOL &
	PARENT_TO_CHILD : Pivot --> BOOL &
	
	Male /\ Female = {} &
	
	/* abstract variable for proofs */
	famName : Family --> STR1 &
	firstName : FamilyMember --> STR1 &
	fullName : Person --> STR2 &
	
	/* Correctness properties */
	familyOf : FamilyMember --> Family

INITIALISATION
	
	Family := {} ||
	FamilyMember := {} ||
	Mapping := {} ||
	Person := {} ||
	Male := {} ||
	Female := {} ||
	theFather := {} ||
	theMother := {} ||
	theSons := {} ||
	theDaughters := {} ||
	families := {} ||
	familyModel := {} ||
	personModel := {} ||
	pre := {} ||
	post := {} ||
	mMap := {} ||
	pMap := {} ||
	mapping := {} ||
	persons := {} ||
	
	strategie := {} ||
	FAMILY_TO_NEW := {} ||
	PARENT_TO_CHILD := {} ||
	famName := {} ||
	firstName := {} ||
	fullName := {} 

OPERATIONS

MemberToPerson = BEGIN skip END ;

PersonToMember = BEGIN skip END
	
END
