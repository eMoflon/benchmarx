package FamiliesSmartEMF;

import FamiliesSmartEMF.FamiliesSmartEMFPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface Family extends EObject {
	
    public FamiliesSmartEMF.FamilyMember getFather();
    
    public void setFather(FamiliesSmartEMF.FamilyMember value);
    
    public FamiliesSmartEMF.FamilyMember getMother();
    
    public void setMother(FamiliesSmartEMF.FamilyMember value);
    
    public LinkedSmartESet<FamiliesSmartEMF.FamilyMember> getSons();
    
    public void setSons(LinkedSmartESet<FamiliesSmartEMF.FamilyMember> value);
    
    public LinkedSmartESet<FamiliesSmartEMF.FamilyMember> getDaughters();
    
    public void setDaughters(LinkedSmartESet<FamiliesSmartEMF.FamilyMember> value);
    
    public java.lang.String getName();
    
    public void setName(java.lang.String value);
    
    public FamiliesSmartEMF.FamilyRegister getFamiliesInverse();
    
    public void setFamiliesInverse(FamiliesSmartEMF.FamilyRegister value);
    

}
