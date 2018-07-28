package org.emoflon.ibex.tgg.operational.csp.constraints.custom.ibextggecoretosql;

import java.util.ArrayList;
import java.util.Arrays;

import org.emoflon.ibex.tgg.operational.csp.RuntimeTGGAttributeConstraint;
import org.emoflon.ibex.tgg.operational.csp.RuntimeTGGAttributeConstraintVariable;

import sql.Property;

public class UserDefined_setDefaultEnums extends RuntimeTGGAttributeConstraint {

	/**
	 * Constraint setDefaultEnums(v0)
	 * 
	 * @see TGGLanguage.csp.impl.ConstraintImpl#solve()
	 */
	@Override
	public void solve() {
		if (variables.size() != 1)
			throw new RuntimeException("The CSP -SETDEFAULTENUMS- needs exactly 1 variables");

		RuntimeTGGAttributeConstraintVariable v0 = variables.get(0);
		String bindingStates = getBindingStates(v0);

		switch (bindingStates) {

		case "B":
			setSatisfied(true);
			return;
		case "F":
			v0.bindToValue(Arrays.asList(Property.NOT_NULL, Property.AUTO_INCREMENT));
			setSatisfied(true);
			return;
		default:
			throw new UnsupportedOperationException(
					"This case in the constraint has not been implemented yet: " + bindingStates);
		}
	}
}
