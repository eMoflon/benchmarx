package org.benchmarx.examples.pdb12pdb2.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.pdb12pdb2.testsuite.Decisions;
import org.benchmarx.examples.pdb12pdb2.testsuite.Pdb12Pdb2TestCase;
import org.junit.Test;

public class BatchForward extends Pdb12Pdb2TestCase {

	public BatchForward(BXTool<pdb1.Database, pdb2.Database, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for agreed upon starting state.<br/>
	 * <b>Expect</b> root elements of both source and target models.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testInitialiseSynchronisation()
	{
		// No precondition!
		//------------
		util.assertPostcondition("RootElementPdb1", "RootElementPdb2");
	}
	
	/**
	 * <b>Test</b> for name change of an empty Database.<br/>
	 * <b>Expect</b> the name in the target Database is also changed.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testDatabaseNameChangeOfEmpty()
	{
		tool.performAndPropagateSourceEdit(util.execute(helperPerson1::setDatabaseName));

		util.assertPrecondition("EmptyBundeskanzlerPdb1", "EmptyBundeskanzlerPdb2");
		//------------
		tool.performAndPropagateSourceEdit(util.execute(helperPerson1::renameKanzlerDatabaseToPräsidenten));
		//------------
		util.assertPostcondition("EmptyBundespräsidentenPdb1", "EmptyBundespräsidentenPdb2");
	}
	
	/**
	 * <b>Test</b> for creation of a single Person (Adenauer) in an empty Database.
	 * <br/>
	 * <b>Expect</b> Adenauer to be created in the target model.
	 * <br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test
	public void testCreatePerson()
	{
		// No precondition!
		//------------
		tool.performAndPropagateSourceEdit(helperPerson1::createKonradAdenauer);
		//------------
		util.assertPostcondition("AdenauerPdb1", "AdenauerPdb2");
	}

	/**
	 * Analogous to @link {@link #testCreatePerson()}, but now for
	 * multiple Persons (first three chancellors).<br/>
	 * <b>Features:</b>: fwd, fixed
	 */
	@Test 
	public void testCreateMultiplePersons(){
		tool.performAndPropagateSourceEdit(util.execute(helperPerson1::setDatabaseName));

		util.assertPrecondition("EmptyBundeskanzlerPdb1", "EmptyBundeskanzlerPdb2");
		//------------
		tool.performAndPropagateSourceEdit(util
				.execute(helperPerson1::createKonradAdenauer)
				.andThen(helperPerson1::createLudwigErhard)
				.andThen(helperPerson1::createKurtKiesinger));
		//------------
		util.assertPostcondition("PDB1FirstThreeChancellors", "Pre_IncrBwdPDB2FirstThreeChancellors");
	}
}
