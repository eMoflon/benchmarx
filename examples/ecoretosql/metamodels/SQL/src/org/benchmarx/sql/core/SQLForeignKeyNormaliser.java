package org.benchmarx.sql.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sql.ForeignKey;

public class SQLForeignKeyNormaliser implements Comparator<ForeignKey> {
	public int compare(ForeignKey arg0, ForeignKey arg1) {
		return arg0.getColumn().getName().compareTo(arg1.getColumn().getName());
	}
	
	public static void normalize(List<ForeignKey> foreignKeys) {
		Comparator<ForeignKey> comparator = new SQLForeignKeyNormaliser();
		Collections.sort(foreignKeys, comparator);
	}
}
