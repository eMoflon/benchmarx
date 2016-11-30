package org.benchmarx;

public abstract class BXToolForEMF<S, T, D> implements BXTool<S, T, D> {
	
	private Comparator<S> src;
	private Comparator<T> trg;
	
	public BXToolForEMF(Comparator<S> src, Comparator<T> trg){
		this.src = src;
		this.trg = trg;
	}
	
	public abstract S getSourceModel();
	public abstract T getTargetModel();
	
	private void assertModels(S source, T target) {
		src.compare(source, getSourceModel());
		trg.compare(target, getTargetModel());		
	}
	
	@Override
	public void assertPostcondition(S source, T target){
		assertModels(source, target);
	}

	@Override
	public void assertPrecondition(S source, T target){
		assertModels(source, target);
	}
}
