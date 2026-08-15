package org.benchmarx.examples.familiestopersons.testsuite;

import org.benchmarx.examples.familiestopersons.categories.CapabilityTest;
import org.benchmarx.examples.familiestopersons.testsuite.alignment_based.bwd.IncrementalBackward;
import org.benchmarx.examples.familiestopersons.testsuite.alignment_based.fwd.IncrementalForward;
import org.benchmarx.examples.familiestopersons.testsuite.alignment_based.roundtrip.RoundtripTests;
import org.benchmarx.examples.familiestopersons.testsuite.batch.bwd.BatchBwdEAndP;
import org.benchmarx.examples.familiestopersons.testsuite.batch.bwd.BatchBwdENotP;
import org.benchmarx.examples.familiestopersons.testsuite.batch.bwd.BatchBwdNotEAndP;
import org.benchmarx.examples.familiestopersons.testsuite.batch.bwd.BatchBwdNotENotP;
import org.benchmarx.examples.familiestopersons.testsuite.batch.fwd.BatchForward;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.Conflicts;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.MonotonicCreating;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.MonotonicDeleting;
import org.benchmarx.examples.familiestopersons.testsuite.concurrent.NonMonotonic;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(CapabilityTest.class)
@SuiteClasses({
	BatchForward.class,
	BatchBwdEAndP.class,
	BatchBwdENotP.class,
	BatchBwdNotEAndP.class,
	BatchBwdNotENotP.class,
	IncrementalForward.class,
	IncrementalBackward.class,
	RoundtripTests.class,
	Conflicts.class,
	MonotonicCreating.class,
	MonotonicDeleting.class,
	NonMonotonic.class
})
public class FamiliesToPersonsCapabilityTestSuite {

}
