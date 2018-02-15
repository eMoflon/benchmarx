/**
 */
package cpm;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link cpm.Event#getOutgoingActivities <em>Outgoing Activities</em>}</li>
 *   <li>{@link cpm.Event#getIncomingActivities <em>Incoming Activities</em>}</li>
 *   <li>{@link cpm.Event#getNumber <em>Number</em>}</li>
 * </ul>
 *
 * @see cpm.CpmPackage#getEvent()
 * @model
 * @generated
 */
public interface Event extends Element {
	/**
	 * Returns the value of the '<em><b>Outgoing Activities</b></em>' reference list.
	 * The list contents are of type {@link cpm.Activity}.
	 * It is bidirectional and its opposite is '{@link cpm.Activity#getSourceEvent <em>Source Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Activities</em>' reference list.
	 * @see cpm.CpmPackage#getEvent_OutgoingActivities()
	 * @see cpm.Activity#getSourceEvent
	 * @model opposite="sourceEvent"
	 * @generated
	 */
	EList<Activity> getOutgoingActivities();

	/**
	 * Returns the value of the '<em><b>Incoming Activities</b></em>' reference list.
	 * The list contents are of type {@link cpm.Activity}.
	 * It is bidirectional and its opposite is '{@link cpm.Activity#getTargetEvent <em>Target Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Activities</em>' reference list.
	 * @see cpm.CpmPackage#getEvent_IncomingActivities()
	 * @see cpm.Activity#getTargetEvent
	 * @model opposite="targetEvent"
	 * @generated
	 */
	EList<Activity> getIncomingActivities();

	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(int)
	 * @see cpm.CpmPackage#getEvent_Number()
	 * @model
	 * @generated
	 */
	int getNumber();

	/**
	 * Sets the value of the '{@link cpm.Event#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(int value);

} // Event
