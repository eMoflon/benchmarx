package org.benchmarx.examples.familiestopersons.scalability;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.benchmarx.examples.familiestopersons.scalability.runner.ScalabilityConfig;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Families.FamiliesPackage;
import Persons.PersonsPackage;

/**
 * Standalone CLI runner for Benchmarx scalability tests.
 * Enables executing test suites from a Fat JAR on any machine (e.g., Linux server).
 */
public class ScalabilityMain {

	public static void main(String[] args) {
		System.out.println("==========================================================");
		System.out.println("   Benchmarx FamiliesToPersons Scalability Test Runner    ");
		System.out.println("==========================================================");

		String suiteName = "FamiliesToPersonsScalabilityTestSuite";
		String resultsDir = null;
		Integer repeat = null;
		Integer timeout = null;
		String xmx = null;
		String includeTools = null;
		String excludeTools = null;

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equals("--suite") || arg.equals("-s")) {
				if (i + 1 < args.length) {
					suiteName = args[++i];
				}
			} else if (arg.equals("--results") || arg.equals("-r")) {
				if (i + 1 < args.length) {
					resultsDir = args[++i];
				}
			} else if (arg.equals("--repeat") || arg.equals("-n")) {
				if (i + 1 < args.length) {
					repeat = Integer.parseInt(args[++i]);
				}
			} else if (arg.equals("--timeout") || arg.equals("-t")) {
				if (i + 1 < args.length) {
					timeout = Integer.parseInt(args[++i]);
				}
			} else if (arg.equals("--xmx") || arg.equals("-m")) {
				if (i + 1 < args.length) {
					xmx = args[++i];
				}
			} else if (arg.equals("--tools") || arg.equals("-T") || arg.equals("--include-tools")) {
				if (i + 1 < args.length) {
					includeTools = args[++i];
				}
			} else if (arg.startsWith("-T=")) {
				includeTools = arg.substring(3);
			} else if (arg.startsWith("--tools=")) {
				includeTools = arg.substring(8);
			} else if (arg.equals("--exclude-tools") || arg.equals("-E")) {
				if (i + 1 < args.length) {
					excludeTools = args[++i];
				}
			} else if (arg.startsWith("-E=")) {
				excludeTools = arg.substring(3);
			} else if (arg.startsWith("--exclude-tools=")) {
				excludeTools = arg.substring(16);
			} else if (arg.equals("--help") || arg.equals("-h")) {
				printHelp();
				System.exit(0);
			}
		}

		// Configure result directory (defaults to ./results if not set)
		if (resultsDir == null) {
			resultsDir = System.getProperty("scalability.resultfolder");
		}
		if (resultsDir == null || resultsDir.isBlank() || resultsDir.equals("C:/scalability_results")) {
			File defaultDir = new File("results").getAbsoluteFile();
			resultsDir = defaultDir.getAbsolutePath();
		}
		System.setProperty("scalability.resultfolder", resultsDir);
		new File(resultsDir).mkdirs();

		// Configure repeat count
		if (repeat != null) {
			System.setProperty("scalability.repeat", String.valueOf(repeat));
		}

		// Configure timeout
		if (timeout != null) {
			System.setProperty("scalability.timeout", String.valueOf(timeout));
		}

		// Configure Xmx for child processes
		if (xmx != null) {
			if (!xmx.startsWith("-Xmx")) {
				xmx = "-Xmx" + xmx;
			}
			System.setProperty("scalability.xmx", xmx);
		}

		// Configure tool filter
		if (includeTools != null && !includeTools.isBlank()) {
			System.setProperty("scalability.tools.include", includeTools);
		}
		if (excludeTools != null && !excludeTools.isBlank()) {
			System.setProperty("scalability.tools.exclude", excludeTools);
		}

		// Pre-register EMF packages
		try {
			FamiliesPackage.eINSTANCE.getName();
			PersonsPackage.eINSTANCE.getName();
		} catch (Throwable t) {
			System.err.println("Warning: Pre-registering EMF packages: " + t.getMessage());
		}

		Class<?> testClass = resolveSuiteClass(suiteName);
		if (testClass == null) {
			System.err.println("Error: Could not resolve test suite or test class: " + suiteName);
			System.exit(1);
		}

		System.out.println("Running suite:       " + testClass.getName());
		System.out.println("Results directory:   " + ScalabilityConfig.getResultFolder());
		System.out.println("Repetitions:         " + ScalabilityConfig.getRepeatCount());
		System.out.println("Timeout (seconds):   " + ScalabilityConfig.getTimeoutSeconds());
		System.out.println("Child JVM args:      " + ScalabilityConfig.getJvmArgs());
		if (System.getProperty("scalability.tools.include") != null) {
			System.out.println("Included tools:      " + System.getProperty("scalability.tools.include"));
		}
		if (System.getProperty("scalability.tools.exclude") != null) {
			System.out.println("Excluded tools:      " + System.getProperty("scalability.tools.exclude"));
		}
		System.out.println("Classpath in use:    " + System.getProperty("java.class.path"));
		System.out.println("==========================================================\n");

		long startTime = System.currentTimeMillis();
		Result result = JUnitCore.runClasses(testClass);
		long duration = System.currentTimeMillis() - startTime;

		System.out.println("\n==========================================================");
		System.out.println("                     Execution Summary                    ");
		System.out.println("==========================================================");
		System.out.println("Total tests run:     " + result.getRunCount());
		System.out.println("Failures:            " + result.getFailureCount());
		System.out.println("Ignored:             " + result.getIgnoreCount());
		System.out.println(String.format("Time elapsed:        %.2f s", (duration / 1000.0)));

		if (!result.wasSuccessful()) {
			System.out.println("\nFailures detail:");
			for (Failure failure : result.getFailures()) {
				System.out.println(" - " + failure.getTestHeader() + ": " + failure.getMessage());
				if (failure.getException() != null) {
					failure.getException().printStackTrace(System.out);
				}
			}
			System.exit(1);
		}

		System.out.println("\nAll tests completed successfully! Results written to: " + ScalabilityConfig.getResultFolder());
		System.exit(0);
	}

	private static Class<?> resolveSuiteClass(String name) {
		List<String> candidateNames = new ArrayList<>();
		candidateNames.add(name);
		candidateNames.add("org.benchmarx.examples.familiestopersons.scalability." + name);
		candidateNames.add("org.benchmarx.examples.familiestopersons.testsuite." + name);
		candidateNames.add("org.benchmarx.examples.familiestopersons.testsuite.batch.fwd." + name);
		candidateNames.add("org.benchmarx.examples.familiestopersons.testsuite.batch.bwd." + name);
		candidateNames.add("org.benchmarx.examples.familiestopersons.testsuite.alignment_based.fwd." + name);
		candidateNames.add("org.benchmarx.examples.familiestopersons.testsuite.alignment_based.bwd." + name);
		candidateNames.add("org.benchmarx.examples.familiestopersons.testsuite.alignment_based.roundtrip." + name);

		for (String cName : candidateNames) {
			try {
				return Class.forName(cName);
			} catch (ClassNotFoundException ignored) {
			}
		}
		return null;
	}

	private static void printHelp() {
		System.out.println("Usage: java [jvm-options] -jar benchmarx-scalability.jar [options]");
		System.out.println();
		System.out.println("Options:");
		System.out.println("  -s, --suite <name>     Test suite or class to run (default: FamiliesToPersonsScalabilityTestSuite)");
		System.out.println("                         Examples: ScalabilityIncrTestsBwd, ScalabilityConstModelCSync");
		System.out.println("  -r, --results <dir>    Directory for benchmark CSV/TXT output (default: ./results)");
		System.out.println("  -n, --repeat <num>     Number of repetitions per scale factor (default: 10)");
		System.out.println("  -t, --timeout <sec>    Process timeout per run in seconds (default: 60)");
		System.out.println("  -m, --xmx <size>       Max heap memory for spawned child runs (default: -Xmx32G, e.g., 16G, 8G)");
		System.out.println("  -T, --tools <names>    Comma-separated tool name filter to include (e.g., ibex, eneo)");
		System.out.println("  -E, --exclude-tools    Comma-separated tool name filter to exclude (e.g., bxtend)");
		System.out.println("  -h, --help             Display this help message");
		System.out.println();
		System.out.println("System properties can also be passed via -D, e.g.:");
		System.out.println("  -Dscalability.resultfolder=/custom/results");
		System.out.println("  -Dscalability.repeat=5");
		System.out.println("  -Dscalability.timeout=120");
		System.out.println("  -Dscalability.xmx=-Xmx16G");
	}
}
