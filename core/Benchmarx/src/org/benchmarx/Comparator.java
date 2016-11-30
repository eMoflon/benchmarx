package org.benchmarx;

public interface Comparator<M> {

	void compare(M expected, M actual);
}
