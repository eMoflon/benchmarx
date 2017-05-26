package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Male;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;

public class FamilyMemberPO extends PatternObject<FamilyMemberPO, FamilyMember>
{

    public FamilyMemberSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FamilyMemberSet matches = new FamilyMemberSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((FamilyMember) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public FamilyMemberPO(){
      newInstance(null);
   }

   public FamilyMemberPO(FamilyMember... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public FamilyMemberPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public FamilyMemberPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(FamilyMember.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FamilyMemberPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(FamilyMember.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FamilyMemberPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(FamilyMember.PROPERTY_NAME)
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
         return ((FamilyMember) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public FamilyMemberPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((FamilyMember) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public PersonPO createCpPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(FamilyMember.PROPERTY_CP, result);
      
      return result;
   }

   public PersonPO createCpPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(FamilyMember.PROPERTY_CP, result);
      
      return result;
   }
   
   public MalePO createCpMalePO(String modifier)
   {
      MalePO result = new MalePO(new Male[]{});
      
      result.setModifier(modifier);
      super.hasLink(FamilyMember.PROPERTY_CP, result);
      
      return result;
   }
   
   

   public FamilyMemberPO createCpLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, FamilyMember.PROPERTY_CP);
   }

   public FamilyMemberPO createCpLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, FamilyMember.PROPERTY_CP, modifier);
   }

   public Person getCp()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FamilyMember) this.getCurrentMatch()).getCp();
      }
      return null;
   }

   public FamilyPO createDaughterOfPO()
   {
      FamilyPO result = new FamilyPO(new Family[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(FamilyMember.PROPERTY_DAUGHTEROF, result);
      
      return result;
   }

   public FamilyPO createDaughterOfPO(String modifier)
   {
      FamilyPO result = new FamilyPO(new Family[]{});
      
      result.setModifier(modifier);
      super.hasLink(FamilyMember.PROPERTY_DAUGHTEROF, result);
      
      return result;
   }

   public FamilyMemberPO createDaughterOfLink(FamilyPO tgt)
   {
      return hasLinkConstraint(tgt, FamilyMember.PROPERTY_DAUGHTEROF);
   }

   public FamilyMemberPO createDaughterOfLink(FamilyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, FamilyMember.PROPERTY_DAUGHTEROF, modifier);
   }

   public Family getDaughterOf()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FamilyMember) this.getCurrentMatch()).getDaughterOf();
      }
      return null;
   }

   public FamilyPO createFatherOfPO()
   {
      FamilyPO result = new FamilyPO(new Family[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(FamilyMember.PROPERTY_FATHEROF, result);
      
      return result;
   }

   public FamilyPO createFatherOfPO(String modifier)
   {
      FamilyPO result = new FamilyPO(new Family[]{});
      
      result.setModifier(modifier);
      super.hasLink(FamilyMember.PROPERTY_FATHEROF, result);
      
      return result;
   }

   public FamilyMemberPO createFatherOfLink(FamilyPO tgt)
   {
      return hasLinkConstraint(tgt, FamilyMember.PROPERTY_FATHEROF);
   }

   public FamilyMemberPO createFatherOfLink(FamilyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, FamilyMember.PROPERTY_FATHEROF, modifier);
   }

   public Family getFatherOf()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FamilyMember) this.getCurrentMatch()).getFatherOf();
      }
      return null;
   }

   public FamilyPO createMotherOfPO()
   {
      FamilyPO result = new FamilyPO(new Family[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(FamilyMember.PROPERTY_MOTHEROF, result);
      
      return result;
   }

   public FamilyPO createMotherOfPO(String modifier)
   {
      FamilyPO result = new FamilyPO(new Family[]{});
      
      result.setModifier(modifier);
      super.hasLink(FamilyMember.PROPERTY_MOTHEROF, result);
      
      return result;
   }

   public FamilyMemberPO createMotherOfLink(FamilyPO tgt)
   {
      return hasLinkConstraint(tgt, FamilyMember.PROPERTY_MOTHEROF);
   }

   public FamilyMemberPO createMotherOfLink(FamilyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, FamilyMember.PROPERTY_MOTHEROF, modifier);
   }

   public Family getMotherOf()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FamilyMember) this.getCurrentMatch()).getMotherOf();
      }
      return null;
   }

   public FamilyPO createSonOfPO()
   {
      FamilyPO result = new FamilyPO(new Family[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(FamilyMember.PROPERTY_SONOF, result);
      
      return result;
   }

   public FamilyPO createSonOfPO(String modifier)
   {
      FamilyPO result = new FamilyPO(new Family[]{});
      
      result.setModifier(modifier);
      super.hasLink(FamilyMember.PROPERTY_SONOF, result);
      
      return result;
   }

   public FamilyMemberPO createSonOfLink(FamilyPO tgt)
   {
      return hasLinkConstraint(tgt, FamilyMember.PROPERTY_SONOF);
   }

   public FamilyMemberPO createSonOfLink(FamilyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, FamilyMember.PROPERTY_SONOF, modifier);
   }

   public Family getSonOf()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FamilyMember) this.getCurrentMatch()).getSonOf();
      }
      return null;
   }




}
