package org.benchmarx.sql.core;

import sql.Annotation;
import sql.Schema;
import sql.SqlFactory;
import sql.Table;

public class SchemaBuilder extends SQLBuilder {
	
	private Schema schema;
	
	public SchemaBuilder(Schema s) {
		super(null);
		this.schema = s;
	}
	
	public SchemaBuilder name(String value) {
		schema.setName(value);
		return this;
	}
	
	public TableBuilder table() {
		Table table = SqlFactory.eINSTANCE.createTable();
		schema.getOwnedTables().add(table);
		return new TableBuilder(table, this);
	}
	
	public AnnotationBuilder annotation() {
		Annotation a = SqlFactory.eINSTANCE.createAnnotation();
		schema.getOwnedAnnotations().add(a);
		return new AnnotationBuilder(a, this);
	}
}
