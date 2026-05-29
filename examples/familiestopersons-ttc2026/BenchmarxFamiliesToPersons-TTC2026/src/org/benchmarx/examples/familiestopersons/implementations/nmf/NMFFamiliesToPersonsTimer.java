package org.benchmarx.examples.familiestopersons.implementations.nmf;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.util.BXToolTimer;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class NMFFamiliesToPersonsTimer extends BXToolTimer<FamilyRegister, PersonRegister, Decisions> {

	public NMFFamiliesToPersonsTimer(BXTool<FamilyRegister, PersonRegister, Decisions> tool, int repeat) {
		super(tool, repeat);
	}

	@Override
	protected long timeAction(Runnable action) {
		NMFFamiliesToPersonsIncremental nmf = (NMFFamiliesToPersonsIncremental)tool;

		long start = nmf.getRunningTimeInNanoSeconds();
		try {
			action.run();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		long end = nmf.getRunningTimeInNanoSeconds();

		return (end - start) / 1000;
	}
}