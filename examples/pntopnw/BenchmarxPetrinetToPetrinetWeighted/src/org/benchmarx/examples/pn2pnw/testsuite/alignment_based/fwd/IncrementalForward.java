package org.benchmarx.examples.pn2pnw.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.pn2pnw.testsuite.Decisions;
import org.benchmarx.examples.pn2pnw.testsuite.Pn2PnwTestCase;
import org.junit.Test;

public class IncrementalForward extends Pn2PnwTestCase {
	public IncrementalForward(BXTool<pn.Net, pnw.Net, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting of a place and a transition into an existing pn after the weights in the pnw have been
	 * changed. <br/>
	 * <b>Expect</b> : New elements are added to the net, while the old elements remain unchanged. <br/>
	 * <b>Features</b>: fwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateSourceEdit(helperPn::createPTPLettersDigits);
		tool.performIdleTargetEdit(helperPnw::weightA1BWith42);
		
		util.assertPrecondition("PTPLettersDigitsPn", "PTPLettersDigitsWeightedPnw");
		//------------
		tool.performAndPropagateSourceEdit(helperPn::extendPTPLettersDigits);
		//------------
		util.assertPostcondition("PTPExtendedLettersDigitsPn", "PTPExtendedLettersDigitsWeightedPnw");
	}
	
	/**
	 * <b>Test</b> for deleting of a place and a transition from an existing pn after the weights in the pnw have been
	 * changed. <br/>
	 * <b>Expect</b>: Deletion of the correct elements, while the other elements remain unchanged. <br/>
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperPn::createPTPLettersDigits)
				.andThen(helperPn::extendPTPLettersDigits));
		tool.performIdleTargetEdit(helperPnw::weightA1BWith42);
		
		util.assertPrecondition("PTPExtendedLettersDigitsPn", "PTPExtendedLettersDigitsWeightedPnw");
		//------------
		tool.performAndPropagateSourceEdit(helperPn::reducePTPExtendedLettersDigits);
		//------------
		util.assertPostcondition("PTPLettersDigitsPn", "PTPLettersDigitsWeightedPnw");
	}
	
	/**
	 * <b>Test</b> for changing the tokens and edges of an existing pn after the weights in the pnw have been
	 * changed. <br/>
	 * <b>Expect</b>: Change the respective tokens and edges in the pnw, while the other elements remain
	 * unchanged. <br/>
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalChanges() {
		tool.performAndPropagateSourceEdit(helperPn::create1234LettersDigits);
		tool.performIdleTargetEdit(util
				.execute(helperPnw::weightA1BWith42)
				.andThen(helperPnw::weightB2With9));
		
		util.assertPrecondition("1234LettersDigitsPn", "1234LettersDigitsWeightedPnw");
		//------------
		tool.performAndPropagateSourceEdit(helperPn::construct5678LettersDigits);
		//------------
		util.assertPostcondition("5678LettersDigitsPn", "5678LettersDigitsWeightedPnw");
	}
	
	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta does not change the target model.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testStability() {
		tool.performAndPropagateSourceEdit(helperPn::createComplexLettersDigits);
		tool.performIdleTargetEdit(helperPnw::weightA3DWith24);
		
		util.assertPrecondition("ComplexLettersDigitsPn", "ComplexLettersDigitsSimpleWeightedPnw");
		//------------
		tool.performAndPropagateSourceEdit(helperPn::idleDelta);
		//------------
		util.assertPostcondition("ComplexLettersDigitsPn", "ComplexLettersDigitsSimpleWeightedPnw");
	}
	
	/**
	 * <b>Test</b> for hippocraticness of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after changing the incrementalID does not change the pnw.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testHipporcraticness() {
		tool.performAndPropagateSourceEdit(helperPn::createComplexLettersDigits);
		tool.performIdleTargetEdit(helperPnw::weightA3DWith24);
		
		util.assertPrecondition("ComplexLettersDigitsPn", "ComplexLettersDigitsSimpleWeightedPnw");
		//------------
		tool.performAndPropagateSourceEdit(helperPn::changeIncrementalID);
		//------------
		util.assertPostcondition("ComplexLettersDigitsChangedPn", "ComplexLettersDigitsSimpleWeightedPnw");
	}
}
