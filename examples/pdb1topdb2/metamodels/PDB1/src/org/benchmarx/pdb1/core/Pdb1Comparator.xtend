package org.benchmarx.pdb1.core

import org.benchmarx.emf.Comparator
import pdb1.Database
import java.util.List
import pdb1.Person
import java.util.ArrayList

import static org.junit.Assert.*

class Pdb1Comparator implements Comparator<pdb1.Database> {
	
	Pdb1Normaliser comparator
	
	new (){
		comparator = new Pdb1Normaliser();
	}
	
	override assertEquals(Database expected, Database actual) {
		assertTrue(personsToString(expected).startsWith("Pdb1Database"))
		assertEquals(personsToString(expected), personsToString(actual))
	}
	
	def personsToString(Database persons) {
		return '''
		Pdb1Database «persons.name» {
			persons = [
				«val List<Person> sortedList = new ArrayList<Person>(persons.persons)»
				«comparator.normalize(sortedList)»
				«FOR p: sortedList SEPARATOR ","»
				«p.id» {
					  firstname = "«p.firstName»"
					, lastname = "«p.lastName»"
					, birthday = "«p.birthday»"
					, placeOfBirth = "«p.placeOfBirth»"
					, incrementalID = "«p.incrementalID»"
				}
				«ENDFOR»
			]
		}
		'''
	}	
	
}