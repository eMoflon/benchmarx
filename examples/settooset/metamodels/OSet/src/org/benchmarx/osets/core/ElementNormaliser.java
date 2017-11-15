package org.benchmarx.osets.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import osets.Element;

public class ElementNormaliser implements Comparator<Element> {
	@Override
	public int compare(Element o1, Element o2) {
		if (o1.getValue().equals(o2.getValue())) {
			return 0;
		}
		
		for (Element previous = o2.getPrevious(); previous != null; previous = previous.getPrevious()) {
			if (previous.getValue().equals(o2.getValue())) {
				throw new IllegalArgumentException("The reference element is part of a cycle.");
			}
		}
		
		for (Element previous = o2.getPrevious(); previous != null; previous = previous.getPrevious()) {
			if (previous.getValue().equals(o1.getValue())) {
				return -1;
			}
		}
		
		for (Element next = o2.getNext(); next != null; next = next.getNext()) {
			if (next.getValue().equals(o1.getValue())) {
				return 1;
			}
		}
		
		throw new IllegalArgumentException("Elements not connected.");
	}

	public void normalize(List<Element> elements){
		Comparator<Element> comparator = new ElementNormaliser();
		Collections.sort(elements, comparator);
	}
}
