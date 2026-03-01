/**
 */
package Families;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Family Member</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Families.FamilyMember#getName <em>Name</em>}</li>
 *   <li>{@link Families.FamilyMember#getFatherInverse <em>Father Inverse</em>}</li>
 *   <li>{@link Families.FamilyMember#getMotherInverse <em>Mother Inverse</em>}</li>
 *   <li>{@link Families.FamilyMember#getSonsInverse <em>Sons Inverse</em>}</li>
 *   <li>{@link Families.FamilyMember#getDaughtersInverse <em>Daughters Inverse</em>}</li>
 * </ul>
 *
 * @see Families.FamiliesPackage#getFamilyMember()
 * @model
 * @generated
 */
public interface FamilyMember extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Families.FamiliesPackage#getFamilyMember_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Families.FamilyMember#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Father Inverse</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link Families.Family#getFather <em>Father</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Father Inverse</em>' container reference.
	 * @see #setFatherInverse(Family)
	 * @see Families.FamiliesPackage#getFamilyMember_FatherInverse()
	 * @see Families.Family#getFather
	 * @model opposite="father" transient="false"
	 * @generated
	 */
	Family getFatherInverse();

	/**
	 * Sets the value of the '{@link Families.FamilyMember#getFatherInverse <em>Father Inverse</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Father Inverse</em>' container reference.
	 * @see #getFatherInverse()
	 * @generated
	 */
	void setFatherInverse(Family value);

	/**
	 * Returns the value of the '<em><b>Mother Inverse</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link Families.Family#getMother <em>Mother</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mother Inverse</em>' container reference.
	 * @see #setMotherInverse(Family)
	 * @see Families.FamiliesPackage#getFamilyMember_MotherInverse()
	 * @see Families.Family#getMother
	 * @model opposite="mother" transient="false"
	 * @generated
	 */
	Family getMotherInverse();

	/**
	 * Sets the value of the '{@link Families.FamilyMember#getMotherInverse <em>Mother Inverse</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mother Inverse</em>' container reference.
	 * @see #getMotherInverse()
	 * @generated
	 */
	void setMotherInverse(Family value);

	/**
	 * Returns the value of the '<em><b>Sons Inverse</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link Families.Family#getSons <em>Sons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sons Inverse</em>' container reference.
	 * @see #setSonsInverse(Family)
	 * @see Families.FamiliesPackage#getFamilyMember_SonsInverse()
	 * @see Families.Family#getSons
	 * @model opposite="sons" transient="false"
	 * @generated
	 */
	Family getSonsInverse();

	/**
	 * Sets the value of the '{@link Families.FamilyMember#getSonsInverse <em>Sons Inverse</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sons Inverse</em>' container reference.
	 * @see #getSonsInverse()
	 * @generated
	 */
	void setSonsInverse(Family value);

	/**
	 * Returns the value of the '<em><b>Daughters Inverse</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link Families.Family#getDaughters <em>Daughters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Daughters Inverse</em>' container reference.
	 * @see #setDaughtersInverse(Family)
	 * @see Families.FamiliesPackage#getFamilyMember_DaughtersInverse()
	 * @see Families.Family#getDaughters
	 * @model opposite="daughters" transient="false"
	 * @generated
	 */
	Family getDaughtersInverse();

	/**
	 * Sets the value of the '{@link Families.FamilyMember#getDaughtersInverse <em>Daughters Inverse</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Daughters Inverse</em>' container reference.
	 * @see #getDaughtersInverse()
	 * @generated
	 */
	void setDaughtersInverse(Family value);

} // FamilyMember
