package org.benchmarx.util;

import java.util.Collection;

/**
* A class that contains runnable performance measurements which are supported
* by a set of tools. It also has methods to get informations of containing tests and
* supported tools. 
* 
* @author Tom Schmidt
*/
public interface PerformanceTestSuite {
	
	/**
	* Result of a performance measurement.
	* 
	* @author Tom Schmidt
	*/
	public interface PerformanceResult {
		
		/**
		* Returns size of model for finished measurement.
		* 
		* @author Tom Schmidt
		* @return size of model
		*/
		public int getSize();
		
		/**
		* Returns measured time.
		* 
		* @author Tom Schmidt
		* @return measured time
		*/
		public double getTime();
	}
		
	/**
	* Returns names of all available measurements/tests
	* 
	* @author Tom Schmidt
	* @return collection of test names
	*/
	public abstract Collection<String> getTests();
	
	/**
	* Get supported tools for a given test name
	* 
	* @author Tom Schmidt
	* @param test	name of the test for which the supported tools should be returned
	* @return 		collection of tool names which support the given test
	*/
	public abstract Collection<String> getSupportedTools(String test);
	
	/**
	* Do a measurement for a given test, a supported tool and a given model size
	* 
	* @author Tom Schmidt
	* @param test	name of the test which should be run
	* @param tool	name of tool which sould be used
	* @param size	size of model
	* @return 		collection of tool names which support the given test
	*/
	public abstract PerformanceResult measure(String test, String tool, int size);
	
}
