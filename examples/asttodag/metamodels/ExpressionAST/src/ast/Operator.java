/**
 */
package ast;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ast.Operator#getLeft <em>Left</em>}</li>
 *   <li>{@link ast.Operator#getRight <em>Right</em>}</li>
 *   <li>{@link ast.Operator#getOp <em>Op</em>}</li>
 * </ul>
 *
 * @see ast.AstPackage#getOperator()
 * @model
 * @generated
 */
public interface Operator extends Expression {
	/**
	 * Returns the value of the '<em><b>Left</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link ast.Expression#getLeftInverse <em>Left Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left</em>' containment reference.
	 * @see #setLeft(Expression)
	 * @see ast.AstPackage#getOperator_Left()
	 * @see ast.Expression#getLeftInverse
	 * @model opposite="leftInverse" containment="true"
	 * @generated
	 */
	Expression getLeft();

	/**
	 * Sets the value of the '{@link ast.Operator#getLeft <em>Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' containment reference.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(Expression value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link ast.Expression#getRightInverse <em>Right Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' containment reference.
	 * @see #setRight(Expression)
	 * @see ast.AstPackage#getOperator_Right()
	 * @see ast.Expression#getRightInverse
	 * @model opposite="rightInverse" containment="true"
	 * @generated
	 */
	Expression getRight();

	/**
	 * Sets the value of the '{@link ast.Operator#getRight <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' containment reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(Expression value);

	/**
	 * Returns the value of the '<em><b>Op</b></em>' attribute.
	 * The literals are from the enumeration {@link ast.ArithmeticOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Op</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op</em>' attribute.
	 * @see ast.ArithmeticOperator
	 * @see #setOp(ArithmeticOperator)
	 * @see ast.AstPackage#getOperator_Op()
	 * @model
	 * @generated
	 */
	ArithmeticOperator getOp();

	/**
	 * Sets the value of the '{@link ast.Operator#getOp <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op</em>' attribute.
	 * @see ast.ArithmeticOperator
	 * @see #getOp()
	 * @generated
	 */
	void setOp(ArithmeticOperator value);

} // Operator
