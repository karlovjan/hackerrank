package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * Picking Numbers
 * https://www.hackerrank.com/challenges/picking-numbers/problem
 * Given an array of integers, find the longest subarray where the absolute difference between any two elements is less than or equal to 1.
 */
public class PickingNumbersTest {

	/**
	 * Given an array of integers, find the longest subarray where the absolute difference between any two elements is less than or equal to 1.
	 *
	 * @param a an array of integers
	 * @return the length of the longest subarray that meets the criterion
	 */
	public int pickingNumbers(List<Integer> a) {

		if (a.size() < 2) {
			return 0;
		}

		Map<Integer, Long> resultSubArr = a.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		long max = 0;
		int lastKey = 0;
		long lastLen = 0;

		for (Map.Entry<Integer, Long> entry : resultSubArr.entrySet()) {
			if (lastKey + 1 == entry.getKey()) {
				max = Math.max(max, lastLen + entry.getValue());
			} else {
				max = Math.max(max, entry.getValue());
			}

			lastLen = entry.getValue();
			lastKey = entry.getKey();

		}

		return (int) max;
	}

	/*
	//Counting Sort
	public int pickingNumbers(List<Integer> a) {
		int frequency[] = new int[101];
		int result = Integer.MIN_VALUE;

		for (int i = 0; i < a.size(); i++) {
			int index=a.get(i);
			frequency[index]++; //frequency[index]=frequency[index]+1
		}

		for (int i = 1; i <= 100; i++) {
			result = Math.max(result, frequency[i] + frequency[i - 1]);
		}
		return result;

	}

	 */

	@Test
	void pickingNumbersTest_allSame() {

		assertEquals(0, pickingNumbers(List.of(1)));
		assertEquals(2, pickingNumbers(List.of(1, 1)));
		assertEquals(3, pickingNumbers(List.of(1, 1, 1)));
		assertEquals(3, pickingNumbers(List.of(4, 4, 4)));
	}

	@Test
	void pickingNumbersTest_allDiffer() {

		assertEquals(1, pickingNumbers(List.of(1, 3)));
		assertEquals(1, pickingNumbers(List.of(1, 4, 6, 8, 10)));
		assertEquals(1, pickingNumbers(List.of(4, 6, 8, 10)));
	}

	@Test
	void pickingNumbersTest_oneLongSubarray() {

		assertEquals(2, pickingNumbers(List.of(1, 2)));
		assertEquals(4, pickingNumbers(List.of(1, 1, 2, 2)));
		assertEquals(6, pickingNumbers(List.of(1, 1, 1, 2, 2, 2)));
		assertEquals(12, pickingNumbers(List.of(1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2)));
		assertEquals(5, pickingNumbers(List.of(4, 4, 5, 5, 5)));
	}

	@Test
	void pickingNumbersTest_moreSubarrayes() {

		assertEquals(2, pickingNumbers(List.of(1, 2)));
		assertEquals(3, pickingNumbers(List.of(1, 3, 2, 2)));
		assertEquals(2, pickingNumbers(List.of(1, 4, 4, 2)));
		assertEquals(5, pickingNumbers(List.of(1, 1, 4, 3, 3, 2, 5, 4, 4)));
		assertEquals(8, pickingNumbers(List.of(1, 3, 1, 3, 2, 3, 1, 3, 1, 2, 2, 2)));
		assertEquals(5, pickingNumbers(List.of(1, 1, 2, 2, 4, 4, 5, 5, 5)));
		assertEquals(5, pickingNumbers(List.of(3, 4, 4, 5, 5, 5)));

		assertEquals(5, pickingNumbers(List.of(1, 2, 2, 3, 1, 2)));

		assertEquals(2, pickingNumbers(List.of(98, 3, 99, 1, 97, 2)));
	}
}
