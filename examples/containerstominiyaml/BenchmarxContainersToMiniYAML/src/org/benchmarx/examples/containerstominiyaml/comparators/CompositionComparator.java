package org.benchmarx.examples.containerstominiyaml.comparators;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.benchmarx.emf.Comparator;
import org.eclipse.emf.common.util.EList;
import org.junit.Assert;

import containers.Composition;
import containers.Container;
import containers.Image;
import containers.NamedElement;
import containers.Node;
import containers.Volume;
import containers.VolumeMount;

public class CompositionComparator implements Comparator<Composition> {

	@Override
	public void assertEquals(Composition expected, Composition actual) {
		Assert.assertEquals("Compositions should have the same number of nodes",
			expected.getNodes().size(), actual.getNodes().size());

		Map<String, Container> lContainers = byName(expected, Container.class);
		Map<String, Container> rContainers = byName(actual, Container.class);
		for (Map.Entry<String, Container> lContainerEntry : lContainers.entrySet()) {
			Container rContainer = rContainers.remove(lContainerEntry.getKey());
			assertNotNull("Right composition should have container " + lContainerEntry.getKey(), rContainer);
			assertEquals(lContainerEntry.getValue(), rContainer);
		}
		assertTrue("Right composition should have the same containers as the left one", rContainers.isEmpty());

		Map<String, Volume> lVolumes = byName(expected, Volume.class);
		Map<String, Volume> rVolumes = byName(actual, Volume.class);
		Assert.assertEquals("Compositions should have the same volumes", lVolumes.keySet(), rVolumes.keySet());

		Set<String> lImages = images(expected);
		Set<String> rImages = images(actual);
		Assert.assertEquals("Composition should have the same images", lImages, rImages);
	}

	private void assertEquals(Container expected, Container actual) {
		Assert.assertEquals("Should have same name", expected.getName(), actual.getName());
		Assert.assertEquals("Should have same replicas", expected.getReplicas(), actual.getReplicas());

		assertEquals("Image for " + expected.getName(),
			expected.getImage(), actual.getImage());

		Assert.assertEquals("Depends for " + expected.getName(),
			names(expected.getDependsOn()), names(actual.getDependsOn()));
 
		Assert.assertEquals("Mounts for " + expected.getName(),
			volumeMounts(expected.getVolumeMounts()),
			volumeMounts(actual.getVolumeMounts()));
	}

	private void assertEquals(String desc, Image expected, Image actual) {
		if (expected != null) {
			assertNotNull(desc + ": should have an image", actual);
			Assert.assertEquals(desc + ": should use same image", expected.getImage(), actual.getImage());
		} else {
			assertNull(desc + ": should not have an image", actual);
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends NamedElement> Map<String, T> byName(Composition cmp, Class<T> klass) {
		Map<String, T> containers = new HashMap<>();
		for (Node n : cmp.getNodes()) {
			if (klass.isInstance(n)) {
				T container = (T) n;
				containers.put(container.getName(), container);
			}
		}
		return containers;
	}

	private <T extends NamedElement> Set<String> names(EList<T> elements) {
		Set<String> names = new HashSet<>();
		for (T e : elements) {
			names.add(e.getName());
		}
		return names;
	}

	private Set<String> volumeMounts(EList<VolumeMount> lMounts) {
		Set<String> mounts = new HashSet<>(lMounts.size());
		for (VolumeMount vm : lMounts) {
			mounts.add(String.format("%s:%s", vm.getVolume().getName(), vm.getPath()));
		}
		return mounts;
	}

	private Set<String> images(Composition cmp) {
		Set<String> images = new HashSet<>();
		for (Node n : cmp.getNodes()) {
			if (n instanceof Image) {
				images.add(((Image) n).getImage());
			}
		}
		return images;
	}
}
