package org.benchmarx.examples.containerstominiyaml.testsuite;

import containers.Composition;
import containers.Container;
import containers.ContainersFactory;
import containers.Image;
import containers.Volume;
import containers.VolumeMount;

public class CompositionsHelper {

	public Image addImage(Composition c, String name) {
		Image image = ContainersFactory.eINSTANCE.createImage();
		image.setImage(name);
		c.getNodes().add(image);
		return image;
	}

	public Volume addVolume(Composition c, String name) {
		Volume vol = ContainersFactory.eINSTANCE.createVolume();
		vol.setName(name);
		c.getNodes().add(vol);
		return vol;
	}

	public Container addContainer(Composition c, String name, int nReplicas) {
		Container container = ContainersFactory.eINSTANCE.createContainer();
		container.setName(name);
		container.setReplicas(nReplicas);
		c.getNodes().add(container);
		return container;
	}

	public VolumeMount mountVolume(Container container, Volume volume, String path) {
		VolumeMount mount = ContainersFactory.eINSTANCE.createVolumeMount();
		mount.setVolume(volume);
		mount.setPath(path);
		container.getVolumeMounts().add(mount);
		return mount;
	}
}
