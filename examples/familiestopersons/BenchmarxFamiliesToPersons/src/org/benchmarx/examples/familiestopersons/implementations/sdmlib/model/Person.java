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
import de.uniks.networkparser.EntityUtil;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
   /**
    * 
    * @see <a href='../../../../../../../../src/org/benchmarx/examples/familiestopersons/implementations/sdmlib/ModelGen.java'>ModelGen.java</a>
 */
   public  class Person implements SendableEntity
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
      FamilyMember oldCfm = getCfm();
      setCfm(null);
      if (oldCfm != null)
      {
         oldCfm.removeYou();
      }
      if (getRegister() != null)
      {
         getRegister().withoutC(this);
      }
      setRegister(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_BIRTHDAY = "birthday";
   
   private String birthday = "0001-01-01";

   public String getBirthday()
   {
      return this.birthday;
   }
   
   public void setBirthday(String value)
   {
      if ( ! EntityUtil.stringEquals(this.birthday, value)) {
      
         String oldValue = this.birthday;
         this.birthday = value;
         this.firePropertyChange(PROPERTY_BIRTHDAY, oldValue, value);
      }
   }
   
   public Person withBirthday(String value)
   {
      setBirthday(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getBirthday());
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! EntityUtil.stringEquals(this.name, value)) {
      
         String oldValue = this.name;
         this.name = value;
         this.firePropertyChange(PROPERTY_NAME, oldValue, value);
         
         this.getRegister().withC(this);
      }
   }
   
   public Person withName(String value)
   {
      setName(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Person ----------------------------------- FamilyMember
    *              cp                   cfm
    * </pre>
    */
   
   public static final String PROPERTY_CFM = "cfm";

   private FamilyMember cfm = null;

   public FamilyMember getCfm()
   {
      return this.cfm;
   }

   public boolean setCfm(FamilyMember value)
   {
      boolean changed = false;
      
      if (this.cfm != value)
      {
         FamilyMember oldValue = this.cfm;
         
         if (this.cfm != null)
         {
            this.cfm = null;
            oldValue.setCp(null);
         }
         
         this.cfm = value;
         
         if (value != null)
         {
            value.withCp(this);
         }
         
         firePropertyChange(PROPERTY_CFM, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withCfm(FamilyMember value)
   {
      setCfm(value);
      return this;
   } 

   public FamilyMember createCfm()
   {
      FamilyMember value = new FamilyMember();
      withCfm(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Person ----------------------------------- PersonRegister
    *              persons                   register
    * </pre>
    */
   
   public static final String PROPERTY_REGISTER = "register";

   private PersonRegister register = null;

   public PersonRegister getRegister()
   {
      return this.register;
   }

   public boolean setRegister(PersonRegister value)
   {
      boolean changed = false;
      
      if (this.register != value)
      {
         PersonRegister oldValue = this.register;
         
         if (this.register != null)
         {
            this.register = null;
            oldValue.withoutPersons(this);
         }
         
         this.register = value;
         
         if (value != null)
         {
            value.withPersons(this);
         }
         
         firePropertyChange(PROPERTY_REGISTER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withRegister(PersonRegister value)
   {
      setRegister(value);
      return this;
   } 

   public PersonRegister createRegister()
   {
      PersonRegister value = new PersonRegister();
      withRegister(value);
      return value;
   }

   public String getGivenName()
   {
      String[] split = this.getName().split(", ");
      
      return split[1];
   }

   public String getFamilyName()
   {
      String[] split = this.getName().split(", ");
      
      return split[0];
   }

   public int compareTo(Person o)
   {
      int result = this.getName().compareTo(o.getName());
      
      if (result == 0) return this.getBirthday().compareTo(o.getBirthday());
      
      return result;
   } 
}
