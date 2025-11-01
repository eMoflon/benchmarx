package org.benchmarx.examples.familiestopersons.scalability.cases;

import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.ScalabilityConstDeltaCFCSync;
import org.benchmarx.examples.familiestopersons.scalability.runner.BenchTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.benchmarx.util.BXToolTimer;

import Families.FamilyRegister;
import Persons.PersonRegister;


public class ConstDeltaCFCSyncTestcase extends BenchTestcase {
	public static void main(String args[]) {
		new ConstDeltaCFCSyncTestcase(args[0], Integer.valueOf(args[1])).execute();
	}

	public ConstDeltaCFCSyncTestcase(String toolName, int scaleFactor) {
		super(toolName, scaleFactor);
	}

	@Override
	protected Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> getAvailableTools() {
		return FamiliesToPersonsTestCase.tools();
	}

	@Override
	public void executeTest(int nrOfFamilyPairs) {
		var timer = new BXToolTimer<>(tool, 1);
		System.out.println(timer.timeEditAfterSetUpInS(
				srcEdit(() -> helperFamily.createSimpsonFamiliesWithMembers(nrOfFamilyPairs)),
				srcEdit(() -> helperFamily.createSonHugo(ScalabilityConstDeltaCFCSync.NR_OF_EDITED_FAMILIES)),
				trgEdit(() -> helperPerson.deleteLisa(ScalabilityConstDeltaCFCSync.NR_OF_EDITED_FAMILIES))));
	}
}
