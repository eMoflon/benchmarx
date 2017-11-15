package org.benchmarx.sql.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sql.Annotation;

public class SQLAnnotationNormaliser implements Comparator<Annotation> {
	public int compare(Annotation arg0, Annotation arg1) {
		return arg0.getAnnotation().compareTo(arg1.getAnnotation());
	}
	
	public static void normalize(List<Annotation> annotations) {
		Comparator<Annotation> comparator = new SQLAnnotationNormaliser();
		Collections.sort(annotations, comparator);
	}
}
