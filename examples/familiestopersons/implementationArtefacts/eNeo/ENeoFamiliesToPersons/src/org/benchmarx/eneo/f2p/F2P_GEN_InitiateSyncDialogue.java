package org.benchmarx.eneo.f2p;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.emoflon.neo.api.eneofamiliestopersons.API_Common;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.API_FamiliesToPersons;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.run.F2P_GEN_Run;
import org.emoflon.neo.api.eneofamiliestopersons.org.benchmarx.eneo.f2p.tgg.API_F2P_GEN;
import org.emoflon.neo.cypher.models.NeoCoreBuilder;
import org.emoflon.neo.emsl.util.FlattenerException;
import org.emoflon.neo.engine.generator.INodeSampler;
import org.emoflon.neo.engine.modules.NeoGenerator;
import org.emoflon.neo.engine.modules.cleanup.NoOpCleanup;
import org.emoflon.neo.engine.modules.matchreprocessors.ParanoidNeoReprocessor;
import org.emoflon.neo.engine.modules.monitors.HeartBeatAndReportMonitor;
import org.emoflon.neo.engine.modules.ruleschedulers.TwoPhaseRuleSchedulerForGEN;
import org.emoflon.neo.engine.modules.startup.NoOpStartup;
import org.emoflon.neo.engine.modules.terminationcondition.CompositeTerminationConditionForGEN;
import org.emoflon.neo.engine.modules.terminationcondition.MaximalRuleApplicationsTerminationCondition;
import org.emoflon.neo.engine.modules.updatepolicies.TwoPhaseUpdatePolicyForGEN;
import org.emoflon.neo.engine.modules.valueGenerators.LoremIpsumStringValueGenerator;
import org.emoflon.neo.engine.modules.valueGenerators.ModelNameValueGenerator;

public class F2P_GEN_InitiateSyncDialogue extends F2P_GEN_Run {

	public F2P_GEN_InitiateSyncDialogue() {
		super(SRC_MODEL_NAME, TRG_MODEL_NAME);
	}
	
	@Override
	public void run() throws Exception {
		try (var builder = API_Common.createBuilder()) {
			var generator = createGenerator(builder);
	
			logger.info("Running generator...");
			generator.generate();
			logger.info("Generator terminated.");
		}
	}

	@Override
	public NeoGenerator createGenerator(NeoCoreBuilder builder) {
		var api = new API_FamiliesToPersons(builder);
		try {
			api.exportMetamodelsForF2P();
		} catch (FlattenerException e) {
			e.printStackTrace();
		}
		var allRules = new API_F2P_GEN(builder, "../", API_Common.PLATFORM_PLUGIN_URI, API_Common.NEOCORE_URI_INSTALLED).getAllRulesForF2P_GEN();
		var maxRuleApps = new MaximalRuleApplicationsTerminationCondition(allRules, 0);
		maxRuleApps.setMax(API_FamiliesToPersons.F2P__RegisterToRegisterRule, 1);
		
		INodeSampler sampler = (String type, String ruleName, String nodeName) -> {
			return INodeSampler.EMPTY;
		};
		
		return new NeoGenerator(//
				allRules, //
				new NoOpStartup(), //
				new CompositeTerminationConditionForGEN(2, TimeUnit.MINUTES, maxRuleApps), //
				new TwoPhaseRuleSchedulerForGEN(sampler), //
				new TwoPhaseUpdatePolicyForGEN(maxRuleApps), //
				new ParanoidNeoReprocessor(), //
				new NoOpCleanup(), //
				new HeartBeatAndReportMonitor(), //
				new ModelNameValueGenerator(srcModelName, trgModelName), //
				List.of(new LoremIpsumStringValueGenerator()));
	}
}
