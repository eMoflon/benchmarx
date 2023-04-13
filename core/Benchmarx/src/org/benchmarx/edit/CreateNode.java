package org.benchmarx.edit;

import org.eclipse.emf.ecore.EObject;

public class CreateNode<M> implements AtomicEdit<M> {
	private EObject node;

	public CreateNode(EObject node) {
		this.node = node;
	}
	
	public EObject getNode() {
		return node;
	}
}
