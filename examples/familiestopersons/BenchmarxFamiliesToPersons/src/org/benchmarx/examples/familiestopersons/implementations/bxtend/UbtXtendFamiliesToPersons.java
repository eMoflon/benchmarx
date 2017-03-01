package org.benchmarx.examples.familiestopersons.implementations.bxtend;

import java.util.function.Consumer;

import org.benchmarx.BXToolForEMF;
import org.benchmarx.Configurator;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import bitrafo.eval.familyperson.rules.decisions.ConfigurableTargetToSourceDecision;
import bitrafo.eval.familyperson.rules.Family2PersonTransformation;

public class UbtXtendFamiliesToPersons extends BXToolForEMF<FamilyRegister, PersonRegister, Decisions> {
	
	private ResourceSet set = new ResourceSetImpl();
	private Resource source;
	private Resource target;
	private Resource corr;
	private Family2PersonTransformation f2pt;
	private Configurator<Decisions> conf;
	private Configurator<Decisions> defaultConf;
	
	public UbtXtendFamiliesToPersons() {
		super(new FamiliesComparator(), new PersonsComparator());
	}
	
	@Override
	public String getName() {
		return "UBT Xtend";
	}
	
	@Override
	public void initiateSynchronisationDialogue() {
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("family", new UUIDResourceFactoryImpl());
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("person", new UUIDResourceFactoryImpl());
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("corr", new UUIDResourceFactoryImpl());
		
		//set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new UUIDResourceFactoryImpl());
		
		source = set.createResource(URI.createURI("sourceModel.family"));
		target = set.createResource(URI.createURI("targetModel.person"));
		corr = set.createResource(URI.createURI("corrModel.corr"));
		FamilyRegister familiesRoot = FamiliesFactory.eINSTANCE.createFamilyRegister();
		source.getContents().add(familiesRoot);
		f2pt = new Family2PersonTransformation(source, target, corr);
		// Fix default preferences (which can be overwritten)
		setConfigurator(new Configurator<Decisions>()
				.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
			    .makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true));
		
		// perform batch to establish consistent starting state
		f2pt.Family2Person();
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<PersonRegister> edit) {
		edit.accept(getTargetModel());
		f2pt.configure(new ConfigurableTargetToSourceDecision(!conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW), conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD), false, false));
		f2pt.Person2Family();
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<FamilyRegister> edit) {
		edit.accept(getSourceModel());
		f2pt.configure(new ConfigurableTargetToSourceDecision(!conf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW), conf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD), false, false));
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
		if(defaultConf == null)
			defaultConf = configurator;
		conf = configurator;
		try{
			configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW);
		} catch(IllegalArgumentException e) {
			conf.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, defaultConf.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW));
		}
		try{
			configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD);
		} catch(IllegalArgumentException e) {
			conf.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, defaultConf.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD));
		}
	}
	
	/**
	  * An extension of the standard XMIResourceFactory
	  * that allows the creation of XMIResources using UUIDs
	  * @author tbuchmann
	  *
	  */
	public class UUIDResourceFactoryImpl extends XMIResourceFactoryImpl {

	     public UUIDResourceFactoryImpl() {
	         super();
	     }

	     @Override
	     public Resource createResource(URI uri) {
	         return new UUIDXMIResourceImpl(uri);
	     }
	}

	/**
	  * A simple extension of the standard XMIResource
	  * providing UUIDs.
	  *
	  * usage: Register UUIDResourceFactoryImpl
	  * ResourceSet set = new ResourceSetImpl();
	  *
	set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi",
	new UUIDResourceFactoryImpl());
	  *
	  * @author tbuchmann
	  *
	  */
	public class UUIDXMIResourceImpl extends XMIResourceImpl implements Resource {

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

	@Override
	public void saveModels(String name) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void performTargetEdit(Consumer<PersonRegister> edit) {
		edit.accept(getTargetModel());
	}

	@Override
	public void performSourceEdit(Consumer<FamilyRegister> edit) {
		edit.accept(getSourceModel());
	}
}
