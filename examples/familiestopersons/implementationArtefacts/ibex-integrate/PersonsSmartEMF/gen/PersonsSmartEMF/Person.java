package PersonsSmartEMF;

import PersonsSmartEMF.PersonsSmartEMFPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface Person extends EObject {
	
    public java.lang.String getName();
    
    public void setName(java.lang.String value);
    
    public java.util.Date getBirthday();
    
    public void setBirthday(java.util.Date value);
    
    public PersonsSmartEMF.PersonRegister getPersonsInverse();
    
    public void setPersonsInverse(PersonsSmartEMF.PersonRegister value);
    

}
