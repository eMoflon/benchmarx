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

public class FamilyImpl extends SmartObject implements FamiliesSmartEMF.Family {

    protected FamiliesSmartEMF.FamilyMember father = null;
    protected FamiliesSmartEMF.FamilyMember mother = null;
    protected LinkedSmartESet<FamiliesSmartEMF.FamilyMember> sons = new LinkedSmartESet<FamiliesSmartEMF.FamilyMember>(this, FamiliesSmartEMFPackage.Literals.FAMILY__SONS);
    protected LinkedSmartESet<FamiliesSmartEMF.FamilyMember> daughters = new LinkedSmartESet<FamiliesSmartEMF.FamilyMember>(this, FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS);
    protected java.lang.String name = null;
    protected FamiliesSmartEMF.FamilyRegister familiesInverse = null;
	
	protected FamilyImpl() {
		super(FamiliesSmartEMFPackage.Literals.FAMILY);
	}
	
    
    @Override
    public FamiliesSmartEMF.FamilyMember getFather() {
    	return this.father;
    }
    
    @Override
    public void setFather(FamiliesSmartEMF.FamilyMember value) {
    	
    	Object oldValue = this.father;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	Resource.Internal resource = (Resource.Internal) eResource();
    		        if(oldValue != null && value != null) {
    		        	setResourceWithoutChecks(null);
    		        }
    		        
    		        NotifyStatus status = NotifyStatus.SUCCESS_NO_NOTIFICATION;
    	if(oldValue != null) {
    	        		status = ((MinimalSObjectContainer) oldValue).resetContainment();
    	}	
    	
    		        this.father = value;
    		        
    	
    	
    			 	if(oldValue != null && value != null) {
    		        	setResourceWithoutChecks(resource);
    		        }
    	
    	if(value != null)
    		status = ((MinimalSObjectContainer) this.father).setContainment(this, FamiliesSmartEMFPackage.Literals.FAMILY__FATHER);
    	
    	
    	if(status == NotifyStatus.SUCCESS_NO_NOTIFICATION || oldValue != null && value != null)
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY__FATHER, oldValue, value, -1));
    	        	
    	
    	        	if(oldValue != null) {
    	        		((SmartObject) oldValue).eInverseRemove(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__FATHER_INVERSE);
    	        	}
    	        	if(value != null) {
    	        		((SmartObject) value).eInverseAdd(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__FATHER_INVERSE);
    	        	}
    }
    
    private void setFatherAsInverse(FamiliesSmartEMF.FamilyMember value) {
			    
			    Object oldValue = this.father;
			    
			    if(value == null && oldValue == null)
			    	return;
			    	
			    if(value != null && value.equals(oldValue))
			    	return;
			    	
			    
			    Resource.Internal resource = (Resource.Internal) eResource();
			    	        if(oldValue != null && value != null) {
			    	        	setResourceWithoutChecks(null);
			    	        }
			    	        
			    	        NotifyStatus status = NotifyStatus.SUCCESS_NO_NOTIFICATION;
			    if(oldValue != null) {
			            		status = ((MinimalSObjectContainer) oldValue).resetContainment();
			    }	
			    
			    	        this.father = value;
			    	        
			    
			    
			    		 	if(oldValue != null && value != null) {
			    	        	setResourceWithoutChecks(resource);
			    	        }
			    
			    if(value != null)
			    	status = ((MinimalSObjectContainer) this.father).setContainment(this, FamiliesSmartEMFPackage.Literals.FAMILY__FATHER);
			    
			    
			    if(status == NotifyStatus.SUCCESS_NO_NOTIFICATION || oldValue != null && value != null)
			            	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY__FATHER, oldValue, value, -1));
			            	
    }
    
    @Override
    public FamiliesSmartEMF.FamilyMember getMother() {
    	return this.mother;
    }
    
    @Override
    public void setMother(FamiliesSmartEMF.FamilyMember value) {
    	
    	Object oldValue = this.mother;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	Resource.Internal resource = (Resource.Internal) eResource();
    		        if(oldValue != null && value != null) {
    		        	setResourceWithoutChecks(null);
    		        }
    		        
    		        NotifyStatus status = NotifyStatus.SUCCESS_NO_NOTIFICATION;
    	if(oldValue != null) {
    	        		status = ((MinimalSObjectContainer) oldValue).resetContainment();
    	}	
    	
    		        this.mother = value;
    		        
    	
    	
    			 	if(oldValue != null && value != null) {
    		        	setResourceWithoutChecks(resource);
    		        }
    	
    	if(value != null)
    		status = ((MinimalSObjectContainer) this.mother).setContainment(this, FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER);
    	
    	
    	if(status == NotifyStatus.SUCCESS_NO_NOTIFICATION || oldValue != null && value != null)
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER, oldValue, value, -1));
    	        	
    	
    	        	if(oldValue != null) {
    	        		((SmartObject) oldValue).eInverseRemove(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__MOTHER_INVERSE);
    	        	}
    	        	if(value != null) {
    	        		((SmartObject) value).eInverseAdd(this, FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__MOTHER_INVERSE);
    	        	}
    }
    
    private void setMotherAsInverse(FamiliesSmartEMF.FamilyMember value) {
			    
			    Object oldValue = this.mother;
			    
			    if(value == null && oldValue == null)
			    	return;
			    	
			    if(value != null && value.equals(oldValue))
			    	return;
			    	
			    
			    Resource.Internal resource = (Resource.Internal) eResource();
			    	        if(oldValue != null && value != null) {
			    	        	setResourceWithoutChecks(null);
			    	        }
			    	        
			    	        NotifyStatus status = NotifyStatus.SUCCESS_NO_NOTIFICATION;
			    if(oldValue != null) {
			            		status = ((MinimalSObjectContainer) oldValue).resetContainment();
			    }	
			    
			    	        this.mother = value;
			    	        
			    
			    
			    		 	if(oldValue != null && value != null) {
			    	        	setResourceWithoutChecks(resource);
			    	        }
			    
			    if(value != null)
			    	status = ((MinimalSObjectContainer) this.mother).setContainment(this, FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER);
			    
			    
			    if(status == NotifyStatus.SUCCESS_NO_NOTIFICATION || oldValue != null && value != null)
			            	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER, oldValue, value, -1));
			            	
    }
    
    @Override
    public LinkedSmartESet<FamiliesSmartEMF.FamilyMember> getSons() {
    	return this.sons;
    }
    
    @Override
    public void setSons(LinkedSmartESet<FamiliesSmartEMF.FamilyMember> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    
    private void addSonsAsInverse(FamiliesSmartEMF.FamilyMember value) {
    	if(this.sons.addInternal(value, false) == NotifyStatus.SUCCESS_NO_NOTIFICATION) {
    sendNotification(SmartEMFNotification.createAddNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY__SONS, value, -1));
    	} 
    }
    
    private void removeSonsAsInverse(FamiliesSmartEMF.FamilyMember value) {
    	sons.removeInternal(value, false, true);
    }
    
    @Override
    public LinkedSmartESet<FamiliesSmartEMF.FamilyMember> getDaughters() {
    	return this.daughters;
    }
    
    @Override
    public void setDaughters(LinkedSmartESet<FamiliesSmartEMF.FamilyMember> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    
    private void addDaughtersAsInverse(FamiliesSmartEMF.FamilyMember value) {
    	if(this.daughters.addInternal(value, false) == NotifyStatus.SUCCESS_NO_NOTIFICATION) {
    sendNotification(SmartEMFNotification.createAddNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS, value, -1));
    	} 
    }
    
    private void removeDaughtersAsInverse(FamiliesSmartEMF.FamilyMember value) {
    	daughters.removeInternal(value, false, true);
    }
    
    @Override
    public java.lang.String getName() {
    	return this.name;
    }
    
    @Override
    public void setName(java.lang.String value) {
    	Object oldValue = this.name;
    	this.name = value;
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY__NAME, oldValue, value, -1));
    }
    
    
    @Override
    public FamiliesSmartEMF.FamilyRegister getFamiliesInverse() {
    	return this.familiesInverse;
    }
    
    @Override
    public void setFamiliesInverse(FamiliesSmartEMF.FamilyRegister value) {
    	
    	Object oldValue = this.familiesInverse;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		       	if(this.familiesInverse != null && value == null) {
    		       		resetContainmentSilently();
    		       	}
    		        this.familiesInverse = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY__FAMILIES_INVERSE, oldValue, value, -1));
    	        	
    	
    	        	if(oldValue != null) {
    	        		((SmartObject) oldValue).eInverseRemove(this, FamiliesSmartEMFPackage.Literals.FAMILY_REGISTER__FAMILIES);
    	        	}
    	        	if(value != null) {
    	        		((SmartObject) value).eInverseAdd(this, FamiliesSmartEMFPackage.Literals.FAMILY_REGISTER__FAMILIES);
    	        	}
    }
    
    private void setFamiliesInverseAsInverse(FamiliesSmartEMF.FamilyRegister value) {
			    
			    Object oldValue = this.familiesInverse;
			    
			    if(value == null && oldValue == null)
			    	return;
			    	
			    if(value != null && value.equals(oldValue))
			    	return;
			    	
			    
			    
			    	       	if(this.familiesInverse != null && value == null) {
			    	       		resetContainmentSilently();
			    	       	}
			    	        this.familiesInverse = value;
			    	        
			    
			    
			            	sendNotification(SmartEMFNotification.createSetNotification(this, FamiliesSmartEMFPackage.Literals.FAMILY__FAMILIES_INVERSE, oldValue, value, -1));
			            	
    }

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__FATHER.equals(eFeature)) {
    		setFather((FamiliesSmartEMF.FamilyMember) newValue); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER.equals(eFeature)) {
    		setMother((FamiliesSmartEMF.FamilyMember) newValue); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__SONS.equals(eFeature)) {
    		setSons((LinkedSmartESet<FamiliesSmartEMF.FamilyMember>) newValue); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS.equals(eFeature)) {
    		setDaughters((LinkedSmartESet<FamiliesSmartEMF.FamilyMember>) newValue); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__NAME.equals(eFeature)) {
    		setName((java.lang.String) newValue); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__FAMILIES_INVERSE.equals(eFeature)) {
    		setFamiliesInverse((FamiliesSmartEMF.FamilyRegister) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__FATHER.equals(eFeature)) {
    		setFather((FamiliesSmartEMF.FamilyMember)null); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER.equals(eFeature)) {
    		setMother((FamiliesSmartEMF.FamilyMember)null); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__SONS.equals(eFeature)) {
    		getSons().clear(); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS.equals(eFeature)) {
    		getDaughters().clear(); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__NAME.equals(eFeature)) {
    		setName((java.lang.String)null); 
    		return;
    	}
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__FAMILIES_INVERSE.equals(eFeature)) {
    		setFamiliesInverse((FamiliesSmartEMF.FamilyRegister)null); 
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
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__FATHER.equals(eFeature))
    		return getFather();
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER.equals(eFeature))
    		return getMother();
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__SONS.equals(eFeature))
    		return getSons();
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS.equals(eFeature))
    		return getDaughters();
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__NAME.equals(eFeature))
    		return getName();
    	if (FamiliesSmartEMFPackage.Literals.FAMILY__FAMILIES_INVERSE.equals(eFeature))
    		return getFamiliesInverse();
    	return eDynamicGet(eFeature);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType){
    	throw new UnsupportedOperationException("This method has been deactivated since it is not always safe to use.");
    }
    
    @Override
    public void eInverseAdd(Object otherEnd, EStructuralFeature feature) {
if (FamiliesSmartEMFPackage.Literals.FAMILY__FATHER.equals(feature)) {
setFatherAsInverse((FamiliesSmartEMF.FamilyMember) otherEnd); 
 	return;
			        }	
if (FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER.equals(feature)) {
setMotherAsInverse((FamiliesSmartEMF.FamilyMember) otherEnd); 
 	return;
			        }	
if (FamiliesSmartEMFPackage.Literals.FAMILY__SONS.equals(feature)) {
	addSonsAsInverse((FamiliesSmartEMF.FamilyMember) otherEnd);
 	return;
			        }	
if (FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS.equals(feature)) {
	addDaughtersAsInverse((FamiliesSmartEMF.FamilyMember) otherEnd);
 	return;
			        }	
if (FamiliesSmartEMFPackage.Literals.FAMILY__FAMILIES_INVERSE.equals(feature)) {
setFamiliesInverseAsInverse((FamiliesSmartEMF.FamilyRegister) otherEnd); 
 	return;
			        }	
	    if(feature == null)
	    	return;
	    	
    	eDynamicInverseAdd(otherEnd, feature);
	    	}
    	
    @Override
	    	public void eInverseRemove(Object otherEnd, EStructuralFeature feature) {
if (FamiliesSmartEMFPackage.Literals.FAMILY__FATHER.equals(feature)) {
setFatherAsInverse(null); 
 	return;
			        }
if (FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER.equals(feature)) {
setMotherAsInverse(null); 
 	return;
			        }
if (FamiliesSmartEMFPackage.Literals.FAMILY__SONS.equals(feature)) {
	removeSonsAsInverse((FamiliesSmartEMF.FamilyMember) otherEnd);
 	return;
			        }
if (FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS.equals(feature)) {
	removeDaughtersAsInverse((FamiliesSmartEMF.FamilyMember) otherEnd);
 	return;
			        }
if (FamiliesSmartEMFPackage.Literals.FAMILY__FAMILIES_INVERSE.equals(feature)) {
setFamiliesInverseAsInverse(null); 
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
if(getFather() != null)
	setResourceCall.accept((SmartObject) getFather());
if(getMother() != null)
	setResourceCall.accept((SmartObject) getMother());
    	for(Object obj : getSons()) {
    		setResourceCall.accept(((SmartObject) obj));
	    		}
    	for(Object obj : getDaughters()) {
    		setResourceCall.accept(((SmartObject) obj));
	    		}
	    	}
	    	
	    	@Override
	    	/**
	    	* This method sets the resource and only generates REMOVING_ADAPTER notifications (no ADD messages)
	    	*/
    protected void setResourceOfContainmentsSilently(Resource r) { 		
if(getFather() != null)
    ((SmartObject) getFather()).setResourceSilently(r);
if(getMother() != null)
    ((SmartObject) getMother()).setResourceSilently(r);
    	for(Object obj : getSons()) {
    		((SmartObject) obj).setResourceSilently(r);
	    		}
    	for(Object obj : getDaughters()) {
    		((SmartObject) obj).setResourceSilently(r);
	    		}
	    	}
}
