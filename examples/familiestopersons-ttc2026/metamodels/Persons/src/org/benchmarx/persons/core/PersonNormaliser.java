package org.benchmarx.persons.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Persons.Female;
import Persons.Male;
import Persons.Person;

public class PersonNormaliser implements Comparator<Person>{
	@Override
	public int compare(Person person1, Person person2) {
		if(!(person1.getName().equals(person2.getName())))
		{
			return person1.getName().compareTo(person2.getName());
		}
		else
		{
			if(!(person1.getBirthday().equals(person2.getBirthday())))
			{
				return person1.getBirthday().compareTo(person2.getBirthday());
			}
			else
			{
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
		}
	}

	public void normalize(List<Person> persons){
		Comparator<Person> comparator = new PersonNormaliser();
		Collections.sort(persons, comparator);
	}
}
