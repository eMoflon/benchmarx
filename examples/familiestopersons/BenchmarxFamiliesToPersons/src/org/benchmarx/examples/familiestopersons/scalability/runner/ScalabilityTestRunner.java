package org.benchmarx.examples.familiestopersons.scalability.runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ScalabilityTestRunner {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

	private final static int TIMEOUT_SECONDS = 60;

	protected final Class<? extends BenchTestcase> testcaseClass;
	protected final List<String> jvmArgs;
	protected final String[] execArgs;
	private File currentLogFile;

	public ScalabilityTestRunner(Class<? extends BenchTestcase> testcaseClass, List<String> jvmArgs, String[] execArgs) {
		this.testcaseClass = testcaseClass;
		this.jvmArgs = jvmArgs;
		this.execArgs = execArgs;
	}

	public BenchEntry run() throws Exception {
		Process process = execute(testcaseClass, jvmArgs, Arrays.asList(execArgs));
		InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
		BufferedReader reader = new BufferedReader(inputStreamReader);

		if (!process.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
			// count timeouts and restart repetition
			terminateProcess(process);
			throw new TimeoutException();
		}

		if (process.exitValue() != 0) {
			StringBuilder b = new StringBuilder();
			String read = reader.readLine();
			while (read != null) {
				b.append(read);
				b.append("\n");
				read = reader.readLine();
			}
			System.err.println(b);
			// count exceptions and restart repetition if one is detected
			terminateProcess(process);
			throw new IllegalStateException("Errors during execution");
		}

		// clean up log file if it is empty
		if (currentLogFile.length() == 0) {
			currentLogFile.delete();
		}

		StringBuilder b = new StringBuilder();
		String read = reader.readLine();
		while (read != null) {
			b.append(read);
			b.append("\n");
			read = reader.readLine();
		}

		return new BenchEntry(b.toString());
	}

	private void terminateProcess(Process process) throws InterruptedException {
		process.destroy();
		int counter = 0;
		while (process.isAlive()) {
			Thread.sleep(10);
			counter++;
			if (counter >= 100)
				process.destroyForcibly();
		}
	}

	protected Process execute(Class<?> clazz, List<String> jvmArgs, List<String> args)
			throws IOException, InterruptedException {
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = clazz.getName();

		// create log file and redirect the error stream to it
		String logFolderPath = clazz.getProtectionDomain().getCodeSource().getLocation().getPath().toString()
				.replace("bin/", "") + "log/";
		File logFolder = new File(logFolderPath);
		logFolder.mkdirs();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		File logFile = new File(logFolderPath + "log_" + args + DATE_FORMAT.format(timestamp) + ".txt");
		if (!logFile.exists())
			logFile.createNewFile();
		currentLogFile = logFile;

		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.addAll(jvmArgs);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.addAll(args);
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.redirectError(logFile);
		Process process = builder.start();
		return process;
	}

}