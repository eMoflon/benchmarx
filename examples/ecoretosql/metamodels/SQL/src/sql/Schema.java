/**
 */
package sql;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link sql.Schema#getOwnedTables <em>Owned Tables</em>}</li>
 * </ul>
 *
 * @see sql.SqlPackage#getSchema()
 * @model
 * @generated
 */
public interface Schema extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Owned Tables</b></em>' containment reference list.
	 * The list contents are of type {@link sql.Table}.
	 * It is bidirectional and its opposite is '{@link sql.Table#getOwningSchema <em>Owning Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Tables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Tables</em>' containment reference list.
	 * @see sql.SqlPackage#getSchema_OwnedTables()
	 * @see sql.Table#getOwningSchema
	 * @model opposite="owningSchema" containment="true" required="true"
	 * @generated
	 */
	EList<Table> getOwnedTables();

} // Schema
