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
		runTest(ConstDeltaCFCSyncTestcase.class, tool.getName(), nrOfFamilyPairs);
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
	public void testCreate0000020FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(20);
	}
	
	@Test
	public void testCreate0000030FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(30);
	}
	
	@Test
	public void testCreate0000040FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(40);
	}
	
	@Test
	public void testCreate0000050FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(50);
	}
	

	@Test
	public void testCreate0000075FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(75);
	}
	
	@Test
	public void testCreate0000100FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(100);
	}
	
	@Test
	public void testCreate0000150FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(150);
	}
	
	@Test
	public void testCreate0000200FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(200);
	}
	

	@Test
	public void testCreate0000250FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(250);
	}
	
	@Test
	public void testCreate0000300FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(300);
	}
	
	@Test
	public void testCreate0000350FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(350);
	}
	
	@Test
	public void testCreate0000400FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(400);
	}
	
	@Test
	public void testCreate0000450FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(450);
	}
	
	@Test
	public void testCreate0000500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(500);
	}
	
	@Test
	public void testCreate0000600FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(600);
	}
	
	@Test
	public void testCreate0000700FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(700);
	}
	
	@Test
	public void testCreate0000800FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(800);
	}
	
	@Test
	public void testCreate0000900FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(900);
	}
	
	@Test
	public void testCreate0001000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1000);
	}
	
	@Test
	public void testCreate0001250FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1250);
	}
	
	@Test
	public void testCreate0001500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1500);
	}
	
	@Test
	public void testCreate0001750FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1750);
	}
	
	@Test
	public void testCreate0002000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(2000);
	}
	
	@Test
	public void testCreate0002500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(2500);
	}
	
	@Test
	public void testCreate0003000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(3000);
	}
	
	@Test
	public void testCreate0003500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(3500);
	}
	
	@Test
	public void testCreate0004000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(4000);
	}
	
	@Test
	public void testCreate0004500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(4500);
	}
	
	@Test
	public void testCreate0005000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(5000);
	}
	
	@Test
	public void testCreate0006000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(6000);
	}
	
	@Test
	public void testCreate0007000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(7000);
	}
	
	@Test
	public void testCreate0008000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(8000);
	}
	
	@Test
	public void testCreate0009000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(9000);
	}
	
	@Test
	public void testCreate0010000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(10000);
	}
	
	@Test
	public void testCreate0012500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(12500);
	}
	
	@Test
	public void testCreate0015000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(15000);
	}
	
	@Test
	public void testCreate0017500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(17500);
	}
	
	@Test
	public void testCreate0020000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(20000);
	}
	
	@Test
	public void testCreate0022500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(22500);
	}
	
	@Test
	public void testCreate0025000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(25000);
	}
	
	@Test
	public void testCreate0030000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(30000);
	}
	
	@Test
	public void testCreate0035000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(35000);
	}
	
	@Test
	public void testCreate0040000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(40000);
	}
	
	@Test
	public void testCreate0045000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(45000);
	}
	
	@Test
	public void testCreate0050000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(50000);
	}
}
