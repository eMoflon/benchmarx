package org.emoflon.ibex.tgg.run.ibextggsettooset;

import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.operational.csp.constraints.factories.ibextggsettooset.UserDefinedRuntimeTGGAttrConstraintFactory;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;
import osets.OsetsPackage;
import sets.SetsPackage;


public class _RegistrationHelper {

	/** Load and register source and target metamodels */
	public static void registerMetamodels(ResourceSet rs, OperationalStrategy strategy) throws IOException {
		EPackage srcPack = SetsPackage.eINSTANCE;
		EPackage trgPack = OsetsPackage.eINSTANCE;
		
		rs.getPackageRegistry().put(srcPack.getNsURI(), srcPack);
		rs.getPackageRegistry().put(trgPack.getNsURI(), trgPack);
		rs.getPackageRegistry().put("platform:/resource/Set/model/Sets.ecore", srcPack);
		rs.getPackageRegistry().put("platform:/resource/OSet/model/OrderedSets.ecore", trgPack);
	}

	/** Create default options **/
	public static IbexOptions createIbexOptions() {
		IbexOptions options = new IbexOptions();
		options.projectName("IBeXTGGSetToOSet");
		options.projectPath("IBeXTGGSetToOSet");
		options.debug(false);
		options.userDefinedConstraints(new UserDefinedRuntimeTGGAttrConstraintFactory());
		return options;
	}
}
