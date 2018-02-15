package org.benchmarx.petrinet.core;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;

import pn.Net;
import pn.Place;
import pn.PnFactory;
import pn.Transition;

/**
 * Builder class for easily creating (unweighted) PetriNets using a Java API, general assumptions for the model:
 * <ul><li>unique names for Places and Transitions</li>
 * <li>not more then one edge between a place/transition or a transition/place pair</li></ul>
 * @author tbuchmann
 *
 */
public class PNBuilder {
	public PNBuilder(Net n) {
		net = n;
	}
	
	public PNBuilder netName(String name) {
		net.setName(name);
		return this;
	}
	
	public PNBuilder place(String name, int numberOfTokens) {
		Place p = f.createPlace();
		p.setName(name);
		p.setNoOfTokens(numberOfTokens);
		net.getElements().add(p);
		return this;
	}
	
	public PNBuilder deletePlace(String place) {
		EcoreUtil.delete(findPlaceByName(place));
		return this;
	}
	
	public PNBuilder renamePlace(String oldName, String newName) {
		findPlaceByName(oldName).setName(newName);
		return this;
	}
	
	public PNBuilder changeTokens(String place, int tokens) {
		findPlaceByName(place).setNoOfTokens(tokens);
		return this;
	}
	
	public PNBuilder transition(String name, String source, String target) {
		Transition trans = findTransitionByName(name); 
		if (trans == null) {
			trans = f.createTransition();
			trans.setName(name);
			net.getElements().add(trans);
		}
		lastTransition = trans;
		if (source != null) {
			addSource(source);
		}
		if (target != null) {
			addTarget(target);
		}
		return this;
	}
	
	public PNBuilder deleteTransition(String transition) {
		EcoreUtil.delete(findTransitionByName(transition));
		return this;
	}
	
	public PNBuilder renameTransition(String oldName, String newName) {
		findTransitionByName(oldName).setName(newName);
		return this;
	}
	
	public PNBuilder addSource(String place) {
		return changeSource(place, true);
	}
	public PNBuilder addTarget(String place) {
		return changeTarget(place, true);
	}
	public PNBuilder removeSource(String place) {
		return changeSource(place, false);
	}
	public PNBuilder removeTarget(String place) {
		return changeTarget(place, false);
	}
	
	private final Net net;
	private final PnFactory f = PnFactory.eINSTANCE;
	private Transition lastTransition;
	
	private Place findPlaceByName(String name) {
		List<Place> result = net.getElements().stream()
				.filter(Place.class::isInstance)
				.map(Place.class::cast)
				.filter(a -> a.getName().equals(name))
				.collect(Collectors.toList());
			
		if (result.size() > 0) return result.get(0);
		else return null;
	}
	
	private Transition findTransitionByName(String name) {
		List<Transition> result = net.getElements().stream()
				.filter(Transition.class::isInstance)
				.map(Transition.class::cast)
				.filter(t -> t.getName().equals(name))
				.collect(Collectors.toList());
		
		if (result.size() > 0) return result.get(0);
		else return null;
	}
	
	private PNBuilder changeSource(String place, boolean add) {
		if (lastTransition == null) {
			throw new IllegalStateException("No transition created to change Source.");
		}
		
		Place s = findPlaceByName(place);
		if (s == null) {
			throw new IllegalArgumentException("No place with name " + place + " in the net.");
		}
		
		if (add && !lastTransition.getSrcP2T().contains(s)) {
			lastTransition.getSrcP2T().add(s);
		} else if (!add && lastTransition.getSrcP2T().contains(s)) {
			lastTransition.getSrcP2T().remove(s);
		}
		return this;
	}
	private PNBuilder changeTarget(String place, boolean add) {
		if (lastTransition == null) {
			throw new IllegalStateException("No transition created to change Source.");
		}
		
		Place t = findPlaceByName(place);
		if (t == null) {
			throw new IllegalArgumentException("No place with name " + place + " in the net.");
		}
		
		if (add && !lastTransition.getTrgT2P().contains(t)) {
			lastTransition.getTrgT2P().add(t);
		} else if (!add && lastTransition.getTrgT2P().contains(t)) {
			lastTransition.getTrgT2P().remove(t);
		}
		return this;
	}
}
