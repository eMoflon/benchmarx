package org.benchmarx.examples.ecore2sql.testsuite;


import java.util.Arrays;
import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.ecore.core.EcoreComparator;
import org.benchmarx.ecore.core.EcoreHelper;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.ecore2sql.implementations.bxtend.BXtendEcore2SQL;
import org.benchmarx.examples.ecore2sql.implementations.plainjavaubt.PlainJavaUbtEcore2Sql;
import org.benchmarx.sql.core.SQLHelper;
import org.benchmarx.util.BenchmarxUtil;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.benchmarx.sql.core.SQLComparator;
import sql.Schema;
import sql.SqlPackage;

@RunWith(Parameterized.class)
public abstract class EcoreToSQLTestCase {
	protected BXTool<EPackage, Schema, Decisions> tool;
	protected Comparator<EPackage> ecoreComparator;
	protected Comparator<Schema> sqlComparator;
	protected BenchmarxUtil<EPackage, Schema, Decisions> util;
	protected EcoreHelper helperEcore;
	protected SQLHelper helperSQL;

	@Before
	public void initialise() {
		// Make sure packages are registered
		EcorePackage.eINSTANCE.getName();
		SqlPackage.eINSTANCE.getName();
		
		// Initialise all helpers
		ecoreComparator = new EcoreComparator();
		sqlComparator = new SQLComparator();
		util = new BenchmarxUtilForEcoreToSQL(tool);
		helperEcore = new EcoreHelper();
		helperSQL = new SQLHelper();
		
		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();
	}

	@After
	public void terminate(){
		tool.terminateSynchronisationDialogue();
	}
	
	@Parameters(name = "{0}")
	public static Collection<BXTool<EPackage, Schema, Decisions>> tools() {
		return Arrays.asList(
				new BXtendEcore2SQL(),  // Currently 0 failures
				new PlainJavaUbtEcore2Sql()
//				new MediniQVTEcore2SQL()
			);
	}
	
	protected EcoreToSQLTestCase(BXTool<EPackage, Schema, Decisions> tool) {
		this.tool = tool; 
	}
}
