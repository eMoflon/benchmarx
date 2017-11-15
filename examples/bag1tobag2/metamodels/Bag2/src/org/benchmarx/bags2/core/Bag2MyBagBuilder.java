package org.benchmarx.bags2.core;

import bags2.Bags2Factory;
import bags2.Element;
import bags2.MyBag;

public class Bag2MyBagBuilder {
	private Bags2Factory f = Bags2Factory.eINSTANCE;
	private MyBag bag;
	private Element last;
	
	Bag2MyBagBuilder(MyBag bag) {
		this.bag = bag;
	}
	
	public Bag2MyBagBuilder addElement() {
		last = f.createElement();
		last.setBag(bag);
		return this;
	}
	
	public Bag2MyBagBuilder setValue(String value) {
		last.setValue(value);
		return this;
	}
	
	public Bag2MyBagBuilder setMultiplicity(int value) {
		last.setMultiplicity(value);
		return this;
	}
}
