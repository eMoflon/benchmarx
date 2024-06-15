package FamiliesSmartEMF.impl;

import FamiliesSmartEMF.FamiliesSmartEMFPackage;
import FamiliesSmartEMF.FamiliesSmartEMFPackage;

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

public class FamilyRegisterImpl extends SmartObject implements FamiliesSmartEMF.FamilyRegister {

    protected LinkedSmartESet<FamiliesSmartEMF.Family> families = new LinkedSmartESet<FamiliesSmartEMF.Family>(this, FamiliesSmartEMFPackage.Literals.FAMILY_REGISTER__FAMILIES);
	
	protected FamilyRegisterImpl() {
		super(FamiliesSmartEMFPackage.Literals.FAMILY_REGISTER);
	}
	
    
    @Override
    public LinkedSmartESet<FamiliesSmartEMF.Family> getFamilies() {
    	return this.families;
    }
    
    @Override
    public void setFamilies(LinkedSmartESet<FamiliesSmartEMF.Family> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    
    private void addFamiliesAsInverse(FamiliesSmartEMF.Family value) {
    	if(this.families.addInternal(value, false) == NotifyStatus.SUCCESS_NO_NOTIFICATION) {
    sendNotification(SmartEMFNotification.createAddNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY_REGISTER__FAMILIES, value, -1));
    	} 
    }
    
    private void removeFamiliesAsInverse(FamiliesSmartEMF.Family value) {
    	families.removeInternal(value, false, true);
    }

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_REGISTER__FAMILIES.equals(eFeature)) {
    		setFamilies((LinkedSmartESet<FamiliesSmartEMF.Family>) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_REGISTER__FAMILIES.equals(eFeature)) {
    		getFamilies().clear(); 
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
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_REGISTER__FAMILIES.equals(eFeature))
    		return getFamilies();
    	return eDynamicGet(eFeature);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType){
    	throw new UnsupportedOperationException("This method has been deactivated since it is not always safe to use.");
    }
    
    @Override
    public void eInverseAdd(Object otherEnd, EStructuralFeature feature) {
if (FamiliesSmartEMFPackage.Literals.FAMILY_REGISTER__FAMILIES.equals(feature)) {
	addFamiliesAsInverse((FamiliesSmartEMF.Family) otherEnd);
 	return;
			        }	
	    if(feature == null)
	    	return;
	    	
    	eDynamicInverseAdd(otherEnd, feature);
	    	}
    	
    @Override
	    	public void eInverseRemove(Object otherEnd, EStructuralFeature feature) {
if (FamiliesSmartEMFPackage.Literals.FAMILY_REGISTER__FAMILIES.equals(feature)) {
	removeFamiliesAsInverse((FamiliesSmartEMF.Family) otherEnd);
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
    	for(Object obj : getFamilies()) {
    		setResourceCall.accept(((SmartObject) obj));
	    		}
	    	}
	    	
	    	@Override
	    	/**
	    	* This method sets the resource and only generates REMOVING_ADAPTER notifications (no ADD messages)
	    	*/
    protected void setResourceOfContainmentsSilently(Resource r) { 		
    	for(Object obj : getFamilies()) {
    		((SmartObject) obj).setResourceSilently(r);
	    		}
	    	}
}
