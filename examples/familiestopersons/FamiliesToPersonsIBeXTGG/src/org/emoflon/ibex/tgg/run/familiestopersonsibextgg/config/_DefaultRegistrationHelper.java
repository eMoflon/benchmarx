package org.emoflon.ibex.tgg.run.familiestopersonsibextgg.config;

import java.io.IOException;
	
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.runtime.config.IRegistrationHelper;
import org.emoflon.ibex.tgg.runtime.config.options.IbexOptions;
import org.emoflon.ibex.tgg.runtime.strategies.modules.IbexExecutable;

public class _DefaultRegistrationHelper implements IRegistrationHelper{

	/** Load and register source and target metamodels */
	public void registerMetamodels(ResourceSet rs, IbexExecutable executable) throws IOException {
		// Replace to register generated code or handle other URI-related requirements
		new HiPERegistrationHelper().registerMetamodels(rs, executable);
	}

	/** Create default options **/
	public IbexOptions createIbexOptions() {
		return new HiPERegistrationHelper().createIbexOptions();
	}
}
