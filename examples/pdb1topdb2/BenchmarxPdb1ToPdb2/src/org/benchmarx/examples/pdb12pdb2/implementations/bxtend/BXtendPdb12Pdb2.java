package org.benchmarx.examples.pdb12pdb2.implementations.bxtend;

import java.io.IOException;
import java.util.function.Consumer;

import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.pdb12pdb2.testsuite.Decisions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.benchmarx.pdb1.core.Pdb1Comparator;
import org.benchmarx.pdb2.core.Pdb2Comparator;
import de.ubt.ai1.m2m.pdb12pdb2.rules.Pdb12pdb2Transformation;
import de.ubt.ai1.m2m.pdb12pdb2.rules.decisions.ConfigurableTargetToSourceDecision;
import pdb1.Pdb1Factory;

public class BXtendPdb12Pdb2 extends BXToolForEMF<pdb1.Database, pdb2.Database, Decisions> {

	private ResourceSet set = new ResourceSetImpl();
	private Resource source;
	private Resource target;
	private Resource corr;
	private Pdb12pdb2Transformation pdb12pdb2t;
	
	private Configurator<Decisions> conf;
	private Configurator<Decisions> defaultConf;
	
	private static final String RESULTPATH = "results/BXtend";
	
	public BXtendPdb12Pdb2() {
		super(new Pdb1Comparator(), new Pdb2Comparator());
	}
	
	@Override
	public String getName() {
		return "BXtend";
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public void initiateSynchronisationDialogue() {
		// Fix default preferences (which can be overwritten)
		setConfigurator(new Configurator<Decisions>()
				.makeDecision(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST, false));
				
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());		
				
		source = set.createResource(URI.createURI("pdb1.xmi"));
		target = set.createResource(URI.createURI("pdb2.xmi"));
		corr = set.createResource(URI.createURI("corr.xmi"));
		pdb1.Database familiesRoot = Pdb1Factory.eINSTANCE.createDatabase();
		source.getContents().add(familiesRoot);
		pdb12pdb2t = new Pdb12pdb2Transformation(source, target, corr);
				
		// perform batch to establish consistent starting state
		pdb12pdb2t.sourceToTarget();
		
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<pdb1.Database> edit) {
		edit.accept(getSourceModel());
		pdb12pdb2t.sourceToTarget();
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<pdb2.Database> edit) {
		edit.accept(getTargetModel());
		try{
			if(conf.decide(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST)) {
				pdb12pdb2t.configure(new ConfigurableTargetToSourceDecision(1));
			} else {
				pdb12pdb2t.configure(new ConfigurableTargetToSourceDecision(-1));
			}
		} catch(IllegalArgumentException e) {
			if(defaultConf.decide(Decisions.PREFER_USING_FIRST_SPACE_TO_LAST)) {
				pdb12pdb2t.configure(new ConfigurableTargetToSourceDecision(1));
			} else {
				pdb12pdb2t.configure(new ConfigurableTargetToSourceDecision(-1));
			}
		}
		pdb12pdb2t.targetToSource();
		
	}

	@Override
	public void performIdleSourceEdit(Consumer<pdb1.Database> edit) {
		edit.accept(getSourceModel());
	}

	@Override
	public void performIdleTargetEdit(Consumer<pdb2.Database> edit) {
		edit.accept(getTargetModel());
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		if(defaultConf == null)
			defaultConf = configurator;
		conf = configurator;
	}

	@Override
	public pdb1.Database getSourceModel() {
		return (pdb1.Database) source.getContents().get(0);
	}

	@Override
	public pdb2.Database getTargetModel() {
		return (pdb2.Database) target.getContents().get(0);
	}

	@Override
	public void saveModels(String name) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "pdb1.xmi");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "pdb2.xmi");
		Resource resSource = set.createResource(srcURI);
		Resource resTarget = set.createResource(trgURI);
		
		EObject colSource = EcoreUtil.copy(getSourceModel());
		EObject colTarget = EcoreUtil.copy(getTargetModel());
		
		resSource.getContents().add(colSource);
		resTarget.getContents().add(colTarget);
		
		try {
			resSource.save(null);
			resTarget.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
