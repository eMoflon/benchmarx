package org.benchmarx.examples.familiestopersons.implementations.eneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.benchmarx.BXTool;
import org.benchmarx.config.Configurator;
import org.benchmarx.edit.CreateEdge;
import org.benchmarx.edit.CreateNode;
import org.benchmarx.edit.Edit;
import org.benchmarx.edit.IEdit;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.junit.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Families.FamiliesFactory;
import Families.FamiliesPackage;
import Families.FamilyRegister;
import Persons.PersonRegister;
import Persons.PersonsFactory;
import Persons.PersonsPackage;

public class ENEoFamiliesToPersons implements BXTool<FamilyRegister, PersonRegister, Decisions> {
	// FIXME: Adapt to launch configuration settings from executing /EneoFamiliesToPersons/src/org/benchmarx/eneo/f2p/ENeoRunner.java
	private String JAVA = "java";
	private String CLASSPATH = "";
	
	private Configurator<Decisions> configurator;
	private FamilyRegister sourceRegister;
	private PersonRegister targetRegister;
	private boolean preconditionAchieved;
	private FamiliesComparator srcHelper = new FamiliesComparator();
	private PersonsComparator trgHelper = new PersonsComparator();

	@Override
	public String getName() {
		return "eNeo";
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public void initiateSynchronisationDialogue() {
		sourceRegister = FamiliesFactory.eINSTANCE.createFamilyRegister();
		targetRegister = PersonsFactory.eINSTANCE.createPersonRegister();
		preconditionAchieved = false;

		var input = new ENeoInput();
		input.setMode(MODE.INIT);
		runENeo(input);
	}

	@Override
	public void assertPostcondition(FamilyRegister source, PersonRegister target) {
		var input = new ENeoInput();
		input.setMode(MODE.POSTCOND);
		var output = runENeo(input);
		
		String expectedFamilyRegister = srcHelper.familyToString(source);
		String expectedPersonRegister = trgHelper.personsToString(target);
		
		Assert.assertEquals(expectedFamilyRegister, output.getSource());
		Assert.assertEquals(expectedPersonRegister, output.getTarget());
	}

	@Override
	public void noPrecondition() {
		preconditionAchieved = true;
	}

	@Override
	public void assertPrecondition(FamilyRegister source, PersonRegister target) {
		// Create models in database
		IEdit<FamilyRegister> sourceEdit = new Edit<>();
		source.getFamilies().forEach(f -> {
			sourceEdit.getSteps().add(new CreateNode<>(f));
			sourceEdit.getSteps().add(new CreateEdge<>(FamiliesPackage.Literals.FAMILY_REGISTER__FAMILIES, source, f));

			if (f.getFather() != null) {
				sourceEdit.getSteps().add(new CreateNode<>(f.getFather()));
				sourceEdit.getSteps().add(new CreateEdge<>(FamiliesPackage.Literals.FAMILY__FATHER, f, f.getFather()));
			}

			if (f.getMother() != null) {
				sourceEdit.getSteps().add(new CreateNode<>(f.getMother()));
				sourceEdit.getSteps().add(new CreateEdge<>(FamiliesPackage.Literals.FAMILY__MOTHER, f, f.getMother()));
			}

			f.getDaughters().forEach(d -> {
				sourceEdit.getSteps().add(new CreateNode<>(d));
				sourceEdit.getSteps().add(new CreateEdge<>(FamiliesPackage.Literals.FAMILY__DAUGHTERS, f, d));
			});

			f.getSons().forEach(s -> {
				sourceEdit.getSteps().add(new CreateNode<>(s));
				sourceEdit.getSteps().add(new CreateEdge<>(FamiliesPackage.Literals.FAMILY__SONS, f, s));
			});
		});

		IEdit<PersonRegister> targetEdit = new Edit<>();
		target.getPersons().forEach(p -> {
			targetEdit.getSteps().add(new CreateNode<>(p));
			targetEdit.getSteps().add(new CreateEdge<>(PersonsPackage.Literals.PERSON_REGISTER__PERSONS, target, p));
		});

		var input = new ENeoInput();
		input.setMode(MODE.PRECOND);
		input.setEdits(sourceEdit, targetEdit);
		runENeo(input);

		assertPostcondition(source, target);

		sourceRegister = source;
		targetRegister = target;

		preconditionAchieved = true;
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEdit,
			Supplier<IEdit<PersonRegister>> targetEdit) {
		if (preconditionAchieved) {
			var input = new ENeoInput();
			input.setMode(MODE.PROPAGATE);
			input.setEdits(sourceEdit.get(), targetEdit.get());
			if(configurator != null)
				input.setConfigurator(configurator);
			runENeo(input);
		}
	}

	@Override
	public void performIdleSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		performAndPropagateSourceEdit(edit);
	}

	@Override
	public void performIdleTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		performAndPropagateTargetEdit(edit);
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		this.configurator = configurator;
	}

	@Override
	public FamilyRegister getSourceModel() {
		return sourceRegister;
	}

	@Override
	public PersonRegister getTargetModel() {
		return targetRegister;
	}

	private ENeoOutput runENeo(ENeoInput input) {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();  
			gsonBuilder.setPrettyPrinting();
			Gson gson = gsonBuilder.create();
			String in = gson.toJson(input);

			System.out.println("Running ENeo with " + in);
			
			ProcessBuilder processBuilder = new ProcessBuilder(
					JAVA,
					"-Dfile.encoding=UTF-8", 
					"-classpath",
					CLASSPATH,
					"-XX:+ShowCodeDetailsInExceptionMessages", 
					"org.benchmarx.eneo.f2p.ENeoRunner", 
					in);

			processBuilder.redirectErrorStream(false);

			Process process = processBuilder.start();
			InputStream stdout = process.getInputStream();
			InputStream stderr = process.getErrorStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(stderr));
			
			process.waitFor();

			String output = reader.lines().collect(Collectors.joining("\n"));
			String err = errorReader.lines().collect(Collectors.joining("\n"));
			reader.close();
			errorReader.close();

			System.out.println(err);

			return gson.fromJson(output, ENeoOutput.class);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		throw new IllegalStateException("Unable to run eNeo.");
	}
}
