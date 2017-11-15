/**
 */
package pnw;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PT Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pnw.PTEdge#getFromPlace <em>From Place</em>}</li>
 *   <li>{@link pnw.PTEdge#getToTransition <em>To Transition</em>}</li>
 * </ul>
 *
 * @see pnw.PnwPackage#getPTEdge()
 * @model
 * @generated
 */
public interface PTEdge extends Edge {
	/**
	 * Returns the value of the '<em><b>From Place</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link pnw.Place#getOutPTEdges <em>Out PT Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Place</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Place</em>' reference.
	 * @see #setFromPlace(Place)
	 * @see pnw.PnwPackage#getPTEdge_FromPlace()
	 * @see pnw.Place#getOutPTEdges
	 * @model opposite="outPTEdges" required="true"
	 * @generated
	 */
	Place getFromPlace();

	/**
	 * Sets the value of the '{@link pnw.PTEdge#getFromPlace <em>From Place</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Place</em>' reference.
	 * @see #getFromPlace()
	 * @generated
	 */
	void setFromPlace(Place value);

	/**
	 * Returns the value of the '<em><b>To Transition</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link pnw.Transition#getInPTEdges <em>In PT Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Transition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Transition</em>' reference.
	 * @see #setToTransition(Transition)
	 * @see pnw.PnwPackage#getPTEdge_ToTransition()
	 * @see pnw.Transition#getInPTEdges
	 * @model opposite="inPTEdges" required="true"
	 * @generated
	 */
	Transition getToTransition();

	/**
	 * Sets the value of the '{@link pnw.PTEdge#getToTransition <em>To Transition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Transition</em>' reference.
	 * @see #getToTransition()
	 * @generated
	 */
	void setToTransition(Transition value);

} // PTEdge
