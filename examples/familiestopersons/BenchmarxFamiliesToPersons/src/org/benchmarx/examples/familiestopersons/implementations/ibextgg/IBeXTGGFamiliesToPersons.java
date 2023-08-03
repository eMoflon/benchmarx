package org.benchmarx.examples.familiestopersons.implementations.ibextgg;

import java.io.IOException;
import java.util.function.Supplier;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.benchmarx.config.Configurator;
import org.benchmarx.edit.IEdit;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.emoflon.ibex.tgg.run.ibextggfamiliestopersons.SYNC_App;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;

public class IBeXTGGFamiliesToPersons extends
		BXToolForEMF<FamilyRegister, PersonRegister, org.benchmarx.examples.familiestopersons.testsuite.Decisions> {

	private SYNC_App sync;

	public IBeXTGGFamiliesToPersons() {
		super(new FamiliesComparator(), new PersonsComparator());

		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.ERROR);
	}

	@Override
	public String getName() {
		return "IBeX-TGG";
	}

	@Override
	public void initiateSynchronisationDialogue() {
		try {
			sync = new SYNC_App();

			// Create initial src and trg models
			FamilyRegister o = FamiliesFactory.eINSTANCE.createFamilyRegister();
			sync.getSourceResource().getContents().add(o);

			sync.forward();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		// Adapt source model
		edit.get();

		// Invoke sync
		try {
			sync.forward();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		// Adapt target model
		edit.get();

		// Invoke sync
		try {
			sync.backward();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performIdleSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		performAndPropagateSourceEdit(edit);
	}

	@Override
	public void performIdleTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		performAndPropagateTargetEdit(edit);
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		sync.setUpdatePolicy(new F2PUpdatePolicy(configurator));
	}

	@Override
	public FamilyRegister getSourceModel() {
		return (FamilyRegister) sync.getSourceResource().getContents().get(0);
	}

	@Override
	public PersonRegister getTargetModel() {
		return (PersonRegister) sync.getTargetResource().getContents().get(0);
	}

	@Override
	public void saveModels(String name) {
		try {
			sync.saveModels();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEdit,
			Supplier<IEdit<PersonRegister>> targetEdit) {
		throw new UnsupportedOperationException("Concurrent edits not supported.");
	}

}
