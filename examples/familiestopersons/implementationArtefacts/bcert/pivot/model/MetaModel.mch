MACHINE 
	
	MetaModel

DEFINITIONS

	"definitions.def" ;
	"match.def";
	"LibraryStrings.def";
	"BatchBwd.def"

SETS
	
	Strategy = {BWD,FWD};
	FAMILYREGISTER;
	FAMILY;
	FAMILYMEMBER;
	PIVOT;
	PRECONDITION;
	POSTCONDITION;
	MAPPING;
	PERSONREGISTER;
	PERSON
	/*;STR1*/

CONSTANTS
	
	FamilyRegister,
	PreCondition,
	PostCondition,
	PersonRegister,
	PreCondition_name,
	PostCondition_name,
	outputPre,
	inputPre,
	outputPost,
	inputPost,
	Pivot,
	familyModel,
	personModel,
    	
	pvtRoot,
	fmlRoot,
	prsRoot

PROPERTIES
	card(FAMILYREGISTER) = 10 &
	card(FAMILY) = 50 &
	card(FAMILYMEMBER) = 100 &
	card(PIVOT) = 1 &
	card(PRECONDITION) = 10 &
	card(POSTCONDITION) = 10 &
	card(MAPPING) = card(FAMILYMEMBER)  &
	card(PERSONREGISTER) = 10  &
	card(PERSON) = card(FAMILYMEMBER)  &
	
	FamilyRegister : FIN(FAMILYREGISTER) &
	PreCondition : FIN(PRECONDITION) &
	PostCondition : FIN(POSTCONDITION) &
	PersonRegister : FIN(PERSONREGISTER) &
	PreCondition_name : PreCondition --> STR1 &
	PostCondition_name : PostCondition --> STR1 &
	outputPre : PreCondition --> PersonRegister &
	inputPre : PreCondition --> FamilyRegister &
	outputPost : PostCondition --> PersonRegister &
	inputPost : PostCondition --> FamilyRegister &
	
	Pivot : FIN(PIVOT) &
	familyModel : Pivot --> FamilyRegister &
	personModel : Pivot --> PersonRegister
	
	& pvtRoot : Pivot 
	& fmlRoot = familyModel(pvtRoot) 
	& prsRoot = personModel(pvtRoot) 
	
	/* correctness properties */
	& card(Pivot) = 1

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
	pre,
	post,
	mMap,
	pMap,
	mapping,
	persons,
	Families_Family_name,
	Families_FamilyMember_name,
	Pivot_name,	
	FAMILY_TO_NEW,
	PARENT_TO_CHILD,
	strategie,
	
	sync,
	Persons_Person_name,
	Person_name,

	birthday

INVARIANT

	Family : FIN(FAMILY) &
	FamilyMember : FIN(FAMILYMEMBER) &
	Mapping : FIN(MAPPING) &
	Person : FIN(PERSON) &
	Male <: Person &
	Female <: Person &
	theFather : FamilyMember >+> Family &
	theMother : FamilyMember >+> Family &
	theSons : FamilyMember +-> Family &
	theDaughters : FamilyMember +-> Family &
	families : Family +-> FamilyRegister &
	
	pre : PreCondition +-> Pivot &
	post : PostCondition +-> Pivot &
	mMap : Mapping +-> FamilyMember &
	pMap : Mapping +-> Person &
	mapping : Mapping --> Pivot &
	persons : Person +-> PersonRegister &
	Pivot_name : Pivot --> STR1 &
	birthday : Person +-> STR1 &
	
	FAMILY_TO_NEW : Pivot --> BOOL &
	PARENT_TO_CHILD : Pivot --> BOOL &
	strategie : Pivot --> Strategy &

	sync : Pivot --> BOOL &
	Male /\ Female = {} &
	
	Families_Family_name : Family +-> STR1 &
	Families_FamilyMember_name : FamilyMember +-> STR1 &
	Persons_Person_name : Person +-> STR1 
	& Person_name : Person +-> STR2 /* We use sequence for proofs */

	/* correctness invariants */
	& familyOf : FamilyMember --> Family /* a member cannot be outside a Family */ 
	& families : Family --> FamilyRegister /* a family cannot be outside the register */ 
	& Families_FamilyMember_name : FamilyMember --> STR1 /* Mandatory member name */
	& Persons_Person_name : Person --> STR1 /* Mandatory person name */
	& persons : Person --> PersonRegister /* a person cannot be outside the register */ 
	&
	/*& (
		sync(pvtRoot) = TRUE
		=>*/
		mapped[Male] <: dom(theSons) \/ dom(theFather) &
		mapped[Female] <: dom(theDaughters) \/ dom(theMother)
	/*)*/
	
ASSERTIONS
    ! mm . (mm : FamilyMember & not(isFemale(mm)) => mm : dom(theFather \/ theSons))
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
	pre := {} ||
	post := {} ||
	mMap := {} ||
	pMap := {} ||
	mapping := {} ||
	persons := {} ||
	Families_Family_name := {} ||
	Families_FamilyMember_name := {} ||
	Pivot_name := {} ||
	strategie := {} ||
	sync := {} ||
	FAMILY_TO_NEW := {} ||
	PARENT_TO_CHILD := {} ||
	Persons_Person_name := {} ||
	Person_name := {} ||
	birthday := {}

