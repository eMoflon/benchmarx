package org.benchmarx.families.core;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import Families.FamiliesFactory;
import Families.FamiliesPackage;
import Families.Family;
import Families.FamilyMember;
import Families.FamilyRegister;

public class FamilyRegisterBuilder {
	private FamiliesFactory f = FamiliesFactory.eINSTANCE;

	private FamilyRegister register;
	private Consumer<EObject> createNode;
	private BiConsumer<EReference, List<EObject>> createEdge;
	
	public FamilyRegisterBuilder(FamilyRegister reg, Consumer<EObject> cn, BiConsumer<EReference, List<EObject>> ce) {
		register = reg;
		createNode = cn;
		createEdge = ce;
	}
	
	public FamilyBuilder family(String name) {
		FamilyBuilder builder = new FamilyBuilder(name);
		return builder;
	}
	
	public FamilyBuilder family(Family fam) {
		FamilyBuilder builder = new FamilyBuilder(fam);
		return builder;
	}
	
	class FamilyBuilder {
		private Family fam;
		
		public FamilyBuilder(String name) {
			fam = f.createFamily();
			fam.setName(name);
			createNode.accept(fam);
			
			register.getFamilies().add(fam);
			createEdge.accept(FamiliesPackage.Literals.FAMILY_REGISTER__FAMILIES, List.of(register, fam));
		}
		
		public FamilyBuilder(Family family) {
			fam = family;
		}
		
		public FamilyBuilder father(String name) {
			FamilyMember m = f.createFamilyMember();
			m.setName(name);
			createNode.accept(m);
			
			fam.setFather(m);
			createEdge.accept(FamiliesPackage.Literals.FAMILY__FATHER, List.of(fam, m));
			
			return this;
		}
		
		public FamilyBuilder mother(String name) {
			FamilyMember m = f.createFamilyMember();
			m.setName(name);
			createNode.accept(m);
			
			fam.setMother(m);
			createEdge.accept(FamiliesPackage.Literals.FAMILY__MOTHER, List.of(fam, m));
			
			return this;
		}
		
		public FamilyBuilder son(String name) {
			FamilyMember m = f.createFamilyMember();
			m.setName(name);
			createNode.accept(m);

			fam.getSons().add(m);
			createEdge.accept(FamiliesPackage.Literals.FAMILY__SONS, List.of(fam, m));
			
			return this;
		}
		
		public FamilyBuilder daughter(String name) {
			FamilyMember m = f.createFamilyMember();
			m.setName(name);
			createNode.accept(m);

			fam.getDaughters().add(m);
			createEdge.accept(FamiliesPackage.Literals.FAMILY__DAUGHTERS, List.of(fam, m));

			return this;
		}
		
		public Family getFamily() {
			return fam;
		}
		
		public FamilyRegister getFamilyRegister() {
			return register;
		}
	}
}
