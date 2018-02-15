package org.benchmarx.petrinetweighted.core;

import pnw.Net;

public class PNWHelper {
	public void renameToLettersAndDigits(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder.netName("LettersAndDigits");
	}
	public void renameToFactoryModel(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
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
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.netName("LettersAndDigits")
		
			.place("A", 0)
			.place("B", 1)
			.place("C", 1)
			.place("D", 2)
			.place("E", 3)
			.place("F", 5)
			.place("G", 8)
			
			.transition("1", "B", null, 13, 0)
			.transition("2", null, "C", 0, 21)
			.transition("3", "D",  "E", 34, 55)
			.transition("4", "F", "G", 89, 144);
	}	
	public void createComplexLettersDigits(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.netName("LettersAndDigits")
		
			.place("A", 9)
			.place("B", 16)
			.place("C", 25)
			.place("D", 36)
			
			.transition("1", "B", null, 1, 0)
			.transition("2", null, "C", 0, 1).addTarget("D", 1)
			.transition("3", "A", "D", 2, 4).addSource("B", 3)
			.transition("4", "B", "B", 5, 6).addSource("D", 7).addTarget("D", 8);
	}
	
	public void createPTPLettersDigits(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.netName("LettersAndDigits")
		
			.place("A", 1)
			.place("B", 0)
			
			.transition("1", "A", "B", 1, 1);
	}
	public void extendPTPLettersDigits(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.place("C", 0)
			
			.transition("1", "C", null, 1, 1)
			.transition("2", "A", "B", 1, 1);
	}
	public void furtherExtendPTPLettersDigits(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.transition("1", "B", null, 1, 1)
			.transition("2", null, "C", 1, 1);
	}
	public void reducePTPExtendedLettersDigits(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.deletePlace("C")

			.deleteTransition("2");
	}
	public void reducePTPFurtherExtendedLettersDigits(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.transition("1", null, null, -1, -1).removeSource("B")
			.transition("2", null, null, -1, -1).removeTarget("C");
	}
	
	public void create1234LettersDigits(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.netName("LettersAndDigits")
		
			.place("A", 1)
			.place("B", 2)
			.place("C", 3)
			.place("D", 4)
			
			.transition("1", "A", "B", 1, 1)
			.transition("2", "C", "D", 1, 1).addSource("B", 1);
	}	
	public void construct9012LettersDigits(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.changeTokens("A", 9)
			.changeTokens("B", 0)
			.changeTokens("C", 1)
			.changeTokens("D", 2)
			
			.reconnectPTEdge("B", "2", "D", "1")
			.reconnectTPEdge("2", "D", "2", "A")
		
			.renamePlace("A", "tmp")
			.renamePlace("D", "A")
			.renamePlace("tmp", "D")
			.renamePlace("B", "E")
			
			.renameTransition("1", "tmp")
			.renameTransition("2", "1")
			.renameTransition("tmp", "2");
	}
	
	public void weightA1BWith42(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.weightPTEdge("A", "1", 4)
			.weightTPEdge("1", "B", 2);
	}
	public void weightA1BWith73(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.weightPTEdge("A", "1", 7)
			.weightTPEdge("1", "B", 3);
	}
	public void weightA3DWith24(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.weightPTEdge("A", "3", 2)
			.weightTPEdge("3", "D", 4);
	}
	public void weightB2With9(Net net) {
		PNWBuilder builder = new PNWBuilder(net);
		builder
			.weightPTEdge("B", "2", 9);
	}
	
	public void idleDelta(Net net) {	
	}
}
