package FamiliesSmartEMF.impl;

import FamiliesSmartEMF.FamilyRegister;
import FamiliesSmartEMF.Family;
import FamiliesSmartEMF.FamilyMember;


import FamiliesSmartEMF.FamiliesSmartEMFFactory;
import FamiliesSmartEMF.FamiliesSmartEMFPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

public class FamiliesSmartEMFFactoryImpl extends EFactoryImpl implements FamiliesSmartEMF.FamiliesSmartEMFFactory {

	public static FamiliesSmartEMF.FamiliesSmartEMFFactory init() {
		try {
			FamiliesSmartEMFFactory theFamiliesSmartEMFFactory = (FamiliesSmartEMFFactory) EPackage.Registry.INSTANCE
					.getEFactory(FamiliesSmartEMFPackage.eNS_URI);
			if (theFamiliesSmartEMFFactory != null) {
				return theFamiliesSmartEMFFactory;
			}
		} catch (java.lang.Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FamiliesSmartEMFFactoryImpl();
	}

	public FamiliesSmartEMFFactoryImpl() {
		super();
	}

	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case FamiliesSmartEMFPackage.FAMILY_REGISTER:
			return createFamilyRegister();
		case FamiliesSmartEMFPackage.FAMILY:
			return createFamily();
		case FamiliesSmartEMFPackage.FAMILY_MEMBER:
			return createFamilyMember();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}
	
	
	@Override
	public FamiliesSmartEMF.FamilyRegister createFamilyRegister() {
		FamilyRegisterImpl familyRegister = new FamilyRegisterImpl();
		return familyRegister;
	}
	@Override
	public FamiliesSmartEMF.Family createFamily() {
		FamilyImpl family = new FamilyImpl();
		return family;
	}
	@Override
	public FamiliesSmartEMF.FamilyMember createFamilyMember() {
		FamilyMemberImpl familyMember = new FamilyMemberImpl();
		return familyMember;
	}
	

	@Override
	public FamiliesSmartEMFPackage getFamiliesSmartEMFPackage() {
	return (FamiliesSmartEMFPackage) getEPackage();
	}
} 
