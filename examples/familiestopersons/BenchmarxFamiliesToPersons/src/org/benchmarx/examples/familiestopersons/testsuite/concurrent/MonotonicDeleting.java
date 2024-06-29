package org.benchmarx.examples.familiestopersons.testsuite.concurrent;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class MonotonicDeleting extends FamiliesToPersonsTestCase {

	public MonotonicDeleting(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for deleting Homer in both the family and persons register. <br/>
	 * <b>Expect</b> : Concurrent deletions to be matched (nothing needs to be done).<br/>
	 * <b>Features</b>: Concurrent sync, hippocraticness.
	 */
	@Test
	public void testMatchingDeletion() {
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
		tool.performAndPropagateEdit(//
				srcEdit(helperFamily::deleteFatherHomer), //
				trgEdit(helperPerson::deleteHomer));
		// ------------

		util.assertPostcondition("FamiliesAfterConcSyncMatchingDeletion", "PersonsAfterConcSyncMatchingDeletion");
	}

	/**
	 * <b>Test</b> for orthogonal (non-matching) deletions of Homer as a father, and Maggie as a female person. <br/>
	 * <b>Expect</b> : Both deletions must be propagated to the other model in each case.<br/>
	 * <b>Features</b>: Concurrent sync.
	 */
	@Test
	public void testNonMatchingDeletion() {
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
		tool.performAndPropagateEdit(//
				srcEdit(helperFamily::deleteFatherHomer), //
				trgEdit(helperPerson::deleteMaggie));
		// ------------
		
//		if (tool instanceof BXToolForEMF)
//			((BXToolForEMF)tool).saveModels("NonMatchingDeletion");

		util.assertPostcondition("FamiliesAfterConcSyncNonMatchingDeletion", "PersonsAfterConcSyncNonMatchingDeletion");
	}
	
	/**
	 * <b>Test</b> Combine cases of concurrent matching and non-matching deletion.<br/>
	 * <b>Expect</b> : Matching deletion to be detected, non-matching to be propagated.<br/>
	 * <b>Features</b>: Concurrent sync.
	 */
	@Test
	public void testCombinedCases() {
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
		tool.performAndPropagateEdit(//
				srcEdit(helperFamily::deleteFatherHomer, helperFamily::deleteRodAsSon), //
				trgEdit(helperPerson::deleteHomer, helperPerson::deleteMaggie));
		// ------------

		util.assertPostcondition("FamiliesAfterConcSyncCombinedCases", "PersonsAfterConcSyncCombinedCases");
	}
}