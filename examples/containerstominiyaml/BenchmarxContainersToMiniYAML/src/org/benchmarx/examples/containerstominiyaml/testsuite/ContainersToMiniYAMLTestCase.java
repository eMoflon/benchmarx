package org.benchmarx.examples.containerstominiyaml.testsuite;

import java.util.Arrays;
import java.util.Collection;

import org.benchmarx.BXTool;
import org.benchmarx.emf.Comparator;
import org.benchmarx.examples.containerstominiyaml.comparators.CompositionComparator;
import org.benchmarx.examples.containerstominiyaml.comparators.MiniYAMLComparator;
import org.benchmarx.examples.containerstominiyaml.implementations.epsilon.EpsilonContainersToMiniYAML;
import org.benchmarx.util.BenchmarxUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import containers.Composition;
import containers.ContainersPackage;
import miniyaml.MiniyamlPackage;

@RunWith(Parameterized.class)
public abstract class ContainersToMiniYAMLTestCase {

	protected BXTool<Composition, miniyaml.Map, Decisions> tool;
	protected Comparator<Composition> compositionComparator;
	protected Comparator<miniyaml.Map> miniyamlComparator;

	protected BenchmarxUtil<Composition, miniyaml.Map, Decisions> util;
	protected CompositionsHelper compositionsHelper = new CompositionsHelper();

	@Before
	public void initialise() {
		// Make sure packages are registered
		ContainersPackage.eINSTANCE.getName();
		MiniyamlPackage.eINSTANCE.getName();

		// Initialise all helpers
		compositionComparator = new CompositionComparator();
		miniyamlComparator = new MiniYAMLComparator();
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
				new EpsilonContainersToMiniYAML()
		);
	}

	protected ContainersToMiniYAMLTestCase(BXTool<Composition, miniyaml.Map, Decisions> tool) {
		this.tool = tool;
	}
}
