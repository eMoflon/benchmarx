package org.benchmarx.examples.familiestopersons.testsuite.level1.incr.wocorrs.state.auto;

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
	 * <b>Test</b> for changing the birthday of a person.
	 * <p>
	 * <b>Expected</b>: Nothing has to be changed in the families model.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-state-auto
	 * <ul>
	 * <li><b>incr</b>: old family register is required as decision to map Homer
	 * to a father would otherwise be lost.
	 * <li><b>wocorr</b>: assumption based on unique naming works here.
	 * <li><b>state</b>: as the name of the person remains the same, computing
	 * the change here is pretty straightforward.
	 * <li><b>auto</b>: propagation is deterministic (namely doing nothing).
	 * </ul>
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
}
