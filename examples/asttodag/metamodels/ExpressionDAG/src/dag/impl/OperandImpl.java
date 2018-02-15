/**
 */
package dag.impl;

import org.eclipse.emf.ecore.EClass;

import dag.DagPackage;
import dag.Operand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operand</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class OperandImpl extends ExpressionImpl implements Operand {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperandImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DagPackage.Literals.OPERAND;
	}

} //OperandImpl
