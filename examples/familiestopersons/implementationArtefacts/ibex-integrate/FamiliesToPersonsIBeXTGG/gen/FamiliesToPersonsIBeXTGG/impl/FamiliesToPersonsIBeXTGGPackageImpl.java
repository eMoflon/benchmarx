package FamiliesToPersonsIBeXTGG.impl;

import FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr;
import FamiliesToPersonsIBeXTGG.FamilyToRegisterCorr;
import FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr;
import FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily;
import FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale;
import FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale;
import FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons;
import FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale;
import FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale;
import FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale;
import FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale;
import FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale;
import FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale;


import FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGFactory;
import FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage;

import TGGRuntimeModel.TGGRuntimeModelPackage;
import FamiliesSmartEMF.FamiliesSmartEMFPackage;
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

public class FamiliesToPersonsIBeXTGGPackageImpl extends SmartPackageImpl
		implements FamiliesToPersonsIBeXTGGPackage {
			
	private EClass registerToRegisterCorrEClass = null;
	private EReference registerToRegisterCorr_sourceEReference = null;
	private EReference registerToRegisterCorr_targetEReference = null;
	private EClass familyToRegisterCorrEClass = null;
	private EReference familyToRegisterCorr_sourceEReference = null;
	private EReference familyToRegisterCorr_targetEReference = null;
	private EClass familyMemberToPersonCorrEClass = null;
	private EReference familyMemberToPersonCorr_sourceEReference = null;
	private EReference familyMemberToPersonCorr_targetEReference = null;
	private EClass protocolNode_CreateFamilyEClass = null;
	private EReference protocolNode_CreateFamily_cONTEXT__SOURCE__familiesEReference = null;
	private EReference protocolNode_CreateFamily_cREATE__SOURCE__familyEReference = null;
	private EClass protocolNode_DaughterOfExistingFamilyToFemaleEClass = null;
	private EReference protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__SOURCE__fEReference = null;
	private EReference protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__SOURCE__familiesEReference = null;
	private EReference protocolNode_DaughterOfExistingFamilyToFemale_cREATE__SOURCE__fmEReference = null;
	private EReference protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference = null;
	private EReference protocolNode_DaughterOfExistingFamilyToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = null;
	private EReference protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__TARGET__personsEReference = null;
	private EReference protocolNode_DaughterOfExistingFamilyToFemale_cREATE__TARGET__pEReference = null;
	private EClass protocolNode_DaughterToFemaleEClass = null;
	private EReference protocolNode_DaughterToFemale_cONTEXT__SOURCE__familiesEReference = null;
	private EReference protocolNode_DaughterToFemale_cREATE__SOURCE__fEReference = null;
	private EReference protocolNode_DaughterToFemale_cREATE__SOURCE__fmEReference = null;
	private EReference protocolNode_DaughterToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference = null;
	private EReference protocolNode_DaughterToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = null;
	private EReference protocolNode_DaughterToFemale_cONTEXT__TARGET__personsEReference = null;
	private EReference protocolNode_DaughterToFemale_cREATE__TARGET__pEReference = null;
	private EClass protocolNode_Families2PersonsEClass = null;
	private EReference protocolNode_Families2Persons_cREATE__SOURCE__familiesEReference = null;
	private EReference protocolNode_Families2Persons_cREATE__CORRESPONDENCE__families2personsEReference = null;
	private EReference protocolNode_Families2Persons_cREATE__TARGET__personsEReference = null;
	private EClass protocolNode_FatherOfExistingFamilyToMaleEClass = null;
	private EReference protocolNode_FatherOfExistingFamilyToMale_cONTEXT__SOURCE__fEReference = null;
	private EReference protocolNode_FatherOfExistingFamilyToMale_cONTEXT__SOURCE__familiesEReference = null;
	private EReference protocolNode_FatherOfExistingFamilyToMale_cREATE__SOURCE__fmEReference = null;
	private EReference protocolNode_FatherOfExistingFamilyToMale_cONTEXT__CORRESPONDENCE__families2personsEReference = null;
	private EReference protocolNode_FatherOfExistingFamilyToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = null;
	private EReference protocolNode_FatherOfExistingFamilyToMale_cONTEXT__TARGET__personsEReference = null;
	private EReference protocolNode_FatherOfExistingFamilyToMale_cREATE__TARGET__pEReference = null;
	private EClass protocolNode_FatherToMaleEClass = null;
	private EReference protocolNode_FatherToMale_cONTEXT__SOURCE__familiesEReference = null;
	private EReference protocolNode_FatherToMale_cREATE__SOURCE__fEReference = null;
	private EReference protocolNode_FatherToMale_cREATE__SOURCE__fmEReference = null;
	private EReference protocolNode_FatherToMale_cONTEXT__CORRESPONDENCE__families2personsEReference = null;
	private EReference protocolNode_FatherToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = null;
	private EReference protocolNode_FatherToMale_cONTEXT__TARGET__personsEReference = null;
	private EReference protocolNode_FatherToMale_cREATE__TARGET__pEReference = null;
	private EClass protocolNode_MotherOfExistingFamilyToFemaleEClass = null;
	private EReference protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__SOURCE__fEReference = null;
	private EReference protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__SOURCE__familiesEReference = null;
	private EReference protocolNode_MotherOfExistingFamilyToFemale_cREATE__SOURCE__fmEReference = null;
	private EReference protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference = null;
	private EReference protocolNode_MotherOfExistingFamilyToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = null;
	private EReference protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__TARGET__personsEReference = null;
	private EReference protocolNode_MotherOfExistingFamilyToFemale_cREATE__TARGET__pEReference = null;
	private EClass protocolNode_MotherToFemaleEClass = null;
	private EReference protocolNode_MotherToFemale_cONTEXT__SOURCE__familiesEReference = null;
	private EReference protocolNode_MotherToFemale_cREATE__SOURCE__fEReference = null;
	private EReference protocolNode_MotherToFemale_cREATE__SOURCE__fmEReference = null;
	private EReference protocolNode_MotherToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference = null;
	private EReference protocolNode_MotherToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = null;
	private EReference protocolNode_MotherToFemale_cONTEXT__TARGET__personsEReference = null;
	private EReference protocolNode_MotherToFemale_cREATE__TARGET__pEReference = null;
	private EClass protocolNode_SonOfExistingFamilyToMaleEClass = null;
	private EReference protocolNode_SonOfExistingFamilyToMale_cONTEXT__SOURCE__fEReference = null;
	private EReference protocolNode_SonOfExistingFamilyToMale_cONTEXT__SOURCE__familiesEReference = null;
	private EReference protocolNode_SonOfExistingFamilyToMale_cREATE__SOURCE__fmEReference = null;
	private EReference protocolNode_SonOfExistingFamilyToMale_cONTEXT__CORRESPONDENCE__families2personsEReference = null;
	private EReference protocolNode_SonOfExistingFamilyToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = null;
	private EReference protocolNode_SonOfExistingFamilyToMale_cONTEXT__TARGET__personsEReference = null;
	private EReference protocolNode_SonOfExistingFamilyToMale_cREATE__TARGET__pEReference = null;
	private EClass protocolNode_SonToMaleEClass = null;
	private EReference protocolNode_SonToMale_cONTEXT__SOURCE__familiesEReference = null;
	private EReference protocolNode_SonToMale_cREATE__SOURCE__fEReference = null;
	private EReference protocolNode_SonToMale_cREATE__SOURCE__fmEReference = null;
	private EReference protocolNode_SonToMale_cONTEXT__CORRESPONDENCE__families2personsEReference = null;
	private EReference protocolNode_SonToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = null;
	private EReference protocolNode_SonToMale_cONTEXT__TARGET__personsEReference = null;
	private EReference protocolNode_SonToMale_cREATE__TARGET__pEReference = null;
	
	

	private FamiliesToPersonsIBeXTGGPackageImpl() {
		super(eNS_URI, FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGFactory.eINSTANCE);
	}

	private static boolean isRegistered = false;
	private boolean isCreated = false;
	private boolean isInitialized = false;

	public static FamiliesToPersonsIBeXTGGPackage init() {
		if (isRegistered)
			return (FamiliesToPersonsIBeXTGGPackage) EPackage.Registry.INSTANCE
					.getEPackage(FamiliesToPersonsIBeXTGGPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredFamiliesToPersonsIBeXTGGPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		FamiliesToPersonsIBeXTGGPackageImpl theFamiliesToPersonsIBeXTGGPackage = registeredFamiliesToPersonsIBeXTGGPackage instanceof FamiliesToPersonsIBeXTGGPackageImpl
				? (FamiliesToPersonsIBeXTGGPackageImpl) registeredFamiliesToPersonsIBeXTGGPackage
				: new FamiliesToPersonsIBeXTGGPackageImpl();

		isRegistered = true;

		// Create package meta-data objects
		theFamiliesToPersonsIBeXTGGPackage.createPackageContents();

		// Initialize created meta-data
		theFamiliesToPersonsIBeXTGGPackage.initializePackageContents();
		
		// Inject internal eOpposites to unidirectional references
		theFamiliesToPersonsIBeXTGGPackage.injectDynamicOpposites();
		
		// Inject external references into foreign packages
		theFamiliesToPersonsIBeXTGGPackage.injectExternalReferences();

		// Mark meta-data to indicate it can't be changed
		theFamiliesToPersonsIBeXTGGPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FamiliesToPersonsIBeXTGGPackage.eNS_URI,
				theFamiliesToPersonsIBeXTGGPackage);
				
		theFamiliesToPersonsIBeXTGGPackage.fetchDynamicEStructuralFeaturesOfSuperTypes();
		return theFamiliesToPersonsIBeXTGGPackage;
	}

	@Override
	public EClass getRegisterToRegisterCorr() {
		return registerToRegisterCorrEClass;
	}
	@Override
	public EReference getRegisterToRegisterCorr_Source() {
		return registerToRegisterCorr_sourceEReference;	
	}
	@Override
	public EReference getRegisterToRegisterCorr_Target() {
		return registerToRegisterCorr_targetEReference;	
	}
	@Override
	public EClass getFamilyToRegisterCorr() {
		return familyToRegisterCorrEClass;
	}
	@Override
	public EReference getFamilyToRegisterCorr_Source() {
		return familyToRegisterCorr_sourceEReference;	
	}
	@Override
	public EReference getFamilyToRegisterCorr_Target() {
		return familyToRegisterCorr_targetEReference;	
	}
	@Override
	public EClass getFamilyMemberToPersonCorr() {
		return familyMemberToPersonCorrEClass;
	}
	@Override
	public EReference getFamilyMemberToPersonCorr_Source() {
		return familyMemberToPersonCorr_sourceEReference;	
	}
	@Override
	public EReference getFamilyMemberToPersonCorr_Target() {
		return familyMemberToPersonCorr_targetEReference;	
	}
	@Override
	public EClass getProtocolNode_CreateFamily() {
		return protocolNode_CreateFamilyEClass;
	}
	@Override
	public EReference getProtocolNode_CreateFamily_CONTEXT__SOURCE__families() {
		return protocolNode_CreateFamily_cONTEXT__SOURCE__familiesEReference;	
	}
	@Override
	public EReference getProtocolNode_CreateFamily_CREATE__SOURCE__family() {
		return protocolNode_CreateFamily_cREATE__SOURCE__familyEReference;	
	}
	@Override
	public EClass getProtocolNode_DaughterOfExistingFamilyToFemale() {
		return protocolNode_DaughterOfExistingFamilyToFemaleEClass;
	}
	@Override
	public EReference getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__f() {
		return protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__SOURCE__fEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__families() {
		return protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__SOURCE__familiesEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__SOURCE__fm() {
		return protocolNode_DaughterOfExistingFamilyToFemale_cREATE__SOURCE__fmEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons() {
		return protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons() {
		return protocolNode_DaughterOfExistingFamilyToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__TARGET__persons() {
		return protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__TARGET__personsEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__TARGET__p() {
		return protocolNode_DaughterOfExistingFamilyToFemale_cREATE__TARGET__pEReference;	
	}
	@Override
	public EClass getProtocolNode_DaughterToFemale() {
		return protocolNode_DaughterToFemaleEClass;
	}
	@Override
	public EReference getProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families() {
		return protocolNode_DaughterToFemale_cONTEXT__SOURCE__familiesEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterToFemale_CREATE__SOURCE__f() {
		return protocolNode_DaughterToFemale_cREATE__SOURCE__fEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterToFemale_CREATE__SOURCE__fm() {
		return protocolNode_DaughterToFemale_cREATE__SOURCE__fmEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterToFemale_CONTEXT__CORRESPONDENCE__families2persons() {
		return protocolNode_DaughterToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons() {
		return protocolNode_DaughterToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons() {
		return protocolNode_DaughterToFemale_cONTEXT__TARGET__personsEReference;	
	}
	@Override
	public EReference getProtocolNode_DaughterToFemale_CREATE__TARGET__p() {
		return protocolNode_DaughterToFemale_cREATE__TARGET__pEReference;	
	}
	@Override
	public EClass getProtocolNode_Families2Persons() {
		return protocolNode_Families2PersonsEClass;
	}
	@Override
	public EReference getProtocolNode_Families2Persons_CREATE__SOURCE__families() {
		return protocolNode_Families2Persons_cREATE__SOURCE__familiesEReference;	
	}
	@Override
	public EReference getProtocolNode_Families2Persons_CREATE__CORRESPONDENCE__families2persons() {
		return protocolNode_Families2Persons_cREATE__CORRESPONDENCE__families2personsEReference;	
	}
	@Override
	public EReference getProtocolNode_Families2Persons_CREATE__TARGET__persons() {
		return protocolNode_Families2Persons_cREATE__TARGET__personsEReference;	
	}
	@Override
	public EClass getProtocolNode_FatherOfExistingFamilyToMale() {
		return protocolNode_FatherOfExistingFamilyToMaleEClass;
	}
	@Override
	public EReference getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__f() {
		return protocolNode_FatherOfExistingFamilyToMale_cONTEXT__SOURCE__fEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__families() {
		return protocolNode_FatherOfExistingFamilyToMale_cONTEXT__SOURCE__familiesEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherOfExistingFamilyToMale_CREATE__SOURCE__fm() {
		return protocolNode_FatherOfExistingFamilyToMale_cREATE__SOURCE__fmEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons() {
		return protocolNode_FatherOfExistingFamilyToMale_cONTEXT__CORRESPONDENCE__families2personsEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons() {
		return protocolNode_FatherOfExistingFamilyToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__TARGET__persons() {
		return protocolNode_FatherOfExistingFamilyToMale_cONTEXT__TARGET__personsEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherOfExistingFamilyToMale_CREATE__TARGET__p() {
		return protocolNode_FatherOfExistingFamilyToMale_cREATE__TARGET__pEReference;	
	}
	@Override
	public EClass getProtocolNode_FatherToMale() {
		return protocolNode_FatherToMaleEClass;
	}
	@Override
	public EReference getProtocolNode_FatherToMale_CONTEXT__SOURCE__families() {
		return protocolNode_FatherToMale_cONTEXT__SOURCE__familiesEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherToMale_CREATE__SOURCE__f() {
		return protocolNode_FatherToMale_cREATE__SOURCE__fEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherToMale_CREATE__SOURCE__fm() {
		return protocolNode_FatherToMale_cREATE__SOURCE__fmEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherToMale_CONTEXT__CORRESPONDENCE__families2persons() {
		return protocolNode_FatherToMale_cONTEXT__CORRESPONDENCE__families2personsEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherToMale_CREATE__CORRESPONDENCE__familyMember2Persons() {
		return protocolNode_FatherToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherToMale_CONTEXT__TARGET__persons() {
		return protocolNode_FatherToMale_cONTEXT__TARGET__personsEReference;	
	}
	@Override
	public EReference getProtocolNode_FatherToMale_CREATE__TARGET__p() {
		return protocolNode_FatherToMale_cREATE__TARGET__pEReference;	
	}
	@Override
	public EClass getProtocolNode_MotherOfExistingFamilyToFemale() {
		return protocolNode_MotherOfExistingFamilyToFemaleEClass;
	}
	@Override
	public EReference getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__f() {
		return protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__SOURCE__fEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__families() {
		return protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__SOURCE__familiesEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__SOURCE__fm() {
		return protocolNode_MotherOfExistingFamilyToFemale_cREATE__SOURCE__fmEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons() {
		return protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons() {
		return protocolNode_MotherOfExistingFamilyToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__TARGET__persons() {
		return protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__TARGET__personsEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__TARGET__p() {
		return protocolNode_MotherOfExistingFamilyToFemale_cREATE__TARGET__pEReference;	
	}
	@Override
	public EClass getProtocolNode_MotherToFemale() {
		return protocolNode_MotherToFemaleEClass;
	}
	@Override
	public EReference getProtocolNode_MotherToFemale_CONTEXT__SOURCE__families() {
		return protocolNode_MotherToFemale_cONTEXT__SOURCE__familiesEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherToFemale_CREATE__SOURCE__f() {
		return protocolNode_MotherToFemale_cREATE__SOURCE__fEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherToFemale_CREATE__SOURCE__fm() {
		return protocolNode_MotherToFemale_cREATE__SOURCE__fmEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherToFemale_CONTEXT__CORRESPONDENCE__families2persons() {
		return protocolNode_MotherToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherToFemale_CREATE__CORRESPONDENCE__familyMember2Persons() {
		return protocolNode_MotherToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherToFemale_CONTEXT__TARGET__persons() {
		return protocolNode_MotherToFemale_cONTEXT__TARGET__personsEReference;	
	}
	@Override
	public EReference getProtocolNode_MotherToFemale_CREATE__TARGET__p() {
		return protocolNode_MotherToFemale_cREATE__TARGET__pEReference;	
	}
	@Override
	public EClass getProtocolNode_SonOfExistingFamilyToMale() {
		return protocolNode_SonOfExistingFamilyToMaleEClass;
	}
	@Override
	public EReference getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__f() {
		return protocolNode_SonOfExistingFamilyToMale_cONTEXT__SOURCE__fEReference;	
	}
	@Override
	public EReference getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__families() {
		return protocolNode_SonOfExistingFamilyToMale_cONTEXT__SOURCE__familiesEReference;	
	}
	@Override
	public EReference getProtocolNode_SonOfExistingFamilyToMale_CREATE__SOURCE__fm() {
		return protocolNode_SonOfExistingFamilyToMale_cREATE__SOURCE__fmEReference;	
	}
	@Override
	public EReference getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons() {
		return protocolNode_SonOfExistingFamilyToMale_cONTEXT__CORRESPONDENCE__families2personsEReference;	
	}
	@Override
	public EReference getProtocolNode_SonOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons() {
		return protocolNode_SonOfExistingFamilyToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference;	
	}
	@Override
	public EReference getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__TARGET__persons() {
		return protocolNode_SonOfExistingFamilyToMale_cONTEXT__TARGET__personsEReference;	
	}
	@Override
	public EReference getProtocolNode_SonOfExistingFamilyToMale_CREATE__TARGET__p() {
		return protocolNode_SonOfExistingFamilyToMale_cREATE__TARGET__pEReference;	
	}
	@Override
	public EClass getProtocolNode_SonToMale() {
		return protocolNode_SonToMaleEClass;
	}
	@Override
	public EReference getProtocolNode_SonToMale_CONTEXT__SOURCE__families() {
		return protocolNode_SonToMale_cONTEXT__SOURCE__familiesEReference;	
	}
	@Override
	public EReference getProtocolNode_SonToMale_CREATE__SOURCE__f() {
		return protocolNode_SonToMale_cREATE__SOURCE__fEReference;	
	}
	@Override
	public EReference getProtocolNode_SonToMale_CREATE__SOURCE__fm() {
		return protocolNode_SonToMale_cREATE__SOURCE__fmEReference;	
	}
	@Override
	public EReference getProtocolNode_SonToMale_CONTEXT__CORRESPONDENCE__families2persons() {
		return protocolNode_SonToMale_cONTEXT__CORRESPONDENCE__families2personsEReference;	
	}
	@Override
	public EReference getProtocolNode_SonToMale_CREATE__CORRESPONDENCE__familyMember2Persons() {
		return protocolNode_SonToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference;	
	}
	@Override
	public EReference getProtocolNode_SonToMale_CONTEXT__TARGET__persons() {
		return protocolNode_SonToMale_cONTEXT__TARGET__personsEReference;	
	}
	@Override
	public EReference getProtocolNode_SonToMale_CREATE__TARGET__p() {
		return protocolNode_SonToMale_cREATE__TARGET__pEReference;	
	}
	
	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGFactory getFamiliesToPersonsIBeXTGGFactory() {
		return (FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGFactory) getEFactoryInstance();
	}

	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		registerToRegisterCorrEClass = createEClass(REGISTER_TO_REGISTER_CORR);
		createEReference(registerToRegisterCorrEClass, REGISTER_TO_REGISTER_CORR__SOURCE);
		registerToRegisterCorr_sourceEReference = (EReference) registerToRegisterCorrEClass.getEStructuralFeatures().get(0);
		createEReference(registerToRegisterCorrEClass, REGISTER_TO_REGISTER_CORR__TARGET);
		registerToRegisterCorr_targetEReference = (EReference) registerToRegisterCorrEClass.getEStructuralFeatures().get(1);
		
		familyToRegisterCorrEClass = createEClass(FAMILY_TO_REGISTER_CORR);
		createEReference(familyToRegisterCorrEClass, FAMILY_TO_REGISTER_CORR__SOURCE);
		familyToRegisterCorr_sourceEReference = (EReference) familyToRegisterCorrEClass.getEStructuralFeatures().get(0);
		createEReference(familyToRegisterCorrEClass, FAMILY_TO_REGISTER_CORR__TARGET);
		familyToRegisterCorr_targetEReference = (EReference) familyToRegisterCorrEClass.getEStructuralFeatures().get(1);
		
		familyMemberToPersonCorrEClass = createEClass(FAMILY_MEMBER_TO_PERSON_CORR);
		createEReference(familyMemberToPersonCorrEClass, FAMILY_MEMBER_TO_PERSON_CORR__SOURCE);
		familyMemberToPersonCorr_sourceEReference = (EReference) familyMemberToPersonCorrEClass.getEStructuralFeatures().get(0);
		createEReference(familyMemberToPersonCorrEClass, FAMILY_MEMBER_TO_PERSON_CORR__TARGET);
		familyMemberToPersonCorr_targetEReference = (EReference) familyMemberToPersonCorrEClass.getEStructuralFeatures().get(1);
		
		protocolNode_CreateFamilyEClass = createEClass(PROTOCOL_NODE__CREATE_FAMILY);
		createEReference(protocolNode_CreateFamilyEClass, PROTOCOL_NODE__CREATE_FAMILY__CONTEX_T__SOURC_E__FAMILIES);
		protocolNode_CreateFamily_cONTEXT__SOURCE__familiesEReference = (EReference) protocolNode_CreateFamilyEClass.getEStructuralFeatures().get(0);
		createEReference(protocolNode_CreateFamilyEClass, PROTOCOL_NODE__CREATE_FAMILY__CREAT_E__SOURC_E__FAMILY);
		protocolNode_CreateFamily_cREATE__SOURCE__familyEReference = (EReference) protocolNode_CreateFamilyEClass.getEStructuralFeatures().get(1);
		
		protocolNode_DaughterOfExistingFamilyToFemaleEClass = createEClass(PROTOCOL_NODE__DAUGHTER_OF_EXISTING_FAMILY_TO_FEMALE);
		createEReference(protocolNode_DaughterOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__DAUGHTER_OF_EXISTING_FAMILY_TO_FEMALE__CONTEX_T__SOURC_E__F);
		protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__SOURCE__fEReference = (EReference) protocolNode_DaughterOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(0);
		createEReference(protocolNode_DaughterOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__DAUGHTER_OF_EXISTING_FAMILY_TO_FEMALE__CONTEX_T__SOURC_E__FAMILIES);
		protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__SOURCE__familiesEReference = (EReference) protocolNode_DaughterOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(1);
		createEReference(protocolNode_DaughterOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__DAUGHTER_OF_EXISTING_FAMILY_TO_FEMALE__CREAT_E__SOURC_E__FM);
		protocolNode_DaughterOfExistingFamilyToFemale_cREATE__SOURCE__fmEReference = (EReference) protocolNode_DaughterOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(2);
		createEReference(protocolNode_DaughterOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__DAUGHTER_OF_EXISTING_FAMILY_TO_FEMALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS);
		protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference = (EReference) protocolNode_DaughterOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(3);
		createEReference(protocolNode_DaughterOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__DAUGHTER_OF_EXISTING_FAMILY_TO_FEMALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS);
		protocolNode_DaughterOfExistingFamilyToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = (EReference) protocolNode_DaughterOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(4);
		createEReference(protocolNode_DaughterOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__DAUGHTER_OF_EXISTING_FAMILY_TO_FEMALE__CONTEX_T__TARGE_T__PERSONS);
		protocolNode_DaughterOfExistingFamilyToFemale_cONTEXT__TARGET__personsEReference = (EReference) protocolNode_DaughterOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(5);
		createEReference(protocolNode_DaughterOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__DAUGHTER_OF_EXISTING_FAMILY_TO_FEMALE__CREAT_E__TARGE_T__P);
		protocolNode_DaughterOfExistingFamilyToFemale_cREATE__TARGET__pEReference = (EReference) protocolNode_DaughterOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(6);
		
		protocolNode_DaughterToFemaleEClass = createEClass(PROTOCOL_NODE__DAUGHTER_TO_FEMALE);
		createEReference(protocolNode_DaughterToFemaleEClass, PROTOCOL_NODE__DAUGHTER_TO_FEMALE__CONTEX_T__SOURC_E__FAMILIES);
		protocolNode_DaughterToFemale_cONTEXT__SOURCE__familiesEReference = (EReference) protocolNode_DaughterToFemaleEClass.getEStructuralFeatures().get(0);
		createEReference(protocolNode_DaughterToFemaleEClass, PROTOCOL_NODE__DAUGHTER_TO_FEMALE__CREAT_E__SOURC_E__F);
		protocolNode_DaughterToFemale_cREATE__SOURCE__fEReference = (EReference) protocolNode_DaughterToFemaleEClass.getEStructuralFeatures().get(1);
		createEReference(protocolNode_DaughterToFemaleEClass, PROTOCOL_NODE__DAUGHTER_TO_FEMALE__CREAT_E__SOURC_E__FM);
		protocolNode_DaughterToFemale_cREATE__SOURCE__fmEReference = (EReference) protocolNode_DaughterToFemaleEClass.getEStructuralFeatures().get(2);
		createEReference(protocolNode_DaughterToFemaleEClass, PROTOCOL_NODE__DAUGHTER_TO_FEMALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS);
		protocolNode_DaughterToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference = (EReference) protocolNode_DaughterToFemaleEClass.getEStructuralFeatures().get(3);
		createEReference(protocolNode_DaughterToFemaleEClass, PROTOCOL_NODE__DAUGHTER_TO_FEMALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS);
		protocolNode_DaughterToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = (EReference) protocolNode_DaughterToFemaleEClass.getEStructuralFeatures().get(4);
		createEReference(protocolNode_DaughterToFemaleEClass, PROTOCOL_NODE__DAUGHTER_TO_FEMALE__CONTEX_T__TARGE_T__PERSONS);
		protocolNode_DaughterToFemale_cONTEXT__TARGET__personsEReference = (EReference) protocolNode_DaughterToFemaleEClass.getEStructuralFeatures().get(5);
		createEReference(protocolNode_DaughterToFemaleEClass, PROTOCOL_NODE__DAUGHTER_TO_FEMALE__CREAT_E__TARGE_T__P);
		protocolNode_DaughterToFemale_cREATE__TARGET__pEReference = (EReference) protocolNode_DaughterToFemaleEClass.getEStructuralFeatures().get(6);
		
		protocolNode_Families2PersonsEClass = createEClass(PROTOCOL_NODE__FAMILIES2_PERSONS);
		createEReference(protocolNode_Families2PersonsEClass, PROTOCOL_NODE__FAMILIES2_PERSONS__CREAT_E__SOURC_E__FAMILIES);
		protocolNode_Families2Persons_cREATE__SOURCE__familiesEReference = (EReference) protocolNode_Families2PersonsEClass.getEStructuralFeatures().get(0);
		createEReference(protocolNode_Families2PersonsEClass, PROTOCOL_NODE__FAMILIES2_PERSONS__CREAT_E__CORRESPONDENC_E__FAMILIES2PERSONS);
		protocolNode_Families2Persons_cREATE__CORRESPONDENCE__families2personsEReference = (EReference) protocolNode_Families2PersonsEClass.getEStructuralFeatures().get(1);
		createEReference(protocolNode_Families2PersonsEClass, PROTOCOL_NODE__FAMILIES2_PERSONS__CREAT_E__TARGE_T__PERSONS);
		protocolNode_Families2Persons_cREATE__TARGET__personsEReference = (EReference) protocolNode_Families2PersonsEClass.getEStructuralFeatures().get(2);
		
		protocolNode_FatherOfExistingFamilyToMaleEClass = createEClass(PROTOCOL_NODE__FATHER_OF_EXISTING_FAMILY_TO_MALE);
		createEReference(protocolNode_FatherOfExistingFamilyToMaleEClass, PROTOCOL_NODE__FATHER_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__F);
		protocolNode_FatherOfExistingFamilyToMale_cONTEXT__SOURCE__fEReference = (EReference) protocolNode_FatherOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(0);
		createEReference(protocolNode_FatherOfExistingFamilyToMaleEClass, PROTOCOL_NODE__FATHER_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__FAMILIES);
		protocolNode_FatherOfExistingFamilyToMale_cONTEXT__SOURCE__familiesEReference = (EReference) protocolNode_FatherOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(1);
		createEReference(protocolNode_FatherOfExistingFamilyToMaleEClass, PROTOCOL_NODE__FATHER_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__SOURC_E__FM);
		protocolNode_FatherOfExistingFamilyToMale_cREATE__SOURCE__fmEReference = (EReference) protocolNode_FatherOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(2);
		createEReference(protocolNode_FatherOfExistingFamilyToMaleEClass, PROTOCOL_NODE__FATHER_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS);
		protocolNode_FatherOfExistingFamilyToMale_cONTEXT__CORRESPONDENCE__families2personsEReference = (EReference) protocolNode_FatherOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(3);
		createEReference(protocolNode_FatherOfExistingFamilyToMaleEClass, PROTOCOL_NODE__FATHER_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS);
		protocolNode_FatherOfExistingFamilyToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = (EReference) protocolNode_FatherOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(4);
		createEReference(protocolNode_FatherOfExistingFamilyToMaleEClass, PROTOCOL_NODE__FATHER_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__TARGE_T__PERSONS);
		protocolNode_FatherOfExistingFamilyToMale_cONTEXT__TARGET__personsEReference = (EReference) protocolNode_FatherOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(5);
		createEReference(protocolNode_FatherOfExistingFamilyToMaleEClass, PROTOCOL_NODE__FATHER_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__TARGE_T__P);
		protocolNode_FatherOfExistingFamilyToMale_cREATE__TARGET__pEReference = (EReference) protocolNode_FatherOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(6);
		
		protocolNode_FatherToMaleEClass = createEClass(PROTOCOL_NODE__FATHER_TO_MALE);
		createEReference(protocolNode_FatherToMaleEClass, PROTOCOL_NODE__FATHER_TO_MALE__CONTEX_T__SOURC_E__FAMILIES);
		protocolNode_FatherToMale_cONTEXT__SOURCE__familiesEReference = (EReference) protocolNode_FatherToMaleEClass.getEStructuralFeatures().get(0);
		createEReference(protocolNode_FatherToMaleEClass, PROTOCOL_NODE__FATHER_TO_MALE__CREAT_E__SOURC_E__F);
		protocolNode_FatherToMale_cREATE__SOURCE__fEReference = (EReference) protocolNode_FatherToMaleEClass.getEStructuralFeatures().get(1);
		createEReference(protocolNode_FatherToMaleEClass, PROTOCOL_NODE__FATHER_TO_MALE__CREAT_E__SOURC_E__FM);
		protocolNode_FatherToMale_cREATE__SOURCE__fmEReference = (EReference) protocolNode_FatherToMaleEClass.getEStructuralFeatures().get(2);
		createEReference(protocolNode_FatherToMaleEClass, PROTOCOL_NODE__FATHER_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS);
		protocolNode_FatherToMale_cONTEXT__CORRESPONDENCE__families2personsEReference = (EReference) protocolNode_FatherToMaleEClass.getEStructuralFeatures().get(3);
		createEReference(protocolNode_FatherToMaleEClass, PROTOCOL_NODE__FATHER_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS);
		protocolNode_FatherToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = (EReference) protocolNode_FatherToMaleEClass.getEStructuralFeatures().get(4);
		createEReference(protocolNode_FatherToMaleEClass, PROTOCOL_NODE__FATHER_TO_MALE__CONTEX_T__TARGE_T__PERSONS);
		protocolNode_FatherToMale_cONTEXT__TARGET__personsEReference = (EReference) protocolNode_FatherToMaleEClass.getEStructuralFeatures().get(5);
		createEReference(protocolNode_FatherToMaleEClass, PROTOCOL_NODE__FATHER_TO_MALE__CREAT_E__TARGE_T__P);
		protocolNode_FatherToMale_cREATE__TARGET__pEReference = (EReference) protocolNode_FatherToMaleEClass.getEStructuralFeatures().get(6);
		
		protocolNode_MotherOfExistingFamilyToFemaleEClass = createEClass(PROTOCOL_NODE__MOTHER_OF_EXISTING_FAMILY_TO_FEMALE);
		createEReference(protocolNode_MotherOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__MOTHER_OF_EXISTING_FAMILY_TO_FEMALE__CONTEX_T__SOURC_E__F);
		protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__SOURCE__fEReference = (EReference) protocolNode_MotherOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(0);
		createEReference(protocolNode_MotherOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__MOTHER_OF_EXISTING_FAMILY_TO_FEMALE__CONTEX_T__SOURC_E__FAMILIES);
		protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__SOURCE__familiesEReference = (EReference) protocolNode_MotherOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(1);
		createEReference(protocolNode_MotherOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__MOTHER_OF_EXISTING_FAMILY_TO_FEMALE__CREAT_E__SOURC_E__FM);
		protocolNode_MotherOfExistingFamilyToFemale_cREATE__SOURCE__fmEReference = (EReference) protocolNode_MotherOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(2);
		createEReference(protocolNode_MotherOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__MOTHER_OF_EXISTING_FAMILY_TO_FEMALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS);
		protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference = (EReference) protocolNode_MotherOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(3);
		createEReference(protocolNode_MotherOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__MOTHER_OF_EXISTING_FAMILY_TO_FEMALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS);
		protocolNode_MotherOfExistingFamilyToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = (EReference) protocolNode_MotherOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(4);
		createEReference(protocolNode_MotherOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__MOTHER_OF_EXISTING_FAMILY_TO_FEMALE__CONTEX_T__TARGE_T__PERSONS);
		protocolNode_MotherOfExistingFamilyToFemale_cONTEXT__TARGET__personsEReference = (EReference) protocolNode_MotherOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(5);
		createEReference(protocolNode_MotherOfExistingFamilyToFemaleEClass, PROTOCOL_NODE__MOTHER_OF_EXISTING_FAMILY_TO_FEMALE__CREAT_E__TARGE_T__P);
		protocolNode_MotherOfExistingFamilyToFemale_cREATE__TARGET__pEReference = (EReference) protocolNode_MotherOfExistingFamilyToFemaleEClass.getEStructuralFeatures().get(6);
		
		protocolNode_MotherToFemaleEClass = createEClass(PROTOCOL_NODE__MOTHER_TO_FEMALE);
		createEReference(protocolNode_MotherToFemaleEClass, PROTOCOL_NODE__MOTHER_TO_FEMALE__CONTEX_T__SOURC_E__FAMILIES);
		protocolNode_MotherToFemale_cONTEXT__SOURCE__familiesEReference = (EReference) protocolNode_MotherToFemaleEClass.getEStructuralFeatures().get(0);
		createEReference(protocolNode_MotherToFemaleEClass, PROTOCOL_NODE__MOTHER_TO_FEMALE__CREAT_E__SOURC_E__F);
		protocolNode_MotherToFemale_cREATE__SOURCE__fEReference = (EReference) protocolNode_MotherToFemaleEClass.getEStructuralFeatures().get(1);
		createEReference(protocolNode_MotherToFemaleEClass, PROTOCOL_NODE__MOTHER_TO_FEMALE__CREAT_E__SOURC_E__FM);
		protocolNode_MotherToFemale_cREATE__SOURCE__fmEReference = (EReference) protocolNode_MotherToFemaleEClass.getEStructuralFeatures().get(2);
		createEReference(protocolNode_MotherToFemaleEClass, PROTOCOL_NODE__MOTHER_TO_FEMALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS);
		protocolNode_MotherToFemale_cONTEXT__CORRESPONDENCE__families2personsEReference = (EReference) protocolNode_MotherToFemaleEClass.getEStructuralFeatures().get(3);
		createEReference(protocolNode_MotherToFemaleEClass, PROTOCOL_NODE__MOTHER_TO_FEMALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS);
		protocolNode_MotherToFemale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = (EReference) protocolNode_MotherToFemaleEClass.getEStructuralFeatures().get(4);
		createEReference(protocolNode_MotherToFemaleEClass, PROTOCOL_NODE__MOTHER_TO_FEMALE__CONTEX_T__TARGE_T__PERSONS);
		protocolNode_MotherToFemale_cONTEXT__TARGET__personsEReference = (EReference) protocolNode_MotherToFemaleEClass.getEStructuralFeatures().get(5);
		createEReference(protocolNode_MotherToFemaleEClass, PROTOCOL_NODE__MOTHER_TO_FEMALE__CREAT_E__TARGE_T__P);
		protocolNode_MotherToFemale_cREATE__TARGET__pEReference = (EReference) protocolNode_MotherToFemaleEClass.getEStructuralFeatures().get(6);
		
		protocolNode_SonOfExistingFamilyToMaleEClass = createEClass(PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE);
		createEReference(protocolNode_SonOfExistingFamilyToMaleEClass, PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__F);
		protocolNode_SonOfExistingFamilyToMale_cONTEXT__SOURCE__fEReference = (EReference) protocolNode_SonOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(0);
		createEReference(protocolNode_SonOfExistingFamilyToMaleEClass, PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__FAMILIES);
		protocolNode_SonOfExistingFamilyToMale_cONTEXT__SOURCE__familiesEReference = (EReference) protocolNode_SonOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(1);
		createEReference(protocolNode_SonOfExistingFamilyToMaleEClass, PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__SOURC_E__FM);
		protocolNode_SonOfExistingFamilyToMale_cREATE__SOURCE__fmEReference = (EReference) protocolNode_SonOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(2);
		createEReference(protocolNode_SonOfExistingFamilyToMaleEClass, PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS);
		protocolNode_SonOfExistingFamilyToMale_cONTEXT__CORRESPONDENCE__families2personsEReference = (EReference) protocolNode_SonOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(3);
		createEReference(protocolNode_SonOfExistingFamilyToMaleEClass, PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS);
		protocolNode_SonOfExistingFamilyToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = (EReference) protocolNode_SonOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(4);
		createEReference(protocolNode_SonOfExistingFamilyToMaleEClass, PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__TARGE_T__PERSONS);
		protocolNode_SonOfExistingFamilyToMale_cONTEXT__TARGET__personsEReference = (EReference) protocolNode_SonOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(5);
		createEReference(protocolNode_SonOfExistingFamilyToMaleEClass, PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__TARGE_T__P);
		protocolNode_SonOfExistingFamilyToMale_cREATE__TARGET__pEReference = (EReference) protocolNode_SonOfExistingFamilyToMaleEClass.getEStructuralFeatures().get(6);
		
		protocolNode_SonToMaleEClass = createEClass(PROTOCOL_NODE__SON_TO_MALE);
		createEReference(protocolNode_SonToMaleEClass, PROTOCOL_NODE__SON_TO_MALE__CONTEX_T__SOURC_E__FAMILIES);
		protocolNode_SonToMale_cONTEXT__SOURCE__familiesEReference = (EReference) protocolNode_SonToMaleEClass.getEStructuralFeatures().get(0);
		createEReference(protocolNode_SonToMaleEClass, PROTOCOL_NODE__SON_TO_MALE__CREAT_E__SOURC_E__F);
		protocolNode_SonToMale_cREATE__SOURCE__fEReference = (EReference) protocolNode_SonToMaleEClass.getEStructuralFeatures().get(1);
		createEReference(protocolNode_SonToMaleEClass, PROTOCOL_NODE__SON_TO_MALE__CREAT_E__SOURC_E__FM);
		protocolNode_SonToMale_cREATE__SOURCE__fmEReference = (EReference) protocolNode_SonToMaleEClass.getEStructuralFeatures().get(2);
		createEReference(protocolNode_SonToMaleEClass, PROTOCOL_NODE__SON_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS);
		protocolNode_SonToMale_cONTEXT__CORRESPONDENCE__families2personsEReference = (EReference) protocolNode_SonToMaleEClass.getEStructuralFeatures().get(3);
		createEReference(protocolNode_SonToMaleEClass, PROTOCOL_NODE__SON_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS);
		protocolNode_SonToMale_cREATE__CORRESPONDENCE__familyMember2PersonsEReference = (EReference) protocolNode_SonToMaleEClass.getEStructuralFeatures().get(4);
		createEReference(protocolNode_SonToMaleEClass, PROTOCOL_NODE__SON_TO_MALE__CONTEX_T__TARGE_T__PERSONS);
		protocolNode_SonToMale_cONTEXT__TARGET__personsEReference = (EReference) protocolNode_SonToMaleEClass.getEStructuralFeatures().get(5);
		createEReference(protocolNode_SonToMaleEClass, PROTOCOL_NODE__SON_TO_MALE__CREAT_E__TARGE_T__P);
		protocolNode_SonToMale_cREATE__TARGET__pEReference = (EReference) protocolNode_SonToMaleEClass.getEStructuralFeatures().get(6);
		
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
		TGGRuntimeModelPackage theTGGRuntimeModelPackagePackage = TGGRuntimeModelPackage.eINSTANCE;
		FamiliesSmartEMFPackage theFamiliesSmartEMFPackagePackage = FamiliesSmartEMFPackage.eINSTANCE;
		PersonsSmartEMFPackage thePersonsSmartEMFPackagePackage = PersonsSmartEMFPackage.eINSTANCE;

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		registerToRegisterCorrEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getCorrespondence());
		familyToRegisterCorrEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getCorrespondence());
		familyMemberToPersonCorrEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getCorrespondence());
		protocolNode_CreateFamilyEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getTGGRuleApplication());
		protocolNode_DaughterOfExistingFamilyToFemaleEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getTGGRuleApplication());
		protocolNode_DaughterToFemaleEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getTGGRuleApplication());
		protocolNode_Families2PersonsEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getTGGRuleApplication());
		protocolNode_FatherOfExistingFamilyToMaleEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getTGGRuleApplication());
		protocolNode_FatherToMaleEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getTGGRuleApplication());
		protocolNode_MotherOfExistingFamilyToFemaleEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getTGGRuleApplication());
		protocolNode_MotherToFemaleEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getTGGRuleApplication());
		protocolNode_SonOfExistingFamilyToMaleEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getTGGRuleApplication());
		protocolNode_SonToMaleEClass.getESuperTypes().add(TGGRuntimeModelPackage.eINSTANCE.getTGGRuleApplication());

		// Initialize classes, features, and operations; add parameters
		initEClass(registerToRegisterCorrEClass, RegisterToRegisterCorr.class, "RegisterToRegisterCorr", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRegisterToRegisterCorr_Source(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"source", null, 0, 1, RegisterToRegisterCorr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRegisterToRegisterCorr_Target(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"target", null, 0, 1, RegisterToRegisterCorr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(familyToRegisterCorrEClass, FamilyToRegisterCorr.class, "FamilyToRegisterCorr", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFamilyToRegisterCorr_Source(), FamiliesSmartEMFPackage.eINSTANCE.getFamily(),  null, 
			"source", null, 0, 1, FamilyToRegisterCorr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFamilyToRegisterCorr_Target(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"target", null, 0, 1, FamilyToRegisterCorr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(familyMemberToPersonCorrEClass, FamilyMemberToPersonCorr.class, "FamilyMemberToPersonCorr", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFamilyMemberToPersonCorr_Source(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(),  null, 
			"source", null, 0, 1, FamilyMemberToPersonCorr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFamilyMemberToPersonCorr_Target(), PersonsSmartEMFPackage.eINSTANCE.getPerson(),  null, 
			"target", null, 0, 1, FamilyMemberToPersonCorr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(protocolNode_CreateFamilyEClass, ProtocolNode_CreateFamily.class, "ProtocolNode_CreateFamily", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProtocolNode_CreateFamily_CONTEXT__SOURCE__families(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"CONTEXT__SOURCE__families", null, 1, 1, ProtocolNode_CreateFamily.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_CreateFamily_CREATE__SOURCE__family(), FamiliesSmartEMFPackage.eINSTANCE.getFamily(),  null, 
			"CREATE__SOURCE__family", null, 1, 1, ProtocolNode_CreateFamily.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(protocolNode_DaughterOfExistingFamilyToFemaleEClass, ProtocolNode_DaughterOfExistingFamilyToFemale.class, "ProtocolNode_DaughterOfExistingFamilyToFemale", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__f(), FamiliesSmartEMFPackage.eINSTANCE.getFamily(),  null, 
			"CONTEXT__SOURCE__f", null, 1, 1, ProtocolNode_DaughterOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__SOURCE__families(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"CONTEXT__SOURCE__families", null, 1, 1, ProtocolNode_DaughterOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__SOURCE__fm(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(),  null, 
			"CREATE__SOURCE__fm", null, 1, 1, ProtocolNode_DaughterOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons(), this.getRegisterToRegisterCorr(),  null, 
			"CONTEXT__CORRESPONDENCE__families2persons", null, 1, 1, ProtocolNode_DaughterOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), this.getFamilyMemberToPersonCorr(),  null, 
			"CREATE__CORRESPONDENCE__familyMember2Persons", null, 1, 1, ProtocolNode_DaughterOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterOfExistingFamilyToFemale_CONTEXT__TARGET__persons(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"CONTEXT__TARGET__persons", null, 1, 1, ProtocolNode_DaughterOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterOfExistingFamilyToFemale_CREATE__TARGET__p(), PersonsSmartEMFPackage.eINSTANCE.getFemale(),  null, 
			"CREATE__TARGET__p", null, 1, 1, ProtocolNode_DaughterOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(protocolNode_DaughterToFemaleEClass, ProtocolNode_DaughterToFemale.class, "ProtocolNode_DaughterToFemale", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProtocolNode_DaughterToFemale_CONTEXT__SOURCE__families(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"CONTEXT__SOURCE__families", null, 1, 1, ProtocolNode_DaughterToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterToFemale_CREATE__SOURCE__f(), FamiliesSmartEMFPackage.eINSTANCE.getFamily(),  null, 
			"CREATE__SOURCE__f", null, 1, 1, ProtocolNode_DaughterToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterToFemale_CREATE__SOURCE__fm(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(),  null, 
			"CREATE__SOURCE__fm", null, 1, 1, ProtocolNode_DaughterToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterToFemale_CONTEXT__CORRESPONDENCE__families2persons(), this.getRegisterToRegisterCorr(),  null, 
			"CONTEXT__CORRESPONDENCE__families2persons", null, 1, 1, ProtocolNode_DaughterToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), this.getFamilyMemberToPersonCorr(),  null, 
			"CREATE__CORRESPONDENCE__familyMember2Persons", null, 1, 1, ProtocolNode_DaughterToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterToFemale_CONTEXT__TARGET__persons(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"CONTEXT__TARGET__persons", null, 1, 1, ProtocolNode_DaughterToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_DaughterToFemale_CREATE__TARGET__p(), PersonsSmartEMFPackage.eINSTANCE.getFemale(),  null, 
			"CREATE__TARGET__p", null, 1, 1, ProtocolNode_DaughterToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(protocolNode_Families2PersonsEClass, ProtocolNode_Families2Persons.class, "ProtocolNode_Families2Persons", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProtocolNode_Families2Persons_CREATE__SOURCE__families(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"CREATE__SOURCE__families", null, 1, 1, ProtocolNode_Families2Persons.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_Families2Persons_CREATE__CORRESPONDENCE__families2persons(), this.getRegisterToRegisterCorr(),  null, 
			"CREATE__CORRESPONDENCE__families2persons", null, 1, 1, ProtocolNode_Families2Persons.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_Families2Persons_CREATE__TARGET__persons(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"CREATE__TARGET__persons", null, 1, 1, ProtocolNode_Families2Persons.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(protocolNode_FatherOfExistingFamilyToMaleEClass, ProtocolNode_FatherOfExistingFamilyToMale.class, "ProtocolNode_FatherOfExistingFamilyToMale", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__f(), FamiliesSmartEMFPackage.eINSTANCE.getFamily(),  null, 
			"CONTEXT__SOURCE__f", null, 1, 1, ProtocolNode_FatherOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__SOURCE__families(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"CONTEXT__SOURCE__families", null, 1, 1, ProtocolNode_FatherOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherOfExistingFamilyToMale_CREATE__SOURCE__fm(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(),  null, 
			"CREATE__SOURCE__fm", null, 1, 1, ProtocolNode_FatherOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons(), this.getRegisterToRegisterCorr(),  null, 
			"CONTEXT__CORRESPONDENCE__families2persons", null, 1, 1, ProtocolNode_FatherOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), this.getFamilyMemberToPersonCorr(),  null, 
			"CREATE__CORRESPONDENCE__familyMember2Persons", null, 1, 1, ProtocolNode_FatherOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherOfExistingFamilyToMale_CONTEXT__TARGET__persons(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"CONTEXT__TARGET__persons", null, 1, 1, ProtocolNode_FatherOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherOfExistingFamilyToMale_CREATE__TARGET__p(), PersonsSmartEMFPackage.eINSTANCE.getMale(),  null, 
			"CREATE__TARGET__p", null, 1, 1, ProtocolNode_FatherOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(protocolNode_FatherToMaleEClass, ProtocolNode_FatherToMale.class, "ProtocolNode_FatherToMale", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProtocolNode_FatherToMale_CONTEXT__SOURCE__families(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"CONTEXT__SOURCE__families", null, 1, 1, ProtocolNode_FatherToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherToMale_CREATE__SOURCE__f(), FamiliesSmartEMFPackage.eINSTANCE.getFamily(),  null, 
			"CREATE__SOURCE__f", null, 1, 1, ProtocolNode_FatherToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherToMale_CREATE__SOURCE__fm(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(),  null, 
			"CREATE__SOURCE__fm", null, 1, 1, ProtocolNode_FatherToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherToMale_CONTEXT__CORRESPONDENCE__families2persons(), this.getRegisterToRegisterCorr(),  null, 
			"CONTEXT__CORRESPONDENCE__families2persons", null, 1, 1, ProtocolNode_FatherToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), this.getFamilyMemberToPersonCorr(),  null, 
			"CREATE__CORRESPONDENCE__familyMember2Persons", null, 1, 1, ProtocolNode_FatherToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherToMale_CONTEXT__TARGET__persons(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"CONTEXT__TARGET__persons", null, 1, 1, ProtocolNode_FatherToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_FatherToMale_CREATE__TARGET__p(), PersonsSmartEMFPackage.eINSTANCE.getMale(),  null, 
			"CREATE__TARGET__p", null, 1, 1, ProtocolNode_FatherToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(protocolNode_MotherOfExistingFamilyToFemaleEClass, ProtocolNode_MotherOfExistingFamilyToFemale.class, "ProtocolNode_MotherOfExistingFamilyToFemale", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__f(), FamiliesSmartEMFPackage.eINSTANCE.getFamily(),  null, 
			"CONTEXT__SOURCE__f", null, 1, 1, ProtocolNode_MotherOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__SOURCE__families(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"CONTEXT__SOURCE__families", null, 1, 1, ProtocolNode_MotherOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__SOURCE__fm(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(),  null, 
			"CREATE__SOURCE__fm", null, 1, 1, ProtocolNode_MotherOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__CORRESPONDENCE__families2persons(), this.getRegisterToRegisterCorr(),  null, 
			"CONTEXT__CORRESPONDENCE__families2persons", null, 1, 1, ProtocolNode_MotherOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), this.getFamilyMemberToPersonCorr(),  null, 
			"CREATE__CORRESPONDENCE__familyMember2Persons", null, 1, 1, ProtocolNode_MotherOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherOfExistingFamilyToFemale_CONTEXT__TARGET__persons(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"CONTEXT__TARGET__persons", null, 1, 1, ProtocolNode_MotherOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherOfExistingFamilyToFemale_CREATE__TARGET__p(), PersonsSmartEMFPackage.eINSTANCE.getFemale(),  null, 
			"CREATE__TARGET__p", null, 1, 1, ProtocolNode_MotherOfExistingFamilyToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(protocolNode_MotherToFemaleEClass, ProtocolNode_MotherToFemale.class, "ProtocolNode_MotherToFemale", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProtocolNode_MotherToFemale_CONTEXT__SOURCE__families(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"CONTEXT__SOURCE__families", null, 1, 1, ProtocolNode_MotherToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherToFemale_CREATE__SOURCE__f(), FamiliesSmartEMFPackage.eINSTANCE.getFamily(),  null, 
			"CREATE__SOURCE__f", null, 1, 1, ProtocolNode_MotherToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherToFemale_CREATE__SOURCE__fm(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(),  null, 
			"CREATE__SOURCE__fm", null, 1, 1, ProtocolNode_MotherToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherToFemale_CONTEXT__CORRESPONDENCE__families2persons(), this.getRegisterToRegisterCorr(),  null, 
			"CONTEXT__CORRESPONDENCE__families2persons", null, 1, 1, ProtocolNode_MotherToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherToFemale_CREATE__CORRESPONDENCE__familyMember2Persons(), this.getFamilyMemberToPersonCorr(),  null, 
			"CREATE__CORRESPONDENCE__familyMember2Persons", null, 1, 1, ProtocolNode_MotherToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherToFemale_CONTEXT__TARGET__persons(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"CONTEXT__TARGET__persons", null, 1, 1, ProtocolNode_MotherToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_MotherToFemale_CREATE__TARGET__p(), PersonsSmartEMFPackage.eINSTANCE.getFemale(),  null, 
			"CREATE__TARGET__p", null, 1, 1, ProtocolNode_MotherToFemale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(protocolNode_SonOfExistingFamilyToMaleEClass, ProtocolNode_SonOfExistingFamilyToMale.class, "ProtocolNode_SonOfExistingFamilyToMale", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__f(), FamiliesSmartEMFPackage.eINSTANCE.getFamily(),  null, 
			"CONTEXT__SOURCE__f", null, 1, 1, ProtocolNode_SonOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__SOURCE__families(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"CONTEXT__SOURCE__families", null, 1, 1, ProtocolNode_SonOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonOfExistingFamilyToMale_CREATE__SOURCE__fm(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(),  null, 
			"CREATE__SOURCE__fm", null, 1, 1, ProtocolNode_SonOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__CORRESPONDENCE__families2persons(), this.getRegisterToRegisterCorr(),  null, 
			"CONTEXT__CORRESPONDENCE__families2persons", null, 1, 1, ProtocolNode_SonOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonOfExistingFamilyToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), this.getFamilyMemberToPersonCorr(),  null, 
			"CREATE__CORRESPONDENCE__familyMember2Persons", null, 1, 1, ProtocolNode_SonOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonOfExistingFamilyToMale_CONTEXT__TARGET__persons(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"CONTEXT__TARGET__persons", null, 1, 1, ProtocolNode_SonOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonOfExistingFamilyToMale_CREATE__TARGET__p(), PersonsSmartEMFPackage.eINSTANCE.getMale(),  null, 
			"CREATE__TARGET__p", null, 1, 1, ProtocolNode_SonOfExistingFamilyToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(protocolNode_SonToMaleEClass, ProtocolNode_SonToMale.class, "ProtocolNode_SonToMale", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProtocolNode_SonToMale_CONTEXT__SOURCE__families(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyRegister(),  null, 
			"CONTEXT__SOURCE__families", null, 1, 1, ProtocolNode_SonToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonToMale_CREATE__SOURCE__f(), FamiliesSmartEMFPackage.eINSTANCE.getFamily(),  null, 
			"CREATE__SOURCE__f", null, 1, 1, ProtocolNode_SonToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonToMale_CREATE__SOURCE__fm(), FamiliesSmartEMFPackage.eINSTANCE.getFamilyMember(),  null, 
			"CREATE__SOURCE__fm", null, 1, 1, ProtocolNode_SonToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonToMale_CONTEXT__CORRESPONDENCE__families2persons(), this.getRegisterToRegisterCorr(),  null, 
			"CONTEXT__CORRESPONDENCE__families2persons", null, 1, 1, ProtocolNode_SonToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonToMale_CREATE__CORRESPONDENCE__familyMember2Persons(), this.getFamilyMemberToPersonCorr(),  null, 
			"CREATE__CORRESPONDENCE__familyMember2Persons", null, 1, 1, ProtocolNode_SonToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonToMale_CONTEXT__TARGET__persons(), PersonsSmartEMFPackage.eINSTANCE.getPersonRegister(),  null, 
			"CONTEXT__TARGET__persons", null, 1, 1, ProtocolNode_SonToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProtocolNode_SonToMale_CREATE__TARGET__p(), PersonsSmartEMFPackage.eINSTANCE.getMale(),  null, 
			"CREATE__TARGET__p", null, 1, 1, ProtocolNode_SonToMale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		
		// Initialize enums and add enum literals
		
		// Initialize data types
		
		// Create resource
		createResource(eNS_URI);
	}

} 