OPERATIONS

/* 
 * Start Transformation Rules
 */ 
Member2Person =
    ANY aMember, aPerson, aMapping WHERE
        aMember : theMembers
        & aMember /: ran(mMap)
        & aPerson : PERSON
        & aPerson /: Person
        & aMapping : MAPPING
        & aMapping /: Mapping
        & strategie(pvtRoot) = FWD
		& sync(pvtRoot) = FALSE
    THEN
        IF isFemale(aMember) THEN
            Female := Female \/ {aPerson}
        ELSE
            Male := Male \/ {aPerson}
        END
        || Person := Person \/ {aPerson}
		|| birthday := birthday <+ {aPerson |-> "DEFAULT"}
        || Persons_Person_name(aPerson) := 
        		STRING_CONC([Families_Family_name(familyOf(aMember)), 
        					", ", Families_FamilyMember_name(aMember)])
        || persons := persons <+ {aPerson |-> prsRoot}
        || Mapping := Mapping \/ {aMapping}
        || mapping := mapping <+ {aMapping |-> pvtRoot}
        || mMap := mMap <+ {aMapping |-> aMember}
        || pMap := pMap <+ {aMapping |-> aPerson}
END ;

ForwardRepairGender =
  ANY aMapping, aMember, aPerson WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping : dom(mMap) &
    aMapping : dom(pMap) &
    aMember = mMap(aMapping) &
    aPerson = pMap(aMapping) &
    strategie(pvtRoot) = FWD &
    sync(pvtRoot) = FALSE &
    (
      (aPerson : Female & not(isFemale(aMember))) or
      (aPerson : Male & isFemale(aMember))
    )
  THEN
    IF isFemale(aMember) THEN
      Female := Female \/ {aPerson} ||
      Male := Male - {aPerson}
    ELSE
      Male := Male \/ {aPerson} ||
      Female := Female - {aPerson}
    END
  END;

ForwardDelete =
  ANY aMapping, aPerson WHERE
    aMapping : Mapping &
    aMapping : dom(pMap) &
    aMapping /: dom(mMap) &
    mapping(aMapping) = pvtRoot &
    aPerson : Person &
    pMap(aMapping) = aPerson &
    strategie(pvtRoot) = FWD &
    sync(pvtRoot) = FALSE
  THEN
    Person := Person - {aPerson} ||
    Male := Male - {aPerson} ||
    Female := Female - {aPerson} ||
    persons := {aPerson} <<| persons ||
    Persons_Person_name := {aPerson} <<| Persons_Person_name ||
    Person_name := {aPerson} <<| Person_name ||
    birthday := {aPerson} <<| birthday ||

    Mapping := Mapping - {aMapping} ||
    mapping := {aMapping} <<| mapping ||
    pMap := {aMapping} <<| pMap
  END ;

ForwardRename =
  ANY aMapping, aMember, aPerson WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping : dom(mMap) &
    aMapping : dom(pMap) &
    aMember = mMap(aMapping) &
    aPerson = pMap(aMapping) &
    Persons_Person_name(aPerson) /=
      STRING_CONC([
        Families_Family_name(familyOf(aMember)),
        ", ",
        Families_FamilyMember_name(aMember)
      ]) &
    strategie(pvtRoot) = FWD &
    sync(pvtRoot) = FALSE
  THEN
    Persons_Person_name(aPerson) :=
      STRING_CONC([
        Families_Family_name(familyOf(aMember)),
        ", ",
        Families_FamilyMember_name(aMember)
      ])
  END;

ForwardCreateMissingPerson =
  ANY aMapping, aMember, aPerson WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping : dom(mMap) &
    aMapping /: dom(pMap) &
    aMember = mMap(aMapping) &
    aMember : theMembers &

    aPerson : PERSON &
    aPerson /: Person &

    strategie(pvtRoot) = FWD &
    sync(pvtRoot) = FALSE
  THEN
    IF isFemale(aMember) THEN
      Female := Female \/ {aPerson}
    ELSE
      Male := Male \/ {aPerson}
    END ||

    Person := Person \/ {aPerson} ||
    birthday := birthday <+ {aPerson |-> "DEFAULT"} ||
    Persons_Person_name :=
      Persons_Person_name <+ {
        aPerson |->
          STRING_CONC([
            Families_Family_name(familyOf(aMember)),
            ", ",
            Families_FamilyMember_name(aMember)
          ])
      } ||
    persons := persons <+ {aPerson |-> prsRoot} ||
    pMap := pMap <+ {aMapping |-> aPerson}
  END;

