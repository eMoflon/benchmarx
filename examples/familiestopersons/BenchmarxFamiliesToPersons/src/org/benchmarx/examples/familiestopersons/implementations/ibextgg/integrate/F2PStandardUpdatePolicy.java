package org.benchmarx.examples.familiestopersons.implementations.ibextgg.integrate;

import java.util.LinkedList;
import java.util.List;

import org.emoflon.ibex.tgg.patterns.PatternType;
import org.emoflon.ibex.tgg.runtime.matches.ITGGMatch;
import org.emoflon.ibex.tgg.runtime.matches.container.ImmutableMatchContainer;
import org.emoflon.ibex.tgg.runtime.updatepolicy.IUpdatePolicy;

public class F2PStandardUpdatePolicy implements IUpdatePolicy{

	@Override
	public ITGGMatch chooseOneMatch(ImmutableMatchContainer matchContainer) {
		var matches = matchContainer.getMatches();
		
		var preferedMatch = matches.parallelStream().filter(m -> {
			return m.getType() != PatternType.CONSISTENCY && m.getType() != PatternType.CC && m.getPatternName().contains("Existing");
		}).findAny();
		
		if(preferedMatch.isPresent())
			return preferedMatch.get();
		
		return matchContainer.getNext();
	}

}
