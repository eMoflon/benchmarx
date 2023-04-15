package org.benchmarx.edit;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

public class ChangeAttribute<M> implements AtomicEdit<M> {
	private EObject node;
	private EAttribute attribute;
	private Object oldValue;
	private Object newValue;

	public ChangeAttribute(EAttribute attribute, EObject node, Object oldValue, Object newValue) {
		this.node = node;
		this.attribute = attribute;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public EObject getNode() {
		return node;
	}

	public EAttribute getAttribute() {
		return attribute;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public Object getNewValue() {
		return newValue;
	}
}
