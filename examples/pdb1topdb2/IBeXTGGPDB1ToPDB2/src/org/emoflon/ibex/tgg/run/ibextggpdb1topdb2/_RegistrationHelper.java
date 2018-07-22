package org.emoflon.ibex.tgg.run.ibextggpdb1topdb2;

import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.operational.csp.constraints.factories.ibextggpdb1topdb2.UserDefinedRuntimeTGGAttrConstraintFactory;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;
import pdb1.Pdb1Package;
import pdb2.Pdb2Package;

public class _RegistrationHelper {

	/** Load and register source and target metamodels */
	public static void registerMetamodels(ResourceSet rs, OperationalStrategy strategy) throws IOException {
		EPackage srcPack = Pdb1Package.eINSTANCE;
		EPackage trgPack = Pdb2Package.eINSTANCE;
		
		rs.getPackageRegistry().put(srcPack.getNsURI(), srcPack);
		rs.getPackageRegistry().put(trgPack.getNsURI(), trgPack);
		rs.getPackageRegistry().put("platform:/resource/PDB1/model/PersonsDB1.ecore", srcPack);
		rs.getPackageRegistry().put("platform:/resource/PDB2/model/PersonsDB2.ecore", trgPack);
	}

	/** Create default options **/
	public static IbexOptions createIbexOptions() {
		IbexOptions options = new IbexOptions();
		options.projectName("IBeXTGGPDB1ToPDB2");
		options.projectPath("IBeXTGGPDB1ToPDB2");
		options.debug(false);
		options.userDefinedConstraints(new UserDefinedRuntimeTGGAttrConstraintFactory());
		return options;
	}
}
