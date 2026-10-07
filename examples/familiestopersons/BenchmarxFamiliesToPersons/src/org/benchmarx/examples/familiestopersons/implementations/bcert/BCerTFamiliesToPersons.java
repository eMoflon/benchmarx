package org.benchmarx.examples.familiestopersons.implementations.bcert;



import java.util.function.Supplier;

import org.benchmarx.config.Configurator;
import org.benchmarx.edit.IEdit;
import org.benchmarx.emf.BXToolForEMF;

import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;

import fr.liglab.bcert.ttc.familiespersons.FamiliesToPersonsTransformation;


import pivot.PivotPackage;
import pivot.PivotFactory;
import pivot.Pivot;
import pivot.Strategy;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Families.FamiliesPackage;

import Persons.PersonsFactory;
import Persons.PersonsPackage;
import Persons.PersonRegister;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;


public class BCerTFamiliesToPersons extends BXToolForEMF<FamilyRegister, PersonRegister, Decisions> {

	
	private final FamiliesToPersonsTransformation transformation;
	
	private Configurator<Decisions> configurator;
	private Pivot pivot;

	private static final String RESULT_PATH = "results/BCerT";

	public BCerTFamiliesToPersons() throws RuntimeException {
		super(new FamiliesComparator(), new PersonsComparator());
		
		try {
			this.transformation = new FamiliesToPersonsTransformation();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public String getName() {
		return "BCerT";
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		this.configurator = configurator;
	}

	
	@Override
	public void initiateSynchronisationDialogue() {
		
		ResourceSet sandbox	= new ResourceSetImpl();
		
		sandbox.getPackageRegistry().put(PivotPackage.eNS_URI, PivotPackage.eINSTANCE);
		sandbox.getPackageRegistry().put(FamiliesPackage.eNS_URI, FamiliesPackage.eINSTANCE);
		sandbox.getPackageRegistry().put(PersonsPackage.eNS_URI, PersonsPackage.eINSTANCE);
		
		sandbox.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		
		this.pivot = PivotFactory.eINSTANCE.createPivot();
		this.pivot.setName("test");
		this.pivot.setFamilyModel(FamiliesFactory.eINSTANCE.createFamilyRegister());
		this.pivot.setPersonModel(PersonsFactory.eINSTANCE.createPersonRegister());
		this.pivot.setFAMILY_TO_NEW(true);
		this.pivot.setPARENT_TO_CHILD(true);
		
		if (configurator != null) {
			this.pivot.setFAMILY_TO_NEW(configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW));
			this.pivot.setPARENT_TO_CHILD(configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD));
		}

		sandbox.createResource(URI.createURI("test.pivot")).getContents().add(this.pivot);
		sandbox.createResource(URI.createURI("test.persons")).getContents().add(this.pivot.getPersonModel());
		sandbox.createResource(URI.createURI("test.families")).getContents().add(this.pivot.getFamilyModel());
		
	}

	@Override
	public FamilyRegister getSourceModel() {
		return pivot.getFamilyModel();
	}

	@Override
	public PersonRegister getTargetModel() {
		return pivot.getPersonModel();
	}

	private void propagate(Strategy strategy) {
		
		if (configurator != null) {
			this.pivot.setFAMILY_TO_NEW(configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW));
			this.pivot.setPARENT_TO_CHILD(configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD));
		}
		
		pivot.setStrategie(strategy);
		
		try {
			transformation.apply(pivot,10000,50000,50000);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@Override
	public void performAndPropagateSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		edit.get();
		propagate(Strategy.FWD);
	}

	@Override
	public void performAndPropagateTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		edit.get();
		propagate(Strategy.BWD);
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEditOp, Supplier<IEdit<PersonRegister>> targetEditOp) {
		sourceEditOp.get();
		targetEditOp.get();
		
		propagate(Strategy.BWD_FWD);
	}



	@Override
	public void saveModels(String name) {
		
		ResourceSet sandbox	= new ResourceSetImpl();
		
		sandbox.getPackageRegistry().put(FamiliesPackage.eNS_URI, FamiliesPackage.eINSTANCE);
		sandbox.getPackageRegistry().put(PersonsPackage.eNS_URI, PersonsPackage.eINSTANCE);
		
		sandbox.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

		Resource source = sandbox.createResource(URI.createURI(RESULT_PATH + "/" + name + "Family.xmi\""));
		Resource target = sandbox.createResource(URI.createURI(RESULT_PATH + "/" + name + "Person.xmi\""));
		

		try {
			
			source.getContents().add(EcoreUtil.copy(getSourceModel()));
			source.save(null);
			
			target.getContents().add(EcoreUtil.copy(getTargetModel()));
			target.save(null);
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}


}
