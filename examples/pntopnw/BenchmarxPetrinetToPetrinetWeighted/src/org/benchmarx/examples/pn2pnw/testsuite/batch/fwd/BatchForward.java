package org.benchmarx.examples.pn2pnw.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.pn2pnw.testsuite.Decisions;
import org.benchmarx.examples.pn2pnw.testsuite.Pn2PnwTestCase;
import org.junit.Test;

public class BatchForward extends Pn2PnwTestCase {
	public BatchForward(BXTool<pn.Net, pnw.Net, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for agreed upon starting state.<br/>
	 * <b>Expect</b> root elements of both source and target models.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testInitialiseSynchronisation()
	{
		// No precondition!
		//------------
		util.assertPostcondition("EmptyPn", "EmptyPnw");
	}
	
	/**
	 * <b>Test</b> for name change of an empty net.<br/>
	 * <b>Expect</b> corresponding name change in target net.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testNetNameChangeOfEmpty()
	{
		tool.performAndPropagateSourceEdit(helperPn::renameToLettersAndDigits);

		util.assertPrecondition("EmptyLettersDigitsPn", "EmptyLettersDigitsPnw");
		//------------
		tool.performAndPropagateSourceEdit(helperPn::renameToFactoryModel);
		//------------
		util.assertPostcondition("EmptyFactoryModelPn", "EmptyFactoryModelPnw");
	}
	
	/**
	 * <b>Test</b> for creation of a simple net.
	 * <br/>
	 * <b>Expect</b> the creation of the corresponding net in the target model.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateSimpleNet()
	{
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperPn::createSimpleLettersDigits);
		//------------
		util.assertPostcondition("SimpleLettersDigitsPn", "SimpleLettersDigitsPnw");
	}

	/**
	 * Analogous to @link {@link #testCreateSimpleNet()}, but now with a more sophisticated net.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test 
	public void testCreateComplexNet(){
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperPn::createComplexLettersDigits);
		//------------
		util.assertPostcondition("ComplexLettersDigitsPn", "ComplexLettersDigitsPnw");
	}
}
