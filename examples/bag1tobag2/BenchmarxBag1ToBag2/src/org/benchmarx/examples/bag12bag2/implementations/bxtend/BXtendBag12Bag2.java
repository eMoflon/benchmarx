package org.benchmarx.examples.bag12bag2.implementations.bxtend;

import java.io.IOException;
import java.util.function.Consumer;

import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.bag12bag2.testsuite.Decisions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.ubt.ai1.m2m.bag12bag2.rules.Bag12bag2Transformation;
import org.benchmarx.bags1.core.Bag1Comparator;
import org.benchmarx.bags2.core.Bag2Comparator;

public class BXtendBag12Bag2 extends BXToolForEMF<bags1.MyBag, bags2.MyBag, Decisions> {

	private ResourceSet set = new ResourceSetImpl();
	private Resource source;
	private Resource target;
	private Resource corr;
	private Bag12bag2Transformation set2oset;
	
	private static final String RESULTPATH = "results/BXtend";
	
	public BXtendBag12Bag2() {
		super(new Bag1Comparator(), new Bag2Comparator());
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
		
		source = set.createResource(URI.createURI("bag1.xmi"));
		target = set.createResource(URI.createURI("bag2.xmi"));
		corr = set.createResource(URI.createURI("corr.xmi"));
		bags1.MyBag root = bags1.Bags1Factory.eINSTANCE.createMyBag();
		source.getContents().add(root);
		set2oset = new Bag12bag2Transformation(source, target, corr);
				
		// perform batch to establish consistent starting state
		set2oset.sourceToTarget();
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<bags1.MyBag> edit) {
		edit.accept(getSourceModel());
		set2oset.sourceToTarget();
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<bags2.MyBag> edit) {
		edit.accept(getTargetModel());
		set2oset.targetToSource();
	}

	@Override
	public void performIdleSourceEdit(Consumer<bags1.MyBag> edit) {
		edit.accept(getSourceModel());
	}

	@Override
	public void performIdleTargetEdit(Consumer<bags2.MyBag> edit) {
		edit.accept(getTargetModel());
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {

	}

	@Override
	public bags1.MyBag getSourceModel() {
		return (bags1.MyBag) source.getContents().get(0);
	}

	@Override
	public bags2.MyBag getTargetModel() {
		return (bags2.MyBag) target.getContents().get(0);
	}

	@Override
	public void saveModels(String name) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "bag1.xmi");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "bag2.xmi");
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
