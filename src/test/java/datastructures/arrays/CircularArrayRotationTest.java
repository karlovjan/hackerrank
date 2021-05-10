package datastructures.arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/circular-array-rotation/problem
 *
 */
public class CircularArrayRotationTest {

	/**
	 *
	 * @param a  the array to rotate
	 * @param k  the rotation count
	 * @param queries the indices to report
	 * @return the values in the rotated  a as requested in queries
	 */
	public static List<Integer> circularArrayRotation(List<Integer> a, int k, List<Integer> queries) {
		return queries.stream().map(q -> rotateArrayIndexToRight(a, k, q)).collect(Collectors.toList());
	}

	private static int rotateArrayIndexToRight(List<Integer> a, int k, int i){
		if(k >= a.size()){
			int move = k % a.size();
			int diff = i - move;
			return diff >= 0 ? a.get(diff) : a.get(a.size() + diff);
		} else {
			int move = i - k;
			return move >= 0 ? a.get(move) : a.get(a.size() + move);
		}
	}

	@Test
	void test1() {
		//pocet rotaci je stejny nebo vetsi nez velikost pole
		assertIterableEquals(List.of(1), circularArrayRotation(List.of(1,2), 2, List.of(0)));
		assertIterableEquals(List.of(2), circularArrayRotation(List.of(1,2), 3, List.of(0)));
		assertIterableEquals(List.of(1), circularArrayRotation(List.of(1,2), 100, List.of(0)));
		assertIterableEquals(List.of(1), circularArrayRotation(List.of(1,2,3), 3, List.of(0)));
		assertIterableEquals(List.of(3), circularArrayRotation(List.of(1,2,3), 4, List.of(0)));
		assertIterableEquals(List.of(2), circularArrayRotation(List.of(1,2,3), 5, List.of(0)));
		assertIterableEquals(List.of(1), circularArrayRotation(List.of(1,2,3), 6, List.of(0)));

		//pole ma velikost vetsi nez pocet rotaci
		assertIterableEquals(List.of(5, 3), circularArrayRotation(List.of(3,4,5), 2, List.of(1,2)));
		assertIterableEquals(List.of(2,3, 1), circularArrayRotation(List.of(1,2,3), 2, List.of(0,1,2)));
		assertIterableEquals(List.of(10), circularArrayRotation(List.of(1,2,3,4,5,6,7,8,9,10), 1, List.of(0)));
		assertIterableEquals(List.of(9), circularArrayRotation(List.of(1,2,3,4,5,6,7,8,9,10), 2, List.of(0)));
		assertIterableEquals(List.of(8), circularArrayRotation(List.of(1,2,3,4,5,6,7,8,9,10), 3, List.of(0)));
		assertIterableEquals(List.of(1), circularArrayRotation(List.of(1,2,3,4,5,6,7,8,9,10), 1, List.of(1)));
		assertIterableEquals(List.of(10), circularArrayRotation(List.of(1,2,3,4,5,6,7,8,9,10), 2, List.of(1)));
		assertIterableEquals(List.of(9), circularArrayRotation(List.of(1,2,3,4,5,6,7,8,9,10), 3, List.of(1)));

		assertIterableEquals(List.of(1), circularArrayRotation(List.of(1,2,3,4,5,6,7,8,9,10), 5, List.of(5)));
	}
}
