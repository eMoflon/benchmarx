/*
   Copyright (c) 2017 zuendorf
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Male;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Female;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;

/**
 * 
 * @see <a href='../../../../../../../../src/org/benchmarx/examples/familiestopersons/implementations/sdmlib/ModelGen.java'>ModelGen.java</a>
 */
public  class PersonRegister implements SendableEntity
{
   public boolean preferExistingFamily = true;
   public boolean preferParentToKid = true;

   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutC(this.getC().toArray(new Person[this.getC().size()]));
      withoutPersons(this.getPersons().toArray(new Person[this.getPersons().size()]));
      setFamilyRegister(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * PersonRegister ----------------------------------- Person
    *              personregister                   c
    * </pre>
    */
   
   public static final String PROPERTY_C = "c";

   private PersonSet c = null;
   
   public PersonSet getC()
   {
      if (this.c == null)
      {
         return PersonSet.EMPTY_SET;
      }
   
      return this.c;
   }

   public PersonRegister withC(Person... value)
   {
      if(value==null){
         return this;
      }
      for (Person item : value)
      {
         if (item != null)
         {
            if (this.c == null)
            {
               this.c = new PersonSet();
            }
            
            boolean changed = this.c.add (item);

            if (changed)
            {
               firePropertyChange(PROPERTY_C, null, item);
            }
         }
      }
      return this;
   } 

   public PersonRegister withoutC(Person... value)
   {
      for (Person item : value)
      {
         if ((this.c != null) && (item != null))
         {
            if (this.c.remove(item))
            {
               firePropertyChange(PROPERTY_C, item, null);
            }
         }
      }
      return this;
   }

   public Person createC()
   {
      Person value = new Person();
      withC(value);
      return value;
   } 

   public Male createCMale()
   {
      Male value = new Male();
      withC(value);
      return value;
   } 

   public Female createCFemale()
   {
      Female value = new Female();
      withC(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * PersonRegister ----------------------------------- Person
    *              register                   persons
    * </pre>
    */
   
   public static final String PROPERTY_PERSONS = "persons";

   private PersonSet persons = null;
   
   public PersonSet getPersons()
   {
      if (this.persons == null)
      {
         return PersonSet.EMPTY_SET;
      }
   
      return this.persons;
   }

   public PersonRegister withPersons(Person... value)
   {
      if(value==null){
         return this;
      }
      for (Person item : value)
      {
         if (item != null)
         {
            if (this.persons == null)
            {
               this.persons = new PersonSet();
            }
            
            boolean changed = this.persons.add (item);

            if (changed)
            {
               item.withRegister(this);
               firePropertyChange(PROPERTY_PERSONS, null, item);
            }
         }
      }
      return this;
   } 

   public PersonRegister withoutPersons(Person... value)
   {
      for (Person item : value)
      {
         if ((this.persons != null) && (item != null))
         {
            if (this.persons.remove(item))
            {
               item.setRegister(null);
               firePropertyChange(PROPERTY_PERSONS, item, null);
            }
         }
      }
      return this;
   }

   public Person createPersons()
   {
      Person value = new Person();
      withPersons(value);
      return value;
   } 

   public Male createPersonsMale()
   {
      Male value = new Male();
      withPersons(value);
      return value;
   } 

   public Female createPersonsFemale()
   {
      Female value = new Female();
      withPersons(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * PersonRegister ----------------------------------- FamilyRegister
    *              personRegister                   familyRegister
    * </pre>
    */
   
   public static final String PROPERTY_FAMILYREGISTER = "familyRegister";

   private FamilyRegister familyRegister = null;

   public FamilyRegister getFamilyRegister()
   {
      return this.familyRegister;
   }

   public boolean setFamilyRegister(FamilyRegister value)
   {
      boolean changed = false;
      
      if (this.familyRegister != value)
      {
         FamilyRegister oldValue = this.familyRegister;
         
         if (this.familyRegister != null)
         {
            this.familyRegister = null;
            oldValue.setPersonRegister(null);
         }
         
         this.familyRegister = value;
         
         if (value != null)
         {
            value.withPersonRegister(this);
         }
         
         firePropertyChange(PROPERTY_FAMILYREGISTER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public PersonRegister withFamilyRegister(FamilyRegister value)
   {
      setFamilyRegister(value);
      return this;
   } 

   public FamilyRegister createFamilyRegister()
   {
      FamilyRegister value = new FamilyRegister();
      withFamilyRegister(value);
      return value;
   } 
}
