package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonRegisterPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;

public class PersonPO extends PatternObject<PersonPO, Person>
{

    public PersonSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PersonPO(){
      newInstance(null);
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PersonPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createBirthdayCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BIRTHDAY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createBirthdayCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BIRTHDAY)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createBirthdayAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BIRTHDAY)
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
         return ((Person) getCurrentMatch()).getBirthday();
      }
      return null;
   }
   
   public PersonPO withBirthday(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setBirthday(value);
      }
      return this;
   }
   
   public PersonPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
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
         return ((Person) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PersonPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public FamilyMemberPO createCfmPO()
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(this.getOnDutyPattern().getModifier());
      super.hasLink(Person.PROPERTY_CFM, result);
      
      return result;
   }

   public FamilyMemberPO createCfmPO(String modifier)
   {
      FamilyMemberPO result = new FamilyMemberPO(new FamilyMember[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_CFM, result);
      
      return result;
   }

   public PersonPO createCfmLink(FamilyMemberPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_CFM);
   }

   public PersonPO createCfmLink(FamilyMemberPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_CFM, modifier);
   }

   public FamilyMember getCfm()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getCfm();
      }
      return null;
   }

   public PersonRegisterPO createRegisterPO()
   {
      PersonRegisterPO result = new PersonRegisterPO(new PersonRegister[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_REGISTER, result);
      
      return result;
   }

   public PersonRegisterPO createRegisterPO(String modifier)
   {
      PersonRegisterPO result = new PersonRegisterPO(new PersonRegister[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_REGISTER, result);
      
      return result;
   }

   public PersonPO createRegisterLink(PersonRegisterPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_REGISTER);
   }

   public PersonPO createRegisterLink(PersonRegisterPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_REGISTER, modifier);
   }

   public PersonRegister getRegister()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getRegister();
      }
      return null;
   }


   public String getFamilyName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getFamilyName();
      }
      return null;
   }

}
