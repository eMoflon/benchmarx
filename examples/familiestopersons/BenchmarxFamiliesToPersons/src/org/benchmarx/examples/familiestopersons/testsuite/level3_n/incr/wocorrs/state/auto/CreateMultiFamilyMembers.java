package org.benchmarx.examples.familiestopersons.testsuite.level3_n.incr.wocorrs.state.auto;

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
	 * family.
	 * <p>
	 * <b>Expect</b> the creation of multiple persons (male/female as required)
	 * associated to the new family members.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-state-auto
	 * <ul>
	 * <li><b>incr</b>: creation of family and multiple family member requires
	 * old(er) states, otherwise birthdays would be lost (here for the father).
	 * <li><b>wocorr</b>: assumption based on unique names works for this
	 * example.
	 * <li><b>state</b>: easy to guess/compute the delta involved here based on
	 * the old and new states (what was added is clear).
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * <ul>
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
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		//------------
		util.assertPostcondition("FamilyWithMultiFamilyMember", "PersonWithMultiMember");
	}
}
