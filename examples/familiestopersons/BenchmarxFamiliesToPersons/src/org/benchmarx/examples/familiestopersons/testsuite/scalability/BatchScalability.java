package org.benchmarx.examples.familiestopersons.testsuite.scalability;

import org.benchmarx.BXTool;
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
	private static final BXTool<FamilyRegister, PersonRegister, Decisions> tool1 = new BiGULFamiliesToPersons();
	private static final BXTool<FamilyRegister, PersonRegister, Decisions> tool2 = new EMoflonFamiliesToPersons();
	private static final BXTool<FamilyRegister, PersonRegister, Decisions> tool3 = new MediniQVTFamiliesToPersons();
	
	private final int NO_OF_FAMILIES;
	private final int NO_OF_CHILDREN;
	private final int NO_OF_ELEMENTS;
	
	private BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer1;
	private BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer2;
	private BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer3;
	
	public BatchScalability(int numberOfFamilies, int noOfChildren, int repeat) {
		this.NO_OF_FAMILIES = numberOfFamilies;
		this.NO_OF_CHILDREN = noOfChildren;
		this.NO_OF_ELEMENTS = 1 + 2*NO_OF_FAMILIES + NO_OF_FAMILIES * 2*(NO_OF_CHILDREN + 2);
		
		timer1 = new BXToolTimer<>(tool1, repeat);
		timer2 = new BXToolTimer<>(tool2, repeat);
		timer3 = new BXToolTimer<>(tool3, repeat);;
	}
	
	public PersonRegister createPersons(PersonRegister register){
		for(int i = 0; i < NO_OF_FAMILIES; i++){
			Person mother = PersonsFactory.eINSTANCE.createFemale();
			mother.setName("Doe_" + i + ", Jane");
			register.getPersons().add(mother);
			Person father = PersonsFactory.eINSTANCE.createMale();
			father.setName("Doe_" + i + ", John");
			register.getPersons().add(father);
			
			for (int j = 0; j < NO_OF_CHILDREN; j++) {
				Person person = Math.random() < 0.5 ? PersonsFactory.eINSTANCE.createFemale() : PersonsFactory.eINSTANCE.createMale();
				person.setName("Doe_" + i + ", Child_" + j);
				register.getPersons().add(person);
			}
		}
		
		return register;
	}
	
	public FamilyRegister createFamiliesWithMembers(FamilyRegister register){
		for (int i = 0; i < NO_OF_FAMILIES; i++) {
			Family family = FamiliesFactory.eINSTANCE.createFamily();
			family.setName("Doe_" + i);
			register.getFamilies().add(family);

			{
				FamilyMember familyMother = FamiliesFactory.eINSTANCE.createFamilyMember();
				familyMother.setName("Jane");
				family.setMother(familyMother);
			}
			
			{
				FamilyMember familyFather = FamiliesFactory.eINSTANCE.createFamilyMember();
				familyFather.setName("John");
				family.setFather(familyFather);
			}
			
			for (int j = 0; j < NO_OF_CHILDREN; j++) {
				FamilyMember child = FamiliesFactory.eINSTANCE.createFamilyMember();
				child.setName("Child_" + j);
				
				if(Math.random() < 0.5)
					family.getDaughters().add(child);	
				else
					family.getSons().add(child);
			}
		}
		
		return register;
	}
	
	private void runFWDMeasurements(){
		System.out.print(NO_OF_ELEMENTS + ";");
		System.out.print(timer1.timeSourceEditFromScratchInS(this::createFamiliesWithMembers) + "s;");
		System.out.print(timer2.timeSourceEditFromScratchInS(this::createFamiliesWithMembers) + "s;");
		System.out.print(timer3.timeSourceEditFromScratchInS(this::createFamiliesWithMembers) + "s");
		System.out.println();
	}
	
	private void runBWDMeasurements(){
		System.out.print(NO_OF_ELEMENTS + ";");
		System.out.print(timer1.timeTargetEditFromScratchInS(this::createPersons) + "s;");
		System.out.print(timer2.timeTargetEditFromScratchInS(this::createPersons) + "s;");
		System.out.print(timer3.timeTargetEditFromScratchInS(this::createPersons) + "s");
		System.out.println();
	}

	private static void runFWDMeasurements(int numOfFamilies, int numOfChildren, int repetitions) {
		new BatchScalability(numOfFamilies, numOfChildren, repetitions).runFWDMeasurements();
	}
	
	private static void runBWDMeasurements(int numOfFamilies, int numOfChildren, int repetitions) {
		new BatchScalability(numOfFamilies, numOfChildren, repetitions).runBWDMeasurements();
	}
	
	private static void printHeader() {
		System.out.println("model size (# of nodes and edges);" +  tool1.getName() + ";" +  tool2.getName() + ";" +  tool3.getName());
	}
	
	public static void main(String[] args) {
		printHeader();
		runFWDMeasurements(100, 3, 21);
		runFWDMeasurements(500, 3, 5);
		runFWDMeasurements(1000, 3, 3);
		runFWDMeasurements(5000, 3, 1);
		
		printHeader();
		runBWDMeasurements(100, 3, 21);
		runBWDMeasurements(500, 3, 3);
		runBWDMeasurements(1000, 3, 1);
	}
}
