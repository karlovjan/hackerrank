package algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/two-arrays/problem
 */
public class PermutingTwoArraysTest {

	//list indexis to saved values
	private static final int minIndex = 0;
	private static final int minCountIndex = 1;
	private static final int maxIndex = 2;
	private static final int sumIndex = 3;

	static String twoArrays(int k, List<Integer> A, List<Integer> B) {

		List<Integer> a_stat = getListItemsStats(A);
		List<Integer> b_stat = getListItemsStats(B);

		int sumAll = a_stat.get(sumIndex) + b_stat.get(sumIndex);

		if (sumAll < k * A.size()) {
			return "NO";
		}

		if (a_stat.get(minIndex) < k && b_stat.get(minIndex) < k
				&& a_stat.get(minCountIndex) + b_stat.get(minCountIndex) > A.size()) {
			return "NO";
		}

		return "YES";
	}

	private static List<Integer> getListItemsStats(List<Integer> list) {
		return list.stream().collect(ArrayList::new, (resList, item) -> {
			if (resList.isEmpty()) {
				resList.add(0); // min value
				resList.add(0); // min count
				resList.add(0); // max
				resList.add(0); // sum
			}

			if (item > resList.get(maxIndex)) {
				resList.set(maxIndex, item);
			}

			if (item < resList.get(minIndex)) {
				resList.set(minIndex, item);
				//reset min count
				resList.set(minCountIndex, 1);
			}

			if (item.equals(resList.get(minIndex))) {
				int count = resList.get(minCountIndex);
				//increment min count
				resList.set(minCountIndex, ++count);
			}

			resList.set(sumIndex, resList.get(sumIndex) + item);

		}, (resList1, resList2) -> {
			// merge two sub results -- combine second list to first result list
			if (resList1.get(minIndex) > resList2.get(minIndex)) {
				resList1.set(minIndex, resList2.get(minIndex));
				resList1.set(minCountIndex, resList2.get(minCountIndex));
			}
			if (resList1.get(maxIndex) < resList2.get(maxIndex)) {
				resList1.set(maxIndex, resList2.get(maxIndex));
			}
			resList1.set(sumIndex, resList1.get(sumIndex) + resList2.get(sumIndex));
		});
	}

	@Test
	void test1() {

		Assertions.assertEquals("NO", twoArrays(1, List.of(0), List.of(0)));
		Assertions.assertEquals("YES", twoArrays(1, List.of(0), List.of(1)));
		Assertions.assertEquals("YES", twoArrays(1, List.of(1), List.of(1)));
		Assertions.assertEquals("NO", twoArrays(1, List.of(0, 0, 0, 0), List.of(0, 0, 0, 1)));

	}

	@Test
	void test2() {

		Assertions.assertEquals("YES", twoArrays(1, List.of(1, 1, 1, 1), List.of(0, 0, 0, 0)));
		Assertions.assertEquals("YES", twoArrays(1, List.of(10, 0, 0, 0), List.of(0, 1, 1, 1)));
		Assertions.assertEquals("NO", twoArrays(1, List.of(10, 0, 0, 0), List.of(0, 0, 0, 1)));
		Assertions.assertEquals("NO", twoArrays(1, List.of(10, 0, 0, 0), List.of(0, 0, 10, 1)));
		Assertions.assertEquals("YES", twoArrays(1, List.of(10, 0), List.of(0, 1)));
		Assertions.assertEquals("YES", twoArrays(1, List.of(10, 0), List.of(1, 0)));
		Assertions.assertEquals("NO", twoArrays(1, List.of(10, 0), List.of(0, 0)));
	}
}
