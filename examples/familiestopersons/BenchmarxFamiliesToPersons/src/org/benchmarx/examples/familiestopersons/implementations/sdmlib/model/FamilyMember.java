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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilySet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
   /**
    * 
    * @see <a href='../../../../../../../../src/org/benchmarx/examples/familiestopersons/implementations/sdmlib/ModelGen.java'>ModelGen.java</a>
 */
   public  class FamilyMember implements SendableEntity
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
      Person oldCp = getCp();
      setCp(null);
      if (oldCp != null) 
      {
         oldCp.removeYou();
      }
      setDaughterOf(null);
      setFatherOf(null);
      setMotherOf(null);
      setSonOf(null);
      firePropertyChange("REMOVE_YOU", this, null);
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
         
         Family f = getFamily();
         if (f != null) 
         {
            f.getRegister().withC(this);
         }
      }
   }

   public Family getFamily()
   {
      Family f = this.getMotherOf();
      if (f == null) f = this.getFatherOf();
      if (f == null) f = this.getSonOf();
      if (f == null) f = this.getDaughterOf();
      return f;
   }
   
   public FamilySet getFamilySet()
   {
      Family f = this.getFamily();
      return new FamilySet(f);
   }
   
   public FamilyMember withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       one
    * FamilyMember ----------------------------------- Person
    *              cfm                   cp
    * </pre>
    */
   
   public static final String PROPERTY_CP = "cp";

   private Person cp = null;

   public Person getCp()
   {
      return this.cp;
   }

   public boolean setCp(Person value)
   {
      boolean changed = false;
      
      if (this.cp != value)
      {
         Person oldValue = this.cp;
         
         if (this.cp != null)
         {
            this.cp = null;
            oldValue.setCfm(null);
         }
         
         this.cp = value;
         
         if (value != null)
         {
            value.withCfm(this);
         }
         
         firePropertyChange(PROPERTY_CP, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public FamilyMember withCp(Person value)
   {
      setCp(value);
      return this;
   } 

   public Person createCp()
   {
      Person value = new Person();
      withCp(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * FamilyMember ----------------------------------- Family
    *              daughters                   daughterOf
    * </pre>
    */
   
   public static final String PROPERTY_DAUGHTEROF = "daughterOf";

   private Family daughterOf = null;

   public Family getDaughterOf()
   {
      return this.daughterOf;
   }

   public boolean setDaughterOf(Family value)
   {
      boolean changed = false;
      
      if (this.daughterOf != value)
      {
         Family oldValue = this.daughterOf;
         
         if (this.daughterOf != null)
         {
            this.daughterOf = null;
            oldValue.withoutDaughters(this);
         }
         
         this.daughterOf = value;
         
         if (value != null)
         {
            value.withDaughters(this);
         }
         
         firePropertyChange(PROPERTY_DAUGHTEROF, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public FamilyMember withDaughterOf(Family value)
   {
      setDaughterOf(value);
      return this;
   } 

   public Family createDaughterOf()
   {
      Family value = new Family();
      withDaughterOf(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * FamilyMember ----------------------------------- Family
    *              father                   fatherOf
    * </pre>
    */
   
   public static final String PROPERTY_FATHEROF = "fatherOf";

   private Family fatherOf = null;

   public Family getFatherOf()
   {
      return this.fatherOf;
   }

   public boolean setFatherOf(Family value)
   {
      boolean changed = false;
      
      if (this.fatherOf != value)
      {
         Family oldValue = this.fatherOf;
         
         if (this.fatherOf != null)
         {
            this.fatherOf = null;
            oldValue.setFather(null);
         }
         
         this.fatherOf = value;
         
         if (value != null)
         {
            value.withFather(this);
         }
         
         firePropertyChange(PROPERTY_FATHEROF, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public FamilyMember withFatherOf(Family value)
   {
      setFatherOf(value);
      return this;
   } 

   public Family createFatherOf()
   {
      Family value = new Family();
      withFatherOf(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * FamilyMember ----------------------------------- Family
    *              mother                   motherOf
    * </pre>
    */
   
   public static final String PROPERTY_MOTHEROF = "motherOf";

   private Family motherOf = null;

   public Family getMotherOf()
   {
      return this.motherOf;
   }

   public boolean setMotherOf(Family value)
   {
      boolean changed = false;
      
      if (this.motherOf != value)
      {
         Family oldValue = this.motherOf;
         
         if (this.motherOf != null)
         {
            this.motherOf = null;
            oldValue.setMother(null);
         }
         
         this.motherOf = value;
         
         if (value != null)
         {
            value.withMother(this);
         }
         
         firePropertyChange(PROPERTY_MOTHEROF, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public FamilyMember withMotherOf(Family value)
   {
      setMotherOf(value);
      return this;
   } 

   public Family createMotherOf()
   {
      Family value = new Family();
      withMotherOf(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * FamilyMember ----------------------------------- Family
    *              sons                   sonOf
    * </pre>
    */
   
   public static final String PROPERTY_SONOF = "sonOf";

   private Family sonOf = null;

   public Family getSonOf()
   {
      return this.sonOf;
   }

   public boolean setSonOf(Family value)
   {
      boolean changed = false;
      
      if (this.sonOf != value)
      {
         Family oldValue = this.sonOf;
         
         if (this.sonOf != null)
         {
            this.sonOf = null;
            oldValue.withoutSons(this);
         }
         
         this.sonOf = value;
         
         if (value != null)
         {
            value.withSons(this);
         }
         
         firePropertyChange(PROPERTY_SONOF, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public FamilyMember withSonOf(Family value)
   {
      setSonOf(value);
      return this;
   } 

   public Family createSonOf()
   {
      Family value = new Family();
      withSonOf(value);
      return value;
   } 
}
