/**
 */
package pivot;

import Families.FamilyMember;

import Persons.Person;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link pivot.Mapping#getMMap <em>MMap</em>}</li>
 *   <li>{@link pivot.Mapping#getPMap <em>PMap</em>}</li>
 * </ul>
 *
 * @see pivot.PivotPackage#getMapping()
 * @model
 * @generated
 */
public interface Mapping extends EObject {
	/**
	 * Returns the value of the '<em><b>MMap</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MMap</em>' reference.
	 * @see #setMMap(FamilyMember)
	 * @see pivot.PivotPackage#getMapping_MMap()
	 * @model annotation="meeduse association='mMap'"
	 * @generated
	 */
	FamilyMember getMMap();

	/**
	 * Sets the value of the '{@link pivot.Mapping#getMMap <em>MMap</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MMap</em>' reference.
	 * @see #getMMap()
	 * @generated
	 */
	void setMMap(FamilyMember value);

	/**
	 * Returns the value of the '<em><b>PMap</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>PMap</em>' reference.
	 * @see #setPMap(Person)
	 * @see pivot.PivotPackage#getMapping_PMap()
	 * @model annotation="meeduse association='pMap'"
	 * @generated
	 */
	Person getPMap();

	/**
	 * Sets the value of the '{@link pivot.Mapping#getPMap <em>PMap</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PMap</em>' reference.
	 * @see #getPMap()
	 * @generated
	 */
	void setPMap(Person value);

} // Mapping
