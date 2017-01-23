package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class MovingAndChangingRoles extends FamiliesToPersonsTestCase {
	
	public MovingAndChangingRoles(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for moving a family member to a new family, i.e., daughter
	 * marries and is registered under a new family. <br/>
	 * <b>Expect</b> the family name of the associated person to change
	 * appropriately in the person model. <br/>
	 * <b>Features</b>: fwd, add+del structural, fixed
	 */
	@Test
	public void testFamilyMemberMovesToNewFamily() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createBachchanFamily)
			       							   .andThen(helperFamily::createFatherAmitabh));
		tool.performAndPropagateSourceEdit(helperFamily::createOtherRemainingMembersInFamilyBachchan);
		
		util.assertPrecondition("Pre_FamilyMemberWithDiffFamily", "Pre_PersonsFirstNameChange");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::moveDaughterToMotherOfNewFamily);
		//------------
		util.assertPostcondition("FamilyMemberWithDiffFamily", "PersonsFirstNameChange");
	}
}
