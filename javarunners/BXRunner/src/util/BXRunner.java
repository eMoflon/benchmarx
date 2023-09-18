package util;

import java.util.Arrays;

/**
* A class that only contains a main method. Use arguments of main to start 
* searching/running JUnit tests/performance measurements in a given jar file.
* After exporting this project as runnable jar-file it can be used from other
* programs (e.g. bxRunner implementation in Python)
* 
* @author Tom Schmidt
*/
public class BXRunner {
	
	/**
	* Main method to start searching/running JUnit tests/performance measurements in a given jar file.
	* Command line arguments are used to specify what should be done. Usage of arguments is printed
	* to standard out if they are wrong/missing.  
	* 
	* @author Tom Schmidt
	* @param args	arguments to specify what should be done (searching/running JUnit tests/performance 
	* 				measurements in a given jar file): <mode> <mode specific arguments> 
	* 				Usage is printed to standard out.
	*/
	public static void main(String[] args) throws Exception {	
		if (args.length == 0) {
			printUsage();
			return;
		}
		
		String mode = args[0];
		if (mode.equals("JUnitFinder")) {
			if (args.length != 3) {
				printUsage();
				System.out.println("Usage JUnitFinder: JUnitFinder <jar path> <src path>. \n"
						+ "jar path: path to jar which should be searched through.\n"
						+ "src path: path to directory which contains benchmarx project.");
				return;
			}
			String jarPath = args[1];
			String srcPath = args[2];
			JUnitFinder finder = new JUnitFinder();
			finder.find(jarPath, srcPath);
		} else if (mode.equals("JUnitRunner")) {
			if (args.length < 4) {
				printUsage();
				System.out.println("Usage JUnitRunner: JUnitRunner <jar path> <class> <tool> <tests+>. \n"
						+ "jar path: path to jar which should be searched through.\n"
						+ "class: qualifier for class which contains the JUnit tests.\n"
						+ "tool: name of tool which should be used to run JUnit tests\n"
						+ "tests+: one or multiple names of test(s) to run. ");
				return;
			}
			String jarPath = args[1];
			String classQualifier = args[2];
			String toolName = args[3];
			String[] tasks = Arrays.copyOfRange(args, 4, args.length);
			var runner = new JUnitRunner(jarPath);
			runner.run(classQualifier, toolName, tasks);
		} else if (mode.equals("PerformanceFinder")) {
			if (args.length != 3) {
				printUsage();
				System.out.println("Usage PerformanceFinder: PerformanceFinder <jar path> <src path>. \n"
						+ "jar path: path to jar which should be searched through.\n"
						+ "src path: path to directory which contains benchmarx project.");
				return;
			}
			String jarPath = args[1];
			String srcPath = args[2];
			PerformanceFinder finder = new PerformanceFinder();
			finder.find(jarPath, srcPath);
		} else if (mode.equals("PerformanceRunner")) {
			if (args.length != 6) {
				printUsage();
				System.out.println("Usage PerformanceRunner: PerformanceRunner <jar path> <size> <class> <tool> <test>.\n"
						+ "jar path: path to jar which contains class for measurement through.\n"
						+ "size: model size for measurement (will be parsed to int)"
						+ "class: qualifier for class which contains the JUnit tests.\n"
						+ "tool: name of tool which should be used to run JUnit tests\n"
						+ "test: name of test to run. ");
				return;
			}
			String jarPath = args[1];
			int size = Integer.parseInt(args[2]);
			String className = args[3];
			String tool = args[4];
			String test = args[5];
			var runner = new PerformanceRunner(jarPath);
			runner.run(size, className, tool, test);
		} else {
			printUsage();
		}
	}
	
	/**
	* Print general usage of command line arguments for this program to standard out.
	* 
	* @author Tom Schmidt
	*/
	private static void printUsage() {
		System.out.println("Usage: Use as first argument 'JUnitFinder', 'JUnitRunner', "
				+ "'PerformanceFinder' or 'PerformanceRunner'.");
	}
	
}
