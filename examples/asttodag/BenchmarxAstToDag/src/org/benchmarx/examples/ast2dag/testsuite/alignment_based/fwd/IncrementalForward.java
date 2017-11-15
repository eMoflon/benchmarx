package org.benchmarx.examples.ast2dag.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.ast2dag.testsuite.Ast2DagTestCase;
import org.benchmarx.examples.ast2dag.testsuite.Decisions;
import org.junit.Test;

public class IncrementalForward extends Ast2DagTestCase {
	public IncrementalForward(BXTool<ast.Model, dag.Model, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting new nodes in an existing ast. Inserting a node is only possible at leaves. <br/>
	 * <b>Expect</b> : New nodes are inserted in the ast. <br/>
	 * <b>Features</b>: fwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateSourceEdit(util.execute(helperAst::createBestDigit));
		tool.performIdleTargetEdit(helperDag::changeIncrementalID);
		
		util.assertPrecondition("BestDigitAst", "BestDigitIncrIDDag");
		//------------
		tool.performAndPropagateSourceEdit(util.execute(helperAst::insertMoreBestDigits));
		//------------
		util.assertPostcondition("MoreBestDigitsAst", "MoreBestDigitsIncrIDDag");
	}
	
	/**
	 * <b>Test</b> for deleting nodes from an existing ast. Only inner nodes can be deleted, deleting leaves would
	 * result in an invalid tree. Childs of the deleted node are deleted recursiv.
	 * <b>Expect</b>: Delete the correct nodes from an ast.
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateSourceEdit(util.execute(helperAst::createMoreBestDigits));
		tool.performIdleTargetEdit(helperDag::changeIncrementalID);
		
		util.assertPrecondition("MoreBestDigitsAst", "MoreBestDigitsAllIncrIDDag");
		//------------
		tool.performAndPropagateSourceEdit(util.execute(helperAst::removeSomeBestDigits));
		//------------
		util.assertPostcondition("BestDigitAst", "BestDigitIncrIDDag");
	}
	
	/**
	 * <b>Test</b> for modifying an ast. Modifying is changing the value of a node or switch between variable and
	 *  number.
	 * <b>Expect</b>: Change some nodes in the ast.
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalModifications() {
		tool.performAndPropagateSourceEdit(util.execute(helperAst::createBestDigitRef));
		tool.performIdleTargetEdit(helperDag::changeIncrementalID);
		
		util.assertPrecondition("BestDigitRefAst", "BestDigitRefIncrIDDag");
		//------------
		tool.performAndPropagateSourceEdit(util.execute(helperAst::modifyBestDigitRef));
		tool.performIdleTargetEdit(helperDag::changeIncrementalIDOf8);
		//------------
		util.assertPostcondition("BestDigitRefModifiedAst", "BestDigitRefModifiedIncrIDDag");
	}
	
	/**
	 * <b>Test</b> for modifying an AST. The modification leads to deletions of elements in the DAG
	 * <b>Expect</b>: Chaning the value of a node in the AST leads to deletions in the DAG
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalModificationsResultingInDeletions() {
		tool.performAndPropagateSourceEdit(util.execute(helperAst::createSimpleASTRef));		
		
		util.assertPrecondition("SimpleASTRef", "SimpleDAGRef");
		//-------------
		tool.performIdleTargetEdit(helperDag::changeIncrementalID);
		tool.performAndPropagateSourceEdit(helperAst::modifySimpleASTRef);
		//-------------
		util.assertPostcondition("SimpleASTRefAfter", "SimpleDAGRefAfter");
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
		tool.performAndPropagateSourceEdit(util.execute(helperAst::createBestDigit));
		tool.performIdleTargetEdit(helperDag::changeIncrementalID);
		//------------
		util.assertPostcondition("BestDigitAst", "BestDigitIncrIDDag");
		
		tool.performAndPropagateSourceEdit(helperAst::idleDelta);
		util.assertPostcondition("BestDigitAst", "BestDigitIncrIDDag");
	}
}
