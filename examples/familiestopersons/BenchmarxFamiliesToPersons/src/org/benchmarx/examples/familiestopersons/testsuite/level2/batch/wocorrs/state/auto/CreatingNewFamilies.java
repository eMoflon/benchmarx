package org.benchmarx.examples.familiestopersons.testsuite.level2.batch.wocorrs.state.auto;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class CreatingNewFamilies extends FamiliesToPersonsTestCase {

	public CreatingNewFamilies(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for creation of a single family in an empty root container.
	 * <p>
	 * <b>Expect</b> nothing to be changed in the person model.
	 * <p>
	 * <b>Classification</b>: batch-wocorr-state-auto
	 * <ul>
	 * <li><b>batch</b>: no information loss, so the empty persons register can be re-created from scratch.
	 * <li><b>wocorr</b>: creating a single family does not require traceability links.
	 * <li><b>state</b>: easy to guess/compute the delta involved here (a new family is added).
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * <ul>
	 */
	@Test
	public void testCreateFamily()
	{
		tool.initiateSynchronisationDialogue();
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		//------------
		util.assertPostcondition("oneFamily", "personsForOneFamily");
	}
}
