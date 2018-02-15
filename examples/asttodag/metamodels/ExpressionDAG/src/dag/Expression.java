/**
 */
package dag;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link dag.Expression#getModel <em>Model</em>}</li>
 *   <li>{@link dag.Expression#getLeftInverse <em>Left Inverse</em>}</li>
 *   <li>{@link dag.Expression#getRightInverse <em>Right Inverse</em>}</li>
 *   <li>{@link dag.Expression#getIncrementalID <em>Incremental ID</em>}</li>
 * </ul>
 *
 * @see dag.DagPackage#getExpression()
 * @model abstract="true"
 * @generated
 */
public interface Expression extends EObject {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link dag.Model#getExprs <em>Exprs</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(Model)
	 * @see dag.DagPackage#getExpression_Model()
	 * @see dag.Model#getExprs
	 * @model opposite="exprs" transient="false"
	 * @generated
	 */
	Model getModel();

	/**
	 * Sets the value of the '{@link dag.Expression#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(Model value);

	/**
	 * Returns the value of the '<em><b>Left Inverse</b></em>' reference list.
	 * The list contents are of type {@link dag.Operator}.
	 * It is bidirectional and its opposite is '{@link dag.Operator#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Inverse</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Inverse</em>' reference list.
	 * @see dag.DagPackage#getExpression_LeftInverse()
	 * @see dag.Operator#getLeft
	 * @model opposite="left"
	 * @generated
	 */
	EList<Operator> getLeftInverse();

	/**
	 * Returns the value of the '<em><b>Right Inverse</b></em>' reference list.
	 * The list contents are of type {@link dag.Operator}.
	 * It is bidirectional and its opposite is '{@link dag.Operator#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Inverse</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Inverse</em>' reference list.
	 * @see dag.DagPackage#getExpression_RightInverse()
	 * @see dag.Operator#getRight
	 * @model opposite="right"
	 * @generated
	 */
	EList<Operator> getRightInverse();

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
	 * @see dag.DagPackage#getExpression_IncrementalID()
	 * @model
	 * @generated
	 */
	String getIncrementalID();

	/**
	 * Sets the value of the '{@link dag.Expression#getIncrementalID <em>Incremental ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Incremental ID</em>' attribute.
	 * @see #getIncrementalID()
	 * @generated
	 */
	void setIncrementalID(String value);

} // Expression
