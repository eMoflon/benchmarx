package org.benchmarx.examples.familiestopersons.implementations.ibextgg.integrate;

import java.io.IOException;
import java.util.function.BiConsumer;
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
import org.eclipse.emf.ecore.EObject;
import org.emoflon.ibex.tgg.run.familiestopersonsibextgg.INTEGRATE_App;
import org.emoflon.ibex.tgg.run.ibextggfamiliestopersons.SYNC_App;
import org.emoflon.ibex.tgg.runtime.matches.ITGGMatch;
import org.emoflon.ibex.tgg.runtime.matches.container.ImmutableMatchContainer;
import org.emoflon.ibex.tgg.runtime.monitoring.AbstractIbexObservable;
import org.emoflon.ibex.tgg.runtime.monitoring.IbexObservable;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import de.uni_koblenz.jgralab.gretl.SysOut;

public class IBeXTGGIntegrateFamiliesToPersons extends
		BXToolForEMF<FamilyRegister, PersonRegister, org.benchmarx.examples.familiestopersons.testsuite.Decisions> {

	private INTEGRATE_App integrate;

	public IBeXTGGIntegrateFamiliesToPersons() {
		super(new FamiliesComparator(), new PersonsComparator());

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
//			if(integrate == null) {
				System.out.println("-----------------------------------------");
				System.out.println("--------------NEW INTEGRATE--------------");
				System.out.println("-----------------------------------------");
				integrate = new INTEGRATE_App() {
					@Override
					protected ITGGMatch chooseOneMatch() {
						return getUpdatePolicy().chooseOneMatch(new ImmutableMatchContainer(getMatchContainer()));
					}
				};
				
				integrate.setUpdatePolicy(new F2PStandardUpdatePolicy());
				
				// Create initial src and trg models
				FamilyRegister o = FamiliesFactory.eINSTANCE.createFamilyRegister();
				integrate.getResourceHandler().getSourceResource().getContents().add(o);
//			}

			integrate.integrate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		// Adapt source model
		integrate.applyDelta(new BiConsumer<EObject, EObject>() {
			@Override
			public void accept(EObject source, EObject target) {
				edit.get();
			}
		});

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
		integrate.applyDelta(new BiConsumer<EObject, EObject>() {
			@Override
			public void accept(EObject source, EObject target) {
				edit.get();
			}
		});

		// Invoke sync
		try {
			integrate.integrate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performIdleSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		integrate.applyDelta(new BiConsumer<EObject, EObject>() {
			@Override
			public void accept(EObject source, EObject target) {
				edit.get();
			}
		});
	}

	@Override
	public void performIdleTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		integrate.applyDelta(new BiConsumer<EObject, EObject>() {
			@Override
			public void accept(EObject source, EObject target) {
				edit.get();
			}
		});
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		integrate.setUpdatePolicy(new F2PUpdatePolicy(configurator));
	}

	@Override
	public FamilyRegister getSourceModel() {
		return (FamilyRegister) integrate.getResourceHandler().getSourceResource().getContents().get(0);
	}

	@Override
	public PersonRegister getTargetModel() {
		return (PersonRegister) integrate.getResourceHandler().getTargetResource().getContents().get(0);
	}

	@Override
	public void saveModels(String name) {
		try {
			integrate.saveModels();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEdit,
			Supplier<IEdit<PersonRegister>> targetEdit) {

		integrate.applyDelta((source, target) -> {
			sourceEdit.get();
			targetEdit.get();
		});
		
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
}
