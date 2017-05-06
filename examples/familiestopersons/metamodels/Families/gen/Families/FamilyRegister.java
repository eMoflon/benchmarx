/**
 */
package Families;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
// <-- [user defined imports]
// [user defined imports] -->

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Family Register</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Families.FamilyRegister#getFamilies <em>Families</em>}</li>
 * </ul>
 * </p>
 *
 * @see Families.FamiliesPackage#getFamilyRegister()
 * @model
 * @generated
 */
public interface FamilyRegister extends EObject {
	/**
	 * Returns the value of the '<em><b>Families</b></em>' containment reference list.
	 * The list contents are of type {@link Families.Family}.
	 * It is bidirectional and its opposite is '{@link Families.Family#getFamiliesInverse <em>Families Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Families</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Families</em>' containment reference list.
	 * @see Families.FamiliesPackage#getFamilyRegister_Families()
	 * @see Families.Family#getFamiliesInverse
	 * @model opposite="familiesInverse" containment="true"
	 * @generated
	 */
	EList<Family> getFamilies();
	// <-- [user code injected with eMoflon]

	// [user code injected with eMoflon] -->
} // FamilyRegister
