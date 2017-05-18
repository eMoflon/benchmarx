package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberSet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyRegisterPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;

public class FamilyPO extends PatternObject<FamilyPO, Family>
{

    public FamilySet allMatches()
   {
      this.setDoAllMatches(true);
      
      FamilySet matches = new FamilySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Family) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public FamilyPO(){
      newInstance(null);
   }

   public FamilyPO(Family... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public FamilyPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public FamilyPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Family.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FamilyPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Family.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FamilyPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Family.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Family) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public FamilyPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Family) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public FamilyMemberPO createDaughtersPO()
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Family.PROPERTY_DAUGHTERS, result);
      
      return result;
   }

   public FamilyMemberPO createDaughtersPO(String modifier)
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(modifier);
      super.hasLink(Family.PROPERTY_DAUGHTERS, result);
      
      return result;
   }

   public FamilyPO createDaughtersLink(FamilyMemberPO tgt)
   {
      return hasLinkConstraint(tgt, Family.PROPERTY_DAUGHTERS);
   }

   public FamilyPO createDaughtersLink(FamilyMemberPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Family.PROPERTY_DAUGHTERS, modifier);
   }

   public FamilyMemberSet getDaughters()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Family) this.getCurrentMatch()).getDaughters();
      }
      return null;
   }

   public FamilyRegisterPO createRegisterPO()
   {
      FamilyRegisterPO result = new FamilyRegisterPO(new FamilyRegister[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Family.PROPERTY_REGISTER, result);
      
      return result;
   }

   public FamilyRegisterPO createRegisterPO(String modifier)
   {
      FamilyRegisterPO result = new FamilyRegisterPO(new FamilyRegister[]{});
      
      result.setModifier(modifier);
      super.hasLink(Family.PROPERTY_REGISTER, result);
      
      return result;
   }

   public FamilyPO createRegisterLink(FamilyRegisterPO tgt)
   {
      return hasLinkConstraint(tgt, Family.PROPERTY_REGISTER);
   }

   public FamilyPO createRegisterLink(FamilyRegisterPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Family.PROPERTY_REGISTER, modifier);
   }

   public FamilyRegister getRegister()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Family) this.getCurrentMatch()).getRegister();
      }
      return null;
   }

   public FamilyMemberPO createFatherPO()
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Family.PROPERTY_FATHER, result);
      
      return result;
   }

   public FamilyMemberPO createFatherPO(String modifier)
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(modifier);
      super.hasLink(Family.PROPERTY_FATHER, result);
      
      return result;
   }

   public FamilyPO createFatherLink(FamilyMemberPO tgt)
   {
      return hasLinkConstraint(tgt, Family.PROPERTY_FATHER);
   }

   public FamilyPO createFatherLink(FamilyMemberPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Family.PROPERTY_FATHER, modifier);
   }

   public FamilyMember getFather()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Family) this.getCurrentMatch()).getFather();
      }
      return null;
   }

   public FamilyMemberPO createMotherPO()
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Family.PROPERTY_MOTHER, result);
      
      return result;
   }

   public FamilyMemberPO createMotherPO(String modifier)
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(modifier);
      super.hasLink(Family.PROPERTY_MOTHER, result);
      
      return result;
   }

   public FamilyPO createMotherLink(FamilyMemberPO tgt)
   {
      return hasLinkConstraint(tgt, Family.PROPERTY_MOTHER);
   }

   public FamilyPO createMotherLink(FamilyMemberPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Family.PROPERTY_MOTHER, modifier);
   }

   public FamilyMember getMother()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Family) this.getCurrentMatch()).getMother();
      }
      return null;
   }

   public FamilyMemberPO createSonsPO()
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Family.PROPERTY_SONS, result);
      
      return result;
   }

   public FamilyMemberPO createSonsPO(String modifier)
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(modifier);
      super.hasLink(Family.PROPERTY_SONS, result);
      
      return result;
   }

   public FamilyPO createSonsLink(FamilyMemberPO tgt)
   {
      return hasLinkConstraint(tgt, Family.PROPERTY_SONS);
   }

   public FamilyPO createSonsLink(FamilyMemberPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Family.PROPERTY_SONS, modifier);
   }

   public FamilyMemberSet getSons()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Family) this.getCurrentMatch()).getSons();
      }
      return null;
   }

}
