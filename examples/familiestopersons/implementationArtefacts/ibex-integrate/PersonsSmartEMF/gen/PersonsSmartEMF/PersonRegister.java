package PersonsSmartEMF;

import PersonsSmartEMF.PersonsSmartEMFPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface PersonRegister extends EObject {
	
    public LinkedSmartESet<PersonsSmartEMF.Person> getPersons();
    
    public void setPersons(LinkedSmartESet<PersonsSmartEMF.Person> value);
    

}
