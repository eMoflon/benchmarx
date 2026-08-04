package org.emoflon.ibex.tgg.operational.csp.constraints.custom.familiestopersonsibextgg;

import java.util.HashMap;
import java.util.HashSet;			

import org.emoflon.ibex.tgg.runtime.csp.constraints.factories.RuntimeTGGAttrConstraintFactory;

import org.emoflon.ibex.tgg.operational.csp.constraints.custom.familiestopersonsibextgg.customattributeconditionlibrary.BindFamilyName;

public class CustomAttributeConditionLibraryRuntimeTGGAttrConstraintFactory extends RuntimeTGGAttrConstraintFactory {

	public CustomAttributeConditionLibraryRuntimeTGGAttrConstraintFactory() {
		super();
	}
	
	@Override
	protected void initialize() {
		creators = new HashMap<>();
		creators.put("bindFamilyName", () -> new BindFamilyName());

		constraints = new HashSet<String>();
		constraints.addAll(creators.keySet());
	}
	
	@Override
	public String getLibraryName() {
		return "CustomAttributeConditionLibrary";
	}
}
