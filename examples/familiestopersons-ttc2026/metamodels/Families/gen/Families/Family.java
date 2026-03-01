/**
 */
package Families;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Family</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Families.Family#getFather <em>Father</em>}</li>
 *   <li>{@link Families.Family#getMother <em>Mother</em>}</li>
 *   <li>{@link Families.Family#getSons <em>Sons</em>}</li>
 *   <li>{@link Families.Family#getDaughters <em>Daughters</em>}</li>
 *   <li>{@link Families.Family#getName <em>Name</em>}</li>
 *   <li>{@link Families.Family#getFamiliesInverse <em>Families Inverse</em>}</li>
 * </ul>
 *
 * @see Families.FamiliesPackage#getFamily()
 * @model
 * @generated
 */
public interface Family extends EObject {
	/**
	 * Returns the value of the '<em><b>Father</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link Families.FamilyMember#getFatherInverse <em>Father Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Father</em>' containment reference.
	 * @see #setFather(FamilyMember)
	 * @see Families.FamiliesPackage#getFamily_Father()
	 * @see Families.FamilyMember#getFatherInverse
	 * @model opposite="fatherInverse" containment="true"
	 * @generated
	 */
	FamilyMember getFather();

	/**
	 * Sets the value of the '{@link Families.Family#getFather <em>Father</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Father</em>' containment reference.
	 * @see #getFather()
	 * @generated
	 */
	void setFather(FamilyMember value);

	/**
	 * Returns the value of the '<em><b>Mother</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link Families.FamilyMember#getMotherInverse <em>Mother Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mother</em>' containment reference.
	 * @see #setMother(FamilyMember)
	 * @see Families.FamiliesPackage#getFamily_Mother()
	 * @see Families.FamilyMember#getMotherInverse
	 * @model opposite="motherInverse" containment="true"
	 * @generated
	 */
	FamilyMember getMother();

	/**
	 * Sets the value of the '{@link Families.Family#getMother <em>Mother</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mother</em>' containment reference.
	 * @see #getMother()
	 * @generated
	 */
	void setMother(FamilyMember value);

	/**
	 * Returns the value of the '<em><b>Sons</b></em>' containment reference list.
	 * The list contents are of type {@link Families.FamilyMember}.
	 * It is bidirectional and its opposite is '{@link Families.FamilyMember#getSonsInverse <em>Sons Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sons</em>' containment reference list.
	 * @see Families.FamiliesPackage#getFamily_Sons()
	 * @see Families.FamilyMember#getSonsInverse
	 * @model opposite="sonsInverse" containment="true"
	 * @generated
	 */
	EList<FamilyMember> getSons();

	/**
	 * Returns the value of the '<em><b>Daughters</b></em>' containment reference list.
	 * The list contents are of type {@link Families.FamilyMember}.
	 * It is bidirectional and its opposite is '{@link Families.FamilyMember#getDaughtersInverse <em>Daughters Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Daughters</em>' containment reference list.
	 * @see Families.FamiliesPackage#getFamily_Daughters()
	 * @see Families.FamilyMember#getDaughtersInverse
	 * @model opposite="daughtersInverse" containment="true"
	 * @generated
	 */
	EList<FamilyMember> getDaughters();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Families.FamiliesPackage#getFamily_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Families.Family#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Families Inverse</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link Families.FamilyRegister#getFamilies <em>Families</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Families Inverse</em>' container reference.
	 * @see #setFamiliesInverse(FamilyRegister)
	 * @see Families.FamiliesPackage#getFamily_FamiliesInverse()
	 * @see Families.FamilyRegister#getFamilies
	 * @model opposite="families" transient="false"
	 * @generated
	 */
	FamilyRegister getFamiliesInverse();

	/**
	 * Sets the value of the '{@link Families.Family#getFamiliesInverse <em>Families Inverse</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Families Inverse</em>' container reference.
	 * @see #getFamiliesInverse()
	 * @generated
	 */
	void setFamiliesInverse(FamilyRegister value);

} // Family
