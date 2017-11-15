package org.benchmarx.sets.core;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;

import sets.Element;
import sets.MySet;

public class SetHelper {
	public static abstract class Delta {
		public static class SetNameChange extends Delta {
			public final String newName;

			public SetNameChange(String newName) {
				this.newName = newName;
			}
		}
		
		public static class ElementCreation extends Delta {
			public final String value;

			public ElementCreation(String value) {
				this.value = value;
			}
		}
		
		public static class ElementDeletion extends Delta {
			public final String value;
			
			public ElementDeletion(String value) {
				this.value = value;
			}
		}
		
		public static class ElementChange extends Delta {
			public final String oldValue;
			public final String newValue;
			
			public ElementChange(String oldValue, String newValue) {
				this.oldValue = oldValue;
				this.newValue = newValue;
			}
		}
	}
	
	public List<Delta> getDelta() {
		return delta;
	}
	public void clearDelta() {
		delta.clear();
	}
	
	public void setSetName(MySet set) {
		set.setName("Alphabet");
		delta.add(new SetHelper.Delta.SetNameChange("Alphabet"));
	}
	
	public void renameAlphabetSetToABC(MySet set) {
		assertTrue(set.getName().equals("Alphabet"));
		set.setName("ABC");
		delta.add(new SetHelper.Delta.SetNameChange("ABC"));
	}
	
	public void changeIncrementalID(MySet set) {
		if ("changed".equals(set.getIncrementalID())) {
			set.setIncrementalID("changed again");
		} else {
			set.setIncrementalID("changed");
		}
	}
	
	public void createA(MySet set) {
		builder = new SetMySetBuilder(set);
		builder.addElement().setElementValue("A");
		delta.add(new SetHelper.Delta.ElementCreation("A"));
	}
	
	public void deleteA(MySet set) {
		EcoreUtil.delete(getElement(set, "A"));
		delta.add(new SetHelper.Delta.ElementDeletion("A"));
	}
	
	public void createB(MySet set) {
		builder = new SetMySetBuilder(set);
		builder.addElement().setElementValue("B");
		delta.add(new SetHelper.Delta.ElementCreation("B"));
	}
	
	public void deleteB(MySet set) {
		EcoreUtil.delete(getElement(set, "B"));
		delta.add(new SetHelper.Delta.ElementDeletion("B"));
	}
	
	public void createC(MySet set) {
		builder = new SetMySetBuilder(set);
		builder.addElement().setElementValue("C");
		delta.add(new SetHelper.Delta.ElementCreation("C"));
	}
	
	public void deleteC(MySet set) {
		EcoreUtil.delete(getElement(set, "C"));
		delta.add(new SetHelper.Delta.ElementDeletion("C"));
	}
	
	public void createD(MySet set) {
		builder = new SetMySetBuilder(set);
		builder.addElement().setElementValue("D");
		delta.add(new SetHelper.Delta.ElementCreation("D"));
	}
	
	public void deleteD(MySet set) {
		EcoreUtil.delete(getElement(set, "D"));
		delta.add(new SetHelper.Delta.ElementDeletion("D"));
	}
	
	public void changeABCtoZXY(MySet set) {
		getElement(set, "A").setValue("Z");
		getElement(set, "B").setValue("X");
		getElement(set, "C").setValue("Y");
		delta.add(new SetHelper.Delta.ElementChange("A", "Z"));
		delta.add(new SetHelper.Delta.ElementChange("B", "X"));
		delta.add(new SetHelper.Delta.ElementChange("C", "Y"));
	}
	
	public void changeZXYtoABC(MySet set) {
		getElement(set, "Z").setValue("A");
		getElement(set, "X").setValue("B");
		getElement(set, "Y").setValue("C");
		delta.add(new SetHelper.Delta.ElementChange("Z", "A"));
		delta.add(new SetHelper.Delta.ElementChange("X", "B"));
		delta.add(new SetHelper.Delta.ElementChange("Y", "C"));
	}
	
	public void idleDelta(MySet set) {
		
	}
	
	private SetMySetBuilder builder = null;
	private List<Delta> delta = new ArrayList<>();
	
	private Element getElement(MySet set, String value) {
		Optional<Element> elementOpt = set.getElements().stream().filter(e -> e.getValue().equals(value)).findAny();
		
		assertTrue(elementOpt.isPresent());
		Element element = elementOpt.get();
		assertTrue(element.getValue().equals(value));
		return element;		
	}
}
