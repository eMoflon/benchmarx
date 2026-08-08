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

public class ScalabilityTestRunner {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

	private final static int TIMEOUT_SECONDS = Integer.getInteger("scalability.timeout", 30);

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
		var process = execute(testcaseClass, jvmArgs, Arrays.asList(execArgs));
		var timeout = false;
		var time = System.currentTimeMillis();

		try {
			var inputStreamReader = new InputStreamReader(process.getInputStream());
			var reader = new BufferedReader(inputStreamReader);

			if (!process.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
				timeout = true;
				terminateProcess(process);
			}

			System.out.println("Execution took: " + (double) (System.currentTimeMillis() - time) / 1000.0);

			if (timeout || process.exitValue() != 0) {
				System.out.println("Timeout: " + timeout);
				var b = new StringBuilder();
				var read = reader.readLine();
				while (read != null) {
					b.append(read);
					b.append("\n");
					read = reader.readLine();
				}
				System.err.println(b);
				throw new IllegalStateException("Errors during execution");
			}

			if (currentLogFile != null && currentLogFile.length() == 0) {
				currentLogFile.delete();
			}

			var b = new StringBuilder();
			var read = reader.readLine();
			while (read != null) {
				b.append(read);
				b.append("\n");
				read = reader.readLine();
			}

			System.out.println(b);

			return new BenchEntry(b.toString());
		} finally {
			terminateProcess(process);
		}
	}

	private void terminateProcess(Process process) {
		if (process != null && process.isAlive()) {
			try {
				process.descendants().forEach(ProcessHandle::destroyForcibly);
			} catch (Exception ignored) {
			}
			process.destroyForcibly();
		}
	}

	protected Process execute(Class<?> clazz, List<String> jvmArgs, List<String> args)
			throws IOException, InterruptedException {
		var javaHome = System.getProperty("java.home");
		var javaBin = javaHome + File.separator + "bin" + File.separator + "javaw.exe";
		var classpath = System.getProperty("java.class.path");
		var className = clazz.getName();

		// create log file and redirect the error stream to it
		var logFolderPath = clazz.getProtectionDomain().getCodeSource().getLocation().getPath().toString()
				.replace("bin/", "") + "log/";
		var logFolder = new File(logFolderPath);
		logFolder.mkdirs();
		var timestamp = new Timestamp(System.currentTimeMillis());
		var logFile = new File(logFolderPath + "log_" + args + DATE_FORMAT.format(timestamp) + ".txt");
		if (!logFile.exists())
			logFile.createNewFile();
		currentLogFile = logFile;

		var command = new ArrayList<String>();
		command.add(javaBin);
		command.addAll(jvmArgs);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.addAll(args);
		var builder = new ProcessBuilder(command);
		builder.redirectError(logFile);
		var process = builder.start();
		return process;
	}

}