package de.tbuchmann.ttc.rules;

import containers.Volume
import de.tbuchmann.ttc.trafo.Containers2MiniYAML
import miniyaml.MapEntry
import miniyaml.Scalar

class VolumeMount2ScalarImpl extends VolumeMount2Scalar {	
	new(Containers2MiniYAML trafo) {
		super(trafo)
	}
	
	override protected valueFrom(String path, Volume volume) {
		return new Type4value(volume.name + ":" + path)
	}

	override protected filterSc(Scalar sc) {
		!(sc.eContainer instanceof MapEntry && (sc.eContainer as MapEntry).key == "version")
	}
	
}
