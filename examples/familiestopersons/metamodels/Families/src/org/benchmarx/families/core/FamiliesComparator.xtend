package org.benchmarx.families.core

import Families.Family
import Families.FamilyMember
import Families.FamilyRegister
import java.util.ArrayList
import java.util.List
import java.util.function.BiConsumer

import static org.junit.Assert.*

class FamiliesComparator implements BiConsumer<FamilyRegister, FamilyRegister> {
	FamilyNormaliser comparator
	FamilyMemberNormaliser familyMemberComparator
	boolean checkAttributeValues
	
	new (){
		comparator = new FamilyNormaliser();
		familyMemberComparator = new FamilyMemberNormaliser();
		checkAttributeValues = true
	}
	
	new (boolean checkAttributeValues){
		comparator = new FamilyNormaliser();
		familyMemberComparator = new FamilyMemberNormaliser();
		this.checkAttributeValues = checkAttributeValues
	}
	
	override accept(FamilyRegister expected, FamilyRegister actual) {	
		assertTrue(familyToString(expected).startsWith("FamilyRegister"))
		assertEquals(familyToString(expected), familyToString(actual))
	}
	
	def familyToString(FamilyRegister families) {
		return '''
		FamilyRegister {
		    families = [
		    «val List<Family> sortedList = new ArrayList<Family>(families.families)»
		    «comparator.normalize(sortedList)»
			«FOR f : sortedList SEPARATOR ", "»
			Family {
				     familyName = «IF checkAttributeValues»"«f.name»"«ELSE»«ENDIF»
				    ,father     = «IF checkAttributeValues»«maybeFamilyMember(f.father)»«ELSE»«ENDIF»
				    ,mother     = «IF checkAttributeValues»«maybeFamilyMember(f.mother)»«ELSE»«ENDIF»
			«val List<FamilyMember> sortedListOfSon = new ArrayList<FamilyMember>(f.sons)»
			«IF checkAttributeValues»«familyMemberComparator.normalize(sortedListOfSon)»«ELSE»«ENDIF»
				    ,sons       = [«FOR son : sortedListOfSon SEPARATOR ", "»«IF checkAttributeValues»«familyMember(son)»«ELSE»son«ENDIF»«ENDFOR»]
			«val List<FamilyMember> sortedListOfDaughter = new ArrayList<FamilyMember>(f.daughters)»
			«IF checkAttributeValues»«familyMemberComparator.normalize(sortedListOfDaughter)»«ELSE»«ENDIF»
				    ,daughters  = [«FOR daughter : sortedListOfDaughter SEPARATOR ", "»«IF checkAttributeValues»«familyMember(daughter)»«ELSE»daughter«ENDIF»«ENDFOR»]
				}
			«ENDFOR»
			]
		}
		'''
	}
	
	def maybeFamilyMember(FamilyMember fm){
		return '''«IF fm !== null»Just («familyMember(fm)»)«ELSE»Nothing«ENDIF»'''
	}
	
	def familyMember(FamilyMember fm){
		return '''FamilyMember { firstName = "«fm.name»" }'''
	}
}

	

