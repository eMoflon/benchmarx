package org.benchmarx.osets.core;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.benchmarx.osets.core.OsetComparator;
import osets.Element;
import osets.MyOrderedSet;

public class OsetHelper {
	public static abstract class Delta {
		public static class OsetElementsInversion extends Delta {
		}
	}
	
	public List<Delta> getDelta() {
		return delta;
	}
	public void clearDelta() {
		delta.clear();
	}
	
	public void setSetName(MyOrderedSet set) {
		set.setName("Alphabet");
	}
	
	public void renameAlphabetSetToABC(MyOrderedSet set) {
		assertTrue(set.getName().equals("Alphabet"));
		set.setName("ABC");
	}
	
	public void changeIncrementalID(MyOrderedSet set) {
		if ("changed".equals(set.getIncrementalID())) {
			set.setIncrementalID("changed again");
		} else {
			set.setIncrementalID("changed");
		}
	}
	
	public void createA(MyOrderedSet set) {
		builder = new OsetMyOrderedSetBuilder(set);
		builder.next().setElementValue("A");
	}
	
	public void deleteA(MyOrderedSet set) {
		delete(set, "A");
	}
	
	public void createB(MyOrderedSet set) {
		builder = new OsetMyOrderedSetBuilder(set);
		builder.next().setElementValue("B");
	}
	
	public void deleteB(MyOrderedSet set) {
		delete(set, "B");
	}
	
	public void createC(MyOrderedSet set) {
		builder = new OsetMyOrderedSetBuilder(set);
		builder.next().setElementValue("C");
	}
	
	public void deleteC(MyOrderedSet set) {
		delete(set, "C");
	}
	
	public void createD(MyOrderedSet set) {
		builder = new OsetMyOrderedSetBuilder(set);
		builder.next().setElementValue("D");
	}
	
	public void deleteD(MyOrderedSet set) {
		delete(set, "D");
	}
	
	public void insertABeforeC(MyOrderedSet set) {
		builder = new OsetMyOrderedSetBuilder(set);
		builder.insertNewElementBefore("C").setElementValue("A");
	}
	public void insertBBeforeC(MyOrderedSet set) {
		builder = new OsetMyOrderedSetBuilder(set);
		builder.insertNewElementBefore("C").setElementValue("B");
	}
	public void insertDAfterC(MyOrderedSet set) {
		builder = new OsetMyOrderedSetBuilder(set);
		builder.insertNewElementAfter("C").setElementValue("D");
	}
	
	public void changeABCtoZXY(MyOrderedSet set) {
		getElement(set, "A").setValue("Z");
		getElement(set, "B").setValue("X");
		getElement(set, "C").setValue("Y");
	}
	
	public void invert(MyOrderedSet set) {
		if (set.getElements().isEmpty()) {
			return;
		}
		
		Element first = findFirst(set);
		List<String> values = new ArrayList<>();
		while(first != null) {
			values.add(first.getValue());
			Element next = first.getNext();
			first.setNext(null);
			first = next;
		}
		Element last = null;
		for (String value : values) {
			Element current = getElement(set, value);
			current.setNext(last);
			last = current;
		}
		delta.add(new OsetHelper.Delta.OsetElementsInversion());
	}
	
	public void idleDelta(MyOrderedSet set) {
	}
	
	private OsetMyOrderedSetBuilder builder = null;
	private List<Delta> delta = new ArrayList<>();
	
	private Element getElement(MyOrderedSet set, String value) {
		Optional<Element> elementOpt = set.getElements().stream().filter(e -> e.getValue().equals(value)).findAny();
		
		assertTrue(elementOpt.isPresent());
		Element element = elementOpt.get();
		assertTrue(element.getValue().equals(value));
		return element;		
	}
	
	private Element findFirst(MyOrderedSet set) {
		assertTrue(OsetComparator.isConnected(set) && !OsetComparator.isCycle(set));
		
		Element first = set.getElements().get(0);
		while (first.getPrevious() != null) {
			first = first.getPrevious();
		}
		return first;
	}
	
	private void delete(MyOrderedSet set, String value) {
		Element element = getElement(set, value);
		if (element.getPrevious() != null) {
			element.getPrevious().setNext(element.getNext());
		}
		EcoreUtil.delete(element);
	}
}
