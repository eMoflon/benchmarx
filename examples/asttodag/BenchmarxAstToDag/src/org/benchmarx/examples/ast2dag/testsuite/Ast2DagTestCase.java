package org.benchmarx.examples.ast2dag.testsuite;

import java.util.Arrays;
import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.ast.core.AstHelper;
import org.benchmarx.dag.core.DagHelper;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.ast2dag.implementations.bxtend.BXtendAst2Dag;
import org.benchmarx.examples.ast2dag.implementations.medini.MediniQVTAst2Dag;
import org.benchmarx.examples.ast2dag.implementations.plainjavaubt.PlainJavaUbtAst2Dag;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.benchmarx.ast.core.AstComparator;
import org.benchmarx.dag.core.DagComparator;

@RunWith(Parameterized.class)
public abstract class Ast2DagTestCase {
	protected BXTool<ast.Model, dag.Model, Decisions> tool;
	protected Comparator<ast.Model> astComparator;
	protected Comparator<dag.Model> dagComparator;
	protected BenchmarxUtil<ast.Model, dag.Model, Decisions> util;
	protected AstHelper helperAst;
	protected DagHelper helperDag;

	@Before
	public void initialise() {
		// Make sure packages are registered
		ast.AstPackage.eINSTANCE.getAstFactory();
		dag.DagPackage.eINSTANCE.getDagFactory();
		
		// Initialise all helpers
		astComparator = new AstComparator();
		dagComparator = new DagComparator();
		util = new BenchmarxUtil<>(tool);
		helperAst = new AstHelper();
		helperDag = new DagHelper();
		
		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();
	}

	@After
	public void terminate(){
		tool.terminateSynchronisationDialogue();
	}
	
	@Parameters(name = "{0}")
	public static Collection<BXTool<ast.Model, dag.Model, Decisions>> tools() {
		return Arrays.asList(
				new BXtendAst2Dag()
				, new PlainJavaUbtAst2Dag()
				, new MediniQVTAst2Dag()
			);
	}
	
	protected Ast2DagTestCase(BXTool<ast.Model, dag.Model, Decisions> tool) {
		this.tool = tool; 
	}
}
