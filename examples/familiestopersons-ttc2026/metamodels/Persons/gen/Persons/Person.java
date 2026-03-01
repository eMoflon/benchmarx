/**
 */
package Persons;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Persons.Person#getName <em>Name</em>}</li>
 *   <li>{@link Persons.Person#getBirthday <em>Birthday</em>}</li>
 *   <li>{@link Persons.Person#getPersonsInverse <em>Persons Inverse</em>}</li>
 * </ul>
 *
 * @see Persons.PersonsPackage#getPerson()
 * @model abstract="true"
 * @generated
 */
public interface Person extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Persons.PersonsPackage#getPerson_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Persons.Person#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Birthday</b></em>' attribute.
	 * The default value is <code>"0000-1-1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Birthday</em>' attribute.
	 * @see #setBirthday(Date)
	 * @see Persons.PersonsPackage#getPerson_Birthday()
	 * @model default="0000-1-1"
	 * @generated
	 */
	Date getBirthday();

	/**
	 * Sets the value of the '{@link Persons.Person#getBirthday <em>Birthday</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Birthday</em>' attribute.
	 * @see #getBirthday()
	 * @generated
	 */
	void setBirthday(Date value);

	/**
	 * Returns the value of the '<em><b>Persons Inverse</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link Persons.PersonRegister#getPersons <em>Persons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persons Inverse</em>' container reference.
	 * @see #setPersonsInverse(PersonRegister)
	 * @see Persons.PersonsPackage#getPerson_PersonsInverse()
	 * @see Persons.PersonRegister#getPersons
	 * @model opposite="persons" transient="false"
	 * @generated
	 */
	PersonRegister getPersonsInverse();

	/**
	 * Sets the value of the '{@link Persons.Person#getPersonsInverse <em>Persons Inverse</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persons Inverse</em>' container reference.
	 * @see #getPersonsInverse()
	 * @generated
	 */
	void setPersonsInverse(PersonRegister value);

} // Person
