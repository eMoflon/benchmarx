package org.benchmarx.cpm.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cpm.Activity;
import cpm.Element;
import cpm.Event;

public final class CPMNormalizer {
	public static void normalize(List<Element> elements) {
		Comparator<Element> comparator = new ElementComparator();
		Collections.sort(elements, comparator);
	}
	
	private static class ElementComparator implements Comparator<Element> {
		@Override
		public int compare(Element lhs, Element rhs) {
			if (lhs instanceof Event && rhs instanceof Activity) {
				return -1;
			} else if (lhs instanceof Activity && rhs instanceof Event) {
				return 1;
			} else if (lhs instanceof Event && rhs instanceof Event) {
				ArrayList<Activity> activityCandidatesLhs = new ArrayList<Activity>();
				activityCandidatesLhs.addAll(((Event) lhs).getOutgoingActivities());
				activityCandidatesLhs.addAll(((Event) lhs).getIncomingActivities());
				ArrayList<Activity> activityCandidatesRhs = new ArrayList<Activity>();
				activityCandidatesRhs.addAll(((Event) rhs).getOutgoingActivities());
				activityCandidatesRhs.addAll(((Event) rhs).getIncomingActivities());
				Activity activityLhs =
						activityCandidatesLhs.stream().filter(e -> !e.getName().contains("->")).findAny().get();
				Activity activityRhs =
						activityCandidatesRhs.stream().filter(e -> !e.getName().contains("->")).findAny().get();
				int result = activityLhs.getName().compareTo(activityRhs.getName());
				if (result == 0) {
					if (((Event) lhs).getOutgoingActivities().contains(activityRhs)
							&& ((Event) rhs).getIncomingActivities().contains(activityRhs)) {
						return -1;
					} else if (((Event) lhs).getIncomingActivities().contains(activityRhs)
							&& ((Event) rhs).getOutgoingActivities().contains(activityRhs)) {
						return 1;
					} else {
						return 0;
					}
				}
				return result;
			} else {
				int result = ((Activity) lhs).getName().compareTo(((Activity) rhs).getName());
				if (result == 0) {
					result = ((Integer) ((Activity) lhs).getDuration())
							.compareTo(((Activity) rhs).getDuration());
				}
				return result;
			}
		}	
	}
	
	private CPMNormalizer() {
	}
}
