package algorithms;

import java.util.Arrays;

/**
 * Created by Yue on 1/18/2016.
 *
 */
public class AdaptiveSort extends AbstractSort{

    static {
        System.loadLibrary("sortlib");
    }

    private native int[] native_shellsort(int data[], int n);
    private native int[] native_mergesort(int data[], int n);
    private native int[] native_insertionsort(int data[], int n);

    private static int THRESHOLD = 100;
    private static int workingArray[];

    private static final int[] incs = { 1, 3, 7, 21, 48, 112, 336, 861, 1968,
            4592, 13776, 33936, 86961, 198768, 463792, 1391376, 3402672,
            8382192, 21479367, 49095696, 114556624, 343669872, 52913488,
            2085837936 };

    public void sort(int[] data) {

        int n = data.length;

        if(n <= 100) {
            // Compute Runs
            long runs = 0;
            int subsetCount = 1;

            for (int i = 1; i < n; i++)
            {
                if (data[i - 1] >= data[i])
                    subsetCount++;
            }
            // Compute runs in long to reduce execution cycles and boost speed
            runs = (long) ((subsetCount/ (double) n) * 100000);
            
            if(runs > 68799) {
                // Shell Sort
               
            	for (int l = incs.length / incs[0]; l > 0;) {
                    int m = incs[--l];
                    for (int i = m; i < n; ++i) {
                        int j = i - m;
                        if (data[i] < data[j]) {
                            int tempItem = data[i];
                            do {
                                data[j + m] = data[j];
                                j -= m;
                            } while ((j >= 0) && (tempItem < data[j]));
                            data[j + m] = tempItem;
                        }
                    }
                }
                
                //native_shellsort(data, data.length);
            }
            else if(n <= 50 && runs > 44000) { // Short Circuit AND to stop if n > 50
                // Parallel Merge Sort
                Arrays.parallelSort(data);
            }
            else if(n <= 50 && runs > 25388) { // Short Circuit AND to stop if n > 50
                // Merge Sort
                workingArray = new int[data.length];
                mergesort(data, 0, data.length - 1);

                //native_mergesort(data, data.length);
            }
            else { // If all else failes
                // Insertion sort
                //System.out.println("Runs < 25388 : " + (System.nanoTime() - t1));

                int temp, j;
                for (int i = 1; i < data.length; i++) {
                    temp = data[i];
                    for (j = i - 1; j >= 0 && temp < data[j]; j--)
                        data[j + 1] = data[j];
                    data[j + 1] = temp;
                }

                //native_insertionsort(data, data.length);
            }
        }
        else if (n <= 1000) {
        	// Quick Sort
        	quicksort(data, 0, data.length - 1);
        }
        else if (n < 500000){
            // Parallel Merge Sort
            Arrays.parallelSort(data);
        }
        else {
        	// Parallel Quick Sort
        	ParallelQuickSort.sortStatic(data);
        }
    }

    private static void mergesort(int data[], int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergesort(data, low, middle);
            mergesort(data, middle + 1, high);
            merge(data, low, middle, high);
        }
    }


    private static void merge(int data[], int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            workingArray[i] = data[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (workingArray[i] <= workingArray[j]) {
                data[k] = workingArray[i];
                i++;
            } else {
                data[k] = workingArray[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            data[k] = workingArray[i];
            k++;
            i++;
        }
    }
    
    private void quicksort(int data[], int low, int high) {
		if((high - low) <= THRESHOLD) {
			Arrays.sort(data, low, high + 1);
			return;
		}
		 
		int i = low, j = high;
		int pivot = data[(low + high) >>> 1];
		int temp;
		
		while (i <= j) {
			while (data[i] < pivot) {
				i++;
			}
			
			while (data[j] > pivot) {
				j--;
			}

			if (i <= j) {
				temp = data[i];
				data[i] = data[j];
				data[j] = temp;
				i++;
				j--;
			}
		}
		if (low < j) {
			quicksort(data, low, j);
		}
		if (i < high) {
			quicksort(data, i, high);
		}
	}

    private static void insertionSort(int data[]) {
        int temp, j;
        for (int i = 1; i < data.length; i++) {
            temp = data[i];
            for (j = i - 1; j >= 0 && temp < data[j]; j--)
                data[j + 1] = data[j];
            data[j + 1] = temp;
        }
    }
}
