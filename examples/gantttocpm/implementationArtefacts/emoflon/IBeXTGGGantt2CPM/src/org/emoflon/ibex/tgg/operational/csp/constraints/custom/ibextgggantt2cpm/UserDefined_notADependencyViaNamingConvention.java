package org.emoflon.ibex.tgg.operational.csp.constraints.custom.ibextgggantt2cpm;

import org.emoflon.ibex.tgg.operational.csp.RuntimeTGGAttributeConstraint;
import org.emoflon.ibex.tgg.operational.csp.RuntimeTGGAttributeConstraintVariable;

public class UserDefined_notADependencyViaNamingConvention extends RuntimeTGGAttributeConstraint {

	/**
	 * Constraint notADependencyViaNamingConvention(v0)
	 * 
	 * @see TGGLanguage.csp.impl.ConstraintImpl#solve()
	 */
	@Override
	public void solve() {
		if (variables.size() != 1)
			throw new RuntimeException("The CSP -NOTADEPENDENCYVIANAMINGCONVENTION- needs exactly 1 variables");

		RuntimeTGGAttributeConstraintVariable v0 = variables.get(0);
		String bindingStates = getBindingStates(v0);

		switch (bindingStates) {
		case "B":
			String name = (String) v0.getValue();
			setSatisfied(!name.contains("->"));
			break;
		default:
			throw new UnsupportedOperationException(
					"This case in the constraint has not been implemented yet: " + bindingStates);
		}
	}
}
