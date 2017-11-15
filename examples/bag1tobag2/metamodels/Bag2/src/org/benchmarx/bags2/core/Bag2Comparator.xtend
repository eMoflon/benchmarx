package org.benchmarx.bags2.core

import bags2.MyBag
import org.benchmarx.emf.Comparator

import static org.junit.Assert.*
import bags2.Element
import java.util.ArrayList

class Bag2Comparator implements Comparator<MyBag> {
	
	ElementNormaliser comparator
	
	new (){
		comparator = new ElementNormaliser();
	}
	override assertEquals(MyBag expected, MyBag actual) {
		assertTrue(bagToString(expected).startsWith("Bag2"))
		assertEquals(bagToString(expected), bagToString(actual))
	}
	
	def bagToString(MyBag b) {
		return '''
		Bag2 {
			elements = [
				«val sortedList = new ArrayList<Element>(b.elements)»
				«comparator.normalize(sortedList)»
				«FOR e: sortedList SEPARATOR ","»
				{
					  value = "«e.value»",
					  multiplicity = «e.multiplicity»,
					  incrementalID = "«e.incrementalID»"
				}
				«ENDFOR»
			]
		}
		'''
	}
	
}
