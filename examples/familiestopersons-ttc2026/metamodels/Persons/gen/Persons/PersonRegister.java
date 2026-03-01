/**
 */
package Persons;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person Register</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Persons.PersonRegister#getPersons <em>Persons</em>}</li>
 * </ul>
 *
 * @see Persons.PersonsPackage#getPersonRegister()
 * @model
 * @generated
 */
public interface PersonRegister extends EObject {
	/**
	 * Returns the value of the '<em><b>Persons</b></em>' containment reference list.
	 * The list contents are of type {@link Persons.Person}.
	 * It is bidirectional and its opposite is '{@link Persons.Person#getPersonsInverse <em>Persons Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persons</em>' containment reference list.
	 * @see Persons.PersonsPackage#getPersonRegister_Persons()
	 * @see Persons.Person#getPersonsInverse
	 * @model opposite="personsInverse" containment="true"
	 * @generated
	 */
	EList<Person> getPersons();

} // PersonRegister
