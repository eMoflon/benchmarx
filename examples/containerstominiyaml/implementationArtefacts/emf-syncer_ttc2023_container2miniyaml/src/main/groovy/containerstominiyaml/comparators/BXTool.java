//package org.benchmarx;
package containerstominiyaml.comparators;

import java.util.function.Consumer;

import org.junit.Assert;

import junit.framework.AssertionFailedError;

/**
 * This interface describes the expected functionality of a "BXTool" from the
 * perspective of the benchmarx.
 * 
 * @author anthony anjorin
 *
 * @param <S>
 *            The root type of all source models
 * @param <T>
 *            The root type of all target models
 * @param <D>
 *            Represents runtime decisions that can be requested by the tool at
 *            runtime.
 **/
public interface BXTool<S, T, D> {

	/**
	 * This method should be invoked once before any propagation to give the BX
	 * tool the chance to establish an agreed upon starting point for any
	 * further synchronisation. This can be used to establish and connect all
	 * empty model containers, create any additional models the tool might
	 * require such as correspondence models, bookkeeping information, or
	 * protocols, etc.
	 */
	public void initiateSynchronisationDialogue();

	/**
	 * This method should be invoked once at the end of every test
	 * (synchronisation dialogue) to give the BX tool the chance to shut down as
	 * required. No update propagation or assertions should be performed after
	 * this method is invoked.
	 */
	default public void terminateSynchronisationDialogue() {}
	
	/**
	 * Request the bx tool to perform the source edit on the soure model and to
	 * propagate it to the target model.
	 * 
	 * @param edit
	 *            The edit to be performed is encoded as a void function. The bx
	 *            tool can attach listeners to the model to record changes as it
	 *            is manipulated, supply a special consumer that can be
	 *            inspected, or only access the resulting model if this is all
	 *            that is required.
	 */
	public void performAndPropagateSourceEdit(Consumer<S> edit);
	
	/**
	 * See {@link #performAndPropagateTargetEdit(Consumer)}
	 * 
	 * @param edit
	 */	
	public void performAndPropagateTargetEdit(Consumer<T> edit);
	
	/**
	 * Some testing tasks require performing idle updates on one model, i.e.,
	 * updates that are not relevant for the other model, but enable testing
	 * certain properties. The bx tool is thus free to simply perform this idle
	 * update on the source model and do nothing else, or of course propagate it
	 * if this is important for internal bookkeeping. This explicit extra method
	 * for idle updates is important to avoid testing, e.g., the backward
	 * propagation when testing the forward propagation. testing.
	 * 
	 * @param edit
	 *            See {@link #performAndPropagateSourceEdit(Consumer)}
	 */
	public void performIdleSourceEdit(Consumer<S> edit);

	/**
	 * See {@link #performIdleSourceEdit(Consumer)}
	 * 
	 * @param edit
	 */
	public void performIdleTargetEdit(Consumer<T> edit);

	/**
	 * Used to set the update policy to use for testing. This takes over
	 * decisions to control otherwise non-deterministic results.
	 * 
	 * @param configurator See {@link Configurator}
	 */
	public void setConfigurator(Configurator<D> configurator);	

	/**
	 * Request the bx tool to ensure that the state of its source and target
	 * models are identical to the provided pair of models. This method is to be
	 * invoked after a synchronisation dialogue to test the expected
	 * postcondition.
	 * 
	 * @param source
	 *            The expected source model
	 * @param target
	 *            The expected target model
	 * @throws AssertionFailedError
	 *             Expected to throw an assertion failed error via {@link Assert} if the
	 *             postcondition does not hold.
	 */
	public void assertPostcondition(S source, T target);

	/**
	 * Request the bx tool to ensure that the sate of its source and target
	 * models are identical to the provided pair of models. This method is to be
	 * invoked after the initial setup and before the actual series of
	 * propagation steps to be tested. Clarifies if a test fails because the
	 * precondition cannot be fulfilled or because the actual propagation to be
	 * tested is not working as expected.
	 * 
	 * @param source
	 *            The expected source model
	 * @param target
	 *            The expected target model
	 * @throws AssertionFailedError
	 *             Expected to throw assertion failed errors via {@link Assert} if the
	 *             precondition does not hold.
	 */
	public void assertPrecondition(S source, T target);

	/**
	 * Implement to set the name of your tool and improve generated debug messages.
	 * 
	 * @return
	 */
	default public String getName() { return "Please set the name of your bx tool!"; }
}
