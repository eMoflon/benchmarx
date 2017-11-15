package org.benchmarx.examples.gantt2cpm.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.gantt2cpm.testsuite.Decisions;
import org.benchmarx.examples.gantt2cpm.testsuite.GanttToCPMTestCase;
import org.junit.Test;

import cpm.CPMNetwork;
import gantt.GanttDiagram;

public class IncrementalForward extends GanttToCPMTestCase {
	public IncrementalForward(BXTool<GanttDiagram, CPMNetwork, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting new activities and dependencies into an existing gantt diagram. <br/>
	 * <b>Expect</b> : New events and activities are added to the cpm network, while the old events and activities
	 * remain unchanged. <br/>
	 * <b>Features</b>: fwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperGantt::createGantt2CPMTestCases)
				.andThen(helperGantt::changeIncrementalID));
		tool.performIdleTargetEdit(helperCPM::changeIncrementalID);
		
		util.assertPrecondition("TestsGantt", "TestsCPM");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperGantt::addGantt2CPMHelpers)
				.andThen(helperGantt::addGantt2CPMComparators)
				.andThen(helperGantt::addGantt2CPMModels));
		//------------
		util.assertPostcondition("TestsHelperModelComparatorGantt", "TestsHelperModelComparatorCPM");
	}
	
	/**
	 * <b>Test</b> for deleting dependencies at first. After that two activites with their incoming dependencies will be deleted.
	 * <b>Expect</b>: Delete the correct events and activities in the cpm network
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperGantt::createGantt2CPMTestCases)
				.andThen(helperGantt::addGantt2CPMHelpers)
				.andThen(helperGantt::addGantt2CPMComparators)
				.andThen(helperGantt::addGantt2CPMModels)
				.andThen(helperGantt::addGantt2CPMModelsToComparatorDependencies)
				.andThen(helperGantt::changeIncrementalID));
		tool.performIdleTargetEdit(helperCPM::changeIncrementalID);
		
		util.assertPrecondition("TestsHelperModel-ComparatorGantt", "TestsHelperModel-ComparatorCPM");	
		//Delete Dependency
		//------------
		tool.performAndPropagateSourceEdit(helperGantt::deleteGantt2CPMModelsToComparatorDependencies);
		//------------
		util.assertPostcondition("TestsHelperModelComparatorGantt", "TestsHelperModelComparatorCPM");
		
		//Delete Activity
		//------------
		tool.performAndPropagateSourceEdit(helperGantt::deleteGantt2CPMHelpers);
		//------------
		util.assertPostcondition("TestsModelComparatorGantt", "TestsModelComparatorCPM");
	}
	
	/**
	 * <b>Test</b> for changing all variable-values in different persons. After creating the pdb2 database. 
	 * Then change values of each variable in another person and all variables of one person.
	 * <b>Expect</b>: Change the values of the affected variables in Persons of the pdb2 database.
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalValueChange() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperGantt::createGantt2CPMTestCases)
				.andThen(helperGantt::addGantt2CPMHelpers)
				.andThen(helperGantt::addGantt2CPMComparators)
				.andThen(helperGantt::addGantt2CPMModels)
				.andThen(helperGantt::addGantt2CPMModelsToComparatorDependencies)
				.andThen(helperGantt::changeIncrementalID));
		tool.performIdleTargetEdit(helperCPM::changeIncrementalID);
		
		util.assertPrecondition("TestsHelperModel-ComparatorGantt", "TestsHelperModel-ComparatorCPM");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperGantt::changeGantt2CPMHelperToBuilder)
				.andThen(helperGantt::changeGantt2CPMModelDuration));
		//------------
		util.assertPostcondition("TestsBuilderMModel-ComparatorGantt", "TestsBuilderMModel-ComparatorCPM");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperGantt::changeGantt2CPMTestCasesNameDuration)
				.andThen(helperGantt::changeGantt2CPMModelToComparatorDependencyTypeDurationTargetAndSource));
		//------------
		util.assertPostcondition("TestsBuilderModelComparatorModifiedGantt", "TestsBuilderModelComparatorModifiedCPM");
	}

	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta does not change the target model.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testStability() {		
		tool.performAndPropagateSourceEdit(util
				.execute(helperGantt::createGantt2CPMTestCases)
				.andThen(helperGantt::addGantt2CPMHelpers)
				.andThen(helperGantt::addGantt2CPMComparators)
				.andThen(helperGantt::addGantt2CPMModels)
				.andThen(helperGantt::addGantt2CPMModelsToComparatorDependencies)
				.andThen(helperGantt::changeIncrementalID));
		tool.performIdleTargetEdit(helperCPM::changeIncrementalID);

		util.assertPrecondition("TestsHelperModel-ComparatorGantt", "TestsHelperModel-ComparatorCPM");
		//------------
		tool.performAndPropagateSourceEdit(helperGantt::idleDelta);
		//------------
		util.assertPostcondition("TestsHelperModel-ComparatorGantt", "TestsHelperModel-ComparatorCPM");
	}
	
	/**
	 * <b>Test</b> for hippocraticness of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after changing the incrementalID does not change the network.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testHippocraticness() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperGantt::createGantt2CPMTestCases)
				.andThen(helperGantt::addGantt2CPMHelpers)
				.andThen(helperGantt::addGantt2CPMComparators)
				.andThen(helperGantt::addGantt2CPMModels)
				.andThen(helperGantt::addGantt2CPMModelsToComparatorDependencies)
				.andThen(helperGantt::changeIncrementalID));
		tool.performIdleTargetEdit(helperCPM::changeIncrementalID);

		util.assertPrecondition("TestsHelperModel-ComparatorGantt", "TestsHelperModel-ComparatorCPM");
		//------------
		tool.performAndPropagateSourceEdit(helperGantt::changeIncrementalID);
		//------------
		util.assertPostcondition("TestsHelperModel-ComparatorChangedAgainGantt", "TestsHelperModel-ComparatorCPM");
	}

}
