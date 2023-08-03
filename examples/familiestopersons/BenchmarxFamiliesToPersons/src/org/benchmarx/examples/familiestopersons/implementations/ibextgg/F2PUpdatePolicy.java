package org.benchmarx.examples.familiestopersons.implementations.ibextgg;

import java.util.HashSet;
import java.util.Set;

import org.benchmarx.config.Configurator;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.emoflon.ibex.tgg.compiler.patterns.PatternSuffixes;
import org.emoflon.ibex.tgg.operational.matches.IMatch;
import org.emoflon.ibex.tgg.operational.matches.ImmutableMatchContainer;
import org.emoflon.ibex.tgg.operational.updatepolicy.UpdatePolicy;
import org.emoflon.ibex.tgg.util.MAUtil;


public class F2PUpdatePolicy extends UpdatePolicy {

	private Configurator<Decisions> configurator;
	
	public F2PUpdatePolicy(Configurator<Decisions> configurator) {
		this.configurator = configurator;
	}

	@Override
	public IMatch chooseOneMatch(ImmutableMatchContainer matchContainer) {
		Set<IMatch> matches = new HashSet<IMatch>(matchContainer.getMatches());
		matches.removeIf(this::isIrrelevantMatchForSync);
				
		handlePrefsForExistingFamily(matches);
		handlePrefsForParents(matches);

		return matches.isEmpty()? matchContainer.getNextRandom() : matches.iterator().next();
	}

	private boolean isIrrelevantMatchForSync(IMatch m) {
		return m.getPatternName().endsWith(PatternSuffixes.CONSISTENCY);
	}

	private void handlePrefsForExistingFamily(Set<IMatch> matches) {
		if (configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW)) {
			if(thereIsAtLeastOneExistingFamily(matches))
				matches.removeIf(m -> !usesExistingFamily(m));
		} else {
			matches.removeIf(this::usesExistingFamily);
		}
	}

	private boolean thereIsAtLeastOneExistingFamily(Set<IMatch> matches) {
		return matches.stream().anyMatch(this::usesExistingFamily);
	}
	
	private boolean usesExistingFamily(IMatch m) {
		return !m.getPatternName().contains(MAUtil.FUSED);
	}

	private void handlePrefsForParents(Set<IMatch> matches) {
		if (configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD)) {
			if(atLeastOneParentCanBeCreated(matches))
				matches.removeIf(this::isChildRule);
		} else if(atLeastOneChildCanBeCreated(matches))
			matches.removeIf(this::isParentRule);
	}
	
	private boolean atLeastOneParentCanBeCreated(Set<IMatch> matches) {
		return matches.stream().anyMatch(this::isParentRule);
	}

	private boolean isParentRule(IMatch m) {
		return m.getPatternName().contains("Mother") || m.getPatternName().contains("Father");
	}

	private boolean isChildRule(IMatch m) {
		return m.getPatternName().contains("Son") || m.getPatternName().contains("Daughter");
	}

	private boolean atLeastOneChildCanBeCreated(Set<IMatch> matches) {
		return matches.stream().anyMatch(this::isChildRule);
	}
}
