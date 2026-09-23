package org.benchmarx.examples.familiestopersons.implementations.bcert;

import java.io.BufferedReader;
import java.io.File;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.benchmarx.config.Configurator;
import org.benchmarx.edit.IEdit;
import org.benchmarx.emf.BXToolForEMF;
import org.benchmarx.examples.familiestopersons.testsuite.Decisions;
import org.benchmarx.families.core.FamiliesComparator;
import org.benchmarx.persons.core.PersonsComparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Families.FamiliesFactory;
import Families.FamiliesPackage;
import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;
import Persons.Female;
import Persons.Male;
import Persons.Person;
import Persons.PersonRegister;
import Persons.PersonsFactory;
import Persons.PersonsPackage;
import pivot.Mapping;
import pivot.Pivot;
import pivot.PivotFactory;
import pivot.PivotPackage;
import pivot.Strategy;

public class BCerTFamiliesToPersons extends BXToolForEMF<FamilyRegister, PersonRegister, Decisions> {

	private static final String PROBCLI_PATH = getProBCliPath();

	private static File findFile(String relativePath) {
		var f1 = new File(relativePath);
		if (f1.exists()) return f1;

		var f2 = new File("../" + relativePath);
		if (f2.exists()) return f2;

		var f3 = new File("../../" + relativePath);
		if (f3.exists()) return f3;

		var f4 = new File("../../../" + relativePath);
		if (f4.exists()) return f4;

		try {
			var codeSourcePath = BCerTFamiliesToPersons.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			var decodedPath = java.net.URLDecoder.decode(codeSourcePath, "UTF-8");
			var idx = decodedPath.indexOf("examples/familiestopersons");
			if (idx >= 0) {
				var rootPath = decodedPath.substring(0, idx);
				var f5 = new File(rootPath + relativePath);
				if (f5.exists()) return f5;
			}
		} catch (Exception ignored) {
		}

		return null;
	}

	private static String getProBCliPath() {
		var path = System.getProperty("probcli.path", System.getenv("PROBCLI_PATH"));
		if (path != null && !path.isEmpty()) {
			return path;
		}

		var isWindows = System.getProperty("os.name").toLowerCase().contains("win");
		var relativePath = isWindows
				? "examples/familiestopersons/implementationArtefacts/bcert/pivot/lib/proB-win/probcli.exe"
				: "examples/familiestopersons/implementationArtefacts/bcert/pivot/lib/proB-linux/probcli";

		var file = findFile(relativePath);
		if (file != null && file.exists()) {
			return file.getAbsolutePath();
		}
		return null;
	}

	private ResourceSet resourceSet = new ResourceSetImpl();
	private Resource sourceResource;
	private Resource targetResource;
	private Resource pivotResource;
	private Pivot pivot;
	private Configurator<Decisions> configurator;
	private Configurator<Decisions> defaultConfigurator;

	private static final String RESULT_PATH = "results/BCerT";

	public BCerTFamiliesToPersons() {
		super(new FamiliesComparator(), new PersonsComparator());
	}

