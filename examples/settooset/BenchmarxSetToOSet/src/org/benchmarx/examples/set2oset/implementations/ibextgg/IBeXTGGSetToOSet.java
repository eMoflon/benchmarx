package org.benchmarx.examples.set2oset.implementations.ibextgg;

import java.io.IOException;

import org.benchmarx.examples.set2oset.testsuite.Decisions;
import org.benchmarx.osets.core.OsetComparator;
import org.benchmarx.sets.core.SetComparator;
import org.emoflon.ibex.tgg.operational.strategies.sync.SYNC;
import org.emoflon.ibex.tgg.run.ibextggsettooset.SYNC_App;

import osets.MyOrderedSet;
import sets.MySet;
import sets.SetsFactory;

public class IBeXTGGSetToOSet extends IBeXTGGAdapter<MySet, MyOrderedSet, Decisions, SYNC_App> {
	public IBeXTGGSetToOSet() throws IOException {
		super(new SetComparator(), new OsetComparator());
	}

	@Override
	protected MySet createInitialSrc() {
		MySet set = SetsFactory.eINSTANCE.createMySet();
		set.setName("");
		return set;
	}

	@Override
	protected SYNC createSynchroniser() throws IOException {
		return new SYNC_App();
	}
	
	@Override
	public void initiateSynchronisationDialogue() {
		try {
			sync = createSynchroniser();
			
			// Create initial src and trg models
			MySet src = createInitialSrc();
			sync.getSourceResource().getContents().add(src);

			sync.forward();
			
			MyOrderedSet trg = (MyOrderedSet) sync.getTargetResource().getContents().get(0);

			src.setName(null);
			trg.setName(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
