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

public class PersonRegisterImpl extends SmartObject implements PersonsSmartEMF.PersonRegister {

    protected LinkedSmartESet<PersonsSmartEMF.Person> persons = new LinkedSmartESet<PersonsSmartEMF.Person>(this, PersonsSmartEMFPackage.Literals.PERSON_REGISTER__PERSONS);
	
	protected PersonRegisterImpl() {
		super(PersonsSmartEMFPackage.Literals.PERSON_REGISTER);
	}
	
    
    @Override
    public LinkedSmartESet<PersonsSmartEMF.Person> getPersons() {
    	return this.persons;
    }
    
    @Override
    public void setPersons(LinkedSmartESet<PersonsSmartEMF.Person> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    
    private void addPersonsAsInverse(PersonsSmartEMF.Person value) {
    	if(this.persons.addInternal(value, false) == NotifyStatus.SUCCESS_NO_NOTIFICATION) {
    sendNotification(SmartEMFNotification.createAddNotification(this, PersonsSmartEMFPackage.Literals.PERSON_REGISTER__PERSONS, value, -1));
    	} 
    }
    
    private void removePersonsAsInverse(PersonsSmartEMF.Person value) {
    	persons.removeInternal(value, false, true);
    }

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (PersonsSmartEMFPackage.Literals.PERSON_REGISTER__PERSONS.equals(eFeature)) {
    		setPersons((LinkedSmartESet<PersonsSmartEMF.Person>) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (PersonsSmartEMFPackage.Literals.PERSON_REGISTER__PERSONS.equals(eFeature)) {
    		getPersons().clear(); 
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
    	if (PersonsSmartEMFPackage.Literals.PERSON_REGISTER__PERSONS.equals(eFeature))
    		return getPersons();
    	return eDynamicGet(eFeature);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType){
    	throw new UnsupportedOperationException("This method has been deactivated since it is not always safe to use.");
    }
    
    @Override
    public void eInverseAdd(Object otherEnd, EStructuralFeature feature) {
if (PersonsSmartEMFPackage.Literals.PERSON_REGISTER__PERSONS.equals(feature)) {
	addPersonsAsInverse((PersonsSmartEMF.Person) otherEnd);
 	return;
			        }	
	    if(feature == null)
	    	return;
	    	
    	eDynamicInverseAdd(otherEnd, feature);
	    	}
    	
    @Override
	    	public void eInverseRemove(Object otherEnd, EStructuralFeature feature) {
if (PersonsSmartEMFPackage.Literals.PERSON_REGISTER__PERSONS.equals(feature)) {
	removePersonsAsInverse((PersonsSmartEMF.Person) otherEnd);
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
    	for(Object obj : getPersons()) {
    		setResourceCall.accept(((SmartObject) obj));
	    		}
	    	}
	    	
	    	@Override
	    	/**
	    	* This method sets the resource and only generates REMOVING_ADAPTER notifications (no ADD messages)
	    	*/
    protected void setResourceOfContainmentsSilently(Resource r) { 		
    	for(Object obj : getPersons()) {
    		((SmartObject) obj).setResourceSilently(r);
	    		}
	    	}
}
