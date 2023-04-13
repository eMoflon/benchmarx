package org.benchmarx.edit;

import java.util.List;

public interface IEdit<M> {
	List<AtomicEdit<M>> getSteps();

	static <M> IEdit<M> idleEdit() {
		return new IdleEdit<M>();
	}

	IEdit<M> andThen(IEdit<M> edit);
}
