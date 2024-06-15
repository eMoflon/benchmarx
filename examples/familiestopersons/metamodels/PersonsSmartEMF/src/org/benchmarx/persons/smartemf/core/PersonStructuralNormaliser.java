package org.benchmarx.persons.smartemf.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import PersonsSmartEMF.Female;
import PersonsSmartEMF.Male;
import PersonsSmartEMF.Person;

public class PersonStructuralNormaliser implements Comparator<Person>{
	@Override
	public int compare(Person person1, Person person2) {
		if(person1 instanceof Female && person2 instanceof Male)
		{
			return 1;
		}
		else if((person1 instanceof Female && person2 instanceof Female)||(person1 instanceof Male && person2 instanceof Male))
		{
			return 0;
		}
		else
		{
			return -1;
		}
			
		
	}

	public void normalize(List<Person> persons){
		Comparator<Person> comparator = new PersonStructuralNormaliser();
		Collections.sort(persons, comparator);
	}
}
