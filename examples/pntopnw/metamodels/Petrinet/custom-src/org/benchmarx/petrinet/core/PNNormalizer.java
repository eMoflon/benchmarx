package org.benchmarx.petrinet.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pn.NetElement;
import pn.Place;
import pn.Transition;

public final class PNNormalizer {
	public static void normalize(List<NetElement> elements) {
		Comparator<NetElement> comparator = new NetElementComparator();
		Collections.sort(elements, comparator);
	}
	
	private static class NetElementComparator implements Comparator<NetElement> {
		@Override
		public int compare(NetElement lhs, NetElement rhs) {
			if (lhs instanceof Place && rhs instanceof Transition) {
				return -1;
			} else if (lhs instanceof Transition && rhs instanceof Place) {
				return 1;
			} else if (lhs instanceof Place && rhs instanceof Place) {
				return ((Place) lhs).getName().compareTo(((Place) rhs).getName());
			} else {
				return ((Transition) lhs).getName().compareTo(((Transition) rhs).getName());
			}
		}	
	}
	
	private PNNormalizer() {
	}
}
