package org.benchmarx.util;

import org.benchmarx.util.PerformanceTestSuite.PerformanceResult;

/**
 * Class that holds the result of a performance test run
 * @author tb
 *
 */
public class PerformanceResultImpl implements PerformanceResult {
	
	private int size;
	private double time;
	
	public PerformanceResultImpl(int size, double time) {
		this.size = size;
		this.time = time;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public double getTime() {
		return time;
	}

}
