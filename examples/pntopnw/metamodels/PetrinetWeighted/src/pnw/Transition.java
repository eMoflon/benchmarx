/**
 */
package pnw;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pnw.Transition#getOutTPEdges <em>Out TP Edges</em>}</li>
 *   <li>{@link pnw.Transition#getInPTEdges <em>In PT Edges</em>}</li>
 * </ul>
 *
 * @see pnw.PnwPackage#getTransition()
 * @model
 * @generated
 */
public interface Transition extends NetElement, NamedElement {
	/**
	 * Returns the value of the '<em><b>Out TP Edges</b></em>' reference list.
	 * The list contents are of type {@link pnw.TPEdge}.
	 * It is bidirectional and its opposite is '{@link pnw.TPEdge#getFromTransition <em>From Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out TP Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out TP Edges</em>' reference list.
	 * @see pnw.PnwPackage#getTransition_OutTPEdges()
	 * @see pnw.TPEdge#getFromTransition
	 * @model opposite="fromTransition"
	 * @generated
	 */
	EList<TPEdge> getOutTPEdges();

	/**
	 * Returns the value of the '<em><b>In PT Edges</b></em>' reference list.
	 * The list contents are of type {@link pnw.PTEdge}.
	 * It is bidirectional and its opposite is '{@link pnw.PTEdge#getToTransition <em>To Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In PT Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In PT Edges</em>' reference list.
	 * @see pnw.PnwPackage#getTransition_InPTEdges()
	 * @see pnw.PTEdge#getToTransition
	 * @model opposite="toTransition"
	 * @generated
	 */
	EList<PTEdge> getInPTEdges();

} // Transition
