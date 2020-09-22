package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinMaxSumTest {

	//constaints:
	//A single line of five space-separated integers.

	@Test
	void minMaxSum() {

		int[] arr = new int[] { 1, 2, 3, 4, 5 };

		long[] result = minMaxSumImpl2(arr);

		assertEquals(10, result[0], "min");
		assertEquals(14, result[1], "max");
	}

	@Test
	void minMaxSum_long() {

		int[] arr = new int[] { 0, 1, 2, 999999988, 888888888 };

		long[] result = minMaxSumImpl2(arr);

		assertEquals(888888891, result[0], "min");
		assertEquals(1888888879, result[1], "max");
	}

	@Test
	void minMaxSum_ones() {

		int[] arr = new int[] { 1, 1, 1, 1, 1 };

		long[] result = minMaxSumImpl2(arr);

		assertEquals(4, result[0], "min");
		assertEquals(4, result[1], "max");
	}

	//failed one test case in hackerrank
	long[] minMaxSumImpl(int[] arr) {

		//		int[] arr = new int[] {1, 2, 3, 4, 5};
		long min = 0;
		long max = 0;

		long sum;

		for (int i = 0; i < arr.length; i++) {
			//eliminuj vzdy jeden prvek z pole
			sum = 0;
			for (int j = 0; j < arr.length; j++) {
				//sumuj prvky pole bez jednoho

				//skip jedne prvek ostatni sumuj
				if (i != j) {
					sum += arr[j];
				}
			}

			//is sum max or min?
			if (sum > max) {
				max = sum;
			} else if (sum < min || min == 0) {
				min = sum;
			}
		}

		return new long[] { min, max };
	}

	long[] minMaxSumImpl2(int[] arr) {
		long minSum = 0;
		long maxSum = 0;

		int minArrItemIndex = 0;
		int maxArrItemIndex = 0;

		int maxItemTmp = 0;
		int minItemTmp = 0;

		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				//				init
				maxItemTmp = arr[i];
				minItemTmp = arr[i];
			}
			if (arr[i] > maxItemTmp) {
				maxItemTmp = arr[i];
				maxArrItemIndex = i;
			} else if (arr[i] < minItemTmp) {
				minItemTmp = arr[i];
				minArrItemIndex = i;
			}
		}

		//min ziskam kdyz odeberu z pole max value
		//max ziskam kdyz odeberu z pole min value
		for (int i = 0; i < arr.length; i++) {
			if (i != maxArrItemIndex) {
				minSum += arr[i];
			}
			if (i != minArrItemIndex) {
				maxSum += arr[i];
			}
		}

		return new long[] { minSum, maxSum };
	}

}
