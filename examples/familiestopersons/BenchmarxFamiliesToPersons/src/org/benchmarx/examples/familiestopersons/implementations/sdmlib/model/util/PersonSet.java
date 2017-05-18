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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import de.uniks.networkparser.interfaces.Condition;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Male;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.MaleSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Female;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FemaleSet;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonRegisterSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;

public class PersonSet extends SimpleSet<Person>
{
	public Class<?> getTypClass() {
		return Person.class;
	}

   public PersonSet()
   {
      // empty
   }

   public PersonSet(Person... objects)
   {
      for (Person obj : objects)
      {
         this.add(obj);
      }
   }

   public PersonSet(Collection<Person> objects)
   {
      this.addAll(objects);
   }

   public static final PersonSet EMPTY_SET = new PersonSet().withFlag(PersonSet.READONLY);


   public PersonPO createPersonPO()
   {
      return new PersonPO(this.toArray(new Person[this.size()]));
   }


   public String getEntryType()
   {
      return "org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person";
   }


   @Override
   public PersonSet getNewList(boolean keyValue)
   {
      return new PersonSet();
   }


   public PersonSet filter(Condition<Person> condition) {
      PersonSet filterList = new PersonSet();
      filterItems(filterList, condition);
      return filterList;
   }

   public MaleSet instanceOfMale()
   {
      MaleSet result = new MaleSet();
      
      for(Object obj : this)
      {
         if (obj instanceof Male)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public FemaleSet instanceOfFemale()
   {
      FemaleSet result = new FemaleSet();
      
      for(Object obj : this)
      {
         if (obj instanceof Female)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   @SuppressWarnings("unchecked")
   public PersonSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Person>)value);
      }
      else if (value != null)
      {
         this.add((Person) value);
      }
      
      return this;
   }
   
   public PersonSet without(Person value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Person objects and collect a list of the birthday attribute values. 
    * 
    * @return List of String objects reachable via birthday attribute
    */
   public ObjectSet getBirthday()
   {
      ObjectSet result = new ObjectSet();
      
      for (Person obj : this)
      {
         result.add(obj.getBirthday());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the birthday attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet createBirthdayCondition(String value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value.equals(obj.getBirthday()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the birthday attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet createBirthdayCondition(String lower, String upper)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (lower.compareTo(obj.getBirthday()) <= 0 && obj.getBirthday().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and assign value to the birthday attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Person objects now with new attribute values.
    */
   public PersonSet withBirthday(String value)
   {
      for (Person obj : this)
      {
         obj.setBirthday(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Person objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Person obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet createNameCondition(String value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet createNameCondition(String lower, String upper)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Person objects now with new attribute values.
    */
   public PersonSet withName(String value)
   {
      for (Person obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the FamilyMember objects reached via cfm. 
    * 
    * @return Set of FamilyMember objects reachable via cfm
    */
   public FamilyMemberSet getCfm()
   {
      FamilyMemberSet result = new FamilyMemberSet();
      
      for (Person obj : this)
      {
         result.with(obj.getCfm());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference cfm pointing to the object passed as parameter. 
    * 
    * @param value The object required as cfm neighbor of the collected results. 
    * 
    * @return Set of FamilyMember objects referring to value via cfm
    */
   public PersonSet filterCfm(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if (neighbors.contains(obj.getCfm()) || (neighbors.isEmpty() && obj.getCfm() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Cfm attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Cfm attributes.
    */
   public PersonSet withCfm(FamilyMember value)
   {
      for (Person obj : this)
      {
         obj.withCfm(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the PersonRegister objects reached via register. 
    * 
    * @return Set of PersonRegister objects reachable via register
    */
   public PersonRegisterSet getRegister()
   {
      PersonRegisterSet result = new PersonRegisterSet();
      
      for (Person obj : this)
      {
         result.with(obj.getRegister());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference register pointing to the object passed as parameter. 
    * 
    * @param value The object required as register neighbor of the collected results. 
    * 
    * @return Set of PersonRegister objects referring to value via register
    */
   public PersonSet filterRegister(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if (neighbors.contains(obj.getRegister()) || (neighbors.isEmpty() && obj.getRegister() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Register attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Register attributes.
    */
   public PersonSet withRegister(PersonRegister value)
   {
      for (Person obj : this)
      {
         obj.withRegister(value);
      }
      
      return this;
   }

}
