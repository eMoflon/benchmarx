package org.benchmarx.util;

import static org.benchmarx.util.EMFUtil.loadExpectedModel;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.benchmarx.BXTool;
import org.benchmarx.config.Configurator;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.ComparisonFailure;

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
public class BenchmarxUtil<S, T, D> {

	private BXTool<S, T, D> tool;

	public BenchmarxUtil(BXTool<S, T, D> tool) {
		this.tool = tool;
	}

	/**
	 * Consider using {@link #assertPrecondition(ResourceSet, String, String)
	 * instead.
	 */
	public void assertPrecondition(String srcPath, String trgPath) {
		tool.assertPrecondition(loadExpectedModel(srcPath), loadExpectedModel(trgPath));
	}

	/**
	 * Consider using {@link #assertPostcondition(ResourceSet, String, String)
	 * instead.
	 */
	public void assertPostcondition(String srcPath, String trgPath) {
		tool.assertPostcondition(loadExpectedModel(srcPath), loadExpectedModel(trgPath));
	}

	/**
	 * Asserts that at least one of the supplied postconditions holds.
	 * 
	 * @param alternatives Multiple entries of paths to source and target models.
	 *                     Each entry is a possible postcondition (consistent pair).
	 */
	public void assertAnyPostcondition(Map<String, String> alternativePostconditions) {
		var failures = new ArrayList<ComparisonFailure>();
		for (var entry : alternativePostconditions.entrySet()) {
			try {
				// Attempt to assert post condition
				assertPostcondition(entry.getKey(), entry.getValue());

				// Attempt was successful so exit
				return;
			} catch (ComparisonFailure failed) {
				// Attempt was unsuccessful so continue search
				failures.add(failed);
			}
		}

		// No attempt was successful so fail
		var finalError = new ComparisonFailure("None of the provided postconditions holds.",
				failures.stream().map(ComparisonFailure::getExpected).collect(Collectors.joining("========>\n")),
				failures.stream().map(ComparisonFailure::getActual).collect(Collectors.joining("========>\n")));

		throw finalError;
	}

	/**
	 * Assert precondition by loading source and target models. These models are
	 * expected to be located at "./resources/<suppliedPath>.xmi"
	 * 
	 * @param srcPath Supplied path to source model
	 * @param trgPath Supplied path to target model
	 */
	public void assertPrecondition(ResourceSet rs, String srcPath, String trgPath) {
		tool.assertPrecondition(loadExpectedModel(srcPath, rs), loadExpectedModel(trgPath, rs));
	}

	public void assertPostcondition(ResourceSet rs, String srcPath, String trgPath) {
		tool.assertPostcondition(loadExpectedModel(srcPath, rs), loadExpectedModel(trgPath, rs));
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
	public <X> Consumer<X> execute(Consumer<X> a) {
		return a;
	}
}
