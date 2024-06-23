package org.benchmarx.examples.familiestopersons.implementations.ibextgg.integrate;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.benchmarx.BXTool;
import org.benchmarx.config.Configurator;
import org.benchmarx.edit.IEdit;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.families.core.FamilyHelper;
import org.benchmarx.families.core.FamilyNormaliser;
import org.benchmarx.persons.core.PersonsComparator;
import org.eclipse.emf.ecore.EObject;
import org.emoflon.ibex.tgg.run.familiestopersonsibextgg.INTEGRATE_App;
import org.emoflon.ibex.tgg.run.ibextggfamiliestopersons.SYNC_App;
import org.emoflon.ibex.tgg.runtime.matches.ITGGMatch;
import org.emoflon.ibex.tgg.runtime.matches.container.ImmutableMatchContainer;
import org.emoflon.ibex.tgg.runtime.monitoring.AbstractIbexObservable;
import org.emoflon.ibex.tgg.runtime.monitoring.IbexObservable;
import org.junit.Assert;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsFactory;

import static org.junit.Assert.*;

public class IBeXTGGIntegrateFamiliesToPersons implements BXTool<FamilyRegister, PersonRegister, org.benchmarx.examples.familiestopersons.testsuite.Decisions> {

	private INTEGRATE_App integrate;

	public IBeXTGGIntegrateFamiliesToPersons() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.ERROR);
	}

	@Override
	public String getName() {
		return "IBeX-TGG-Integrate";
	}

	@Override
	public void initiateSynchronisationDialogue() {

		try {
			integrate = new INTEGRATE_App();
			
			integrate.setUpdatePolicy(new F2PStandardUpdatePolicy());
			
			integrate.initialiseFamilyModel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		// Adapt source model
		integrate.applyDelta(integrate.applyDelta(edit, null));

		// Invoke sync
		try {
			integrate.integrate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		// Adapt target model
		integrate.applyDelta(integrate.applyDelta(null, edit));

		// Invoke sync
		try {
			integrate.integrate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performIdleSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		integrate.applyDelta(integrate.applyDelta(edit, null));
	}

	@Override
	public void performIdleTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		integrate.applyDelta(integrate.applyDelta(null, edit));
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		integrate.setUpdatePolicy(new F2PUpdatePolicy(configurator));
	}

	@Override
	public FamilyRegister getSourceModel() {
		return integrate.getForeignSource();
	}

	@Override
	public PersonRegister getTargetModel() {
		return integrate.getForeignTarget();
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEdit,
			Supplier<IEdit<PersonRegister>> targetEdit) {

		integrate.applyDelta(integrate.applyDelta(sourceEdit, targetEdit));
		
		// Invoke sync
		try {
			integrate.integrate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void terminateSynchronisationDialogue() {
		try {
			integrate.terminate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void assertPostcondition(FamilyRegister source, PersonRegister target) {
		compareAndAssert(source, target);
	}

	@Override
	public void assertPrecondition(FamilyRegister source, PersonRegister target) {
		compareAndAssert(source, target);
	}

	private void compareAndAssert(FamilyRegister source, PersonRegister target) {
		var actualSource = integrate.getSourceAsString();
		var actualTarget = integrate.getTargetAsString();

		var expectedSource = new FamiliesComparator().familyToString(source);
		var expectedTarget = new PersonsComparator().personsToString(target);
		
		assertTrue(expectedSource.startsWith("FamilyRegister"));
		assertEquals(expectedSource, actualSource);
		
		assertTrue(expectedTarget.startsWith("PersonRegister"));
		assertEquals(expectedTarget, actualTarget);
	}
}
