package org.benchmarx.sets.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sets.Element;

public class ElementNormaliser implements Comparator<Element> {
	@Override
	public int compare(Element o1, Element o2) {
		return o1.getValue().compareTo(o2.getValue());
	}

	public void normalize(List<Element> elements){
		Comparator<Element> comparator = new ElementNormaliser();
		Collections.sort(elements, comparator);
	}
}
