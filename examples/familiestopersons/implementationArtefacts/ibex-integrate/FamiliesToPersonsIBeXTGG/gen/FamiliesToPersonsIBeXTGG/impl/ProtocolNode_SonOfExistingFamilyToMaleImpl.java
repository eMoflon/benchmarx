package FamiliesToPersonsIBeXTGG.impl;

import FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage;
import TGGRuntimeModel.TGGRuntimeModelPackage;
import FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage;

import org.emoflon.smartemf.runtime.*;
import org.emoflon.smartemf.runtime.collections.*;
import org.emoflon.smartemf.persistence.SmartEMFResource;
import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.notification.NotifyStatus;

import java.util.function.Consumer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

public class ProtocolNode_SonOfExistingFamilyToMaleImpl extends SmartObject implements FamiliesToPersonsIBeXTGG.ProtocolNode_SonOfExistingFamilyToMale {

    protected TGGRuntimeModel.Protocol protocol = null;
    protected FamiliesSmartEMF.Family CONTEXT__SOURCE__f = null;
    protected FamiliesSmartEMF.FamilyRegister CONTEXT__SOURCE__families = null;
    protected FamiliesSmartEMF.FamilyMember CREATE__SOURCE__fm = null;
    protected FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr CONTEXT__CORRESPONDENCE__families2persons = null;
    protected FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr CREATE__CORRESPONDENCE__familyMember2Persons = null;
    protected PersonsSmartEMF.PersonRegister CONTEXT__TARGET__persons = null;
    protected PersonsSmartEMF.Male CREATE__TARGET__p = null;
	
	protected ProtocolNode_SonOfExistingFamilyToMaleImpl() {
		super(FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE);
	}
	
    
    @Override
    public TGGRuntimeModel.Protocol getProtocol() {
    	return this.protocol;
    }
    
