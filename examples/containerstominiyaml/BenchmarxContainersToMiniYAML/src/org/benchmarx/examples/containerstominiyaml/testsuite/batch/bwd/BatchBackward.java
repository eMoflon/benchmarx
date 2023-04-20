package org.benchmarx.examples.containerstominiyaml.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.testsuite.ContainersToMiniYAMLTestCase;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.junit.Test;

import containers.Composition;

/**
 * Test cases for backward transformations with parameters E and P set to true
 * Please note, that in general this leads to a non-deterministic behavior, 
 * which is hard to test. Thus, we restricted ourselves to a PersonRegister
 * configuration, which allows to deterministically execute the backward
 * transformation. (c.f., Test Case 2d on GitHub).
 */
public class BatchBackward extends ContainersToMiniYAMLTestCase {

	public BatchBackward(BXTool<Composition, miniyaml.Map, Decisions> tool) {
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
		util.assertPostcondition("RootElementContainers", "RootElementMiniYAML");
	}

	@Test
	public void addVolume() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateTargetEdit((yamlMap) -> miniYAMLHelper.addVolume(yamlMap, "storage"));
		util.assertPostcondition("Post_AddVolumeContainers", "Post_AddVolumeMiniYAML");
	}

	@Test
	public void addContainerOneReplica() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateTargetEdit((yamlMap) -> miniYAMLHelper.addContainer(yamlMap, "myservice", 1));
		util.assertPostcondition("Post_AddContainerOneReplicaContainers", "Post_AddContainerOneReplicaMiniYAML");
	}

}
