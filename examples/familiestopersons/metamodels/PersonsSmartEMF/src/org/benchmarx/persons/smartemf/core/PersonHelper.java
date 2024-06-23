package org.benchmarx.persons.smartemf.core;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.emoflon.smartemf.runtime.util.SmartEMFUtil;

import PersonsSmartEMF.Person;
import PersonsSmartEMF.PersonRegister;
import PersonsSmartEMF.PersonsSmartEMFPackage;

public class PersonHelper {
	private PersonRegisterBuilder builder;
	private Supplier<PersonRegister> register;
	private BiConsumer<EAttribute, List<?>> changeAttribute;
	private Consumer<EObject> deleteNode;
	private BiConsumer<EReference, List<EObject>> deleteEdge;

	public PersonHelper(Supplier<PersonRegister> register, Consumer<EObject> createNode,
			BiConsumer<EReference, List<EObject>> createEdge, BiConsumer<EAttribute, List<?>> changeAttribute,
			Consumer<EObject> deleteNode, BiConsumer<EReference, List<EObject>> deleteEdge) {
		builder = new PersonRegisterBuilder(register, createNode, createEdge);
		this.register = register;
		this.changeAttribute = changeAttribute;
		this.deleteEdge = deleteEdge;
		this.deleteNode = deleteNode;
	}

	private Person getFromRegister(String name) {
		Optional<Person> person = register.get().getPersons().stream().filter(p -> p.getName().equals(name)).findAny();

		if (person.isPresent())
			return person.get();
		else
			return null;
	}

	private List<Person> getAllFromRegister(String name) {
		List<Person> result = register.get().getPersons().stream().filter(p -> p.getName().equals(name))
				.collect(Collectors.toList());

		return result;
	}

	public void createRod() {
		builder.male("Flanders, Rod");
	}

	public void createHomer() {
		builder.male("Simpson, Homer");
	}

	public void createBart() {
		builder.male("Simpson, Bart");
	}

	public void createMarge() {
		builder.female("Simpson, Marge");
	}

	public void createLisa() {
		builder.female("Simpson, Lisa");
	}

	public void createMaggie() {
		builder.female("Simpson, Maggie");
	}

	public void createSeymour() {
		builder.male("Skinner, Seymour");
	}

	public void changeAllBirthdays() {
		setBirthdaysOfSimpson();
		Person person = getFromRegister("Flanders, Rod");
		if (person != null)
			setBirthdayOfPerson(person, getDate(2013, Calendar.OCTOBER, 1, 10, 11, 12));
	}

	private void setBirthdayOfPerson(String name, Date date) {
		Person person = getFromRegister(name);
		setBirthdayOfPerson(person, date);
	}

	public void setBirthdayOfPerson(Person person, Date date) {
		Date oldDate = person.getBirthday();
		person.setBirthday(date);

		changeAttribute.accept(//
				PersonsSmartEMFPackage.Literals.PERSON__BIRTHDAY, //
				List.of(person, oldDate, date));
	}

