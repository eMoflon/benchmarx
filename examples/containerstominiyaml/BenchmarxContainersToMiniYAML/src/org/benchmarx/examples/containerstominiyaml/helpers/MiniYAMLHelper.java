package org.benchmarx.examples.containerstominiyaml.helpers;

import java.util.function.Supplier;

import miniyaml.List;
import miniyaml.Map;
import miniyaml.MapEntry;
import miniyaml.MiniyamlFactory;
import miniyaml.Scalar;
import miniyaml.Value;

public class MiniYAMLHelper {

	public Map addVolume(Map yamlMap, String volumeName) {
		Map volumesMap = getOrCreateMapEntry(yamlMap, "volumes", MiniyamlFactory.eINSTANCE::createMap);
		return getOrCreateMapEntry(volumesMap, volumeName, () -> null);
	}

	public Map addContainer(Map yamlMap, String containerName, int nReplicas) {
		Map servicesMap = getOrCreateMapEntry(yamlMap, "services", MiniyamlFactory.eINSTANCE::createMap);
		Map containerMap = getOrCreateMapEntry(servicesMap, containerName, MiniyamlFactory.eINSTANCE::createMap);

		if (nReplicas != 1) {
			putMapEntry(containerMap, "replicas", scalar(nReplicas + ""));
		}

		return containerMap;
	}

	public static miniyaml.Scalar scalar(String value) {
		Scalar scalar = MiniyamlFactory.eINSTANCE.createScalar();
		scalar.setValue(value);
		return scalar;
	}

	public static MapEntry putMapEntry(Map map, String key, Value value) {
		for (MapEntry entry : map.getEntries()) {
			if (key.equals(entry.getKey())) {
				entry.setValue(value);
				return entry;
			}
		}
		return addMapEntry(map, key, () -> value);
	}

	public MapEntry setImage(Map serverMap, String imageName) {
		return putMapEntry(serverMap, "image", scalar(imageName));
	}

	public List addDependsOn(Map webServer, String dbContainerName) {
		List l = getOrCreateMapEntry(webServer, "depends_on", MiniyamlFactory.eINSTANCE::createList);
		l.getValues().add(scalar(dbContainerName));
		return l;
	}

	public List mountVolume(Map container, String volume, String path) {
		List l = getOrCreateMapEntry(container, "volumes", MiniyamlFactory.eINSTANCE::createList);
		l.getValues().add(scalar(volume + ":" + path));
		return l;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Value> T getOrCreateMapEntry(miniyaml.Map map, String key, Supplier<T> defaultValue) {
		for (MapEntry entry : map.getEntries()) {
			if (key.equals(entry.getKey())) {
				return (T) entry.getValue();
			}
		}
		return (T) addMapEntry(map, key, defaultValue).getValue();
	}

	public static MapEntry addMapEntry(miniyaml.Map map, String key, Supplier<? extends Value> defaultValue) {
		MapEntry newEntry = MiniyamlFactory.eINSTANCE.createMapEntry();
		newEntry.setKey(key);
		newEntry.setValue(defaultValue.get());
		map.getEntries().add(newEntry);
		return newEntry;
	}
}
