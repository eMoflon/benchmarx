package org.benchmarx.examples.containerstominiyaml.implementations.nmf;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.benchmarx.util.BXToolTimer;

import containers.Composition;

public class NMFFamiliesToPersonsTimer extends BXToolTimer<Composition, miniyaml.Map, Decisions> {

	public NMFFamiliesToPersonsTimer(BXTool<Composition, miniyaml.Map, Decisions> tool, int repeat) {
		super(tool, repeat);
	}

	@Override
	protected long timeAction(Runnable action) {
		NMFFamiliesToPersonsIncremental nmf = (NMFFamiliesToPersonsIncremental)tool;
		
		long start = nmf.getRunningTimeInNanoSeconds();
		try {
			action.run();
		} catch (Exception e) {
			// We don't care what goes wrong
		}
		long end = nmf.getRunningTimeInNanoSeconds();
		
		return (end - start) / 1000000;
	}
}
