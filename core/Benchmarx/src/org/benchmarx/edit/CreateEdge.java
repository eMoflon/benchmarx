package org.benchmarx.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class CreateEdge<M> extends EdgeEdit<M> {

	public CreateEdge(EReference type, EObject source, EObject target) {
		super(type, source, target);
	}

}
