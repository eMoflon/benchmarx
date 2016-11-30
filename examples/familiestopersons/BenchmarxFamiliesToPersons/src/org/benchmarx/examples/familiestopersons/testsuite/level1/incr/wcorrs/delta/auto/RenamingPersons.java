package org.benchmarx.examples.familiestopersons.testsuite.level1.incr.wcorrs.delta.auto;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class RenamingPersons extends FamiliesToPersonsTestCase {
	
	public RenamingPersons(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for changing a person's first name (where another person with
	 * the same name exists).
	 * <p>
	 * <b>Expected</b>: the first name of the corresponding member in the
	 * families model should be changed.
	 * <p>
	 * <b>Classification</b>: incr-wcorr-delta-auto
	 * <ul>
	 * <li><b>incr</b>: the old family register is required as it is impossible
	 * to guess if female/male persons are mothers/fathers or daughters/sons in
	 * the family register.
	 * <li><b>wcorr</b>: it's impossible to guess which family member has to be
	 * renamed in the families model as two persons (one of which is to be
	 * renamed) have the same name.
	 * <li><b>delta</b>: renaming is inherently delta-based as it cannot be
	 * distinguished from combined deletion and creation.
	 * <li><b>auto</b>: propagation is deterministic so no choice required.
	 * </ul>
	 */
	@Test
	public void testFirstNameChangeOfNonUniquePerson() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherHomer)
				.andThen(helperFamily::createSimpsonFamilyMembers));
		tool.performAndPropagateTargetEdit(helperPerson::createOtherBart);
			
		util.assertPrecondition("Pre_MemberNameChangeOther", "Pre_PersonNameChangeOther");
		//----------------
		tool.performAndPropagateTargetEdit(helperPerson::firstNameChangeOfBart);
		//----------------
		util.assertPostcondition("MemberNameChangeOther","PersonNameChangeOther");
	}
}
