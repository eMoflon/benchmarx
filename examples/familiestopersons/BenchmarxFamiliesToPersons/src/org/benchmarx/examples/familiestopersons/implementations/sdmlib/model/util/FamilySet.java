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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyRegisterSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;

public class FamilySet extends SimpleSet<Family>
{
	public Class<?> getTypClass() {
		return Family.class;
	}

   public FamilySet()
   {
      // empty
   }

   public FamilySet(Family... objects)
   {
      for (Family obj : objects)
      {
         this.add(obj);
      }
   }

   public FamilySet(Collection<Family> objects)
   {
      this.addAll(objects);
   }

   public static final FamilySet EMPTY_SET = new FamilySet().withFlag(FamilySet.READONLY);


   public FamilyPO createFamilyPO()
   {
      return new FamilyPO(this.toArray(new Family[this.size()]));
   }


   public String getEntryType()
   {
      return "org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family";
   }


   @Override
   public FamilySet getNewList(boolean keyValue)
   {
      return new FamilySet();
   }


   public FamilySet filter(Condition<Family> condition) {
      FamilySet filterList = new FamilySet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public FamilySet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Family>)value);
      }
      else if (value != null)
      {
         this.add((Family) value);
      }
      
      return this;
   }
   
   public FamilySet without(Family value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Family objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Family obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Family objects and collect those Family objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Family objects that match the parameter
    */
   public FamilySet createNameCondition(String value)
   {
      FamilySet result = new FamilySet();
      
      for (Family obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Family objects and collect those Family objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Family objects that match the parameter
    */
   public FamilySet createNameCondition(String lower, String upper)
   {
      FamilySet result = new FamilySet();
      
      for (Family obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Family objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Family objects now with new attribute values.
    */
   public FamilySet withName(String value)
   {
      for (Family obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Family objects and collect a set of the FamilyMember objects reached via daughters. 
    * 
    * @return Set of FamilyMember objects reachable via daughters
    */
   public FamilyMemberSet getDaughters()
   {
      FamilyMemberSet result = new FamilyMemberSet();
      
      for (Family obj : this)
      {
         result.with(obj.getDaughters());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Family objects and collect all contained objects with reference daughters pointing to the object passed as parameter. 
    * 
    * @param value The object required as daughters neighbor of the collected results. 
    * 
    * @return Set of FamilyMember objects referring to value via daughters
    */
   public FamilySet filterDaughters(Object value)
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
      
      FamilySet answer = new FamilySet();
      
      for (Family obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getDaughters()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Family object passed as parameter to the Daughters attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Daughters attributes.
    */
   public FamilySet withDaughters(FamilyMember value)
   {
      for (Family obj : this)
      {
         obj.withDaughters(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Family object passed as parameter from the Daughters attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public FamilySet withoutDaughters(FamilyMember value)
   {
      for (Family obj : this)
      {
         obj.withoutDaughters(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Family objects and collect a set of the FamilyRegister objects reached via register. 
    * 
    * @return Set of FamilyRegister objects reachable via register
    */
   public FamilyRegisterSet getRegister()
   {
      FamilyRegisterSet result = new FamilyRegisterSet();
      
      for (Family obj : this)
      {
         result.with(obj.getRegister());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Family objects and collect all contained objects with reference register pointing to the object passed as parameter. 
    * 
    * @param value The object required as register neighbor of the collected results. 
    * 
    * @return Set of FamilyRegister objects referring to value via register
    */
   public FamilySet filterRegister(Object value)
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
      
      FamilySet answer = new FamilySet();
      
      for (Family obj : this)
      {
         if (neighbors.contains(obj.getRegister()) || (neighbors.isEmpty() && obj.getRegister() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Family object passed as parameter to the Register attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Register attributes.
    */
   public FamilySet withRegister(FamilyRegister value)
   {
      for (Family obj : this)
      {
         obj.withRegister(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Family objects and collect a set of the FamilyMember objects reached via father. 
    * 
    * @return Set of FamilyMember objects reachable via father
    */
   public FamilyMemberSet getFather()
   {
      FamilyMemberSet result = new FamilyMemberSet();
      
      for (Family obj : this)
      {
         result.with(obj.getFather());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Family objects and collect all contained objects with reference father pointing to the object passed as parameter. 
    * 
    * @param value The object required as father neighbor of the collected results. 
    * 
    * @return Set of FamilyMember objects referring to value via father
    */
   public FamilySet filterFather(Object value)
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
      
      FamilySet answer = new FamilySet();
      
      for (Family obj : this)
      {
         if (neighbors.contains(obj.getFather()) || (neighbors.isEmpty() && obj.getFather() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Family object passed as parameter to the Father attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Father attributes.
    */
   public FamilySet withFather(FamilyMember value)
   {
      for (Family obj : this)
      {
         obj.withFather(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Family objects and collect a set of the FamilyMember objects reached via mother. 
    * 
    * @return Set of FamilyMember objects reachable via mother
    */
   public FamilyMemberSet getMother()
   {
      FamilyMemberSet result = new FamilyMemberSet();
      
      for (Family obj : this)
      {
         result.with(obj.getMother());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Family objects and collect all contained objects with reference mother pointing to the object passed as parameter. 
    * 
    * @param value The object required as mother neighbor of the collected results. 
    * 
    * @return Set of FamilyMember objects referring to value via mother
    */
   public FamilySet filterMother(Object value)
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
      
      FamilySet answer = new FamilySet();
      
      for (Family obj : this)
      {
         if (neighbors.contains(obj.getMother()) || (neighbors.isEmpty() && obj.getMother() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Family object passed as parameter to the Mother attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Mother attributes.
    */
   public FamilySet withMother(FamilyMember value)
   {
      for (Family obj : this)
      {
         obj.withMother(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Family objects and collect a set of the FamilyMember objects reached via sons. 
    * 
    * @return Set of FamilyMember objects reachable via sons
    */
   public FamilyMemberSet getSons()
   {
      FamilyMemberSet result = new FamilyMemberSet();
      
      for (Family obj : this)
      {
         result.with(obj.getSons());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Family objects and collect all contained objects with reference sons pointing to the object passed as parameter. 
    * 
    * @param value The object required as sons neighbor of the collected results. 
    * 
    * @return Set of FamilyMember objects referring to value via sons
    */
   public FamilySet filterSons(Object value)
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
      
      FamilySet answer = new FamilySet();
      
      for (Family obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSons()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Family object passed as parameter to the Sons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Sons attributes.
    */
   public FamilySet withSons(FamilyMember value)
   {
      for (Family obj : this)
      {
         obj.withSons(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Family object passed as parameter from the Sons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public FamilySet withoutSons(FamilyMember value)
   {
      for (Family obj : this)
      {
         obj.withoutSons(value);
      }
      
      return this;
   }

}