	private Date getDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hour, minute, second);
		Date date = cal.getTime();
		return date;
	}

	public void setBirthdayOfHomer() {
		setBirthdayOfPerson("Simpson, Homer", getDate(2013, Calendar.JANUARY, 9, 10, 11, 12));
	}

	public void setBirthdayOfSeymour() {
		setBirthdayOfPerson("Skinner, Seymour", getDate(2013, Calendar.JANUARY, 9, 10, 11, 12));
	}

	public void setBirthdayOfMarge() {
		setBirthdayOfPerson("Simpson, Marge", getDate(2013, Calendar.FEBRUARY, 9, 10, 11, 12));
	}

	public void setBirthdayOfMaggie() {
		setBirthdayOfPerson("Simpson, Maggie", getDate(2013, Calendar.MARCH, 7, 10, 11, 12));
	}

	public void setBirthdayOfRod() {
		setBirthdayOfPerson("Flanders, Rod", getDate(2013, Calendar.OCTOBER, 1, 10, 11, 12));
	}

	public void setBirthdayOfFatherBart() {
		List<Person> barts = getAllFromRegister("Simpson, Bart");
		Date defaultDate = (Date) EcoreFactory.eINSTANCE.createFromString(//
				EcorePackage.eINSTANCE.getEDate(), "0000-1-1");
		for (Person bart : barts) {
			if (bart.getBirthday().equals(defaultDate)) {
				setBirthdayOfPerson(bart, getDate(2013, Calendar.MARCH, 9, 10, 11, 12));
			}
		}
	}
	
	public void setBirthdayOfLisa() {
		Person lisa = getFromRegister("Simpson, Lisa");
		setBirthdayOfPerson(lisa, getDate(2013, Calendar.MARCH, 8, 10, 11, 12));
	}

	public void setBirthdaysOfSimpson() {
		Person person1 = getFromRegister("Simpson, Homer");
		if (person1 != null) {
			setBirthdayOfPerson(person1, getDate(2013, Calendar.JANUARY, 9, 10, 11, 12));
		}

		Person person2 = getFromRegister("Simpson, Marge");

		if (person2 != null) {
			setBirthdayOfPerson(person2, getDate(2013, Calendar.FEBRUARY, 9, 10, 11, 12));
		}

		List<Person> barts = getAllFromRegister("Simpson, Bart");

		Date defaultDate = (Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(),
				"0000-1-1");
		for (Person p : barts) {
			if (p.getBirthday().equals(defaultDate)) {
				setBirthdayOfPerson(p, getDate(2013, Calendar.MARCH, 10, 10, 11, 12));
			}
		}

		Person person4 = getFromRegister("Simpson, Lisa");

		if (person4 != null) {
			setBirthdayOfPerson(person4, getDate(2013, Calendar.MARCH, 8, 10, 11, 12));
		}

		Person person5 = getFromRegister("Simpson, Maggie");

		if (person5 != null) {
			setBirthdayOfPerson(person5, getDate(2013, Calendar.MARCH, 7, 10, 11, 12));
		}
	}

	public void setBirthdayOfYoungerBart() {
		List<Person> barts = getAllFromRegister("Simpson, Bart");
		Date defaultDate = (Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(),
				"0000-1-1");
		for (Person p : barts) {
			if (p.getBirthday().equals(defaultDate)) {
				setBirthdayOfPerson(p, getDate(2013, Calendar.MARCH, 11, 10, 11, 12));
			}
		}
	}

	private void setNameOfPerson(Person p, String name) {
		String oldName = p.getName();
		p.setName(name);
		changeAttribute.accept(//
				PersonsSmartEMFPackage.Literals.PERSON__NAME, //
				List.of(p, oldName, name));
	}

	public void firstNameChangeOfHomer() {
		Person person = getFromRegister("Simpson, Homer");
		setNameOfPerson(person, "Simpson, HomerX");
	}

	@SuppressWarnings("deprecation")
	public void firstNameChangeOfBart() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.MARCH, 10, 10, 11, 12);
		Date date = cal.getTime();

		List<Person> barts = getAllFromRegister("Simpson, Bart");
		for (Person bart : barts) {
			if (bart.getBirthday().getDay() == date.getDay())
				setNameOfPerson(bart, "Simpson, Bartholomew");
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
				setNameOfPerson(bart, "Skinner, Seymour");
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
				setNameOfPerson(bart, "Flanders, Todd");
		}
	}

	public void familyNameChangeOfLisa() {
		Person person = getFromRegister("Simpson, Lisa");
		setNameOfPerson(person, "Flanders, Lisa");
	}
	
	public void familyNameChangeOfLisaToVanHouten() {
		Person person = getFromRegister("Simpson, Lisa");
		setNameOfPerson(person, "Van Houten, Lisa");
	}
	
	public void nameChangeOfLisa() {
		Person person = getFromRegister("Simpson, Lisa");
		setNameOfPerson(person, "Simpson, Marie");
	}

	public void fullNameChangeOfMarge() {
		Person person = getFromRegister("Simpson, Marge");
		setNameOfPerson(person, "Flanders, Maude");
	}

	private void deletePersonFromRegister(Person person) {
		deleteEdge.accept(PersonsSmartEMFPackage.Literals.PERSON_REGISTER__PERSONS, List.of(register.get(), person));
		deleteNode.accept(person);
		SmartEMFUtil.deleteNode(person, false);
	}

	public void deleteMarge() {
		Person person = getFromRegister("Simpson, Marge");
		deletePersonFromRegister(person);
	}

	public void deleteHomer() {
		Person person = getFromRegister("Simpson, Homer");
		deletePersonFromRegister(person);
	}

	public void deleteMaggie() {
		Person person = getFromRegister("Simpson, Maggie");
		deletePersonFromRegister(person);
	}
	
	public void deleteLisa() {
		Person person = getFromRegister("Simpson, Lisa");
		deletePersonFromRegister(person);
	}
	

	public void idleDelta() {

	}

	public void hippocraticDelta() {
		// change birthday for each person
		for (Person p : register.get().getPersons()) {
			setBirthdayOfPerson(p, getDate(2013, Calendar.MARCH, 9, 10, 11, 12));
		}
	}

	public void createPersons(int nrOfFamilies, int familySize) {
		for (int i = 0; i < nrOfFamilies; i++) {
			for (int j = 0; j < familySize; j++) {
				if (Math.random() < 0.5)
					builder.female("Doe_" + i + ", Member_" + j);
				else
					builder.male("Doe_" + i + ", Member_" + j);
			}
		}
	}

	public void createOnePerson() {
		builder.female("Doe_0, Jane");
	}
}
