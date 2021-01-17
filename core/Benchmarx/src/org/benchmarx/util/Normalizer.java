package org.benchmarx.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

public abstract class Normalizer<T> implements Comparator<T> {

	public List<T> normalize(EList<T> items) {
		final List<T> list = new LinkedList<>(items);
		Collections.sort(list, this);
		return list;
	}
	
}
