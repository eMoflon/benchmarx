package de.tbuchmann.ttc.rules

import de.tbuchmann.ttc.corrmodel.Corr
import de.tbuchmann.ttc.corrmodel.CorrElem
import de.tbuchmann.ttc.corrmodel.CorrModelFactory
import de.tbuchmann.ttc.corrmodel.MultiElem
import de.tbuchmann.ttc.corrmodel.SingleElem
import de.tbuchmann.ttc.corrmodel.Transformation
import de.tbuchmann.ttc.trafo.Containers2MiniYAML
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Objects
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.xbase.lib.Functions.Function0
import org.eclipse.xtext.xbase.lib.Functions.Function1

abstract class Elem2Elem {
	val public String ruleId
	
	val protected Containers2MiniYAML trafo
	val protected Resource sourceModel
	val protected Resource targetModel
	val protected Resource corrModel
	
	protected List<EObject> createdElems
	protected List<EObject> spareElems
	protected Set<EObject> detachedCorrElems
	
	val static sourcePackage = containers.ContainersPackage::eINSTANCE
	val static targetPackage = miniyaml.MiniyamlPackage::eINSTANCE
	val static sourceFactory = containers.ContainersFactory::eINSTANCE
	val static targetFactory = miniyaml.MiniyamlFactory::eINSTANCE
	val static corrFactory = CorrModelFactory::eINSTANCE
	
	public static Map<EObject, Corr> elementsToCorr = newHashMap()
	
	new(String ruleId, Containers2MiniYAML trafo) {
		this.ruleId = ruleId
		this.trafo = trafo
		sourceModel = trafo.source
		targetModel = trafo.target
		corrModel = trafo.corr;
		(corrModel.contents.get(0) as Transformation).correspondences.forEach[c |
			c.flatSrc.forEach[e | elementsToCorr.put(e, c)]
			c.flatTrg.forEach[e | elementsToCorr.put(e, c)]
		]
	}
	
	@Data static class CorrModelDelta {
		List<EObject> createdElems
		List<EObject> spareElems
		Set<EObject> detachedCorrElems
	}
	
	def abstract CorrModelDelta sourceToTarget(Set<EObject> _detachedCorrElems);
	def void onTrgElemCreation(EObject trgElem) {}
	def void onTrgElemDeletion(EObject trgElem) {}
	
	def abstract CorrModelDelta targetToSource(Set<EObject> _detachedCorrElems);
	def void onSrcElemCreation(EObject srcElem) {}
	def void onSrcElemDeletion(EObject srcElem) {}
	
	def protected boolean hasCorr(EObject obj) {
		return elementsToCorr.containsKey(obj)
	}
	def protected Corr getCorr(EObject obj) {
		val corr = elementsToCorr.get(obj)
		if (corr === null) {
			throw new IllegalArgumentException("No correspondence was created for the given object!")
		} else {
			return corr
		}
	}
	def protected int getCorrElemPosition(EObject element) {
		val source = element.corr.source
		for (var i = 0; i < source.size(); i++) {
			if (source.get(i) instanceof SingleElem && (source.get(i) as SingleElem).element == element) {
				return i
			} else if (source.get(i) instanceof MultiElem && (source.get(i) as MultiElem).elements.contains(element)) {
				return i
			}
		}
		
		val target = element.corr.target
		for (var i = 0; i < target.size(); i++) {
			if (target.get(i) instanceof SingleElem && (target.get(i) as SingleElem).element == element) {
				return i
			} else if (target.get(i) instanceof MultiElem && (target.get(i) as MultiElem).elements.contains(element)) {
				return i
			}
		}
		
		throw new AssertionError("Invalid mapping in elementsToCorr map!")
	}
	
	def protected SingleElem wrap(EObject obj) {
		val singleElem = corrFactory.createSingleElem() => [it.element = obj]
		corrModel.contents += singleElem
		return singleElem
	}
	def protected static dispatch EObject unwrap(SingleElem elem) {
		return elem.element
	}
	def protected MultiElem wrap(List<? extends EObject> objs) {
		val multiElem = corrFactory.createMultiElem() => [it.elements += objs]
		corrModel.contents += multiElem
		return multiElem
	}
	def protected static dispatch List<? extends EObject> unwrap(MultiElem elem) {
		return elem.elements
	}
	
	def protected static void assertRuleId(Corr corr, String... ruleIds) {
		if (!ruleIds.contains(corr.ruleId)) {
			//throw new AssertionError("The given corr doesn't have any of the asserted rule ids!")
		}
	}
	
	def protected Corr updateOrCreateCorrSrc(CorrElem elem, CorrElem... additionalElems) {
		return updateOrCreateCorr(elem, true, additionalElems)
	}
	def protected Corr updateOrCreateCorrTrg(CorrElem elem, CorrElem... additionalElems) {
		return updateOrCreateCorr(elem, false, additionalElems)
	}
	
