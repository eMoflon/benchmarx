package de.tbuchmann.ttc.trafo

import de.tbuchmann.ttc.corrmodel.Corr
import de.tbuchmann.ttc.corrmodel.CorrModelFactory
import de.tbuchmann.ttc.corrmodel.Transformation
import de.tbuchmann.ttc.rules.Elem2Elem
import de.ubt.ai1.m2m.bxtenddsl.BXtendTransformation
import java.util.HashMap
import java.util.HashSet
import java.util.Iterator
import java.util.List
import java.util.Set
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl

abstract class AbstractContainers2MiniYAML implements BXtendTransformation {
	val protected Resource sourceModel
	val protected Resource targetModel
	val protected Resource corrModel
	
	val List<Elem2Elem> rules
	
	new() {
		val ResourceSet set = new ResourceSetImpl()
		set.resourceFactoryRegistry.extensionToFactoryMap.put("xmi", new XMIResourceFactoryImpl())
		
		sourceModel = set.createResource(URI.createURI("source.xmi"))
		targetModel = set.createResource(URI.createURI("target.xmi"))
		corrModel = set.createResource(URI.createURI("corr.xmi"))
		corrModel.contents.add(CorrModelFactory.eINSTANCE.createTransformation)
		
		rules = createRules()
	}
	new(Resource source, Resource target, Resource correspondence) {
		sourceModel = source
		targetModel = target
		corrModel = correspondence
		if (corrModel.contents.size() == 0) {
			corrModel.contents.add(CorrModelFactory.eINSTANCE.createTransformation)
		}
		
		rules = createRules()
	}
	
	override void sourceToTarget() {
		val createdElems = new HashMap<Elem2Elem, List<EObject>>()
		val spareElems = new HashMap<Elem2Elem, List<EObject>>()
		var Set<EObject> detachedCorrElems = new HashSet<EObject>()
		
		for (rule : rules) {
			val delta = rule.sourceToTarget(detachedCorrElems)
			createdElems.put(rule, delta.createdElems)
			spareElems.put(rule, delta.spareElems)
			detachedCorrElems = delta.detachedCorrElems
		}
		for (Corr corr : (corrModel.contents.get(0) as Transformation).correspondences) {
			for (EObject trg : corr.flatTrg.filter[eContainer === null]) {
				targetModel.contents += trg
			}
		}
		
		for (rule : rules) {
			for (createdElem : createdElems.get(rule)) {
				rule.onTrgElemCreation(createdElem)
			}
		}
		for (rule : rules) {
			for (spareElem : spareElems.get(rule)) {
				rule.onTrgElemDeletion(spareElem)
				EcoreUtil.delete(spareElem, false)
			}
		}
		deleteUnreferencedTargetElements()
	}
	override void targetToSource() {
		val createdElems = new HashMap<Elem2Elem, List<EObject>>()
		val spareElems = new HashMap<Elem2Elem, List<EObject>>()
		var Set<EObject> detachedCorrElems = new HashSet<EObject>()
		
		for (rule : rules) {
			val delta = rule.targetToSource(detachedCorrElems)
			createdElems.put(rule, delta.createdElems)
			spareElems.put(rule, delta.spareElems)
			detachedCorrElems = delta.detachedCorrElems
		}
		for (Corr corr : (corrModel.contents.get(0) as Transformation).correspondences) {
			for (EObject src : corr.flatSrc.filter[eContainer === null]) {
				sourceModel.contents += src
			}
		}
		
		for (rule : rules) {
			for (createdElem : createdElems.get(rule)) {
				rule.onSrcElemCreation(createdElem)
			}
		}
		for (rule : rules) {
			for (spareElem : spareElems.get(rule)) {
				rule.onSrcElemDeletion(spareElem)
				EcoreUtil.delete(spareElem, false)
			}
		}
		deleteUnreferencedSourceElements()
	}
	
	override Resource getCorr() {
		return corrModel
	}
	override Resource getSource() {
		return sourceModel
	}
	override Resource getTarget() {
		return targetModel
	}
	
	def protected abstract List<Elem2Elem> createRules();
	
	def private Iterator<Corr> detectSourceDeletions() {
		corrModel.allContents.filter(typeof(Corr)).filter[c |
			c.flatSrc.empty
		]
	}
	def private Iterator<Corr> detectTargetDeletions() {
		corrModel.allContents.filter(typeof(Corr)).filter[c |
			c.flatTrg.empty
		]
	}
	def private void deleteUnreferencedTargetElements() {
		val List<EObject> deletionList = newArrayList;
		
		detectSourceDeletions().forEach[c |
			val rule = rules.findFirst[ruleId == c.ruleId]
			c.flatTrg.forEach[rule.onTrgElemDeletion(it)]
			deletionList += c.flatTrg
			deletionList += c
		]
		deletionList.forEach[e | EcoreUtil.delete(e, true)]
	}
	def private void deleteUnreferencedSourceElements() {
		val List<EObject> deletionList = newArrayList; 
		
		detectTargetDeletions().forEach[c |
			val rule = rules.findFirst[ruleId == c.ruleId]
			c.flatSrc.forEach[rule.onSrcElemDeletion(it)]
			deletionList += c.flatSrc
			deletionList += c
		]
		deletionList.forEach[e | EcoreUtil.delete(e, true)]
	}
}
