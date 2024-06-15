package org.benchmarx.families.smartemf.core;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.emoflon.smartemf.runtime.util.SmartEMFUtil;

import FamiliesSmartEMF.FamiliesSmartEMFPackage;
import FamiliesSmartEMF.Family;
import FamiliesSmartEMF.FamilyMember;
import FamiliesSmartEMF.FamilyRegister;

public class FamilyHelper {
	private FamilyRegisterBuilder builder;
	private Supplier<FamilyRegister> register;
	private BiConsumer<EAttribute /* attribute type */, List<?> /* [owning node, old value, new value] */> changeAttribute;
	private Consumer<EObject> deleteNode;
	private BiConsumer<EObject, List<EObject>
	/*
	 * [old parent, old connection (ERef), new parent, new connection (ERef)]
	 */> moveNode;
	private BiConsumer<EReference, List<EObject> /* [source, target] */> deleteEdge;
	@SuppressWarnings("unused")
	private BiConsumer<EReference, List<EObject /* [source, target] */>> createEdge;

	private FamilyMember firstBart;

	public FamilyHelper(Supplier<FamilyRegister> register, Consumer<EObject> createNode,
			BiConsumer<EReference, List<EObject>> createEdge, BiConsumer<EAttribute, List<?>> changeAttribute,
			Consumer<EObject> deleteNode, BiConsumer<EObject, List<EObject>> moveNode,
			BiConsumer<EReference, List<EObject>> deleteEdge) {
		builder = new FamilyRegisterBuilder(register, createNode, createEdge);
		this.register = register;
		this.changeAttribute = changeAttribute;
		this.deleteEdge = deleteEdge;
		this.deleteNode = deleteNode;
		this.moveNode = moveNode;
		this.createEdge = createEdge;
	}

	private Family getFromRegister(String name) {
		Optional<Family> family = register.get().getFamilies().stream().filter(f -> f.getName().equals(name)).findAny();

		assertTrue(family.isPresent());
		return family.get();
	}

	private Family getSimpsonFamily() {
		Optional<Family> family = register.get().getFamilies().stream()
				.filter(f -> f.getName().equals("Simpson") && f.getFather().getName().equals("Homer")).findAny();

		assertTrue(family.isPresent());

		Family fam = family.get();
		assertTrue(fam.getName().equals("Simpson"));
		assertTrue(fam.getFather().getName().equals("Homer"));

		return fam;
	}

	private FamilyMember getLisa() {
		Family fam = getSimpsonFamily();
		Optional<FamilyMember> liz = fam.getDaughters().stream().filter(f -> f.getName().equals("Lisa")).findAny();

		assertTrue(liz.isPresent());
		FamilyMember lisa = liz.get();
		assertTrue(lisa.getName().equals("Lisa"));
		return lisa;
	}

	private FamilyMember getMaggie() {
		Family fam = getSimpsonFamily();
		Optional<FamilyMember> mag = fam.getDaughters().stream().filter(f -> f.getName().equals("Maggie")).findAny();

		assertTrue(mag.isPresent());
		FamilyMember maggie = mag.get();
		assertTrue(maggie.getName().equals("Maggie"));
		return maggie;
	}

	// helpers required for basic behavior

	public void createSkinnerFamily() {
		builder.family("Skinner");
	}

	public void createFlandersFamily() {
		builder.family("Flanders");
	}

	public void createFatherNed() {
		Family family = getFromRegister("Flanders");
		assertTrue(family.getName().equals("Flanders"));
		builder.family(family).father("Ned");
	}

	public void createMotherMaude() {
		Family family = getFromRegister("Flanders");
		assertTrue(family.getName().equals("Flanders"));
		builder.family(family).mother("Maude");
	}

	public void createSonTodd() {
		Family family = getFromRegister("Flanders");
		assertTrue(family.getName().equals("Flanders"));
		builder.family(family).son("Todd");
	}

	public void createSonRod() {
		Family family = getFromRegister("Flanders");
		assertTrue(family.getName().equals("Flanders"));
		builder.family(family).son("Rod");
	}

	public void createSimpsonFamily() {
		builder.family("Simpson");
	}

	public void createSimpsonFamiliesWithMembers(int nrOfFamilies) {
		for (int i = 0; i < nrOfFamilies; i++) {
			var fam = builder.family("Simpson_" + i);
			fam.father("Homer");
			fam.mother("Marge");
			fam.son("Bart");
			fam.daughter("Maggie");
			fam.daughter("Lisa");
		}
	}

	public void createFatherHomer() {
		Optional<Family> family = register.get().getFamilies().stream()
				.filter(f -> f.getName().equals("Simpson") && f.getFather() == null).findAny();

		assertTrue(family.isPresent());
		Family fam = family.get();

		builder.family(fam).father("Homer");
	}

	public void createMotherMarge() {
		Family family = getSimpsonFamily();
		builder.family(family).mother("Marge");
	}

	public void createSonBart() {
		Family family = getSimpsonFamily();
		builder.family(family).son("Bart");
		if (firstBart == null) {
			firstBart = family.getSons().get(0);
		}
	}

	public void createDaughterLisa() {
		Family family = getSimpsonFamily();
		builder.family(family).daughter("Lisa");
	}

	public void createDaughterMaggie() {
		Family family = getSimpsonFamily();
		builder.family(family).daughter("Maggie");
	}

