package algorithms;

public class HeapSort extends AbstractSort {

	private static int N;

	@Override
	public void sort(int[] data) {
		heapify(data);
		int temp = 0;
		for (int i = N; i > 0; i--) {
			temp = data[0];
			data[0] = data[i];
			data[i] = temp;
			N = N - 1;
			maxheap(data, 0);
		}
	}

	public static void heapify(int arr[]) {
		N = arr.length - 1;
		for (int i = N / 2; i >= 0; i--)
			maxheap(arr, i);
	}

	public static void maxheap(int arr[], int i) {
		int left = 2 * i;
		int right = 2 * i + 1;
		int max = i;
		if (left <= N && arr[left] > arr[i])
			max = left;
		if (right <= N && arr[right] > arr[max])
			max = right;

		if (max != i) {
			int temp = 0;
			temp = arr[i];
			arr[i] = arr[max];
			arr[max] = temp;
			maxheap(arr, max);
		}
	}

}
