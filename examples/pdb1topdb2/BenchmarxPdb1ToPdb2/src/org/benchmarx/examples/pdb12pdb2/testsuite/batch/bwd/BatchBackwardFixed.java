package org.benchmarx.examples.pdb12pdb2.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.pdb12pdb2.testsuite.Decisions;
import org.benchmarx.examples.pdb12pdb2.testsuite.Pdb12Pdb2TestCase;
import org.junit.Test;

public class BatchBackwardFixed extends Pdb12Pdb2TestCase {

	public BatchBackwardFixed(BXTool<pdb1.Database, pdb2.Database, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for agreed upon starting state.<br/>
	 * <b>Expect</b> root elements of both source and target models.<br/>
	 * <b>Features</b>: bwd, fixed
	 */
	@Test
	public void testInitialiseSynchronisation()
	{
		// No precondition!
		//------------
		util.assertPostcondition("RootElementPdb1", "RootElementPdb2");
	}
	
	/**
	 * <b>Test</b> for name change of an empty Database.<br/>
	 * <b>Expect</b> the name in the source Database is also changed.<br/>
	 * <b>Features</b>: bwd, fixed
	 */
	@Test
	public void testDatabaseNameChangeOfEmpty()
	{
		tool.performAndPropagateTargetEdit(util.execute(helperPerson2::setDatabaseName));

		util.assertPrecondition("EmptyBundeskanzlerPdb1", "EmptyBundeskanzlerPdb2");
		//------------
		tool.performAndPropagateTargetEdit(util.execute(helperPerson2::renameKanzlerDatabaseToPräsidenten));
		//------------
		util.assertPostcondition("EmptyBundespräsidentenPdb1", "EmptyBundespräsidentenPdb2");
	}
}
