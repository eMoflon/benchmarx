package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new FamilyCreator());
      jsonIdMap.with(new FamilyPOCreator());
      jsonIdMap.with(new FamilyMemberCreator());
      jsonIdMap.with(new FamilyMemberPOCreator());
      jsonIdMap.with(new FamilyRegisterCreator());
      jsonIdMap.with(new FamilyRegisterPOCreator());
      jsonIdMap.with(new FemaleCreator());
      jsonIdMap.with(new FemalePOCreator());
      jsonIdMap.with(new MaleCreator());
      jsonIdMap.with(new MalePOCreator());
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new PersonRegisterCreator());
      jsonIdMap.with(new PersonRegisterPOCreator());
      return jsonIdMap;
   }
}
