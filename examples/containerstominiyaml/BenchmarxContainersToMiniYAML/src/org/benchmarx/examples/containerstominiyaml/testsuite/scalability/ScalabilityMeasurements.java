package org.benchmarx.examples.containerstominiyaml.testsuite.scalability;

import static org.benchmarx.examples.containerstominiyaml.helpers.MiniYAMLHelper.getOrCreateMapEntry;
import static org.benchmarx.examples.containerstominiyaml.helpers.MiniYAMLHelper.scalar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.comparators.MiniYAMLComparator;
import org.benchmarx.examples.containerstominiyaml.implementations.epsilon.EpsilonContainersToMiniYAML;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.benchmarx.util.BXToolTimer;

import containers.Composition;
import containers.Container;
import containers.ContainersFactory;
import containers.ContainersPackage;
import containers.Image;
import containers.Node;
import containers.Volume;
import containers.VolumeMount;
import miniyaml.Map;
import miniyaml.MapEntry;
import miniyaml.MiniyamlFactory;
import miniyaml.MiniyamlPackage;

public class ScalabilityMeasurements {

	private static final int MAX_REPLICAS = 3;

	private static final double PROBABILITY_DEPENDS_ON = 0.2;
	private static final double PROBABILITY_VOLUME_MOUNT = 0.2;

	private static final BXTool<Composition, miniyaml.Map, Decisions> tool1 = new EpsilonContainersToMiniYAML("Epsilon", new MiniYAMLComparator());
	
	private static final String DELIMITER = ",";
	private static final String UNIT = "";
	
	private final int NO_OF_CONTAINERS;
	private final int NO_OF_VOLUMES;
	private final int NO_OF_IMAGES;
	private final Random rnd = new Random(1234);
	
	private BXToolTimer<Composition, miniyaml.Map, Decisions> timer1;
	
	public ScalabilityMeasurements(int numberOfContainers, int noOfVolumes, int noOfImages, int repeat) {
		this.NO_OF_CONTAINERS = numberOfContainers;
		this.NO_OF_VOLUMES = noOfVolumes;
		this.NO_OF_IMAGES = noOfImages;

		timer1 = new BXToolTimer<>(tool1, repeat);
	}

	public void createInitialComposition(Composition comp) {
		List<Image> images = new ArrayList<>();
		for (int i = 0; i < NO_OF_IMAGES; i++) {
			Image image = ContainersFactory.eINSTANCE.createImage();
			image.setImage("provider/image" + i);

			comp.getNodes().add(image);
			images.add(image);
		}

		List<Volume> volumes = new ArrayList<>();
		for (int i = 0; i < NO_OF_VOLUMES; i++) {
			Volume volume = ContainersFactory.eINSTANCE.createVolume();
			volume.setName("volume" + i);

			comp.getNodes().add(volume);
			volumes.add(volume);
		}

		List<Container> containers = new ArrayList<>();
		for (int i = 0; i < NO_OF_CONTAINERS; i++) {
			Container cont = ContainersFactory.eINSTANCE.createContainer();
			cont.setReplicas(rnd.nextInt(MAX_REPLICAS));
			cont.setName("container" + i);
			cont.setImage(images.get(rnd.nextInt(images.size())));

			comp.getNodes().add(cont);
			containers.add(cont);
		}

		// Every volume is mounted in one random Container
		for (Volume vol : volumes) {
			Container rndCont = containers.get(rnd.nextInt(containers.size()));
			VolumeMount vm = ContainersFactory.eINSTANCE.createVolumeMount();
			vm.setPath("/path/to/" + vol.getName());
			vm.setVolume(vol);
			rndCont.getVolumeMounts().add(vm);
		}

		// Random dependencies between containers
		for (Container c : containers) {
			for (Container c2 : containers) {
				if (c != c2 && rnd.nextFloat() < PROBABILITY_DEPENDS_ON) {
					c.getDependsOn().add(c2);
				}
			}
		}
	}

