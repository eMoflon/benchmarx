/**
 */
package sql;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link sql.Table#getOwnedColumns <em>Owned Columns</em>}</li>
 *   <li>{@link sql.Table#getOwnedPrimaryKey <em>Owned Primary Key</em>}</li>
 *   <li>{@link sql.Table#getOwnedForeignKeys <em>Owned Foreign Keys</em>}</li>
 *   <li>{@link sql.Table#getReferencingForeignKeys <em>Referencing Foreign Keys</em>}</li>
 *   <li>{@link sql.Table#getOwningSchema <em>Owning Schema</em>}</li>
 * </ul>
 *
 * @see sql.SqlPackage#getTable()
 * @model
 * @generated
 */
public interface Table extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Owned Columns</b></em>' containment reference list.
	 * The list contents are of type {@link sql.Column}.
	 * It is bidirectional and its opposite is '{@link sql.Column#getOwningTable <em>Owning Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Columns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Columns</em>' containment reference list.
	 * @see sql.SqlPackage#getTable_OwnedColumns()
	 * @see sql.Column#getOwningTable
	 * @model opposite="owningTable" containment="true" required="true"
	 * @generated
	 */
	EList<Column> getOwnedColumns();

	/**
	 * Returns the value of the '<em><b>Owned Primary Key</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link sql.PrimaryKey#getOwningTable <em>Owning Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Primary Key</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Primary Key</em>' containment reference.
	 * @see #setOwnedPrimaryKey(PrimaryKey)
	 * @see sql.SqlPackage#getTable_OwnedPrimaryKey()
	 * @see sql.PrimaryKey#getOwningTable
	 * @model opposite="owningTable" containment="true"
	 * @generated
	 */
	PrimaryKey getOwnedPrimaryKey();

	/**
	 * Sets the value of the '{@link sql.Table#getOwnedPrimaryKey <em>Owned Primary Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Primary Key</em>' containment reference.
	 * @see #getOwnedPrimaryKey()
	 * @generated
	 */
	void setOwnedPrimaryKey(PrimaryKey value);

	/**
	 * Returns the value of the '<em><b>Owned Foreign Keys</b></em>' containment reference list.
	 * The list contents are of type {@link sql.ForeignKey}.
	 * It is bidirectional and its opposite is '{@link sql.ForeignKey#getOwningTable <em>Owning Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Foreign Keys</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Foreign Keys</em>' containment reference list.
	 * @see sql.SqlPackage#getTable_OwnedForeignKeys()
	 * @see sql.ForeignKey#getOwningTable
	 * @model opposite="owningTable" containment="true"
	 * @generated
	 */
	EList<ForeignKey> getOwnedForeignKeys();

	/**
	 * Returns the value of the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * The list contents are of type {@link sql.ForeignKey}.
	 * It is bidirectional and its opposite is '{@link sql.ForeignKey#getReferencedTable <em>Referenced Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referencing Foreign Keys</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referencing Foreign Keys</em>' reference list.
	 * @see sql.SqlPackage#getTable_ReferencingForeignKeys()
	 * @see sql.ForeignKey#getReferencedTable
	 * @model opposite="referencedTable"
	 * @generated
	 */
	EList<ForeignKey> getReferencingForeignKeys();

	/**
	 * Returns the value of the '<em><b>Owning Schema</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link sql.Schema#getOwnedTables <em>Owned Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Schema</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Schema</em>' container reference.
	 * @see #setOwningSchema(Schema)
	 * @see sql.SqlPackage#getTable_OwningSchema()
	 * @see sql.Schema#getOwnedTables
	 * @model opposite="ownedTables" required="true" transient="false"
	 * @generated
	 */
	Schema getOwningSchema();

	/**
	 * Sets the value of the '{@link sql.Table#getOwningSchema <em>Owning Schema</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Schema</em>' container reference.
	 * @see #getOwningSchema()
	 * @generated
	 */
	void setOwningSchema(Schema value);

} // Table
