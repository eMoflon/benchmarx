//package org.benchmarx;
package containerstominiyaml.comparators;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements a simple update policy that makes decisions based on stored
 * values.
 *
 * @param <D>
 *            The objects for which decisions can be made.
 * 
 * @author anthony anjorin
 */
public class Configurator<D> {

	private Map<D, Boolean> decisions = new HashMap<>();

	/**
	 * Invoked to register b for decision d as part of a setup.
	 * 
	 * @param d
	 *            The decision to be registered.
	 * @param b
	 *            The value to be returned if this decision is requested.
	 * @return
	 */
	public Configurator<D> makeDecision(D d, boolean b) {
		decisions.put(d, b);
		return this;
	}
	
	/**
	 * Invoked by a {@link BXTool} during propagation to retrieve a yes or no
	 * for the passed decision.
	 * 
	 * @param decision
	 *            The decision for which a yes or no is requested.
	 * @return Returns a yes or no for the decision based on previously
	 *         registered values in {@link #makeDecision(Object, boolean)}.
	 * @throws IllegalArgumentException
	 *             A runtime exception if there is no registered value for the
	 *             requested decision. This can either indicate a faulty update
	 *             policy or an expected decision request from the
	 *             {@link BXTool} under test.
	 */
	public boolean decide(D decision) {
		if (decisions.containsKey(decision))
			return decisions.get(decision);
		else
			throw new IllegalArgumentException("I don't know how to handle: " + decision);
	}
}
