package org.benchmarx.examples.familiestopersons.testsuite.performance;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.benchmarx.BXTool;
import org.benchmarx.edit.Edit;
import org.benchmarx.edit.IEdit;
import org.benchmarx.examples.familiestopersons.implementations.bxtend.UbtXtendFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.emoflon.EMoflonFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.medini.MediniQVTFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.nmf.NMFFamiliesToPersonsIncremental;
import org.benchmarx.examples.familiestopersons.implementations.nmf.NMFFamiliesToPersonsTimer;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamilyHelper;
import org.benchmarx.persons.core.PersonHelper;
import org.benchmarx.util.BXToolTimer;

import Families.FamiliesFactory;
import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;
import Persons.Person;
import Persons.PersonRegister;
import Persons.PersonsFactory;
import util.PerformanceResultImpl;
import util.PerformanceTestSuite;


/**
* Class for performance measurements of FamiliesToPersons.
* @author tb
*/
public class ScalabilityMeasurements implements PerformanceTestSuite {
	
	private static final Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> tools = setUpDefaultTools();

	private int numFamilies;
	private int numChildren;
	private int numElements;
	
	protected FamilyHelper helperFamily;
	protected PersonHelper helperPerson;
	protected IEdit<FamilyRegister> sourceEdit;
	protected IEdit<PersonRegister> targetEdit;
	
	protected PersonRegister personRegister;
	protected FamilyRegister familyRegister;
	
	
	/**
	* Run performance measurements with this main method. Prints results to standard out.
	* 
	* @param args	ignored
	*/
	public static void main(String[] args) {			
		ScalabilityMeasurements sm = new ScalabilityMeasurements();		
				
		int start = 1102;
		int increment = 22000;
		int stop =  1013103;

		final int columnWidth = 20;
        String format = "%-" + columnWidth + "s";
		
		printHeader("Batch FWD:", format);
		for (int i = start; i < stop; i += increment) {		
			sm.printRow(i, Test.BatchFWD, format);
		}
		
		printHeader("Batch BWD:", format);
		for (int i = start; i < stop; i += increment) {		
			sm.printRow(i, Test.BatchBWD, format);
		}
	
		printHeader("Incr. FWD:", format);
		for (int i = start; i < stop; i += increment) {		
			sm.printRow(i, Test.IncrFWD, format);
		}
		
		printHeader("Incr. BWD:", format);
		for (int i = start; i < stop; i += increment) {		
			sm.printRow(i, Test.IncrBWD, format);
		}
		
	}
	
	/**
	* Set up the tools to use for measurements. 
	* 
	* @return collection of tools
	*/
	private static Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> setUpDefaultTools() {
		LinkedList<BXTool<FamilyRegister, PersonRegister, Decisions>> tools 
			= new LinkedList<BXTool<FamilyRegister, PersonRegister, Decisions>>();
		
		//tools.add(new BiGULFamiliesToPersons());
		//tools.add(new FunnyQTFamiliesToPerson());
		
		tools.add(new EMoflonFamiliesToPersons());
		tools.add(new MediniQVTFamiliesToPersons());
		tools.add(new UbtXtendFamiliesToPersons());
		tools.add(new NMFFamiliesToPersonsIncremental());
				
		return tools;
	}
	
	public void createPersons(){
		for(int i = 0; i < numFamilies; i++){
			Person mother = PersonsFactory.eINSTANCE.createFemale();
			mother.setName("Doe_" + i + ", Jane");
			personRegister.getPersons().add(mother);
			Person father = PersonsFactory.eINSTANCE.createMale();
			father.setName("Doe_" + i + ", John");
			personRegister.getPersons().add(father);
			
			for (int j = 0; j < numChildren; j++) {
				Person person = Math.random() < 0.5 ? PersonsFactory.eINSTANCE.createFemale() : PersonsFactory.eINSTANCE.createMale();
				person.setName("Doe_" + i + ", Child_" + j);
				personRegister.getPersons().add(person);
			}
		}		
	}
	
	public void createFamiliesWithMembers(){
		for (int i = 0; i < numFamilies; i++) {
			Family family = FamiliesFactory.eINSTANCE.createFamily();
			family.setName("Doe_" + i);
			familyRegister.getFamilies().add(family);

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
			
			for (int j = 0; j < numChildren; j++) {
				FamilyMember child = FamiliesFactory.eINSTANCE.createFamilyMember();
				child.setName("Child_" + j);
				
				if(Math.random() < 0.5)
					family.getDaughters().add(child);	
				else
					family.getSons().add(child);
			}
		}		
	}
	
	public void createOnePerson(){
		Person person = PersonsFactory.eINSTANCE.createFemale();
		person.setName("Doe_" + personRegister.getPersons().size() + ", Jane");
		personRegister.getPersons().add(person);
	}
	
	public void createOneFamilyMember(){
		Family f = familyRegister.getFamilies().get(0);
		FamilyMember familyMember = FamiliesFactory.eINSTANCE.createFamilyMember();
		familyMember.setName("Johanna");
		f.getDaughters().add(familyMember);
	}
	
	/**
	* Get supported tests of this suite.
	* 
	* @return collection of supported test names
	*/
	public Collection<String> getTests() {
		return Stream.of(Test.values()).map(Test::name).collect(Collectors.toList());
	}
	
	/**
	* Get supported tools for a specific test.
	* 
	* @param test	name of test
	* @return 		collection of tools which support the test
	*/
	public Collection<String> getSupportedTools(String test) {
		// Every test is supported here by every tool in this suite -> test parameter irrelevant here
		LinkedList<String> supportedTools = new LinkedList<String>();
		for (BXTool<FamilyRegister, PersonRegister, Decisions> tool: tools) {
			supportedTools.add(tool.getName());
		}
		return supportedTools;
	}

