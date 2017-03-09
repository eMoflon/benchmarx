package org.benchmarx.persons.core;

import java.util.Date;

import Persons.Female;
import Persons.Male;
import Persons.PersonRegister;
import Persons.PersonsFactory;

public class PersonRegisterBuilder {

	private PersonRegister register;
	private PersonsFactory f = PersonsFactory.eINSTANCE;
	
	public PersonRegisterBuilder() {
		register = f.createPersonRegister();
	}
	
	public PersonRegisterBuilder(PersonRegister reg) {
		register = reg;
	}
	
	public PersonRegisterBuilder male(String name)  {
		Male m = f.createMale();
		m.setName(name);
		register.getPersons().add(m);
		return this;
	}
	
	public PersonRegisterBuilder male(String name, Date date)  {
		Male m = f.createMale();
		m.setName(name);
		m.setBirthday(date);
		register.getPersons().add(m);
		return this;
	}
	
	public PersonRegisterBuilder female(String name) {
		Female fm = f.createFemale();
		fm.setName(name);
		register.getPersons().add(fm);
		return this;
	}
	
	public PersonRegisterBuilder female(String name, Date date) {
		Female fm = f.createFemale();
		fm.setName(name);
		fm.setBirthday(date);
		register.getPersons().add(fm);
		return this;
	}
}
