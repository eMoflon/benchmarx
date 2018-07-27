package org.benchmarx.examples.pn2pnw.implementations.ibextgg;

import java.io.IOException;

import org.benchmarx.examples.pn2pnw.testsuite.Decisions;
import org.benchmarx.petrinet.core.PNComparator;
import org.benchmarx.petrinetweighted.core.PNWComparator;
import org.emoflon.ibex.tgg.operational.strategies.sync.SYNC;
import org.emoflon.ibex.tgg.run.ibextggpetrinets.SYNC_App;

import pn.Net;

public class IBeXTGGPetrinets extends IBeXTGGAdapter<pn.Net, pnw.Net, Decisions, SYNC_App> {

	public IBeXTGGPetrinets() {
		super(new PNComparator(), new PNWComparator());
	}

	@Override
	protected SYNC createSynchroniser() throws IOException {
		return new SYNC_App();
	}

	@Override
	protected Net createInitialSrc() {
		pn.Net net = pn.PnFactory.eINSTANCE.createNet();
		net.setName("");
		return net;
	}

}
