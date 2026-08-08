/**
 */
package pivot.impl;

import Families.FamiliesPackage;

import Persons.PersonsPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import pivot.Mapping;
import pivot.Pivot;
import pivot.PivotFactory;
import pivot.PivotPackage;
import pivot.PostCondition;
import pivot.PreCondition;
import pivot.Strategy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PivotPackageImpl extends EPackageImpl implements PivotPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pivotEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass preConditionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass postConditionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum strategyEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see pivot.PivotPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PivotPackageImpl() {
		super(eNS_URI, PivotFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link PivotPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PivotPackage init() {
		if (isInited) return (PivotPackage)EPackage.Registry.INSTANCE.getEPackage(PivotPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredPivotPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		PivotPackageImpl thePivotPackage = registeredPivotPackage instanceof PivotPackageImpl ? (PivotPackageImpl)registeredPivotPackage : new PivotPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		FamiliesPackage.eINSTANCE.eClass();
		PersonsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePivotPackage.createPackageContents();

		// Initialize created meta-data
		thePivotPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePivotPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PivotPackage.eNS_URI, thePivotPackage);
		return thePivotPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPivot() {
		return pivotEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPivot_FamilyModel() {
		return (EReference)pivotEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPivot_PersonModel() {
		return (EReference)pivotEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPivot_Pre() {
		return (EReference)pivotEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPivot_Post() {
		return (EReference)pivotEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPivot_Mapping() {
		return (EReference)pivotEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPivot_Name() {
		return (EAttribute)pivotEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPivot_Strategie() {
		return (EAttribute)pivotEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPivot_FAMILY_TO_NEW() {
		return (EAttribute)pivotEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPivot_PARENT_TO_CHILD() {
		return (EAttribute)pivotEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPivot_Sync() {
		return (EAttribute)pivotEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPreCondition() {
		return preConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPreCondition_Input() {
		return (EReference)preConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPreCondition_Output() {
		return (EReference)preConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPreCondition_Name() {
		return (EAttribute)preConditionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPostCondition() {
		return postConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPostCondition_Input() {
		return (EReference)postConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPostCondition_Output() {
		return (EReference)postConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPostCondition_Name() {
		return (EAttribute)postConditionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMapping() {
		return mappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMapping_MMap() {
		return (EReference)mappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMapping_PMap() {
		return (EReference)mappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getStrategy() {
		return strategyEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PivotFactory getPivotFactory() {
		return (PivotFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		pivotEClass = createEClass(PIVOT);
		createEReference(pivotEClass, PIVOT__FAMILY_MODEL);
		createEReference(pivotEClass, PIVOT__PERSON_MODEL);
		createEReference(pivotEClass, PIVOT__PRE);
		createEReference(pivotEClass, PIVOT__POST);
		createEReference(pivotEClass, PIVOT__MAPPING);
		createEAttribute(pivotEClass, PIVOT__NAME);
		createEAttribute(pivotEClass, PIVOT__STRATEGIE);
		createEAttribute(pivotEClass, PIVOT__FAMILY_TO_NEW);
		createEAttribute(pivotEClass, PIVOT__PARENT_TO_CHILD);
		createEAttribute(pivotEClass, PIVOT__SYNC);

		preConditionEClass = createEClass(PRE_CONDITION);
		createEReference(preConditionEClass, PRE_CONDITION__OUTPUT);
		createEReference(preConditionEClass, PRE_CONDITION__INPUT);
		createEAttribute(preConditionEClass, PRE_CONDITION__NAME);

		postConditionEClass = createEClass(POST_CONDITION);
		createEReference(postConditionEClass, POST_CONDITION__OUTPUT);
		createEReference(postConditionEClass, POST_CONDITION__INPUT);
		createEAttribute(postConditionEClass, POST_CONDITION__NAME);

		mappingEClass = createEClass(MAPPING);
		createEReference(mappingEClass, MAPPING__MMAP);
		createEReference(mappingEClass, MAPPING__PMAP);

		// Create enums
		strategyEEnum = createEEnum(STRATEGY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		FamiliesPackage theFamiliesPackage = (FamiliesPackage)EPackage.Registry.INSTANCE.getEPackage(FamiliesPackage.eNS_URI);
		PersonsPackage thePersonsPackage = (PersonsPackage)EPackage.Registry.INSTANCE.getEPackage(PersonsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(pivotEClass, Pivot.class, "Pivot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPivot_FamilyModel(), theFamiliesPackage.getFamilyRegister(), null, "familyModel", null, 1, 1, Pivot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPivot_PersonModel(), thePersonsPackage.getPersonRegister(), null, "personModel", null, 1, 1, Pivot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPivot_Pre(), this.getPreCondition(), null, "pre", null, 0, -1, Pivot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPivot_Post(), this.getPostCondition(), null, "post", null, 0, -1, Pivot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPivot_Mapping(), this.getMapping(), null, "mapping", null, 0, -1, Pivot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPivot_Name(), ecorePackage.getEString(), "name", null, 0, 1, Pivot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPivot_Strategie(), this.getStrategy(), "strategie", null, 1, 1, Pivot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPivot_FAMILY_TO_NEW(), ecorePackage.getEBoolean(), "FAMILY_TO_NEW", null, 1, 1, Pivot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPivot_PARENT_TO_CHILD(), ecorePackage.getEBoolean(), "PARENT_TO_CHILD", null, 1, 1, Pivot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPivot_Sync(), ecorePackage.getEBoolean(), "sync", null, 1, 1, Pivot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(preConditionEClass, PreCondition.class, "PreCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPreCondition_Output(), thePersonsPackage.getPersonRegister(), null, "output", null, 1, 1, PreCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPreCondition_Input(), theFamiliesPackage.getFamilyRegister(), null, "input", null, 1, 1, PreCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPreCondition_Name(), ecorePackage.getEString(), "name", null, 1, 1, PreCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(postConditionEClass, PostCondition.class, "PostCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPostCondition_Output(), thePersonsPackage.getPersonRegister(), null, "output", null, 1, 1, PostCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPostCondition_Input(), theFamiliesPackage.getFamilyRegister(), null, "input", null, 1, 1, PostCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPostCondition_Name(), ecorePackage.getEString(), "name", null, 1, 1, PostCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mappingEClass, Mapping.class, "Mapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMapping_MMap(), theFamiliesPackage.getFamilyMember(), null, "mMap", null, 0, 1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMapping_PMap(), thePersonsPackage.getPerson(), null, "pMap", null, 0, 1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(strategyEEnum, Strategy.class, "Strategy");
		addEEnumLiteral(strategyEEnum, Strategy.BWD);
		addEEnumLiteral(strategyEEnum, Strategy.FWD);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// meeduse
		createMeeduseAnnotations();
	}

	/**
	 * Initializes the annotations for <b>meeduse</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createMeeduseAnnotations() {
		String source = "meeduse";
		addAnnotation
		  (pivotEClass,
		   source,
		   new String[] {
			   "constant", ""
		   });
		addAnnotation
		  (getPivot_FamilyModel(),
		   source,
		   new String[] {
			   "association", "familyModel"
		   });
		addAnnotation
		  (getPivot_PersonModel(),
		   source,
		   new String[] {
			   "association", "personModel"
		   });
		addAnnotation
		  (getPivot_Pre(),
		   source,
		   new String[] {
			   "association", "pre"
		   });
		addAnnotation
		  (getPivot_Post(),
		   source,
		   new String[] {
			   "association", "post"
		   });
		addAnnotation
		  (getPivot_Mapping(),
		   source,
		   new String[] {
			   "association", "mapping",
			   "opposite-lower", "1"
		   });
		addAnnotation
		  (preConditionEClass,
		   source,
		   new String[] {
			   "constant", ""
		   });
		addAnnotation
		  (getPreCondition_Output(),
		   source,
		   new String[] {
			   "constant", "",
			   "association", "outputPre"
		   });
		addAnnotation
		  (getPreCondition_Input(),
		   source,
		   new String[] {
			   "constant", "",
			   "association", "inputPre"
		   });
		addAnnotation
		  (getPreCondition_Name(),
		   source,
		   new String[] {
			   "constant", ""
		   });
		addAnnotation
		  (postConditionEClass,
		   source,
		   new String[] {
			   "constant", ""
		   });
		addAnnotation
		  (getPostCondition_Output(),
		   source,
		   new String[] {
			   "constant", "",
			   "association", "outputPost"
		   });
		addAnnotation
		  (getPostCondition_Input(),
		   source,
		   new String[] {
			   "constant", "",
			   "association", "inputPost"
		   });
		addAnnotation
		  (getPostCondition_Name(),
		   source,
		   new String[] {
			   "constant", ""
		   });
		addAnnotation
		  (getMapping_MMap(),
		   source,
		   new String[] {
			   "association", "mMap"
		   });
		addAnnotation
		  (getMapping_PMap(),
		   source,
		   new String[] {
			   "association", "pMap"
		   });
	}

} //PivotPackageImpl
