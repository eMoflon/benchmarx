package org.benchmarx.families.core;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.benchmarx.edit.IEdit;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import Families.FamiliesPackage;
import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;

public class FamilyHelper {
	private FamilyRegisterBuilder builder;
	private FamilyRegister register;
	private FamilyMember firstBart;
	private Runnable startNewEdit;
	private Supplier<IEdit<FamilyRegister>> getEdit;
	private BiConsumer<EAttribute, List<?>> changeAttribute;

	public FamilyHelper(
			FamilyRegister register,
			Runnable startNewEdit, 
			Supplier<IEdit<FamilyRegister>> getEdit, 
			Consumer<EObject> createNode, 
			BiConsumer<EReference, List<EObject>> createEdge,
			BiConsumer<EAttribute, List<?>> changeAttribute
		) {
		firstBart = null;
		builder = new FamilyRegisterBuilder(register, createNode, createEdge);
		this.register = register;
		this.startNewEdit = startNewEdit;
		this.getEdit = getEdit;
		this.changeAttribute = changeAttribute;
	}

	private Family getFromRegister(String name) {
		Optional<Family> family = register.getFamilies().stream().filter(f -> f.getName().equals(name)).findAny();

		assertTrue(family.isPresent());
		return family.get();
	}

	private Family getSimpsonFamily() {
		Optional<Family> family = register.getFamilies().stream()
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

	private IEdit<FamilyRegister> edit(Runnable operations) {
		startNewEdit.run();
		operations.run();
		return getEdit.get();
	}

	public IEdit<FamilyRegister> createSkinnerFamily() {
		return edit(() -> builder.family("Skinner"));
	}

	public IEdit<FamilyRegister> createFlandersFamily() {
		return edit(() -> builder.family("Flanders"));
	}

	public IEdit<FamilyRegister> createFatherNed() {
		Family family = getFromRegister("Flanders");
		assertTrue(family.getName().equals("Flanders"));
		return edit(() -> builder.family(family).father("Ned"));
	}

	public IEdit<FamilyRegister> createMotherMaude() {
		Family family = getFromRegister("Flanders");
		assertTrue(family.getName().equals("Flanders"));
		return edit(() -> builder.family(family).mother("Maude"));
	}

	public IEdit<FamilyRegister> createSonTodd() {
		Family family = getFromRegister("Flanders");
		assertTrue(family.getName().equals("Flanders"));
		return edit(() -> builder.family(family).son("Todd"));
	}

	public IEdit<FamilyRegister> createSonRod() {
		Family family = getFromRegister("Flanders");
		assertTrue(family.getName().equals("Flanders"));
		return edit(() -> builder.family(family).son("Rod"));
	}

	public IEdit<FamilyRegister> createSimpsonFamily() {
		return edit(() -> builder.family("Simpson"));
	}

	public IEdit<FamilyRegister> createFatherHomer() {
		Optional<Family> family = register.getFamilies().stream()
				.filter(f -> f.getName().equals("Simpson") && f.getFather() == null).findAny();

		assertTrue(family.isPresent());
		Family fam = family.get();

		return edit(() -> builder.family(fam).father("Homer"));
	}

	public IEdit<FamilyRegister> createMotherMarge() {
		Family family = getSimpsonFamily();
		return edit(() -> builder.family(family).mother("Marge"));
	}

	public IEdit<FamilyRegister> createSonBart() {
		Family family = getSimpsonFamily();
		var edit = edit(() -> builder.family(family).son("Bart"));
		if (firstBart == null) {
			firstBart = family.getSons().get(0);
		}
		return edit;
	}

	public IEdit<FamilyRegister> createDaughterLisa() {
		Family family = getSimpsonFamily();
		return edit(() -> builder.family(family).daughter("Lisa"));
	}

	public IEdit<FamilyRegister> createDaughterMaggie() {
		Family family = getSimpsonFamily();
		return edit(() -> builder.family(family).daughter("Maggie"));
	}

	public IEdit<FamilyRegister> createFatherBart() {
		Optional<Family> family = register.getFamilies().stream()
				.filter(f -> f.getName().equals("Simpson") && f.getFather() == null).findAny();

		assertTrue(family.isPresent());
		Family fam = family.get();
		return edit(() -> builder.family(fam).father("Bart"));
	}

	public IEdit<FamilyRegister> createNewFamilySimpsonWithMembers() {
		return createSimpsonFamily()//
				.andThen(createFatherHomer())//
				.andThen(createMotherMarge())//
				.andThen(createSonBart())//
				.andThen(createDaughterLisa())//
				.andThen(createDaughterMaggie());
	}

	// helpers required for incremental behavior

	public void deleteFirstSonBart() {
		if (firstBartCanBeIdentifiedInRegister())
			// FIXME: Create edit for deletion
			EcoreUtil.delete(firstBart, true);
		else {
			// Unable to locate firstBart via object identity, so rely on position-based
			// heuristics
			Family family = getSimpsonFamily();
			assertTrue(family.getName().equals("Simpson"));
			// FIXME: Create edit for deletion
			EcoreUtil.delete(family.getSons().get(0), true);
		}
	}

	private boolean firstBartCanBeIdentifiedInRegister() {
		return firstBart != null && firstBart.getSonsInverse().getFamiliesInverse().equals(register);
	}

	public IEdit<FamilyRegister> renameEmptySimpsonToBouvier() {
		Family fam = getFromRegister("Simpson");
		assertTrue(fam.getName().equals("Simpson"));

		return edit(() -> {
			changeAttribute.accept(FamiliesPackage.Literals.FAMILY__NAME, List.of(fam, "Simpson", "Bouvier"));
			fam.setName("Bouvier");
		});
	}

	public IEdit<FamilyRegister> renameSimpsonToBouvier() {
		Family family = getSimpsonFamily();
		assertTrue(family.getName().equals("Simpson"));

		// FIXME: Create edit
		return edit(() -> family.setName("Bouvier"));
	}

	public IEdit<FamilyRegister> moveLisa() {
		Family fam = getFromRegister("Flanders");
		FamilyMember lisa = getLisa();

		// FIXME: Create edit
		return edit(() -> fam.setMother(lisa));
	}

	public IEdit<FamilyRegister> moveMaggieAndChangeRole() {
		Family fam = getFromRegister("Flanders");
		FamilyMember maggie = getMaggie();

		// FIXME: Create edit
		return edit(() -> fam.getSons().add(maggie));
	}

	public IEdit<FamilyRegister> moveMarge() {
		Family skinner = getFromRegister("Skinner");
		FamilyMember marge = getSimpsonFamily().getMother();

		// FIXME: Create edit
		return edit(() -> skinner.setMother(marge));
	}

	public IEdit<FamilyRegister> deleteFatherHomer() {
		Family simpson = getSimpsonFamily();
		FamilyMember homer = simpson.getFather();
		return edit(() -> deleteMemberFromFamily(simpson, homer));
	}

	private void deleteMemberFromFamily(Family f, FamilyMember m) {
		// FIXME: Create edit
		EcoreUtil.delete(m, true);
	}

	public IEdit<FamilyRegister> idleDelta() {
		return IEdit.idleEdit();
	}

	public IEdit<FamilyRegister> hippocraticDelta() {
		return edit(() -> builder.family("Van Houten"));
	}
}
