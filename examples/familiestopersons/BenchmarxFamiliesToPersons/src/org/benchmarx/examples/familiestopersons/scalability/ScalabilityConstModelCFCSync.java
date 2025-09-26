package org.benchmarx.examples.familiestopersons.scalability;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.Conflicts;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.MonotonicCreating;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.MonotonicDeleting;
import org.benchmarx.util.BXToolTimer;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

	public static final int NR_OF_FAMILY_PAIRS = 100;

	public ScalabilityConstModelCFCSync(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	private void createFamiliesAndConflictingChanges(int nrOfFamilyPairs, int nrOfEditedFamilyPairs) {
		var timer = new BXToolTimer<>(tool, REPEAT);

		assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT * REPEAT), () -> {
			results.put(nrOfFamilyPairs, //
					timer.timeEditAfterSetUpInS(
							srcEdit(() -> helperFamily.createSimpsonFamiliesWithMembers(nrOfFamilyPairs)),
							srcEdit(() -> helperFamily.createSonHugo(nrOfEditedFamilyPairs)),
							trgEdit(() -> helperPerson.deleteLisa(nrOfEditedFamilyPairs))));
		});
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000005ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 5);
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000010ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 10);
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000020ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 20);
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000030ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 30);
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000040ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 40);
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000050ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 50);
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000060ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 60);
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000070ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 70);
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000080ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 80);
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000090ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 90);
	}
	
	@Test
	public void testCreateFamiliesAndCreate0000100ConflictingChanges() {
		createFamiliesAndConflictingChanges(NR_OF_FAMILY_PAIRS, 100);
	}
}
