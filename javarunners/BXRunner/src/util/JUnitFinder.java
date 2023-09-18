package util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.runner.Request;


/**
* This class is used to search for JUnit tests in a given JAR-file.
* This class is specific to JUnit tests in Benchmarx project.
* 
* @author Tom Schmidt
*/
public class JUnitFinder {
	
	// regular expression pattern to find features in source files
	private final Pattern pattern = Pattern.compile("<b>Features.?</b>:(.*)(?:(?:.|\\R)+?)public\\s+void\\s+(test\\w+?)\\(");

	
	/**
	* Find all JUnit tests of a given JAR-file and extract features of corresponding source files.
	* Prints results to standard out to allow capturing them by other programs.
	* 
	* @author Tom Schmidt
	* @param jarPath	path to JAR-file
	* @param srcPath	path to directory which contains Benchmarx project
	*/
	public void find(String jarPath, String srcPath) throws IOException {
		List<String> projects = findAllProjects(srcPath); // find all projects and safe their paths
		
		File file = new File(jarPath);
		JarFile jarFile = new JarFile(file);
		var entries = jarFile.entries();
		var url = new URL[] { new URL("jar:file:" + jarPath + "!/")};
		var cl = new URLClassLoader(url);
		
		try {
	        while (entries.hasMoreElements()) {	//iterate through entries of jar
	            JarEntry jarEntry = entries.nextElement();
	            if (!jarEntry.getName().endsWith(".class")) continue;
	            String classQualifier = jarEntry.getName().replace("/", ".").replace(".class", "");
	            try {
	        		@SuppressWarnings("rawtypes")
					Class clazz = null;
	                try {	//load class
	                	clazz = Class.forName(classQualifier, true, cl);
	                } catch (ClassNotFoundException e) {
	                	System.out.println("ClassNotFoundException: "+ e);
	                	continue;
	                }
	            	
	            	if (clazz == null) continue;    
	
	            	Request req = Request.aClass(clazz);       	
	            	if (!isJUnit(req)) continue;
	            	
	            	var classDescription = req.getRunner().getDescription();
	        		String className = classDescription.getDisplayName();
	        		if (className.contains("initializationError(") || className.contains("warning(")) continue;
	        		
	        		String projectName = getProject(classQualifier, projects);
	        		if (projectName == null) {
	        			System.out.println("No corresponding project found for class: " + classQualifier);
	        			continue;
	        		}
	        		
	        		var features = extractFeatures(projectName, classQualifier);
	        		
	        		for (var toolDescription: classDescription.getChildren()) {
	            		String toolName = toolDescription.getDisplayName();
	            		if (toolName.contains("initializationError(") || toolName.contains("warning(")) continue;
	            		
	            		for (var testDescription: toolDescription.getChildren()) {
	            			String testName = testDescription.getDisplayName();
	                		if (testName.contains("initializationError(") || testName.contains("warning(")) continue;
	                		
	                		if (testName.endsWith(toolName + "(" + className + ")")) {
	                			testName = testName.substring(0, testName.length() - toolName.length() - className.length() - 2);
	                		} else {
	                    		System.out.println("Error: Found invalid test name format. Test will be skipped. Test name: " + testName);
	                    		continue;
	                		}
	                		
	                		String classification;
	                		if (features.containsKey(testName)) {
	                			classification = features.get(testName);
	                		} else {
	                			classification = "";
	                		}
	                		
	                		//print found test to standard out
	                		System.out.println("Found: " + className + " " + toolName + " " + testName + " " + projectName + " " + classification);
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
	* Method to check if request is a valid JUnit test.
	* 
	* @author Tom Schmidt
	* @param request 	which should be checked	
	* @return 			true if request is valid JUnit test
	*/
	private static boolean isJUnit(Request request) {
		var runner = request.getRunner();
		var description = runner.getDescription();
		return description.getTestClass() != null && runner.testCount() > 0 
				&& !description.getChildren().get(0).getDisplayName().contains("initializationError(")
				&& !description.getChildren().get(0).getDisplayName().contains("warning(");
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
		
		var projects = new LinkedList<String>();
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
	
	/**
	* Extracts features for tests of given class in source file.
	* Pattern for features is specific to Benchmarx project .
	* 
	* @author Tom Schmidt
	* @param project		path to project which contains source file
	* @param classQualifier	qualified name of class for which tests features should be extracted
	* @return a HashMap with test names as keys and features as values
	*/
	private HashMap<String, String> extractFeatures(String project, String classQualifier) throws IOException {
		String path = project + "\\src\\" + classQualifier.replace(".", "\\") + ".java";
		String content = Files.readString(Paths.get(path));
		Matcher m = pattern.matcher(content);
		var features = new HashMap<String, String>();
		while (m.find()) {
			String fs = m.group(1);
			String test = m.group(2);
			features.put(test, fs);
		}
		return features;
	}
	
}
