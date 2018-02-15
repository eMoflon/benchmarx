package org.benchmarx.osets.core

import org.benchmarx.emf.Comparator
import java.util.List
import java.util.ArrayList

import static org.junit.Assert.*

import osets.MyOrderedSet
import osets.Element

class OsetComparator implements Comparator<MyOrderedSet> {	
	static val ElementNormaliser comparator = new ElementNormaliser()
	
	def static myOrderedSetToString(MyOrderedSet set) {
		if (!isConnected(set)) {
			return "invalid SetsMyOrderedSet: not connected!"
		}
		
		if (isCycle(set)) {
			return "invalid SetsMyOrderedSet: cycle!"
		}
		
		val List<Element> elements = new ArrayList<Element>(set.elements)
		comparator.normalize(elements)
		return "SetsMyOrderedSet " + set.name + " " + set.incrementalID + " elements: " + elements.map[value].join(", ")
	}
	
	/**
	 * @return true if all elements of set are connected, false otherwise.
	 */
	def static isConnected(MyOrderedSet set) {
		if (set.getElements().isEmpty()) {
			return true;
		}
		
		val Element start = set.getElements().get(0);
		var Element first = start;
		while (first.getPrevious() != null) {
			first = first.getPrevious();
			if (first == start) {
				return true;
			}
		}
		var int connectedElements = 1;
		while (first.getNext() != null) {
			connectedElements++;
			first = first.getNext();
		}
		return (connectedElements == set.getElements().size());
	}
	
	/**
	 * @return true if the first element in the elements list of set is part of a cycle, false otherwise.
	 */
	def static isCycle(MyOrderedSet set) {
		if (set.getElements().isEmpty()) {
			return false;
		}
		
		val Element first = set.getElements().get(0);
		var Element next = first;
		while (next.getNext() != null) {
			next = next.getNext();
			if (first == next) {
				return true;
			}
		}
		return false;
	}
	
	override assertEquals(MyOrderedSet expected, MyOrderedSet actual) {
		assertTrue(myOrderedSetToString(expected).startsWith("SetsMyOrderedSet"))
		assertEquals(myOrderedSetToString(expected), myOrderedSetToString(actual))
	}
}