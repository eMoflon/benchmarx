package org.benchmarx.petrinetweighted.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;

import pnw.Edge;
import pnw.Net;
import pnw.PTEdge;
import pnw.Place;
import pnw.PnwFactory;
import pnw.TPEdge;
import pnw.Transition;

/**
 * Builder class for easily creating weighted PetriNets using a Java API, general assumptions for the model:
 * <ul><li>unique names for Places and Transitions</li>
 * <li>not more then one edge between a place/transition or a transition/place pair</li></ul>
 * @author tbuchmann
 *
 */
public class PNWBuilder {
	public PNWBuilder(Net n) {
		net = n;
	}
	
	public PNWBuilder netName(String name) {
		net.setName(name);
		return this;
	}
	
	public PNWBuilder place(String name, int numberOfTokens) {
		Place p = f.createPlace();
		p.setName(name);
		p.setNoOfTokens(numberOfTokens);
		net.getElements().add(p);
		return this;
	}
	
	public PNWBuilder deletePlace(String place) {
		ArrayList<Edge> edges = new ArrayList<>();
		edges.addAll(findPlaceByName(place).getOutPTEdges());
		edges.addAll(findPlaceByName(place).getInTPEdges());
		for (Edge edge : edges) {
			EcoreUtil.delete(edge);
		}
		EcoreUtil.delete(findPlaceByName(place));
		return this;
	}
	
	public PNWBuilder renamePlace(String oldName, String newName) {
		findPlaceByName(oldName).setName(newName);
		return this;
	}
	
	public PNWBuilder changeTokens(String place, int tokens) {
		findPlaceByName(place).setNoOfTokens(tokens);
		return this;
	}
	
	public PNWBuilder transition(String name, String source, String target, int sourceWeight, int targetWeight) {
		Transition trans = findTransitionByName(name); 
		if (trans == null) {		
			trans = f.createTransition();
			trans.setName(name);
			net.getElements().add(trans);
		}
		lastTransition = trans;
		if (source != null) {
			addSource(source, sourceWeight);
		}
		if (target != null) {
			addTarget(target, targetWeight);
		}
		return this;
	}
	
	public PNWBuilder deleteTransition(String transition) {
		ArrayList<Edge> edges = new ArrayList<>();
		edges.addAll(findTransitionByName(transition).getInPTEdges());
		edges.addAll(findTransitionByName(transition).getOutTPEdges());
		for (Edge edge : edges) {
			EcoreUtil.delete(edge);
		}
		EcoreUtil.delete(findTransitionByName(transition));
		return this;
	}
	
	public PNWBuilder renameTransition(String oldName, String newName) {
		findTransitionByName(oldName).setName(newName);
		return this;
	}
	
	public PNWBuilder addSource(String place, int weight) {
		return changeSource(place, weight, true);
	}
	public PNWBuilder addTarget(String place, int weight) {
		return changeTarget(place, weight, true);
	}
	public PNWBuilder removeSource(String place) {
		return changeSource(place, -1, false);
	}
	public PNWBuilder removeTarget(String place) {
		return changeTarget(place, -1, false);
	}
	
	public PNWBuilder reconnectPTEdge(String oldPlace, String oldTransition, String newPlace, String newTransition) {
		if (findPlaceByName(newPlace) == null) {
			throw new IllegalArgumentException("No place with name " + newPlace + ".");
		}
		if (findTransitionByName(newTransition) == null) {
			throw new IllegalArgumentException("No transition with name " + newTransition + ".");
		}
		
		PTEdge edge = findPTEdge(oldPlace, oldTransition);
		edge.setFromPlace(findPlaceByName(newPlace));
		edge.setToTransition(findTransitionByName(newTransition));
		return this;
	}
	public PNWBuilder reconnectTPEdge(String oldTransition, String oldPlace, String newTransition, String newPlace) {
		if (findPlaceByName(newPlace) == null) {
			throw new IllegalArgumentException("No place with name " + newPlace + ".");
		}
		if (findTransitionByName(newTransition) == null) {
			throw new IllegalArgumentException("No transition with name " + newTransition + ".");
		}
		
		TPEdge edge = findTPEdge(oldTransition, oldPlace);
		edge.setFromTransition(findTransitionByName(newTransition));
		edge.setToPlace(findPlaceByName(newPlace));
		return this;
	}
	
	public PNWBuilder weightPTEdge(String place, String transition, int weight) {
		findPTEdge(place, transition).setWeight(weight);
		return this;
	}
	public PNWBuilder weightTPEdge(String transition, String place, int weight) {
		findTPEdge(transition, place).setWeight(weight);
		return this;
	}
	
	private final Net net;
	private final PnwFactory f = PnwFactory.eINSTANCE;
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
	
	private PTEdge findPTEdge(String place, String transition) {
		Place source = findPlaceByName(place);
		if (source == null) {
			throw new IllegalArgumentException("No place with name " + place + ".");
		}
		
		return source.getOutPTEdges().stream()
				.filter(e -> e.getToTransition().getName().equals(transition))
				.findAny().get();
	}
	private TPEdge findTPEdge(String transition, String place) {
		Transition source = findTransitionByName(transition);
		if (source == null) {
			throw new IllegalArgumentException("No transition with name " + transition + ".");
		}
		
		return source.getOutTPEdges().stream()
				.filter(e -> e.getToPlace().getName().equals(place))
				.findAny().get();
	}
	
	private PNWBuilder changeSource(String place, int weight, boolean add) {
		if (lastTransition == null) {
			throw new IllegalStateException("No transition created to change Source.");
		}
		
		Place s = findPlaceByName(place);
		if (s == null) {
			throw new IllegalArgumentException("No place with name " + place + " in the net.");
		}
		
		PTEdge edge = lastTransition.getInPTEdges().stream()
				.filter(e -> e.getFromPlace().equals(s))
				.findAny().orElse(null);
		if (add && edge == null) {
			PTEdge pte = f.createPTEdge();
			pte.setWeight(weight);
			pte.setFromPlace(s);
			pte.setToTransition(lastTransition);
			pte.setNet(net);
		} else if (!add && edge != null) {
			EcoreUtil.delete(edge);
		}
		return this;
	}
	private PNWBuilder changeTarget(String place, int weight, boolean add) {
		if (lastTransition == null) {
			throw new IllegalStateException("No transition created to change Source.");
		}
		
		Place t = findPlaceByName(place);
		if (t == null) {
			throw new IllegalArgumentException("No place with name " + place + " in the net.");
		}
		
		TPEdge edge = lastTransition.getOutTPEdges().stream()
				.filter(e -> e.getToPlace().equals(t))
				.findAny().orElse(null);
		if (add && edge == null) {
			TPEdge tpe = f.createTPEdge();
			tpe.setWeight(weight);
			tpe.setToPlace(t);
			tpe.setFromTransition(lastTransition);
			tpe.setNet(net);
		} else if (!add && edge != null) {
			EcoreUtil.delete(edge);
		}
		return this;
	}
}
