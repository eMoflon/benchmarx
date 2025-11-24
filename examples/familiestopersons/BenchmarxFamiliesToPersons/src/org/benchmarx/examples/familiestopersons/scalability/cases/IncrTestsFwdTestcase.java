package org.benchmarx.examples.familiestopersons.scalability.cases;

import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.runner.BenchTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.benchmarx.util.BXToolTimer;

import Families.FamilyRegister;
import Persons.PersonRegister;


public class IncrTestsFwdTestcase extends BenchTestcase {
	public static void main(String args[]) {
		new IncrTestsFwdTestcase(args[0], Integer.valueOf(args[1])).execute();
	}

	public IncrTestsFwdTestcase(String toolName, int scaleFactor) {
		super(toolName, scaleFactor);
	}

	@Override
	protected Collection<BXTool<FamilyRegister, PersonRegister, Decisions>> getAvailableTools() {
		return FamiliesToPersonsTestCase.tools();
	}

	@Override
	public double executeTest(int nrOfFamilies) {
		var timer = new BXToolTimer<>(tool, 1);
		return timer.timeSourceEditAfterSetUpInS(
				srcEdit(() -> helperFamily.createSimpsonFamiliesWithMembers(nrOfFamilies)),
				srcEdit(() -> helperFamily.createOneFamilyMember()));
	}
}
