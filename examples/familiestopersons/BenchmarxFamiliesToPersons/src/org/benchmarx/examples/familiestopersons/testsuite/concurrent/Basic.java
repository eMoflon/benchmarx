package org.benchmarx.examples.familiestopersons.testsuite.concurrent;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class Basic extends FamiliesToPersonsTestCase {

	public Basic(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for inserting Homer as a family member and Bart as a person
	 * concurrently. <br/>
	 * <b>Expect</b> : Bart should become a family member too, Homer a person, and
	 * the Simpson family should not be created twice.<br/>
	 * <b>Features</b>: Least change suggests avoiding two Simpson families.
	 */
	@Test
	public void testSuitableFamilyNonMatchingMember() {
		tool.noPrecondition();

		// ------------
		tool.performAndPropagateEdit(srcEdit(helperFamily::createSimpsonFamily, helperFamily::createFatherHomer),
				trgEdit(helperPerson::createBart, helperPerson::changeAllBirthdays));

		util.assertPostcondition("FamilyAfterBasicConcurrentEdit1", "PersonsAfterBasicConcurrentEdit1");
		// ------------
	}

	/**
	 * <b>Test</b> for inserting Homer as a family member and Homer as a person
	 * concurrently. <br/>
	 * <b>Expect</b> : The two Homers should be identified and set in
	 * correspondence.<br/>
	 * <b>Features</b>: Least change suggests avoiding any changes at all.
	 */
	@Test
	public void testSuitableFamilyMatchingMember() {
		tool.noPrecondition();

		// ------------
		tool.performAndPropagateEdit(//
				srcEdit(helperFamily::createSimpsonFamily, helperFamily::createFatherHomer), //
				trgEdit(helperPerson::createHomer, helperPerson::changeAllBirthdays));

		util.assertPostcondition("FamilyAfterBasicConcurrentEdit2", "PersonsAfterBasicConcurrentEdit2");
		// ------------
	}

	/**
	 * <b>Test</b> for inserting Homer as a family member and Seymour as a person
	 * concurrently. <br/>
	 * <b>Expect</b> : Homer must be created as a person, Seymour as a family member
	 * in a new family.<br/>
	 * <b>Features</b>: Concurrent changes are orthogonal.
	 */
	@Test
	public void testNonSuitableFamily() {
		tool.noPrecondition();

		// ------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
				.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false);
		tool.performAndPropagateEdit(//
				srcEdit(helperFamily::createSimpsonFamily, helperFamily::createFatherHomer), //
				trgEdit(helperPerson::createSeymour, helperPerson::setBirthdayOfSeymour));

		util.assertPostcondition("FamilyAfterBasicConcurrentEdit3", "PersonsAfterBasicConcurrentEdit3");
		// ------------
	}
}