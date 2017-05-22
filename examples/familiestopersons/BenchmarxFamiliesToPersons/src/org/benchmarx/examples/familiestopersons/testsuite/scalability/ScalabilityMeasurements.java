package org.benchmarx.examples.familiestopersons.testsuite.scalability;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.implementations.bigul.BiGULFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.bxtend.UbtXtendFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.emoflon.EMoflonFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.funnyqt.FunnyQTFamiliesToPerson;
import org.benchmarx.examples.familiestopersons.implementations.medini.MediniQVTFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.SDMLibFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.IndiHelper;
import org.benchmarx.util.BXToolTimer;

import Families.FamiliesFactory;
import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;
import Persons.Person;
import Persons.PersonRegister;
import Persons.PersonsFactory;

public class ScalabilityMeasurements {
	private static final BXTool<Object, Object, Decisions> tool1 = new SDMLibFamiliesToPersons();
	private static final BXTool<FamilyRegister, PersonRegister, Decisions> tool2 = new EMoflonFamiliesToPersons();
	// private static final BXTool<FamilyRegister, PersonRegister, Decisions> tool3 = new MediniQVTFamiliesToPersons();
	private static final BXTool<FamilyRegister, PersonRegister, Decisions> tool3 = new UbtXtendFamiliesToPersons();
   private static final BXTool<FamilyRegister, PersonRegister, Decisions> tool4 = new UbtXtendFamiliesToPersons();
	
	private static final String DELIMITER = ",";
	private static final String UNIT = "";
	
	private final int NO_OF_FAMILIES;
	private final int NO_OF_CHILDREN;
	private final int NO_OF_ELEMENTS;
	
	private BXToolTimer<Object, Object, Decisions> timer1;
	private BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer2;
	private BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer3;
	private BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer4;
	
	private IndiHelper indiHelper = new IndiHelper();
	
	public ScalabilityMeasurements(int numberOfFamilies, int noOfChildren, int repeat) {
		this.NO_OF_FAMILIES = numberOfFamilies;
		this.NO_OF_CHILDREN = noOfChildren;
		int allFamilyMembers = 2 /*parents*/ + NO_OF_CHILDREN; 
		this.NO_OF_ELEMENTS = 1 /* family register*/ 
							+ 2*NO_OF_FAMILIES /* families and assoc to register */ 
							+ NO_OF_FAMILIES * 2*allFamilyMembers /* family member and connection to family */
							+ 1 /* person register */
							+ NO_OF_FAMILIES * 2*allFamilyMembers; /* person and connection to register */
		
		timer1 = new BXToolTimer<>(tool1, repeat);
		timer2 = new BXToolTimer<>(tool2, repeat);
		timer3 = new BXToolTimer<>(tool3, repeat);;
		timer4 = new BXToolTimer<>(tool4, repeat);
	}
	
	public void createPersons(Object obj){
	   if (obj instanceof PersonRegister)
      {
	      PersonRegister register = (PersonRegister) obj;
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
      }
	   else 
	   {
	      indiHelper.createPersons(obj, NO_OF_FAMILIES, NO_OF_CHILDREN);
	   }
	}
	
	public void createFamiliesWithMembers(Object obj){
	   if (obj instanceof FamilyRegister)
      {
         FamilyRegister register = (FamilyRegister) obj;
         for (int i = 0; i < NO_OF_FAMILIES; i++)
         {
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

            for (int j = 0; j < NO_OF_CHILDREN; j++)
            {
               FamilyMember child = FamiliesFactory.eINSTANCE.createFamilyMember();
               child.setName("Child_" + j);

               if (Math.random() < 0.5)
                  family.getDaughters().add(child);
               else
                  family.getSons().add(child);
            }
         } 
      }
	   else
	   {  
	      indiHelper.createFamiliesWithMembers(obj, NO_OF_FAMILIES, NO_OF_CHILDREN);
	   }
	}
	
	public void createOnePerson(Object obj){
	   if (obj instanceof PersonRegister)
      {
	      PersonRegister register = (PersonRegister) obj;
	      Person person = PersonsFactory.eINSTANCE.createFemale();
	      person.setName("Doe_" + register.getPersons().size() + ", Jane");
	      register.getPersons().add(person);
      }
	   else
	   {
	      indiHelper.createOnePerson(obj);
	   }
	}
	
	public void createOneFamilyMember(Object obj){
	   if (obj instanceof FamilyRegister)
	   {
	      FamilyRegister register = (FamilyRegister) obj;
	      Family f = register.getFamilies().get(0);
	      FamilyMember familyMember = FamiliesFactory.eINSTANCE.createFamilyMember();
	      familyMember.setName("Johanna");
	      f.getDaughters().add(familyMember);
	   }
	   else
	   {
	      indiHelper.createOneFamilyMember(obj);
	   }
	}
	
