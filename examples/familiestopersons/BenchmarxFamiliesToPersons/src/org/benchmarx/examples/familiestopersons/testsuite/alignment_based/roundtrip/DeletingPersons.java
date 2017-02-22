package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.roundtrip;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class DeletingPersons extends FamiliesToPersonsTestCase {
	
	public DeletingPersons(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for deleting a person. <br/>
	 * <b>Expect</b> the deletion of the corresponding family member in the
	 * families model. <br/>
	 * <b>Features</b>: roundtrip, add+del, fixed
	 */
	@Test
	public void testDeletePerson() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createSimpsonFamily)
				   							   .andThen(helperFamily::createFatherHomer));
		//tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//---------------------- 
		tool.performAndPropagateTargetEdit(helperPerson::deleteMarge);
		//---------------------- 
		util.assertPostcondition("MemberDelete", "PersonDelete");
	}
	
	/**
	 * Analogous to @link {@link #testDeletePerson()}, but the first person
	 * created in the family is deleted here.<br/>
	 * <b>Features</b>: roundtrip, add+del, fixed
	 */
	@Test
	public void testDeleteFirstPerson() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createSimpsonFamily)
											   .andThen(helperFamily::createFatherHomer));
		//tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//---------------------- 
		tool.performAndPropagateTargetEdit(helperPerson::deleteHomer);
		//---------------------- 
		util.assertPostcondition("MemberFirstDelete", "PersonFirstDelete");
	}
	
}
