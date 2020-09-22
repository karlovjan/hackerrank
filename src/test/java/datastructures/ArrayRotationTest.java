package datastructures;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ArrayRotationTest {

	private static final List<Integer> testList = List.of(1, 2, 3, 4, 5);

	private List<Integer> rotateLeft(int d, List<Integer> arr) {

		//constaints: 1<=d<=arr.size
		List<Integer> resultList = new ArrayList<>();

		if (arr.size() == d) {
			//pole po rotaci zustanou na stejnem miste, pokud je d rovno nebo nasobkem arr.size
			return arr;
		}

		/*
		1-> [1,2] -> [2,1]
		1-> [1,2,3] -> [2,3,1]
		1-> [1,2,3,4] -> [2,3,4,1]

		2-> [1,2,3] -> [3,1,2]
		2-> [1,2,3,4] -> [3,4,1,2]
		2-> [1,2,3,4,5] -> [3,4,5,1,2]
		3-> [1,2,3,4,5] -> [4,5,1,2,3]
		4-> [1,2,3,4,5] -> [5,1,2,3,4]


		 */

		List<Integer> headList = arr.subList(d, arr.size());
		List<Integer> endList = arr.subList(0, d);

		resultList.addAll(headList);
		resultList.addAll(endList);

		return resultList;
	}

	@Test
	void rotateLeftTest() {

		assertArrayEquals(new int[] { 2, 3, 4, 5, 1 }, rotateLeft(1, testList).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 3, 4, 5, 1, 2 }, rotateLeft(2, testList).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 4, 5, 1, 2, 3 }, rotateLeft(3, testList).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 5, 1, 2, 3, 4 }, rotateLeft(4, testList).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, rotateLeft(5, testList).stream().mapToInt(i -> i).toArray());
	}
}
