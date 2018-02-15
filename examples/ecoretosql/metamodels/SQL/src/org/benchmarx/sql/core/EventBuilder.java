package org.benchmarx.sql.core;

import sql.Action;
import sql.Condition;
import sql.Event;

public class EventBuilder extends SQLBuilder {

	private Event event;

	public EventBuilder(Event e, SQLBuilder b) {
		super(b);
		this.event = e;
	}
	
	public EventBuilder action(Action a) {
		event.setAction(a);
		return this;
	}
	
	public EventBuilder condition(Condition c) {
		event.setCondition(c);
		return this;
	}

}
