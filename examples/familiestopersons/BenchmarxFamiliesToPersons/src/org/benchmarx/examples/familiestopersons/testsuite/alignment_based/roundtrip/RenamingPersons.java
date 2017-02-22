package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.roundtrip;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class RenamingPersons extends FamiliesToPersonsTestCase {
	
	public RenamingPersons(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for changing a person's first name. <br/>
	 * <b>Expected</b>: only the first name of the corresponding family member
	 * should be changed. <br/>
	 * <b>Features</b>: round trip, add+attribute, structural, fixed
	 */
	@Test
	public void testFirstNameChangePerson() {
		tool.initiateSynchronisationDialogue();
//		tool.performAndPropagateSourceEdit(util
//				.execute(helperFamily::createSimpsonFamily)
//				.andThen(helperFamily::createFatherHomer)
//				.andThen(helperFamily::createSimpsonFamilyMembers));
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");	
		//----------------
		tool.performAndPropagateTargetEdit(helperPerson::firstNameChangeOfHomer);
		//----------------
		util.assertPostcondition("MemberNameChange","PersonNameChange");
	}
	
	/**
	 * <b>Test</b> for changing a person's first name (where another person with
	 * the same name exists). <br/>
	 * <b>Expected</b>: the first name of the corresponding member in the
	 * families model should be changed. <br/>
	 * <b>Features</b>: round trip, add+attribute, corr-based, structural, fixed
	 */
	@Test
	public void testFirstNameChangeOfNonUniquePerson() {
		tool.initiateSynchronisationDialogue();
//		tool.performAndPropagateSourceEdit(util
//				.execute(helperFamily::createSimpsonFamily)
//				.andThen(helperFamily::createFatherHomer)
//				.andThen(helperFamily::createSimpsonFamilyMembers));
//		tool.performAndPropagateTargetEdit(helperPerson::createOtherBart);
			
		util.assertPrecondition("Pre_MemberNameChangeOther", "Pre_PersonNameChangeOther");
		//----------------
		tool.performAndPropagateTargetEdit(helperPerson::firstNameChangeOfBart);
		//----------------
		util.assertPostcondition("MemberNameChangeOther","PersonNameChangeOther");
	}
}
