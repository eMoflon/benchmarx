package org.benchmarx.examples.familiestopersons.implementations.sdmlib;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ModelGen
{
     /**
    * 
    * @see <a href='../../../../../../../doc/Family2PersonsClassModel.html'>Family2PersonsClassModel.html</a>
 */
   @Test
   public void Family2PersonsClassModel() throws Exception
   {
      Storyboard story = new Storyboard();

      ClassModel model = new ClassModel("org.benchmarx.examples.familiestopersons.implementations.sdmlib.model");

      Clazz familyRegister = model.createClazz("FamilyRegister");
      
      Clazz family = model.createClazz("Family")
            .withAttribute("name", DataType.STRING);

      familyRegister.withBidirectional(family, "families", Cardinality.MANY, "register", Cardinality.ONE);
      
      Clazz familyMember = model.createClazz("FamilyMember")
            .withAttribute("name", DataType.STRING);

      family.withBidirectional(familyMember, "mother", Cardinality.ONE, "motherOf", Cardinality.ONE);
      family.withBidirectional(familyMember, "father", Cardinality.ONE, "fatherOf", Cardinality.ONE);
      family.withBidirectional(familyMember, "sons", Cardinality.MANY, "sonOf", Cardinality.ONE);
      family.withBidirectional(familyMember, "daughters", Cardinality.MANY, "daughterOf", Cardinality.ONE);
      
      familyRegister.withUniDirectional(familyMember, "c", Cardinality.MANY);
      
      
      
      Clazz personRegister = model.createClazz("PersonRegister");
      
      Clazz person = model.createClazz("Person")
            .withAttribute("name", DataType.STRING)
            .withAttribute("birthday", DataType.STRING);

      personRegister.withBidirectional(person, "persons", Cardinality.MANY, "register", Cardinality.ONE);
      personRegister.withUniDirectional(person, "c", Cardinality.MANY);

      
      Clazz male = model.createClazz("Male").withSuperClazz(person);
      Clazz female = model.createClazz("Female").withSuperClazz(person);
      
      familyRegister.withBidirectional(personRegister, "personRegister", Cardinality.ONE, "familyRegister", Cardinality.ONE);
      
      familyMember.withBidirectional(person, "cp", Cardinality.ONE, "cfm", Cardinality.ONE);

      model.generate();
      
      story.addClassDiagram(model);

      story.dumpHTML();
   }
}
