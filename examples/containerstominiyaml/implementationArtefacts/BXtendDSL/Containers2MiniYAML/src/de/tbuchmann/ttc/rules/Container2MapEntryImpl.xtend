package de.tbuchmann.ttc.rules;

import containers.Container
import containers.Image
import de.tbuchmann.ttc.corrmodel.SingleElem
import de.tbuchmann.ttc.trafo.Containers2MiniYAML
import java.util.List
import miniyaml.Map
import miniyaml.MapEntry
import miniyaml.MiniyamlFactory
import miniyaml.Scalar
import miniyaml.Value

class Container2MapEntryImpl extends Container2MapEntry {
	val yamlFactory = MiniyamlFactory.eINSTANCE
		
	new(Containers2MiniYAML trafo) {
		super(trafo)
	}
	
	override protected valueFrom(Image image, int replicas, List<Container> dependsOn, List<Scalar> volSc, Value oldValue) {
		var entry = yamlFactory.createMap()
				
		if (image !== null)
			entry.entries += (elementsToCorr.get(image).getTarget().get(0) as SingleElem).element as MapEntry
		
		if (!dependsOn.isEmpty) {
			val me = createMapEntry("depends_on")			
			val list = me.value as miniyaml.List
			for (Container c : dependsOn) 
				list.values += yamlFactory.createScalar() => [s | s.value = c.name]			
			entry.entries += me	
		}
	
		if (replicas > 1) {
			val me = yamlFactory.createMapEntry() => [key = "replicas"
				value = yamlFactory.createScalar() => [value = "" + replicas]
			]			
			entry.entries += me
		}		
		
		if (!volSc.empty) {
			val me = createMapEntry("volumes")
			val list = me.value as miniyaml.List
			for (Scalar s : volSc) {
				list.values += s
			}
			entry.entries += me
		}
		
		if (oldValue != null && oldValue instanceof Map) {
			for (MapEntry me : ((oldValue as Map).entries)) {
				if (me.key == "restart") {
					val newME = yamlFactory.createMapEntry() => [key = me.key]
					val newVal = yamlFactory.createScalar() => [value = (me.value as Scalar).value]
					newME.value = newVal
					entry.entries += newME
				}
				if (me.key == "tmpfs") {
					val newME = yamlFactory.createMapEntry() => [key = me.key]
					val newList = yamlFactory.createList()
					newME.value = newList
					val oldMEValue = me.value 
					for (Value v : (oldMEValue as miniyaml.List).values ) {
						val newV = yamlFactory.createScalar => [value = (v as Scalar).value]
						newList.values += newV						
					}
					entry.entries += newME
				}
			}
		}
				
		new Type4value(entry)
	}
		
	override protected filterMe(MapEntry me) {
		(me.key != "services" || me.key != "version" || me.key != "volumes") &&
		me.eContainer instanceof Map &&
		me.eContainer.eContainer instanceof MapEntry &&
		(me.eContainer.eContainer as MapEntry).key == "services"
	}
	
	override protected image_replicas_dependsOnFrom(Value value) {
		val depends = newArrayList
		var replicas = 1
		if (value instanceof Map) {
			for (MapEntry me : ((value as Map).entries)) {
				if (me.key == "replicas") 
					replicas = Integer::parseInt((me.value as Scalar).value)
			}
		} 		
		return new Type4image_replicas_dependsOn(null, replicas, depends)
	}
	
	def private createMapEntry(String name) {
		val me = yamlFactory.createMapEntry() => [m | m.key = name]			
		val list = yamlFactory.createList()
		me.value = list
		
		return me
	}
}
