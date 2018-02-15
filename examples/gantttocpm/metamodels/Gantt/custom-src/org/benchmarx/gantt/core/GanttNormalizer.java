package org.benchmarx.gantt.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import gantt.Activity;
import gantt.Dependency;
import gantt.Element;

public final class GanttNormalizer {
	public static void normalize(List<Element> elements) {
		Comparator<Element> comparator = new ElementComparator();
		Collections.sort(elements, comparator);
	}
	
	private static class ElementComparator implements Comparator<Element> {
		@Override
		public int compare(Element lhs, Element rhs) {
			if (lhs instanceof Dependency && rhs instanceof Activity) {
				return 1;
			} else if (lhs instanceof Activity && rhs instanceof Dependency) {
				return -1;
			} else if (lhs instanceof Activity && rhs instanceof Activity) {
				return ((Activity) lhs).getName().compareTo(((Activity) rhs).getName());
			} else {
				int result = ((Dependency) lhs).getPredecessor().getName()
						.compareTo(((Dependency) rhs).getPredecessor().getName());
				if (result == 0) {
					result = ((Dependency) lhs).getSuccessor().getName()
							.compareTo(((Dependency) rhs).getSuccessor().getName());
				}
				if (result == 0) {
					result = ((Dependency) lhs).getDependencyType()
							.compareTo(((Dependency) rhs).getDependencyType());
				}
				if (result == 0) {
					result = ((Integer) ((Dependency) lhs).getOffset())
							.compareTo(((Dependency) rhs).getOffset());
				}
				return result;
			}
		}	
	}
	
	private GanttNormalizer() {
	}
}
