package org.benchmarx.petrinet.core;

import pn.Net;

public class PNHelper {
	public void renameToLettersAndDigits(Net net) {
		PNBuilder builder = new PNBuilder(net);
		builder.netName("LettersAndDigits");
	}	
	public void renameToFactoryModel(Net net) {
		PNBuilder builder = new PNBuilder(net);
		builder.netName("FactoryModel");
	}
	
	public void changeIncrementalID(Net net) {
		if ("changed".equals(net.getIncrementalID())) {
			net.setIncrementalID("changed again");
		} else {
			net.setIncrementalID("changed");
		}
	}
	
	public void createSimpleLettersDigits(Net net) {
		PNBuilder builder = new PNBuilder(net);
		builder
			.netName("LettersAndDigits")
		
			.place("A", 0)
			.place("B", 1)
			.place("C", 1)
			.place("D", 2)
			.place("E", 3)
			.place("F", 5)
			.place("G", 8)
			
			.transition("1", "B", null)
			.transition("2", null, "C")
			.transition("3", "D", "E")
			.transition("4", "F", "G");
	}	
	public void createComplexLettersDigits(Net net) {
		PNBuilder builder = new PNBuilder(net);
		builder
			.netName("LettersAndDigits")
		
			.place("A", 9)
			.place("B", 16)
			.place("C", 25)
			.place("D", 36)
			
			.transition("1", "B", null)
			.transition("2", null, "C").addTarget("D")
			.transition("3", "A", "D").addSource("B")
			.transition("4", "B", "B").addSource("D").addTarget("D");
	}
	
	public void createPTPLettersDigits(Net net) {
		PNBuilder builder = new PNBuilder(net);
		builder
			.netName("LettersAndDigits")
		
			.place("A", 1)
			.place("B", 0)
			
			.transition("1", "A", "B");
	}	
	public void extendPTPLettersDigits(Net net) {
		PNBuilder builder = new PNBuilder(net);
		builder
			.place("C", 0)
			
			.transition("1", "C", null)
			.transition("2", "A", "B");
	}	
	public void reducePTPExtendedLettersDigits(Net net) {
		PNBuilder builder = new PNBuilder(net);
		builder
			.deletePlace("C")

			.deleteTransition("2");
	}
	
	public void create1234LettersDigits(Net net) {
		PNBuilder builder = new PNBuilder(net);
		builder
			.netName("LettersAndDigits")
		
			.place("A", 1)
			.place("B", 2)
			.place("C", 3)
			.place("D", 4)
			
			.transition("1", "A", "B")
			.transition("2", "C", "D").addSource("B");
	}	
	public void construct5678LettersDigits(Net net) {
		PNBuilder builder = new PNBuilder(net);
		builder
			.changeTokens("A", 5)
			.changeTokens("B", 6)
			.changeTokens("C", 7)
			.changeTokens("D", 8)
			
			.transition("1", "C", null)
			.transition("2", "D", null).removeSource("B").removeTarget("D")
		
			.renamePlace("A", "tmp")
			.renamePlace("D", "A")
			.renamePlace("tmp", "D")
			.renamePlace("B", "E")
			
			.renameTransition("1", "tmp")
			.renameTransition("2", "1")
			.renameTransition("tmp", "2");
	}
	
	public void idleDelta(Net net) {	
	}
}
