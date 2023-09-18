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
	private String JAVA = "/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.justj.openjdk.hotspot.jre.full.macosx.x86_64_17.0.4.v20220903-1038/jre/bin/java";
	private String CLASSPATH = "/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/implementationArtefacts/eNeo/ENeoFamiliesToPersons/bin:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.osgi.annotation.versioning_1.1.2.202109301733.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.osgi.annotation.bundle_2.0.0.202202082230.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.osgi.service.component.annotations_1.5.0.202109301733.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/com.google.gson_2.9.1.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.emf_1.0.0.202211241827.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.apache.commons.io_2.8.0.v20210415-0900.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/com.google.guava_30.1.0.v20210127-2300.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.emf.ecore_2.28.0.v20220817-1401.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.emf.common_2.26.0.v20220817-1401.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.neo4j.adapter_1.0.0.202211241827:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.neo4j.adapter_1.0.0.202211241827/lib/neo4j-java-driver-4.1.1.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.neo4j.adapter_1.0.0.202211241827/lib/reactive-streams-1.0.3.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.core.runtime_3.26.0.v20220813-0916.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.osgi_3.18.100.v20220817-1601.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.osgi.compatibility.state_1.2.700.v20220722-0431.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.equinox.common_3.16.200.v20220817-1601.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.core.jobs_3.13.100.v20220817-1539.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.equinox.registry_3.11.200.v20220817-1601.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.equinox.preferences_3.10.100.v20220710-1223.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.osgi.service.prefs_1.1.2.202109301733.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.core.contenttype_3.8.200.v20220817-1539.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.equinox.app_1.6.200.v20220720-2012.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.xtext.xbase.lib_2.28.0.v20220829-0436.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.xtend.lib_2.28.0.v20220829-0436.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.xtend.lib.macro_2.28.0.v20220829-0436.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.emsl_1.0.0.202211241827/lib/javabdd-1.0b2.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.emsl_1.0.0.202211241827:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.core.resources_3.18.0.v20220828-0546.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.engine_1.0.0.202211241827.jar:/Users/anthonyanjorin/eclipse-workspaces/benchmarx/.metadata/.plugins/org.eclipse.pde.core/.external_libraries/org.emoflon.neo.engine_1.0.0.202211241827/lib/commons-cli-1.2.jar:/Users/anthonyanjorin/eclipse-workspaces/benchmarx/.metadata/.plugins/org.eclipse.pde.core/.external_libraries/org.emoflon.neo.engine_1.0.0.202211241827/lib/commons-codec-1.8.jar:/Users/anthonyanjorin/eclipse-workspaces/benchmarx/.metadata/.plugins/org.eclipse.pde.core/.external_libraries/org.emoflon.neo.engine_1.0.0.202211241827/lib/commons-lang3-3.1.jar:/Users/anthonyanjorin/eclipse-workspaces/benchmarx/.metadata/.plugins/org.eclipse.pde.core/.external_libraries/org.emoflon.neo.engine_1.0.0.202211241827/lib/commons-math3-3.4.1.jar:/Users/anthonyanjorin/eclipse-workspaces/benchmarx/.metadata/.plugins/org.eclipse.pde.core/.external_libraries/org.emoflon.neo.engine_1.0.0.202211241827/lib/jcommon-1.0.20.jar:/Users/anthonyanjorin/eclipse-workspaces/benchmarx/.metadata/.plugins/org.eclipse.pde.core/.external_libraries/org.emoflon.neo.engine_1.0.0.202211241827/lib/jfreechart-1.0.15.jar:/Users/anthonyanjorin/eclipse-workspaces/benchmarx/.metadata/.plugins/org.eclipse.pde.core/.external_libraries/org.emoflon.neo.engine_1.0.0.202211241827/lib/JMetal-4.3.jar:/Users/anthonyanjorin/eclipse-workspaces/benchmarx/.metadata/.plugins/org.eclipse.pde.core/.external_libraries/org.emoflon.neo.engine_1.0.0.202211241827/lib/MOEAFramework-2.13.jar:/Users/anthonyanjorin/eclipse-workspaces/benchmarx/.metadata/.plugins/org.eclipse.pde.core/.external_libraries/org.emoflon.neo.engine_1.0.0.202211241827/lib/gurobi.jar:/Users/anthonyanjorin/eclipse-workspaces/benchmarx/.metadata/.plugins/org.eclipse.pde.core/.external_libraries/org.emoflon.neo.engine_1.0.0.202211241827/lib/rsyntaxtextarea.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.engine.modules_1.0.0.202211241827/lib/gurobi.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.engine.modules_1.0.0.202211241827/lib/MOEAFramework-2.13.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.engine.modules_1.0.0.202211241827:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.sat4j.core_2.3.6.v20201214.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.sat4j.pb_2.3.6.v20201214.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.emoflon.neo.neocore_1.0.0.202211241827:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.xtext_2.28.0.v20220829-0438.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.emf.ecore.xmi_2.17.0.v20220817-1334.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.antlr.runtime_3.2.0.v20220404-1927.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/com.google.inject_5.0.1.v20210324-2015.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.emf.mwe.core_1.7.0.v20220519-1115.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.apache.commons.cli_1.4.0.v20200417-1444.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.emf.mwe2.runtime_2.13.0.v20220519-1115.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.emf.mwe.utils_1.7.0.v20220519-1115.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.xtext.util_2.28.0.v20220829-0438.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/javax.inject_1.0.0.v20220405-0441.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.apache.commons.logging_1.2.0.v20180409-1502.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.apache.log4j_1.2.19.v20220208-1728.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.apache.commons.lang_2.6.0.v20220406-2305.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.junit_4.13.2.v20211018-1956.jar:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.hamcrest.core_1.3.0.v20180420-1519.jar:/Users/anthonyanjorin/git/benchmarx/core/Benchmarx/bin:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/bin:/Applications/eNeo/Eclipse.app/Contents/Eclipse/plugins/org.eclipse.emf.common.source_2.26.0.v20220817-1401.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/metamodels/Families/bin:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/metamodels/Persons/bin:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/UBTBxTendFamilies2Persons.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/FunnyQT/ttc17-families2persons-bx-1.0.0-standalone.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/jtl/JTL-0.2.1-standalone.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/mediniQVT/mediniQVT.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/mediniQVT/qvtemf.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/IBeXTGGPDB1ToPDB2.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/eMoflon/commons-io-2.5.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/eMoflon/eMoflonFamilies2Persons.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/eMoflon/MocaTree_2.0.0.201611230913.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/eMoflon/org.apache.commons.lang3_3.1.0.v201403281430.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/eMoflon/org.eclipse.xtext.xbase.lib_2.10.0.v201605250459.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/eMoflon/org.moflon.core.utilities_1.2.1.201611230913.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/eMoflon/org.moflon.tgg.language_2.1.0.201611230913.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/eMoflon/org.moflon.tgg.runtime_2.0.0.201611230913.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/eMoflon/SDMLanguage_2.0.0.201611230913.jar:/Users/anthonyanjorin/git/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/lib/eMoflon/trove-3.1a1.jar";
	
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
