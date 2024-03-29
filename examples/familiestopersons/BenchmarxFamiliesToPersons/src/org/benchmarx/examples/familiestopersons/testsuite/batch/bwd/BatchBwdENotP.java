package org.benchmarx.examples.familiestopersons.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class BatchBwdENotP extends FamiliesToPersonsTestCase {

	public BatchBwdENotP(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for creation of a single male person (Flanders, Rod).<br/>
	 * <b>Expect</b> the creation of a family member in the families model with the
	 * given first name, in a suitable family. Creation of children is
	 * preferred.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testCreateMalePersonAsSon() {
		tool.noPrecondition();
		// ---------------------------------
		util.configure().makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
				.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(trgEdit(helperPerson::createRod));
		// ---------------------------------
		util.assertPostcondition("OneFamilyWithOneFamilyMemberSon", "PersonOneMaleMember");
	}

	/**
	 * <b>Test</b> for creation of family members in existing families.<br/>
	 * <b>Expect</b> the creation of a family member in the families model with the
	 * given first name, in a suitable family. Creation of Children is
	 * preferred.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testCreateFamilyMembersInExistingFamilyAsChildren() {
		tool.noPrecondition();
		// ---------------------------------
		util.configure().makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
				.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(trgEdit(//
				helperPerson::createRod, //
				helperPerson::createHomer, //
				helperPerson::createBart, //
				helperPerson::createMarge, //
				helperPerson::createLisa, //
				helperPerson::createMaggie));
		// ---------------------------------
		util.assertPostcondition("FamiliesWithChildrenOnly", "PersonsMulti");
	}

	/**
	 * <b>Test</b> for creation of family members in existing families.<br/>
	 * <b>Expect</b> As creation of children is preferred, two families should be
	 * created with no parents. All Barts should be in the same family as sons with
	 * the same first name.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testCreateDuplicateFamilyMembersInExistingFamilyAsChildren() {
		tool.noPrecondition();
		// ---------------------------------
		util.configure().makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
				.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateTargetEdit(trgEdit(//
				helperPerson::createRod, //
				helperPerson::createBart, //
				helperPerson::createHomer, //
				helperPerson::createBart, //
				helperPerson::createBart, //
				helperPerson::createMarge, //
				helperPerson::createLisa, //
				helperPerson::createMaggie));
		// ---------------------------------
		util.assertPostcondition("FamilyWithDuplicateChildrenOnly", "PersonsDuplicateMulti");
	}
}
