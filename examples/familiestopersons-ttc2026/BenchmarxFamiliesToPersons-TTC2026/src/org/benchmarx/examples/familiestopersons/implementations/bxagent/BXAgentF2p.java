package org.benchmarx.examples.familiestopersons.implementations.bxagent;

import java.io.IOException;
import java.util.function.Supplier;

import org.benchmarx.config.Configurator;
import org.benchmarx.edit.IEdit;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.google.common.collect.BiMap;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import de.tbuchmann.bxagent.f2p.Families2PersonsTransformation;
import dev.emtagent.correspondence.CorrespondenceModel;
import dev.emtagent.correspondence.SyncConflictPolicy;
import dev.emtagent.correspondence.TransformationContext;

public class BXAgentF2p extends BXToolForEMF<FamilyRegister, PersonRegister, Decisions> {
	private ResourceSet set = new ResourceSetImpl();
	protected Resource source;
	protected Resource target;
	protected Resource corr;	
	private Configurator<Decisions> conf;
	private Configurator<Decisions> defaultConf;
	private Families2PersonsTransformation f2pt;

	private static final String RESULTPATH = "results/BXagent";
	
	public BXAgentF2p() {
		super(new FamiliesComparator(), new PersonsComparator());
	}
	
	@Override
	public String getName() {
		return "BXagent";
	}

	/**
	 * Initiates a synchronization between a source and a target model. The BXtend
	 * Transformation is initialized and empty source, target and correspondence
	 * models are created. Finally a FamilyRegister is added to the source model and
	 * an initial forward transformation is issued to create a corresponding
	 * PersonRegister.
	 */
	@Override
	public void initiateSynchronisationDialogue() {
		// Fix default preferences (which can be overwritten)
		setConfigurator(new Configurator<Decisions>().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
				.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true));

		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("family", new XMIResourceFactoryImpl());
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("person", new XMIResourceFactoryImpl());
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("corr", new XMIResourceFactoryImpl());
		
		source = set.createResource(URI.createURI("sourceModel.family"));
		target = set.createResource(URI.createURI("targetModel.person"));
		//corr = set.createResource(URI.createURI("corrModel.corr"));
		FamilyRegister familiesRoot = FamiliesFactory.eINSTANCE.createFamilyRegister();
		source.getContents().add(familiesRoot);
		PersonRegister personsRoot = Persons.PersonsFactory.eINSTANCE.createPersonRegister();
		target.getContents().add(personsRoot);
		// Fix default preferences (which can be overwritten)
		setConfigurator(new Configurator<Decisions>().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
				.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true));
		//f2pt = new Families2PersonsTransformation();

		// perform batch to establish consistent starting state
		Families2PersonsTransformation.transform(source, target);
		org.eclipse.emf.common.util.URI corrURI = CorrespondenceModel.deriveCorrespondenceURI(
				source.getURI(), target.getURI());
		corr = CorrespondenceModel.loadOrCreate(corrURI, set);
		
	}
	
	/**
	 * Perform an edit delta on the target model and propagate the change to the
	 * source model
	 * 
	 * @param edit : the source edit delta
	 */
	@Override
	public void performAndPropagateTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		edit.get();
//		f2pt.configure(new ConfigurableTargetToSourceDecision(!conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW),
//				conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD), false, false));
		Families2PersonsTransformation.Options options = 
				new Families2PersonsTransformation.Options(conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW),
						conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD));
		Families2PersonsTransformation.transformBack(target, source, corr, options);
	}

	/**
	 * Perform an edit delta on the source model and propagate the change to the
	 * target model
	 * 
	 * @param edit : the source edit delta
	 */
	@Override
	public void performAndPropagateSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		edit.get();
//		f2pt.configure(new ConfigurableTargetToSourceDecision(!conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW),
//				conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD), false, false));
		Families2PersonsTransformation.transform(source, target, corr);
	}				

	@Override
	public FamilyRegister getSourceModel() {
		return (FamilyRegister) source.getContents().get(0);
	}

	@Override
	public PersonRegister getTargetModel() {
		return (PersonRegister) target.getContents().get(0);
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		if (defaultConf == null)
			defaultConf = configurator;
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
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "Family.xmi");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "Person.xmi");
		Resource resSource = set.createResource(srcURI);
		Resource resTarget = set.createResource(trgURI);
		Resource corrRes = set.createResource(URI.createFileURI(RESULTPATH + "/" + name + "Corr.xmi"));

		EObject colSource = EcoreUtil.copy(getSourceModel());
		EObject colTarget = EcoreUtil.copy(getTargetModel());
		EObject colCorr = EcoreUtil.copy(corr.getContents().get(0));

		resSource.getContents().add(colSource);
		resTarget.getContents().add(colTarget);
		corrRes.getContents().add(colCorr);

		try {
			resSource.save(null);
			resTarget.save(null);
			corrRes.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEditOp,
			Supplier<IEdit<PersonRegister>> targetEditOp) {
		sourceEditOp.get();
		targetEditOp.get();
//		BiMap<EObject, EObject> idx = CorrespondenceModel.buildIndex(corr);
//		  System.out.println("corrIndex entries: " + idx.size());
		Families2PersonsTransformation.Options options = 
				new Families2PersonsTransformation.Options(conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW),
						conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD));
		saveModels("Pre_ConcurrentEdit");
		Families2PersonsTransformation.sync(source, target, corr, 
				SyncConflictPolicy.TARGET_WINS, 
				TransformationContext.DeletionPolicy.CASCADE, 
				options);
	}

}
