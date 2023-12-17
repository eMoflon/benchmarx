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
public class ScalabilityIncrTestsFwd extends ScalabilityTests {

	public ScalabilityIncrTestsFwd(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	private void createOneFamilyMember(int nrOfFamilies) {
		var timer = new BXToolTimer<>(tool, REPEAT);
		
		assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT * REPEAT), () -> {
			results.put(nrOfFamilies, //
					timer.timeSourceEditAfterSetUpInS(
							srcEdit(() -> helperFamily.createSimpsonFamiliesWithMembers(nrOfFamilies)),
							srcEdit(() -> helperFamily.createOneFamilyMember())));
		});
	}

	@Test
	public void testCreate0000003FamiliesWithMembers() {
		createOneFamilyMember(3);
	}

	@Test
	public void testCreate0000005FamiliesWithMembers() {
		createOneFamilyMember(5);
	}

	@Test
	public void testCreate0000010FamiliesWithMembers() {
		createOneFamilyMember(10);
	}

	@Test
	public void testCreate0000100FamiliesWithMembers() {
		createOneFamilyMember(100);
	}

	@Test
	@Ignore
	public void testCreate0001000FamiliesWithMembers() {
		createOneFamilyMember(1000);
	}
	
	@Test
	@Ignore
	public void testCreate0010000FamiliesWithMembers() {
		createOneFamilyMember(10000);
	}
	
	@Test
	@Ignore
	public void testCreate0100000FamiliesWithMembers() {
		createOneFamilyMember(100000);
	}
	
	@Test
	@Ignore
	public void testCreate1000000FamiliesWithMembers() {
		createOneFamilyMember(1000000);
	}
}
