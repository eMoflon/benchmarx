package org.benchmarx.sql.core;

import java.util.Optional;

import sql.Annotation;
import sql.Column;
import sql.PrimaryKey;
import sql.SqlFactory;
import sql.Table;

public class PrimaryKeyBuilder extends SQLBuilder {

	private PrimaryKey key;

	public PrimaryKeyBuilder(PrimaryKey c, SQLBuilder b) {
		super(b);
		this.key = c;
	}
	
	public AnnotationBuilder annotation() {
		Annotation a = SqlFactory.eINSTANCE.createAnnotation();
		key.getOwnedAnnotations().add(a);
		return new AnnotationBuilder(a, this);
	}
	
	public PrimaryKeyBuilder referencedColumn(String name) {
		Table t = key.getOwningTable();
		Optional<Column> oc = t.getOwnedColumns().stream().filter(c->c.getName().equals(name)).findAny();
		if(!oc.isPresent()) {
			throw new IllegalArgumentException("Cannot find column with name: " + name);
		}
		key.setColumn(oc.get());
		return this;
	}
}
