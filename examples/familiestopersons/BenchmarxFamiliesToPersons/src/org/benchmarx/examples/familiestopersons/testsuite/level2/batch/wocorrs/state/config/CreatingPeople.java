package org.benchmarx.examples.familiestopersons.testsuite.level2.batch.wocorrs.state.config;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class CreatingPeople extends FamiliesToPersonsTestCase {
	public CreatingPeople(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for creation of a single male person.
	 * <p>
	 * <b>Expect</b> the creation of a family member in the families model with
	 * the given first name, in a suitable family.
	 * <p>
	 * <b>Classification</b>: batch-wocorr-state-config
	 * <ul>
	 * <li><b>batch</b>: the family register is previously empty so no
	 * information loss.
	 * <li><b>wocorr</b>: assumption based on unique naming works here as there
	 * are no members with the same name.
	 * <li><b>state</b>: it's possible to determine that a new person was
	 * created.
	 * <li><b>config</b>: there is only one decisions to be made here: (i)
	 * whether the member is to be created as a child or as a parent (choice for this test) in their
	 * new family.
	 * <ul>
	 */
	@Test
	public void testCreateMalePersonAsFather()
	{
		tool.initiateSynchronisationDialogue();
		
		// No precondition!
		// ---------------------------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createHomer);
		// ---------------------------------
		util.assertPostcondition("OneFamilyWithOneFamilyMember", "PersonOneMaleMember");	
	}
	
	/**
	 * Analogous to @link {@link #testCreateMalePersonAsFather()}, but here the
	 * choice is to create a child.
	 */
	@Test
	public void testCreateMalePersonAsSon()
	{
		tool.initiateSynchronisationDialogue();
	
		// No precondition!
		// ---------------------------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(helperPerson::createBart);
		// ---------------------------------
		util.assertPostcondition("oneFamilyWithOneFamilyMemberSon", "PersonOneMaleMemberSon");
	}
	
	/**
	 * Analogous to @link {@link #testCreateMalePersonAsFather()}, but here a mother is to be created.
	 */
	@Test
	public void testCreatePersonMother()
	{
		tool.initiateSynchronisationDialogue();
	
		// No precondition!
		// ---------------------------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::createMarge);
		// ---------------------------------
		util.assertPostcondition("oneFamilyWithOneFamilyMemberMother", "PersonOneFemaleMemberMother");
	}
	
	/**
	 * Analogous to @link {@link #testCreateMalePersonAsFather()}, but here a daughter is to be created.
	 */
	@Test
	public void testCreatePersonDaughter()
	{
		tool.initiateSynchronisationDialogue();
		
		// No precondition!
		// ---------------------------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(helperPerson::createLisa);
		// ---------------------------------
		util.assertPostcondition("oneFamilyWithOneFamilyMemberDaughter", "PersonOneFemaleMemberDaughter");	
	}
	
}
