/**
 */
package osets;

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
 *   <li>{@link osets.Element#getValue <em>Value</em>}</li>
 *   <li>{@link osets.Element#getNext <em>Next</em>}</li>
 *   <li>{@link osets.Element#getPrevious <em>Previous</em>}</li>
 *   <li>{@link osets.Element#getOrderedSet <em>Ordered Set</em>}</li>
 * </ul>
 *
 * @see osets.OsetsPackage#getElement()
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
	 * @see osets.OsetsPackage#getElement_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link osets.Element#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Next</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link osets.Element#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next</em>' reference.
	 * @see #setNext(Element)
	 * @see osets.OsetsPackage#getElement_Next()
	 * @see osets.Element#getPrevious
	 * @model opposite="previous"
	 * @generated
	 */
	Element getNext();

	/**
	 * Sets the value of the '{@link osets.Element#getNext <em>Next</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next</em>' reference.
	 * @see #getNext()
	 * @generated
	 */
	void setNext(Element value);

	/**
	 * Returns the value of the '<em><b>Previous</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link osets.Element#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous</em>' reference.
	 * @see #setPrevious(Element)
	 * @see osets.OsetsPackage#getElement_Previous()
	 * @see osets.Element#getNext
	 * @model opposite="next"
	 * @generated
	 */
	Element getPrevious();

	/**
	 * Sets the value of the '{@link osets.Element#getPrevious <em>Previous</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Previous</em>' reference.
	 * @see #getPrevious()
	 * @generated
	 */
	void setPrevious(Element value);

	/**
	 * Returns the value of the '<em><b>Ordered Set</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link osets.MyOrderedSet#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordered Set</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordered Set</em>' container reference.
	 * @see #setOrderedSet(MyOrderedSet)
	 * @see osets.OsetsPackage#getElement_OrderedSet()
	 * @see osets.MyOrderedSet#getElements
	 * @model opposite="elements" transient="false"
	 * @generated
	 */
	MyOrderedSet getOrderedSet();

	/**
	 * Sets the value of the '{@link osets.Element#getOrderedSet <em>Ordered Set</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordered Set</em>' container reference.
	 * @see #getOrderedSet()
	 * @generated
	 */
	void setOrderedSet(MyOrderedSet value);

} // Element
