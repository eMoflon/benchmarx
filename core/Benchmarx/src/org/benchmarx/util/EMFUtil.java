package org.benchmarx.util;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class EMFUtil {
	
	public static Resource loadExpectedResource(String path, ResourceSet resourceSet){
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
	      
		Resource resource = resourceSet.createResource(URI.createFileURI("resources/" + path + ".xmi"));
		try {
			resource.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}
	
	@SuppressWarnings("unchecked")
	public static <M> M loadExpectedModel(String path, ResourceSet resourceSet) {
		return (M)loadExpectedResource(path, resourceSet).getContents().get(0);
	}
	
	public static <M> M loadExpectedModel(String path){
		ResourceSet resourceSet = new ResourceSetImpl();
		return loadExpectedModel(path, resourceSet);
	}
}