Person2MemberNewFamily =
  ANY aPerson, aFamily, aMember, aMapping WHERE
    aPerson : thePersons & aPerson /: dom(mapped) & aPerson /: ran(pMap) &
    aFamily : FAMILY & aFamily /: Family &
    aMember : FAMILYMEMBER & aMember /: FamilyMember &
    aMapping : MAPPING & aMapping /: Mapping &
    
	strategie(pvtRoot) = BWD &
    sync(pvtRoot) = FALSE &
    aPerson : dom(Persons_Person_name) &
    
	1 : dom(PersonSplit(aPerson)) &
    2 : dom(PersonSplit(aPerson)) &

	(
		FAMILY_TO_NEW(pvtRoot) = FALSE
		or
		not(#ff.(
			ff : families~[{fmlRoot}] &
			Families_Family_name(ff) = PersonFamilyName(aPerson)
		))
	) 
  THEN
    Family := Family \/ {aFamily} ||
    families := families <+ {aFamily |-> fmlRoot} ||
    Families_Family_name := Families_Family_name <+ {aFamily |-> PersonFamilyName(aPerson)} ||

    FamilyMember := FamilyMember \/ {aMember} ||
    Families_FamilyMember_name := Families_FamilyMember_name <+ {aMember |-> PersonMemberName(aPerson)} ||

    IF aPerson : Male THEN
      IF PARENT_TO_CHILD(pvtRoot) = TRUE THEN
        theFather := theFather \/ {aMember |-> aFamily}
      ELSE
        theSons := theSons \/ {aMember |-> aFamily}
      END
    ELSE
      IF PARENT_TO_CHILD(pvtRoot) = TRUE THEN
        theMother := theMother \/ {aMember |-> aFamily}
      ELSE
        theDaughters := theDaughters \/ {aMember |-> aFamily}
      END
    END ||

    Mapping := Mapping \/ {aMapping} ||
    mapping := mapping <+ {aMapping |-> pvtRoot} ||
    mMap := mMap <+ {aMapping |-> aMember} ||
    pMap := pMap <+ {aMapping |-> aPerson}
  END ;

Person2MemberExistingFamily =
  ANY aPerson, aMember, aMapping, aFamily WHERE

    aPerson : thePersons &
    aPerson /: dom(mapped) &
    aPerson /: ran(pMap) &

    aFamily : Family &
    aFamily : families~[{fmlRoot}] &
    Families_Family_name(aFamily) = PersonFamilyName(aPerson) &

    aMember : FAMILYMEMBER &
    aMember /: FamilyMember &

    aMapping : MAPPING &
    aMapping /: Mapping &

    strategie(pvtRoot) = BWD &
    FAMILY_TO_NEW(pvtRoot) = TRUE &
    sync(pvtRoot) = FALSE

  THEN
    FamilyMember := FamilyMember \/ {aMember} ||

    Families_FamilyMember_name :=
      Families_FamilyMember_name <+ {aMember |-> PersonMemberName(aPerson)} ||

    IF aPerson : Male THEN
      IF PARENT_TO_CHILD(pvtRoot) = TRUE & aFamily /: ran(theFather) THEN
        theFather := theFather \/ {aMember |-> aFamily}
      ELSE
        theSons := theSons \/ {aMember |-> aFamily}
      END
    ELSE
      IF PARENT_TO_CHILD(pvtRoot) = TRUE & aFamily /: ran(theMother) THEN
        theMother := theMother \/ {aMember |-> aFamily}
      ELSE
        theDaughters := theDaughters \/ {aMember |-> aFamily}
      END
    END ||

    Mapping := Mapping \/ {aMapping} ||
    mapping := mapping <+ {aMapping |-> pvtRoot} ||
    mMap := mMap <+ {aMapping |-> aMember} ||
    pMap := pMap <+ {aMapping |-> aPerson}

  END;

BackwardDelete =
  ANY aMapping, aMember WHERE
    aMapping : Mapping &
    aMapping : dom(mMap) &
    aMapping /: dom(pMap) &
    mapping(aMapping) = pvtRoot &

    aMember : FamilyMember &
    aMember = mMap(aMapping) &

    strategie(pvtRoot) = BWD &
    sync(pvtRoot) = FALSE
  THEN
    FamilyMember := FamilyMember - {aMember} ||

    theFather := {aMember} <<| theFather ||
    theMother := {aMember} <<| theMother ||
    theSons := {aMember} <<| theSons ||
    theDaughters := {aMember} <<| theDaughters ||
    Families_FamilyMember_name := {aMember} <<| Families_FamilyMember_name ||

    Mapping := Mapping - {aMapping} ||
    mapping := {aMapping} <<| mapping ||
    mMap := {aMapping} <<| mMap
  END;

BackwardRenameMemberName =
  ANY aMapping, aMember, aPerson WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping : dom(mMap) &
    aMapping : dom(pMap) &
    aMember = mMap(aMapping) &
    aPerson = pMap(aMapping) &

    PersonFamilyName(aPerson) = Families_Family_name(familyOf(aMember)) &
    PersonMemberName(aPerson) /= Families_FamilyMember_name(aMember) &

    strategie(pvtRoot) = BWD &
    sync(pvtRoot) = FALSE
  THEN
    Families_FamilyMember_name :=
      Families_FamilyMember_name <+ {aMember |-> PersonMemberName(aPerson)}
  END;

