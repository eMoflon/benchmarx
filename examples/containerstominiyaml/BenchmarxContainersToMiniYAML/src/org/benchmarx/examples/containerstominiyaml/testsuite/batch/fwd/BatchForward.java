package org.benchmarx.examples.containerstominiyaml.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.testsuite.ContainersToMiniYAMLTestCase;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.junit.Test;

import containers.Composition;


public class BatchForward extends ContainersToMiniYAMLTestCase {

	public BatchForward(BXTool<Composition, miniyaml.Map, Decisions> tool) {
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
		util.assertPostcondition("RootElementContainers", "RootElementMiniYAML");
	}

	@Test
	public void addImage() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> compositionsHelper.addImage(c, "test"));
		util.assertPostcondition("Post_AddImageContainers", "RootElementMiniYAML");
	}

	@Test
	public void addVolume() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> compositionsHelper.addVolume(c, "storage"));
		util.assertPostcondition("Post_AddVolumeContainers", "Post_AddVolumeMiniYAML");
	}

	@Test
	public void addContainerOneReplica() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> compositionsHelper.addContainer(c, "myservice", 1));
		util.assertPostcondition("Post_AddContainerOneReplicaContainers", "Post_AddContainerOneReplicaMiniYAML");
	}

	@Test
	public void addContainerTwoReplicas() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> compositionsHelper.addContainer(c, "myservice", 2));
		util.assertPostcondition("Post_AddContainerTwoReplicasContainers", "Post_AddContainerTwoReplicasMiniYAML");
	}
}