	@Override
	public String getName() {
		return "BCerT";
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public void initiateSynchronisationDialogue() {
		resourceSet.getResources().clear();
		defaultConfigurator = new Configurator<Decisions>()
				.makeDecision(Decisions.PREFER_CREATING_PARENT_TO_CHILD, true)
				.makeDecision(Decisions.PREFER_EXISTING_FAMILY_TO_NEW, true);
		setConfigurator(defaultConfigurator);

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("family", new XMIResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("person", new XMIResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("pivot", new XMIResourceFactoryImpl());

		FamiliesPackage.eINSTANCE.getName();
		PersonsPackage.eINSTANCE.getName();
		PivotPackage.eINSTANCE.getName();

		sourceResource = resourceSet.createResource(URI.createURI("sourceModel.family"));
		targetResource = resourceSet.createResource(URI.createURI("targetModel.person"));
		pivotResource = resourceSet.createResource(URI.createURI("pivotModel.pivot"));

		var familiesRoot = FamiliesFactory.eINSTANCE.createFamilyRegister();
		var personsRoot = PersonsFactory.eINSTANCE.createPersonRegister();

		sourceResource.getContents().add(familiesRoot);
		targetResource.getContents().add(personsRoot);

		pivot = PivotFactory.eINSTANCE.createPivot();
		pivot.setFamilyModel(familiesRoot);
		pivot.setPersonModel(personsRoot);
		pivot.setStrategie(Strategy.FWD);
		pivot.setFAMILY_TO_NEW(true);
		pivot.setPARENT_TO_CHILD(true);
		pivot.setSync(false);
		pivotResource.getContents().add(pivot);

		syncSourceToTarget();
	}

	@Override
	public void performAndPropagateSourceEdit(Supplier<IEdit<FamilyRegister>> edit) {
		edit.get();
		configurePivot();
		pivot.setStrategie(Strategy.FWD);
		syncSourceToTarget();
	}

	@Override
	public void performAndPropagateTargetEdit(Supplier<IEdit<PersonRegister>> edit) {
		edit.get();
		configurePivot();
		pivot.setStrategie(Strategy.BWD);
		syncTargetToSource();
	}

	@Override
	public void performAndPropagateEdit(Supplier<IEdit<FamilyRegister>> sourceEditOp,
			Supplier<IEdit<PersonRegister>> targetEditOp) {
		sourceEditOp.get();
		targetEditOp.get();
		configurePivot();
		syncConcurrent();
	}

	@Override
	public FamilyRegister getSourceModel() {
		return (FamilyRegister) sourceResource.getContents().get(0);
	}

	@Override
	public PersonRegister getTargetModel() {
		return (PersonRegister) targetResource.getContents().get(0);
	}

	@Override
	public void setConfigurator(Configurator<Decisions> configurator) {
		if (defaultConfigurator == null) {
			defaultConfigurator = configurator;
		}
		this.configurator = configurator;
	}

	@Override
	public void saveModels(String name) {
		var set = new ResourceSetImpl();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());

		var srcURI = URI.createFileURI(RESULT_PATH + "/" + name + "Family.xmi");
		var trgURI = URI.createFileURI(RESULT_PATH + "/" + name + "Person.xmi");
		var resSource = set.createResource(srcURI);
		var resTarget = set.createResource(trgURI);

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

	private void configurePivot() {
		pivot.getMapping().removeIf(m -> m.getPMap() != null && m.getPMap().eContainer() == null);
		pivot.getMapping().removeIf(m -> m.getMMap() != null && m.getMMap().eContainer() == null);

		var preferExistingFamily = configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW);
		var preferParent = configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD);

		pivot.setFAMILY_TO_NEW(preferExistingFamily);
		pivot.setPARENT_TO_CHILD(preferParent);
	}

	private String generateBMachine(Strategy strategy) {
		var modelDir = findFile("examples/familiestopersons/implementationArtefacts/bcert/pivot/model");
		if (modelDir == null || !modelDir.exists()) {
			return null;
		}

		var mchName = (strategy == Strategy.FWD) ? "TempBatchFwd.mch" : "TempBatchBwd.mch";
		var cspName = (strategy == Strategy.FWD) ? "TempBatchFwd.csp" : "TempBatchBwd.csp";
		var tempFile = new File(modelDir, mchName);

		try (var writer = new PrintWriter(new FileWriter(tempFile))) {
			if (strategy == Strategy.FWD) {
				var sourceReg = getSourceModel();

				writer.println("MACHINE TempBatchFwd");
				writer.println("INCLUDES MetaModel");
				writer.println("DEFINITIONS");
				writer.println("  SET_PREF_MAX_OPERATIONS == 100000 ;");
				writer.println("  \"definitions.def\" ;");
				writer.println("  \"LibraryStrings.def\" ;");
				writer.println("  \"BatchBwd.def\"");
				writer.println("INITIALISATION");
				writer.println("  Reset");
				writer.println("OPERATIONS");

				var stmts = new ArrayList<String>();
				stmts.add("SetFWD");
				stmts.add("SetUnSync");
				var preferExistingFamilyFWD = (configurator != null) ? configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW) : true;
				var preferParentFWD = (configurator != null) ? configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD) : true;
				var familyToNewBValFWD = (preferExistingFamilyFWD) ? "TRUE" : "FALSE";
				var parentToChildBValFWD = (preferParentFWD) ? "TRUE" : "FALSE";
				stmts.add("MakeDecision(" + familyToNewBValFWD + ", " + parentToChildBValFWD + ")");

				var familyStmts = new ArrayList<String>();
				for (var fam : sourceReg.getFamilies()) {
					var famName = fam.getName();
					familyStmts.add("aFamily <-- FamilyNEW(\"" + famName + "\")");
					if (fam.getFather() != null) {
						familyStmts.add("AddFather(aFamily, \"" + fam.getFather().getName() + "\")");
					}
					if (fam.getMother() != null) {
						familyStmts.add("AddMother(aFamily, \"" + fam.getMother().getName() + "\")");
					}
					for (var son : fam.getSons()) {
						familyStmts.add("AddSon(aFamily, \"" + son.getName() + "\")");
					}
					for (var daughter : fam.getDaughters()) {
						familyStmts.add("AddDaughter(aFamily, \"" + daughter.getName() + "\")");
					}
				}

				if (!familyStmts.isEmpty()) {
					stmts.add("VAR aFamily IN\n      " + String.join(" ;\n      ", familyStmts) + "\n    END");
				}



				writer.println("setupModel = ");
				writer.println("  PRE");
				writer.println("    Family = {}");
				writer.println("  THEN");
				writer.println("    " + String.join(" ||\n    ", stmts));
				writer.println("  END ;");

				var birthdayStmts = new ArrayList<String>();
				for (var person : getTargetModel().getPersons()) {
					if (person.getBirthday() != null) {
						var bdayStr = new SimpleDateFormat("yyyy-MM-dd").format(person.getBirthday());
						if (!bdayStr.isEmpty() && !"0001-01-01".equals(bdayStr)) {
							var mapping = pivot.getMapping().stream()
									.filter(m -> m.getPMap() == person)
									.findFirst().orElse(null);
							if (mapping != null && mapping.getMMap() != null && mapping.getMMap().eContainer() instanceof Family) {
								var fam = (Family) mapping.getMMap().eContainer();
								var m = mapping.getMMap();
								var featName = m.eContainingFeature().getName();
								String opName;
								if ("father".equalsIgnoreCase(featName)) {
									opName = "SetFatherBirthdayIfExists";
								} else if ("mother".equalsIgnoreCase(featName)) {
									opName = "SetMotherBirthdayIfExists";
								} else if ("sons".equalsIgnoreCase(featName)) {
									opName = "SetSonBirthdayIfExists";
								} else {
									opName = "SetDaughterBirthdayIfExists";
								}
								birthdayStmts.add(opName + "(\"" + fam.getName() + "\", \"" + m.getName() + "\", \"" + bdayStr + "\")");
							}
						}
					}
				}

				writer.println("transformStep =");
				writer.println("  PRE");
				writer.println("    Family /= {} &");
				writer.println("    theMembers /<: ran(mMap)");
				writer.println("  THEN");
				writer.println("    Member2Person");
				if (!birthdayStmts.isEmpty()) {
					writer.println("  END ;");
					writer.println("setBirthdayStep =");
					writer.println("  PRE");
					writer.println("    Family /= {} &");
					writer.println("    theMembers <: ran(mMap) &");
					writer.println("    ran(birthday) = {\"DEFAULT\"}");
					writer.println("  THEN");
					writer.println("    BEGIN");
					writer.println("      " + String.join(" ;\n      ", birthdayStmts));
					writer.println("    END");
					writer.println("  END");
				} else {
					writer.println("  END");
				}
				writer.println("END");
			} else {
				var targetReg = getTargetModel();

				writer.println("MACHINE TempBatchBwd");
				writer.println("INCLUDES MetaModel");
				writer.println("DEFINITIONS");
				writer.println("  SET_PREF_MAX_OPERATIONS == 100000 ;");
				writer.println("  \"definitions.def\" ;");
				writer.println("  \"LibraryStrings.def\" ;");
				writer.println("  \"BatchBwd.def\" ;");
				writer.println("  \"concurrent.def\"");
				writer.println("VARIABLES");
				writer.println("  setupStep");
				writer.println("INVARIANT");
				writer.println("  setupStep : INT");
				writer.println("INITIALISATION");
				writer.println("  Reset ||");
				writer.println("  setupStep := 0");
				writer.println("OPERATIONS");

				var preferExistingFamilyBWD = (configurator != null) ? configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW) : true;
				var preferParentBWD = (configurator != null) ? configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD) : true;
				var familyToNewBValBWD = (preferExistingFamilyBWD) ? "TRUE" : "FALSE";
				var parentToChildBValBWD = (preferParentBWD) ? "TRUE" : "FALSE";

