package org.emoflon.ibex.tgg.run.ibextggfamiliestopersons;

import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.operational.csp.constraints.factories.ibextggfamiliestopersons.UserDefinedRuntimeTGGAttrConstraintFactory;
import org.emoflon.ibex.tgg.operational.defaults.IbexOptions;
import org.emoflon.ibex.tgg.operational.strategies.OperationalStrategy;

import Families.FamiliesPackage;
import Persons.PersonsPackage;

public class _RegistrationHelper {

	/** Load and register source and target metamodels */
	public static void registerMetamodels(ResourceSet rs, OperationalStrategy strategy) throws IOException {
		EPackage srcPack = FamiliesPackage.eINSTANCE;
		EPackage trgPack = PersonsPackage.eINSTANCE;
		
		rs.getPackageRegistry().put(srcPack.getNsURI(), srcPack);
		rs.getPackageRegistry().put(trgPack.getNsURI(), trgPack);
		rs.getPackageRegistry().put("platform:/resource/Families/model/Families.ecore", srcPack);
		rs.getPackageRegistry().put("platform:/resource/Persons/model/Persons.ecore", trgPack);

	}

	/** Create default options **/
	public static IbexOptions createIbexOptions() {
		IbexOptions options = new IbexOptions();
		options.projectName("IBeXTGGFamiliesToPersons");
		options.projectPath("IBeXTGGFamiliesToPersons");
		options.debug(false);
		options.userDefinedConstraints(new UserDefinedRuntimeTGGAttrConstraintFactory());
		return options;
	}
}
