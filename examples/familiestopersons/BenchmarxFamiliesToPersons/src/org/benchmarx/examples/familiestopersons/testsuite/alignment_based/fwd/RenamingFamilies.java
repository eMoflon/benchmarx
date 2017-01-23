package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class RenamingFamilies extends FamiliesToPersonsTestCase {

	public RenamingFamilies(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for name change of a family. <br/>
	 * <b>Expect</b> a change in the family name part of the full name of all
	 * associated persons in the person register, i.e., by replacing the old
	 * family name with the new one. <br/>
	 * <b>Features</b>: fwd, add+attribute, structural, fixed
	 */
	@Test
	public void testFamilyNameChange()
	{
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyNameSimpsonChange);
		//------------
		util.assertPostcondition("NameChangeFamily", "NameChangePerson");
	}
	
	/**
	 * <b>Test</b> for first name change of a family member (here the father).
	 * <br/>
	 * <b>Expect</b> a full name change of the corresponding person by replacing
	 * the old first name with the new one. <br/>
	 * <b>Features</b>: fwd, add+attribute, structural,fixed
	 */
	@Test
	public void testFamilyMemberNameChangeFather()
	{	
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		
		util.assertPrecondition("Pre_NameChangeFamilyMember", "Pre_NameChangeOfPerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyFatherHomerNameChange);
		//------------
		util.assertPostcondition("NameChangeFamilyMember", "NameChangeOfPerson");
	}
	
	/**
	 * Analogous to {@link #testFamilyMemberNameChangeFather()}
	 * <b>Features</b>: fwd, add+attribute, structural,fixed
	 */
	@Test
	public void testFamilyMemberNameChangeMother() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyMotherMargeNameChange);
		//------------
		util.assertPostcondition("NameChangeFamilyMemberMother", "NameChangeOfPersonMother");
	}
	
	/**
	 * Analogous to {@link #testFamilyMemberNameChangeFather()}
	 * <b>Features</b>: fwd, add+attribute, structural,fixed
	 */
	@Test
	public void testFamilyMemberNameChangeDaughter() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyDaughterLisaNameChange);
		//------------
		util.assertPostcondition("NameChangeFamilyMemberDaughter", "NameChangeOfPersonDaughter");
	}
	
	/**
	 * Analogous to {@link #testFamilyMemberNameChangeFather()}
	 * <b>Features</b>: fwd, add+attribute, structural,fixed
	 */
	@Test
	public void testFamilyMemberNameChangeSon() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familySonBartNameChange);
		//------------
		util.assertPostcondition("NameChangeFamilyMemberSon", "NameChangeOfPersonSon");
	}
	
	/**
	 * <b>Test</b> for name change of one family, where another family with the
	 * same (old) name exists. <br/>
	 * <b>Expect</b> a change in the family name part of the full name of all
	 * associated persons (corresponding to family members) in the persons
	 * model, i.e., by replacing the old family name with the new one. <br/>
	 * <b>Features</b>: fwd, add+attribute, corr-based, operational, fixed
	 */
	@Test
	public void testNonUniqueFamilyNameChange()
	{
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherGeorge);
		tool.performAndPropagateSourceEdit(helperFamily::createOtherSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeOtherFamily", "Pre_NameChangeOtherPerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyNameOtherSimpsonChange);
		//------------
		util.assertPostcondition("NameChangeOtherFamily", "NameChangeOtherPerson");
	}
	
	/**
	 * <b>Test</b> for first name change of the mother of a family (where
	 * another family with the same name exists). <br/>
	 * <b>Expect</b> the corresponding person's full name to be changed by
	 * replacing the first name part with the new value. <br/>
	 * <b>Features</b>: fwd, add+attribute, structural, fixed
	 */
	@Test
	public void testNameChangeOfMotherInNonUniqueFamily() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherGeorge);
		tool.performAndPropagateSourceEdit(helperFamily::createOtherSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeOtherFamily", "Pre_NameChangeOtherPerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyMotherJaneNameChange);
		//------------
		util.assertPostcondition("NameChangeFamilyMemberOtherMother", "NameChangeOfPersonOtherMother");
	}
	
	/**
	 * Analogous to {@link #testNameChangeOfMotherInNonUniqueFamily()}
	 * <b>Features</b>: fwd, add+attribute, structural, fixed 
	 */
	@Test
	public void testNameChangeOfDaughterInNonUniqueFamily() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherGeorge);
		tool.performAndPropagateSourceEdit(helperFamily::createOtherSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeOtherFamily", "Pre_NameChangeOtherPerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyDaughterJudyNameChange);
		//------------
		util.assertPostcondition("NameChangeFamilyMemberOtherDaughter", "NameChangeOfPersonOtherDaughter");
	}
	
	/**
	 * Analogous to {@link #testNameChangeOfMotherInNonUniqueFamily()}
	 * <b>Features</b>: fwd, add+attribute, structural, fixed
	 */
	@Test
	public void testNameChangeOfSonInNonUniqueFamily() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherGeorge);
		tool.performAndPropagateSourceEdit(helperFamily::createOtherSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeOtherFamily", "Pre_NameChangeOtherPerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familySonElroyNameChange);
		//------------
		util.assertPostcondition("NameChangeFamilyMemberOtherSon", "NameChangeOfPersonOtherSon");
	}
	
	/**
	 * Analogous to {@link #testNameChangeOfMotherInNonUniqueFamily()}
	 * <b>Features</b>: fwd, add+attribute, structural, fixed
	 */
	@Test
	public void testFamilyMemberNameChangeFatherOfSameFamilyName()
	{	
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherGeorge);
		
		util.assertPrecondition("Pre_NameChangeOtherFamilyMember", "Pre_NameChangeOfOtherPerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyFatherGeorgeNameChange);
		//------------
		util.assertPostcondition("NameChangeOtherFamilyMember", "NameChangeOfOtherPerson");
	}
}
