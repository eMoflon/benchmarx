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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyRegisterSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;

public class PersonRegisterSet extends SimpleSet<PersonRegister>
{
	public Class<?> getTypClass() {
		return PersonRegister.class;
	}

   public PersonRegisterSet()
   {
      // empty
   }

   public PersonRegisterSet(PersonRegister... objects)
   {
      for (PersonRegister obj : objects)
      {
         this.add(obj);
      }
   }

   public PersonRegisterSet(Collection<PersonRegister> objects)
   {
      this.addAll(objects);
   }

   public static final PersonRegisterSet EMPTY_SET = new PersonRegisterSet().withFlag(PersonRegisterSet.READONLY);


   public PersonRegisterPO createPersonRegisterPO()
   {
      return new PersonRegisterPO(this.toArray(new PersonRegister[this.size()]));
   }


   public String getEntryType()
   {
      return "org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister";
   }


   @Override
   public PersonRegisterSet getNewList(boolean keyValue)
   {
      return new PersonRegisterSet();
   }


   public PersonRegisterSet filter(Condition<PersonRegister> condition) {
      PersonRegisterSet filterList = new PersonRegisterSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public PersonRegisterSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PersonRegister>)value);
      }
      else if (value != null)
      {
         this.add((PersonRegister) value);
      }
      
      return this;
   }
   
   public PersonRegisterSet without(PersonRegister value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of PersonRegister objects and collect a set of the Person objects reached via c. 
    * 
    * @return Set of Person objects reachable via c
    */
   public PersonSet getC()
   {
      PersonSet result = new PersonSet();
      
      for (PersonRegister obj : this)
      {
         result.with(obj.getC());
      }
      
      return result;
   }

   /**
    * Loop through the current set of PersonRegister objects and collect all contained objects with reference c pointing to the object passed as parameter. 
    * 
    * @param value The object required as c neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via c
    */
   public PersonRegisterSet filterC(Object value)
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
      
      PersonRegisterSet answer = new PersonRegisterSet();
      
      for (PersonRegister obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getC()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the PersonRegister object passed as parameter to the C attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their C attributes.
    */
   public PersonRegisterSet withC(Person value)
   {
      for (PersonRegister obj : this)
      {
         obj.withC(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the PersonRegister object passed as parameter from the C attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PersonRegisterSet withoutC(Person value)
   {
      for (PersonRegister obj : this)
      {
         obj.withoutC(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of PersonRegister objects and collect a set of the Person objects reached via persons. 
    * 
    * @return Set of Person objects reachable via persons
    */
   public PersonSet getPersons()
   {
      PersonSet result = new PersonSet();
      
      for (PersonRegister obj : this)
      {
         result.with(obj.getPersons());
      }
      
      return result;
   }

   /**
    * Loop through the current set of PersonRegister objects and collect all contained objects with reference persons pointing to the object passed as parameter. 
    * 
    * @param value The object required as persons neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via persons
    */
   public PersonRegisterSet filterPersons(Object value)
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
      
      PersonRegisterSet answer = new PersonRegisterSet();
      
      for (PersonRegister obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPersons()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the PersonRegister object passed as parameter to the Persons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Persons attributes.
    */
   public PersonRegisterSet withPersons(Person value)
   {
      for (PersonRegister obj : this)
      {
         obj.withPersons(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the PersonRegister object passed as parameter from the Persons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PersonRegisterSet withoutPersons(Person value)
   {
      for (PersonRegister obj : this)
      {
         obj.withoutPersons(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of PersonRegister objects and collect a set of the FamilyRegister objects reached via familyRegister. 
    * 
    * @return Set of FamilyRegister objects reachable via familyRegister
    */
   public FamilyRegisterSet getFamilyRegister()
   {
      FamilyRegisterSet result = new FamilyRegisterSet();
      
      for (PersonRegister obj : this)
      {
         result.with(obj.getFamilyRegister());
      }
      
      return result;
   }

   /**
    * Loop through the current set of PersonRegister objects and collect all contained objects with reference familyRegister pointing to the object passed as parameter. 
    * 
    * @param value The object required as familyRegister neighbor of the collected results. 
    * 
    * @return Set of FamilyRegister objects referring to value via familyRegister
    */
   public PersonRegisterSet filterFamilyRegister(Object value)
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
      
      PersonRegisterSet answer = new PersonRegisterSet();
      
      for (PersonRegister obj : this)
      {
         if (neighbors.contains(obj.getFamilyRegister()) || (neighbors.isEmpty() && obj.getFamilyRegister() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the PersonRegister object passed as parameter to the FamilyRegister attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their FamilyRegister attributes.
    */
   public PersonRegisterSet withFamilyRegister(FamilyRegister value)
   {
      for (PersonRegister obj : this)
      {
         obj.withFamilyRegister(value);
      }
      
      return this;
   }

}
