package org.benchmarx.examples.ast2dag.testsuite.alignment_based.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.ast2dag.testsuite.Ast2DagTestCase;
import org.benchmarx.examples.ast2dag.testsuite.Decisions;
import org.junit.Test;

public class IncrementalBackward extends Ast2DagTestCase {
	public IncrementalBackward(BXTool<ast.Model, dag.Model, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting new nodes in an existing dag. Inserting a node is only possible at leaves. <br/>
	 * <b>Expect</b> : New nodes are inserted in the dag. <br/>
	 * <b>Features</b>: fwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateTargetEdit(util.execute(helperDag::createBestDigit));
		tool.performIdleSourceEdit(helperAst::changeIncrementalID);
		
		util.assertPrecondition("BestDigitIncrIDAst", "BestDigitDag");
		//------------
		tool.performAndPropagateTargetEdit(util.execute(helperDag::insertMoreBestDigits));
		//------------
		util.assertPostcondition("MoreBestDigitsIncrIDAst", "MoreBestDigitsDag");
	}
	
	/**
	 * <b>Test</b> for deleting nodes from an existing dag. Only inner nodes can be deleted, deleting leaves would
	 * result in an invalid tree. Childs of the deleted node are deleted recursiv.
	 * <b>Expect</b>: Delete the correct nodes from an dag.
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateTargetEdit(util.execute(helperDag::createMoreBestDigits));
		tool.performIdleSourceEdit(helperAst::changeIncrementalID);
		
		util.assertPrecondition("MoreBestDigitsAllIncrIDAst", "MoreBestDigitsDag");
		//------------
		tool.performAndPropagateTargetEdit(util.execute(helperDag::removeSomeBestDigits));
		//------------
		util.assertPostcondition("BestDigitIncrIDDelAst", "BestDigitDag");
	}
	
	/**
	 * <b>Test</b> for modifying an dag. Modifying is changing the value of a node or switch between variable and
	 *  number.
	 * <b>Expect</b>: Change some nodes in the dag.
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalModifications() {
		tool.performAndPropagateTargetEdit(util.execute(helperDag::createBestDigitRef));
		tool.performIdleSourceEdit(helperAst::changeIncrementalID);
		
		util.assertPrecondition("BestDigitRefIncrIDAst", "BestDigitRefDag");
		//------------
		tool.performAndPropagateTargetEdit(util.execute(helperDag::modifyBestDigitRef));
		//------------
		util.assertPostcondition("BestDigitRefModifiedIncrIDAst", "BestDigitRefModifiedDag");
	}

	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta does not change the target model.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testStability() {
		// No precondition!
		//------------
		tool.performAndPropagateTargetEdit(util.execute(helperDag::createBestDigit));
		tool.performIdleSourceEdit(helperAst::changeIncrementalID);
		//------------
		util.assertPostcondition("BestDigitIncrIDAst", "BestDigitDag");
		
		tool.performAndPropagateTargetEdit(helperDag::idleDelta);
		util.assertPostcondition("BestDigitIncrIDAst", "BestDigitDag");
	}
}
