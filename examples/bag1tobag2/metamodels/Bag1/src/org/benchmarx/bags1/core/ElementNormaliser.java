package org.benchmarx.bags1.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bags1.Element;

public class ElementNormaliser implements Comparator<Element> {
	@Override
	public int compare(Element o1, Element o2) {
		int val = o1.getValue().compareTo(o2.getValue());
		if(val == 0) {
			return o1.getIncrementalID().compareTo(o2.getIncrementalID());
		} else {
			return val;
		}
		
	}

	public void normalize(List<Element> elements){
		Comparator<Element> comparator = new ElementNormaliser();
		Collections.sort(elements, comparator);
	}
}
