package org.benchmarx.examples.pn2pnw.testsuite;

import java.util.Arrays;
import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.pn2pnw.implementations.bxtend.BXtendPn2Pnw;
import org.benchmarx.examples.pn2pnw.implementations.medini.MediniQVTPn2Pnw;
import org.benchmarx.examples.pn2pnw.implementations.plainjavaubt.PlainJavaUbtPn2Pnw;
import org.benchmarx.petrinet.core.PNHelper;
import org.benchmarx.petrinetweighted.core.PNWComparator;
import org.benchmarx.petrinetweighted.core.PNWHelper;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.benchmarx.petrinet.core.PNComparator;

@RunWith(Parameterized.class)
public abstract class Pn2PnwTestCase {
	protected BXTool<pn.Net, pnw.Net, Decisions> tool;
	protected Comparator<pn.Net> pnComparator;
	protected Comparator<pnw.Net> pnwComparator;
	protected BenchmarxUtil<pn.Net, pnw.Net, Decisions> util;
	protected PNHelper helperPn;
	protected PNWHelper helperPnw;

	@Before
	public void initialise() {
		// Make sure packages are registered
		pn.PnPackage.eINSTANCE.getPnFactory();
		pnw.PnwPackage.eINSTANCE.getPnwFactory();
		
		// Initialise all helpers
		pnComparator = new PNComparator();
		pnwComparator = new PNWComparator();
		util = new BenchmarxUtil<>(tool);
		helperPn = new PNHelper();
		helperPnw = new PNWHelper();
		
		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();
	}

	@After
	public void terminate(){
		tool.terminateSynchronisationDialogue();
	}
	
	@Parameters(name = "{0}")
	public static Collection<BXTool<pn.Net, pnw.Net, Decisions>> tools() {
		return Arrays.asList(
				new BXtendPn2Pnw(),
				new PlainJavaUbtPn2Pnw(),
				new MediniQVTPn2Pnw()
			);
	}
	
	protected Pn2PnwTestCase(BXTool<pn.Net, pnw.Net, Decisions> tool) {
		this.tool = tool; 
	}
}
