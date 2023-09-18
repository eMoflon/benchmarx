package util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.notification.Failure;


/**
* This class is used to run JUnit tests in a given JAR-file.
* This class is specific to JUnit tests in Benchmarx project.
* 
* @author Tom Schmidt
*/
public class JUnitRunner {
	
	private URLClassLoader cl;
	
	/**
	* Class constructor
	* 
	* @author Tom Schmidt
	* @param jarPath	path to JAR file which contains JUnit tests to run
	*/
	public JUnitRunner(String jarPath) throws MalformedURLException {
		var url = new URL[] { new URL("jar:file:" + jarPath + "!/")};
		cl = new URLClassLoader(url);
	}
	
	/**
	* Method to run JUnit tests of a JAR-file. Prints results to std out
	* 
	* @author Tom Schmidt
	* @param className	qualifier for class which contains tests
	* @param tool 			name of tool which will be used (JUnit tests have to be parameterized tests
	* 						like the ones in Benchmarx project)
	* @param testsToRun		test names of tests which should be run
	*/
	public void run(String className, String tool, String[] testsToRun) throws ClassNotFoundException {  
        LinkedList<String> remainingTests =  new LinkedList<String>(Arrays.asList(testsToRun));
		
        Result res = null;
        try {
    		@SuppressWarnings("rawtypes")
			Class clazz = Class.forName(className, true, cl);
    		Request req = Request.aClass(clazz);
    		
    		//ToDo: If test is not found result will say "1 fails". Give better feedback to user that test was not found.
        	TFilter filter = new TFilter(tool, testsToRun);
        	req = req.filterWith(filter);
        	
        	res = (new JUnitCore()).run(req);
    	} catch(Exception | LinkageError e) {
    		System.out.println("Error during run of test for class: " + className + " with tool " + tool + " Error: " + e);
    		return;
    	}
        
        for (Failure failure: res.getFailures()) {
            String failedTest = failure.getDescription().getDisplayName();
            failedTest = failedTest.substring(0, failedTest.length() - tool.length() - className.length() - 2);
            
            String errorMsg = failure.getTrimmedTrace();
            remainingTests.remove(failedTest);
            
            printResult(className, tool, failedTest, false, errorMsg);
        }

        for (String successfullTest: remainingTests) {
            printResult(className, tool, successfullTest, true, "");
        }
	}
	
	/**
	* Print result of JUnit test to standard out. 
	* 
	* @author Tom Schmidt
	* @param className	name of class which contains test
	* @param tool		name of tool which run test
	* @param test		name of test which war run
	* @param success	true if run was a success, else false
	* @param errorMsg	a error message to print which occurred during test
	*/
	private void printResult(String className, String tool, String test, boolean success, String errorMsg) {
		String out = "JUnit result:";
		if (success) out += " 1 ";
		else out += " 0 ";
		out += className + " " + tool + " " + test + "<errormsg>" + errorMsg + "</errormsg>";
		System.out.println(out);
	}
	
	/**
	* Private class to filter tests to run.
	* 
	* @author Tom Schmidt
	*/
	static class TFilter extends Filter {
		private String parameter;
		private String[] tests;
		
		/**
		* Constructor of class
		* 
		* @author Tom Schmidt
		* @param parameter	of parameterized JUnit test (name of tool for this application)
		* @param tests 		names of tests which should be run
		*/
		public TFilter(String parameter, String[] tests) {
			this.parameter = parameter;
			this.tests = tests;
		}

		/**
		* Overrides method to filter tests correctly
		* 
		* @author Tom Schmidt
		* @param description of test
		* @return true if test should be run, else false
		*/
		@Override
		public boolean shouldRun(Description description) {
			String name = description.toString();
			for (String test: tests) {
				if (name.contains(parameter) && (name.equals(parameter) || name.contains(test + parameter))) {
					return true;
				}
			}
			return false;
		}

		@Override
		public String describe() {
			// Auto-generated method stub
			return null;
		}
		
	}
}
