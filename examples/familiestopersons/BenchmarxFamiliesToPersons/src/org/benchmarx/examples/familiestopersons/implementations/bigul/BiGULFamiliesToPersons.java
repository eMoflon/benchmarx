package org.benchmarx.examples.familiestopersons.implementations.bigul;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.benchmarx.BXTool;
import org.benchmarx.Configurator;
import org.benchmarx.examples.familiestopersons.families.core.FamiliesComparator;
import org.benchmarx.examples.familiestopersons.persons.core.PersonsComparator;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.junit.Assert;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsFactory;

public class BiGULFamiliesToPersons implements BXTool<FamilyRegister, PersonRegister, Decisions> {
	private static final String BIGUL_EXE = "src/org/benchmarx/examples/familiestopersons/implementations/bigul/FamiliesToPersons";
	private FamilyRegister src;
	private PersonRegister trg;
	private String resultSrc;
	private String resultTrg;
	private FamiliesComparator srcHelper = new FamiliesComparator();
	private PersonsComparator trgHelper = new PersonsComparator();
	private BiConsumer<String, String> propagation;
	
	@Override
	public void initiateSynchronisationDialogue() {
		// BiGUL does not require any internal state
		src = FamiliesFactory.eINSTANCE.createFamilyRegister();
		trg = PersonsFactory.eINSTANCE.createPersonRegister();
		
		// Initial results
		resultSrc = srcHelper.familyToString(src);
		resultTrg = trgHelper.personsToString(trg);
	}

	@Override
	public void performAndPropagateTargetEdit(Consumer<PersonRegister> edit) {
		performEdit(edit, trg, this::runBigulBWD);
	}

	@Override
	public void performAndPropagateSourceEdit(Consumer<FamilyRegister> edit) {
		performEdit(edit, src, this::runBigulFWD);
	}
	
	private <M> void performEdit(Consumer<M> edit, M model, BiConsumer<String, String> p){
		try {
			edit.accept(model);
			propagation =  p;
		} catch (Error e) {
			// We are not interested in any problems that might occur while constructing deltas
		}
	}

	private void runBigulBWD(String familyToString, String personsToString) {
		resultSrc = runBigul("bwd", familyToString, personsToString);
		resultTrg = personsToString;
	}

	private void runBigulFWD(String familyToString, String personsToString) {
		resultTrg = runBigul("fwd", familyToString, personsToString);
		resultSrc = familyToString;
	}

	@Override
	public void assertPostcondition(FamilyRegister fr, PersonRegister pr) {
		propagation.accept(srcHelper.familyToString(src), trgHelper.personsToString(trg));
		
		String expectedFamilyRegister = srcHelper.familyToString(fr);
		String expectedPersonsRegister = trgHelper.personsToString(pr);
		
		normaliseAndCompare(expectedFamilyRegister, resultSrc);
		normaliseAndCompare(expectedPersonsRegister, resultTrg);
	}
	
	private void normaliseAndCompare(String expected, String actual) {
		Assert.assertEquals(expected.replaceAll("\\s+",""), actual.replaceAll("\\s+",""));
	}

	private String runBigul(String dir, String familyRegister, String personsRegister) {
		try {
			File pathToExecutable = new File(BIGUL_EXE);
			String input = "(" + "\"" + dir + "\"" + ", " + familyRegister + "," + personsRegister + ")";
			ProcessBuilder processBuilder = new ProcessBuilder(pathToExecutable.getAbsoluteFile().toString());
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			
			OutputStream stdin = process.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
			
			InputStream stdout = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
			
			System.out.println(input);
			
			writer.write(input);
			writer.flush();
			writer.close();
			
			String output = reader.lines().collect(Collectors.joining(" "));
			reader.close();
			
			return output;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "ERROR";
	}

	@Override
	public void assertPrecondition(FamilyRegister source, PersonRegister target) {
		src = source;
		trg = target;
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		// No configuration
	}	 
}

