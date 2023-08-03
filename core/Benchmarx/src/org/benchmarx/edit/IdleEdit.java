package org.benchmarx.edit;

import java.util.Collections;
import java.util.List;

public class IdleEdit<M> implements IEdit<M> {

	@Override
	public List<AtomicEdit<M>> getSteps() {
		return Collections.emptyList();
	}

	@Override
	public IEdit<M> andThen(IEdit<M> edit) {
		return edit;
	}

}
