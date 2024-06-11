package org.benchmarx.examples.familiestopersons.implementations.ibextgg.integrate;

import java.util.HashSet;
import java.util.Set;

import org.benchmarx.config.Configurator;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.emoflon.ibex.tgg.runtime.matches.ITGGMatch;
import org.emoflon.ibex.tgg.runtime.matches.container.ImmutableMatchContainer;
import org.emoflon.ibex.tgg.runtime.updatepolicy.IUpdatePolicy;
import org.emoflon.ibex.tgg.patterns.PatternType;

public class F2PUpdatePolicy implements IUpdatePolicy {

	private Configurator<Decisions> configurator;
	
	public F2PUpdatePolicy(Configurator<Decisions> configurator) {
		this.configurator = configurator;
	}

	@Override
	public ITGGMatch chooseOneMatch(ImmutableMatchContainer matchContainer) {
		Set<ITGGMatch> matches = new HashSet<ITGGMatch>(matchContainer.getMatches());
		matches.removeIf(this::isIrrelevantMatchForSync);
				
		handlePrefsForExistingFamily(matches);
		handlePrefsForParents(matches);

		return matches.isEmpty()? matchContainer.getNext() : matches.iterator().next();
	}

	private boolean isIrrelevantMatchForSync(ITGGMatch m) {
		return m.getType() == PatternType.CONSISTENCY;
	}

	private void handlePrefsForExistingFamily(Set<ITGGMatch> matches) {
		if (configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW)) {
			if(thereIsAtLeastOneExistingFamily(matches)) {
				matches.removeIf(m -> !usesExistingFamily(m));
			}
		} else {
			matches.removeIf(this::usesExistingFamily);
		}
	}

	private boolean thereIsAtLeastOneExistingFamily(Set<ITGGMatch> matches) {
		return matches.stream().anyMatch(this::usesExistingFamily);
	}
	
	private boolean usesExistingFamily(ITGGMatch m) {
		return m.getPatternName().contains("Existing");
	}

	private void handlePrefsForParents(Set<ITGGMatch> matches) {
		if (configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD)) {
			if(atLeastOneParentCanBeCreated(matches))
				matches.removeIf(this::isChildRule);
		} else if(atLeastOneChildCanBeCreated(matches))
			matches.removeIf(this::isParentRule);
	}
	
	private boolean atLeastOneParentCanBeCreated(Set<ITGGMatch> matches) {
		return matches.stream().anyMatch(this::isParentRule);
	}

	private boolean isParentRule(ITGGMatch m) {
		return m.getPatternName().contains("Mother") || m.getPatternName().contains("Father");
	}

	private boolean isChildRule(ITGGMatch m) {
		return m.getPatternName().contains("Son") || m.getPatternName().contains("Daughter");
	}

	private boolean atLeastOneChildCanBeCreated(Set<ITGGMatch> matches) {
		return matches.stream().anyMatch(this::isChildRule);
	}
}
