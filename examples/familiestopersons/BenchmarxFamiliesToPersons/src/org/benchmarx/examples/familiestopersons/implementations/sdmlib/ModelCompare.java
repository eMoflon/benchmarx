package org.benchmarx.examples.familiestopersons.implementations.sdmlib;

import java.util.Arrays;

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
         String father = "Nothing";
         if (family.getFather() != null)
         {
            father = "Just (FamilyMember { firstName = \"Homer\" })";
            father = CGUtil.replaceAll(father, "Homer", family.getFather().getName());
         }
         
         String mother = "Nothing";
         if (family.getMother() != null)
         {
            mother = "Just (FamilyMember { firstName = \"Homer\" })";
            mother = CGUtil.replaceAll(mother, "Homer", family.getMother().getName());
         }
         
         
         StringList sonsList = new StringList();
         
         String oneSon = "" +
               "FamilyMember { firstName = \"Rod\" }";
         
         for (FamilyMember m : family.getSons())
         {
            String tmp = CGUtil.replaceAll(oneSon, "Rod", m.getName());
            sonsList.add(tmp);
         }
         
         sonsList.sort(String::compareTo);
         
         StringList daughtersList = new StringList();
         
         String oneDaughter = "" +
               "FamilyMember { firstName = \"Rod\" }";
         
         for (FamilyMember m : family.getDaughters())
         {
            String tmp = CGUtil.replaceAll(oneDaughter, "Rod", m.getName());
            daughtersList.add(tmp);
         }
         
         daughtersList.sort(String::compareTo);
         
         
         String familyString = "" +
               "  Family {\n" + 
               "        familyName = \"Simpson\"\n" + 
               "       ,father     = NoFather\n" + 
               "       ,mother     = NoMother\n" + 
               "       ,sons       = [listOfSons]\n" + 
               "       ,daughters  = [listOfDaughters]\n" + 
               "   }\n" + 
               "";
         
         familyString = CGUtil.replaceAll(familyString, 
            "Simpson", family.getName(), 
            "NoFather", father,
            "NoMother", mother,
            "listOfSons", sonsList.concat(", "),
            "listOfDaughters", daughtersList.concat(", ")
            );
         
//         familyString = CGUtil.replaceAll(familyString, 
//            "= []", "=  []");
         
         familyDetails.add(familyString);
      }
      
      familyDetails.sort(String::compareTo);
      
      
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
      
      Person[] personArray = personRegister.getPersons().toArray(new Person[personRegister.getPersons().size()]);
      Arrays.sort(personArray, (t,o) -> t.compareTo(o));

      for (Person p : personArray)
      {
         String gender = " Male";
         if (p instanceof Female)
         {
            gender = "Female";
         }
         
         String tmp = CGUtil.replaceAll(personText, 
            "Male", gender,
            "Flanders, Rod", p.getName(), 
            "0001-01-01", p.getBirthday());
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
