package org.benchmarx.examples.containerstominiyaml.implementations.nmf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.Consumer;

import org.benchmarx.Configurator;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.containerstominiyaml.comparators.CompositionComparator;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Assert;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import containers.Composition;
import containers.ContainersFactory;

/**
 * The NMF solution basically starts a co-program and communicates with it through stdin/stdout plus temporary files
 * 
 * @author Georg Hinkel
 */
public class NMFContainersToMiniYaml extends BXToolForEMF<Composition, miniyaml.Map, Decisions> {
	private static final String NMF_EXE = "../implementationArtefacts/nmf/bin/NMFSolution.dll";
    
	private final String name;
	private Resource source;
	private Resource target;

	private Configurator<Decisions> conf;
	private Configurator<Decisions> defaultConf;
	
	private static final String RESULTPATH = "results/nmf";
	
	public NMFContainersToMiniYaml(String name, Comparator<miniyaml.Map> yamlComparator) {
		super(new CompositionComparator(), yamlComparator);
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
    }

	private static final ResourceSet createResourceSet() {
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
			Resource.Factory.Registry.DEFAULT_EXTENSION,
			new XMIResourceFactoryImpl()
		);
		return rs;
	}
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private long propagation;
	private long includingSerialization;
	
	private Configurator<Decisions> configurator;
	
	@Override
	public void initiateSynchronisationDialogue() {
		try {
        // Fix default preferences (which can be overwritten)
		setConfigurator(new Configurator<Decisions>());		
		File fTempSource = File.createTempFile("sourceModel", ".containers");
		fTempSource.deleteOnExit();
		source = createResourceSet().createResource(URI.createFileURI(fTempSource.getPath()));

		File fTempTarget = File.createTempFile("targetModel", ".miniyaml");
		fTempTarget.deleteOnExit();
		target = createResourceSet().createResource(URI.createFileURI(fTempTarget.getPath()));

		Composition compositionRoot = ContainersFactory.eINSTANCE.createComposition();
		source.getContents().add(compositionRoot);
        clearAdapters(source);
        clearAdapters(target);

		// Fix default preferences (which can be overwritten)
		setConfigurator(new Configurator<Decisions>());
		runNMF();
		
		propagation = 0;
		includingSerialization = 0;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void clearAdapters(Resource r) {
		r.eAdapters().clear();
		for (TreeIterator<EObject> it = r.getAllContents(); it.hasNext(); ) {
			it.next().eAdapters().clear();
		}
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<miniyaml.Map> edit) {
		long start = System.nanoTime();
        ChangeRecorder recorder = new ChangeRecorder();
        miniyaml.Map trg = getTargetModel();
		recorder.observeMiniYaml(trg);
		edit.accept(trg);
		long actual = propagate(recorder);
		source = readModel("SaveContainer");
		
		long end = System.nanoTime();
		
		propagation += actual;
		includingSerialization += (end - start);
	}
	
	public long getRunningTimeInNanoSeconds() {
		return propagation;
	}

	@Override
	public Composition getSourceModel() {
		return (Composition) source.getContents().get(0);
	} 

	@Override
	public miniyaml.Map getTargetModel() {
		return (miniyaml.Map) target.getContents().get(0);
	}

	@SuppressWarnings("deprecation")
	private long propagate(ChangeRecorder recorder) {
		String changes = recorder.stopAndExport();
		try {
			File tempFile = File.createTempFile("change", ".xmi");
			Files.write(changes, tempFile, Charsets.UTF_8);
			writer.write("Propagate ");
			writer.write(tempFile.getAbsolutePath());
			writer.write("\n");
			writer.flush();
			String result = reader.readLine();
			tempFile.delete();
			return Long.parseLong(result);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
			return 0;
		}
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<Composition> edit) {
		long start = System.nanoTime();
        ChangeRecorder recorder = new ChangeRecorder();
        Composition src = getSourceModel();
		recorder.observeComposition(src);
		edit.accept(src);
		long actual = propagate(recorder);
		target = readModel("SaveYaml");
		
		long end = System.nanoTime();
		
		propagation += actual;
		includingSerialization += (end - start);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void terminateSynchronisationDialogue() {
		try {
			Files.append(String.format("%d;%d\n", this.includingSerialization, this.propagation), new File("nmfresults.csv"), Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.write("exit\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private void runNMF() {
		try {
			File pathToExecutable = new File(NMF_EXE);
			ProcessBuilder processBuilder = new ProcessBuilder("dotnet", pathToExecutable.getAbsoluteFile().toString());
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			
			OutputStream stdin = process.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(stdin));
			
			InputStream stdout = process.getInputStream();
			reader = new BufferedReader(new InputStreamReader(stdout));
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	private Resource readModel(String command) {
		try {
			File tempFile = File.createTempFile("model", ".xmi");
			writer.write(command);
			writer.write(" ");
			writer.write(tempFile.getAbsolutePath());
			writer.write("\n");
			writer.flush();
			reader.readLine();
			Resource result = loadModel(tempFile.getAbsolutePath());
			tempFile.delete();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
			return null;
		}
	}
	
	private Resource loadModel(String path){
		ResourceSet resourceSet = new ResourceSetImpl();
		return loadModel(path, resourceSet);
	}
	
	@SuppressWarnings("unchecked")
	private Resource loadModel(String path, ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
	      
		Resource resource = resourceSet.createResource(URI.createFileURI(path));
		try {
			resource.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		this.configurator = configurator;
	}	
	
	@Override
	public void saveModels(String name) {
		ResourceSet set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		URI srcURI = URI.createFileURI(RESULTPATH + "/" + name + "Composition.xmi");
		URI trgURI = URI.createFileURI(RESULTPATH + "/" + name + "MiniYAML.xmi");
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
	
	@Override
	public void performIdleTargetEdit(Consumer<miniyaml.Map> edit) {
		this.performAndPropagateTargetEdit(edit);
	}

	@Override
	public void performIdleSourceEdit(Consumer<Composition> edit) {
		this.performAndPropagateSourceEdit(edit);
	}
}

