package org.emoflon.ibex.tgg.run.ibextggecoretosql;

import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.operational.csp.constraints.factories.ibextggecoretosql.UserDefinedRuntimeTGGAttrConstraintFactory;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;

import sql.SqlPackage;

public class _RegistrationHelper {

	/** Load and register source and target metamodels */
	public static void registerMetamodels(ResourceSet rs, OperationalStrategy strategy) throws IOException {
		EPackage srcPack = EcorePackage.eINSTANCE;
		EPackage trgPack = SqlPackage.eINSTANCE;
		
		rs.getPackageRegistry().put(srcPack.getNsURI(), srcPack);
		rs.getPackageRegistry().put(trgPack.getNsURI(), trgPack);
		rs.getPackageRegistry().put("platform:/resource/SQL/model/SQL.ecore", trgPack);
	}

	/** Create default options **/
	public static IbexOptions createIbexOptions() {
		IbexOptions options = new IbexOptions();
		options.projectName("IBeXTGGEcoreToSQL");
		options.projectPath("IBeXTGGEcoreToSQL");
		options.debug(false);
		options.userDefinedConstraints(new UserDefinedRuntimeTGGAttrConstraintFactory());
		return options;
	}
}
