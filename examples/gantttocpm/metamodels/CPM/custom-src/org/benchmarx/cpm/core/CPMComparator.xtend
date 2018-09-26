package org.benchmarx.cpm.core

import static org.junit.Assert.*

import org.benchmarx.emf.Comparator
import cpm.CPMNetwork
import java.util.ArrayList
import cpm.Element
import cpm.Event
import cpm.Activity
import java.util.HashMap

class CPMComparator implements Comparator<CPMNetwork> {
	def static networkToString(CPMNetwork network) {
		val ArrayList<Element> sortedElements = new ArrayList<Element>(network.elements)
		CPMNormalizer.normalize(sortedElements)
		var int i = 0
		val HashMap<Integer, Integer> normalizedEventNumbers = new HashMap<Integer, Integer>()
		for (Element element : sortedElements) {
			if (element instanceof Event) {
				if (normalizedEventNumbers.containsKey(element.number)) {
					return "invalid CPMNetwork: the event numbers are not unique!"
				}
				normalizedEventNumbers.put(element.number, i++)
			}
		}
		val name = if (network.name === null) "" else network.name
		return 
		'''
		CPMNetwork name: «name» id: «network.incrementalID» {
  		«sortedElements.map[e | e.elementToString(normalizedEventNumbers)]»
		}
		'''
	}
	
	override assertEquals(CPMNetwork expected, CPMNetwork actual) {
		assertTrue(networkToString(expected).startsWith("CPMNetwork "))
		assertEquals(networkToString(expected), networkToString(actual))
	}
	
	def dispatch private static String elementToString(
			Event event, HashMap<Integer, Integer> normalizedEventNumbers) {
		if (!normalizedEventNumbers.containsKey(event.number)) {
			throw new IllegalArgumentException("The referenced event " + event.number + " doesn't exist.")
		}
		return 
		'''
		{ 
			«normalizedEventNumbers.get(event.number)»
		}
		'''
		
	}
	
	def dispatch private static String elementToString(
			Activity activity, HashMap<Integer, Integer> normalizedEventNumbers) {	
		if (!normalizedEventNumbers.containsKey(activity.sourceEvent.number)) {
			throw new IllegalArgumentException(
					"The referenced event " + activity.sourceEvent.number + " doesn't exist.")
		}
		if (!normalizedEventNumbers.containsKey(activity.targetEvent.number)) {
			throw new IllegalArgumentException(
					"The referenced event " + activity.targetEvent.number + " doesn't exist.")
		}	
		return 
		'''
		{ 
			SourceEvent:       «normalizedEventNumbers.get(activity.sourceEvent.number)»
			TargetEvent: 	   «normalizedEventNumbers.get(activity.targetEvent.number)»
			Activity_Name: 	   «activity.name»
			Activity_Duration: «activity.duration» 
		}
		'''
	}
}
