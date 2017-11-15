package org.benchmarx.bags1.core;

import bags1.Bags1Factory;
import bags1.Element;
import bags1.MyBag;

public class Bag1MyBagBuilder {
	private Bags1Factory f = Bags1Factory.eINSTANCE;
	private MyBag bag;
	private Element last;
	
	Bag1MyBagBuilder(MyBag bag) {
		this.bag = bag;
	}
	
	public Bag1MyBagBuilder addElement() {
		last = f.createElement();
		last.setBag(bag);
		return this;
	}
	
	public Bag1MyBagBuilder setValue(String value) {
		last.setValue(value);
		return this;
	}
}
