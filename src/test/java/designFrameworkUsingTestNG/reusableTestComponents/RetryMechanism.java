package designFrameworkUsingTestNG.reusableTestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryMechanism implements IRetryAnalyzer{

	@Override
	public boolean retry(ITestResult result) { // only executed if test Fails
		int count = 3;
		if(count > 0) {
			count--;
			return true;   // try again
		}
		return false; // dont try already maximum try attepts executed
	}

}


// we need to explicitely tell IreTry mechnism which tests to retry. Hence add only flaky tests