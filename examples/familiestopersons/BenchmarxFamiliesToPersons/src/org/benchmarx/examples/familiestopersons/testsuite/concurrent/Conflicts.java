package org.benchmarx.examples.familiestopersons.testsuite.concurrent;

import static java.util.Map.entry;

import java.util.Map;

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
	 * <b>Expect</b> : One of provided postconditions <br/>
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
	 * <b>Expect</b> : One of provided postconditions <br/>
	 * <b>Features</b>: conflict resolution
	 */
	@Test
	public void testMoveRenameConflict() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createNewFamilyFlandersWithMembers//
		));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfLisa));
		util.assertPrecondition("Pre_ConflictFamily", "Pre_ConflictPersons");
		// ------------
		util.configure()//
				.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false)
				.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateEdit(srcEdit(helperFamily::moveLisaToFlandersAsDaughter),
				trgEdit(helperPerson::familyNameChangeOfLisaToVanHouten));
		// ------------

		util.assertAnyPostcondition(Map.ofEntries(//
				// Reject move, propagate family name change to source
				entry("Post_MoveRenameConflictFamily_1", "Post_MoveRenameConflictPersons_1"),
				// Propagate move, reject family name change
				entry("Post_MoveRenameConflictFamily_2", "Post_MoveRenameConflictPersons_2")));
	}

	/**
	 * <b>Test</b> for resolution of a delete/rename conflict. <br/>
	 * <b>Expect</b> : One of provided postconditions <br/>
	 * <b>Features</b>: conflict resolution
	 */
	@Test
	public void testDeleteRenameConflict() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createNewFamilyFlandersWithMembers//
		));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfLisa));
		util.assertPrecondition("Pre_ConflictFamily", "Pre_ConflictPersons");
		// ------------
		util.configure()//
				.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
				.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateEdit(//
				srcEdit(helperFamily::deleteLisa), //
				trgEdit(helperPerson::nameChangeOfLisa));
		// ------------
		util.assertAnyPostcondition(Map.ofEntries(//
				// Reject deletion, propagate renaming
				entry("Post_DeleteRenameConflictFamily_1", "Post_DeleteRenameConflictPersons_1"),
				// Propagate deletion, reject renaming
				entry("Post_DeleteRenameConflictFamily_2", "Post_DeleteRenameConflictPersons_2")));
	}

	/**
	 * <b>Test</b> for resolution of a delete/rename conflict. <br/>
	 * <b>Expect</b> : One of provided postconditions <br/>
	 * <b>Features</b>: conflict resolution
	 */
	@Test
	public void testRenameRenameConflict() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createNewFamilyFlandersWithMembers//
		));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfLisa));
		util.assertPrecondition("Pre_ConflictFamily", "Pre_ConflictPersons");
		// ------------
		tool.performAndPropagateEdit(//
				srcEdit(helperFamily::nameChangeOfLisa), //
				trgEdit(helperPerson::nameChangeOfLisa));
		// ------------
		util.assertAnyPostcondition(Map.ofEntries(//
				// Source renaming wins
				entry("Post_RenameRenameConflictFamily_1", "Post_RenameRenameConflictPersons_1"),
				// Target renaming wins
				entry("Post_RenameRenameConflictFamily_2", "Post_RenameRenameConflictPersons_2")));
	}
}
