package org.benchmarx.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class DeleteEdge<M> extends EdgeEdit<M> {
	public DeleteEdge(EReference type, EObject source, EObject target) {
		super(type, source, target);
	}
}
