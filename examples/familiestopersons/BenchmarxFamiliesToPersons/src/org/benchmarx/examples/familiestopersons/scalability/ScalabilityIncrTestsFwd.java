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
	public void testCreate0000015FamilyMembers() {
		createOneFamilyMember(15);
	}
	@Test
	public void testCreate0000020FamilyMembers() {
		createOneFamilyMember(20);
	}
	@Test
	public void testCreate0000025FamilyMembers() {
		createOneFamilyMember(25);
	}
	@Test
	public void testCreate0000030FamilyMembers() {
		createOneFamilyMember(30);
	}
	@Test
	public void testCreate0000035FamilyMembers() {
		createOneFamilyMember(35);
	}
	@Test
	public void testCreate0000040FamilyMembers() {
		createOneFamilyMember(40);
	}
	@Test
	public void testCreate0000045FamilyMembers() {
		createOneFamilyMember(45);
	}
	@Test
	public void testCreate0000050FamilyMembers() {
		createOneFamilyMember(50);
	}
	@Test
	public void testCreate0000055FamilyMembers() {
		createOneFamilyMember(55);
	}
	@Test
	public void testCreate0000060FamilyMembers() {
		createOneFamilyMember(60);
	}
	@Test
	public void testCreate0000065FamilyMembers() {
		createOneFamilyMember(65);
	}
	@Test
	public void testCreate0000070FamilyMembers() {
		createOneFamilyMember(70);
	}
	@Test
	public void testCreate0000075FamilyMembers() {
		createOneFamilyMember(75);
	}
	@Test
	public void testCreate0000080FamilyMembers() {
		createOneFamilyMember(80);
	}
	@Test
	public void testCreate0000085FamilyMembers() {
		createOneFamilyMember(85);
	}
	@Test
	public void testCreate0000090FamilyMembers() {
		createOneFamilyMember(90);
	}
	@Test
	public void testCreate0000095FamilyMembers() {
		createOneFamilyMember(95);
	}
	@Test
	public void testCreate0000100FamilyMembers() {
		createOneFamilyMember(100);
	}
	@Test
	public void testCreate0000110FamilyMembers() {
		createOneFamilyMember(110);
	}
	@Test
	public void testCreate0000120FamilyMembers() {
		createOneFamilyMember(120);
	}
	@Test
	public void testCreate0000130FamilyMembers() {
		createOneFamilyMember(130);
	}
	@Test
	public void testCreate0000140FamilyMembers() {
		createOneFamilyMember(140);
	}
	@Test
	public void testCreate0000150FamilyMembers() {
		createOneFamilyMember(150);
	}
	@Test
	public void testCreate0000160FamilyMembers() {
		createOneFamilyMember(160);
	}
	@Test
	public void testCreate0000170FamilyMembers() {
		createOneFamilyMember(170);
	}
	@Test
	public void testCreate0000180FamilyMembers() {
		createOneFamilyMember(180);
	}
	@Test
	public void testCreate0000190FamilyMembers() {
		createOneFamilyMember(190);
	}
	@Test
	public void testCreate0000200FamilyMembers() {
		createOneFamilyMember(200);
	}
	@Test
	public void testCreate0000220FamilyMembers() {
		createOneFamilyMember(220);
	}
	@Test
	public void testCreate0000240FamilyMembers() {
		createOneFamilyMember(240);
	}
	@Test
	public void testCreate0000260FamilyMembers() {
		createOneFamilyMember(260);
	}
	@Test
	public void testCreate0000280FamilyMembers() {
		createOneFamilyMember(280);
	}
	@Test
	public void testCreate0000300FamilyMembers() {
		createOneFamilyMember(300);
	}
	@Test
	public void testCreate0000320FamilyMembers() {
		createOneFamilyMember(320);
	}
	@Test
	public void testCreate0000340FamilyMembers() {
		createOneFamilyMember(340);
	}
	@Test
	public void testCreate0000360FamilyMembers() {
		createOneFamilyMember(360);
	}
	@Test
	public void testCreate0000380FamilyMembers() {
		createOneFamilyMember(380);
	}
	@Test
	public void testCreate0000400FamilyMembers() {
		createOneFamilyMember(400);
	}
	@Test
	public void testCreate0000420FamilyMembers() {
		createOneFamilyMember(420);
	}
	@Test
	public void testCreate0000440FamilyMembers() {
		createOneFamilyMember(440);
	}
	@Test
	public void testCreate0000460FamilyMembers() {
		createOneFamilyMember(460);
	}
	@Test
	public void testCreate0000480FamilyMembers() {
		createOneFamilyMember(480);
	}
	@Test
	public void testCreate0000500FamilyMembers() {
		createOneFamilyMember(500);
	}
	@Test
	public void testCreate0000550FamilyMembers() {
		createOneFamilyMember(550);
	}
	@Test
	public void testCreate0000600FamilyMembers() {
		createOneFamilyMember(600);
	}
	@Test
	public void testCreate0000650FamilyMembers() {
		createOneFamilyMember(650);
	}
	@Test
	public void testCreate0000700FamilyMembers() {
		createOneFamilyMember(700);
	}
	@Test
	public void testCreate0000750FamilyMembers() {
		createOneFamilyMember(750);
	}
	@Test
	public void testCreate0000800FamilyMembers() {
		createOneFamilyMember(800);
	}
	@Test
	public void testCreate0000850FamilyMembers() {
		createOneFamilyMember(850);
	}
	@Test
	public void testCreate0000900FamilyMembers() {
		createOneFamilyMember(900);
	}
	@Test
	public void testCreate0000950FamilyMembers() {
		createOneFamilyMember(950);
	}
	@Test
	public void testCreate0001000FamilyMembers() {
		createOneFamilyMember(1000);
	}
	@Test
	public void testCreate0001100FamilyMembers() {
		createOneFamilyMember(1100);
	}
	@Test
	public void testCreate0001200FamilyMembers() {
		createOneFamilyMember(1200);
	}
	@Test
	public void testCreate0001300FamilyMembers() {
		createOneFamilyMember(1300);
	}
	@Test
	public void testCreate0001400FamilyMembers() {
		createOneFamilyMember(1400);
	}
	@Test
	public void testCreate0001500FamilyMembers() {
		createOneFamilyMember(1500);
	}
	@Test
	public void testCreate0001600FamilyMembers() {
		createOneFamilyMember(1600);
	}
	@Test
	public void testCreate0001700FamilyMembers() {
		createOneFamilyMember(1700);
	}
	@Test
	public void testCreate0001800FamilyMembers() {
		createOneFamilyMember(1800);
	}
	@Test
	public void testCreate0001900FamilyMembers() {
		createOneFamilyMember(1900);
	}
	@Test
	public void testCreate0002000FamilyMembers() {
		createOneFamilyMember(2000);
	}
}
