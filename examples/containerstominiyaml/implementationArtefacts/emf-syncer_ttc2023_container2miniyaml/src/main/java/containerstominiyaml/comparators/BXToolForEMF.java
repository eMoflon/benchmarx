//package org.benchmarx.emf;
package containerstominiyaml.comparators;

/**
 * A super class for EMF-based BX tools. Provides support for asserting pre- and
 * postconditions based on the internal models of the {@link BXTool} (which are assumed
 * to be EMF-based) and therefore directly comparable to the expected models.
 * 
 * See {@link BXTool} for a documentation of all type parameters.
 * 
 * @author anthony anjorin
 *
 * @param <S>
 * @param <T>
 * @param <D>
 */
public abstract class BXToolForEMF<S, T, D> implements BXTool<S, T, D> {
	
	private Comparator<S> src;
	private Comparator<T> trg;
	
	/**
	 * Requires a {@link Comparator} for each metamodel, which is used for
	 * asserting pre- and postconditions.
	 * 
	 * @param src
	 *            A {@link Comparator} for source models
	 * @param trg
	 *            A {@link Comparator} for target models
	 */
	public BXToolForEMF(Comparator<S> src, Comparator<T> trg){
		this.src = src;
		this.trg = trg;
	}
	
	/**
	 * Return the internally stored source model of the {@link BXTool}
	 * 
	 * @return An EMF-based source model
	 */
	public abstract S getSourceModel();
	
	/**
	 * See {@link #getSourceModel()}
	 */
	public abstract T getTargetModel();
	
	/**
	 * Prompt the tool to save the current state of all its models for debugging
	 * purposes. The tool decides what to save and where to save it.
	 * 
	 * @param name
	 *            A label that can be used to form the names of the models (for
	 *            easy identification).
	 */
	public abstract void saveModels(String name);
	
	private void assertModels(S source, T target) {
		src.assertEquals(source, getSourceModel());
		trg.assertEquals(target, getTargetModel());		
	}
	
	@Override
	public void assertPostcondition(S source, T target){
		assertModels(source, target);
	}

	@Override
	public void assertPrecondition(S source, T target){
		assertModels(source, target);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
