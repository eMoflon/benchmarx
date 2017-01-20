package org.benchmarx.examples.familiestopersons.testsuite.batch.bwd;

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
	 * <b>Test</b> for creation of a single male person.<br/>
	 * <b>Expect</b> the creation of a family member in the families model with
	 * the given first name, in a suitable family.  A parent should be created if possible.<br/>
	 * <b>Features</b>: bwd, runtime
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
	 * preference is to create a child.<br/>
	 * <b>Features</b>: bwd, runtime
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
	 * Analogous to @link {@link #testCreateMalePersonAsFather()}, but here a mother is to be created.<br/>
	 * <b>Features</b>: bwd, runtime
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
	 * Analogous to @link {@link #testCreateMalePersonAsFather()}, but here a daughter is to be created.<br/>
	 * <b>Features</b>: bwd, runtime
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
