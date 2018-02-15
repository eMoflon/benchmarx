package org.benchmarx.sql.core

import org.benchmarx.emf.Comparator
import sql.Schema

import static org.junit.Assert.*
import java.util.List
import java.util.ArrayList
import sql.Table
import sql.Column
import sql.Property
import sql.ForeignKey
import sql.Annotation
import sql.Event

class SQLComparator implements Comparator<Schema>{
	
	override assertEquals(Schema expected, Schema actual) {
		assertTrue(schemaToString(expected).startsWith("SQLSchema"))
		assertEquals(schemaToString(expected), schemaToString(actual))
	}
	
	def schemaToString(Schema s) {
		return '''
		SQLSchema «s.name» {
			  tables = [
				«val List<Table> sortedList = new ArrayList<Table>(s.ownedTables)»
				«SQLTableNormaliser.normalize(sortedList)»
				«FOR t: sortedList SEPARATOR ","»
				«t.name» {
					«IF t.ownedPrimaryKey != null»
					  primaryKey = (
					  	  columnName = «t.ownedPrimaryKey.column.name»
					  	, annotations = «t.ownedPrimaryKey.ownedAnnotations.annotations»
					)
					«ENDIF»
					, foreignKeys = {«val List<ForeignKey> sortedFList = new ArrayList<ForeignKey>(t.ownedForeignKeys)»
					«SQLForeignKeyNormaliser.normalize(sortedFList)»
						«FOR f : sortedFList SEPARATOR ","»
						(
							  columnName = «f.column?.name»
							, referencedTable = «f.referencedTable.name»
							, events = [
								«f.ownedEvents.events»
							  ]
							, annotations = «f.ownedAnnotations.annotations»
						)
						«ENDFOR»
					}
					«val List<Column> sortedCList = new ArrayList<Column>(t.ownedColumns)»«SQLColumnNormaliser.normalize(sortedCList)»
					, columns = ( 
						«FOR c : sortedCList SEPARATOR ","»
						«c.name» [
							  type = «c.type»
							, properties = [«val List<Property> sortedPList = new ArrayList<Property>(c.properties)»«SQLPropertyNormaliser.normalize(sortedPList)»«FOR p : sortedPList SEPARATOR ", "»«p»«ENDFOR»]
							, annotations = «c.ownedAnnotations.annotations»
						]
						«ENDFOR»
					)
					, annotations = «t.ownedAnnotations.annotations»
				}
				«ENDFOR»
			  ]
			, annotations = «s.ownedAnnotations.annotations»
		}
		'''
	}
	
	def private annotations(List<Annotation> annot) {
		return '''
		[«val List<Annotation> sortedAList = new ArrayList<Annotation>(annot)»«SQLAnnotationNormaliser.normalize(sortedAList)»
			«FOR a : sortedAList SEPARATOR ", "»
			«a.annotation»
			«ENDFOR»
		]
		'''
	}
	
	def private events(List<Event> events) {
		return '''
		[«val List<Event> sortedList = new ArrayList<Event>(events)»«SQLEventNormaliser.normalize(sortedList)»
			«FOR e : sortedList SEPARATOR ", "»
			on «e.condition» : «e.action»
			«ENDFOR»
		]
		'''
	}
}