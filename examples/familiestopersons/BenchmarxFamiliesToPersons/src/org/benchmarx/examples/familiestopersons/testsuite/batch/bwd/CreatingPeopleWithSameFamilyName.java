package org.benchmarx.examples.familiestopersons.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class CreatingPeopleWithSameFamilyName extends FamiliesToPersonsTestCase {

	public CreatingPeopleWithSameFamilyName(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for creating multiple persons together with same last
	 * name.<br/>
	 * <b>Expect</b> the creation of corresponding family members in the
	 * families model with the given first names, depending on the specified
	 * preferences. <br/>
	 * <b>Features:</b>: bwd, runtime
	 */
	@Test
	public void testCreateMultiPersonPrefTT() {
		tool.initiateSynchronisationDialogue();
		
		// No precondition!
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
						.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createHomer)
			    .andThen(helperPerson::createMarge));
		tool.performAndPropagateTargetEdit(helperPerson::createLisa);
		//----------------
		util.assertPostcondition("FamiliesMultiMembers", "PersonMultiMembers");
	}
	
	/**
	 * Analogous to {@link #testCreateMultiPersonPrefTT()}, but preferring
	 * children to parents.<br/>
	 * <b>Features:</b>: bwd, runtime
	 */
	@Test
	public void testCreateMultiPersonWithDiffPrefFT() {
		tool.initiateSynchronisationDialogue();
		
		// No precondition!
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false)
						.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createLisa)
			    .andThen(helperPerson::createBart));
		tool.performAndPropagateTargetEdit(helperPerson::createHomer);
		//----------------
		util.assertPostcondition("FamiliesMultiMembersPref", "PersonMultiMembersPref");
	}
	
	/**
	 * Analogous to {@link #testCreateMultiPersonPrefTT()}, but preferring new
	 * families to existing.<br/>
	 * <b>Features:</b>: bwd, runtime
	 */
	@Test
	public void testCreateMultiPersonWithDiffPrefTF() {
		tool.initiateSynchronisationDialogue();
		
		// No precondition!
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
						.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createHomer)
			    .andThen(helperPerson::createMarge));
		tool.performAndPropagateTargetEdit(helperPerson::createLisa);
		//----------------
		util.assertPostcondition("FamiliesMultiMembersPrefTF", "PersonMultiMembersPrefTF");
	}
	
	/**
	 * Analogous to {@link #testCreateMultiPersonPrefTT()}, but preferring
	 * children to parents and new families to existing.<br/>
	 * <b>Features:</b>: bwd, runtime
	 */
	@Test
	public void testCreateMultiPersonWithDiffPrefFF() {
		tool.initiateSynchronisationDialogue();
		
		// No precondition!
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false)
						.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createLisa)
			    .andThen(helperPerson::createBart));
		tool.performAndPropagateTargetEdit(helperPerson::createHomer);
		//----------------
		util.assertPostcondition("FamiliesMultiMembersPrefFF", "PersonMultiMembersPrefFF");
	}
}
