package org.benchmarx.examples.familiestopersons.testrunner;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.notification.Failure;

public class SingleTestRunner {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: SingleTestRunner <fullClassName> [methodName]");
			System.exit(1);
		}
		var className = args[0];
		var targetMethod = (args.length > 1) ? args[1] : null;

		try {
			var clazz = Class.forName(className);
			Request request = Request.aClass(clazz);

			if (targetMethod != null && !targetMethod.isEmpty()) {
				request = request.filterWith(new Filter() {
					@Override
					public boolean shouldRun(Description description) {
						if (description.isTest()) {
							var name = description.getMethodName();
							return name != null && (name.equals(targetMethod) || name.startsWith(targetMethod + "[") || name.startsWith(targetMethod));
						}
						for (var child : description.getChildren()) {
							if (shouldRun(child)) {
								return true;
							}
						}
						return false;
					}

					@Override
					public String describe() {
						return "Filter for method: " + targetMethod;
					}
				});
			}

			var startTime = System.currentTimeMillis();
			var result = new JUnitCore().run(request);
			var duration = System.currentTimeMillis() - startTime;

			System.out.println("=== TEST_RESULT_START ===");
			System.out.println("Class: " + className);
			System.out.println("Method: " + (targetMethod != null ? targetMethod : "ALL"));
			System.out.println("DurationMs: " + duration);
			System.out.println("Successful: " + result.wasSuccessful());
			System.out.println("RunCount: " + result.getRunCount());
			System.out.println("FailureCount: " + result.getFailureCount());
			for (var failure : result.getFailures()) {
				System.out.println("FAILURE_HEADER: " + failure.getTestHeader());
				System.out.println("FAILURE_MESSAGE: " + failure.getMessage());
				System.out.println("FAILURE_TRACE:");
				failure.getException().printStackTrace(System.out);
			}
			System.out.println("=== TEST_RESULT_END ===");
			System.exit(result.wasSuccessful() ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(2);
		}
	}
}
