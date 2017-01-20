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
	 * <b>Test</b> for name change of a family.
	 * <p>
	 * <b>Expect</b> a change in the family name part of the full name of all
	 * associated persons in the person register, i.e., by replacing the old
	 * family name with the new one.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-delta-auto
	 * <ul>
	 * <li><b>incr</b>: old person register is required to avoid losing
	 * birthdays.
	 * <li><b>wocorr</b>: it's possible to guess required correspondences as there is only one family and full names of persons are unique (in this example).
	 * name has to be renamed in the persons model which is clear.
	 * <li><b>delta</b>: renaming cannot be distinguished from combined deletion and creation.
	 * <li><b>auto</b>: propagation is deterministic.
	 * </ul>
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
	 * <p>
	 * <b>Expect</b> a full name change of the corresponding person by replacing
	 * the old first name with the new one.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-delta-auto
	 * <ul>
	 * <li><b>incr</b>: old person register is required to avoid losing
	 * birthdays.
	 * <li><b>wocorr</b>: assumption based on unique names works for this
	 * example.
	 * <li><b>delta</b>: renaming cannot be distinguised from combined deletion
	 * and creation.
	 * <li><b>auto</b>: propagation is deterministic.
	 * </ul>
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
	 * same (old) name exists.
	 * <p>
	 * <b>Expect</b> a change in the family name part of the full name of all
	 * associated persons (corresponding to family members) in the persons
	 * model, i.e., by replacing the old family name with the new one.
	 * <p>
	 * <b>Classification</b>: incr-wcorr-delta-auto
	 * <ul>
	 * <li><b>incr</b>: renaming persons requires the old consistent state as
	 * their birthdays would be otherwise lost.
	 * <li><b>wcorr</b>: traceability links are required as it is impossible to
	 * guess correctly which persons correspond to which family members, given
	 * that there are multiple persons with the exact same full name.
	 * <li><b>delta</b>: renaming is inherently delta-based as it is impossible to
	 * distinguish between renaming and deletion + creation.
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * </ul>
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
	 * another family with the same name exists). 
	 * <p>
	 * <b>Expect</b> the
	 * corresponding person's full name to be changed by replacing the first
	 * name part with the new value.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-delta-auto
	 * <ul>
	 * <li><b>incr</b>: renaming the corresponding person requires the old state
	 * to avoid losing their birthday.
	 * <li><b>wcorr</b>: traceability links are required as it is impossible to
	 * guess the right "mother" from persons with exactly the same name in the
	 * persons register.
	 * <li><b>delta</b>: renaming is inherently delta-based as it is impossible
	 * to distinguish between renaming and deletion + creation.
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * </ul>
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
