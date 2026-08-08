/**
 */
package pivot;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see pivot.PivotFactory
 * @model kind="package"
 * @generated
 */
public interface PivotPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pivot";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://pivot";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "pivot";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PivotPackage eINSTANCE = pivot.impl.PivotPackageImpl.init();

	/**
	 * The meta object id for the '{@link pivot.impl.PivotImpl <em>Pivot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pivot.impl.PivotImpl
	 * @see pivot.impl.PivotPackageImpl#getPivot()
	 * @generated
	 */
	int PIVOT = 0;

	/**
	 * The feature id for the '<em><b>Family Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT__FAMILY_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Person Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT__PERSON_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Pre</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT__PRE = 2;

	/**
	 * The feature id for the '<em><b>Post</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT__POST = 3;

	/**
	 * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT__MAPPING = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT__NAME = 5;

	/**
	 * The feature id for the '<em><b>Strategie</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT__STRATEGIE = 6;

	/**
	 * The feature id for the '<em><b>FAMILY TO NEW</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT__FAMILY_TO_NEW = 7;

	/**
	 * The feature id for the '<em><b>PARENT TO CHILD</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT__PARENT_TO_CHILD = 8;

	/**
	 * The feature id for the '<em><b>Sync</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT__SYNC = 9;

	/**
	 * The number of structural features of the '<em>Pivot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIVOT_FEATURE_COUNT = 10;


	/**
	 * The meta object id for the '{@link pivot.impl.PreConditionImpl <em>Pre Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pivot.impl.PreConditionImpl
	 * @see pivot.impl.PivotPackageImpl#getPreCondition()
	 * @generated
	 */
	int PRE_CONDITION = 1;

	/**
	 * The feature id for the '<em><b>Output</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_CONDITION__OUTPUT = 0;

	/**
	 * The feature id for the '<em><b>Input</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_CONDITION__INPUT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_CONDITION__NAME = 2;

	/**
	 * The number of structural features of the '<em>Pre Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_CONDITION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link pivot.impl.PostConditionImpl <em>Post Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pivot.impl.PostConditionImpl
	 * @see pivot.impl.PivotPackageImpl#getPostCondition()
	 * @generated
	 */
	int POST_CONDITION = 2;

	/**
	 * The feature id for the '<em><b>Output</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POST_CONDITION__OUTPUT = 0;

	/**
	 * The feature id for the '<em><b>Input</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POST_CONDITION__INPUT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POST_CONDITION__NAME = 2;

	/**
	 * The number of structural features of the '<em>Post Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POST_CONDITION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link pivot.impl.MappingImpl <em>Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pivot.impl.MappingImpl
	 * @see pivot.impl.PivotPackageImpl#getMapping()
	 * @generated
	 */
	int MAPPING = 3;

	/**
	 * The feature id for the '<em><b>MMap</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING__MMAP = 0;

	/**
	 * The feature id for the '<em><b>PMap</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING__PMAP = 1;

	/**
	 * The number of structural features of the '<em>Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link pivot.Strategy <em>Strategy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pivot.Strategy
	 * @see pivot.impl.PivotPackageImpl#getStrategy()
	 * @generated
	 */
	int STRATEGY = 4;


	/**
	 * Returns the meta object for class '{@link pivot.Pivot <em>Pivot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pivot</em>'.
	 * @see pivot.Pivot
	 * @generated
	 */
	EClass getPivot();

	/**
	 * Returns the meta object for the reference '{@link pivot.Pivot#getFamilyModel <em>Family Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Family Model</em>'.
	 * @see pivot.Pivot#getFamilyModel()
	 * @see #getPivot()
	 * @generated
	 */
	EReference getPivot_FamilyModel();

	/**
	 * Returns the meta object for the reference '{@link pivot.Pivot#getPersonModel <em>Person Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Person Model</em>'.
	 * @see pivot.Pivot#getPersonModel()
	 * @see #getPivot()
	 * @generated
	 */
	EReference getPivot_PersonModel();

	/**
	 * Returns the meta object for the containment reference list '{@link pivot.Pivot#getPre <em>Pre</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pre</em>'.
	 * @see pivot.Pivot#getPre()
	 * @see #getPivot()
	 * @generated
	 */
	EReference getPivot_Pre();

	/**
	 * Returns the meta object for the containment reference list '{@link pivot.Pivot#getPost <em>Post</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Post</em>'.
	 * @see pivot.Pivot#getPost()
	 * @see #getPivot()
	 * @generated
	 */
	EReference getPivot_Post();

	/**
	 * Returns the meta object for the containment reference list '{@link pivot.Pivot#getMapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mapping</em>'.
	 * @see pivot.Pivot#getMapping()
	 * @see #getPivot()
	 * @generated
	 */
	EReference getPivot_Mapping();

	/**
	 * Returns the meta object for the attribute '{@link pivot.Pivot#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see pivot.Pivot#getName()
	 * @see #getPivot()
	 * @generated
	 */
	EAttribute getPivot_Name();

	/**
	 * Returns the meta object for the attribute '{@link pivot.Pivot#getStrategie <em>Strategie</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Strategie</em>'.
	 * @see pivot.Pivot#getStrategie()
	 * @see #getPivot()
	 * @generated
	 */
	EAttribute getPivot_Strategie();

	/**
	 * Returns the meta object for the attribute '{@link pivot.Pivot#isFAMILY_TO_NEW <em>FAMILY TO NEW</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>FAMILY TO NEW</em>'.
	 * @see pivot.Pivot#isFAMILY_TO_NEW()
	 * @see #getPivot()
	 * @generated
	 */
	EAttribute getPivot_FAMILY_TO_NEW();

	/**
	 * Returns the meta object for the attribute '{@link pivot.Pivot#isPARENT_TO_CHILD <em>PARENT TO CHILD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PARENT TO CHILD</em>'.
	 * @see pivot.Pivot#isPARENT_TO_CHILD()
	 * @see #getPivot()
	 * @generated
	 */
	EAttribute getPivot_PARENT_TO_CHILD();

	/**
	 * Returns the meta object for the attribute '{@link pivot.Pivot#isSync <em>Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sync</em>'.
	 * @see pivot.Pivot#isSync()
	 * @see #getPivot()
	 * @generated
	 */
	EAttribute getPivot_Sync();

	/**
	 * Returns the meta object for class '{@link pivot.PreCondition <em>Pre Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pre Condition</em>'.
	 * @see pivot.PreCondition
	 * @generated
	 */
	EClass getPreCondition();

	/**
	 * Returns the meta object for the reference '{@link pivot.PreCondition#getInput <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Input</em>'.
	 * @see pivot.PreCondition#getInput()
	 * @see #getPreCondition()
	 * @generated
	 */
	EReference getPreCondition_Input();

	/**
	 * Returns the meta object for the reference '{@link pivot.PreCondition#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Output</em>'.
	 * @see pivot.PreCondition#getOutput()
	 * @see #getPreCondition()
	 * @generated
	 */
	EReference getPreCondition_Output();

	/**
	 * Returns the meta object for the attribute '{@link pivot.PreCondition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see pivot.PreCondition#getName()
	 * @see #getPreCondition()
	 * @generated
	 */
	EAttribute getPreCondition_Name();

	/**
	 * Returns the meta object for class '{@link pivot.PostCondition <em>Post Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Post Condition</em>'.
	 * @see pivot.PostCondition
	 * @generated
	 */
	EClass getPostCondition();

	/**
	 * Returns the meta object for the reference '{@link pivot.PostCondition#getInput <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Input</em>'.
	 * @see pivot.PostCondition#getInput()
	 * @see #getPostCondition()
	 * @generated
	 */
	EReference getPostCondition_Input();

	/**
	 * Returns the meta object for the reference '{@link pivot.PostCondition#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Output</em>'.
	 * @see pivot.PostCondition#getOutput()
	 * @see #getPostCondition()
	 * @generated
	 */
	EReference getPostCondition_Output();

	/**
	 * Returns the meta object for the attribute '{@link pivot.PostCondition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see pivot.PostCondition#getName()
	 * @see #getPostCondition()
	 * @generated
	 */
	EAttribute getPostCondition_Name();

	/**
	 * Returns the meta object for class '{@link pivot.Mapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mapping</em>'.
	 * @see pivot.Mapping
	 * @generated
	 */
	EClass getMapping();

	/**
	 * Returns the meta object for the reference '{@link pivot.Mapping#getMMap <em>MMap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>MMap</em>'.
	 * @see pivot.Mapping#getMMap()
	 * @see #getMapping()
	 * @generated
	 */
	EReference getMapping_MMap();

	/**
	 * Returns the meta object for the reference '{@link pivot.Mapping#getPMap <em>PMap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>PMap</em>'.
	 * @see pivot.Mapping#getPMap()
	 * @see #getMapping()
	 * @generated
	 */
	EReference getMapping_PMap();

	/**
	 * Returns the meta object for enum '{@link pivot.Strategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Strategy</em>'.
	 * @see pivot.Strategy
	 * @generated
	 */
	EEnum getStrategy();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PivotFactory getPivotFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link pivot.impl.PivotImpl <em>Pivot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pivot.impl.PivotImpl
		 * @see pivot.impl.PivotPackageImpl#getPivot()
		 * @generated
		 */
		EClass PIVOT = eINSTANCE.getPivot();

		/**
		 * The meta object literal for the '<em><b>Family Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIVOT__FAMILY_MODEL = eINSTANCE.getPivot_FamilyModel();

		/**
		 * The meta object literal for the '<em><b>Person Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIVOT__PERSON_MODEL = eINSTANCE.getPivot_PersonModel();

		/**
		 * The meta object literal for the '<em><b>Pre</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIVOT__PRE = eINSTANCE.getPivot_Pre();

		/**
		 * The meta object literal for the '<em><b>Post</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIVOT__POST = eINSTANCE.getPivot_Post();

		/**
		 * The meta object literal for the '<em><b>Mapping</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIVOT__MAPPING = eINSTANCE.getPivot_Mapping();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIVOT__NAME = eINSTANCE.getPivot_Name();

		/**
		 * The meta object literal for the '<em><b>Strategie</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIVOT__STRATEGIE = eINSTANCE.getPivot_Strategie();

		/**
		 * The meta object literal for the '<em><b>FAMILY TO NEW</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIVOT__FAMILY_TO_NEW = eINSTANCE.getPivot_FAMILY_TO_NEW();

		/**
		 * The meta object literal for the '<em><b>PARENT TO CHILD</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIVOT__PARENT_TO_CHILD = eINSTANCE.getPivot_PARENT_TO_CHILD();

		/**
		 * The meta object literal for the '<em><b>Sync</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIVOT__SYNC = eINSTANCE.getPivot_Sync();

		/**
		 * The meta object literal for the '{@link pivot.impl.PreConditionImpl <em>Pre Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pivot.impl.PreConditionImpl
		 * @see pivot.impl.PivotPackageImpl#getPreCondition()
		 * @generated
		 */
		EClass PRE_CONDITION = eINSTANCE.getPreCondition();

		/**
		 * The meta object literal for the '<em><b>Input</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRE_CONDITION__INPUT = eINSTANCE.getPreCondition_Input();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRE_CONDITION__OUTPUT = eINSTANCE.getPreCondition_Output();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRE_CONDITION__NAME = eINSTANCE.getPreCondition_Name();

		/**
		 * The meta object literal for the '{@link pivot.impl.PostConditionImpl <em>Post Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pivot.impl.PostConditionImpl
		 * @see pivot.impl.PivotPackageImpl#getPostCondition()
		 * @generated
		 */
		EClass POST_CONDITION = eINSTANCE.getPostCondition();

		/**
		 * The meta object literal for the '<em><b>Input</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POST_CONDITION__INPUT = eINSTANCE.getPostCondition_Input();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POST_CONDITION__OUTPUT = eINSTANCE.getPostCondition_Output();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POST_CONDITION__NAME = eINSTANCE.getPostCondition_Name();

		/**
		 * The meta object literal for the '{@link pivot.impl.MappingImpl <em>Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pivot.impl.MappingImpl
		 * @see pivot.impl.PivotPackageImpl#getMapping()
		 * @generated
		 */
		EClass MAPPING = eINSTANCE.getMapping();

		/**
		 * The meta object literal for the '<em><b>MMap</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAPPING__MMAP = eINSTANCE.getMapping_MMap();

		/**
		 * The meta object literal for the '<em><b>PMap</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAPPING__PMAP = eINSTANCE.getMapping_PMap();

		/**
		 * The meta object literal for the '{@link pivot.Strategy <em>Strategy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pivot.Strategy
		 * @see pivot.impl.PivotPackageImpl#getStrategy()
		 * @generated
		 */
		EEnum STRATEGY = eINSTANCE.getStrategy();

	}

} //PivotPackage
