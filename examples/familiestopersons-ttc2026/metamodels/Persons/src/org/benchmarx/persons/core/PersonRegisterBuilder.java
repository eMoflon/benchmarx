package org.benchmarx.persons.core;

import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import Persons.Female;
import Persons.Male;
import Persons.Person;
import Persons.PersonRegister;
import Persons.PersonsFactory;
import Persons.PersonsPackage;

public class PersonRegisterBuilder {
	private Supplier<PersonRegister> register;
	private PersonsFactory f = PersonsFactory.eINSTANCE;
	private Consumer<EObject> createNode;
	private BiConsumer<EReference, List<EObject>> createEdge;

	public PersonRegisterBuilder(Supplier<PersonRegister> reg, Consumer<EObject> cn, BiConsumer<EReference, List<EObject>> ce) {
		register = reg;
		this.createEdge = ce;
		this.createNode = cn;
	}
	
	private void createPersonEdit(Person p) {
		createNode.accept(p);
		createEdge.accept(PersonsPackage.Literals.PERSON_REGISTER__PERSONS, List.of(register.get(), p));		
	}

	public PersonRegisterBuilder male(String name) {
		Male m = f.createMale();
		m.setName(name);
		register.get().getPersons().add(m);
		
		createPersonEdit(m);
		return this;
	}

	public PersonRegisterBuilder male(String name, Date date) {
		Male m = f.createMale();
		m.setName(name);
		m.setBirthday(date);
		register.get().getPersons().add(m);
		
		createPersonEdit(m);
		return this;
	}

	public PersonRegisterBuilder female(String name) {
		Female fm = f.createFemale();
		fm.setName(name);
		register.get().getPersons().add(fm);
		
		createPersonEdit(fm);
		return this;
	}

	public PersonRegisterBuilder female(String name, Date date) {
		Female fm = f.createFemale();
		fm.setName(name);
		fm.setBirthday(date);
		register.get().getPersons().add(fm);
		
		createPersonEdit(fm);
		return this;
	}
}
