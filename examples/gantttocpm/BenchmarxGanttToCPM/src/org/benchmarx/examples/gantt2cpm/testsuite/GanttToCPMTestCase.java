package org.benchmarx.examples.gantt2cpm.testsuite;

import java.util.Arrays;
import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.cpm.core.CPMBuilder;
import org.benchmarx.cpm.core.CPMHelper;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.gantt2cpm.implementations.bxtend.BXtendGantt2CPM;
import org.benchmarx.examples.gantt2cpm.implementations.medini.MediniQVTGantt2CPM;
import org.benchmarx.examples.gantt2cpm.implementations.plainjavaubt.PlainJavaUbtGantt2Cpm;
import org.benchmarx.gantt.core.GanttHelper;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import cpm.CPMNetwork;
import cpm.CpmPackage;
import org.benchmarx.cpm.core.CPMComparator;
import org.benchmarx.gantt.core.GanttComparator;

import gantt.GanttDiagram;
import gantt.GanttPackage;

@RunWith(Parameterized.class)
public abstract class GanttToCPMTestCase {
	protected BXTool<GanttDiagram, CPMNetwork, Decisions> tool;
	protected Comparator<GanttDiagram> ganttComparator;
	protected Comparator<CPMNetwork> cpmComparator;
	protected BenchmarxUtil<GanttDiagram, CPMNetwork, Decisions> util;
	protected GanttHelper helperGantt;
	protected CPMHelper helperCPM;

	@Before
	public void initialise() {
		// Make sure packages are registered
		GanttPackage.eINSTANCE.getName();
		CpmPackage.eINSTANCE.getName();
		
		// Initialise all helpers
		ganttComparator = new GanttComparator();
		cpmComparator = new CPMComparator();
		util = new BenchmarxUtil<>(tool);
		helperGantt = new GanttHelper();
		helperCPM = new CPMHelper();
		
		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();
	}

	@After
	public void terminate(){
		tool.terminateSynchronisationDialogue();
		CPMBuilder.reset();
	}
	
	@Parameters(name = "{0}")
	public static Collection<BXTool<GanttDiagram, CPMNetwork, Decisions>> tools() {
		return Arrays.asList(
				new BXtendGantt2CPM(),  // Currently 0 failures
				new PlainJavaUbtGantt2Cpm(),
				new MediniQVTGantt2CPM()
			);
	}
	
	protected GanttToCPMTestCase(BXTool<GanttDiagram, CPMNetwork, Decisions> tool) {
		this.tool = tool; 
	}
}
