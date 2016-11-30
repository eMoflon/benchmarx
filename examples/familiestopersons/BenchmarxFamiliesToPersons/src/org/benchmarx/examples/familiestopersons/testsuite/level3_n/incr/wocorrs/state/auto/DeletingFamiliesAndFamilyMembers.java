package org.benchmarx.examples.familiestopersons.testsuite.level3_n.incr.wocorrs.state.auto;

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
	 * <b>Test</b> for deletion of an entire family with all family members.
	 * <p>
	 * <b>Expect</b> all corresponding persons in the persons register to be
	 * deleted.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-state-auto
	 * <ul>
	 * <li><b>incr</b>: deleting family requires old consistent state as
	 * birthdays (of other people) would be otherwise lost.
	 * <li><b>wocorr</b>: it's possible to guess required correspondences as
	 * full names of persons are unique (in this example).
	 * <li><b>state</b>: deleting is state-based, as it's possible to determine
	 * the change from old and new states.
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * <ul>
	 */
	@Test
	public void testDeleteFamily() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		tool.performAndPropagateTargetEdit(helperPerson::setBirthdaysOfSimpson);

		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createBachchanFamily)
			       							   .andThen(helperFamily::createFatherAmitabh));
		tool.performAndPropagateSourceEdit(helperFamily::createOtherRemainingMembersInFamilyBachchan);
		tool.performAndPropagateTargetEdit(helperPerson::setBirthdaysOfBachchan);

		util.assertPrecondition("Pre_DeleteFamily", "Pre_DeleteAllPerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::deleteFamilyBachchan);
		//------------
		util.assertPostcondition("DeleteFamily", "DeleteAllPerson");
	}
}
