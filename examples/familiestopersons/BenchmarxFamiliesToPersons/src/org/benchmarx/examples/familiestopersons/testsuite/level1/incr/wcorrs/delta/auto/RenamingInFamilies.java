package org.benchmarx.examples.familiestopersons.testsuite.level1.incr.wcorrs.delta.auto;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;


public class RenamingInFamilies extends FamiliesToPersonsTestCase {

	public RenamingInFamilies(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
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
