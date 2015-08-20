package algorithms;

public class ShellSort extends AbstractSort {

	private static final int[] incs = { 1, 3, 7, 21, 48, 112, 336, 861, 1968,
			4592, 13776, 33936, 86961, 198768, 463792, 1391376, 3402672,
			8382192, 21479367, 49095696, 114556624, 343669872, 52913488,
			2085837936 };

	@Override
	public void sort(int[] data) {
		int n = data.length;
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
