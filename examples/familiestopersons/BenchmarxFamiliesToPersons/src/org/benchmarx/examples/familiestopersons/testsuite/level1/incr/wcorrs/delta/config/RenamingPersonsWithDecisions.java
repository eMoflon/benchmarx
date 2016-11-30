package org.benchmarx.examples.familiestopersons.testsuite.level1.incr.wcorrs.delta.config;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class RenamingPersonsWithDecisions extends FamiliesToPersonsTestCase {

	public RenamingPersonsWithDecisions(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for changing a person's full name (where another person with
	 * the same name exists).
	 * <p>
	 * <b>Expected</b>: first name of the corresponding member and their family
	 * name must be changed. As no fitting family exists, a new family must be
	 * created and the member moved to this new family (as the father of this
	 * family).
	 * <p>
	 * <b>Classification</b>: incr-wcorr-delta-config
	 * <ul>
	 * <li><b>incr</b>: Old family register is required to remember the correct
	 * mapping of female/male persons to daughters/mothers and sons/fathers,
	 * respectively.
	 * <li><b>wcorr</b>: it's impossible to guess which family member has to be
	 * renamed in the families model as two persons (of which one is renamed)
	 * have the same name.
	 * <li><b>delta</b>: renaming cannot be distinguished from combined creation
	 * and deletion.
	 * <li><b>config</b>: there is a choice involved when moving the family member
	 * to the new family. Bart is male so one can choose between the father and
	 * son role. It might seem reasonable to retain the old role (in this case
	 * son) but it is actually a freedom of choice, and the preference here is
	 * to establish the family member formally known as Bart as a father of the
	 * new family.
	 * </ul>
	 */
	@Test
	public void testFullNameChangeOfNonUniquePerson() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		tool.performAndPropagateTargetEdit(helperPerson::createOtherBart);
			
		util.assertPrecondition("Pre_MemberNameChangeOther", "Pre_PersonNameChangeOther");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::fullNameChangeOfOtherBart);
		//----------------
		util.assertPostcondition("MemberFullNameChangeOther","PersonFullNameChangeOther");
	}
}
