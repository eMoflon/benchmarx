package org.benchmarx.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class CreateEdge<M> implements AtomicEdit<M> {
	private EReference type;
	private EObject source;
	private EObject target;

	public CreateEdge(EReference type, EObject source, EObject target) {
		this.type = type;
		this.source = source;
		this.target = target;
	}

	public EReference getType() {
		return type;
	}

	public EObject getSource() {
		return source;
	}

	public EObject getTarget() {
		return target;
	}
}
