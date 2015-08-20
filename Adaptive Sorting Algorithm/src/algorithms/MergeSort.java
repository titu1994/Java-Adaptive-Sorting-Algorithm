package algorithms;

public class MergeSort extends AbstractSort {

	private int workingArray[];

	@Override
	public void sort(int[] data) {
		workingArray = new int[data.length];
		mergesort(data, 0, data.length - 1);
	}

	private void mergesort(int data[], int low, int high) {
		if (low < high) {
			int middle = low + (high - low) / 2;
			mergesort(data, low, middle);
			mergesort(data, middle + 1, high);
			merge(data, low, middle, high);
		}
	}

	private void merge(int data[], int low, int middle, int high) {
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

}