BackwardMoveMaleToExistingFamily =
  ANY aMapping, aMember, aPerson, aFamily WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping : dom(mMap) &
    aMapping : dom(pMap) &
    aMember = mMap(aMapping) &
    aPerson = pMap(aMapping) &

    aPerson : Male &
    PersonFamilyName(aPerson) /= Families_Family_name(familyOf(aMember)) &

    FAMILY_TO_NEW(pvtRoot) = TRUE &
    aFamily : Family &
    aFamily : families~[{fmlRoot}] &
    Families_Family_name(aFamily) = PersonFamilyName(aPerson) &

    strategie(pvtRoot) = BWD &
    sync(pvtRoot) = FALSE
  THEN
    Families_FamilyMember_name :=
      Families_FamilyMember_name <+ {aMember |-> PersonMemberName(aPerson)} ||

    theMother := {aMember} <<| theMother ||
    theDaughters := {aMember} <<| theDaughters ||

    IF PARENT_TO_CHILD(pvtRoot) = TRUE & aFamily /: ran(theFather) THEN
      theFather := ({aMember} <<| theFather) \/ {aMember |-> aFamily} ||
      theSons := {aMember} <<| theSons
    ELSE
      theFather := {aMember} <<| theFather ||
      theSons := ({aMember} <<| theSons) \/ {aMember |-> aFamily}
    END
  END;

BackwardMoveFemaleToExistingFamily =
  ANY aMapping, aMember, aPerson, aFamily WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping : dom(mMap) &
    aMapping : dom(pMap) &
    aMember = mMap(aMapping) &
    aPerson = pMap(aMapping) &

    aPerson : Female &
    PersonFamilyName(aPerson) /= Families_Family_name(familyOf(aMember)) &

    FAMILY_TO_NEW(pvtRoot) = TRUE &
    aFamily : Family &
    aFamily : families~[{fmlRoot}] &
    Families_Family_name(aFamily) = PersonFamilyName(aPerson) &

    strategie(pvtRoot) = BWD &
    sync(pvtRoot) = FALSE
  THEN
    Families_FamilyMember_name :=
      Families_FamilyMember_name <+ {aMember |-> PersonMemberName(aPerson)} ||

    theFather := {aMember} <<| theFather ||
    theSons := {aMember} <<| theSons ||

    IF PARENT_TO_CHILD(pvtRoot) = TRUE & aFamily /: ran(theMother) THEN
      theMother := ({aMember} <<| theMother) \/ {aMember |-> aFamily} ||
      theDaughters := {aMember} <<| theDaughters
    ELSE
      theMother := {aMember} <<| theMother ||
      theDaughters := ({aMember} <<| theDaughters) \/ {aMember |-> aFamily}
    END
  END;

BackwardMoveMaleToNewFamily =
  ANY aMapping, aMember, aPerson, aFamily WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping : dom(mMap) &
    aMapping : dom(pMap) &
    aMember = mMap(aMapping) &
    aPerson = pMap(aMapping) &

    aPerson : Male &

    PersonFamilyName(aPerson) /= Families_Family_name(familyOf(aMember)) &

    Persons_Person_name(aPerson) /=
      STRING_CONC([
        Families_Family_name(familyOf(aMember)),
        ", ",
        Families_FamilyMember_name(aMember)
      ]) &

    (
      FAMILY_TO_NEW(pvtRoot) = FALSE
      or
      not(#ff.(ff : families~[{fmlRoot}] &
               Families_Family_name(ff) = PersonFamilyName(aPerson)))
    ) &

    aFamily : FAMILY &
    aFamily /: Family &

    strategie(pvtRoot) = BWD &
    sync(pvtRoot) = FALSE
  THEN
    Family := Family \/ {aFamily} ||
    families := families <+ {aFamily |-> fmlRoot} ||
    Families_Family_name :=
      Families_Family_name <+ {aFamily |-> PersonFamilyName(aPerson)} ||

    Families_FamilyMember_name :=
      Families_FamilyMember_name <+ {aMember |-> PersonMemberName(aPerson)} ||

    theMother := {aMember} <<| theMother ||
    theDaughters := {aMember} <<| theDaughters ||

    IF PARENT_TO_CHILD(pvtRoot) = TRUE THEN
      theFather := ({aMember} <<| theFather) \/ {aMember |-> aFamily} ||
      theSons := {aMember} <<| theSons
    ELSE
      theFather := {aMember} <<| theFather ||
      theSons := ({aMember} <<| theSons) \/ {aMember |-> aFamily}
    END
  END;

BackwardMoveFemaleToNewFamily =
  ANY aMapping, aMember, aPerson, aFamily WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping : dom(mMap) &
    aMapping : dom(pMap) &
    aMember = mMap(aMapping) &
    aPerson = pMap(aMapping) &

    aPerson : Female &

    /* vrai changement de famille */
    PersonFamilyName(aPerson) /= Families_Family_name(familyOf(aMember)) &

    /* éviter faux positif dû à un simple rename de famille */
    Persons_Person_name(aPerson) /=
      STRING_CONC([
        Families_Family_name(familyOf(aMember)),
        ", ",
        Families_FamilyMember_name(aMember)
      ]) &

    (
      FAMILY_TO_NEW(pvtRoot) = FALSE
      or
      not(#ff.(ff : families~[{fmlRoot}] &
               Families_Family_name(ff) = PersonFamilyName(aPerson)))
    ) &

    aFamily : FAMILY &
    aFamily /: Family &

    strategie(pvtRoot) = BWD &
    sync(pvtRoot) = FALSE
  THEN
    Family := Family \/ {aFamily} ||
    families := families <+ {aFamily |-> fmlRoot} ||
    Families_Family_name :=
      Families_Family_name <+ {aFamily |-> PersonFamilyName(aPerson)} ||

    Families_FamilyMember_name :=
      Families_FamilyMember_name <+ {aMember |-> PersonMemberName(aPerson)} ||

    theFather := {aMember} <<| theFather ||
    theSons := {aMember} <<| theSons ||

    IF PARENT_TO_CHILD(pvtRoot) = TRUE THEN
      theMother := ({aMember} <<| theMother) \/ {aMember |-> aFamily} ||
      theDaughters := {aMember} <<| theDaughters
    ELSE
      theMother := {aMember} <<| theMother ||
      theDaughters := ({aMember} <<| theDaughters) \/ {aMember |-> aFamily}
    END
  END;

