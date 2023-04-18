package org.benchmarx.examples.containerstominiyaml.testsuite;

import containers.Composition;
import containers.ContainersFactory;
import containers.Image;
import containers.Volume;

public class CompositionsHelper {

	public void addImage(Composition c, String name) {
		Image image = ContainersFactory.eINSTANCE.createImage();
		image.setImage(name);
		c.getNodes().add(image);
	}

	public void addVolume(Composition c, String name) {
		Volume vol = ContainersFactory.eINSTANCE.createVolume();
		vol.setName(name);
		c.getNodes().add(vol);
	}
}
