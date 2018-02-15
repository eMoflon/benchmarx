/**
 */
package bags2;

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
 * @see bags2.Bags2Factory
 * @model kind="package"
 * @generated
 */
public interface Bags2Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bags2";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://de.ubt.ai1.bw.qvt.examples.bags2.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "bags2";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Bags2Package eINSTANCE = bags2.impl.Bags2PackageImpl.init();

	/**
	 * The meta object id for the '{@link bags2.impl.MyBagImpl <em>My Bag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bags2.impl.MyBagImpl
	 * @see bags2.impl.Bags2PackageImpl#getMyBag()
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
	 * The meta object id for the '{@link bags2.impl.ElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bags2.impl.ElementImpl
	 * @see bags2.impl.Bags2PackageImpl#getElement()
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
	 * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__MULTIPLICITY = 1;

	/**
	 * The feature id for the '<em><b>Bag</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__BAG = 2;

	/**
	 * The feature id for the '<em><b>Incremental ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__INCREMENTAL_ID = 3;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link bags2.MyBag <em>My Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>My Bag</em>'.
	 * @see bags2.MyBag
	 * @generated
	 */
	EClass getMyBag();

	/**
	 * Returns the meta object for the containment reference list '{@link bags2.MyBag#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see bags2.MyBag#getElements()
	 * @see #getMyBag()
	 * @generated
	 */
	EReference getMyBag_Elements();

	/**
	 * Returns the meta object for class '{@link bags2.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see bags2.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for the attribute '{@link bags2.Element#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see bags2.Element#getValue()
	 * @see #getElement()
	 * @generated
	 */
	EAttribute getElement_Value();

	/**
	 * Returns the meta object for the attribute '{@link bags2.Element#getMultiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiplicity</em>'.
	 * @see bags2.Element#getMultiplicity()
	 * @see #getElement()
	 * @generated
	 */
	EAttribute getElement_Multiplicity();

	/**
	 * Returns the meta object for the container reference '{@link bags2.Element#getBag <em>Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Bag</em>'.
	 * @see bags2.Element#getBag()
	 * @see #getElement()
	 * @generated
	 */
	EReference getElement_Bag();

	/**
	 * Returns the meta object for the attribute '{@link bags2.Element#getIncrementalID <em>Incremental ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Incremental ID</em>'.
	 * @see bags2.Element#getIncrementalID()
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
	Bags2Factory getBags2Factory();

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
		 * The meta object literal for the '{@link bags2.impl.MyBagImpl <em>My Bag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bags2.impl.MyBagImpl
		 * @see bags2.impl.Bags2PackageImpl#getMyBag()
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
		 * The meta object literal for the '{@link bags2.impl.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bags2.impl.ElementImpl
		 * @see bags2.impl.Bags2PackageImpl#getElement()
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
		 * The meta object literal for the '<em><b>Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT__MULTIPLICITY = eINSTANCE.getElement_Multiplicity();

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

} //Bags2Package
