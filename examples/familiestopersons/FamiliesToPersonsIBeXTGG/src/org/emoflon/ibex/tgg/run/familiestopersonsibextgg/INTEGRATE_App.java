package org.emoflon.ibex.tgg.run.familiestopersonsibextgg;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.emoflon.ibex.common.engine.IMatch;
import org.emoflon.ibex.tgg.run.familiestopersonsibextgg.config._DefaultRegistrationHelper;
import org.emoflon.ibex.tgg.runtime.config.IRegistrationHelper;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.FragmentProvider;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.INTEGRATE;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.Conflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.ConflictContainer;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.DelPreserveAttrConflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.DelPreserveEdgeConflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.DeletePreserveConflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.InconsDomainChangesConflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.OperationalMultiplicityConflict;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.resolution.CRS_PreferTarget;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.conflicts.resolution.util.ConflictResolver;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.pattern.IntegrationFragment;
import org.emoflon.ibex.tgg.runtime.strategies.integrate.pattern.IntegrationPattern;
import org.emoflon.ibex.tgg.runtime.strategies.modules.TGGResourceHandler;

public class INTEGRATE_App extends INTEGRATE {

	public static final List<IntegrationFragment> FIRST_ITERATION_FRAGMENTS = Arrays.asList( //
			FragmentProvider.CHECK_LOCAL_CONSISTENCY, //
			FragmentProvider.REPAIR,
			FragmentProvider.RESOLVE_CONFLICTS, //
			FragmentProvider.RESOLVE_BROKEN_MATCHES, //
			FragmentProvider.TRANSLATE,
			FragmentProvider.CLEAN_UP 
	);
	
	// eMoflon supports other pattern matching engines. Replace _DefaultRegistrationHelper with one of the other registrationHelpers from the *.config-package to choose between them. Default: Democles 
	public static IRegistrationHelper registrationHelper = new _DefaultRegistrationHelper();

	public INTEGRATE_App() throws IOException {
		super(registrationHelper.createIbexOptions().resourceHandler(new TGGResourceHandler() {
			@Override
			public void saveModels() throws IOException {
				super.saveModels();
			}
			
			@Override
			public void loadModels() throws IOException {
				source = createResource(options.project.path() + "/instances/src.xmi");
				target = createResource(options.project.path() + "/instances/trg.xmi");
				corr = createResource(options.project.path() + "/instances/corr.xmi");
				protocol = createResource(options.project.path() + "/instances/protocol.xmi");
			}
		}).integration.conflictSolver(new ConflictResolver() {
			
			@Override
			public void resolveConflict(ConflictContainer conflictContainer) {
				System.out.println("Solving conflictContainer: " + conflictContainer);
				for(var conflict : getSignificantConflicts(conflictContainer.getConflicts())) {
					// unsupported as of yet
					if(conflict instanceof OperationalMultiplicityConflict multiConflict)
						continue;
					else
					if(conflict instanceof CRS_PreferTarget preferTarget) {
						preferTarget.crs_preferTarget();
					}
				}
			}
			
		}).repair.usePGbasedSCruleCreation(false));
	}
	
	private static Collection<Conflict> getSignificantConflicts(Set<Conflict> conflicts) {
		// for each causing match, we only store one conflict that should be resolved
		Map<IMatch, Conflict> match2conflict = new HashMap<>();
		for(var conflict : conflicts) {
			if(conflict instanceof DeletePreserveConflict deletePreserveConflict) {
				for(var causingMatch : deletePreserveConflict.getCausingMatches()) {
					if(match2conflict.containsKey(causingMatch)) {
						if(conflict instanceof DelPreserveEdgeConflict deletePreserveEdgeConflict) {
							// this preserves families
							if(deletePreserveEdgeConflict.getOperationalMatch().getPatternName().contains("Existing")) {
								match2conflict.put(causingMatch, conflict);
							}
							else {
								// we prefer edge conflicts over attributes here
								if(match2conflict.get(causingMatch) instanceof DelPreserveAttrConflict) {
									match2conflict.put(causingMatch, conflict);
								}
							}
						}
					}
					else {
						match2conflict.put(causingMatch, conflict);
					}
				}
			}
		}
		return match2conflict.values();
	}
	
	@Override
	public void integrate() throws IOException {
		options.integration.pattern(new IntegrationPattern(FIRST_ITERATION_FRAGMENTS));
		super.integrate();
	}
	
	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);

		logger.info("Starting INTEGRATE");
		long tic = System.currentTimeMillis();
		INTEGRATE_App integrate = new INTEGRATE_App();
		long toc = System.currentTimeMillis();
		logger.info("Completed init for INTEGRATE in: " + (toc - tic) + " ms");
		
		tic = System.currentTimeMillis();
		integrate.integrate();
		toc = System.currentTimeMillis();
		logger.info("Completed INTEGRATE in: " + (toc - tic) + " ms");
		
		integrate.saveModels();
		integrate.terminate();
	}
}
