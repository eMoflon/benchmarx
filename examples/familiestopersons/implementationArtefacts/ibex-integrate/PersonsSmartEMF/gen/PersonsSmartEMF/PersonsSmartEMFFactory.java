package PersonsSmartEMF;

import PersonsSmartEMF.PersonRegister;
import PersonsSmartEMF.Person;
import PersonsSmartEMF.Male;
import PersonsSmartEMF.Female;

import org.eclipse.emf.ecore.EFactory;

public interface PersonsSmartEMFFactory extends EFactory {

	PersonsSmartEMFFactory eINSTANCE = PersonsSmartEMF.impl.PersonsSmartEMFFactoryImpl.init();
	
	PersonRegister createPersonRegister();
	
	Male createMale();
	
	Female createFemale();
	
	
	PersonsSmartEMFPackage getPersonsSmartEMFPackage();

}
