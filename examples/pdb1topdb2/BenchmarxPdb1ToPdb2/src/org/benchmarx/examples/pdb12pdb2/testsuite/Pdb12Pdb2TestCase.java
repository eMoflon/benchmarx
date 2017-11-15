package org.benchmarx.examples.pdb12pdb2.testsuite;

import java.util.Arrays;
import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.pdb12pdb2.implementations.bxtend.BXtendPdb12Pdb2;
import org.benchmarx.examples.pdb12pdb2.implementations.medini.MediniQVTPdb12Pdb2;
import org.benchmarx.examples.pdb12pdb2.implementations.plainjavaubt.PlainJavaUbtPdb12Pdb2;
import org.benchmarx.pdb1.core.Pdb1Helper;
import org.benchmarx.pdb2.core.Pdb2Helper;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.benchmarx.pdb1.core.Pdb1Comparator;
import org.benchmarx.pdb2.core.Pdb2Comparator;

@RunWith(Parameterized.class)
public abstract class Pdb12Pdb2TestCase {

	protected BXTool<pdb1.Database, pdb2.Database, Decisions> tool;
	protected Comparator<pdb1.Database> person1Comparator;
	protected Comparator<pdb2.Database> person2Comparator;
	protected BenchmarxUtil<pdb1.Database, pdb2.Database, Decisions> util;
	protected Pdb1Helper helperPerson1;
	protected Pdb2Helper helperPerson2;

	@Before
	public void initialise() {
		// Make sure packages are registered
		pdb1.Pdb1Package.eINSTANCE.getPdb1Factory();
		pdb2.Pdb2Package.eINSTANCE.getPdb2Factory();
		
		// Initialise all helpers
		person1Comparator = new Pdb1Comparator();
		person2Comparator = new Pdb2Comparator();
		util = new BenchmarxUtil<>(tool);
		helperPerson1 = new Pdb1Helper();
		helperPerson2 = new Pdb2Helper();
		
		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();
	}

	@After
	public void terminate(){
		tool.terminateSynchronisationDialogue();
	}
	
	@Parameters(name = "{0}")
	public static Collection<BXTool<pdb1.Database, pdb2.Database, Decisions>> tools() {
		return Arrays.asList(
				new BXtendPdb12Pdb2(),
				new PlainJavaUbtPdb12Pdb2(),
				new MediniQVTPdb12Pdb2()
			);
	}
	
	protected Pdb12Pdb2TestCase(BXTool<pdb1.Database, pdb2.Database, Decisions> tool) {
		this.tool = tool; 
	}
}
