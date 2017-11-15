package org.benchmarx.sets.core

import org.benchmarx.emf.Comparator
import java.util.List
import java.util.ArrayList

import static org.junit.Assert.*

import sets.MySet
import sets.Element

class SetComparator implements Comparator<MySet> {	
	ElementNormaliser comparator
	
	new (){
		comparator = new ElementNormaliser();
	}
	
	override assertEquals(MySet expected, MySet actual) {
		assertTrue(mySetToString(expected).startsWith("SetsMySet"))
		assertEquals(mySetToString(expected), mySetToString(actual))
	}
	
	def mySetToString(MySet set) {
		val List<Element> elements = new ArrayList<Element>(set.elements)
		comparator.normalize(elements)
		return "SetsMySet " + set.name + " " + set.incrementalID + " elements: " + elements.map[value].join(", ")
	}
}