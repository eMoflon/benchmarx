package org.benchmarx.ecore.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

public class EcoreOperationNormaliser implements Comparator<EOperation> {
	@Override
	public int compare(EOperation arg0, EOperation arg1) {
		//Compare Names first
		int nameComp = arg0.getName().compareTo(arg1.getName());
		if(nameComp != 0)
			return nameComp;
		
		//If names are equal sort by length of parameters list
		int paramCountComp = arg0.getEParameters().size() - arg1.getEParameters().size();
		if(paramCountComp != 0)
			return paramCountComp;
		
		//Else compare Parameter Type names
		List<EParameter> l0 = arg0.getEParameters();
		List<EParameter> l1 = arg1.getEParameters();
		for(int i = 0; i < l0.size(); i++) {
			int paramTypeComp = l0.get(i).getEType().getName().compareTo(l1.get(i).getEType().getName());
			if(paramTypeComp != 0) return paramTypeComp;
		}
		
		//Else the operations are equal
		return 0;
	}
	
	public static void normalize(List<EOperation> classes){
		Comparator<EOperation> comparator = new EcoreOperationNormaliser();
		Collections.sort(classes, comparator);
	}
}
