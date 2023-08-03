package org.benchmarx.edit;

import org.eclipse.emf.ecore.EObject;

public abstract class NodeEdit<M> implements AtomicEdit<M> {
	private EObject node;

	public NodeEdit(EObject node) {
		this.node = node;
	}

	public EObject getNode() {
		return node;
	}
}
