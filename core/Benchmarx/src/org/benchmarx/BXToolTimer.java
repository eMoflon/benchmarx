package org.benchmarx;

import java.util.function.Consumer;

public class BXToolTimer<S, T, D> {
	
	private BXTool<S,T,D> tool;
	
	public BXToolTimer(BXTool<S, T, D> tool){
		this.tool = tool;
	}
	
	private long timeFromScratch(Runnable action){
		tool.initiateSynchronisationDialogue();
		return timeAction(action);
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
		return timeFromScratch(() -> tool.performAndPropagateSourceEdit(edit));
	}
	
	public long timeTargetEditFromScratchInMS(Consumer<T> edit){
		return timeFromScratch(() -> tool.performAndPropagateTargetEdit(edit));
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
}
