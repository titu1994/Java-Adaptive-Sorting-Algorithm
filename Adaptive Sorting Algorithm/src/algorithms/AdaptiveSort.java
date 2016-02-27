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
            runs = (long) ((subsetCount/ (double) n) * 100000);
            //System.out.println(runs);

            if(runs > 68799) {
                //System.out.println("Else : " + (System.nanoTime() - t1));

                //shellsortInternal(data);
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
            else if(n <= 50 && runs > 44000) {
                //System.out.println("n <= 50 && runs > 44000 && runs <= 68799 : " + (System.nanoTime() - t1));

                Arrays.parallelSort(data);
            }
            else if(n <= 50 && runs > 25388) {
                //System.out.println("n <= 50 && runs > 25388 && runs <= 44000 : " + (System.nanoTime() - t1));

                workingArray = new int[data.length];
                mergesort(data, 0, data.length - 1);

                //native_mergesort(data, data.length);
            }
            else {
                //insertionSortInternal(data);
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
        else {
            // Parallel Merge Sort
            Arrays.parallelSort(data);
        }
    }

    private static void iterativeMergeSort(int data[]) {
        for (int i = 1; i <= data.length / 2 + 1; i *= 2)
        {
            for (int j = i; j < data.length; j += 2 * i)
            {
                iterativeMerge(data, j - i, j, Math.min(j + i, data.length));
            }
        }
    }
    private static void iterativeMerge(int[] data, int start, int middle, int end) {
        int[] merge = new int[end-start];
        int l = 0, r = 0, i = 0;
        while (l < (middle- start) && r < (end- middle))
        {
            merge[i++] = data[start + l] <  data[middle + r] ? data[start + l++] : data[middle + r++];
        }

        while (r < (end - middle)) merge[i++] = data[middle + r++];

        while (l < (middle - start)) merge[i++] = data[start + l++];

        System.arraycopy(merge, 0, data, start, merge.length);
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

    private static void insertionSort(int data[]) {
        int temp, j;
        for (int i = 1; i < data.length; i++) {
            temp = data[i];
            for (j = i - 1; j >= 0 && temp < data[j]; j--)
                data[j + 1] = data[j];
            data[j + 1] = temp;
        }
    }

    private static int binarySearch(int data[], int key, int low, int high) {
        while (low <= high) {
            int imid = (low + high) >>> 1;
            if (data[imid] == key)
                return imid;
            else if (data[imid] < key)
                low = imid + 1;
            else
                high = imid - 1;
        }
        return -1;
    }

    private static void binaryInsertionSort(int data[]) {
        int i, loc, j, k, selected;

        for (i = 1; i < data.length; i++) {
            j = i - 1;
            selected = data[i];
            loc = binarySearch(data, selected, 0, j);

            while (j >= loc) {
                data[j+1] = data[j];
                j--;
            }
            data[j+1] = selected;
        }
    }

}
