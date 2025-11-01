package org.benchmarx.examples.familiestopersons.scalability;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.scalability.cases.ConstModelCSyncTestcase;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.Conflicts;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Families.FamilyRegister;
import Persons.PersonRegister;

/**
 * This class implements scalability tests for concurrent synchronisation cases
 * with a constant model size and a growing number of conflicting changes. The
 * families are created in pairs of two (Simpson and Flanders). A conflicting
 * change consists of a relocation of Lisa to the family Flanders and a deletion
 * of Lisa in the person register (see {@link Conflicts}.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScalabilityConstModelCSync extends ScalabilityTests {

	public static final int NR_OF_FAMILY_PAIRS = 500;

	public ScalabilityConstModelCSync(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool, "CMCSync_");
	}

	private void createFamilyPairsAndConflictingChanges(int nrOfEditedFamilyPairs) {
		runTest(ConstModelCSyncTestcase.class, tool.getName(), nrOfEditedFamilyPairs);
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
	
}
