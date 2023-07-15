package org.benchmarx.examples.containerstominiyaml.testsuite.scalability;

import static org.benchmarx.examples.containerstominiyaml.helpers.MiniYAMLHelper.getOrCreateMapEntry;
import static org.benchmarx.examples.containerstominiyaml.helpers.MiniYAMLHelper.scalar;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.comparators.CompositionComparator;
import org.benchmarx.examples.containerstominiyaml.comparators.MiniYAMLComparator;
import org.benchmarx.examples.containerstominiyaml.implementations.epsilon.EpsilonContainersToMiniYAML;
import org.benchmarx.examples.containerstominiyaml.implementations.nmf.NMFContainersToMiniYaml;
import org.benchmarx.examples.containerstominiyaml.implementations.nmf.NMFContainersToMiniYamlTimer;
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
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import de.ubt.ai1.m2m.bxtenddsl.connectors.BXToolForBXtendDsl;
import miniyaml.Map;
import miniyaml.MapEntry;
import miniyaml.MiniyamlFactory;
import miniyaml.MiniyamlPackage;

public class ScalabilityMeasurements {

	private static final int MAX_REPLICAS = 3;

	private static final double PROBABILITY_DEPENDS_ON = 0.2;
	private static final double PROBABILITY_VOLUME_MOUNT = 0.2;

	public static final BXTool<Composition, miniyaml.Map, Decisions> TOOL_EPSILON
		= new EpsilonContainersToMiniYAML("Epsilon", new MiniYAMLComparator());
	public static final BXTool<Composition, miniyaml.Map, Decisions> TOOL_BXTENDDSL
		= new BXToolForBXtendDsl<containers.Composition, miniyaml.Map, Decisions>(
			() -> new Containers2MiniYAML(), ContainersFactory.eINSTANCE.createComposition(), 
			Decisions.values(), new CompositionComparator(), new MiniYAMLComparator() );
	public static final BXTool<Composition, miniyaml.Map, Decisions> TOOL_NMF
		= new NMFContainersToMiniYaml("NMF", new MiniYAMLComparator());

	private static final String DELIMITER = ",";
	private static final String UNIT = "";
	
	private int nContainers, nVolumes, nImages, nRepetitions;

	/** Disables runs of the tool with further sizes if its average time in seconds exceeds it. */
	private int disableToolIfSlowerThanSeconds = 100;

	private final Random rnd = new Random(1234);

	public int getContainers() {
		return nContainers;
	}

	public void setContainers(int nContainers) {
		this.nContainers = nContainers;
	}

	public int getVolumes() {
		return nVolumes;
	}

	public void setVolumes(int nVolumes) {
		this.nVolumes = nVolumes;
	}

	public int getImages() {
		return nImages;
	}

	public void setImages(int nImages) {
		this.nImages = nImages;
	}

	public int getRepetitions() {
		return nRepetitions;
	}

	public void setRepetitions(int nRepetitions) {
		this.nRepetitions = nRepetitions;
	}

	public int getDisableToolIfSlowerThanSeconds() {
		return disableToolIfSlowerThanSeconds;
	}

	public void setDisableToolIfSlowerThanSeconds(int disableToolIfSlowerThanSeconds) {
		this.disableToolIfSlowerThanSeconds = disableToolIfSlowerThanSeconds;
	}

