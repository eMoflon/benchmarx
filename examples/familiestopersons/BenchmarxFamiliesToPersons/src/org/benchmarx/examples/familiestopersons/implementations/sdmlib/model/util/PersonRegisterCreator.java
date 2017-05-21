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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;

public class PersonRegisterCreator extends EntityFactory implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      PersonRegister.PROPERTY_C,
      PersonRegister.PROPERTY_PERSONS,
      PersonRegister.PROPERTY_FAMILYREGISTER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new PersonRegister();
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

      if (PersonRegister.PROPERTY_C.equalsIgnoreCase(attribute))
      {
         return ((PersonRegister) target).getC();
      }

      if (PersonRegister.PROPERTY_PERSONS.equalsIgnoreCase(attribute))
      {
         return ((PersonRegister) target).getPersons();
      }

      if (PersonRegister.PROPERTY_FAMILYREGISTER.equalsIgnoreCase(attribute))
      {
         return ((PersonRegister) target).getFamilyRegister();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (PersonRegister.PROPERTY_C.equalsIgnoreCase(attrName))
      {
         ((PersonRegister) target).withC((Person) value);
         return true;
      }
      
      if ((PersonRegister.PROPERTY_C + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PersonRegister) target).withoutC((Person) value);
         return true;
      }

      if (PersonRegister.PROPERTY_PERSONS.equalsIgnoreCase(attrName))
      {
         ((PersonRegister) target).withPersons((Person) value);
         return true;
      }
      
      if ((PersonRegister.PROPERTY_PERSONS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PersonRegister) target).withoutPersons((Person) value);
         return true;
      }

      if (PersonRegister.PROPERTY_FAMILYREGISTER.equalsIgnoreCase(attrName))
      {
         ((PersonRegister) target).setFamilyRegister((FamilyRegister) value);
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
      ((PersonRegister) entity).removeYou();
   }
}
