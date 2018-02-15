package org.benchmarx.bags2.core;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;

import bags2.Element;
import bags2.MyBag;

public class Bag2Helper {

	private Bag2MyBagBuilder builder;
	
	public void createOneBeer(MyBag bag) {
		builder = new Bag2MyBagBuilder(bag);
		builder.addElement().setMultiplicity(1).setValue("Beer");
	}
	
	public void createFourBeer(MyBag bag) {
		builder = new Bag2MyBagBuilder(bag);
		builder.addElement().setMultiplicity(4).setValue("Beer");
	}
	
	public void createFiveBeer(MyBag bag) {
		builder = new Bag2MyBagBuilder(bag);
		builder.addElement().setMultiplicity(5).setValue("Beer");
	}
	
	public void createEmptyBottle(MyBag bag) {
		builder = new Bag2MyBagBuilder(bag);
		builder.addElement().setMultiplicity(1).setValue("Empty Bottle");
	}
	
	public void createBeerGlass(MyBag bag) {
		builder = new Bag2MyBagBuilder(bag);
		builder.addElement().setMultiplicity(1).setValue("Beer Glass");
	}
	
	public void deleteBeerGlass (MyBag bag) {
		EcoreUtil.delete(getElement(bag, "Beer Glass"), true);
	}
	
	public void deleteAllBeers (MyBag bag) {
		EcoreUtil.delete(getElement(bag, "Beer"), true);
	}
	
	public void changeEmptyBottleToBrokenBottle(MyBag bag) {
		getElement(bag, "Empty Bottle").setValue("Broken Bottle");
	}
	
	public void changeMultiplicityOfBeer(MyBag bag) {
		getElement(bag, "Beer").setMultiplicity(2);
	}
	
	public void changeBeerToEmptyBottle(MyBag bag) {
		getElement(bag, "Beer").setValue("Empty Bottle");
	}
	
	public void changeMultiplicityOfBeerGlass(MyBag bag) {
		getElement(bag, "Beer Glass").setMultiplicity(2);
	}
	
	public void changeIncrementalID(MyBag bag) {
		getElement(bag, "Beer").setIncrementalID("incrTestValue");
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
