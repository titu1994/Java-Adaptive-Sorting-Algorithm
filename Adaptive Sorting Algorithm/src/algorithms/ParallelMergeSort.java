package algorithms;

import java.util.Arrays;

public class ParallelMergeSort extends AbstractSort {

	@Override
	public void sort(int[] data) {
		Arrays.parallelSort(data);
	}

}
