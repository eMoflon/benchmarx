package org.benchmarx.cpm.core;

import java.util.List;
import java.util.stream.Collectors;

import cpm.Activity;
import cpm.CPMNetwork;
import cpm.CpmFactory;
import cpm.Event;

/**
 * Builder class for easily creating CPM Networks using a Java API
 * general assumption: unique names for Activities
 * @author tbuchmann
 *
 */
public class CPMBuilder {
	
	private final CPMNetwork net;
	private final CpmFactory f = CpmFactory.eINSTANCE;
	private static int number = 1;
	
	public CPMBuilder(String name) {
		net = f.createCPMNetwork();
		net.setName(name);
	}
	
	public CPMBuilder(CPMNetwork network) {
		net = network;
	}
	
	public CPMBuilder name(String name) {
		net.setName(name);
		return this;
	}
	
	public CPMBuilder event() {
		Event e = f.createEvent();
		e.setNumber(number);
		number++;
		net.getElements().add(e);		
		return this;
	}
	
	public CPMBuilder events(int num) {		
		for (int i = 1; i <= num; i++) {
			Event e = f.createEvent();
			e.setNumber(number);
			number++;
			net.getElements().add(e);
		}
		return this;
	}
	
	public CPMBuilder activity(int e1, int e2, String name, int duration) {
		Event s = findEventByNumber(e1);
		Event t = findEventByNumber(e2);
		Activity a = f.createActivity();
		a.setName(name);
		a.setDuration(duration);
		a.setSourceEvent(s);
		a.setTargetEvent(t);
		net.getElements().add(a);		
		return this;
	}
	
	public CPMNetwork end() {
		return net;
	}
	
	private Event findEventByNumber(int number) {
		List<Event> result = net.getElements().stream()
				.filter(Event.class::isInstance)
				.map(Event.class::cast)
				.filter(e -> e.getNumber() == number)
				.collect(Collectors.toList());
			
			if (result.size() > 0) return result.get(0);
			else return null;
	}
	
	public static void reset() {
		number = 1;
	}
}
