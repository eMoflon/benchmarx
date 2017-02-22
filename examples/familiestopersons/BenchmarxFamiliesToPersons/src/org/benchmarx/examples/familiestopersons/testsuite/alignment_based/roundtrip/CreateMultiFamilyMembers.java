package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.roundtrip;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class CreateMultiFamilyMembers extends FamiliesToPersonsTestCase {
	
	public CreateMultiFamilyMembers(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for creation of multiple family members in an existing
	 * family. <br/>
	 * <b>Expect</b> the creation of multiple persons (male/female as required)
	 * associated to the new family members. <br/>
	 * <b>Features</b>: round trip, add+attribute, fixed
	 */
	@Test
	public void testCreateMultiFamilyMember()
	{
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateTargetEdit(helperPerson::birthdayChangeOfHomer);
		
		util.assertPrecondition("Pre_NameChangeFamilyMember", "PersonBirthdayChange");
		//------------
		//tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		//------------
		util.assertPostcondition("FamilyWithMultiFamilyMember", "PersonWithMultiMember");
	}
}
