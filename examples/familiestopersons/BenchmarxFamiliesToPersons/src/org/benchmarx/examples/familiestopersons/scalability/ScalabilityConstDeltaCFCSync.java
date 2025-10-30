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

	private void createFamiliesAndConflictFreeChanges(int nrOfFamilyPairs, int nrOfEditedFamilyPairs) {
		var timer = new BXToolTimer<>(tool, REPEAT);

		assertLastTestSuccessfull();
		
		assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT * REPEAT), () -> {
			results.put(nrOfFamilyPairs, //
					timer.timeEditAfterSetUpInS(
							srcEdit(() -> helperFamily.createSimpsonFamiliesWithMembers(nrOfFamilyPairs)),
							srcEdit(() -> helperFamily.createSonHugo(nrOfEditedFamilyPairs)),
							trgEdit(() -> helperPerson.deleteLisa(nrOfEditedFamilyPairs))));
		});
		
		setTestSuccessfull();
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
	public void testCreate0000075FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(75, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000100FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(100, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000150FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(150, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000200FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(200, NR_OF_EDITED_FAMILIES);
	}
	

	@Test
	public void testCreate0000250FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(250, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000300FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(300, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000350FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(350, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000400FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(400, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000450FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(450, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(500, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000600FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(600, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000700FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(700, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000800FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(800, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0000900FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(900, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0001000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0001250FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1250, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0001500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1500, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0001750FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(1750, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0002000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(2000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0002500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(2500, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0003000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(3000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0003500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(3500, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0004000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(4000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0004500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(4500, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0005000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(5000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0006000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(6000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0007000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(7000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0008000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(8000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0009000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(9000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0010000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(10000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0012500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(12500, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0015000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(15000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0017500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(17500, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0020000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(20000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0022500FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(22500, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0025000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(25000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0030000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(30000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0035000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(35000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0040000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(40000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0045000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(45000, NR_OF_EDITED_FAMILIES);
	}
	
	@Test
	public void testCreate0050000FamiliesAndConflictFreeChanges() {
		createFamiliesAndConflictFreeChanges(50000, NR_OF_EDITED_FAMILIES);
	}
}
