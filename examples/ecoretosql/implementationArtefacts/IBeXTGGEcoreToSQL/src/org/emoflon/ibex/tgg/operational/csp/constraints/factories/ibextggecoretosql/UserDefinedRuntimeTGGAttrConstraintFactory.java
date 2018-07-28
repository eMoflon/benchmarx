package org.emoflon.ibex.tgg.operational.csp.constraints.factories.ibextggecoretosql;

import java.util.HashMap;
import java.util.HashSet;			

import org.emoflon.ibex.tgg.operational.csp.constraints.factories.RuntimeTGGAttrConstraintFactory;			

import org.emoflon.ibex.tgg.operational.csp.constraints.custom.ibextggecoretosql.UserDefined_setDefaultEnums;

public class UserDefinedRuntimeTGGAttrConstraintFactory extends RuntimeTGGAttrConstraintFactory {

	public UserDefinedRuntimeTGGAttrConstraintFactory() {
		super();
	}
	
	@Override
	protected void initialize() {
		creators = new HashMap<>();
		creators.put("setDefaultEnums", () -> new UserDefined_setDefaultEnums());

		constraints = new HashSet<String>();
		constraints.addAll(creators.keySet());
	}
}
