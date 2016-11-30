package org.benchmarx.examples.familiestopersons.testsuite.level1.incr.wocorrs.delta.config;

import org.benchmarx.BXTool;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.examples.familiestopersons.testsuite.FamiliesToPersonsTestCase;
import org.junit.Test;

import Families.FamilyRegister;
import Persons.PersonRegister;

public class RenamingPersonsWithDecisions extends FamiliesToPersonsTestCase {
	
	public RenamingPersonsWithDecisions(BXTool<FamilyRegister, PersonRegister, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for changing a person's family name.
	 * <p>
	 * <b>Expect</b> the corresponding family member must be associated with
	 * another family as their family name does not fit anymore. In this case, a
	 * fitting family already exists and must be used as this is preferred.
	 * <p>
	 * <b>Classification</b>: incr-wocorr-delta-config
	 * <ul>
	 * <li><b>incr</b>: old family register is required to avoid information
	 * loss (mapping of (fe)males to mothers/fathers or daughters/sons).
	 * <li><b>wocorr</b>: assumption based on unique naming works here as there
	 * are no persons with the same name.
	 * <li><b>delta</b>: renaming cannot be distinguished from combined creation
	 * and deletion.
	 * <li><b>config</b>: there are actually two decisions to be made here: (i)
	 * whether the member is to be created as a child or parent in their new
	 * family, and (ii) if a new family is to be created or an existing suitable
	 * family is to be used.
	 * </ul>
	 */
	@Test
	public void testFamilyNameChangeOfPersonWhereSuitableFamilyExists() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createBachchanFamily)
			       							   .andThen(helperFamily::createFatherAmitabh));
		tool.performAndPropagateSourceEdit(helperFamily::createOtherRemainingMembersInFamilyBachchan);
		
		tool.performAndPropagateSourceEdit(helperFamily::createNandaFamily);
		
		util.assertPrecondition("Pre_MemberFamilyNameChangeToExist", "Pre_PersonFamilyNameChangeToExist");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
						.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		tool.performAndPropagateTargetEdit(helperPerson::familyNameChangeOfShweta);
		//----------------
		util.assertPostcondition("MemberFamilyNameChangeToExist","PersonFamilyNameChangeToExist");
	}
	
	/**
	 * Analogous to {@link #testFamilyNameChangeOfPersonWhereSuitableFamilyExists()},
	 * but here a new family is to be created even though a suitable family
	 * actually exists and could be used.
	 */
	@Test
	public void testFamilyNameChangeOfPersonButPreferCreatingNewFamily() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createBachchanFamily)
			       							   .andThen(helperFamily::createFatherAmitabh));
		tool.performAndPropagateSourceEdit(helperFamily::createOtherRemainingMembersInFamilyBachchan);
		
		tool.performAndPropagateSourceEdit(helperFamily::createNandaFamily);
		
		util.assertPrecondition("Pre_MemberFamilyNameChangeToExist", "Pre_PersonFamilyNameChangeToExist");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
						.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, false);
		tool.performAndPropagateTargetEdit(helperPerson::familyNameChangeOfShweta);
		//----------------
		util.assertPostcondition("MemberFamilyNameChangeToExistNew","PersonFamilyNameChangeToExistNew");
	}

	/**
	 * Analogous to
	 * {@link #testFamilyNameChangeOfPersonWhereSuitableFamilyExists()} but here
	 * a suitable family does not exist. The only choice to be made, therefore,
	 * is if the female person should be a daughter or mother (preferred here)
	 * of the new family.
	 */
	@Test
	public void testFamilyNameChangeOfPersonWhereSuitableFamilyDoesNotExist() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(util.execute(helperFamily::createSimpsonFamily)
											   .andThen(helperFamily::createFatherHomer));
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
			
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::familyNameChangeOfLisa);
		//----------------
		util.assertPostcondition("MemberFamilyNameChange","PersonFamilyNameChange");
	}
	
	/**
	 * <b>Test</b> for changing a person's full name.
	 * <p>
	 * <b>Expected</b>: first name of the corresponding family member and their
	 * family name must be changed in the families model
	 * <p>
	 * <b>Classification</b>: incr-wocorr-delta-config
	 * <ul>
	 * <li><b>incr</b>: renaming persons full name requires old consistent state
	 * as it replace old member name and family name with new one in families
	 * model.
	 * <li><b>wocorr</b>: it's possible to guess, as only one person is
	 * available with this name and the related member's name has to be renamed
	 * in the families model which is clear.
	 * <li><b>delta</b>: renaming is mostly delta bases as it is impossible to
	 * decide weather it is renamed, deleted or recreated.
	 * <li><b>config</b>: as there is no suitable family, a new one must be
	 * created (so no decision for this), but one has to decide if a parent or
	 * child should be preferred (here parent).
	 * </ul>
	 */
	@Test
	public void testFullNameChangeFather() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::fullNameChangeOfHomer);
		//----------------
		util.assertPostcondition("MemberFullNameChange","PersonFullNameChange");
	}
	
	/**
	 * Analogous to @link {@link #testFullNameChangeFather()}, but here the
	 * corresponding family member is a son, not a father in the family.
	 */
	@Test
	public void testFullNameChangeSon() {
		tool.initiateSynchronisationDialogue();
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamily);
		tool.performAndPropagateSourceEdit(helperFamily::createFatherHomer);
		tool.performAndPropagateSourceEdit(helperFamily::createSimpsonFamilyMembers);
		
		util.assertPrecondition("Pre_NameChangeFamily", "Pre_NameChangePerson");
		//----------------
		util.configure().makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true);
		tool.performAndPropagateTargetEdit(helperPerson::fullNameChangeOfBart);
		//----------------
		util.assertPostcondition("MemberFullNameChangeSecond","PersonFullNameChangeSecond");
	}
}
