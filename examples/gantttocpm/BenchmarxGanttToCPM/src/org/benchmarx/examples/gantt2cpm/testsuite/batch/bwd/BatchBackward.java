package org.benchmarx.examples.gantt2cpm.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.gantt2cpm.testsuite.Decisions;
import org.benchmarx.examples.gantt2cpm.testsuite.GanttToCPMTestCase;
import org.junit.Test;

import cpm.CPMNetwork;
import gantt.GanttDiagram;

public class BatchBackward extends GanttToCPMTestCase {
	public BatchBackward(BXTool<GanttDiagram, CPMNetwork, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for name change of an empty cpm network.<br/>
	 * <b>Expect</b> name in the gantt diagram is also changed.<br/>
	 * <b>Features</b>: bwd, fixed
	 */
	@Test
	public void testCpmNameChangeOfEmpty()
	{
		tool.performAndPropagateTargetEdit(helperCPM::createEmptyGantt2CPMProcedure);

		util.assertPrecondition("EmptyGantt2CpmGantt", "EmptyGantt2CpmCpm");
		//------------
		tool.performAndPropagateTargetEdit(helperCPM::createEmptyItalyTankRush);
		//------------
		util.assertPostcondition("EmptyItalyTankRushGantt", "EmptyItalyTankRushCpm");
	}
	
	/**
	 * <b>Test</b> for creation of a simple cpm network.
	 * <br/>
	 * <b>Expect</b> the creation of the corresponding gantt diagram.
	 * <br/>
	 * <b>Features:</b>: bwd, fixed
	 */
	@Test
	public void testCreateCpm()
	{
		// No precondition!
		//------------
		tool.performAndPropagateTargetEdit(helperCPM::createSimpleTankRush);
		//------------
		util.assertPostcondition("SimpleTankRushGantt", "SimpleTankRushCpm");
	}

	/**
	 * Analogous to @link {@link #testCreateCpm()}, now with all possible dependency types.<br/>
	 * <b>Features:</b>: bwd, fixed
	 */
	@Test 
	public void testCreateComplexGantt(){ // rename to CPM?
		// No precondition!
		//------------
		tool.performAndPropagateTargetEdit(helperCPM::createComplexTankRush);
		//------------
		util.assertPostcondition("ComplexTankRushGantt", "ComplexTankRushCpm");
	}
}
