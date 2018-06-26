package org.emoflon.ibex.tgg.operational.csp.constraints.factories.ibextgggantt2cpm;

import java.util.HashMap;
import java.util.HashSet;			

import org.emoflon.ibex.tgg.operational.csp.constraints.factories.RuntimeTGGAttrConstraintFactory;			

import org.emoflon.ibex.tgg.operational.csp.constraints.custom.ibextgggantt2cpm.UserDefined_notADependencyViaNamingConvention;
import org.emoflon.ibex.tgg.operational.csp.constraints.custom.ibextgggantt2cpm.UserDefined_setCounter;

public class UserDefinedRuntimeTGGAttrConstraintFactory extends RuntimeTGGAttrConstraintFactory {

	public UserDefinedRuntimeTGGAttrConstraintFactory() {
		super();
	}
	
	@Override
	protected void initialize() {
		creators = new HashMap<>();
		creators.put("notADependencyViaNamingConvention", () -> new UserDefined_notADependencyViaNamingConvention());
		creators.put("setCounter", () -> new UserDefined_setCounter());

		constraints = new HashSet<String>();
		constraints.addAll(creators.keySet());
	}
}
