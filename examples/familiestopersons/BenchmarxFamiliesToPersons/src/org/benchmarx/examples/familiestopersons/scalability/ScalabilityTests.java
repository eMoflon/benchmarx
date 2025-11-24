package org.benchmarx.examples.familiestopersons.scalability;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.benchmarx.BXTool;
import org.benchmarx.edit.IEdit;
import org.benchmarx.examples.familiestopersons.scalability.runner.BenchEntry;
import org.benchmarx.examples.familiestopersons.scalability.runner.BenchTestcase;
import org.benchmarx.examples.familiestopersons.scalability.runner.ScalabilityTestRunner;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.benchmarx.families.core.FamilyHelper;
import org.benchmarx.persons.core.PersonHelper;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.AfterParam;
import org.junit.runners.Parameterized.BeforeParam;
import org.junit.runners.Parameterized.Parameters;

import Families.FamilyRegister;
import Persons.PersonRegister;

@RunWith(Parameterized.class)
public abstract class ScalabilityTests {
	protected BXTool<FamilyRegister, PersonRegister, Decisions> tool;
	protected BiConsumer<FamilyRegister, FamilyRegister> familiesComparator;
	protected BiConsumer<PersonRegister, PersonRegister> personsComparator;
	protected BenchmarxUtil<FamilyRegister, PersonRegister, Decisions> util;
	protected FamilyHelper helperFamily;
	protected PersonHelper helperPerson;
	protected IEdit<FamilyRegister> sourceEdit;
	protected IEdit<PersonRegister> targetEdit;
	
	private static final String DELIMITER = "\n";
	protected static final int REPEAT = 5;
	private static final String resultFolder = "C:/scalability_results";

	protected static Map<Integer, Double> results;
	protected static String label;
	
	private static boolean lastTestSuccessfull;
	
	@BeforeParam
	public static void initResults(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		results = new HashMap<>();
		lastTestSuccessfull = true;
	}
	
	@AfterParam
	public static void saveResults(BXTool<FamilyRegister, PersonRegister, Decisions> tool)
			throws FileNotFoundException {
		if(results.isEmpty())
			return;
					
		var file = new File(resultFolder + "/");
		if(!file.exists()) {
			file.mkdirs();
		}
		
		try (PrintWriter out = new PrintWriter(resultFolder + "/" + label + tool.getName() + ".txt")) {
			out.println(results.keySet().stream()//
					.sorted()//
					.map(k -> k + ", " + results.get(k))//
					.collect(Collectors.joining(DELIMITER)));
		}
	}


	public ScalabilityTests(BXTool<FamilyRegister, PersonRegister, Decisions> tool, String l) {
		this.tool = tool;
		label = l;
	}
	
	public void setTestSuccessfull() {
		lastTestSuccessfull = true;
	}
	
	public void assertLastTestSuccessfull() {
		assertTrue(lastTestSuccessfull);
		lastTestSuccessfull = false;
	}
	
	protected void runTest(Class<? extends BenchTestcase> testcaseClass, BXTool tool, int scaleFactor) {
		assertLastTestSuccessfull();

		var entries = new LinkedList<BenchEntry>();
		
		
		try {			
			for(var r = 0; r < REPEAT; r++) {
				tool.preExecution();
				var runner = new ScalabilityTestRunner(testcaseClass, Arrays.asList("-Xmx32G"), new String[] {tool.getName(), ""+scaleFactor});
				entries.add(runner.run());
				tool.postExecution();
			}
		}
		catch(TimeoutException timeout) {
			tool.postExecution();
			assertTrue(false);
			return;
		}
		catch(IllegalStateException illegalState) {
			tool.postExecution();
			assertTrue(false);
			return;
		} catch (Exception e) {
			tool.postExecution();
			assertTrue(false);
			e.printStackTrace();
			return;
		}
		
		results.put(scaleFactor, entries.stream().map(e -> e.resolve).sorted().toList().get((int) (REPEAT / 2)));
		setTestSuccessfull();
	}
	
	@Parameters(name = "{0}")
	public static Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> tools() {
		return FamiliesToPersonsTestCase.tools();
	}
	
}
