package org.emoflon.ibex.tgg.operational.csp.constraints.factories.ibextggpdb1topdb2;

import java.util.HashMap;
import java.util.HashSet;			

import org.emoflon.ibex.tgg.operational.csp.constraints.factories.RuntimeTGGAttrConstraintFactory;			

import org.emoflon.ibex.tgg.operational.csp.constraints.custom.ibextggpdb1topdb2.UserDefined_config_concat;

public class UserDefinedRuntimeTGGAttrConstraintFactory extends RuntimeTGGAttrConstraintFactory {

	public UserDefinedRuntimeTGGAttrConstraintFactory() {
		super();
	}
	
	@Override
	protected void initialize() {
		creators = new HashMap<>();
		creators.put("config_concat", () -> new UserDefined_config_concat());

		constraints = new HashSet<String>();
		constraints.addAll(creators.keySet());
	}
}
