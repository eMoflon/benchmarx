/**
 */
package Families.impl;

import Families.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FamiliesFactoryImpl extends EFactoryImpl implements FamiliesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FamiliesFactory init() {
		try {
			FamiliesFactory theFamiliesFactory = (FamiliesFactory) EPackage.Registry.INSTANCE
					.getEFactory(FamiliesPackage.eNS_URI);
			if (theFamiliesFactory != null) {
				return theFamiliesFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FamiliesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FamiliesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case FamiliesPackage.FAMILY_REGISTER:
			return createFamilyRegister();
		case FamiliesPackage.FAMILY:
			return createFamily();
		case FamiliesPackage.FAMILY_MEMBER:
			return createFamilyMember();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FamilyRegister createFamilyRegister() {
		FamilyRegisterImpl familyRegister = new FamilyRegisterImpl();
		return familyRegister;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Family createFamily() {
		FamilyImpl family = new FamilyImpl();
		return family;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FamilyMember createFamilyMember() {
		FamilyMemberImpl familyMember = new FamilyMemberImpl();
		return familyMember;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FamiliesPackage getFamiliesPackage() {
		return (FamiliesPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FamiliesPackage getPackage() {
		return FamiliesPackage.eINSTANCE;
	}

} //FamiliesFactoryImpl
