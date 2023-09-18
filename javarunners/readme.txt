The folder BXRunner contains an eclipse project. 
It contains the PerformanceTestSuite Interface for the ScalabilityMeasurements classes.
It also contains the runner classes in java. They are exported as JAR-files and used by
Python BXrunner to find and run testcases and performance measurements. 
Export them again if they are changed. The export location for the jar has to be: 
..\PythonToBenchmarx\Test Runner Project\bx_runner\model\model_util\JavaUtil.jar

Export in eclipse as runnable JAR file, with main method of BXRunner class as launch configuration.
Use option "Extract required libraries into generated JAR". If the export fails: Try again clicking
on finish, often it works on the second try.
