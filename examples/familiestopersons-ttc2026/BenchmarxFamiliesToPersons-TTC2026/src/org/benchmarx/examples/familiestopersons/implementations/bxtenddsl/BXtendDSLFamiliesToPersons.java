package org.benchmarx.examples.familiestopersons.implementations.bxtenddsl;

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
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import de.tbuchmann.bxtenddsl.f2p.corrmodel.Transformation;
import de.tbuchmann.bxtenddsl.f2p.trafo.FamiliesToPersons;

public class BXtendDSLFamiliesToPersons extends BXToolForEMF<FamilyRegister, PersonRegister, Decisions> {
	
	public class UUIDXMIResourceImpl extends XMIResourceImpl {
		public UUIDXMIResourceImpl() {
			super();
		}
		
		public UUIDXMIResourceImpl(URI uri) {
			super(uri);
		}
		@Override
		protected boolean useUUIDs() {
			return true;
		}
	}
	
	public class UUIDXMIResourceFactoryImpl extends XMIResourceFactoryImpl {
		@Override
		public Resource createResource(URI uri) {
			return new UUIDXMIResourceImpl(uri);
		}
	}
	
	private ResourceSet set = new ResourceSetImpl();
	protected Resource source;
	protected Resource target;
	protected Resource corr;
	protected FamiliesToPersons f2p;
	
	private Configurator<Decisions> conf;
	private Configurator<Decisions> defaultConf;

	private static final String RESULTPATH = "results/BXtendDSL";

	public BXtendDSLFamiliesToPersons() {
		super(new FamiliesComparator(), new PersonsComparator());
	}

	@Override
	public String getName() {
		return "BXtendDSL Synch";
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

		// delete FamiliesMap
		//Families2personsTransformation.familiesMap.clear();
		
		source = set.createResource(URI.createURI("sourceModel.family"));
		target = set.createResource(URI.createURI("targetModel.person"));
		corr = set.createResource(URI.createURI("corrModel.corr"));
		FamilyRegister familiesRoot = FamiliesFactory.eINSTANCE.createFamilyRegister();
		source.getContents().add(familiesRoot);
		f2p = new FamiliesToPersons(source, target, corr);
		// Fix default preferences (which can be overwritten)
		setConfigurator(new Configurator<Decisions>().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
				.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true));

		// perform batch to establish consistent starting state
		f2p.sourceToTarget();
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
//		f2p.configure(new ConfigurableTargetToSourceDecision(!conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW),
//				conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD), false, false));
		f2p.setOption(Decisions.PREFER_EXISTING_FAMILY_TO_NEW.name(), conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW));
		f2p.setOption(Decisions.PREFER_CREATING_PARENT_TO_CHILD.name(), conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD));
		f2p.targetToSource();
		//f2p.cleanUp();
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
//		f2p.configure(new ConfigurableTargetToSourceDecision(!conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW),
//				conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD), false, false));
		f2p.sourceToTarget();
		//f2p.cleanUp();
	}				

	@Override
	public FamilyRegister getSourceModel() {
		return (FamilyRegister) source.getContents().get(0);
	}

	@Override
	public PersonRegister getTargetModel() {
		return (PersonRegister) target.getContents().get(0);
	}
	
	public Transformation getCorrModel() {
		return (Transformation) corr.getContents().get(0);
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
		URI corrURI = URI.createFileURI(RESULTPATH + "/" + name + "Corr.xmi");
		Resource resSource = set.createResource(srcURI);
		Resource resTarget = set.createResource(trgURI);
		Resource resCorr = set.createResource(corrURI);

		EObject colSource = EcoreUtil.copy(getSourceModel());
		EObject colTarget = EcoreUtil.copy(getTargetModel());
		EObject colCorr = EcoreUtil.copy(getCorrModel());

		resSource.getContents().add(colSource);
		resTarget.getContents().add(colTarget);
		resCorr.getContents().add(colCorr);

		try {
			resSource.save(null);
			resTarget.save(null);
			resCorr.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEditOp,
			Supplier<IEdit<PersonRegister>> targetEditOp) {
		sourceEditOp.get();
		targetEditOp.get();
		//f2pt.updateFamiliesMap();
//		f2p.configure(new ConfigurableTargetToSourceDecision(!conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW),
//				conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD), false, false));
		f2p.setOption(Decisions.PREFER_EXISTING_FAMILY_TO_NEW.name(), conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW));
		f2p.setOption(Decisions.PREFER_CREATING_PARENT_TO_CHILD.name(), conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD));
		f2p.synch();
		//f2p.cleanUp();
		//f2p.targetToSource();
		
	}
	
	@Override
	public void noPrecondition() {
		//f2pt.updateFamiliesMap();
	}

}
