package org.emoflon.ibex.tgg.run.familiestopersonsibextgg.config;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emoflon.ibex.tgg.operational.csp.constraints.custom.familiestopersonsibextgg.RuntimeTGGAttrConstraintFactoryContainer;
import org.emoflon.ibex.tgg.runtime.config.IRegistrationHelper;
import org.emoflon.ibex.tgg.runtime.config.options.IbexOptions;
import org.emoflon.ibex.tgg.runtime.hipe.HiPETGGEngine;
import org.emoflon.ibex.tgg.runtime.strategies.modules.IbexExecutable;
import org.emoflon.ibex.tgg.runtime.strategies.opt.BWD_OPT;
import org.emoflon.ibex.tgg.runtime.strategies.opt.FWD_OPT;

import Families.impl.FamiliesPackageImpl;
import FamiliesSmartEMF.FamiliesSmartEMFPackage;
import FamiliesSmartEMF.impl.FamiliesSmartEMFPackageImpl;
import FamiliesToPersonsIBeXTGG.FamiliesToPersonsIBeXTGGPackage;
import FamiliesToPersonsIBeXTGG.impl.FamiliesToPersonsIBeXTGGPackageImpl;
import Persons.impl.PersonsPackageImpl;
import PersonsSmartEMF.impl.PersonsSmartEMFPackageImpl;

public class HiPERegistrationHelper implements IRegistrationHelper {
	
	/** Create default options **/
	public final void setWorkspaceRootDirectory(ResourceSet resourceSet) throws IOException {
		final String root = "../";
		URI key = URI.createPlatformResourceURI("/", true);
		URI value = URI.createFileURI(new File(root).getCanonicalPath() + File.separatorChar);
		resourceSet.getURIConverter().getURIMap().put(key, value);
	}

	/** Load and register source and target metamodels */
	public void registerMetamodels(ResourceSet rs, IbexExecutable executable) throws IOException {
		
		// Set correct workspace root
		setWorkspaceRootDirectory(rs);
		
		// Load and register source and target metamodels
		EPackage familiesPack = null;
		EPackage personsPack = null;
		EPackage familiestopersonsibextggPack = null;
		
		if(executable instanceof FWD_OPT) {
			Resource res = executable.getResourceHandler().loadResource("platform:/resource/PersonsSmartEMF/model/PersonsSmartEMF.ecore");
			personsPack = (EPackage) res.getContents().get(0);
			rs.getResources().remove(res);
			
			res = executable.getResourceHandler().loadResource("platform:/resource/FamiliesToPersonsIBeXTGG/model/FamiliesToPersonsIBeXTGG.ecore");
			familiestopersonsibextggPack = (EPackage) res.getContents().get(0);
			rs.getResources().remove(res);
		}
				
		if(executable instanceof BWD_OPT) {
			Resource res = executable.getResourceHandler().loadResource("platform:/resource/FamiliesSmartEMF/model/FamiliesSmartEMF.ecore");
			familiesPack = (EPackage) res.getContents().get(0);
			rs.getResources().remove(res);
			
			res = executable.getResourceHandler().loadResource("platform:/resource/FamiliesToPersonsIBeXTGG/model/FamiliesToPersonsIBeXTGG.ecore");
			familiestopersonsibextggPack = (EPackage) res.getContents().get(0);
			rs.getResources().remove(res);
		}

		if(familiesPack == null)
			familiesPack = FamiliesSmartEMFPackageImpl.init();
				
		if(personsPack == null)
			personsPack = PersonsSmartEMFPackageImpl.init();
		
		if(familiestopersonsibextggPack == null) {
			familiestopersonsibextggPack = FamiliesToPersonsIBeXTGGPackageImpl.init();
			rs.getPackageRegistry().put("platform:/resource/FamiliesToPersonsIBeXTGG/model/FamiliesToPersonsIBeXTGG.ecore", FamiliesToPersonsIBeXTGGPackage.eINSTANCE);
			rs.getPackageRegistry().put("platform:/plugin/FamiliesToPersonsIBeXTGG/model/FamiliesToPersonsIBeXTGG.ecore", FamiliesToPersonsIBeXTGGPackage.eINSTANCE);
		}
			
		rs.getPackageRegistry().put("platform:/resource/FamiliesSmartEMF/model/FamiliesSmartEMF.ecore", familiesPack);
	    rs.getPackageRegistry().put("platform:/plugin/FamiliesSmartEMF/model/FamiliesSmartEMF.ecore", familiesPack);	
			
		rs.getPackageRegistry().put("platform:/resource/PersonsSmartEMF/model/PersonsSmartEMF.ecore", personsPack);
		rs.getPackageRegistry().put("platform:/plugin/PersonsSmartEMF/model/PersonsSmartEMF.ecore", personsPack);
	}

	/** Create default options **/
	public IbexOptions createIbexOptions() {
		IbexOptions options = new IbexOptions();
		options.blackInterpreter(new HiPETGGEngine());
		options.project.name("FamiliesToPersonsIBeXTGG");
		options.project.path("FamiliesToPersonsIBeXTGG");
		options.debug.ibexDebug(false);
		options.csp.registerConstraintFactories(new RuntimeTGGAttrConstraintFactoryContainer().getFactories());
		options.registrationHelper(this);
		return options;
	}
}
