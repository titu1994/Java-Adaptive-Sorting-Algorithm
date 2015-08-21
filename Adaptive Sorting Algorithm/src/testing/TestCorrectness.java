package testing;

import managers.ArrayManager;
import managers.ExecutionManager;
import algorithms.HeapSort;
import algorithms.InsertionSort;
import algorithms.MergeSort;
import algorithms.ParallelMergeSort;
import algorithms.QuickSort;
import algorithms.ShellSort;

public class TestCorrectness {

	public static void main(String[] args) {
		InsertionSort is = new InsertionSort();
		ShellSort ss = new ShellSort();
		HeapSort hs = new HeapSort();
		MergeSort ms = new MergeSort();
		QuickSort qs = new QuickSort();
		ParallelMergeSort parallelms = new ParallelMergeSort();

		/**
		 * 100 Random
		 */
		int data[] = ArrayManager.getNextArray(ArrayManager.getRandomArrayOfSize(100));
		int copy[] = ArrayManager.copyArray(data);
		is.sort(copy);
		System.out.println("Insertion Sort - 100 Random " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ss.sort(copy);
		System.out.println("Shell Sort - 100 Random " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		hs.sort(copy);
		System.out.println("Heap Sort - 100 Random " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ms.sort(copy);
		System.out.println("Merge Sort - 100 Random " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		qs.sort(copy);
		System.out.println("Quick Sort - 100 Random " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		parallelms.sort(copy);
		System.out.println("Parallel Merge Sort - 100 Random " + isSorted(copy));
		
		
		/**
		 * 100 Reversed
		 */
		data = ArrayManager.getNextArray(ArrayManager.getReversedArrayOfSize(100));
		
		copy = ArrayManager.copyArray(data);
		is.sort(copy);
		System.out.println("Insertion Sort - 100 Reversed " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ss.sort(copy);
		System.out.println("Shell Sort - 100 Reversed " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		hs.sort(copy);
		System.out.println("Heap Sort - 100 Reversed " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ms.sort(copy);
		System.out.println("Merge Sort - 100 Reversed " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		qs.sort(copy);
		System.out.println("Quick Sort - 100 Reversed " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		parallelms.sort(copy);
		System.out.println("Parallel Merge Sort - 100 Reversed " + isSorted(copy));
		
		/**
		 * 100 Almost Sorted 0.1
		 */
		data = ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 1));
		
		copy = ArrayManager.copyArray(data);
		is.sort(copy);
		System.out.println("Insertion Sort - 100 Almost Sorted 0.1 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ss.sort(copy);
		System.out.println("Shell Sort - 100 Almost Sorted 0.1 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		hs.sort(copy);
		System.out.println("Heap Sort - 100 Almost Sorted 0.1 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ms.sort(copy);
		System.out.println("Merge Sort - 100 Almost Sorted 0.1 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		qs.sort(copy);
		System.out.println("Quick Sort - 100 Almost Sorted 0.1 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		parallelms.sort(copy);
		System.out.println("Parallel Merge Sort - 100 Almost Sorted 0.1 " + isSorted(copy));
		
		/**
		 * 100 Almost Sorted 0.2
		 */
		data = ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 2));
		
		copy = ArrayManager.copyArray(data);
		is.sort(copy);
		System.out.println("Insertion Sort - 100 Almost Sorted 0.2 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ss.sort(copy);
		System.out.println("Shell Sort - 100 Almost Sorted 0.2 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		hs.sort(copy);
		System.out.println("Heap Sort - 100 Almost Sorted 0.2 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ms.sort(copy);
		System.out.println("Merge Sort - 100 Almost Sorted 0.2 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		qs.sort(copy);
		System.out.println("Quick Sort - 100 Almost Sorted 0.2 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		parallelms.sort(copy);
		System.out.println("Parallel Merge Sort - 100 Almost Sorted 0.2 " + isSorted(copy));
		
		
		/**
		 * 100 Almost Sorted 0.33
		 */
		data = ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 3));
		
		copy = ArrayManager.copyArray(data);
		is.sort(copy);
		System.out.println("Insertion Sort - 100 Almost Sorted 0.33 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ss.sort(copy);
		System.out.println("Shell Sort - 100 Almost Sorted 0.33 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		hs.sort(copy);
		System.out.println("Heap Sort - 100 Almost Sorted 0.33 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ms.sort(copy);
		System.out.println("Merge Sort - 100 Almost Sorted 0.33 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		qs.sort(copy);
		System.out.println("Quick Sort - 100 Almost Sorted 0.33 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		parallelms.sort(copy);
		System.out.println("Parallel Merge Sort - 100 Almost Sorted 0.33 " + isSorted(copy));
		
		
		
		/**
		 * 100 Almost Sorted 0.4
		 */
		data = ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 4));
		
		copy = ArrayManager.copyArray(data);
		is.sort(copy);
		System.out.println("Insertion Sort - 100 Almost Sorted 0.4 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ss.sort(copy);
		System.out.println("Shell Sort - 100 Almost Sorted 0.4 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		hs.sort(copy);
		System.out.println("Heap Sort - 100 Almost Sorted 0.4 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ms.sort(copy);
		System.out.println("Merge Sort - 100 Almost Sorted 0.4 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		qs.sort(copy);
		System.out.println("Quick Sort - 100 Almost Sorted 0.4 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		parallelms.sort(copy);
		System.out.println("Parallel Merge Sort - 100 Almost Sorted 0.4 " + isSorted(copy));
		
		
		/**
		 * 100 Almost Sorted 0.5
		 */
		data = ArrayManager.getNextArray(ArrayManager.getAlmostSortedArrayOfSize(100, 5));
		
		copy = ArrayManager.copyArray(data);
		is.sort(copy);
		System.out.println("Insertion Sort - 100 Almost Sorted 0.5 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ss.sort(copy);
		System.out.println("Shell Sort - 100 Almost Sorted 0.5 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		hs.sort(copy);
		System.out.println("Heap Sort - 100 Almost Sorted 0.5 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		ms.sort(copy);
		System.out.println("Merge Sort - 100 Almost Sorted 0.5 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		qs.sort(copy);
		System.out.println("Quick Sort - 100 Almost Sorted 0.5 " + isSorted(copy));
		
		copy = ArrayManager.copyArray(data);
		parallelms.sort(copy);
		System.out.println("Parallel Merge Sort - 100 Almost Sorted 0.5 " + isSorted(copy));
		
	}
	
	private static boolean isSorted(int sortedArray[]) {
		for(int i = 1; i < sortedArray.length; i++) {
			if(sortedArray[i-1] > sortedArray[i]) {
				return false;
			}
		}
		return true;
	}

}
