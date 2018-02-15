package org.benchmarx.examples.pdb12pdb2.implementations.plainjavaubt;

import org.benchmarx.examples.pdb12pdb2.testsuite.Decisions;

import org.benchmarx.pdb1.core.Pdb1Comparator;
import org.benchmarx.pdb2.core.Pdb2Comparator;
import pdb1.Pdb1Factory;
import pdb2.Pdb2Factory;
import plainjavaubt.pdb12pdb2.Pdb12Pdb2;
import plainjavaubt.pdb22pdb1.Pdb22Pdb1;
import plainjavaubt.util.test.BXToolForPlainJavaUbt;
import plainjavaubt.util.trafo.Transformation;

public class PlainJavaUbtPdb12Pdb2 extends BXToolForPlainJavaUbt<pdb1.Database, pdb2.Database, Decisions> {
	public PlainJavaUbtPdb12Pdb2() {
		super(new Pdb12Pdb2(), new Pdb22Pdb1(), "src/de/ubt/ai1/m2m/pdb12pdb2/implementations/plainjavaubt",
				Pdb1Factory.eINSTANCE.createDatabase(), Pdb2Factory.eINSTANCE.createDatabase(),
				new Pdb1Comparator(), new Pdb2Comparator());
		Transformation.validateInput = true;
	}
}
