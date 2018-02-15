package org.benchmarx.sql.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sql.Event;

public class SQLEventNormaliser implements Comparator<Event>{

	@Override
	public int compare(Event o1, Event o2) {
		if(o1.getCondition().compareTo(o2.getCondition()) != 0)
			return o1.getCondition().compareTo(o2.getCondition());
		else
			return o1.getAction().compareTo(o2.getAction());
	}
	
	public static void normalize(List<Event> events) {
		Comparator<Event> comparator = new SQLEventNormaliser();
		Collections.sort(events, comparator);
	}

}
