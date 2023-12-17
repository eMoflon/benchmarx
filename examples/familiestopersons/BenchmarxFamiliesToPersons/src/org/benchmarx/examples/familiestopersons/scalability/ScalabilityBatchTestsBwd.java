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
public class ScalabilityBatchTestsBwd extends ScalabilityTests {

	public ScalabilityBatchTestsBwd(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	private void createPersons(int nrOfFamilies) {
		var timer = new BXToolTimer<>(tool, REPEAT);
		
		assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT * REPEAT), () -> {
			results.put(nrOfFamilies, //
					timer.timeTargetEditFromScratchInS(
						trgEdit(() -> helperPerson.createPersons(nrOfFamilies, 5))));
		});
	}

	@Test
	public void testCreate0000001Persons() {
		createPersons(1);
	}
	
	@Test
	public void testCreate0000003Persons() {
		createPersons(3);
	}

	@Test
	public void testCreate0000005Persons() {
		createPersons(5);
	}

	@Test
	public void testCreate0000010Persons() {
		createPersons(10);
	}

	@Test
	public void testCreate0000100Persons() {
		createPersons(100);
	}

	@Test
	@Ignore
	public void testCreate0001000Persons() {
		createPersons(1000);
	}
	
	@Test
	@Ignore
	public void testCreate0010000Persons() {
		createPersons(10000);
	}
	
	@Test
	@Ignore
	public void testCreate0100000Persons() {
		createPersons(100000);
	}
	
	@Test
	@Ignore
	public void testCreate1000000Persons() {
		createPersons(1000000);
	}
}
