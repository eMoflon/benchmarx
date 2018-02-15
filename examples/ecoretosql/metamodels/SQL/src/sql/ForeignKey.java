/**
 */
package sql;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link sql.ForeignKey#getReferencedTable <em>Referenced Table</em>}</li>
 *   <li>{@link sql.ForeignKey#getOwningTable <em>Owning Table</em>}</li>
 *   <li>{@link sql.ForeignKey#getOwnedEvents <em>Owned Events</em>}</li>
 * </ul>
 *
 * @see sql.SqlPackage#getForeignKey()
 * @model
 * @generated
 */
public interface ForeignKey extends Key {
	/**
	 * Returns the value of the '<em><b>Referenced Table</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link sql.Table#getReferencingForeignKeys <em>Referencing Foreign Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Table</em>' reference.
	 * @see #setReferencedTable(Table)
	 * @see sql.SqlPackage#getForeignKey_ReferencedTable()
	 * @see sql.Table#getReferencingForeignKeys
	 * @model opposite="referencingForeignKeys" required="true"
	 * @generated
	 */
	Table getReferencedTable();

	/**
	 * Sets the value of the '{@link sql.ForeignKey#getReferencedTable <em>Referenced Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Table</em>' reference.
	 * @see #getReferencedTable()
	 * @generated
	 */
	void setReferencedTable(Table value);

	/**
	 * Returns the value of the '<em><b>Owning Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link sql.Table#getOwnedForeignKeys <em>Owned Foreign Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Table</em>' container reference.
	 * @see #setOwningTable(Table)
	 * @see sql.SqlPackage#getForeignKey_OwningTable()
	 * @see sql.Table#getOwnedForeignKeys
	 * @model opposite="ownedForeignKeys" required="true" transient="false"
	 * @generated
	 */
	Table getOwningTable();

	/**
	 * Sets the value of the '{@link sql.ForeignKey#getOwningTable <em>Owning Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Table</em>' container reference.
	 * @see #getOwningTable()
	 * @generated
	 */
	void setOwningTable(Table value);

	/**
	 * Returns the value of the '<em><b>Owned Events</b></em>' containment reference list.
	 * The list contents are of type {@link sql.Event}.
	 * It is bidirectional and its opposite is '{@link sql.Event#getOwningForeignKey <em>Owning Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Events</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Events</em>' containment reference list.
	 * @see sql.SqlPackage#getForeignKey_OwnedEvents()
	 * @see sql.Event#getOwningForeignKey
	 * @model opposite="owningForeignKey" containment="true"
	 * @generated
	 */
	EList<Event> getOwnedEvents();

} // ForeignKey
