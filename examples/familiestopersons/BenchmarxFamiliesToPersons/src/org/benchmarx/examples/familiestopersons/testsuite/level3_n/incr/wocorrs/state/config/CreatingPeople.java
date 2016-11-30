package org.benchmarx.examples.familiestopersons.testsuite.level3_n.incr.wocorrs.state.config;

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
	 * <b>Test</b> for creating multiple persons with different last names.
	 * <p>
	 * <b>Expect</b> the creation of corresponding family members in the
	 * families model with the given first names (based on specified
	 * preferences).
	 * <p>
	 * <b>Classification</b>: incr-wocorr-state-config
	 * <ul>
	 * <li><b>incr</b>: family register is required to avoid information loss
	 * (mapping of (fe)males to mothers/fathers or daughters/sons).
	 * <li><b>wocorr</b>: assumption based on unique naming works here as there
	 * are no members with the same names.
	 * <li><b>state</b>: it's possible to determine the applied changes from old
	 * and new states.
	 * <li><b>config</b>: there are two decisions to be made: (i) whether the
	 * member is to be created as a child or parent (preferred here) in their
	 * new family, and (ii) if a new family is to be created or an existing
	 * suitable family (preferred here) is to be used.
	 * <ul>
	 */
	@Test
	public void testMultiPersonWithDiffFamilyNamesTT() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
			
		util.assertPrecondition("Pre_NameChangeFamilyMember", "Pre_NameChangeOfPerson");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
					    .makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		tool.performAndPropagateTargetEdit(helperPerson::createBart);
		tool.performAndPropagateTargetEdit(helperPerson::createMarge);
		tool.performAndPropagateTargetEdit(helperPerson::createLisa);
		
		tool.performAndPropagateTargetEdit(helperPerson::createAmitabh);
		tool.performAndPropagateTargetEdit(helperPerson::createJaya);
		tool.performAndPropagateTargetEdit(helperPerson::createAbhishek);
		tool.performAndPropagateTargetEdit(helperPerson::createShweta);
		//----------------
		
		util.assertPostcondition("familyMulti", "PersonsNewMulti");
	}	
	
	/**
	 * Analogous to @link {@link #testMultiPersonWithDiffFamilyNamesTT()}, but preferring creating children.
	 */
	@Test
	public void testMultiPersonWithDiffFamilyNamesWithDiffPrefFT() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
			
		util.assertPrecondition("Pre_NameChangeFamilyMember", "Pre_NameChangeOfPerson");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false)
					    .makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		tool.performAndPropagateTargetEdit(helperPerson::createBart);
		tool.performAndPropagateTargetEdit(helperPerson::createLisa);
		
		tool.performAndPropagateTargetEdit(helperPerson::createAbhishek);
		tool.performAndPropagateTargetEdit(helperPerson::createShweta);
		//----------------
			
		util.assertPostcondition("familyMultiDiffPref", "PersonsNewMultiDiffPref");
	}	
	
	/**
	 * Analogous to @link {@link #testMultiPersonWithDiffFamilyNamesTT()}, but preferring creating new families.
	 */
	@Test
	public void testMultiPersonWithDiffFamilyNamesWithDiffPrefTF() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		
		util.assertPrecondition("Pre_NameChangeFamilyMember", "Pre_NameChangeOfPerson");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
					    .makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false);
		tool.performAndPropagateTargetEdit(helperPerson::createBart);
		
		tool.performAndPropagateTargetEdit(helperPerson::createAmitabh);
		tool.performAndPropagateTargetEdit(helperPerson::createAbhishek);
		//----------------
			
		util.assertPostcondition("familyMultiDiffPrefTF", "PersonsNewMultiDiffPrefTF");
	}	
	
	/**
	 * Analogous to @link {@link #testMultiPersonWithDiffFamilyNamesTT()}, but preferring creating children and new families.
	 */
	@Test
	public void testMultiPersonWithDiffFamilyNamesWithDiffPrefFF() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
			
		util.assertPrecondition("Pre_NameChangeFamilyMember", "Pre_NameChangeOfPerson");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false)
					    .makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false);
		tool.performAndPropagateTargetEdit(helperPerson::createBart);
		
		tool.performAndPropagateTargetEdit(helperPerson::createAbhishek);
		tool.performAndPropagateTargetEdit(helperPerson::createShweta);
		//----------------
		
		util.assertPostcondition("familyMultiDiffPrefFF", "PersonsNewMultiDiffPrefFF");
	}	
}
