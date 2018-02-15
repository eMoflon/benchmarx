/**
 */
package pnw;

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
 *   <li>{@link pnw.Place#getNoOfTokens <em>No Of Tokens</em>}</li>
 *   <li>{@link pnw.Place#getOutPTEdges <em>Out PT Edges</em>}</li>
 *   <li>{@link pnw.Place#getInTPEdges <em>In TP Edges</em>}</li>
 * </ul>
 *
 * @see pnw.PnwPackage#getPlace()
 * @model
 * @generated
 */
public interface Place extends NetElement, NamedElement {
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
	 * @see pnw.PnwPackage#getPlace_NoOfTokens()
	 * @model default="1"
	 * @generated
	 */
	int getNoOfTokens();

	/**
	 * Sets the value of the '{@link pnw.Place#getNoOfTokens <em>No Of Tokens</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No Of Tokens</em>' attribute.
	 * @see #getNoOfTokens()
	 * @generated
	 */
	void setNoOfTokens(int value);

	/**
	 * Returns the value of the '<em><b>Out PT Edges</b></em>' reference list.
	 * The list contents are of type {@link pnw.PTEdge}.
	 * It is bidirectional and its opposite is '{@link pnw.PTEdge#getFromPlace <em>From Place</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out PT Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out PT Edges</em>' reference list.
	 * @see pnw.PnwPackage#getPlace_OutPTEdges()
	 * @see pnw.PTEdge#getFromPlace
	 * @model opposite="fromPlace"
	 * @generated
	 */
	EList<PTEdge> getOutPTEdges();

	/**
	 * Returns the value of the '<em><b>In TP Edges</b></em>' reference list.
	 * The list contents are of type {@link pnw.TPEdge}.
	 * It is bidirectional and its opposite is '{@link pnw.TPEdge#getToPlace <em>To Place</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In TP Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In TP Edges</em>' reference list.
	 * @see pnw.PnwPackage#getPlace_InTPEdges()
	 * @see pnw.TPEdge#getToPlace
	 * @model opposite="toPlace"
	 * @generated
	 */
	EList<TPEdge> getInTPEdges();

} // Place