				var setupOps = new ArrayList<String>();
				var currentStep = 0;
				currentStep++;
				setupOps.add(
					"setup_init =\n" +
					"  PRE setupStep = 0 & pvtRoot /: dom(strategie) THEN\n" +
					"    SetBWD || SetUnSync || MakeDecision(" + familyToNewBValBWD + ", " + parentToChildBValBWD + ") || setupStep := " + currentStep + "\n" +
					"  END ;"
				);

				var familyMapBWD = new HashMap<Family, String>();
				var famVarIndex = 1;

				for (var fam : getSourceModel().getFamilies()) {
					var famName = fam.getName();
					var varName = familyMapBWD.get(fam);
					if (varName == null) {
						varName = "aFam" + (famVarIndex++);
						familyMapBWD.put(fam, varName);
						var famStmts = new ArrayList<String>();
						famStmts.add(varName + " <-- FamilyNEW(\"" + famName + "\")");

						if (fam.getFather() != null && isMappedInPivot(fam.getFather())) {
							famStmts.add("AddFather(" + varName + ", \"" + fam.getFather().getName() + "\")");
						}
						if (fam.getMother() != null && isMappedInPivot(fam.getMother())) {
							famStmts.add("AddMother(" + varName + ", \"" + fam.getMother().getName() + "\")");
						}
						for (var son : fam.getSons()) {
							if (isMappedInPivot(son)) {
								famStmts.add("AddSon(" + varName + ", \"" + son.getName() + "\")");
							}
						}
						for (var daughter : fam.getDaughters()) {
							if (isMappedInPivot(daughter)) {
								famStmts.add("AddDaughter(" + varName + ", \"" + daughter.getName() + "\")");
							}
						}
						var nextStep = ++currentStep;
						setupOps.add(
							"setup_fam_" + (famVarIndex - 1) + " =\n" +
							"  PRE setupStep = " + (nextStep - 1) + " THEN\n" +
							"    BEGIN VAR " + varName + " IN " + String.join(" ; ", famStmts) + " END || setupStep := " + nextStep + " END\n" +
							"  END ;"
						);
					}
				}

				var personIdx = 1;
				for (var person : targetReg.getPersons()) {
					var isMale = (person instanceof Male);
					var personStmts = new ArrayList<String>();
					personStmts.add("PersonNEW(\"" + person.getName() + "\", " + (isMale ? "TRUE" : "FALSE") + ")");
					if (person.getBirthday() != null) {
						var bdayStr = new SimpleDateFormat("yyyy-MM-dd").format(person.getBirthday());
						if (!bdayStr.isEmpty() && !"0001-01-01".equals(bdayStr)) {
							personStmts.add("SetBirthday(\"" + person.getName() + "\", \"" + bdayStr + "\")");
						}
					}
					var mappingEntry = pivot.getMapping().stream().filter(m -> m.getPMap() == person && m.getMMap() != null).findFirst().orElse(null);
					if (mappingEntry != null && mappingEntry.getMMap().eContainer() instanceof Family) {
						var fam = (Family) mappingEntry.getMMap().eContainer();
						var mem = mappingEntry.getMMap();
						personStmts.add("RegisterMapping(\"" + person.getName() + "\", \"" + fam.getName() + "\", \"" + mem.getName() + "\")");
					}
					var nextStep = ++currentStep;
					setupOps.add(
						"setup_person_" + (personIdx++) + " =\n" +
						"  PRE setupStep = " + (nextStep - 1) + " THEN\n" +
						"    BEGIN " + String.join(" ; ", personStmts) + " || setupStep := " + nextStep + " END\n" +
						"  END ;"
					);
				}

				for (var op : setupOps) {
					writer.println(op);
				}

