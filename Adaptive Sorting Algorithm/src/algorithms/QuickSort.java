package algorithms;

import java.util.Arrays;

public class QuickSort extends AbstractSort {
	private int THRESHOLD = 1000;
	
	@Override
	public void sort(int[] data) {
		quicksort(data, 0, data.length - 1);
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

}
