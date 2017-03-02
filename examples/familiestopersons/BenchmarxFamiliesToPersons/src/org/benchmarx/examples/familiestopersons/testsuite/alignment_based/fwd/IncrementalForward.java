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
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSkinnerFamily)
				.andThen(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart));
		tool.performTargetEdit(helperPerson::setBirthdayOfRod);
		tool.performTargetEdit(helperPerson::setBirthdayOfFatherBart);
		tool.performAndPropagateSourceEdit(helperFamily::createNewFamilySimpsonWithMembers);
		tool.performTargetEdit(helperPerson::changeAllBirthdays);
		tool.performAndPropagateSourceEdit(helperFamily::createSonBart);
		tool.performTargetEdit(helperPerson::setBirthdayOfYoungerBart);
		
		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");

		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createFatherNed)
				.andThen(helperFamily::createMotherMaude)
				.andThen(helperFamily::createSonTodd));
		//------------
		tool.saveModels("AfterIncrInsert");
		util.assertPostcondition("FamilyAfterInsertion", "PersonAfterInsertion");
	}
	
	/**
	 * <b>Test</b> for deleting family members. After creating the person register,
	 * set birthdates and make sure, that the sons with the name Bart can be 
	 * distinguished. Then delete the younger son Bart from the Family register.
	 * <b>Expect</b>: Delete the correct Person in the Person Register
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSkinnerFamily)
				.andThen(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart));
		tool.performTargetEdit(helperPerson::setBirthdayOfRod);
		tool.performTargetEdit(helperPerson::setBirthdayOfFatherBart);
		tool.performAndPropagateSourceEdit(helperFamily::createNewFamilySimpsonWithMembers);
		tool.performTargetEdit(helperPerson::changeAllBirthdays);
		tool.performAndPropagateSourceEdit(helperFamily::createSonBart);
		tool.performTargetEdit(helperPerson::setBirthdayOfYoungerBart);
		
		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::deleteFirstSonBart));
		//------------
		tool.saveModels("AfterIncrDelete");
		util.assertPostcondition("FamilyAfterDeletion", "PersonAfterDeletion");
	}
	
	/**
	 * <b>Test</b> for renaming a family. After creating the person register,
	 * set birthdates. Then rename the name of the complete Family Simpson to Bouvier
	 * <b>Expect</b>: Change the name of the affected Persons in the Person Register
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalRename() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSkinnerFamily)
				.andThen(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart));
		tool.performTargetEdit(helperPerson::setBirthdayOfRod);
		tool.performTargetEdit(helperPerson::setBirthdayOfFatherBart);
		tool.performAndPropagateSourceEdit(helperFamily::createNewFamilySimpsonWithMembers);
		tool.performTargetEdit(helperPerson::changeAllBirthdays);
		tool.performAndPropagateSourceEdit(helperFamily::createSonBart);
		tool.performTargetEdit(helperPerson::setBirthdayOfYoungerBart);
		
		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::renameSimpsonToBouvier));
		//------------
		tool.saveModels("AfterIncrRename");
		util.assertPostcondition("FamilyAfterRename", "PersonAfterRename");
	}
	
	/**
	 * <b>Test</b> for moving family members to different families and also changing their role. 
	 * After creating the person register, set birthdates. Then move Lisa to Flanders as mother
	 * and Marge to Skinner as mother.
	 * <b>Expect</b>: Change the name of the affected Persons in the Person Register
	 * <b>Features</b>: fwd, del+add, fixed, structural
	 */
	@Test
	public void testIncrementalMove() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSkinnerFamily)
				.andThen(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart));
		tool.performTargetEdit(helperPerson::setBirthdayOfRod);
		tool.performTargetEdit(helperPerson::setBirthdayOfFatherBart);
		tool.performAndPropagateSourceEdit(helperFamily::createNewFamilySimpsonWithMembers);
		tool.performTargetEdit(helperPerson::changeAllBirthdays);
		tool.performAndPropagateSourceEdit(helperFamily::createSonBart);
		tool.performTargetEdit(helperPerson::setBirthdayOfYoungerBart);
		
		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::moveLisa)
				.andThen(helperFamily::moveMarge));
		//------------
		tool.saveModels("AfterIncrMove");
		util.assertPostcondition("FamilyAfterMove", "PersonAfterMove");
	}
	
	/**
	 * <b>Test</b> for deleting an re-creating family members.
	 * After creating the person register, set birthdates. Then delete and re-create Homer
	 * <b>Expect</b>: Person register remains unchanged, except for "Simpson, Homer", which
	 * should be re-created with default birthdate.
	 * <b>Features</b>: fwd, structural, add+del, fixed 
	 */
	@Test
	public void testIncrementalMixed() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSkinnerFamily)
				.andThen(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart));
		tool.performTargetEdit(helperPerson::setBirthdayOfRod);
		tool.performTargetEdit(helperPerson::setBirthdayOfFatherBart);
		tool.performAndPropagateSourceEdit(helperFamily::createNewFamilySimpsonWithMembers);
		tool.performTargetEdit(helperPerson::changeAllBirthdays);
		tool.performAndPropagateSourceEdit(helperFamily::createSonBart);
		tool.performTargetEdit(helperPerson::setBirthdayOfYoungerBart);
		
		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::deleteFatherHomer)
				.andThen(helperFamily::createFatherHomer));
		//------------
		tool.saveModels("AfterIncrMixed");
		util.assertPostcondition("FamilyAfterMixed", "PersonAfterMixed");
	}
	
	/**
	 * <b>Test</b> for moving a family member to a new family and changing its role from daughter to son
	 * After creating the person register, set birthdates. Then move daughter Lisa to the Flanders family
	 * as a son.
	 * <b>Expect</b>: A new male person should be created in the PersonRegister, but the birthdate of Lisa
	 * should be retained.
	 * <b>Features</b>: fwd, structural, add+del, fixed 
	 */
	@Test
	public void testIncrementalMoveRoleChange() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSkinnerFamily)
				.andThen(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart));
		tool.performTargetEdit(helperPerson::setBirthdayOfRod);
		tool.performTargetEdit(helperPerson::setBirthdayOfFatherBart);
		tool.performAndPropagateSourceEdit(helperFamily::createNewFamilySimpsonWithMembers);
		tool.performTargetEdit(helperPerson::changeAllBirthdays);
		tool.performAndPropagateSourceEdit(helperFamily::createSonBart);
		tool.performTargetEdit(helperPerson::setBirthdayOfYoungerBart);
		
		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::moveMaggieAndChangeRole);
		//------------
		util.assertPostcondition("FamilyAfterMoveRoleChange", "PersonAfterMoveRoleChange");
	}

}
