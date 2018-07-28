package org.benchmarx.examples.ecore2sql.implementations.ibextgg;

import java.io.IOException;
import java.util.function.Consumer;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.emf.Comparator;
import org.eclipse.emf.ecore.EObject;
import org.emoflon.ibex.tgg.operational.strategies.sync.SYNC;

public abstract class IBeXTGGAdapter<S extends EObject, T extends EObject, D, X extends SYNC>
		extends BXToolForEMF<S, T, D> {

	public IBeXTGGAdapter(Comparator<S> src, Comparator<T> trg) {
		super(src, trg);

		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}

	protected SYNC sync;

	@Override
	public String getName() {
		return "IBeX-TGG";
	}

	@Override
	public void initiateSynchronisationDialogue() {
		try {
			sync = createSynchroniser();
			
			// Create initial src and trg models
			S o = createInitialSrc();
			sync.getSourceResource().getContents().add(o);

			sync.forward();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract SYNC createSynchroniser() throws IOException;
	protected abstract S createInitialSrc();

	@Override
	public void performAndPropagateSourceEdit(Consumer<S> edit) {
		// Adapt source model
		@SuppressWarnings("unchecked")
		S o = (S) sync.getSourceResource().getContents().get(0);
		edit.accept(o);

		// Invoke sync
		try {
			sync.forward();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<T> edit) {
		// Adapt target model
		@SuppressWarnings("unchecked")
		T o = (T) sync.getTargetResource().getContents().get(0);
		edit.accept(o);

		// Invoke sync
		try {
			sync.backward();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void performIdleSourceEdit(Consumer<S> edit) {
		performAndPropagateSourceEdit(edit);
	}

	@Override
	public void performIdleTargetEdit(Consumer<T> edit) {
		performAndPropagateTargetEdit(edit);
	}

	@Override
	public void setConfigurator(Configurator<D> configurator) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public S getSourceModel() {
		return (S) sync.getSourceResource().getContents().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getTargetModel() {
		return (T) sync.getTargetResource().getContents().get(0);
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
