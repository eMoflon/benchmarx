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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;
   /**
    * 
    * @see <a href='../../../../../../../../src/org/benchmarx/examples/familiestopersons/implementations/sdmlib/ModelGen.java'>ModelGen.java</a>
 */
   public  class Family implements SendableEntity
{

   
   //==========================================================================
   
   public static final FamilyMember[] FAMILY_MEMBERS_ARRAY = new FamilyMember[0];

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
      withoutDaughters(this.getDaughters().toArray(new FamilyMember[this.getDaughters().size()]));
      setRegister(null);
      setFather(null);
      setMother(null);
      withoutSons(this.getSons().toArray(new FamilyMember[this.getSons().size()]));
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
         
         this.getRegister().withC(this.getMother());
         this.getRegister().withC(this.getFather());
         this.getRegister().withC(this.getSons().toArray(new FamilyMember[this.getSons().size()]));
         this.getRegister().withC(this.getDaughters().toArray(new FamilyMember[this.getDaughters().size()]));
      }
   }
   
   public Family withName(String value)
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
    *              one                       many
    * Family ----------------------------------- FamilyMember
    *              daughterOf                   daughters
    * </pre>
    */
   
   public static final String PROPERTY_DAUGHTERS = "daughters";

   private FamilyMemberSet daughters = null;
   
   public FamilyMemberSet getDaughters()
   {
      if (this.daughters == null)
      {
         return FamilyMemberSet.EMPTY_SET;
      }
   
      return this.daughters;
   }

   public Family withDaughters(FamilyMember... value)
   {
      if(value==null){
         return this;
      }
      for (FamilyMember item : value)
      {
         if (item != null)
         {
            if (this.daughters == null)
            {
               this.daughters = new FamilyMemberSet();
            }
            
            boolean changed = this.daughters.add (item);

            if (changed)
            {
               item.withDaughterOf(this);
               firePropertyChange(PROPERTY_DAUGHTERS, null, item);
               this.getRegister().withC(item);
            }
         }
      }
      return this;
   } 

   public Family withoutDaughters(FamilyMember... value)
   {
      for (FamilyMember item : value)
      {
         if ((this.daughters != null) && (item != null))
         {
            if (this.daughters.remove(item))
            {
               item.setDaughterOf(null);
               firePropertyChange(PROPERTY_DAUGHTERS, item, null);
            }
         }
      }
      return this;
   }

   public FamilyMember createDaughters()
   {
      FamilyMember value = new FamilyMember();
      withDaughters(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Family ----------------------------------- FamilyRegister
    *              families                   register
    * </pre>
    */
   
   public static final String PROPERTY_REGISTER = "register";

   private FamilyRegister register = null;

   public FamilyRegister getRegister()
   {
      return this.register;
   }

   public boolean setRegister(FamilyRegister value)
   {
      boolean changed = false;
      
      if (this.register != value)
      {
         FamilyRegister oldValue = this.register;
         
         if (this.register != null)
         {
            this.register = null;
            oldValue.withoutFamilies(this);
         }
         
         this.register = value;
         
         if (value != null)
         {
            value.withFamilies(this);
         }
         
         firePropertyChange(PROPERTY_REGISTER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Family withRegister(FamilyRegister value)
   {
      setRegister(value);
      return this;
   } 

   public FamilyRegister createRegister()
   {
      FamilyRegister value = new FamilyRegister();
      withRegister(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Family ----------------------------------- FamilyMember
    *              fatherOf                   father
    * </pre>
    */
   
   public static final String PROPERTY_FATHER = "father";

   private FamilyMember father = null;

   public FamilyMember getFather()
   {
      return this.father;
   }

   public boolean setFather(FamilyMember value)
   {
      boolean changed = false;
      
      if (this.father != value)
      {
         FamilyMember oldValue = this.father;
         
         if (this.father != null)
         {
            this.father = null;
            oldValue.setFatherOf(null);
         }
         
         this.father = value;
         
         if (value != null)
         {
            value.withFatherOf(this);
            this.getRegister().withC(value);
         }
         
         firePropertyChange(PROPERTY_FATHER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Family withFather(FamilyMember value)
   {
      setFather(value);
      return this;
   } 

   public FamilyMember createFather()
   {
      FamilyMember value = new FamilyMember();
      withFather(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Family ----------------------------------- FamilyMember
    *              motherOf                   mother
    * </pre>
    */
   
   public static final String PROPERTY_MOTHER = "mother";

   private FamilyMember mother = null;

   public FamilyMember getMother()
   {
      return this.mother;
   }

   public boolean setMother(FamilyMember value)
   {
      boolean changed = false;
      
      if (this.mother != value)
      {
         FamilyMember oldValue = this.mother;
         
         if (this.mother != null)
         {
            this.mother = null;
            oldValue.setMotherOf(null);
         }
         
         this.mother = value;
         
         if (value != null)
         {
            value.withMotherOf(this);
            
            this.getRegister().withC(value);
         }
         
         firePropertyChange(PROPERTY_MOTHER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Family withMother(FamilyMember value)
   {
      setMother(value);
      return this;
   } 

   public FamilyMember createMother()
   {
      FamilyMember value = new FamilyMember();
      withMother(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Family ----------------------------------- FamilyMember
    *              sonOf                   sons
    * </pre>
    */
   
   public static final String PROPERTY_SONS = "sons";

   private FamilyMemberSet sons = null;
   
   public FamilyMemberSet getSons()
   {
      if (this.sons == null)
      {
         return FamilyMemberSet.EMPTY_SET;
      }
   
      return this.sons;
   }

   public Family withSons(FamilyMember... value)
   {
      if(value==null){
         return this;
      }
      for (FamilyMember item : value)
      {
         if (item != null)
         {
            if (this.sons == null)
            {
               this.sons = new FamilyMemberSet();
            }
            
            boolean changed = this.sons.add (item);

            if (changed)
            {
               item.withSonOf(this);
               firePropertyChange(PROPERTY_SONS, null, item);
               this.getRegister().withC(item);
            }
         }
      }
      return this;
   } 

   public Family withoutSons(FamilyMember... value)
   {
      for (FamilyMember item : value)
      {
         if ((this.sons != null) && (item != null))
         {
            if (this.sons.remove(item))
            {
               item.setSonOf(null);
               firePropertyChange(PROPERTY_SONS, item, null);
            }
         }
      }
      return this;
   }

   public FamilyMember createSons()
   {
      FamilyMember value = new FamilyMember();
      withSons(value);
      return value;
   }

   public boolean addToFit(FamilyMemberPO familyMemberPO)
   {
      FamilyMember familyMember = familyMemberPO.getCurrentMatch();
      Person person = familyMember.getCp();
      
      if (person instanceof Male)
      {
         if (this.getFather() == null && person.getRegister().preferParentToKid)
         {
            this.withFather(familyMember);
         }
         else
         {
            this.withSons(familyMember);
         }
      }
      else
      {
         if (this.getMother() == null && person.getRegister().preferParentToKid)
         {
            this.withMother(familyMember);
         }
         else
         {
            this.withDaughters(familyMember);
         }
      }
      
      return true;
   } 
}
