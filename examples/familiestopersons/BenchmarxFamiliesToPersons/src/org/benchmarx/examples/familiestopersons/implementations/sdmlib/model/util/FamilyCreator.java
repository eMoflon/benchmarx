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
   
package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import de.uniks.networkparser.IdMap;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;
import org.sdmlib.serialization.EntityFactory;

public class FamilyCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Family.PROPERTY_NAME,
      Family.PROPERTY_DAUGHTERS,
      Family.PROPERTY_REGISTER,
      Family.PROPERTY_FATHER,
      Family.PROPERTY_MOTHER,
      Family.PROPERTY_SONS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Family();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Family.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Family) target).getName();
      }

      if (Family.PROPERTY_DAUGHTERS.equalsIgnoreCase(attribute))
      {
         return ((Family) target).getDaughters();
      }

      if (Family.PROPERTY_REGISTER.equalsIgnoreCase(attribute))
      {
         return ((Family) target).getRegister();
      }

      if (Family.PROPERTY_FATHER.equalsIgnoreCase(attribute))
      {
         return ((Family) target).getFather();
      }

      if (Family.PROPERTY_MOTHER.equalsIgnoreCase(attribute))
      {
         return ((Family) target).getMother();
      }

      if (Family.PROPERTY_SONS.equalsIgnoreCase(attribute))
      {
         return ((Family) target).getSons();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Family.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Family) target).setName((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Family.PROPERTY_DAUGHTERS.equalsIgnoreCase(attrName))
      {
         ((Family) target).withDaughters((FamilyMember) value);
         return true;
      }
      
      if ((Family.PROPERTY_DAUGHTERS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Family) target).withoutDaughters((FamilyMember) value);
         return true;
      }

      if (Family.PROPERTY_REGISTER.equalsIgnoreCase(attrName))
      {
         ((Family) target).setRegister((FamilyRegister) value);
         return true;
      }

      if (Family.PROPERTY_FATHER.equalsIgnoreCase(attrName))
      {
         ((Family) target).setFather((FamilyMember) value);
         return true;
      }

      if (Family.PROPERTY_MOTHER.equalsIgnoreCase(attrName))
      {
         ((Family) target).setMother((FamilyMember) value);
         return true;
      }

      if (Family.PROPERTY_SONS.equalsIgnoreCase(attrName))
      {
         ((Family) target).withSons((FamilyMember) value);
         return true;
      }
      
      if ((Family.PROPERTY_SONS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Family) target).withoutSons((FamilyMember) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Family) entity).removeYou();
   }
}
