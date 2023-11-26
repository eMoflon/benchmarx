package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.trafo.Containers2MiniYAML
import miniyaml.MapEntry
import miniyaml.Map

class Volume2MapEntryImpl extends Volume2MapEntry {	
	new(Containers2MiniYAML trafo) {
		super(trafo)
	}
	
	override protected filterMe(MapEntry me) {
		(me.key != "services" || me.key != "version" || me.key != "volumes") &&
		me.eContainer instanceof Map &&
		me.eContainer.eContainer instanceof MapEntry &&
		(me.eContainer.eContainer as MapEntry).key == "volumes"
	}
	
}