ConcurrentMatchMemberPerson =
  ANY aMember, aPerson, aMapping WHERE
    aMember : theMembers &
    aMember /: ran(mMap) &

    aPerson : thePersons &
    aPerson /: dom(mapped) &

    Persons_Person_name(aPerson) =
      STRING_CONC([
        Families_Family_name(familyOf(aMember)),
        ", ",
        Families_FamilyMember_name(aMember)
      ]) &

    aMapping : MAPPING &
    aMapping /: Mapping &

    sync(pvtRoot) = FALSE
  THEN
    Mapping := Mapping \/ {aMapping} ||
    mapping := mapping <+ {aMapping |-> pvtRoot} ||
    mMap := mMap <+ {aMapping |-> aMember} ||
    pMap := pMap <+ {aMapping |-> aPerson}
  END;

ConcurrentDeleteMatched =
  ANY aMapping WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping /: dom(mMap) &
    aMapping /: dom(pMap) &
    sync(pvtRoot) = FALSE
  THEN
    Mapping := Mapping - {aMapping} ||
    mapping := {aMapping} <<| mapping
  END;


/*
 * Resolves concurrent target-side rename conflicts.
 * If a mapped Person has been renamed while the corresponding FamilyMember
 * still keeps the old name, the target-side rename takes precedence over
 * ForwardRename. The rule propagates the new Person name back to the Family
 * model and forces backward repair, preventing the rename from being undone.
 */
ConcurrentTargetRenameWins =
  ANY aMapping, aMember, aPerson WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping : dom(mMap) &
    aMapping : dom(pMap) &
    aMember = mMap(aMapping) &
    aPerson = pMap(aMapping) &

    PersonFamilyName(aPerson) = Families_Family_name(familyOf(aMember)) &
    PersonMemberName(aPerson) /= Families_FamilyMember_name(aMember) &
	strategie(pvtRoot) = BWD &
    sync(pvtRoot) = FALSE
  THEN
    
    Families_FamilyMember_name :=
      Families_FamilyMember_name <+ {aMember |-> PersonMemberName(aPerson)}
  END;

ConcurrentDeleteWins =
  ANY aMapping, aMember, aPerson WHERE
    aMapping : Mapping &
    mapping(aMapping) = pvtRoot &
    aMapping : dom(mMap) &
    aMapping : dom(pMap) &
    aMember = mMap(aMapping) &
    aPerson = pMap(aMapping) &

    /* target rename détecté */
    PersonMemberName(aPerson) /= Families_FamilyMember_name(aMember) &

    /* source delete détecté */
    aMember /: theMembers &

    sync(pvtRoot) = FALSE
  THEN
    /* on force la suppression côté cible */
    Person := Person - {aPerson} ||
    Male := Male - {aPerson} ||
    Female := Female - {aPerson} ||
    persons := {aPerson} <<| persons ||
    Persons_Person_name := {aPerson} <<| Persons_Person_name ||
    birthday := {aPerson} <<| birthday ||

    Mapping := Mapping - {aMapping} ||
    mapping := {aMapping} <<| mapping ||
    pMap := {aMapping} <<| pMap ||
    mMap := {aMapping} <<| mMap
  END;

/* 
 * End Transformation Rules 
 */ 


/*
 * Begin Utility Operations 
 */

SetFWD =
  BEGIN
    strategie(pvtRoot) := FWD
  END;

SetBWD =
  BEGIN
    strategie(pvtRoot) := BWD
  END;

MakeDecision(toNew, toChild) =
  PRE
    toNew : BOOL &
    toChild : BOOL
  THEN
    FAMILY_TO_NEW := FAMILY_TO_NEW <+ {pvtRoot |-> toNew} ||
    PARENT_TO_CHILD := PARENT_TO_CHILD <+ {pvtRoot |-> toChild}
  END;

SetFamilyMemberName(aMember, newName) =
  PRE
    aMember : FamilyMember &
    aMember : theMembers &
    newName : STR1
  THEN
    Families_FamilyMember_name :=
      Families_FamilyMember_name <+ {aMember |-> newName}
  END;

SetBirthdayIfExists(aName, aBirthday) =
  PRE
    aName : STR1 &
    aBirthday : STR1
  THEN
    IF #pp.(pp : Person & pp : persons~[{prsRoot}] & Persons_Person_name(pp) = aName) THEN
      ANY aPerson WHERE
        aPerson : Person &
        aPerson : persons~[{prsRoot}] &
        Persons_Person_name(aPerson) = aName
      THEN
        birthday := birthday <+ {aPerson |-> aBirthday}
      END
    ELSE
      skip
    END
  END;
  
