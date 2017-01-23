package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.roundtrip;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class ChangingBirthdays extends FamiliesToPersonsTestCase {
	
	public ChangingBirthdays(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for changing the birthday of a person. <br/>
	 * <b>Expected</b>: Nothing has to be changed in the families model. <br/>
	 * <b>Features</b>: round trip, add+attribute, fixed
	 */
	@Test
	public void testBirthdayChange()
	{
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		
		util.assertPrecondition("Pre_OneFamilyWithOneFamilyMember", "Pre_PersonBirthdayChange");
		//-----------------------------
		tool.performAndPropagateTargetEdit((helperPerson::birthdayChangeOfHomer));
		//-----------------------------
		util.assertPostcondition("OneFamilyWithOneFamilyMember","PersonBirthdayChange");
	}
	
	/**
	 * <b>Test</b> for a swap of family member roles via renaming of the family
	 * members (here father and son). <br/>
	 * <b>Expect</b> appropriate persons should be renamed. <br/>
	 * <b>Features</b>: round trip, add+attribute, structural, fixed
	 */
	@Test
	public void testFamilyMemberSwapRoleFatherToSonViaRenaming() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		tool.performAndPropagateTargetEdit(helperPerson::setBirthdaysOfSimpson);
		
		util.assertPrecondition("Pre_RoleChangeFamilyMember", "Pre_NoChangePerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyFatherHomerRoleChangeToSon);
		//------------
		util.assertPostcondition("RoleChangeFamilyMember", "NoChangePerson");
	}
	
	/**
	 * Analogous to @link
	 * {@link #testFamilyMemberSwapRoleFatherToSonViaRenaming()}, but here for
	 * daughter <-> mother.<br/>
	 * <b>Features</b>: round trip, add+attribute, structural, fixed
	 */
	@Test
	public void testFamilyMemberSwapRoleMotherToDaughterViaRenaming() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		tool.performAndPropagateTargetEdit(helperPerson::setBirthdaysOfSimpson);
		
		util.assertPrecondition("Pre_RoleChangeFamilyMember", "Pre_NoChangePerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyMotherMargeRoleChangeToDaughterLisa);
		//------------
		util.assertPostcondition("RoleChangeFamilyMemberMoToDau", "NoChangePersonFemale");
	}
	
	/**
	 * Analogous to @link
	 * {@link #testFamilyMemberSwapRoleFatherToSonViaRenaming()}, but here for
	 * mother <-> father.<br/>
	 * <b>Features</b>: round trip, add+attribute, structural, fixed
	 */
	@Test
	public void testFamilyMemberSwapRoleFatherToMotherViaRenaming() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		tool.performAndPropagateTargetEdit(helperPerson::setBirthdaysOfSimpson);
		
		util.assertPrecondition("Pre_RoleChangeFamilyMember", "Pre_NoChangePerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyFatherHomerRoleChangeToMotherMarge);
		//------------
		util.assertPostcondition("RoleChangeFamilyMemberFaToMo", "ChangePersonMToF");
	}
	
	/**
	 * Analogous to @link
	 * {@link #testFamilyMemberSwapRoleFatherToSonViaRenaming()}, but here for
	 * mother <-> son.<br/>
	 * <b>Features</b>: round trip, add+attribute, structural, fixed
	 */
	@Test
	public void testFamilyMemberRoleChangeSonToMother() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		tool.performAndPropagateTargetEdit(helperPerson::setBirthdaysOfSimpson);
		
		util.assertPrecondition("Pre_RoleChangeFamilyMember", "Pre_NoChangePerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familySonBartRoleChangeToMotherMarge);
		//------------
		util.assertPostcondition("RoleChangeFamilyMemberSoToMo", "ChangePersonMaToFe");
	}
}
