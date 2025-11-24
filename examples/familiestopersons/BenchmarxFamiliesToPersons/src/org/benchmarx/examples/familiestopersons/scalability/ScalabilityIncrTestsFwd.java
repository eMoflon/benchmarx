package org.benchmarx.examples.familiestopersons.scalability;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.cases.IncrTestsFwdTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Families.FamilyRegister;
import Persons.PersonRegister;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScalabilityIncrTestsFwd extends ScalabilityTests {

	public ScalabilityIncrTestsFwd(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool, "INCR_FWD_");
	}
	
	private void createOneFamilyMember(int nrOfFamilies) {
		runTest(IncrTestsFwdTestcase.class, tool, nrOfFamilies);
	}
	
	@Test
	public void testCreate0000005FamilyMembers() {
		createOneFamilyMember(5);
	}
	
	@Test
	public void testCreate0000010FamilyMembers() {
		createOneFamilyMember(10);
	}
	
	@Test
	public void testCreate0000020FamilyMembers() {
		createOneFamilyMember(20);
	}
	
	@Test
	public void testCreate0000030FamilyMembers() {
		createOneFamilyMember(30);
	}
	
	@Test
	public void testCreate0000040FamilyMembers() {
		createOneFamilyMember(40);
	}
	
	@Test
	public void testCreate0000050FamilyMembers() {
		createOneFamilyMember(50);
	}
	

	@Test
	public void testCreate0000075FamilyMembers() {
		createOneFamilyMember(75);
	}
	
	@Test
	public void testCreate0000100FamilyMembers() {
		createOneFamilyMember(100);
	}
	
	@Test
	public void testCreate0000150FamilyMembers() {
		createOneFamilyMember(150);
	}
	
	@Test
	public void testCreate0000200FamilyMembers() {
		createOneFamilyMember(200);
	}

	@Test
	public void testCreate0000250FamilyMembers() {
		createOneFamilyMember(250);
	}
	
	@Test
	public void testCreate0000300FamilyMembers() {
		createOneFamilyMember(300);
	}
	
	@Test
	public void testCreate0000350FamilyMembers() {
		createOneFamilyMember(350);
	}
	
	@Test
	public void testCreate0000400FamilyMembers() {
		createOneFamilyMember(400);
	}
	
	@Test
	public void testCreate0000450FamilyMembers() {
		createOneFamilyMember(450);
	}
	
	@Test
	public void testCreate0000500FamilyMembers() {
		createOneFamilyMember(500);
	}
	
	@Test
	public void testCreate0000600FamilyMembers() {
		createOneFamilyMember(600);
	}
	
	@Test
	public void testCreate0000700FamilyMembers() {
		createOneFamilyMember(700);
	}
	
	@Test
	public void testCreate0000800FamilyMembers() {
		createOneFamilyMember(800);
	}
	
	@Test
	public void testCreate0000900FamilyMembers() {
		createOneFamilyMember(900);
	}
	
	@Test
	public void testCreate0001000FamilyMembers() {
		createOneFamilyMember(1000);
	}
	
	@Test
	public void testCreate0001250FamilyMembers() {
		createOneFamilyMember(1250);
	}
	
	@Test
	public void testCreate0001500FamilyMembers() {
		createOneFamilyMember(1500);
	}
	
	@Test
	public void testCreate0001750FamilyMembers() {
		createOneFamilyMember(1750);
	}
	
	@Test
	public void testCreate0002000FamilyMembers() {
		createOneFamilyMember(2000);
	}
	
	@Test
	public void testCreate0002500FamilyMembers() {
		createOneFamilyMember(2500);
	}
	
	@Test
	public void testCreate0003000FamilyMembers() {
		createOneFamilyMember(3000);
	}
	
	@Test
	public void testCreate0003500FamilyMembers() {
		createOneFamilyMember(3500);
	}
	
	@Test
	public void testCreate0004000FamilyMembers() {
		createOneFamilyMember(4000);
	}
	
	@Test
	public void testCreate0004500FamilyMembers() {
		createOneFamilyMember(4500);
	}
	
	@Test
	public void testCreate0005000FamilyMembers() {
		createOneFamilyMember(5000);
	}
	
	@Test
	public void testCreate0006000FamilyMembers() {
		createOneFamilyMember(6000);
	}
	
	@Test
	public void testCreate0007000FamilyMembers() {
		createOneFamilyMember(7000);
	}
	
	@Test
	public void testCreate0008000FamilyMembers() {
		createOneFamilyMember(8000);
	}
	
	@Test
	public void testCreate0009000FamilyMembers() {
		createOneFamilyMember(9000);
	}
	
	@Test
	public void testCreate0010000FamilyMembers() {
		createOneFamilyMember(10000);
	}
	
	@Test
	public void testCreate0012500FamilyMembers() {
		createOneFamilyMember(12500);
	}
	
	@Test
	public void testCreate0015000FamilyMembers() {
		createOneFamilyMember(15000);
	}
	
	@Test
	public void testCreate0017500FamilyMembers() {
		createOneFamilyMember(17500);
	}
	
	@Test
	public void testCreate0020000FamilyMembers() {
		createOneFamilyMember(20000);
	}
	
	@Test
	public void testCreate0022500FamilyMembers() {
		createOneFamilyMember(22500);
	}
	
	@Test
	public void testCreate0025000FamilyMembers() {
		createOneFamilyMember(25000);
	}
	
	@Test
	public void testCreate0030000FamilyMembers() {
		createOneFamilyMember(30000);
	}
	
	@Test
	public void testCreate0035000FamilyMembers() {
		createOneFamilyMember(35000);
	}
	
	@Test
	public void testCreate0040000FamilyMembers() {
		createOneFamilyMember(40000);
	}
	
	@Test
	public void testCreate0045000FamilyMembers() {
		createOneFamilyMember(45000);
	}
	
	@Test
	public void testCreate0050000FamilyMembers() {
		createOneFamilyMember(50000);
	}
}
