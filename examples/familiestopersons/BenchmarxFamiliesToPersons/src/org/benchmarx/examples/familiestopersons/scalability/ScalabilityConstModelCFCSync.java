package org.benchmarx.examples.familiestopersons.scalability;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.cases.ConstModelCFCSyncTestcase;
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
 * with a constant model size and a growing number of conflict-free (CF) changes. A conflict-free
 * change consists of a new family member Hugo (son) to the family Simpson and a deletion
 * of Lisa in the person register (see {@link MonotonicCreating} and {@link MonotonicDeleting}.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScalabilityConstModelCFCSync extends ScalabilityTests {

	public static final int NR_OF_FAMILY_PAIRS = 500;

	public ScalabilityConstModelCFCSync(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool, "CMCFCSync_");
	}

	private void createFamiliesAndConflictingChanges(int nrOfEditedFamilyPairs) {
		runTest(ConstModelCFCSyncTestcase.class, tool, nrOfEditedFamilyPairs);
	}
	@Test
	public void testCreate0000005FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(5);
	}
	@Test
	public void testCreate0000010FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(10);
	}
	@Test
	public void testCreate0000015FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(15);
	}
	@Test
	public void testCreate0000020FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(20);
	}
	@Test
	public void testCreate0000025FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(25);
	}
	@Test
	public void testCreate0000030FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(30);
	}
	@Test
	public void testCreate0000035FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(35);
	}
	@Test
	public void testCreate0000040FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(40);
	}
	@Test
	public void testCreate0000045FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(45);
	}
	@Test
	public void testCreate0000050FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(50);
	}
	@Test
	public void testCreate0000055FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(55);
	}
	@Test
	public void testCreate0000060FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(60);
	}
	@Test
	public void testCreate0000065FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(65);
	}
	@Test
	public void testCreate0000070FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(70);
	}
	@Test
	public void testCreate0000075FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(75);
	}
	@Test
	public void testCreate0000080FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(80);
	}
	@Test
	public void testCreate0000085FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(85);
	}
	@Test
	public void testCreate0000090FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(90);
	}
	@Test
	public void testCreate0000095FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(95);
	}
	@Test
	public void testCreate0000100FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(100);
	}
	@Test
	public void testCreate0000110FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(110);
	}
	@Test
	public void testCreate0000120FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(120);
	}
	@Test
	public void testCreate0000130FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(130);
	}
	@Test
	public void testCreate0000140FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(140);
	}
	@Test
	public void testCreate0000150FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(150);
	}
	@Test
	public void testCreate0000160FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(160);
	}
	@Test
	public void testCreate0000170FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(170);
	}
	@Test
	public void testCreate0000180FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(180);
	}
	@Test
	public void testCreate0000190FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(190);
	}
	@Test
	public void testCreate0000200FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(200);
	}
	@Test
	public void testCreate0000220FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(220);
	}
	@Test
	public void testCreate0000240FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(240);
	}
	@Test
	public void testCreate0000260FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(260);
	}
	@Test
	public void testCreate0000280FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(280);
	}
	@Test
	public void testCreate0000300FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(300);
	}
	@Test
	public void testCreate0000320FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(320);
	}
	@Test
	public void testCreate0000340FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(340);
	}
	@Test
	public void testCreate0000360FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(360);
	}
	@Test
	public void testCreate0000380FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(380);
	}
	@Test
	public void testCreate0000400FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(400);
	}
	@Test
	public void testCreate0000420FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(420);
	}
	@Test
	public void testCreate0000440FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(440);
	}
	@Test
	public void testCreate0000460FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(460);
	}
	@Test
	public void testCreate0000480FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(480);
	}
	@Test
	public void testCreate0000500FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(500);
	}
}
