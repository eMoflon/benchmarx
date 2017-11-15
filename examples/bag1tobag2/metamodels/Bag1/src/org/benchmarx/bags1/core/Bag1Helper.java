package org.benchmarx.bags1.core;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;

import bags1.Element;
import bags1.MyBag;

public class Bag1Helper {

	private Bag1MyBagBuilder builder;
	
	public void createOneBeer(MyBag bag) {
		builder = new Bag1MyBagBuilder(bag);
		builder.addElement().setValue("Beer");
	}
	
	public void createFiveBeers(MyBag bag) {
		builder = new Bag1MyBagBuilder(bag);
		builder.addElement().setValue("Beer")
				.addElement().setValue("Beer")
				.addElement().setValue("Beer")
				.addElement().setValue("Beer")
				.addElement().setValue("Beer");
	}
	
	public void createBeerGlass(MyBag bag) {
		builder = new Bag1MyBagBuilder(bag);
		builder.addElement().setValue("Beer Glass");
	}
	
	public void deleteBeer(MyBag bag) {
		EcoreUtil.delete(getElement(bag, "Beer"), true);
	}
	
	public void deleteBeerGlass(MyBag bag) {
		EcoreUtil.delete(getElement(bag, "Beer Glass"), true);
	}
	
	public void changeOneBeerToEmptyBottle(MyBag bag) {
		getElement(bag,"Beer").setValue("Empty Bottle");
	}
	
	public void changeAllBeerToEmptyBottles(MyBag bag) {
		getElement(bag,"Beer").setValue("Empty Bottle");
		getElement(bag,"Beer").setValue("Empty Bottle");
		getElement(bag,"Beer").setValue("Empty Bottle");
		getElement(bag,"Beer").setValue("Empty Bottle");
		getElement(bag,"Beer").setValue("Empty Bottle");
	}
	
	public void changeIncrementalID(MyBag bag) {
		getElement(bag, "Empty Bottle").setIncrementalID("incrIDTestValue");
	}
	
	public void idleDelta(MyBag bag) {
	}
	
	private Element getElement(MyBag bag, String value) {
		Optional<Element> elementOpt = bag.getElements().stream().filter(e -> ((Element) e).getValue().equals(value)).findAny();
		
		assertTrue(elementOpt.isPresent());
		Element element = elementOpt.get();
		assertTrue(element.getValue().equals(value));
		return element;		
	}
}
