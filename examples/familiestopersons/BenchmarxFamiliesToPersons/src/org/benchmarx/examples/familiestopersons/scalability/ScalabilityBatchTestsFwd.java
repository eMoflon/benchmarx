package org.benchmarx.examples.familiestopersons.scalability;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.cases.BatchFwdTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Families.FamilyRegister;
import Persons.PersonRegister;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScalabilityBatchTestsFwd extends ScalabilityTests {

	public ScalabilityBatchTestsFwd(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool, "FWD_");
	}
	
	private void createFamilies(int nrOfFamilies) {
		runTest(BatchFwdTestcase.class, tool, nrOfFamilies);
	}

	@Test
	public void testCreate0000005FamilyMembers() {
		createFamilies(5);
	}
	
	@Test
	public void testCreate0000010FamilyMembers() {
		createFamilies(10);
	}
	
	@Test
	public void testCreate0000020FamilyMembers() {
		createFamilies(20);
	}
	
	@Test
	public void testCreate0000030FamilyMembers() {
		createFamilies(30);
	}
	
	@Test
	public void testCreate0000040FamilyMembers() {
		createFamilies(40);
	}
	
	@Test
	public void testCreate0000050FamilyMembers() {
		createFamilies(50);
	}
	

	@Test
	public void testCreate0000075FamilyMembers() {
		createFamilies(75);
	}
	
	@Test
	public void testCreate0000100FamilyMembers() {
		createFamilies(100);
	}
	
	@Test
	public void testCreate0000150FamilyMembers() {
		createFamilies(150);
	}
	
	@Test
	public void testCreate0000200FamilyMembers() {
		createFamilies(200);
	}

	@Test
	public void testCreate0000250FamilyMembers() {
		createFamilies(250);
	}
	
	@Test
	public void testCreate0000300FamilyMembers() {
		createFamilies(300);
	}
	
	@Test
	public void testCreate0000350FamilyMembers() {
		createFamilies(350);
	}
	
	@Test
	public void testCreate0000400FamilyMembers() {
		createFamilies(400);
	}
	
	@Test
	public void testCreate0000450FamilyMembers() {
		createFamilies(450);
	}
	
	@Test
	public void testCreate0000500FamilyMembers() {
		createFamilies(500);
	}
	
	@Test
	public void testCreate0000600FamilyMembers() {
		createFamilies(600);
	}
	
	@Test
	public void testCreate0000700FamilyMembers() {
		createFamilies(700);
	}
	
	@Test
	public void testCreate0000800FamilyMembers() {
		createFamilies(800);
	}
	
	@Test
	public void testCreate0000900FamilyMembers() {
		createFamilies(900);
	}
	
	@Test
	public void testCreate0001000FamilyMembers() {
		createFamilies(1000);
	}
	
	@Test
	public void testCreate0001250FamilyMembers() {
		createFamilies(1250);
	}
	
	@Test
	public void testCreate0001500FamilyMembers() {
		createFamilies(1500);
	}
	
	@Test
	public void testCreate0001750FamilyMembers() {
		createFamilies(1750);
	}
	
	@Test
	public void testCreate0002000FamilyMembers() {
		createFamilies(2000);
	}
	
	@Test
	public void testCreate0002500FamilyMembers() {
		createFamilies(2500);
	}
	
	@Test
	public void testCreate0003000FamilyMembers() {
		createFamilies(3000);
	}
	
	@Test
	public void testCreate0003500FamilyMembers() {
		createFamilies(3500);
	}
	
	@Test
	public void testCreate0004000FamilyMembers() {
		createFamilies(4000);
	}
	
	@Test
	public void testCreate0004500FamilyMembers() {
		createFamilies(4500);
	}
	
	@Test
	public void testCreate0005000FamilyMembers() {
		createFamilies(5000);
	}
	
	@Test
	public void testCreate0006000FamilyMembers() {
		createFamilies(6000);
	}
	
	@Test
	public void testCreate0007000FamilyMembers() {
		createFamilies(7000);
	}
	
	@Test
	public void testCreate0008000FamilyMembers() {
		createFamilies(8000);
	}
	
	@Test
	public void testCreate0009000FamilyMembers() {
		createFamilies(9000);
	}
	
	@Test
	public void testCreate0010000FamilyMembers() {
		createFamilies(10000);
	}
	
	@Test
	public void testCreate0012500FamilyMembers() {
		createFamilies(12500);
	}
	
	@Test
	public void testCreate0015000FamilyMembers() {
		createFamilies(15000);
	}
	
	@Test
	public void testCreate0017500FamilyMembers() {
		createFamilies(17500);
	}
	
	@Test
	public void testCreate0020000FamilyMembers() {
		createFamilies(20000);
	}
	
	@Test
	public void testCreate0022500FamilyMembers() {
		createFamilies(22500);
	}
	
	@Test
	public void testCreate0025000FamilyMembers() {
		createFamilies(25000);
	}
	
	@Test
	public void testCreate0030000FamilyMembers() {
		createFamilies(30000);
	}
	
	@Test
	public void testCreate0035000FamilyMembers() {
		createFamilies(35000);
	}
	
	@Test
	public void testCreate0040000FamilyMembers() {
		createFamilies(40000);
	}
	
	@Test
	public void testCreate0045000FamilyMembers() {
		createFamilies(45000);
	}
	
	@Test
	public void testCreate0050000FamilyMembers() {
		createFamilies(50000);
	}
}
