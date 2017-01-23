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
	 * father already exists in the family register. <br/>
	 * <b>Expect</b> the creation of a son in the families model with the given
	 * first name. <br/>
	 * <b>Features</b>: round trip, add, runtime
	 */
	@Test
	public void testCreateMalePersonForWhichFatherExists()
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
	 * for a mother and daughter.<br/>
	 * <b>Features</b>: round trip, add, runtime
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
	 * <br/>
	 * <b>Expect</b> the creation of corresponding family members in the
	 * families model with the given first names (based on specified
	 * preferences). <br/>
	 * <b>Features</b>: round trip, add, runtime
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
	 * Analogous to @link {@link #testMultiPersonWithDiffFamilyNamesTT()}, but
	 * preferring creating children.<br/>
	 * <b>Features</b>: round trip, add, runtime
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
	 * Analogous to @link {@link #testMultiPersonWithDiffFamilyNamesTT()}, but
	 * preferring creating new families.<br/>
	 * <b>Features</b>: round trip, add, runtime
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
	 * Analogous to @link {@link #testMultiPersonWithDiffFamilyNamesTT()}, but
	 * preferring creating children and new families.<br/>
	 * <b>Features</b>: round trip, add, runtime
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
