/**
 */
package cpm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link cpm.Activity#getSourceEvent <em>Source Event</em>}</li>
 *   <li>{@link cpm.Activity#getTargetEvent <em>Target Event</em>}</li>
 *   <li>{@link cpm.Activity#getName <em>Name</em>}</li>
 *   <li>{@link cpm.Activity#getDuration <em>Duration</em>}</li>
 * </ul>
 *
 * @see cpm.CpmPackage#getActivity()
 * @model
 * @generated
 */
public interface Activity extends Element {
	/**
	 * Returns the value of the '<em><b>Source Event</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link cpm.Event#getOutgoingActivities <em>Outgoing Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Event</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Event</em>' reference.
	 * @see #setSourceEvent(Event)
	 * @see cpm.CpmPackage#getActivity_SourceEvent()
	 * @see cpm.Event#getOutgoingActivities
	 * @model opposite="outgoingActivities" required="true"
	 * @generated
	 */
	Event getSourceEvent();

	/**
	 * Sets the value of the '{@link cpm.Activity#getSourceEvent <em>Source Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Event</em>' reference.
	 * @see #getSourceEvent()
	 * @generated
	 */
	void setSourceEvent(Event value);

	/**
	 * Returns the value of the '<em><b>Target Event</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link cpm.Event#getIncomingActivities <em>Incoming Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Event</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Event</em>' reference.
	 * @see #setTargetEvent(Event)
	 * @see cpm.CpmPackage#getActivity_TargetEvent()
	 * @see cpm.Event#getIncomingActivities
	 * @model opposite="incomingActivities" required="true"
	 * @generated
	 */
	Event getTargetEvent();

	/**
	 * Sets the value of the '{@link cpm.Activity#getTargetEvent <em>Target Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Event</em>' reference.
	 * @see #getTargetEvent()
	 * @generated
	 */
	void setTargetEvent(Event value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see cpm.CpmPackage#getActivity_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link cpm.Activity#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(int)
	 * @see cpm.CpmPackage#getActivity_Duration()
	 * @model required="true"
	 * @generated
	 */
	int getDuration();

	/**
	 * Sets the value of the '{@link cpm.Activity#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(int value);

} // Activity