	public void createFatherBart() {
		Optional<Family> family = register.get().getFamilies().stream()
				.filter(f -> f.getName().equals("Simpson") && f.getFather() == null).findAny();

		assertTrue(family.isPresent());
		Family fam = family.get();
		builder.family(fam).father("Bart");
	}

	public void createNewFamilySimpsonWithMembers() {
		createSimpsonFamily();
		createFatherHomer();
		createMotherMarge();
		createSonBart();
		createDaughterLisa();
		createDaughterMaggie();
	}
	
	public void createNewFamilyFlandersWithMembers() {
		createFlandersFamily();
		createFatherNed();
		createMotherMaude();
		createSonRod();
		createSonTodd();
	}

	// helpers required for incremental behavior

	public void deleteFirstSonBart() {
		if (firstBartCanBeIdentifiedInRegister())
			deleteMemberFromFamily(FamiliesSmartEMFPackage.Literals.FAMILY__SONS, firstBart.getSonsInverse(), firstBart);
		else {
			// Unable to locate firstBart via object identity, so rely on position-based
			// heuristics
			Family family = getSimpsonFamily();
			assertTrue(family.getName().equals("Simpson"));
			deleteMemberFromFamily(FamiliesSmartEMFPackage.Literals.FAMILY__SONS, family, family.getSons().get(0));
		}
	}
	
	public void moveLisaToFlandersAsDaughter() {
		Family fam = getFromRegister("Flanders");
		FamilyMember lisa = getLisa();

		moveNode.accept(lisa, List.of(lisa.getDaughtersInverse(), FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS, fam,
				FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS));
		
		fam.getDaughters().add(lisa);
	}
	
	public void deleteLisa() {
		var lisa = getLisa();
		deleteMemberFromFamily(FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS, lisa.getDaughtersInverse(), lisa);
	}
	
	public void nameChangeOfLisa() {
		var lisa = getLisa();
		changeAttribute.accept(FamiliesSmartEMFPackage.Literals.FAMILY_MEMBER__NAME, List.of(lisa, "Lisa", "Mary"));

	}

	private boolean firstBartCanBeIdentifiedInRegister() {
		return firstBart != null && firstBart.getSonsInverse().getFamiliesInverse().equals(register.get());
	}

	public void renameEmptySimpsonToBouvier() {
		Family fam = getFromRegister("Simpson");
		assertTrue(fam.getName().equals("Simpson"));

		changeAttribute.accept(FamiliesSmartEMFPackage.Literals.FAMILY__NAME, List.of(fam, "Simpson", "Bouvier"));
		fam.setName("Bouvier");
	}

	public void renameSimpsonToBouvier() {
		Family family = getSimpsonFamily();
		assertTrue(family.getName().equals("Simpson"));

		changeAttribute.accept(FamiliesSmartEMFPackage.Literals.FAMILY__NAME, List.of(family, "Simpson", "Bouvier"));
		family.setName("Bouvier");
	}

	public void renameFlandersFamilyToBouvier() {
		Family family = getFromRegister("Flanders");
		assertTrue(family.getName().equals("Flanders"));

		changeAttribute.accept(FamiliesSmartEMFPackage.Literals.FAMILY__NAME, List.of(family, "Flanders", "Bouvier"));
		family.setName("Bouvier");
	}

	public void moveLisa() {
		Family fam = getFromRegister("Flanders");
		FamilyMember lisa = getLisa();

		moveNode.accept(lisa, List.of(lisa.getDaughtersInverse(), FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS, fam,
				FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER));

		fam.setMother(lisa);
	}

	public void moveMaggieAndChangeRole() {
		Family fam = getFromRegister("Flanders");
		FamilyMember maggie = getMaggie();

		moveNode.accept(maggie, List.of(maggie.getDaughtersInverse(), FamiliesSmartEMFPackage.Literals.FAMILY__DAUGHTERS, fam,
				FamiliesSmartEMFPackage.Literals.FAMILY__SONS));

		fam.getSons().add(maggie);
	}

	public void moveMarge() {
		Family skinner = getFromRegister("Skinner");
		FamilyMember marge = getSimpsonFamily().getMother();

		moveNode.accept(marge, List.of(marge.getMotherInverse(), FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER, skinner,
				FamiliesSmartEMFPackage.Literals.FAMILY__MOTHER));

		skinner.setMother(marge);
	}

	public void deleteFatherHomer() {
		Family simpson = getSimpsonFamily();
		FamilyMember homer = simpson.getFather();
		deleteMemberFromFamily(FamiliesSmartEMFPackage.Literals.FAMILY__FATHER, simpson, homer);
	}

	public void deleteRodAsSon() {
		Family flanders = getFromRegister("Flanders");
		FamilyMember rod = flanders.getSons().get(0);
		deleteMemberFromFamily(FamiliesSmartEMFPackage.Literals.FAMILY__SONS, flanders, rod);
	}

	private void deleteMemberFromFamily(EReference ref, Family f, FamilyMember m) {
		deleteEdge.accept(ref, List.of(f, m));
		deleteNode.accept(m);
		SmartEMFUtil.deleteNode(m, true);
	}

	public void idleDelta() {

	}

	public void hippocraticDelta() {
		builder.family("Van Houten");
	}

	public void createOneFamilyMember() {
		Family f = register.get().getFamilies().get(0);
		builder.family(f).daughter("Johanna");
	}
}
