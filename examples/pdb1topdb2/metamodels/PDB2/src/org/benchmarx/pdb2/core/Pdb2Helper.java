package org.benchmarx.pdb2.core;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;

import pdb2.Database;
import pdb2.Person;

public class Pdb2Helper {
	
	private Pdb2DatabaseBuilder builder = null;
	
	public void setDatabaseName(Database database) {
		database.setName("Bundeskanzler");
	}
	
	public void renameKanzlerDatabaseToPräsidenten(Database database) {
		assertTrue(database.getName().equals("Bundeskanzler"));
		database.setName("Bundespräsidenten");
	}
	
	public void createKonradAdenauer(Database database) {
		builder = new Pdb2DatabaseBuilder(database);
		builder.addPerson("KA").name("Konrad Hermann Joseph Adenauer").birthday("05.01.1876").placeOfBirth("Koeln");
	}
	
	public void createLudwigErhard(Database database) {
		builder = new Pdb2DatabaseBuilder(database);
		builder.addPerson("LE").name("Ludwig Wilhelm Erhard").birthday("04.02.1897").placeOfBirth("Fuerth");
	}
	
	public void createKurtKiesinger(Database database) {
		builder = new Pdb2DatabaseBuilder(database);
		builder.addPerson("KK").name("Kurt Georg Kiesinger").birthday("06.04.1904").placeOfBirth("Ebingen");
	}
	
	public void createWillyBrandt(Database database) {
		builder = new Pdb2DatabaseBuilder(database);
		builder.addPerson("WB").name("Willy Brandt").birthday("18.12.1913").placeOfBirth("Luebeck");
	}
	
	public void createHelmutSchmidt(Database database) {
		builder = new Pdb2DatabaseBuilder(database);
		builder.addPerson("HS").name("Helmut Heinrich Waldemar Schmidt").birthday("23.12.1918").placeOfBirth("Hamburg");
	}
	
	public void createHelmutKohl(Database database) {
		builder = new Pdb2DatabaseBuilder(database);
		builder.addPerson("HK").name("Helmut Josef Michael Kohl").birthday("03.04.1930").placeOfBirth("Ludwigshafen am Rhein");
	}
	
	public void createGerhardSchroeder(Database database) {
		builder = new Pdb2DatabaseBuilder(database);
		builder.addPerson("GS").name("Gerhard Fritz Kurt Schroeder").birthday("07.04.1944").placeOfBirth("Mossenberg-Woehren");
	}
	
	public void createAngelaMerkel(Database database) {
		builder = new Pdb2DatabaseBuilder(database);
		builder.addPerson("AM").name("Angela Dorothea Merkel").birthday("17.07.1954").placeOfBirth("Hamburg");
	}
	
	public void deleteKurtKiesinger(Database database) {
		EcoreUtil.delete(getKurtKiesinger(database));
	}
	
	public void changeFirstNameOfKonradAdenauer(Database database) {
		getKonradAdenauer(database).setName("Heinz Jochen Adenauer");
	}
	
	public void changeLastNameOfLudwigErhard(Database database) {
		getLudwigErhard(database).setName("Ludwig Wilhelm Meyer");
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
		helmut.setId("Helmut2");
		helmut.setName("Heinz Meyer");
		helmut.setPlaceOfBirth("Germany");
	}
	
	public void changeIncrementalIDs(Database database) {
		database.getPersons().stream().forEach(p -> p.setIncrementalID("incrTestValue"));
	}
	
	public void idleDelta(Database database) {
		
	}
	
	public void hippocraticDelta(Database database) {
		
	}
	
	private Person getKonradAdenauer(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("KA")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getName().equals("Konrad Hermann Joseph Adenauer"));
		return konrad;		
	}
	
	private Person getKurtKiesinger(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("KK")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getName().equals("Kurt Georg Kiesinger"));
		return konrad;		
	}
	
	private Person getLudwigErhard(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("LE")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getName().equals("Ludwig Wilhelm Erhard"));
		return konrad;		
	}
	
	private Person getWillyBrandt(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("WB")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getName().equals("Willy Brandt"));
		return konrad;		
	}
	
	private Person getHelmutSchmidt(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("HS")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getName().equals("Helmut Heinrich Waldemar Schmidt"));
		return konrad;		
	}
	
	private Person getHelmutKohl(Database database) {
		Optional<Person> ka = database.getPersons().stream().filter(p -> p.getId().equals("HK")).findAny();
		
		assertTrue(ka.isPresent());
		Person konrad = ka.get();
		assertTrue(konrad.getName().equals("Helmut Josef Michael Kohl"));
		return konrad;		
	}
}
