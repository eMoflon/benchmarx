package PersonsSmartEMF;

import java.lang.String;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EEnum;


import org.emoflon.smartemf.runtime.SmartPackage;

public interface PersonsSmartEMFPackage extends SmartPackage {

	String eNAME = "PersonsSmartEMF";
	String eNS_URI = "platform:/plugin/PersonsSmartEMF/model/PersonsSmartEMF.ecore";
	String eNS_PREFIX = "PersonsSmartEMF";

	PersonsSmartEMFPackage eINSTANCE = PersonsSmartEMF.impl.PersonsSmartEMFPackageImpl.init();

	int PERSON_REGISTER = 0;
	int PERSON_REGISTER__PERSONS = 0;
	int PERSON_REGISTER_FEATURE_COUNT = 1;
	int PERSON_REGISTER_OPERATION_COUNT = 0;
	
	int PERSON = 1;
	int PERSON__NAME = 1;
	int PERSON__BIRTHDAY = 2;
	int PERSON__PERSONS_INVERSE = 3;
	int PERSON_FEATURE_COUNT = 3;
	int PERSON_OPERATION_COUNT = 0;
	
	int MALE = 2;
	int MALE_FEATURE_COUNT = 3;
	int MALE_OPERATION_COUNT = 0;
	
	int FEMALE = 3;
	int FEMALE_FEATURE_COUNT = 3;
	int FEMALE_OPERATION_COUNT = 0;
	
	

	EClass getPersonRegister();
	EReference getPersonRegister_Persons();
	
	EClass getPerson();
	EAttribute getPerson_Name();
	EAttribute getPerson_Birthday();
	EReference getPerson_PersonsInverse();
	
	EClass getMale();
	
	EClass getFemale();
	
	
	PersonsSmartEMF.PersonsSmartEMFFactory getPersonsSmartEMFFactory();

	interface Literals {
		
		EClass PERSON_REGISTER = eINSTANCE.getPersonRegister();
		
		EReference PERSON_REGISTER__PERSONS = eINSTANCE.getPersonRegister_Persons();
		
		EClass PERSON = eINSTANCE.getPerson();
		
		EAttribute PERSON__NAME = eINSTANCE.getPerson_Name();
		
		EAttribute PERSON__BIRTHDAY = eINSTANCE.getPerson_Birthday();
		
		EReference PERSON__PERSONS_INVERSE = eINSTANCE.getPerson_PersonsInverse();
		
		EClass MALE = eINSTANCE.getMale();
		
		EClass FEMALE = eINSTANCE.getFemale();
		
		
		
		
	}

} 
