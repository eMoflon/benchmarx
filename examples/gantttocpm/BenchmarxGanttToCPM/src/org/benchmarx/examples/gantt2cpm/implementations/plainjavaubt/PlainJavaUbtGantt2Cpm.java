package org.benchmarx.examples.gantt2cpm.implementations.plainjavaubt;

import cpm.CpmFactory;
import org.benchmarx.cpm.core.CPMComparator;
import org.benchmarx.examples.gantt2cpm.testsuite.Decisions;
import org.benchmarx.gantt.core.GanttComparator;

import gantt.GanttFactory;
import plainjavaubt.cpm2gantt.Cpm2Gantt;
import plainjavaubt.gantt2cpm.Gantt2Cpm;
import plainjavaubt.util.test.BXToolForPlainJavaUbt;
import plainjavaubt.util.trafo.Transformation;

public class PlainJavaUbtGantt2Cpm extends BXToolForPlainJavaUbt<gantt.GanttDiagram, cpm.CPMNetwork, Decisions> {
	public PlainJavaUbtGantt2Cpm() {
		super(new Gantt2Cpm(), new Cpm2Gantt(), "src/de/ubt/ai1/m2m/gantt2cpm/implementations/plainjavaubt",
				GanttFactory.eINSTANCE.createGanttDiagram(), CpmFactory.eINSTANCE.createCPMNetwork(),
				new GanttComparator(), new CPMComparator());
		Transformation.validateInput = false;
	}
}
