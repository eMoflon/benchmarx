package FamiliesSmartEMF;

import FamiliesSmartEMF.FamiliesSmartEMFPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface FamilyRegister extends EObject {
	
    public LinkedSmartESet<FamiliesSmartEMF.Family> getFamilies();
    
    public void setFamilies(LinkedSmartESet<FamiliesSmartEMF.Family> value);
    

}
