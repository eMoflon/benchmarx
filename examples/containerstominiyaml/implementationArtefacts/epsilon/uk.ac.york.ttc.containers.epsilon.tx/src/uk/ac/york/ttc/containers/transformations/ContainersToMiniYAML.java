package uk.ac.york.ttc.containers.transformations;

import java.io.File;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.etl.EtlModule;

import containers.ContainersPackage;
import miniyaml.MiniyamlPackage;

/**
 * Java-based encapsulation of a transformation from the Containers DSL to
 * its MiniYAML representation as a Docker Compose-like file. It directly
 * overwrites the target, without merging.
 */
public class ContainersToMiniYAML implements ITransformation {

	@Override
	public void run(File fSource, File fTarget) throws Exception {
		EmfModel inputModel = new EmfModel();
		inputModel.setModelFile(fSource.getCanonicalPath());
		inputModel.setMetamodelUri(ContainersPackage.eINSTANCE.getNsURI());
		inputModel.setReadOnLoad(true);
		inputModel.load();

		EmfModel outputModel = new EmfModel();
		outputModel.setModelFile(fTarget.getCanonicalPath());
		outputModel.setMetamodelUri(MiniyamlPackage.eINSTANCE.getNsURI());
		outputModel.setReadOnLoad(false);
		outputModel.load();

		run(inputModel, outputModel);
	}

	public void run(IModel inputModel, IModel outputModel) throws Exception, EolRuntimeException {
		EtlModule module = new EtlModule();
		try {
			inputModel.setName("Containers");
			inputModel.setStoredOnDisposal(false);
			outputModel.setName("Compose");
			outputModel.setStoredOnDisposal(true);

			module.parse(getClass().getResource("containers2miniyaml.etl"));
			module.getContext().getModelRepository().addModels(inputModel, outputModel);
			module.execute();
		} finally {
			module.getContext().dispose();
			module.getContext().getFrameStack().dispose();
			module.getContext().getModelRepository().dispose();
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
			new ContainersToMiniYAML().run(fInput, fOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
