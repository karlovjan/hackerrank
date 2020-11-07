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


	/*
	Day 29 - Bitwise AND (&)

	Task
	Given set S = {1,2,3,4,5,... N}. Find two integers, A and B (where A < B), from set S such that the value of A&B
	is the maximum possible and also less than a given integer, K. In this case, & represents the bitwise AND operator.

	int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);
        }

        scanner.close();


Constraints:
T -> {1,10000}
N -> {2, 1000}
K -> {2, N}

	 */

	private int getBitwiseANDMax(int n, int k) {
		int bitwiseANDMax = 0;
		int bitwiseAND;
		for (int i = 1; i <= n - 1; i++) {
			for (int j = i + 1; j <= n; j++) {
				bitwiseAND = i & j;
				if(bitwiseAND < k && bitwiseAND > bitwiseANDMax){
					bitwiseANDMax = bitwiseAND;
				}
			}
		}
		return bitwiseANDMax;
	}

	/*
	Test na hacker ranku neprosel, pravidlo nefinguje vzdy
	 */
	private int getBitwiseANDMax_heuristic(int n, int k) {
		return n == k ? k - 2 : k - 1;
	}

	@Test
	void getBitwiseANDMaxTest_taskExample() {
		assertEquals(1, getBitwiseANDMax(5, 2));
		assertEquals(4, getBitwiseANDMax(8, 5));
		assertEquals(0, getBitwiseANDMax(2, 2));
	}

	@Test
	void getBitwiseANDMaxTest_spec() {
		assertEquals(1, getBitwiseANDMax(3, 2));
		assertEquals(1, getBitwiseANDMax(1000, 2));
		assertEquals(998, getBitwiseANDMax(1000, 1000)); //max dvojice a < b -> 998 & 999 = 998
	}

	@Test
	void getBitwiseANDMaxTest_halfTreshhold() {
		assertEquals(498, getBitwiseANDMax(500, 500)); //max dvojice a < b -> 498 & 499 = 498
	}

	@Test
	void getBitwiseANDMaxHeurissticTest() {
		assertEquals(1, getBitwiseANDMax_heuristic(5, 2));
		assertEquals(4, getBitwiseANDMax_heuristic(8, 5));
		assertEquals(0, getBitwiseANDMax_heuristic(2, 2));

		assertEquals(1, getBitwiseANDMax_heuristic(3, 2));
		assertEquals(1, getBitwiseANDMax_heuristic(1000, 2));
		assertEquals(998, getBitwiseANDMax_heuristic(1000, 1000)); //max dvojice a < b -> 998 & 999 = 998

		assertEquals(498, getBitwiseANDMax_heuristic(500, 500)); //max dvojice a < b -> 498 & 499 = 498
	}
}
