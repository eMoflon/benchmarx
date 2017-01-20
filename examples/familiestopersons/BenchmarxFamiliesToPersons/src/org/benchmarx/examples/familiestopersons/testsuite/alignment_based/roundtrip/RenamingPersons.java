package org.benchmarx.examples.familiestopersons.testsuite.alignment_based.roundtrip;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class RenamingPersons extends FamiliesToPersonsTestCase {
	
	public RenamingPersons(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for changing a person's first name.
	 * <p>
	 * <b>Expected</b>: only the first name of the corresponding family member
	 * should be changed.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-delta-auto
	 * <ul>
	 * <li><b>incr</b>: changing a persons first name requires old consistent
	 * family model as the information if females are daughters or mothers (and
	 * males analogously) would otherwise be lost.
	 * <li><b>wocorr</b>: assumption of unique names can be used here to compute
	 * correspondences correctly.
	 * <li><b>delta</b>: renaming cannot be distinguished from combined deletion
	 * and creation.
	 * <li><b>auto</b>: propagation is deterministic.
	 * </ul>
	 */
	@Test
	public void testFirstNameChangePerson() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherHomer)
				.andThen(helperFamily::createSimpsonFamilyMembers));
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");	
		//----------------
		tool.performAndPropagateTargetEdit(helperPerson::firstNameChangeOfHomer);
		//----------------
		util.assertPostcondition("MemberNameChange","PersonNameChange");
	}
	
	/**
	 * <b>Test</b> for changing a person's full name (where another person with
	 * the same name exists). <br/>
	 * <b>Expected</b>: first name of the corresponding member and their family
	 * name must be changed. As no fitting family exists, a new family must be
	 * created and the member moved to this new family (as the father of this
	 * family). <br/>
	 * <b>Features</b>: round trip, add+attribute, corr-based, structural,
	 * runtime <br/>
	 */
	@Test
	public void testFullNameChangeOfNonUniquePerson() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		tool.performAndPropagateTargetEdit(helperPerson::createOtherBart);
			
		util.assertPrecondition("Pre_MemberNameChangeOther", "Pre_PersonNameChangeOther");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::fullNameChangeOfOtherBart);
		//----------------
		util.assertPostcondition("MemberFullNameChangeOther","PersonFullNameChangeOther");
	}
	
	/**
	 * <b>Test</b> for changing a person's first name (where another person with
	 * the same name exists).
	 * <br/>
	 * <b>Expected</b>: the first name of the corresponding member in the
	 * families model should be changed.
	 * <p>
	 * <b>Classification</b>: incr-wcorr-delta-auto
	 * <ul>
	 * <li><b>incr</b>: the old family register is required as it is impossible
	 * to guess if female/male persons are mothers/fathers or daughters/sons in
	 * the family register.
	 * <li><b>wcorr</b>: it's impossible to guess which family member has to be
	 * renamed in the families model as two persons (one of which is to be
	 * renamed) have the same name.
	 * <li><b>delta</b>: renaming is inherently delta-based as it cannot be
	 * distinguished from combined deletion and creation.
	 * <li><b>auto</b>: propagation is deterministic so no choice required.
	 * </ul>
	 */
	@Test
	public void testFirstNameChangeOfNonUniquePerson() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util
				.execute(helperFamily::createSimpsonFamily)
				.andThen(helperFamily::createFatherHomer)
				.andThen(helperFamily::createSimpsonFamilyMembers));
		tool.performAndPropagateTargetEdit(helperPerson::createOtherBart);
			
		util.assertPrecondition("Pre_MemberNameChangeOther", "Pre_PersonNameChangeOther");
		//----------------
		tool.performAndPropagateTargetEdit(helperPerson::firstNameChangeOfBart);
		//----------------
		util.assertPostcondition("MemberNameChangeOther","PersonNameChangeOther");
	}
}
