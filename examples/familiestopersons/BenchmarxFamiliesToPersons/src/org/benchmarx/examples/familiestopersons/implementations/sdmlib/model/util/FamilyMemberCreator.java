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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import de.uniks.networkparser.IdMap;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.sdmlib.serialization.EntityFactory;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;

public class FamilyMemberCreator extends EntityFactory implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      FamilyMember.PROPERTY_NAME,
      FamilyMember.PROPERTY_CP,
      FamilyMember.PROPERTY_DAUGHTEROF,
      FamilyMember.PROPERTY_FATHEROF,
      FamilyMember.PROPERTY_MOTHEROF,
      FamilyMember.PROPERTY_SONOF,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new FamilyMember();
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

      if (FamilyMember.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((FamilyMember) target).getName();
      }

      if (FamilyMember.PROPERTY_CP.equalsIgnoreCase(attribute))
      {
         return ((FamilyMember) target).getCp();
      }

      if (FamilyMember.PROPERTY_DAUGHTEROF.equalsIgnoreCase(attribute))
      {
         return ((FamilyMember) target).getDaughterOf();
      }

      if (FamilyMember.PROPERTY_FATHEROF.equalsIgnoreCase(attribute))
      {
         return ((FamilyMember) target).getFatherOf();
      }

      if (FamilyMember.PROPERTY_MOTHEROF.equalsIgnoreCase(attribute))
      {
         return ((FamilyMember) target).getMotherOf();
      }

      if (FamilyMember.PROPERTY_SONOF.equalsIgnoreCase(attribute))
      {
         return ((FamilyMember) target).getSonOf();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (FamilyMember.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((FamilyMember) target).setName((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (FamilyMember.PROPERTY_CP.equalsIgnoreCase(attrName))
      {
         ((FamilyMember) target).setCp((Person) value);
         return true;
      }

      if (FamilyMember.PROPERTY_DAUGHTEROF.equalsIgnoreCase(attrName))
      {
         ((FamilyMember) target).setDaughterOf((Family) value);
         return true;
      }

      if (FamilyMember.PROPERTY_FATHEROF.equalsIgnoreCase(attrName))
      {
         ((FamilyMember) target).setFatherOf((Family) value);
         return true;
      }

      if (FamilyMember.PROPERTY_MOTHEROF.equalsIgnoreCase(attrName))
      {
         ((FamilyMember) target).setMotherOf((Family) value);
         return true;
      }

      if (FamilyMember.PROPERTY_SONOF.equalsIgnoreCase(attrName))
      {
         ((FamilyMember) target).setSonOf((Family) value);
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
      ((FamilyMember) entity).removeYou();
   }
}
