package FamiliesSmartEMF;

import java.lang.String;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EEnum;


import org.emoflon.smartemf.runtime.SmartPackage;

public interface FamiliesSmartEMFPackage extends SmartPackage {

	String eNAME = "FamiliesSmartEMF";
	String eNS_URI = "platform:/plugin/FamiliesSmartEMF/model/FamiliesSmartEMF.ecore";
	String eNS_PREFIX = "FamiliesSmartEMF";

	FamiliesSmartEMFPackage eINSTANCE = FamiliesSmartEMF.impl.FamiliesSmartEMFPackageImpl.init();

	int FAMILY_REGISTER = 0;
	int FAMILY_REGISTER__FAMILIES = 0;
	int FAMILY_REGISTER_FEATURE_COUNT = 1;
	int FAMILY_REGISTER_OPERATION_COUNT = 0;
	
	int FAMILY = 1;
	int FAMILY__FATHER = 1;
	int FAMILY__MOTHER = 2;
	int FAMILY__SONS = 3;
	int FAMILY__DAUGHTERS = 4;
	int FAMILY__NAME = 5;
	int FAMILY__FAMILIES_INVERSE = 6;
	int FAMILY_FEATURE_COUNT = 6;
	int FAMILY_OPERATION_COUNT = 0;
	
	int FAMILY_MEMBER = 2;
	int FAMILY_MEMBER__NAME = 7;
	int FAMILY_MEMBER__FATHER_INVERSE = 8;
	int FAMILY_MEMBER__MOTHER_INVERSE = 9;
	int FAMILY_MEMBER__SONS_INVERSE = 10;
	int FAMILY_MEMBER__DAUGHTERS_INVERSE = 11;
	int FAMILY_MEMBER_FEATURE_COUNT = 5;
	int FAMILY_MEMBER_OPERATION_COUNT = 0;
	
	

	EClass getFamilyRegister();
	EReference getFamilyRegister_Families();
	
	EClass getFamily();
	EReference getFamily_Father();
	EReference getFamily_Mother();
	EReference getFamily_Sons();
	EReference getFamily_Daughters();
	EAttribute getFamily_Name();
	EReference getFamily_FamiliesInverse();
	
	EClass getFamilyMember();
	EAttribute getFamilyMember_Name();
	EReference getFamilyMember_FatherInverse();
	EReference getFamilyMember_MotherInverse();
	EReference getFamilyMember_SonsInverse();
	EReference getFamilyMember_DaughtersInverse();
	
	
	FamiliesSmartEMF.FamiliesSmartEMFFactory getFamiliesSmartEMFFactory();

	interface Literals {
		
		EClass FAMILY_REGISTER = eINSTANCE.getFamilyRegister();
		
		EReference FAMILY_REGISTER__FAMILIES = eINSTANCE.getFamilyRegister_Families();
		
		EClass FAMILY = eINSTANCE.getFamily();
		
		EReference FAMILY__FATHER = eINSTANCE.getFamily_Father();
		
		EReference FAMILY__MOTHER = eINSTANCE.getFamily_Mother();
		
		EReference FAMILY__SONS = eINSTANCE.getFamily_Sons();
		
		EReference FAMILY__DAUGHTERS = eINSTANCE.getFamily_Daughters();
		
		EAttribute FAMILY__NAME = eINSTANCE.getFamily_Name();
		
		EReference FAMILY__FAMILIES_INVERSE = eINSTANCE.getFamily_FamiliesInverse();
		
		EClass FAMILY_MEMBER = eINSTANCE.getFamilyMember();
		
		EAttribute FAMILY_MEMBER__NAME = eINSTANCE.getFamilyMember_Name();
		
		EReference FAMILY_MEMBER__FATHER_INVERSE = eINSTANCE.getFamilyMember_FatherInverse();
		
		EReference FAMILY_MEMBER__MOTHER_INVERSE = eINSTANCE.getFamilyMember_MotherInverse();
		
		EReference FAMILY_MEMBER__SONS_INVERSE = eINSTANCE.getFamilyMember_SonsInverse();
		
		EReference FAMILY_MEMBER__DAUGHTERS_INVERSE = eINSTANCE.getFamilyMember_DaughtersInverse();
		
		
		
		
	}

} 
