package org.benchmarx.ecore.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

public class EcoreClassNormaliser implements Comparator<EClass>{

	@Override
	public int compare(EClass arg0, EClass arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}
	
	public static void normalize(List<EClass> classes){
		Comparator<EClass> comparator = new EcoreClassNormaliser();
		Collections.sort(classes, comparator);
	}

}