    @Override
    public void setProtocol(TGGRuntimeModel.Protocol value) {
    	
    	Object oldValue = this.protocol;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		       	if(this.protocol != null && value == null) {
    		       		resetContainmentSilently();
    		       	}
    		        this.protocol = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, TGGRuntimeModelPackage.Literals.TGG_RULE_APPLICATION__PROTOCOL, oldValue, value, -1));
    	        	
    	
    	        	if(oldValue != null) {
    	        		((SmartObject) oldValue).eInverseRemove(this, TGGRuntimeModelPackage.Literals.PROTOCOL__STEPS);
    	        	}
    	        	if(value != null) {
    	        		((SmartObject) value).eInverseAdd(this, TGGRuntimeModelPackage.Literals.PROTOCOL__STEPS);
    	        	}
    }
    
    private void setProtocolAsInverse(TGGRuntimeModel.Protocol value) {
			    
			    Object oldValue = this.protocol;
			    
			    if(value == null && oldValue == null)
			    	return;
			    	
			    if(value != null && value.equals(oldValue))
			    	return;
			    	
			    
			    
			    	       	if(this.protocol != null && value == null) {
			    	       		resetContainmentSilently();
			    	       	}
			    	        this.protocol = value;
			    	        
			    
			    
			            	sendNotification(SmartEMFNotification.createSetNotification(this, TGGRuntimeModelPackage.Literals.TGG_RULE_APPLICATION__PROTOCOL, oldValue, value, -1));
			            	
    }
    
    @Override
    public FamiliesSmartEMF.Family getCONTEXT__SOURCE__f() {
    	return this.CONTEXT__SOURCE__f;
    }
    
    @Override
    public void setCONTEXT__SOURCE__f(FamiliesSmartEMF.Family value) {
    	
    	Object oldValue = this.CONTEXT__SOURCE__f;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		        this.CONTEXT__SOURCE__f = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__F, oldValue, value, -1));
    	        	
    	        	if(FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__F.getEOpposite() != null) {
    	        		if(oldValue != null) {
    	        			((SmartObject) oldValue).eInverseRemove(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__F.getEOpposite());
    	        		}
    	        		if(value != null) {
    	        		    ((SmartObject) value).eInverseAdd(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__F.getEOpposite());
    	        		}
    	        	}
    }
    
    
    @Override
    public FamiliesSmartEMF.FamilyRegister getCONTEXT__SOURCE__families() {
    	return this.CONTEXT__SOURCE__families;
    }
    
    @Override
    public void setCONTEXT__SOURCE__families(FamiliesSmartEMF.FamilyRegister value) {
    	
    	Object oldValue = this.CONTEXT__SOURCE__families;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		        this.CONTEXT__SOURCE__families = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__FAMILIES, oldValue, value, -1));
    	        	
    	        	if(FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__FAMILIES.getEOpposite() != null) {
    	        		if(oldValue != null) {
    	        			((SmartObject) oldValue).eInverseRemove(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__FAMILIES.getEOpposite());
    	        		}
    	        		if(value != null) {
    	        		    ((SmartObject) value).eInverseAdd(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__FAMILIES.getEOpposite());
    	        		}
    	        	}
    }
    
    
    @Override
    public FamiliesSmartEMF.FamilyMember getCREATE__SOURCE__fm() {
    	return this.CREATE__SOURCE__fm;
    }
    
    @Override
    public void setCREATE__SOURCE__fm(FamiliesSmartEMF.FamilyMember value) {
    	
    	Object oldValue = this.CREATE__SOURCE__fm;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		        this.CREATE__SOURCE__fm = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__SOURC_E__FM, oldValue, value, -1));
    	        	
    	        	if(FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__SOURC_E__FM.getEOpposite() != null) {
    	        		if(oldValue != null) {
    	        			((SmartObject) oldValue).eInverseRemove(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__SOURC_E__FM.getEOpposite());
    	        		}
    	        		if(value != null) {
    	        		    ((SmartObject) value).eInverseAdd(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__SOURC_E__FM.getEOpposite());
    	        		}
    	        	}
    }
    
    
    @Override
    public FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr getCONTEXT__CORRESPONDENCE__families2persons() {
    	return this.CONTEXT__CORRESPONDENCE__families2persons;
    }
    
    @Override
    public void setCONTEXT__CORRESPONDENCE__families2persons(FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr value) {
    	
    	Object oldValue = this.CONTEXT__CORRESPONDENCE__families2persons;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		        this.CONTEXT__CORRESPONDENCE__families2persons = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS, oldValue, value, -1));
    	        	
    	        	if(FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS.getEOpposite() != null) {
    	        		if(oldValue != null) {
    	        			((SmartObject) oldValue).eInverseRemove(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS.getEOpposite());
    	        		}
    	        		if(value != null) {
    	        		    ((SmartObject) value).eInverseAdd(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS.getEOpposite());
    	        		}
    	        	}
    }
    
    
    @Override
    public FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr getCREATE__CORRESPONDENCE__familyMember2Persons() {
    	return this.CREATE__CORRESPONDENCE__familyMember2Persons;
    }
    
    @Override
    public void setCREATE__CORRESPONDENCE__familyMember2Persons(FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr value) {
    	
    	Object oldValue = this.CREATE__CORRESPONDENCE__familyMember2Persons;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		        this.CREATE__CORRESPONDENCE__familyMember2Persons = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS, oldValue, value, -1));
    	        	
    	        	if(FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS.getEOpposite() != null) {
    	        		if(oldValue != null) {
    	        			((SmartObject) oldValue).eInverseRemove(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS.getEOpposite());
    	        		}
    	        		if(value != null) {
    	        		    ((SmartObject) value).eInverseAdd(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS.getEOpposite());
    	        		}
    	        	}
    }
    
    
    @Override
    public PersonsSmartEMF.PersonRegister getCONTEXT__TARGET__persons() {
    	return this.CONTEXT__TARGET__persons;
    }
    
    @Override
    public void setCONTEXT__TARGET__persons(PersonsSmartEMF.PersonRegister value) {
    	
    	Object oldValue = this.CONTEXT__TARGET__persons;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		        this.CONTEXT__TARGET__persons = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__TARGE_T__PERSONS, oldValue, value, -1));
    	        	
    	        	if(FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__TARGE_T__PERSONS.getEOpposite() != null) {
    	        		if(oldValue != null) {
    	        			((SmartObject) oldValue).eInverseRemove(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__TARGE_T__PERSONS.getEOpposite());
    	        		}
    	        		if(value != null) {
    	        		    ((SmartObject) value).eInverseAdd(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__TARGE_T__PERSONS.getEOpposite());
    	        		}
    	        	}
    }
    
    
    @Override
    public PersonsSmartEMF.Male getCREATE__TARGET__p() {
    	return this.CREATE__TARGET__p;
    }
    
    @Override
    public void setCREATE__TARGET__p(PersonsSmartEMF.Male value) {
    	
    	Object oldValue = this.CREATE__TARGET__p;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		        this.CREATE__TARGET__p = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__TARGE_T__P, oldValue, value, -1));
    	        	
    	        	if(FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__TARGE_T__P.getEOpposite() != null) {
    	        		if(oldValue != null) {
    	        			((SmartObject) oldValue).eInverseRemove(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__TARGE_T__P.getEOpposite());
    	        		}
    	        		if(value != null) {
    	        		    ((SmartObject) value).eInverseAdd(this, FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__TARGE_T__P.getEOpposite());
    	        		}
    	        	}
    }
    

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (TGGRuntimeModelPackage.Literals.TGG_RULE_APPLICATION__PROTOCOL.equals(eFeature)) {
    		setProtocol((TGGRuntimeModel.Protocol) newValue); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__F.equals(eFeature)) {
    		setCONTEXT__SOURCE__f((FamiliesSmartEMF.Family) newValue); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__FAMILIES.equals(eFeature)) {
    		setCONTEXT__SOURCE__families((FamiliesSmartEMF.FamilyRegister) newValue); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__SOURC_E__FM.equals(eFeature)) {
    		setCREATE__SOURCE__fm((FamiliesSmartEMF.FamilyMember) newValue); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS.equals(eFeature)) {
    		setCONTEXT__CORRESPONDENCE__families2persons((FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr) newValue); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS.equals(eFeature)) {
    		setCREATE__CORRESPONDENCE__familyMember2Persons((FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr) newValue); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__TARGE_T__PERSONS.equals(eFeature)) {
    		setCONTEXT__TARGET__persons((PersonsSmartEMF.PersonRegister) newValue); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__TARGE_T__P.equals(eFeature)) {
    		setCREATE__TARGET__p((PersonsSmartEMF.Male) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (TGGRuntimeModelPackage.Literals.TGG_RULE_APPLICATION__PROTOCOL.equals(eFeature)) {
    		setProtocol((TGGRuntimeModel.Protocol)null); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__F.equals(eFeature)) {
    		setCONTEXT__SOURCE__f((FamiliesSmartEMF.Family)null); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__FAMILIES.equals(eFeature)) {
    		setCONTEXT__SOURCE__families((FamiliesSmartEMF.FamilyRegister)null); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__SOURC_E__FM.equals(eFeature)) {
    		setCREATE__SOURCE__fm((FamiliesSmartEMF.FamilyMember)null); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS.equals(eFeature)) {
    		setCONTEXT__CORRESPONDENCE__families2persons((FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr)null); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS.equals(eFeature)) {
    		setCREATE__CORRESPONDENCE__familyMember2Persons((FamiliesToPersonsIBeXTGG.FamilyMemberToPersonCorr)null); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__TARGE_T__PERSONS.equals(eFeature)) {
    		setCONTEXT__TARGET__persons((PersonsSmartEMF.PersonRegister)null); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__TARGE_T__P.equals(eFeature)) {
    		setCREATE__TARGET__p((PersonsSmartEMF.Male)null); 
    		return;
    	}
    	eDynamicUnset(eFeature);
    }

    @Override
    public String toString(){
		return super.toString();
    }

 	@Override
    public Object eGet(EStructuralFeature eFeature){
    	if (TGGRuntimeModelPackage.Literals.TGG_RULE_APPLICATION__PROTOCOL.equals(eFeature))
    		return getProtocol();
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__F.equals(eFeature))
    		return getCONTEXT__SOURCE__f();
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__SOURC_E__FAMILIES.equals(eFeature))
    		return getCONTEXT__SOURCE__families();
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__SOURC_E__FM.equals(eFeature))
    		return getCREATE__SOURCE__fm();
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__CORRESPONDENC_E__FAMILIES2PERSONS.equals(eFeature))
    		return getCONTEXT__CORRESPONDENCE__families2persons();
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__CORRESPONDENC_E__FAMILY_MEMBER2_PERSONS.equals(eFeature))
    		return getCREATE__CORRESPONDENCE__familyMember2Persons();
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CONTEX_T__TARGE_T__PERSONS.equals(eFeature))
    		return getCONTEXT__TARGET__persons();
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.PROTOCOL_NODE__SON_OF_EXISTING_FAMILY_TO_MALE__CREAT_E__TARGE_T__P.equals(eFeature))
    		return getCREATE__TARGET__p();
    	return eDynamicGet(eFeature);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType){
    	throw new UnsupportedOperationException("This method has been deactivated since it is not always safe to use.");
    }
    
    @Override
    public void eInverseAdd(Object otherEnd, EStructuralFeature feature) {
if (TGGRuntimeModelPackage.Literals.TGG_RULE_APPLICATION__PROTOCOL.equals(feature)) {
setProtocolAsInverse((TGGRuntimeModel.Protocol) otherEnd); 
 	return;
			        }	
	    if(feature == null)
	    	return;
	    	
    	eDynamicInverseAdd(otherEnd, feature);
	    	}
    	
    @Override
	    	public void eInverseRemove(Object otherEnd, EStructuralFeature feature) {
if (TGGRuntimeModelPackage.Literals.TGG_RULE_APPLICATION__PROTOCOL.equals(feature)) {
setProtocolAsInverse(null); 
 	return;
			        }
	    if(feature == null)
	    	return;
	    		    		
    	eDynamicInverseRemove(otherEnd, feature);
	    	}
    
    @Override
    /**
    * This method sets the resource and generates REMOVING_ADAPTER and ADD notifications
    */
    protected void setResourceOfContainments(Consumer<SmartObject> setResourceCall) {
	    	}
	    	
	    	@Override
	    	/**
	    	* This method sets the resource and only generates REMOVING_ADAPTER notifications (no ADD messages)
	    	*/
    protected void setResourceOfContainmentsSilently(Resource r) { 		
	    	}
}
