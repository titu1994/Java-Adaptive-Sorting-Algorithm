package testing;

import java.util.Random;
import java.util.Scanner;

import managers.ArrayManager;
import algorithms.AbstractSort;
import algorithms.RunSort;
import algorithms.ShellSort;

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

		RunSort runsort = new RunSort();
		long t1 = System.nanoTime();
		runsort.sort(data);
		long t2 = System.nanoTime();
		
		long time = (t2 - t1) / 1000;
		System.out.println("Runs : " + runsort.runs);
		System.out.println("Execution Time RunSort : " + time);
		
		AbstractSort is = new ShellSort();
		t1 = System.nanoTime();
		is.sort(dataCopy);
		t2 = System.nanoTime();
		
		time = (t2 - t1) / 1000;
		System.out.println("Execution Time In Micro Seconds : " + time);
		
		System.out.println("Sort Type : " + runsort.type);
	}

}
