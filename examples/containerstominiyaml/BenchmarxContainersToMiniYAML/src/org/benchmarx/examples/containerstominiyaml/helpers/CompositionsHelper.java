package org.benchmarx.examples.containerstominiyaml.helpers;

import java.util.Objects;
import java.util.Optional;

import containers.Composition;
import containers.Container;
import containers.ContainersFactory;
import containers.Image;
import containers.Node;
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

	public Optional<Container> getContainer(Composition c, String name) {
		for (Node n : c.getNodes()) {
			if (n instanceof Container) {
				Container container = (Container) n;
				if (Objects.equals(name, container.getName())) {
					return Optional.of(container);
				}
			}
		}
		return Optional.empty();
	}

	public void createWebserverMongoComposition(Composition c) {
		Container cWebServer = addContainer(c, "webserver", 1);
		Image nginx = addImage(c, "nginx:latest");
		cWebServer.setImage(nginx);

		Container cDatabase = addContainer(c, "database", 1);
		Image mongodb = addImage(c, "mongodb:latest");
		cDatabase.setImage(mongodb);
		cWebServer.getDependsOn().add(cDatabase);

		Volume volMongo = addVolume(c, "mongo_storage");
		mountVolume(cDatabase, volMongo, "/mongo/storage");
	}
}