	@Data protected static class CorrElemType {
		String clazz
		boolean multivalued
	}
	def protected List<CorrElem> getOrCreateSrc(Corr corr, CorrElemType... srcTypes) {
		if (srcTypes.empty) throw new IllegalArgumentException("The source elements to create may not be empty!")
		
		var List<CorrElem> source = corr.source
		if (corr.flatSrc.empty) {
			for (type : srcTypes) {
				val corrElem = if (!type.multivalued) {
					val eClass = sourcePackage.getEClassifier(type.clazz) as EClass
					corrFactory.createSingleElem() => [it.element = sourceFactory.create(eClass)]
				} else {
					corrFactory.createMultiElem()
				}
				source += corrElem
			}
			createdElems += corr.flatSrc
			corr.flatSrc.forEach[e | elementsToCorr.put(e, corr)]
		}
		return source
	}
	def protected List<CorrElem> getOrCreateTrg(Corr corr, CorrElemType... trgTypes) {
		if (trgTypes.empty) throw new IllegalArgumentException("The target elements to create may not be empty!")
		
		var List<CorrElem> target = corr.target
		if (corr.flatTrg.empty) {
			for (type : trgTypes) {
				val corrElem = if (!type.multivalued) {
					val eClass = targetPackage.getEClassifier(type.clazz) as EClass
					corrFactory.createSingleElem() => [it.element = targetFactory.create(eClass)]
				} else {
					corrFactory.createMultiElem()
				}
				target += corrElem
			}
			createdElems += corr.flatTrg
			corr.flatTrg.forEach[e | elementsToCorr.put(e, corr)]
		}
		return target
	}
	
	protected static abstract class MultiElemUpdater<T extends EObject> {
		List<T> outdated
		List<T> updated
		val Function0<T> elemFactory
		val Elem2Elem rule
		val Corr corr
		var boolean finished
		
		new(List<T> outdated, Function0<T> elemFactory, Elem2Elem rule, Corr corr) {
			this.outdated = newArrayList() => [it += outdated]
			this.updated = newArrayList()
			this.elemFactory = elemFactory
			this.rule = rule
			this.corr = corr
			this.finished = false
		}
		
		def protected T update(Function1<? super T, Boolean> predicate) {
			if (finished) throw new IllegalStateException("Finish was already called on this MultiElemUpdater!")
			
			val existing = outdated.findFirst[predicate.apply(it)]
			if (outdated.remove(existing)) {
				updated += existing
				return existing
			} else {
				val created = elemFactory.apply()
				rule.createdElems += created
				elementsToCorr.put(created, corr)
				updated += created
				return created
			}
		}
		
		def protected List<T> finish() {
			if (finished) throw new IllegalStateException("Finish was already called on this MultiElemUpdater!")
			finished = true
			
			rule.spareElems += outdated
			return updated
		}
	}
	protected static class SrcMultiElemUpdater<T extends EObject> extends MultiElemUpdater<T> {
		new(List<T> outdated, String elemClass, Elem2Elem rule, Corr corr) {
			super(outdated, [sourceFactory.create(sourcePackage.getEClassifier(elemClass) as EClass) as T], rule, corr)
		}
	}
	protected static class TrgMultiElemUpdater<T extends EObject> extends MultiElemUpdater<T> {
		new(List<T> outdated, String elemClass, Elem2Elem rule, Corr corr) {
			super(outdated, [targetFactory.create(targetPackage.getEClassifier(elemClass) as EClass) as T], rule, corr)
		}
	}
	
	def private Corr updateOrCreateCorr(CorrElem elem, boolean src, CorrElem... additionalElems) {
		Objects.requireNonNull(elem, "The parameter 'elem' must not be null!")
		Objects.requireNonNull(additionalElems, "The parameter 'additionalElems' must not be null!")
		if (additionalElems.contains(null)) {
			throw new IllegalArgumentException("The additional elements must not contain null!")
		}
		
		val elems = new ArrayList<CorrElem>() => [
			it += elem
			it += additionalElems
		]
		val flatElems = elems.map[if (it instanceof SingleElem) #[element] else (it as MultiElem).elements].flatten()
		if (flatElems.exists[it === null]) {
			throw new IllegalArgumentException("A corr must not be created from a null element!")
		}
		
		var corrCount = new HashMap<Corr, Integer>
		for (e : flatElems) {
			val corr = elementsToCorr.get(e)
			if (corr !== null && corrCount.containsKey(corr)) {
				corrCount.put(corr, corrCount.get(corr) + 1)
			} else if (corr !== null) {
				corrCount.put(corr, 1)
			}
		}
		val corrs = corrCount.keySet.toList()
		val corrsElems = if (src) corrs.map[source] else corrs.map[target]
		val corrsFlatElems = if (src) corrs.map[flatSrc] else corrs.map[flatTrg]
		
		val anyDetachedElem = flatElems.exists[detachedCorrElems.contains(it)]
		val corr = if (corrs.size() == 0) {
			corrFactory.createCorr()
			
		} else if (corrs.size() == 1 && corrs.head().ruleId == ruleId
				&& corrsFlatElems.head().size() == corrCount.values.head() && !anyDetachedElem) {
			corrsElems.head().clear()
			corrs.head()
			
		} else {
			for (var i = 0; i < corrs.size(); i++) {
				for (element : corrsFlatElems.get(i)) {
					elementsToCorr.remove(element)
					detachedCorrElems += element
				}
				corrsElems.get(i).clear()
			}
			corrFactory.createCorr()
		}
		
		corr.ruleId = ruleId
		if (src) {
			corr.source += elems
			corr.flatSrc.forEach[e | elementsToCorr.put(e, corr)]
		} else {
			corr.target += elems
			corr.flatTrg.forEach[e | elementsToCorr.put(e, corr)]
		}
		(corrModel.contents.get(0) as Transformation).correspondences += corr
		
		return corr
	}
}
