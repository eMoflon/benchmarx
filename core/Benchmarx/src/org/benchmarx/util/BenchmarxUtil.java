package org.benchmarx.util;

import static org.benchmarx.util.EMFUtil.loadExpectedModel;

import java.util.function.Consumer;

import org.benchmarx.BXTool;
import org.benchmarx.Configurator;

/**
 * A collection of helper methods used by the test framework. See {@link BXTool}
 * for a documentation of all type parameters.
 * 
 * @author anthony anjorin
 *
 * @param <S>
 * @param <T>
 * @param <D>
 */
public class BenchmarxUtil<S,T, D> {

	private BXTool<S, T, D> tool;
	
	public BenchmarxUtil(BXTool<S,T,D> tool){
		this.tool = tool;
	}
	
	/**
	 * Assert precondition by loading source and target models. These models are
	 * expected to be located at "./resources/<suppliedPath>.xmi"
	 * 
	 * @param srcPath
	 *            Supplied path to source model
	 * @param trgPath
	 *            Supplied path to target model
	 */
	public void assertPrecondition(String srcPath, String trgPath){
		tool.assertPrecondition(loadExpectedModel(srcPath), loadExpectedModel(trgPath));
	}
	
	/**
	 * See {@link #assertPrecondition(String, String)}
	 */
	public void assertPostcondition(String srcPath, String trgPath){
		tool.assertPostcondition(loadExpectedModel(srcPath), loadExpectedModel(trgPath));
	}
	
	/**
	 * Create and set a new empty {@link Configurator} for the {@link BXTool}
	 * 
	 * @return The newly created {@link Configurator}
	 */
	public Configurator<D> configure() {
		Configurator<D> c = new Configurator<>();
		tool.setConfigurator(c);
		return c;
	}

	/**
	 * Use to form chain of consumers.
	 * 
	 * @param a
	 * @return
	 */
	public <X> Consumer<X> execute(Consumer<X> a){
		return a;
	}
}
