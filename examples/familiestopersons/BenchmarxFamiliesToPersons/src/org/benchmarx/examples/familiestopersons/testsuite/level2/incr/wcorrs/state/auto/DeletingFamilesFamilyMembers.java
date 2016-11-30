package org.benchmarx.examples.familiestopersons.testsuite.level2.incr.wcorrs.state.auto;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class DeletingFamilesFamilyMembers extends FamiliesToPersonsTestCase {

	public DeletingFamilesFamilyMembers(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for deletion of a single family member, where two members
	 * (one of which is deleted) have the same name. In this case, mother and
	 * daughter have same name.
	 * <p>
	 * <b>Expect</b> the associated person to be deleted from the persons
	 * register.
	 * <p>
	 * <b>Classification</b>: incr-wcorr-state-auto
	 * <ul>
	 * <li><b>incr</b>: deletion requires the old consistent state of the
	 * persons register as the birthdays (of all other family members) would be
	 * otherwise lost.
	 * <li><b>wcorr</b>: traceability links are required as it is impossible to
	 * guess correctly which persons correspond to which family members, given
	 * that there are multiple persons with the exact same full name.
	 * <li><b>state</b>: deletion is state-based, as it is reasonably easy to
	 * determine what was changed from the old and new states of the family
	 * register.
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * <ul>
	 */
	@Test
	public void testDeleteNonUniqueDaughter() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createSimpsonFamily)
											   .andThen(helperFamily::createFatherHomer));
		tool.performAndPropagateSourceEdit(helperFamily::createMotherMarge);
		tool.performAndPropagateSourceEdit(helperFamily::createDaughterMarge);
		
		util.assertPrecondition("Pre_DeleteFamilyMemberOfSameName", "Pre_DeletePersonSameName");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::deleteFamilyDaughterMarge);
		//------------
		util.assertPostcondition("DeleteFamilyMemberOfSameName", "DeletePersonSameName");
	}
	
	/**
	 * Analogous to @link {@link #testDeleteNonUniqueDaughter()}, but here for father/son.
	 */
	@Test
	public void testDeleteNonUniqueSon() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createMotherMarge);
		tool.performAndPropagateSourceEdit(helperFamily::createSonHomer);
		
		util.assertPrecondition("Pre_DeleteFamilyMemberSonHomer", "Pre_DeletePersonSonHomer");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::deleteFamilySonHomer);
		//------------
		util.assertPostcondition("DeleteFamilyMemberSonHomer", "DeletePersonSonHomer");
	}
}
