package org.benchmarx.examples.set2oset.testsuite.alignment_based.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.set2oset.testsuite.Decisions;
import org.benchmarx.examples.set2oset.testsuite.Set2OsetTestCase;
import org.junit.Test;

public class IncrementalBackward extends Set2OsetTestCase {
	public IncrementalBackward(BXTool<sets.MySet, osets.MyOrderedSet, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting elements into an existing oset, after the initial oset has been transformed into a
	 * set. <br/>
	 * <b>Expect</b> : New elements are added to the set, while the old elements
	 * remain unchanged. <br/>
	 * <b>Features</b>: bwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperOset::setSetName)
				.andThen(helperOset::createC));
		tool.performIdleSourceEdit(helperSet::changeIncrementalID);
		
		util.assertPrecondition("CChangedSet", "COset");
		//------------
		tool.performAndPropagateTargetEdit(helperOset::insertABeforeC);
		//------------
		util.assertPostcondition("AcChangedSet", "AcOset");
		
		tool.performAndPropagateTargetEdit(helperOset::insertBBeforeC);
		util.assertPostcondition("FirstThreeLettersChangedSet", "FirstThreeLettersOset");
		
		tool.performAndPropagateTargetEdit(helperOset::insertDAfterC);
		util.assertPostcondition("AbcdChangedSet", "AbcdOset");
	}
	
	/**
	 * <b>Test</b> for deleting elements from an existing oset, after the initial oset has been transformed into a
	 * set. <br/>
	 * <b>Expect</b>: Deletion of the elements in the set, while old elements
	 * remain unchanged. <br/>
	 * <b>Features</b>: bwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperOset::setSetName)
				.andThen(helperOset::createA)
				.andThen(helperOset::createB)
				.andThen(helperOset::createC)
				.andThen(helperOset::createD));
		tool.performIdleSourceEdit(helperSet::changeIncrementalID);
		
		util.assertPrecondition("AbcdChangedSet", "AbcdOset");
		//------------
		tool.performAndPropagateTargetEdit(helperOset::deleteD);
		//------------
		util.assertPostcondition("FirstThreeLettersChangedSet", "FirstThreeLettersOset");
		
		tool.performAndPropagateTargetEdit(helperOset::deleteB);
		util.assertPostcondition("AcChangedSet", "AcOset");
		
		tool.performAndPropagateTargetEdit(helperOset::deleteA);
		util.assertPostcondition("CChangedSet", "COset");
	}
	
	/**
	 * <b>Test</b> for renaming the elements A, B, C to Z, X, Y, after the initial oset has been transformed into a
	 * set. <br/>
	 * <b>Expect</b>: Change occurs also in the set.
	 * <b>Features</b>: bwd, attribute, structural, corr-based
	 */
	@Test
	public void testIncrementalValueChange() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperOset::setSetName)
				.andThen(helperOset::createA)
				.andThen(helperOset::createB)
				.andThen(helperOset::createC));
		tool.performIdleSourceEdit(helperSet::changeIncrementalID);
		
		util.assertPrecondition("FirstThreeLettersChangedSet", "FirstThreeLettersOset");
		//------------
		tool.performAndPropagateTargetEdit(helperOset::changeABCtoZXY);
		//------------
		util.assertPostcondition("ZxyChangedSet", "ZxyOset");
	}

	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle target delta does not change the source model.<br/>
	 * <b>Features:</b>: bwd, fixed
	 */
	@Test
	public void testStability() {		
		tool.performAndPropagateTargetEdit(util
				.execute(helperOset::setSetName)
				.andThen(helperOset::createA)
				.andThen(helperOset::createB)
				.andThen(helperOset::createC));
		tool.performIdleSourceEdit(helperSet::changeIncrementalID);

		util.assertPrecondition("FirstThreeLettersChangedSet", "FirstThreeLettersOset");
		//------------
		tool.performAndPropagateTargetEdit(helperOset::idleDelta);
		//------------
		util.assertPostcondition("FirstThreeLettersChangedSet", "FirstThreeLettersOset");
	}
	
	/**
	 * <b>Test</b> for hippocraticness of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after inverting the oset ABC to CBA should not change the
	 * set.<br/>
	 * <b>Features:</b>: bwd, fixed
	 */
	@Test
	public void testHippocraticness() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperOset::setSetName)
				.andThen(helperOset::createA)
				.andThen(helperOset::createB)
				.andThen(helperOset::createC));
		tool.performIdleSourceEdit(helperSet::changeIncrementalID);

		util.assertPrecondition("FirstThreeLettersChangedSet", "FirstThreeLettersOset");
		//------------
		tool.performAndPropagateTargetEdit(helperOset::invert);
		//------------
		util.assertPostcondition("FirstThreeLettersChangedSet", "CbaOset");
	}
}
