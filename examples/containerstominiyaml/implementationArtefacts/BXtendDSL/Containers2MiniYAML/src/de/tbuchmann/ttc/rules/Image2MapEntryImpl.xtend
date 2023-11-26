package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.trafo.Containers2MiniYAML
import miniyaml.MapEntry
import miniyaml.MiniyamlFactory
import miniyaml.Scalar
import miniyaml.Value

class Image2MapEntryImpl extends Image2MapEntry {	
	new(Containers2MiniYAML trafo) {
		super(trafo)
	}
	
	override protected filterMe(MapEntry me) {
		me.key == "image"
	}
	
	override protected valueFrom(String image) {
		new Type4value(MiniyamlFactory.eINSTANCE.createScalar() => [value = image])
	}
	
	override protected imageFrom(Value value) {
		return new Type4image(((value as Scalar).value))		
	}
	
	override protected onMeCreation(MapEntry me) {
		me.key = "image"
	}
	
	
}
