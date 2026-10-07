package fr.liglab.bcert.ttc.familiespersons;


import pivot.PivotPackage;
import Families.FamiliesPackage;
import Persons.PersonsPackage;

import fr.lig.vasco.animation.Interpreter;

import fr.lig.vasco.animation.Execution;
import fr.lig.vasco.animation.SearchStrategy;

import pivot.Pivot;
import fr.lig.vasco.animation.Extent;
import fr.lig.vasco.animation.mapping.Mapped;
import fr.lig.vasco.animation.mapping.ModelSynchronizer;

import fr.lig.vasco.becore.Trace;
import fr.liglab.vasco.models.ctm.trace.TracePackage;
import fr.liglab.vasco.models.bmethod.BMethodPackage;

import fr.lig.vasco.animation.prob.ModelLoader;
import fr.lig.vasco.animation.prob.ProBPlatform;


import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.Resource;
import fr.liglab.vasco.models.bmethod.util.BMethodResourceFactoryImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;


import java.net.URL;
import java.nio.file.Path;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class FamiliesToPersonsTransformation {
	
	
	private final Interpreter transformation;
	
	public FamiliesToPersonsTransformation() throws Exception {
		
		Path machines = Files.createDirectories(Files.createTempDirectory("ttc").resolve("familiespersons"));
		
		loadResourceFromClasspath(machines, "FamiliesToPersonsTransformation.mch");
		loadResourceFromClasspath(machines, "definitions.def");
		loadResourceFromClasspath(machines, "BatchBwd.def");
		loadResourceFromClasspath(machines, "LibraryStrings.def");
		loadResourceFromClasspath(machines, "match.def");

		loadResourceFromClasspath(machines,"FamiliesToPersonsTransformationDriver.mch");
		loadResourceFromClasspath(machines,"concurrent.def");
		
		Path machine 		= machines.resolve("FamiliesToPersonsTransformationDriver.mch");
		EPackage metamodel	= PivotPackage.eINSTANCE;
		URL mapping			= FamiliesToPersonsTransformation.class.getResource("pivot.ecore.bmethod");
		
		transformation 		= getInterpreter(machine,metamodel,loadMapping(URI.createURI(mapping.toURI().toString())));
		
	}

	
	public void apply(Pivot pivot, int expectedFamilies, int expectedMembers, int expectedPersons) throws Exception {
		
		Extent input = Extent.of(pivot.eResource(),true);
		
		Map<Interpreter.Option,String> preferences = new HashMap<>();
		preferences.put(ModelLoader.Property.MAX_OPERATIONS, "1");
		preferences.put(ModelLoader.Property.DEFAULT_SETSIZE, "10");

		Map<String,Integer> cardinalities = new HashMap<>();
		
		cardinalities.put(transformation.getMetamodelMapping().intentFor(PivotPackage.Literals.PIVOT).getName(),1);
		cardinalities.put(transformation.getMetamodelMapping().intentFor(PersonsPackage.Literals.PERSON_REGISTER).getName(),1);
		cardinalities.put(transformation.getMetamodelMapping().intentFor(FamiliesPackage.Literals.FAMILY_REGISTER).getName(),1);
		cardinalities.put(transformation.getMetamodelMapping().intentFor(PivotPackage.Literals.MAPPING).getName(),expectedMembers+expectedPersons);

		cardinalities.put(transformation.getMetamodelMapping().intentFor(FamiliesPackage.Literals.FAMILY).getName(),expectedFamilies);
		cardinalities.put(transformation.getMetamodelMapping().intentFor(FamiliesPackage.Literals.FAMILY_MEMBER).getName(),expectedMembers);

		cardinalities.put(transformation.getMetamodelMapping().intentFor(PersonsPackage.Literals.PERSON).getName(),expectedPersons);

		Mapped<Execution> animator 		= transformation.execute("families to persons",input,cardinalities,preferences);
		ModelSynchronizer synchronizer 	= new ModelSynchronizer(animator.getModelMapping());
		
		Execution animation	= animator.get();
		animation.keepHistory(false);
		
		/*
		 * Animate randomly until deadlock
		 */
		animation.run(SearchStrategy.RANDOM);
		
		/*
		 * Synchronize model only at the end of the animation (to avoid overhead of partial updates) and
		 * kill the ProB instance
		 */
		animation.addListener(synchronizer);			
		animation.stop();
		
	}
	
	
	private static ProBPlatform PlatformProB = new ProBPlatform();

	private static Interpreter getInterpreter(Path machine, EPackage metamodel, Trace mapping) throws Exception {	
		return PlatformProB.getInperpreter(URI.createFileURI(machine.toFile().getAbsolutePath()),metamodel,mapping);
	}
	
	private static Trace loadMapping(URI resource) throws Exception {
		
		ResourceSetImpl sandbox	= new ResourceSetImpl();
		
		/*
		 * Normalize all references in the trace so that we do not load in memory several versions of
		 * the packages
		 */
		new ResourceSetImpl.ResourceLocator(sandbox) {
			
			@Override
			public Resource getResource(URI uri, boolean loadOnDemand) {
				
				if (uri.lastSegment().equals("pivot.ecore"))
					return PivotPackage.eINSTANCE.eResource();

				if (uri.lastSegment().equals("Families.ecore"))
					return FamiliesPackage.eINSTANCE.eResource();

				if (uri.lastSegment().equals("Persons.ecore"))
					return PersonsPackage.eINSTANCE.eResource();
				
				return basicGetResource(uri,loadOnDemand);
			}
		};
		
		sandbox.getPackageRegistry().put(BMethodPackage.eNS_URI, BMethodPackage.eINSTANCE);
		sandbox.getPackageRegistry().put(TracePackage.eNS_URI, TracePackage.eINSTANCE);
		
		sandbox.getResourceFactoryRegistry().getExtensionToFactoryMap().put("bmethod", new BMethodResourceFactoryImpl());
		
		
		return Trace.of(content(sandbox.getResource(resource,true),BMethodPackage.Literals.BSPEC));
	}

	private static void loadResourceFromClasspath(Path directory, String name) throws IOException {
		URL 	source	= FamiliesToPersonsTransformation.class.getResource(name);
		Files.copy(source.openStream(), directory.resolve(name), StandardCopyOption.REPLACE_EXISTING);		
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T content(Resource resource, EClassifier kind) {
		return (T) EcoreUtil.getObjectByType(resource.getContents(),kind);
	}

}
