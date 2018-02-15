package org.benchmarx.ecore.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;

public class EcoreAttributeNormaliser implements Comparator<EAttribute> {
	@Override
	public int compare(EAttribute arg0, EAttribute arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}
	
	public static void normalize(List<EAttribute> classes){
		Comparator<EAttribute> comparator = new EcoreAttributeNormaliser();
		Collections.sort(classes, comparator);
	}
}
