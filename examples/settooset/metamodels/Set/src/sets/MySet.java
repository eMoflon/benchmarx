/**
 */
package sets;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>My Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link sets.MySet#getName <em>Name</em>}</li>
 *   <li>{@link sets.MySet#getElements <em>Elements</em>}</li>
 *   <li>{@link sets.MySet#getIncrementalID <em>Incremental ID</em>}</li>
 * </ul>
 *
 * @see sets.SetsPackage#getMySet()
 * @model
 * @generated
 */
public interface MySet extends EObject {
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
	 * @see sets.SetsPackage#getMySet_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link sets.MySet#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link sets.Element}.
	 * It is bidirectional and its opposite is '{@link sets.Element#getSet <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see sets.SetsPackage#getMySet_Elements()
	 * @see sets.Element#getSet
	 * @model opposite="set" containment="true" ordered="false"
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
	 * @see sets.SetsPackage#getMySet_IncrementalID()
	 * @model
	 * @generated
	 */
	String getIncrementalID();

	/**
	 * Sets the value of the '{@link sets.MySet#getIncrementalID <em>Incremental ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Incremental ID</em>' attribute.
	 * @see #getIncrementalID()
	 * @generated
	 */
	void setIncrementalID(String value);

} // MySet
