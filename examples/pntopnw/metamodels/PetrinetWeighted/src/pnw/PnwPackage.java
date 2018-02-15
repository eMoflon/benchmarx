/**
 */
package pnw;

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
 * @see pnw.PnwFactory
 * @model kind="package"
 * @generated
 */
public interface PnwPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pnw";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://de.ubt.ai1.bw.qvt.examples.pnw.ecore";

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
	PnwPackage eINSTANCE = pnw.impl.PnwPackageImpl.init();

	/**
	 * The meta object id for the '{@link pnw.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pnw.impl.NamedElementImpl
	 * @see pnw.impl.PnwPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link pnw.impl.NetImpl <em>Net</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pnw.impl.NetImpl
	 * @see pnw.impl.PnwPackageImpl#getNet()
	 * @generated
	 */
	int NET = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NET__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NET__ELEMENTS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incremental ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NET__INCREMENTAL_ID = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Net</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NET_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Net</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NET_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link pnw.impl.NetElementImpl <em>Net Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pnw.impl.NetElementImpl
	 * @see pnw.impl.PnwPackageImpl#getNetElement()
	 * @generated
	 */
	int NET_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Net</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NET_ELEMENT__NET = 0;

	/**
	 * The number of structural features of the '<em>Net Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NET_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Net Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NET_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link pnw.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pnw.impl.TransitionImpl
	 * @see pnw.impl.PnwPackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 3;

	/**
	 * The feature id for the '<em><b>Net</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NET = NET_ELEMENT__NET;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NAME = NET_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Out TP Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__OUT_TP_EDGES = NET_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>In PT Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__IN_PT_EDGES = NET_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = NET_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_OPERATION_COUNT = NET_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link pnw.impl.PlaceImpl <em>Place</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pnw.impl.PlaceImpl
	 * @see pnw.impl.PnwPackageImpl#getPlace()
	 * @generated
	 */
	int PLACE = 4;

	/**
	 * The feature id for the '<em><b>Net</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__NET = NET_ELEMENT__NET;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__NAME = NET_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>No Of Tokens</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__NO_OF_TOKENS = NET_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Out PT Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__OUT_PT_EDGES = NET_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>In TP Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__IN_TP_EDGES = NET_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Place</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE_FEATURE_COUNT = NET_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Place</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE_OPERATION_COUNT = NET_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link pnw.impl.EdgeImpl <em>Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pnw.impl.EdgeImpl
	 * @see pnw.impl.PnwPackageImpl#getEdge()
	 * @generated
	 */
	int EDGE = 5;

	/**
	 * The feature id for the '<em><b>Net</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__NET = NET_ELEMENT__NET;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__WEIGHT = NET_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_FEATURE_COUNT = NET_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_OPERATION_COUNT = NET_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link pnw.impl.TPEdgeImpl <em>TP Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pnw.impl.TPEdgeImpl
	 * @see pnw.impl.PnwPackageImpl#getTPEdge()
	 * @generated
	 */
	int TP_EDGE = 6;

	/**
	 * The feature id for the '<em><b>Net</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TP_EDGE__NET = EDGE__NET;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TP_EDGE__WEIGHT = EDGE__WEIGHT;

	/**
	 * The feature id for the '<em><b>From Transition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TP_EDGE__FROM_TRANSITION = EDGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To Place</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TP_EDGE__TO_PLACE = EDGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TP Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TP_EDGE_FEATURE_COUNT = EDGE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>TP Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TP_EDGE_OPERATION_COUNT = EDGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link pnw.impl.PTEdgeImpl <em>PT Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see pnw.impl.PTEdgeImpl
	 * @see pnw.impl.PnwPackageImpl#getPTEdge()
	 * @generated
	 */
	int PT_EDGE = 7;

	/**
	 * The feature id for the '<em><b>Net</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_EDGE__NET = EDGE__NET;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_EDGE__WEIGHT = EDGE__WEIGHT;

	/**
	 * The feature id for the '<em><b>From Place</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_EDGE__FROM_PLACE = EDGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To Transition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_EDGE__TO_TRANSITION = EDGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>PT Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_EDGE_FEATURE_COUNT = EDGE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>PT Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PT_EDGE_OPERATION_COUNT = EDGE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link pnw.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see pnw.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link pnw.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see pnw.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link pnw.Net <em>Net</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Net</em>'.
	 * @see pnw.Net
	 * @generated
	 */
	EClass getNet();

	/**
	 * Returns the meta object for the containment reference list '{@link pnw.Net#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see pnw.Net#getElements()
	 * @see #getNet()
	 * @generated
	 */
	EReference getNet_Elements();

	/**
	 * Returns the meta object for the attribute '{@link pnw.Net#getIncrementalID <em>Incremental ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Incremental ID</em>'.
	 * @see pnw.Net#getIncrementalID()
	 * @see #getNet()
	 * @generated
	 */
	EAttribute getNet_IncrementalID();

	/**
	 * Returns the meta object for class '{@link pnw.NetElement <em>Net Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Net Element</em>'.
	 * @see pnw.NetElement
	 * @generated
	 */
	EClass getNetElement();

	/**
	 * Returns the meta object for the container reference '{@link pnw.NetElement#getNet <em>Net</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Net</em>'.
	 * @see pnw.NetElement#getNet()
	 * @see #getNetElement()
	 * @generated
	 */
	EReference getNetElement_Net();

	/**
	 * Returns the meta object for class '{@link pnw.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see pnw.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the reference list '{@link pnw.Transition#getOutTPEdges <em>Out TP Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out TP Edges</em>'.
	 * @see pnw.Transition#getOutTPEdges()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_OutTPEdges();

	/**
	 * Returns the meta object for the reference list '{@link pnw.Transition#getInPTEdges <em>In PT Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>In PT Edges</em>'.
	 * @see pnw.Transition#getInPTEdges()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_InPTEdges();

	/**
	 * Returns the meta object for class '{@link pnw.Place <em>Place</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Place</em>'.
	 * @see pnw.Place
	 * @generated
	 */
	EClass getPlace();

	/**
	 * Returns the meta object for the attribute '{@link pnw.Place#getNoOfTokens <em>No Of Tokens</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>No Of Tokens</em>'.
	 * @see pnw.Place#getNoOfTokens()
	 * @see #getPlace()
	 * @generated
	 */
	EAttribute getPlace_NoOfTokens();

	/**
	 * Returns the meta object for the reference list '{@link pnw.Place#getOutPTEdges <em>Out PT Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out PT Edges</em>'.
	 * @see pnw.Place#getOutPTEdges()
	 * @see #getPlace()
	 * @generated
	 */
	EReference getPlace_OutPTEdges();

	/**
	 * Returns the meta object for the reference list '{@link pnw.Place#getInTPEdges <em>In TP Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>In TP Edges</em>'.
	 * @see pnw.Place#getInTPEdges()
	 * @see #getPlace()
	 * @generated
	 */
	EReference getPlace_InTPEdges();

	/**
	 * Returns the meta object for class '{@link pnw.Edge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge</em>'.
	 * @see pnw.Edge
	 * @generated
	 */
	EClass getEdge();

	/**
	 * Returns the meta object for the attribute '{@link pnw.Edge#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see pnw.Edge#getWeight()
	 * @see #getEdge()
	 * @generated
	 */
	EAttribute getEdge_Weight();

	/**
	 * Returns the meta object for class '{@link pnw.TPEdge <em>TP Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TP Edge</em>'.
	 * @see pnw.TPEdge
	 * @generated
	 */
	EClass getTPEdge();

	/**
	 * Returns the meta object for the reference '{@link pnw.TPEdge#getFromTransition <em>From Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From Transition</em>'.
	 * @see pnw.TPEdge#getFromTransition()
	 * @see #getTPEdge()
	 * @generated
	 */
	EReference getTPEdge_FromTransition();

	/**
	 * Returns the meta object for the reference '{@link pnw.TPEdge#getToPlace <em>To Place</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To Place</em>'.
	 * @see pnw.TPEdge#getToPlace()
	 * @see #getTPEdge()
	 * @generated
	 */
	EReference getTPEdge_ToPlace();

	/**
	 * Returns the meta object for class '{@link pnw.PTEdge <em>PT Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PT Edge</em>'.
	 * @see pnw.PTEdge
	 * @generated
	 */
	EClass getPTEdge();

	/**
	 * Returns the meta object for the reference '{@link pnw.PTEdge#getFromPlace <em>From Place</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From Place</em>'.
	 * @see pnw.PTEdge#getFromPlace()
	 * @see #getPTEdge()
	 * @generated
	 */
	EReference getPTEdge_FromPlace();

	/**
	 * Returns the meta object for the reference '{@link pnw.PTEdge#getToTransition <em>To Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To Transition</em>'.
	 * @see pnw.PTEdge#getToTransition()
	 * @see #getPTEdge()
	 * @generated
	 */
	EReference getPTEdge_ToTransition();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PnwFactory getPnwFactory();

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
		 * The meta object literal for the '{@link pnw.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pnw.impl.NamedElementImpl
		 * @see pnw.impl.PnwPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link pnw.impl.NetImpl <em>Net</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pnw.impl.NetImpl
		 * @see pnw.impl.PnwPackageImpl#getNet()
		 * @generated
		 */
		EClass NET = eINSTANCE.getNet();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NET__ELEMENTS = eINSTANCE.getNet_Elements();

		/**
		 * The meta object literal for the '<em><b>Incremental ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NET__INCREMENTAL_ID = eINSTANCE.getNet_IncrementalID();

		/**
		 * The meta object literal for the '{@link pnw.impl.NetElementImpl <em>Net Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pnw.impl.NetElementImpl
		 * @see pnw.impl.PnwPackageImpl#getNetElement()
		 * @generated
		 */
		EClass NET_ELEMENT = eINSTANCE.getNetElement();

		/**
		 * The meta object literal for the '<em><b>Net</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NET_ELEMENT__NET = eINSTANCE.getNetElement_Net();

		/**
		 * The meta object literal for the '{@link pnw.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pnw.impl.TransitionImpl
		 * @see pnw.impl.PnwPackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Out TP Edges</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__OUT_TP_EDGES = eINSTANCE.getTransition_OutTPEdges();

		/**
		 * The meta object literal for the '<em><b>In PT Edges</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__IN_PT_EDGES = eINSTANCE.getTransition_InPTEdges();

		/**
		 * The meta object literal for the '{@link pnw.impl.PlaceImpl <em>Place</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pnw.impl.PlaceImpl
		 * @see pnw.impl.PnwPackageImpl#getPlace()
		 * @generated
		 */
		EClass PLACE = eINSTANCE.getPlace();

		/**
		 * The meta object literal for the '<em><b>No Of Tokens</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLACE__NO_OF_TOKENS = eINSTANCE.getPlace_NoOfTokens();

		/**
		 * The meta object literal for the '<em><b>Out PT Edges</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLACE__OUT_PT_EDGES = eINSTANCE.getPlace_OutPTEdges();

		/**
		 * The meta object literal for the '<em><b>In TP Edges</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLACE__IN_TP_EDGES = eINSTANCE.getPlace_InTPEdges();

		/**
		 * The meta object literal for the '{@link pnw.impl.EdgeImpl <em>Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pnw.impl.EdgeImpl
		 * @see pnw.impl.PnwPackageImpl#getEdge()
		 * @generated
		 */
		EClass EDGE = eINSTANCE.getEdge();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDGE__WEIGHT = eINSTANCE.getEdge_Weight();

		/**
		 * The meta object literal for the '{@link pnw.impl.TPEdgeImpl <em>TP Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pnw.impl.TPEdgeImpl
		 * @see pnw.impl.PnwPackageImpl#getTPEdge()
		 * @generated
		 */
		EClass TP_EDGE = eINSTANCE.getTPEdge();

		/**
		 * The meta object literal for the '<em><b>From Transition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TP_EDGE__FROM_TRANSITION = eINSTANCE.getTPEdge_FromTransition();

		/**
		 * The meta object literal for the '<em><b>To Place</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TP_EDGE__TO_PLACE = eINSTANCE.getTPEdge_ToPlace();

		/**
		 * The meta object literal for the '{@link pnw.impl.PTEdgeImpl <em>PT Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see pnw.impl.PTEdgeImpl
		 * @see pnw.impl.PnwPackageImpl#getPTEdge()
		 * @generated
		 */
		EClass PT_EDGE = eINSTANCE.getPTEdge();

		/**
		 * The meta object literal for the '<em><b>From Place</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PT_EDGE__FROM_PLACE = eINSTANCE.getPTEdge_FromPlace();

		/**
		 * The meta object literal for the '<em><b>To Transition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PT_EDGE__TO_TRANSITION = eINSTANCE.getPTEdge_ToTransition();

	}

} //PnwPackage
