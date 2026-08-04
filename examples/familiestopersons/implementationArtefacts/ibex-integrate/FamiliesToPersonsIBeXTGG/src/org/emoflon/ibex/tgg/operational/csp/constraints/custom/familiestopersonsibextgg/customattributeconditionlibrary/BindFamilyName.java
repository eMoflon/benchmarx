package org.emoflon.ibex.tgg.operational.csp.constraints.custom.familiestopersonsibextgg.customattributeconditionlibrary;

import org.emoflon.ibex.tgg.runtime.csp.RuntimeTGGAttributeConstraint;
import org.emoflon.ibex.tgg.runtime.csp.RuntimeTGGAttributeConstraintVariable;

public class BindFamilyName extends RuntimeTGGAttributeConstraint
{

   /**
    * Constraint bindFamilyName(v0, v1, v2)
    * 
    * @see TGGLanguage.csp.impl.ConstraintImpl#solve()
    */
	@Override
	public void solve() {
		if (variables.size() != 3)
			throw new RuntimeException("The CSP -BINDFAMILYNAME- needs exactly 3 variables");

		RuntimeTGGAttributeConstraintVariable separator = variables.get(0);
		RuntimeTGGAttributeConstraintVariable familyName = variables.get(1);
		RuntimeTGGAttributeConstraintVariable fullName = variables.get(2);
		String bindingStates = getBindingStates(separator, familyName, fullName);

	  	switch(bindingStates) {
	  	case "BBB":{
	  		setSatisfied(fullName.getValue().toString().startsWith(familyName.getValue().toString() + separator.getValue().toString()));
	  		return;
	  	}
	  		default:  throw new UnsupportedOperationException("This case in the constraint has not been implemented yet: " + bindingStates);
	  		 	}
	  	}
}

