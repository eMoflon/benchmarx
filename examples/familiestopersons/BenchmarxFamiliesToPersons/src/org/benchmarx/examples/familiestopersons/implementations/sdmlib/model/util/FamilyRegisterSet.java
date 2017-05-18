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

import de.uniks.networkparser.list.SimpleSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilySet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonRegisterSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;

public class FamilyRegisterSet extends SimpleSet<FamilyRegister>
{
	public Class<?> getTypClass() {
		return FamilyRegister.class;
	}

   public FamilyRegisterSet()
   {
      // empty
   }

   public FamilyRegisterSet(FamilyRegister... objects)
   {
      for (FamilyRegister obj : objects)
      {
         this.add(obj);
      }
   }

   public FamilyRegisterSet(Collection<FamilyRegister> objects)
   {
      this.addAll(objects);
   }

   public static final FamilyRegisterSet EMPTY_SET = new FamilyRegisterSet().withFlag(FamilyRegisterSet.READONLY);


   public FamilyRegisterPO createFamilyRegisterPO()
   {
      return new FamilyRegisterPO(this.toArray(new FamilyRegister[this.size()]));
   }


   public String getEntryType()
   {
      return "org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister";
   }


   @Override
   public FamilyRegisterSet getNewList(boolean keyValue)
   {
      return new FamilyRegisterSet();
   }


   public FamilyRegisterSet filter(Condition<FamilyRegister> condition) {
      FamilyRegisterSet filterList = new FamilyRegisterSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public FamilyRegisterSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<FamilyRegister>)value);
      }
      else if (value != null)
      {
         this.add((FamilyRegister) value);
      }
      
      return this;
   }
   
   public FamilyRegisterSet without(FamilyRegister value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of FamilyRegister objects and collect a set of the FamilyMember objects reached via c. 
    * 
    * @return Set of FamilyMember objects reachable via c
    */
   public FamilyMemberSet getC()
   {
      FamilyMemberSet result = new FamilyMemberSet();
      
      for (FamilyRegister obj : this)
      {
         result.with(obj.getC());
      }
      
      return result;
   }

   /**
    * Loop through the current set of FamilyRegister objects and collect all contained objects with reference c pointing to the object passed as parameter. 
    * 
    * @param value The object required as c neighbor of the collected results. 
    * 
    * @return Set of FamilyMember objects referring to value via c
    */
   public FamilyRegisterSet filterC(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      FamilyRegisterSet answer = new FamilyRegisterSet();
      
      for (FamilyRegister obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getC()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the FamilyRegister object passed as parameter to the C attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their C attributes.
    */
   public FamilyRegisterSet withC(FamilyMember value)
   {
      for (FamilyRegister obj : this)
      {
         obj.withC(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the FamilyRegister object passed as parameter from the C attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public FamilyRegisterSet withoutC(FamilyMember value)
   {
      for (FamilyRegister obj : this)
      {
         obj.withoutC(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of FamilyRegister objects and collect a set of the Family objects reached via families. 
    * 
    * @return Set of Family objects reachable via families
    */
   public FamilySet getFamilies()
   {
      FamilySet result = new FamilySet();
      
      for (FamilyRegister obj : this)
      {
         result.with(obj.getFamilies());
      }
      
      return result;
   }

   /**
    * Loop through the current set of FamilyRegister objects and collect all contained objects with reference families pointing to the object passed as parameter. 
    * 
    * @param value The object required as families neighbor of the collected results. 
    * 
    * @return Set of Family objects referring to value via families
    */
   public FamilyRegisterSet filterFamilies(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      FamilyRegisterSet answer = new FamilyRegisterSet();
      
      for (FamilyRegister obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getFamilies()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the FamilyRegister object passed as parameter to the Families attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Families attributes.
    */
   public FamilyRegisterSet withFamilies(Family value)
   {
      for (FamilyRegister obj : this)
      {
         obj.withFamilies(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the FamilyRegister object passed as parameter from the Families attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public FamilyRegisterSet withoutFamilies(Family value)
   {
      for (FamilyRegister obj : this)
      {
         obj.withoutFamilies(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of FamilyRegister objects and collect a set of the PersonRegister objects reached via personRegister. 
    * 
    * @return Set of PersonRegister objects reachable via personRegister
    */
   public PersonRegisterSet getPersonRegister()
   {
      PersonRegisterSet result = new PersonRegisterSet();
      
      for (FamilyRegister obj : this)
      {
         result.with(obj.getPersonRegister());
      }
      
      return result;
   }

   /**
    * Loop through the current set of FamilyRegister objects and collect all contained objects with reference personRegister pointing to the object passed as parameter. 
    * 
    * @param value The object required as personRegister neighbor of the collected results. 
    * 
    * @return Set of PersonRegister objects referring to value via personRegister
    */
   public FamilyRegisterSet filterPersonRegister(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      FamilyRegisterSet answer = new FamilyRegisterSet();
      
      for (FamilyRegister obj : this)
      {
         if (neighbors.contains(obj.getPersonRegister()) || (neighbors.isEmpty() && obj.getPersonRegister() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the FamilyRegister object passed as parameter to the PersonRegister attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their PersonRegister attributes.
    */
   public FamilyRegisterSet withPersonRegister(PersonRegister value)
   {
      for (FamilyRegister obj : this)
      {
         obj.withPersonRegister(value);
      }
      
      return this;
   }

}
