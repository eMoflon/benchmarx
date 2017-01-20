package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.roundtrip;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class CreatingPersons extends FamiliesToPersonsTestCase {
	
	public CreatingPersons(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
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
