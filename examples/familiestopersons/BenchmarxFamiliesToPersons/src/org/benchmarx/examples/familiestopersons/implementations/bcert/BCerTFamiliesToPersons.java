package org.benchmarx.examples.familiestopersons.implementations.bcert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	private void syncSourceToTarget() {
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

		for (var person : targetReg.getPersons()) {
			if (!mappedPersons.containsKey(person)) {
				createMemberForPerson(person, sourceReg);
			}
		}
	}

	private void syncConcurrent() {
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

	private List<FamilyMember> collectMembers(Family fam) {
		var members = new ArrayList<FamilyMember>();
		if (fam.getFather() != null) members.add(fam.getFather());
		if (fam.getMother() != null) members.add(fam.getMother());
		members.addAll(fam.getSons());
		members.addAll(fam.getDaughters());
		return members;
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

	private void createMemberForPerson(Person person, FamilyRegister sourceReg) {
		var nameParts = parsePersonName(person.getName());
		var famName = nameParts[0];
		var givenName = nameParts[1];

		var preferExisting = !pivot.isFAMILY_TO_NEW();
		Family fam = null;

		if (preferExisting) {
			fam = sourceReg.getFamilies().stream()
					.filter(f -> famName.equals(f.getName()))
					.findFirst()
					.orElse(null);
		}

		if (fam == null) {
			fam = FamiliesFactory.eINSTANCE.createFamily();
			fam.setName(famName);
			sourceReg.getFamilies().add(fam);
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

	private void moveMemberToFamily(FamilyMember member, Family currentFam, String newFamName, FamilyRegister sourceReg) {
		var preferExisting = !pivot.isFAMILY_TO_NEW();
		Family targetFam = null;

		if (preferExisting) {
			targetFam = sourceReg.getFamilies().stream()
					.filter(f -> newFamName.equals(f.getName()))
					.findFirst()
					.orElse(null);
		}

		if (targetFam == null) {
			targetFam = FamiliesFactory.eINSTANCE.createFamily();
			targetFam.setName(newFamName);
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
