package org.benchmarx.examples.ast2dag.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.ast2dag.testsuite.Ast2DagTestCase;
import org.benchmarx.examples.ast2dag.testsuite.Decisions;
import org.junit.Test;

public class BatchForward extends Ast2DagTestCase {

	public BatchForward(BXTool<ast.Model, dag.Model, Decisions> tool) {
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
		util.assertPostcondition("RootElementAst", "RootElementDag");
	}
	
	/**
	 * <b>Test</b> for creation of a Expression with only one Number in an empty Ast.
	 * <br/>
	 * <b>Expect</b> Expression with only one number should be created.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateSingleExpression()
	{
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperAst::create42);
		//------------
		util.assertPostcondition("42Ast", "42Dag");
	}

	/**
	 * Analogous to @link {@link #testCreateSingleExpression()}, but now for
	 * multiple Expressions.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test 
	public void testCreateMultipleExpressions(){
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperAst::createTextSum));
		//------------
		util.assertPostcondition("HG2GAst", "HG2GDag");
	}
	
	/**
	 * Analogous to @link {@link #testCreateSingleExpression()}, but now for
	 * multiple Expressions. This time the Expressions will be there more than one time.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateMultipleExpressionsComplex() {
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperAst::createComplexNumberExample));
		//------------
		util.assertPostcondition("42ByMultiplyAddSubtractDivideAst", "42ByMultiplyAddSubtractDivideDag");
	}
	
	/**
	 * Analogous to @link {@link #testCreateSingleExpression()}, but now for
	 * multiple Expressions in multiple identical subtrees.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateMultipleSameSubtrees() {
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperAst::createMulitpleSubtrees);
		//------------
		util.assertPostcondition("42ByMultipleSubteesAst", "42ByMultipleSubtreesDag");
	}
}
