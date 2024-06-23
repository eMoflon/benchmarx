package FamiliesSmartEMF.impl;

import FamiliesSmartEMF.FamilyRegister;
import FamiliesSmartEMF.Family;
import FamiliesSmartEMF.FamilyMember;


import FamiliesSmartEMF.FamiliesSmartEMFFactory;
import FamiliesSmartEMF.FamiliesSmartEMFPackage;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.emoflon.smartemf.runtime.SmartPackageImpl;

public class FamiliesSmartEMFPackageImpl extends SmartPackageImpl
		implements FamiliesSmartEMFPackage {
			
	private EClass familyRegisterEClass = null;
	private EReference familyRegister_familiesEReference = null;
	private EClass familyEClass = null;
	private EReference family_fatherEReference = null;
	private EReference family_motherEReference = null;
	private EReference family_sonsEReference = null;
	private EReference family_daughtersEReference = null;
	private EAttribute family_nameEAttribute = null;
	private EReference family_familiesInverseEReference = null;
	private EClass familyMemberEClass = null;
	private EAttribute familyMember_nameEAttribute = null;
	private EReference familyMember_fatherInverseEReference = null;
	private EReference familyMember_motherInverseEReference = null;
	private EReference familyMember_sonsInverseEReference = null;
	private EReference familyMember_daughtersInverseEReference = null;
	
	

	private FamiliesSmartEMFPackageImpl() {
		super(eNS_URI, FamiliesSmartEMF.FamiliesSmartEMFFactory.eINSTANCE);
	}

	private static boolean isRegistered = false;
	private boolean isCreated = false;
	private boolean isInitialized = false;

	public static FamiliesSmartEMFPackage init() {
		if (isRegistered)
			return (FamiliesSmartEMFPackage) EPackage.Registry.INSTANCE
					.getEPackage(FamiliesSmartEMFPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredFamiliesSmartEMFPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		FamiliesSmartEMFPackageImpl theFamiliesSmartEMFPackage = registeredFamiliesSmartEMFPackage instanceof FamiliesSmartEMFPackageImpl
				? (FamiliesSmartEMFPackageImpl) registeredFamiliesSmartEMFPackage
				: new FamiliesSmartEMFPackageImpl();

		isRegistered = true;

		// Create package meta-data objects
		theFamiliesSmartEMFPackage.createPackageContents();

		// Initialize created meta-data
		theFamiliesSmartEMFPackage.initializePackageContents();
		
		// Inject internal eOpposites to unidirectional references
		theFamiliesSmartEMFPackage.injectDynamicOpposites();
		
		// Inject external references into foreign packages
		theFamiliesSmartEMFPackage.injectExternalReferences();

		// Mark meta-data to indicate it can't be changed
		theFamiliesSmartEMFPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FamiliesSmartEMFPackage.eNS_URI,
				theFamiliesSmartEMFPackage);
				
		theFamiliesSmartEMFPackage.fetchDynamicEStructuralFeaturesOfSuperTypes();
		return theFamiliesSmartEMFPackage;
	}

	@Override
	public EClass getFamilyRegister() {
		return familyRegisterEClass;
	}
	@Override
	public EReference getFamilyRegister_Families() {
		return familyRegister_familiesEReference;	
	}
	@Override
	public EClass getFamily() {
		return familyEClass;
	}
	@Override
	public EReference getFamily_Father() {
		return family_fatherEReference;	
	}
	@Override
	public EReference getFamily_Mother() {
		return family_motherEReference;	
	}
	@Override
	public EReference getFamily_Sons() {
		return family_sonsEReference;	
	}
	@Override
	public EReference getFamily_Daughters() {
		return family_daughtersEReference;	
	}
	@Override
	public EAttribute getFamily_Name() {
		return family_nameEAttribute;	
	}
	@Override
	public EReference getFamily_FamiliesInverse() {
		return family_familiesInverseEReference;	
	}
	@Override
	public EClass getFamilyMember() {
		return familyMemberEClass;
	}
	@Override
	public EAttribute getFamilyMember_Name() {
		return familyMember_nameEAttribute;	
	}
	@Override
	public EReference getFamilyMember_FatherInverse() {
		return familyMember_fatherInverseEReference;	
	}
	@Override
	public EReference getFamilyMember_MotherInverse() {
		return familyMember_motherInverseEReference;	
	}
	@Override
	public EReference getFamilyMember_SonsInverse() {
		return familyMember_sonsInverseEReference;	
	}
	@Override
	public EReference getFamilyMember_DaughtersInverse() {
		return familyMember_daughtersInverseEReference;	
	}
	
	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FamiliesSmartEMF.FamiliesSmartEMFFactory getFamiliesSmartEMFFactory() {
		return (FamiliesSmartEMF.FamiliesSmartEMFFactory) getEFactoryInstance();
	}

	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		familyRegisterEClass = createEClass(FAMILY_REGISTER);
		createEReference(familyRegisterEClass, FAMILY_REGISTER__FAMILIES);
		familyRegister_familiesEReference = (EReference) familyRegisterEClass.getEStructuralFeatures().get(0);
		
		familyEClass = createEClass(FAMILY);
		createEReference(familyEClass, FAMILY__FATHER);
		family_fatherEReference = (EReference) familyEClass.getEStructuralFeatures().get(0);
		createEReference(familyEClass, FAMILY__MOTHER);
		family_motherEReference = (EReference) familyEClass.getEStructuralFeatures().get(1);
		createEReference(familyEClass, FAMILY__SONS);
		family_sonsEReference = (EReference) familyEClass.getEStructuralFeatures().get(2);
		createEReference(familyEClass, FAMILY__DAUGHTERS);
		family_daughtersEReference = (EReference) familyEClass.getEStructuralFeatures().get(3);
		createEAttribute(familyEClass, FAMILY__NAME);
		family_nameEAttribute = (EAttribute) familyEClass.getEStructuralFeatures().get(4);
		createEReference(familyEClass, FAMILY__FAMILIES_INVERSE);
		family_familiesInverseEReference = (EReference) familyEClass.getEStructuralFeatures().get(5);
		
		familyMemberEClass = createEClass(FAMILY_MEMBER);
		createEAttribute(familyMemberEClass, FAMILY_MEMBER__NAME);
		familyMember_nameEAttribute = (EAttribute) familyMemberEClass.getEStructuralFeatures().get(0);
		createEReference(familyMemberEClass, FAMILY_MEMBER__FATHER_INVERSE);
		familyMember_fatherInverseEReference = (EReference) familyMemberEClass.getEStructuralFeatures().get(1);
		createEReference(familyMemberEClass, FAMILY_MEMBER__MOTHER_INVERSE);
		familyMember_motherInverseEReference = (EReference) familyMemberEClass.getEStructuralFeatures().get(2);
		createEReference(familyMemberEClass, FAMILY_MEMBER__SONS_INVERSE);
		familyMember_sonsInverseEReference = (EReference) familyMemberEClass.getEStructuralFeatures().get(3);
		createEReference(familyMemberEClass, FAMILY_MEMBER__DAUGHTERS_INVERSE);
		familyMember_daughtersInverseEReference = (EReference) familyMemberEClass.getEStructuralFeatures().get(4);
		
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

		// Initialize classes, features, and operations; add parameters
		initEClass(familyRegisterEClass, FamilyRegister.class, "FamilyRegister", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFamilyRegister_Families(), this.getFamily(), this.getFamily_FamiliesInverse(), 
			"families", null, 0, -1, FamilyRegister.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(familyEClass, Family.class, "Family", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFamily_Father(), this.getFamilyMember(), this.getFamilyMember_FatherInverse(), 
			"father", null, 0, 1, Family.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFamily_Mother(), this.getFamilyMember(), this.getFamilyMember_MotherInverse(), 
			"mother", null, 0, 1, Family.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFamily_Sons(), this.getFamilyMember(), this.getFamilyMember_SonsInverse(), 
			"sons", null, 0, -1, Family.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFamily_Daughters(), this.getFamilyMember(), this.getFamilyMember_DaughtersInverse(), 
			"daughters", null, 0, -1, Family.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFamily_Name(), ecorePackage.getEString(),
			"name", null, 0, 1, Family.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEReference(getFamily_FamiliesInverse(), this.getFamilyRegister(), this.getFamilyRegister_Families(), 
			"familiesInverse", null, 0, 1, Family.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(familyMemberEClass, FamilyMember.class, "FamilyMember", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFamilyMember_Name(), ecorePackage.getEString(),
			"name", null, 0, 1, FamilyMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEReference(getFamilyMember_FatherInverse(), this.getFamily(), this.getFamily_Father(), 
			"fatherInverse", null, 0, 1, FamilyMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFamilyMember_MotherInverse(), this.getFamily(), this.getFamily_Mother(), 
			"motherInverse", null, 0, 1, FamilyMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFamilyMember_SonsInverse(), this.getFamily(), this.getFamily_Sons(), 
			"sonsInverse", null, 0, 1, FamilyMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFamilyMember_DaughtersInverse(), this.getFamily(), this.getFamily_Daughters(), 
			"daughtersInverse", null, 0, 1, FamilyMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		
		// Initialize enums and add enum literals
		
		// Initialize data types
		
		// Create resource
		createResource(eNS_URI);
	}

} 

