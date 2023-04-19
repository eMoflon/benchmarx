package org.benchmarx.examples.containerstominiyaml.testsuite;

import containers.Composition;
import containers.Container;
import containers.ContainersFactory;
import containers.Image;
import containers.Volume;

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
}
