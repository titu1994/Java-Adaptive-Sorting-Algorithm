package algorithms;

import java.util.Arrays;

/**
 * Created by Yue on 1/18/2016.
 *
 */
public class AdaptiveSort extends AbstractSort{

    private static int THRESHOLD = 100;
    private static int workingArray[];

    private static final int[] incs = { 1, 3, 7, 21, 48, 112, 336, 861, 1968,
            4592, 13776, 33936, 86961, 198768, 463792, 1391376, 3402672,
            8382192, 21479367, 49095696, 114556624, 343669872, 52913488,
            2085837936 };


    @Override
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

            //System.out.print("Runs : " + runs + " ");
            //long t1 = System.nanoTime();

            if(runs > 68799) {
                //System.out.println("Else : " + (System.nanoTime() - t1));

                shellsortInternal(data);
            }
            else if(n <= 50 && runs > 44000) {
                //System.out.println("n <= 50 && runs > 44000 && runs <= 68799 : " + (System.nanoTime() - t1));

                Arrays.parallelSort(data);
            }
            else if(n <= 50 && runs > 25388) {
                //System.out.println("n <= 50 && runs > 25388 && runs <= 44000 : " + (System.nanoTime() - t1));

                workingArray = new int[data.length];
                mergesort(data, 0, data.length - 1);
            }
            else {
                //insertionSortInternal(data);
                //System.out.println("Runs < 25388 : " + (System.nanoTime() - t1));

                int temp, j;
                for(int i = 1; i < data.length; i++) {
                    temp = data[i];
                    for(j = i - 1; j >= 0 && temp < data[j]; j--)
                        data[j + 1] = data[j];
                    data[j + 1] = temp;
                }
            }

            /*
            //Attempt 2
            if(runs <= 25388) {
                //insertionSortInternal(data);
                System.out.println("Runs < 25388 : " + (System.nanoTime() - t1));

                int temp, j;
                for(int i = 1; i < data.length; i++) {
                    temp = data[i];
                    for(j = i - 1; j >= 0 && temp < data[j]; j--)
                        data[j + 1] = data[j];
                    data[j + 1] = temp;
                }
            }
            else if(n <= 50 && runs <= 44000) {
                System.out.println("n <= 50 && runs > 25388 && runs <= 44000 : " + (System.nanoTime() - t1));

                workingArray = new int[data.length];
                mergesort(data, 0, data.length - 1);
            }
            else if(n <= 50 && runs <= 68799) {
                System.out.println("n <= 50 && runs > 44000 && runs <= 68799 : " + (System.nanoTime() - t1));

                Arrays.parallelSort(data);
            }
            else {
                System.out.println("Else : " + (System.nanoTime() - t1));

                shellsortInternal(data);
            }
            */

            /*
            // Attempt 1
            if(runs <= 11000) {
                // Insertion Sort
                //insertionSortInternal(data);
                int temp, j;
                for(int i = 1; i < data.length; i++) {
                    temp = data[i];
                    for(j = i - 1; j >= 0 && temp < data[j]; j--)
                        data[j + 1] = data[j];
                    data[j + 1] = temp;
                }
            }
            else {
                if(runs <= 68799) {
                    if (runs <= 25388) {
                        // Insertion Sort
                        //insertionSortInternal(data);
                        int temp, j;
                        for(int i = 1; i < data.length; i++) {
                            temp = data[i];
                            for(j = i - 1; j >= 0 && temp < data[j]; j--)
                                data[j + 1] = data[j];
                            data[j + 1] = temp;
                        }
                    }
                    else {
                        if(n <= 50) {
                            if(runs <= 44000) {
                                // Merge Sort
                                //workingArray = new int[data.length];
                                //mergesort(data, 0, data.length - 1);

                                Arrays.parallelSort(data);
                            }
                            else {
                                // Parallel Merge Sort
                                Arrays.parallelSort(data);
                                //System.out.println("PMS");
                            }
                        }
                        else {
                            // Insertion Sort
                            //insertionSortInternal(data);
                            int temp, j;
                            for(int i = 1; i < data.length; i++) {
                                temp = data[i];
                                for(j = i - 1; j >= 0 && temp < data[j]; j--)
                                    data[j + 1] = data[j];
                                data[j + 1] = temp;
                            }
                        }
                    }
                }
                else {
                    // Shell Sort
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
                }
            }
            */
        }
        else {
            // Parallel Merge Sort
            Arrays.parallelSort(data);
        }
    }

    private static void insertionSortInternal(int data[]) {
        int temp, j;
        for(int i = 1; i < data.length; i++) {
            temp = data[i];
            for(j = i - 1; j >= 0 && temp < data[j]; j--)
                data[j + 1] = data[j];
            data[j + 1] = temp;
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

    private static void shellsortInternal(int data[]) {
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
