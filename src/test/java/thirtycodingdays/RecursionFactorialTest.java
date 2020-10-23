package thirtycodingdays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Write a factorial function that takes a positive integer,  as a parameter and prints the result of  ( factorial).
 */
class RecursionFactorialTest {

	private static int factorial(int n) {

		if (n <= 1) {
			return 1;
		}

		return n * factorial(n - 1);
	}

	@Test
	void factorialTest() {
		assertEquals(6, factorial(3)); //3*2*1
		assertEquals(1, factorial(1));
		assertEquals(1, factorial(-1));
		assertEquals(479001600, factorial(12)); //12*11*10*9*8*7*6*5*4*3*2*1
	}
}
