package org.benchmarx.examples.pdb12pdb2.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.pdb12pdb2.testsuite.Decisions;
import org.benchmarx.examples.pdb12pdb2.testsuite.Pdb12Pdb2TestCase;
import org.junit.Test;

public class IncrementalForward extends Pdb12Pdb2TestCase {
	public IncrementalForward(BXTool<pdb1.Database, pdb2.Database, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting persons into an existing person database. <br/>
	 * <b>Expect</b> : New persons are added to the register, while the old persons
	 * remain unchanged. <br/>
	 * <b>Features</b>: fwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperPerson1::setDatabaseName)
				.andThen(helperPerson1::createKonradAdenauer)
				.andThen(helperPerson1::createLudwigErhard)
				.andThen(helperPerson1::createKurtKiesinger)
				.andThen(helperPerson1::createWillyBrandt)
				.andThen(helperPerson1::createHelmutSchmidt)
				.andThen(helperPerson1::createHelmutKohl));
		tool.performIdleTargetEdit(helperPerson2::changeIncrementalIDs);
		
		util.assertPrecondition("Pre_IncrFwdPDB1FirstSixChancellors", "Pre_IncrFwdPDB2FirstSixChancellors");

		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperPerson1::createGerhardSchroeder)
				.andThen(helperPerson1::createAngelaMerkel));
		//------------
		util.assertPostcondition("IncrFwdPDB1AllChancellors", "IncrFwdPDB2AllChancellors");
	}
	
	/**
	 * <b>Test</b> for deleting persons. After creating the person register,
	 * set set 6 chancelors with all variables. Then delete Kurt Kiesinger from the pdb1 database.
	 * <b>Expect</b>: Delete the correct Person in the pdb2 database
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperPerson1::setDatabaseName)
				.andThen(helperPerson1::createKonradAdenauer)
				.andThen(helperPerson1::createLudwigErhard)
				.andThen(helperPerson1::createKurtKiesinger)
				.andThen(helperPerson1::createWillyBrandt)
				.andThen(helperPerson1::createHelmutSchmidt)
				.andThen(helperPerson1::createHelmutKohl));
		tool.performIdleTargetEdit(helperPerson2::changeIncrementalIDs);
		
		util.assertPrecondition("Pre_IncrFwdPDB1FirstSixChancellors", "Pre_IncrFwdPDB2FirstSixChancellors");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperPerson1::deleteKurtKiesinger));
		//------------
		util.assertPostcondition("IncrFwdPDB1FirstSixChancellorsWithoutKiesinger", "IncrFwdPDB2FirstSixChancellorsWithoutKiesinger");
	}
	
	/**
	 * <b>Test</b> for changing all variable-values in different persons. After creating the pdb2 database. 
	 * Then change values of each variable in another person and all variables of one person.
	 * <b>Expect</b>: Change the values of the affected variables in Persons of the pdb2 database.
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalValueChange() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperPerson1::setDatabaseName)
				.andThen(helperPerson1::createKonradAdenauer)
				.andThen(helperPerson1::createLudwigErhard)
				.andThen(helperPerson1::createKurtKiesinger)
				.andThen(helperPerson1::createWillyBrandt)
				.andThen(helperPerson1::createHelmutSchmidt)
				.andThen(helperPerson1::createHelmutKohl));
		tool.performIdleTargetEdit(helperPerson2::changeIncrementalIDs);
		
		util.assertPrecondition("Pre_IncrFwdPDB1FirstSixChancellors", "Pre_IncrFwdPDB2FirstSixChancellors");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperPerson1::changeAllOfHelmutKohl)
				.andThen(helperPerson1::changeBirthdayOfKurtKiesinger)
				.andThen(helperPerson1::changeFirstNameOfKonradAdenauer)
				.andThen(helperPerson1::changeIDOfHelmutSchmidt)
				.andThen(helperPerson1::changeLastNameOfLudwigErhard)
				.andThen(helperPerson1::changePlaceOfBirthOfWillyBrandt));
		//------------
		util.assertPostcondition("IncrFwdPDB1FirstSixChancellorsAfterValueChange", "IncrFwdPDB2FirstSixChancellorsAfterValueChange");
	}

	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta does not change the target model.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testStability() {		
		tool.performAndPropagateSourceEdit(util
				.execute(helperPerson1::setDatabaseName)
				.andThen(helperPerson1::createKonradAdenauer)
				.andThen(helperPerson1::createLudwigErhard)
				.andThen(helperPerson1::createKurtKiesinger)
				.andThen(helperPerson1::createWillyBrandt)
				.andThen(helperPerson1::createHelmutSchmidt)
				.andThen(helperPerson1::createHelmutKohl)
				.andThen(helperPerson1::createGerhardSchroeder)
				.andThen(helperPerson1::createAngelaMerkel));
		tool.performIdleTargetEdit(helperPerson2::changeIncrementalIDs);

		util.assertPrecondition("IncrFwdPDB1AllChancellors", "IncrFwdPDB2AllChancellorsIDs");
		//------------
		tool.performAndPropagateSourceEdit(helperPerson1::idleDelta);
		//------------
		util.assertPostcondition("IncrFwdPDB1AllChancellors", "IncrFwdPDB2AllChancellorsIDs");
	}
	
	/**
	 * <b>Test</b> for hippocraticness of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after getting the first part of the last name of a person to the firstname does not change the pdb2 Database<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testHippocraticness() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperPerson1::setDatabaseName)
				.andThen(helperPerson1::createWrongKonradAdenauer)
				.andThen(helperPerson1::createLudwigErhard)
				.andThen(helperPerson1::createKurtKiesinger)
				.andThen(helperPerson1::createWillyBrandt)
				.andThen(helperPerson1::createHelmutSchmidt)
				.andThen(helperPerson1::createHelmutKohl)
				.andThen(helperPerson1::createGerhardSchroeder)
				.andThen(helperPerson1::createAngelaMerkel));
		tool.performIdleTargetEdit(helperPerson2::changeIncrementalIDs);

		util.assertPrecondition("IncrFwdPDB1AllChancellorsWrongAdenauer", "IncrFwdPDB2AllChancellorsIDs");
		//------------
		tool.performAndPropagateSourceEdit(helperPerson1::hippocraticDelta);
		//------------
		util.assertPostcondition("IncrFwdPDB1AllChancellors", "IncrFwdPDB2AllChancellorsIDs");
	}
}
