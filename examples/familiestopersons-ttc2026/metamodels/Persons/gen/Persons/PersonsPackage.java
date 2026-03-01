/**
 */
package Persons;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see Persons.PersonsFactory
 * @model kind="package"
 * @generated
 */
public interface PersonsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Persons";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "platform:/plugin/Persons/model/Persons.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Persons";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PersonsPackage eINSTANCE = Persons.impl.PersonsPackageImpl.init();

	/**
	 * The meta object id for the '{@link Persons.impl.PersonRegisterImpl <em>Person Register</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Persons.impl.PersonRegisterImpl
	 * @see Persons.impl.PersonsPackageImpl#getPersonRegister()
	 * @generated
	 */
	int PERSON_REGISTER = 0;

	/**
	 * The feature id for the '<em><b>Persons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_REGISTER__PERSONS = 0;

	/**
	 * The number of structural features of the '<em>Person Register</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_REGISTER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Person Register</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_REGISTER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Persons.impl.PersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Persons.impl.PersonImpl
	 * @see Persons.impl.PersonsPackageImpl#getPerson()
	 * @generated
	 */
	int PERSON = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__NAME = 0;

	/**
	 * The feature id for the '<em><b>Birthday</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__BIRTHDAY = 1;

	/**
	 * The feature id for the '<em><b>Persons Inverse</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__PERSONS_INVERSE = 2;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Persons.impl.MaleImpl <em>Male</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Persons.impl.MaleImpl
	 * @see Persons.impl.PersonsPackageImpl#getMale()
	 * @generated
	 */
	int MALE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MALE__NAME = PERSON__NAME;

	/**
	 * The feature id for the '<em><b>Birthday</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MALE__BIRTHDAY = PERSON__BIRTHDAY;

	/**
	 * The feature id for the '<em><b>Persons Inverse</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MALE__PERSONS_INVERSE = PERSON__PERSONS_INVERSE;

	/**
	 * The number of structural features of the '<em>Male</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MALE_FEATURE_COUNT = PERSON_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Male</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MALE_OPERATION_COUNT = PERSON_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link Persons.impl.FemaleImpl <em>Female</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Persons.impl.FemaleImpl
	 * @see Persons.impl.PersonsPackageImpl#getFemale()
	 * @generated
	 */
	int FEMALE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEMALE__NAME = PERSON__NAME;

	/**
	 * The feature id for the '<em><b>Birthday</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEMALE__BIRTHDAY = PERSON__BIRTHDAY;

	/**
	 * The feature id for the '<em><b>Persons Inverse</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEMALE__PERSONS_INVERSE = PERSON__PERSONS_INVERSE;

	/**
	 * The number of structural features of the '<em>Female</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEMALE_FEATURE_COUNT = PERSON_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Female</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEMALE_OPERATION_COUNT = PERSON_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link Persons.PersonRegister <em>Person Register</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person Register</em>'.
	 * @see Persons.PersonRegister
	 * @generated
	 */
	EClass getPersonRegister();

	/**
	 * Returns the meta object for the containment reference list '{@link Persons.PersonRegister#getPersons <em>Persons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persons</em>'.
	 * @see Persons.PersonRegister#getPersons()
	 * @see #getPersonRegister()
	 * @generated
	 */
	EReference getPersonRegister_Persons();

	/**
	 * Returns the meta object for class '{@link Persons.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see Persons.Person
	 * @generated
	 */
	EClass getPerson();

	/**
	 * Returns the meta object for the attribute '{@link Persons.Person#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see Persons.Person#getName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Name();

	/**
	 * Returns the meta object for the attribute '{@link Persons.Person#getBirthday <em>Birthday</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Birthday</em>'.
	 * @see Persons.Person#getBirthday()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Birthday();

	/**
	 * Returns the meta object for the container reference '{@link Persons.Person#getPersonsInverse <em>Persons Inverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Persons Inverse</em>'.
	 * @see Persons.Person#getPersonsInverse()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_PersonsInverse();

	/**
	 * Returns the meta object for class '{@link Persons.Male <em>Male</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Male</em>'.
	 * @see Persons.Male
	 * @generated
	 */
	EClass getMale();

	/**
	 * Returns the meta object for class '{@link Persons.Female <em>Female</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Female</em>'.
	 * @see Persons.Female
	 * @generated
	 */
	EClass getFemale();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PersonsFactory getPersonsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link Persons.impl.PersonRegisterImpl <em>Person Register</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Persons.impl.PersonRegisterImpl
		 * @see Persons.impl.PersonsPackageImpl#getPersonRegister()
		 * @generated
		 */
		EClass PERSON_REGISTER = eINSTANCE.getPersonRegister();

		/**
		 * The meta object literal for the '<em><b>Persons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON_REGISTER__PERSONS = eINSTANCE.getPersonRegister_Persons();

		/**
		 * The meta object literal for the '{@link Persons.impl.PersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Persons.impl.PersonImpl
		 * @see Persons.impl.PersonsPackageImpl#getPerson()
		 * @generated
		 */
		EClass PERSON = eINSTANCE.getPerson();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__NAME = eINSTANCE.getPerson_Name();

		/**
		 * The meta object literal for the '<em><b>Birthday</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__BIRTHDAY = eINSTANCE.getPerson_Birthday();

		/**
		 * The meta object literal for the '<em><b>Persons Inverse</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__PERSONS_INVERSE = eINSTANCE.getPerson_PersonsInverse();

		/**
		 * The meta object literal for the '{@link Persons.impl.MaleImpl <em>Male</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Persons.impl.MaleImpl
		 * @see Persons.impl.PersonsPackageImpl#getMale()
		 * @generated
		 */
		EClass MALE = eINSTANCE.getMale();

		/**
		 * The meta object literal for the '{@link Persons.impl.FemaleImpl <em>Female</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Persons.impl.FemaleImpl
		 * @see Persons.impl.PersonsPackageImpl#getFemale()
		 * @generated
		 */
		EClass FEMALE = eINSTANCE.getFemale();

	}

} //PersonsPackage
