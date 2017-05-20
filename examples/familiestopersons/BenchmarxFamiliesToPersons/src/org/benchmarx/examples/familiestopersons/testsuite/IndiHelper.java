package org.benchmarx.examples.familiestopersons.testsuite;

import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
import org.benchmarx.families.core.FamilyHelper;
import org.benchmarx.families.core.FamilyRegisterBuilder;
import org.benchmarx.persons.core.PersonHelper;



public class IndiHelper
{
   FamilyHelper emfFamilyHelper = new FamilyHelper();
   PersonHelper emfPersonHelper = new PersonHelper();
   
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

   public void renameSimpsonToBouvier(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.renameSimpsonToBouvier(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family simpsons = register.getFamilies().createNameCondition("Simpson").last();
         
         simpsons.setName("Bouvier");
      }
   }

   public void moveLisa(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.moveLisa(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family flanders = register.getFamilies().createNameCondition("Flanders").first();
         FamilyMember lisa = register.getFamilies().getDaughters().createNameCondition("Lisa").first();
         
         lisa.withDaughterOf(null);
         flanders.withMother(lisa);
      }
   }

   public void moveMarge(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.moveMarge(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family skinner = register.getFamilies().createNameCondition("Skinner").first();
         FamilyMember marge = register.getFamilies().createNameCondition("Simpson").last().getMother();
         
         // marge.withMotherOf(null);
         skinner.withMother(marge);
      }
   }

   public void moveMaggieAndChangeRole(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.moveMaggieAndChangeRole(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family skinner = register.getFamilies().createNameCondition("Skinner").first();
         FamilyMember maggie = register.getFamilies().getDaughters().createNameCondition("Maggie").first();
         
         maggie.withDaughterOf(null);
         skinner.withSons(maggie);
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

   public void createFatherNed(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createFatherNed(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         register.getFamilies("Flanders").createFather().withName("Ned");
      }
   }

   public void deleteFatherHomer(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.deleteFatherHomer(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family simpsons = register.getFamilies().createNameCondition("Simpson").last();
         FamilyMember homer = simpsons.getFather();
         homer.removeYou();
      }
   }

   public void createFatherHomer(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createFatherHomer(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family simpsons = register.getFamilies().createNameCondition("Simpson").last();
         FamilyMember homer = simpsons.createFather().withName("Homer");
      }
   }

   public void createMotherMaude(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createMotherMaude(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         register.getFamilies("Flanders").createMother().withName("Maude");
      }
   }

   public void createSonTodd(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.createSonTodd(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         register.getFamilies("Flanders").createSons().withName("Todd");
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
         
         Family newSimpsons = register.getFamilies().createNameCondition("Simpson").last();
         newSimpsons.createSons().withName("Bart");
      }
   }

   public void deleteFirstSonBart(Object obj) 
   {
      if (obj instanceof Families.FamilyRegister)
      {
         Families.FamilyRegister register = (Families.FamilyRegister) obj;
         emfFamilyHelper.deleteFirstSonBart(register);
      }
      else
      {
         FamilyRegister register = (FamilyRegister) obj;
         
         Family newSimpsons = register.getFamilies().createNameCondition("Simpson").last();
         FamilyMember firstBart = newSimpsons.getSons().first();
         firstBart.removeYou();
      }
   }

   public void setBirthdayOfRod(Object obj) 
   {
      if (obj instanceof Persons.PersonRegister)
      {
         Persons.PersonRegister register = (Persons.PersonRegister) obj;
         emfPersonHelper.setBirthdayOfRod(register);
      }
      else
      {
         PersonRegister register = (PersonRegister) obj;
         
         Person rod = register.getPersons().createNameCondition("Flanders, Rod").first();
         rod.withBirthday("2013-10-01");
      }
   }

   public void setBirthdayOfFatherBart(Object obj) 
   {
      if (obj instanceof Persons.PersonRegister)
      {
         Persons.PersonRegister register = (Persons.PersonRegister) obj;
         emfPersonHelper.setBirthdayOfFatherBart(register);
      }
      else
      {
         PersonRegister register = (PersonRegister) obj;
         
         Person p = register.getPersons().createNameCondition("Simpson, Bart").createBirthdayCondition("0001-01-01").first();
         p.withBirthday("2013-03-09");
      }
   }

   public void changeAllBirthdays(Object obj) 
   {
      if (obj instanceof Persons.PersonRegister)
      {
         Persons.PersonRegister register = (Persons.PersonRegister) obj;
         emfPersonHelper.changeAllBirthdays(register);
      }
      else
      {
         PersonRegister register = (PersonRegister) obj;
         
         setBirthdaysOfSimpson(register);
         setBirthdayOfRod(register);
      }
   }
   
   public void setBirthdaysOfSimpson(Object obj) 
   {
      if (obj instanceof Persons.PersonRegister)
      {
         Persons.PersonRegister register = (Persons.PersonRegister) obj;
         emfPersonHelper.setBirthdaysOfSimpson(register);
      }
      else
      {
         PersonRegister register = (PersonRegister) obj;
         
         Person p = register.getPersons().createNameCondition("Simpson, Homer").first();
         p.withBirthday("2013-01-09");
         p = register.getPersons().createNameCondition("Simpson, Marge").first();
         p.withBirthday("2013-02-09");
         p = register.getPersons().createNameCondition("Simpson, Bart").createBirthdayCondition("0001-01-01").first();
         p.withBirthday("2013-03-10");
         p = register.getPersons().createNameCondition("Simpson, Lisa").first();
         p.withBirthday("2013-03-08");
         p = register.getPersons().createNameCondition("Simpson, Maggie").first();
         p.withBirthday("2013-03-07");
         }
   }

   public void setBirthdayOfYoungerBart(Object obj) 
   {
      if (obj instanceof Persons.PersonRegister)
      {
         Persons.PersonRegister register = (Persons.PersonRegister) obj;
         emfPersonHelper.setBirthdayOfYoungerBart(register);
      }
      else
      {
         PersonRegister register = (PersonRegister) obj;
         
         Person p = register.getPersons().createNameCondition("Simpson, Bart").createBirthdayCondition("0001-01-01").first();
         p.withBirthday("2013-03-11");
      }
   }



}
