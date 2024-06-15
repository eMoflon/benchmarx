package FamiliesSmartEMF;

import FamiliesSmartEMF.FamilyRegister;
import FamiliesSmartEMF.Family;
import FamiliesSmartEMF.FamilyMember;

import org.eclipse.emf.ecore.EFactory;

public interface FamiliesSmartEMFFactory extends EFactory {

	FamiliesSmartEMFFactory eINSTANCE = FamiliesSmartEMF.impl.FamiliesSmartEMFFactoryImpl.init();
	
	FamilyRegister createFamilyRegister();
	
	Family createFamily();
	
	FamilyMember createFamilyMember();
	
	
	FamiliesSmartEMFPackage getFamiliesSmartEMFPackage();

}
