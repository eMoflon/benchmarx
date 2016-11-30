package org.benchmarx.examples.familiestopersons.testsuite.level1.incr.wocorrs.delta.auto;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class RenamingPerson extends FamiliesToPersonsTestCase {
	
	public RenamingPerson(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for changing a person's first name.
	 * <p>
	 * <b>Expected</b>: only the first name of the corresponding family member
	 * should be changed.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-delta-auto
	 * <ul>
	 * <li><b>incr</b>: changing a persons first name requires old consistent
	 * family model as the information if females are daughters or mothers (and
	 * males analogously) would otherwise be lost.
	 * <li><b>wocorr</b>: assumption of unique names can be used here to compute
	 * correspondences correctly.
	 * <li><b>delta</b>: renaming cannot be distinguished from combined deletion
	 * and creation.
	 * <li><b>auto</b>: propagation is deterministic.
	 * </ul>
	 */
	@Test
	public void testFirstNameChangePerson() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherHomer)
				.andThen(helperFamily::createSimpsonFamilyMembers));
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");	
		//----------------
		tool.performAndPropagateTargetEdit(helperPerson::firstNameChangeOfHomer);
		//----------------
		util.assertPostcondition("MemberNameChange","PersonNameChange");
	}
}
