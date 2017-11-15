/**
 */
package bags1;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>My Bag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link bags1.MyBag#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see bags1.Bags1Package#getMyBag()
 * @model
 * @generated
 */
public interface MyBag extends EObject {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link bags1.Element}.
	 * It is bidirectional and its opposite is '{@link bags1.Element#getBag <em>Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see bags1.Bags1Package#getMyBag_Elements()
	 * @see bags1.Element#getBag
	 * @model opposite="bag" containment="true"
	 * @generated
	 */
	EList<Element> getElements();

} // MyBag
