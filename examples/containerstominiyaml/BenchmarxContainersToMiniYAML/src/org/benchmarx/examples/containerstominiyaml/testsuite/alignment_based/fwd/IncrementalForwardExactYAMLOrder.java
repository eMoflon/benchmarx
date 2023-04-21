package org.benchmarx.examples.containerstominiyaml.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;

import containers.Composition;
import miniyaml.Map;

public class IncrementalForwardExactYAMLOrder extends IncrementalForward {

	public IncrementalForwardExactYAMLOrder(BXTool<Composition, Map, Decisions> tool) {
		super(tool);
	}
	
}
