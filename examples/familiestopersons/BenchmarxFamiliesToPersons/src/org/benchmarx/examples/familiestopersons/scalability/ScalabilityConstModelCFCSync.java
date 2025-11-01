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
		runTest(ConstModelCFCSyncTestcase.class, tool.getName(), nrOfEditedFamilyPairs);
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
	public void testCreate0000040FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(40);
	}
	
	@Test
	public void testCreate0000050FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(50);
	}
	

	@Test
	public void testCreate0000075FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(75);
	}
	
	@Test
	public void testCreate0000100FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(100);
	}
	
	@Test
	public void testCreate0000150FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(150);
	}
	
	@Test
	public void testCreate0000200FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(200);
	}
	

	@Test
	public void testCreate0000250FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(250);
	}
	
	@Test
	public void testCreate0000300FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(300);
	}
	
	@Test
	public void testCreate0000350FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(350);
	}
	
	@Test
	public void testCreate0000400FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(400);
	}
	
	@Test
	public void testCreate0000450FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(450);
	}
	
	@Test
	public void testCreate0000500FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(500);
	}
	
}
