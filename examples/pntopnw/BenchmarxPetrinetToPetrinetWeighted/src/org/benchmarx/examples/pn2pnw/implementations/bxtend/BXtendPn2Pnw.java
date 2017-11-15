package org.benchmarx.examples.pn2pnw.implementations.bxtend;

import java.io.IOException;
import java.util.function.Consumer;

import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.pn2pnw.testsuite.Decisions;
import org.benchmarx.petrinetweighted.core.PNWComparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.benchmarx.petrinet.core.PNComparator;
import de.ubt.ai1.m2m.pn2pnw.rules.Pn2pnwTransformation;

public class BXtendPn2Pnw extends BXToolForEMF<pn.Net, pnw.Net, Decisions> {

	private ResourceSet set = new ResourceSetImpl();
	private Resource source;
	private Resource target;
	private Resource corr;
	private Pn2pnwTransformation pn2pnw;
	
	private static final String RESULTPATH = "results/BXtend";
	
	public BXtendPn2Pnw() {
		super(new PNComparator(), new PNWComparator());
	}
	
	@Override
	public String getName() {
		return "BXtend";
	}

	@Override
	public void initiateSynchronisationDialogue() {
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());		
		
		source = set.createResource(URI.createURI("pn.xmi"));
		target = set.createResource(URI.createURI("pnw.xmi"));
		corr = set.createResource(URI.createURI("corr.xmi"));
		pn.Net root = pn.PnFactory.eINSTANCE.createNet();
		source.getContents().add(root);
		pn2pnw = new Pn2pnwTransformation(source, target, corr);
				
		// perform batch to establish consistent starting state
		pn2pnw.sourceToTarget();
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<pn.Net> edit) {
		edit.accept(getSourceModel());
		pn2pnw.sourceToTarget();
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<pnw.Net> edit) {
		edit.accept(getTargetModel());
		pn2pnw.targetToSource();
	}

	@Override
	public void performIdleSourceEdit(Consumer<pn.Net> edit) {
		edit.accept(getSourceModel());
	}

	@Override
	public void performIdleTargetEdit(Consumer<pnw.Net> edit) {
		edit.accept(getTargetModel());
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {

	}

	@Override
	public pn.Net getSourceModel() {
		return (pn.Net) source.getContents().get(0);
	}

	@Override
	public pnw.Net getTargetModel() {
		return (pnw.Net) target.getContents().get(0);
	}

	@Override
	public void saveModels(String name) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "ast.xmi");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "dag.xmi");
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
