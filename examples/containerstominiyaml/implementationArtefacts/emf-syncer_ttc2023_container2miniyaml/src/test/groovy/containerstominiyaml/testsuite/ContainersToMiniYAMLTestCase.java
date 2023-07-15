package containerstominiyaml.testsuite;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import containers.Composition;
import containers.ContainersPackage;
import containerstominiyaml.comparators.BXTool;
import containerstominiyaml.comparators.MiniYAMLComparator;
import containerstominiyaml.helpers.CompositionsHelper;
import containerstominiyaml.helpers.MiniYAMLHelper;
import containerstominiyaml.yamtl.YAMTLContainersToMiniYAML_BXTool;
import miniyaml.MiniyamlPackage;

@RunWith(Parameterized.class)
public abstract class ContainersToMiniYAMLTestCase {

	protected BXTool<Composition, miniyaml.Map, Decisions> tool;

	protected BenchmarxUtil<Composition, miniyaml.Map, Decisions> util;
	protected CompositionsHelper compositionsHelper = new CompositionsHelper();
	protected MiniYAMLHelper miniYAMLHelper = new MiniYAMLHelper();

	@Before
	public void initialise() {
		// Make sure packages are registered
		ContainersPackage.eINSTANCE.getName();
		MiniyamlPackage.eINSTANCE.getName();

		// Initialise all helpers
		util = new BenchmarxUtil<>(tool);

		// Initialise the bx tool
		tool.initiateSynchronisationDialogue();
	}

	@After
	public void terminate() {
		tool.terminateSynchronisationDialogue();
	}

	// Solutions requiring additional setup are commented out.
	@Parameters(name = "{0}")
	public static Collection<BXTool<Composition, miniyaml.Map, Decisions>> tools() {
		return Arrays.asList(
				new YAMTLContainersToMiniYAML_BXTool("YAMTL (ignore order)", new MiniYAMLComparator())
//				new YAMTLContainersToMiniYAML_BXTool("YAMTL (exact order)", new MiniYAMLExactComparator())
		);
	}

	protected ContainersToMiniYAMLTestCase(BXTool<Composition, miniyaml.Map, Decisions> tool) {
		this.tool = tool;
	}
}
