package org.benchmarx.examples.familiestopersons.scalability;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.Conflicts;
import org.benchmarx.util.BXToolTimer;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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

	private void createFamilyPairsAndConflictingChanges(int nrOfFamilyPairs, int nrOfEditedFamilyPairs) {
		var timer = new BXToolTimer<>(tool, REPEAT);

		assertLastTestSuccessfull();
		
		assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT * REPEAT), () -> {
			results.put(nrOfEditedFamilyPairs, //
					timer.timeEditAfterSetUpInS(
							srcEdit(() -> {
								helperFamily.createSimpsonFamiliesWithMembers(nrOfFamilyPairs);
								helperFamily.createFlandersFamiliesWithMembers(nrOfFamilyPairs);
								}),
							srcEdit(() -> helperFamily.moveLisaToFlandersAsDaugther(nrOfEditedFamilyPairs)),
							trgEdit(() -> helperPerson.deleteLisa(nrOfEditedFamilyPairs))));
		});
		
		setTestSuccessfull();
	}

	@Test
	public void testCreate0000005FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 5);
	}
	
	@Test
	public void testCreate0000010FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 10);
	}
	
	@Test
	public void testCreate0000015FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 15);
	}
	
	@Test
	public void testCreate0000020FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 20);
	}
	
	@Test
	public void testCreate0000025FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 25);
	}
	
	@Test
	public void testCreate0000030FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 30);
	}
	
	@Test
	public void testCreate0000040FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 40);
	}
	
	@Test
	public void testCreate0000050FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 50);
	}
	

	@Test
	public void testCreate0000075FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 75);
	}
	
	@Test
	public void testCreate0000100FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 100);
	}
	
	@Test
	public void testCreate0000150FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 150);
	}
	
	@Test
	public void testCreate0000200FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 200);
	}
	

	@Test
	public void testCreate0000250FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 250);
	}
	
	@Test
	public void testCreate0000300FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 300);
	}
	
	@Test
	public void testCreate0000350FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 350);
	}
	
	@Test
	public void testCreate0000400FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 400);
	}
	
	@Test
	public void testCreate0000450FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 450);
	}
	
	@Test
	public void testCreate0000500FamilyPairsAndConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 500);
	}
	
}
