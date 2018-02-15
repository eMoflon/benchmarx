package org.benchmarx.examples.pn2pnw.testsuite.alignment_based.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.pn2pnw.testsuite.Decisions;
import org.benchmarx.examples.pn2pnw.testsuite.Pn2PnwTestCase;
import org.junit.Test;

public class IncrementalBackward extends Pn2PnwTestCase {
	public IncrementalBackward(BXTool<pn.Net, pnw.Net, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting of a place, a transition and some edges into an existing pnw, after the initial pnw has
	 * been transformed into a pn. <br/>
	 * <b>Expect</b> : New elements are added to the net, while the old elements remain unchanged. <br/>
	 * <b>Features</b>: bwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateTargetEdit(util.execute(helperPnw::createPTPLettersDigits)
				.andThen(helperPnw::weightA1BWith42));
		tool.performIdleSourceEdit(helperPn::changeIncrementalID);
		
		util.assertPrecondition("PTPLettersDigitsChangedPn", "PTPLettersDigitsWeightedPnw");
		//------------
		tool.performAndPropagateTargetEdit(helperPnw::extendPTPLettersDigits);
		//------------
		util.assertPostcondition("PTPExtendedLettersDigitsChangedPn", "PTPExtendedLettersDigitsWeightedPnw");
		
		tool.performAndPropagateTargetEdit(helperPnw::furtherExtendPTPLettersDigits);
		util.assertPostcondition("PTPFurtherExtendedLettersDigitsChangedPn", "PTPFurtherExtendedLettersDigitsWeightedPnw");
	}
	
	/**
	 * <b>Test</b> for deleting a place, a transition and some edges into an existing pnw, after the initial pnw has
	 * been transformed into a pn. <br/>
	 * <b>Expect</b>: Deletion of the correct elements, while the other elements remain unchanged. <br/>
	 * <b>Features</b>: bwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperPnw::createPTPLettersDigits)
				.andThen(helperPnw::extendPTPLettersDigits)
				.andThen(helperPnw::furtherExtendPTPLettersDigits)
				.andThen(helperPnw::weightA1BWith42));
		tool.performIdleSourceEdit(helperPn::changeIncrementalID);
		
		util.assertPrecondition("PTPFurtherExtendedLettersDigitsChangedPn", "PTPFurtherExtendedLettersDigitsWeightedPnw");
		//------------
		tool.performAndPropagateTargetEdit(helperPnw::reducePTPFurtherExtendedLettersDigits);
		//------------
		util.assertPostcondition("PTPExtendedLettersDigitsChangedPn", "PTPExtendedLettersDigitsWeightedPnw");
		
		tool.performAndPropagateTargetEdit(helperPnw::reducePTPExtendedLettersDigits);
		util.assertPostcondition("PTPLettersDigitsChangedPn", "PTPLettersDigitsWeightedPnw");
	}
	
	/**
	 * <b>Test</b> for changing the tokens and edges of an existing pnw, after the initial pnw has been transformed
	 * into a pn. <br/>
	 * <b>Expect</b>: Change the respective tokens and edges in the pn, while the other elements remain
	 * unchanged. <br/>
	 * <b>Features</b>: bwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalChanges() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperPnw::create1234LettersDigits)
				.andThen(helperPnw::weightA1BWith42)
				.andThen(helperPnw::weightB2With9));
		tool.performIdleSourceEdit(helperPn::changeIncrementalID);
		
		util.assertPrecondition("1234LettersDigitsChangedPn", "1234LettersDigitsWeightedPnw");
		//------------
		tool.performIdleTargetEdit(helperPnw::weightA1BWith73);
		tool.performAndPropagateTargetEdit(helperPnw::construct9012LettersDigits);
		//------------
		util.assertPostcondition("9012LettersDigitsChangedPn", "9012LettersDigitsWeightedPnw");
	}
	
	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle target delta does not change the source model.<br/>
	 * <b>Features:</b>: bwd, fixed
	 */
	@Test
	public void testStability() {
		tool.performAndPropagateTargetEdit(helperPnw::createComplexLettersDigits);
		tool.performIdleSourceEdit(helperPn::changeIncrementalID);
		
		util.assertPostcondition("ComplexLettersDigitsChangedPn", "ComplexLettersDigitsWeightedPnw");
		//------------
		tool.performAndPropagateTargetEdit(helperPnw::idleDelta);
		//------------
		util.assertPostcondition("ComplexLettersDigitsChangedPn", "ComplexLettersDigitsWeightedPnw");
	}
	
	@Test
	public void testHippocraticness() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperPnw::createPTPLettersDigits)
				.andThen(helperPnw::weightA1BWith42));
		tool.performIdleSourceEdit(helperPn::changeIncrementalID);
		
		util.assertPrecondition("PTPLettersDigitsChangedPn", "PTPLettersDigitsWeightedPnw"); 
		// ---------------------------------
		tool.performAndPropagateTargetEdit(helperPnw::weightA1BWith73);
		// ---------------------------------
		util.assertPostcondition("PTPLettersDigitsChangedPn", "PTPLettersDigitsWeighted73Pnw"); 
	}
}
