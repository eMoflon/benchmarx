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
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Female;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonRegisterSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;

public class FemaleSet extends SimpleSet<Female>
{
	public Class<?> getTypClass() {
		return Female.class;
	}

   public FemaleSet()
   {
      // empty
   }

   public FemaleSet(Female... objects)
   {
      for (Female obj : objects)
      {
         this.add(obj);
      }
   }

   public FemaleSet(Collection<Female> objects)
   {
      this.addAll(objects);
   }

   public static final FemaleSet EMPTY_SET = new FemaleSet().withFlag(FemaleSet.READONLY);


   public FemalePO createFemalePO()
   {
      return new FemalePO(this.toArray(new Female[this.size()]));
   }


   public String getEntryType()
   {
      return "org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Female";
   }


   @Override
   public FemaleSet getNewList(boolean keyValue)
   {
      return new FemaleSet();
   }


   public FemaleSet filter(Condition<Female> condition) {
      FemaleSet filterList = new FemaleSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public FemaleSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Female>)value);
      }
      else if (value != null)
      {
         this.add((Female) value);
      }
      
      return this;
   }
   
   public FemaleSet without(Female value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Female objects and collect a list of the birthday attribute values. 
    * 
    * @return List of String objects reachable via birthday attribute
    */
   public ObjectSet getBirthday()
   {
      ObjectSet result = new ObjectSet();
      
      for (Female obj : this)
      {
         result.add(obj.getBirthday());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Female objects and collect those Female objects where the birthday attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Female objects that match the parameter
    */
   public FemaleSet createBirthdayCondition(String value)
   {
      FemaleSet result = new FemaleSet();
      
      for (Female obj : this)
      {
         if (value.equals(obj.getBirthday()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Female objects and collect those Female objects where the birthday attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Female objects that match the parameter
    */
   public FemaleSet createBirthdayCondition(String lower, String upper)
   {
      FemaleSet result = new FemaleSet();
      
      for (Female obj : this)
      {
         if (lower.compareTo(obj.getBirthday()) <= 0 && obj.getBirthday().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Female objects and assign value to the birthday attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Female objects now with new attribute values.
    */
   public FemaleSet withBirthday(String value)
   {
      for (Female obj : this)
      {
         obj.setBirthday(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Female objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Female obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Female objects and collect those Female objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Female objects that match the parameter
    */
   public FemaleSet createNameCondition(String value)
   {
      FemaleSet result = new FemaleSet();
      
      for (Female obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Female objects and collect those Female objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Female objects that match the parameter
    */
   public FemaleSet createNameCondition(String lower, String upper)
   {
      FemaleSet result = new FemaleSet();
      
      for (Female obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Female objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Female objects now with new attribute values.
    */
   public FemaleSet withName(String value)
   {
      for (Female obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Female objects and collect a set of the FamilyMember objects reached via cfm. 
    * 
    * @return Set of FamilyMember objects reachable via cfm
    */
   public FamilyMemberSet getCfm()
   {
      FamilyMemberSet result = new FamilyMemberSet();
      
      for (Female obj : this)
      {
         result.with(obj.getCfm());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Female objects and collect all contained objects with reference cfm pointing to the object passed as parameter. 
    * 
    * @param value The object required as cfm neighbor of the collected results. 
    * 
    * @return Set of FamilyMember objects referring to value via cfm
    */
   public FemaleSet filterCfm(Object value)
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
      
      FemaleSet answer = new FemaleSet();
      
      for (Female obj : this)
      {
         if (neighbors.contains(obj.getCfm()) || (neighbors.isEmpty() && obj.getCfm() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Female object passed as parameter to the Cfm attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Cfm attributes.
    */
   public FemaleSet withCfm(FamilyMember value)
   {
      for (Female obj : this)
      {
         obj.withCfm(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Female objects and collect a set of the PersonRegister objects reached via register. 
    * 
    * @return Set of PersonRegister objects reachable via register
    */
   public PersonRegisterSet getRegister()
   {
      PersonRegisterSet result = new PersonRegisterSet();
      
      for (Female obj : this)
      {
         result.with(obj.getRegister());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Female objects and collect all contained objects with reference register pointing to the object passed as parameter. 
    * 
    * @param value The object required as register neighbor of the collected results. 
    * 
    * @return Set of PersonRegister objects referring to value via register
    */
   public FemaleSet filterRegister(Object value)
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
      
      FemaleSet answer = new FemaleSet();
      
      for (Female obj : this)
      {
         if (neighbors.contains(obj.getRegister()) || (neighbors.isEmpty() && obj.getRegister() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Female object passed as parameter to the Register attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Register attributes.
    */
   public FemaleSet withRegister(PersonRegister value)
   {
      for (Female obj : this)
      {
         obj.withRegister(value);
      }
      
      return this;
   }

}
