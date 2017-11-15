package org.benchmarx.pdb1.core;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;

import pdb1.Database;
import pdb1.Person;

public class Pdb1Helper {
	
	private Pdb1DatabaseBuilder builder = null;
	
	public void setDatabaseName(Database database) {
		database.setName("Bundeskanzler");
	}
	
	public void renameKanzlerDatabaseToPräsidenten(Database database) {
		assertTrue(database.getName().equals("Bundeskanzler"));
		database.setName("Bundespräsidenten");
	}
	
	public void createKonradAdenauer(Database database) {
		builder = new Pdb1DatabaseBuilder(database);
		builder.addPerson("KA").firstName("Konrad Hermann Joseph").lastName("Adenauer").birthday("05.01.1876").placeOfBirth("Koeln");
	}
	
	public void createWrongKonradAdenauer(Database database) {
		builder = new Pdb1DatabaseBuilder(database);
		builder.addPerson("KA").firstName("Konrad Hermann").lastName("Joseph Adenauer").birthday("05.01.1876").placeOfBirth("Koeln");
	}
	
	public void createLudwigErhard(Database database) {
		builder = new Pdb1DatabaseBuilder(database);
		builder.addPerson("LE").firstName("Ludwig Wilhelm").lastName("Erhard").birthday("04.02.1897").placeOfBirth("Fuerth");
	}
	
	public void createKurtKiesinger(Database database) {
		builder = new Pdb1DatabaseBuilder(database);
		builder.addPerson("KK").firstName("Kurt Georg").lastName("Kiesinger").birthday("06.04.1904").placeOfBirth("Ebingen");
	}
	
	public void createWillyBrandt(Database database) {
		builder = new Pdb1DatabaseBuilder(database);
		builder.addPerson("WB").firstName("Willy").lastName("Brandt").birthday("18.12.1913").placeOfBirth("Luebeck");
	}
	
	public void createHelmutSchmidt(Database database) {
		builder = new Pdb1DatabaseBuilder(database);
		builder.addPerson("HS").firstName("Helmut Heinrich Waldemar").lastName("Schmidt").birthday("23.12.1918").placeOfBirth("Hamburg");
	}
	
	public void createHelmutKohl(Database database) {
		builder = new Pdb1DatabaseBuilder(database);
		builder.addPerson("HK").firstName("Helmut Josef Michael").lastName("Kohl").birthday("03.04.1930").placeOfBirth("Ludwigshafen am Rhein");
	}
	
	public void createGerhardSchroeder(Database database) {
		builder = new Pdb1DatabaseBuilder(database);
		builder.addPerson("GS").firstName("Gerhard Fritz Kurt").lastName("Schroeder").birthday("07.04.1944").placeOfBirth("Mossenberg-Woehren");
	}
	
	public void createAngelaMerkel(Database database) {
		builder = new Pdb1DatabaseBuilder(database);
		builder.addPerson("AM").firstName("Angela Dorothea").lastName("Merkel").birthday("17.07.1954").placeOfBirth("Hamburg");
	}
	
	public void deleteKurtKiesinger(Database database) {
		EcoreUtil.delete(getKurtKiesinger(database));
	}
	
	public void changeFirstNameOfKonradAdenauer(Database database) {
		getKonradAdenauer(database).setFirstName("Heinz");
	}
	
	public void changeLastNameOfLudwigErhard(Database database) {
		getLudwigErhard(database).setLastName("Meyer");
	}
	
	public void changeBirthdayOfKurtKiesinger(Database database) {
		getKurtKiesinger(database).setBirthday("01.01.1990");
	}
	
	public void changePlaceOfBirthOfWillyBrandt(Database database) {
		getWillyBrandt(database).setPlaceOfBirth("Germany");
	}
	
	public void changeIDOfHelmutSchmidt(Database database) {
		getHelmutSchmidt(database).setId("Helmut");
	}
	
	public void changeAllOfHelmutKohl(Database database) {
		Person helmut = getHelmutKohl(database);
		helmut.setBirthday("01.01.1990");
		helmut.setFirstName("Heinz");
		helmut.setId("Helmut2");
		helmut.setLastName("Meyer");
		helmut.setPlaceOfBirth("Germany");
	}
	
	public void changeIncrementalIDs(Database database) {
		database.getPersons().stream().forEach(p -> p.setIncrementalID("incrTestValue"));
	}
	
	public void idleDelta(Database database) {
		
	}
	
	public void hippocraticDelta(Database database) {
		Person k = getWrongKonradAdenauer(database);
		k.setFirstName("Konrad Hermann Joseph");
		k.setLastName("Adenauer");
	}
	
	private Person getWrongKonradAdenauer(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("KA")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getFirstName().equals("Konrad Hermann"));
		assertTrue(konrad.getLastName().equals("Joseph Adenauer"));
		return konrad;		
	}
	
	private Person getKonradAdenauer(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("KA")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getFirstName().equals("Konrad Hermann Joseph"));
		assertTrue(konrad.getLastName().equals("Adenauer"));
		return konrad;		
	}
	
	private Person getKurtKiesinger(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("KK")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getFirstName().equals("Kurt Georg"));
		assertTrue(konrad.getLastName().equals("Kiesinger"));
		return konrad;		
	}
	
	private Person getLudwigErhard(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("LE")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getFirstName().equals("Ludwig Wilhelm"));
		assertTrue(konrad.getLastName().equals("Erhard"));
		return konrad;		
	}
	
	private Person getWillyBrandt(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("WB")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getFirstName().equals("Willy"));
		assertTrue(konrad.getLastName().equals("Brandt"));
		return konrad;		
	}
	
	private Person getHelmutSchmidt(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("HS")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getFirstName().equals("Helmut Heinrich Waldemar"));
		assertTrue(konrad.getLastName().equals("Schmidt"));
		return konrad;		
	}
	
	private Person getHelmutKohl(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("HK")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getFirstName().equals("Helmut Josef Michael"));
		assertTrue(konrad.getLastName().equals("Kohl"));
		return konrad;		
	}
}
