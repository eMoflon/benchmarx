package org.benchmarx.examples.familiestopersons.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class BatchForward extends FamiliesToPersonsTestCase {

	public BatchForward(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for agreed upon starting state.<br/>
	 * <b>Expect</b> root elements of both source and target models.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testInitialiseSynchronisation() {
		tool.noPrecondition();
		// ------------
		util.assertPostcondition("RootElementFamilies", "RootElementPersons");
	}

	/**
	 * <b>Test</b> for name change of an empty family, i.e, a family without any
	 * family members.<br/>
	 * <b>Expect</b> no change in the persons model.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testFamilyNameChangeOfEmpty() {
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSimpsonFamily));

		util.assertPrecondition("Pre_NameChangeFamilyEmpty", "Pre_NameChangePersonEmpty");
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::renameEmptySimpsonToBouvier));
		// ------------
		util.assertPostcondition("NameChangeFamilyEmpty", "NameChangePersonEmpty");
	}

	/**
	 * <b>Test</b> for creation of a single family (Skinner) in an empty root
	 * container. <br/>
	 * <b>Expect</b> nothing to be changed in the person model. <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateFamily() {
		tool.noPrecondition();
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSkinnerFamily));
		// ------------
		util.assertPostcondition("OneFamily", "PersonsForOneFamily");
	}

	/**
	 * <b>Test</b> for creation of a single family member (a son - Rod) in a new
	 * family (Flanders). <br/>
	 * <b>Expect</b> the creation of a new male person in the persons model, with
	 * full name consisting of the first name and family name of the associated
	 * family member. <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateFamilyMember() {
		tool.noPrecondition();
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createFlandersFamily, helperFamily::createSonRod));
		// ------------
		util.assertPostcondition("OneFamilyWithOneFamilyMemberSon", "PersonOneMaleMember");
	}

	/**
	 * Analogous to @link {@link #testCreateFamilyMember()}, but now for multiple
	 * new family members (Simpsons Family).<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testNewFamilyWithMultiMembers() {
		tool.noPrecondition();
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createFlandersFamily, //
				helperFamily::createSonRod, //
				helperFamily::createNewFamilySimpsonWithMembers));
		// ------------
		util.assertPostcondition("NewFamilyWithMembers", "PersonsMulti");
	}

	/**
	 * <b>Test</b> for creation of another Family with the same name (Simpson).<br/>
	 * In the new Simpson family, Bart is the father. <b>Expect</b> a new male
	 * Person with the name "Simpson, Bart" is created in the person register.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testNewDuplicateFamilyNames() {
		tool.noPrecondition();
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createSimpsonFamily, //
				helperFamily::createFatherBart));
		// ------------
		util.assertPostcondition("FamiliesWithSameName", "PersonWithSameName");
	}

	/**
	 * <b>Test</b> for creation of another family member with the same name
	 * (Bart).<br/>
	 * <b>Expect</b> a new male Person with the name "Simpson, Bart" is created in
	 * the person register.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testDuplicateFamilyMemberNames() {
		tool.noPrecondition();
		// ------------
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createNewFamilySimpsonWithMembers, //
				helperFamily::createSonBart));
		// ------------
		util.assertPostcondition("FamilyWithDuplicateMember", "PersonWithSameName");
	}
}
