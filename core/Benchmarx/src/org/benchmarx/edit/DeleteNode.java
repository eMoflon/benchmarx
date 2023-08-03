package org.benchmarx.edit;

import org.eclipse.emf.ecore.EObject;

public class DeleteNode<M> extends NodeEdit<M> {

	public DeleteNode(EObject node) {
		super(node);
	}

}
