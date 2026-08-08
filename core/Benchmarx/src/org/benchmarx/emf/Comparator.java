package org.benchmarx.emf;

/**
 * A class that is able to compare models of type M
 * 
 * @author anthony anjorin
 *
 * @param <M>
 *            Type of models that can be compared.
 */
public interface Comparator<M> {

	/**
	 * Compare two models and throw an exception if they are not to be
	 * considered identical.
	 * 
	 * @param expected
	 *            The expected model
	 * @param actual
	 *            The actual model
	 */
	void assertEquals(M expected, M actual);
}
