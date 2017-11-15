package org.benchmarx.examples.set2oset.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.set2oset.testsuite.Decisions;
import org.benchmarx.examples.set2oset.testsuite.Set2OsetTestCase;
import org.junit.Test;

public class BatchForward extends Set2OsetTestCase {
	public BatchForward(BXTool<sets.MySet, osets.MyOrderedSet, Decisions> tool) {
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
		util.assertPostcondition("RootElementSet", "RootElementOset");
	}
	
	/**
	 * <b>Test</b> for name change of an empty Set.<br/>
	 * <b>Expect</b> the name in the target OrderedSet is also changed.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testDatabaseNameChangeOfEmpty()
	{
		tool.performAndPropagateSourceEdit(util.execute(helperSet::setSetName));

		util.assertPrecondition("EmptyAlphabetSet", "EmptyAlphabetOset");
		//------------
		tool.performAndPropagateSourceEdit(util.execute(helperSet::renameAlphabetSetToABC));
		//------------
		util.assertPostcondition("EmptyABCSet", "EmptyABCOset");
	}
	
	/**
	 * <b>Test</b> for creation of a single Element (A) in an empty Set.
	 * <br/>
	 * <b>Expect</b> A to be created in the target model.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateElement()
	{
		tool.performAndPropagateSourceEdit(util.execute(helperSet::setSetName));

		util.assertPrecondition("EmptyAlphabetSet", "EmptyAlphabetOset");
		//------------
		tool.performAndPropagateSourceEdit(helperSet::createA);
		//------------
		util.assertPostcondition("OnlyASet", "OnlyAOset");
	}

	/**
	 * Analogous to @link {@link #testCreateElement()}, but now for
	 * multiple Elements (first three letters).<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test 
	public void testCreateMultipleElements(){
		tool.performAndPropagateSourceEdit(util.execute(helperSet::setSetName));

		util.assertPrecondition("EmptyAlphabetSet", "EmptyAlphabetOset");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperSet::createA)
				.andThen(helperSet::createB)
				.andThen(helperSet::createC));
		//------------
		util.assertPostcondition("FirstThreeLettersSet", "FirstThreeLettersOset");
	}
}
