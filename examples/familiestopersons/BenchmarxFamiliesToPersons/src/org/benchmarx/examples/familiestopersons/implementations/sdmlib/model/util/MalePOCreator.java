package org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.Male;

public class MalePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MalePO(new Male[]{});
      } else {
          return new MalePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.benchmarx.examples.familiestopersons.implementations.sdmlib.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
