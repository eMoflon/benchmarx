package uk.ac.york.ttc.containers.transformations;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import uk.ac.york.ttc.miniyaml.model2yaml.MiniYAMLConverter;

/**
 * Java encapsulation of a transformation from the Containers DSL to
 * YAML. If the target YAML exists, it will merge the results of the
 * transformation with it. The containers and volumes from Containers
 * DSL will take precedence over those of the existing YAML.
 */
public class MergingContainersToYAML implements ITransformation {

	@Override
	public void run(File source, File target) throws Exception {
		final File fMiniYAML = File.createTempFile("interim", ".miniyaml");
		final MiniYAMLConverter miniYAMLConverter = new MiniYAMLConverter();
		if (target.exists()) {
			miniYAMLConverter.convertToModel(target, fMiniYAML);
		} else {
			fMiniYAML.delete();
		}

		new MergingContainersToMiniYAML().run(source, fMiniYAML);

		final File fTempYAML = File.createTempFile("final", ".yml");
		miniYAMLConverter.convertToYAML(fMiniYAML, fTempYAML);

		if (target.exists()) {
			Files.move(target.toPath(),
				target.getParentFile().toPath().resolve(target.getName() + ".old"),
				StandardCopyOption.REPLACE_EXISTING);
		}
		Files.move(fTempYAML.toPath(), target.toPath());
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Required arguments: input.containers output.yml");
			System.exit(1);
		}

		final File fInput = new File(args[0]);
		final File fOutput = new File(args[1]);
		try {
			new MergingContainersToYAML().run(fInput, fOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
