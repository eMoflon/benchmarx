package org.benchmarx.pdb1.core;

public class Pdb1DatabaseBuilder {
	Pdb1DatabaseBuilder(pdb1.Database database) {
		this.database = database;
	}
	
	Pdb1DatabaseBuilder databaseName(String name) {
		database.setName(name);
		return this;
	}
	
	Pdb1DatabaseBuilder addPerson(String id) {
		lastAdded = f.createPerson();
		lastAdded.setDatabase(database);
		lastAdded.setId(id);
		return this;
	}
	
	Pdb1DatabaseBuilder birthday(String birthday) {
		lastAdded.setBirthday(birthday);
		return this;
	}
	
	Pdb1DatabaseBuilder placeOfBirth(String placeOfBirth) {
		lastAdded.setPlaceOfBirth(placeOfBirth);
		return this;
	}
	
	Pdb1DatabaseBuilder firstName(String firstName) {
		lastAdded.setFirstName(firstName);
		return this;
	}
	
	Pdb1DatabaseBuilder lastName(String lastName) {
		lastAdded.setLastName(lastName);
		return this;
	}
	
	private pdb1.Pdb1Factory f = pdb1.Pdb1Factory.eINSTANCE;
	private pdb1.Database database;
	private pdb1.Person lastAdded;
}
