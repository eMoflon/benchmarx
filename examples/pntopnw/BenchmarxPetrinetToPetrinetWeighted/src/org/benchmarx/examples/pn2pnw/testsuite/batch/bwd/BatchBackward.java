package org.benchmarx.examples.pn2pnw.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.pn2pnw.testsuite.Decisions;
import org.benchmarx.examples.pn2pnw.testsuite.Pn2PnwTestCase;
import org.junit.Test;

public class BatchBackward extends Pn2PnwTestCase {
	public BatchBackward(BXTool<pn.Net, pnw.Net, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for name change of an empty net.<br/>
	 * <b>Expect</b> corresponding name change in source net.<br/>
	 * <b>Features</b>: bwd, fixed
	 */
	@Test
	public void testNetNameChangeOfEmpty()
	{
		tool.performAndPropagateTargetEdit(helperPnw::renameToLettersAndDigits);

		util.assertPrecondition("EmptyLettersDigitsPn", "EmptyLettersDigitsPnw");
		//------------
		tool.performAndPropagateTargetEdit(helperPnw::renameToFactoryModel);
		//------------
		util.assertPostcondition("EmptyFactoryModelPn", "EmptyFactoryModelPnw");
	}
	
	/**
	 * <b>Test</b> for creation of a simple net.
	 * <br/>
	 * <b>Expect</b> the creation of the corresponding net in the source model.
	 * <br/>
	 * <b>Features:</b>: bwd, fixed
	 */
	@Test
	public void testCreateSimpleNet()
	{
		// No precondition!
		//------------
		tool.performAndPropagateTargetEdit(helperPnw::createSimpleLettersDigits);
		//------------
		util.assertPostcondition("SimpleLettersDigitsPn", "SimpleLettersDigitsWeightedPnw");
	}

	/**
	 * Analogous to @link {@link #testCreateSimpleNet()}, but now with a more sophisticated net.<br/>
	 * <b>Features:</b>: bwd, fixed
	 */
	@Test 
	public void testCreateComplexNet(){
		// No precondition!
		//------------
		tool.performAndPropagateTargetEdit(helperPnw::createComplexLettersDigits);
		//------------
		util.assertPostcondition("ComplexLettersDigitsPn", "ComplexLettersDigitsWeightedPnw");
	}
}