MoveDaughterAsSon(aMember, aFamily) =
/*
 * This would violate the invariant if the daughter is already mapped
 * inv: mapped[Female] <: dom(theDaughters) \/ dom(theMother)
 */
  PRE
    aMember : FamilyMember &
    aMember : theMembers &
    aMember : dom(theDaughters) &
    aFamily : Family &
    aFamily : families~[{fmlRoot}]
  THEN
    theDaughters := {aMember} <<| theDaughters ||
    theSons := theSons \/ {aMember |-> aFamily}
  END;

MoveDaughterAsDaughter(aMember, aFamily) =
  PRE
    aMember : FamilyMember &
    aMember : theMembers &
    aMember : dom(theDaughters) &
    aFamily : Family &
    aFamily : families~[{fmlRoot}]
  THEN
    theDaughters := ({aMember} <<| theDaughters) \/ {aMember |-> aFamily}
  END;

MoveMemberAsMother(aMember, aFamily) =
  PRE
    aMember : FamilyMember &
    aMember : theMembers &
    aFamily : Family &
    aFamily : families~[{fmlRoot}] &
    aFamily /: ran(theMother)
  THEN
    theFather := {aMember} <<| theFather ||
    theMother := ({aMember} <<| theMother) \/ {aMember |-> aFamily} ||
    theSons := {aMember} <<| theSons ||
    theDaughters := {aMember} <<| theDaughters
  END;

SetBirthdayOfDefaultPerson(aName, aBirthday) =
  PRE
    aName : STR1 &
    aBirthday : STR1
  THEN
    ANY aPerson WHERE
      aPerson : persons~[{prsRoot}] &
      Persons_Person_name(aPerson) = aName &
      birthday(aPerson) = "DEFAULT"
    THEN
      birthday := birthday <+ {aPerson |-> aBirthday}
    END
  END;

RenameFamily(aFamily, newName) =
  PRE
    aFamily : Family &
    families(aFamily) = fmlRoot &
    newName : STR1
  THEN
    Families_Family_name := Families_Family_name <+ {aFamily |-> newName}
  END;

DeletePersonOnly(aPerson) =
  PRE
    aPerson : Person &
    aPerson : thePersons
  THEN
    Person := Person - {aPerson} ||
    Male := Male - {aPerson} ||
    Female := Female - {aPerson} ||
    persons := {aPerson} <<| persons ||
    Persons_Person_name := {aPerson} <<| Persons_Person_name ||
    Person_name := {aPerson} <<| Person_name ||
    birthday := {aPerson} <<| birthday ||
    pMap := pMap |>> {aPerson}
  END;

DeleteFamilyMemberOnly(aMember) =
  PRE
    aMember : FamilyMember &
    aMember : theMembers
  THEN
    FamilyMember := FamilyMember - {aMember} ||
    theFather := {aMember} <<| theFather ||
    theMother := {aMember} <<| theMother ||
    theSons := {aMember} <<| theSons ||
    theDaughters := {aMember} <<| theDaughters ||
    Families_FamilyMember_name := {aMember} <<| Families_FamilyMember_name ||
    mMap := mMap |>> {aMember}
  END ;

ok <-- AssertPreCondition(preName) =
  PRE
    preName : STR1 &
    mapped : thePersons >-> theMembers &
    dom(mapped) = thePersons &
    ran(mapped) = theMembers
  THEN
    ANY pc WHERE
      pc : PreCondition &
      PreCondition_name(pc) = preName
    THEN
      ok := bool(
        SameFamilies(inputPre(pc), fmlRoot) = TRUE &
        SamePersons(outputPre(pc), prsRoot) = TRUE
      )
    END
  END;
  
ok <-- AssertPostCondition(postName) =
  PRE
    postName : STR1 &
    mapped : thePersons >-> theMembers &
    dom(mapped) = thePersons &
    ran(mapped) = theMembers
  THEN
    ANY pc WHERE
      pc : PostCondition &
      PostCondition_name(pc) = postName
    THEN
      ok := bool(
        SameFamilies(inputPost(pc), fmlRoot) = TRUE &
        SamePersons(outputPost(pc), prsRoot) = TRUE
      )
    END
  END;

ok <-- AssertAnyPostCondition(postName1, postName2) =
  PRE
    postName1 : STR1 &
    postName2 : STR1 &
    mapped : thePersons >-> theMembers &
    dom(mapped) = thePersons &
    ran(mapped) = theMembers
  THEN
    ANY pc1, pc2 WHERE
      pc1 : PostCondition &
      pc2 : PostCondition &
      PostCondition_name(pc1) = postName1 &
      PostCondition_name(pc2) = postName2
    THEN
      ok := bool(
        (
          SameFamilies(inputPost(pc1), fmlRoot) = TRUE &
          SamePersons(outputPost(pc1), prsRoot) = TRUE
        )
        or
        (
          SameFamilies(inputPost(pc2), fmlRoot) = TRUE &
          SamePersons(outputPost(pc2), prsRoot) = TRUE
        )
      )
    END
  END;

