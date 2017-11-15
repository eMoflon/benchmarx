package org.benchmarx.bags1.core

import bags1.MyBag
import org.benchmarx.emf.Comparator

import static org.junit.Assert.*
import bags1.Element
import java.util.ArrayList

class Bag1Comparator implements Comparator<MyBag> {
	
	ElementNormaliser comparator
	
	new (){
		comparator = new ElementNormaliser();
	}
	override assertEquals(MyBag expected, MyBag actual) {
		assertTrue(bagToString(expected).startsWith("Bag1"))
		assertEquals(bagToString(expected), bagToString(actual))
	}
	
	def bagToString(MyBag b) {
		return '''
		Bag1 {
			elements = [
				«val sortedList = new ArrayList<Element>(b.elements)»
				«comparator.normalize(sortedList)»
				«FOR e: sortedList SEPARATOR ","»
				{
					  value = "«e.value»",
					  incrementalID = "«e.incrementalID»"
				}
				«ENDFOR»
			]
		}
		'''
	}
	
}