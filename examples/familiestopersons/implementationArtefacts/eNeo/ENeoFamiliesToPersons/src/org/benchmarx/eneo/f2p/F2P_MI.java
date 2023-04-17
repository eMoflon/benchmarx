package org.benchmarx.eneo.f2p;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.emoflon.neo.api.eneofamiliestopersons.API_Common;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.API_FamiliesToPersons;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.run.F2P_GEN_Run;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.run.F2P_MI_Run;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.tgg.API_F2P_GEN;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.tgg.API_F2P_MI;
import org.emoflon.neo.cypher.models.NeoCoreBuilder;
import org.emoflon.neo.cypher.patterns.NeoMatch;
import org.emoflon.neo.cypher.rules.NeoCoMatch;
import org.emoflon.neo.cypher.rules.NeoRule;
import org.emoflon.neo.engine.generator.MatchContainer;
import org.emoflon.neo.engine.modules.NeoGenerator;
import org.emoflon.neo.engine.modules.analysis.TripleRuleAnalyser;
import org.emoflon.neo.engine.modules.matchreprocessors.MIReprocessor;
import org.emoflon.neo.engine.modules.monitors.HeartBeatAndReportMonitor;
import org.emoflon.neo.engine.modules.ruleschedulers.MIRuleScheduler;
import org.emoflon.neo.engine.modules.startup.PrepareContextDeltaAttributes;
import org.emoflon.neo.engine.modules.terminationcondition.MaximalRuleApplicationsTerminationCondition;
import org.emoflon.neo.engine.modules.terminationcondition.NoMoreMatchesTerminationCondition;
import org.emoflon.neo.engine.modules.terminationcondition.TimedTerminationCondition;
import org.emoflon.neo.engine.modules.updatepolicies.ModelIntegrationOperationalStrategy;
import org.emoflon.neo.engine.modules.valueGenerators.LoremIpsumStringValueGenerator;
import org.emoflon.neo.engine.modules.valueGenerators.ModelNameValueGenerator;

public class F2P_MI extends F2P_MI_Run {
	private Optional<Boolean> preferParents;

	public F2P_MI(Optional<Boolean> preferParents) {
		super(F2P_GEN_Run.SRC_MODEL_NAME, F2P_GEN_Run.TRG_MODEL_NAME);
		this.preferParents = preferParents;
	}

	@Override
	public void run() throws Exception {
		try (var builder = API_Common.createBuilder()) {
			new API_FamiliesToPersons(builder, "../", API_Common.PLATFORM_PLUGIN_URI, API_Common.NEOCORE_URI_INSTALLED)
					.exportMetamodelsForF2P();

			var generator = createGenerator(builder);

			logger.info("Running generator...");
			generator.generate();
			logger.info("Generator terminated.");
		}
	}

	@Override
	public NeoGenerator createGenerator(NeoCoreBuilder builder) {
		var api = new API_FamiliesToPersons(builder);
		var genAPI = new API_F2P_GEN(builder);
		var miAPI = new API_F2P_MI(builder);
		var genRules = genAPI.getAllRulesForF2P_GEN();
		var opRules = miAPI.getAllRulesForF2P_MI();
		var analyser = new TripleRuleAnalyser(new API_FamiliesToPersons(builder).getTripleRulesOfF2P());

		preferParents.ifPresent((pp) -> {
			var blacklist = pp ? List.of(
					API_FamiliesToPersons.F2P__SonToMaleRule,
					API_FamiliesToPersons.F2P__SonWithFamilyToMaleRule, 
					API_FamiliesToPersons.F2P__DaughterToFemaleRule,
					API_FamiliesToPersons.F2P__DaughterWithFamilyToFemaleRule
				) : List.of(
						API_FamiliesToPersons.F2P__FatherToMaleRule,
						API_FamiliesToPersons.F2P__FatherWithFamilyToMaleRule,
						API_FamiliesToPersons.F2P__MotherToFemaleRule,
						API_FamiliesToPersons.F2P__MotherWithFamilyToFemaleRule
					);

			blacklist.forEach(rn -> {
				var remove = opRules.stream().filter(r -> r.getName().startsWith(rn)).collect(Collectors.toList());
				opRules.removeAll(remove);
			});
		});
		
		modelIntegration = new ModelIntegrationOperationalStrategy(//
				solver, //
				builder, //
				genRules, //
				opRules, //
				api.getConstraintsOfF2P(), //
				srcModelName, //
				trgModelName//
		);

		return new NeoGenerator(//
				opRules, //
				new PrepareContextDeltaAttributes(builder, srcModelName, trgModelName), //
				new NoMoreMatchesOrTimeout(opRules, 20, 5), //
				new MIRuleScheduler(analyser), //
				modelIntegration, //
				new MIReprocessor(analyser), //
				modelIntegration, //
				new HeartBeatAndReportMonitor(), //
				new ModelNameValueGenerator(srcModelName, trgModelName), //
				List.of(new LoremIpsumStringValueGenerator()));
	}

}

class NoMoreMatchesOrTimeout extends NoMoreMatchesTerminationCondition {
	TimedTerminationCondition timout;
	MaximalRuleApplicationsTerminationCondition maxRuleApps;

	public NoMoreMatchesOrTimeout(Collection<NeoRule> allRules, int timeout_s, int maxApps) {
		timout = new TimedTerminationCondition(timeout_s, TimeUnit.SECONDS);
		maxRuleApps = new MaximalRuleApplicationsTerminationCondition(allRules, maxApps);
	}

	@Override
	public boolean isReached(MatchContainer<NeoMatch, NeoCoMatch> matchContainer) {
		return super.isReached(matchContainer) || timout.isReached(matchContainer)
				|| maxRuleApps.isReached(matchContainer);
	}

}