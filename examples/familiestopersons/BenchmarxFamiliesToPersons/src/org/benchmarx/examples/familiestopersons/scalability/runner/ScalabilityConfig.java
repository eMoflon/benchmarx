package org.benchmarx.examples.familiestopersons.scalability.runner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScalabilityConfig {

	public static final int DEFAULT_TIMEOUT_SECONDS = 30;
	public static final int DEFAULT_REPEAT_COUNT = 10;
	public static final String DEFAULT_JVM_MAX_MEMORY = "-Xmx32G";
	public static final String DEFAULT_RESULT_FOLDER = "C:/scalability_results";

	public static int getTimeoutSeconds() {
		return Integer.getInteger("scalability.timeout", DEFAULT_TIMEOUT_SECONDS);
	}

	public static int getRepeatCount() {
		return Integer.getInteger("scalability.repeat", DEFAULT_REPEAT_COUNT);
	}

	public static List<String> getJvmArgs() {
		var customJvmArgs = System.getProperty("scalability.jvmargs");
		if (customJvmArgs != null && !customJvmArgs.isBlank()) {
			return Arrays.asList(customJvmArgs.split("\\s+"));
		}
		var customXmx = System.getProperty("scalability.xmx", DEFAULT_JVM_MAX_MEMORY);
		return Collections.singletonList(customXmx);
	}

	public static String getResultFolder() {
		return System.getProperty("scalability.resultfolder", DEFAULT_RESULT_FOLDER);
	}

	public static String getCustomClasspath() {
		return System.getProperty("scalability.classpath");
	}
}
