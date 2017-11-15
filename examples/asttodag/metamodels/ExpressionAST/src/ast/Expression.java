/**
 */
package ast;

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
 *   <li>{@link ast.Expression#getModel <em>Model</em>}</li>
 *   <li>{@link ast.Expression#getLeftInverse <em>Left Inverse</em>}</li>
 *   <li>{@link ast.Expression#getRightInverse <em>Right Inverse</em>}</li>
 *   <li>{@link ast.Expression#getIncrementalID <em>Incremental ID</em>}</li>
 * </ul>
 *
 * @see ast.AstPackage#getExpression()
 * @model abstract="true"
 * @generated
 */
public interface Expression extends EObject {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ast.Model#getExpr <em>Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(Model)
	 * @see ast.AstPackage#getExpression_Model()
	 * @see ast.Model#getExpr
	 * @model opposite="expr" transient="false"
	 * @generated
	 */
	Model getModel();

	/**
	 * Sets the value of the '{@link ast.Expression#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(Model value);

	/**
	 * Returns the value of the '<em><b>Left Inverse</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ast.Operator#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Inverse</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Inverse</em>' container reference.
	 * @see #setLeftInverse(Operator)
	 * @see ast.AstPackage#getExpression_LeftInverse()
	 * @see ast.Operator#getLeft
	 * @model opposite="left" transient="false"
	 * @generated
	 */
	Operator getLeftInverse();

	/**
	 * Sets the value of the '{@link ast.Expression#getLeftInverse <em>Left Inverse</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Inverse</em>' container reference.
	 * @see #getLeftInverse()
	 * @generated
	 */
	void setLeftInverse(Operator value);

	/**
	 * Returns the value of the '<em><b>Right Inverse</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ast.Operator#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Inverse</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Inverse</em>' container reference.
	 * @see #setRightInverse(Operator)
	 * @see ast.AstPackage#getExpression_RightInverse()
	 * @see ast.Operator#getRight
	 * @model opposite="right" transient="false"
	 * @generated
	 */
	Operator getRightInverse();

	/**
	 * Sets the value of the '{@link ast.Expression#getRightInverse <em>Right Inverse</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Inverse</em>' container reference.
	 * @see #getRightInverse()
	 * @generated
	 */
	void setRightInverse(Operator value);

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
	 * @see ast.AstPackage#getExpression_IncrementalID()
	 * @model
	 * @generated
	 */
	String getIncrementalID();

	/**
	 * Sets the value of the '{@link ast.Expression#getIncrementalID <em>Incremental ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Incremental ID</em>' attribute.
	 * @see #getIncrementalID()
	 * @generated
	 */
	void setIncrementalID(String value);

} // Expression
