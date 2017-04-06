# benchmarx
Infrastructure for implementing benchmarx: benchmarks for bidirectional transformation (bx) tools.   Also contains a collection of example benchmarx and test runners for various and diverse bx tools.


## How to setup and execute the benchmarx

1.  Clone this repo:  `git clone https://github.com/eMoflon/benchmarx.git benchmarx` 
2.  Download the latest version of the **Eclipse Modeling Tools**  for your platform.  Currently this is: http://www.eclipse.org/downloads/packages/eclipse-modeling-tools/neon1a
3.  Install the latest version of the Xtend (https://eclipse.org/xtend/) Eclipse plugin.  Currently this is: http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases/
4.  Install the latest version of the eMoflon (www.emoflon.org) Eclipse plugin.  Currently this is:  https://emoflon.github.io/eclipse-plugin/beta/updatesite/
5.  Start Eclipse in a workspace of your choice and import all Eclipse projects from the working tree of the benchmarx git repository you just cloned.  You should have 6 projects in your workspace with no compilation errors.  Note that some Xtend code is used in the projects so ensure that the encoding for your Eclipse workspace is UTF-8.   
6.  Choose the tools you want to execute by appropriately manipulating `/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/FamiliesToPersonsTestCase.java/tools()`.
7.  (Optional) If you want to setup the bx tool BiGUL, then work through `/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/implementations/bigul/README-SETUP` to do this.
8.  Choose the project **BenchmarxFamiliesToPersons** and select "Run As/JUnit Test" to execute the benchmarx "Families to Persons" for all tools chosen in Step 5.
9.  You can compare your results with `/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/resultsAsOf_30_11_2016.xlsx`.

## How to extend the benchmarx by adding a new implementation (and/or new test cases!)

To add an implementation of an existing benchmarx example, e.g., `FamiliesToPersons` with your favourite bx tool, do the following (taking `FamiliesToPersons` as an example, substitute as appropriate for another example):

1.  Add a folder for your tool to `/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/implementations/<your_bx_tool>`.
2.  Add a Java test runner that is responsible for invoking and interfacing with your bx tool.  If your bx tool is EMF-based, take a look at `emoflon/EMoflonFamiliesToPersons.java` and `medini/MediniQVTFamiliesToPersons.java`.  If your bx tool does _not_ use EMF, then take a look at `bigul/BiGULFamiliesToPersons.java`.
3.  If your implementation needs extra artefacts (such as Eclipse projects, etc) then add these to `/examples/familiestopersons/implementationsArtefacts`.
4.  Extend `/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/FamiliesToPersonsTestCase.java` to include your new test runner.
5.  Feel free to add new JUnit tests that demonstrate the strengths of your tool (and reveal weaknesses of others).  Please try to classify your new tests using the existing structure in the benchmarx.
6.  If your bx tool requires additional setup steps in addition to compiling a standard Eclipse project, add a README-SETUP file to this effect in your bx tool's implementation folder (cf. `bigul/README-SETUP`). 
7.  Please remember to create and send us a pull request from your fork so we can update the benchmarx!


## How to extend the benchmarx by adding a new example

1.  Copy the existing structure for `/examples/familiestopersons`.  This consists of a folder `metamodels` for the source and target metamodels in an EMF representation, a folder `BenchmarxFamiliesToPersons` for the actual testsuite, and a folder `implementationArtefacts` for any further implementation artefacts.
2.  The folder `BenchmarxFamiliesToPersons` is an Eclipse project that can be run as a JUnit test suite and contains all test cases as JUnit tests, all resources (input and expected models), any implementations of the benchmarx example, and auxiliary classes for both metamodels used to simplify the comparison of output and expected models (or model representations).
3.  Please remember to create and send us a pull request from your fork so we can update the benchmarx!

