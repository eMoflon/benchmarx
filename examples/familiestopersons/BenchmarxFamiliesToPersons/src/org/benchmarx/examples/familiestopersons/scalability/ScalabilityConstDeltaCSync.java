package org.benchmarx.examples.familiestopersons.scalability;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.cases.ConstDeltaCSyncTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.Conflicts;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Families.FamilyRegister;
import Persons.PersonRegister;

/**
 * This class implements scalability tests for concurrent synchronisation cases
 * with an increasing model size and a constant number of conflicting changes. The
 * families are created in pairs of two (Simpson and Flanders). A conflicting
 * change consists of a relocation of Lisa to the family Flanders and a deletion
 * of Lisa in the person register (see {@link Conflicts}.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScalabilityConstDeltaCSync extends ScalabilityTests {

	public static final int NR_OF_EDITED_FAMILY_PAIRS = 5;

	public ScalabilityConstDeltaCSync(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool, "CDCsync_");
	}

	private void createFamilyPairsAndConflictingChanges(int nrOfFamilyPairs) {
		runTest(ConstDeltaCSyncTestcase.class, tool, nrOfFamilyPairs);
	}
	@Test
	public void testCreate0000005FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(5);
	}
	@Test
	public void testCreate0000010FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(10);
	}
	@Test
	public void testCreate0000015FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(15);
	}
	@Test
	public void testCreate0000020FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(20);
	}
	@Test
	public void testCreate0000025FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(25);
	}
	@Test
	public void testCreate0000030FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(30);
	}
	@Test
	public void testCreate0000035FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(35);
	}
	@Test
	public void testCreate0000040FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(40);
	}
	@Test
	public void testCreate0000045FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(45);
	}
	@Test
	public void testCreate0000050FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(50);
	}
	@Test
	public void testCreate0000055FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(55);
	}
	@Test
	public void testCreate0000060FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(60);
	}
	@Test
	public void testCreate0000065FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(65);
	}
	@Test
	public void testCreate0000070FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(70);
	}
	@Test
	public void testCreate0000075FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(75);
	}
	@Test
	public void testCreate0000080FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(80);
	}
	@Test
	public void testCreate0000085FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(85);
	}
	@Test
	public void testCreate0000090FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(90);
	}
	@Test
	public void testCreate0000095FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(95);
	}
	@Test
	public void testCreate0000100FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(100);
	}
	@Test
	public void testCreate0000110FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(110);
	}
	@Test
	public void testCreate0000120FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(120);
	}
	@Test
	public void testCreate0000130FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(130);
	}
	@Test
	public void testCreate0000140FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(140);
	}
	@Test
	public void testCreate0000150FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(150);
	}
	@Test
	public void testCreate0000160FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(160);
	}
	@Test
	public void testCreate0000170FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(170);
	}
	@Test
	public void testCreate0000180FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(180);
	}
	@Test
	public void testCreate0000190FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(190);
	}
	@Test
	public void testCreate0000200FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(200);
	}
	@Test
	public void testCreate0000220FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(220);
	}
	@Test
	public void testCreate0000240FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(240);
	}
	@Test
	public void testCreate0000260FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(260);
	}
	@Test
	public void testCreate0000280FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(280);
	}
	@Test
	public void testCreate0000300FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(300);
	}
	@Test
	public void testCreate0000320FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(320);
	}
	@Test
	public void testCreate0000340FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(340);
	}
	@Test
	public void testCreate0000360FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(360);
	}
	@Test
	public void testCreate0000380FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(380);
	}
	@Test
	public void testCreate0000400FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(400);
	}
	@Test
	public void testCreate0000420FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(420);
	}
	@Test
	public void testCreate0000440FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(440);
	}
	@Test
	public void testCreate0000460FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(460);
	}
	@Test
	public void testCreate0000480FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(480);
	}
	@Test
	public void testCreate0000500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(500);
	}
	@Test
	public void testCreate0000550FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(550);
	}
	@Test
	public void testCreate0000600FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(600);
	}
	@Test
	public void testCreate0000650FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(650);
	}
	@Test
	public void testCreate0000700FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(700);
	}
	@Test
	public void testCreate0000750FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(750);
	}
	@Test
	public void testCreate0000800FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(800);
	}
	@Test
	public void testCreate0000850FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(850);
	}
	@Test
	public void testCreate0000900FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(900);
	}
	@Test
	public void testCreate0000950FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(950);
	}
	@Test
	public void testCreate0001000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1000);
	}
	@Test
	public void testCreate0001100FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1100);
	}
	@Test
	public void testCreate0001200FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1200);
	}
	@Test
	public void testCreate0001300FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1300);
	}
	@Test
	public void testCreate0001400FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1400);
	}
	@Test
	public void testCreate0001500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1500);
	}
	@Test
	public void testCreate0001600FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1600);
	}
	@Test
	public void testCreate0001700FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1700);
	}
	@Test
	public void testCreate0001800FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1800);
	}
	@Test
	public void testCreate0001900FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1900);
	}
	@Test
	public void testCreate0002000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(2000);
	}
}