	private void runBatchFWDMeasurements(){
		System.out.print(NO_OF_ELEMENTS + DELIMITER);
		System.out.print(timer1.timeSourceEditFromScratchInS(this::createFamiliesWithMembers) + UNIT + DELIMITER);
		System.out.print(timer2.timeSourceEditFromScratchInS(this::createFamiliesWithMembers) + UNIT + DELIMITER);
		System.out.print(timer3.timeSourceEditFromScratchInS(this::createFamiliesWithMembers) + UNIT + DELIMITER);
		System.out.print(timer4.timeSourceEditFromScratchInS(this::createFamiliesWithMembers) + UNIT);
		System.out.println();
	}
	
	private void runBatchBWDMeasurements(){
		System.out.print(NO_OF_ELEMENTS + DELIMITER);
		System.out.print(timer1.timeTargetEditFromScratchInS(this::createPersons) + UNIT + DELIMITER);
		System.out.print(timer2.timeTargetEditFromScratchInS(this::createPersons) + UNIT + DELIMITER);
		System.out.print(timer3.timeTargetEditFromScratchInS(this::createPersons) + UNIT + DELIMITER);
		System.out.print(timer4.timeTargetEditFromScratchInS(this::createPersons) + UNIT + DELIMITER);
		System.out.println();
	}
	
	private void runIncrFWDMeasurements(){
		System.out.print(NO_OF_ELEMENTS + DELIMITER);
		System.out.print(timer1.timeSourceEditAfterSetUpInS(this::createFamiliesWithMembers, this::createOneFamilyMember) + UNIT + DELIMITER);
		System.out.print(timer2.timeSourceEditAfterSetUpInS(this::createFamiliesWithMembers, this::createOneFamilyMember) + UNIT + DELIMITER);
		System.out.print(timer3.timeSourceEditAfterSetUpInS(this::createFamiliesWithMembers, this::createOneFamilyMember) + UNIT + DELIMITER);
		System.out.print(timer4.timeSourceEditAfterSetUpInS(this::createFamiliesWithMembers, this::createOneFamilyMember) + UNIT + DELIMITER);
		System.out.println();
	}
	
	private void runIncrBWDMeasurements(){
		System.out.print(NO_OF_ELEMENTS + DELIMITER);
		System.out.print(timer1.timeTargetEditAfterSetUpInS(this::createPersons, this::createOnePerson) + UNIT + DELIMITER);
		System.out.print(timer2.timeTargetEditAfterSetUpInS(this::createPersons, this::createOnePerson) + UNIT + DELIMITER);
		System.out.print(timer3.timeTargetEditAfterSetUpInS(this::createPersons, this::createOnePerson) + UNIT + DELIMITER);
		System.out.print(timer4.timeTargetEditAfterSetUpInS(this::createPersons, this::createOnePerson) + UNIT + DELIMITER);
		System.out.println();
	}

	private static void runBatchFWDMeasurements(int numOfFamilies, int numOfChildren, int repetitions) {
		new ScalabilityMeasurements(numOfFamilies, numOfChildren, repetitions).runBatchFWDMeasurements();
	}
	
	private static void runBatchBWDMeasurements(int numOfFamilies, int numOfChildren, int repetitions) {
		new ScalabilityMeasurements(numOfFamilies, numOfChildren, repetitions).runBatchBWDMeasurements();
	}
	
	private static void runIncrFWDMeasurements(int numOfFamilies, int numOfChildren, int repetitions) {
		new ScalabilityMeasurements(numOfFamilies, numOfChildren, repetitions).runIncrFWDMeasurements();
	}
	
	private static void runIncrBWDMeasurements(int numOfFamilies, int numOfChildren, int repetitions) {
		new ScalabilityMeasurements(numOfFamilies, numOfChildren, repetitions).runIncrBWDMeasurements();
	}
	
	private static void printHeader(String title) {
		System.out.println("------------------");
		System.out.println(title);
		System.out.println("------------------");
		System.out.println("model size (# of nodes and edges)" + DELIMITER +  tool1.getName() + DELIMITER +  tool2.getName() + DELIMITER +  tool3.getName() + DELIMITER + tool4.getName());
	}
	
	public static void main(String[] args) {
		int max = 400;
		printHeader("Batch FWD:");
		for (int i = 50; i < max/*0*/; i+=50) {			
			runBatchFWDMeasurements(i, 3, 5);
		}
		
		printHeader("Incr. FWD:");
		for (int i = 50; i < max/*00*/; i+=50) {			
			runIncrFWDMeasurements(i, 3, 5);
		}		
		
		printHeader("Batch BWD:");
		for (int i = 50; i < max; i+=50) {			
			runBatchBWDMeasurements(i, 3, 5);
		}	
		
		printHeader("Incr. BWD:");
		for (int i = 50; i < max; i+=50) {			
			runIncrBWDMeasurements(i, 3, 5);
		}
	}
}
