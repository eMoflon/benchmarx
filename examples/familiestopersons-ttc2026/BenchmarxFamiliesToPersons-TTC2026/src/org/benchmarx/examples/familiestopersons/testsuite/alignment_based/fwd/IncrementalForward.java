package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class IncrementalForward extends FamiliesToPersonsTestCase {

	public IncrementalForward(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for inserting of a family members into an existing family after
	 * the birthday dates in the Person register have been changed. <br/>
	 * <b>Expect</b> : New persons are added to the register, while the old persons
	 * remain unchanged (their altered birthday dates are preserved). <br/>
	 * <b>Features</b>: fwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createSkinnerFamily, //
				helperFamily::createFlandersFamily, //
				helperFamily::createSonRod, //
				helperFamily::createSimpsonFamily, //
				helperFamily::createFatherBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfRod));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfFatherBart));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createNewFamilySimpsonWithMembers));
		tool.performIdleTargetEdit(trgEdit(helperPerson::changeAllBirthdays));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSonBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfYoungerBart));

		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");

		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createFatherNed, //
				helperFamily::createMotherMaude, //
				helperFamily::createSonTodd));
		// ------------
		util.assertPostcondition("FamilyAfterInsertion", "PersonAfterInsertion");
	}

	/**
	 * <b>Test</b> for deleting family members. After creating the person register,
	 * set birthdays and make sure that the sons with the name Bart can be
	 * distinguished. Then delete the younger son Bart from the Family register.
	 * <b>Expect</b>: Delete the correct Person in the Person Register
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createSkinnerFamily, //
				helperFamily::createFlandersFamily, //
				helperFamily::createSonRod, //
				helperFamily::createSimpsonFamily, //
				helperFamily::createFatherBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfRod));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createNewFamilySimpsonWithMembers));
		tool.performIdleTargetEdit(trgEdit(helperPerson::changeAllBirthdays));

		util.assertPrecondition("Pre_IncrFwdFamilyForDeletion", "Pre_IncrFwdPersonForDeletion");
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSonBart));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::deleteFirstSonBart));
		// ------------
		util.assertPostcondition("FamilyAfterDeletion", "PersonAfterDeletion");
	}

	/**
	 * <b>Test</b> for renaming a family. After creating the person register, set
	 * birthdays. Then rename the complete Family Simpson to Bouvier. <b>Expect</b>:
	 * Change the name of the affected Persons in the Person Register
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalRename() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createSkinnerFamily, //
				helperFamily::createFlandersFamily, //
				helperFamily::createSonRod, //
				helperFamily::createSimpsonFamily, //
				helperFamily::createFatherBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfRod));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfFatherBart));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createNewFamilySimpsonWithMembers));
		tool.performIdleTargetEdit(trgEdit(helperPerson::changeAllBirthdays));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSonBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfYoungerBart));

		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::renameSimpsonToBouvier));
		// ------------
		util.assertPostcondition("FamilyAfterRename", "PersonAfterRename");
	}

	/**
	 * <b>Test</b> for moving family members to different families and also changing
	 * their roles. After creating the person register, set birthdays. Then move
	 * Lisa to Flanders as mother and Marge to Skinner as mother. <b>Expect</b>:
	 * Change the name of the affected Persons in the Person Register
	 * <b>Features</b>: fwd, del+add, fixed, structural
	 */
	@Test
	public void testIncrementalMove() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createSkinnerFamily, //
				helperFamily::createFlandersFamily, //
				helperFamily::createSonRod, //
				helperFamily::createSimpsonFamily, //
				helperFamily::createFatherBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfRod));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfFatherBart));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createNewFamilySimpsonWithMembers));
		tool.performIdleTargetEdit(trgEdit(helperPerson::changeAllBirthdays));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSonBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfYoungerBart));

		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::moveLisa, //
				helperFamily::moveMarge));
		// ------------
		util.assertPostcondition("FamilyAfterMove", "PersonAfterMove");
	}

	/**
	 * <b>Test</b> for deleting and re-creating family members. After creating the
	 * person register, set birthdays. Then delete and re-create Homer. <br/>
	 * <b>Expect</b>: Person register remains unchanged, except for "Simpson,
	 * Homer", who should be re-created with the default birthday. <br/>
	 * <b>Features</b>: fwd, structural, add+del, fixed
	 */
	@Test
	public void testIncrementalMixed() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createSkinnerFamily, //
				helperFamily::createFlandersFamily, //
				helperFamily::createSonRod, //
				helperFamily::createSimpsonFamily, //
				helperFamily::createFatherBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfRod));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfFatherBart));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createNewFamilySimpsonWithMembers));
		tool.performIdleTargetEdit(trgEdit(helperPerson::changeAllBirthdays));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSonBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfYoungerBart));

		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::deleteFatherHomer));
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createFatherHomer));
		// ------------
		util.assertPostcondition("FamilyAfterMixed", "PersonAfterMixed");
	}

	/**
	 * <b>Test</b> for moving a family member to a new family and changing her role
	 * from daughter to son. After creating the person register, set birthdays. Then
	 * move daughter Maggie to the Flanders family as a son. <b>Expect</b>: A new
	 * male person should be created in the PersonRegister, but the birthday of
	 * Maggie should be retained. <b>Features</b>: fwd, structural, add+del, fixed
	 */
	@Test
	public void testIncrementalMoveRoleChange() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createSkinnerFamily, //
				helperFamily::createFlandersFamily, //
				helperFamily::createSonRod, //
				helperFamily::createSimpsonFamily, //
				helperFamily::createFatherBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfRod));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfFatherBart));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createNewFamilySimpsonWithMembers));
		tool.performIdleTargetEdit(trgEdit(helperPerson::changeAllBirthdays));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSonBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfYoungerBart));

		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::moveMaggieAndChangeRole));
		// ------------
		util.assertPostcondition("FamilyAfterMoveRoleChange", "PersonAfterMoveRoleChange");
	}

	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta does
	 * not change the target model.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testStability() {
		tool.noPrecondition();
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createSonBart));
		// ------------
		util.assertPostcondition("FamilyWithDuplicateMember", "PersonWithSameName");

		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::idleDelta));
		util.assertPostcondition("FamilyWithDuplicateMember", "PersonWithSameName");
	}

	/**
	 * <b>Test</b> for hippocraticness of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after creating an empty family
	 * does not change the person register.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testHippocraticness() {
		tool.noPrecondition();
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createSonBart));
		// ------------
		util.assertPostcondition("FamilyWithDuplicateMember", "PersonWithSameName");

		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::hippocraticDelta));
		util.assertPostcondition("FamilyWithDuplicateMember2", "PersonWithSameName");
	}

}
