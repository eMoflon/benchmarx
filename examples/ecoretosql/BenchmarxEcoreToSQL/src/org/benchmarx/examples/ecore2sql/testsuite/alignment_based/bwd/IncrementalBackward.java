package org.benchmarx.examples.ecore2sql.testsuite.alignment_based.bwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.ecore2sql.testsuite.Decisions;
import org.benchmarx.examples.ecore2sql.testsuite.EcoreToSQLTestCase;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;

import sql.Schema;

public class IncrementalBackward extends EcoreToSQLTestCase {

	public IncrementalBackward(BXTool<EPackage, Schema, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting tables, columns and foreign keys in a SQL schema after the initial
	 * schema has been transformed into an EPackage. <br/>
	 * <b>Expect</b> : New classes, attributes and references should be added to the EPackage.<br/>
	 * <b>Features</b>: bwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateTargetEdit(util
				.execute(helperSQL::changePackageName)
				.andThen(helperSQL::createNodeTable)
				.andThen(helperSQL::createLeafTable)
				.andThen(helperSQL::createDataNodeTable)
				.andThen(helperSQL::createListTable));
		tool.performIdleSourceEdit(helperEcore::createMethodsSimple);
		tool.performIdleSourceEdit(helperEcore::changeListLengthAttribute);
		
		util.assertPrecondition("CompositeListSimpleEcore", "CompositeListSimpleSQL");
		//------------		
		tool.performAndPropagateTargetEdit(util
				.execute(helperSQL::createDataElementTable)
				.andThen(helperSQL::createPairTable)
				.andThen(helperSQL::createValueTable)
				.andThen(helperSQL::createKeyTable));
		tool.performIdleSourceEdit(helperEcore::setDataElementAsInterface);
		tool.performIdleSourceEdit(helperEcore::changeListAddParameter);
		//------------	
		util.assertPostcondition("CompositeListSimpleDataEcore", "CompositeListSimpleDataSQL");
		
		//------------	
		tool.performAndPropagateTargetEdit(util
				.execute(helperSQL::createKey_keyValuesTable)
				.andThen(helperSQL::createList_start_inverse_Node_startOfTable)
				.andThen(helperSQL::changeDataNodeTable)
				.andThen(helperSQL::changeListTable));
		//------------	
		util.assertPostcondition("CompositeListDataEcore", "CompositeListDataSQL");
	}
	
	/**
	 * <b>Test</b> for deleting columns and tables from the SQL schema.<br/>
	 * <b>Expect</b> : EPackage and SQL schema are structured as specified in the corresponding
	 * assertPostcondition statements.<br/>
	 * <b>Features</b>: bwd, del
	 */
	@Test
	public void testIncrementalDeletions() {
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
		tool.performIdleSourceEdit(helperEcore::createMethods);
		tool.performIdleSourceEdit(helperEcore::changeListLengthAttribute);
		
		util.assertPrecondition("CompositeListDataEcore", "CompositeListDataSQL"); 
		
		//------------		
		tool.performAndPropagateTargetEdit(util
				.execute(helperSQL::deleteListLengthColumn)
				.andThen(helperSQL::deleteDataNodeDataColumn)
				.andThen(helperSQL::deleteKeyKeyinverseColumn)
				.andThen(helperSQL::deleteValuePairinverseValueColumn));
		//------------
		util.assertPostcondition("CompositeListDataColumnDeletionEcore", "CompositeListDataColumnDeletionSQL");
		//------------		
		tool.performAndPropagateTargetEdit(util
				.execute(helperSQL::deleteKey_keyValuesTable)
				.andThen(helperSQL::deleteList_start_inverse_Node_startOfTable));
		//------------
		util.assertPostcondition("CompositeListDataColumnATableDeletionEcore", "CompositeListDataColumnATableDeletionSQL");
		//------------		
		tool.performAndPropagateTargetEdit(util
				.execute(helperSQL::deleteDataElementTable)
				.andThen(helperSQL::deleteValueTable)
				.andThen(helperSQL::deletePairTable)
				.andThen(helperSQL::deleteKeyTable));
		tool.performIdleSourceEdit(helperEcore::changeBackListAddParameter);
		//------------
		util.assertPostcondition("CompositeListDataColumnTablesDeletionEcore", "CompositeListDataColumnTablesDeletionSQL");
	}
	
