package org.benchmarx.examples.familiestopersons.implementations.nmf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.Supplier;

import org.benchmarx.BXTool;
import org.benchmarx.config.Configurator;
import org.benchmarx.edit.IEdit;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Assert;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsFactory;

/**
 * The NMF solution basically starts a co-program and communicates with it through stdin/stdout plus temporary files
 * 
 * @author Georg Hinkel
 */
public class NMFFamiliesToPersonsIncremental implements BXTool<FamilyRegister, PersonRegister, Decisions> {
	private static final String NMF_EXE = "../implementationArtefacts/NMF/bin/NMFSolution.exe";
	private FamilyRegister src;
	private PersonRegister trg;
	private FamiliesComparator srcHelper = new FamiliesComparator();
	private PersonsComparator trgHelper = new PersonsComparator();
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private long propagation;
	private long includingSerialization;
	
	private Configurator<Decisions> configurator;
	
	@Override
	public String getName() {
		return "NMF";
	}
	
	@Override
	public void initiateSynchronisationDialogue() {
		src = FamiliesFactory.eINSTANCE.createFamilyRegister();
		trg = PersonsFactory.eINSTANCE.createPersonRegister();
		
		configurator = new Configurator<Decisions>();
		runNMF();
		
		propagation = 0;
		includingSerialization = 0;
	}

	@Override
	public void performAndPropagateTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		long start = System.nanoTime();
		setUpdatePolicy();
		ChangeRecorder recorder = new ChangeRecorder();

		//TODO[Georg Hinkel:  Hopefully this will no longer be necessary with the new explicit delta structure ]
		recorder.observePersonsRegister(trg);
		edit.get();		
		//recorder.observeMiniYaml(trg);
		//edit.accept(trg);
		
		long actual = propagate(recorder);
		src = readModel("SaveFamilies");
		
		long end = System.nanoTime();
		
		propagation += actual;
		includingSerialization += (end - start);
	}
	
	public long getRunningTimeInNanoSeconds() {
		return propagation;
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
	public void performAndPropagateSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		long start = System.nanoTime();
		setUpdatePolicy();
		ChangeRecorder recorder = new ChangeRecorder();
		recorder.observeFamilyRegister(src);
		edit.get();
		long actual = propagate(recorder);
		trg = readModel("SavePersons");
		
		long end = System.nanoTime();
		
		propagation += actual;
		includingSerialization += (end - start);
	}
	
	@Override
	public void assertPostcondition(FamilyRegister fr, PersonRegister pr) {
		this.assertPrecondition(fr, pr);
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
	
	private void normaliseAndCompare(String expected, String actual) {
		Assert.assertEquals(expected.replaceAll("\\s+",""), actual.replaceAll("\\s+",""));
	}

	private void runNMF() {
		try {
			File pathToExecutable = new File(NMF_EXE);
			ProcessBuilder processBuilder = new ProcessBuilder(pathToExecutable.getAbsoluteFile().toString());
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

	private void setUpdatePolicy() {
		try {
			boolean b = configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD);
			boolean e = configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW);
			
			writer.write("SetPreferExistingFamilies ");
			writer.write(Boolean.toString(e));
			writer.write("\n");
			writer.flush();
			reader.readLine();
			writer.write("SetPreferCreateParents ");
			writer.write(Boolean.toString(b));
			writer.write("\n");
			writer.flush();
			reader.readLine();
		} catch (IllegalArgumentException e) {
			// swallow this exception. no decision made yet
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Override
	public void assertPrecondition(FamilyRegister source, PersonRegister target) {
		String resultSrc = srcHelper.familyToString(readModel("SaveFamilies"));
		String resultTrg = trgHelper.personsToString(readModel("SavePersons"));
		
		String expectedFamilyRegister = srcHelper.familyToString(source);
		String expectedPersonsRegister = trgHelper.personsToString(target);
		
		normaliseAndCompare(expectedFamilyRegister, resultSrc);
		normaliseAndCompare(expectedPersonsRegister, resultTrg);
	}
	
	private <M> M readModel(String command) {
		try {
			File tempFile = File.createTempFile("model", ".xmi");
			writer.write(command);
			writer.write(" ");
			writer.write(tempFile.getAbsolutePath());
			writer.write("\n");
			writer.flush();
			reader.readLine();
			M result = loadModel(tempFile.getAbsolutePath());
			tempFile.delete();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
			return null;
		}
	}
	
	private <M> M loadModel(String path){
		ResourceSet resourceSet = new ResourceSetImpl();
		return loadModel(path, resourceSet);
	}
	
	@SuppressWarnings("unchecked")
	private <M> M loadModel(String path, ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
	      
		Resource resource = resourceSet.createResource(URI.createFileURI(path));
		try {
			resource.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (M)resource.getContents().get(0);
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		this.configurator = configurator;
	}	
	
	public void saveModels(String name) {
		
	}
	
	@Override
	public void performIdleTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		this.performAndPropagateTargetEdit(edit);
	}

	@Override
	public void performIdleSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		this.performAndPropagateSourceEdit(edit);
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEdit,
			Supplier<IEdit<PersonRegister>> targetEdit) {
		throw new UnsupportedOperationException("Concurrent edits not supported.");		
	}

	@Override
	public FamilyRegister getSourceModel() {
		return src;
	}

	@Override
	public PersonRegister getTargetModel() {
		return trg;
	}
}

