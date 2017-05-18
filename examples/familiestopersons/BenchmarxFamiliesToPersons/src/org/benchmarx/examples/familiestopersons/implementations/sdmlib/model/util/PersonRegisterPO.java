package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonRegisterPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyRegisterPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;

public class PersonRegisterPO extends PatternObject<PersonRegisterPO, PersonRegister>
{

    public PersonRegisterSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonRegisterSet matches = new PersonRegisterSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PersonRegister) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PersonRegisterPO(){
      newInstance(null);
   }

   public PersonRegisterPO(PersonRegister... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PersonRegisterPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createCPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PersonRegister.PROPERTY_C, result);
      
      return result;
   }

   public PersonPO createCPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(PersonRegister.PROPERTY_C, result);
      
      return result;
   }

   public PersonRegisterPO createCLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, PersonRegister.PROPERTY_C);
   }

   public PersonRegisterPO createCLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PersonRegister.PROPERTY_C, modifier);
   }

   public PersonSet getC()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PersonRegister) this.getCurrentMatch()).getC();
      }
      return null;
   }

   public PersonPO createPersonsPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PersonRegister.PROPERTY_PERSONS, result);
      
      return result;
   }

   public PersonPO createPersonsPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(PersonRegister.PROPERTY_PERSONS, result);
      
      return result;
   }

   public PersonRegisterPO createPersonsLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, PersonRegister.PROPERTY_PERSONS);
   }

   public PersonRegisterPO createPersonsLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PersonRegister.PROPERTY_PERSONS, modifier);
   }

   public PersonSet getPersons()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PersonRegister) this.getCurrentMatch()).getPersons();
      }
      return null;
   }

   public FamilyRegisterPO createFamilyRegisterPO()
   {
      FamilyRegisterPO result = new FamilyRegisterPO(new FamilyRegister[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PersonRegister.PROPERTY_FAMILYREGISTER, result);
      
      return result;
   }

   public FamilyRegisterPO createFamilyRegisterPO(String modifier)
   {
      FamilyRegisterPO result = new FamilyRegisterPO(new FamilyRegister[]{});
      
      result.setModifier(modifier);
      super.hasLink(PersonRegister.PROPERTY_FAMILYREGISTER, result);
      
      return result;
   }

   public PersonRegisterPO createFamilyRegisterLink(FamilyRegisterPO tgt)
   {
      return hasLinkConstraint(tgt, PersonRegister.PROPERTY_FAMILYREGISTER);
   }

   public PersonRegisterPO createFamilyRegisterLink(FamilyRegisterPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PersonRegister.PROPERTY_FAMILYREGISTER, modifier);
   }

   public FamilyRegister getFamilyRegister()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PersonRegister) this.getCurrentMatch()).getFamilyRegister();
      }
      return null;
   }

}
