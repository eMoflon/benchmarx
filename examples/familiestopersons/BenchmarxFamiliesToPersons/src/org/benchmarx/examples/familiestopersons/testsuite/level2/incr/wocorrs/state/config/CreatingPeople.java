package org.benchmarx.examples.familiestopersons.testsuite.level2.incr.wocorrs.state.config;

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
	 * <b>Test</b> for creation of a single male person, for which a suitable
	 * father already exists in the family register.
	 * <p>
	 * <b>Expect</b> the creation of a son in the families model with the given
	 * first name.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-state-config
	 * <ul>
	 * <li><b>batch</b>: old state of the family register is required to avoid
	 * losing the mapping of (fe)males to mothers/daughters and fathers/sons.
	 * <li><b>wocorr</b>: assumption based on unique naming works here as there
	 * are no members with the same name.
	 * <li><b>state</b>: it's possible to determine that a new person was
	 * created.
	 * <li><b>config</b>: even though creating parents is preferred to creating
	 * children, as existing families are to be used, bart must be created as a
	 * son since Homer is already the father of the existing family.
	 * <ul>
	 */
	@Test
	public void testCreateMalePersonForWhichFatherExist()
	{
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		
		util.assertPrecondition("Pre_oneFamilyWithOneFamilyMemberExistSon", "Pre_PersonMaleMemberExistSon");
		// ---------------------------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		util.configure().makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		tool.performAndPropagateTargetEdit(helperPerson::createBart);
		// ---------------------------------
			
		util.assertPostcondition("oneFamilyWithOneFamilyMemberExistSon", "PersonMaleMemberExistSon");	
	}
	
	/**
	 * Analogous to {@link #testCreateMalePersonForWhichFatherExist()}, but here
	 * for a mother and daughter.
	 */
	@Test
	public void testCreatePersonDaughterMotherExist()
	{
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createMotherMarge);
		
		util.assertPrecondition("Pre_oneFamilyWithOneFamilyMemberExistDaughter", "Pre_PersonFemaleMemberExistDaughter");
		// ---------------------------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		util.configure().makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		tool.performAndPropagateTargetEdit(helperPerson::createLisa);
		// ---------------------------------
		util.assertPostcondition("oneFamilyWithOneFamilyMemberExistDaughter", "PersonFemaleMemberExistDaughter");	
	}
}
