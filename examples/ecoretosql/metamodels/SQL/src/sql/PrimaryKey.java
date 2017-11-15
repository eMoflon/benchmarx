/**
 */
package sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Primary Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link sql.PrimaryKey#getOwningTable <em>Owning Table</em>}</li>
 * </ul>
 *
 * @see sql.SqlPackage#getPrimaryKey()
 * @model
 * @generated
 */
public interface PrimaryKey extends Key {
	/**
	 * Returns the value of the '<em><b>Owning Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link sql.Table#getOwnedPrimaryKey <em>Owned Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Table</em>' container reference.
	 * @see #setOwningTable(Table)
	 * @see sql.SqlPackage#getPrimaryKey_OwningTable()
	 * @see sql.Table#getOwnedPrimaryKey
	 * @model opposite="ownedPrimaryKey" required="true" transient="false"
	 * @generated
	 */
	Table getOwningTable();

	/**
	 * Sets the value of the '{@link sql.PrimaryKey#getOwningTable <em>Owning Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Table</em>' container reference.
	 * @see #getOwningTable()
	 * @generated
	 */
	void setOwningTable(Table value);

} // PrimaryKey
