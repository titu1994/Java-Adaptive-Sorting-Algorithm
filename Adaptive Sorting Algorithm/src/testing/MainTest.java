package testing;

import managers.ExecutionManager;

public class MainTest {

	public static void main(String[] args) {
		ExecutionManager.processArrayOfType(100, 0, "100-Random-");
		ExecutionManager.processArrayOfType(100, 1, "100-AlmostSorted-1");
		ExecutionManager.processArrayOfType(100, 2, "100-AlmostSorted-2");
		ExecutionManager.processArrayOfType(100, 3, "100-AlmostSorted-3");
		ExecutionManager.processArrayOfType(100, 4, "100-AlmostSorted-4");
		ExecutionManager.processArrayOfType(100, 5, "100-AlmostSorted-5");
		ExecutionManager.processArrayOfType(100, 6, "100-ReverseSorted");
		
		ExecutionManager.processArrayOfType(1000, 0, "1000-Random-");
		ExecutionManager.processArrayOfType(1000, 1, "1000-AlmostSorted-1");
		ExecutionManager.processArrayOfType(1000, 2, "1000-AlmostSorted-2");
		ExecutionManager.processArrayOfType(1000, 3, "1000-AlmostSorted-3");
		ExecutionManager.processArrayOfType(1000, 4, "1000-AlmostSorted-4");
		ExecutionManager.processArrayOfType(1000, 5, "1000-AlmostSorted-5");
		ExecutionManager.processArrayOfType(1000, 6, "1000-ReverseSorted");
		
		ExecutionManager.processArrayOfType(10000, 0, "10000-Random-");
		ExecutionManager.processArrayOfType(10000, 1, "10000-AlmostSorted-1");
		ExecutionManager.processArrayOfType(10000, 2, "10000-AlmostSorted-2");
		ExecutionManager.processArrayOfType(10000, 3, "10000-AlmostSorted-3");
		ExecutionManager.processArrayOfType(10000, 4, "10000-AlmostSorted-4");
		ExecutionManager.processArrayOfType(10000, 5, "1000-AlmostSorted-5");
		ExecutionManager.processArrayOfType(10000, 6, "10000-ReverseSorted");
		
		System.out.println("End.");
	}

}