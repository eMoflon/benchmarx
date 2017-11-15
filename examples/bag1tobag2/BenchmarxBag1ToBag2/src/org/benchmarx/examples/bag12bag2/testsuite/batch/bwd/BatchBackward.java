package org.benchmarx.examples.bag12bag2.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.bag12bag2.testsuite.Bag12Bag2TestCase;
import org.benchmarx.examples.bag12bag2.testsuite.Decisions;
import org.junit.Test;

import bags1.MyBag;

public class BatchBackward extends Bag12Bag2TestCase {

	public BatchBackward(BXTool<MyBag, bags2.MyBag, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for creation of a single Element (Beer) in an empty Database.
	 * <br/>
	 * <b>Expect</b> 1 Beer to be created in the source model.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateElement()
	{
		// No precondition!
		//------------
		tool.performAndPropagateTargetEdit(helperBag2::createOneBeer);
		//------------
		util.assertPostcondition("OneBeerBags1", "OneBeerBags2");
	}

	/**
	 * Analogous to @link {@link #testCreateElement()}, but now for
	 * multiple Elements (5 Beers and 1 Beer Glass).<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test 
	public void testCreateMultipleElements(){
		// No precondition!
		//------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperBag2::createFiveBeer)
				.andThen(helperBag2::createBeerGlass));
		//------------
		util.assertPostcondition("FiveBeerWithGlassBags1", "FiveBeerWithGlassBags2");
	}
}
