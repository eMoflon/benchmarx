package org.benchmarx.examples.set2oset.implementations.medini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

import org.apache.commons.io.output.NullOutputStream;
import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.set2oset.testsuite.Decisions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import de.ikv.emf.qvt.EMFQvtProcessorImpl;
import de.ikv.medini.qvt.QVTProcessorConsts;
import org.benchmarx.osets.core.OsetComparator;
import org.benchmarx.sets.core.SetComparator;
import sets.SetsFactory;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;

public class MediniQVTSetToOSet extends BXToolForEMF<sets.MySet, osets.MyOrderedSet, Decisions> {
	private static final String RULESET = "set2oset.qvt";
	// Version as presented in the BX 2017 paper
	// Switch to families2persons.qvt for the version with top level relations only

	private ILog logger;
	private EMFQvtProcessorImpl processorImpl;
	private ResourceSet resourceSet;
	private Resource source;
	private Resource target;
	private String basePath;
	private String transformation;
	private FileReader qvtRuleSet;
	private static final String fwdDir = "orderedSetModel";
	private static final String bwdDir = "setModel";
	
	private static final String RESULTPATH = "results/medini";
	
	@Override
	public String getName() {
		return "MediniQVT";
	}
	
	public MediniQVTSetToOSet() {
		super(new SetComparator(), new OsetComparator());
		
		logger = new OutputStreamLog(new PrintStream(new NullOutputStream())); 
		// logger = new OutputStreamLog(System.err); 
		
		processorImpl = new EMFQvtProcessorImpl(this.logger);
		processorImpl.setProperty(QVTProcessorConsts.PROP_DEBUG, "true");
		basePath = "./src/org/benchmarx/examples/set2oset/implementations/medini/base/";
		
		// Tell the QVT engine, which transformation to execute
		transformation = "set2oset";

		// Tell the QVT engine a directory to work in - e.g. to store the trace (meta)models
		File tracesFile = new File(basePath + "traces/trace.trafo");
		tracesFile.delete();
	}
	
