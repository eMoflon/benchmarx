package org.benchmarx.examples.containerstominiyaml.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.benchmarx.examples.containerstominiyaml.testsuite.ContainersToMiniYAMLTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

/**
 * Test cases for backward transformations with parameters E and P set to true
 * Please note, that in general this leads to a non-deterministic behavior, 
 * which is hard to test. Thus, we restricted ourselves to a PersonRegister
 * configuration, which allows to deterministically execute the backward
 * transformation. (c.f., Test Case 2d on GitHub).
 */
public class BatchBackward extends ContainersToMiniYAMLTestCase {

	public BatchBackward(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for creation of a single male person (Flanders, Rod).<br/>
	 * <b>Expect</b> the creation of a family member in the families model with
	 * the given first name, in a suitable family.  Creation of parents is preferred.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testCreateMalePersonAsSon() {
		// No precondition!
		// ---------------------------------
		util.configure()
			.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createRod);
		// ---------------------------------
		util.assertPostcondition("OneFamilyWithOneFamilyMember", "PersonOneMaleMember"); 
	}
	
	/**
	 * <b>Test</b> for creation of family members in existing families.<br/>
	 * <b>Expect</b> the creation of a family member in the families model with
	 * the given first name, in a suitable family.  Creation of Children is preferred.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testCreateFamilyMembersInExistingFamilyAsParents() {
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
	}
}
