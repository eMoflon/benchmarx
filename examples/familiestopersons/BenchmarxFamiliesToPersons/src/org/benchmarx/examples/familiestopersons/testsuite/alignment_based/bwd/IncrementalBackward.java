package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class IncrementalBackward extends FamiliesToPersonsTestCase {

	public IncrementalBackward(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting of a Persons in a PersonRegister after the initial
	 * register has been transformed into a family model. Please note: In order to
	 * avoid a combinatorial explosion of the number of test cases, we only supply
	 * a test case for the parameter configuration "e ^ p". A new person "Skinner, Seymour
	 * (male) is inserted. Then the backward transformation is called. Afterwards another
	 * person with the same name is introduced in the person register.<br/>
	 * <b>Expect</b> : After the first backward propagation, a new Family "Skinner" with
	 * father "Seymour" is introduced to the family model. The second run then must create a
	 * son "Seymor" in the now already existing family "Skinner".<br/>
	 * <b>Features</b>: bwd, add, fixed
	 */
	@Test
	public void testIncrementalInsertsFixedConfig() {
		tool.initiateSynchronisationDialogue();
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(util
					.execute(helperPerson::createHomer)
					.andThen(helperPerson::createMaggie));	
		tool.performIdleTargetEdit(helperPerson::setBirthdaysOfSimpson);
		util.assertPrecondition("Pre_IncrBwdFamily", "Pre_IncrBwdPerson");

		//------------		
		tool.performAndPropagateTargetEdit(helperPerson::createSeymour);
		util.assertPostcondition("FamilyAfterBwdInsertion1", "PersonAfterBwdInsertion1");
		tool.performAndPropagateTargetEdit(helperPerson::createSeymour);
		util.assertPostcondition("FamilyAfterBwdInsertion2", "PersonAfterBwdInsertion2");
		//------------			
	}
	
	/**
	 * <b>Test</b> for inserting of a Persons in a PersonRegister after the initial
	 * register has been transformed into a family model.<br/>
	 * <b>Expect</b> : FamilyRegister and Person model are structured as specified int he corresponding
	 * assertPostcondition statements.<br/>
	 * <b>Features</b>: bwd, add, runtime
	 */
	@Test
	public void testIncrementalInsertsDynamicConfig() {
		tool.initiateSynchronisationDialogue();
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(util
					.execute(helperPerson::createHomer)
					.andThen(helperPerson::createMaggie));	
		tool.performIdleTargetEdit(helperPerson::setBirthdaysOfSimpson);
		util.assertPrecondition("Pre_IncrBwdFamily", "Pre_IncrBwdPerson");

		//------------		
		tool.performAndPropagateTargetEdit(helperPerson::createSeymour);
		util.assertPostcondition("FamilyAfterBwdInsertion1", "PersonAfterBwdInsertion1");
		tool.performAndPropagateTargetEdit(helperPerson::createSeymour);
		util.assertPostcondition("FamilyAfterBwdInsertion2", "PersonAfterBwdInsertion2");
		
		// now setting !e^p
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createSeymour);
		util.assertPostcondition("FamilyAfterBwdInsertion3", "PersonAfterBwdInsertion3");
		
		// now setting !e^!p
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(helperPerson::createSeymour);
		util.assertPostcondition("FamilyAfterBwdInsertion4", "PersonAfterBwdInsertion4");
		
		// now setting e^p
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createSeymour);
		tool.saveModels("DynamicEandP");
		util.assertPostcondition("FamilyAfterBwdInsertion5", "PersonAfterBwdInsertion5");
		
		// now setting e^!p
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createBart)
				.andThen(helperPerson::createLisa));
		util.assertPostcondition("FamilyAfterBwdInsertion6", "PersonAfterBwdInsertion6");
		//------------			
	}
	
	/**
	 * <b>Test</b> for deleting Persons from the PersonRegister.<br/>
	 * <b>Expect</b> : FamilyRegister and Person model are structured as specified in the corresponding
	 * assertPostcondition statements.<br/>
	 * <b>Features</b>: bwd, del
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.initiateSynchronisationDialogue();
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(util
					.execute(helperPerson::createHomer)
					.andThen(helperPerson::createMaggie));	
		tool.performIdleTargetEdit(helperPerson::setBirthdaysOfSimpson);
		util.assertPrecondition("Pre_IncrBwdFamily", "Pre_IncrBwdPerson");
		
		//------------		
		tool.performAndPropagateTargetEdit(helperPerson::deleteHomer);
		tool.performAndPropagateTargetEdit(helperPerson::deleteMaggie);
		util.assertPostcondition("FamilyAfterBwdDeletion", "PersonAfterBwdDeletion");
		//------------
	}

}
