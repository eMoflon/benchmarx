package org.benchmarx.persons.core

import Persons.Male
import Persons.Person
import Persons.PersonRegister
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.List
import org.benchmarx.emf.Comparator

import static org.junit.Assert.*

public class PersonsComparator implements Comparator<PersonRegister>{
	PersonNormaliser comparator
	
	new (){
		comparator = new PersonNormaliser();
	}
	
	override assertEquals(PersonRegister expected, PersonRegister actual) {
		assertTrue(personsToString(expected).startsWith("PersonRegister"))
		assertEquals(personsToString(expected), personsToString(actual))
	}
	
	def personsToString(PersonRegister persons) {
		return '''
		PersonRegister {
			persons = [
				«val List<Person> sortedList = new ArrayList<Person>(persons.persons)»
				«comparator.normalize(sortedList)»
				«FOR p: sortedList SEPARATOR ","»
				«IF p instanceof Male»
				 Male  {   
				          fullName = "«p.name»"
				        , birthday = "«p.birthday.toMyString»"
				 }
				«ELSE»
				 Female  {   
				          fullName = "«p.name»"
				        , birthday = "«p.birthday.toMyString»"
				 }
				«ENDIF»
				«ENDFOR»
			]
		}
		'''
	}

	def toMyString(Date d) {
		val sm = new SimpleDateFormat("YYYY-MM-dd")
		return sm.format(d)
	}	
}
