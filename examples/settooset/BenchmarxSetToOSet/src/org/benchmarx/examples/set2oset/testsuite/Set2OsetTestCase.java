package org.benchmarx.examples.set2oset.testsuite;

import java.util.Arrays;
import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.set2oset.implementations.bxtend.BXtendSet2Oset;
import org.benchmarx.examples.set2oset.implementations.medini.MediniQVTSetToOSet;
import org.benchmarx.examples.set2oset.implementations.plainjavaubt.PlainJavaUbtSet2Oset;
import org.benchmarx.osets.core.OsetHelper;
import org.benchmarx.sets.core.SetHelper;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.benchmarx.sets.core.SetComparator;
import org.benchmarx.osets.core.OsetComparator;

@RunWith(Parameterized.class)
public abstract class Set2OsetTestCase {
	protected BXTool<sets.MySet, osets.MyOrderedSet, Decisions> tool;
	protected Comparator<sets.MySet> setComparator;
	protected Comparator<osets.MyOrderedSet> osetComparator;
	protected BenchmarxUtil<sets.MySet, osets.MyOrderedSet, Decisions> util;
	protected SetHelper helperSet;
	protected OsetHelper helperOset;

	@Before
	public void initialise() {
		// Make sure packages are registered
		sets.SetsPackage.eINSTANCE.getSetsFactory();
		osets.OsetsPackage.eINSTANCE.getOsetsFactory();
		
		// Initialise all helpers
		setComparator = new SetComparator();
		osetComparator = new OsetComparator();
		util = new BenchmarxUtil<>(tool);
		helperSet = new SetHelper();
		helperOset = new OsetHelper();
		
		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();
	}

	@After
	public void terminate(){
		tool.terminateSynchronisationDialogue();
	}
	
	@Parameters(name = "{0}")
	public static Collection<BXTool<sets.MySet, osets.MyOrderedSet, Decisions>> tools() {
		return Arrays.asList(
				new BXtendSet2Oset(),
				new PlainJavaUbtSet2Oset(),
				new MediniQVTSetToOSet()
			);
	}
	
	protected Set2OsetTestCase(BXTool<sets.MySet, osets.MyOrderedSet, Decisions> tool) {
		this.tool = tool; 
	}
}
