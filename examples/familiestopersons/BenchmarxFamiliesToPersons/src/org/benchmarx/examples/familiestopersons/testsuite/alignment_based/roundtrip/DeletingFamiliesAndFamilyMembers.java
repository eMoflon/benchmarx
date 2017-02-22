package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.roundtrip;

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
	 * <br/>
	 * <b>Expect</b> all corresponding persons in the persons register to be
	 * deleted. <br/>
	 * <b>Features</b>: roundtrip, add+del+attribute, fixed
	 */
	@Test
	public void testDeleteFamily() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		//tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		tool.performAndPropagateTargetEdit(helperPerson::setBirthdaysOfSimpson);

//		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createBachchanFamily)
//			       							   .andThen(helperFamily::createFatherAmitabh));
//		tool.performAndPropagateSourceEdit(helperFamily::createOtherRemainingMembersInFamilyBachchan);
//		tool.performAndPropagateTargetEdit(helperPerson::setBirthdaysOfBachchan);

		util.assertPrecondition("Pre_DeleteFamily", "Pre_DeleteAllPerson");
		//------------
//		tool.performAndPropagateSourceEdit(helperFamily::deleteFamilyBachchan);
		//------------
		util.assertPostcondition("DeleteFamily", "DeleteAllPerson");
	}
}
