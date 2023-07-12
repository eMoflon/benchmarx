package containerstominiyaml.testsuite;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class EMFUtil {
	
	public static Resource loadExpectedResource(String path, ResourceSet resourceSet){
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
	      
//Artur		Path relativePath = FileSystems.getDefault().getPath("resources", path + ".xmi");
		Path relativePath = FileSystems.getDefault().getPath("../../BenchmarxContainersToMiniYAML/resources", path + ".xmi");
		Path absolutePath = relativePath.normalize();
		Resource resource = resourceSet.createResource(URI.createFileURI(absolutePath.toString()));
		try {
			resource.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}
	
	@SuppressWarnings("unchecked")
	public static <M> M loadExpectedModel(String path, ResourceSet resourceSet) {
		Resource r = loadExpectedResource(path, resourceSet);
		M model = (M) r.getContents().get(0);
		resourceSet.getResources().remove(r);
		return model;
	}
	
	/** Consider supplying a configured resource set and use {@link #loadExpectedModel(String, ResourceSet)} instead */
	public static <M> M loadExpectedModel(String path){
		ResourceSet resourceSet = new ResourceSetImpl();
		return loadExpectedModel(path, resourceSet);
	}
}
