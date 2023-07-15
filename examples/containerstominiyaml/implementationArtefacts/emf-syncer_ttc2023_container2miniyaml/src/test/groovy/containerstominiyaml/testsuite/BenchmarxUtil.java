package containerstominiyaml.testsuite;

import static containerstominiyaml.testsuite.EMFUtil.loadExpectedModel;

import java.util.function.Consumer;

import org.eclipse.emf.ecore.resource.ResourceSet;

import containerstominiyaml.comparators.BXTool;
import containerstominiyaml.comparators.Configurator;

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
	
	
	/** Consider using {@link #assertPrecondition(ResourceSet, String, String) instead. */
	public void assertPrecondition(String srcPath, String trgPath){
		tool.assertPrecondition(loadExpectedModel(srcPath), loadExpectedModel(trgPath));
	}
	
	/** Consider using {@link #assertPostcondition(ResourceSet, String, String) instead. */
	public void assertPostcondition(String srcPath, String trgPath){
		tool.assertPostcondition(loadExpectedModel(srcPath), loadExpectedModel(trgPath));
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
	public void assertPrecondition(ResourceSet rs, String srcPath, String trgPath){
		tool.assertPrecondition(loadExpectedModel(srcPath, rs), loadExpectedModel(trgPath, rs));
	}
	

	public void assertPostcondition(ResourceSet rs, String srcPath, String trgPath){
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
	public <X> Consumer<X> execute(Consumer<X> a){
		return a;
	}
}
