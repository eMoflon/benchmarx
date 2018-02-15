package org.benchmarx.ecore.core;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

public class EcoreBuilder {
	
	private final EPackage pck;
	private final EcoreFactory f = EcoreFactory.eINSTANCE;
	private EOperation op;
	
	public EcoreBuilder(String name) {
		pck = f.createEPackage();
		pck.setName(name);
		pck.setNsPrefix(name);
		pck.setNsURI(name);
	}
	
	public EcoreBuilder(EPackage _pack) {
		pck = _pack;
	}
	
	public EcoreBuilder name(String name) {
		pck.setName(name);
		pck.setNsPrefix(name);
		pck.setNsURI(name);
		return this;
	}
	
	public EcoreBuilder clazz(String name, String... superclasses) {
		EClass c = f.createEClass();
		c.setName(name);
		for (String s : superclasses) {
			EClassifier sup = pck.getEClassifier(s);
			if (sup instanceof EClass)
				c.getESuperTypes().add((EClass)sup);
		}
		pck.getEClassifiers().add(c);
		return this;
	}
	
	public EcoreBuilder abstractClass(String name, String... superclasses) {
		EClass c = f.createEClass();
		c.setName(name);
		c.setAbstract(true);
		for (String s : superclasses) {
			EClassifier sup = pck.getEClassifier(s);
			if (sup instanceof EClass)
				c.getESuperTypes().add((EClass)sup);
		}
		pck.getEClassifiers().add(c);
		return this;
	}
	
	public EcoreBuilder iface(String name, String... superclasses) {
		EClass c = f.createEClass();
		c.setName(name);
		c.setInterface(true);
		c.setAbstract(true);
		for (String s : superclasses) {
			EClassifier sup = pck.getEClassifier(s);
			if (sup instanceof EClass)
				c.getESuperTypes().add((EClass)sup);
		}
		pck.getEClassifiers().add(c);
		return this;
	}
	
	public EcoreBuilder field(String parentClass, String name, String type) {
		EClassifier parent = pck.getEClassifier(parentClass);
		if (parent instanceof EClass) {
			EAttribute att = f.createEAttribute();
			att.setName(name);
			
			if (type.equals("int"))
				att.setEType(EcorePackage.eINSTANCE.getEInt());
			else if (type.equals("boolean"))
				att.setEType(EcorePackage.eINSTANCE.getEBoolean());
			else if (type.equals("String"))
				att.setEType(EcorePackage.eINSTANCE.getEString());
			if (type.equals("double"))
				att.setEType(EcorePackage.eINSTANCE.getEDouble());
			
			att.setUpperBound(1);
			((EClass)parent).getEStructuralFeatures().add(att);
		}
		
		return this;
	}
	
	public EcoreBuilder multiField(String parentClass, String name, String type) {
		EClassifier parent = pck.getEClassifier(parentClass);
		if (parent instanceof EClass) {
			EAttribute att = f.createEAttribute();
			att.setName(name);
			
			if (type.equals("int"))
				att.setEType(EcorePackage.eINSTANCE.getEInt());
			else if (type.equals("boolean"))
				att.setEType(EcorePackage.eINSTANCE.getEBoolean());
			else if (type.equals("String"))
				att.setEType(EcorePackage.eINSTANCE.getEString());
			if (type.equals("double"))
				att.setEType(EcorePackage.eINSTANCE.getEDouble());
			
			att.setUpperBound(-1);
			((EClass)parent).getEStructuralFeatures().add(att);
		}
		
		return this;
	}
	
