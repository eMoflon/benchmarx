package org.emoflon.ibex.tgg.run.ibextggpetrinets;

import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.operational.csp.constraints.factories.ibextggpetrinets.UserDefinedRuntimeTGGAttrConstraintFactory;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;

import pn.PnPackage;
import pnw.PnwPackage;

public class _RegistrationHelper {

	/** Load and register source and target metamodels */
	public static void registerMetamodels(ResourceSet rs, OperationalStrategy strategy) throws IOException {
		EPackage srcPack = PnPackage.eINSTANCE;
		EPackage trgPack = PnwPackage.eINSTANCE;
		
		rs.getPackageRegistry().put(srcPack.getNsURI(), srcPack);
		rs.getPackageRegistry().put(trgPack.getNsURI(), trgPack);
		rs.getPackageRegistry().put("platform:/resource/Petrinet/model/PetriNet.ecore", srcPack);
		rs.getPackageRegistry().put("platform:/resource/PetrinetWeighted/model/PetriNetWeighted.ecore", trgPack);
	}

	/** Create default options **/
	public static IbexOptions createIbexOptions() {
		IbexOptions options = new IbexOptions();
		options.projectName("IBexTGGPetrinets");
		options.projectPath("IBexTGGPetrinets");
		options.debug(false);
		options.userDefinedConstraints(new UserDefinedRuntimeTGGAttrConstraintFactory());
		return options;
	}
}
