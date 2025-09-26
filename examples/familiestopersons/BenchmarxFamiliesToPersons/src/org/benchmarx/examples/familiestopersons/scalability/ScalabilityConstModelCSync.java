package org.benchmarx.examples.familiestopersons.scalability;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.Conflicts;
import org.benchmarx.util.BXToolTimer;
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

	public static final int NR_OF_FAMILY_PAIRS = 50;

	public ScalabilityConstModelCSync(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool, "CMCSync_");
	}

	private void createFamilyPairsAndConflictingChanges(int nrOfFamilyPairs, int nrOfEditedFamilyPairs) {
		var timer = new BXToolTimer<>(tool, REPEAT);

		assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT * REPEAT), () -> {
			results.put(nrOfFamilyPairs, //
					timer.timeEditAfterSetUpInS(
							srcEdit(() -> {
								helperFamily.createSimpsonFamiliesWithMembers(nrOfFamilyPairs);
								helperFamily.createFlandersFamiliesWithMembers(nrOfFamilyPairs);
								}),
							srcEdit(() -> helperFamily.moveLisaToFlandersAsDaugther(nrOfEditedFamilyPairs)),
							trgEdit(() -> helperPerson.deleteLisa(nrOfEditedFamilyPairs))));
		});
	}

	@Test
	public void testCreateFamilyPairsAnd0000003ConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 3);
	}
	
	@Test
	public void testCreateFamilyPairsAnd0000005ConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 5);
	}

	@Test
	public void testCreateFamilyPairsAnd0000010ConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 10);
	}
	

	@Test
	@Ignore
	public void testCreateFamilyPairsAnd0000020ConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 20);
	}
	

	@Test
	@Ignore
	public void testCreateFamilyPairsAnd0000030ConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 30);
	}
	

	@Test
	@Ignore
	public void testCreateFamilyPairsAnd0000040ConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 40);
	}
	

	@Test
	@Ignore
	public void testCreateFamilyPairsAnd0000050ConflictingChanges() {
		createFamilyPairsAndConflictingChanges(NR_OF_FAMILY_PAIRS, 50);
	}
	
}
