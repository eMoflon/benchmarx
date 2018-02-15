package org.benchmarx.sets.core;

import sets.Element;
import sets.MySet;
import sets.SetsFactory;

public class SetMySetBuilder {
	
	private MySet set;
	private Element last;
	private SetsFactory f = SetsFactory.eINSTANCE;
	
	SetMySetBuilder(MySet set) {
		this.set = set;
	}
	
	SetMySetBuilder mySetName(String name) {
		set.setName(name);
		return this;
	}
	
	SetMySetBuilder addElement() {
		last = f.createElement();
		last.setSet(set);
		return this;
	}
	
	SetMySetBuilder setElementValue(String val) {
		last.setValue(val);
		return this;
	}
}
