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

public interface ProtocolNode_CreateFamily extends EObject, TGGRuntimeModel.TGGRuleApplication {
	
    public TGGRuntimeModel.Protocol getProtocol();
    
    public void setProtocol(TGGRuntimeModel.Protocol value);
    
    public FamiliesSmartEMF.FamilyRegister getCONTEXT__SOURCE__families();
    
    public void setCONTEXT__SOURCE__families(FamiliesSmartEMF.FamilyRegister value);
    
    public FamiliesSmartEMF.Family getCREATE__SOURCE__family();
    
    public void setCREATE__SOURCE__family(FamiliesSmartEMF.Family value);
    

}
