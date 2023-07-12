package containerstominiyaml.testsuite.alignment_based.fwd;

import containers.Composition;
import containerstominiyaml.comparators.BXTool;
import containerstominiyaml.testsuite.Decisions;
import miniyaml.Map;

public class IncrementalForwardExactYAMLOrder extends IncrementalForward {

	public IncrementalForwardExactYAMLOrder(BXTool<Composition, Map, Decisions> tool) {
		super(tool);
	}
	
}