	/**
	 * Initiates a synchronization between a source and a target model. The medini QVT engine is initialized,
	 * the required metamodels are registered and empty source and target models are created.
	 * Finally a FamilyRegister is added to the source model and an initial forward transformation is issued
	 * to create a corresponding PersonRegister.
	 */
	@Override
	public void initiateSynchronisationDialogue() {
		// delete content of traces folder
		File tracesFolder = new File("./src/org/benchmarx/examples/set2oset/implementations/medini/base/traces/");
		final File[] files = tracesFolder.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f != null)
					f.delete();
			}
		}
		
		// Initialise resource set of models
		this.resourceSet = new ResourceSetImpl();
		this.resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
		    Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		// Collect all necessary packages from the metamodel(s)
		Collection<EPackage> metaPackages = new ArrayList<EPackage>();
		this.collectMetaModels(metaPackages);

		// Make these packages known to the QVT engine
		init(metaPackages);
		
		// Create resources for models
		source = resourceSet.createResource(URI.createURI("source.xmi"));
		target = resourceSet.createResource(URI.createURI("target.xmi"));
		
		// Collect the models, which should participate in the transformation.
		// You can provide a list of models for each direction.
		// The models must be added in the same order as defined in your transformation!
		Collection<Collection<Resource>> modelResources = new ArrayList<Collection<Resource>>();
		Collection<Resource> firstSetOfModels = new ArrayList<Resource>();
		Collection<Resource> secondSetOfModels = new ArrayList<Resource>();
		Collection<Resource> thirdSetOfModels = new ArrayList<Resource>();
		modelResources.add(firstSetOfModels);
		modelResources.add(secondSetOfModels);
		modelResources.add(thirdSetOfModels);
		firstSetOfModels.add(source);
		secondSetOfModels.add(target);
		
		URI directory = URI.createFileURI(basePath + "traces");
		this.preExecution(modelResources, directory);
		
		source.getContents().add(SetsFactory.eINSTANCE.createMySet());
		launchFWD();
	}

	private void launchFWD() {
		launch(fwdDir);
	}
	
	private void launchBWD() {
		launch(bwdDir);
	}

	/**
	 * Perform an edit delta on the target model and propagate the change to the source model
	 * 
	 * @param edit : the source edit delta
	 */
	@Override
	public void performAndPropagateTargetEdit(Consumer<osets.MyOrderedSet> edit) {
		edit.accept(getTargetModel());
		launchBWD();
	}

	/**
	 * Perform an edit delta on the source model and propagate the change to the target model
	 * 
	 * @param edit : the source edit delta
	 */
	@Override
	public void performAndPropagateSourceEdit(Consumer<sets.MySet> edit) {
		edit.accept(getSourceModel());
		launchFWD();
	}

	@Override
	public sets.MySet getSourceModel() {
		return (sets.MySet) source.getContents().get(0);
	}

	@Override
	public osets.MyOrderedSet getTargetModel() {
		return (osets.MyOrderedSet) target.getContents().get(0);
	}
	
	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		// the version of the QVT-R script does not support configuration or interatcion
	}

	/**
	 * Provide the information about the metamodels, which are involved in the transformation
	 * 
	 * @param ePackages
	 *            the metamodel packages
	 */
	public void init(Collection<EPackage> ePackages) {
		Iterator<EPackage> iterator = ePackages.iterator();
		while (iterator.hasNext()) {
			this.processorImpl.addMetaModel(iterator.next());
		}
	}

	/**
	 * Before transforming, the engine has to know the model to perform the transformation on, as
	 * well as a directory for the traces to store.
	 * 
	 * @param modelResources
	 *            models for the execution - take care of the right order!
	 * @param workingDirectory
	 *            working directory of the QVT engine
	 */
	public void preExecution(Collection<?> modelResources, URI workingDirectory) {
		this.processorImpl.setWorkingLocation(workingDirectory);
		this.processorImpl.setModels(modelResources);
	}

	/**
	 * Transform a QVT script in a specific direction.
	 * 
	 * @param qvtRuleSet
	 *            the QVT transformation
	 * @param transformation
	 *            name of the transformation
	 * @param direction
	 *            name of the target - must conform to your QVT transformation definition
	 * @throws Throwable
	 */
	public void transform(Reader qvtRuleSet, String transformation, String direction) throws Throwable {
		processorImpl.evaluateQVT(qvtRuleSet, transformation, true, direction, new Object[0], null, this.logger);		
	}

	/**
	 * launch the transformation in the QVT execution engine
	 * 
	 * @param direction : the desired execution direction
	 */
	public void launch(String direction) {
		PrintStream ps = System.out;
		PrintStream ps_err = System.err;
		
		// Load the QVT relations
		try {
			System.setOut(new PrintStream(new NullOutputStream()));
			System.setErr(new PrintStream(new NullOutputStream()));

			qvtRuleSet = new FileReader(basePath + RULESET);
			this.transform(qvtRuleSet, transformation, direction);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
			return;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			System.out.println(throwable.getMessage());
		} finally {		
			System.setOut(ps);
			System.setErr(ps_err);
		}
	}

	/**
	 * Add all metamodel packages of models/qvt script
	 * 
	 * @param metaPackages
	 * @return
	 */
	protected void collectMetaModels(Collection<EPackage> metaPackages) {
		metaPackages.add(sets.SetsPackage.eINSTANCE);
		metaPackages.add(osets.OsetsPackage.eINSTANCE);
	}
	
	/**
	 * Allows to save the current state of the source and target models
	 * 
	 * @param name : Filename 
	 */
	public void saveModels(String name) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "Set.xmi");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "OSet.xmi");
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
	public void performIdleTargetEdit(Consumer<osets.MyOrderedSet> edit) {
		edit.accept(getTargetModel());
	}

	/**
	 * Perform an edit operation on the source model, without propagating the change to the target model
	 * 
	 * @param edit : the edit delta
	 */
	@Override
	public void performIdleSourceEdit(Consumer<sets.MySet> edit) {
		edit.accept(getSourceModel());
	}
}
