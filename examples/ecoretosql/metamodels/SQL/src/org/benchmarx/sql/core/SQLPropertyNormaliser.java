package org.benchmarx.sql.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sql.Property;

public class SQLPropertyNormaliser implements Comparator<Property> {
	
	public int compare(Property arg0, Property arg1) {
		return arg0.compareTo(arg1);
	}
	
	public static void normalize(List<Property> tables) {
		Comparator<Property> comparator = new SQLPropertyNormaliser();
		Collections.sort(tables, comparator);
	}
}
