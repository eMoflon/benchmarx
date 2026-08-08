MACHINE
  T2_BatchBackward

INCLUDES
  MetaModel

DEFINITIONS
  SET_PREF_MAX_OPERATIONS == 1 ;
  CSP_GUIDE_FILE == "BatchBackward.csp" ;
  "definitions.def" ;
  "LibraryStrings.def" ;
  "BatchBwd.def"

INITIALISATION
  Reset

OPERATIONS

noPrecondition = BEGIN skip END ;

/* 
 * Prefer reusing an existing family when PREFER_FAMILY_TO_NEW is TRUE.
 * Otherwise create a new family. 
 */
propagate =
  SELECT
    FAMILY_TO_NEW(pvtRoot) = TRUE &
    #(pp,ff).(
      pp : Person &
      pp : thePersons &
      pp /: dom(mapped) &
      ff : Family &
      ff : families~[{fmlRoot}] &
      Families_Family_name(ff) = PersonFamilyName(pp)
    )
  THEN
    Person2MemberExistingFamily
  ELSE
    Person2MemberNewFamily
  END ;

createRod =
  BEGIN
    PersonNEW("Flanders, Rod", TRUE)
  END;

createHomer =
  BEGIN
    PersonNEW("Simpson, Homer", TRUE)
  END;

createBart =
  BEGIN
    PersonNEW("Simpson, Bart", TRUE)
  END;

createMarge =
  BEGIN
    PersonNEW("Simpson, Marge", FALSE)
  END;

createLisa =
  BEGIN
    PersonNEW("Simpson, Lisa", FALSE)
  END;

createMaggie =
  BEGIN
    PersonNEW("Simpson, Maggie", FALSE)
  END;

ok <-- assertPostcondition =
  SELECT Pivot_name(pvtRoot) = "testCreateMalePersonAsParent" THEN
    ok <-- AssertPostCondition("Post_CreateMalePersonAsParent")
  WHEN Pivot_name(pvtRoot) = "testCreateFamilyMembersInExistingFamilyAsParents" THEN
    ok <-- AssertPostCondition("Post_CreateFamilyMembersInExistingFamilyAsParents")

  WHEN Pivot_name(pvtRoot) = "testCreateMalePersonAsSon" THEN
    ok <-- AssertPostCondition("Post_CreateMalePersonAsSon")
  WHEN Pivot_name(pvtRoot) = "testCreateFamilyMembersInExistingFamilyAsChildren" THEN
    ok <-- AssertPostCondition("Post_CreateFamilyMembersInExistingFamilyAsChildren")
  WHEN Pivot_name(pvtRoot) = "testCreateDuplicateFamilyMembersInExistingFamilyAsChildren" THEN
    ok <-- AssertPostCondition("Post_CreateDuplicateFamilyMembersInExistingFamilyAsChildren")

  WHEN Pivot_name(pvtRoot) = "testCreateMalePersonAsParent2" THEN
    ok <-- AssertPostCondition("Post_CreateMalePersonAsParent2")
  WHEN Pivot_name(pvtRoot) = "testCreateFamilyMembersInNewFamilyAsParents" THEN
    ok <-- AssertPostCondition("Post_CreateFamilyMembersInNewFamilyAsParents")
  WHEN Pivot_name(pvtRoot) = "testCreateDuplicateFamilyMembersInNewFamilyAsParents" THEN
    ok <-- AssertPostCondition("Post_CreateDuplicateFamilyMembersInNewFamilyAsParents")

  WHEN Pivot_name(pvtRoot) = "testCreateMalePersonAsSon2" THEN
    ok <-- AssertPostCondition("Post_CreateMalePersonAsSon2")
  WHEN Pivot_name(pvtRoot) = "testCreateFamilyMembersInNewFamilyAsChildren" THEN
    ok <-- AssertPostCondition("Post_CreateFamilyMembersInNewFamilyAsChildren")
  WHEN Pivot_name(pvtRoot) = "testCreateDuplicateFamilyMembersInNewFamilyAsChildren" THEN
    ok <-- AssertPostCondition("Post_CreateDuplicateFamilyMembersInNewFamilyAsChildren")
  END;

