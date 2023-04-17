package org.benchmarx.persons.core;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.benchmarx.edit.IEdit;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

import Persons.Person;
import Persons.PersonRegister;

public class PersonHelper {

	private PersonRegisterBuilder builder;
	private PersonRegister register;
	private Runnable startNewEdit;
	private Supplier<IEdit<PersonRegister>> getEdit;
	private BiConsumer<EAttribute, List<?>> changeAttribute;

	public PersonHelper(PersonRegister register, Runnable startNewEdit, Supplier<IEdit<PersonRegister>> getEdit,
			Consumer<EObject> createNode, BiConsumer<EReference, List<EObject>> createEdge,
			BiConsumer<EAttribute, List<?>> changeAttribute

	) {
		builder = new PersonRegisterBuilder(register, createNode, createEdge);
		this.register = register;
		this.startNewEdit = startNewEdit;
		this.getEdit = getEdit;
		this.changeAttribute = changeAttribute;
	}

	private IEdit<PersonRegister> edit(Runnable operations) {
		startNewEdit.run();
		operations.run();
		return getEdit.get();
	}

	private Person getFromRegister(String name) {
		Optional<Person> person = register.getPersons().stream().filter(p -> p.getName().equals(name)).findAny();

		if (person.isPresent())
			return person.get();
		else
			return null;
	}

	private List<Person> getAllFromRegister(String name) {
		List<Person> result = register.getPersons().stream().filter(p -> p.getName().equals(name))
				.collect(Collectors.toList());

		return result;
	}

	public IEdit<PersonRegister> createRod() {
		return edit(() -> builder.male("Flanders, Rod"));
	}

	public IEdit<PersonRegister> createHomer() {
		return edit(() -> builder.male("Simpson, Homer"));
	}

	public IEdit<PersonRegister> createBart() {
		return edit(() -> builder.male("Simpson, Bart"));
	}

	public IEdit<PersonRegister> createMarge() {
		return edit(() -> builder.female("Simpson, Marge"));
	}

	public IEdit<PersonRegister> createLisa() {
		return edit(() -> builder.female("Simpson, Lisa"));
	}

	public IEdit<PersonRegister> createMaggie() {
		return edit(() -> builder.female("Simpson, Maggie"));
	}

	public IEdit<PersonRegister> createSeymour() {
		return edit(() -> builder.male("Skinner, Seymour"));
	}

	//FIXME: Create edit
	
	public void changeAllBirthdays() {
		setBirthdaysOfSimpson();
		setBirthdayOfRod();
	}