	public void createContainer(Composition composition) {
		Container c = ContainersFactory.eINSTANCE.createContainer();
		c.setName("newContainer" + composition.getNodes().size());
		c.setReplicas(rnd.nextInt(MAX_REPLICAS));

		for (Node o : composition.getNodes()) {
			if (o instanceof Volume && rnd.nextFloat() < PROBABILITY_VOLUME_MOUNT) {
				Volume vol = (Volume) o;

				VolumeMount vm = ContainersFactory.eINSTANCE.createVolumeMount();
				vm.setPath("/path/to/" + vol.getName());
				vm.setVolume(vol);
				c.getVolumeMounts().add(vm);
				break;
			}
		}

		for (Node o : composition.getNodes()) {
			if (o instanceof Container && rnd.nextFloat() < PROBABILITY_DEPENDS_ON) {
				c.getDependsOn().add((Container) o);
			}
		}

		composition.getNodes().add(c);
	}

	public void createInitialMap(miniyaml.Map yamlMap) {
		yamlMap.getEntries().add(mapEntry("version", scalar("2.4")));
		Map servicesMap = map();
		yamlMap.getEntries().add(mapEntry("services", servicesMap));
		Map volumesMap = map();
		yamlMap.getEntries().add(mapEntry("volumes", volumesMap));

		List<String> images = new ArrayList<>(NO_OF_IMAGES);
		for (int i = 0; i < NO_OF_IMAGES; i++) {
			images.add("provider/image" + i);
		}

		List<String> volumes = new ArrayList<>(NO_OF_VOLUMES);
		for (int i = 0; i < NO_OF_VOLUMES; i++) {
			String volumeName = "volume" + i;
			volumesMap.getEntries().add(mapEntry(volumeName, null));
			volumes.add(volumeName);
		}

		for (int i = 0; i < NO_OF_CONTAINERS; i++) {
			Map containerMap = map();
			servicesMap.getEntries().add(mapEntry("container" + i, containerMap));

			containerMap.getEntries().add(
				mapEntry("replicas", scalar(rnd.nextInt(MAX_REPLICAS) + "")));
			containerMap.getEntries().add(
				mapEntry("image", scalar(images.get(rnd.nextInt(images.size())))));
		}

		for (String vol : volumes) {
			MapEntry rndCont = servicesMap.getEntries().get(rnd.nextInt(servicesMap.getEntries().size()));

			miniyaml.List volumeMounts = getOrCreateMapEntry(
				(miniyaml.Map) rndCont.getValue(), "volumes",
				MiniyamlFactory.eINSTANCE::createList);

			volumeMounts.getValues().add(scalar(vol));
		}

		for (MapEntry c : servicesMap.getEntries()) {
			for (MapEntry c2 : servicesMap.getEntries()) {
				if (c != c2 && rnd.nextFloat() < PROBABILITY_DEPENDS_ON) {
					miniyaml.List dependsOn = getOrCreateMapEntry((miniyaml.Map) c.getValue(),
						"depends_on", MiniyamlFactory.eINSTANCE::createList);

					dependsOn.getValues().add(scalar(c.getKey()));
				}
			}
		}
	}

	public void createContainerEntry(miniyaml.Map yamlMap) {
		Map volumesMap = getOrCreateMapEntry(yamlMap, "volumes", MiniyamlFactory.eINSTANCE::createMap);
		Map servicesMap = getOrCreateMapEntry(yamlMap, "services", MiniyamlFactory.eINSTANCE::createMap);

		Map containerMap = MiniyamlFactory.eINSTANCE.createMap();
		servicesMap.getEntries().add(mapEntry("newContainer" + servicesMap.getEntries().size(), containerMap));

		containerMap.getEntries().add(mapEntry("replicas", scalar(rnd.nextInt(MAX_REPLICAS) + "")));
		for (MapEntry e : volumesMap.getEntries()) {
			if (rnd.nextFloat() < PROBABILITY_VOLUME_MOUNT) {
				miniyaml.List volumeMounts = getOrCreateMapEntry(
					containerMap, "volumes",
					MiniyamlFactory.eINSTANCE::createList);
				volumeMounts.getValues().add(scalar(e.getKey()));
			}
		}

		for (MapEntry e : servicesMap.getEntries()) {
			if (rnd.nextFloat() < PROBABILITY_DEPENDS_ON) {
				miniyaml.List dependsOn = getOrCreateMapEntry(containerMap,
					"depends_on", MiniyamlFactory.eINSTANCE::createList);

				dependsOn.getValues().add(scalar(e.getKey()));
			}
		}
	}

