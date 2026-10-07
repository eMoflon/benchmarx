MACHINE 
	
	FamiliesToPersonsTransformation

DEFINITIONS

	"definitions.def" ;
	"match.def";
	"LibraryStrings.def";
	"BatchBwd.def"

SETS
	
	Strategy = {BWD,FWD,BWD_FWD};
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
	card(PIVOT) = 1 &
	
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

SetFWD =
  BEGIN
    strategie(pvtRoot) := FWD
  END;

SetBWD =
  BEGIN
    strategie(pvtRoot) := BWD
  END;

SetBWD_FWD =
  BEGIN
    strategie(pvtRoot) := BWD_FWD
  END;

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
        || Persons_Person_name := Persons_Person_name <+ {
             aPerson |-> STRING_CONC([
               Families_Family_name(familyOf(aMember)),
               ", ",
               Families_FamilyMember_name(aMember)
             ])
           }
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

    (
      aPerson : Male =>
      (
        (PARENT_TO_CHILD(pvtRoot) = TRUE &
         #(ff).(ff : Family & ff : families~[{fmlRoot}] & Families_Family_name(ff) = PersonFamilyName(aPerson) & ff /: ran(theFather)))
        =>
        aFamily /: ran(theFather)
      )
    ) &
    (
      aPerson : Female =>
      (
        (PARENT_TO_CHILD(pvtRoot) = TRUE &
         #(ff).(ff : Family & ff : families~[{fmlRoot}] & Families_Family_name(ff) = PersonFamilyName(aPerson) & ff /: ran(theMother)))
        =>
        aFamily /: ran(theMother)
      )
    ) &

    aMember : FAMILYMEMBER &
    aMember /: FamilyMember &

    aMapping : MAPPING &
    aMapping /: Mapping &

    FAMILY_TO_NEW(pvtRoot) = TRUE &
    strategie(pvtRoot) = BWD &
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

    (
      Persons_Person_name(aPerson) =
        STRING_CONC([
          Families_Family_name(familyOf(aMember)),
          ", ",
          Families_FamilyMember_name(aMember)
        ])
      or
      #(map).(map : Mapping & pMap(map) = aPerson & mMap(map) = aMember)
    ) &

    aMapping : MAPPING &
    aMapping /: Mapping &

    sync(pvtRoot) = FALSE
  THEN
    Mapping := Mapping \/ {aMapping} ||
    mapping := mapping <+ {aMapping |-> pvtRoot} ||
    mMap := mMap <+ {aMapping |-> aMember} ||
    pMap := pMap <+ {aMapping |-> aPerson} ||
    Families_FamilyMember_name := Families_FamilyMember_name <+ {aMember |-> PersonMemberName(aPerson)}
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
  END

/* 
 * End Transformation Rules 
 */ 


    
END