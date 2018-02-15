package org.benchmarx.pdb2.core;

public class Pdb2DatabaseBuilder {
	Pdb2DatabaseBuilder(pdb2.Database database) {
		this.database = database;
	}
	
	Pdb2DatabaseBuilder databaseName(String name) {
		database.setName(name);
		return this;
	}
	
	Pdb2DatabaseBuilder addPerson(String id) {
		lastAdded = f.createPerson();
		lastAdded.setDatabase(database);
		lastAdded.setId(id);
		return this;
	}
	
	Pdb2DatabaseBuilder birthday(String birthday) {
		lastAdded.setBirthday(birthday);
		return this;
	}
	
	Pdb2DatabaseBuilder placeOfBirth(String placeOfBirth) {
		lastAdded.setPlaceOfBirth(placeOfBirth);
		return this;
	}
	
	Pdb2DatabaseBuilder name(String name) {
		lastAdded.setName(name);
		return this;
	}
	
	private pdb2.Pdb2Factory f = pdb2.Pdb2Factory.eINSTANCE;
	private pdb2.Database database;
	private pdb2.Person lastAdded;
}
