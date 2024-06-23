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

public class RegisterToRegisterCorrImpl extends SmartObject implements FamiliesToPersonsIBeXTGG.RegisterToRegisterCorr {

    protected FamiliesSmartEMF.FamilyRegister source = null;
    protected PersonsSmartEMF.PersonRegister target = null;
	
	protected RegisterToRegisterCorrImpl() {
		super(FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR);
	}
	
    
    @Override
    public FamiliesSmartEMF.FamilyRegister getSource() {
    	return this.source;
    }
    
    @Override
    public void setSource(FamiliesSmartEMF.FamilyRegister value) {
    	
    	Object oldValue = this.source;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		        this.source = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__SOURCE, oldValue, value, -1));
    	        	
    	        	if(FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__SOURCE.getEOpposite() != null) {
    	        		if(oldValue != null) {
    	        			((SmartObject) oldValue).eInverseRemove(this, FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__SOURCE.getEOpposite());
    	        		}
    	        		if(value != null) {
    	        		    ((SmartObject) value).eInverseAdd(this, FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__SOURCE.getEOpposite());
    	        		}
    	        	}
    }
    
    
    @Override
    public PersonsSmartEMF.PersonRegister getTarget() {
    	return this.target;
    }
    
    @Override
    public void setTarget(PersonsSmartEMF.PersonRegister value) {
    	
    	Object oldValue = this.target;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		        this.target = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__TARGET, oldValue, value, -1));
    	        	
    	        	if(FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__TARGET.getEOpposite() != null) {
    	        		if(oldValue != null) {
    	        			((SmartObject) oldValue).eInverseRemove(this, FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__TARGET.getEOpposite());
    	        		}
    	        		if(value != null) {
    	        		    ((SmartObject) value).eInverseAdd(this, FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__TARGET.getEOpposite());
    	        		}
    	        	}
    }
    

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__SOURCE.equals(eFeature)) {
    		setSource((FamiliesSmartEMF.FamilyRegister) newValue); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__TARGET.equals(eFeature)) {
    		setTarget((PersonsSmartEMF.PersonRegister) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__SOURCE.equals(eFeature)) {
    		setSource((FamiliesSmartEMF.FamilyRegister)null); 
    		return;
    	}
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__TARGET.equals(eFeature)) {
    		setTarget((PersonsSmartEMF.PersonRegister)null); 
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
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__SOURCE.equals(eFeature))
    		return getSource();
    	if (FamiliesToPersonsIBeXTGGPackage.Literals.REGISTER_TO_REGISTER_CORR__TARGET.equals(eFeature))
    		return getTarget();
    	return eDynamicGet(eFeature);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType){
    	throw new UnsupportedOperationException("This method has been deactivated since it is not always safe to use.");
    }
    
    @Override
    public void eInverseAdd(Object otherEnd, EStructuralFeature feature) {
	    if(feature == null)
	    	return;
	    	
    	eDynamicInverseAdd(otherEnd, feature);
	    	}
    	
    @Override
	    	public void eInverseRemove(Object otherEnd, EStructuralFeature feature) {
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
