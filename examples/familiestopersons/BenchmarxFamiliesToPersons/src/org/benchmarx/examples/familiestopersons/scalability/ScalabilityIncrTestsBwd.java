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
public class ScalabilityIncrTestsBwd extends ScalabilityTests {

	public ScalabilityIncrTestsBwd(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	private void createOnePerson(int nrOfFamilies) {
		var timer = new BXToolTimer<>(tool, REPEAT);
		
		assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT * REPEAT), () -> {
			results.put(nrOfFamilies, //
					timer.timeTargetEditAfterSetUpInS(
							trgEdit(() -> helperPerson.createPersons(nrOfFamilies, 5)),
							trgEdit(() -> helperPerson.createOnePerson())));
		});
	}

	@Test
	public void testCreate0000001Persons() {
		createOnePerson(1);
	}
	
	@Test
	public void testCreate0000003Persons() {
		createOnePerson(3);
	}

	@Test
	public void testCreate0000005Persons() {
		createOnePerson(5);
	}

	@Test
	public void testCreate0000010Persons() {
		createOnePerson(10);
	}

	@Test
	public void testCreate0000100Persons() {
		createOnePerson(100);
	}

	@Test
	@Ignore
	public void testCreate0001000Persons() {
		createOnePerson(1000);
	}
	
	@Test
	@Ignore
	public void testCreate0010000Persons() {
		createOnePerson(10000);
	}
	
	@Test
	@Ignore
	public void testCreate0100000Persons() {
		createOnePerson(100000);
	}
	
	@Test
	@Ignore
	public void testCreate1000000FamiliesWithMembers() {
		createOnePerson(1000000);
	}
}
