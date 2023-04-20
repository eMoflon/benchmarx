package org.benchmarx.examples.containerstominiyaml.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.testsuite.ContainersToMiniYAMLTestCase;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.junit.Test;

import containers.Composition;
import containers.Container;

public class IncrementalForward extends ContainersToMiniYAMLTestCase {
	
	public IncrementalForward(BXTool<Composition, miniyaml.Map, Decisions> tool) {
		super(tool);
	}
	
	@Test
	public void updateReplicas() {
		tool.performAndPropagateSourceEdit(compositionsHelper::createWebserverMongoComposition);
		tool.performIdleTargetEdit(miniYAMLHelper::addWebserverMongoExtras);

		util.assertPrecondition("Pre_IncrUpdateReplicasContainers", "Pre_IncrUpdateReplicasMiniYAML");
		//------------
		tool.performAndPropagateSourceEdit((c) -> {
			Container webserver = compositionsHelper.getContainer(c, "webserver").get();
			webserver.setReplicas(2);
		});
		//------------
		util.assertPostcondition("Post_IncrUpdateReplicasContainers", "Post_IncrUpdateReplicasMiniYAML");
	}
	
	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta does not change the target model.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testStability() {
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(compositionsHelper::createWebserverMongoComposition);
		tool.performIdleTargetEdit(miniYAMLHelper::addWebserverMongoExtras);

		//------------
		util.assertPostcondition("Pre_IncrUpdateReplicasContainers", "Pre_IncrUpdateReplicasMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> {});
		util.assertPostcondition("Pre_IncrUpdateReplicasContainers", "Pre_IncrUpdateReplicasMiniYAML");
	}

}
