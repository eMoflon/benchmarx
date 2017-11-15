/**
 */
package pn;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Place</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pn.Place#getTrgP2T <em>Trg P2T</em>}</li>
 *   <li>{@link pn.Place#getSrcT2P <em>Src T2P</em>}</li>
 *   <li>{@link pn.Place#getNoOfTokens <em>No Of Tokens</em>}</li>
 * </ul>
 *
 * @see pn.PnPackage#getPlace()
 * @model
 * @generated
 */
public interface Place extends NetElement, NamedElement {
	/**
	 * Returns the value of the '<em><b>Trg P2T</b></em>' reference list.
	 * The list contents are of type {@link pn.Transition}.
	 * It is bidirectional and its opposite is '{@link pn.Transition#getSrcP2T <em>Src P2T</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trg P2T</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trg P2T</em>' reference list.
	 * @see pn.PnPackage#getPlace_TrgP2T()
	 * @see pn.Transition#getSrcP2T
	 * @model opposite="srcP2T"
	 * @generated
	 */
	EList<Transition> getTrgP2T();

	/**
	 * Returns the value of the '<em><b>Src T2P</b></em>' reference list.
	 * The list contents are of type {@link pn.Transition}.
	 * It is bidirectional and its opposite is '{@link pn.Transition#getTrgT2P <em>Trg T2P</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Src T2P</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Src T2P</em>' reference list.
	 * @see pn.PnPackage#getPlace_SrcT2P()
	 * @see pn.Transition#getTrgT2P
	 * @model opposite="trgT2P"
	 * @generated
	 */
	EList<Transition> getSrcT2P();

	/**
	 * Returns the value of the '<em><b>No Of Tokens</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>No Of Tokens</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>No Of Tokens</em>' attribute.
	 * @see #setNoOfTokens(int)
	 * @see pn.PnPackage#getPlace_NoOfTokens()
	 * @model default="1"
	 * @generated
	 */
	int getNoOfTokens();

	/**
	 * Sets the value of the '{@link pn.Place#getNoOfTokens <em>No Of Tokens</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No Of Tokens</em>' attribute.
	 * @see #getNoOfTokens()
	 * @generated
	 */
	void setNoOfTokens(int value);

} // Place
