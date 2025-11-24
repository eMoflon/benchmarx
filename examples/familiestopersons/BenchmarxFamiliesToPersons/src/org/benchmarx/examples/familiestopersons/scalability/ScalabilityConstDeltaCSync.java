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
	public void testCreate0000020FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(20);
	}
	
	@Test
	public void testCreate0000030FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(30);
	}
	
	@Test
	public void testCreate0000040FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(40);
	}
	
	@Test
	public void testCreate0000050FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(50);
	}
	

	@Test
	public void testCreate0000075FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(75);
	}
	
	@Test
	public void testCreate0000100FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(100);
	}
	
	@Test
	public void testCreate0000150FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(150);
	}
	
	@Test
	public void testCreate0000200FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(200);
	}
	

	@Test
	public void testCreate0000250FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(250);
	}
	
	@Test
	public void testCreate0000300FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(300);
	}
	
	@Test
	public void testCreate0000350FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(350);
	}
	
	@Test
	public void testCreate0000400FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(400);
	}
	
	@Test
	public void testCreate0000450FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(450);
	}
	
	@Test
	public void testCreate0000500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(500);
	}
	
	@Test
	public void testCreate0000600FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(600);
	}
	
	@Test
	public void testCreate0000700FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(700);
	}
	
	@Test
	public void testCreate0000800FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(800);
	}
	
	@Test
	public void testCreate0000900FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(900);
	}
	
	@Test
	public void testCreate0001000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1000);
	}
	
	@Test
	public void testCreate0001250FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1250);
	}
	
	@Test
	public void testCreate0001500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1500);
	}
	
	@Test
	public void testCreate0001750FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(1750);
	}
	
	@Test
	public void testCreate0002000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(2000);
	}
	
	@Test
	public void testCreate0002500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(2500);
	}
	
	@Test
	public void testCreate0003000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(3000);
	}
	
	@Test
	public void testCreate0003500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(3500);
	}
	
	@Test
	public void testCreate0004000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(4000);
	}
	
	@Test
	public void testCreate0004500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(4500);
	}
	
	@Test
	public void testCreate0005000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(5000);
	}
	
	@Test
	public void testCreate0006000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(6000);
	}
	
	@Test
	public void testCreate0007000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(7000);
	}
	
	@Test
	public void testCreate0008000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(8000);
	}
	
	@Test
	public void testCreate0009000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(9000);
	}
	
	@Test
	public void testCreate0010000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(10000);
	}
	
	@Test
	public void testCreate0012500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(12500);
	}
	
	@Test
	public void testCreate0015000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(15000);
	}
	
	@Test
	public void testCreate0017500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(17500);
	}
	
	@Test
	public void testCreate0020000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(20000);
	}
	
	@Test
	public void testCreate0022500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(22500);
	}
	
	@Test
	public void testCreate0025000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(25000);
	}
	
	@Test
	public void testCreate0030000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(30000);
	}
	
	@Test
	public void testCreate0035000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(35000);
	}
	
	@Test
	public void testCreate0040000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(40000);
	}
	
	@Test
	public void testCreate0045000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(45000);
	}
	
	@Test
	public void testCreate0050000FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(50000);
	}
}
