package org.benchmarx.examples.familiestopersons.families.core;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;

import Families.FamiliesFactory;
import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;

public class FamilyHelper {
	
	private Family getFromRegister(String name, FamilyRegister register) {
		Optional<Family> family = register.getFamilies().stream()
				.filter(f -> f.getName().equals(name))
				.findAny();
				
		assertTrue(family.isPresent());
		return family.get();
	}
	public void createSimpsonFamily(FamilyRegister register) {
		Family family = FamiliesFactory.eINSTANCE.createFamily();
		family.setName("Simpson");
		register.getFamilies().add(family);
	}	
	
	public void createBachchanFamily(FamilyRegister register) {
		Family family = FamiliesFactory.eINSTANCE.createFamily();
		family.setName("Bachchan");
		register.getFamilies().add(family);
	}	
	
	public void createFatherHomer(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
			
		FamilyMember familyFather = FamiliesFactory.eINSTANCE.createFamilyMember();
		family.setFather(familyFather);
		familyFather.setName("Homer");
	}
	
	public void createFatherAmitabh(FamilyRegister register){
		Family family = getFromRegister("Bachchan", register);
		assertTrue(family.getName().equals("Bachchan"));
			
		FamilyMember familyFather = FamiliesFactory.eINSTANCE.createFamilyMember();
		family.setFather(familyFather);
		familyFather.setName("Amitabh");
	}
	
	public void createSonHomer(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getFather().getName().equals("Homer"));
			
