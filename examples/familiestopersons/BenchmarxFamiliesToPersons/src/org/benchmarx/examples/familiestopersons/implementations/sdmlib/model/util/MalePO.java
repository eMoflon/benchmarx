package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Male;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.MalePO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonRegisterPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;

public class MalePO extends PatternObject<MalePO, Male>
{

    public MaleSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MaleSet matches = new MaleSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Male) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MalePO(){
      newInstance(null);
   }

   public MalePO(Male... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public MalePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public MalePO createBirthdayCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Male.PROPERTY_BIRTHDAY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MalePO createBirthdayCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Male.PROPERTY_BIRTHDAY)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MalePO createBirthdayAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Male.PROPERTY_BIRTHDAY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getBirthday()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Male) getCurrentMatch()).getBirthday();
      }
      return null;
   }
   
   public MalePO withBirthday(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Male) getCurrentMatch()).setBirthday(value);
      }
      return this;
   }
   
   public MalePO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Male.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MalePO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Male.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MalePO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Male.PROPERTY_NAME)
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
         return ((Male) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public MalePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Male) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public FamilyMemberPO createCfmPO()
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Male.PROPERTY_CFM, result);
      
      return result;
   }

   public FamilyMemberPO createCfmPO(String modifier)
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(modifier);
      super.hasLink(Male.PROPERTY_CFM, result);
      
      return result;
   }

   public MalePO createCfmLink(FamilyMemberPO tgt)
   {
      return hasLinkConstraint(tgt, Male.PROPERTY_CFM);
   }

   public MalePO createCfmLink(FamilyMemberPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Male.PROPERTY_CFM, modifier);
   }

   public FamilyMember getCfm()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Male) this.getCurrentMatch()).getCfm();
      }
      return null;
   }

   public PersonRegisterPO createRegisterPO()
   {
      PersonRegisterPO result = new PersonRegisterPO(new PersonRegister[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Male.PROPERTY_REGISTER, result);
      
      return result;
   }

   public PersonRegisterPO createRegisterPO(String modifier)
   {
      PersonRegisterPO result = new PersonRegisterPO(new PersonRegister[]{});
      
      result.setModifier(modifier);
      super.hasLink(Male.PROPERTY_REGISTER, result);
      
      return result;
   }

   public MalePO createRegisterLink(PersonRegisterPO tgt)
   {
      return hasLinkConstraint(tgt, Male.PROPERTY_REGISTER);
   }

   public MalePO createRegisterLink(PersonRegisterPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Male.PROPERTY_REGISTER, modifier);
   }

   public PersonRegister getRegister()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Male) this.getCurrentMatch()).getRegister();
      }
      return null;
   }

}
