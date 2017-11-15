/**
 */
package bags1;

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
 *   <li>{@link bags1.Element#getValue <em>Value</em>}</li>
 *   <li>{@link bags1.Element#getBag <em>Bag</em>}</li>
 *   <li>{@link bags1.Element#getIncrementalID <em>Incremental ID</em>}</li>
 * </ul>
 *
 * @see bags1.Bags1Package#getElement()
 * @model
 * @generated
 */
public interface Element extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see bags1.Bags1Package#getElement_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link bags1.Element#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Bag</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link bags1.MyBag#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bag</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bag</em>' container reference.
	 * @see #setBag(MyBag)
	 * @see bags1.Bags1Package#getElement_Bag()
	 * @see bags1.MyBag#getElements
	 * @model opposite="elements" transient="false"
	 * @generated
	 */
	MyBag getBag();

	/**
	 * Sets the value of the '{@link bags1.Element#getBag <em>Bag</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bag</em>' container reference.
	 * @see #getBag()
	 * @generated
	 */
	void setBag(MyBag value);

	/**
	 * Returns the value of the '<em><b>Incremental ID</b></em>' attribute.
	 * The default value is <code>"default"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incremental ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incremental ID</em>' attribute.
	 * @see #setIncrementalID(String)
	 * @see bags1.Bags1Package#getElement_IncrementalID()
	 * @model default="default"
	 * @generated
	 */
	String getIncrementalID();

	/**
	 * Sets the value of the '{@link bags1.Element#getIncrementalID <em>Incremental ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Incremental ID</em>' attribute.
	 * @see #getIncrementalID()
	 * @generated
	 */
	void setIncrementalID(String value);

} // Element