		FamilyMember familySon = FamiliesFactory.eINSTANCE.createFamilyMember();
		familySon.setName("Homer");
		family.getSons().add(familySon);
	}
	
	public void createFatherGeorge(FamilyRegister register){
		Family family = register.getFamilies().get(1);
		assertTrue(family.getName().equals("Simpson"));
		
		if(family.getFather()==null)
		{
			FamilyMember familyFather = FamiliesFactory.eINSTANCE.createFamilyMember();
			family.setFather(familyFather);
			familyFather.setName("George");
		}
		
	}
	
	public void createMotherMarge(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		FamilyMember familyMother = FamiliesFactory.eINSTANCE.createFamilyMember();
		family.setMother(familyMother);
		familyMother.setName("Marge");
	}
	
	public void createDaughterMarge(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getMother().getName().equals("Marge"));
		
		FamilyMember familyDaughter = FamiliesFactory.eINSTANCE.createFamilyMember();
		familyDaughter.setName("Marge");
		family.getDaughters().add(familyDaughter);
	}
	
	public void createSimpsonFamilyMembers(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		FamilyMember familyMother = FamiliesFactory.eINSTANCE.createFamilyMember();
		familyMother.setName("Marge");
		family.setMother(familyMother);
		
		FamilyMember familySon = FamiliesFactory.eINSTANCE.createFamilyMember();
		familySon.setName("Bart");
		family.getSons().add(familySon);
		
		FamilyMember familyDaughterOne = FamiliesFactory.eINSTANCE.createFamilyMember();
		familyDaughterOne.setName("Lisa");
		family.getDaughters().add(familyDaughterOne);
		
		FamilyMember familyDaughterTwo = FamiliesFactory.eINSTANCE.createFamilyMember();
		familyDaughterTwo.setName("Maggie");
		family.getDaughters().add(familyDaughterTwo);
		
	}
	
	public void createOtherSimpsonFamilyMembers(FamilyRegister register){
		Family family = register.getFamilies().get(1);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getFather().getName().equals("George"));
		
		FamilyMember familyMother = FamiliesFactory.eINSTANCE.createFamilyMember();
		familyMother.setName("Jane");
		family.setMother(familyMother);
		
		FamilyMember familySon = FamiliesFactory.eINSTANCE.createFamilyMember();
		familySon.setName("Elroy");
		family.getSons().add(familySon);
		
		FamilyMember familyDaughterOne = FamiliesFactory.eINSTANCE.createFamilyMember();
		familyDaughterOne.setName("Judy");
		family.getDaughters().add(familyDaughterOne);
		
	}
	
	public void familyNameSimpsonChange(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		family.setName("Jetson");
	}
	
	public void familyNameOtherSimpsonChange(FamilyRegister register){
		Family family = register.getFamilies().get(1);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getFather().getName().equals("George"));
		
		family.setName("Jetson");
	}
	
	public void familyNameSimpsonChangeEmpty(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		family.setName("Jetson");
	}
	
	public void familyFatherHomerNameChange(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		family.getFather().setName("Jay");
	}
	
	public void familyFatherGeorgeNameChange(FamilyRegister register){
		Family family = register.getFamilies().get(1);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getFather().getName().equals("George"));
		
		family.getFather().setName("Jay");
	}
	
	public void familyMotherMargeNameChange(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		family.getMother().setName("Julie");
	}
	
	public void familyMotherJaneNameChange(FamilyRegister register){
		Family family = register.getFamilies().get(1);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getFather().getName().equals("George"));
		
		family.getMother().setName("Julie");
	}
	
	public void familyDaughterLisaNameChange(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		family.getDaughters().get(0).setName("Nancy");
	}
	
	public void familyDaughterJudyNameChange(FamilyRegister register){
		Family family = register.getFamilies().get(1);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getFather().getName().equals("George"));
		
		family.getDaughters().get(0).setName("Nancy");
	}
	
	public void familySonBartNameChange(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		family.getSons().get(0).setName("Harry");
	}
	
	public void familySonElroyNameChange(FamilyRegister register){
		Family family = register.getFamilies().get(1);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getFather().getName().equals("George"));
		
		family.getSons().get(0).setName("Harry");
	}
	
	public void familyFatherHomerRoleChangeToSon(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		String familySonName = family.getSons().get(0).getName();
		String familyFatherName = family.getFather().getName();
		
		family.getFather().setName(familySonName);
		family.getSons().get(0).setName(familyFatherName);
	}
	
	public void familyMotherMargeRoleChangeToDaughterLisa(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		String familyDaughterName = family.getDaughters().get(0).getName();
		String familyMotherName = family.getMother().getName();
		
		family.getMother().setName(familyDaughterName);
		family.getDaughters().get(0).setName(familyMotherName);
	}
	
	public void familyFatherHomerRoleChangeToMotherMarge(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		String familyMotherName = family.getMother().getName();
		String familyFatherName = family.getFather().getName();
		
		family.getFather().setName(familyMotherName);
		family.getMother().setName(familyFatherName);
	}
	
	public void familySonBartRoleChangeToMotherMarge(FamilyRegister register){
		Family family = getFromRegister("Simpson", register);
		assertTrue(family.getName().equals("Simpson"));
		
		String familyMotherName = family.getMother().getName();
		String familySonName = family.getSons().get(0).getName();
		
		family.getSons().get(0).setName(familyMotherName);
		family.getMother().setName(familySonName);
	}
	
	public void createNewfamilyBachchanWithMembers(FamilyRegister register){
		Family family = FamiliesFactory.eINSTANCE.createFamily();
		family.setName("Bachchan");
		register.getFamilies().add(family);
		
		FamilyMember familyFather = FamiliesFactory.eINSTANCE.createFamilyMember();
		family.setFather(familyFather);
		familyFather.setName("Amitabh");
		
		FamilyMember familyMother = FamiliesFactory.eINSTANCE.createFamilyMember();
		family.setMother(familyMother);
		familyMother.setName("Jaya");
		
		FamilyMember familySon = FamiliesFactory.eINSTANCE.createFamilyMember();
		familySon.setName("Abhishek");
		family.getSons().add(familySon);
		
		FamilyMember familyDaughter = FamiliesFactory.eINSTANCE.createFamilyMember();
		familyDaughter.setName("Shweta");
		family.getDaughters().add(familyDaughter);
	}
	
	public void createOtherRemainingMembersInFamilyBachchan(FamilyRegister register){
		Family family = getFromRegister("Bachchan", register);
		assertTrue(family.getName().equals("Bachchan"));
		
		FamilyMember familyMother = FamiliesFactory.eINSTANCE.createFamilyMember();
		family.setMother(familyMother);
		familyMother.setName("Jaya");
		
		FamilyMember familySon = FamiliesFactory.eINSTANCE.createFamilyMember();
		familySon.setName("Abhishek");
		family.getSons().add(familySon);
		
		FamilyMember familyDaughter = FamiliesFactory.eINSTANCE.createFamilyMember();
		familyDaughter.setName("Shweta");
		family.getDaughters().add(familyDaughter);
	}
	
	public void createNandaFamily(FamilyRegister register) {
		Family family = FamiliesFactory.eINSTANCE.createFamily();
		family.setName("Nanda");
		register.getFamilies().add(family);
	}
	
	public void moveDaughterToMotherOfNewFamily(FamilyRegister register){
		Family family = getFromRegister("Bachchan", register);
		assertTrue(family.getName().equals("Bachchan"));
		
		FamilyMember familyDaughter = family.getDaughters().get(0);
		assertTrue(familyDaughter.getName().equals("Shweta"));
		
		Family newFamily = FamiliesFactory.eINSTANCE.createFamily();
		newFamily.setName("Nanda");
		register.getFamilies().add(newFamily);
		
		// This also moves the daughter from the old family to the mother in the new family
		newFamily.setMother(familyDaughter);
	}
	
	public void deleteFamilyFatherAmitabh(FamilyRegister eObject){
		Family family = eObject.getFamilies().get(1);
		assertTrue(family.getName().equals("Bachchan"));
		
		EcoreUtil.delete(family.getFather());
	}
	
	public void deleteFamilyFatherHomer(FamilyRegister eObject){
		Family family = eObject.getFamilies().get(0);
		assertTrue(family.getName().equals("Simpson"));
		
		EcoreUtil.delete(family.getFather());
	}
	
	public void deleteFamilySonHomer(FamilyRegister eObject){
		Family family = eObject.getFamilies().get(0);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getFather().getName().equals("Homer"));
		assertTrue(family.getSons().get(0).getName().equals("Homer"));
		
		EcoreUtil.delete(family.getSons().get(0));
	}
	
	public void deleteFamilyDaughterMarge(FamilyRegister eObject){
		Family family = eObject.getFamilies().get(0);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getMother().getName().equals("Marge"));
		assertTrue(family.getDaughters().get(0).getName().equals("Marge"));
		
		EcoreUtil.delete(family.getDaughters().get(0));
	}
	
	public void deleteFamilyBachchan(FamilyRegister eObject){
		Family family = eObject.getFamilies().get(1);
		assertTrue(family.getName().equals("Bachchan"));
		
		EcoreUtil.delete(family);
	}
	
	public void deleteFirstSimpsonFamily(FamilyRegister eObject){
		Family family = eObject.getFamilies().get(0);
		assertTrue(family.getName().equals("Simpson"));
		assertTrue(family.getFather().getName().equals("Homer"));
		
		EcoreUtil.delete(family.getFather());
		EcoreUtil.delete(family.getMother());
		EcoreUtil.delete(family.getSons().get(0));
		EcoreUtil.delete(family.getDaughters().get(0));
		EcoreUtil.delete(family.getDaughters().get(0));
		
		EcoreUtil.delete(family);
	}
}
