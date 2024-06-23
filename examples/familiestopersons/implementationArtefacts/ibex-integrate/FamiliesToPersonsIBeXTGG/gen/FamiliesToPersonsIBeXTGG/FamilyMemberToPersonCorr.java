package FamiliesToPersonsIBeXTGG;

import TGGRuntimeModel.TGGRuntimeModelPackage;
import FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface FamilyMemberToPersonCorr extends EObject, TGGRuntimeModel.Correspondence {
	
    public FamiliesSmartEMF.FamilyMember getSource();
    
    public void setSource(FamiliesSmartEMF.FamilyMember value);
    
    public PersonsSmartEMF.Person getTarget();
    
    public void setTarget(PersonsSmartEMF.Person value);
    

}
