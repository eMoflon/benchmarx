package org.benchmarx.examples.familiestopersons.scalability;

import org.benchmarx.examples.familiestopersons.categories.PerformanceTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(PerformanceTest.class)
@SuiteClasses({
	ScalabilityBatchTestsFwd.class,
	ScalabilityBatchTestsBwd.class,
	ScalabilityIncrTestsFwd.class,
	ScalabilityIncrTestsBwd.class,
	ScalabilityConstModelCSync.class,
	ScalabilityConstModelCFCSync.class,
	ScalabilityConstDeltaCSync.class,
	ScalabilityConstDeltaCFCSync.class
})
public class FamiliesToPersonsScalabilityTestSuite {

}
