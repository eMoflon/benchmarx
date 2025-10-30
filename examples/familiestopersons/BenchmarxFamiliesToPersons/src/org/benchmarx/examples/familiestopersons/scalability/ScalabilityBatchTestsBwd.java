package org.benchmarx.examples.familiestopersons.scalability;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.util.BXToolTimer;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Families.FamilyRegister;
import Persons.PersonRegister;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScalabilityBatchTestsBwd extends ScalabilityTests {

	public ScalabilityBatchTestsBwd(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool, "BWD_");
	}
	
	private void createPersons(int nrOfFamilies) {
		var timer = new BXToolTimer<>(tool, REPEAT);
		
		assertLastTestSuccessfull();
		
		assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT * REPEAT), () -> {
			results.put(nrOfFamilies, //
					timer.timeTargetEditFromScratchInS(
						trgEdit(() -> helperPerson.createPersons(nrOfFamilies, 5))));
		});
		
		setTestSuccessfull();
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
	public void testCreate0000020Persons() {
		createPersons(20);
	}
	
	@Test
	public void testCreate0000030Persons() {
		createPersons(30);
	}
	
	@Test
	public void testCreate0000040Persons() {
		createPersons(40);
	}
	
	@Test
	public void testCreate0000050Persons() {
		createPersons(50);
	}
	

	@Test
	public void testCreate0000075Persons() {
		createPersons(75);
	}
	
	@Test
	public void testCreate0000100Persons() {
		createPersons(100);
	}
	
	@Test
	public void testCreate0000150Persons() {
		createPersons(150);
	}
	
	@Test
	public void testCreate0000200Persons() {
		createPersons(200);
	}

	@Test
	public void testCreate0000250Persons() {
		createPersons(250);
	}
	
	@Test
	public void testCreate0000300Persons() {
		createPersons(300);
	}
	
	@Test
	public void testCreate0000350Persons() {
		createPersons(350);
	}
	
	@Test
	public void testCreate0000400Persons() {
		createPersons(400);
	}
	
	@Test
	public void testCreate0000450Persons() {
		createPersons(450);
	}
	
	@Test
	public void testCreate0000500Persons() {
		createPersons(500);
	}
	
	@Test
	public void testCreate0000600Persons() {
		createPersons(600);
	}
	
	@Test
	public void testCreate0000700Persons() {
		createPersons(700);
	}
	
	@Test
	public void testCreate0000800Persons() {
		createPersons(800);
	}
	
	@Test
	public void testCreate0000900Persons() {
		createPersons(900);
	}
	
	@Test
	public void testCreate0001000Persons() {
		createPersons(1000);
	}
	
	@Test
	public void testCreate0001250Persons() {
		createPersons(1250);
	}
	
	@Test
	public void testCreate0001500Persons() {
		createPersons(1500);
	}
	
	@Test
	public void testCreate0001750Persons() {
		createPersons(1750);
	}
	
	@Test
	public void testCreate0002000Persons() {
		createPersons(2000);
	}
	
	@Test
	public void testCreate0002500Persons() {
		createPersons(2500);
	}
	
	@Test
	public void testCreate0003000Persons() {
		createPersons(3000);
	}
	
	@Test
	public void testCreate0003500Persons() {
		createPersons(3500);
	}
	
	@Test
	public void testCreate0004000Persons() {
		createPersons(4000);
	}
	
	@Test
	public void testCreate0004500Persons() {
		createPersons(4500);
	}
	
	@Test
	public void testCreate0005000Persons() {
		createPersons(5000);
	}
	
	@Test
	public void testCreate0006000Persons() {
		createPersons(6000);
	}
	
	@Test
	public void testCreate0007000Persons() {
		createPersons(7000);
	}
	
	@Test
	public void testCreate0008000Persons() {
		createPersons(8000);
	}
	
	@Test
	public void testCreate0009000Persons() {
		createPersons(9000);
	}
	
	@Test
	public void testCreate0010000Persons() {
		createPersons(10000);
	}
	
	@Test
	public void testCreate0012500Persons() {
		createPersons(12500);
	}
	
	@Test
	public void testCreate0015000Persons() {
		createPersons(15000);
	}
	
	@Test
	public void testCreate0017500Persons() {
		createPersons(17500);
	}
	
	@Test
	public void testCreate0020000Persons() {
		createPersons(20000);
	}
	
	@Test
	public void testCreate0022500Persons() {
		createPersons(22500);
	}
	
	@Test
	public void testCreate0025000Persons() {
		createPersons(25000);
	}
	
	@Test
	public void testCreate0030000Persons() {
		createPersons(30000);
	}
	
	@Test
	public void testCreate0035000Persons() {
		createPersons(35000);
	}
	
	@Test
	public void testCreate0040000Persons() {
		createPersons(40000);
	}
	
	@Test
	public void testCreate0045000Persons() {
		createPersons(45000);
	}
	
	@Test
	public void testCreate0050000Persons() {
		createPersons(50000);
	}
}
