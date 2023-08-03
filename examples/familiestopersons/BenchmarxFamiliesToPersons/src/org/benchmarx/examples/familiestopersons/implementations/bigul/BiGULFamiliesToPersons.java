package org.benchmarx.examples.familiestopersons.implementations.bigul;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.benchmarx.BXTool;
import org.benchmarx.config.Configurator;
import org.benchmarx.edit.IEdit;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.junit.Assert;

import Families.FamiliesFactory;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsFactory;

/**
 * The trick applied by this testrunner is simply to set the source and target
 * models A and B, when
 * {@link #assertPrecondition(FamilyRegister, PersonRegister)} is invoked (BiGUL
 * is state-based and does not require any internal state). As idle updates are
 * only used to establish the precondition, these can be simply ignored.
 * 
 * Any subsequent invocations of
 * {@link #performAndPropagateSourceEdit(Consumer)} or
 * {@link #performAndPropagateTargetEdit(Consumer)} are then used to update the
 * models, e.g., to A' and B.
 * 
 * This means that round trips are not directly supported (only multiple source
 * or multiple target updates are allowed, not a mixture), and have to be split
 * into separate tests!
 * 
 * Finally, when {@link #assertPostcondition(FamilyRegister, PersonRegister)} is
 * invoked, BiGUL is called with the current state of A' and B to produce B'.
 * 
 * @author anthony anjorin
 */
public class BiGULFamiliesToPersons implements BXTool<FamilyRegister, PersonRegister, Decisions> {
	private static final boolean debug = false;
	
	private static final String BIGUL_EXE = "src/org/benchmarx/examples/familiestopersons/implementations/bigul/FamiliesToPersons";
	private FamilyRegister src;
	private PersonRegister trg;
	private String resultSrc;
	private String resultTrg;
	private FamiliesComparator srcHelper = new FamiliesComparator();
	private PersonsComparator trgHelper = new PersonsComparator();
	private Runnable propagation;
	private Configurator<Decisions> configurator;
	
	@Override
	public String getName() {
		return "BiGUL";
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public void initiateSynchronisationDialogue() {
		// BiGUL does not require any internal state
		src = FamiliesFactory.eINSTANCE.createFamilyRegister();
		trg = PersonsFactory.eINSTANCE.createPersonRegister();
		
		// Initial results
		resultSrc = srcHelper.familyToString(src);
		resultTrg = trgHelper.personsToString(trg);
		
		configurator = new Configurator<Decisions>();
		propagation = () -> {};
	}

	@Override
	public void performAndPropagateTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		performEdit(edit, trg, this::runBigulBWD);
	}

	@Override
	public void performAndPropagateSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		performEdit(edit, src, this::runBigulFWD);
	}
	
	private <M> void performEdit(Supplier<IEdit<M>> edit, M model, Runnable p){
		try {
			edit.get();
			propagation =  p;
		} catch (Error e) {
			// We are not interested in any problems that might occur while constructing deltas
		}
	}

	private void runBigulBWD() {
		resultSrc = runBigul("bwd", resultSrc, trgHelper.personsToString(trg));
		resultTrg = trgHelper.personsToString(trg);
	}

	private void runBigulFWD() {
		resultTrg = runBigul("fwd", srcHelper.familyToString(src), resultTrg);
		resultSrc = srcHelper.familyToString(src);
	}

	@Override
	public void assertPostcondition(FamilyRegister fr, PersonRegister pr) {
		propagation.run();
		
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
			String input = "(" + "\"" + dir + "\"" + ", " + updatePolicy() + ", " + familyRegister + "," + personsRegister + ")";
			ProcessBuilder processBuilder = new ProcessBuilder(pathToExecutable.getAbsoluteFile().toString());
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			
			OutputStream stdin = process.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
			
			InputStream stdout = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));

			if(debug)
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
		
		return "ERROR__Have_you_set_up_the_BiGUL_implementation_properly?__"
				+ "Please_consult_the_\"/implementations/bigul/README-SETUP\"_file!";
	}

	private String updatePolicy() {
		try {
			boolean b = configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD);
			boolean e = configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW);
			
			String policy = "[";
			if(b)
				policy += "PREFER_CREATING_PARENT_TO_CHILD";
			if(b && e)
				policy += ", ";
			if(e)
				policy += "PREFER_EXISTING_FAMILY_TO_NEW";
			
			return policy + "]";
		} catch (Exception e) {
			return "[]";
		}
	}

	@Override
	public void assertPrecondition(FamilyRegister source, PersonRegister target) {
		src = source;
		trg = target;
		resultSrc = srcHelper.familyToString(src);
		resultTrg = trgHelper.personsToString(trg);
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		this.configurator = configurator;
	}	
	
	public void saveModels(String name) {
		
	}
	
	@Override
	public void performIdleTargetEdit(Supplier<IEdit<PersonRegister>> edit) {

	}

	@Override
	public void performIdleSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
	
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