	/**
	 * <b>Test</b> for renaming tables and columns in a SQL schema after the initial
	 * schema has been transformed into an EPackage.<br/>
	 * <b>Expect</b> : Model states as described in the postcondition.<br/>
	 * <b>Features</b>: bwd, attribute, structural, corr-based, runtime
	 */
	@Test
	public void testIncrementalRenaming() {
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
		tool.performIdleSourceEdit(helperEcore::createMethods);
		tool.performIdleSourceEdit(helperEcore::changeListLengthAttribute);
		
		util.assertPrecondition("CompositeListDataEcore", "CompositeListDataSQL");
		
		//----------------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperSQL::renameSchema)
				.andThen(helperSQL::renameListTable)
				.andThen(helperSQL::renameDataNodeDataColumn)
				.andThen(helperSQL::renameKey_keyValuesTable)
				.andThen(helperSQL::addAnnotationToDataNode));
		//----------------------
		util.assertPostcondition("CompositeListDataAfterRenameBWDEcore", "CompositeListDataAfterRenameBWDWithDataAnnotationSQL");
		
	}
	
	/**
	 * <b>Test</b> for deleting and recreating a Person in a PersonRegister after the initial
	 * register has been transformed into a family model.<br/>
	 * <b>Expect</b> : Model states as described in the postcondition.<br/>
	 * <b>Features</b>: bwd, del+add, structural, runtime
	 */
	@Test
	public void testIncrementalMixed() {
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
		tool.performIdleSourceEdit(helperEcore::createMethods);
		tool.performIdleSourceEdit(helperEcore::changeListLengthAttribute);
		
		util.assertPrecondition("CompositeListDataEcore", "CompositeListDataSQL");

		//------------
		tool.performAndPropagateTargetEdit(util
				.execute(helperSQL::changePair_KeyValueReferences)
				.andThen(helperSQL::changeDataNodeData)
				.andThen(helperSQL::changeListLength)
				.andThen(helperSQL::changeForeignKeyPair_Key));
		//------------
		util.assertPostcondition("CompositeListDataAfterMixedEcore", "CompositeListDataAfterMixedSQL");
	}
	
	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> Nothing should be changed after an idle target delta.<br/>
	 * <b>Features</b>: bwd, runtime
	 */
	@Test
	public void testStability() {
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
		tool.performIdleSourceEdit(helperEcore::createMethods);
		tool.performIdleSourceEdit(helperEcore::changeListLengthAttribute);
		
		util.assertPrecondition("CompositeListDataEcore", "CompositeListDataSQL"); 
		//------------
		tool.performAndPropagateTargetEdit(helperSQL::idleDelta);
		//------------
		util.assertPostcondition("CompositeListDataEcore", "CompositeListDataSQL");
	}
	
	
	/**
	 * <b>Test</b> for hippocraticness of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after creating annotations does not change the EPackage.<br/>
	 * <b>Features:</b>: bwd, fixed
	 */
	@Test
	public void testHippocraticness() {
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
		tool.performIdleSourceEdit(helperEcore::createMethods);
		tool.performIdleSourceEdit(helperEcore::changeListLengthAttribute);
		
		util.assertPrecondition("CompositeListDataEcore", "CompositeListDataSQL"); 
		//------------
		tool.performAndPropagateTargetEdit(helperSQL::hippocraticDelta);
		//------------
		util.assertPostcondition("CompositeListDataEcore", "CompositeListDataWithDataAnnotationsSQL");
	}

}
