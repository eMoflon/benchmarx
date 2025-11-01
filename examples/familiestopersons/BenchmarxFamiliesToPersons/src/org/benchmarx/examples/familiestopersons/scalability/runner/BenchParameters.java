package org.benchmarx.examples.familiestopersons.scalability.runner;

public class BenchParameters {

	public final String name;
	public final int scaleFactor;

	public BenchParameters(String name, int scaleFactor) {
		this.name = name;
		this.scaleFactor = scaleFactor;
	}

	public String[] serializeInputParameters() {
		return new String[] {name, ""+scaleFactor};
	}

	public String[] getInputParameterNames() {
		return new String[] {"name", "resolve"};
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append("_" + scaleFactor);
		return builder.toString();
	}

}