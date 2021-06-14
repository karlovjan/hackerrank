package algorithms.binarySearch;

/**
 * https://www.hackerrank.com/challenges/icecream-parlor/topics
 * <p>
 * Binary search is the most prominent example of a Divide and Conquer algorithm.
 * Suppose you are given an array of size N in sorted order and you have to return the index of an element.
 * <p>
 * initialize low = 0, high = N, mid = (low + high)/2
 * check if element is at position mid; if found, return
 * else depending upon value of mid is lower or greater,
 * we explore the right side of the array or the left side
 */
public class DivideAndConquer {

	int binary_search_recursive(int[] arr, int key, int imin, int imax) {
		// test if array is empty
		if (imax < imin)
			// set is empty, so return value showing not found
			return -1;
		else {
			// calculate midpoint to cut set in half
			int imid = (imin + imax) / 2;

			// three-way comparison
			if (arr[imid] > key)
				// key is in lower subset
				return binary_search_recursive(arr, key, imin, imid - 1);
			else if (arr[imid] < key)
				// key is in upper subset
				return binary_search_recursive(arr, key, imid + 1, imax);
			else
				// key has been found
				return imid;
		}
	}

	int binary_search(int arr[], int N, int key) {

		// considering 1 based index
		int low, high, mid;
		low = 1;
		high = N;
		while (low <= high) {
			mid = (low + high) / 2;
			if (arr[mid] == key) {
				return mid; // a match is found
			} else if (arr[mid] < key) { // if middle element is less than the key
				low = mid + 1;     // key will be right part if it exists
			} else {
				high = mid - 1;    // otherwise key will be in left part if it exists
			}
		}
		return -1; // indicating no such key exists
	}

	/*
	Common Variations of BinarySearch:
	LowerBound: find an element X in a sorted array of intergers such that X >= K, where key K is given.
	UpperBound: find an element X in a sorted array of intergers such that X > K, where key K is given.

	 */

	//UpperBound Implementation:
	int upperBoundBS(int arr[], int N, int K) {
		int low, high, mid = -1;
		low = 1;
		high = N;
		while (low <= high) {
			mid = (low + high) / 2; // finding middle element
			if (arr[mid] > K && (mid == 1 || arr[mid - 1] <= K)) // checking conditions for upperbound
				return mid;
			else if (arr[mid] > K) // answer should be in left part
				high = mid - 1;
			else                // answer should in right part if it exists
				low = mid + 1;
		}
		return mid; // this will execute when there is no element in the given array which > K
	}

	//LowerBound Implementation:
	int lowerBoundBS(int arr[], int N, int K) {
		int low, high, mid = -1;
		low = 1;
		high = N;
		while (low <= high) {
			mid = (low + high) / 2; // finding middle element
			if (arr[mid] >= K && (mid == 1 || arr[mid - 1] < K)) // checking conditions for lowerbound
				return mid;
			else if (arr[mid] >= K) // answer should be in left part
				high = mid - 1;
			else                // answer should in right part if it exists
				low = mid + 1;
		}
		return mid; // this will execute when there is no element in the given array which >= K
	}
}
