package org.benchmarx.examples.familiestopersons.scalability;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.cases.BatchBwdTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
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
		runTest(BatchBwdTestcase.class, tool, nrOfFamilies);
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
	public void testCreate0000015Persons() {
		createPersons(15);
	}
	@Test
	public void testCreate0000020Persons() {
		createPersons(20);
	}
	@Test
	public void testCreate0000025Persons() {
		createPersons(25);
	}
	@Test
	public void testCreate0000030Persons() {
		createPersons(30);
	}
	@Test
	public void testCreate0000035Persons() {
		createPersons(35);
	}
	@Test
	public void testCreate0000040Persons() {
		createPersons(40);
	}
	@Test
	public void testCreate0000045Persons() {
		createPersons(45);
	}
	@Test
	public void testCreate0000050Persons() {
		createPersons(50);
	}
	@Test
	public void testCreate0000055Persons() {
		createPersons(55);
	}
	@Test
	public void testCreate0000060Persons() {
		createPersons(60);
	}
	@Test
	public void testCreate0000065Persons() {
		createPersons(65);
	}
	@Test
	public void testCreate0000070Persons() {
		createPersons(70);
	}
	@Test
	public void testCreate0000075Persons() {
		createPersons(75);
	}
	@Test
	public void testCreate0000080Persons() {
		createPersons(80);
	}
	@Test
	public void testCreate0000085Persons() {
		createPersons(85);
	}
	@Test
	public void testCreate0000090Persons() {
		createPersons(90);
	}
	@Test
	public void testCreate0000095Persons() {
		createPersons(95);
	}
	@Test
	public void testCreate0000100Persons() {
		createPersons(100);
	}
	@Test
	public void testCreate0000110Persons() {
		createPersons(110);
	}
	@Test
	public void testCreate0000120Persons() {
		createPersons(120);
	}
	@Test
	public void testCreate0000130Persons() {
		createPersons(130);
	}
	@Test
	public void testCreate0000140Persons() {
		createPersons(140);
	}
	@Test
	public void testCreate0000150Persons() {
		createPersons(150);
	}
	@Test
	public void testCreate0000160Persons() {
		createPersons(160);
	}
	@Test
	public void testCreate0000170Persons() {
		createPersons(170);
	}
	@Test
	public void testCreate0000180Persons() {
		createPersons(180);
	}
	@Test
	public void testCreate0000190Persons() {
		createPersons(190);
	}
	@Test
	public void testCreate0000200Persons() {
		createPersons(200);
	}
	@Test
	public void testCreate0000220Persons() {
		createPersons(220);
	}
	@Test
	public void testCreate0000240Persons() {
		createPersons(240);
	}
	@Test
	public void testCreate0000260Persons() {
		createPersons(260);
	}
	@Test
	public void testCreate0000280Persons() {
		createPersons(280);
	}
	@Test
	public void testCreate0000300Persons() {
		createPersons(300);
	}
	@Test
	public void testCreate0000320Persons() {
		createPersons(320);
	}
	@Test
	public void testCreate0000340Persons() {
		createPersons(340);
	}
	@Test
	public void testCreate0000360Persons() {
		createPersons(360);
	}
	@Test
	public void testCreate0000380Persons() {
		createPersons(380);
	}
	@Test
	public void testCreate0000400Persons() {
		createPersons(400);
	}
	@Test
	public void testCreate0000420Persons() {
		createPersons(420);
	}
	@Test
	public void testCreate0000440Persons() {
		createPersons(440);
	}
	@Test
	public void testCreate0000460Persons() {
		createPersons(460);
	}
	@Test
	public void testCreate0000480Persons() {
		createPersons(480);
	}
	@Test
	public void testCreate0000500Persons() {
		createPersons(500);
	}
	@Test
	public void testCreate0000550Persons() {
		createPersons(550);
	}
	@Test
	public void testCreate0000600Persons() {
		createPersons(600);
	}
	@Test
	public void testCreate0000650Persons() {
		createPersons(650);
	}
	@Test
	public void testCreate0000700Persons() {
		createPersons(700);
	}
	@Test
	public void testCreate0000750Persons() {
		createPersons(750);
	}
	@Test
	public void testCreate0000800Persons() {
		createPersons(800);
	}
	@Test
	public void testCreate0000850Persons() {
		createPersons(850);
	}
	@Test
	public void testCreate0000900Persons() {
		createPersons(900);
	}
	@Test
	public void testCreate0000950Persons() {
		createPersons(950);
	}
	@Test
	public void testCreate0001000Persons() {
		createPersons(1000);
	}
	@Test
	public void testCreate0001100Persons() {
		createPersons(1100);
	}
	@Test
	public void testCreate0001200Persons() {
		createPersons(1200);
	}
	@Test
	public void testCreate0001300Persons() {
		createPersons(1300);
	}
	@Test
	public void testCreate0001400Persons() {
		createPersons(1400);
	}
	@Test
	public void testCreate0001500Persons() {
		createPersons(1500);
	}
	@Test
	public void testCreate0001600Persons() {
		createPersons(1600);
	}
	@Test
	public void testCreate0001700Persons() {
		createPersons(1700);
	}
	@Test
	public void testCreate0001800Persons() {
		createPersons(1800);
	}
	@Test
	public void testCreate0001900Persons() {
		createPersons(1900);
	}
	@Test
	public void testCreate0002000Persons() {
		createPersons(2000);
	}
}
