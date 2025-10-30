package org.benchmarx.examples.familiestopersons.scalability;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.MonotonicCreating;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.MonotonicDeleting;
import org.benchmarx.util.BXToolTimer;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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

	private void createFamiliesAndConflictingChanges(int nrOfFamilyPairs, int nrOfEditedFamilyPairs) {
		var timer = new BXToolTimer<>(tool, REPEAT);

		assertLastTestSuccessfull();

		assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT * REPEAT), () -> {
			results.put(nrOfEditedFamilyPairs, //
					timer.timeEditAfterSetUpInS(
							srcEdit(() -> helperFamily.createSimpsonFamiliesWithMembers(nrOfFamilyPairs)),
							srcEdit(() -> helperFamily.createSonHugo(nrOfEditedFamilyPairs)),
							trgEdit(() -> helperPerson.deleteLisa(nrOfEditedFamilyPairs))));
		});
		
		setTestSuccessfull();
	}
	
	@Test
	public void testCreate0000005FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 5);
	}
	
	@Test
	public void testCreate0000010FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 10);
	}
	
	@Test
	public void testCreate0000015FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 15);
	}
	
	@Test
	public void testCreate0000020FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 20);
	}
	
	@Test
	public void testCreate0000025FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 25);
	}
	
	@Test
	public void testCreate0000030FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 30);
	}
	
	@Test
	public void testCreate0000040FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 40);
	}
	
	@Test
	public void testCreate0000050FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 50);
	}
	

	@Test
	public void testCreate0000075FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 75);
	}
	
	@Test
	public void testCreate0000100FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 100);
	}
	
	@Test
	public void testCreate0000150FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 150);
	}
	
	@Test
	public void testCreate0000200FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 200);
	}
	

	@Test
	public void testCreate0000250FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 250);
	}
	
	@Test
	public void testCreate0000300FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 300);
	}
	
	@Test
	public void testCreate0000350FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 350);
	}
	
	@Test
	public void testCreate0000400FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 400);
	}
	
	@Test
	public void testCreate0000450FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 450);
	}
	
	@Test
	public void testCreate0000500FamiliesAndConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 500);
	}
	
}
