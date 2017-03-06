package org.benchmarx.examples.familiestopersons.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

/**
 * This class covers the Test Case (TC 1) as specified on github
 *
 */
public class CreatingNewFamiliesAndMembers extends FamiliesToPersonsTestCase {

	public CreatingNewFamiliesAndMembers(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for creation of a single family (Skinner) in an empty root container.
	 * <br/>
	 * <b>Expect</b> nothing to be changed in the person model.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateFamily()
	{
		tool.initiateSynchronisationDialogue();
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperFamily::createSkinnerFamily);
		//------------
		util.assertPostcondition("OneFamily", "PersonsForOneFamily");
	}

	/**
	 * <b>Test</b> for creation of a single family member (a son - Rod) in a new family (Flanders). <br/>
	 * <b>Expect</b> the creation of a new male person in the persons model,
	 * with full name consisting of the first name and family name of the
	 * associated family member. <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateFamilyMember()
	{
		tool.initiateSynchronisationDialogue();
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod));
		//------------
		util.assertPostcondition("OneFamilyWithOneFamilyMemberSon", "PersonOneMaleMember");
	}
	
	/**
	 * Analogous to @link {@link #testCreateFamilyMember()}, but now for
	 * multiple new family members (Simpsons Family).<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test 
	public void testNewFamilyWithMultiMembers(){
		tool.initiateSynchronisationDialogue();
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createFlandersFamily)
				.andThen(helperFamily::createSonRod)
				.andThen(helperFamily::createNewFamilySimpsonWithMembers));
		//------------
		util.assertPostcondition("NewFamilyWithMembers", "PersonsMulti");
	}
	
	/**
	 * <b>Test</b> for creation of another Family with the same name (Simpson).<br/>
	 * In the new Simpson family, Bart is the father. 
	 * <b>Expect</b> a new male Person with the name "Simpson, Bart" is created in the person register.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testNewDuplicateFamilyNames() {
		tool.initiateSynchronisationDialogue();
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createNewFamilySimpsonWithMembers)
				.andThen(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherBart));
		//------------
		util.assertPostcondition("FamiliesWithSameName", "PersonWithSameName");
	}
	
	/**
	 * <b>Test</b> for creation of another family member with the same name (Bart).<br/>
	 * <b>Expect</b> a new male Person with the name "Simpson, Bart" is created in the person register.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testDuplicateFamilyMemberNames() {
		tool.initiateSynchronisationDialogue();
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createNewFamilySimpsonWithMembers)
				.andThen(helperFamily::createSonBart));
		//------------
		util.assertPostcondition("FamilyWithDuplicateMember", "PersonWithSameName");
	}
	
	@Test
	public void testStability() {
		tool.initiateSynchronisationDialogue();
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createNewFamilySimpsonWithMembers)
				.andThen(helperFamily::createSonBart));
		//------------
		util.assertPostcondition("FamilyWithDuplicateMember", "PersonWithSameName");
		
		tool.performAndPropagateSourceEdit(helperFamily::idleDelta);
		util.assertPostcondition("FamilyWithDuplicateMember", "PersonWithSameName");
	}
}
