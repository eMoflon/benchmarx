package uk.ac.york.ttc.containers.transformations;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.ecl.trace.MatchTrace;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eml.EmlModule;
import org.eclipse.epsilon.eol.models.IModel;

import miniyaml.MiniyamlPackage;

/**
 * Java encapsulation of a merging transformation that turns a Containers model into
 * a MiniYAML model, but preserves the information that is not directly modelled by
 * the Containers DSL if the target already exists.
 */
public class MergingContainersToMiniYAML implements ITransformation {

	@Override
	public void run(File source, File target) throws Exception {
		if (!target.exists()) {
			// If the target file does not exist, do a regular ETL tx
			new ContainersToMiniYAML().run(source, target);
			return;
		}

		// Target file exists: do regular tx to temp file and then merge
		final File unmergedModel = File.createTempFile("unmerged", ".xmi");
		new ContainersToMiniYAML().run(source, unmergedModel);

		EmfModel leftModel = new EmfModel();
		leftModel.setModelFile(unmergedModel.getPath());
		leftModel.setMetamodelUri(MiniyamlPackage.eNS_URI);
		leftModel.setReadOnLoad(true);
		leftModel.load();

		EmfModel rightModel = new EmfModel();
		rightModel.setModelFile(target.getCanonicalPath());
		rightModel.setMetamodelUri(MiniyamlPackage.eNS_URI);
		rightModel.setReadOnLoad(true);
		rightModel.load();

		final File mergedModel = File.createTempFile("merged", ".xmi");
		EmfModel targetModel = new EmfModel();
		targetModel.setModelFile(mergedModel.getPath());
		targetModel.setMetamodelUri(MiniyamlPackage.eNS_URI);
		targetModel.setReadOnLoad(false);
		targetModel.load();

		run(leftModel, rightModel, targetModel);

		// Move old model to an .old suffix
		Path oldVersionPath = target.getParentFile().toPath().resolve(target.getName() + ".old");
		Files.move(target.toPath(),	oldVersionPath,	StandardCopyOption.REPLACE_EXISTING);

		// Move new merged model into old location
		Files.move(mergedModel.toPath(), target.toPath());
	}

	public void run(IModel leftModel, IModel rightModel, IModel targetModel) throws Exception {
		EclModule eclModule = new EclModule();
		EmlModule emlModule = new EmlModule();

		try {
			leftModel.setName("Left");
			leftModel.getAliases().clear();
			leftModel.getAliases().add("Source");
			leftModel.setStoredOnDisposal(false);

			rightModel.setName("Right");
			rightModel.getAliases().clear();
			rightModel.getAliases().add("Source");
			rightModel.setStoredOnDisposal(false);

			targetModel.setName("Target");
			targetModel.setStoredOnDisposal(true);

			eclModule.parse(getClass().getResource("compareMiniyaml.ecl"));
			eclModule.getContext().getModelRepository().addModels(leftModel, rightModel);
			eclModule.execute();
			final MatchTrace reducedMatchTrace = eclModule.getContext().getMatchTrace().getReduced();

			emlModule.parse(getClass().getResource("mergeMiniyaml.eml"));
			emlModule.getContext().getModelRepository().addModels(leftModel, rightModel, targetModel);
			emlModule.getContext().setMatchTrace(reducedMatchTrace);
			emlModule.execute();
		} finally {
			eclModule.getContext().dispose();
			eclModule.getContext().getFrameStack().dispose();
			eclModule.getContext().getModelRepository().dispose();

			emlModule.getContext().dispose();
			emlModule.getContext().getFrameStack().dispose();
			emlModule.getContext().getModelRepository().dispose();
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Required arguments: input.containers output.miniyaml");
			System.exit(1);
		}

		final File fInput = new File(args[0]);
		final File fOutput = new File(args[1]);
		try {
			new MergingContainersToMiniYAML().run(fInput, fOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
