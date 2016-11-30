package org.benchmarx.examples.familiestopersons.testsuite.level3_n.batch.wocorrs.state.auto;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class CreatingNewFamiliesAndMembers extends FamiliesToPersonsTestCase {

	public CreatingNewFamiliesAndMembers(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for creation of a single family member (a father) in the only existing family.
	 * <p>
	 * <b>Expect</b> the creation of a new male person in the persons model, with full name consisting of the first name and family name of the associated family member.
	 * <p>
	 * <b>Classification</b>: batch-wocorr-state-auto
	 * <ul>
	 * <li><b>batch</b>: creation of family and only one family member does not require old(er) states as there is no information loss.
	 * <li><b>wocorr</b>: no traceability links required (the only existing link is the trivial correspondence of empty registers).
	 * <li><b>state</b>: easy to guess/compute the delta involved here based on the old and new state (what was added is clear).
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * <ul>
	 */
	@Test
	public void testCreateFamilyMember()
	{
		tool.initiateSynchronisationDialogue();
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherHomer));
		//------------
		util.assertPostcondition("oneFamilyWithOneFamilyMember", "PersonWithOneMaleMember");
	}
	
	/**
	 * Analogous to @link {@link #testCreateFamilyMember()}, but now for multiple new family members. 
	 */
	@Test 
	public void testNewFamilyWithMultiMembers(){
		tool.initiateSynchronisationDialogue();
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::createNewfamilyBachchanWithMembers);
		//------------
		util.assertPostcondition("NewFamilyWithMembers", "PersonsMulti");
	}
}
