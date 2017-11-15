package org.benchmarx.sql.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sql.Table;

public class SQLTableNormaliser implements Comparator<Table> {

	public int compare(Table arg0, Table arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}
	
	public static void normalize(List<Table> tables) {
		Comparator<Table> comparator = new SQLTableNormaliser();
		Collections.sort(tables, comparator);
	}

}
