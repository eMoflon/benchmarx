package org.benchmarx.examples.familiestopersons.testsuite.level1.batch.wocorrs.state.auto;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class Initialisation extends FamiliesToPersonsTestCase {
	
	public Initialisation(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for agreed upon starting state.
	 * <p>
	 * <b>Expect</b> root elements of both source and target models.
	 * <p>
	 * <b>Classification</b>: batch-wocorr-state-auto
	 * <ul>
	 * <li><b>batch</b>: this is the initial state for synchronisation, so an
	 * old(er) consistent state doesn't exist.
	 * <li><b>wocorr</b>: no need for traceability links (in general, wcorr and
	 * batch does not make sense).
	 * <li><b>state</b>: easy to guess/compute the delta involved here (creating
	 * a root container).
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * <ul>
	 */
	@Test
	public void testInitialiseSynchronisation()
	{
		// No precondition!
		//------------
		tool.initiateSynchronisationDialogue();
		//------------
		util.assertPostcondition("RootElementFamilies", "RootElementPersons");
	}
	
	/**
	 * <b>Test</b> for name change of an empty family, i.e, a family without any family members.
	 * <p>
	 * <b>Expect</b> no change in the persons model.
	 * <p>
	 * <b>Classification</b>: batch-wocorr-state-auto
	 * <ul>
	 * <li><b>batch</b>: person register can be recreated from scratch without loss of information.
	 * <li><b>wocorr</b>: no traceability links required.
	 * <li><b>state</b>: in this case, renaming and delete+create have the same effect.
	 * <li><b>auto</b>: propagation is deterministic so no choice involved.
	 * </ul>
	 */
	@Test
	public void testFamilyNameChangeOfEmpty()
	{
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);

		util.assertPrecondition("Pre_NameChangeFamilyEmpty", "Pre_NameChangePersonEmpty");
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::familyNameSimpsonChangeEmpty);
		//------------
		util.assertPostcondition("NameChangeFamilyEmpty", "NameChangePersonEmpty");
	}
}
