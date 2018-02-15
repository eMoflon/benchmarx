package org.benchmarx.sql.core;

import sql.Annotation;

public class AnnotationBuilder extends SQLBuilder {
	
	private Annotation annot;

	public AnnotationBuilder(Annotation a, SQLBuilder b) {
		super(b);
		this.annot = a;
	}
	
	public AnnotationBuilder name(String name) {
		annot.setAnnotation(name);
		return this;
	}
}
