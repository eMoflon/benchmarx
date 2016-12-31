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
	private static final int NO_OF_FAMILIES = 20000;
	private static final int NO_OF_PERSONS  = 60000;
	
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
	
	public static void main(String[] args) {
		BatchScalability scalabilityTest = new BatchScalability();
		
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer1 = new BXToolTimer<>(new EMoflonFamiliesToPersons());
		System.out.print(timer1.timeSourceEditFromScratchInS(scalabilityTest::createFamiliesWithMembers) + "s;");
		System.out.println(timer1.timeTargetEditFromScratchInS(scalabilityTest::createPersons) + "s;");
		
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer2 = new BXToolTimer<>(new BiGULFamiliesToPersons());
		System.out.print(timer2.timeSourceEditFromScratchInS(scalabilityTest::createFamiliesWithMembers) + "s;");
		System.out.println(timer2.timeTargetEditFromScratchInS(scalabilityTest::createPersons) + "s;");
		
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer3 = new BXToolTimer<>(new MediniQVTFamiliesToPersons());
		System.out.print(timer3.timeSourceEditFromScratchInS(scalabilityTest::createFamiliesWithMembers) + "s;");
		System.out.println(timer3.timeTargetEditFromScratchInS(scalabilityTest::createPersons) + "s;");
	}
}
