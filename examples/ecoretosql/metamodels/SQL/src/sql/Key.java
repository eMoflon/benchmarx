/**
 */
package sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link sql.Key#getColumn <em>Column</em>}</li>
 * </ul>
 *
 * @see sql.SqlPackage#getKey()
 * @model abstract="true"
 * @generated
 */
public interface Key extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Column</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link sql.Column#getKeys <em>Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column</em>' reference.
	 * @see #setColumn(Column)
	 * @see sql.SqlPackage#getKey_Column()
	 * @see sql.Column#getKeys
	 * @model opposite="keys" required="true"
	 * @generated
	 */
	Column getColumn();

	/**
	 * Sets the value of the '{@link sql.Key#getColumn <em>Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column</em>' reference.
	 * @see #getColumn()
	 * @generated
	 */
	void setColumn(Column value);

} // Key
