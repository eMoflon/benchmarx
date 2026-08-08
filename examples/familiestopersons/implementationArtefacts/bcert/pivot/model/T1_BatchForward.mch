MACHINE     T1_BatchForward
INCLUDES    MetaModel
INITIALISATION
    Reset
DEFINITIONS
	SET_PREF_MAX_OPERATIONS == 1 ;
    CSP_GUIDE_FILE == "BatchForward.csp"
OPERATIONS
noPrecondition = BEGIN skip END ;

propagate = 
    BEGIN 
        Member2Person 
    END ;
fam <-- createSimpsonFamily =
    BEGIN
        fam <-- FamilyNEW("Simpson")
    END; 
createFatherBart =
    BEGIN
        FatherNEW("Simpson","Bart")
    END;
createSonBart =
    BEGIN
        SonNEW("Simpson","Bart")
    END;
fam <-- renameEmptySimpsonToBouvier = 
    BEGIN
        fam <-- FamilySetName("Simpson", "Bouvier")
    END;
fam <-- createSkinnerFamily =
    BEGIN
        fam <-- FamilyNEW("Skinner")
    END;  
fam <-- createFlandersFamilySonRod = 
    BEGIN
        fam <-- FamilyNEW("Flanders") ;
        SonNEW("Flanders", "Rod")
    END ;
createNewFamilySimpsonWithMembers =
    VAR aFamily IN
        aFamily <-- FamilyNEW("Simpson") ;
        AddFather(aFamily, "Homer") ;
        AddMother(aFamily, "Marge") ;
        AddSon(aFamily, "Bart") ;
        AddDaughter(aFamily, "Lisa") ;
        AddDaughter(aFamily, "Maggie")
    END ;

ok <-- assertPrecondition =
    SELECT Pivot_name(pvtRoot) = "testFamilyNameChangeOfEmpty" THEN
      ok <-- AssertPreCondition("Pre_NameChangeEmpty")
    ELSE
      ok := FALSE
    END;

ok <-- assertPostcondition =
    SELECT Pivot_name(pvtRoot) = "testFamilyNameChangeOfEmpty" THEN
        ok <-- AssertPostCondition("Post_NameChangeEmpty")
    WHEN Pivot_name(pvtRoot) = "testInitialiseSynchronisation" THEN
        ok <-- AssertPostCondition("Post_RootElement")
    WHEN Pivot_name(pvtRoot) = "testCreateFamily" THEN
        ok <-- AssertPostCondition("Post_CreateFamily")
    WHEN Pivot_name(pvtRoot) = "testCreateFamilyMember" THEN
        ok <-- AssertPostCondition("Post_CreateFamilyMember")
    WHEN Pivot_name(pvtRoot) = "testNewFamilyWithMultiMembers" THEN
        ok <-- AssertPostCondition("Post_NewFamilyWithMultiMembers")
    WHEN Pivot_name(pvtRoot) = "testNewDuplicateFamilyNames" THEN
        ok <-- AssertPostCondition("Post_NewDuplicateFamilyNames")
    WHEN Pivot_name(pvtRoot) = "testDuplicateFamilyMemberNames" THEN
        ok <-- AssertPostCondition("Post_DuplicateFamilyMemberNames")
    ELSE
        ok := FALSE
    END;

ok <-- testInitialiseSynchronisation = 
    BEGIN
        ok := bool(Pivot_name(pvtRoot) = "testInitialiseSynchronisation")
    END;

ok <-- testFamilyNameChangeOfEmpty = 
    BEGIN
        ok := bool(Pivot_name(pvtRoot) = "testFamilyNameChangeOfEmpty")
    END;

ok <-- testCreateFamily = 
    BEGIN
        ok := bool(Pivot_name(pvtRoot) = "testCreateFamily")
    END ;

ok <-- testCreateFamilyMember = 
    BEGIN
        ok := bool(Pivot_name(pvtRoot) = "testCreateFamilyMember")
    END;

ok <-- testNewFamilyWithMultiMembers = 
    BEGIN
        ok := bool(Pivot_name(pvtRoot) = "testNewFamilyWithMultiMembers")
    END;

ok <-- testNewDuplicateFamilyNames = 
    BEGIN
        ok := bool(Pivot_name(pvtRoot) = "testNewDuplicateFamilyNames")
    END;

ok <-- testDuplicateFamilyMemberNames = 
    BEGIN
        ok := bool(Pivot_name(pvtRoot) = "testDuplicateFamilyMemberNames")
    END 
END