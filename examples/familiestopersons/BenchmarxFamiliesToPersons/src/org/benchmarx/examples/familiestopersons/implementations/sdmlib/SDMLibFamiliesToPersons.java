package org.benchmarx.examples.familiestopersons.implementations.sdmlib;

import java.util.function.Consumer;

import org.benchmarx.BXTool;
import org.benchmarx.Configurator;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Female;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Male;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyRegisterPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilySet;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.MalePO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonRegisterPO;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.storyboards.Storyboard;

import Families.FamiliesFactory;




public class SDMLibFamiliesToPersons implements BXTool<Object, Object, Decisions>
{

   private FamilyRegister familyRegister;
   private PersonRegister personRegister;
   private Families.FamilyRegister emfFamilyRegister;
   private FamilyRegisterPO familyRegisterPO;
   private Configurator<Decisions> configurator;
   
   @Override
   public String getName()
   {
      // TODO Auto-generated method stub
      return "SDMLib";
   }

   @Override
   public void initiateSynchronisationDialogue()
   {
      // enabling the edit operations
      emfFamilyRegister = FamiliesFactory.eINSTANCE.createFamilyRegister();
      
      familyRegister = new FamilyRegister();
      personRegister = familyRegister.createPersonRegister();
      
      
      // System.out.println("initiateSynchronisationDialogue done");
   }

   @Override
   public void performAndPropagateSourceEdit(Consumer<Object> edit)
   {
      edit.accept(familyRegister);
      
      transformForward();
   }
   
   
   private void transformBackward()
   {
      personRegisterPO = new PersonRegisterPO(personRegister).withPatternObjectName("pr");
      
      FamilyRegisterPO familyRegisterPO = personRegisterPO.createFamilyRegisterPO().withPatternObjectName("fr");
      
      PersonPO personPO = personRegisterPO.createCPO().withPatternObjectName("p");
      
      // old member in wrong family, delete
      personPO.startSubPattern();
      FamilyMemberPO oldFamilyMemberPO = personPO.createCfmPO().withPatternObjectName("cfm");
      oldFamilyMemberPO.createCondition(fm -> assignGivenName(fm), "name := p.getGivenName()");
      FamilyPO oldFamilyPO = new FamilyPO();
      oldFamilyMemberPO.createPath(fm -> ((FamilyMember)fm).getFamilySet(), oldFamilyPO);
      oldFamilyPO.createCondition(f -> ! f.getName().equals(personPO.getFamilyName()), "f.name != p.getFamilyName()");
      oldFamilyMemberPO.createCpLink(personPO, Pattern.DESTROY);
      oldFamilyMemberPO.destroy();
      personPO.endSubPattern();
      
      personPO.startSubPattern();
      personPO.startNAC();
      personPO.createCfmPO();
      personPO.endNAC();
      
      FamilyMemberPO familyMemberPO = personPO.createCfmPO(Pattern.CREATE).withPatternObjectName("fm");
      familyMemberPO.createCondition(fm -> assignGivenName(fm), "name := p.getGivenName()");
      
      FamilyPO familyPO = new FamilyPO().withPatternObjectName("f");
      familyRegisterPO.createPath(fr -> getOrCreateFamily((FamilyRegister) fr, personPO), familyPO);
      
      familyPO.createCondition(f -> f.addToFit(familyMemberPO), "f.addToFit(fm)");
      personPO.endSubPattern();
      
      personRegisterPO.createCLink(personPO, Pattern.DESTROY);
      
      personRegisterPO.doAllMatches();
   }
   
   public FamilySet getOrCreateFamily(FamilyRegister fr, PersonPO p)
   {
      FamilySet result = new FamilySet();
      
      String familyName = p.getFamilyName();
      
      Family f;
      
      if (fr.getPersonRegister().preferExistingFamily)
      {
         f = fr.getOrCreateFamily(familyName);
      }
      else
      {
         f = fr.createFamilies().withName(familyName);
      }
      
      result.add(f);
      
      return result;
   }

   public boolean assignGivenName(FamilyMember fm)
   {
      String givenName = fm.getCp().getGivenName();
      fm.withName(givenName);
      
      return true;
   }
   
     /**
    * 
    * @see <a href='../../../../../../../doc/dumpRules.html'>dumpRules.html</a>
 */
   @Test
   public void dumpRules() {
      
      transformForward();
      
      Storyboard story = new Storyboard();
      
      story.addPattern(familyRegisterPO, false);
      
      transformBackward();
      
      story.addPattern(personRegisterPO, false);
      
      story.dumpHTML();
   }

