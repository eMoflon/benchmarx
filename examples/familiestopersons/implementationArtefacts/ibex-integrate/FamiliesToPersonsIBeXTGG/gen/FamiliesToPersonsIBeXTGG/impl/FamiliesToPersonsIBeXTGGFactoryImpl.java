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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

public class FamiliesToPersonsIBeXTGGFactoryImpl extends EFactoryImpl implements FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGFactory {

	public static FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGFactory init() {
		try {
			FamiliesToPersonsIBeXTGGFactory theFamiliesToPersonsIBeXTGGFactory = (FamiliesToPersonsIBeXTGGFactory) EPackage.Registry.INSTANCE
					.getEFactory(FamiliesToPersonsIBeXTGGPackage.eNS_URI);
			if (theFamiliesToPersonsIBeXTGGFactory != null) {
				return theFamiliesToPersonsIBeXTGGFactory;
			}
		} catch (java.lang.Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FamiliesToPersonsIBeXTGGFactoryImpl();
	}

	public FamiliesToPersonsIBeXTGGFactoryImpl() {
		super();
	}

	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case FamiliesToPersonsIBeXTGGPackage.REGISTER_TO_REGISTER_CORR:
			return createRegisterToRegisterCorr();
		case FamiliesToPersonsIBeXTGGPackage.FAMILY_TO_REGISTER_CORR:
			return createFamilyToRegisterCorr();
		case FamiliesToPersonsIBeXTGGPackage.FAMILY_MEMBER_TO_PERSON_CORR:
			return createFamilyMemberToPersonCorr();
		case FamiliesToPersonsIBeXTGGPackage.PROTOCOL_NODE__CREATE_FAMILY:
			return createProtocolNode_CreateFamily();
		case FamiliesToPersonsIBeXTGGPackage.PROTOCOL_NODE__DAUGHTER_OF_EXISTING_FAMILY_TO_FEMALE:
			return createProtocolNode_DaughterOfExistingFamilyToFemale();
		case FamiliesToPersonsIBeXTGGPackage.PROTOCOL_NODE__DAUGHTER_TO_FEMALE:
			return createProtocolNode_DaughterToFemale();
		case FamiliesToPersonsIBeXTGGPackage.PROTOCOL_NODE__FAMILIES2_PERSONS:
			return createProtocolNode_Families2Persons();
		case FamiliesToPersonsIBeXTGGPackage.PROTOCOL_NODE__FATHER_OF_EXISTING_FAMILY_TO_MALE:
			return createProtocolNode_FatherOfExistingFamilyToMale();
		case FamiliesToPersonsIBeXTGGPackage.PROTOCOL_NODE__FATHER_TO_MALE:
			return createProtocolNode_FatherToMale();
		case FamiliesToPersonsIBeXTGGPackage.PROTOCOL_NODE__MOTHER_OF_EXISTING_FAMILY_TO_FEMALE:
			return createProtocolNode_MotherOfExistingFamilyToFemale();
		case FamiliesToPersonsIBeXTGGPackage.PROTOCOL_NODE__MOTHER_TO_FEMALE:
			return createProtocolNode_MotherToFemale();
		case FamiliesToPersonsIBeXTGGPackage.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE:
			return createProtocolNode_SonOfExistingFamilyToMale();
		case FamiliesToPersonsIBeXTGGPackage.PROTOCOL_NODE__SON_TO_MALE:
			return createProtocolNode_SonToMale();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}
	
	
	@Override
	public FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr createRegisterToRegisterCorr() {
		RegisterToRegisterCorrImpl registerToRegisterCorr = new RegisterToRegisterCorrImpl();
		return registerToRegisterCorr;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.FamilyToRegisterCorr createFamilyToRegisterCorr() {
		FamilyToRegisterCorrImpl familyToRegisterCorr = new FamilyToRegisterCorrImpl();
		return familyToRegisterCorr;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr createFamilyMemberToPersonCorr() {
		FamilyMemberToPersonCorrImpl familyMemberToPersonCorr = new FamilyMemberToPersonCorrImpl();
		return familyMemberToPersonCorr;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.ProtocolNode_CreateFamily createProtocolNode_CreateFamily() {
		ProtocolNode_CreateFamilyImpl protocolNode_CreateFamily = new ProtocolNode_CreateFamilyImpl();
		return protocolNode_CreateFamily;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterOfExistingFamilyToFemale createProtocolNode_DaughterOfExistingFamilyToFemale() {
		ProtocolNode_DaughterOfExistingFamilyToFemaleImpl protocolNode_DaughterOfExistingFamilyToFemale = new ProtocolNode_DaughterOfExistingFamilyToFemaleImpl();
		return protocolNode_DaughterOfExistingFamilyToFemale;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.ProtocolNode_DaughterToFemale createProtocolNode_DaughterToFemale() {
		ProtocolNode_DaughterToFemaleImpl protocolNode_DaughterToFemale = new ProtocolNode_DaughterToFemaleImpl();
		return protocolNode_DaughterToFemale;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.ProtocolNode_Families2Persons createProtocolNode_Families2Persons() {
		ProtocolNode_Families2PersonsImpl protocolNode_Families2Persons = new ProtocolNode_Families2PersonsImpl();
		return protocolNode_Families2Persons;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.ProtocolNode_FatherOfExistingFamilyToMale createProtocolNode_FatherOfExistingFamilyToMale() {
		ProtocolNode_FatherOfExistingFamilyToMaleImpl protocolNode_FatherOfExistingFamilyToMale = new ProtocolNode_FatherOfExistingFamilyToMaleImpl();
		return protocolNode_FatherOfExistingFamilyToMale;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.ProtocolNode_FatherToMale createProtocolNode_FatherToMale() {
		ProtocolNode_FatherToMaleImpl protocolNode_FatherToMale = new ProtocolNode_FatherToMaleImpl();
		return protocolNode_FatherToMale;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.ProtocolNode_MotherOfExistingFamilyToFemale createProtocolNode_MotherOfExistingFamilyToFemale() {
		ProtocolNode_MotherOfExistingFamilyToFemaleImpl protocolNode_MotherOfExistingFamilyToFemale = new ProtocolNode_MotherOfExistingFamilyToFemaleImpl();
		return protocolNode_MotherOfExistingFamilyToFemale;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.ProtocolNode_MotherToFemale createProtocolNode_MotherToFemale() {
		ProtocolNode_MotherToFemaleImpl protocolNode_MotherToFemale = new ProtocolNode_MotherToFemaleImpl();
		return protocolNode_MotherToFemale;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale createProtocolNode_SonOfExistingFamilyToMale() {
		ProtocolNode_SonOfExistingFamilyToMaleImpl protocolNode_SonOfExistingFamilyToMale = new ProtocolNode_SonOfExistingFamilyToMaleImpl();
		return protocolNode_SonOfExistingFamilyToMale;
	}
	@Override
	public FamiliesToPersonsIBeXTGG.ProtocolNode_SonToMale createProtocolNode_SonToMale() {
		ProtocolNode_SonToMaleImpl protocolNode_SonToMale = new ProtocolNode_SonToMaleImpl();
		return protocolNode_SonToMale;
	}
	

	@Override
	public FamiliesToPersonsIBeXTGGPackage getFamiliesToPersonsIBeXTGGPackage() {
	return (FamiliesToPersonsIBeXTGGPackage) getEPackage();
	}
} 
