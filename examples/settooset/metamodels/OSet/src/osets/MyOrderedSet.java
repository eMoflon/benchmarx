/**
 */
package osets;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>My Ordered Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link osets.MyOrderedSet#getName <em>Name</em>}</li>
 *   <li>{@link osets.MyOrderedSet#getElements <em>Elements</em>}</li>
 *   <li>{@link osets.MyOrderedSet#getIncrementalID <em>Incremental ID</em>}</li>
 * </ul>
 *
 * @see osets.OsetsPackage#getMyOrderedSet()
 * @model
 * @generated
 */
public interface MyOrderedSet extends EObject {
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
	 * @see osets.OsetsPackage#getMyOrderedSet_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link osets.MyOrderedSet#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link osets.Element}.
	 * It is bidirectional and its opposite is '{@link osets.Element#getOrderedSet <em>Ordered Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see osets.OsetsPackage#getMyOrderedSet_Elements()
	 * @see osets.Element#getOrderedSet
	 * @model opposite="orderedSet" containment="true" ordered="false"
	 * @generated
	 */
	EList<Element> getElements();

	/**
	 * Returns the value of the '<em><b>Incremental ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incremental ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incremental ID</em>' attribute.
	 * @see #setIncrementalID(String)
	 * @see osets.OsetsPackage#getMyOrderedSet_IncrementalID()
	 * @model
	 * @generated
	 */
	String getIncrementalID();

	/**
	 * Sets the value of the '{@link osets.MyOrderedSet#getIncrementalID <em>Incremental ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Incremental ID</em>' attribute.
	 * @see #getIncrementalID()
	 * @generated
	 */
	void setIncrementalID(String value);

} // MyOrderedSet
