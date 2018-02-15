/**
 */
package bags1;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see bags1.Bags1Factory
 * @model kind="package"
 * @generated
 */
public interface Bags1Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bags1";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://de.ubt.ai1.bw.qvt.examples.bags1.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "de.ubt.ai1.m2m";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Bags1Package eINSTANCE = bags1.impl.Bags1PackageImpl.init();

	/**
	 * The meta object id for the '{@link bags1.impl.MyBagImpl <em>My Bag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bags1.impl.MyBagImpl
	 * @see bags1.impl.Bags1PackageImpl#getMyBag()
	 * @generated
	 */
	int MY_BAG = 0;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_BAG__ELEMENTS = 0;

	/**
	 * The number of structural features of the '<em>My Bag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_BAG_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>My Bag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_BAG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link bags1.impl.ElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bags1.impl.ElementImpl
	 * @see bags1.impl.Bags1PackageImpl#getElement()
	 * @generated
	 */
	int ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Bag</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__BAG = 1;

	/**
	 * The feature id for the '<em><b>Incremental ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__INCREMENTAL_ID = 2;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link bags1.MyBag <em>My Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>My Bag</em>'.
	 * @see bags1.MyBag
	 * @generated
	 */
	EClass getMyBag();

	/**
	 * Returns the meta object for the containment reference list '{@link bags1.MyBag#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see bags1.MyBag#getElements()
	 * @see #getMyBag()
	 * @generated
	 */
	EReference getMyBag_Elements();

	/**
	 * Returns the meta object for class '{@link bags1.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see bags1.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for the attribute '{@link bags1.Element#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see bags1.Element#getValue()
	 * @see #getElement()
	 * @generated
	 */
	EAttribute getElement_Value();

	/**
	 * Returns the meta object for the container reference '{@link bags1.Element#getBag <em>Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Bag</em>'.
	 * @see bags1.Element#getBag()
	 * @see #getElement()
	 * @generated
	 */
	EReference getElement_Bag();

	/**
	 * Returns the meta object for the attribute '{@link bags1.Element#getIncrementalID <em>Incremental ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Incremental ID</em>'.
	 * @see bags1.Element#getIncrementalID()
	 * @see #getElement()
	 * @generated
	 */
	EAttribute getElement_IncrementalID();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Bags1Factory getBags1Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link bags1.impl.MyBagImpl <em>My Bag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bags1.impl.MyBagImpl
		 * @see bags1.impl.Bags1PackageImpl#getMyBag()
		 * @generated
		 */
		EClass MY_BAG = eINSTANCE.getMyBag();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MY_BAG__ELEMENTS = eINSTANCE.getMyBag_Elements();

		/**
		 * The meta object literal for the '{@link bags1.impl.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bags1.impl.ElementImpl
		 * @see bags1.impl.Bags1PackageImpl#getElement()
		 * @generated
		 */
		EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT__VALUE = eINSTANCE.getElement_Value();

		/**
		 * The meta object literal for the '<em><b>Bag</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT__BAG = eINSTANCE.getElement_Bag();

		/**
		 * The meta object literal for the '<em><b>Incremental ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT__INCREMENTAL_ID = eINSTANCE.getElement_IncrementalID();

	}

} //Bags1Package
