package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArrayManager {

	private ArrayManager() { }

	private static final String[] array50 = {
		"Array-50-Random.txt",
		"Array-50-Almost-Sorted-0.1.txt",
		"Array-50-Almost-Sorted-0.2.txt",
		"Array-50-Almost-Sorted-0.33.txt",
		"Array-50-Almost-Sorted-0.4.txt",
		"Array-50-Almost-Sorted-0.5.txt",
		"Array-50-Reverse-Sorted.txt"
	};

	private static final String[] array100 = {
		"Array-100-Random.txt",
		"Array-100-Almost-Sorted-0.1.txt",
		"Array-100-Almost-Sorted-0.2.txt",
		"Array-100-Almost-Sorted-0.33.txt",
		"Array-100-Almost-Sorted-0.4.txt",
		"Array-100-Almost-Sorted-0.5.txt",
		"Array-100-Reverse-Sorted.txt"
	};
	
	private static final String[] array500 = {
		"Array-500-Random.txt",
		"Array-500-Almost-Sorted-0.1.txt",
		"Array-500-Almost-Sorted-0.2.txt",
		"Array-500-Almost-Sorted-0.33.txt",
		"Array-500-Almost-Sorted-0.4.txt",
		"Array-500-Almost-Sorted-0.5.txt",
		"Array-500-Reverse-Sorted.txt"
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

	private static final String[] array100000 = {
		"Array-100000-Random.txt",
		"Array-100000-Almost-Sorted-0.1.txt",
		"Array-100000-Almost-Sorted-0.2.txt",
		"Array-100000-Almost-Sorted-0.33.txt",
		"Array-100000-Almost-Sorted-0.4.txt",
		"Array-100000-Almost-Sorted-0.5.txt",
		"Array-100000-Reverse-Sorted.txt"
	};

	private static final String[] array500000 = {
		"Array-500000-Random.txt",
		"Array-500000-Almost-Sorted-0.1.txt",
		"Array-500000-Almost-Sorted-0.2.txt",
		"Array-500000-Almost-Sorted-0.33.txt",
		"Array-500000-Almost-Sorted-0.4.txt",
		"Array-500000-Almost-Sorted-0.5.txt",
		"Array-500000-Reverse-Sorted.txt"
	};

	private static final String[] array1000000 = {
		"Array-1000000-Random.txt",
		"Array-1000000-Almost-Sorted-0.1.txt",
		"Array-1000000-Almost-Sorted-0.2.txt",
		"Array-1000000-Almost-Sorted-0.33.txt",
		"Array-1000000-Almost-Sorted-0.4.txt",
		"Array-1000000-Almost-Sorted-0.5.txt",
		"Array-1000000-Reverse-Sorted.txt"
	};


	// ARRAY TYPE: 
	public static int TypeRandom = 0; // Not sorted / Randomly arranged
	public static int TypeAlmostSorted1 = 1; // 10% sorted recurring fragments
	public static int TypeAlmostSorted2 = 2; // 20% sorted recurring fragments
	public static int TypeAlmostSorted3 = 3; // 33% sorted recurring fragments
	public static int TypeAlmostSorted4 = 4; // 40% sorted recurring fragments
	public static int TypeAlmostSorted5 = 5; // 50% sorted recurring fragments
	public static int TypeReverseSorted = 6; // Reverse sorted
	//END of ARRAY TYPES

	public static int Size50 = 50;
	public static int Size100 = 100;
	public static int Size500 = 500;
	public static int Size1000 = 1000;
	public static int Size10000 = 10000;
	public static int Size100000 = 100000;
	public static int Size500000 = 500000;
	public static int Size1000000 = 1000000;

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

				lastArrayName = null;
			}
			else {
				String parts[];
				parts = line.substring(1, line.length() - 1).split(",");
				hold = new int[parts.length];

				for(int i = 0; i < parts.length; i++) 
					hold[i] = Integer.parseInt(parts[i].trim());

				//System.out.println("ArrayManager: Array is not empty.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//if(hold == null)
		//	System.out.println("ArrayManager: Array is empty.");
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
			if (data[i - 1] >= data[i])
				subsetCount++;
		}
		runs = subsetCount/(double) n;
		return runs;
	}

	public static String getRandomArrayOfSize(int size)
	{
		switch (size)
		{
		case 50:
			return array50[TypeRandom];
		case 100:
			return array100[TypeRandom];
		case 500:
			return array500[TypeRandom];
		case 1000:
			return array1000[TypeRandom];
		case 10000:
			return array10000[TypeRandom];
		case 100000:
			return array100000[TypeRandom];
		case 500000:
			return array500000[TypeRandom];
		case 1000000:
			return array1000000[TypeRandom];
		}
		return array100[TypeRandom];
	}

	public static String getReversedArrayOfSize(int size)
	{
		switch (size)
		{
		case 50:
			return array50[TypeReverseSorted];
		case 100:
			return array100[TypeReverseSorted];
		case 500:
			return array500[TypeReverseSorted];
		case 1000:
			return array1000[TypeReverseSorted];
		case 10000:
			return array10000[TypeReverseSorted];
		case 100000:
			return array100000[TypeReverseSorted];
		case 500000:
			return array500000[TypeReverseSorted];
		case 1000000:
			return array1000000[TypeReverseSorted];
		}
		return array100[TypeRandom];
	}

	public static String getAlmostSortedArrayOfSize(int size, int preSortednessDegree)
	{
		switch (size)
		{
		case 50:
			switch (preSortednessDegree)
			{
			case 1: 
				return array50[TypeAlmostSorted1];
			case 2:
				return array50[TypeAlmostSorted2];
			case 3:
				return array50[TypeAlmostSorted3];
			case 4:
				return array50[TypeAlmostSorted4];
			case 5:
				return array50[TypeAlmostSorted5];
			}
			return array50[TypeAlmostSorted1];
		case 100:
			switch (preSortednessDegree)
			{
			case 1: 
				return array100[TypeAlmostSorted1];
			case 2:
				return array100[TypeAlmostSorted2];
			case 3:
				return array100[TypeAlmostSorted3];
			case 4:
				return array100[TypeAlmostSorted4];
			case 5:
				return array100[TypeAlmostSorted5];
			}
			return array100[TypeAlmostSorted1];
		case 500:
			switch (preSortednessDegree)
			{
			case 1: 
				return array500[TypeAlmostSorted1];
			case 2:
				return array500[TypeAlmostSorted2];
			case 3:
				return array500[TypeAlmostSorted3];
			case 4:
				return array500[TypeAlmostSorted4];
			case 5:
				return array500[TypeAlmostSorted5];
			}
			return array500[TypeAlmostSorted1];
		case 1000:
			switch (preSortednessDegree)
			{
			case 1:
				return array1000[TypeAlmostSorted1];
			case 2:
				return array1000[TypeAlmostSorted2];
			case 3:
				return array1000[TypeAlmostSorted3];
			case 4:
				return array1000[TypeAlmostSorted4];
			case 5:
				return array1000[TypeAlmostSorted5];
			}
			return array1000[TypeAlmostSorted1];
		case 10000:
			switch (preSortednessDegree)
			{
			case 1:
				return array10000[TypeAlmostSorted1];
			case 2:
				return array10000[TypeAlmostSorted2];
			case 3:
				return array10000[TypeAlmostSorted3];
			case 4:
				return array10000[TypeAlmostSorted4];
			case 5:
				return array10000[TypeAlmostSorted5];
			}
			return array10000[TypeAlmostSorted1];
		case 100000:
			switch (preSortednessDegree)
			{
			case 1:
				return array100000[TypeAlmostSorted1];
			case 2:
				return array100000[TypeAlmostSorted2];
			case 3:
				return array100000[TypeAlmostSorted3];
			case 4:
				return array100000[TypeAlmostSorted4];
			case 5:
				return array100000[TypeAlmostSorted5];
			}
			return array100000[TypeAlmostSorted1];
		case 500000:
			switch (preSortednessDegree)
			{
			case 1:
				return array500000[TypeAlmostSorted1];
			case 2:
				return array500000[TypeAlmostSorted2];
			case 3:
				return array500000[TypeAlmostSorted3];
			case 4:
				return array500000[TypeAlmostSorted4];
			case 5:
				return array500000[TypeAlmostSorted5];
			}
			return array500000[TypeAlmostSorted1];
		case 1000000:
			switch (preSortednessDegree)
			{
			case 1:
				return array1000000[TypeAlmostSorted1];
			case 2:
				return array1000000[TypeAlmostSorted2];
			case 3:
				return array1000000[TypeAlmostSorted3];
			case 4:
				return array1000000[TypeAlmostSorted4];
			case 5:
				return array1000000[TypeAlmostSorted5];
			}
			return array1000000[TypeAlmostSorted1];
		}
		return array100[TypeAlmostSorted1];
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
