/**
 */
package de.tbuchmann.ttc.corrmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Corr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tbuchmann.ttc.corrmodel.Corr#getRuleId <em>Rule Id</em>}</li>
 *   <li>{@link de.tbuchmann.ttc.corrmodel.Corr#getSource <em>Source</em>}</li>
 *   <li>{@link de.tbuchmann.ttc.corrmodel.Corr#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see de.tbuchmann.ttc.corrmodel.CorrModelPackage#getCorr()
 * @model
 * @generated
 */
public interface Corr extends EObject {
	/**
	 * Returns the value of the '<em><b>Rule Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule Id</em>' attribute.
	 * @see #setRuleId(String)
	 * @see de.tbuchmann.ttc.corrmodel.CorrModelPackage#getCorr_RuleId()
	 * @model
	 * @generated
	 */
	String getRuleId();

	/**
	 * Sets the value of the '{@link de.tbuchmann.ttc.corrmodel.Corr#getRuleId <em>Rule Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule Id</em>' attribute.
	 * @see #getRuleId()
	 * @generated
	 */
	void setRuleId(String value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference list.
	 * The list contents are of type {@link de.tbuchmann.ttc.corrmodel.CorrElem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference list.
	 * @see de.tbuchmann.ttc.corrmodel.CorrModelPackage#getCorr_Source()
	 * @model containment="true"
	 * @generated
	 */
	EList<CorrElem> getSource();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' containment reference list.
	 * The list contents are of type {@link de.tbuchmann.ttc.corrmodel.CorrElem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' containment reference list.
	 * @see de.tbuchmann.ttc.corrmodel.CorrModelPackage#getCorr_Target()
	 * @model containment="true"
	 * @generated
	 */
	EList<CorrElem> getTarget();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<EObject> flatSrc();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<EObject> flatTrg();

} // Corr
