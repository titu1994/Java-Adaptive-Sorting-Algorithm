package algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArrayManager {
	
	private ArrayManager() {	}

	private static final String[] array100 = {
		"Array-100-Random.txt",
		"Array-100-Almost-Sorted-0.1.txt",
		"Array-100-Almost-Sorted-0.2.txt",
		"Array-100-Almost-Sorted-0.33.txt",
		"Array-100-Almost-Sorted-0.4.txt",
		"Array-100-Almost-Sorted-0.5.txt",
		"Array-100-Reverse-Sorted.txt"
	};

	private static final String[] array1000 = {
		"Array-1000-Random.txt",
		"Array-1000-Almost-Sorted-0.1.txt",
		"Array-1000-Almost-Sorted-0.2.txt",
		"Array-1000-Almost-Sorted-0.33.txt",
		"Array-1000-Almost-Sorted-0.4.txt",
		"Array-1000-Almost-Sorted-0.5.txt",
		"Array-1000-Reverse-Sorted.txt"
	};

	private static final String[] array10000 = {
		"Array-10000-Random.txt",
		"Array-10000-Almost-Sorted-0.1.txt",
		"Array-10000-Almost-Sorted-0.2.txt",
		"Array-10000-Almost-Sorted-0.33.txt",
		"Array-10000-Almost-Sorted-0.4.txt",
		"Array-10000-Almost-Sorted-0.5.txt",
		"Array-10000-Reverse-Sorted.txt"
	};

	private static int RandomSortedIndex = 0;
	private static int AlmostSortedIndex1 = 1;
	private static int AlmostSortedIndex2 = 2;
	private static int AlmostSortedIndex3 = 3;
	private static int AlmostSortedIndex4 = 4;
	private static int AlmostSortedIndex5 = 5;
	private static int ReverseSortedIndex = 6;

	public static int Size100 = 100;
	public static int Size1000 = 1000;
	public static int Size10000 = 10000;

	private static int arrayIndex = 0;
	private static String lastArrayName;
	private static BufferedReader arrayReader;

	public static int[] getNextArray(String filename) {
		int[] hold = null;
		if(lastArrayName == null)
			lastArrayName = filename;

		if(!lastArrayName.equals(filename)) {
			if(arrayReader != null)
				try {
					arrayReader.close();
					arrayReader = null;
				} catch (IOException e) { }

			arrayIndex = 0;
			lastArrayName = null;
		}

		if(arrayReader == null)
			try {
				arrayReader = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		try {
			String line = arrayReader.readLine();
			if(line == null) {
				if(arrayReader != null)
					try {
						arrayReader.close();
						arrayReader = null;
					} catch (IOException e) { }

				arrayIndex = 0;
				lastArrayName = null;
			}
			else {
				String parts[];
				parts = line.substring(1, line.length() - 1).split(",");
				hold = new int[parts.length];
				
				for(int i = 0; i < parts.length; i++) 
					hold[i] = Integer.parseInt(parts[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hold;
	}
	
	public static int[] copyArray(int data[]) {
		int copy[] = new int[data.length];
		System.arraycopy(data, 0, copy, 0, data.length);
		return copy;
	}
	
	public static double computeRuns(int data[]) {
		 double runs = 0;
         int n = data.length;
         int subsetCount = 1;

         for (int i = 1; i < n; i++)
         {
             if (data[i - 1] > data[i])
                 subsetCount++;
         }
         runs = subsetCount/(double) n;
         return runs;
	}
	
	public static String getRandomArrayOfSize(int size)
    {
        switch (size)
        {
            case 100:
                return array100[RandomSortedIndex];
            case 1000:
                return array1000[RandomSortedIndex];
            case 10000:
                return array10000[RandomSortedIndex];
        }
        return array100[RandomSortedIndex];
    }

    public static String getReversedArrayOfSize(int size)
    {
        switch (size)
        {
            case 100:
                return array100[ReverseSortedIndex];
            case 1000:
                return array1000[ReverseSortedIndex];
            case 10000:
                return array10000[ReverseSortedIndex];
        }
        return array100[RandomSortedIndex];
    }

    public static String getAlmostSortedArrayOfSize(int size, int preSortednessDegree)
    {
        switch (size)
        {
            case 100:
                switch (preSortednessDegree)
                {
                    case 1: 
                        return array100[AlmostSortedIndex1];
                    case 2:
                        return array100[AlmostSortedIndex2];
                    case 3:
                        return array100[AlmostSortedIndex3];
                    case 4:
                        return array100[AlmostSortedIndex4];
                    case 5:
                        return array100[AlmostSortedIndex5];
                }
                return array100[AlmostSortedIndex1];
            case 1000:
                switch (preSortednessDegree)
                {
                    case 1:
                        return array1000[AlmostSortedIndex1];
                    case 2:
                        return array1000[AlmostSortedIndex2];
                    case 3:
                        return array1000[AlmostSortedIndex3];
                    case 4:
                        return array1000[AlmostSortedIndex4];
                    case 5:
                        return array1000[AlmostSortedIndex5];
                }
                return array1000[AlmostSortedIndex1];
            case 10000:
                switch (preSortednessDegree)
                {
                    case 1:
                        return array10000[AlmostSortedIndex1];
                    case 2:
                        return array10000[AlmostSortedIndex2];
                    case 3:
                        return array10000[AlmostSortedIndex3];
                    case 4:
                        return array10000[AlmostSortedIndex4];
                    case 5:
                        return array10000[AlmostSortedIndex5];
                }
                return array10000[AlmostSortedIndex1];
        }
        return array100[AlmostSortedIndex1];
    }
    
    @Override
    protected void finalize() throws Throwable {
    	if(arrayReader != null)
			try {
				arrayReader.close();
				arrayReader = null;
			} catch (IOException e) { }
    	super.finalize();
    }


}