ok <-- AssertAnyPostCondition3(postName1, postName2, postName3) =
  PRE
    postName1 : STR1 &
    postName2 : STR1 &
    postName3 : STR1 &
    mapped : thePersons >-> theMembers &
    dom(mapped) = thePersons &
    ran(mapped) = theMembers
  THEN
    ANY pc1, pc2, pc3 WHERE
      pc1 : PostCondition &
      pc2 : PostCondition &
      pc3 : PostCondition &
      PostCondition_name(pc1) = postName1 &
      PostCondition_name(pc2) = postName2 &
      PostCondition_name(pc3) = postName3
    THEN
      ok := bool(
        (
          SameFamilies(inputPost(pc1), fmlRoot) = TRUE &
          SamePersons(outputPost(pc1), prsRoot) = TRUE
        )
        or
        (
          SameFamilies(inputPost(pc2), fmlRoot) = TRUE &
          SamePersons(outputPost(pc2), prsRoot) = TRUE
        )
        or
        (
          SameFamilies(inputPost(pc3), fmlRoot) = TRUE &
          SamePersons(outputPost(pc3), prsRoot) = TRUE
        )
      )
    END
  END;

Reset =
  ANY oldFamilies, oldMembers, oldMappings, oldPersons WHERE
    oldFamilies = families~[{fmlRoot}] &
    oldMembers  = familyOf~[oldFamilies] &
    oldMappings = mapping~[{pvtRoot}] &
    oldPersons  = persons~[{prsRoot}]
  THEN
    Family := Family - oldFamilies ||
    FamilyMember := FamilyMember - oldMembers ||
    Mapping := Mapping - oldMappings ||
    Person := Person - oldPersons ||

    theFather := theFather |>> oldFamilies ||
    theMother := theMother |>> oldFamilies ||
    theSons := theSons |>> oldFamilies ||
    theDaughters := theDaughters |>> oldFamilies ||
    families := oldFamilies <<| families ||

    mMap := (oldMappings <<| mMap) |>> oldMembers ||
    pMap := (oldMappings <<| pMap) |>> oldPersons ||
    mapping := oldMappings <<| mapping ||

    persons := oldPersons <<| persons ||
    Male := Male - oldPersons ||
    Female := Female - oldPersons ||

    Families_Family_name := oldFamilies <<| Families_Family_name ||
    Families_FamilyMember_name := oldMembers <<| Families_FamilyMember_name ||
    Persons_Person_name := oldPersons <<| Persons_Person_name ||
    Person_name := oldPersons <<| Person_name ||
	birthday := oldPersons <<| birthday ||

    sync := sync <+ {pvtRoot |-> FALSE}
  END;
    
SetSync =
  SELECT
    sync(pvtRoot) = FALSE &

    mapped : thePersons >-> theMembers &
    dom(mapped) = thePersons &
    ran(mapped) = theMembers &

    /* no pending insert */
    !mm.( mm : theMembers => mm : ran(mMap) ) &

    /* no pending delete */
    !mp.( mp : Mapping & mapping(mp) = pvtRoot
      => mp : dom(mMap) & mp : dom(pMap)
    ) &

    /* no pending rename/update */
    !mp.(
      mp : Mapping & mp : dom(mMap) & mp : dom(pMap) & mapping(mp) = pvtRoot
      =>
      Persons_Person_name(pMap(mp)) =
        STRING_CONC([
          Families_Family_name(familyOf(mMap(mp))),
          ", ",
          Families_FamilyMember_name(mMap(mp))
        ])
    ) &
	mapped[Male] <: dom(theSons) \/ dom(theFather) &
    mapped[Female] <: dom(theDaughters) \/ dom(theMother)
  THEN
    sync(pvtRoot) := TRUE
  END;

SetUnSync = 
	BEGIN
		sync(pvtRoot) := FALSE
	END;

Sync(val) = 
	PRE val : BOOL THEN
   	  sync(pvtRoot) := val
	END;

out <-- FamilyNEW(aName) = 
	PRE	aName : STR1 THEN 
	  ANY aFamily WHERE
	        aFamily : FAMILY & aFamily /: Family
	  THEN
	        Family := Family \/ {aFamily}
        	|| families := families <+ {(aFamily |-> fmlRoot)}
        	|| Families_Family_name := Families_Family_name <+ {(aFamily |-> aName)}
        	|| out := aFamily
      END
	END;
out <-- FamilySetName(oldName, newName) = 
	PRE	
		oldName : STR1 & newName : STR1
	THEN 
	  ANY aFamily WHERE
	        aFamily : Family & Families_Family_name(aFamily) = oldName
			& families(aFamily) = fmlRoot
	  THEN
        	Families_Family_name := Families_Family_name <+ {(aFamily |-> newName)}
        	|| out := aFamily
      END
	END;

SonNEW(aFamilyName, aSonName) =
  PRE
    aSonName : STR1 &
    aFamilyName : STR1 
  THEN
    ANY aFamily, aMember WHERE
		aFamily : Family & Families_Family_name(aFamily) = aFamilyName
		& families(aFamily) = fmlRoot
		& aMember : FAMILYMEMBER 
		& aMember /: FamilyMember
    THEN
		FamilyMember := FamilyMember \/ {aMember} ||
		theSons := theSons \/ {aMember |-> aFamily} ||
		Families_FamilyMember_name := Families_FamilyMember_name <+ {aMember |-> aSonName} 
    END
  END;