	private void createInitialComposition(Composition comp) {
		List<Image> images = new ArrayList<>();
		for (int i = 0; i < nImages; i++) {
			Image image = ContainersFactory.eINSTANCE.createImage();
			image.setImage("provider/image" + i);

			comp.getNodes().add(image);
			images.add(image);
		}

		List<Volume> volumes = new ArrayList<>();
		for (int i = 0; i < nVolumes; i++) {
			Volume volume = ContainersFactory.eINSTANCE.createVolume();
			volume.setName("volume" + i);

			comp.getNodes().add(volume);
			volumes.add(volume);
		}

		List<Container> containers = new ArrayList<>();
		for (int i = 0; i < nContainers; i++) {
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

	private void createContainer(Composition composition) {
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

	private void createInitialMap(miniyaml.Map yamlMap) {
		// Note: initial version /services / volumes maps are created during initiation of sync dialogue
		Map servicesMap = getOrCreateMapEntry(yamlMap, "services", MiniyamlFactory.eINSTANCE::createMap);
		Map volumesMap = getOrCreateMapEntry(yamlMap, "volumes", MiniyamlFactory.eINSTANCE::createMap);

		List<String> images = new ArrayList<>(nImages);
		for (int i = 0; i < nImages; i++) {
			images.add("provider/image" + i);
		}

		List<String> volumes = new ArrayList<>(nVolumes);
		for (int i = 0; i < nVolumes; i++) {
			String volumeName = "volume" + i + ":/path/to/volume" + i;
			volumesMap.getEntries().add(mapEntry(volumeName, null));
			volumes.add(volumeName);
		}

		for (int i = 0; i < nContainers; i++) {
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

	private void createContainerEntry(miniyaml.Map yamlMap) {
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

	public void runBatchFWDMeasurements(List<Integer> sizes, BXTool<Composition, miniyaml.Map, Decisions>... tools){
		timerMeasurements(sizes, createTimers(tools), (timer) -> timer.timeSourceEditFromScratchInS(this::createInitialComposition));
	}

	private List<BXToolTimer<Composition, Map, Decisions>> createTimers(BXTool<Composition, Map, Decisions>[] tools) {
		List<BXToolTimer<Composition, Map, Decisions>> timers = new ArrayList<>(tools.length);
		for (BXTool<Composition, Map, Decisions> tool : tools) {
			if (tool == TOOL_NMF) {
				timers.add(new NMFContainersToMiniYamlTimer(tool, nRepetitions));
			} else {
				timers.add(new BXToolTimer<>(tool, nRepetitions));
			}
		}
		
		return timers;
	}

	private void timerMeasurements(List<Integer> sizes, List<BXToolTimer<Composition, Map, Decisions>> timers, Function<BXToolTimer<Composition, Map, Decisions>, Double> fn) {
		// Initially, we use all timers
		java.util.Map<BXToolTimer<Composition, Map, Decisions>, Boolean> active = new IdentityHashMap<>();

		for (int size : sizes) {
			this.setContainers(size);

			System.out.print(nContainers + DELIMITER + nVolumes + DELIMITER + nImages);
			for (BXToolTimer<Composition, Map, Decisions> timer : timers) {
				Boolean isActive = active.getOrDefault(timer, true);
				if (isActive) {
					double time = fn.apply(timer);
					System.out.print(DELIMITER + time + UNIT);
					active.put(timer, time <= this.disableToolIfSlowerThanSeconds);
				} else {
					System.out.print(DELIMITER + "TIMED_OUT" + UNIT);
				}
			}
			System.out.println();
		}
	}
	
	public void runBatchBWDMeasurements(List<Integer> sizes, BXTool<Composition, miniyaml.Map, Decisions>... tools){
		timerMeasurements(sizes, createTimers(tools), (timer) -> timer.timeTargetEditFromScratchInS(this::createInitialMap));
	}

	public void runIncrFWDMeasurements(List<Integer> sizes, BXTool<Composition, miniyaml.Map, Decisions>... tools){
		timerMeasurements(sizes, createTimers(tools), (timer) -> timer.timeSourceEditAfterSetUpInS(this::createInitialComposition, this::createContainer));
	}

	public void runIncrBWDMeasurements(List<Integer> sizes, BXTool<Composition, miniyaml.Map, Decisions>... tools){
		timerMeasurements(sizes, createTimers(tools), (timer) -> timer.timeTargetEditAfterSetUpInS(this::createInitialMap, this::createContainerEntry));
	}

    @SafeVarargs
	private static void printHeader(String title, BXTool<Composition, miniyaml.Map, Decisions>... tools) {
        System.out.println("------------------");
        System.out.println(title);
        System.out.println("------------------");
        System.out.print("n_containers" + DELIMITER + "n_volumes" + DELIMITER + "n_images");
        for (BXTool<Composition, miniyaml.Map, Decisions> tool : tools) {
        	System.out.print(DELIMITER + tool.getName());
        }
        System.out.println();
    }

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ContainersPackage.eINSTANCE.getName();
		MiniyamlPackage.eINSTANCE.getName();

		final List<Integer> sizes = new ArrayList<>();
		for (int i = 50; i < 500; i += 50) {
			sizes.add(i);
		}

		final ScalabilityMeasurements measurements = new ScalabilityMeasurements();
		measurements.setVolumes(3);
		measurements.setImages(4);
		measurements.setRepetitions(5);
		measurements.setDisableToolIfSlowerThanSeconds(100);

		// Note: NMF solutions produce errors in backwards mode
		printHeader("Batch FWD:", TOOL_EPSILON, TOOL_BXTENDDSL, TOOL_NMF);
		measurements.runBatchFWDMeasurements(sizes, TOOL_EPSILON, TOOL_BXTENDDSL, TOOL_NMF);

		printHeader("Batch BWD:", TOOL_EPSILON, TOOL_BXTENDDSL);
		measurements.runBatchFWDMeasurements(sizes, TOOL_EPSILON, TOOL_BXTENDDSL);

		printHeader("Incr. FWD:", TOOL_EPSILON, TOOL_BXTENDDSL, TOOL_NMF);
		measurements.runIncrFWDMeasurements(sizes, TOOL_EPSILON, TOOL_BXTENDDSL, TOOL_NMF);

		printHeader("Incr. BWD:", TOOL_EPSILON, TOOL_BXTENDDSL);
		measurements.runIncrBWDMeasurements(sizes, TOOL_EPSILON, TOOL_BXTENDDSL);
	}
}
