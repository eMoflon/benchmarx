package org.benchmarx.sql.core;

import java.util.Optional;

import sql.Annotation;
import sql.Column;
import sql.Event;
import sql.ForeignKey;
import sql.Schema;
import sql.SqlFactory;
import sql.Table;

public class ForeignKeyBuilder extends SQLBuilder {
	
	private ForeignKey key;

	public ForeignKeyBuilder(ForeignKey c, SQLBuilder b) {
		super(b);
		this.key = c;
	}
	
	public AnnotationBuilder annotation() {
		Annotation a = SqlFactory.eINSTANCE.createAnnotation();
		key.getOwnedAnnotations().add(a);
		return new AnnotationBuilder(a, this);
	}
	
	public EventBuilder event() {
		Event e = SqlFactory.eINSTANCE.createEvent();
		key.getOwnedEvents().add(e);
		return new EventBuilder(e, this);
	}
	
	public ForeignKeyBuilder referencedTable(String name) {
		Schema s = key.getOwningTable().getOwningSchema();
		Optional<Table> ot = s.getOwnedTables().stream().filter(t->t.getName().equals(name)).findAny();
		if(!ot.isPresent()) {
			throw new IllegalArgumentException("Cannot find table with name: " + name);
		}
		key.setReferencedTable(ot.get());
		return this;
	}
	
	public ForeignKeyBuilder referencedColumn(String name) {
		Table t = key.getOwningTable();
		Optional<Column> oc = t.getOwnedColumns().stream().filter(c->c.getName().equals(name)).findAny();
		if(!oc.isPresent()) {
			throw new IllegalArgumentException("Cannot find column with name: " + name);
		}
		key.setColumn(oc.get());
		return this;
	}
}
