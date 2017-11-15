package org.benchmarx.sql.core;

import sql.Annotation;
import sql.Column;
import sql.ForeignKey;
import sql.PrimaryKey;
import sql.SqlFactory;
import sql.Table;

public class TableBuilder extends SQLBuilder {

	private Table table;
	
	public TableBuilder(Table t, SQLBuilder b) {
		super(b);
		this.table = t;
	}
	
	public TableBuilder name(String name) {
		table.setName(name);
		return this;
	}
	
	public AnnotationBuilder annotation() {
		Annotation a = SqlFactory.eINSTANCE.createAnnotation();
		table.getOwnedAnnotations().add(a);
		return new AnnotationBuilder(a, this);
	}
	
	public ColumnBuilder column() {
		Column c = SqlFactory.eINSTANCE.createColumn();
		table.getOwnedColumns().add(c);
		return new ColumnBuilder(c, this);
	}
	
	public PrimaryKeyBuilder primaryKey() {
		PrimaryKey p = SqlFactory.eINSTANCE.createPrimaryKey();
		table.setOwnedPrimaryKey(p);
		return new PrimaryKeyBuilder(p, this);
	}
	
	public ForeignKeyBuilder foreignKey() {
		ForeignKey f = SqlFactory.eINSTANCE.createForeignKey();
		table.getOwnedForeignKeys().add(f);
		return new ForeignKeyBuilder(f, this);
	}
}
