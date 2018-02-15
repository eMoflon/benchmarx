package org.benchmarx.petrinetweighted.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.benchmarx.emf.Comparator;

import pnw.Net;
import pnw.NetElement;
import pnw.PTEdge;
import pnw.Place;
import pnw.TPEdge;
import pnw.Transition;

public class PNWComparator implements Comparator<Net> {
	public static String netToString(Net net) {
		String name = net.getName() == null ? "" : net.getName();
		ArrayList<Place> places = new ArrayList<>();
		ArrayList<Transition> transitions = new ArrayList<>();
		ArrayList<PTEdge> ptEdges = new ArrayList<>();
		ArrayList<TPEdge> tpEdges = new ArrayList<>();
		for (NetElement element : net.getElements()) {
			if (element instanceof Place) {
				places.add((Place) element);
			} else if (element instanceof Transition) {
				transitions.add((Transition) element);
			} else if (element instanceof PTEdge) {
				ptEdges.add((PTEdge) element);
			} else if (element instanceof TPEdge) {
				tpEdges.add((TPEdge) element);
			} else {
				return "invalid PetriNetWeighted: unknown net element!";
			}
		}
		try {
			return "PetriNetWeighted " + name + " " + net.getIncrementalID() + " {Places: " + placesToString(places)
				+ ", Transitions: " + transitionsToString(transitions)
				+ ", PTEdges: " + ptEdgesToString(ptEdges)
				+ ", TPEdges: " + tpEdgesToString(tpEdges) + "}";
		} catch (IllegalArgumentException e) {
			if (e.getMessage().equals("The list contains an unconnected edge.")) {
				return "invalid PetriNetWeighted: the net contains an unconnected edge!";
			} else {
				throw e;
			}
		}
	}
	
	@Override
	public void assertEquals(Net expected, Net actual) {
		assertTrue(netToString(expected).startsWith("PetriNetWeighted "));
		org.junit.Assert.assertEquals(netToString(expected), netToString(actual));
	}
	
	private static String placesToString(List<Place> places) {
		places.sort((lhs, rhs) -> lhs.getName().compareTo(rhs.getName()));
		return places.stream()
				.map(e -> "{" + e.getName() + ", " + e.getNoOfTokens() + "}")
				.collect(Collectors.joining(", "));
	}
	private static String transitionsToString(List<Transition> transitions) {
		transitions.sort((lhs, rhs) -> lhs.getName().compareTo(rhs.getName()));
		return transitions.stream()
				.map(e -> "{" + e.getName() + "}")
				.collect(Collectors.joining(", "));
	}
	private static String ptEdgesToString(List<PTEdge> ptEdges) {
		ptEdges.sort((lhs, rhs) -> {
			if (lhs.getFromPlace() == null || lhs.getToTransition() == null
					|| rhs.getFromPlace() == null || rhs.getToTransition() == null) {
				throw new IllegalArgumentException("The list contains an unconnected edge.");
			}
			int result = lhs.getFromPlace().getName().compareTo(rhs.getFromPlace().getName());
			if (result == 0) {
				result = lhs.getToTransition().getName().compareTo(rhs.getToTransition().getName());
			}
			return result;
		});
		return ptEdges.stream()
				.map(e -> "{" + e.getFromPlace().getName() + ", " + e.getToTransition().getName()
						+ ", " + e.getWeight() + "}")
				.collect(Collectors.joining(", "));
	}
	private static String tpEdgesToString(List<TPEdge> tpEdges) {
		tpEdges.sort((lhs, rhs) -> {
			if (lhs.getFromTransition() == null || lhs.getToPlace() == null
					|| rhs.getFromTransition() == null || rhs.getToPlace() == null) {
				throw new IllegalArgumentException("The list contains an unconnected edge.");
			}
			int result = lhs.getFromTransition().getName().compareTo(rhs.getFromTransition().getName());
			if (result == 0) {
				result = lhs.getToPlace().getName().compareTo(rhs.getToPlace().getName());
			}
			return result;
		});
		return tpEdges.stream()
				.map(e -> "{" + e.getFromTransition().getName() + ", " + e.getToPlace().getName()
						+ ", " + e.getWeight() + "}")
				.collect(Collectors.joining(", "));
	}
}