# benchmarx
Infrastructure for implementing benchmarx: benchmarks for bidirectional transformation (bx) tools.   Also contains a collection of example benchmarx and test runners for various and diverse bx tools.


## How to setup and exeucte the benchmarx

1.  Clone this repo:  `git clone https://github.com/eMoflon/benchmarx.git benchmarx` 
2.  Download the latest version of the **Eclipse Modeling Tools**  for your platform.  Currently this is: http://www.eclipse.org/downloads/packages/eclipse-modeling-tools/neon1a
3.  Install the latest version of the eMoflon (www.emoflon.org) Eclipse plugin.  Currently this is:  http://emoflon.github.io/eclipse-plugin/beta/update-site2/
4.  Start Eclipse in a workspace of your choice and import all Eclipse projects from the working tree of the benchmarx git repository you just cloned.  You should have 5 projects in your workspace with no compilation errors.   
5.  Choose the tools you want to execute by appropriately manipulating `/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/FamiliesToPersonsTestCase.java/tools()`
6.  (Optional) If you want to setup the bx tool BiGUL, then work through `/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/implementations/bigul/README-SETUP` to do this.
7.  Choose the project **BenchmarxFamiliesToPersons** and select "Run As/JUnit Test" to execute the benchmarx "Families to Persons" for all tools chosen in Step 5.
