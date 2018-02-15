package org.benchmarx.sql.core;

public abstract class SQLBuilder {

	private SQLBuilder builder;
	
	public SQLBuilder(SQLBuilder b) {
		this.builder = b;
	}

	public <B extends SQLBuilder> B end(Class<B> type) {
		return type.cast(builder);
	}
}
