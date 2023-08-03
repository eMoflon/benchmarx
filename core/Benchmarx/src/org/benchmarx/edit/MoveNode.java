package org.benchmarx.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class MoveNode<M> extends NodeEdit<M> {
	private DeleteEdge<M> deleteEdge;
	private CreateEdge<M> createEdge;

	public MoveNode(EObject node, EObject oldParent, EReference oldConnection, EObject newParent, EReference newConnection) {
		super(node);
		deleteEdge = new DeleteEdge<>(oldConnection, oldParent, node);
		createEdge = new CreateEdge<>(newConnection, newParent, node);
	}

	public DeleteEdge<M> getDeleteEdge() {
		return deleteEdge;
	}

	public CreateEdge<M> getCreateEdge() {
		return createEdge;
	}
}