	/**
	* Do a performance measurement for a specific model size, tool and test
	* 
	* @param testName	name of test
	* @param tool		name of tool
	* @param size		model size
	* @return 			result of measurement or null if something went wrong
	*/
	public PerformanceResult measure(String testName, String tool, int size) {
		// Get corresponding enum object for name
		Test test;
		try {
			test = Test.valueOf(testName);
		} catch (IllegalArgumentException e) {
			System.out.print("Unknown test: ");
			System.out.println(testName);
			return null;
		}
		
		return measure(test, tool, size);
	}
	
	/**
	* Do a performance measurement for a specific model size, tool and test
	* 
	* @param testName	test
	* @param tool		name of tool
	* @param size		model size
	* @return 			result of measurement or null if something went wrong
	*/
	private PerformanceResult measure(Test test, String toolName, int size) {
		this.numChildren = 3;
		int allFamilyMembers = 2 /*parents*/ + numChildren; 
		this.numFamilies = (size - 2) / (2 + 4 * allFamilyMembers);
		if (this.numFamilies < 1) this.numFamilies = 1;
		this.numElements = 1 /* family register*/ 
							+ 2*numFamilies /* families and assoc to register */ 
							+ numFamilies * 2*allFamilyMembers /* family member and connection to family */
							+ 1 /* person register */
							+ numFamilies * 2*allFamilyMembers; /* person and connection to register */
		
		// Find corresponding tool
		BXTool<FamilyRegister, PersonRegister, Decisions> tool = null;
		for (BXTool<FamilyRegister, PersonRegister, Decisions> possibleTool: tools) {
			if (possibleTool.getName().equals(toolName)) {
				tool = possibleTool;
				break;
			}
		}
		if (tool == null) {
			System.out.print("Tool not found. Name: ");
			System.out.println(toolName);
			return null;
		}
		
		// Create timer
		BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer;
		// The following if-clause is only there because NMFFamiliesToPersonsIncremental
		// doesn't use BXToolTimer like the other tools.
		if (tool instanceof NMFFamiliesToPersonsIncremental) {
			timer = new NMFFamiliesToPersonsTimer((NMFFamiliesToPersonsIncremental) tool, 1);
		} else {
			timer = new BXToolTimer<FamilyRegister, PersonRegister, Decisions>(tool, 1);
		}
		
		familyRegister = tool.getSourceModel();
		personRegister = tool.getTargetModel();
		
		// Measure time
		Double time;
		switch (test) {
			case BatchFWD:
				time = measureBatchFWD(timer);
				break;
			case BatchBWD:
				time = measureBatchBWD(timer);
				break;
			case IncrFWD:
				time = measureIncrFWD(timer);
				break;
			case IncrBWD:
				time = measureIncrBWD(timer);
				break;
			default:
				System.out.println("Unknown test: " + test.toString());
				return null;
		}
		
		return new PerformanceResultImpl(numElements, time);
	}
	
	private double measureBatchFWD(BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer) {
		return timer.timeSourceEditFromScratchInS(srcEdit(this::createFamiliesWithMembers));
	}
	
	private double measureBatchBWD(BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer) {
		return timer.timeTargetEditFromScratchInS(trgEdit(this::createPersons));
	}
	
	private double measureIncrFWD(BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer) {
		return timer.timeSourceEditAfterSetUpInS(srcEdit(this::createFamiliesWithMembers), srcEdit(this::createOneFamilyMember));
	}
	
	private double measureIncrBWD(BXToolTimer<FamilyRegister, PersonRegister, Decisions> timer) {
		return timer.timeTargetEditAfterSetUpInS(trgEdit(this::createPersons), trgEdit(this::createOnePerson));
	}
	
	/**
	* Tests supported by this test suite.
	*/
	private enum Test {
		BatchFWD, BatchBWD, IncrFWD, IncrBWD;
	}
	
	/**
	* Run and print measurements for a specific model size for all tools.
	* 
	* @param size	model size
	* @param test	test to run
	* @param format of output
	*/
	private void printRow(int size, Test test, String format) {
		boolean sizePrinted = false;
		for (BXTool<FamilyRegister, PersonRegister, Decisions> tool: tools) {
			PerformanceResult result = measure(test, tool.getName(), size);
			if (!sizePrinted) {
				sizePrinted = true;
				System.out.printf(format, result.getSize());
			}
			System.out.printf(format, result.getTime());
		}
		System.out.println();
	}
	
	/**
	* Print header for a new test with tool names.
	* 
	* @param title	title of test
	* @param format of output
	*/
    private static void printHeader(String title, String format) {
        System.out.println("------------------");
        System.out.println(title);
        
        String info = String.format(format, "model size"); // # of nodes and edges
        for (BXTool<?, ?, ?> tool: tools) {
	    	info += String.format(format, tool.getName());
	    }
        System.out.println(info);
    }
    
    protected Supplier<IEdit<FamilyRegister>> srcEdit(Runnable... ops) {
		return () -> {
			sourceEdit = new Edit<FamilyRegister>();
			for (var op : ops) {
				op.run();
			}
			return sourceEdit;
		};
	}

	protected Supplier<IEdit<PersonRegister>> trgEdit(Runnable... ops) {
		return () -> {
			targetEdit = new Edit<PersonRegister>();
			for (var op : ops) {
				op.run();
			}
			return targetEdit;
		};
	}
	
}

