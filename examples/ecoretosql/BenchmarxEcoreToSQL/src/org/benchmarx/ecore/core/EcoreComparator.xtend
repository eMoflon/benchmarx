package org.benchmarx.ecore.core

import java.util.ArrayList
import java.util.List
import org.benchmarx.emf.Comparator
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage

import static org.junit.Assert.*
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EClassifier

class EcoreComparator implements Comparator<EPackage>{
		
	override assertEquals(EPackage expected, EPackage actual) {
		assertTrue(ePackageToString(expected).startsWith("EcorePackage"))
		assertEquals(ePackageToString(expected), ePackageToString(actual))
	}
	
	
	def ePackageToString(EPackage pack) {
		return '''
		EcorePackage «pack.name» {
			  prefix = "«pack.nsPrefix»"
			, uri = "«pack.nsURI»"
			, classes = [
				«val List<EClass> sortedList = new ArrayList<EClass>(pack.EClassifiers.filter(typeof(EClass)).toList)»
				«EcoreClassNormaliser.normalize(sortedList)»
				«FOR ec: sortedList SEPARATOR ","»
				«ec.name» {
					  isAbstract = «ec.abstract»
					, isInterface = «ec.interface»
					«IF !ec.ESuperTypes.isEmpty»
					, superclass = «ec.ESuperTypes.get(0).name»
					«ENDIF»
					«val List<EAttribute> sortedAList = new ArrayList<EAttribute>(ec.EAttributes)»
					, attributes = ( «EcoreAttributeNormaliser.normalize(sortedAList)»
						«FOR ea: sortedAList SEPARATOR ","»
						«ea.name» : «ea.EType.name» [«ea.lowerBound»..«ea.upperBound»] (
							  changeable=«ea.changeable»
							, derived=«ea.isDerived»
							, many=«ea.isMany»
							, ordered=«ea.isOrdered»
							, required=«ea.isRequired»
							, transient=«ea.isTransient»
							, unique=«ea.isUnique»
							, unsettable=«ea.isUnsettable»
							, volatile=«ea.isVolatile»
						)
						«ENDFOR»
					)
					«val List<EReference> sortedRList = new ArrayList<EReference>(ec.EReferences)»
					, references = ( «EcoreReferenceNormaliser.normalize(sortedRList)»
						«FOR er: sortedRList SEPARATOR ","»
						«er.name» : «er.EType.name» [«er.lowerBound»..«er.upperBound»] (
							  changeable=«er.isChangeable»
							, container=«er.isContainer»
							, containment=«er.isContainment»
							, derived=«er.isDerived»
							, many=«er.isMany»
							, ordered=«er.isOrdered»
							, required=«er.isRequired»
							, transient=«er.isTransient»
							, unique=«er.isUnique»
							, unsettable=«er.isUnsettable»
							, volatile=«er.isVolatile»
							«IF er.EOpposite != null»
							, opposite=«(er.EOpposite.eContainer as EClassifier).name»::«er.EOpposite.name»
							«ENDIF»
						)
						«ENDFOR»
					)
					«val List<EOperation> sortedOList = new ArrayList<EOperation>(ec.EOperations)»
					, operations = ( «EcoreOperationNormaliser.normalize(sortedOList)»
						«FOR eo: sortedOList SEPARATOR ","»
						«eo.name» («FOR eop: eo.EParameters SEPARATOR ", "»«eop.EType?.name»«ENDFOR») : «eo.EType?.name» [«eo.lowerBound»..«eo.upperBound»]
						«ENDFOR»
					)
				}
				«ENDFOR»
			]
		}
		'''
	}
}