package managers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import algorithms.AbstractSort;
import algorithms.HeapSort;
import algorithms.InsertionSort;
import algorithms.MergeSort;
import algorithms.ParallelMergeSort;
import algorithms.QuickSort;
import algorithms.ShellSort;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExecutionManager {
	
	private ExecutionManager() { }
	
	/**
	 * Method to load the array from file to memory, execute all algorithms on the copies of the array, and write the result
	 * of each execution into a file.
	 * @param size - 100, 1000, 10000
	 * @param type - 0 to 6 : See ArrayManager.Type... integers for meaning
	 * @param filename - Filename to store the results
	 */
	public static void processArrayOfType(int size, int type, String filename) {
		int[] data = null;
		Result result = null;
		
		data = loadNextArray(size, type);
		while(data != null) {
			result = executeAllSortingAlgorithms(data, type);
			writeResultToFile(filename, result);
			data = loadNextArray(size, type);
		}
	}
	
	private static int[] loadNextArray(int size, int type) {
		int data[] = null;
		switch(type) {
		case 0: {
			switch(size) {
			case 100: return ArrayManager.getNextArray(ArrayManager.getRandomArrayOfSize(100));
			case 1000: return ArrayManager.getNextArray(ArrayManager.getRandomArrayOfSize(1000));
			case 10000: return ArrayManager.getNextArray(ArrayManager.getRandomArrayOfSize(10000));
			}
			return ArrayManager.getNextArray(ArrayManager.getRandomArrayOfSize(100));
		}
		case 1: {
			switch(size) {
			case 100: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 1));
			case 1000: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(1000, 1));
			case 10000: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(10000, 1));
			}
			return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 1));
		}
		case 2: {
			switch(size) {
			case 100: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 2));
			case 1000: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(1000, 2));
			case 10000: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(10000, 2));
			}
			return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 2));
		}
		case 3: {
			switch(size) {
			case 100: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 3));
			case 1000: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(1000, 3));
			case 10000: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(10000, 3));
			}
			return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 3));
		}
		case 4: {
			switch(size) {
			case 100: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 4));
			case 1000: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(1000, 4));
			case 10000: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(10000, 4));
			}
			return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 4));
		}
		case 5: {
			switch(size) {
			case 100: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 5));
			case 1000: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(1000, 5));
			case 10000: return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(10000, 5));
			}
			return ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 5));
		}
		}
		
		return data;
	}
	
	private static Result executeAllSortingAlgorithms(int data[], int type) {
		return executeSortingAlgorithm(data, type, new InsertionSort(), 
											 	   new ShellSort(),
											 	   new HeapSort(),
											 	   new MergeSort(),
											 	   new QuickSort(),
											 	   new ParallelMergeSort());
	}
	
	private static void writeResultToFile(String filename, Result result) {
		try (PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".json")), true)) {
			Gson gson = new GsonBuilder().serializeNulls().serializeSpecialFloatingPointValues().create();
			pr.println(gson.toJson(result));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Succesfully saved results to file.");
	}
	
	
	private static Result executeSortingAlgorithm(int data[], int type, AbstractSort... sortingAlgorithms) {
		Result result = new Result();
		result.arraySize = data.length;
		
		String typeString = "Random";
		switch(type) {
		case 0: typeString = "Random"; break;
		case 1: typeString = "Almost-Sorted-0.1"; break;
		case 2: typeString = "Almost-Sorted-0.2"; break;
		case 3: typeString = "Almost-Sorted-0.33"; break;
		case 4: typeString = "Almost-Sorted-0.4"; break;
		case 5: typeString = "Almost-Sorted-0.5"; break;
		case 6: typeString = "Reverse-Sorted"; break;
		}
		
		result.arraytype = typeString;
		
		int copy[];
		long t1, t2, total;
		for(int i = 0; i < sortingAlgorithms.length; i++) {
			copy = ArrayManager.copyArray(data);
			result.runs = ArrayManager.computeRuns(copy);
			
			t1 = System.nanoTime();
			sortingAlgorithms[i].sort(copy);
			t2 = System.nanoTime();
			
			total = (t2 - t1) / 1000; // Time in microseconds
			
			if(sortingAlgorithms[i] instanceof InsertionSort)
				result.insertionSortExecutionTime = total;
			else if(sortingAlgorithms[i] instanceof ShellSort)
				result.shellSortExecutionTime = total;
			else if(sortingAlgorithms[i] instanceof HeapSort)
				result.heapSortExecutionTime = total;
			else if(sortingAlgorithms[i] instanceof MergeSort)
				result.mergeSortExecutionTime = total;
			else if(sortingAlgorithms[i] instanceof QuickSort)
				result.quickSortExecutionTime = total;
			else if(sortingAlgorithms[i] instanceof ParallelMergeSort)
				result.parallelMergeSortExecutionTime = total;
		}
		
		return result;		
	}
	
	public static class Result {
		public int arraySize;
		public double runs;
		public String arraytype;
		public long insertionSortExecutionTime;
		public long shellSortExecutionTime;
		public long heapSortExecutionTime;
		public long mergeSortExecutionTime;
		public long quickSortExecutionTime;
		public long parallelMergeSortExecutionTime;
		
		public Result() {}
	}

}