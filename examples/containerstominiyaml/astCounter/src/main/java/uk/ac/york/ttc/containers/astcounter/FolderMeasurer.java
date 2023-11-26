package uk.ac.york.ttc.containers.astcounter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.eml.EmlModule;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.etl.EtlModule;

public class FolderMeasurer {

	private final static Map<String, IFileMeasurer> measurersByExtension = new HashMap<>();

	static {
		measurersByExtension.put("java", new JavaMeasurer());
		measurersByExtension.put("etl", new EpsilonMeasurer(EtlModule::new));
		measurersByExtension.put("eml", new EpsilonMeasurer(EmlModule::new));
		measurersByExtension.put("ecl", new EpsilonMeasurer(EclModule::new));
		measurersByExtension.put("eol", new EpsilonMeasurer(EolModule::new));
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage: java -jar counter.jar [path to source folder]");
			System.exit(1);
		}

		final File folder = new File(args[0]);
		final FolderMeasurer folderMeasurer = new FolderMeasurer();
		Map<String, Integer> totalsByExtension = folderMeasurer.measure(folder);

		int total = 0;
		for (Entry<String, Integer> entry : totalsByExtension.entrySet()) {
			printLine(entry.getKey(), entry.getValue());
			total += entry.getValue();
		}
		printLine("total", total);
	}

	private static void printLine(String label, int value) {
		System.out.println(String.format("%s\t%d", label, value));
	}

	public Map<String, Integer> measure(File folder) throws Exception {
		Map<String, Integer> results = new TreeMap<>();

		Files.walkFileTree(folder.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				var result = super.visitFile(file, attrs);

				final String filename = file.getFileName().toString();
				int idxDot = filename.lastIndexOf('.');
				if (idxDot != -1) {
					final String extension = filename.substring(idxDot + 1);
					var measurer = measurersByExtension.get(extension);
					if (measurer != null) {
						final int fileMeasure = measurer.measure(file.toFile());
						results.compute(extension, (k, v) -> v == null ? fileMeasure : v + fileMeasure);
					} else {
						throw new IllegalArgumentException(String.format("Unknown extension '%s'", extension));
					}
				}

				return result;
			}
		});

		return results;
	}

}
