package org.benchmarx.examples.ecore2sql.testsuite.alignment_based.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.ecore2sql.testsuite.Decisions;
import org.benchmarx.examples.ecore2sql.testsuite.EcoreToSQLTestCase;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;

import sql.Schema;

public class IncrementalForward extends EcoreToSQLTestCase {
	
	public IncrementalForward(BXTool<EPackage, Schema, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for inserting a set of new classes and changing a attribute to a reference.
	 * Added one additional Annotation in the target model. <br/>
	 * <b>Expect</b> : New tables are added to the schema, while the old tables
	 * remain nearly unchanged. <br/>
	 * <b>Features</b>: fwd, add, fixed
	 */
	@Test
	public void testIncrementalInserts() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::createSimpleCompositeList)
				.andThen(helperEcore::changePackageName));
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNodeData);
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNode);
		
		util.assertPrecondition("CompositeListSimpleEcore", "CompositeListSimpleWithDataAnnotationSQL");

		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::addDataElementFeature)
				.andThen(helperEcore::changeListAddParameter));
		//------------
		util.assertPostcondition("CompositeListDataEcore", "CompositeListDataWithDataAnnotationSQL");
	}
	
	/**
	 * <b>Test</b> for deleting the data element structure.
	 * <b>Expect</b>: Delete the correct tables in the sql schema
	 * <b>Features</b>: fwd, del, corr-based, structural
	 */
	@Test
	public void testIncrementalDeletions() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::changePackageName)
				.andThen(helperEcore::createSimpleCompositeList)
				.andThen(helperEcore::addDataElementFeature)
				.andThen(helperEcore::changeListAddParameter));
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNode);
		
		util.assertPrecondition("CompositeListDataEcore", "CompositeListDataWithDataAnnotationSQL");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::deleteListLengthAttribute)
				.andThen(helperEcore::deleteKeyKeyValuesAttribute));
		//------------
		util.assertPostcondition("CompositeListDataAttributeDeletionEcore", "CompositeListDataAttributeDeletionWithDataAnnotationSQL");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::deleteNodeStartOfReference)
				.andThen(helperEcore::deletePairReferences)
				.andThen(helperEcore::deleteDataNodeDataReference));
		//------------
		util.assertPostcondition("CompositeListDataAttributeReferenceDeletionEcore", "CompositeListDataAttributeReferenceDeletionWithDataAnnotationSQL");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::deleteDataElementFeature)
				.andThen(helperEcore::changeBackListAddParameter));
		//------------
		util.assertPostcondition("CompositeListSimple-DataEcore", "CompositeListSimple-DataWithDataAnnotationSQL");
				
	}
	
	/**
	 * <b>Test</b> for renaming class, package, attribute and a reference.
	 * <b>Expect</b>: Change the name of the affected tables and columns in the SQL schema
	 * <b>Features</b>: fwd, attribute, fixed, structural, corr-based
	 */
	@Test
	public void testIncrementalRename() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::changePackageName)
				.andThen(helperEcore::createSimpleCompositeList)
				.andThen(helperEcore::addDataElementFeature)
				.andThen(helperEcore::changeListAddParameter));
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNode);
		
		util.assertPrecondition("CompositeListDataEcore", "CompositeListDataWithDataAnnotationSQL");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::renameListClass)
				.andThen(helperEcore::renamePackage)
				.andThen(helperEcore::renameDataNodeDataReference)
				.andThen(helperEcore::renameValuesAttribute));
		//------------
		util.assertPostcondition("CompositeListDataAfterRenameEcore", "CompositeListDataAfterRenameWithDataAnnotationSQL");
	}
	
	/**
	 * <b>Test</b> for moving generalizations, attributes and containment references to different classes and also changing their names. 
	 * <b>Expect</b>: Changes should be propagated to the SQL Schema
	 * <b>Features</b>: fwd, del+add, fixed, structural
	 */
	@Test
	public void testIncrementalMove() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::changePackageName)
				.andThen(helperEcore::createSimpleCompositeList)
				.andThen(helperEcore::addDataElementFeature)
				.andThen(helperEcore::changeListAddParameter));
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNode);
		
		util.assertPrecondition("CompositeListDataEcore", "CompositeListDataWithDataAnnotationSQL");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::changeGeneralizationDataElement)
				.andThen(helperEcore::moveReferencePair)
				.andThen(helperEcore::moveAttributeLengthAndRename));
		//------------
		util.assertPostcondition("CompositeListDataAfterMoveEcore", "CompositeListDataAfterMoveWithDataAnnotationSQL");
	}
	
	/**
	 * <b>Test</b> for deleting and re-creating an attribute and a class.
	 * After creating the SQL schema, set some other annotations. Then delete and re-create an attribute and a class with an additional annotation.
	 * <b>Expect</b>: SQL schema remains unchanged, except for the attribute and the class, who
	 * should be re-created without the additional annotation.
	 * <b>Features</b>: fwd, structural, add+del, fixed 
	 */
	@Test
	public void testIncrementalMixed() {
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::createSimpleCompositeList)
				.andThen(helperEcore::changePackageName));
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNodeData);
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNode);
		
		util.assertPrecondition("CompositeListSimpleEcore", "CompositeListSimpleWithDataAnnotationSQL");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::deleteDataAttribute)
				.andThen(helperEcore::createDataAttribute));
		//------------
		util.assertPostcondition("CompositeListSimpleEcore", "CompositeListSimpleWithDataNodeAnnotationSQL");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::deleteDataNode)
				.andThen(helperEcore::createDataNode));
		//------------
		util.assertPostcondition("CompositeListSimpleEcore", "CompositeListSimpleSQL");
	}
	
	/**
	 * <b>Test</b> for stability of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after an idle source delta does not change the target model.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testStability() {
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::createSimpleCompositeList)
				.andThen(helperEcore::changePackageName));
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNodeData);
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNode);
		
		util.assertPrecondition("CompositeListSimpleEcore", "CompositeListSimpleWithDataAnnotationSQL");
		//------------
		tool.performAndPropagateSourceEdit(helperEcore::idleDelta);
		//------------
		util.assertPostcondition("CompositeListSimpleEcore", "CompositeListSimpleWithDataAnnotationSQL");
	}
	
	/**
	 * <b>Test</b> for hippocraticness of the transformation.<br/>
	 * <b>Expect</b> re-running the transformation after creating a operation, deleting a operation and changing some EAttribute values
	 * does not change the SQL schema.<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testHippocraticness() {
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperEcore::createSimpleCompositeList)
				.andThen(helperEcore::changePackageName));
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNodeData);
		tool.performIdleTargetEdit(helperSQL::addAnnotationToDataNode);
				
		util.assertPrecondition("CompositeListSimpleEcore", "CompositeListSimpleWithDataAnnotationSQL");
		//------------
		tool.performAndPropagateSourceEdit(helperEcore::hippocraticDelta);
		//------------
		util.assertPostcondition("CompositeListSimpleHippocraticEcore", "CompositeListSimpleWithDataAnnotationSQL");
	}

}
