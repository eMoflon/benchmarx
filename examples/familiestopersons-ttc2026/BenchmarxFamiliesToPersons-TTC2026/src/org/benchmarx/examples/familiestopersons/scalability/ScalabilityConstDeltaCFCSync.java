package org.benchmarx.examples.familiestopersons.scalability;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.MonotonicCreating;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.MonotonicDeleting;
import org.benchmarx.util.BXToolTimer;
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

	public static final int NR_OF_EDITED_FAMILIES = 3;

	public ScalabilityConstDeltaCFCSync(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool, "CDCFCSync_");
	}

	private void createFamiliesAndConflictFreeChanges(int nrOfFamilyPairs, int nrOfEditedFamilyPairs) {
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
	public void testCreate0000003FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(3, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000005FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(5, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000010FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(10, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000020FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(20, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000030FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(30, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000040FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(40, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000050FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(50, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000060FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(60, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000070FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(70, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000080FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(80, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000090FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(90, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000100FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(100, NR_OF_EDITED_FAMILIES);
	}
}
