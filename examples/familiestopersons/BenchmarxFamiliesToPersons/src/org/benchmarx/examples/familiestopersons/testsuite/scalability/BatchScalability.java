package org.benchmarx.examples.familiestopersons.testsuite.scalability;

import org.benchmarx.BXToolTimer;
import org.benchmarx.examples.familiestopersons.implementations.bigul.BiGULFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.emoflon.EMoflonFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.medini.MediniQVTFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;

import Families.FamiliesFactory;
import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;
import Persons.Person;
import Persons.PersonRegister;
import Persons.PersonsFactory;

public class BatchScalability {
	private final int NO_OF_FAMILIES;
	private final int NO_OF_PERSONS;
	private final int REPEAT;
	
	public BatchScalability(int numberOfFamilies, int numberOfPersons, int repeat) {
		this.NO_OF_FAMILIES = numberOfFamilies;
		this.NO_OF_PERSONS = numberOfPersons;
		this.REPEAT = repeat;
	}
	
	public PersonRegister createPersons(PersonRegister register){
		for(int i = 0; i < NO_OF_PERSONS; i++){
			Person person = PersonsFactory.eINSTANCE.createFemale();
			person.setName("Doe, Jane_" + i);
			register.getPersons().add(person);
		}
		
		return register;
	}
	
	public FamilyRegister createFamiliesWithMembers(FamilyRegister register){
		for (int i = 0; i < NO_OF_FAMILIES; i++) {
			Family family = FamiliesFactory.eINSTANCE.createFamily();
			family.setName("Simpson_" + i);
			register.getFamilies().add(family);

			FamilyMember familyMother = FamiliesFactory.eINSTANCE.createFamilyMember();
			familyMother.setName("Marge");
			family.setMother(familyMother);

			FamilyMember familySon = FamiliesFactory.eINSTANCE.createFamilyMember();
			familySon.setName("Bart");
			family.getSons().add(familySon);

			FamilyMember familyDaughterOne = FamiliesFactory.eINSTANCE.createFamilyMember();
			familyDaughterOne.setName("Lisa");
			family.getDaughters().add(familyDaughterOne);

			FamilyMember familyDaughterTwo = FamiliesFactory.eINSTANCE.createFamilyMember();
			familyDaughterTwo.setName("Maggie");
			family.getDaughters().add(familyDaughterTwo);
		}
		
		return register;
	}
	
	public void runMeasurements() {
		for (int i = 0; i < REPEAT; i++) {
			System.out.println();
			System.out.println("(" + NO_OF_FAMILIES + ", " + NO_OF_PERSONS + "), " + (i+1) + " of " + REPEAT);
			
			BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer1 = new BXToolTimer<>(new EMoflonFamiliesToPersons());
			System.out.print("eMoflon: ");
			System.out.print(timer1.timeSourceEditFromScratchInS(this::createFamiliesWithMembers) + "s;");
			System.out.println(timer1.timeTargetEditFromScratchInS(this::createPersons) + "s;");

			BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer2 = new BXToolTimer<>(new BiGULFamiliesToPersons());
			System.out.print("BiGUL:   ");
			System.out.print(timer2.timeSourceEditFromScratchInS(this::createFamiliesWithMembers) + "s;");
			System.out.println(timer2.timeTargetEditFromScratchInS(this::createPersons) + "s;");

			BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer3 = new BXToolTimer<>(new MediniQVTFamiliesToPersons());
			System.out.print("Medini:  ");
			System.out.print(timer3.timeSourceEditFromScratchInS(this::createFamiliesWithMembers) + "s;");
			System.out.println(timer3.timeTargetEditFromScratchInS(this::createPersons) + "s;");			
		}
	}
	
	public static void main(String[] args) {
		BatchScalability scalabilityTest = new BatchScalability(1000, 1000, 5);
		scalabilityTest.runMeasurements();
		
		scalabilityTest = new BatchScalability(5000, 5000, 5);
		scalabilityTest.runMeasurements();
	}
}
