package org.benchmarx.examples.set2oset.implementations.plainjavaubt;

import org.benchmarx.examples.set2oset.testsuite.Decisions;

import org.benchmarx.osets.core.OsetComparator;
import org.benchmarx.sets.core.SetComparator;
import osets.OsetsFactory;
import plainjavaubt.oset2set.Oset2Set;
import plainjavaubt.set2oset.Set2Oset;
import plainjavaubt.util.test.BXToolForPlainJavaUbt;
import plainjavaubt.util.trafo.Transformation;
import sets.SetsFactory;

public class PlainJavaUbtSet2Oset extends BXToolForPlainJavaUbt<sets.MySet, osets.MyOrderedSet, Decisions> {
	public PlainJavaUbtSet2Oset() {
		super(new Set2Oset(), new Oset2Set(), "src/de/ubt/ai1/m2m/set2oset/implementations/plainjavaubt",
				SetsFactory.eINSTANCE.createMySet(), OsetsFactory.eINSTANCE.createMyOrderedSet(),
				new SetComparator(), new OsetComparator());
		Transformation.validateInput = true;
	}
}
