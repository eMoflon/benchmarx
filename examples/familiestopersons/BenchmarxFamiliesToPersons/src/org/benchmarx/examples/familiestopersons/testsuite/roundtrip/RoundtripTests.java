package org.benchmarx.examples.familiestopersons.testsuite.roundtrip;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class RoundtripTests extends FamiliesToPersonsTestCase {

	public RoundtripTests(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	@Test
	public void testRoundtripEdit() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createSkinnerFamily, //
				helperFamily::createFlandersFamily, //
				helperFamily::createSonRod, //
				helperFamily::createSimpsonFamily, //
				helperFamily::createFatherBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfRod));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfFatherBart));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createNewFamilySimpsonWithMembers));
		tool.performIdleTargetEdit(trgEdit(helperPerson::changeAllBirthdays));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSonBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfYoungerBart));

		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		
		//--------------------------
		tool.performAndPropagateTargetEdit(trgEdit(helperPerson::firstNameChangeOfHomer));
		//--------------------------
		
		util.assertPostcondition("FamilyAfterRoundtripEdit", "PersonAfterRoundtripEdit");
	}
	
	@Test
	public void testRoundtripAdd() {
		util.configure().makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true)
		.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, false);
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createSkinnerFamily, //
				helperFamily::createFlandersFamily, //
				helperFamily::createSonRod, //
				helperFamily::createSimpsonFamily, //
				helperFamily::createFatherBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfRod));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfFatherBart));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createNewFamilySimpsonWithMembers));
		tool.performIdleTargetEdit(trgEdit(helperPerson::changeAllBirthdays));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSonBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfYoungerBart));

		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		
		//--------------------------
		tool.performAndPropagateTargetEdit(trgEdit(helperPerson::createSeymour));
		//--------------------------		
		
		util.assertPostcondition("FamilyAfterRoundtripAdd", "PersonAfterRoundtripAdd");
	}
	
	@Test
	public void testRoundtripDelete() {
		tool.performAndPropagateSourceEdit(srcEdit(//
				helperFamily::createSkinnerFamily, //
				helperFamily::createFlandersFamily, //
				helperFamily::createSonRod, //
				helperFamily::createSimpsonFamily, //
				helperFamily::createFatherBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfRod));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfFatherBart));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createNewFamilySimpsonWithMembers));
		tool.performIdleTargetEdit(trgEdit(helperPerson::changeAllBirthdays));
		tool.performAndPropagateSourceEdit(srcEdit(helperFamily::createSonBart));
		tool.performIdleTargetEdit(trgEdit(helperPerson::setBirthdayOfYoungerBart));

		util.assertPrecondition("Pre_IncrFwdFamily", "Pre_IncrFwdPerson");
		
		//--------------------------
		tool.performAndPropagateTargetEdit(trgEdit(helperPerson::deleteMarge));
		//--------------------------		
		
		util.assertPostcondition("FamilyAfterRoundtripDelete", "PersonAfterRoundtripDelete");		
	}

}
