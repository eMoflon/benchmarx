package org.benchmarx.examples.containerstominiyaml.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.testsuite.ContainersToMiniYAMLTestCase;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.junit.Test;

import containers.Composition;
import miniyaml.Map;

/**
 * Test cases for backward transformations.
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

	@Test
	public void addContainerTwoReplicas() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateTargetEdit((yamlMap) -> miniYAMLHelper.addContainer(yamlMap, "myservice", 2));
		util.assertPostcondition("Post_AddContainerTwoReplicasContainers", "Post_AddContainerTwoReplicasMiniYAML");
	}

	@Test
	public void addContainerWithImage() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateTargetEdit((yamlMap) -> {
			Map serverMap = miniYAMLHelper.addContainer(yamlMap, "webserver", 1);
			miniYAMLHelper.setImage(serverMap, "my/image");
		});
		util.assertPostcondition("Post_AddContainerWithImageContainers", "Post_AddContainerWithImageMiniYAML");
	}

	@Test
	public void addContainerDependsOn() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateTargetEdit((yamlMap) -> {
			final String dbContainerName = "database";
			Map webServer = miniYAMLHelper.addContainer(yamlMap, "webserver", 1);
			miniYAMLHelper.addContainer(yamlMap, dbContainerName, 1);
			miniYAMLHelper.addDependsOn(webServer, dbContainerName);
		});
		util.assertPostcondition("Post_AddContainerDependsOnContainers", "Post_AddContainerDependsOnMiniYAML");
	}

	@Test
	public void addContainerVolumeMount() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateTargetEdit((yamlMap) -> {
			Map container = miniYAMLHelper.addContainer(yamlMap, "database", 1);
			final String dbVolumeName = "db_storage";
			miniYAMLHelper.addVolume(yamlMap, dbVolumeName);
			miniYAMLHelper.mountVolume(container, dbVolumeName, "/db/storage");
		});
		util.assertPostcondition("Post_AddContainerVolumeMountContainers", "Post_AddContainerVolumeMountMiniYAML");
	}

	@Test
	public void completeModel() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateTargetEdit((yamlMap) -> {
			Map webServer = miniYAMLHelper.addContainer(yamlMap, "webserver", 2);
			miniYAMLHelper.setImage(webServer, "nginx:latest");

			final String dbContainerName = "database";
			Map database = miniYAMLHelper.addContainer(yamlMap, dbContainerName, 1);
			miniYAMLHelper.setImage(database, "mariadb:latest");
			miniYAMLHelper.addDependsOn(webServer, dbContainerName);

			final String dbVolumeName = "db_storage";
			miniYAMLHelper.addVolume(yamlMap, dbVolumeName);
			miniYAMLHelper.mountVolume(database, dbVolumeName, "/db/storage");
		});
		util.assertPostcondition("Post_CompleteModelContainers", "Post_CompleteModelMiniYAML");
	}

}