/* BatchBwdEAndP: FAMILY_TO_NEW=TRUE, PARENT_TO_CHILD=TRUE */

ok <-- testCreateMalePersonAsParent =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateMalePersonAsParent" &
      FAMILY_TO_NEW(pvtRoot) = TRUE &
      PARENT_TO_CHILD(pvtRoot) = TRUE
    )
  END;

ok <-- testCreateMalePersonAsParent2 =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateMalePersonAsParent2" &
      FAMILY_TO_NEW(pvtRoot) = FALSE &
      PARENT_TO_CHILD(pvtRoot) = TRUE
    )
  END;

ok <-- testCreateFamilyMembersInExistingFamilyAsParents =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateFamilyMembersInExistingFamilyAsParents" &
      FAMILY_TO_NEW(pvtRoot) = TRUE &
      PARENT_TO_CHILD(pvtRoot) = TRUE
    )
  END;


/* BatchBwdENotP: FAMILY_TO_NEW=TRUE, PARENT_TO_CHILD=FALSE */

ok <-- testCreateMalePersonAsSon =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateMalePersonAsSon" &
      FAMILY_TO_NEW(pvtRoot) = TRUE &
      PARENT_TO_CHILD(pvtRoot) = FALSE
    )
  END;

ok <-- testCreateFamilyMembersInExistingFamilyAsChildren =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateFamilyMembersInExistingFamilyAsChildren" &
      FAMILY_TO_NEW(pvtRoot) = TRUE &
      PARENT_TO_CHILD(pvtRoot) = FALSE
    )
  END;

ok <-- testCreateDuplicateFamilyMembersInExistingFamilyAsChildren =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateDuplicateFamilyMembersInExistingFamilyAsChildren" &
      FAMILY_TO_NEW(pvtRoot) = TRUE &
      PARENT_TO_CHILD(pvtRoot) = FALSE
    )
  END;


/* BatchBwdNotEAndP: FAMILY_TO_NEW=FALSE, PARENT_TO_CHILD=TRUE */

ok <-- testCreateFamilyMembersInNewFamilyAsParents =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateFamilyMembersInNewFamilyAsParents" &
      FAMILY_TO_NEW(pvtRoot) = FALSE &
      PARENT_TO_CHILD(pvtRoot) = TRUE
    )
  END;

ok <-- testCreateDuplicateFamilyMembersInNewFamilyAsParents =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateDuplicateFamilyMembersInNewFamilyAsParents" &
      FAMILY_TO_NEW(pvtRoot) = FALSE &
      PARENT_TO_CHILD(pvtRoot) = TRUE
    )
  END;


/* BatchBwdNotENotP: FAMILY_TO_NEW=FALSE, PARENT_TO_CHILD=FALSE */

ok <-- testCreateMalePersonAsSon2 =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateMalePersonAsSon2" &
      FAMILY_TO_NEW(pvtRoot) = FALSE &
      PARENT_TO_CHILD(pvtRoot) = FALSE
    )
  END;

ok <-- testCreateFamilyMembersInNewFamilyAsChildren =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateFamilyMembersInNewFamilyAsChildren" &
      FAMILY_TO_NEW(pvtRoot) = FALSE &
      PARENT_TO_CHILD(pvtRoot) = FALSE
    )
  END;

ok <-- testCreateDuplicateFamilyMembersInNewFamilyAsChildren =
  BEGIN
    ok := bool(
      Pivot_name(pvtRoot) = "testCreateDuplicateFamilyMembersInNewFamilyAsChildren" &
      FAMILY_TO_NEW(pvtRoot) = FALSE &
      PARENT_TO_CHILD(pvtRoot) = FALSE
    )
  END

END