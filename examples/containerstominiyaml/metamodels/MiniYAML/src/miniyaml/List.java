/**
 */
package miniyaml;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link miniyaml.List#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @see miniyaml.MiniyamlPackage#getList()
 * @model
 * @generated
 */
public interface List extends Value {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link miniyaml.Value}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see miniyaml.MiniyamlPackage#getList_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<Value> getValues();

} // List
