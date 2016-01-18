package algorithms;

import managers.ArrayManager;

import java.util.Arrays;

/**
 * Created by Yue on 1/18/2016.
 *
 */
public class AdaptiveSort extends AbstractSort{

    @Override
    public void sort(int[] data) {

        int n = data.length;

        if(n < 100) {
            double runs = ArrayManager.computeRuns(data);

            if(runs <= 0.11) {
                // Insertion Sort
                new InsertionSort().sort(data);
            }
            else {
                if(runs <= 0.68799) {
                    if (runs <= 0.25388) {
                        // Insertion Sort
                        new InsertionSort().sort(data);
                    }
                    else {
                        if(n <= 50) {
                            if(runs < 0.44) {
                                // Merge Sort
                                new MergeSort().sort(data);
                            }
                            else {
                                // Parallel Merge Sort
                                Arrays.parallelSort(data);
                            }
                        }
                        else {
                            // Insertion Sort
                            new InsertionSort().sort(data);
                        }
                    }
                }
                else {
                    // Shell Sort
                    new ShellSort().sort(data);
                }
            }
        }
        else {
            // Parallel Merge Sort
            Arrays.parallelSort(data);
        }
    }
}
