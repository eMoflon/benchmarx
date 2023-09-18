package util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;


/**
* This class is used to search for performance tests/measurements in a given JAR-file.
* 
* @author Tom Schmidt
* @see PerformanceTestSuite
*/
public class PerformanceFinder {
	
	/**
	* Find all performance tests of a given JAR-file and corresponding project path.
	* Prints results to standard out to allow capturing them by other programs.
	* 
	* @author Tom Schmidt
	* @param jarPath	path to JAR-file
	* @param srcPath	path to directory which contains Benchmarx project
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void find(String jarPath, String srcPath) throws IOException {
		var projects = findAllProjects(srcPath); // find all projects and safe their paths
		
		File file = new File(jarPath);
		JarFile jarFile = new JarFile(file);
		var entries = jarFile.entries();
		var url = new URL[] { new URL("jar:file:" + jarPath + "!/")};
		var cl = new URLClassLoader(url);
		
		try {
	        while (entries.hasMoreElements()) {
	            JarEntry jarEntry = entries.nextElement();
	            if (!jarEntry.getName().endsWith(".class")) continue;
	            String classQualifier = jarEntry.getName().replace("/", ".").replace(".class", "");
	            try {
					Class clazz = Class.forName(classQualifier, true, cl);
	            	
	            	if (clazz == null) continue;    
	            	    	
	            	if (!isPerformanceSuite(clazz)) continue;
	            	
					Constructor constructor = null;
	            	for(Constructor c : clazz.getConstructors()) {
	            		    if(c.getParameterTypes().length == 0) constructor = c;
	        	  	}
	            	if (constructor == null) {
	            		System.out.println("Scalability class needs a constructor without parameters. Class: " + clazz.getName());
	            		continue;
	            	}
	            	
	            	Object obj;
	            	try {
	            		obj = constructor.newInstance();
	            	} catch (Exception e) {
	            		System.out.println("Scalability class constructor without parameters failed. Class: " + clazz.getName() + " Exception: " + e);
	            		continue;
	            	}
	            	
	        		String project = getProject(classQualifier, projects);
	        		if (project == null) {
	        			System.out.println("No corresponding project found for class: " + classQualifier);
	        			continue;
	        		}
	
	            	if (PerformanceTestSuite.class.isAssignableFrom(clazz)) { //class uses PerformanceTestSuite Interface
	            		PerformanceTestSuite suite = (PerformanceTestSuite) obj; 
	            		Collection<String> tests = suite.getTests();
		            	for (String test: tests) {
		                	Collection<String> tools = suite.getSupportedTools(test);
		            		for (String tool: tools) {
		            			printFoundTest(classQualifier, tool, test, project);
		            		}
		            	}
	            	} else { 	            	//backup method: use java reflection
		            	Collection<String> tests;
		            	try {
		            		var method = clazz.getDeclaredMethod("getTests");
		            		tests = (Collection<String>) method.invoke(obj);
		            	} catch (Exception e) {
		            		System.out.println("An error occurred during getting tests. Class: " + clazz.getName() + " Exception: " + e);
		            		continue;
		            	}
		        			
		            	for (String test: tests) {
		                	Collection<String> tools;
		                	try {
		                		var method = clazz.getDeclaredMethod("getSupportedTools", String.class);
		                		tools = (Collection<String>) method.invoke(obj, test);
		                	} catch (Exception e) {
		                		System.out.println("An error occurred during getting tools of test: " + test + ". Class: " + clazz.getName() + " Exception: " + e);
		                		continue;
		                	}
		            		
		            		for (String tool: tools) {
		            			printFoundTest(classQualifier, tool, test, project);
		            		}
		            	}
	            	}
	        	} catch(Exception | LinkageError e) {
	        		System.out.println("Error for class: " + classQualifier + " Error: " + e);
	        		continue;
	        	}
	        }
		} finally {
			jarFile.close();
	    	cl.close();
		}
	}

	/**
	* Method to check if request is a valid performance test.
	* Uses the Interface PerformanceTestSuite to find tests but also uses java reflection
	* as backup to check if class might have right methods to be a performance test
	* although it does not conform to Interface PerformanceTestSuite.
	* 
	* @author Tom Schmidt
	* @param klass 	which should be checked	
	* @return 		true if request is valid JUnit test
	*/
	private static boolean isPerformanceSuite(Class<?> klass) {
		try	{			
			if (PerformanceTestSuite.class.isAssignableFrom(klass)) return true;
			Method[] methods = PerformanceTestSuite.class.getDeclaredMethods();
			if (methods.length == 0) return false;
			for (var method: methods) {
				if (!Modifier.isPublic(method.getModifiers())) continue;
				try {
					if (klass.getDeclaredMethod(method.getName(), method.getParameterTypes()) == null) return false;
				} catch(NoSuchMethodException e) {
					return false;
				}
			}
			return true;
    	} catch(Exception | LinkageError e) {
			e.printStackTrace();
		}
		return false;
	}	
	
	/**
	* Print fount performance test to standard out. 
	* 
	* @author Tom Schmidt
	* @param className	name of class which contains test
	* @param tool		name of tool which run test
	* @param test		name of test which war run
	* @param project	corresponding project path
	*/
	private void printFoundTest(String classQualifier, String tool, String test, String project) {
		System.out.println("Found: " + classQualifier + " " + tool + " " + test + " " + project);
	}
	
	/**
	* Method searches for .project files in directory to determine paths of projects.
	* 
	* @author Tom Schmidt
	* @param srcPath	path to directory which should be searched through
	* @return			list of paths to found projects
	*/
	private List<String> findAllProjects(String srcPath) throws IOException {
		List<String> projectFilePaths;
		String extension = "\\.project";
		try (var walk = Files.walk(Paths.get(srcPath))) {
			projectFilePaths = walk
		              .filter(p -> !Files.isDirectory(p)) 	// check if directory
		              .map(p -> p.toString())				
		              .filter(f -> f.endsWith(extension))  // check file format
		              .collect(Collectors.toList());        // collect matches
		}
		
		LinkedList<String> projects = new LinkedList<String>();
		int extensionSize = extension.length();
		for (String project: projectFilePaths) {
			projects.add(project.substring(0, project.length() - extensionSize));
		}
		return projects;
	}
	
	/**
	* Get project which contains a given class.
	* 
	* @author Tom Schmidt
	* @param classQualifier qualifier for class 
	* @param projects		list of paths to projects
	* @return path to project which contains given class. null if not found
	*/
	private String getProject(String classQualifier, List<String> projects) throws IOException {
		String projectPath = null;
		for (String project: projects) {
			String path = project + "\\src\\" + classQualifier.replace(".", "\\") + ".java";
			File f = new File(path);
			if (!f.exists()) continue;
			if (projectPath == null) {
				projectPath = project;
			} else {
				System.out.println("Warning: Found class in multiple projects:\n First: " + projectPath + "\nSecond: " + project + "\nSecond one will be ignored.");
			}
		}
		return projectPath;
	}
	
}
