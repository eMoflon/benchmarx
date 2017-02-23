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
		System.out.println("Incremental Insert:");
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSkinnerFamily)
				.andThen(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart)
				.andThen(helperFamily::createNewFamilySimpsonWithMembers)
				.andThen(helperFamily::createSonBart));
		
		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		tool.performAndPropagateTargetEdit(helperPerson::changeAllBirthdays);
		/**
		 * Note: medini QVT fails here for an unknown reason
		 * it seems that the propagation of the target delta (which should not affect the family model)
		 * results in a backward transformation that messes up the family model (see comment in the corresponding issue in github)
		 */
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createFatherNed)
				.andThen(helperFamily::createMotherMaude)
				.andThen(helperFamily::createSonTodd));
		//------------
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
		System.out.println("Incremental Delete:");
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSkinnerFamily)
				.andThen(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart)
				.andThen(helperFamily::createNewFamilySimpsonWithMembers)
				.andThen(helperFamily::createSonBart));
		
		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		tool.performAndPropagateTargetEdit(helperPerson::changeAllBirthdays);
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::deleteFirstSonBart));
		//------------
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
		System.out.println("Incremental Rename:");
		tool.initiateSynchronisationDialogue();
		util.configure().makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSkinnerFamily)
				.andThen(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart)
				.andThen(helperFamily::createNewFamilySimpsonWithMembers)
				.andThen(helperFamily::createSonBart));
		
		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		tool.performAndPropagateTargetEdit(helperPerson::changeAllBirthdays);
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::renameSimpsonToBouvier));
		//------------
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
		System.out.println("Incremental Move:");
		tool.initiateSynchronisationDialogue();
		util.configure().makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
			.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSkinnerFamily)
				.andThen(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart)
				.andThen(helperFamily::createNewFamilySimpsonWithMembers)
				.andThen(helperFamily::createSonBart));
		
		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		tool.performAndPropagateTargetEdit(helperPerson::changeAllBirthdays);
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::moveLisa)
				.andThen(helperFamily::moveMarge));
		//------------
		util.assertPostcondition("FamilyAfterMove", "PersonAfterMove");
	}

}
