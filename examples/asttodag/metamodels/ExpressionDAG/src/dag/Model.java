/**
 */
package dag;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link dag.Model#getExprs <em>Exprs</em>}</li>
 * </ul>
 *
 * @see dag.DagPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject {
	/**
	 * Returns the value of the '<em><b>Exprs</b></em>' containment reference list.
	 * The list contents are of type {@link dag.Expression}.
	 * It is bidirectional and its opposite is '{@link dag.Expression#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exprs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exprs</em>' containment reference list.
	 * @see dag.DagPackage#getModel_Exprs()
	 * @see dag.Expression#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<Expression> getExprs();

} // Model
