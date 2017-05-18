package org.benchmarx.examples.familiestopersons.implementations.sdmlib;

import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Family;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyMember;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.FamilyRegister;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Female;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Person;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.PersonRegister;
import org.sdmlib.CGUtil;

import de.uniks.networkparser.list.StringList;

public class ModelCompare
{

   public String familyToString(FamilyRegister register)
   {
      StringList familyDetails = new StringList();
      
      for (Family family : register.getFamilies())
      {
         StringList kidsList = new StringList();
         
         String oneKid = "" +
               "FamilyMember { firstName = \"Rod\" }";
         
         for (FamilyMember m : family.getSons())
         {
            String tmp = CGUtil.replaceAll(oneKid, "Rod", m.getName());
            kidsList.add(tmp);
         }
         
         
         String familyString = "" +
               "  Family {\n" + 
               "        familyName = \"Simpson\"\n" + 
               "       ,father     = Nothing\n" + 
               "       ,mother     = Nothing\n" + 
               "       ,sons       = [listOfSons]\n" + 
               "       ,daughters  = []\n" + 
               "   }\n" + 
               "";
         
         familyString = CGUtil.replaceAll(familyString, 
            "Simpson", family.getName(), 
            "listOfSons", kidsList.concat(",\n"));
         
         familyDetails.add(familyString);
      }
      
      
      String rootTemplate = "" + 
         "FamilyRegister {\n" + 
         "    families = [\n" + 
         "    familyDetails]\n" + 
         "}\n";
      
      rootTemplate = CGUtil.replaceAll(rootTemplate, "familyDetails", familyDetails.concat(" ,"));
      
      return rootTemplate;
   }

   public String personsToString(PersonRegister personRegister)
   {
      StringList personList = new StringList();
      
      String personText =  "" +
            "\n" + 
            "      Male  {   \n" + 
            "               fullName = \"Flanders, Rod\"\n" + 
            "             , birthday = \"0001-01-01\"\n" + 
            "      }";

      for (Person p : personRegister.getPersons())
      {
         String gender = "Male";
         if (p instanceof Female)
         {
            gender = "Female";
         }
         
         String tmp = CGUtil.replaceAll(personText, 
            "Male", gender,
            "Flanders, Rod", p.getName());
         personList.add(tmp);
      }
      
      
      String template = "" + 
            "PersonRegister {\n" + 
            "   persons = [personList\n" + 
            "   ]\n" + 
            "}\n" + 
            "";
      
      template = CGUtil.replaceAll(template, "personList", personList.concat(","));
      
      return template;
   }

   public void clone(Families.FamilyRegister emfFamilyRegister, FamilyRegister familyRegister)
   {
      for (Families.Family emfFamily : emfFamilyRegister.getFamilies())
      {
         Family family = familyRegister.createFamilies().withName(emfFamily.getName());
         
         if (emfFamily.getMother() != null)
         {
            family.createMother().withName(emfFamily.getMother().getName());
         }
         
         if (emfFamily.getFather() != null)
         {
            family.createFather().withName(emfFamily.getFather().getName());
         }
         
         for (Families.FamilyMember emfMember : emfFamily.getDaughters())
         {
            family.createDaughters().withName(emfMember.getName());
         }

         for (Families.FamilyMember emfMember : emfFamily.getSons())
         {
            family.createSons().withName(emfMember.getName());
         }
      }
   }

}
