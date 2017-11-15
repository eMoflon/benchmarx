package org.benchmarx.examples.ecore2sql.testsuite;

import static org.benchmarx.util.EMFUtil.loadExpectedModel;

import java.io.IOException;

import org.benchmarx.BXTool;
import org.benchmarx.util.BenchmarxUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import sql.Schema;

public class BenchmarxUtilForEcoreToSQL extends BenchmarxUtil<EPackage, Schema, Decisions> {
	
	private BXTool<EPackage, Schema, Decisions> tool;

	public BenchmarxUtilForEcoreToSQL(BXTool<EPackage, Schema, Decisions> tool) {
		super(tool);
		this.tool = tool;
	}
	
	@Override
	public void assertPrecondition(String srcPath, String trgPath){
		tool.assertPrecondition(loadExpectedEcoreModel(srcPath), loadExpectedModel(trgPath));
	}
	
	@Override
	public void assertPostcondition(String srcPath, String trgPath){
		tool.assertPostcondition(loadExpectedEcoreModel(srcPath), loadExpectedModel(trgPath));
	}
	
	public static Resource loadExpectedEcoreResource(String path, ResourceSet resourceSet){
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		Resource resource = resourceSet.createResource(URI.createFileURI("resources/" + path + ".ecore"));
		try {
			resource.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}
	
	@SuppressWarnings("unchecked")
	public static <M> M loadExpectedEcoreModel(String path, ResourceSet resourceSet) {
		return (M)loadExpectedEcoreResource(path, resourceSet).getContents().get(0);
	}
	
	public static <M> M loadExpectedEcoreModel(String path){
		ResourceSet resourceSet = new ResourceSetImpl();
		return loadExpectedEcoreModel(path, resourceSet);
	}

}
