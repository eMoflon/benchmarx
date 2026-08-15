package org.benchmarx.examples.familiestopersons.scalability;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
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
import org.benchmarx.examples.familiestopersons.categories.PerformanceTest;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.AfterParam;
import org.junit.runners.Parameterized.BeforeParam;
import org.junit.runners.Parameterized.Parameters;

import Families.FamilyRegister;
import Persons.PersonRegister;

@Category(PerformanceTest.class)
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
	protected static Map<Integer, List<Double>> allResults;
	protected static String label;
	
	private static boolean lastTestSuccessfull;
	
	@BeforeParam
	public static void initResults(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		results = new LinkedHashMap<>();
		allResults = new LinkedHashMap<>();
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

		if (allResults != null && !allResults.isEmpty()) {
			var maxRuns = allResults.values().stream().mapToInt(List::size).max().orElse(REPEAT);
			var header = new StringBuilder("ScaleFactor, Median, Average, StdDev, StdErr");
			for (var i = 1; i <= maxRuns; i++) {
				header.append(", Run_").append(i);
			}

			try (var outDetailed = new PrintWriter(resultFolder + "/" + label + tool.getName() + "_detailed.csv")) {
				outDetailed.println(header.toString());
				for (var k : allResults.keySet().stream().sorted().collect(Collectors.toList())) {
					var values = allResults.get(k);
					var n = values.size();
					var sorted = values.stream().sorted().collect(Collectors.toList());
					var median = (n % 2 == 1) ? sorted.get(n / 2) : (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0;
					var average = values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
					var variance = (n > 1) ? values.stream().mapToDouble(v -> Math.pow(v - average, 2)).sum() / (n - 1) : 0.0;
					var stdDev = Math.sqrt(variance);
					var stdErr = (n > 0) ? stdDev / Math.sqrt(n) : 0.0;

					var sb = new StringBuilder();
					sb.append(String.format(Locale.US, "%d, %.6f, %.6f, %.6f, %.6f", k, median, average, stdDev, stdErr));
					for (var v : values) {
						sb.append(String.format(Locale.US, ", %.6f", v));
					}
					outDetailed.println(sb.toString());
				}
			}
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
		
		var times = entries.stream().map(e -> e.resolve).collect(Collectors.toList());
		allResults.put(scaleFactor, times);

		var sortedTimes = times.stream().sorted().collect(Collectors.toList());
		results.put(scaleFactor, sortedTimes.get((int) (REPEAT / 2)));
		setTestSuccessfull();
	}
	
	@Parameters(name = "{0}")
	public static Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> tools() {
		return FamiliesToPersonsTestCase.tools();
	}
	
}