DaughterNEW(aFamilyName, aDaughterName) =
  PRE
    aDaughterName : STR1 &
    aFamilyName : STR1 
  THEN
    ANY aFamily, aMember WHERE
		aFamily : Family & Families_Family_name(aFamily) = aFamilyName
		& families(aFamily) = fmlRoot
		& aMember : FAMILYMEMBER 
		& aMember /: FamilyMember
    THEN
		FamilyMember := FamilyMember \/ {aMember} ||
		theDaughters := theDaughters \/ {aMember |-> aFamily} ||
		Families_FamilyMember_name := Families_FamilyMember_name <+ {aMember |-> aDaughterName} 
    END
  END;

FatherNEW(aFamilyName, aFatherName) =
  PRE
    aFatherName : STR1 &
    aFamilyName : STR1 
  THEN
    ANY aFamily, aMember WHERE
		aFamily : Family & Families_Family_name(aFamily) = aFamilyName
		& families(aFamily) = fmlRoot
		& aMember : FAMILYMEMBER 
		& aMember /: FamilyMember
		& aFamily /: ran(theFather)
    THEN
		FamilyMember := FamilyMember \/ {aMember} ||
		theFather := theFather \/ {aMember |-> aFamily} ||
		Families_FamilyMember_name := Families_FamilyMember_name <+ {aMember |-> aFatherName} 
    END
  END;

MotherNEW(aFamilyName, aMotherName) =
  PRE
    aMotherName : STR1 &
    aFamilyName : STR1 
  THEN
    ANY aFamily, aMember WHERE
		aFamily : Family & Families_Family_name(aFamily) = aFamilyName
		& families(aFamily) = fmlRoot
		& aMember : FAMILYMEMBER 
		& aMember /: FamilyMember
		& aFamily /: ran(theMother)
    THEN
		FamilyMember := FamilyMember \/ {aMember} ||
		theMother := theMother \/ {aMember |-> aFamily} ||
		Families_FamilyMember_name := Families_FamilyMember_name <+ {aMember |-> aMotherName} 
    END
  END;

AddFather(aFamily, aName) =
    PRE
        aName : STR1 &
        aFamily : Family &
        families(aFamily) = fmlRoot &
        aFamily /: ran(theFather)
    THEN
        ANY aMember WHERE
            aMember : FAMILYMEMBER & aMember /: FamilyMember
        THEN
            FamilyMember := FamilyMember \/ {aMember} ||
            theFather := theFather \/ {aMember |-> aFamily} ||
            Families_FamilyMember_name := Families_FamilyMember_name <+ {aMember |-> aName}
        END
    END;
AddMother(aFamily, aName) =
    PRE
        aName : STR1 &
        aFamily : Family &
        families(aFamily) = fmlRoot &
        aFamily /: ran(theMother)
    THEN
        ANY aMember WHERE
            aMember : FAMILYMEMBER & aMember /: FamilyMember
        THEN
            FamilyMember := FamilyMember \/ {aMember} ||
            theMother := theMother \/ {aMember |-> aFamily} ||
            Families_FamilyMember_name := Families_FamilyMember_name <+ {aMember |-> aName}
        END
    END ;

AddSon(aFamily, aName) =
  PRE
    aName : STR1 &
    aFamily : Family &
    families(aFamily) = fmlRoot
  THEN
    ANY aMember WHERE
      aMember : FAMILYMEMBER &
      aMember /: FamilyMember
    THEN
      FamilyMember := FamilyMember \/ {aMember} ||
      theSons := theSons \/ {aMember |-> aFamily} ||
      Families_FamilyMember_name := Families_FamilyMember_name <+ {aMember |-> aName} 
    END
  END;

AddDaughter(aFamily, aName) =
  PRE
    aName : STR1 &
    aFamily : Family &
    families(aFamily) = fmlRoot
  THEN
    ANY aMember WHERE
      aMember : FAMILYMEMBER &
      aMember /: FamilyMember
    THEN
      FamilyMember := FamilyMember \/ {aMember} ||
      theDaughters := theDaughters \/ {aMember |-> aFamily} ||
      Families_FamilyMember_name := Families_FamilyMember_name <+ {aMember |-> aName} 
    END
  END ;

PersonNEW(aName, isMale) =
  PRE
    aName : STR1 &
    isMale : BOOL
  THEN
    ANY aPerson WHERE
      aPerson : PERSON &
      aPerson /: Person
    THEN
      Person := Person \/ {aPerson} ||
      persons := persons <+ {aPerson |-> prsRoot} ||
      Persons_Person_name := Persons_Person_name <+ {aPerson |-> aName} ||
	  birthday := birthday <+ {aPerson |-> "DEFAULT"} ||
      IF isMale = TRUE THEN
        Male := Male \/ {aPerson}
      ELSE
        Female := Female \/ {aPerson}
      END
    END
  END ;

SetBirthday(aName, aBirthday) =
  PRE
    aName : STR1 &
    aBirthday : STR1
  THEN
    ANY aPerson WHERE
      aPerson : Person &
      aPerson : persons~[{prsRoot}] &
      Persons_Person_name(aPerson) = aName
    THEN
      birthday := birthday <+ {aPerson |-> aBirthday}
    END
  END ;

SetPersonName(aPerson, newName) =
  PRE
    aPerson : Person &
    aPerson : thePersons &
    newName : STR1
  THEN
    Persons_Person_name := Persons_Person_name <+ {aPerson |-> newName}
  END
    
END