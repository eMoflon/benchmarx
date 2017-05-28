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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilySet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
   /**
    * 
    * @see <a href='../../../../../../../../src/org/benchmarx/examples/familiestopersons/implementations/sdmlib/ModelGen.java'>ModelGen.java</a>
 */
   public  class FamilyRegister implements SendableEntity
{

   
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
      withoutC(this.getC().toArray(new FamilyMember[this.getC().size()]));
      withoutFamilies(this.getFamilies().toArray(new Family[this.getFamilies().size()]));
      setPersonRegister(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * FamilyRegister ----------------------------------- FamilyMember
    *              familyregister                   c
    * </pre>
    */
   
   public static final String PROPERTY_C = "c";

   private FamilyMemberSet c = null;
   
   public FamilyMemberSet getC()
   {
      if (this.c == null)
      {
         return FamilyMemberSet.EMPTY_SET;
      }
   
      return this.c;
   }

   public FamilyRegister withC(FamilyMember... value)
   {
      if(value==null){
         return this;
      }
      for (FamilyMember item : value)
      {
         if (item != null)
         {
            if (this.c == null)
            {
               this.c = new FamilyMemberSet();
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

   public FamilyRegister withoutC(FamilyMember... value)
   {
      for (FamilyMember item : value)
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

   public FamilyMember createC()
   {
      FamilyMember value = new FamilyMember();
      withC(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * FamilyRegister ----------------------------------- Family
    *              register                   families
    * </pre>
    */
   
   public static final String PROPERTY_FAMILIES = "families";

   private FamilySet families = null;
   
   public FamilySet getFamilies()
   {
      if (this.families == null)
      {
         return FamilySet.EMPTY_SET;
      }
   
      return this.families;
   }

   public FamilyRegister withFamilies(Family... value)
   {
      if(value==null){
         return this;
      }
      for (Family item : value)
      {
         if (item != null)
         {
            if (this.families == null)
            {
               this.families = new FamilySet();
            }
            
            boolean changed = this.families.add (item);

            if (changed)
            {
               item.withRegister(this);
               firePropertyChange(PROPERTY_FAMILIES, null, item);
            }
         }
      }
      return this;
   } 

   public FamilyRegister withoutFamilies(Family... value)
   {
      for (Family item : value)
      {
         if ((this.families != null) && (item != null))
         {
            if (this.families.remove(item))
            {
               item.setRegister(null);
               firePropertyChange(PROPERTY_FAMILIES, item, null);
            }
         }
      }
      return this;
   }

   public Family createFamilies()
   {
      Family value = new Family();
      withFamilies(value);
      return value;
   }

   public Family getFamilies(String name)
   {
      for (Family f : this.getFamilies())
      {
         if (name.equals(f.getName()))
         {
            return f;
         }
      }
      
      return null;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * FamilyRegister ----------------------------------- PersonRegister
    *              familyRegister                   personRegister
    * </pre>
    */
   
   public static final String PROPERTY_PERSONREGISTER = "personRegister";

   private PersonRegister personRegister = null;

   public PersonRegister getPersonRegister()
   {
      return this.personRegister;
   }

   public boolean setPersonRegister(PersonRegister value)
   {
      boolean changed = false;
      
      if (this.personRegister != value)
      {
         PersonRegister oldValue = this.personRegister;
         
         if (this.personRegister != null)
         {
            this.personRegister = null;
            oldValue.setFamilyRegister(null);
         }
         
         this.personRegister = value;
         
         if (value != null)
         {
            value.withFamilyRegister(this);
         }
         
         firePropertyChange(PROPERTY_PERSONREGISTER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public FamilyRegister withPersonRegister(PersonRegister value)
   {
      setPersonRegister(value);
      return this;
   } 

   public PersonRegister createPersonRegister()
   {
      PersonRegister value = new PersonRegister();
      withPersonRegister(value);
      return value;
   }

   public Family getOrCreateFamily(String familyName)
   {
      Family perfectF = null;
      
      for (Family f : this.getFamilies())
      {
         if (familyName.equals(f.getName()))
         {
            if (perfectF == null)
            {
               perfectF = f;
            }
            else if (perfectF.getFather() != null && f.getFather() == null)
            {
               perfectF = f;
               break;
            }
               
         }
      }
      
      if (perfectF == null)
      {
         perfectF = this.createFamilies().withName(familyName);
      }
      return perfectF;
   } 
}
