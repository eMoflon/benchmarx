package containerstominiyaml.yamtl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import containers.Composition;
import containers.ContainersFactory;
import containers.ContainersPackage;
import containerstominiyaml.comparators.BXToolForEMF;
import containerstominiyaml.comparators.Comparator;
import containerstominiyaml.comparators.CompositionComparator;
import containerstominiyaml.comparators.Configurator;
import containerstominiyaml.testsuite.Decisions;
import emf_syncer.EMFSyncer;
import emf_syncer.EMFSyncerParameter_EMF;
import miniyaml.MiniyamlPackage;
import yamtl.core.YAMTLModule;
import yamtl.core.YAMTLModule.ExecutionMode;
import yamtl.core.YAMTLModule.IncrementalGranularity;
import yamtl.groovy.YAMTLGroovyExtensions;

public class YAMTLContainersToMiniYAML_BXTool extends BXToolForEMF<Composition, miniyaml.Map, Decisions> {
	
	private final String name;
	private Resource source;
	private Resource target;
	private Resource target2;
	private Resource trafoTarget;

	private Configurator<Decisions> conf;
	private Configurator<Decisions> defaultConf;
	
	private YAMTLModule xformForward;
	private YAMTLModule xformBackward;
	private EMFSyncer syncerMiniYAML;
	private EMFSyncer syncerContainers;
	
	private static final String RESULTPATH = "results/yamtl";
	
	public YAMTLContainersToMiniYAML_BXTool(String name, Comparator<miniyaml.Map> yamlComparator) {
		super(new CompositionComparator(), yamlComparator);
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	private static final ResourceSet createResourceSet() {
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
			Resource.Factory.Registry.DEFAULT_EXTENSION,
			new XMIResourceFactoryImpl()
		);
		return rs;
	}
	
	private static final Resource copy(Resource originalRes, String path, String fileName) {
		File fTempSource;
		Resource copyRes = null;
		try {
			fTempSource = File.createTempFile(path, fileName);
			fTempSource.deleteOnExit();
			copyRes = createResourceSet().createResource(URI.createFileURI(fTempSource.getPath()));
			for(var root: originalRes.getContents()) {
				copyRes.getContents().add(EcoreUtil.copy(root));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return copyRes;
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

		try {
			File fTempSource = File.createTempFile("sourceModel", ".containers");
			fTempSource.deleteOnExit();
			source = createResourceSet().createResource(URI.createFileURI(fTempSource.getPath()));

			File fTempTarget = File.createTempFile("targetModel", ".miniyaml");
			fTempTarget.deleteOnExit();
			target = createResourceSet().createResource(URI.createFileURI(fTempTarget.getPath()));
			
			Composition compositionRoot = ContainersFactory.eINSTANCE.createComposition();
			source.getContents().add(compositionRoot);
			
			
			// Fix default preferences (which can be overwritten)
			setConfigurator(new Configurator<Decisions>());

			/*
			 * YAMTL configuration
			 */
			xformForward = new YAMTLContainersToMiniYAML(ContainersPackage.eINSTANCE, MiniyamlPackage.eINSTANCE);
			YAMTLGroovyExtensions.init(xformForward);
			xformForward.setExecutionMode(ExecutionMode.INCREMENTAL);
			xformForward.setIncrementalGranularity(IncrementalGranularity.ELEMENT);
			xformForward.adviseWithinThisNamespaceExpressions(List.of("containers..*"));
			xformForward.loadInputResources(Map.of("cmm", source));
			xformForward.execute();
			xformForward.adaptInputModel("cmm");
			
			/*
			 * EMFSyncer configuration
			 */
			syncerMiniYAML = new EMFSyncer();
			syncerMiniYAML.enableEagerMode();
			var miniyamlDomain = new EMFSyncerParameter_EMF("miniyaml", Map.of("pk", MiniyamlPackage.eINSTANCE));
	        syncerMiniYAML.setSourceModelHandler( miniyamlDomain );
	        syncerMiniYAML.setTargetModelHandler( miniyamlDomain );
	        // load the model
	        syncerMiniYAML.setSourceModel(xformForward.getOutputModel("ymm").getContents().stream().map(o -> (Object)o).collect(Collectors.toList()));
	        syncerMiniYAML.setTargetModel(target.getContents().stream().map(o -> (Object)o).collect(Collectors.toList()));
	        // syncing
	        syncerMiniYAML.match();
	        syncerMiniYAML.forwardSync();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	
	/**
	 * Perform an edit delta on the source model and propagate the change to the target model
	 * 
	 * @param edit : the source edit delta
	 */
	@Override
	public void performAndPropagateSourceEdit(Consumer<Composition> edit) {
		edit.accept(getSourceModel());

		xformForward.propagateDelta("cmm");

		// there may have been changes in the target that must not be deleted by trafo
		syncerMiniYAML.match();
		// perform merge
		syncerMiniYAML.forwardSync();
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
	 * Perform an edit delta on the target model and propagate the change to the source model
	 * 
	 * @param edit : the source edit delta
	 */
	@Override
	public void performAndPropagateTargetEdit(Consumer<miniyaml.Map> edit) {
		edit.accept(getTargetModel());
        
		File fTempTarget;
		try {
			// create a temp resource for target
			fTempTarget = File.createTempFile("targetModel", ".miniyaml");
			fTempTarget.deleteOnExit();
			target2 = createResourceSet().createResource(URI.createFileURI(fTempTarget.getPath()));
			target2.getContents().add(getTargetModel());
			
			// clear the source resource
			source.getContents().clear();
			
			/*
			 * YAMTL configuration
			 */
			xformBackward = new MiniYAML2Containers(MiniyamlPackage.eINSTANCE, ContainersPackage.eINSTANCE);
			YAMTLGroovyExtensions.init(xformBackward);
			xformBackward.loadInputResources(Map.of("ymm", target2));
			xformBackward.execute();
			var model = xformBackward.getOutputModel("cmm").getContents();
			source.getContents().addAll(model);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public Composition getSourceModel() {
		return (Composition) source.getContents().get(0);
	} 

	@Override
	public miniyaml.Map getTargetModel() {
		return (miniyaml.Map) syncerMiniYAML.getTargetModel().get(0);
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


	
	
	
	public void saveModel(List<EObject> rootList, String path) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(path);
		Resource resSource = set.createResource(srcURI);
		
		for (var root: rootList) {
			resSource.getContents().add(EcoreUtil.copy(root));
		}
		
		try {
			resSource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
}
