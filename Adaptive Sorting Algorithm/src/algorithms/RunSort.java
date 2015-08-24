package algorithms;

import java.util.Arrays;

public class RunSort extends AbstractSort {
	
	private static final int[] incs = { 1, 3, 7, 21, 48, 112, 336, 861, 1968,
		4592, 13776, 33936, 86961, 198768, 463792, 1391376, 3402672,
		8382192, 21479367, 49095696, 114556624, 343669872, 52913488,
		2085837936 };
	
	public double runs = 0;
	public int type;
	
	@Override
	public void sort(int[] data) {
		int n = data.length;
		
		if(n < 100) {
			int subsetCount = 1;
			for (int i = 1; i < n; i++)
			{
				if (data[i - 1] >= data[i])
					subsetCount++;
			}
			runs = subsetCount/(double) n;
			
			if(runs <= 0.57) {
				// Insertion Sort 
				int temp, j;
				for(int i = 1; i < data.length; i++) {
					temp = data[i];
					for(j = i - 1; j >= 0 && temp < data[j]; j--)
						data[j + 1] = data[j];
					data[j + 1] = temp;
				}
			}
			else {
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
			}
		}
		else if(n >= 100 && n < 1000) {
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
		}
		else if(n >= 1000) {
			// Parallel Merge Sort
			Arrays.parallelSort(data);
		}
		
	}
	
	

}
