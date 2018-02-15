package org.benchmarx.examples.ast2dag.implementations.plainjavaubt;

import org.benchmarx.examples.ast2dag.testsuite.Decisions;

import ast.AstFactory;
import dag.DagFactory;
import org.benchmarx.ast.core.AstComparator;
import org.benchmarx.dag.core.DagComparator;
import plainjavaubt.ast2dag.Ast2Dag;
import plainjavaubt.dag2ast.Dag2Ast;
import plainjavaubt.util.test.BXToolForPlainJavaUbt;
import plainjavaubt.util.trafo.Transformation;

public class PlainJavaUbtAst2Dag extends BXToolForPlainJavaUbt<ast.Model, dag.Model, Decisions> {
	public PlainJavaUbtAst2Dag() {
		super(new Ast2Dag(), new Dag2Ast(), "src/de/ubt/ai1/m2m/ast2dag/implementations/plainjavaubt",
				AstFactory.eINSTANCE.createModel(), DagFactory.eINSTANCE.createModel(),
				new AstComparator(), new DagComparator());
		Transformation.validateInput = true;
	}
}
