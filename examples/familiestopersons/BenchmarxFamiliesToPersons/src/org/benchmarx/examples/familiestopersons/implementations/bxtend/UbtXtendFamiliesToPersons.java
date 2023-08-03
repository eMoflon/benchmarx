package org.benchmarx.examples.familiestopersons.implementations.bxtend;

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

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import bitrafo.eval.familyperson.rules.Family2PersonTransformation;
import bitrafo.eval.familyperson.rules.decisions.ConfigurableTargetToSourceDecision;

public class UbtXtendFamiliesToPersons extends BXToolForEMF<FamilyRegister, PersonRegister, Decisions> {
	private ResourceSet set = new ResourceSetImpl();
	private Resource source;
	private Resource target;
	private Resource corr;
	private Family2PersonTransformation f2pt;
	private Configurator<Decisions> conf;
	private Configurator<Decisions> defaultConf;

	private static final String RESULTPATH = "results/BXtend";

	public UbtXtendFamiliesToPersons() {
		super(new FamiliesComparator(), new PersonsComparator());
	}

	@Override
	public String getName() {
		return "BXtend";
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
		corr = set.createResource(URI.createURI("corrModel.corr"));
		FamilyRegister familiesRoot = FamiliesFactory.eINSTANCE.createFamilyRegister();
		source.getContents().add(familiesRoot);
		f2pt = new Family2PersonTransformation(source, target, corr);
		// Fix default preferences (which can be overwritten)
		setConfigurator(new Configurator<Decisions>().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
				.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true));

		// perform batch to establish consistent starting state
		f2pt.Family2Person();
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
		f2pt.configure(new ConfigurableTargetToSourceDecision(!conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW),
				conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD), false, false));
		f2pt.Person2Family();
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
		f2pt.configure(new ConfigurableTargetToSourceDecision(!conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW),
				conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD), false, false));
		f2pt.Family2Person();
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

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEditOp,
			Supplier<IEdit<PersonRegister>> targetEditOp) {
		throw new UnsupportedOperationException("Concurrent edits not supported.");
	}
}
