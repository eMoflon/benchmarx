package org.benchmarx.examples.ast2dag.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.ast2dag.testsuite.Ast2DagTestCase;
import org.benchmarx.examples.ast2dag.testsuite.Decisions;
import org.junit.Test;

public class BatchBackward extends Ast2DagTestCase {

	public BatchBackward(BXTool<ast.Model, dag.Model, Decisions> tool) {
		super(tool);
	}

	/**
	 * <b>Test</b> for creation of a Expression with a single number in an empty Dag.
	 * <br/>
	 * <b>Expect</b> Single number Expression to be created in the source model.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateSingleExpression()
	{
		// No precondition!
		//------------
		tool.performAndPropagateTargetEdit(helperDag::create42);
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
		tool.performAndPropagateTargetEdit(util
				.execute(helperDag::createTextSum));
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
		tool.performAndPropagateTargetEdit(util
				.execute(helperDag::createComplexNumberExample));
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
		tool.performAndPropagateTargetEdit(util
				.execute(helperDag::createMulitpleSubtrees));
		//------------
		util.assertPostcondition("42ByMultipleSubteesAst", "42ByMultipleSubtreesDag");
	}
}
