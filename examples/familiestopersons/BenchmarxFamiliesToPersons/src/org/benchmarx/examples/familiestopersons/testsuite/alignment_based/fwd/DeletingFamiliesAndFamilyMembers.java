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
	 * <b>Test</b> for deletion of a single family member.
	 * <p>
	 * <b>Expect</b> the associated person to be deleted from the persons
	 * register.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-state-auto
	 * <ul>
	 * <li><b>incr</b>: deleting a family member requires old consistent state
	 * of the persons register as the associated birthdays of all other family
	 * members (to be precise: of their associated persons) would be otherwise
	 * lost.
	 * <li><b>wocorr</b>: it's possible to guess the required correspondences as
	 * full names of persons are unique (in this example).
	 * <li><b>state</b>: deletion in this example is state-based, as it's
	 * reasonably easy to determine what changed from the old and new state of
	 * the families register.
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * <ul>
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
	 * there is another family in the families register.
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
	 * daughter have same name.
	 * <p>
	 * <b>Expect</b> the associated person to be deleted from the persons
	 * register.
	 * <p>
	 * <b>Classification</b>: incr-wcorr-state-auto
	 * <ul>
	 * <li><b>incr</b>: deletion requires the old consistent state of the
	 * persons register as the birthdays (of all other family members) would be
	 * otherwise lost.
	 * <li><b>wcorr</b>: traceability links are required as it is impossible to
	 * guess correctly which persons correspond to which family members, given
	 * that there are multiple persons with the exact same full name.
	 * <li><b>state</b>: deletion is state-based, as it is reasonably easy to
	 * determine what was changed from the old and new states of the family
	 * register.
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * <ul>
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
	 * Analogous to @link {@link #testDeleteNonUniqueDaughter()}, but here for father/son.
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
	 * <p>
	 * <b>Expect</b> Delete all corresponding persons in the persons model.
	 * <p>
	 * <b>Classification</b>: incr-wcorr-state-auto
	 * <ul>
	 * <li><b>incr</b>: deleting a family requires old consistent state of
	 * persons register as all other birthdays would be otherwise lost.
	 * <li><b>wcorr</b>: traceability links are required as it is impossible to
	 * guess correctly which persons correspond to which family members, given
	 * that there are multiple persons with the exact same family name.
	 * <li><b>state</b>: deletion is state-based, as it's possible to determine
	 * the change from old and new states.
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * <ul>
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
