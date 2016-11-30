package org.benchmarx;

import java.util.function.Consumer;

/**
 * This interface describes the expected functionality of a "BXTool" from the
 * perspective of the benchmarx.
 * 
 * @author aanjorin
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

	public void initiateSynchronisationDialogue();

	public void performAndPropagateTargetEdit(Consumer<T> edit);

	public void performAndPropagateSourceEdit(Consumer<S> edit);

	public void setConfigurator(Configurator<D> configurator);

	public void assertPostcondition(S source, T target);
	
	public void assertPrecondition(S source, T target);
}
