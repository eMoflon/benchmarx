package org.benchmarx.examples.bag12bag2.testsuite;

import java.util.Arrays;
import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.bags1.core.Bag1Helper;
import org.benchmarx.bags2.core.Bag2Helper;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.bag12bag2.implementations.bxtend.BXtendBag12Bag2;
import org.benchmarx.examples.bag12bag2.implementations.medini.MediniQVTBag12Bag2;
import org.benchmarx.examples.bag12bag2.implementations.plainjavaubt.PlainJavaUbtBag12Bag2;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.benchmarx.bags1.core.Bag1Comparator;
import org.benchmarx.bags2.core.Bag2Comparator;

@RunWith(Parameterized.class)
public abstract class Bag12Bag2TestCase {
	protected BXTool<bags1.MyBag, bags2.MyBag, Decisions> tool;
	protected Comparator<bags1.MyBag> bag1Comparator;
	protected Comparator<bags2.MyBag> bag2Comparator;
	protected BenchmarxUtil<bags1.MyBag, bags2.MyBag, Decisions> util;
	protected Bag1Helper helperBag1;
	protected Bag2Helper helperBag2;

	@Before
	public void initialise() {
		// Make sure packages are registered
		bags1.Bags1Package.eINSTANCE.getBags1Factory();
		bags2.Bags2Package.eINSTANCE.getBags2Factory();
		
		// Initialise all helpers
		bag1Comparator = new Bag1Comparator();
		bag2Comparator = new Bag2Comparator();
		util = new BenchmarxUtil<>(tool);
		helperBag1 = new Bag1Helper();
		helperBag2 = new Bag2Helper();
		
		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();
	}

	@After
	public void terminate(){
		tool.terminateSynchronisationDialogue();
	}
	
	@Parameters(name = "{0}")
	public static Collection<BXTool<bags1.MyBag, bags2.MyBag, Decisions>> tools() {
		return Arrays.asList(
				new BXtendBag12Bag2(),
				new PlainJavaUbtBag12Bag2(),
				new MediniQVTBag12Bag2()
			);
	}
	
	protected Bag12Bag2TestCase(BXTool<bags1.MyBag, bags2.MyBag, Decisions> tool) {
		this.tool = tool; 
	}
}
