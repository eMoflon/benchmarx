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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilySet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;

public class FamilyMemberSet extends SimpleSet<FamilyMember>
{
	public Class<?> getTypClass() {
		return FamilyMember.class;
	}

   public FamilyMemberSet()
   {
      // empty
   }

   public FamilyMemberSet(FamilyMember... objects)
   {
      for (FamilyMember obj : objects)
      {
         this.add(obj);
      }
   }

   public FamilyMemberSet(Collection<FamilyMember> objects)
   {
      this.addAll(objects);
   }

   public static final FamilyMemberSet EMPTY_SET = new FamilyMemberSet().withFlag(FamilyMemberSet.READONLY);


   public FamilyMemberPO createFamilyMemberPO()
   {
      return new FamilyMemberPO(this.toArray(new FamilyMember[this.size()]));
   }


   public String getEntryType()
   {
      return "org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember";
   }


   @Override
   public FamilyMemberSet getNewList(boolean keyValue)
   {
      return new FamilyMemberSet();
   }


   public FamilyMemberSet filter(Condition<FamilyMember> condition) {
      FamilyMemberSet filterList = new FamilyMemberSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public FamilyMemberSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<FamilyMember>)value);
      }
      else if (value != null)
      {
         this.add((FamilyMember) value);
      }
      
      return this;
   }
   
   public FamilyMemberSet without(FamilyMember value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of FamilyMember objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (FamilyMember obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of FamilyMember objects and collect those FamilyMember objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of FamilyMember objects that match the parameter
    */
   public FamilyMemberSet createNameCondition(String value)
   {
      FamilyMemberSet result = new FamilyMemberSet();
      
      for (FamilyMember obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of FamilyMember objects and collect those FamilyMember objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of FamilyMember objects that match the parameter
    */
   public FamilyMemberSet createNameCondition(String lower, String upper)
   {
      FamilyMemberSet result = new FamilyMemberSet();
      
      for (FamilyMember obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of FamilyMember objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of FamilyMember objects now with new attribute values.
    */
   public FamilyMemberSet withName(String value)
   {
      for (FamilyMember obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of FamilyMember objects and collect a set of the Person objects reached via cp. 
    * 
    * @return Set of Person objects reachable via cp
    */
   public PersonSet getCp()
   {
      PersonSet result = new PersonSet();
      
      for (FamilyMember obj : this)
      {
         result.with(obj.getCp());
      }
      
      return result;
   }

   /**
    * Loop through the current set of FamilyMember objects and collect all contained objects with reference cp pointing to the object passed as parameter. 
    * 
    * @param value The object required as cp neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via cp
    */
   public FamilyMemberSet filterCp(Object value)
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
      
      FamilyMemberSet answer = new FamilyMemberSet();
      
      for (FamilyMember obj : this)
      {
         if (neighbors.contains(obj.getCp()) || (neighbors.isEmpty() && obj.getCp() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the FamilyMember object passed as parameter to the Cp attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Cp attributes.
    */
   public FamilyMemberSet withCp(Person value)
   {
      for (FamilyMember obj : this)
      {
         obj.withCp(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of FamilyMember objects and collect a set of the Family objects reached via daughterOf. 
    * 
    * @return Set of Family objects reachable via daughterOf
    */
   public FamilySet getDaughterOf()
   {
      FamilySet result = new FamilySet();
      
      for (FamilyMember obj : this)
      {
         result.with(obj.getDaughterOf());
      }
      
      return result;
   }

   /**
    * Loop through the current set of FamilyMember objects and collect all contained objects with reference daughterOf pointing to the object passed as parameter. 
    * 
    * @param value The object required as daughterOf neighbor of the collected results. 
    * 
    * @return Set of Family objects referring to value via daughterOf
    */
   public FamilyMemberSet filterDaughterOf(Object value)
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
      
      FamilyMemberSet answer = new FamilyMemberSet();
      
      for (FamilyMember obj : this)
      {
         if (neighbors.contains(obj.getDaughterOf()) || (neighbors.isEmpty() && obj.getDaughterOf() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the FamilyMember object passed as parameter to the DaughterOf attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their DaughterOf attributes.
    */
   public FamilyMemberSet withDaughterOf(Family value)
   {
      for (FamilyMember obj : this)
      {
         obj.withDaughterOf(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of FamilyMember objects and collect a set of the Family objects reached via fatherOf. 
    * 
    * @return Set of Family objects reachable via fatherOf
    */
   public FamilySet getFatherOf()
   {
      FamilySet result = new FamilySet();
      
      for (FamilyMember obj : this)
      {
         result.with(obj.getFatherOf());
      }
      
      return result;
   }

   /**
    * Loop through the current set of FamilyMember objects and collect all contained objects with reference fatherOf pointing to the object passed as parameter. 
    * 
    * @param value The object required as fatherOf neighbor of the collected results. 
    * 
    * @return Set of Family objects referring to value via fatherOf
    */
   public FamilyMemberSet filterFatherOf(Object value)
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
      
      FamilyMemberSet answer = new FamilyMemberSet();
      
      for (FamilyMember obj : this)
      {
         if (neighbors.contains(obj.getFatherOf()) || (neighbors.isEmpty() && obj.getFatherOf() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the FamilyMember object passed as parameter to the FatherOf attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their FatherOf attributes.
    */
   public FamilyMemberSet withFatherOf(Family value)
   {
      for (FamilyMember obj : this)
      {
         obj.withFatherOf(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of FamilyMember objects and collect a set of the Family objects reached via motherOf. 
    * 
    * @return Set of Family objects reachable via motherOf
    */
   public FamilySet getMotherOf()
   {
      FamilySet result = new FamilySet();
      
      for (FamilyMember obj : this)
      {
         result.with(obj.getMotherOf());
      }
      
      return result;
   }

   /**
    * Loop through the current set of FamilyMember objects and collect all contained objects with reference motherOf pointing to the object passed as parameter. 
    * 
    * @param value The object required as motherOf neighbor of the collected results. 
    * 
    * @return Set of Family objects referring to value via motherOf
    */
   public FamilyMemberSet filterMotherOf(Object value)
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
      
      FamilyMemberSet answer = new FamilyMemberSet();
      
      for (FamilyMember obj : this)
      {
         if (neighbors.contains(obj.getMotherOf()) || (neighbors.isEmpty() && obj.getMotherOf() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the FamilyMember object passed as parameter to the MotherOf attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their MotherOf attributes.
    */
   public FamilyMemberSet withMotherOf(Family value)
   {
      for (FamilyMember obj : this)
      {
         obj.withMotherOf(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of FamilyMember objects and collect a set of the Family objects reached via sonOf. 
    * 
    * @return Set of Family objects reachable via sonOf
    */
   public FamilySet getSonOf()
   {
      FamilySet result = new FamilySet();
      
      for (FamilyMember obj : this)
      {
         result.with(obj.getSonOf());
      }
      
      return result;
   }

   /**
    * Loop through the current set of FamilyMember objects and collect all contained objects with reference sonOf pointing to the object passed as parameter. 
    * 
    * @param value The object required as sonOf neighbor of the collected results. 
    * 
    * @return Set of Family objects referring to value via sonOf
    */
   public FamilyMemberSet filterSonOf(Object value)
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
      
      FamilyMemberSet answer = new FamilyMemberSet();
      
      for (FamilyMember obj : this)
      {
         if (neighbors.contains(obj.getSonOf()) || (neighbors.isEmpty() && obj.getSonOf() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the FamilyMember object passed as parameter to the SonOf attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their SonOf attributes.
    */
   public FamilyMemberSet withSonOf(Family value)
   {
      for (FamilyMember obj : this)
      {
         obj.withSonOf(value);
      }
      
      return this;
   }

}
