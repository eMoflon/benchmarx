package org.benchmarx.sql.core;

import sql.Annotation;
import sql.Column;
import sql.Property;
import sql.SqlFactory;

public class ColumnBuilder extends SQLBuilder {
	
	private Column column;

	public ColumnBuilder(Column c, SQLBuilder b) {
		super(b);
		this.column = c;
	}
	
	public ColumnBuilder name(String name) {
		column.setName(name);
		return this;
	}
	
	public ColumnBuilder type(String type) {
		column.setType(type);
		return this;
	}
	
	public AnnotationBuilder annotation() {
		Annotation a = SqlFactory.eINSTANCE.createAnnotation();
		column.getOwnedAnnotations().add(a);
		return new AnnotationBuilder(a, this);
	}
	
	public ColumnBuilder property(Property p) {
		column.getProperties().add(p);
		return this;
	}
}