   private void transformForward()
   {
      familyRegisterPO = new FamilyRegisterPO().withPatternObjectName("fr");
      // familyRegisterPO.getPattern().setDebugMode(Kanban.TRACE_ON);
      PersonRegisterPO personRegisterPO = familyRegisterPO.createPersonRegisterPO().withPatternObjectName("pr");
      FamilyMemberPO memberPO = familyRegisterPO.createCPO().withPatternObjectName("fm");
      
      // there is an old corresponding person
      memberPO.startSubPattern();
      PersonPO oldPersonPO = memberPO.createCpPO().withName("oldP");
      oldPersonPO.createCondition(p -> ensureNameAndGender(p));
      memberPO.endSubPattern();
      
      // no corresponding person
      memberPO.startNAC();
      memberPO.createCpPO().withPatternObjectName("noOldP");
      memberPO.endNAC();
      MalePO personPO = memberPO.createCpMalePO(Pattern.CREATE).withPatternObjectName("newP");
      personPO.createRegisterLink(personRegisterPO, Pattern.CREATE);
      personPO.createCondition(p -> ensureNameAndGender(p));
      familyRegisterPO.createCLink(memberPO, Pattern.DESTROY);
      
      familyRegisterPO.rebind(familyRegister);
      familyRegisterPO.doAllMatches();
   }

   public boolean ensureNameAndGender(Person p) 
   {
      FamilyMember fm = p.getCfm();
      Family f = fm.getFamily();
      
      Class gender = Male.class;
      if (fm.getMotherOf() != null || fm.getDaughterOf() != null)
      {
         gender = Female.class;
      }
      
      if (p.getClass() != gender)
      {
         try
         {
            Person newP = ((Person) gender.newInstance())
                  .withRegister(p.getRegister())
                  .withBirthday(p.getBirthday())
                  .withCfm(p.getCfm())
                  .withName(p.getName());
            
            p.removeYou();
            p = newP;
         }
         catch (InstantiationException | IllegalAccessException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
      String fullName = String.format("%s, %s", f.getName(), fm.getName());
      p.withName(fullName);
      
      return true;
   }
   
   @Override
   public void performAndPropagateTargetEdit(Consumer<Object> edit)
   {
      try
      {
         edit.accept(personRegister);
      }
      catch (Exception e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      if (configurator != null)
      {
         personRegister.preferExistingFamily = configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW);
         personRegister.preferParentToKid = configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD);
      }
      
      transformBackward();
   }

   @Override
   public void performIdleSourceEdit(Consumer<Object> edit)
   {
      edit.accept(familyRegister);
   }

   @Override
   public void performIdleTargetEdit(Consumer<Object> edit)
   {
      edit.accept(personRegister);
   }

   @Override
   public void setConfigurator(Configurator<Decisions> configurator)
   {
      this.configurator = configurator;
      
   }
   
   private FamiliesComparator familyComp = new FamiliesComparator();
   private PersonsComparator personComp = new PersonsComparator();
   private ModelCompare modelComp = new ModelCompare();
   private PersonRegisterPO personRegisterPO;

   
   @Override
   public void assertPostcondition(Object source, Object target)
   {
      assertEquals(source, target);
   }

   private void assertEquals(Object source, Object target)
   {
      // check whether our families and person are equal to source and target
      String expectedFamilyString = familyComp.familyToString((Families.FamilyRegister) source);
      
      String expectedPersonsString = personComp.personsToString((Persons.PersonRegister) target);
      
      String actualFamilyString = modelComp.familyToString(familyRegister);
      
      String actualPersonString = modelComp.personsToString(personRegister);
      
      // System.out.println(expectedFamilyString + actualFamilyString + expectedPersonsString + actualPersonString);
      
      String expected = (expectedFamilyString + expectedPersonsString).replaceAll("\\s+", "");
      String actual = (actualFamilyString + actualPersonString).replaceAll("\\s+", "");
      
      for (int i = 0; i < expected.length(); i++)
      {
         if (expected.charAt(i) != actual.charAt(i))
         {
            System.out.println("===>" + expected.substring(i));
            System.out.println("===>" + actual.substring(i));
            break;
         }
      }
      
      
      Assert.assertEquals("model strings should be equal", expected, actual);
   }
   
   @Override
   public void assertPrecondition(Object source, Object target)
   {
      assertEquals(source, target);
      System.out.println("assertPostcondition done");
      
   }

}
