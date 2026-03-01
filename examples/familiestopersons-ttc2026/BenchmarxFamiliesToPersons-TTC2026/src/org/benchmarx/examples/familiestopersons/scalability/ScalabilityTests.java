package org.benchmarx.examples.familiestopersons.scalability;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.runners.Parameterized.AfterParam;
import org.junit.runners.Parameterized.BeforeParam;

import Families.FamiliesPackage;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsPackage;

public abstract class ScalabilityTests extends FamiliesToPersonsTestCase {
	private static final String DELIMITER = "\n";
	protected static final int REPEAT = 3;
	protected static final int TIMEOUT = 7200; // seconds
	private static final String resultFolder = "scalability_results";

	protected static Map<Integer, Double> results;
	protected static String label;

	@Override
	public void initialise() {
		Logger.getRootLogger().setLevel(Level.INFO);
		
		// Make sure packages are registered
		FamiliesPackage.eINSTANCE.getName();
		PersonsPackage.eINSTANCE.getName();

		// Initialise all helpers
		familiesComparator = new FamiliesComparator();
		personsComparator = new PersonsComparator();
		util = new BenchmarxUtil<>(tool);

		// we overwrite the super method to avoid initialising the synchronisationDialog
		// this happens within each test

		helperFamily = createAndInitialiseHelperFamily(() -> tool.getSourceModel(), () -> sourceEdit);
		helperPerson = createAndInitialiseHelperPerson(() -> tool.getTargetModel(), () -> targetEdit);
	}
	
	@Override
	public void terminate() {
		// we overwrite the super method to avoid terminating the synchronisationDialog
		// this happens within each test
	}
	
	@AfterParam
	public static void saveResults(BXTool<FamilyRegister, PersonRegister, Decisions> tool)
			throws FileNotFoundException {
		if(results.isEmpty())
			return;
					
		try (PrintWriter out = new PrintWriter(resultFolder + "/" + label + tool.getName() + ".txt")) {
			out.println(results.keySet().stream()//
					.sorted()//
					.map(k -> k + ", " + results.get(k))//
					.collect(Collectors.joining(DELIMITER)));
		}
	}

	@BeforeParam
	public static void initResults(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		results = new HashMap<>();
	}

	public ScalabilityTests(BXTool<FamilyRegister, PersonRegister, Decisions> tool, String l) {
		super(tool);
		label = l;
	}
}
