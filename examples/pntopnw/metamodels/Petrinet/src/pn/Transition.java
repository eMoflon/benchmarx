/**
 */
package pn;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pn.Transition#getTrgT2P <em>Trg T2P</em>}</li>
 *   <li>{@link pn.Transition#getSrcP2T <em>Src P2T</em>}</li>
 * </ul>
 *
 * @see pn.PnPackage#getTransition()
 * @model
 * @generated
 */
public interface Transition extends NetElement {
	/**
	 * Returns the value of the '<em><b>Trg T2P</b></em>' reference list.
	 * The list contents are of type {@link pn.Place}.
	 * It is bidirectional and its opposite is '{@link pn.Place#getSrcT2P <em>Src T2P</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trg T2P</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trg T2P</em>' reference list.
	 * @see pn.PnPackage#getTransition_TrgT2P()
	 * @see pn.Place#getSrcT2P
	 * @model opposite="srcT2P"
	 * @generated
	 */
	EList<Place> getTrgT2P();

	/**
	 * Returns the value of the '<em><b>Src P2T</b></em>' reference list.
	 * The list contents are of type {@link pn.Place}.
	 * It is bidirectional and its opposite is '{@link pn.Place#getTrgP2T <em>Trg P2T</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Src P2T</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Src P2T</em>' reference list.
	 * @see pn.PnPackage#getTransition_SrcP2T()
	 * @see pn.Place#getTrgP2T
	 * @model opposite="trgP2T"
	 * @generated
	 */
	EList<Place> getSrcP2T();

} // Transition
