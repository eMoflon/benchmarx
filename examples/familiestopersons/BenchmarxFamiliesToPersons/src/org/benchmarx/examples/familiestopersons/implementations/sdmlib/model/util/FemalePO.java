package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Female;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FemalePO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonRegisterPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;

public class FemalePO extends PatternObject<FemalePO, Female>
{

    public FemaleSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FemaleSet matches = new FemaleSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Female) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public FemalePO(){
      newInstance(null);
   }

   public FemalePO(Female... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public FemalePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public FemalePO createBirthdayCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Female.PROPERTY_BIRTHDAY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FemalePO createBirthdayCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Female.PROPERTY_BIRTHDAY)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FemalePO createBirthdayAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Female.PROPERTY_BIRTHDAY)
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
         return ((Female) getCurrentMatch()).getBirthday();
      }
      return null;
   }
   
   public FemalePO withBirthday(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Female) getCurrentMatch()).setBirthday(value);
      }
      return this;
   }
   
   public FemalePO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Female.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FemalePO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Female.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FemalePO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Female.PROPERTY_NAME)
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
         return ((Female) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public FemalePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Female) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public FamilyMemberPO createCfmPO()
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Female.PROPERTY_CFM, result);
      
      return result;
   }

   public FamilyMemberPO createCfmPO(String modifier)
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(modifier);
      super.hasLink(Female.PROPERTY_CFM, result);
      
      return result;
   }

   public FemalePO createCfmLink(FamilyMemberPO tgt)
   {
      return hasLinkConstraint(tgt, Female.PROPERTY_CFM);
   }

   public FemalePO createCfmLink(FamilyMemberPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Female.PROPERTY_CFM, modifier);
   }

   public FamilyMember getCfm()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Female) this.getCurrentMatch()).getCfm();
      }
      return null;
   }

   public PersonRegisterPO createRegisterPO()
   {
      PersonRegisterPO result = new PersonRegisterPO(new PersonRegister[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Female.PROPERTY_REGISTER, result);
      
      return result;
   }

   public PersonRegisterPO createRegisterPO(String modifier)
   {
      PersonRegisterPO result = new PersonRegisterPO(new PersonRegister[]{});
      
      result.setModifier(modifier);
      super.hasLink(Female.PROPERTY_REGISTER, result);
      
      return result;
   }

   public FemalePO createRegisterLink(PersonRegisterPO tgt)
   {
      return hasLinkConstraint(tgt, Female.PROPERTY_REGISTER);
   }

   public FemalePO createRegisterLink(PersonRegisterPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Female.PROPERTY_REGISTER, modifier);
   }

   public PersonRegister getRegister()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Female) this.getCurrentMatch()).getRegister();
      }
      return null;
   }

}
