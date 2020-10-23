package thirtycodingdays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/30-binary-numbers/tutorial
 */
class BinaryNumbersTest {

	/**
	 * @param n base-10 integer from 1 to 1000000
	 * @return max consecutive 1's
	 */
	private static int getMaxNumberOfConsecutiveOnesInBinaryNumber(int n) {
		if (1 <= n && n <= 1000000) {

			return Arrays.stream(Integer.toBinaryString(n).split("0")).mapToInt(String::length).max().orElse(0);
		}

		return 0;
	}

	@Test
	void maxConsecutiveTest() {
		assertEquals(0, getMaxNumberOfConsecutiveOnesInBinaryNumber(0));
		assertEquals(0, getMaxNumberOfConsecutiveOnesInBinaryNumber(1000001));
		assertEquals(1, getMaxNumberOfConsecutiveOnesInBinaryNumber(5));
		assertEquals(2, getMaxNumberOfConsecutiveOnesInBinaryNumber(13));
	}

	@Test
	void maxConsecutiveMaxTest() {
		assertEquals(4, getMaxNumberOfConsecutiveOnesInBinaryNumber(1000000));
	}
}
