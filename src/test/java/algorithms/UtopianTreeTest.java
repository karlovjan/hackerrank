package algorithms;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The Utopian Tree goes through 2 cycles of growth every year. Each spring, it doubles in height. Each summer, its height increases by 1 meter.
 * https://www.hackerrank.com/challenges/utopian-tree/problem
 */
public class UtopianTreeTest {

	/**
	 * @param n the number of growth cycles to simulate
	 * @return the height of the tree after the given number of cycles
	 */
	public static int utopianTree(int n) {
		return IntStream.rangeClosed(0, n).reduce(0, (subValue, i) -> i % 2 == 0 ? ++subValue : subValue * 2);

	}

	@Test
	void test1() {

		Assertions.assertEquals(1, utopianTree(0));
		Assertions.assertEquals(2, utopianTree(1));
		Assertions.assertEquals(7, utopianTree(4));
	}

}
