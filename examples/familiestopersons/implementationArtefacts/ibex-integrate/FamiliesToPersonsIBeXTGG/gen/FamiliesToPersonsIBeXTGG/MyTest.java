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

public interface MyTest extends EObject, TGGRuntimeModel.Correspondence {
	
    public FamiliesSmartEMF.Family getSource();
    
    public void setSource(FamiliesSmartEMF.Family value);
    
    public PersonsSmartEMF.Female getTarget();
    
    public void setTarget(PersonsSmartEMF.Female value);
    

}
