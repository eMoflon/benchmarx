package org.benchmarx.examples.containerstominiyaml.testsuite;

import java.util.Arrays;
import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.comparators.MiniYAMLComparator;
import org.benchmarx.examples.containerstominiyaml.comparators.MiniYAMLExactComparator;
import org.benchmarx.examples.containerstominiyaml.comparators.CompositionComparator;
import org.benchmarx.examples.containerstominiyaml.helpers.CompositionsHelper;
import org.benchmarx.examples.containerstominiyaml.helpers.MiniYAMLHelper;
import org.benchmarx.examples.containerstominiyaml.implementations.epsilon.EpsilonContainersToMiniYAML;
import org.benchmarx.examples.containerstominiyaml.implementations.nmf.NMFContainersToMiniYaml;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import containers.Composition;
import containers.ContainersFactory;
import containers.ContainersPackage;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import de.ubt.ai1.m2m.bxtenddsl.connectors.BXToolForBXtendDsl;
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
				//new EpsilonContainersToMiniYAML("Epsilon (ignore order)", new MiniYAMLComparator()), // known to fail IncrementalForward::renameContainer
				//new EpsilonContainersToMiniYAML("Epsilon (exact order)", new MiniYAMLExactComparator()),
				//new NMFContainersToMiniYaml("NMF (ignore order)", new MiniYAMLComparator()),
				//new NMFContainersToMiniYaml("NMF (exact order)", new MiniYAMLExactComparator()),
				new BXToolForBXtendDsl<containers.Composition, miniyaml.Map, Decisions>(
						() -> new Containers2MiniYAML(), ContainersFactory.eINSTANCE.createComposition(), 
						Decisions.values(), new CompositionComparator(), new MiniYAMLComparator() ),
				new BXToolForBXtendDsl<containers.Composition, miniyaml.Map, Decisions>(
						() -> new Containers2MiniYAML(), ContainersFactory.eINSTANCE.createComposition(), 
						Decisions.values(), new CompositionComparator(), new MiniYAMLExactComparator() )
		);
	}

	protected ContainersToMiniYAMLTestCase(BXTool<Composition, miniyaml.Map, Decisions> tool) {
		this.tool = tool;
	}
}
