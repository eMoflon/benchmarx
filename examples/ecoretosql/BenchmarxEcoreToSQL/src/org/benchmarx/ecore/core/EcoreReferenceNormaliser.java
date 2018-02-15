package org.benchmarx.ecore.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EReference;

public class EcoreReferenceNormaliser implements Comparator<EReference> {

	public static void normalize(List<EReference> references){
		Comparator<EReference> comparator = new EcoreReferenceNormaliser();
		Collections.sort(references, comparator);
	}

	@Override
	public int compare(EReference o1, EReference o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
