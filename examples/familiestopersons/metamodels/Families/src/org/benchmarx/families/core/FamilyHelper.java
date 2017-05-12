package org.benchmarx.families.core;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;

import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;

public class FamilyHelper {
	
	private FamilyRegisterBuilder builder = null;
	private FamilyMember firstBart = null;
	
	private Family getFromRegister(String name, FamilyRegister register) {
		Optional<Family> family = register.getFamilies().stream()
				.filter(f -> f.getName().equals(name))
				.findAny();
				
		assertTrue(family.isPresent());
		return family.get();
	}
	
	private Family getSimpsonFamily(FamilyRegister register) {
		Optional<Family> family = register.getFamilies().stream()
				.filter(f -> f.getName().equals("Simpson") && f.getFather().getName().equals("Homer"))
				.findAny();
				
		assertTrue(family.isPresent());
		
		Family fam = family.get();
		assertTrue(fam.getName().equals("Simpson"));
		assertTrue(fam.getFather().getName().equals("Homer"));
		
		return fam;
	}
	
	private FamilyMember getLisa(FamilyRegister register) {
		Family fam = getSimpsonFamily(register);
		Optional<FamilyMember> liz = fam.getDaughters().stream()
				.filter(f -> f.getName().equals("Lisa"))
				.findAny();
		
		assertTrue(liz.isPresent());
		FamilyMember lisa = liz.get();
		assertTrue(lisa.getName().equals("Lisa"));
		return lisa;		
	}
	
	private FamilyMember getMaggie(FamilyRegister register) {
		Family fam = getSimpsonFamily(register);
		Optional<FamilyMember> mag = fam.getDaughters().stream()
				.filter(f -> f.getName().equals("Maggie"))
				.findAny();
		
		assertTrue(mag.isPresent());
		FamilyMember maggie = mag.get();
		assertTrue(maggie.getName().equals("Maggie"));
		return maggie;		
	}
	
	// helpers required for basic behavior
	
	public void createSkinnerFamily(FamilyRegister register) {
		builder = new FamilyRegisterBuilder(register);
		builder.family("Skinner");
	}

	public void createFlandersFamily(FamilyRegister register) {	
		builder = new FamilyRegisterBuilder(register);
		builder.family("Flanders");
	}
	
	public void createFatherNed(FamilyRegister register) {
		Family family = getFromRegister("Flanders", register);
		assertTrue(family.getName().equals("Flanders"));
		builder = new FamilyRegisterBuilder(register);
		builder.family(family).father("Ned");
	}
	
	public void createMotherMaude(FamilyRegister register) {
		Family family = getFromRegister("Flanders", register);
		assertTrue(family.getName().equals("Flanders"));
		builder = new FamilyRegisterBuilder(register);
		builder.family(family).mother("Maude");
	}
	
	public void createSonTodd(FamilyRegister register) {
		Family family = getFromRegister("Flanders", register);
		assertTrue(family.getName().equals("Flanders"));
		builder = new FamilyRegisterBuilder(register);
		builder.family(family).son("Todd");
	}
	
	public void createSonRod(FamilyRegister register) {
		Family family = getFromRegister("Flanders", register);
		assertTrue(family.getName().equals("Flanders"));
			
		builder = new FamilyRegisterBuilder(register);
		builder.family(family).son("Rod");
	}
	
	public void createSimpsonFamily(FamilyRegister register) {		
		builder = new FamilyRegisterBuilder(register);
		builder.family("Simpson");		
	}	
		
	public void createFatherHomer(FamilyRegister register) {
		Optional<Family> family = register.getFamilies().stream()
				.filter(f -> f.getName().equals("Simpson") && f.getFather() == null)
				.findAny();
				
		assertTrue(family.isPresent());
		Family fam = family.get();
				
		builder = new FamilyRegisterBuilder(register);
		builder.family(fam).father("Homer");		
	}
	
	public void createMotherMarge(FamilyRegister register) {
		Family family = getSimpsonFamily(register);
		builder = new FamilyRegisterBuilder(register);
		builder.family(family).mother("Marge");
	}
	
