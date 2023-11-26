package containerstominiyaml.testsuite;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import containerstominiyaml.comparators.BXTool;


/**
 * Helper class used for running runtime measurements for a {@link BXTool}. See
 * {@link BXTool} for a documentation of all type parameters.
 * 
 * @author anthony anjorin
 *
 * @param <S>
 * @param <T>
 * @param <D>
 */
public class BXToolTimer<S, T, D> {
	
	protected final BXTool<S,T,D> tool;
	protected final int REPEAT;
	
	/**
	 * @param tool
	 *            {@link BXTool} to be timed.
	 * @param repeat
	 *            How often to repeat each timed propagation.
	 */
	public BXToolTimer(BXTool<S, T, D> tool, int repeat){
		this.tool = tool;
		this.REPEAT = repeat;
	}
	
	private long timeFromScratch(Runnable action){
		tool.initiateSynchronisationDialogue();
		return timeAction(action);
	}

	private long median(Supplier<Long> measurement) {		
		List<Long> measurements = 
				Stream.generate(measurement)
					 .limit(REPEAT)
					 .sorted()
					 .collect(Collectors.toList());
		
		return measurements.get(REPEAT/2);
	}

	protected long timeAction(Runnable action) {
		long tic = System.currentTimeMillis();
		try {
			action.run();
			tool.assertPostcondition(null, null);
		} catch (Exception e) {
			// We don't care what goes wrong
		}
		long toc = System.currentTimeMillis();
		
		return toc - tic;
	}
	
	private long timeAfterSetup(Runnable setup, Runnable action){
		tool.initiateSynchronisationDialogue();
		setup.run();
		return timeAction(action);
	}
	
	/**
	 * Perform runtime measurements from scratch (i.e., a batch transformation).
	 * 
	 * @param edit
	 *            The source edit to be propagated and timed.
	 * @return The median of propagating edit REPEAT times
	 */
	public long timeSourceEditFromScratchInMS(Consumer<S> edit){
		return median(() -> timeFromScratch(() -> tool.performAndPropagateSourceEdit(edit)));
	}
	
	/**
	 * See {@link #timeSourceEditFromScratchInMS(Consumer)}
	 */
	public double timeSourceEditFromScratchInS(Consumer<S> edit){
		return timeSourceEditFromScratchInMS(edit)/1000.0;
	}
	
	/**
	 * See {@link #timeSourceEditFromScratchInMS(Consumer)}
	 */
	public long timeTargetEditFromScratchInMS(Consumer<T> edit){
		return median(() -> timeFromScratch(() -> tool.performAndPropagateTargetEdit(edit)));
	}
	
	/**
	 * See {@link #timeSourceEditFromScratchInMS(Consumer)}
	 */
	public double timeTargetEditFromScratchInS(Consumer<T> edit){
		return timeTargetEditFromScratchInMS(edit)/1000.0;
	}
	
	/**
	 * Perform runtime measurements after an initial setup (i.e., for
	 * incremental transformations)
	 * 
	 * @param setup
	 *            The initial source edit that is propagated to establish the
	 *            starting point for the measurement. This is not measured.
	 * @param edit
	 *            The source edit to be propagated and timed.
	 * @return The median of propagating edit REPEAT times (each time executed
	 *         after a fresh setup).
	 */
	public long timeSourceEditAfterSetUpInMS(Consumer<S> setup, Consumer<S> edit){
		return median(() -> timeAfterSetup(() -> tool.performAndPropagateSourceEdit(setup), 
							  			   () -> tool.performAndPropagateSourceEdit(edit)));
	}

	/**
	 * See {@link #timeSourceEditAfterSetUpInMS(Consumer, Consumer)}
	 */
	public long timeTargetEditAfterSetUpInMS(Consumer<T> setup, Consumer<T> edit){
		return median(() -> timeAfterSetup(() -> tool.performAndPropagateTargetEdit(setup), 
							  			   () -> tool.performAndPropagateTargetEdit(edit)));
	}
	
	/**
	 * See {@link #timeSourceEditAfterSetUpInMS(Consumer, Consumer)}
	 */
	public double timeTargetEditAfterSetUpInS(Consumer<T> setup, Consumer<T> edit){
		return timeTargetEditAfterSetUpInMS(setup, edit)/1000.0;
	}
	
	/**
	 * See {@link #timeSourceEditAfterSetUpInMS(Consumer, Consumer)}
	 */
	public double timeSourceEditAfterSetUpInS(Consumer<S> setup, Consumer<S> edit){
		return timeSourceEditAfterSetUpInMS(setup, edit)/1000.0;
	}

	public String getName() {
		return tool.getName();
	}
}
