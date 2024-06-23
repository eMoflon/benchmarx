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

public interface ProtocolNode_Families2Persons extends EObject, TGGRuntimeModel.TGGRuleApplication {
	
    public TGGRuntimeModel.Protocol getProtocol();
    
    public void setProtocol(TGGRuntimeModel.Protocol value);
    
    public FamiliesSmartEMF.FamilyRegister getCREATE__SOURCE__families();
    
    public void setCREATE__SOURCE__families(FamiliesSmartEMF.FamilyRegister value);
    
    public FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr getCREATE__CORRESPONDENCE__families2persons();
    
    public void setCREATE__CORRESPONDENCE__families2persons(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr value);
    
    public PersonsSmartEMF.PersonRegister getCREATE__TARGET__persons();
    
    public void setCREATE__TARGET__persons(PersonsSmartEMF.PersonRegister value);
    

}
