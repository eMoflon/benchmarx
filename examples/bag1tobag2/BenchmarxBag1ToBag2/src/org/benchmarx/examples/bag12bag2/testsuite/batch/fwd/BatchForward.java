package org.benchmarx.examples.bag12bag2.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.bag12bag2.testsuite.Bag12Bag2TestCase;
import org.benchmarx.examples.bag12bag2.testsuite.Decisions;
import org.junit.Test;

import bags1.MyBag;

public class BatchForward extends Bag12Bag2TestCase {

	public BatchForward(BXTool<MyBag, bags2.MyBag, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for agreed upon starting state.<br/>
	 * <b>Expect</b> root elements of both source and target models.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testInitialiseSynchronisation()
	{
		// No precondition!
		//------------
		util.assertPostcondition("RootElementBags1", "RootElementBags2");
	}
	
	/**
	 * <b>Test</b> for creation of a single Element (Beer) in an empty Database.
	 * <br/>
	 * <b>Expect</b> 1 Beer to be created in the target model.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateElement()
	{
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperBag1::createOneBeer);
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
		tool.performAndPropagateSourceEdit(util
				.execute(helperBag1::createFiveBeers)
				.andThen(helperBag1::createBeerGlass));
		//------------
		util.assertPostcondition("FiveBeerWithGlassBags1", "FiveBeerWithGlassBags2");
	}
}
