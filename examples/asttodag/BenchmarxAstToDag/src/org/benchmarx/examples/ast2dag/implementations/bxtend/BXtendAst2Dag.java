package org.benchmarx.examples.ast2dag.implementations.bxtend;

import java.io.IOException;
import java.util.function.Consumer;

import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.ast2dag.testsuite.Decisions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.benchmarx.ast.core.AstComparator;
import de.ubt.ai1.m2m.ast2dag.rules.Ast2dagTransformation;
import org.benchmarx.dag.core.DagComparator;

public class BXtendAst2Dag extends BXToolForEMF<ast.Model, dag.Model, Decisions> {

	private ResourceSet set = new ResourceSetImpl();
	private Resource source;
	private Resource target;
	private Resource corr;
	private Ast2dagTransformation ast2dag;
	
	private static final String RESULTPATH = "results/BXtend";
	
	public BXtendAst2Dag() {
		super(new AstComparator(), new DagComparator());
	}
	
	@Override
	public String getName() {
		return "BXtend";
	}

	@Override
	public void initiateSynchronisationDialogue() {
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());		
		
		source = set.createResource(URI.createURI("ast.xmi"));
		target = set.createResource(URI.createURI("dag.xmi"));
		corr = set.createResource(URI.createURI("corr.xmi"));
		ast.Model root = ast.AstFactory.eINSTANCE.createModel();
		source.getContents().add(root);
		ast2dag = new Ast2dagTransformation(source, target, corr);
				
		// perform batch to establish consistent starting state
		ast2dag.sourceToTarget();
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<ast.Model> edit) {
		edit.accept(getSourceModel());
		ast2dag.sourceToTarget();
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<dag.Model> edit) {
		edit.accept(getTargetModel());
		ast2dag.targetToSource();
	}

	@Override
	public void performIdleSourceEdit(Consumer<ast.Model> edit) {
		edit.accept(getSourceModel());
	}

	@Override
	public void performIdleTargetEdit(Consumer<dag.Model> edit) {
		edit.accept(getTargetModel());
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {

	}

	@Override
	public ast.Model getSourceModel() {
		return (ast.Model) source.getContents().get(0);
	}

	@Override
	public dag.Model getTargetModel() {
		return (dag.Model) target.getContents().get(0);
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
