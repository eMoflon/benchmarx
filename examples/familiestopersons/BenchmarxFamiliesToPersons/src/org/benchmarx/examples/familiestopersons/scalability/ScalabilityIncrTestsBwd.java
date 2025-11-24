package org.benchmarx.examples.familiestopersons.scalability;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.cases.IncrTestsBwdTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Families.FamilyRegister;
import Persons.PersonRegister;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScalabilityIncrTestsBwd extends ScalabilityTests {

	public ScalabilityIncrTestsBwd(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool, "INCR_BWD_");
	}
	
	private void createOnePerson(int nrOfFamilies) {
		runTest(IncrTestsBwdTestcase.class, tool, nrOfFamilies);
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
	public void testCreate0000020Persons() {
		createOnePerson(20);
	}
	
	@Test
	public void testCreate0000030Persons() {
		createOnePerson(30);
	}
	
	@Test
	public void testCreate0000040Persons() {
		createOnePerson(40);
	}
	
	@Test
	public void testCreate0000050Persons() {
		createOnePerson(50);
	}
	

	@Test
	public void testCreate0000075Persons() {
		createOnePerson(75);
	}
	
	@Test
	public void testCreate0000100Persons() {
		createOnePerson(100);
	}
	
	@Test
	public void testCreate0000150Persons() {
		createOnePerson(150);
	}
	
	@Test
	public void testCreate0000200Persons() {
		createOnePerson(200);
	}

	@Test
	public void testCreate0000250Persons() {
		createOnePerson(250);
	}
	
	@Test
	public void testCreate0000300Persons() {
		createOnePerson(300);
	}
	
	@Test
	public void testCreate0000350Persons() {
		createOnePerson(350);
	}
	
	@Test
	public void testCreate0000400Persons() {
		createOnePerson(400);
	}
	
	@Test
	public void testCreate0000450Persons() {
		createOnePerson(450);
	}
	
	@Test
	public void testCreate0000500Persons() {
		createOnePerson(500);
	}
	
	@Test
	public void testCreate0000600Persons() {
		createOnePerson(600);
	}
	
	@Test
	public void testCreate0000700Persons() {
		createOnePerson(700);
	}
	
	@Test
	public void testCreate0000800Persons() {
		createOnePerson(800);
	}
	
	@Test
	public void testCreate0000900Persons() {
		createOnePerson(900);
	}
	
	@Test
	public void testCreate0001000Persons() {
		createOnePerson(1000);
	}
	
	@Test
	public void testCreate0001250Persons() {
		createOnePerson(1250);
	}
	
	@Test
	public void testCreate0001500Persons() {
		createOnePerson(1500);
	}
	
	@Test
	public void testCreate0001750Persons() {
		createOnePerson(1750);
	}
	
	@Test
	public void testCreate0002000Persons() {
		createOnePerson(2000);
	}
	
	@Test
	public void testCreate0002500Persons() {
		createOnePerson(2500);
	}
	
	@Test
	public void testCreate0003000Persons() {
		createOnePerson(3000);
	}
	
	@Test
	public void testCreate0003500Persons() {
		createOnePerson(3500);
	}
	
	@Test
	public void testCreate0004000Persons() {
		createOnePerson(4000);
	}
	
	@Test
	public void testCreate0004500Persons() {
		createOnePerson(4500);
	}
	
	@Test
	public void testCreate0005000Persons() {
		createOnePerson(5000);
	}
	
	@Test
	public void testCreate0006000Persons() {
		createOnePerson(6000);
	}
	
	@Test
	public void testCreate0007000Persons() {
		createOnePerson(7000);
	}
	
	@Test
	public void testCreate0008000Persons() {
		createOnePerson(8000);
	}
	
	@Test
	public void testCreate0009000Persons() {
		createOnePerson(9000);
	}
	
	@Test
	public void testCreate0010000Persons() {
		createOnePerson(10000);
	}
	
	@Test
	public void testCreate0012500Persons() {
		createOnePerson(12500);
	}
	
	@Test
	public void testCreate0015000Persons() {
		createOnePerson(15000);
	}
	
	@Test
	public void testCreate0017500Persons() {
		createOnePerson(17500);
	}
	
	@Test
	public void testCreate0020000Persons() {
		createOnePerson(20000);
	}
	
	@Test
	public void testCreate0022500Persons() {
		createOnePerson(22500);
	}
	
	@Test
	public void testCreate0025000Persons() {
		createOnePerson(25000);
	}
	
	@Test
	public void testCreate0030000Persons() {
		createOnePerson(30000);
	}
	
	@Test
	public void testCreate0035000Persons() {
		createOnePerson(35000);
	}
	
	@Test
	public void testCreate0040000Persons() {
		createOnePerson(40000);
	}
	
	@Test
	public void testCreate0045000Persons() {
		createOnePerson(45000);
	}
	
	@Test
	public void testCreate0050000Persons() {
		createOnePerson(50000);
	}
}
