package FamiliesSmartEMF;

import FamiliesSmartEMF.FamiliesSmartEMFPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface FamilyMember extends EObject {
	
    public java.lang.String getName();
    
    public void setName(java.lang.String value);
    
    public FamiliesSmartEMF.Family getFatherInverse();
    
    public void setFatherInverse(FamiliesSmartEMF.Family value);
    
    public FamiliesSmartEMF.Family getMotherInverse();
    
    public void setMotherInverse(FamiliesSmartEMF.Family value);
    
    public FamiliesSmartEMF.Family getSonsInverse();
    
    public void setSonsInverse(FamiliesSmartEMF.Family value);
    
    public FamiliesSmartEMF.Family getDaughtersInverse();
    
    public void setDaughtersInverse(FamiliesSmartEMF.Family value);
    

}
