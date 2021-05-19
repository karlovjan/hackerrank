package algorithms;

import java.math.BigInteger;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * https://www.hackerrank.com/challenges/extra-long-factorials/problem
 *
 *  Factorials of n > 20 can't be stored even in a 64 - bit long long variable. Big integers must be used for such calculations.
 *
 * https://onlinemschool.com/math/formula/factorial_table/
 *
 */
public class ExtraLongFactorialsTest {

	/**
	 * n! = n x (n - 1) x (n - 2) x ... X 3 x 2 x 1
	 * @param n 1 <= n <= 100
	 * @return Get the factorial of n.
	 */
	public static BigInteger factorial(int n) {

		BigInteger result = BigInteger.valueOf(n);

		while (n > 1){

			result = result.multiply(BigInteger.valueOf(--n));
		}

		return result;
	}

	public static BigInteger factorial2(int n) {

		return IntStream.rangeClosed(1, n).mapToObj(BigInteger::valueOf).reduce(BigInteger.ONE, BigInteger::multiply);
	}

	public static void extraLongFactorials(int n) {

		System.out.println(factorial(n));
	}

	@Test
	void test0() {
		Assertions.assertEquals(BigInteger.ONE, BigInteger.ONE);
	}

	@Test
	void test1() {
		Assertions.assertEquals(new BigInteger("15511210043330985984000000"), factorial(25));
	}

	@Test
	void test2() {
		Assertions.assertEquals(new BigInteger("2432902008176640000"), factorial(20));
	}

	@Test
	void test3() {
		Assertions.assertEquals(new BigInteger("15511210043330985984000000"), factorial2(25));
	}

	@Test
	void test4() {
		Assertions.assertEquals(new BigInteger("2432902008176640000"), factorial2(20));
	}

	@Test
	void test5() {
		long startTime = System.currentTimeMillis();

		Assertions.assertEquals(new BigInteger("815915283247897734345611269596115894272000000000"), factorial(40));

		long endTime = System.currentTimeMillis();

		long duration = endTime - startTime;

		System.out.println("Test 5: " + duration);
	}

	@Test
	void test6() {
		long startTime = System.currentTimeMillis();

		Assertions.assertEquals(new BigInteger("815915283247897734345611269596115894272000000000"), factorial2(40));

		long endTime = System.currentTimeMillis();

		long duration = endTime - startTime;

		System.out.println("Test 6: " + duration);
	}
}
