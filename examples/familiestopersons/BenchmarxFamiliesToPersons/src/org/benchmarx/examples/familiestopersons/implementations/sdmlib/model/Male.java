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
   
package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model;

import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
   /**
    * 
    * @see <a href='../../../../../../../../src/org/benchmarx/examples/familiestopersons/implementations/sdmlib/ModelGen.java'>ModelGen.java</a>
 */
   public  class Male extends Person
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      FamilyMember oldCfm = getCfm();
      setCfm(null);
      if (oldCfm != null)
      {
         oldCfm.removeYou();
      }
      if (getRegister() != null)
      {
         getRegister().withoutC(this);
      }
      setRegister(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getBirthday());
      result.append(" ").append(this.getName());
      return result.substring(1);
   }

}
