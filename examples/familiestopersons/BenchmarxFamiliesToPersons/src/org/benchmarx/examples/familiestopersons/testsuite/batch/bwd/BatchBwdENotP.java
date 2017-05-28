package org.benchmarx.examples.familiestopersons.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class BatchBwdENotP extends FamiliesToPersonsTestCase {

	public BatchBwdENotP(BXTool<Object, Object, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for creation of a single male person (Flanders, Rod).<br/>
	 * <b>Expect</b> the creation of a family member in the families model with
	 * the given first name, in a suitable family.  Creation of Children is preferred.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testCreateMalePersonAsSon() {
		// No precondition!
		// ---------------------------------
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(helperPerson::createRod);
		// ---------------------------------
		util.assertPostcondition("OneFamilyWithOneFamilyMemberSon", "PersonOneMaleMember"); 
	}
	
	/**
	 * <b>Test</b> for creation of family members in existing families.<br/>
	 * <b>Expect</b> the creation of a family member in the families model with
	 * the given first name, in a suitable family.  Creation of Children is preferred.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testCreateFamilyMembersInExistingFamilyAsChildren() {
		// No precondition!
		// ---------------------------------
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson::createRod)
				.andThen(helperPerson::createHomer)
				.andThen(helperPerson::createBart)
				.andThen(helperPerson::createMarge)
				.andThen(helperPerson::createLisa)
				.andThen(helperPerson::createMaggie));
		// ---------------------------------
		util.assertPostcondition("FamiliesWithChildrenOnly", "PersonsMulti"); 
	}
	
	/**
	 * <b>Test</b> for creation of family members in existing families.<br/>
	 * <b>Expect</b> As creation of children is preferred, two families should be created with no parents.
	 * All Barts should be in the same family as sons with the same first name.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testCreateDuplicateFamilyMembersInExistingFamilyAsChildren() {
		// No precondition!
		// ---------------------------------
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
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
		util.assertPostcondition("FamilyWithDuplicateChildrenOnly", "PersonsDuplicateMulti"); 
	}
}
