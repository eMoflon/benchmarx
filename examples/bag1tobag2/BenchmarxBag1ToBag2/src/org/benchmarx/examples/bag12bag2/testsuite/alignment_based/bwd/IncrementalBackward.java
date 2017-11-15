package org.benchmarx.examples.bag12bag2.testsuite.alignment_based.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.bag12bag2.testsuite.Bag12Bag2TestCase;
import org.benchmarx.examples.bag12bag2.testsuite.Decisions;
import org.junit.Test;

import bags1.MyBag;

public class IncrementalBackward extends Bag12Bag2TestCase {

	public IncrementalBackward(BXTool<MyBag, bags2.MyBag, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for inserting elements into an existing bag2. <br/>
	 * <b>Expect</b> : New elements are added to the bag1, while the old
	 * elements remain unchanged. <br/>
	 * <b>Features</b>: fwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateTargetEdit(helperBag2::createEmptyBottle);
		tool.performIdleSourceEdit(helperBag1::changeIncrementalID);

		util.assertPrecondition("OneEmptyBottleBags1", "OneEmptyBottleBags2");
		// ------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperBag2::createBeerGlass)
				.andThen(helperBag2::createFourBeer));
		// ------------
		util.assertPostcondition("FourBeerOneEmptyBottleWithGlassBags1", "FourBeerOneEmptyBottleWithGlassBags2");
	}

	/**
	 * <b>Test</b> for deleting elements. Element with value Beer Glass and
	 * value Beer is deleted from the bag2 containing 4 Beers, 1 Beer Glass and
	 * 1 Empty Bottle.<br/>
	 * <b>Expect</b>: Deletion of 1 Beer Glass and 4 Beers in bag2. <br/>
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperBag2::createBeerGlass)
				.andThen(helperBag2::createFourBeer)
				.andThen(helperBag2::createEmptyBottle));
		tool.performIdleSourceEdit(helperBag1::changeIncrementalID);

		util.assertPrecondition("FourBeerOneEmptyBottleWithGlassBags1", "FourBeerOneEmptyBottleWithGlassBags2");
		// ------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperBag2::deleteBeerGlass)
				.andThen(helperBag2::deleteAllBeers));
		// ------------
		util.assertPostcondition("OneEmptyBottleBags1", "OneEmptyBottleBags2");
	}

	/**
	 * <b>Test</b> for changing the value of Beer in bag2 to Empty Bottle and
	 * the multiplicity will get down to 2. The value of Empty Bottle goes to
	 * Broken Bottle and the multiplicity of Beer Glass will be increased by
	 * 2.<br/>
	 * <b>Expect</b>: Change occurs also in bag1.<br/>
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalValueChangeOfAll() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperBag2::createBeerGlass)
				.andThen(helperBag2::createFourBeer)
				.andThen(helperBag2::createEmptyBottle));
		tool.performIdleSourceEdit(helperBag1::changeIncrementalID);

		util.assertPrecondition("FourBeerOneEmptyBottleWithGlassBags1", "FourBeerOneEmptyBottleWithGlassBags2");
		// ------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperBag2::changeEmptyBottleToBrokenBottle)
				.andThen(helperBag2::changeMultiplicityOfBeer)
				.andThen(helperBag2::changeBeerToEmptyBottle)
				.andThen(helperBag2::changeMultiplicityOfBeerGlass));
		// ------------
		util.assertPostcondition("OneBrokenBottleTwoEmptyBottleWithTwoGlassesBags1", "OneBrokenBottleTwoEmptyBottleWithTwoGlassesBags2");
	}

	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta
	 * does not change the target model.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testStability() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperBag2::createBeerGlass)
				.andThen(helperBag2::createFourBeer)
				.andThen(helperBag2::createEmptyBottle));
		tool.performIdleSourceEdit(helperBag1::changeIncrementalID);

		util.assertPrecondition("FourBeerOneEmptyBottleWithGlassBags1", "FourBeerOneEmptyBottleWithGlassBags2");
		// ------------
		tool.performAndPropagateTargetEdit(helperBag2::idleDelta);
		// ------------
		util.assertPostcondition("FourBeerOneEmptyBottleWithGlassBags1", "FourBeerOneEmptyBottleWithGlassBags2");
	}
}
