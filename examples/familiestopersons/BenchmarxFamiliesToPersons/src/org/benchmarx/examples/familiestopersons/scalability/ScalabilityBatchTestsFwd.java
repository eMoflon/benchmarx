package org.benchmarx.examples.familiestopersons.scalability;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.util.BXToolTimer;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Families.FamilyRegister;
import Persons.PersonRegister;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScalabilityBatchTestsFwd extends ScalabilityTests {

	public ScalabilityBatchTestsFwd(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	private void createFamilies(int nrOfFamilies) {
		var timer = new BXToolTimer<>(tool, REPEAT);
		
		assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT * REPEAT), () -> {
			results.put(nrOfFamilies, //
					timer.timeSourceEditFromScratchInS(
							srcEdit(() -> helperFamily.createSimpsonFamiliesWithMembers(nrOfFamilies))));
		});
	}

	@Test
	public void testCreate0000003FamiliesWithMembers() {
		createFamilies(3);
	}

	@Test
	public void testCreate0000005FamiliesWithMembers() {
		createFamilies(5);
	}

	@Test
	public void testCreate0000010FamiliesWithMembers() {
		createFamilies(10);
	}

	@Test
	public void testCreate0000100FamiliesWithMembers() {
		createFamilies(100);
	}

	@Test
	@Ignore
	public void testCreate0001000FamiliesWithMembers() {
		createFamilies(1000);
	}
	
	@Test
	@Ignore
	public void testCreate0010000FamiliesWithMembers() {
		createFamilies(10000);
	}
	
	@Test
	@Ignore
	public void testCreate0100000FamiliesWithMembers() {
		createFamilies(100000);
	}
	
	@Test
	@Ignore
	public void testCreate1000000FamiliesWithMembers() {
		createFamilies(1000000);
	}
}
