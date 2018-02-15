package org.benchmarx.examples.ecore2sql.implementations.bxtend;

import java.io.IOException;
import java.util.function.Consumer;

import org.benchmarx.Configurator;
import org.benchmarx.ecore.core.EcoreComparator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.ecore2sql.testsuite.Decisions;
import org.benchmarx.sql.core.SQLComparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.ubt.ai1.m2m.ecore2sql.rules.Ecore2sqlTransformation;
import sql.Schema;

public class BXtendEcore2SQL extends BXToolForEMF<EPackage, Schema, Decisions> {

	private ResourceSet set = new ResourceSetImpl();
	private Resource source;
	private Resource target;
	private Resource corr;
	private Ecore2sqlTransformation ecore2sql;
	
	private static final String RESULTPATH = "results/BXtend";
	
	public BXtendEcore2SQL() {
		super(new EcoreComparator(), new SQLComparator());
	}
	
	@Override
	public String getName() {
		return "BXtend";
	}

	@Override
	public void initiateSynchronisationDialogue() {
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		
		source = set.createResource(URI.createURI("ecore.ecore"));
		target = set.createResource(URI.createURI("sql.xmi"));
		corr = set.createResource(URI.createURI("corr.xmi"));
		EPackage root = EcoreFactory.eINSTANCE.createEPackage();
		source.getContents().add(root);
		ecore2sql = new Ecore2sqlTransformation(source, target, corr);
				
		// perform batch to establish consistent starting state
		ecore2sql.sourceToTarget();
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<EPackage> edit) {
		edit.accept(getSourceModel());
		ecore2sql.sourceToTarget();
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<Schema> edit) {
		edit.accept(getTargetModel());
		ecore2sql.targetToSource();
	}

	@Override
	public void performIdleSourceEdit(Consumer<EPackage> edit) {
		edit.accept(getSourceModel());
	}

	@Override
	public void performIdleTargetEdit(Consumer<Schema> edit) {
		edit.accept(getTargetModel());
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		
	}

	@Override
	public EPackage getSourceModel() {
		return (EPackage) source.getContents().get(0);
	}

	@Override
	public Schema getTargetModel() {
		return (Schema) target.getContents().get(0);
	}

	@Override
	public void saveModels(String name) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".ecore", new EcoreResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "ecore.ecore");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "sql.xmi");
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
