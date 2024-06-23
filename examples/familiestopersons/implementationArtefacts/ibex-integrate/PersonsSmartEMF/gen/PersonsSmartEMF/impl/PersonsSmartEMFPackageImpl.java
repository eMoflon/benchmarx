package PersonsSmartEMF.impl;

import PersonsSmartEMF.PersonRegister;
import PersonsSmartEMF.Person;
import PersonsSmartEMF.Male;
import PersonsSmartEMF.Female;


import PersonsSmartEMF.PersonsSmartEMFFactory;
import PersonsSmartEMF.PersonsSmartEMFPackage;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.emoflon.smartemf.runtime.SmartPackageImpl;

public class PersonsSmartEMFPackageImpl extends SmartPackageImpl
		implements PersonsSmartEMFPackage {
			
	private EClass personRegisterEClass = null;
	private EReference personRegister_personsEReference = null;
	private EClass personEClass = null;
	private EAttribute person_nameEAttribute = null;
	private EAttribute person_birthdayEAttribute = null;
	private EReference person_personsInverseEReference = null;
	private EClass maleEClass = null;
	private EClass femaleEClass = null;
	
	

	private PersonsSmartEMFPackageImpl() {
		super(eNS_URI, PersonsSmartEMF.PersonsSmartEMFFactory.eINSTANCE);
	}

	private static boolean isRegistered = false;
	private boolean isCreated = false;
	private boolean isInitialized = false;

	public static PersonsSmartEMFPackage init() {
		if (isRegistered)
			return (PersonsSmartEMFPackage) EPackage.Registry.INSTANCE
					.getEPackage(PersonsSmartEMFPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredPersonsSmartEMFPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		PersonsSmartEMFPackageImpl thePersonsSmartEMFPackage = registeredPersonsSmartEMFPackage instanceof PersonsSmartEMFPackageImpl
				? (PersonsSmartEMFPackageImpl) registeredPersonsSmartEMFPackage
				: new PersonsSmartEMFPackageImpl();

		isRegistered = true;

		// Create package meta-data objects
		thePersonsSmartEMFPackage.createPackageContents();

		// Initialize created meta-data
		thePersonsSmartEMFPackage.initializePackageContents();
		
		// Inject internal eOpposites to unidirectional references
		thePersonsSmartEMFPackage.injectDynamicOpposites();
		
		// Inject external references into foreign packages
		thePersonsSmartEMFPackage.injectExternalReferences();

		// Mark meta-data to indicate it can't be changed
		thePersonsSmartEMFPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PersonsSmartEMFPackage.eNS_URI,
				thePersonsSmartEMFPackage);
				
		thePersonsSmartEMFPackage.fetchDynamicEStructuralFeaturesOfSuperTypes();
		return thePersonsSmartEMFPackage;
	}

	@Override
	public EClass getPersonRegister() {
		return personRegisterEClass;
	}
	@Override
	public EReference getPersonRegister_Persons() {
		return personRegister_personsEReference;	
	}
	@Override
	public EClass getPerson() {
		return personEClass;
	}
	@Override
	public EAttribute getPerson_Name() {
		return person_nameEAttribute;	
	}
	@Override
	public EAttribute getPerson_Birthday() {
		return person_birthdayEAttribute;	
	}
	@Override
	public EReference getPerson_PersonsInverse() {
		return person_personsInverseEReference;	
	}
	@Override
	public EClass getMale() {
		return maleEClass;
	}
	@Override
	public EClass getFemale() {
		return femaleEClass;
	}
	
	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PersonsSmartEMF.PersonsSmartEMFFactory getPersonsSmartEMFFactory() {
		return (PersonsSmartEMF.PersonsSmartEMFFactory) getEFactoryInstance();
	}

	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		personRegisterEClass = createEClass(PERSON_REGISTER);
		createEReference(personRegisterEClass, PERSON_REGISTER__PERSONS);
		personRegister_personsEReference = (EReference) personRegisterEClass.getEStructuralFeatures().get(0);
		
		personEClass = createEClass(PERSON);
		createEAttribute(personEClass, PERSON__NAME);
		person_nameEAttribute = (EAttribute) personEClass.getEStructuralFeatures().get(0);
		createEAttribute(personEClass, PERSON__BIRTHDAY);
		person_birthdayEAttribute = (EAttribute) personEClass.getEStructuralFeatures().get(1);
		createEReference(personEClass, PERSON__PERSONS_INVERSE);
		person_personsInverseEReference = (EReference) personEClass.getEStructuralFeatures().get(2);
		
		maleEClass = createEClass(MALE);
		
		femaleEClass = createEClass(FEMALE);
		
		// Create enums
		
		// Create data types
	}

	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);
		
		// Obtain other dependent packages

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		maleEClass.getESuperTypes().add(this.getPerson());
		femaleEClass.getESuperTypes().add(this.getPerson());

		// Initialize classes, features, and operations; add parameters
		initEClass(personRegisterEClass, PersonRegister.class, "PersonRegister", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPersonRegister_Persons(), this.getPerson(), this.getPerson_PersonsInverse(), 
			"persons", null, 0, -1, PersonRegister.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(personEClass, Person.class, "Person", IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPerson_Name(), ecorePackage.getEString(),
			"name", null, 0, 1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPerson_Birthday(), ecorePackage.getEDate(),
			"birthday", "Thu Jan 01 00:00:00 CET 1", 0, 1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEReference(getPerson_PersonsInverse(), this.getPersonRegister(), this.getPersonRegister_Persons(), 
			"personsInverse", null, 0, 1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(maleEClass, Male.class, "Male", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		
		initEClass(femaleEClass, Female.class, "Female", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		
		
		// Initialize enums and add enum literals
		
		// Initialize data types
		
		// Create resource
		createResource(eNS_URI);
	}

} 

