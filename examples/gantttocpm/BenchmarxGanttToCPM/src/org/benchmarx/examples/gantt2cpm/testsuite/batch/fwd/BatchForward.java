package org.benchmarx.examples.gantt2cpm.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.gantt2cpm.testsuite.Decisions;
import org.benchmarx.examples.gantt2cpm.testsuite.GanttToCPMTestCase;
import org.junit.Test;

import cpm.CPMNetwork;
import gantt.GanttDiagram;

public class BatchForward extends GanttToCPMTestCase {
	public BatchForward(BXTool<GanttDiagram, CPMNetwork, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for agreed upon starting state.<br/>
	 * <b>Expect</b> root elements of both source and target models.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testInitialiseSynchronisation() {
		// No precondition!
		//------------
		util.assertPostcondition("RootElementGantt", "RootElementCpm");
	}
	
	/**
	 * <b>Test</b> for name change of an empty gantt diagram.<br/>
	 * <b>Expect</b> name in the cpm network is also changed.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testGanttNameChangeOfEmpty()
	{
		tool.performAndPropagateSourceEdit(helperGantt::createEmptyGantt2CPMProcedure);

		util.assertPrecondition("EmptyGantt2CpmGantt", "EmptyGantt2CpmCpm");
		//------------
		tool.performAndPropagateSourceEdit(helperGantt::createEmptyItalyTankRush);
		//------------
		util.assertPostcondition("EmptyItalyTankRushGantt", "EmptyItalyTankRushCpm");
	}
	
	/**
	 * <b>Test</b> for creation of a simple gantt diagram.
	 * <br/>
	 * <b>Expect</b> the creation of the corresponding cpm network.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateGantt()
	{
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperGantt::createSimpleTankRush);
		//------------
		util.assertPostcondition("SimpleTankRushGantt", "SimpleTankRushCpm");
	}

	/**
	 * Analogous to @link {@link #testCreateGantt()}, now with all possible dependency types.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test 
	public void testCreateComplexGantt(){
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperGantt::createComplexTankRush);
		//------------
		util.assertPostcondition("ComplexTankRushGantt", "ComplexTankRushCpm");
	}
}