	public void createSonBart(FamilyRegister register) {
		Family family = getSimpsonFamily(register);
		builder = new FamilyRegisterBuilder(register);
		builder.family(family).son("Bart");
		if (firstBart == null) {
			firstBart = family.getSons().get(0);
		}
	}		
	
	public void createDaughterLisa(FamilyRegister register) {
		Family family = getSimpsonFamily(register);
		builder = new FamilyRegisterBuilder(register);
		builder.family(family).daughter("Lisa");
	}
	
	public void createDaughterMaggie(FamilyRegister register) {
		Family family = getSimpsonFamily(register);
		builder = new FamilyRegisterBuilder(register);
		builder.family(family).daughter("Maggie");
	}
	
	public void createFatherBart(FamilyRegister register) {
		Optional<Family> family = register.getFamilies().stream()
				.filter(f -> f.getName().equals("Simpson") && f.getFather() == null)
				.findAny();
				
		assertTrue(family.isPresent());
		Family fam = family.get();
		builder = new FamilyRegisterBuilder(register);
		builder.family(fam).father("Bart");
	}
	
	public void createNewFamilySimpsonWithMembers(FamilyRegister register) {
		createSimpsonFamily(register);
		createFatherHomer(register);
		createMotherMarge(register);
		createSonBart(register);
		createDaughterLisa(register);
		createDaughterMaggie(register);
	}	
	
	// helpers required for incremental behavior	
	
	public void deleteFirstSonBart(FamilyRegister register) {
		if (firstBartCanBeIdentifiedInRegister(register))
			EcoreUtil.delete(firstBart, true);
		else {
			// Unable to locate firstBart via object identity, so rely on position-based heuristics
			Family family = getSimpsonFamily(register);
			assertTrue(family.getName().equals("Simpson"));		
			EcoreUtil.delete(family.getSons().get(0), true);
		}
	}

	private boolean firstBartCanBeIdentifiedInRegister(FamilyRegister register) {
		return firstBart != null && firstBart.getSonsInverse().getFamiliesInverse().equals(register);
	}
	
	public void renameEmptySimpsonToBouvier(FamilyRegister register) {
		Family fam = getFromRegister("Simpson", register);
		assertTrue(fam.getName().equals("Simpson"));
		
		fam.setName("Bouvier");
	}
	
	public void renameSimpsonToBouvier(FamilyRegister register) {
		Family family = getSimpsonFamily(register);
		assertTrue(family.getName().equals("Simpson"));
		
		family.setName("Bouvier");
	}
	
	public void moveLisa(FamilyRegister register) {
		Family fam = getFromRegister("Flanders", register);
		FamilyMember lisa = getLisa(register);
		fam.setMother(lisa);
	}
	
	public void moveMaggieAndChangeRole(FamilyRegister register) {
		Family fam = getFromRegister("Flanders", register);
		FamilyMember maggie = getMaggie(register);
		fam.getSons().add(maggie);
	}
	
	public void moveMarge(FamilyRegister register) {
		Family skinner = getFromRegister("Skinner", register);
		FamilyMember marge = getSimpsonFamily(register).getMother();
		skinner.setMother(marge);
	}
	
	public void deleteFatherHomer(FamilyRegister register) {
		Family simpson = getSimpsonFamily(register);
		FamilyMember homer = simpson.getFather();
		
		EcoreUtil.delete(homer, true);
	}
	
	public void idleDelta(FamilyRegister register) {
		
	}
	
