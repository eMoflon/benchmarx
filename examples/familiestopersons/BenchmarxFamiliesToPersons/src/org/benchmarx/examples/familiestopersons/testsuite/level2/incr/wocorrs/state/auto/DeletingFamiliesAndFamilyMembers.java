package org.benchmarx.examples.familiestopersons.testsuite.level2.incr.wocorrs.state.auto;

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
}
