package org.benchmarx.gantt.core

import static org.junit.Assert.*

import org.benchmarx.emf.Comparator
import gantt.GanttDiagram
import gantt.Activity
import gantt.Dependency
import java.util.ArrayList
import gantt.Element

class GanttComparator implements Comparator<GanttDiagram> {
	def static modelToString(GanttDiagram diagram) {
		val ArrayList<Element> sortedElements = new ArrayList<Element>(diagram.elements)
		GanttNormalizer.normalize(sortedElements)
		val name = if (diagram.name == null) "" else diagram.name
		return "GanttDiagram " + name + " " + diagram.incrementalID
				+ " {" + sortedElements.map[e | e.elementToString].join(", ") + "}"
	}
	
	override assertEquals(GanttDiagram expected, GanttDiagram actual) {
		assertTrue(modelToString(expected).startsWith("GanttDiagram "))
		assertEquals(modelToString(expected), modelToString(actual))
	}
	
	def dispatch private static String elementToString(Activity activity) {
		return "{" + activity.name + ", " + activity.duration + "}"
	}
	
	def dispatch private static String elementToString(Dependency dependency) {		
		return "{" + dependency.predecessor.name + ", " + dependency.successor.name + ", "
				+ dependency.dependencyType + ", " + dependency.offset + "}"
	}
}
