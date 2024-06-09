package org.benchmarx.examples.familiestopersons.testsuite.concurrent;

import java.util.Map;
import static java.util.Map.entry;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class Conflicts extends FamiliesToPersonsTestCase {

	public Conflicts(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for resolution of a move/delete conflict. <br/>
	 * <b>Expect</b> : Perform deletion but do not propagate.  Perform move and propagate. <br/>
	 * <b>Features</b>: conflict resolution
	 */
	@Test
	public void testMoveDeleteConflict() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createNewFamilyFlandersWithMembers//
		));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfLisa));
		util.assertPrecondition("Pre_ConflictFamily", "Pre_ConflictPersons");
		// ------------
		tool.performAndPropagateEdit(//
				srcEdit(helperFamily::moveLisaToFlandersAsDaughter), //
				trgEdit(helperPerson::deleteLisa));
		// ------------

		util.assertAnyPostcondition(Map.ofEntries(//
				// Move Lisa to Flanders family and forward propagate, remove Lisa, Simpson
				entry("Post_MoveDeleteConflictFamily_1", "Post_MoveDeleteConflictPersons_1"),
				// Propagate delete, reject move
				entry("Post_MoveDeleteConflictFamily_2", "Post_MoveDeleteConflictPersons_2"),
				// Propagate move, reject delete
				entry("Post_MoveDeleteConflictFamily_3", "Post_MoveDeleteConflictPersons_3")));
	}
	
	/**
	 * <b>Test</b> for resolution of a move/rename conflict. <br/>
	 * <b>Expect</b> : Reject move, propagate renaming <br/>
	 * <b>Features</b>: conflict resolution
	 */
	@Test
	public void testMoveRenameConflict() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createNewFamilyFlandersWithMembers//
				));
		util.assertPrecondition("Pre_ConflictFamily", "Pre_ConflictPersons");
		// ------------
		util.configure()//
		.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
		.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateEdit(
				srcEdit(helperFamily::moveLisaToFlandersAsDaughter),
				trgEdit(helperPerson::familyNameChangeOfLisaToVanHouten));
		// ------------
		util.assertPostcondition("Post_Conflict2Family", "Post_Conflict2Persons");
	}
	
	/**
	 * <b>Test</b> for resolution of a delete/rename conflict. <br/>
	 * <b>Expect</b> : Reject deletion, propagate renaming <br/>
	 * <b>Features</b>: conflict resolution
	 */
	@Test
	public void testDeleteRenameConflict() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createNewFamilyFlandersWithMembers//
				));
		util.assertPrecondition("Pre_ConflictFamily", "Pre_ConflictPersons");
		// ------------
		util.configure()//
		.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
		.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateEdit(
				srcEdit(helperFamily::deleteLisa),
				trgEdit(helperPerson::nameChangeOfLisa));
		// ------------
		util.assertPostcondition("Post_Conflict3Family", "Post_Conflict3Persons");
	}
	
	/**
	 * <b>Test</b> for resolution of a delete/rename conflict. <br/>
	 * <b>Expect</b> : Reject deletion, propagate renaming <br/>
	 * <b>Features</b>: conflict resolution
	 */
	@Test
	public void testRenameRenameConflict() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createNewFamilyFlandersWithMembers//
				));
		util.assertPrecondition("Pre_ConflictFamily", "Pre_ConflictPersons");
		// ------------
		tool.performAndPropagateEdit(
				srcEdit(helperFamily::nameChangeOfLisa),
				trgEdit(helperPerson::nameChangeOfLisa));
		// ------------
		util.assertPostcondition("Post_Conflict4Family", "Post_Conflict4Persons");
	}
}
