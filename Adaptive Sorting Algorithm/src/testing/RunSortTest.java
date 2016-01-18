package testing;

import algorithms.AdaptiveSort;
import algorithms.ParallelMergeSort;
import managers.ArrayManager;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RunSortTest {

	public static void main(String[] args) {
		Random r = new Random();
		System.out.println("Enter n : ");
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int data[] = new int[n];
		int dataCopy[];
		
		for(int i = 0; i < n; i++) {
			data[i] = r.nextInt(n);
		}
		
		/*Arrays.sort(data);
		int temp = 0;
		for(int i = 0; i < n / 2; i++) {
			temp = data[i];
			data[i] = data[n - i - 1];
			data[n - i - 1] = temp;
		}
		*/
		dataCopy = ArrayManager.copyArray(data);
		int[] dataCopy2 = ArrayManager.copyArray(data);

		AdaptiveSort runsort = new AdaptiveSort();
		long t1 = System.nanoTime();
		runsort.sort(data);
		long t2 = System.nanoTime();
		
		long time = (t2 - t1) / 1000;
		System.out.println("Execution Time RunSort : " + time);
		
		ParallelMergeSort is = new ParallelMergeSort();
		t1 = System.nanoTime();
		is.sort(dataCopy);
		t2 = System.nanoTime();
		
		time = (t2 - t1) / 1000;
		System.out.println("Execution Time In Micro Seconds : " + time);
		
		t1 = System.nanoTime();
		Arrays.parallelSort(dataCopy2);
		t2 = System.nanoTime();
		
		time = (t2 - t1) / 1000;
		System.out.println("Execution Time (Array.parallelSort()) In Micro Seconds : " + time);
	}

}
