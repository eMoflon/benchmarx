package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyRegisterPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilySet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonRegisterPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;

public class FamilyRegisterPO extends PatternObject<FamilyRegisterPO, FamilyRegister>
{

    public FamilyRegisterSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FamilyRegisterSet matches = new FamilyRegisterSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((FamilyRegister) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public FamilyRegisterPO(){
      newInstance(null);
   }

   public FamilyRegisterPO(FamilyRegister... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public FamilyRegisterPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public FamilyMemberPO createCPO()
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(FamilyRegister.PROPERTY_C, result);
      
      return result;
   }

   public FamilyMemberPO createCPO(String modifier)
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(modifier);
      super.hasLink(FamilyRegister.PROPERTY_C, result);
      
      return result;
   }

   public FamilyRegisterPO createCLink(FamilyMemberPO tgt)
   {
      return hasLinkConstraint(tgt, FamilyRegister.PROPERTY_C);
   }

   public FamilyRegisterPO createCLink(FamilyMemberPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, FamilyRegister.PROPERTY_C, modifier);
   }

   public FamilyMemberSet getC()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FamilyRegister) this.getCurrentMatch()).getC();
      }
      return null;
   }

   public FamilyPO createFamiliesPO()
   {
      FamilyPO result = new FamilyPO(new Family[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(FamilyRegister.PROPERTY_FAMILIES, result);
      
      return result;
   }

   public FamilyPO createFamiliesPO(String modifier)
   {
      FamilyPO result = new FamilyPO(new Family[]{});
      
      result.setModifier(modifier);
      super.hasLink(FamilyRegister.PROPERTY_FAMILIES, result);
      
      return result;
   }

   public FamilyRegisterPO createFamiliesLink(FamilyPO tgt)
   {
      return hasLinkConstraint(tgt, FamilyRegister.PROPERTY_FAMILIES);
   }

   public FamilyRegisterPO createFamiliesLink(FamilyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, FamilyRegister.PROPERTY_FAMILIES, modifier);
   }

   public FamilySet getFamilies()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FamilyRegister) this.getCurrentMatch()).getFamilies();
      }
      return null;
   }

   public PersonRegisterPO createPersonRegisterPO()
   {
      PersonRegisterPO result = new PersonRegisterPO(new PersonRegister[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(FamilyRegister.PROPERTY_PERSONREGISTER, result);
      
      return result;
   }

   public PersonRegisterPO createPersonRegisterPO(String modifier)
   {
      PersonRegisterPO result = new PersonRegisterPO(new PersonRegister[]{});
      
      result.setModifier(modifier);
      super.hasLink(FamilyRegister.PROPERTY_PERSONREGISTER, result);
      
      return result;
   }

   public FamilyRegisterPO createPersonRegisterLink(PersonRegisterPO tgt)
   {
      return hasLinkConstraint(tgt, FamilyRegister.PROPERTY_PERSONREGISTER);
   }

   public FamilyRegisterPO createPersonRegisterLink(PersonRegisterPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, FamilyRegister.PROPERTY_PERSONREGISTER, modifier);
   }

   public PersonRegister getPersonRegister()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FamilyRegister) this.getCurrentMatch()).getPersonRegister();
      }
      return null;
   }

}
