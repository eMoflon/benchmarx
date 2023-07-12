//package org.benchmarx.emf;
package containerstominiyaml.comparators;

import org.junit.Assert;

import junit.framework.AssertionFailedError;

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
	 * @throws AssertionFailedError
	 *             Expected to throw assertion failed errors via {@link Assert}
	 *             if the models are not identical does not hold.
	 */
	void assertEquals(M expected, M actual);
}
