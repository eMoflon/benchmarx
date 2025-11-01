package org.benchmarx.examples.familiestopersons.scalability.runner;

public class BenchEntry {

	public final double resolve;

	public BenchEntry(double resolve) {
		this.resolve = resolve;
	}

	public BenchEntry(String args) throws Exception {
		String[] splitted = args.trim().split(";");
		// remove all irrelevant lines before first parameter:
		String[] firstParam = splitted[0].split("\n");
		splitted[0] = firstParam[firstParam.length - 1];

		this.resolve = Double.parseDouble(splitted[splitted.length - 1]);
	}

	@Override
	public String toString() {
		return String.valueOf(resolve);
	}

}