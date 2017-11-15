/**
 */
package sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link sql.Event#getCondition <em>Condition</em>}</li>
 *   <li>{@link sql.Event#getAction <em>Action</em>}</li>
 *   <li>{@link sql.Event#getOwningForeignKey <em>Owning Foreign Key</em>}</li>
 * </ul>
 *
 * @see sql.SqlPackage#getEvent()
 * @model
 * @generated
 */
public interface Event extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * The literals are from the enumeration {@link sql.Condition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see sql.Condition
	 * @see #setCondition(Condition)
	 * @see sql.SqlPackage#getEvent_Condition()
	 * @model required="true"
	 * @generated
	 */
	Condition getCondition();

	/**
	 * Sets the value of the '{@link sql.Event#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see sql.Condition
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Condition value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * The literals are from the enumeration {@link sql.Action}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see sql.Action
	 * @see #setAction(Action)
	 * @see sql.SqlPackage#getEvent_Action()
	 * @model required="true"
	 * @generated
	 */
	Action getAction();

	/**
	 * Sets the value of the '{@link sql.Event#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see sql.Action
	 * @see #getAction()
	 * @generated
	 */
	void setAction(Action value);

	/**
	 * Returns the value of the '<em><b>Owning Foreign Key</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link sql.ForeignKey#getOwnedEvents <em>Owned Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Foreign Key</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Foreign Key</em>' container reference.
	 * @see #setOwningForeignKey(ForeignKey)
	 * @see sql.SqlPackage#getEvent_OwningForeignKey()
	 * @see sql.ForeignKey#getOwnedEvents
	 * @model opposite="ownedEvents" required="true" transient="false"
	 * @generated
	 */
	ForeignKey getOwningForeignKey();

	/**
	 * Sets the value of the '{@link sql.Event#getOwningForeignKey <em>Owning Foreign Key</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Foreign Key</em>' container reference.
	 * @see #getOwningForeignKey()
	 * @generated
	 */
	void setOwningForeignKey(ForeignKey value);

} // Event
