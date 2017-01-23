package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;


public class DeletingFamiliesAndFamilyMembers extends FamiliesToPersonsTestCase {
	
	public DeletingFamiliesAndFamilyMembers(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for deletion of a single family member. <br/>
	 * <b>Expect</b> the associated person to be deleted from the persons
	 * register. <br/>
	 * <b>Features</b>: fwd, add+del, fixed
	 */
	@Test
	public void testDeleteFamilyMemberOneFamily() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createSimpsonFamily)
										       .andThen(helperFamily::createFatherHomer));
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::deleteFamilyFatherHomer);
		//------------
		util.assertPostcondition("DeleteFamilyMemberHomer", "DeletePersonHomer");
	}

	/**
	 * Analogous to @link {@link #testDeleteFamilyMemberOneFamily()}, but here
	 * there is another family in the families register. <br/>
	 * <b>Features</b>: fwd, add+del, fixed
	 */
	@Test
	public void testDeleteFamilyMemberMultipleFamilies() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createSimpsonFamily)
			       							   .andThen(helperFamily::createFatherHomer));
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createBachchanFamily)
			       							   .andThen(helperFamily::createFatherAmitabh));
		tool.performAndPropagateSourceEdit(helperFamily::createOtherRemainingMembersInFamilyBachchan);
		
		util.assertPrecondition("Pre_DeleteFamilyMember", "Pre_DeletePerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::deleteFamilyFatherAmitabh);
		//------------
		util.assertPostcondition("DeleteFamilyMember", "DeletePerson");
	}
	
	/**
	 * <b>Test</b> for deletion of a single family member, where two members
	 * (one of which is deleted) have the same name. In this case, mother and
	 * daughter have same name. <br/>
	 * <b>Expect</b> the associated person to be deleted from the persons
	 * register. <br/>
	 * <b>Features</b>: fwd, add+del, corr-based, fixed
	 */
	@Test
	public void testDeleteNonUniqueDaughter() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createSimpsonFamily)
											   .andThen(helperFamily::createFatherHomer));
		tool.performAndPropagateSourceEdit(helperFamily::createMotherMarge);
		tool.performAndPropagateSourceEdit(helperFamily::createDaughterMarge);
		
		util.assertPrecondition("Pre_DeleteFamilyMemberOfSameName", "Pre_DeletePersonSameName");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::deleteFamilyDaughterMarge);
		//------------
		util.assertPostcondition("DeleteFamilyMemberOfSameName", "DeletePersonSameName");
	}
	
	/**
	 * Analogous to @link {@link #testDeleteNonUniqueDaughter()}, but here for
	 * father/son.<br/>
	 * <b>Features</b>: fwd, add+del, corr-based, fixed
	 */
	@Test
	public void testDeleteNonUniqueSon() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createMotherMarge);
		tool.performAndPropagateSourceEdit(helperFamily::createSonHomer);
		
		util.assertPrecondition("Pre_DeleteFamilyMemberSonHomer", "Pre_DeletePersonSonHomer");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::deleteFamilySonHomer);
		//------------
		util.assertPostcondition("DeleteFamilyMemberSonHomer", "DeletePersonSonHomer");
	}
	
	/**
	 * <b>Test</b> for deletion of an entire family with all its family members.
	 * <br/>
	 * <b>Expect</b> Delete all corresponding persons in the persons model.
	 * <br/>
	 * <b>Features</b>: fwd, add+del+attribute, corr-based, fixed
	 */
	@Test
	public void testDeleteFamilyOfSameName() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		tool.performAndPropagateTargetEdit(helperPerson::setBirthdaysOfSimpson);

		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherGeorge);
		tool.performAndPropagateSourceEdit(helperFamily::createOtherSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeOtherFamily", "Pre_DeleteAllPersonSameFamilyName");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::deleteFirstSimpsonFamily);
		//------------
		util.assertPostcondition("DeleteFamilySameName", "DeleteAllPersonSameFamilyName");
	}

}
