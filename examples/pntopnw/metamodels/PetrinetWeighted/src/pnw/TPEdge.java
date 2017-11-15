/**
 */
package pnw;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TP Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pnw.TPEdge#getFromTransition <em>From Transition</em>}</li>
 *   <li>{@link pnw.TPEdge#getToPlace <em>To Place</em>}</li>
 * </ul>
 *
 * @see pnw.PnwPackage#getTPEdge()
 * @model
 * @generated
 */
public interface TPEdge extends Edge {
	/**
	 * Returns the value of the '<em><b>From Transition</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link pnw.Transition#getOutTPEdges <em>Out TP Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Transition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Transition</em>' reference.
	 * @see #setFromTransition(Transition)
	 * @see pnw.PnwPackage#getTPEdge_FromTransition()
	 * @see pnw.Transition#getOutTPEdges
	 * @model opposite="outTPEdges" required="true"
	 * @generated
	 */
	Transition getFromTransition();

	/**
	 * Sets the value of the '{@link pnw.TPEdge#getFromTransition <em>From Transition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Transition</em>' reference.
	 * @see #getFromTransition()
	 * @generated
	 */
	void setFromTransition(Transition value);

	/**
	 * Returns the value of the '<em><b>To Place</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link pnw.Place#getInTPEdges <em>In TP Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Place</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Place</em>' reference.
	 * @see #setToPlace(Place)
	 * @see pnw.PnwPackage#getTPEdge_ToPlace()
	 * @see pnw.Place#getInTPEdges
	 * @model opposite="inTPEdges" required="true"
	 * @generated
	 */
	Place getToPlace();

	/**
	 * Sets the value of the '{@link pnw.TPEdge#getToPlace <em>To Place</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Place</em>' reference.
	 * @see #getToPlace()
	 * @generated
	 */
	void setToPlace(Place value);

} // TPEdge
