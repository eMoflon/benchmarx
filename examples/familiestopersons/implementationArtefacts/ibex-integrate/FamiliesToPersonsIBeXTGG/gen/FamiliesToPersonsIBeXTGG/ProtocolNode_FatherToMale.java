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

public interface ProtocolNode_FatherToMale extends EObject, TGGRuntimeModel.TGGRuleApplication {
	
    public TGGRuntimeModel.Protocol getProtocol();
    
    public void setProtocol(TGGRuntimeModel.Protocol value);
    
    public FamiliesSmartEMF.FamilyRegister getCONTEXT__SOURCE__families();
    
    public void setCONTEXT__SOURCE__families(FamiliesSmartEMF.FamilyRegister value);
    
    public FamiliesSmartEMF.Family getCREATE__SOURCE__f();
    
    public void setCREATE__SOURCE__f(FamiliesSmartEMF.Family value);
    
    public FamiliesSmartEMF.FamilyMember getCREATE__SOURCE__fm();
    
    public void setCREATE__SOURCE__fm(FamiliesSmartEMF.FamilyMember value);
    
    public FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr getCONTEXT__CORRESPONDENCE__families2persons();
    
    public void setCONTEXT__CORRESPONDENCE__families2persons(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr value);
    
    public FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr getCREATE__CORRESPONDENCE__familyMember2Persons();
    
    public void setCREATE__CORRESPONDENCE__familyMember2Persons(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr value);
    
    public PersonsSmartEMF.PersonRegister getCONTEXT__TARGET__persons();
    
    public void setCONTEXT__TARGET__persons(PersonsSmartEMF.PersonRegister value);
    
    public PersonsSmartEMF.Male getCREATE__TARGET__p();
    
    public void setCREATE__TARGET__p(PersonsSmartEMF.Male value);
    

}
