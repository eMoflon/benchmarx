package uk.ac.york.ttc.containers.transformations;

import java.io.File;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.etl.EtlModule;

import containers.ContainersPackage;
import miniyaml.MiniyamlPackage;

/**
 * Java encapsulation of the reverse transformation of {@link ContainersToMiniYAML},
 * which extracts the information modelled by the Containers DSL from the MiniYAML
 * model. Since the Composer-like MiniYAML files cover a superset of the information
 * modelled in the Containers DSL, no merging transformation is considered necessary.
 */
public class MiniYAMLToContainers implements ITransformation {

	@Override
	public void run(File fSource, File fTarget) throws Exception {
		EmfModel inputModel = new EmfModel();
		inputModel.setModelFile(fSource.getCanonicalPath());
		inputModel.setMetamodelUri(MiniyamlPackage.eINSTANCE.getNsURI());
		inputModel.setReadOnLoad(true);
		inputModel.load();

		EmfModel outputModel = new EmfModel();
		outputModel.setModelFile(fTarget.getCanonicalPath());
		outputModel.setMetamodelUri(ContainersPackage.eINSTANCE.getNsURI());
		outputModel.setReadOnLoad(false);
		outputModel.load();

		run(inputModel, outputModel);
	}

	public void run(EmfModel inputModel, EmfModel outputModel) throws Exception, EolRuntimeException {
		inputModel.setName("Compose");
		inputModel.setStoredOnDisposal(false);
		outputModel.setName("Containers");
		outputModel.setStoredOnDisposal(true);
		
		EtlModule module = new EtlModule();
		try {
			module.parse(getClass().getResource("miniyaml2containers.etl"));
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
			System.err.println("Required arguments: input.miniyaml output.containers");
			System.exit(1);
		}

		final File fInput = new File(args[0]);
		final File fOutput = new File(args[1]);
		try {
			new MiniYAMLToContainers().run(fInput, fOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
