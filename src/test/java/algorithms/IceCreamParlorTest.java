package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/icecream-parlor/problem
 * <p>
 * Two friends like to pool their money and go to the ice cream parlor.
 * They always choose two distinct flavors and they spend all of their money.
 */
public class IceCreamParlorTest {

	/*
	binary search logN

array must be sorted


	 */

	/**
	 * There will always be a unique solution.
	 *
	 * @param m   the amount of money they have pooled. m = {2,10^4}
	 * @param arr the cost of each flavor of ice cream. n = {2,10^4}, cost[i] = {1, 10^4} -> i = {1, n}
	 * @return the indices of the prices of the two flavors they buy, sorted ascending
	 */
	public static List<Integer> icecreamParlor(int m, List<Integer> arr) {

		List<Integer> result = new ArrayList<>();
		Map<Integer, Integer> costMap = new HashMap<>();
		for (int i = 1; i <= arr.size(); i++) {
			int diff = m - arr.get(i - 1);
			if (costMap.containsKey(diff)) {
				result.add(costMap.get(diff));
				result.add(i);
				break;
			} else {
				costMap.put(arr.get(i - 1), i);
			}
		}
		return result;
	}
	/*
	public static List<Integer> icecreamParlor_pokusBS(int m, List<Integer> arr) {

		int low, high, mid, summ;
		low = 0;
		high = arr.size() - 1;
		while (low < high) {
			if(getSumm(arr, low, high) == m){
				List<Integer> result = new ArrayList<>();
				result.add(low + 1);
				result.add(high + 1);
				return result;
			}

			mid = (low + high) / 2;

			if(low < mid) {
				if (getSumm(arr, low, mid) == m) {
					List<Integer> result = new ArrayList<>();
					result.add(low + 1);
					result.add(mid + 1);
					return result;
				}

				if (getSumm(arr, mid, high) == m) {
					List<Integer> result = new ArrayList<>();
					result.add(mid + 1);
					result.add(high + 1);
					return result;
				}
			}

			--high;
		}

		return new ArrayList<>();
	}

	 */

	private static int getSumm(List<Integer> arr, int i, int j) {
		return arr.get(i) + arr.get(j);
	}

	/*
	[1,2] 1 2
	[1,2,3] 1 2, 1 3, 2 3  --- 1 2, 1 3, 2 3
	[1,2,3,4] 1 2, 1 3, 1 4, 2 3, 2 4, 3 4  --- 1 4, 1 2, 2 4, 1 3, 1 2 , 2 3, 1 2
	[1,2,3,4,5] 1 2, 1 3, 1 4, 1 5, 2 3, 2 4, 2 5, 3 4, 3 5, 4 5 --- 1 5, 1 3, 3 5, 1 4, 1 2, 2 4, 1 3, 1 2, 2 3, 1 2

	[1,2,3,4,5,6,7] 1 2, 1 3, 1 4, 1 5, 2 3, 2 4, 2 5, 3 4, 3 5, 4 5 --- 1 5, 1 3, 3 5, 1 4, 1 2, 2 4, 1 3, 1 2, 2 3, 1 2

	 */

	public static List<Integer> icecreamParlor_slow(int m, List<Integer> arr) {

		for (int i = 0; i < arr.size() - 1; i++) {
			for (int j = i + 1; j < arr.size(); j++) {
				if (arr.get(i) + arr.get(j) == m) {
					List<Integer> result = new ArrayList<>();
					result.add(i + 1);
					result.add(j + 1);
					return result;
				}
			}
		}

		return new ArrayList<>();
	}

	@Test
	void test_ha() {
		Assertions.assertIterableEquals(List.of(1, 4), icecreamParlor(6, List.of(1, 3, 4, 5, 6)));
		Assertions.assertIterableEquals(List.of(1, 2), icecreamParlor(6, List.of(1, 5, 4, 10, 6)));
		Assertions.assertIterableEquals(List.of(2, 5), icecreamParlor(6, List.of(6, 5, 4, 3, 1)));
		Assertions.assertIterableEquals(List.of(1, 4), icecreamParlor(4, List.of(1, 4, 5, 3, 2)));
		Assertions.assertIterableEquals(List.of(1, 2), icecreamParlor(4, List.of(2, 2, 4, 3)));
		Assertions.assertIterableEquals(List.of(1, 2), icecreamParlor(2, List.of(1, 1)));
		Assertions.assertIterableEquals(List.of(1, 2), icecreamParlor(4, List.of(2, 2)));
	}
}
