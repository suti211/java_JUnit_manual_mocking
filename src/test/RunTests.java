package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunTests {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(FilePartReaderTester.class);

		for (Failure fail : result.getFailures()) {
			System.out.println(fail.toString());
		}

		if (result.wasSuccessful()) {
			System.out.println("OK!");
		}
	}
}
