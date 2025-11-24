package org.benchmarx.examples.familiestopersons.scalability.cases;

import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.ScalabilityConstModelCFCSync;
import org.benchmarx.examples.familiestopersons.scalability.runner.BenchTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.benchmarx.util.BXToolTimer;

import Families.FamilyRegister;
import Persons.PersonRegister;


public class ConstModelCFCSyncTestcase extends BenchTestcase {
	public static void main(String args[]) {
		new ConstModelCFCSyncTestcase(args[0], Integer.valueOf(args[1])).execute();
	}

	public ConstModelCFCSyncTestcase(String toolName, int scaleFactor) {
		super(toolName, scaleFactor);
	}

	@Override
	protected Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> getAvailableTools() {
		return FamiliesToPersonsTestCase.tools();
	}

	@Override
	public double executeTest(int nrOfEditedFamilyPairs) {
		var timer = new BXToolTimer<>(tool, 1);
		return timer.timeEditAfterSetUpInS(
				srcEdit(() -> helperFamily.createSimpsonFamiliesWithMembers(ScalabilityConstModelCFCSync.NR_OF_FAMILY_PAIRS)),
				srcEdit(() -> helperFamily.createSonHugo(nrOfEditedFamilyPairs)),
				trgEdit(() -> helperPerson.deleteLisa(nrOfEditedFamilyPairs)));
	}
}
