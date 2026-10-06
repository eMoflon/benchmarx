package org.benchmarx.examples.familiestopersons.scalability.runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class ScalabilityTestRunner {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

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

		var stdoutFuture = CompletableFuture.supplyAsync(() -> {
			var b = new StringBuilder();
			try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				var line = reader.readLine();
				while (line != null) {
					b.append(line).append("\n");
					line = reader.readLine();
				}
			} catch (Exception ignored) {
			}
			return b.toString();
		});

		try {
			if (!process.waitFor(ScalabilityConfig.getTimeoutSeconds(), TimeUnit.SECONDS)) {
				timeout = true;
				terminateProcess(process);
			}

			System.out.println("Execution took: " + (double) (System.currentTimeMillis() - time) / 1000.0);

			var stdoutContent = "";
			try {
				stdoutContent = stdoutFuture.get(2, TimeUnit.SECONDS);
			} catch (Exception ignored) {
			}

			if (timeout || process.exitValue() != 0) {
				System.err.println("Timeout: " + timeout + ", exit code: " + process.exitValue());
				if (!stdoutContent.isBlank()) {
					System.err.println("Process stdout:\n" + stdoutContent);
				}

				if (currentLogFile != null && currentLogFile.exists() && currentLogFile.length() > 0) {
					try {
						var errContent = Files.readString(currentLogFile.toPath());
						System.err.println("Process stderr (log file " + currentLogFile.getAbsolutePath() + "):\n" + errContent);
					} catch (Exception ignored) {
					}
				}
				throw new IllegalStateException("Errors during execution (exit code: " + process.exitValue() + ")");
			}

			if (currentLogFile != null && currentLogFile.length() == 0) {
				currentLogFile.delete();
			}

			System.out.println(stdoutContent);

			return new BenchEntry(stdoutContent);
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
		var javaBin = resolveJavaBinary();
		var classpath = buildClasspath(clazz);
		var className = clazz.getName();

		var logFolderPath = resolveLogFolderPath(clazz);
		var logFolder = new File(logFolderPath);
		if (!logFolder.exists()) {
			logFolder.mkdirs();
		}
		var timestamp = new Timestamp(System.currentTimeMillis());
		var safeArgs = String.join("_", args).replaceAll("[^a-zA-Z0-9_\\-\\.]", "_");
		var logFile = new File(logFolder, "log_" + safeArgs + "_" + DATE_FORMAT.format(timestamp) + ".txt");
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
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
		return builder.start();
	}

	private String resolveJavaBinary() {
		var javaHome = System.getProperty("java.home");
		if (javaHome != null) {
			var binDir = new File(javaHome, "bin");
			var javaExe = new File(binDir, "java.exe");
			if (javaExe.exists()) {
				return javaExe.getAbsolutePath();
			}
			var javawExe = new File(binDir, "javaw.exe");
			if (javawExe.exists()) {
				return javawExe.getAbsolutePath();
			}
			var javaBin = new File(binDir, "java");
			if (javaBin.exists()) {
				return javaBin.getAbsolutePath();
			}
		}
		return "java";
	}

	private String resolveLogFolderPath(Class<?> clazz) {
		try {
			var codeSource = clazz.getProtectionDomain().getCodeSource();
			if (codeSource != null && codeSource.getLocation() != null) {
				var file = new File(codeSource.getLocation().toURI());
				var base = file.isDirectory() ? file.getParentFile() : file.getParentFile().getParentFile();
				if (base != null && base.exists()) {
					return new File(base, "log").getAbsolutePath() + File.separator;
				}
			}
		} catch (Exception ignored) {
		}
		return "log" + File.separator;
	}

	private String buildClasspath(Class<?> clazz) {
		var customClasspath = ScalabilityConfig.getCustomClasspath();
		if (customClasspath != null && !customClasspath.isBlank()) {
			return customClasspath;
		}

		var entries = new LinkedHashSet<String>();

		// 1. Existing System java.class.path
		var existingCp = System.getProperty("java.class.path");
		if (existingCp != null && !existingCp.isBlank()) {
			for (var path : existingCp.split(Pattern.quote(File.pathSeparator))) {
				if (!path.isBlank()) {
					entries.add(new File(path).getAbsolutePath());
				}
			}
		}

		// 2. Class location and discover project dependencies
		try {
			var codeSource = clazz.getProtectionDomain().getCodeSource();
			if (codeSource != null && codeSource.getLocation() != null) {
				var classLoc = new File(codeSource.getLocation().toURI()).getCanonicalFile();
				entries.add(classLoc.getAbsolutePath());

				// If classLoc is .../BenchmarxFamiliesToPersons/bin
				var projectDir = classLoc.isDirectory() ? classLoc.getParentFile() : classLoc.getParentFile().getParentFile();
				if (projectDir != null && projectDir.exists()) {
					var libDir = new File(projectDir, "lib");
					if (libDir.exists()) {
						addJarsRecursively(libDir, entries);
					}

					var examplesDir = projectDir.getParentFile();
					if (examplesDir != null && examplesDir.exists()) {
						addIfExists(new File(examplesDir, "metamodels/Families/bin"), entries);
						addIfExists(new File(examplesDir, "metamodels/Persons/bin"), entries);
						addIfExists(new File(examplesDir, "implementationArtefacts/bcert/pivot/bin"), entries);
						addIfExists(new File(examplesDir, "implementationArtefacts/ibex-integrate/FamiliesSmartEMF/bin"), entries);
						addIfExists(new File(examplesDir, "implementationArtefacts/ibex-integrate/PersonsSmartEMF/bin"), entries);
						addIfExists(new File(examplesDir, "implementationArtefacts/ibex-integrate/FamiliesToPersonsIBeXTGG/bin"), entries);
						addJarsRecursively(new File(examplesDir, "implementationArtefacts/ibex-integrate"), entries);

						var benchmarxRoot = examplesDir.getParentFile();
						if (benchmarxRoot != null && benchmarxRoot.exists()) {
							var coreDir = new File(benchmarxRoot, "core");
							if (coreDir.exists()) {
								addIfExists(new File(coreDir, "Benchmarx/bin"), entries);
							}
						}
					}
				}
			}
		} catch (Exception ignored) {
		}

		return String.join(File.pathSeparator, entries);
	}

	private void addIfExists(File file, Set<String> entries) {
		if (file != null && file.exists()) {
			try {
				entries.add(file.getCanonicalPath());
			} catch (IOException e) {
				entries.add(file.getAbsolutePath());
			}
		}
	}

	private void addJarsRecursively(File dir, Set<String> entries) {
		if (dir == null || !dir.exists()) {
			return;
		}
		var files = dir.listFiles();
		if (files == null) {
			return;
		}
		for (var file : files) {
			if (file.isDirectory()) {
				addJarsRecursively(file, entries);
			} else if (file.getName().endsWith(".jar") && !file.getName().endsWith("-sources.jar")
					&& !file.getName().startsWith("org.eclipse.equinox.common_")) {
				addIfExists(file, entries);
			}
		}
	}
}