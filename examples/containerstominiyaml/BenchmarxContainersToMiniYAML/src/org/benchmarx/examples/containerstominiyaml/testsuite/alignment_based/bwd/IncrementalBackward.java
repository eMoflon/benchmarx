package org.benchmarx.examples.containerstominiyaml.testsuite.alignment_based.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.benchmarx.examples.containerstominiyaml.testsuite.ContainersToMiniYAMLTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class IncrementalBackward extends ContainersToMiniYAMLTestCase {

	public IncrementalBackward(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting of a Person in a PersonRegister after the initial
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
	 * <b>Expect</b> : FamilyRegister and Person model are structured as specified in the corresponding
	 * assertPostcondition statements.<br/>
	 * <b>Features</b>: bwd, add, runtime
	 */
	@Test
	public void testIncrementalInsertsDynamicConfig() {
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
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createHomer);
		// reconfigure, to allow the creation of a child in the existing family
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(helperPerson::createMaggie);	
		tool.performIdleTargetEdit(helperPerson::setBirthdaysOfSimpson);
		util.assertPrecondition("Pre_IncrBwdFamilyFatherChild", "Pre_IncrBwdPerson");
		
		//------------		
		tool.performAndPropagateTargetEdit(helperPerson::deleteHomer);
		tool.performAndPropagateTargetEdit(helperPerson::deleteMaggie);
		util.assertPostcondition("FamilyAfterBwdDeletion", "PersonAfterBwdDeletion");
		//------------
	}
	
	/**
	 * <b>Test</b> for renaming of a Person in a PersonRegister after the initial
	 * register has been transformed into a family model.<br/>
	 * <b>Expect</b> : Model states as described in the postcondition.<br/>
	 * <b>Features</b>: bwd, attribute, structural, corr-based, runtime
	 */
	@Test
	public void testIncrementalRenamingDynamic() {
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(helperPerson::createRod);
		tool.performIdleTargetEdit(helperPerson::setBirthdayOfRod);		
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createHomer);
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createMarge)
				.andThen(helperPerson::createBart));
		tool.performAndPropagateTargetEdit(util		
				.execute(helperPerson::createLisa)
				.andThen(helperPerson::createMaggie));
		tool.performIdleTargetEdit(helperPerson::changeAllBirthdays);
		tool.performAndPropagateTargetEdit(helperPerson::createBart);
		tool.performIdleTargetEdit(helperPerson::setBirthdayOfYoungerBart);
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createBart);
		tool.performIdleTargetEdit(helperPerson::setBirthdayOfFatherBart);
		util.assertPrecondition("Pre_IncrBwdFamilyRenameDynamic", "Pre_IncrBwdPersonRenameDynamic");
		
		//----------------------
		tool.performAndPropagateTargetEdit(helperPerson::firstNameChangeOfBart);
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::fullNameChangeOfOtherBart);
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(helperPerson::fullNameChangeOfFatherBart);
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(helperPerson::familyNameChangeOfLisa);
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::fullNameChangeOfMarge);
		
		util.assertPostcondition("FamilyAfterBwdIncrRenameDynamic", "PersonAfterBwdIncrRenameDynamic");
		//----------------------
	}
	
	/**
	 * <b>Test</b> for deleting and recreating a Person in a PersonRegister after the initial
	 * register has been transformed into a family model.<br/>
	 * <b>Expect</b> : Model states as described in the postcondition.<br/>
	 * <b>Features</b>: bwd, del+add, structural, runtime
	 */
	@Test
	public void testIncrementalMixedDynamic() {
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createMaggie);	
		tool.performAndPropagateTargetEdit(helperPerson::createHomer);
		tool.performIdleTargetEdit(helperPerson::setBirthdaysOfSimpson);
		util.assertPrecondition("Pre_IncrBwdFamily", "Pre_IncrBwdPerson");

		//------------		
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::deleteHomer)
				.andThen(helperPerson::createHomer));
		util.assertPostcondition("FamilyAfterBwdMixed", "PersonAfterBwdMixed");
	}
	
	/**
	 * <b>Test</b> for creating Persons in a PersonRegister after the initial
	 * register has been transformed into a family model. In the subsequent
	 * backward transformations, the order of inserting the persons affects
	 * the target model. <br/>
	 * <b>Expect</b> : Model states as described in the postcondition.<br/>
	 * <b>Features</b>: bwd, add, operational, runtime
	 */
	@Test
	public void testIncrementalOperational() {
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(helperPerson::createMaggie);
		tool.performIdleTargetEdit(helperPerson::setBirthdayOfMaggie);
		util.assertPrecondition("Pre_IncrBwdOpFamily", "Pre_IncrBwdOpPerson");
		
		//------------		
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createMarge)
				.andThen(helperPerson::createLisa)				
				.andThen(helperPerson::createHomer)
				.andThen(helperPerson::createBart)
				.andThen(helperPerson::createMaggie)
				.andThen(helperPerson::createLisa));
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createLisa);					
		util.assertPostcondition("FamilyAfterIncrOp", "PersonAfterIncrOp");
	}
	
	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> Nothing should be changed after an idle target delta.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testStability() {
		// No precondition!
		// ---------------------------------
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createRod)
				.andThen(helperPerson::createHomer)				
				.andThen(helperPerson::createMarge));
		// ---------------------------------
		util.assertPostcondition("FamilyWithParentsOnly", "PersonsMultiDeterministic"); 
		
		// issue the same transformation a second time
		tool.performAndPropagateTargetEdit(helperPerson::idleDelta);
		util.assertPostcondition("FamilyWithParentsOnly", "PersonsMultiDeterministic"); 
	}
	
	@Test
	public void testHippocraticness() {
		// No precondition!
		// ---------------------------------
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createRod)
				.andThen(helperPerson::createHomer)				
				.andThen(helperPerson::createMarge));
		// ---------------------------------
		util.assertPostcondition("FamilyWithParentsOnly", "PersonsMultiDeterministic"); 
		
		tool.performAndPropagateTargetEdit(helperPerson::hippocraticDelta);
		util.assertPostcondition("FamilyWithParentsOnly", "PersonsMultiDeterministic2"); 
	}

}
