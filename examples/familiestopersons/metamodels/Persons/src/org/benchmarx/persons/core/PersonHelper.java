package org.benchmarx.persons.core;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

import Persons.Person;
import Persons.PersonRegister;

public class PersonHelper {
	
	private PersonRegisterBuilder builder = null;
	
	private Person getFromRegister(String name, PersonRegister register) {
		Optional<Person> person = register.getPersons().stream()
				.filter(p -> p.getName().equals(name))
				.findAny();
				
		assertTrue(person.isPresent());
		return person.get();
	}
	
	private List<Person> getAllFromRegister(String name, PersonRegister register) {
		List<Person> result = register.getPersons().stream()
				.filter(p -> p.getName().equals(name))
				.collect(Collectors.toList());
		
		return result;
	}
	
	public void createRod(PersonRegister register) {
		builder = new PersonRegisterBuilder(register);
		builder.male("Flanders, Rod");
	}
	
	public void createHomer(PersonRegister register) {
		builder = new PersonRegisterBuilder(register);
		builder.male("Simpson, Homer");
	}
	
	public void createBart(PersonRegister register) {
		builder = new PersonRegisterBuilder(register);
		builder.male("Simpson, Bart");
	}
	
	public void createMarge(PersonRegister register) {
		builder = new PersonRegisterBuilder(register);
		builder.female("Simpson, Marge");
	}
	
	public void createLisa(PersonRegister register) {
		builder = new PersonRegisterBuilder(register);
		builder.female("Simpson, Lisa");
	}
	
	public void createMaggie(PersonRegister register) {
		builder = new PersonRegisterBuilder(register);
		builder.female("Simpson, Maggie");
	}
	
	public void changeAllBirthdays(PersonRegister register) {
//		int i = 1;
//		for (Person p : register.getPersons()) {
//			Calendar cal = Calendar.getInstance();
//			cal.set(2000, Calendar.JANUARY, i, 12, 13, 14);
//			Date date = cal.getTime();
//			p.setBirthday(date);
//			i++;
//		}
		setBirthdaysOfSimpson(register);
		setBirthdayOfRod(register);
	}
	
	
	public void birthdayChangeOfHomer(PersonRegister register) {
		Person person = getFromRegister("Simpson, Homer", register);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.JANUARY, 9, 10, 11, 12); 
		Date date = cal.getTime();
		person.setBirthday(date);
	}
	
	public void setBirthdayOfRod(PersonRegister register) {
		Calendar cal = Calendar.getInstance();
		Date date;
		
		Person person1 = getFromRegister("Flanders, Rod", register);
		
		cal.set(2013, Calendar.OCTOBER, 1, 10, 11, 12);
		date = cal.getTime();
		person1.setBirthday(date);
	}
	
	public void setBirthdayOfFatherBart(PersonRegister register) {
		Calendar cal = Calendar.getInstance();
		Date date;
		
		Person bart = getFromRegister("Simpson, Bart", register);
		
		cal.set(2013, Calendar.MARCH, 9, 10, 11, 12);
		date = cal.getTime();
		bart.setBirthday(date);
	}
	
	public void setBirthdaysOfSimpson(PersonRegister register) {
		Calendar cal = Calendar.getInstance();
		Date date;
		
		Person person1 = getFromRegister("Simpson, Homer", register);
		
		cal.set(2013, Calendar.JANUARY, 9, 10, 11, 12); 
		date = cal.getTime();
		person1.setBirthday(date);
		
		Person person2 = getFromRegister("Simpson, Marge", register);
		
		cal.set(2013, Calendar.FEBRUARY, 9, 10, 11, 12); 
		date = cal.getTime();
		person2.setBirthday(date);
		
		/*
		Person person3 = getFromRegister("Simpson, Bart", register);
		
		cal.set(2013, Calendar.MARCH, 9, 10, 11, 12); 
		date = cal.getTime();
		person3.setBirthday(date);
		*/
		List<Person> barts = getAllFromRegister("Simpson, Bart", register);
		int i = 10;
		Date defaultDate = (Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(), "0000-1-1");
		for (Person p : barts) {
			if (p.getBirthday().equals(defaultDate)) {
				cal.set(2013, Calendar.MARCH, 10, 10, 11, 12); 
				date = cal.getTime();
				p.setBirthday(date);
			}
		}
		
		Person person4 = getFromRegister("Simpson, Lisa", register);
		
		cal.set(2013, Calendar.MARCH, 8, 10, 11, 12); 
		date = cal.getTime();
		person4.setBirthday(date);
		
		Person person5 = getFromRegister("Simpson, Maggie", register);
		
		cal.set(2013, Calendar.MARCH, 7, 10, 11, 12); 
		date = cal.getTime();
		person5.setBirthday(date);
	}
	
	public void setBirthdayOfYoungerBart(PersonRegister register) {
		Calendar cal = Calendar.getInstance();
		Date date;
		List<Person> barts = getAllFromRegister("Simpson, Bart", register);	
		Date defaultDate = (Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(), "0000-1-1");
		for (Person p : barts) {
			if (p.getBirthday().equals(defaultDate)) {
				cal.set(2013, Calendar.MARCH, 11, 10, 11, 12); 
				date = cal.getTime();
				p.setBirthday(date);
			}
		}
	}
	
	public void firstNameChangeOfHomer(PersonRegister register) {
		Person person = getFromRegister("Simpson, Homer", register);
		person.setName("Simpson, HomerX");
	}
	
	public void firstNameChangeOfBart(PersonRegister register) {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.JANUARY, 9, 10, 11, 12); 
		Date date = cal.getTime();
		
		for(int i =0;i<register.getPersons().size();i++)
		{
				if(register.getPersons().get(i).getBirthday().toString().equals(date.toString()))
				{
					register.getPersons().get(i).setName("Simpson, BartX");
				}
		}
		
	}

	public void familyNameChangeOfLisa(PersonRegister register) {
		Person person = getFromRegister("Simpson, Lisa", register);
		person.setName("Jetson, Lisa");
	}
	
	public void familyNameChangeOfShweta(PersonRegister register) {
		Person person = getFromRegister("Bachchan, Shweta", register);
		person.setName("Nanda, Shweta");
	}
	
	public void fullNameChangeOfHomer(PersonRegister register) {
		Person person = getFromRegister("Simpson, Homer", register);
		person.setName("Jetson, Elroy");
	}
	
	public void fullNameChangeOfBart(PersonRegister register) {
		Person person = getFromRegister("Simpson, Bart", register);
		person.setName("Orbit, Henry");
	}
	
	public void fullNameChangeOfOtherBart(PersonRegister register) {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, Calendar.JANUARY, 9, 10, 11, 12);
		Date date = cal.getTime();

		for (int i = 0; i < register.getPersons().size(); i++) {
			if (register.getPersons().get(i).getBirthday().toString().equals(date.toString())) {
				register.getPersons().get(i).setName("Orbit, Henry");
			}
		}
	}
	
	public void deleteMarge(PersonRegister register) {
		Person person = getFromRegister("Simpson, Marge", register);
		EcoreUtil.delete(person);
	}
	
	public void deleteHomer(PersonRegister register) {
		Person person = getFromRegister("Simpson, Homer", register);
		EcoreUtil.delete(person);
	}			
	
	public void familyNameChangeShweta(PersonRegister register) {
		Person person = getFromRegister("Bachchan, Shweta", register);
		person.setName("Nanda, Shweta");
	}


}
