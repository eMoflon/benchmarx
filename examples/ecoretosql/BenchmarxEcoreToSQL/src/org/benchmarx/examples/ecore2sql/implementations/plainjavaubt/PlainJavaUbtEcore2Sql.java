package org.benchmarx.examples.ecore2sql.implementations.plainjavaubt;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.benchmarx.ecore.core.EcoreComparator;
import org.benchmarx.examples.ecore2sql.testsuite.Decisions;
import org.benchmarx.sql.core.SQLComparator;
import ecore2sql.Ecore2Sql;
import plainjavaubt.util.test.BXToolForPlainJavaUbt;
import plainjavaubt.util.trafo.Transformation;
import sql.Schema;
import sql.SqlFactory;
import sql2ecore.Sql2Ecore;

public class PlainJavaUbtEcore2Sql extends BXToolForPlainJavaUbt<EPackage, Schema, Decisions> {
	public PlainJavaUbtEcore2Sql() {
		super(new Ecore2Sql(), new Sql2Ecore(), "src/de/ubt/ai1/m2m/ecore2sql/implementations/plainjavaubt",
				EcoreFactory.eINSTANCE.createEPackage(), SqlFactory.eINSTANCE.createSchema(),
				new EcoreComparator(), new SQLComparator());
		Transformation.validateInput = false;
	}
}
