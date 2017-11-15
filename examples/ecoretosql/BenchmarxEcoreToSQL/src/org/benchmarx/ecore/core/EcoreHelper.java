package org.benchmarx.ecore.core;

import java.util.Optional;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class EcoreHelper {

	public void idleDelta(EPackage p) {
		
	}
	
	public void hippocraticDelta(EPackage p) {
		//Delete method in List
		EClass c = (EClass) p.getEClassifier("List");
		EcoreUtil.delete(c.getEOperations().get(0), true);
		
		//Change some attributes from List length
		Optional<EAttribute> oa = c.getEAttributes().stream().filter(a -> a.getName().equals("length")).findAny();
		if(oa.isPresent()) {
			EAttribute a = oa.get();
			a.setChangeable(true);
			a.setVolatile(false);
			a.setDerived(false);
			a.setTransient(false);
		}
		EcoreBuilder builder = new EcoreBuilder(p);
		builder.operation("Leaf", "isLeaf", "boolean");
		
	}
	
	public void createSimpleCompositeList(EPackage p) {
		EcoreBuilder builder = new EcoreBuilder(p);
		builder
		.abstractClass("Node")
		.clazz("Leaf", "Node")
		.clazz("DataNode", "Node")
			.field("DataNode", "data", "int")
			.reference("DataNode", "follower", "Node")
		.operation("Node", "addLast", "Node")
			.param("newNode", "DataNode")
		.operation("DataNode", "addLast", "Node")
			.param("newNode", "DataNode")
		.operation("Leaf", "addLast", "Node")
			.param("newNode", "DataNode")
			
		.clazz("List")
			.reference("List", "start", "Node")
			.field("List", "length", "int")
			.operation("List", "add", "boolean")
				.param("newNode","DataNode");
		EClass l = (EClass) p.getEClassifier("List");
		EAttribute length = (EAttribute) l.getEStructuralFeature("length");
		length.setVolatile(true);
		length.setChangeable(false);
		length.setDerived(true);
		length.setTransient(true);
	}
	
	public void createDataAttribute(EPackage p) {
		EcoreBuilder builder = new EcoreBuilder(p);
		builder.field("DataNode", "data", "int");
	}
	
	public void createDataNode(EPackage p) {
		EcoreBuilder builder = new EcoreBuilder(p);
		builder.clazz("DataNode", "Node")
			.field("DataNode", "data", "int")
			.reference("DataNode", "follower", "Node")
			.operation("DataNode", "addLast", "Node")
				.param("newNode", "DataNode");
		EClass l = (EClass) p.getEClassifier("List");
		EOperation o = l.getEOperations().get(0);
		EParameter param = o.getEParameters().get(0);
		param.setEType(p.getEClassifier("DataNode"));
		
		l = (EClass) p.getEClassifier("Node");
		o = l.getEOperations().get(0);
		param = o.getEParameters().get(0);
		param.setEType(p.getEClassifier("DataNode"));
		
		l = (EClass) p.getEClassifier("Leaf");
		o = l.getEOperations().get(0);
		param = o.getEParameters().get(0);
		param.setEType(p.getEClassifier("DataNode"));
	}
	
	public void createMethods(EPackage p) {
		EcoreBuilder builder = new EcoreBuilder(p);
		builder
		.operation("Node", "addLast", "Node")
			.param("newNode", "DataNode")
		.operation("DataNode", "addLast", "Node")
			.param("newNode", "DataNode")
		.operation("Leaf", "addLast", "Node")
			.param("newNode", "DataNode")
		.operation("List", "add", "boolean")
			.param("newNode","DataElement");
	}
	
	public void createMethodsSimple(EPackage p) {
		EcoreBuilder builder = new EcoreBuilder(p);
		builder
		.operation("Node", "addLast", "Node")
			.param("newNode", "DataNode")
		.operation("DataNode", "addLast", "Node")
			.param("newNode", "DataNode")
		.operation("Leaf", "addLast", "Node")
			.param("newNode", "DataNode")
		.operation("List", "add", "boolean")
			.param("newNode","DataNode");
	}
	
	public void addDataElementFeature(EPackage p) {
		//Precondition: createSimpleCompositeList
		EcoreBuilder builder = new EcoreBuilder(p);
		EClass l = (EClass) p.getEClassifier("DataNode");
		EcoreUtil.delete(l.getEStructuralFeature("data"), true);
		builder.reference("Node", "startOf", "List");
		l = (EClass) p.getEClassifier("Node");
		EClass c = (EClass) p.getEClassifier("List");
		EReference r = (EReference) l.getEStructuralFeature("startOf");
		r.setEOpposite((EReference) c.getEStructuralFeature("start"));
		((EReference) c.getEStructuralFeature("start")).setEOpposite(r);
		builder.iface("DataElement")
			.clazz("Pair", "DataElement")
			.clazz("Value")
				.reference("Value", "pair", "Pair")
			.clazz("Key")
				.multiField("Key", "keyValues", "String")
			.multiReference("Pair", "values", "Value")
			.reference("Pair", "key", "Key");
		builder.reference("DataNode", "data", "DataElement");
		
		
		c = (EClass) p.getEClassifier("Pair");
		l = (EClass) p.getEClassifier("Value");
		
		//Set Pair-Values references as containment and opposite.
		r = (EReference) l.getEStructuralFeature("pair");
		r.setEOpposite((EReference) c.getEStructuralFeature("values"));
		r = (EReference) c.getEStructuralFeature("values");
		r.setEOpposite((EReference) l.getEStructuralFeature("pair"));
		r.setContainment(true);

		//Set key reference as containment.
		r = (EReference) c.getEStructuralFeature("key");
		r.setContainment(true);
	}
	
	public void changeListAddParameter(EPackage p) {
		EClass c = (EClass) p.getEClassifier("List");
		EOperation o = c.getEOperations().get(0);
		o.getEParameters().clear();
		EClassifier t = p.getEClassifier("DataElement");
		EParameter e = EcoreFactory.eINSTANCE.createEParameter();
		e.setName("newElement");
		e.setEType(t);
		o.getEParameters().add(e);
	}
	
	public void changeBackListAddParameter(EPackage p) {
		EClass c = (EClass) p.getEClassifier("List");
		EOperation o = c.getEOperations().get(0);
		o.getEParameters().clear();
		EClassifier t = p.getEClassifier("DataNode");
		EParameter e = EcoreFactory.eINSTANCE.createEParameter();
		e.setName("newNode");
		e.setEType(t);
		o.getEParameters().add(e);
	}
	
	public void changePackageName(EPackage p) {
		EcoreBuilder builder = new EcoreBuilder(p);
		builder.name("CompositeList");
	}
	
	public void changeGeneralizationDataElement(EPackage p) {
		EClass pair = (EClass) p.getEClassifier("Pair");
		EClass key = (EClass) p.getEClassifier("Key");
		key.getESuperTypes().addAll(pair.getESuperTypes());
		pair.getESuperTypes().clear();
	}
	
	public void changeListLengthAttribute(EPackage p) {
		EClass c = (EClass) p.getEClassifier("List");
		Optional<EAttribute> ea = c.getEAttributes().stream().filter(a -> a.getName().equals("length")).findAny();
		if(ea.isPresent()) {
			EAttribute length = ea.get();
			length.setVolatile(true);
			length.setChangeable(false);
			length.setDerived(true);
			length.setTransient(true);
		}
	}
	
	public void deleteListLengthAttribute(EPackage p) {
		EClass c = (EClass) p.getEClassifier("List");
		Optional<EAttribute> ea = c.getEAttributes().stream().filter(a -> a.getName().equals("length")).findAny();
		if(ea.isPresent()) {
			EcoreUtil.delete(ea.get(), true);
		}
	}
	
	public void deleteKeyKeyValuesAttribute(EPackage p) {
		EClass c = (EClass) p.getEClassifier("Key");
		Optional<EAttribute> ea = c.getEAttributes().stream().filter(a -> a.getName().equals("keyValues")).findAny();
		if(ea.isPresent()) {
			EcoreUtil.delete(ea.get(), true);
		}
	}
	
	public void deleteNodeStartOfReference(EPackage p) {
		EClass c = (EClass) p.getEClassifier("Node");
		Optional<EReference> ea = c.getEReferences().stream().filter(a -> a.getName().equals("startOf")).findAny();
		if(ea.isPresent()) {
			EcoreUtil.delete(ea.get(), true);
		}
	}
	
	public void deletePairReferences(EPackage p) {
		EClass c = (EClass) p.getEClassifier("Pair");
		Optional<EReference> ea = c.getEReferences().stream().filter(a -> a.getName().equals("key")).findAny();
		if(ea.isPresent()) {
			EcoreUtil.delete(ea.get(), true);
		}
		ea = c.getEReferences().stream().filter(a -> a.getName().equals("values")).findAny();
		if(ea.isPresent()) {
			EcoreUtil.delete(ea.get().getEOpposite(), true);
			EcoreUtil.delete(ea.get(), true);
		}
	}
	
	public void deleteDataNodeDataReference(EPackage p) {
		EClass c = (EClass) p.getEClassifier("DataNode");
		Optional<EReference> ea = c.getEReferences().stream().filter(a -> a.getName().equals("data")).findAny();
		if(ea.isPresent()) {
			EcoreUtil.delete(ea.get(), true);
		}
	}
	
	public void deleteDataElementFeature(EPackage p) {
		EcoreUtil.delete(p.getEClassifier("Key"), true);
		EcoreUtil.delete(p.getEClassifier("Value"), true);
		EcoreUtil.delete(p.getEClassifier("Pair"), true);
		EcoreUtil.delete(p.getEClassifier("DataElement"), true);
	}
	
	public void deleteDataAttribute(EPackage p) {
		EClass c = (EClass) p.getEClassifier("DataNode");
		Optional<EAttribute> ea = c.getEAttributes().stream().filter(a -> a.getName().equals("data")).findAny();
		if(ea.isPresent()) {
			EcoreUtil.delete(ea.get(), true);
		}
	}
	
	public void deleteDataNode(EPackage p) {
		EcoreUtil.delete(p.getEClassifier("DataNode"), true);
	}
	
	public void moveReferencePair(EPackage p) {
		EClass pair = (EClass) p.getEClassifier("Pair");
		EClass value = (EClass) p.getEClassifier("Value");
		EClass key = (EClass) p.getEClassifier("Key");
		EReference r = value.getEReferences().get(0);
		r.getEOpposite().setEOpposite(null);
		key.getEStructuralFeatures().add(r);
		EReference newOp = (EReference)pair.getEStructuralFeature("key");
		r.setEOpposite(newOp);
		newOp.setEOpposite(r);
	}
	
	public void moveAttributeLengthAndRename(EPackage p) {
		EClass value = (EClass) p.getEClassifier("Value");
		EClass list = (EClass) p.getEClassifier("List");
		EAttribute att = list.getEAttributes().get(0);
		att.setName("value");
		value.getEStructuralFeatures().add(att);
	}
	
	public void renameListClass(EPackage p) {
		EClass c = (EClass) p.getEClassifier("List");
		c.setName("Queue");
	}
	
	public void renamePackage(EPackage p) {
		EcoreBuilder builder = new EcoreBuilder(p);
		builder.name("CompositeQueue");
	}
	
	public void renameDataNodeDataReference(EPackage p) {
		EClass c = (EClass) p.getEClassifier("DataNode");
		Optional<EReference> ea = c.getEReferences().stream().filter(a -> a.getName().equals("data")).findAny();
		if(ea.isPresent()) 
			ea.get().setName("savedInformation");
	}
	
	public void renameValuesAttribute(EPackage p) {
		EClass c = (EClass) p.getEClassifier("Key");
		Optional<EAttribute> ea = c.getEAttributes().stream().filter(a -> a.getName().equals("keyValues")).findAny();
		if(ea.isPresent()) {
			ea.get().setName("keys");
		}
	}
	
	public void setDataElementAsInterface(EPackage p) {
		EClass c = (EClass) p.getEClassifier("DataElement");
		c.setInterface(true);
	}
}
