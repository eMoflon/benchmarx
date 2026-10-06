package org.benchmarx.examples.familiestopersons.scalability;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.cases.IncrTestsBwdTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
	public void testCreate0000015Persons() {
		createOnePerson(15);
	}
	@Test
	public void testCreate0000020Persons() {
		createOnePerson(20);
	}
	@Test
	public void testCreate0000025Persons() {
		createOnePerson(25);
	}
	@Test
	public void testCreate0000030Persons() {
		createOnePerson(30);
	}
	@Test
	public void testCreate0000035Persons() {
		createOnePerson(35);
	}
	@Test
	public void testCreate0000040Persons() {
		createOnePerson(40);
	}
	@Test
	public void testCreate0000045Persons() {
		createOnePerson(45);
	}
	@Test
	public void testCreate0000050Persons() {
		createOnePerson(50);
	}
	@Test
	public void testCreate0000055Persons() {
		createOnePerson(55);
	}
	@Test
	public void testCreate0000060Persons() {
		createOnePerson(60);
	}
	@Test
	public void testCreate0000065Persons() {
		createOnePerson(65);
	}
	@Test
	public void testCreate0000070Persons() {
		createOnePerson(70);
	}
	@Test
	public void testCreate0000075Persons() {
		createOnePerson(75);
	}
	@Test
	public void testCreate0000080Persons() {
		createOnePerson(80);
	}
	@Test
	public void testCreate0000085Persons() {
		createOnePerson(85);
	}
	@Test
	public void testCreate0000090Persons() {
		createOnePerson(90);
	}
	@Test
	public void testCreate0000095Persons() {
		createOnePerson(95);
	}
	@Test
	public void testCreate0000100Persons() {
		createOnePerson(100);
	}
	@Test
	public void testCreate0000110Persons() {
		createOnePerson(110);
	}
	@Test
	public void testCreate0000120Persons() {
		createOnePerson(120);
	}
	@Test
	public void testCreate0000130Persons() {
		createOnePerson(130);
	}
	@Test
	public void testCreate0000140Persons() {
		createOnePerson(140);
	}
	@Test
	public void testCreate0000150Persons() {
		createOnePerson(150);
	}
	@Test
	public void testCreate0000160Persons() {
		createOnePerson(160);
	}
	@Test
	public void testCreate0000170Persons() {
		createOnePerson(170);
	}
	@Test
	public void testCreate0000180Persons() {
		createOnePerson(180);
	}
	@Test
	public void testCreate0000190Persons() {
		createOnePerson(190);
	}
	@Test
	public void testCreate0000200Persons() {
		createOnePerson(200);
	}
	@Test
	public void testCreate0000220Persons() {
		createOnePerson(220);
	}
	@Test
	public void testCreate0000240Persons() {
		createOnePerson(240);
	}
	@Test
	public void testCreate0000260Persons() {
		createOnePerson(260);
	}
	@Test
	public void testCreate0000280Persons() {
		createOnePerson(280);
	}
	@Test
	public void testCreate0000300Persons() {
		createOnePerson(300);
	}
	@Test
	public void testCreate0000320Persons() {
		createOnePerson(320);
	}
	@Test
	public void testCreate0000340Persons() {
		createOnePerson(340);
	}
	@Test
	public void testCreate0000360Persons() {
		createOnePerson(360);
	}
	@Test
	public void testCreate0000380Persons() {
		createOnePerson(380);
	}
	@Test
	public void testCreate0000400Persons() {
		createOnePerson(400);
	}
	@Test
	public void testCreate0000420Persons() {
		createOnePerson(420);
	}
	@Test
	public void testCreate0000440Persons() {
		createOnePerson(440);
	}
	@Test
	public void testCreate0000460Persons() {
		createOnePerson(460);
	}
	@Test
	public void testCreate0000480Persons() {
		createOnePerson(480);
	}
	@Test
	public void testCreate0000500Persons() {
		createOnePerson(500);
	}
	@Test
	public void testCreate0000550Persons() {
		createOnePerson(550);
	}
	@Test
	public void testCreate0000600Persons() {
		createOnePerson(600);
	}
	@Test
	public void testCreate0000650Persons() {
		createOnePerson(650);
	}
	@Test
	public void testCreate0000700Persons() {
		createOnePerson(700);
	}
	@Test
	public void testCreate0000750Persons() {
		createOnePerson(750);
	}
	@Test
	public void testCreate0000800Persons() {
		createOnePerson(800);
	}
	@Test
	public void testCreate0000850Persons() {
		createOnePerson(850);
	}
	@Test
	public void testCreate0000900Persons() {
		createOnePerson(900);
	}
	@Test
	public void testCreate0000950Persons() {
		createOnePerson(950);
	}
	@Test
	public void testCreate0001000Persons() {
		createOnePerson(1000);
	}
	@Test
	public void testCreate0001100Persons() {
		createOnePerson(1100);
	}
	@Test
	public void testCreate0001200Persons() {
		createOnePerson(1200);
	}
	@Test
	public void testCreate0001300Persons() {
		createOnePerson(1300);
	}
	@Test
	public void testCreate0001400Persons() {
		createOnePerson(1400);
	}
	@Test
	public void testCreate0001500Persons() {
		createOnePerson(1500);
	}
	@Test
	public void testCreate0001600Persons() {
		createOnePerson(1600);
	}
	@Test
	public void testCreate0001700Persons() {
		createOnePerson(1700);
	}
	@Test
	public void testCreate0001800Persons() {
		createOnePerson(1800);
	}
	@Test
	public void testCreate0001900Persons() {
		createOnePerson(1900);
	}
	@Test
	public void testCreate0002000Persons() {
		createOnePerson(2000);
	}
}
