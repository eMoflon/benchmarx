package org.benchmarx.examples.containerstominiyaml.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.testsuite.ContainersToMiniYAMLTestCase;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.junit.Test;

import containers.Composition;
import containers.Container;
import containers.Volume;

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
			Container webserver = compositionsHelper.getNodeByName(c, "webserver", Container.class);
			webserver.setReplicas(2);
		});
		//------------
		util.assertPostcondition("Post_IncrUpdateReplicasContainers", "Post_IncrUpdateReplicasMiniYAML");
	}

	@Test
	public void renameVolume() {
		tool.performAndPropagateSourceEdit(compositionsHelper::createWebserverMongoComposition);
		tool.performIdleTargetEdit(miniYAMLHelper::addWebserverMongoExtras);

		util.assertPrecondition("Pre_IncrUpdateReplicasContainers", "Pre_IncrUpdateReplicasMiniYAML");
		//------------
		tool.performAndPropagateSourceEdit((c) -> {
			Volume volume = compositionsHelper.getNodeByName(c, "mongo_storage", Volume.class);
			volume.setName("db_store");
		});
		//------------
		util.assertPostcondition("Post_IncrRenameVolumeContainers", "Post_IncrRenameVolumeMiniYAML");
	}

	@Test
	public void renameContainerNoExtras() {
		tool.performAndPropagateSourceEdit(compositionsHelper::createWebserverMongoComposition);

		util.assertPrecondition("Pre_IncrRenameContainerNoExtrasContainers", "Pre_IncrRenameContainerNoExtrasMiniYAML");
		//------------
		tool.performAndPropagateSourceEdit((c) -> {
			Container container = compositionsHelper.getNodeByName(c, "database", Container.class);
			container.setName("mariadb");
		});
		//------------
		util.assertPostcondition("Post_IncrRenameContainerNoExtrasContainers", "Post_IncrRenameContainerNoExtrasMiniYAML");
	}

	/**
	 * <b>Test</b> that extra YAML information is preserved when renaming a container.<br/>
	 * <b>Expect</b> the extra YAML information is moved to the new name after a rerun.<br/>
	 * <b>Features:</b>: incremental, fixed
	 */
	@Test
	public void renameContainer() {
		tool.performAndPropagateSourceEdit(compositionsHelper::createWebserverMongoComposition);
		tool.performIdleTargetEdit(miniYAMLHelper::addWebserverMongoExtras);

		util.assertPrecondition("Pre_IncrUpdateReplicasContainers", "Pre_IncrUpdateReplicasMiniYAML");
		//------------
		tool.performAndPropagateSourceEdit((c) -> {
			Container container = compositionsHelper.getNodeByName(c, "database", Container.class);
			container.setName("mariadb");
		});
		//------------
		util.assertPostcondition("Post_IncrRenameContainerContainers", "Post_IncrRenameContainerMiniYAML");
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
