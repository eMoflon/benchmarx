package org.benchmarx.examples.bag12bag2.implementations.plainjavaubt;

import org.benchmarx.examples.bag12bag2.testsuite.Decisions;

import bags1.Bags1Factory;
import bags2.Bags2Factory;
import org.benchmarx.bags1.core.Bag1Comparator;
import org.benchmarx.bags2.core.Bag2Comparator;
import plainjavaubt.bags12bags2.Bags12Bags2;
import plainjavaubt.bags22bags1.Bags22Bags1;
import plainjavaubt.util.test.BXToolForPlainJavaUbt;
import plainjavaubt.util.trafo.Transformation;

public class PlainJavaUbtBag12Bag2 extends BXToolForPlainJavaUbt<bags1.MyBag, bags2.MyBag, Decisions> {
	public PlainJavaUbtBag12Bag2() {
		super(new Bags12Bags2(), new Bags22Bags1(), "src/de/ubt/ai1/m2m/bags12bags2/implementations/plainjavaubt",
				Bags1Factory.eINSTANCE.createMyBag(), Bags2Factory.eINSTANCE.createMyBag(),
				new Bag1Comparator(), new Bag2Comparator());
		Transformation.validateInput = true;
	}
}
