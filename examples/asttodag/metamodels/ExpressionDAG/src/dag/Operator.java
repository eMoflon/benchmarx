/**
 */
package dag;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link dag.Operator#getLeft <em>Left</em>}</li>
 *   <li>{@link dag.Operator#getRight <em>Right</em>}</li>
 *   <li>{@link dag.Operator#getOp <em>Op</em>}</li>
 * </ul>
 *
 * @see dag.DagPackage#getOperator()
 * @model
 * @generated
 */
public interface Operator extends Expression {
	/**
	 * Returns the value of the '<em><b>Left</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link dag.Expression#getLeftInverse <em>Left Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left</em>' reference.
	 * @see #setLeft(Expression)
	 * @see dag.DagPackage#getOperator_Left()
	 * @see dag.Expression#getLeftInverse
	 * @model opposite="leftInverse"
	 * @generated
	 */
	Expression getLeft();

	/**
	 * Sets the value of the '{@link dag.Operator#getLeft <em>Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' reference.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(Expression value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link dag.Expression#getRightInverse <em>Right Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' reference.
	 * @see #setRight(Expression)
	 * @see dag.DagPackage#getOperator_Right()
	 * @see dag.Expression#getRightInverse
	 * @model opposite="rightInverse"
	 * @generated
	 */
	Expression getRight();

	/**
	 * Sets the value of the '{@link dag.Operator#getRight <em>Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(Expression value);

	/**
	 * Returns the value of the '<em><b>Op</b></em>' attribute.
	 * The literals are from the enumeration {@link dag.ArithmeticOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Op</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op</em>' attribute.
	 * @see dag.ArithmeticOperator
	 * @see #setOp(ArithmeticOperator)
	 * @see dag.DagPackage#getOperator_Op()
	 * @model
	 * @generated
	 */
	ArithmeticOperator getOp();

	/**
	 * Sets the value of the '{@link dag.Operator#getOp <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op</em>' attribute.
	 * @see dag.ArithmeticOperator
	 * @see #getOp()
	 * @generated
	 */
	void setOp(ArithmeticOperator value);

} // Operator
