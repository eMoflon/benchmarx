/**
 */
package sql;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link sql.Column#getOwningTable <em>Owning Table</em>}</li>
 *   <li>{@link sql.Column#getType <em>Type</em>}</li>
 *   <li>{@link sql.Column#getKeys <em>Keys</em>}</li>
 *   <li>{@link sql.Column#getProperties <em>Properties</em>}</li>
 * </ul>
 *
 * @see sql.SqlPackage#getColumn()
 * @model
 * @generated
 */
public interface Column extends NamedElement, ModelElement {
	/**
	 * Returns the value of the '<em><b>Owning Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link sql.Table#getOwnedColumns <em>Owned Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Table</em>' container reference.
	 * @see #setOwningTable(Table)
	 * @see sql.SqlPackage#getColumn_OwningTable()
	 * @see sql.Table#getOwnedColumns
	 * @model opposite="ownedColumns" required="true" transient="false"
	 * @generated
	 */
	Table getOwningTable();

	/**
	 * Sets the value of the '{@link sql.Column#getOwningTable <em>Owning Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Table</em>' container reference.
	 * @see #getOwningTable()
	 * @generated
	 */
	void setOwningTable(Table value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see sql.SqlPackage#getColumn_Type()
	 * @model required="true"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link sql.Column#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Keys</b></em>' reference list.
	 * The list contents are of type {@link sql.Key}.
	 * It is bidirectional and its opposite is '{@link sql.Key#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keys</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keys</em>' reference list.
	 * @see sql.SqlPackage#getColumn_Keys()
	 * @see sql.Key#getColumn
	 * @model opposite="column"
	 * @generated
	 */
	EList<Key> getKeys();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' attribute list.
	 * The list contents are of type {@link sql.Property}.
	 * The literals are from the enumeration {@link sql.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' attribute list.
	 * @see sql.Property
	 * @see sql.SqlPackage#getColumn_Properties()
	 * @model
	 * @generated
	 */
	EList<Property> getProperties();

} // Column
