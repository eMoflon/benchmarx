/**
 */
package pnw;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Net Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pnw.NetElement#getNet <em>Net</em>}</li>
 * </ul>
 *
 * @see pnw.PnwPackage#getNetElement()
 * @model abstract="true"
 * @generated
 */
public interface NetElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Net</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link pnw.Net#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Net</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Net</em>' container reference.
	 * @see #setNet(Net)
	 * @see pnw.PnwPackage#getNetElement_Net()
	 * @see pnw.Net#getElements
	 * @model opposite="elements" required="true" transient="false"
	 * @generated
	 */
	Net getNet();

	/**
	 * Sets the value of the '{@link pnw.NetElement#getNet <em>Net</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Net</em>' container reference.
	 * @see #getNet()
	 * @generated
	 */
	void setNet(Net value);

} // NetElement
