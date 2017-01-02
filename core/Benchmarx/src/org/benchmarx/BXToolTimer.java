package org.benchmarx;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BXToolTimer<S, T, D> {
	
	private BXTool<S,T,D> tool;
	private final int REPEAT;
	
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
		
		//System.out.print(measurements);
		return measurements.get(REPEAT/2);
	}

	private long timeAction(Runnable action) {
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
	
	public long timeSourceEditFromScratchInMS(Consumer<S> edit){
		return median(() -> timeFromScratch(() -> tool.performAndPropagateSourceEdit(edit)));
	}
	
	public double timeSourceEditFromScratchInS(Consumer<S> edit){
		return timeSourceEditFromScratchInMS(edit)/1000.0;
	}
	
	public long timeTargetEditFromScratchInMS(Consumer<T> edit){
		return median(() -> timeFromScratch(() -> tool.performAndPropagateTargetEdit(edit)));
	}
	
	public double timeTargetEditFromScratchInS(Consumer<T> edit){
		return timeTargetEditFromScratchInMS(edit)/1000.0;
	}
	
	public long timeSourceEditAfterSetUpInMS(Consumer<S> setup, Consumer<S> edit){
		return timeAfterSetup(() -> tool.performAndPropagateSourceEdit(setup), 
							  () -> tool.performAndPropagateSourceEdit(edit));
	}
	
	public long timeTargetEditAfterSetUpInMS(Consumer<T> setup, Consumer<T> edit){
		return timeAfterSetup(() -> tool.performAndPropagateTargetEdit(setup), 
							  () -> tool.performAndPropagateTargetEdit(edit));
	}
	
	private long timeAfterSetup(Runnable setup, Runnable action){
		tool.initiateSynchronisationDialogue();
		setup.run();
		return timeAction(action);
	}

	public String getName() {
		return tool.getName();
	}
}
