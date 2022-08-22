package datastructures.arrays;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/one-week-preparation-kit-lonely-integer/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=one-week-preparation-kit&playlist_slugs%5B%5D=one-week-day-two
 */
public class LonelyIntegerTest {

	/**
	 * Given an array of integers, where all elements but one occur twice, find the unique element.
	 *
	 * @param a a list of integers - n {1, 100}, It is guaranteed that n is an odd number and that there is one unique element.
	 * @return the element that occurs only once
	 */
	public static int lonelyInteger(List<Integer> a) {

//		return byGrouping(a);
//		return byBitwiseXOROperator(a);
		return byMath(a);
	}

	private static int byGrouping(List<Integer> a) {
		return a.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(entry -> entry.getValue() == 1).findFirst().get().getKey().intValue();
	}

	/**
	 * My mathematical approach:
	 * If u take every unique number in the array and sum it,
	 * then double the sum, u will have also doubled the lonely number.
	 * So when you then subtract the sum of the array from it you will be left with the only number that has not been twice in the array.
	 */
	private static int byMath(List<Integer> a) {
		return a.stream().mapToInt(Integer::intValue).reduce(0, (left, right) -> right - left);
	}

	private static int byBitwiseXOROperator(List<Integer> a) {
		int x = 0;

		for(int iter : a){
			x ^= iter;
		}

		return x;
	}

	@Test
	void test_hackerrank() {
		Assertions.assertEquals(4, lonelyInteger(List.of(1, 2, 3, 4, 3, 2, 1)));
		Assertions.assertEquals(3, lonelyInteger(List.of(1, 1, 2, 2, 3))); //
		Assertions.assertEquals(100, lonelyInteger(List.of(1, 1, 100)));
		Assertions.assertEquals(2, lonelyInteger(List.of(1, 1, 2)));
	}
}