	public void hippocraticDelta(FamilyRegister register) {
		builder = new FamilyRegisterBuilder(register);
		builder.family("Van Houten");
	}
	
	
//	public void familyFatherHomerNameChange(FamilyRegister register){
//		Family family = getFromRegister("Simpson", register);
//		assertTrue(family.getName().equals("Simpson"));
//		
//		family.getFather().setName("Jay");
//	}
//	
//	public void familyMotherMargeNameChange(FamilyRegister register){
//		Family family = getFromRegister("Simpson", register);
//		assertTrue(family.getName().equals("Simpson"));
//		
//		family.getMother().setName("Julie");
//	}
//	
//	public void familyDaughterLisaNameChange(FamilyRegister register){
//		Family family = getFromRegister("Simpson", register);
//		assertTrue(family.getName().equals("Simpson"));
//		
//		family.getDaughters().get(0).setName("Nancy");
//	}	
//	
//	public void familySonBartNameChange(FamilyRegister register){
//		Family family = getFromRegister("Simpson", register);
//		assertTrue(family.getName().equals("Simpson"));
//		
//		family.getSons().get(0).setName("Harry");
//	}
	
//	public void familyFatherHomerRoleChangeToSon(FamilyRegister register){
//		Family family = getFromRegister("Simpson", register);
//		assertTrue(family.getName().equals("Simpson"));
//		
//		String familySonName = family.getSons().get(0).getName();
//		String familyFatherName = family.getFather().getName();
//		
//		family.getFather().setName(familySonName);
//		family.getSons().get(0).setName(familyFatherName);
//	}
//	
//	public void familyMotherMargeRoleChangeToDaughterLisa(FamilyRegister register){
//		Family family = getFromRegister("Simpson", register);
//		assertTrue(family.getName().equals("Simpson"));
//		
//		String familyDaughterName = family.getDaughters().get(0).getName();
//		String familyMotherName = family.getMother().getName();
//		
//		family.getMother().setName(familyDaughterName);
//		family.getDaughters().get(0).setName(familyMotherName);
//	}
//	
//	public void familyFatherHomerRoleChangeToMotherMarge(FamilyRegister register){
//		Family family = getFromRegister("Simpson", register);
//		assertTrue(family.getName().equals("Simpson"));
//		
//		String familyMotherName = family.getMother().getName();
//		String familyFatherName = family.getFather().getName();
//		
//		family.getFather().setName(familyMotherName);
//		family.getMother().setName(familyFatherName);
//	}
//	
//	public void familySonBartRoleChangeToMotherMarge(FamilyRegister register){
//		Family family = getFromRegister("Simpson", register);
//		assertTrue(family.getName().equals("Simpson"));
//		
//		String familyMotherName = family.getMother().getName();
//		String familySonName = family.getSons().get(0).getName();
//		
//		family.getSons().get(0).setName(familyMotherName);
//		family.getMother().setName(familySonName);
//	}
//		
//	public void deleteFamilyFatherHomer(FamilyRegister eObject){
//		Family family = eObject.getFamilies().get(0);
//		assertTrue(family.getName().equals("Simpson"));
//		
//		EcoreUtil.delete(family.getFather());
//	}
//	
//	public void deleteFamilySonHomer(FamilyRegister eObject){
//		Family family = eObject.getFamilies().get(0);
//		assertTrue(family.getName().equals("Simpson"));
//		assertTrue(family.getFather().getName().equals("Homer"));
//		assertTrue(family.getSons().get(0).getName().equals("Homer"));
//		
//		EcoreUtil.delete(family.getSons().get(0));
//	}
//	
//	public void deleteFamilyDaughterMarge(FamilyRegister eObject){
//		Family family = eObject.getFamilies().get(0);
//		assertTrue(family.getName().equals("Simpson"));
//		assertTrue(family.getMother().getName().equals("Marge"));
//		assertTrue(family.getDaughters().get(0).getName().equals("Marge"));
//		
//		EcoreUtil.delete(family.getDaughters().get(0));
//	}
//	
//	public void deleteFirstSimpsonFamily(FamilyRegister eObject){
//		Family family = eObject.getFamilies().get(0);
//		assertTrue(family.getName().equals("Simpson"));
//		assertTrue(family.getFather().getName().equals("Homer"));
//		
//		EcoreUtil.delete(family.getFather());
//		EcoreUtil.delete(family.getMother());
//		EcoreUtil.delete(family.getSons().get(0));
//		EcoreUtil.delete(family.getDaughters().get(0));
//		EcoreUtil.delete(family.getDaughters().get(0));
//		
//		EcoreUtil.delete(family);
//	}
}
