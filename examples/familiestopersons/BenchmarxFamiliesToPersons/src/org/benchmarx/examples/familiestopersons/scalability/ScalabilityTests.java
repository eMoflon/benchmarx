package org.benchmarx.examples.familiestopersons.scalability;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized.AfterParam;
import org.junit.runners.Parameterized.BeforeParam;

import Families.FamilyRegister;
import Persons.PersonRegister;

public abstract class ScalabilityTests extends FamiliesToPersonsTestCase {
	private static final String DELIMITER = "\n";
	protected static final int REPEAT = 3;
	protected static final int TIMEOUT = 300; // seconds
	private static final String resultFolder = "scalability_results";

	protected static Map<Integer, Double> results;

	@AfterParam
	public static void saveResults(BXTool<FamilyRegister, PersonRegister, Decisions> tool)
			throws FileNotFoundException {
		try (PrintWriter out = new PrintWriter(resultFolder + "/" + tool.getName() + ".txt")) {
			out.println(results.keySet().stream()//
					.sorted()//
					.map(k -> k + ", " + results.get(k))//
					.collect(Collectors.joining(DELIMITER)));
		}
	}

	@BeforeClass
	public static void clearResultFolder() {
		try {
			if (new File(resultFolder).exists()) {
				Files.walk(Paths.get(resultFolder)).filter(Files::isRegularFile).forEach(t -> {
					try {
						Files.delete(t);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			} else {
				Files.createDirectory(Paths.get(resultFolder));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeParam
	public static void initResults(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		results = new HashMap<>();
	}

	public ScalabilityTests(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
}
