package org.benchmarx.examples.familiestopersons.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class BatchBwdNotEAndP extends FamiliesToPersonsTestCase {

	public BatchBwdNotEAndP(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for creation of a single male person (Flanders, Rod).<br/>
	 * <b>Expect</b> the creation of a family member in the families model with
	 * the given first name, in a suitable family.  Creation of parents is preferred.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testCreateMalePersonAsParent() {
		tool.initiateSynchronisationDialogue();
		// No precondition!
		// ---------------------------------
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createRod);
		// ---------------------------------
		util.assertPostcondition("OneFamilyWithOneFamilyMember", "PersonOneMaleMember"); 
	}
	
	/**
	 * <b>Test</b> for creation of family members in existing families.<br/>
	 * <b>Expect</b> the creation of a family member in the families model with
	 * the given first name, in a suitable family.  Creation of Parents is preferred.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testCreateFamilyMembersInNewFamilyAsParents() {
		tool.initiateSynchronisationDialogue();
		// No precondition!
		// ---------------------------------
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createRod)
				.andThen(helperPerson::createHomer)
				.andThen(helperPerson::createBart)
				.andThen(helperPerson::createMarge)
				.andThen(helperPerson::createLisa)
				.andThen(helperPerson::createMaggie));
		// ---------------------------------
		util.assertPostcondition("MultiFamiliesParents", "PersonsMulti"); 
	}
	
	@Test
	public void testCreateDuplicateFamilyMembersInNewFamilyAsParents() {
		tool.initiateSynchronisationDialogue();
		// No precondition!
		// ---------------------------------
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createRod)
				.andThen(helperPerson::createBart)
				.andThen(helperPerson::createHomer)
				.andThen(helperPerson::createBart)
				.andThen(helperPerson::createBart)
				.andThen(helperPerson::createMarge)
				.andThen(helperPerson::createLisa)
				.andThen(helperPerson::createMaggie));
		// ---------------------------------
		util.assertPostcondition("MultiFamiliesWithDuplicateNamesParents", "PersonsDuplicateMulti"); 
	}
	
//	@Test
//	public void testStability() {
//		tool.initiateSynchronisationDialogue();
//		// No precondition!
//		// ---------------------------------
//		util.configure()
//			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
//			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
//		tool.performAndPropagateTargetEdit(util
//				.execute(helperPerson::createRod)
//				.andThen(helperPerson::createBart)
//				.andThen(helperPerson::createHomer)
//				.andThen(helperPerson::createBart)
//				.andThen(helperPerson::createBart)
//				.andThen(helperPerson::createMarge)
//				.andThen(helperPerson::createLisa)
//				.andThen(helperPerson::createMaggie));
//		// ---------------------------------
//		util.assertPostcondition("MultiFamiliesWithDuplicateNamesParents", "PersonsDuplicateMulti"); 
//		
//		tool.performAndPropagateTargetEdit(helperPerson::idleDelta);
//		util.assertPostcondition("MultiFamiliesWithDuplicateNamesParents", "PersonsDuplicateMulti");
//	}
//	
//	@Test
//	public void testHippocraticness() {
//		tool.initiateSynchronisationDialogue();
//		// No precondition!
//		// ---------------------------------
//		util.configure()
//			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
//			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
//		tool.performAndPropagateTargetEdit(util
//				.execute(helperPerson::createRod)
//				.andThen(helperPerson::createBart)
//				.andThen(helperPerson::createHomer)
//				.andThen(helperPerson::createBart)
//				.andThen(helperPerson::createBart)
//				.andThen(helperPerson::createMarge)
//				.andThen(helperPerson::createLisa)
//				.andThen(helperPerson::createMaggie));
//		// ---------------------------------
//		util.assertPostcondition("MultiFamiliesWithDuplicateNamesParents", "PersonsDuplicateMulti"); 
//		
//		tool.performAndPropagateTargetEdit(helperPerson::hippocraticDelta);
//		util.assertPostcondition("MultiFamiliesWithDuplicateNamesParents", "PersonsDuplicateMulti2");
//	}
}