	public EcoreBuilder operation(String parentClass, String name, String type) {
		EClassifier parent = pck.getEClassifier(parentClass);
		EClassifier t = pck.getEClassifier(type);
		if (parent instanceof EClass) {
			op = f.createEOperation();
			op.setName(name);
			
			if (type.equals("int"))
				op.setEType(EcorePackage.eINSTANCE.getEInt());
			else if (type.equals("boolean"))
				op.setEType(EcorePackage.eINSTANCE.getEBoolean());
			else if (type.equals("String"))
				op.setEType(EcorePackage.eINSTANCE.getEString());
			else if (type.equals("double"))
				op.setEType(EcorePackage.eINSTANCE.getEDouble());
			else
				op.setEType(t);
			
			op.setUpperBound(1);
			((EClass)parent).getEOperations().add(op);
		}
		
		return this;
	}
	
	public EcoreBuilder multiOperation(String parentClass, String name, String type) {
		EClassifier parent = pck.getEClassifier(parentClass);
		EClassifier t = pck.getEClassifier(type);
		if (parent instanceof EClass) {
			op = f.createEOperation();
			op.setName(name);
			
			if (type.equals("int"))
				op.setEType(EcorePackage.eINSTANCE.getEInt());
			else if (type.equals("boolean"))
				op.setEType(EcorePackage.eINSTANCE.getEBoolean());
			else if (type.equals("String"))
				op.setEType(EcorePackage.eINSTANCE.getEString());
			else if (type.equals("double"))
				op.setEType(EcorePackage.eINSTANCE.getEDouble());
			else
				op.setEType(t);
			
			op.setUpperBound(-1);
			((EClass)parent).getEOperations().add(op);
		}
		
		return this;
	}
	
	public EcoreBuilder param(String name, String type) {
		EClassifier t = pck.getEClassifier(type);
		EParameter param = f.createEParameter();
		param.setName(name);
			
		if (type.equals("int"))
			param.setEType(EcorePackage.eINSTANCE.getEInt());
		else if (type.equals("boolean"))
			param.setEType(EcorePackage.eINSTANCE.getEBoolean());
		else if (type.equals("String"))
			param.setEType(EcorePackage.eINSTANCE.getEString());
		else if (type.equals("double"))
			param.setEType(EcorePackage.eINSTANCE.getEDouble());
		else if(t != null)
			param.setEType(t);
		else
			return this;
			
		param.setUpperBound(1);
		op.getEParameters().add(param);

		return this;
	}
	
	public EcoreBuilder multiParam(String name, String type) {
		EClassifier t = pck.getEClassifier(type);
		EParameter param = f.createEParameter();
		param.setName(name);
			
		if (type.equals("int"))
			param.setEType(EcorePackage.eINSTANCE.getEInt());
		else if (type.equals("boolean"))
			param.setEType(EcorePackage.eINSTANCE.getEBoolean());
		else if (type.equals("String"))
			param.setEType(EcorePackage.eINSTANCE.getEString());
		else if (type.equals("double"))
			param.setEType(EcorePackage.eINSTANCE.getEDouble());
		else if(t != null)
			param.setEType(t);
		else
			return this;
			
		param.setUpperBound(-1);
		op.getEParameters().add(param);

		return this;
	}
	
	public EcoreBuilder reference(String parentClass, String name, String targetClass) {
		EClassifier parent = pck.getEClassifier(parentClass);
		EClassifier target = pck.getEClassifier(targetClass);
		if (parent instanceof EClass && target != null && target instanceof EClass) {
			EReference ref = f.createEReference();
			ref.setName(name);
			ref.setEType(target);
			
			ref.setUpperBound(1);
			((EClass)parent).getEStructuralFeatures().add(ref);
		}
		
		return this;
	}
	
	public EcoreBuilder multiReference(String parentClass, String name, String targetClass) {
		EClassifier parent = pck.getEClassifier(parentClass);
		EClassifier target = pck.getEClassifier(targetClass);
		if (parent instanceof EClass && target != null && target instanceof EClass) {
			EReference ref = f.createEReference();
			ref.setName(name);
			ref.setEType(target);
			
			ref.setUpperBound(-1);
			((EClass)parent).getEStructuralFeatures().add(ref);
		}
		
		return this;
	}
	
	public EPackage end() {
		return pck;
	}
}
