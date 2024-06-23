package PersonsSmartEMF.impl;

import PersonsSmartEMF.PersonsSmartEMFPackage;
import PersonsSmartEMF.PersonsSmartEMFPackage;

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

public class PersonImpl extends SmartObject implements PersonsSmartEMF.Person {

    protected java.lang.String name = null;
    protected java.util.Date birthday = (java.util.Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(), "0000-1-1");
    protected PersonsSmartEMF.PersonRegister personsInverse = null;
	
	protected PersonImpl() {
		super(PersonsSmartEMFPackage.Literals.PERSON);
	}
	
    
    @Override
    public java.lang.String getName() {
    	return this.name;
    }
    
    @Override
    public void setName(java.lang.String value) {
    	Object oldValue = this.name;
    	this.name = value;
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, PersonsSmartEMFPackage.Literals.PERSON__NAME, oldValue, value, -1));
    }
    
    
    @Override
    public java.util.Date getBirthday() {
    	return this.birthday;
    }
    
    @Override
    public void setBirthday(java.util.Date value) {
    	Object oldValue = this.birthday;
    	this.birthday = value;
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, PersonsSmartEMFPackage.Literals.PERSON__BIRTHDAY, oldValue, value, -1));
    }
    
    
    @Override
    public PersonsSmartEMF.PersonRegister getPersonsInverse() {
    	return this.personsInverse;
    }
    
    @Override
    public void setPersonsInverse(PersonsSmartEMF.PersonRegister value) {
    	
    	Object oldValue = this.personsInverse;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		       	if(this.personsInverse != null && value == null) {
    		       		resetContainmentSilently();
    		       	}
    		        this.personsInverse = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, PersonsSmartEMFPackage.Literals.PERSON__PERSONS_INVERSE, oldValue, value, -1));
    	        	
    	
    	        	if(oldValue != null) {
    	        		((SmartObject) oldValue).eInverseRemove(this, PersonsSmartEMFPackage.Literals.PERSON_REGISTER__PERSONS);
    	        	}
    	        	if(value != null) {
    	        		((SmartObject) value).eInverseAdd(this, PersonsSmartEMFPackage.Literals.PERSON_REGISTER__PERSONS);
    	        	}
    }
    
    private void setPersonsInverseAsInverse(PersonsSmartEMF.PersonRegister value) {
			    
			    Object oldValue = this.personsInverse;
			    
			    if(value == null && oldValue == null)
			    	return;
			    	
			    if(value != null && value.equals(oldValue))
			    	return;
			    	
			    
			    
			    	       	if(this.personsInverse != null && value == null) {
			    	       		resetContainmentSilently();
			    	       	}
			    	        this.personsInverse = value;
			    	        
			    
			    
			            	sendNotification(SmartEMFNotification.createSetNotification(this, PersonsSmartEMFPackage.Literals.PERSON__PERSONS_INVERSE, oldValue, value, -1));
			            	
    }

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (PersonsSmartEMFPackage.Literals.PERSON__NAME.equals(eFeature)) {
    		setName((java.lang.String) newValue); 
    		return;
    	}
    	if (PersonsSmartEMFPackage.Literals.PERSON__BIRTHDAY.equals(eFeature)) {
    		setBirthday((java.util.Date) newValue); 
    		return;
    	}
    	if (PersonsSmartEMFPackage.Literals.PERSON__PERSONS_INVERSE.equals(eFeature)) {
    		setPersonsInverse((PersonsSmartEMF.PersonRegister) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (PersonsSmartEMFPackage.Literals.PERSON__NAME.equals(eFeature)) {
    		setName((java.lang.String)null); 
    		return;
    	}
    	if (PersonsSmartEMFPackage.Literals.PERSON__BIRTHDAY.equals(eFeature)) {
    		setBirthday((java.util.Date)(java.util.Date) EcoreFactory.eINSTANCE.createFromString(EcorePackage.eINSTANCE.getEDate(), "0000-1-1")); 
    		return;
    	}
    	if (PersonsSmartEMFPackage.Literals.PERSON__PERSONS_INVERSE.equals(eFeature)) {
    		setPersonsInverse((PersonsSmartEMF.PersonRegister)null); 
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
			b.append(", ");
			b.append("birthday: ");
			b.append(getBirthday());
		}
		b.append(")");
		return b.toString();
    }

 	@Override
    public Object eGet(EStructuralFeature eFeature){
    	if (PersonsSmartEMFPackage.Literals.PERSON__NAME.equals(eFeature))
    		return getName();
    	if (PersonsSmartEMFPackage.Literals.PERSON__BIRTHDAY.equals(eFeature))
    		return getBirthday();
    	if (PersonsSmartEMFPackage.Literals.PERSON__PERSONS_INVERSE.equals(eFeature))
    		return getPersonsInverse();
    	return eDynamicGet(eFeature);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType){
    	throw new UnsupportedOperationException("This method has been deactivated since it is not always safe to use.");
    }
    
    @Override
    public void eInverseAdd(Object otherEnd, EStructuralFeature feature) {
if (PersonsSmartEMFPackage.Literals.PERSON__PERSONS_INVERSE.equals(feature)) {
setPersonsInverseAsInverse((PersonsSmartEMF.PersonRegister) otherEnd); 
 	return;
			        }	
	    if(feature == null)
	    	return;
	    	
    	eDynamicInverseAdd(otherEnd, feature);
	    	}
    	
    @Override
	    	public void eInverseRemove(Object otherEnd, EStructuralFeature feature) {
if (PersonsSmartEMFPackage.Literals.PERSON__PERSONS_INVERSE.equals(feature)) {
setPersonsInverseAsInverse(null); 
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
