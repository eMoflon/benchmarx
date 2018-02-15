/**
 */
package cpm;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link cpm.Element#getNetwork <em>Network</em>}</li>
 * </ul>
 *
 * @see cpm.CpmPackage#getElement()
 * @model abstract="true"
 * @generated
 */
public interface Element extends EObject {
	/**
	 * Returns the value of the '<em><b>Network</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link cpm.CPMNetwork#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Network</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Network</em>' container reference.
	 * @see #setNetwork(CPMNetwork)
	 * @see cpm.CpmPackage#getElement_Network()
	 * @see cpm.CPMNetwork#getElements
	 * @model opposite="elements" required="true" transient="false"
	 * @generated
	 */
	CPMNetwork getNetwork();

	/**
	 * Sets the value of the '{@link cpm.Element#getNetwork <em>Network</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Network</em>' container reference.
	 * @see #getNetwork()
	 * @generated
	 */
	void setNetwork(CPMNetwork value);

} // Element
