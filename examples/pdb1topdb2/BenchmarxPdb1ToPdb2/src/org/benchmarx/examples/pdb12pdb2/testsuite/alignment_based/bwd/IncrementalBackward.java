package org.benchmarx.examples.pdb12pdb2.testsuite.alignment_based.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.pdb12pdb2.testsuite.Decisions;
import org.benchmarx.examples.pdb12pdb2.testsuite.Pdb12Pdb2TestCase;
import org.junit.Test;

public class IncrementalBackward extends Pdb12Pdb2TestCase {
	public IncrementalBackward(BXTool<pdb1.Database, pdb2.Database, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting persons into an existing person database. In this case the config is fixed. All persons lastName will be splitted of by the last space.<br/>
	 * <b>Expect</b> : New persons are added to the register, while the old persons
	 * remain unchanged. <br/>
	 * <b>Features</b>: bwd, add, fixed
	 */
	@Test
	public void testIncrementalInsertsFixedConfigLastSpace() {
		tool.setConfigurator(util.configure().makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST , false));
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::setDatabaseName)
				.andThen(helperPerson2::createKonradAdenauer)
				.andThen(helperPerson2::createLudwigErhard)
				.andThen(helperPerson2::createKurtKiesinger)
				.andThen(helperPerson2::createWillyBrandt)
				.andThen(helperPerson2::createHelmutSchmidt)
				.andThen(helperPerson2::createHelmutKohl));
		tool.performIdleSourceEdit(helperPerson1::changeIncrementalIDs);
		
		util.assertPrecondition("Pre_IncrBwdPDB1FirstSixChancellors", "Pre_IncrBwdPDB2FirstSixChancellors");

		//------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::createGerhardSchroeder)
				.andThen(helperPerson2::createAngelaMerkel));
		//------------
		util.assertPostcondition("IncrBwdPDB1AllChancellors", "IncrBwdPDB2AllChancellors");
	}
	
	/**
	 * <b>Test</b> for inserting persons into an existing person database. In this case the config is fixed. All persons lastName will be splitted of by the first space.<br/>
	 * <b>Expect</b> : New persons are added to the register, while the old persons
	 * remain unchanged. <br/>
	 * <b>Features</b>: bwd, add, fixed
	 */
	@Test
	public void testIncrementalInsertsFixedConfigFirstSpace() {
		tool.setConfigurator(util.configure().makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST , true));
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::setDatabaseName)
				.andThen(helperPerson2::createKonradAdenauer)
				.andThen(helperPerson2::createLudwigErhard)
				.andThen(helperPerson2::createKurtKiesinger)
				.andThen(helperPerson2::createWillyBrandt)
				.andThen(helperPerson2::createHelmutSchmidt)
				.andThen(helperPerson2::createHelmutKohl));
		tool.performIdleSourceEdit(helperPerson1::changeIncrementalIDs);
		
		util.assertPrecondition("Pre_IncrBwdPDB1FirstSixChancellorsFirstSpace", "Pre_IncrBwdPDB2FirstSixChancellors");

		//------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::createGerhardSchroeder)
				.andThen(helperPerson2::createAngelaMerkel));
		//------------
		util.assertPostcondition("IncrBwdPDB1AllChancellorsFirstSpace", "IncrBwdPDB2AllChancellors");
	}
	
	/**
	 * <b>Test</b> for inserting of a Person in a pdb2 database after the initial
	 * register has been transformed into a pdb1 database.<br/>
	 * <b>Expect</b> : pdb1 and pdb2 models are structured as specified in the corresponding
	 * assertPostcondition statements.<br/>
	 * <b>Features</b>: bwd, add, runtime
	 */
	@Test
	public void testIncrementalInsertsDynamicConfig() {
		tool.setConfigurator(util.configure().makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST , false));
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::setDatabaseName)
				.andThen(helperPerson2::createKonradAdenauer)
				.andThen(helperPerson2::createLudwigErhard)
				.andThen(helperPerson2::createKurtKiesinger));
		tool.performIdleSourceEdit(helperPerson1::changeIncrementalIDs);
		
		util.assertPrecondition("Pre_IncrBwdPDB1FirstThreeChancellors", "Pre_IncrBwdPDB2FirstThreeChancellors");

		//------------
		util.configure().makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST, true);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::createWillyBrandt)
				.andThen(helperPerson2::createHelmutSchmidt)
				.andThen(helperPerson2::createHelmutKohl));
		
		util.assertPostcondition("IncrBwdDynamicConfigPDB1_1", "Pre_IncrBwdPDB2FirstSixChancellors");
		
		// now setting last_space
		util.configure().makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST, false);
		tool.performAndPropagateTargetEdit(helperPerson2::createGerhardSchroeder);
		util.assertPostcondition("IncrBwdDynamicConfigPDB1_2", "IncrBwdPDB2FirstSevenChancellors");
		
		// now setting first_space
		util.configure().makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST, true);
		tool.performAndPropagateTargetEdit(helperPerson2::createAngelaMerkel);
		util.assertPostcondition("IncrBwdDynamicConfigPDB1_3", "IncrBwdPDB2AllChancellors");
		//------------			
	}
	
	/**
	 * <b>Test</b> for deleting persons. After creating the person register,
	 * set set 6 chancelors with all variables. Then delete Kurt Kiesinger from the pdb1 database.
	 * <b>Expect</b>: Delete the correct Person in the pdb2 database
	 * <b>Features</b>: bwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::setDatabaseName)
				.andThen(helperPerson2::createKonradAdenauer)
				.andThen(helperPerson2::createLudwigErhard)
				.andThen(helperPerson2::createKurtKiesinger)
				.andThen(helperPerson2::createWillyBrandt)
				.andThen(helperPerson2::createHelmutSchmidt)
				.andThen(helperPerson2::createHelmutKohl));
		tool.performIdleSourceEdit(helperPerson1::changeIncrementalIDs);
		
		util.assertPrecondition("Pre_IncrBwdPDB1FirstSixChancellors", "Pre_IncrBwdPDB2FirstSixChancellors");
		//------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::deleteKurtKiesinger));
		//------------
		util.assertPostcondition("IncrBwdPDB1FirstSixChancellorsWithoutKiesinger", "IncrBwdPDB2FirstSixChancellorsWithoutKiesinger");
	}
	
	/**
	 * <b>Test</b> for changing all variable-values in different persons. After creating the pdb2 database. 
	 * Then change values of each variable in another person and all variables of one person. Using different configs for bwd Transformation.
	 * <b>Expect</b>: Change the values of the affected variables in Persons of the pdb1 database.
	 * <b>Features</b>: bwd, attribute, structural, corr-based
	 */
	@Test
	public void testIncrementalValueChange() {
		util.configure().makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST, false);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::setDatabaseName)
				.andThen(helperPerson2::createKonradAdenauer)
				.andThen(helperPerson2::createLudwigErhard)
				.andThen(helperPerson2::createKurtKiesinger)
				.andThen(helperPerson2::createWillyBrandt)
				.andThen(helperPerson2::createHelmutSchmidt)
				.andThen(helperPerson2::createHelmutKohl));
		tool.performIdleSourceEdit(helperPerson1::changeIncrementalIDs);
		
		util.assertPrecondition("Pre_IncrBwdPDB1FirstSixChancellors", "Pre_IncrBwdPDB2FirstSixChancellors");
		//------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::changeAllOfHelmutKohl)
				.andThen(helperPerson2::changeBirthdayOfKurtKiesinger)
				.andThen(helperPerson2::changeFirstNameOfKonradAdenauer));
		//------------
		util.assertPostcondition("IncrBwdPDB1FirstSixChancellorsAfterValueChange_1", "IncrBwdPDB2FirstSixChancellorsAfterValueChange_1");
		
		util.configure().makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST, true);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::changeIDOfHelmutSchmidt)
				.andThen(helperPerson2::changeLastNameOfLudwigErhard)
				.andThen(helperPerson2::changePlaceOfBirthOfWillyBrandt));
		//------------
		util.assertPostcondition("IncrBwdPDB1FirstSixChancellorsAfterValueChange_2", "IncrBwdPDB2FirstSixChancellorsAfterValueChange_2");
	}

	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta does not change the target model.<br/>
	 * <b>Features:</b>: bwd, fixed
	 */
	@Test
	public void testStability() {		
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::setDatabaseName)
				.andThen(helperPerson2::createKonradAdenauer)
				.andThen(helperPerson2::createLudwigErhard)
				.andThen(helperPerson2::createKurtKiesinger)
				.andThen(helperPerson2::createWillyBrandt)
				.andThen(helperPerson2::createHelmutSchmidt)
				.andThen(helperPerson2::createHelmutKohl)
				.andThen(helperPerson2::createGerhardSchroeder)
				.andThen(helperPerson2::createAngelaMerkel));
		tool.performIdleSourceEdit(helperPerson1::changeIncrementalIDs);

		util.assertPrecondition("IncrBwdPDB1AllChancellorsIDs", "IncrBwdPDB2AllChancellors");
		//------------
		tool.performAndPropagateTargetEdit(helperPerson2::idleDelta);
		//------------
		util.assertPostcondition("IncrBwdPDB1AllChancellorsIDs", "IncrBwdPDB2AllChancellors");
	}
	
	/**
	 * <b>Test</b> for hippocraticness of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after changing the configuration should not change anything in the pdb1 Database.<br/>
	 * <b>Features:</b>: bwd, fixed
	 */
	@Test
	public void testHippocraticness() {
		util.configure().makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST, false);
		tool.performAndPropagateTargetEdit(util
				.execute(helperPerson2::setDatabaseName)
				.andThen(helperPerson2::createKonradAdenauer)
				.andThen(helperPerson2::createLudwigErhard)
				.andThen(helperPerson2::createKurtKiesinger)
				.andThen(helperPerson2::createWillyBrandt)
				.andThen(helperPerson2::createHelmutSchmidt)
				.andThen(helperPerson2::createHelmutKohl)
				.andThen(helperPerson2::createGerhardSchroeder)
				.andThen(helperPerson2::createAngelaMerkel));
		tool.performIdleSourceEdit(helperPerson1::changeIncrementalIDs);

		util.assertPrecondition("IncrBwdPDB1AllChancellorsIDs", "IncrBwdPDB2AllChancellors");
		//------------
		util.configure().makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST, true);
		tool.performAndPropagateTargetEdit(helperPerson2::hippocraticDelta);
		//------------
		util.assertPostcondition("IncrBwdPDB1AllChancellorsIDs", "IncrBwdPDB2AllChancellors");
	}
}
