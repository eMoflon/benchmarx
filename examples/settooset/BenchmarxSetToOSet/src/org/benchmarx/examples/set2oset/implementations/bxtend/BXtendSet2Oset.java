package org.benchmarx.examples.set2oset.implementations.bxtend;

import java.io.IOException;
import java.util.function.Consumer;

import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.set2oset.testsuite.Decisions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.benchmarx.osets.core.OsetComparator;
import de.ubt.ai1.m2m.set2oset.rules.Set2osetTransformation;
import org.benchmarx.sets.core.SetComparator;
import sets.SetsFactory;

public class BXtendSet2Oset extends BXToolForEMF<sets.MySet, osets.MyOrderedSet, Decisions> {
	private ResourceSet set = new ResourceSetImpl();
	private Resource source;
	private Resource target;
	private Resource corr;
	private Set2osetTransformation set2oset;
	
//	private Configurator<Decisions> conf;
	
	private static final String RESULTPATH = "results/BXtend";
	
	public BXtendSet2Oset() {
		super(new SetComparator(), new OsetComparator());
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
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());		
				
		source = set.createResource(URI.createURI("set.xmi"));
		target = set.createResource(URI.createURI("oset.xmi"));
		corr = set.createResource(URI.createURI("corr.xmi"));
		sets.MySet root = SetsFactory.eINSTANCE.createMySet();
		source.getContents().add(root);
		set2oset = new Set2osetTransformation(source, target, corr);
				
		// perform batch to establish consistent starting state
		set2oset.sourceToTarget();
		
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<sets.MySet> edit) {
		edit.accept(getSourceModel());
		set2oset.sourceToTarget();
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<osets.MyOrderedSet> edit) {
		edit.accept(getTargetModel());
		set2oset.targetToSource();		
	}

	@Override
	public void performIdleSourceEdit(Consumer<sets.MySet> edit) {
		edit.accept(getSourceModel());
	}

	@Override
	public void performIdleTargetEdit(Consumer<osets.MyOrderedSet> edit) {
		edit.accept(getTargetModel());
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		// conf = configurator;
	}

	@Override
	public sets.MySet getSourceModel() {
		return (sets.MySet) source.getContents().get(0);
	}

	@Override
	public osets.MyOrderedSet getTargetModel() {
		return (osets.MyOrderedSet) target.getContents().get(0);
	}

	@Override
	public void saveModels(String name) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "set.xmi");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "oset.xmi");
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
