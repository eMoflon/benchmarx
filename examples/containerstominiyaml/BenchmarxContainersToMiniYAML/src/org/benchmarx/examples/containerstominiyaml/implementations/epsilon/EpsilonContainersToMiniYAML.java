package org.benchmarx.examples.containerstominiyaml.implementations.epsilon;

import java.io.IOException;
import java.util.function.Consumer;

import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.containerstominiyaml.comparators.CompositionComparator;
import org.benchmarx.examples.containerstominiyaml.comparators.MiniYAMLComparator;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;

import containers.Composition;
import containers.ContainersFactory;
import uk.ac.york.ttc.containers.transformations.ContainersToMiniYAML;
import uk.ac.york.ttc.containers.transformations.MergingContainersToMiniYAML;
import uk.ac.york.ttc.containers.transformations.MiniYAMLToContainers;

public class EpsilonContainersToMiniYAML extends BXToolForEMF<Composition, miniyaml.Map, Decisions> {
	
	private ResourceSet set = new ResourceSetImpl();
	private Resource source;
	private Resource target;

	private Configurator<Decisions> conf;
	private Configurator<Decisions> defaultConf;
	
	private static final String RESULTPATH = "results/epsilon";
	
	public EpsilonContainersToMiniYAML() {
		super(new CompositionComparator(), new MiniYAMLComparator());
	}
	
	@Override
	public String getName() {
		return "Epsilon";
	}
	
	/**
	 * Initiates a synchronisation between a source and a target model. The Epsilon Transformation is
	 * initialised and empty source and target models are created.
	 *
	 * Finally a FamilyRegister is added to the source model and an initial forward transformation is issued.
	 */
	@Override
	public void initiateSynchronisationDialogue() {
		// Fix default preferences (which can be overwritten)
		setConfigurator(new Configurator<Decisions>());			

		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
			Resource.Factory.Registry.DEFAULT_EXTENSION,
			new XMIResourceFactoryImpl());

		source = set.createResource(URI.createURI("sourceModel.containers"));
		target = set.createResource(URI.createURI("targetModel.miniyaml"));

		Composition compositionRoot = ContainersFactory.eINSTANCE.createComposition();
		source.getContents().add(compositionRoot);

		// Fix default preferences (which can be overwritten)
		setConfigurator(new Configurator<Decisions>());
		
		// perform batch to establish consistent starting state
		try {
			new ContainersToMiniYAML().run(new InMemoryEmfModel(source), new InMemoryEmfModel(target));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Perform an edit delta on the target model and propagate the change to the source model
	 * 
	 * @param edit : the source edit delta
	 */
	@Override
	public void performAndPropagateTargetEdit(Consumer<miniyaml.Map> edit) {
		edit.accept(getTargetModel());

		try {
			source.getContents().clear();
			new MiniYAMLToContainers().run(new InMemoryEmfModel(target), new InMemoryEmfModel(source));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Perform an edit delta on the source model and propagate the change to the target model
	 * 
	 * @param edit : the source edit delta
	 */
	@Override
	public void performAndPropagateSourceEdit(Consumer<Composition> edit) {
		edit.accept(getSourceModel());

		Resource rInterim = new ResourceImpl();
		Resource rTempMerged = new ResourceImpl();
		
		try {
			new ContainersToMiniYAML().run(new InMemoryEmfModel(source), new InMemoryEmfModel(rInterim));
			new MergingContainersToMiniYAML().run(new InMemoryEmfModel(rInterim), new InMemoryEmfModel(target), new InMemoryEmfModel(rTempMerged));

			target.getContents().clear();
			target.getContents().addAll(rTempMerged.getContents());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Composition getSourceModel() {
		return (Composition) source.getContents().get(0);
	} 

	@Override
	public miniyaml.Map getTargetModel() {
		return (miniyaml.Map) target.getContents().get(0);
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		if (defaultConf == null) {
			defaultConf = configurator;
		}
		conf = configurator;
	}

	/**
	 * Allows to save the current state of the source and target models
	 * 
	 * @param name : Filename 
	 */
	@Override
	public void saveModels(String name) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "Composition.xmi");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "MiniYAML.xmi");
		Resource resSource = set.createResource(srcURI);
		Resource resTarget = set.createResource(trgURI);
		
		EObject colSource = EcoreUtil.copy(getSourceModel());
		EObject colTarget = EcoreUtil.copy(getTargetModel());
		
		resSource.getContents().add(colSource);
		resTarget.getContents().add(colTarget);
		
		try {
			resSource.save(null);
			resTarget.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	/**
	 * Perform an edit operation on the target model, without propagating the change to the source model
	 * 
	 * @param edit : the edit delta
	 */
	@Override
	public void performIdleTargetEdit(Consumer<miniyaml.Map> edit) {
		edit.accept(getTargetModel());
	}

	/**
	 * Perform an edit operation on the source model, without propagating the change to the target model
	 * 
	 * @param edit : the edit delta
	 */
	@Override
	public void performIdleSourceEdit(Consumer<Composition> edit) {
		edit.accept(getSourceModel());
	}

}
