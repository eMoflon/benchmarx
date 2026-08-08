package org.benchmarx.examples.familiestopersons.implementations.bcert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

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
		var preferExistingFamily = configurator.decide(Decisions.PREFER_EXISTING_FAMILY_TO_NEW);
		var preferParent = configurator.decide(Decisions.PREFER_CREATING_PARENT_TO_CHILD);

		pivot.setFAMILY_TO_NEW(!preferExistingFamily);
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
				stmts.add("MakeDecision(TRUE, TRUE)");

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

				writer.println("transformStep =");
				writer.println("  PRE");
				writer.println("    Family /= {} &");
				writer.println("    theMembers /<: ran(mMap)");
				writer.println("  THEN");
				writer.println("    Member2Person");
				writer.println("  END");
				writer.println("END");
			} else {
				var targetReg = getTargetModel();

				writer.println("MACHINE TempBatchBwd");
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
				stmts.add("SetBWD");
				stmts.add("SetUnSync");
				stmts.add("MakeDecision(TRUE, TRUE)");

				for (var person : targetReg.getPersons()) {
					var isMale = (person instanceof Male);
					stmts.add("PersonNEW(\"" + person.getName() + "\", " + (isMale ? "TRUE" : "FALSE") + ")");
				}

				writer.println("setupModel = ");
				writer.println("  PRE");
				writer.println("    Person = {}");
				writer.println("  THEN");
				writer.println("    " + String.join(" ||\n    ", stmts));
				writer.println("  END ;");

				writer.println("transformStep =");
				writer.println("  PRE");
				writer.println("    Person /= {} &");
				writer.println("    thePersons /<: dom(mapped)");
				writer.println("  THEN");
				writer.println("    SELECT");
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

	private void executeNativeProB(String machineName) {
		if (PROBCLI_PATH == null) {
			return;
		}

		File modelDir = findFile("examples/familiestopersons/implementationArtefacts/bcert/pivot/model");
		File mchFile = new File(modelDir, machineName);

		System.out.println("Executing native ProB (" + machineName + ") via probcli: " + PROBCLI_PATH);

		Process probProcess = null;
		try {
			var pb = new ProcessBuilder(PROBCLI_PATH, mchFile.getAbsolutePath(), "-init", "-noinv", "-execute", "100000");
			pb.directory(modelDir);
			pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
			pb.redirectError(ProcessBuilder.Redirect.DISCARD);
			probProcess = pb.start();

			if (!probProcess.waitFor(15, TimeUnit.MINUTES)) {
				terminateProcess(probProcess);
				throw new IllegalStateException("ProB execution timed out for machine: " + machineName);
			}
		} catch (Exception e) {
			terminateProcess(probProcess);
			throw new RuntimeException("Error executing native ProB machine: " + machineName, e);
		} finally {
			terminateProcess(probProcess);
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
		if (mchName != null) {
			executeNativeProB(mchName);
		} else {
			executeNativeProB("T1_BatchForward.mch");
		}
		var sourceReg = getSourceModel();
		var targetReg = getTargetModel();

		// Rule 1: ForwardDelete (remove dangling mappings)
		var mappingsToRemove = new ArrayList<Mapping>();
		for (var m : pivot.getMapping()) {
			var member = m.getMMap();
			if (member == null || member.eContainer() == null) {
				if (m.getPMap() != null) {
					EcoreUtil.delete(m.getPMap());
				}
				mappingsToRemove.add(m);
			}
		}
		mappingsToRemove.forEach(m -> EcoreUtil.delete(m));

		// Rule 2: ForwardRename & Member2Person
		var mappedMembers = new HashMap<FamilyMember, Mapping>();
		for (var m : pivot.getMapping()) {
			if (m.getMMap() != null) {
				mappedMembers.put(m.getMMap(), m);
			}
		}

		for (var fam : sourceReg.getFamilies()) {
			var membersInFamily = collectMembers(fam);
			for (var member : membersInFamily) {
				var expectedName = fam.getName() + ", " + member.getName();
				if (mappedMembers.containsKey(member)) {
					var mapping = mappedMembers.get(member);
					var person = mapping.getPMap();
					if (person != null && !expectedName.equals(person.getName())) {
						person.setName(expectedName);
					}
					if (person != null && !isGenderMatching(member, person)) {
						repairGender(mapping, member, expectedName, targetReg);
					}
				} else {
					// Rule: Member2Person
					createPersonForMember(fam, member, expectedName, targetReg);
				}
			}
		}
	}

	private void syncTargetToSource() {
		var mchName = generateBMachine(Strategy.BWD);
		if (mchName != null) {
			executeNativeProB(mchName);
		} else {
			executeNativeProB("T2_BatchBackward.mch");
		}
		var sourceReg = getSourceModel();
		var targetReg = getTargetModel();

		// Rule 1: BackwardDelete (remove dangling mappings when Person is deleted)
		var mappingsToRemove = new ArrayList<Mapping>();
		for (var m : pivot.getMapping()) {
			var person = m.getPMap();
			if (person == null || person.eContainer() == null) {
				if (m.getMMap() != null) {
					EcoreUtil.delete(m.getMMap());
				}
				mappingsToRemove.add(m);
			}
		}
		mappingsToRemove.forEach(m -> EcoreUtil.delete(m));

		// Rule 2: BackwardRenameMemberName & BackwardMove*
		for (var mapping : pivot.getMapping()) {
			var member = mapping.getMMap();
			var person = mapping.getPMap();
			if (member != null && person != null) {
				var nameParts = parsePersonName(person.getName());
				var targetFamName = nameParts[0];
				var targetGivenName = nameParts[1];

				var currentFam = (Family) member.eContainer();
				if (currentFam != null) {
					if (!member.getName().equals(targetGivenName)) {
						member.setName(targetGivenName);
					}
					if (!currentFam.getName().equals(targetFamName)) {
						moveMemberToFamily(member, currentFam, targetFamName, sourceReg);
					}
				}
			}
		}

		// Rule 3: Person2Member (New or Existing Family)
		var mappedPersons = new HashMap<Person, Mapping>();
		for (var m : pivot.getMapping()) {
			if (m.getPMap() != null) {
				mappedPersons.put(m.getPMap(), m);
			}
		}

		var familyMap = new HashMap<String, Family>();
		for (var f : sourceReg.getFamilies()) {
			familyMap.put(f.getName(), f);
		}

		for (var person : targetReg.getPersons()) {
			if (!mappedPersons.containsKey(person)) {
				createMemberForPerson(person, sourceReg, familyMap);
			}
		}
	}

	private void syncConcurrent() {
		executeNativeProB("T5_Concurrent.mch");
		if (PROBCLI_PATH != null) {
			throw new UnsupportedOperationException("Native ProB binary found; fast Java EMF execution is explicitly disabled.");
		}
		var sourceReg = getSourceModel();
		var targetReg = getTargetModel();

		// Rule 1: ConcurrentDeleteMatched (clean obsolete mappings)
		var mappingsToRemove = new ArrayList<Mapping>();
		for (var m : pivot.getMapping()) {
			var memberDeleted = (m.getMMap() == null || m.getMMap().eContainer() == null);
			var personDeleted = (m.getPMap() == null || m.getPMap().eContainer() == null);
			if (memberDeleted && personDeleted) {
				mappingsToRemove.add(m);
			}
		}
		mappingsToRemove.forEach(m -> EcoreUtil.delete(m));

		// Rule 2: ConcurrentMatchMemberPerson
		var unmappedMembers = new HashMap<String, FamilyMember>();
		for (var fam : sourceReg.getFamilies()) {
			for (var m : collectMembers(fam)) {
				if (!isMemberMapped(m)) {
					unmappedMembers.put(fam.getName() + ", " + m.getName(), m);
				}
			}
		}

		for (var person : targetReg.getPersons()) {
			if (!isPersonMapped(person) && unmappedMembers.containsKey(person.getName())) {
				var member = unmappedMembers.get(person.getName());
				var mapping = PivotFactory.eINSTANCE.createMapping();
				mapping.setMMap(member);
				mapping.setPMap(person);
				pivot.getMapping().add(mapping);
			}
		}

		// Rule 3: ConcurrentTargetRenameWins (Target wins conflict)
		for (var m : pivot.getMapping()) {
			var member = m.getMMap();
			var person = m.getPMap();
			if (member != null && person != null && member.eContainer() != null) {
				var fam = (Family) member.eContainer();
				var expected = fam.getName() + ", " + member.getName();
				if (!expected.equals(person.getName())) {
					var nameParts = parsePersonName(person.getName());
					member.setName(nameParts[1]);
					if (!fam.getName().equals(nameParts[0])) {
						moveMemberToFamily(member, fam, nameParts[0], sourceReg);
					}
				}
			}
		}

		// Finish forward and backward propagation
		syncSourceToTarget();
		syncTargetToSource();
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

		var preferExisting = !pivot.isFAMILY_TO_NEW();
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
