package org.benchmarx.examples.familiestopersons.scalability;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.cases.ConstDeltaCFCSyncTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.MonotonicCreating;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.MonotonicDeleting;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Families.FamilyRegister;
import Persons.PersonRegister;

/**
 * This class implements scalability tests for concurrent synchronisation cases
 * with an increasing model size and a constant number of conflict-free (CF)
 * changes. A conflict-free change consists of a new family member Hugo (son) to
 * the family Simpson and a deletion of Lisa in the person register (see
 * {@link MonotonicCreating} and {@link MonotonicDeleting}.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScalabilityConstDeltaCFCSync extends ScalabilityTests {

	public static final int NR_OF_EDITED_FAMILIES = 5;

	public ScalabilityConstDeltaCFCSync(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool, "CDCFCSync_");
	}

	private void createFamiliesAndConflictFreeChanges(int nrOfFamilyPairs) {
		runTest(ConstDeltaCFCSyncTestcase.class, tool, nrOfFamilyPairs);
	}
	@Test
	public void testCreate0000005FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(5);
	}
	@Test
	public void testCreate0000010FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(10);
	}
	@Test
	public void testCreate0000015FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(15);
	}
	@Test
	public void testCreate0000020FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(20);
	}
	@Test
	public void testCreate0000025FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(25);
	}
	@Test
	public void testCreate0000030FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(30);
	}
	@Test
	public void testCreate0000035FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(35);
	}
	@Test
	public void testCreate0000040FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(40);
	}
	@Test
	public void testCreate0000045FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(45);
	}
	@Test
	public void testCreate0000050FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(50);
	}
	@Test
	public void testCreate0000055FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(55);
	}
	@Test
	public void testCreate0000060FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(60);
	}
	@Test
	public void testCreate0000065FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(65);
	}
	@Test
	public void testCreate0000070FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(70);
	}
	@Test
	public void testCreate0000075FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(75);
	}
	@Test
	public void testCreate0000080FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(80);
	}
	@Test
	public void testCreate0000085FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(85);
	}
	@Test
	public void testCreate0000090FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(90);
	}
	@Test
	public void testCreate0000095FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(95);
	}
	@Test
	public void testCreate0000100FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(100);
	}
	@Test
	public void testCreate0000110FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(110);
	}
	@Test
	public void testCreate0000120FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(120);
	}
	@Test
	public void testCreate0000130FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(130);
	}
	@Test
	public void testCreate0000140FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(140);
	}
	@Test
	public void testCreate0000150FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(150);
	}
	@Test
	public void testCreate0000160FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(160);
	}
	@Test
	public void testCreate0000170FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(170);
	}
	@Test
	public void testCreate0000180FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(180);
	}
	@Test
	public void testCreate0000190FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(190);
	}
	@Test
	public void testCreate0000200FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(200);
	}
	@Test
	public void testCreate0000220FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(220);
	}
	@Test
	public void testCreate0000240FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(240);
	}
	@Test
	public void testCreate0000260FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(260);
	}
	@Test
	public void testCreate0000280FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(280);
	}
	@Test
	public void testCreate0000300FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(300);
	}
	@Test
	public void testCreate0000320FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(320);
	}
	@Test
	public void testCreate0000340FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(340);
	}
	@Test
	public void testCreate0000360FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(360);
	}
	@Test
	public void testCreate0000380FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(380);
	}
	@Test
	public void testCreate0000400FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(400);
	}
	@Test
	public void testCreate0000420FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(420);
	}
	@Test
	public void testCreate0000440FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(440);
	}
	@Test
	public void testCreate0000460FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(460);
	}
	@Test
	public void testCreate0000480FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(480);
	}
	@Test
	public void testCreate0000500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(500);
	}
	@Test
	public void testCreate0000550FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(550);
	}
	@Test
	public void testCreate0000600FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(600);
	}
	@Test
	public void testCreate0000650FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(650);
	}
	@Test
	public void testCreate0000700FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(700);
	}
	@Test
	public void testCreate0000750FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(750);
	}
	@Test
	public void testCreate0000800FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(800);
	}
	@Test
	public void testCreate0000850FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(850);
	}
	@Test
	public void testCreate0000900FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(900);
	}
	@Test
	public void testCreate0000950FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(950);
	}
	@Test
	public void testCreate0001000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1000);
	}
	@Test
	public void testCreate0001100FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1100);
	}
	@Test
	public void testCreate0001200FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1200);
	}
	@Test
	public void testCreate0001300FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1300);
	}
	@Test
	public void testCreate0001400FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1400);
	}
	@Test
	public void testCreate0001500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1500);
	}
	@Test
	public void testCreate0001600FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1600);
	}
	@Test
	public void testCreate0001700FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1700);
	}
	@Test
	public void testCreate0001800FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1800);
	}
	@Test
	public void testCreate0001900FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1900);
	}
	@Test
	public void testCreate0002000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(2000);
	}
}
