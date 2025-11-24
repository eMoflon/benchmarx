package org.benchmarx.examples.familiestopersons.scalability.cases;

import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.ScalabilityConstDeltaCSync;
import org.benchmarx.examples.familiestopersons.scalability.runner.BenchTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.benchmarx.util.BXToolTimer;

import Families.FamilyRegister;
import Persons.PersonRegister;


public class ConstDeltaCSyncTestcase extends BenchTestcase {
	public static void main(String args[]) {
		new ConstDeltaCSyncTestcase(args[0], Integer.valueOf(args[1])).execute();
	}

	public ConstDeltaCSyncTestcase(String toolName, int scaleFactor) {
		super(toolName, scaleFactor);
	}

	@Override
	protected Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> getAvailableTools() {
		return FamiliesToPersonsTestCase.tools();
	}

	@Override
	public double executeTest(int nrOfFamilyPairs) {
		var timer = new BXToolTimer<>(tool, 1);
		return timer.timeEditAfterSetUpInS(
				srcEdit(() -> {
					helperFamily.createSimpsonFamiliesWithMembers(nrOfFamilyPairs);
					helperFamily.createFlandersFamiliesWithMembers(nrOfFamilyPairs);
					}),
				srcEdit(() -> helperFamily.moveLisaToFlandersAsDaugther(ScalabilityConstDeltaCSync.NR_OF_EDITED_FAMILY_PAIRS)),
				trgEdit(() -> helperPerson.deleteLisa(ScalabilityConstDeltaCSync.NR_OF_EDITED_FAMILY_PAIRS)));
	}
}
