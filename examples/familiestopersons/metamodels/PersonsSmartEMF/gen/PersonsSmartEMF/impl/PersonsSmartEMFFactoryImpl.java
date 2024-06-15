package PersonsSmartEMF.impl;

import PersonsSmartEMF.PersonRegister;
import PersonsSmartEMF.Person;
import PersonsSmartEMF.Male;
import PersonsSmartEMF.Female;


import PersonsSmartEMF.PersonsSmartEMFFactory;
import PersonsSmartEMF.PersonsSmartEMFPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

public class PersonsSmartEMFFactoryImpl extends EFactoryImpl implements PersonsSmartEMF.PersonsSmartEMFFactory {

	public static PersonsSmartEMF.PersonsSmartEMFFactory init() {
		try {
			PersonsSmartEMFFactory thePersonsSmartEMFFactory = (PersonsSmartEMFFactory) EPackage.Registry.INSTANCE
					.getEFactory(PersonsSmartEMFPackage.eNS_URI);
			if (thePersonsSmartEMFFactory != null) {
				return thePersonsSmartEMFFactory;
			}
		} catch (java.lang.Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PersonsSmartEMFFactoryImpl();
	}

	public PersonsSmartEMFFactoryImpl() {
		super();
	}

	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case PersonsSmartEMFPackage.PERSON_REGISTER:
			return createPersonRegister();
		case PersonsSmartEMFPackage.MALE:
			return createMale();
		case PersonsSmartEMFPackage.FEMALE:
			return createFemale();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}
	
	
	@Override
	public PersonsSmartEMF.PersonRegister createPersonRegister() {
		PersonRegisterImpl personRegister = new PersonRegisterImpl();
		return personRegister;
	}
	@Override
	public PersonsSmartEMF.Male createMale() {
		MaleImpl male = new MaleImpl();
		return male;
	}
	@Override
	public PersonsSmartEMF.Female createFemale() {
		FemaleImpl female = new FemaleImpl();
		return female;
	}
	

	@Override
	public PersonsSmartEMFPackage getPersonsSmartEMFPackage() {
	return (PersonsSmartEMFPackage) getEPackage();
	}
} 
