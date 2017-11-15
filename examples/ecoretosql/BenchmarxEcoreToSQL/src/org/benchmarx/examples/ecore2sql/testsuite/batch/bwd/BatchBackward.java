package org.benchmarx.examples.ecore2sql.testsuite.batch.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.ecore2sql.testsuite.Decisions;
import org.benchmarx.examples.ecore2sql.testsuite.EcoreToSQLTestCase;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;

import sql.Schema;

public class BatchBackward extends EcoreToSQLTestCase {
	public BatchBackward(BXTool<EPackage, Schema, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for name change of an empty schema.<br/>
	 * <b>Expect</b> name, uri and prefix in the EPackage is also changed.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testSQLNameChangeOfEmpty()
	{
		util.assertPrecondition("RootElementEcore", "RootElementSQL");
		//------------
		tool.performAndPropagateTargetEdit(helperSQL::changePackageName);
		//------------
		util.assertPostcondition("CompositeListPackageEcore", "CompositeListPackageSQL");
	}
	
	/**
	 * <b>Test</b> for creation of a simple SQL schema.
	 * <br/>
	 * <b>Expect</b> the creation of the corresponding ecore model.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreateSimpleCompositeList()
	{
		// No precondition!
		//------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperSQL::changePackageName)
				.andThen(helperSQL::createNodeTable)
				.andThen(helperSQL::createLeafTable)
				.andThen(helperSQL::createDataNodeTable)
				.andThen(helperSQL::createListTable));
		//------------
		util.assertPostcondition("CompositeListSimple-OperationsEcore", "CompositeListSimpleSQL");
	}

	/**
	 * Analogous to @link {@link #testCreateSimpleCompositeList()}, now with all possible reference types.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test 
	public void testCreateComplexCompositeList(){
		// No precondition!
		//------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperSQL::changePackageName)
				.andThen(helperSQL::createNodeTable)
				.andThen(helperSQL::createLeafTable)
				.andThen(helperSQL::createDataNodeTable)
				.andThen(helperSQL::createListTable)
				.andThen(helperSQL::createDataElementTable)
				.andThen(helperSQL::createPairTable)
				.andThen(helperSQL::createValueTable)
				.andThen(helperSQL::createKeyTable)
				.andThen(helperSQL::createKey_keyValuesTable)
				.andThen(helperSQL::createList_start_inverse_Node_startOfTable)
				.andThen(helperSQL::changeDataNodeTable)
				.andThen(helperSQL::changeListTable));
		tool.performIdleSourceEdit(helperEcore::setDataElementAsInterface);
		//------------
		util.assertPostcondition("CompositeListData-OperationsEcore", "CompositeListDataSQL");
	}
}
