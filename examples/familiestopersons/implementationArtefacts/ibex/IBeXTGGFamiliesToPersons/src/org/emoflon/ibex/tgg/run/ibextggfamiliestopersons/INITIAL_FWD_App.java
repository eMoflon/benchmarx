package org.emoflon.ibex.tgg.run.ibextggfamiliestopersons;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.sync.SYNC;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emoflon.ibex.tgg.compiler.patterns.PatternSuffixes;
import org.emoflon.ibex.tgg.runtime.engine.DemoclesTGGEngine;

public class INITIAL_FWD_App extends SYNC {

	public INITIAL_FWD_App() throws IOException {
		super(createIbexOptions());
		registerBlackInterpreter(new DemoclesTGGEngine());
	}

	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);

		INITIAL_FWD_App init_fwd = new INITIAL_FWD_App();
		
		logger.info("Starting INITIAL FWD");
		long tic = System.currentTimeMillis();
		init_fwd.forward();
		long toc = System.currentTimeMillis();
		logger.info("Completed INITIAL FWD in: " + (toc - tic) + " ms");
		
		init_fwd.saveModels();
		init_fwd.terminate();
	}
	
	@Override
	public boolean isPatternRelevantForCompiler(String patternName) {
		return patternName.endsWith(PatternSuffixes.FWD);
	}
	
	@Override
	public void loadModels() throws IOException {
		s = loadResource(options.projectPath() + "/instances/src.xmi");
		t = createResource(options.projectPath() + "/instances/trg.xmi");
		c = createResource(options.projectPath() + "/instances/corr.xmi");
		p = createResource(options.projectPath() + "/instances/protocol.xmi");
		
		EcoreUtil.resolveAll(rs);
	}
	
	@Override
	public void saveModels() throws IOException {
		t.save(null);
		c.save(null);
		p.save(null);
	}
	
	@Override
	protected void registerUserMetamodels() throws IOException {
		_RegistrationHelper.registerMetamodels(rs, this);
			
		// Register correspondence metamodel last
		loadAndRegisterMetamodel(options.projectPath() + "/model/" + options.projectName() + ".ecore");
	}
	
	private static IbexOptions createIbexOptions() {
		return _RegistrationHelper.createIbexOptions();
	}
}
