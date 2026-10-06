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
	public void testCreate0000015FamilyMembers() {
		createFamilies(15);
	}
	@Test
	public void testCreate0000020FamilyMembers() {
		createFamilies(20);
	}
	@Test
	public void testCreate0000025FamilyMembers() {
		createFamilies(25);
	}
	@Test
	public void testCreate0000030FamilyMembers() {
		createFamilies(30);
	}
	@Test
	public void testCreate0000035FamilyMembers() {
		createFamilies(35);
	}
	@Test
	public void testCreate0000040FamilyMembers() {
		createFamilies(40);
	}
	@Test
	public void testCreate0000045FamilyMembers() {
		createFamilies(45);
	}
	@Test
	public void testCreate0000050FamilyMembers() {
		createFamilies(50);
	}
	@Test
	public void testCreate0000055FamilyMembers() {
		createFamilies(55);
	}
	@Test
	public void testCreate0000060FamilyMembers() {
		createFamilies(60);
	}
	@Test
	public void testCreate0000065FamilyMembers() {
		createFamilies(65);
	}
	@Test
	public void testCreate0000070FamilyMembers() {
		createFamilies(70);
	}
	@Test
	public void testCreate0000075FamilyMembers() {
		createFamilies(75);
	}
	@Test
	public void testCreate0000080FamilyMembers() {
		createFamilies(80);
	}
	@Test
	public void testCreate0000085FamilyMembers() {
		createFamilies(85);
	}
	@Test
	public void testCreate0000090FamilyMembers() {
		createFamilies(90);
	}
	@Test
	public void testCreate0000095FamilyMembers() {
		createFamilies(95);
	}
	@Test
	public void testCreate0000100FamilyMembers() {
		createFamilies(100);
	}
	@Test
	public void testCreate0000110FamilyMembers() {
		createFamilies(110);
	}
	@Test
	public void testCreate0000120FamilyMembers() {
		createFamilies(120);
	}
	@Test
	public void testCreate0000130FamilyMembers() {
		createFamilies(130);
	}
	@Test
	public void testCreate0000140FamilyMembers() {
		createFamilies(140);
	}
	@Test
	public void testCreate0000150FamilyMembers() {
		createFamilies(150);
	}
	@Test
	public void testCreate0000160FamilyMembers() {
		createFamilies(160);
	}
	@Test
	public void testCreate0000170FamilyMembers() {
		createFamilies(170);
	}
	@Test
	public void testCreate0000180FamilyMembers() {
		createFamilies(180);
	}
	@Test
	public void testCreate0000190FamilyMembers() {
		createFamilies(190);
	}
	@Test
	public void testCreate0000200FamilyMembers() {
		createFamilies(200);
	}
	@Test
	public void testCreate0000220FamilyMembers() {
		createFamilies(220);
	}
	@Test
	public void testCreate0000240FamilyMembers() {
		createFamilies(240);
	}
	@Test
	public void testCreate0000260FamilyMembers() {
		createFamilies(260);
	}
	@Test
	public void testCreate0000280FamilyMembers() {
		createFamilies(280);
	}
	@Test
	public void testCreate0000300FamilyMembers() {
		createFamilies(300);
	}
	@Test
	public void testCreate0000320FamilyMembers() {
		createFamilies(320);
	}
	@Test
	public void testCreate0000340FamilyMembers() {
		createFamilies(340);
	}
	@Test
	public void testCreate0000360FamilyMembers() {
		createFamilies(360);
	}
	@Test
	public void testCreate0000380FamilyMembers() {
		createFamilies(380);
	}
	@Test
	public void testCreate0000400FamilyMembers() {
		createFamilies(400);
	}
	@Test
	public void testCreate0000420FamilyMembers() {
		createFamilies(420);
	}
	@Test
	public void testCreate0000440FamilyMembers() {
		createFamilies(440);
	}
	@Test
	public void testCreate0000460FamilyMembers() {
		createFamilies(460);
	}
	@Test
	public void testCreate0000480FamilyMembers() {
		createFamilies(480);
	}
	@Test
	public void testCreate0000500FamilyMembers() {
		createFamilies(500);
	}
	@Test
	public void testCreate0000550FamilyMembers() {
		createFamilies(550);
	}
	@Test
	public void testCreate0000600FamilyMembers() {
		createFamilies(600);
	}
	@Test
	public void testCreate0000650FamilyMembers() {
		createFamilies(650);
	}
	@Test
	public void testCreate0000700FamilyMembers() {
		createFamilies(700);
	}
	@Test
	public void testCreate0000750FamilyMembers() {
		createFamilies(750);
	}
	@Test
	public void testCreate0000800FamilyMembers() {
		createFamilies(800);
	}
	@Test
	public void testCreate0000850FamilyMembers() {
		createFamilies(850);
	}
	@Test
	public void testCreate0000900FamilyMembers() {
		createFamilies(900);
	}
	@Test
	public void testCreate0000950FamilyMembers() {
		createFamilies(950);
	}
	@Test
	public void testCreate0001000FamilyMembers() {
		createFamilies(1000);
	}
	@Test
	public void testCreate0001100FamilyMembers() {
		createFamilies(1100);
	}
	@Test
	public void testCreate0001200FamilyMembers() {
		createFamilies(1200);
	}
	@Test
	public void testCreate0001300FamilyMembers() {
		createFamilies(1300);
	}
	@Test
	public void testCreate0001400FamilyMembers() {
		createFamilies(1400);
	}
	@Test
	public void testCreate0001500FamilyMembers() {
		createFamilies(1500);
	}
	@Test
	public void testCreate0001600FamilyMembers() {
		createFamilies(1600);
	}
	@Test
	public void testCreate0001700FamilyMembers() {
		createFamilies(1700);
	}
	@Test
	public void testCreate0001800FamilyMembers() {
		createFamilies(1800);
	}
	@Test
	public void testCreate0001900FamilyMembers() {
		createFamilies(1900);
	}
	@Test
	public void testCreate0002000FamilyMembers() {
		createFamilies(2000);
	}
}