	public void setBirthdayOfHomer() {
		Person person = getFromRegister("Simpson, Homer");

		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.JANUARY, 9, 10, 11, 12);
		Date date = cal.getTime();
		person.setBirthday(date);
	}

	public void setBirthdayOfMarge() {
		Person person2 = getFromRegister("Simpson, Marge");

		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.FEBRUARY, 9, 10, 11, 12);
		Date date = cal.getTime();
		person2.setBirthday(date);
	}

	public void setBirthdayOfMaggie() {
		Person person5 = getFromRegister("Simpson, Maggie");

		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.MARCH, 7, 10, 11, 12);
		Date date = cal.getTime();
		person5.setBirthday(date);
	}

	public void setBirthdayOfRod() {
		Calendar cal = Calendar.getInstance();
		Date date;

		Person person1 = getFromRegister("Flanders, Rod");

		cal.set(2013, Calendar.OCTOBER, 1, 10, 11, 12);
		date = cal.getTime();
		person1.setBirthday(date);
	}

	public void setBirthdayOfFatherBart() {
		Calendar cal = Calendar.getInstance();
		Date date;

		List<Person> barts = getAllFromRegister("Simpson, Bart");
		Date defaultDate = (Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(),
				"0000-1-1");
		for (Person bart : barts) {
			if (bart.getBirthday().equals(defaultDate)) {
				cal.set(2013, Calendar.MARCH, 9, 10, 11, 12);
				date = cal.getTime();
				bart.setBirthday(date);
				return;
			}
		}
	}

	public void setBirthdaysOfSimpson() {
		Calendar cal = Calendar.getInstance();
		Date date;

		Person person1 = getFromRegister("Simpson, Homer");
		if (person1 != null) {
			cal.set(2013, Calendar.JANUARY, 9, 10, 11, 12);
			date = cal.getTime();
			person1.setBirthday(date);
		}

		Person person2 = getFromRegister("Simpson, Marge");

		if (person2 != null) {
			cal.set(2013, Calendar.FEBRUARY, 9, 10, 11, 12);
			date = cal.getTime();
			person2.setBirthday(date);
		}

		List<Person> barts = getAllFromRegister("Simpson, Bart");

		Date defaultDate = (Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(),
				"0000-1-1");
		for (Person p : barts) {
			if (p.getBirthday().equals(defaultDate)) {
				cal.set(2013, Calendar.MARCH, 10, 10, 11, 12);
				date = cal.getTime();
				p.setBirthday(date);
			}
		}

		Person person4 = getFromRegister("Simpson, Lisa");

		if (person4 != null) {
			cal.set(2013, Calendar.MARCH, 8, 10, 11, 12);
			date = cal.getTime();
			person4.setBirthday(date);
		}

		Person person5 = getFromRegister("Simpson, Maggie");

		if (person5 != null) {
			cal.set(2013, Calendar.MARCH, 7, 10, 11, 12);
			date = cal.getTime();
			person5.setBirthday(date);
		}
	}

	public void setBirthdayOfYoungerBart() {
		Calendar cal = Calendar.getInstance();
		Date date;
		List<Person> barts = getAllFromRegister("Simpson, Bart");
		Date defaultDate = (Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(),
				"0000-1-1");
		for (Person p : barts) {
			if (p.getBirthday().equals(defaultDate)) {
				cal.set(2013, Calendar.MARCH, 11, 10, 11, 12);
				date = cal.getTime();
				p.setBirthday(date);
			}
		}
	}

	public void firstNameChangeOfHomer() {
		Person person = getFromRegister("Simpson, Homer");
		person.setName("Simpson, HomerX");
	}

	@SuppressWarnings("deprecation")
	public void firstNameChangeOfBart() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.MARCH, 10, 10, 11, 12);
		Date date = cal.getTime();

		List<Person> barts = getAllFromRegister("Simpson, Bart");
		for (Person bart : barts) {
			if (bart.getBirthday().getDay() == date.getDay())
				bart.setName("Simpson, Bartholomew");
		}
	}

	@SuppressWarnings("deprecation")
	public void fullNameChangeOfOtherBart() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.MARCH, 11, 10, 11, 12);
		Date date = cal.getTime();

		List<Person> barts = getAllFromRegister("Simpson, Bart");
		for (Person bart : barts) {
			if (bart.getBirthday().getDay() == date.getDay())
				bart.setName("Skinner, Seymour");
		}
	}

	@SuppressWarnings("deprecation")
	public void fullNameChangeOfFatherBart() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.MARCH, 9, 10, 11, 12);
		Date date = cal.getTime();

		List<Person> barts = getAllFromRegister("Simpson, Bart");
		for (Person bart : barts) {
			if (bart.getBirthday().getDay() == date.getDay())
				bart.setName("Flanders, Todd");
		}
	}

	public void familyNameChangeOfLisa() {
		Person person = getFromRegister("Simpson, Lisa");
		person.setName("Flanders, Lisa");
	}

	public void fullNameChangeOfMarge() {
		Person person = getFromRegister("Simpson, Marge");
		person.setName("Flanders, Maude");
	}

	public void deleteMarge(PersonRegister register) {
		Person person = getFromRegister("Simpson, Marge");
		EcoreUtil.delete(person);
	}

	public void deleteHomer(PersonRegister register) {
		Person person = getFromRegister("Simpson, Homer");
		EcoreUtil.delete(person);
	}

	public void deleteMaggie(PersonRegister register) {
		Person person = getFromRegister("Simpson, Maggie");
		EcoreUtil.delete(person);
	}

	public void idleDelta() {

	}

	public void hippocraticDelta() {
		// change birthday for each person
		for (Person p : register.getPersons()) {
			Calendar cal = Calendar.getInstance();
			cal.set(2013, Calendar.MARCH, 9, 10, 11, 12);
			Date date = cal.getTime();
			p.setBirthday(date);
		}
	}

}
