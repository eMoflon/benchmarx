package org.benchmarx.examples.familiestopersons.testsuite.level2.incr.wocorrs.state.auto;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class DeletingPeople extends FamiliesToPersonsTestCase {
	
	public DeletingPeople(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for deleting a person.
	 * <p>
	 * <b>Expect</b> the deletion of the corresponding family member in the
	 * families model.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-state-auto
	 * <ul>
	 * <li><b>incr</b>: deleting the person requires old consistent state as the
	 * information if females are daughters or mothers (and males analogously)
	 * would otherwise be lost (for all other persons in the register).
	 * <li><b>wocorr</b>: assumption of unique names can be used here to compute
	 * correspondences correctly.
	 * <li><b>state</b>: deletion is state-based, as it's reasonably easy to
	 * determine the change from the old and new state.
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * <ul>
	 */
	@Test
	public void testDeletePerson() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createSimpsonFamily)
				   							   .andThen(helperFamily::createFatherHomer));
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//---------------------- 
		tool.performAndPropagateTargetEdit(helperPerson::deleteMarge);
		//---------------------- 
		util.assertPostcondition("MemberDelete", "PersonDelete");
	}
	
	/**
	 * Analogous to @link {@link #testDeletePerson()}, but the first person
	 * created in the family is deleted here.
	 */
	@Test
	public void testDeleteFirstPerson() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createSimpsonFamily)
											   .andThen(helperFamily::createFatherHomer));
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//---------------------- 
		tool.performAndPropagateTargetEdit(helperPerson::deleteHomer);
		//---------------------- 
		util.assertPostcondition("MemberFirstDelete", "PersonFirstDelete");
	}
	
}
