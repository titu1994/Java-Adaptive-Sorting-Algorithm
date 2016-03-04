package algorithms;


import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yue on 27-Feb-16.
 */
public class ParallelQuickSort extends AbstractSort {
	
	private static final int MIN_GRANULARITY = 8192;
			
    private static int THRESHOLD = 1000000;
    private static int NO_OF_THREADS = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executor;

    private static AtomicInteger counter = new AtomicInteger(1);
    private static Object lock = new Object();

    @Override
    public void sort(int[] data) {
        if(NO_OF_THREADS == 1 || data.length <= MIN_GRANULARITY) {
            Arrays.sort(data);
            return;
        }
        
        int g;
        THRESHOLD = (g = (data.length / (NO_OF_THREADS << 2))) <= MIN_GRANULARITY ?  MIN_GRANULARITY: g;
        
        counter.set(1);
        executor = Executors.newFixedThreadPool(NO_OF_THREADS);

        counter.getAndIncrement();
        executor.submit(quickSortCallable(0, data.length - 1, data));

        synchronized (lock) {
            while(counter.get() > 1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void sortStatic(int data[]) {
    	if(NO_OF_THREADS == 1 || data.length <= MIN_GRANULARITY) {
            Arrays.sort(data);
            return;
        }
        
        int g;
        THRESHOLD = (g = (data.length / (NO_OF_THREADS << 2))) <= MIN_GRANULARITY ?  MIN_GRANULARITY: g;
        
        counter.set(1);
        executor = Executors.newFixedThreadPool(NO_OF_THREADS);

        counter.getAndIncrement();
        executor.submit(quickSortCallable(0, data.length - 1, data));

        synchronized (lock) {
            while(counter.get() > 1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Runnable quickSortCallable(int low, int high, int data[]) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                if((high - low) <= THRESHOLD) {
                    Arrays.sort(data, low, high+1);

                    synchronized (lock) {
                        counter.getAndDecrement();
                        lock.notify();
                    }

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
                    counter.getAndIncrement();
                    executor.submit(quickSortCallable(low, j, data));
                }
                if (i < high) {
                    counter.getAndIncrement();
                    executor.submit(quickSortCallable(i, high, data));
                }

                synchronized (lock) {
                    counter.getAndDecrement();
                    lock.notify();
                }
            }
        };
        return r;
    }

}
