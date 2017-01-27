package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.bwd;

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
	 * <b>Test</b> for changing a person's full name (where another person with
	 * the same name exists). <br/>
	 * <b>Expected</b>: first name of the corresponding member and their family
	 * name must be changed. As no fitting family exists, a new family must be
	 * created and the member moved to this new family (as the father of this
	 * family). <br/>
	 * <b>Features</b>: bwd, add+attribute, corr-based, structural, runtime
	 */
	@Test
	public void testFullNameChangeOfNonUniquePerson() {
		tool.initiateSynchronisationDialogue();
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
						.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		tool.performAndPropagateTargetEdit(helperPerson::createParentSimpsons);
		util.configure().makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		tool.performAndPropagateTargetEdit(helperPerson::createChildrenSimpsons);
		tool.performAndPropagateTargetEdit(helperPerson::createOtherBart);
			
		util.assertPrecondition("Pre_MemberNameChangeOther", "Pre_PersonNameChangeOther");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::fullNameChangeOfOtherBart);
		//----------------
		util.assertPostcondition("MemberFullNameChangeOther","PersonFullNameChangeOther");
	}
}
