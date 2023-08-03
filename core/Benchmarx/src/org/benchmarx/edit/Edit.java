package org.benchmarx.edit;

import java.util.ArrayList;
import java.util.List;

public class Edit<M> implements IEdit<M>{
	private List<AtomicEdit<M>> steps = new ArrayList<>();
	
	public void addStep(AtomicEdit<M> step) {
		steps.add(step);
	}
	
	public List<AtomicEdit<M>> getSteps(){
		return steps;
	}

	@Override
	public IEdit<M> andThen(IEdit<M> edit) {
		var composition = new Edit<M>();
		composition.steps.addAll(this.steps);
		composition.steps.addAll(edit.getSteps());
		return composition;
	}
}
