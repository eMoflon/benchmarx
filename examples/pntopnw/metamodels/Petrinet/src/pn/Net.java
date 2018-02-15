/**
 */
package pn;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Net</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pn.Net#getElements <em>Elements</em>}</li>
 *   <li>{@link pn.Net#getIncrementalID <em>Incremental ID</em>}</li>
 * </ul>
 *
 * @see pn.PnPackage#getNet()
 * @model
 * @generated
 */
public interface Net extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link pn.NetElement}.
	 * It is bidirectional and its opposite is '{@link pn.NetElement#getNet <em>Net</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see pn.PnPackage#getNet_Elements()
	 * @see pn.NetElement#getNet
	 * @model opposite="net" containment="true"
	 * @generated
	 */
	EList<NetElement> getElements();

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
	 * @see pn.PnPackage#getNet_IncrementalID()
	 * @model
	 * @generated
	 */
	String getIncrementalID();

	/**
	 * Sets the value of the '{@link pn.Net#getIncrementalID <em>Incremental ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Incremental ID</em>' attribute.
	 * @see #getIncrementalID()
	 * @generated
	 */
	void setIncrementalID(String value);

} // Net
