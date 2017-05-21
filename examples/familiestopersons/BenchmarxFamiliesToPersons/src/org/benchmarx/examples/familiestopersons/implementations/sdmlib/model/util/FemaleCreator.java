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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Female;
import de.uniks.networkparser.IdMap;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
import org.sdmlib.serialization.EntityFactory;

public class FemaleCreator extends EntityFactory implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Person.PROPERTY_BIRTHDAY,
      Person.PROPERTY_NAME,
      Female.PROPERTY_CFM,
      Female.PROPERTY_REGISTER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Female();
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

      if (Person.PROPERTY_BIRTHDAY.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getBirthday();
      }

      if (Person.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getName();
      }

      if (Female.PROPERTY_CFM.equalsIgnoreCase(attribute))
      {
         return ((Female) target).getCfm();
      }

      if (Female.PROPERTY_REGISTER.equalsIgnoreCase(attribute))
      {
         return ((Female) target).getRegister();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Person.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Person) target).setName((String) value);
         return true;
      }

      if (Person.PROPERTY_BIRTHDAY.equalsIgnoreCase(attrName))
      {
         ((Person) target).setBirthday((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Female.PROPERTY_CFM.equalsIgnoreCase(attrName))
      {
         ((Female) target).setCfm((FamilyMember) value);
         return true;
      }

      if (Female.PROPERTY_REGISTER.equalsIgnoreCase(attrName))
      {
         ((Female) target).setRegister((PersonRegister) value);
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
      ((Female) entity).removeYou();
   }
}
