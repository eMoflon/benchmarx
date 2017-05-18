package org.benchmarx.examples.familiestopersons.testsuite;

import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;
import org.benchmarx.families.core.FamilyHelper;
import org.benchmarx.families.core.FamilyRegisterBuilder;


public class IndiHelper
{
   FamilyHelper emfFamilyHelper = new FamilyHelper();
   
   public void createSimpsonFamily(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createSimpsonFamily(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         register.createFamilies().withName("Simpson");
      }
   }  
   
   
   public void renameEmptySimpsonToBouvier(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.renameEmptySimpsonToBouvier(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family simpsons = register.getFamilies("Simpson");
         
         simpsons.setName("Bouvier");
      }
   }

   public void createSkinnerFamily(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createSkinnerFamily(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         register.createFamilies().withName("Skinner");
      }
   }

   public void createFlandersFamily(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createFlandersFamily(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         register.createFamilies().withName("Flanders");
      }
   }

   public void createSonRod(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createSonRod(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family flanders = register.getFamilies("Flanders");
         
         flanders.createSons().withName("Rod");
      }
   }

   public void createNewFamilySimpsonWithMembers(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createNewFamilySimpsonWithMembers(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family simpsons = register.createFamilies().withName("Simpson");
         simpsons.createFather().withName("Homer");
         simpsons.createMother().withName("Marge");
         simpsons.createSons().withName("Bart");
         simpsons.createDaughters().withName("Lisa");
         simpsons.createDaughters().withName("Maggie");
      }
   }

   public void createFatherBart(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createFatherBart(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family newSimpsons = register.getFamilies().createNameCondition("Simpson").filterFather(null).first();
         newSimpsons.createFather().withName("Bart");
      }
   }

   public void createSonBart(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createSonBart(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family newSimpsons = register.getFamilies().createNameCondition("Simpson").first();
         newSimpsons.createSons().withName("Bart");
      }
   }

}
