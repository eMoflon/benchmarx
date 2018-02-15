package org.benchmarx.sql.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sql.Column;

public class SQLColumnNormaliser implements Comparator<Column> {
	public int compare(Column arg0, Column arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}
	
	public static void normalize(List<Column> columns) {
		Comparator<Column> comparator = new SQLColumnNormaliser();
		Collections.sort(columns, comparator);
	}
}
