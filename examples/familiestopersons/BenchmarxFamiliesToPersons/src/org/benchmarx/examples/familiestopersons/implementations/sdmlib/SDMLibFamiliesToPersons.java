package org.benchmarx.examples.familiestopersons.implementations.sdmlib;

import java.util.function.Consumer;

import org.benchmarx.BXTool;
import org.benchmarx.Configurator;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyMemberPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.FamilyRegisterPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonPO;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.PersonRegisterPO;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.junit.Assert;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.Kanban;

import Families.FamiliesFactory;




public class SDMLibFamiliesToPersons implements BXTool<Object, Object, Decisions>
{

   private FamilyRegister familyRegister;
   private PersonRegister personRegister;
   private Families.FamilyRegister emfFamilyRegister;
   private FamilyRegisterPO familyRegisterPO;

   @Override
   public void initiateSynchronisationDialogue()
   {
      // enabling the edit operations
      emfFamilyRegister = FamiliesFactory.eINSTANCE.createFamilyRegister();
      
      familyRegister = new FamilyRegister();
      personRegister = familyRegister.createPersonRegister();
      
      
      System.out.println("initiateSynchronisationDialogue done");
   }

   @Override
   public void performAndPropagateSourceEdit(Consumer<Object> edit)
   {
      edit.accept(familyRegister);
      
      transformForward();
      
      System.out.println("performAndPropagateSourceEdit done");
      
   }

   private void transformForward()
   {
      familyRegisterPO = new FamilyRegisterPO().withPatternObjectName("fr");
      familyRegisterPO.getPattern().setDebugMode(Kanban.TRACE_ON);
      PersonRegisterPO personRegisterPO = familyRegisterPO.createPersonRegisterPO().withPatternObjectName("pr");
      FamilyMemberPO memberPO = familyRegisterPO.createCPO().withPatternObjectName("fm");
      memberPO.startNAC();
      memberPO.createCpPO().withPatternObjectName("oldP");
      memberPO.endNAC();
      PersonPO personPO = memberPO.createCpPO(Pattern.CREATE).withPatternObjectName("newP");
      personPO.createRegisterLink(personRegisterPO, Pattern.CREATE);
      personPO.createCondition(p -> derivePersonName(p));
      familyRegisterPO.createCLink(memberPO, Pattern.DESTROY);
      
      familyRegisterPO.rebind(familyRegister);
      familyRegisterPO.doAllMatches();
   }

   private boolean derivePersonName(Person p)
   {
      FamilyMember fm = p.getCfm();
      Family f = fm.getFamily();
      String fullName = String.format("%s, %s", f.getName(), fm.getName());
      p.withName(fullName);
      
      return true;
   }
   
   @Override
   public void performAndPropagateTargetEdit(Consumer<Object> edit)
   {
      System.out.println("performAndPropagateSourceEdit done");
      
   }

   @Override
   public void performIdleSourceEdit(Consumer<Object> edit)
   {
      System.out.println("performIdleSourceEdit done");
      
   }

   @Override
   public void performIdleTargetEdit(Consumer<Object> edit)
   {
      System.out.println("performIdleTargetEdit done");
      
   }

   @Override
   public void setConfigurator(Configurator<Decisions> configurator)
   {
      System.out.println("setConfigurator done");
      
   }
   
   private FamiliesComparator familyComp = new FamiliesComparator();
   private PersonsComparator personComp = new PersonsComparator();
   private ModelCompare modelComp = new ModelCompare();

   
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
      
      System.out.println(expectedFamilyString + actualFamilyString + expectedPersonsString + actualPersonString);
      
      Assert.assertEquals("model strings should be equal", 
         (expectedFamilyString + expectedPersonsString).replaceAll("\\s+", ""), 
         (actualFamilyString + actualPersonString).replaceAll("\\s+", ""));
   }
   
   @Override
   public void assertPrecondition(Object source, Object target)
   {
      assertEquals(source, target);
      System.out.println("assertPostcondition done");
      
   }

}
