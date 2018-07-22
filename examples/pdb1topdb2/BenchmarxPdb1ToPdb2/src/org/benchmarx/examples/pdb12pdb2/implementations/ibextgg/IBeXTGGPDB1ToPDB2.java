package org.benchmarx.examples.pdb12pdb2.implementations.ibextgg;

import java.io.IOException;
import java.util.function.Consumer;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.pdb12pdb2.testsuite.Decisions;
import org.benchmarx.pdb1.core.Pdb1Comparator;
import org.benchmarx.pdb2.core.Pdb2Comparator;
import org.emoflon.ibex.tgg.operational.csp.constraints.custom.ibextggpdb1topdb2.UserDefined_config_concat;
import org.emoflon.ibex.tgg.run.ibextggpdb1topdb2.SYNC_App;

import pdb1.Database;;

public class IBeXTGGPDB1ToPDB2
		extends BXToolForEMF<Database, pdb2.Database, org.benchmarx.examples.pdb12pdb2.testsuite.Decisions> {

	private SYNC_App sync;

	public IBeXTGGPDB1ToPDB2() {
		super(new Pdb1Comparator(), new Pdb2Comparator());

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
			BasicConfigurator.configure();
			Logger.getRootLogger().setLevel(Level.WARN);

			// Create initial src and trg models
			Database db1 = pdb1.Pdb1Factory.eINSTANCE.createDatabase();
			db1.setName("");
			sync.getSourceResource().getContents().add(db1);

			sync.forward();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<Database> edit) {
		// Adapt source model
		Database db1 = (Database) sync.getSourceResource().getContents().get(0);
		edit.accept(db1);

		// Invoke sync
		try {
			sync.forward();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<pdb2.Database> edit) {
		// Adapt target model
		pdb2.Database cn = (pdb2.Database) sync.getTargetResource().getContents().get(0);
		edit.accept(cn);

		// Invoke sync
		try {
			sync.backward();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performIdleSourceEdit(Consumer<Database> edit) {
		performAndPropagateSourceEdit(edit);
	}

	@Override
	public void performIdleTargetEdit(Consumer<pdb2.Database> edit) {
		performAndPropagateTargetEdit(edit);
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		UserDefined_config_concat.setConfigurator(() -> configurator.decide(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST));
	}

	@Override
	public Database getSourceModel() {
		return (Database) sync.getSourceResource().getContents().get(0);
	}

	@Override
	public pdb2.Database getTargetModel() {
		return (pdb2.Database) sync.getTargetResource().getContents().get(0);
	}

	@Override
	public void saveModels(String name) {
		try {
			sync.saveModels();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
