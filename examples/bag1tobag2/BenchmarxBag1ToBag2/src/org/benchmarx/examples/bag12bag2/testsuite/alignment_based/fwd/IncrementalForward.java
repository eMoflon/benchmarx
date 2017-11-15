package org.benchmarx.examples.bag12bag2.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.bag12bag2.testsuite.Bag12Bag2TestCase;
import org.benchmarx.examples.bag12bag2.testsuite.Decisions;
import org.junit.Test;

import bags1.MyBag;

public class IncrementalForward extends Bag12Bag2TestCase {

	public IncrementalForward(BXTool<MyBag, bags2.MyBag, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for inserting the elements into an existing bag1. Elements with same and different values will be added to bag1.<br/>
	 * <b>Expect</b> : New elements are added to the amount of the existing elements in bag2 and one new Element should be created.<br/>
	 * <b>Features</b>: fwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperBag1::createOneBeer));
		tool.performIdleTargetEdit(helperBag2::changeIncrementalID);
		
		util.assertPrecondition("OneBeerBags1", "OneBeerIncrIDBags2");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperBag1::createFiveBeers)
				.andThen(helperBag1::createBeerGlass)
				.andThen(helperBag1::createBeerGlass));
		//------------
		util.assertPostcondition("SixBeerWithTwoGlassesBags1", "SixBeerWithTwoGlassesBags2");
	}
	
	/**
	 * <b>Test</b> for deleting elements. 2 Elements with value Beer and 1 with value Beer Glass are deleted from a bag1 containing 5 Beers and 1 Beer Glass.
	 * <b>Expect</b>: Change of multiplicity in bag2 of Element with value Beer and Deletion of Element with value Beer Glass.
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperBag1::createFiveBeers)
				.andThen(helperBag1::createBeerGlass));
		tool.performIdleTargetEdit(helperBag2::changeIncrementalID);
		
		util.assertPrecondition("FiveBeerWithGlassBags1", "FiveBeerWithGlassIncrIDBags2");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperBag1::deleteBeer)
				.andThen(helperBag1::deleteBeer)
				.andThen(helperBag1::deleteBeerGlass));
		//------------
		util.assertPostcondition("ThreeBeerBags1", "ThreeBeerBags2");
	}
	
	/**
	 * <b>Test</b> for changing the value of Beer in a single element of bag1 to EmptyBottle.
	 * <b>Expect</b>: Change occurs also in bag2.
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalValueChangeOfOne() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperBag1::createFiveBeers)
				.andThen(helperBag1::createBeerGlass));
		tool.performIdleTargetEdit(helperBag2::changeIncrementalID);
		
		util.assertPrecondition("FiveBeerWithGlassBags1", "FiveBeerWithGlassIncrIDBags2");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperBag1::changeOneBeerToEmptyBottle));
		tool.performIdleSourceEdit(helperBag1::changeIncrementalID);
		//------------
		util.assertPostcondition("FourBeerOneEmptyBottleWithGlassBags1", "FourBeerOneEmptyBottleWithGlassIncrIDBags2");
	}
	
	/**
	 * <b>Test</b> for changing the value of Beer of all occurences of Beer in bag1 to Empty Bottle.
	 * <b>Expect</b>: Change occurs also in bag2: A Element with multiplicity 5 and value Empty Bottle should be there.
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalValueChangeOfAll() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperBag1::createFiveBeers)
				.andThen(helperBag1::createBeerGlass));
		tool.performIdleTargetEdit(helperBag2::changeIncrementalID);
		
		util.assertPrecondition("FiveBeerWithGlassBags1", "FiveBeerWithGlassIncrIDBags2");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperBag1::changeAllBeerToEmptyBottles));
		//------------
		util.assertPostcondition("FiveEmptyBottlesWithGlassBags1", "FiveEmptyBottlesWithGlassBags2");
	}

	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta does not change the target model.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testStability() {		
		tool.performAndPropagateSourceEdit(util
				.execute(helperBag1::createFiveBeers)
				.andThen(helperBag1::createBeerGlass)
				.andThen(helperBag1::createBeerGlass)
				.andThen(helperBag1::createOneBeer));
		tool.performIdleTargetEdit(helperBag2::changeIncrementalID);

		util.assertPrecondition("SixBeerWithTwoGlassesBags1", "SixBeerWithTwoGlassesBags2");
		//------------
		tool.performAndPropagateSourceEdit(helperBag1::idleDelta);
		//------------
		util.assertPostcondition("SixBeerWithTwoGlassesBags1", "SixBeerWithTwoGlassesBags2");
	}
}
