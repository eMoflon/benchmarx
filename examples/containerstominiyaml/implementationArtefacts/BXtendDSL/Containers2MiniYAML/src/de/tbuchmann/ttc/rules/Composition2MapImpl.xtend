package de.tbuchmann.ttc.rules;

import containers.Container
import containers.Image
import containers.Node
import containers.Volume
import de.tbuchmann.ttc.trafo.Containers2MiniYAML
import java.util.ArrayList
import java.util.List
import miniyaml.Map
import miniyaml.MapEntry
import miniyaml.MiniyamlFactory
import org.eclipse.emf.common.util.ECollections
import de.tbuchmann.ttc.rules.Composition2Map.Type4nodes
import de.tbuchmann.ttc.corrmodel.SingleElem
import containers.Composition
import de.tbuchmann.ttc.corrmodel.Transformation
import de.tbuchmann.ttc.corrmodel.Corr
import de.tbuchmann.ttc.corrmodel.CorrElem
import miniyaml.Value
import miniyaml.Scalar
import java.util.HashMap
import containers.ContainersFactory

class Composition2MapImpl extends Composition2Map {
	val yamlFactory = MiniyamlFactory.eINSTANCE
		
	new(Containers2MiniYAML trafo) {
		super(trafo)
	}
	
	override protected onMCreation(Map m) {
		val m1 = yamlFactory.createMapEntry() => [key = "version"]
		val m2 = yamlFactory.createMapEntry() => [key = "services"]
		val m3 = yamlFactory.createMapEntry() => [key = "volumes"]
		
		m1.value = yamlFactory.createScalar() => [value = "2.4"]		
		m2.value = yamlFactory.createMap()
		m3.value = yamlFactory.createMap()
		
		m.entries.addAll(#[m1,m2,m3])
	}

 
	override protected entriesFrom(List<MapEntry> nodImaMe, List<MapEntry> nodConMe, List<MapEntry> nodVolMe) {
		if (targetModel.allContents.size == 0)
			return new Type4entries(newArrayList)
		val root = targetModel.contents.get(0) as Map
		val entries = ECollections.newBasicEList(root.entries)
		
		// find map entries for volumes
		val volumesEntry = entries.findFirst[e | e.key == "volumes"]
		// this is a map again
		val volumesMap = volumesEntry.value as Map
		volumesMap.entries.addAll(nodVolMe)
		
		// find map for services
		val servicesEntry = entries.findFirst[e | e.key == "services"]
		//this is a map again
		val servicesMap = servicesEntry.value as Map
		servicesMap.entries.addAll(nodConMe)		
		
		new Type4entries(entries)
	}

	override protected filterM(Map m) {
		m.eContainer === null
	}
 
	override protected nodesFrom(List<MapEntry> entries, List<Image> entImaImg, List<Container> entConC, List<Volume> entVolV) {
		val nodes = newArrayList
		val volumeMounts = newArrayList
		val corrRoot = corrModel.contents.get(0) as Transformation
		val volumeCorrepondences = corrRoot.correspondences.filter[c | c.ruleId == "Volume2MapEntry"]
		for (Corr c : volumeCorrepondences) {
			val src = c.source
			for (CorrElem ce : src) {
				if (ce instanceof SingleElem) {
					nodes += (ce as SingleElem).element as Node
					volumeMounts += (ce as SingleElem).element as Volume
				}				
			}
		}
		val containerCorrepondences = corrRoot.correspondences.filter[c | c.ruleId == "Container2MapEntry"]
		val containers = newArrayList
		val dependencies = new HashMap<Container, List<String>>()
		for (Corr c : containerCorrepondences) {
			val src = c.source
			val trg = c.target
			for (CorrElem ce : src) {
				if (ce instanceof SingleElem) {
					nodes += (ce as SingleElem).element as Node
					containers += (ce as SingleElem).element as Container
					// handle depends here
					// get corresponding MapEntry
					val me = (trg.get(0) as SingleElem).element as MapEntry
					val map = me.value as Map	
					for (MapEntry childEntry : map.entries) {
						if (childEntry.key == "depends_on") {
							val dependContainers = childEntry.value as miniyaml.List
							val dependNames = newArrayList
							for (Value v : dependContainers.values) {
								dependNames += (v as Scalar).value
							}
							if (dependNames.size > 0)
								dependencies.put( (ce as SingleElem).element as Container, dependNames)					
						}
						if (childEntry.key == "volumes") {
							val mountList = childEntry.value as miniyaml.List
							for (Value v : mountList.values) {
								if ((v as Scalar).value.contains(":")) {
									val vm = ContainersFactory.eINSTANCE.createVolumeMount() => [path = (v as Scalar).value.split(":")?.get(1)
										volume = volumeMounts.findFirst[name == (v as Scalar).value.split(":")?.get(0)]	
									]	
									((ce as SingleElem).element as Container).volumeMounts += vm				
								}						
							}
						}
					}				
				}				
			}
		}
		for (Container c : containers) {
			val deps = dependencies.get(c)
			if (deps !== null) {
				for (String depName : deps) {
					c.dependsOn += containers.findFirst[name == depName]
				}
			}
		}
		
		val imageCorrespondences = corrRoot.correspondences.filter[c | c.ruleId == "Image2MapEntry"]
		for (Corr c : imageCorrespondences) {
			val src = c.source
			val trg = c.target
			for (CorrElem ce : src) {
				if (ce instanceof SingleElem) {
					nodes += (ce as SingleElem).element as Node
					// set container reference also
					val me = (trg.get(0) as SingleElem).element as MapEntry
					val correpondingContainerEntry = me.eContainer.eContainer
					// get Container src Element
					val srcContainer = (elementsToCorr.get(correpondingContainerEntry).source.get(0) as SingleElem).element as Container
					srcContainer.image = (ce as SingleElem).element as Image
				}				
			}
		}
		return new Type4nodes(nodes)

	}
}