	private miniyaml.Map map() {
		return MiniyamlFactory.eINSTANCE.createMap();
	}

	private miniyaml.MapEntry mapEntry(String key, miniyaml.Value value) {
		MapEntry me = MiniyamlFactory.eINSTANCE.createMapEntry();
		me.setKey(key);
		me.setValue(value);
		return me;
	}

	private void runBatchFWDMeasurements(){
		System.out.print(NO_OF_CONTAINERS + DELIMITER + NO_OF_VOLUMES + DELIMITER + NO_OF_IMAGES + DELIMITER);
		System.out.print(timer1.timeSourceEditFromScratchInS(this::createInitialComposition) + UNIT);
		System.out.println();
	}
	
	private void runBatchBWDMeasurements(){
		System.out.print(NO_OF_CONTAINERS + DELIMITER + NO_OF_VOLUMES + DELIMITER + NO_OF_IMAGES + DELIMITER);
		System.out.print(timer1.timeTargetEditFromScratchInS(this::createInitialMap) + UNIT);
		System.out.println();
	}
	
	private void runIncrFWDMeasurements(){
		System.out.print(NO_OF_CONTAINERS + DELIMITER + NO_OF_VOLUMES + DELIMITER + NO_OF_IMAGES + DELIMITER);
		System.out.print(timer1.timeSourceEditAfterSetUpInS(this::createInitialComposition, this::createContainer) + UNIT);
		System.out.println();
	}
	
	private void runIncrBWDMeasurements(){
		System.out.print(NO_OF_CONTAINERS + DELIMITER + NO_OF_VOLUMES + DELIMITER + NO_OF_IMAGES + DELIMITER);
		System.out.print(timer1.timeTargetEditAfterSetUpInS(this::createInitialMap, this::createContainerEntry) + UNIT);
		System.out.println();
	}

	private static void runBatchFWDMeasurements(int numOfContainers, int numOfVolumes, int numOfImages, int repetitions) {
		new ScalabilityMeasurements(numOfContainers, numOfVolumes, numOfImages, repetitions).runBatchFWDMeasurements();
	}
	
	private static void runBatchBWDMeasurements(int numOfFamilies, int numOfChildren, int numOfImages, int repetitions) {
		new ScalabilityMeasurements(numOfFamilies, numOfChildren, numOfImages, repetitions).runBatchBWDMeasurements();
	}
	
	private static void runIncrFWDMeasurements(int numOfFamilies, int numOfChildren, int numOfImages, int repetitions) {
		new ScalabilityMeasurements(numOfFamilies, numOfChildren, numOfImages, repetitions).runIncrFWDMeasurements();
	}
	
	private static void runIncrBWDMeasurements(int numOfFamilies, int numOfChildren, int numOfImages, int repetitions) {
		new ScalabilityMeasurements(numOfFamilies, numOfChildren, numOfImages, repetitions).runIncrBWDMeasurements();
	}
	
    private static void printHeader(String title) {
        System.out.println("------------------");
        System.out.println(title);
        System.out.println("------------------");
        System.out.println("n_containers" + DELIMITER + "n_volumes" + DELIMITER + "n_images" + DELIMITER + tool1.getName());
    }

	public static void main(String[] args) {
		ContainersPackage.eINSTANCE.getName();
		MiniyamlPackage.eINSTANCE.getName();
		
		printHeader("Batch FWD:");
		for (int i = 50; i < 1000; i+=50) {			
			runBatchFWDMeasurements(i, 3, 4, 5);
		}
		
		printHeader("Incr. FWD:");
		for (int i = 50; i < 1000; i+=50) {			
			runIncrFWDMeasurements(i, 3, 4, 5);
		}		
		
		printHeader("Batch BWD:");
		for (int i = 50; i < 1000; i+=50) {			
			runBatchBWDMeasurements(i, 3, 4, 5);
		}	
		
		printHeader("Incr. BWD:");
		for (int i = 50; i < 1000; i+=50) {			
			runIncrBWDMeasurements(i, 3, 4, 5);
		}
	}
}
