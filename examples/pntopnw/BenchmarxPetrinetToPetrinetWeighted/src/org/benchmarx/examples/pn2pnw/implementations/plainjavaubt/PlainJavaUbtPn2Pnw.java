package org.benchmarx.examples.pn2pnw.implementations.plainjavaubt;

import org.benchmarx.petrinetweighted.core.PNWComparator;
import org.benchmarx.examples.pn2pnw.testsuite.Decisions;
import org.benchmarx.petrinet.core.PNComparator;

import plainjavaubt.pn2pnw.Pn2Pnw;
import plainjavaubt.pnw2pn.Pnw2Pn;
import plainjavaubt.util.test.BXToolForPlainJavaUbt;
import plainjavaubt.util.trafo.Transformation;
import pn.PnFactory;
import pnw.PnwFactory;

public class PlainJavaUbtPn2Pnw extends BXToolForPlainJavaUbt<pn.Net, pnw.Net, Decisions> {
	public PlainJavaUbtPn2Pnw() {
		super(new Pn2Pnw(), new Pnw2Pn(), "src/de/ubt/ai1/m2m/pn2pnw/implementations/plainjavaubt",
				PnFactory.eINSTANCE.createNet(), PnwFactory.eINSTANCE.createNet(),
				new PNComparator(), new PNWComparator());
		Transformation.validateInput = true;
	}
}
