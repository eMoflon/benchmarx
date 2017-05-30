package org.benchmarx.examples.familiestopersons.testsuite;

import java.util.Arrays;
import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.familiestopersons.implementations.nmf.NMFFamiliesToPersonsIncremental;
import org.benchmarx.examples.familiestopersons.implementations.bigul.BiGULFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.bxtend.UbtXtendFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.emoflon.EMoflonFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.funnyqt.FunnyQTFamiliesToPerson;
import org.benchmarx.examples.familiestopersons.implementations.medini.MediniQVTFamiliesToPersons;
import org.benchmarx.examples.familiestopersons.implementations.medini.MediniQVTFamiliesToPersonsConfig;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.families.core.FamilyHelper;
import org.benchmarx.persons.core.PersonHelper;
import org.benchmarx.persons.core.PersonsComparator;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Families.FamiliesPackage;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsPackage;

@RunWith(Parameterized.class)
public abstract class FamiliesToPersonsTestCase {

	protected BXTool<FamilyRegister, PersonRegister, Decisions> tool;
	protected Comparator<FamilyRegister> familiesComparator;
	protected Comparator<PersonRegister> personsComparator;
	protected BenchmarxUtil<FamilyRegister, PersonRegister, Decisions> util;
	protected FamilyHelper helperFamily;
	protected PersonHelper helperPerson;

	@Before
	public void initialise() {
		// Make sure packages are registered
		FamiliesPackage.eINSTANCE.getName();
		PersonsPackage.eINSTANCE.getName();
		
		// Initialise all helpers
		familiesComparator = new FamiliesComparator();
		personsComparator = new PersonsComparator();
		util = new BenchmarxUtil<>(tool);
		helperFamily = new FamilyHelper();
		helperPerson = new PersonHelper();
		
		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();
	}

	@After
	public void terminate(){
		tool.terminateSynchronisationDialogue();
	}
	
	@Parameters
	public static Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> tools() {
		return Arrays.asList(
<<<<<<< HEAD
				new NMFFamiliesToPersonsIncremental()
				//new BiGULFamiliesToPersons()  // Currently 9 failures
				//,
				//new EMoflonFamiliesToPersons()  // Currently 5 failures
				//,
				//new MediniQVTFamiliesToPersons() // Currently 19 failures
				//,
				//new MediniQVTFamiliesToPersonsConfig() // Currently 12 failures
				//,
				//new UbtXtendFamiliesToPersons()  // Currently 0 failures
=======
				new BiGULFamiliesToPersons()  // Currently 9 failures
				,
				new EMoflonFamiliesToPersons()  // Currently 6 failures
				,
				new MediniQVTFamiliesToPersons() // Currently 19 failures
				,
				new MediniQVTFamiliesToPersonsConfig() // Currently 12 failures
				,
				new UbtXtendFamiliesToPersons()  // Currently 0 failures
				,
				new FunnyQTFamiliesToPerson() // Currently 10 failures
>>>>>>> b2636822c1d645c92ae6a0e5834d7ce5fde475d9
			);
	}
	
	protected FamiliesToPersonsTestCase(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		this.tool = tool; 
	}
}
