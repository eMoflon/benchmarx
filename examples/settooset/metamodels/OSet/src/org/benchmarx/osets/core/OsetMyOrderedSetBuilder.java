package org.benchmarx.osets.core;

import osets.Element;
import osets.MyOrderedSet;
import osets.OsetsFactory;

public class OsetMyOrderedSetBuilder {
	private MyOrderedSet set;
	private Element last; // last element inserted in the oset, the position in the oset is arbitrary
	private OsetsFactory f = OsetsFactory.eINSTANCE;
	
	OsetMyOrderedSetBuilder(MyOrderedSet set) {
		this.set = set;
	}
	
	OsetMyOrderedSetBuilder mySetName(String name) {
		set.setName(name);
		return this;
	}
	
	OsetMyOrderedSetBuilder next() {
		Element temp = f.createElement();
		if(!set.getElements().isEmpty()) {
			if(last == null)
				last = set.getElements().get(set.getElements().size()-1);
			last.setNext(temp);
		}
		last = temp;
		last.setOrderedSet(set);
		return this;
	}
	
	OsetMyOrderedSetBuilder setElementValue(String val) {
		last.setValue(val);
		return this;
	}
	
	OsetMyOrderedSetBuilder insertNewElementBefore(String val) {
		Element find = null;
		for(Element e : set.getElements()) {
			if(e.getValue().equals(val)) {
				find = e;
				break;
			}
		}
		if(find != null) {
			last = f.createElement();
			last.setPrevious(find.getPrevious());
			last.setNext(find);
			last.setOrderedSet(set);
		}
		return this;
	}
	
	OsetMyOrderedSetBuilder insertNewElementAfter(String val) {
		Element find = null;
		for(Element e : set.getElements()) {
			if(e.getValue().equals(val)) {
				find = e;
				break;
			}
		}
		if(find != null) {
			last = f.createElement();
			last.setNext(find.getNext());
			last.setPrevious(find);
			last.setOrderedSet(set);
		}
		return this;
	}
}
