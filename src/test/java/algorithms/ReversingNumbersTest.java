package algorithms;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import util.UtilTest;

/**
 * https://www.hackerrank.com/challenges/beautiful-days-at-the-movies/problem
 *
 */
public class ReversingNumbersTest {

	public static int beautifulDays_ok(int i, int j, int k) {
		return (int) IntStream.range(i, j).filter(day -> UtilTest.isInteger((float)(day - UtilTest.reversDigits(day)) / k)).count();
	}

	public static int beautifulDays(int i, int j, int k) {
		return (int) IntStream.range(i, j).filter(day -> (day - UtilTest.reversDigits(day)) % k == 0).count();
	}

	@Test
	void test1() {
		Assertions.assertEquals(2, beautifulDays(20, 23, 6));
	}
}