				writer.println("transformStep =");
				writer.println("  PRE");
				writer.println("    setupStep = " + currentStep + " &");
				writer.println("    ((Person /= {} & thePersons /<: dom(mapped)) or");
				writer.println("    (theMembers /<: ran(mMap)) or");
				writer.println("    HasConcurrentTargetRenameWins)");
				writer.println("  THEN");
				writer.println("    SELECT");
				writer.println("      HasConcurrentTargetRenameWins");
				writer.println("    THEN");
				writer.println("      ConcurrentTargetRenameWins");
				writer.println("    WHEN");
				writer.println("      #(mm,pp).(");
				writer.println("        mm : theMembers & mm /: ran(mMap) &");
				writer.println("        pp : thePersons & pp /: dom(mapped) &");
				writer.println("        (");
				writer.println("          Persons_Person_name(pp) = STRING_CONC([Families_Family_name(familyOf(mm)), \", \", Families_FamilyMember_name(mm)]) or");
				writer.println("          #(map).(map : Mapping & pMap(map) = pp & mMap(map) = mm)");
				writer.println("        )");
				writer.println("      )");
				writer.println("    THEN");
				writer.println("      ConcurrentMatchMemberPerson");
				writer.println("    WHEN");
				writer.println("      #(mm).(");
				writer.println("        mm : theMembers & mm /: ran(mMap) &");
				writer.println("        not(#pp.(");
				writer.println("          pp : thePersons & pp /: dom(mapped) &");
				writer.println("          (");
				writer.println("            Persons_Person_name(pp) = STRING_CONC([Families_Family_name(familyOf(mm)), \", \", Families_FamilyMember_name(mm)]) or");
				writer.println("            #(map).(map : Mapping & pMap(map) = pp & mMap(map) = mm)");
				writer.println("          )");
				writer.println("        ))");
				writer.println("      )");
				writer.println("    THEN");
				writer.println("      BackwardDeleteFamilyMember");
				writer.println("    WHEN");
				writer.println("      FAMILY_TO_NEW(pvtRoot) = TRUE &");
				writer.println("      #(pp,ff).(");
				writer.println("        pp : Person &");
				writer.println("        pp : thePersons &");
				writer.println("        pp /: dom(mapped) &");
				writer.println("        ff : Family &");
				writer.println("        ff : families~[{fmlRoot}] &");
				writer.println("        Families_Family_name(ff) = PersonFamilyName(pp)");
				writer.println("      )");
				writer.println("    THEN");
				writer.println("      Person2MemberExistingFamily");
				writer.println("    ELSE");
				writer.println("      Person2MemberNewFamily");
				writer.println("    END");
				writer.println("  END");
				writer.println("END");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return tempFile.getName();
	}

	private static class BEntityComparator implements java.util.Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			try {
				var num1 = Integer.parseInt(s1.replaceAll("\\D+", ""));
				var num2 = Integer.parseInt(s2.replaceAll("\\D+", ""));
				return Integer.compare(num1, num2);
			} catch (Exception e) {
				return s1.compareTo(s2);
			}
		}
	}

	private static class ProBSolverResult {
		Map<String, String> personNames = new java.util.TreeMap<>(new BEntityComparator());
		Set<String> males = new HashSet<>();
		Set<String> females = new HashSet<>();
		Map<String, String> pMap = new HashMap<>();
		Map<String, String> mMap = new HashMap<>();
		Map<String, String> familyNames = new java.util.TreeMap<>(new BEntityComparator());
		Map<String, String> memberNames = new java.util.TreeMap<>(new BEntityComparator());
		Map<String, String> theFather = new HashMap<>();
		Map<String, String> theMother = new HashMap<>();
		Map<String, String> theSons = new HashMap<>();
		Map<String, String> theDaughters = new HashMap<>();
		Map<String, String> birthdays = new HashMap<>();
	}

	private ProBSolverResult executeNativeProBAndParse(String machineName) {
		var result = new ProBSolverResult();
		if (PROBCLI_PATH == null) {
			return result;
		}

		File modelDir = findFile("examples/familiestopersons/implementationArtefacts/bcert/pivot/model");
		File mchFile = new File(modelDir, machineName);

		System.out.println("Executing native ProB (" + machineName + ") via probcli: " + PROBCLI_PATH);

		Process probProcess = null;
		try {
			var pb = new ProcessBuilder(
				PROBCLI_PATH, mchFile.getAbsolutePath(),
				"-init", "-noinv", "-execute", "100000",
				"-eval", "Persons_Person_name",
				"-eval", "Male",
				"-eval", "Female",
				"-eval", "pMap",
				"-eval", "mMap",
				"-eval", "Families_Family_name",
				"-eval", "Families_FamilyMember_name",
				"-eval", "theFather",
				"-eval", "theMother",
				"-eval", "theSons",
				"-eval", "theDaughters",
				"-eval", "birthday"
			);
			pb.directory(modelDir);
			pb.redirectErrorStream(true);
			probProcess = pb.start();

			var sb = new StringBuilder();
			try (var reader = new BufferedReader(new InputStreamReader(probProcess.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			}

			if (!probProcess.waitFor(15, TimeUnit.MINUTES)) {
				terminateProcess(probProcess);
				throw new IllegalStateException("ProB execution timed out for machine: " + machineName);
			}

			parseProBOutput(sb.toString(), result);
			System.out.println("=== DEBUG PROB OUTPUT FOR " + machineName + " ===");
			System.out.println(sb.toString());
			System.out.println("==================================================");
		} catch (Exception e) {
			terminateProcess(probProcess);
			throw new RuntimeException("Error executing native ProB machine: " + machineName, e);
		} finally {
			terminateProcess(probProcess);
		}

		return result;
	}

	private void parseProBOutput(String output, ProBSolverResult result) {
		parseRelationMap(output, "Persons_Person_name", result.personNames);
		parseSet(output, "Male", result.males);
		parseSet(output, "Female", result.females);
		parseRelationMap(output, "pMap", result.pMap);
		parseRelationMap(output, "mMap", result.mMap);
		parseRelationMap(output, "Families_Family_name", result.familyNames);
		parseRelationMap(output, "Families_FamilyMember_name", result.memberNames);
		parseRelationMap(output, "theFather", result.theFather);
		parseRelationMap(output, "theMother", result.theMother);
		parseRelationMap(output, "theSons", result.theSons);
		parseRelationMap(output, "theDaughters", result.theDaughters);
		parseRelationMap(output, "birthday", result.birthdays);
	}

	private void parseRelationMap(String text, String varName, Map<String, String> targetMap) {
		var pattern = Pattern.compile("(?s)" + Pattern.quote(varName) + ".*?\\{([^}]*)\\}");
		var matcher = pattern.matcher(text);
		if (matcher.find()) {
			var body = matcher.group(1);
			var pairPattern = Pattern.compile("\\(([^|]+)\\|\\->(?:\"([^\"]+)\"|([^\\)\\s,]+))\\)");
			var pairMatcher = pairPattern.matcher(body);
			while (pairMatcher.find()) {
				var key = pairMatcher.group(1).trim();
				var val = (pairMatcher.group(2) != null) ? pairMatcher.group(2).trim() : pairMatcher.group(3).trim();
				targetMap.put(key, val);
			}
		}
	}

	private void parseSet(String text, String varName, Set<String> targetSet) {
		var pattern = Pattern.compile("(?s)" + Pattern.quote(varName) + ".*?\\{([^}]*)\\}");
		var matcher = pattern.matcher(text);
		if (matcher.find()) {
			var body = matcher.group(1);
			var items = body.split(",");
			for (var item : items) {
				var val = item.trim();
				if (!val.isEmpty()) {
					targetSet.add(val);
				}
			}
		}
	}

	private void applyProBSolverResults(ProBSolverResult solverResult) {
		var sourceReg = getSourceModel();
		var targetReg = getTargetModel();

		// 1. Update Persons in targetReg
		var activePersonsByBId = new HashMap<String, Person>();
		var existingPersons = new ArrayList<>(targetReg.getPersons());
		targetReg.getPersons().clear();
		var pidToMember = new HashMap<String, FamilyMember>();
		for (var mapEntry : solverResult.pMap.entrySet()) {
			var mapId = mapEntry.getKey();
			var pid = mapEntry.getValue();
			var mid = solverResult.mMap.get(mapId);
			if (mid != null) {
				var memberName = solverResult.memberNames.get(mid);
				if (memberName != null) {
					String role = null;
					String fid = null;
					if (solverResult.theFather.containsKey(mid)) {
						role = "father";
						fid = solverResult.theFather.get(mid);
					} else if (solverResult.theMother.containsKey(mid)) {
						role = "mother";
						fid = solverResult.theMother.get(mid);
					} else if (solverResult.theSons.containsKey(mid)) {
						role = "sons";
						fid = solverResult.theSons.get(mid);
					} else if (solverResult.theDaughters.containsKey(mid)) {
						role = "daughters";
						fid = solverResult.theDaughters.get(mid);
					}
					var famName = (fid != null) ? solverResult.familyNames.get(fid) : null;
					for (var f : sourceReg.getFamilies()) {
						if (famName != null && !famName.equals(f.getName())) continue;
						if ("father".equals(role) && f.getFather() != null && memberName.equals(f.getFather().getName())) {
							pidToMember.put(pid, f.getFather());
							break;
						}
						if ("mother".equals(role) && f.getMother() != null && memberName.equals(f.getMother().getName())) {
							pidToMember.put(pid, f.getMother());
							break;
						}
						if ("sons".equals(role)) {
							for (var s : f.getSons()) {
								if (memberName.equals(s.getName()) && !pidToMember.containsValue(s)) {
									pidToMember.put(pid, s);
									break;
								}
							}
							if (pidToMember.containsKey(pid)) break;
						}
						if ("daughters".equals(role)) {
							for (var d : f.getDaughters()) {
								if (memberName.equals(d.getName()) && !pidToMember.containsValue(d)) {
									pidToMember.put(pid, d);
									break;
								}
							}
							if (pidToMember.containsKey(pid)) break;
						}
					}
				}
			}
		}

		for (var entry : solverResult.personNames.entrySet()) {
			var pid = entry.getKey();
			var personName = entry.getValue();
			var isMale = solverResult.males.contains(pid);

			var bday = solverResult.birthdays.get(pid);
			Person person = null;

			// Pass 0: Pivot mapping match (exact EMF object identity & matching gender)
			var targetMem = pidToMember.get(pid);
			if (targetMem != null) {
				for (var m : pivot.getMapping()) {
					if (m.getMMap() == targetMem && m.getPMap() != null && ((m.getPMap() instanceof Male) == isMale) && existingPersons.contains(m.getPMap())) {
						person = m.getPMap();
						existingPersons.remove(person);
						break;
					}
				}
			}

			// Pass 1: exact match on name, gender, AND birthday
			if (person == null) {
				for (var ep : existingPersons) {
					if (personName.equals(ep.getName()) && (ep instanceof Male) == isMale) {
						var epBdayStr = (ep.getBirthday() != null) ? new SimpleDateFormat("yyyy-MM-dd").format(ep.getBirthday()) : "DEFAULT";
						if ("0001-01-01".equals(epBdayStr)) epBdayStr = "DEFAULT";
						var targetBdayStr = (bday != null && !"DEFAULT".equals(bday)) ? bday : "DEFAULT";
						if (targetBdayStr.equals(epBdayStr)) {
							person = ep;
							existingPersons.remove(ep);
							break;
						}
					}
				}
			}
			// Pass 2: match on name and gender (preferring unmapped existingPersons)
			if (person == null) {
				for (var ep : existingPersons) {
					if (personName.equals(ep.getName()) && (ep instanceof Male) == isMale && !isPersonMapped(ep)) {
						person = ep;
						existingPersons.remove(ep);
						break;
					}
				}
			}
			if (person == null) {
				for (var ep : existingPersons) {
					if (personName.equals(ep.getName()) && (ep instanceof Male) == isMale) {
						person = ep;
						existingPersons.remove(ep);
						break;
					}
				}
			}
			if (person == null) {
				for (var ep : existingPersons) {
					if ((ep instanceof Male) == isMale && !isPersonMapped(ep)) {
						person = ep;
						existingPersons.remove(ep);
						break;
					}
				}
			}
			if (person == null) {
				for (var ep : existingPersons) {
					if ((ep instanceof Male) == isMale) {
						person = ep;
						existingPersons.remove(ep);
						break;
					}
				}
			}
			if (person == null) {
				person = isMale ? PersonsFactory.eINSTANCE.createMale() : PersonsFactory.eINSTANCE.createFemale();
			}

			person.setName(personName);
			if (bday != null && !"DEFAULT".equals(bday)) {
				try {
					person.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(bday));
				} catch (Exception e) {
					// ignore
				}
			} else {
				var defaultDate = (java.util.Date) EcoreFactory.eINSTANCE.createFromString(
						EcorePackage.eINSTANCE.getEDate(), "0000-1-1");
				person.setBirthday(defaultDate);
			}
			targetReg.getPersons().add(person);
			activePersonsByBId.put(pid, person);
		}

		// 2. Update Families in sourceReg
		var activeFamiliesByBId = new HashMap<String, Family>();
		var existingFamilies = new ArrayList<>(sourceReg.getFamilies());

		for (var entry : solverResult.familyNames.entrySet()) {
			var fid = entry.getKey();
			var famName = entry.getValue();

			Family fam = null;
			for (var ef : existingFamilies) {
				if (famName.equals(ef.getName())) {
					fam = ef;
					existingFamilies.remove(ef);
					break;
				}
			}
			if (fam == null && !existingFamilies.isEmpty()) {
				fam = existingFamilies.remove(0);
			}
			if (fam == null) {
				fam = FamiliesFactory.eINSTANCE.createFamily();
				sourceReg.getFamilies().add(fam);
			}
			fam.setName(famName);
			// Do not clear family members upfront; reconcile in place to maintain EMF object identities
			activeFamiliesByBId.put(fid, fam);
		}

		for (var ef : existingFamilies) {
			EcoreUtil.delete(ef);
		}

		// Sync members in families preserving existing FamilyMember instances
		var usedMembers = new HashSet<FamilyMember>();
		for (var entry : solverResult.memberNames.entrySet()) {
			var mid = entry.getKey();
			var memberName = entry.getValue();
			var famId = solverResult.theFather.get(mid);
			var isFather = (famId != null);
			if (!isFather) famId = solverResult.theMother.get(mid);
			var isMother = (!isFather && famId != null);
			if (!isFather && !isMother) famId = solverResult.theSons.get(mid);
			var isSon = (!isFather && !isMother && famId != null);
			if (!isFather && !isMother && !isSon) famId = solverResult.theDaughters.get(mid);

			if (famId != null) {
				var family = activeFamiliesByBId.get(famId);
				if (family != null) {
					if (isFather) {
						var m = family.getFather();
						if (m == null || usedMembers.contains(m)) {
							m = FamiliesFactory.eINSTANCE.createFamilyMember();
							family.setFather(m);
						}
						m.setName(memberName);
						usedMembers.add(m);
					} else if (isMother) {
						var m = family.getMother();
						if (m == null || usedMembers.contains(m)) {
							m = FamiliesFactory.eINSTANCE.createFamilyMember();
							family.setMother(m);
						}
						m.setName(memberName);
						usedMembers.add(m);
					} else if (isSon) {
						FamilyMember m = null;
						for (var s : family.getSons()) {
							if (memberName.equals(s.getName()) && !usedMembers.contains(s)) {
								m = s;
								break;
							}
						}
						if (m == null) {
							for (var s : family.getSons()) {
								if (!usedMembers.contains(s)) {
									m = s;
									break;
								}
							}
						}
						if (m == null) {
							m = FamiliesFactory.eINSTANCE.createFamilyMember();
							family.getSons().add(m);
						}
						m.setName(memberName);
						usedMembers.add(m);
					} else { // Daughter
						FamilyMember m = null;
						for (var d : family.getDaughters()) {
							if (memberName.equals(d.getName()) && !usedMembers.contains(d)) {
								m = d;
								break;
							}
						}
						if (m == null) {
							for (var d : family.getDaughters()) {
								if (!usedMembers.contains(d)) {
									m = d;
									break;
								}
							}
						}
						if (m == null) {
							m = FamiliesFactory.eINSTANCE.createFamilyMember();
							family.getDaughters().add(m);
						}
						m.setName(memberName);
						usedMembers.add(m);
					}
				}
			}
		}

		// Remove unused members
		for (var family : activeFamiliesByBId.values()) {
			if (family.getFather() != null && !usedMembers.contains(family.getFather())) {
				family.setFather(null);
			}
			if (family.getMother() != null && !usedMembers.contains(family.getMother())) {
				family.setMother(null);
			}
			var sonsToRemove = new ArrayList<FamilyMember>();
			for (var s : family.getSons()) {
				if (!usedMembers.contains(s)) sonsToRemove.add(s);
			}
			sonsToRemove.forEach(s -> EcoreUtil.delete(s));

			var daughtersToRemove = new ArrayList<FamilyMember>();
			for (var d : family.getDaughters()) {
				if (!usedMembers.contains(d)) daughtersToRemove.add(d);
			}
			daughtersToRemove.forEach(d -> EcoreUtil.delete(d));
		}

		// 3. Update Pivot Mappings
		var mappingsToRemove = new ArrayList<Mapping>();
		for (var m : pivot.getMapping()) {
			var memberDeleted = (m.getMMap() == null || m.getMMap().eContainer() == null);
			var personDeleted = (m.getPMap() == null || m.getPMap().eContainer() == null);
			if (memberDeleted || personDeleted) {
				mappingsToRemove.add(m);
			}
		}
		mappingsToRemove.forEach(m -> EcoreUtil.delete(m));

		for (var entry : solverResult.pMap.entrySet()) {
			var mappingId = entry.getKey();
			var pid = entry.getValue();
			var mid = solverResult.mMap.get(mappingId);

			var person = activePersonsByBId.get(pid);

			var memberName = solverResult.memberNames.get(mid);
			FamilyMember member = null;
			if (memberName != null) {
				String role = null;
				String fid = null;
				if (solverResult.theFather.containsKey(mid)) {
					role = "father";
					fid = solverResult.theFather.get(mid);
				} else if (solverResult.theMother.containsKey(mid)) {
					role = "mother";
					fid = solverResult.theMother.get(mid);
				} else if (solverResult.theSons.containsKey(mid)) {
					role = "sons";
					fid = solverResult.theSons.get(mid);
				} else if (solverResult.theDaughters.containsKey(mid)) {
					role = "daughters";
					fid = solverResult.theDaughters.get(mid);
				}

				var famName = (fid != null) ? solverResult.familyNames.get(fid) : null;
				var targetFam = (fid != null) ? activeFamiliesByBId.get(fid) : null;
				if (targetFam != null) {
					if ("father".equals(role) && targetFam.getFather() != null && memberName.equals(targetFam.getFather().getName())) {
						member = targetFam.getFather();
					} else if ("mother".equals(role) && targetFam.getMother() != null && memberName.equals(targetFam.getMother().getName())) {
						member = targetFam.getMother();
					} else if ("sons".equals(role)) {
						for (var s : targetFam.getSons()) {
							if (memberName.equals(s.getName())) {
								final var currentMember = s;
								var alreadyMapped = pivot.getMapping().stream().anyMatch(m -> m.getMMap() == currentMember && m.getPMap() != person);
								if (!alreadyMapped) {
									member = s;
									break;
								}
							}
						}
					} else if ("daughters".equals(role)) {
						for (var d : targetFam.getDaughters()) {
							if (memberName.equals(d.getName())) {
								final var currentMember = d;
								var alreadyMapped = pivot.getMapping().stream().anyMatch(m -> m.getMMap() == currentMember && m.getPMap() != person);
								if (!alreadyMapped) {
									member = d;
									break;
								}
							}
						}
					}
				}

				if (member == null) {
					for (var f : sourceReg.getFamilies()) {
						if (famName != null && !famName.equals(f.getName())) continue;
						if ("father".equals(role) && f.getFather() != null && memberName.equals(f.getFather().getName())) {
							member = f.getFather();
							break;
						}
						if ("mother".equals(role) && f.getMother() != null && memberName.equals(f.getMother().getName())) {
							member = f.getMother();
							break;
						}
						if ("sons".equals(role)) {
							for (var s : f.getSons()) {
								if (memberName.equals(s.getName())) {
									final var currentMember = s;
									var alreadyMapped = pivot.getMapping().stream().anyMatch(m -> m.getMMap() == currentMember && m.getPMap() != person);
									if (!alreadyMapped) {
										member = s;
										break;
									}
								}
							}
							if (member != null) break;
						}
						if ("daughters".equals(role)) {
							for (var d : f.getDaughters()) {
								if (memberName.equals(d.getName())) {
									final var currentMember = d;
									var alreadyMapped = pivot.getMapping().stream().anyMatch(m -> m.getMMap() == currentMember && m.getPMap() != person);
									if (!alreadyMapped) {
										member = d;
										break;
									}
								}
							}
							if (member != null) break;
						}
					}
				}
			}

			if (person != null && member != null) {
				var mapped = false;
				for (var m : pivot.getMapping()) {
					if (m.getMMap() == member && m.getPMap() == person) {
						mapped = true;
						break;
					}
				}
				if (!mapped) {
					var mapping = PivotFactory.eINSTANCE.createMapping();
					mapping.setMMap(member);
					mapping.setPMap(person);
					pivot.getMapping().add(mapping);
				}
			}
		}
	}

	private void terminateProcess(Process process) {
		if (process != null && process.isAlive()) {
			try {
				process.descendants().forEach(ProcessHandle::destroyForcibly);
			} catch (Exception ignored) {
			}
			process.destroyForcibly();
		}
	}

	private void syncSourceToTarget() {
		var mchName = generateBMachine(Strategy.FWD);
		var mchToExec = (mchName != null) ? mchName : "T1_BatchForward.mch";
		var solverResult = executeNativeProBAndParse(mchToExec);
		applyProBSolverResults(solverResult);
	}

	private void syncTargetToSource() {
		var mchName = generateBMachine(Strategy.BWD);
		var mchToExec = (mchName != null) ? mchName : "T2_BatchBackward.mch";
		var solverResult = executeNativeProBAndParse(mchToExec);
		applyProBSolverResults(solverResult);
	}

	private String generateConcurrentBMachine() {
		var modelDir = findFile("examples/familiestopersons/implementationArtefacts/bcert/pivot/model");
		if (modelDir == null || !modelDir.exists()) {
			return null;
		}

		var mchName = "TempConcurrent.mch";
		var tempFile = new File(modelDir, mchName);

		try (var writer = new PrintWriter(new FileWriter(tempFile))) {
			var sourceReg = getSourceModel();
			var targetReg = getTargetModel();

			writer.println("MACHINE TempConcurrent");
			writer.println("INCLUDES MetaModel");
			writer.println("DEFINITIONS");
			writer.println("  SET_PREF_MAX_OPERATIONS == 100000 ;");
			writer.println("  \"definitions.def\" ;");
			writer.println("  \"LibraryStrings.def\" ;");
			writer.println("  \"match.def\" ;");
			writer.println("  \"BatchBwd.def\" ;");
			writer.println("  \"concurrent.def\"");
			writer.println("SETS");
			writer.println("  RULE = {");
			writer.println("    ConcurrentDeleteMatched_,");
			writer.println("    ConcurrentMatchMemberPerson_,");
			writer.println("    ConcurrentTargetRenameWins_,");
			writer.println("    Member2Person_,");
			writer.println("    ForwardDelete_,");
			writer.println("    ForwardRename_,");
			writer.println("    Person2MemberExistingFamily_,");
			writer.println("    Person2MemberNewFamily_,");
			writer.println("    BackwardDelete_,");
			writer.println("    BackwardRenameMemberName_");
			writer.println("  }");
			writer.println("INITIALISATION");
			writer.println("  Reset");
			writer.println("OPERATIONS");

			var stmts = new ArrayList<String>();
			stmts.add("SetBWD");
			stmts.add("SetUnSync");

			var preferExistingFamily = (configurator != null) ? configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW) : true;
			var preferParent = (configurator != null) ? configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD) : true;
			var familyToNewBVal = (preferExistingFamily) ? "TRUE" : "FALSE";
			var parentToChildBVal = (preferParent) ? "TRUE" : "FALSE";
			stmts.add("MakeDecision(" + familyToNewBVal + ", " + parentToChildBVal + ")");

			var familyStmts = new ArrayList<String>();
			for (var fam : sourceReg.getFamilies()) {
				var famName = fam.getName();
				familyStmts.add("aFamily <-- FamilyNEW(\"" + famName + "\")");
				if (fam.getFather() != null) {
					familyStmts.add("AddFather(aFamily, \"" + fam.getFather().getName() + "\")");
				}
				if (fam.getMother() != null) {
					familyStmts.add("AddMother(aFamily, \"" + fam.getMother().getName() + "\")");
				}
				for (var son : fam.getSons()) {
					familyStmts.add("AddSon(aFamily, \"" + son.getName() + "\")");
				}
				for (var daughter : fam.getDaughters()) {
					familyStmts.add("AddDaughter(aFamily, \"" + daughter.getName() + "\")");
				}
			}

			if (!familyStmts.isEmpty()) {
				stmts.add("VAR aFamily IN\n      " + String.join(" ;\n      ", familyStmts) + "\n    END");
			}

			var personStmts = new ArrayList<String>();
			for (var person : targetReg.getPersons()) {
				var isMale = (person instanceof Male);
				personStmts.add("PersonNEW(\"" + person.getName() + "\", " + (isMale ? "TRUE" : "FALSE") + ")");
				if (person.getBirthday() != null) {
					var bdayStr = new SimpleDateFormat("yyyy-MM-dd").format(person.getBirthday());
					if (!bdayStr.isEmpty() && !"0001-01-01".equals(bdayStr)) {
						personStmts.add("SetBirthday(\"" + person.getName() + "\", \"" + bdayStr + "\")");
					}
				}
			}

			if (!personStmts.isEmpty()) {
				stmts.add("BEGIN\n      " + String.join(" ;\n      ", personStmts) + "\n    END");
			}

			writer.println("setupModel = ");
			writer.println("  PRE");
			writer.println("    Family = {} & Person = {}");
			writer.println("  THEN");
			writer.println("    " + String.join(" ||\n    ", stmts));
			writer.println("  END ;");

			writer.println("appliedRule <-- propagate =");
			writer.println("  SELECT  HasConcurrentDeleteMatched");
			writer.println("  THEN    ConcurrentDeleteMatched || appliedRule := ConcurrentDeleteMatched_");
			writer.println("  WHEN    HasConcurrentMatchMemberPerson");
			writer.println("  THEN    ConcurrentMatchMemberPerson || appliedRule := ConcurrentMatchMemberPerson_");
			writer.println("  WHEN    HasConcurrentTargetRenameWins");
			writer.println("  THEN    SetBWD ; ConcurrentTargetRenameWins ; appliedRule := ConcurrentTargetRenameWins_");
			writer.println("  ELSE");
			writer.println("    CHOICE");
			writer.println("      SetFWD ;");
			writer.println("      SELECT  HasForwardDelete");
			writer.println("      THEN    ForwardDelete || appliedRule := ForwardDelete_");
			writer.println("      WHEN    HasForwardRename");
			writer.println("      THEN    ForwardRename || appliedRule := ForwardRename_");
			writer.println("      ELSE    Member2Person || appliedRule := Member2Person_");
			writer.println("      END");
			writer.println("    OR");
			writer.println("      SetBWD ;");
			writer.println("      SELECT  HasBackwardDelete");
			writer.println("      THEN    BackwardDelete || appliedRule := BackwardDelete_");
			writer.println("      WHEN    HasBackwardRename");
			writer.println("      THEN    BackwardRenameMemberName || appliedRule := BackwardRenameMemberName_");
			writer.println("      ELSE");
			writer.println("        CHOICE");
			writer.println("          Person2MemberExistingFamily || appliedRule := Person2MemberExistingFamily_");
			writer.println("        OR");
			writer.println("          Person2MemberNewFamily || appliedRule := Person2MemberNewFamily_");
			writer.println("        END");
			writer.println("      END");
			writer.println("    END");
			writer.println("  END;");

			writer.println("fwd = BEGIN SetFWD END ;");
			writer.println("bwd = BEGIN SetBWD END ;");
			writer.println("sync = BEGIN SetSync END;");
			writer.println("unsync = BEGIN SetUnSync END");
			writer.println("END");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return tempFile.getName();
	}

	private void syncConcurrent() {
		var mchName = generateConcurrentBMachine();
		var mchToExec = (mchName != null) ? mchName : "T5_Concurrent.mch";
		var solverResult = executeNativeProBAndParse(mchToExec);
		applyProBSolverResults(solverResult);
	}

	private List<FamilyMember> collectMembers(Family family) {
		var list = new ArrayList<FamilyMember>();
		if (family.getFather() != null)
			list.add(family.getFather());
		if (family.getMother() != null)
			list.add(family.getMother());
		list.addAll(family.getSons());
		list.addAll(family.getDaughters());
		return list;
	}

	private boolean isMemberMapped(FamilyMember member) {
		return pivot.getMapping().stream().anyMatch(m -> m.getMMap() == member);
	}

	private boolean isPersonMapped(Person person) {
		return pivot.getMapping().stream().anyMatch(m -> m.getPMap() == person);
	}

	private boolean isMappedInPivot(FamilyMember member) {
		return pivot.getMapping().stream().anyMatch(m -> m.getMMap() == member && m.getPMap() != null && m.getPMap().eContainer() != null && getTargetModel().getPersons().contains(m.getPMap()));
	}

	private boolean isGenderMatching(FamilyMember member, Person person) {
		var isMaleMember = (member.eContainingFeature() == FamiliesPackage.eINSTANCE.getFamily_Father()
				|| member.eContainingFeature() == FamiliesPackage.eINSTANCE.getFamily_Sons());
		return (isMaleMember && person instanceof Male) || (!isMaleMember && person instanceof Female);
	}

	private void createPersonForMember(Family fam, FamilyMember member, String name, PersonRegister targetReg) {
		var isMale = (member.eContainingFeature() == FamiliesPackage.eINSTANCE.getFamily_Father()
				|| member.eContainingFeature() == FamiliesPackage.eINSTANCE.getFamily_Sons());

		Person person = isMale ? PersonsFactory.eINSTANCE.createMale() : PersonsFactory.eINSTANCE.createFemale();
		person.setName(name);
		targetReg.getPersons().add(person);

		var mapping = PivotFactory.eINSTANCE.createMapping();
		mapping.setMMap(member);
		mapping.setPMap(person);
		pivot.getMapping().add(mapping);
	}

	private void repairGender(Mapping mapping, FamilyMember member, String name, PersonRegister targetReg) {
		var oldPerson = mapping.getPMap();
		var isMale = (member.eContainingFeature() == FamiliesPackage.eINSTANCE.getFamily_Father()
				|| member.eContainingFeature() == FamiliesPackage.eINSTANCE.getFamily_Sons());

		Person newPerson = isMale ? PersonsFactory.eINSTANCE.createMale() : PersonsFactory.eINSTANCE.createFemale();
		newPerson.setName(name);
		if (oldPerson != null) {
			newPerson.setBirthday(oldPerson.getBirthday());
			EcoreUtil.delete(oldPerson);
		}
		targetReg.getPersons().add(newPerson);
		mapping.setPMap(newPerson);
	}

	private void createMemberForPerson(Person person, FamilyRegister sourceReg, Map<String, Family> familyMap) {
		var nameParts = parsePersonName(person.getName());
		var famName = nameParts[0];
		var givenName = nameParts[1];

		var preferExisting = pivot.isFAMILY_TO_NEW();
		Family fam = null;

		if (preferExisting && familyMap != null) {
			fam = familyMap.get(famName);
		} else if (preferExisting) {
			fam = sourceReg.getFamilies().stream()
					.filter(f -> famName.equals(f.getName()))
					.findFirst()
					.orElse(null);
		}

		if (fam == null) {
			fam = FamiliesFactory.eINSTANCE.createFamily();
			fam.setName(famName);
			sourceReg.getFamilies().add(fam);
			if (familyMap != null) {
				familyMap.put(famName, fam);
			}
		}

		var member = FamiliesFactory.eINSTANCE.createFamilyMember();
		member.setName(givenName);

		var isMale = (person instanceof Male);
		var preferParent = pivot.isPARENT_TO_CHILD();

		if (isMale) {
			if (preferParent && fam.getFather() == null) {
				fam.setFather(member);
			} else {
				fam.getSons().add(member);
			}
		} else {
			if (preferParent && fam.getMother() == null) {
				fam.setMother(member);
			} else {
				fam.getDaughters().add(member);
			}
		}

		var mapping = PivotFactory.eINSTANCE.createMapping();
		mapping.setMMap(member);
		mapping.setPMap(person);
		pivot.getMapping().add(mapping);
	}

	private void moveMemberToFamily(FamilyMember member, Family currentFam, String targetFamName,
			FamilyRegister sourceReg) {
		var targetFam = sourceReg.getFamilies().stream().filter(f -> targetFamName.equals(f.getName())).findFirst()
				.orElse(null);

		if (targetFam == null) {
			targetFam = FamiliesFactory.eINSTANCE.createFamily();
			targetFam.setName(targetFamName);
			sourceReg.getFamilies().add(targetFam);
		}

		EcoreUtil.remove(member);
		var isMale = (member.eContainingFeature() == FamiliesPackage.eINSTANCE.getFamily_Father()
				|| member.eContainingFeature() == FamiliesPackage.eINSTANCE.getFamily_Sons());
		var preferParent = pivot.isPARENT_TO_CHILD();

		if (isMale) {
			if (preferParent && targetFam.getFather() == null) {
				targetFam.setFather(member);
			} else {
				targetFam.getSons().add(member);
			}
		} else {
			if (preferParent && targetFam.getMother() == null) {
				targetFam.setMother(member);
			} else {
				targetFam.getDaughters().add(member);
			}
		}

		if (currentFam.getFather() == null && currentFam.getMother() == null
				&& currentFam.getSons().isEmpty() && currentFam.getDaughters().isEmpty()) {
			EcoreUtil.delete(currentFam);
		}
	}

	private String[] parsePersonName(String fullName) {
		if (fullName == null || !fullName.contains(",")) {
			return new String[] { "Unknown", fullName != null ? fullName : "" };
		}
		var parts = fullName.split(",", 2);
		return new String[] { parts[0].trim(), parts[1].trim() };
	}
}
