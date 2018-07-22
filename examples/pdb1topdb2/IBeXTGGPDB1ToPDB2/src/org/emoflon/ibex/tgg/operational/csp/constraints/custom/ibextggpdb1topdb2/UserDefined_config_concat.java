package org.emoflon.ibex.tgg.operational.csp.constraints.custom.ibextggpdb1topdb2;

import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.emoflon.ibex.tgg.operational.csp.RuntimeTGGAttributeConstraintVariable;
import org.emoflon.ibex.tgg.operational.csp.constraints.Concat;

public class UserDefined_config_concat extends Concat {
	private static Supplier<Boolean> PREFER_USING_FIRST_SPACE_TO_LAST; 

	/**
	 * Constraint config_concat(v0, v1, v2, v3)
	 * 
	 * @see TGGLanguage.csp.impl.ConstraintImpl#solve()
	 */
	@Override
	public void solve() {
		if (variables.size() != 4)
			throw new RuntimeException("The CSP -CONFIG_CONCAT- needs exactly four variables");

		RuntimeTGGAttributeConstraintVariable separator = variables.get(0);
		RuntimeTGGAttributeConstraintVariable a = variables.get(1);
		RuntimeTGGAttributeConstraintVariable b = variables.get(2);
		RuntimeTGGAttributeConstraintVariable c = variables.get(3);

		String bindingStates = getBindingStates(separator, a, b, c);

		switch (bindingStates) {

		case "BBFB": {
			String[] split = ("" + c.getValue()).split(Pattern.quote("" + separator.getValue()));
			if (split.length == 2) {
				b.bindToValue(split[1]);
			} else {
				if (PREFER_USING_FIRST_SPACE_TO_LAST.get()) {
					ArrayList<String> lastSegments = new ArrayList<>();
					for (int i = 1; i < split.length; i++)
						lastSegments.add(split[i]);
					
					b.bindToValue(lastSegments.stream().collect(Collectors.joining(" ")));
				} else {
					b.bindToValue(split[split.length - 1]);
				}
			}
			
			setSatisfied(checkAllValues(separator, a, b, c));
			return;
		}

		case "BFFB": {
			String[] split = c.getValue().toString().split(Pattern.quote("" + separator.getValue()));
			if (split.length == 2) {
				a.bindToValue(split[0]);
				b.bindToValue(split[1]);
			} else {
				if (PREFER_USING_FIRST_SPACE_TO_LAST.get()) {
					a.bindToValue(split[0]);

					ArrayList<String> lastSegments = new ArrayList<>();
					for (int i = 1; i < split.length; i++)
						lastSegments.add(split[i]);
					
					b.bindToValue(lastSegments.stream().collect(Collectors.joining(" ")));
				} else {
					b.bindToValue(split[split.length - 1]);

					ArrayList<String> firstSegments = new ArrayList<>();
					for (int i = 0; i < split.length-1; i++)
						firstSegments.add(split[i]);
					
					a.bindToValue(firstSegments.stream().collect(Collectors.joining(" ")));
				}
			}

			setSatisfied(checkAllValues(separator, a, b, c));
			return;
		}

		default:
			super.solve();
		}
	}

	public static void setConfigurator(Supplier<Boolean> configurator) {
		PREFER_USING_FIRST_SPACE_TO_LAST = configurator;
	}
}
