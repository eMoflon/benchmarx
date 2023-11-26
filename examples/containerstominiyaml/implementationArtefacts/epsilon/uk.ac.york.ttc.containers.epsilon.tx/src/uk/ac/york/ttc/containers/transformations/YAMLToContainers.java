package uk.ac.york.ttc.containers.transformations;

import java.io.File;

import uk.ac.york.ttc.miniyaml.model2yaml.MiniYAMLConverter;

/**
 * Java encapsulation of a transformation from a <code>docker-compose.yml</code>
 * file to a Containers model. 
 */
public class YAMLToContainers implements ITransformation {

	@Override
	public void run(File source, File target) throws Exception {
		File fMiniYAML = File.createTempFile("interim", ".miniyaml");

		try {
			new MiniYAMLConverter().convertToModel(source, fMiniYAML);
			new MiniYAMLToContainers().run(fMiniYAML, target);
		} finally {
			fMiniYAML.delete();
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Required arguments: input.yml output.containers");
			System.exit(1);
		}

		final File fInput = new File(args[0]);
		final File fOutput = new File(args[1]);
		try {
			new YAMLToContainers().run(fInput, fOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
