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

public class FamilyMemberImpl extends SmartObject implements FamiliesSmartEMF.FamilyMember {

    protected java.lang.String name = null;
    protected FamiliesSmartEMF.Family fatherInverse = null;
    protected FamiliesSmartEMF.Family motherInverse = null;
    protected FamiliesSmartEMF.Family sonsInverse = null;
    protected FamiliesSmartEMF.Family daughtersInverse = null;
	
	protected FamilyMemberImpl() {
		super(FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER);
	}
	
    
    @Override
    public java.lang.String getName() {
    	return this.name;
    }
    
    @Override
    public void setName(java.lang.String value) {
    	Object oldValue = this.name;
    	this.name = value;
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__NAME, oldValue, value, -1));
    }
    
    
    @Override
    public FamiliesSmartEMF.Family getFatherInverse() {
    	return this.fatherInverse;
    }
    
    @Override
    public void setFatherInverse(FamiliesSmartEMF.Family value) {
    	
    	Object oldValue = this.fatherInverse;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		       	if(this.fatherInverse != null && value == null) {
    		       		resetContainmentSilently();
    		       	}
    		        this.fatherInverse = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__FATHER_INVERSE, oldValue, value, -1));
    	        	
    	
    	        	if(oldValue != null) {
    	        		((SmartObject) oldValue).eInverseRemove(this, FamiliesSmartEMFPackage.Literals.FAMILY__FATHER);
    	        	}
    	        	if(value != null) {
    	        		((SmartObject) value).eInverseAdd(this, FamiliesSmartEMFPackage.Literals.FAMILY__FATHER);
    	        	}
    }
    
    private void setFatherInverseAsInverse(FamiliesSmartEMF.Family value) {
			    
			    Object oldValue = this.fatherInverse;
			    
			    if(value == null && oldValue == null)
			    	return;
			    	
			    if(value != null && value.equals(oldValue))
			    	return;
			    	
			    
			    
			    	       	if(this.fatherInverse != null && value == null) {
			    	       		resetContainmentSilently();
			    	       	}
			    	        this.fatherInverse = value;
			    	        
			    
			    
			            	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__FATHER_INVERSE, oldValue, value, -1));
			            	
    }
    
    @Override
    public FamiliesSmartEMF.Family getMotherInverse() {
    	return this.motherInverse;
    }
    
    @Override
    public void setMotherInverse(FamiliesSmartEMF.Family value) {
    	
    	Object oldValue = this.motherInverse;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		       	if(this.motherInverse != null && value == null) {
    		       		resetContainmentSilently();
    		       	}
    		        this.motherInverse = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__MOTHER_INVERSE, oldValue, value, -1));
    	        	
    	
    	        	if(oldValue != null) {
    	        		((SmartObject) oldValue).eInverseRemove(this, FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER);
    	        	}
    	        	if(value != null) {
    	        		((SmartObject) value).eInverseAdd(this, FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER);
    	        	}
    }
    
    private void setMotherInverseAsInverse(FamiliesSmartEMF.Family value) {
			    
			    Object oldValue = this.motherInverse;
			    
			    if(value == null && oldValue == null)
			    	return;
			    	
			    if(value != null && value.equals(oldValue))
			    	return;
			    	
			    
			    
			    	       	if(this.motherInverse != null && value == null) {
			    	       		resetContainmentSilently();
			    	       	}
			    	        this.motherInverse = value;
			    	        
			    
			    
			            	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__MOTHER_INVERSE, oldValue, value, -1));
			            	
    }
    
    @Override
    public FamiliesSmartEMF.Family getSonsInverse() {
    	return this.sonsInverse;
    }
    
    @Override
    public void setSonsInverse(FamiliesSmartEMF.Family value) {
    	
    	Object oldValue = this.sonsInverse;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		       	if(this.sonsInverse != null && value == null) {
    		       		resetContainmentSilently();
    		       	}
    		        this.sonsInverse = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__SONS_INVERSE, oldValue, value, -1));
    	        	
    	
    	        	if(oldValue != null) {
    	        		((SmartObject) oldValue).eInverseRemove(this, FamiliesSmartEMFPackage.Literals.FAMILY__SONS);
    	        	}
    	        	if(value != null) {
    	        		((SmartObject) value).eInverseAdd(this, FamiliesSmartEMFPackage.Literals.FAMILY__SONS);
    	        	}
    }
    
    private void setSonsInverseAsInverse(FamiliesSmartEMF.Family value) {
			    
			    Object oldValue = this.sonsInverse;
			    
			    if(value == null && oldValue == null)
			    	return;
			    	
			    if(value != null && value.equals(oldValue))
			    	return;
			    	
			    
			    
			    	       	if(this.sonsInverse != null && value == null) {
			    	       		resetContainmentSilently();
			    	       	}
			    	        this.sonsInverse = value;
			    	        
			    
			    
			            	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__SONS_INVERSE, oldValue, value, -1));
			            	
    }
    
    @Override
    public FamiliesSmartEMF.Family getDaughtersInverse() {
    	return this.daughtersInverse;
    }
    
    @Override
    public void setDaughtersInverse(FamiliesSmartEMF.Family value) {
    	
    	Object oldValue = this.daughtersInverse;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		       	if(this.daughtersInverse != null && value == null) {
    		       		resetContainmentSilently();
    		       	}
    		        this.daughtersInverse = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__DAUGHTERS_INVERSE, oldValue, value, -1));
    	        	
    	
    	        	if(oldValue != null) {
    	        		((SmartObject) oldValue).eInverseRemove(this, FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS);
    	        	}
    	        	if(value != null) {
    	        		((SmartObject) value).eInverseAdd(this, FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS);
    	        	}
    }
    
    private void setDaughtersInverseAsInverse(FamiliesSmartEMF.Family value) {
			    
			    Object oldValue = this.daughtersInverse;
			    
			    if(value == null && oldValue == null)
			    	return;
			    	
			    if(value != null && value.equals(oldValue))
			    	return;
			    	
			    
			    
			    	       	if(this.daughtersInverse != null && value == null) {
			    	       		resetContainmentSilently();
			    	       	}
			    	        this.daughtersInverse = value;
			    	        
			    
			    
			            	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__DAUGHTERS_INVERSE, oldValue, value, -1));
			            	
    }

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__NAME.equals(eFeature)) {
    		setName((java.lang.String) newValue); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__FATHER_INVERSE.equals(eFeature)) {
    		setFatherInverse((FamiliesSmartEMF.Family) newValue); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__MOTHER_INVERSE.equals(eFeature)) {
    		setMotherInverse((FamiliesSmartEMF.Family) newValue); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__SONS_INVERSE.equals(eFeature)) {
    		setSonsInverse((FamiliesSmartEMF.Family) newValue); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__DAUGHTERS_INVERSE.equals(eFeature)) {
    		setDaughtersInverse((FamiliesSmartEMF.Family) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__NAME.equals(eFeature)) {
    		setName((java.lang.String)null); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__FATHER_INVERSE.equals(eFeature)) {
    		setFatherInverse((FamiliesSmartEMF.Family)null); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__MOTHER_INVERSE.equals(eFeature)) {
    		setMotherInverse((FamiliesSmartEMF.Family)null); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__SONS_INVERSE.equals(eFeature)) {
    		setSonsInverse((FamiliesSmartEMF.Family)null); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__DAUGHTERS_INVERSE.equals(eFeature)) {
    		setDaughtersInverse((FamiliesSmartEMF.Family)null); 
    		return;
    	}
    	eDynamicUnset(eFeature);
    }

    @Override
    public String toString(){
		StringBuilder b = new StringBuilder();
		b.append(super.toString());
		b.append(" (");
		if (SmartEMFConfig.simpleStringRepresentations()) {
			b.append(getName());
		} else {
			b.append("name: ");
			b.append(getName());
		}
		b.append(")");
		return b.toString();
    }

 	@Override
    public Object eGet(EStructuralFeature eFeature){
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__NAME.equals(eFeature))
    		return getName();
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__FATHER_INVERSE.equals(eFeature))
    		return getFatherInverse();
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__MOTHER_INVERSE.equals(eFeature))
    		return getMotherInverse();
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__SONS_INVERSE.equals(eFeature))
    		return getSonsInverse();
    	if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__DAUGHTERS_INVERSE.equals(eFeature))
    		return getDaughtersInverse();
    	return eDynamicGet(eFeature);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType){
    	throw new UnsupportedOperationException("This method has been deactivated since it is not always safe to use.");
    }
    
    @Override
    public void eInverseAdd(Object otherEnd, EStructuralFeature feature) {
if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__FATHER_INVERSE.equals(feature)) {
setFatherInverseAsInverse((FamiliesSmartEMF.Family) otherEnd); 
 	return;
			        }	
if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__MOTHER_INVERSE.equals(feature)) {
setMotherInverseAsInverse((FamiliesSmartEMF.Family) otherEnd); 
 	return;
			        }	
if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__SONS_INVERSE.equals(feature)) {
setSonsInverseAsInverse((FamiliesSmartEMF.Family) otherEnd); 
 	return;
			        }	
if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__DAUGHTERS_INVERSE.equals(feature)) {
setDaughtersInverseAsInverse((FamiliesSmartEMF.Family) otherEnd); 
 	return;
			        }	
	    if(feature == null)
	    	return;
	    	
    	eDynamicInverseAdd(otherEnd, feature);
	    	}
    	
    @Override
	    	public void eInverseRemove(Object otherEnd, EStructuralFeature feature) {
if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__FATHER_INVERSE.equals(feature)) {
setFatherInverseAsInverse(null); 
 	return;
			        }
if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__MOTHER_INVERSE.equals(feature)) {
setMotherInverseAsInverse(null); 
 	return;
			        }
if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__SONS_INVERSE.equals(feature)) {
setSonsInverseAsInverse(null); 
 	return;
			        }
if (FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__DAUGHTERS_INVERSE.equals(feature)) {
setDaughtersInverseAsInverse(null); 
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
