package thirtycodingdays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class TimeAndComplexityProblemTest {
	/*
	If possible, try to come up with a O(n^1/2) == O(odmocnina ze 2) primality algorithm,
	or see what sort of optimizations you come up with for an O(n) algorithm.
	Be sure to check out the Editorial after submitting your code!

	Example in InterfaceDivisorTest
	and in tutorial
	https://www.hackerrank.com/challenges/30-running-time-and-complexity/tutorial


	 */

	/*
	long startTime = System.currentTimeMillis();
	long endTime = System.currentTimeMillis();

	long duration = endTime - startTime;
	 */

	/*
	The term  time, or "constant time", is used to refer to fundamental operations that take a constant amount of time
	to execute (e.g., reading a single value, performing a comparison between two values, checking a condition, etc.).

	Logarithminc time


	Linear O(n)

	quadratic O(n^2)

	 */

	/**
	 * Prime number is not Slozene cislo
	 *
	 * @param a testing number
	 * @return Prime or Not prime number
	 */
	static boolean isPrimeNumber(int a) {
		//1 + 1 + (1 + n/2 + 1 + n/2) == O(n) - nejhorsi pripad
		if (a == 0 || a == 1) {
			return false; //one is not a prime number
		}

		int max = a / 2; //deleno prvnim prvocislem
		//			n = 2 prvni prvocislo
		for (int n = 2; n <= max; n++) {
			if (a % n == 0) {
				return false; //jedna se o slozene cislo a tedy neni to prvocislo
			}
		}

		return true; //prvocislo - cislo 'a' neni delitelne zadnim jinym cislem nez jednickou a sebou samym
	}

	static boolean isPrimeNumberV2(int a) {
		//cil je O(sqrt(n))
		//
		if (a == 0 || a == 1) {
			return false; //one is not a prime number
		}

		//5^2 = 25
		//25 square root 2 = 5
		//Math.sqrt(a)
		int sqrt = (int) Math.sqrt(a);

		for (int n = 2; n <= sqrt; n++) {
			if (a % n == 0) {
				return false; //jedna se o slozene cislo a tedy neni to prvocislo
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int count;
		int[] numbers;

		try (Scanner sc = new Scanner(System.in)) {
			count = sc.nextInt();
			numbers = new int[count];

			for (int i = 0; i < count; i++) {
				numbers[i] = sc.nextInt();
			}

			/*
			while (count-- > 0) {
				numbers[count] = sc.nextInt();
			} dekrementuje v kazdem kroku a initializuje pole od zadu! To tady nepotrebuju
			 */
		}

		for (int n : numbers) {
			if (isPrimeNumber(n)) {
				System.out.println("Prime");
			} else {
				System.out.println("Not prime");
			}
		}

	}

	@Test
	void isPrimeNumberTest() {

		assertFalse(isPrimeNumber(0));
		assertFalse(isPrimeNumber(1));
		assertTrue(isPrimeNumber(2));
		assertTrue(isPrimeNumber(3));
		assertFalse(isPrimeNumber(4));
		assertTrue(isPrimeNumber(5));
		assertTrue(isPrimeNumber(7));
		assertTrue(isPrimeNumber(11));
		assertTrue(isPrimeNumber(13));
		assertFalse(isPrimeNumber(121));
	}

	@Test
	void isPrimeNumberV2Test() {

		assertFalse(isPrimeNumberV2(0));
		assertFalse(isPrimeNumberV2(1));
		assertTrue(isPrimeNumberV2(2));
		assertTrue(isPrimeNumberV2(3));
		assertFalse(isPrimeNumberV2(4));
		assertTrue(isPrimeNumberV2(5));
		assertTrue(isPrimeNumberV2(7));
		assertTrue(isPrimeNumberV2(11));
		assertTrue(isPrimeNumberV2(13));
		assertFalse(isPrimeNumberV2(121));
	}
}
