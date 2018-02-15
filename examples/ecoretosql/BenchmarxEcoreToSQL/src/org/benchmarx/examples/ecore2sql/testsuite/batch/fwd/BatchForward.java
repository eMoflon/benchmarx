package org.benchmarx.examples.ecore2sql.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.ecore2sql.testsuite.Decisions;
import org.benchmarx.examples.ecore2sql.testsuite.EcoreToSQLTestCase;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;

import sql.Schema;

public class BatchForward extends EcoreToSQLTestCase {
	public BatchForward(BXTool<EPackage, Schema, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for agreed upon starting state.<br/>
	 * <b>Expect</b> root elements of both source and target models.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testInitialiseSynchronisation() {
		// No precondition!
		//------------
		util.assertPostcondition("RootElementEcore", "RootElementSQL");
	}
	
	/**
	 * <b>Test</b> for name change of an empty EPackage.<br/>
	 * <b>Expect</b> name in the SQL schema is also changed.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testEcoreNameChangeOfEmpty()
	{
		util.assertPrecondition("RootElementEcore", "RootElementSQL");
		//------------
		tool.performAndPropagateSourceEdit(helperEcore::changePackageName);
		//------------
		util.assertPostcondition("CompositeListPackageEcore", "CompositeListPackageSQL");
	}
	
	/**
	 * <b>Test</b> for creation of a simple ecore model.
	 * <br/>
	 * <b>Expect</b> the creation of the corresponding SQL schema.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateSimpleCompositeList()
	{
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::changePackageName)
				.andThen(helperEcore::createSimpleCompositeList));
		//------------
		util.assertPostcondition("CompositeListSimpleEcore", "CompositeListSimpleSQL");
	}

	/**
	 * Analogous to @link {@link #testCreateSimpleCompositeList()}, now with all possible reference types.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test 
	public void testCreateComplexCompositeList(){
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::changePackageName)
				.andThen(helperEcore::createSimpleCompositeList)
				.andThen(helperEcore::addDataElementFeature)
				.andThen(helperEcore::changeListAddParameter));
		//------------
		util.assertPostcondition("CompositeListDataEcore", "CompositeListDataSQL");
	}
}
