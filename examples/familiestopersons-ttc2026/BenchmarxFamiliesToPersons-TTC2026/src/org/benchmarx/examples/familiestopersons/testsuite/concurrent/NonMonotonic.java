package org.benchmarx.examples.familiestopersons.testsuite.concurrent;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class NonMonotonic extends FamiliesToPersonsTestCase {

	public NonMonotonic(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for mixing concurrent deletion and creation. <br/>
	 * <b>Expect</b> : Matching deletions are to be detected, orthogonal deletion and creation propagated.<br/>
	 * <b>Features</b>: Concurrent sync for non-monotonic case, hippocraticness.
	 */
	@Test
	public void testCombinedDeletionAndCreation() {
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
				srcEdit(helperFamily::deleteFatherHomer, helperFamily::createFatherNed), //
				trgEdit(helperPerson::deleteMarge, helperPerson::deleteHomer));
		// ------------

		util.assertPostcondition("FamiliesAfterConcSyncCombinedNonMonotonicCases", "PersonsAfterConcSyncCombinedNonMonotonicCases");
	}
	
	@Test
	public void testCombinedRenameDelete() {
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
				trgEdit(helperPerson::nameChangeOfLisa));
		// ------------

		util.assertPostcondition("FamiliesAfterConcSyncCombinedNonMonotonicRenameDelete", "PersonsAfterConcSyncCombinedNonMonotonicRenameDelete");
	}
}