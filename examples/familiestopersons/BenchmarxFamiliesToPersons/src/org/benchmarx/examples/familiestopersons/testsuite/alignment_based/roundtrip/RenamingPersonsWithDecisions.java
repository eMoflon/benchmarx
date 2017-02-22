package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.roundtrip;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class RenamingPersonsWithDecisions extends FamiliesToPersonsTestCase {
	
	public RenamingPersonsWithDecisions(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for changing a person's family name. <br/>
	 * <b>Expect</b> the corresponding family member must be associated with
	 * another family as their family name does not fit anymore. In this case, a
	 * fitting family already exists and must be used as this is preferred.
	 * <br/>
	 * <b>Features</b>: round trip, add+attribute, structural, runtime
	 */
	@Test
	public void testFamilyNameChangeOfPersonWhereSuitableFamilyExists() {
		tool.initiateSynchronisationDialogue();
//		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createBachchanFamily)
//			       							   .andThen(helperFamily::createFatherAmitabh));
//		tool.performAndPropagateSourceEdit(helperFamily::createOtherRemainingMembersInFamilyBachchan);
//		tool.performAndPropagateSourceEdit(helperFamily::createNandaFamily);
		
		util.assertPrecondition("Pre_MemberFamilyNameChangeToExist", "Pre_PersonFamilyNameChangeToExist");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
						.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		tool.performAndPropagateTargetEdit(helperPerson::familyNameChangeOfShweta);
		//----------------
		util.assertPostcondition("MemberFamilyNameChangeToExist","PersonFamilyNameChangeToExist");
	}
	
	/**
	 * Analogous to
	 * {@link #testFamilyNameChangeOfPersonWhereSuitableFamilyExists()}, but
	 * here a new family is to be created even though a suitable family actually
	 * exists and could be used. <br/>
	 * <b>Features</b>: round trip, add+attribute, structural, runtime
	 */
	@Test
	public void testFamilyNameChangeOfPersonButPreferCreatingNewFamily() {
		tool.initiateSynchronisationDialogue();
//		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createBachchanFamily)
//			       							   .andThen(helperFamily::createFatherAmitabh));
//		tool.performAndPropagateSourceEdit(helperFamily::createOtherRemainingMembersInFamilyBachchan);
//		
//		tool.performAndPropagateSourceEdit(helperFamily::createNandaFamily);
		
		util.assertPrecondition("Pre_MemberFamilyNameChangeToExist", "Pre_PersonFamilyNameChangeToExist");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
						.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false);
		tool.performAndPropagateTargetEdit(helperPerson::familyNameChangeOfShweta);
		//----------------
		util.assertPostcondition("MemberFamilyNameChangeToExistNew","PersonFamilyNameChangeToExistNew");
	}

	/**
	 * Analogous to
	 * {@link #testFamilyNameChangeOfPersonWhereSuitableFamilyExists()} but here
	 * a suitable family does not exist. The only choice to be made, therefore,
	 * is if the female person should be a daughter or mother (preferred here)
	 * of the new family. <br/>
	 * <b>Features</b>: round trip, add+attribute, structural, runtime
	 */
	@Test
	public void testFamilyNameChangeOfPersonWhereSuitableFamilyDoesNotExist() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createSimpsonFamily)
											   .andThen(helperFamily::createFatherHomer));
//		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
			
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::familyNameChangeOfLisa);
		//----------------
		util.assertPostcondition("MemberFamilyNameChange","PersonFamilyNameChange");
	}

	/**
	 * <b>Test</b> for changing a person's full name. <br/>
	 * <b>Expected</b>: first name of the corresponding family member and their
	 * family name must be changed in the families model <br/>
	 * <b>Features</b>: round trip, add+attribute, structural, runtime
	 */
	@Test
	public void testFullNameChangeFather() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
//		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::fullNameChangeOfHomer);
		//----------------
		util.assertPostcondition("MemberFullNameChange","PersonFullNameChange");
	}
	
	/**
	 * Analogous to @link {@link #testFullNameChangeFather()}, but here the
	 * corresponding family member is a son, not a father in the family. <br/>
	 * <b>Features</b>: round trip, add+attribute, structural, runtime
	 */
	@Test
	public void testFullNameChangeSon() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
//		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::fullNameChangeOfBart);
		//----------------
		util.assertPostcondition("MemberFullNameChangeSecond","PersonFullNameChangeSecond");
	}
}
