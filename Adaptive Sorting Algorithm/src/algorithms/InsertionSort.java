package algorithms;

public class InsertionSort extends AbstractSort {

	@Override
	public void sort(int[] data) {
		int temp, j;
		for(int i = 1; i < data.length; i++) {
			temp = data[i];
			for(j = i - 1; j >= 0 && temp < data[j]; j--)
				data[j + 1] = data[j];
			data[j + 1] = temp;
		}
	}

}
