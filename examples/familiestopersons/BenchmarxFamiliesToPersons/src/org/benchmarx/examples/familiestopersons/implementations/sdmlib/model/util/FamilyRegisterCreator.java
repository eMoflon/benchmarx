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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;
import de.uniks.networkparser.IdMap;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
import org.sdmlib.serialization.EntityFactory;

public class FamilyRegisterCreator extends EntityFactory implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      FamilyRegister.PROPERTY_C,
      FamilyRegister.PROPERTY_FAMILIES,
      FamilyRegister.PROPERTY_PERSONREGISTER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new FamilyRegister();
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

      if (FamilyRegister.PROPERTY_C.equalsIgnoreCase(attribute))
      {
         return ((FamilyRegister) target).getC();
      }

      if (FamilyRegister.PROPERTY_FAMILIES.equalsIgnoreCase(attribute))
      {
         return ((FamilyRegister) target).getFamilies();
      }

      if (FamilyRegister.PROPERTY_PERSONREGISTER.equalsIgnoreCase(attribute))
      {
         return ((FamilyRegister) target).getPersonRegister();
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

      if (FamilyRegister.PROPERTY_C.equalsIgnoreCase(attrName))
      {
         ((FamilyRegister) target).withC((FamilyMember) value);
         return true;
      }
      
      if ((FamilyRegister.PROPERTY_C + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((FamilyRegister) target).withoutC((FamilyMember) value);
         return true;
      }

      if (FamilyRegister.PROPERTY_FAMILIES.equalsIgnoreCase(attrName))
      {
         ((FamilyRegister) target).withFamilies((Family) value);
         return true;
      }
      
      if ((FamilyRegister.PROPERTY_FAMILIES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((FamilyRegister) target).withoutFamilies((Family) value);
         return true;
      }

      if (FamilyRegister.PROPERTY_PERSONREGISTER.equalsIgnoreCase(attrName))
      {
         ((FamilyRegister) target).setPersonRegister((PersonRegister) value);
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
      ((FamilyRegister) entity).removeYou();
   }
}
