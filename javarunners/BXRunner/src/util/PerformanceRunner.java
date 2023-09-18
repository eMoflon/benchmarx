package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
* This class is used to run performance tests/measurements in a given JAR-file.
* 
* @author Tom Schmidt
* @see PerformanceTestSuite
*/
public class PerformanceRunner {
	
	private URLClassLoader cl;
	
	/**
	* Class constructor
	* 
	* @author Tom Schmidt
	* @param jarPath	path to JAR file which contains performance test to run
	*/
	public PerformanceRunner(String jarPath) throws MalformedURLException {
		var url = new URL[] { new URL("jar:file:" + jarPath + "!/")};
		cl = new URLClassLoader(url);
	}
	
	/**
	* Method to run performance tests of a JAR-file. Prints results to stdandard out
	* 
	* @author Tom Schmidt
	* @param size 		model size for measurement
	* @param className	qualifier for class which contains tests
	* @param tool 		name of tool which will be used 
	* @param test		test name of tests which should be run
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void run(int size, String className, String tool, String test) throws ClassNotFoundException {
		Class clazz = null;
    	try {
			clazz = Class.forName(className, true, cl);
    	} catch (Exception e) {
    		System.out.println("Error during loading class: " + className + " Exception: " + e);
    		return;
    	}
    	
    	if (clazz == null) {
    		System.out.println("Error during loading class: " + className);
    		return;
    	}
    	
    	Constructor constructor = null;
    	for(Constructor c : clazz.getConstructors()) {
    		    if(c.getParameterTypes().length == 0) constructor = c;
	  	}
    	if (constructor == null) {
    		System.out.println("Scalability class needs a constructor without parameters. Class: " + clazz.getName());
    		return;
    	}
    	
    	Object obj;
    	try {
    		obj = constructor.newInstance();
    	} catch (Exception e) {
    		System.out.println("Scalability class constructor without parameters failed. Class: " + clazz.getName() + " Exception: " + e);
    		return;
    	}
    	
    	//measure
    	Object x = null;
    	Object y = null;
    	if (PerformanceTestSuite.class.isAssignableFrom(clazz)) {	//class uses PerformanceTestSuite Interface
    		PerformanceTestSuite suite = (PerformanceTestSuite) obj; 
    		var result = suite.measure(test, tool, size);
    		x = result.getSize();
    		y = result.getTime();
    	} else { 	            	//backup method: use java reflection
	    	Method method = null;
	    	try {
	    		method = clazz.getDeclaredMethod("measure", String.class, String.class, int.class);
	    	} catch (Exception e) {
	    		System.out.println("Scalability class does not have a method 'measure'. Class: " + clazz.getName() + " Exception: " + e);
	    		return;
	    	}
	    	if (method == null) {
	    		System.out.println("Scalability class does not have a method 'measure'. Class: " + clazz.getName());
	    		return;
	    	}
	    	
			Object result = null;
			try {
				result = method.invoke(obj, test, tool, size);
			} catch (Exception e) {
				System.out.println("Error during test " + test + ": " + e);
				e.printStackTrace();
				return;
			}
			
	    	try {
	    		var getSizeMethod = result.getClass().getDeclaredMethod("getSize");
	    		x = getSizeMethod.invoke(result);
	   
	    		var getTimeMethod = result.getClass().getDeclaredMethod("getTime");
	    		y = getTimeMethod.invoke(result);
	    	} catch (Exception e) {
	    		System.out.println("Error during getting size or time of result of performance measurement. "
	    				+ "Class: " + clazz.getName() + " Exception: " + e);
	    		return;
	    	}			
    	}
    	
    	if (x == null || y == null) {
    		System.out.println("x or y is unexpected null. x: " + x + " y: " + y);
    		return;
    	}
    	
    	//print result of measurement
		System.out.println("Found: " + x + " " + y + " " + className + " " + tool + " " + test);
	}
}
