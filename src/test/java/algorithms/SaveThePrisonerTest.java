package algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/save-the-prisoner/problem
 */
public class SaveThePrisonerTest {

	public static int saveThePrisoner_muj(int n, int m, int s) {
		int result;

		if (m > n) {
			int rest = m % n;
			if (rest == 0 && s == 1) {
				result = n;
			} else {
				result = s - 1 + rest;
			}

		} else {
			result = s - 1 + m;
		}

		return result <= n ? result : result - n;
	}

	//a solution from discussion
	public static int saveThePrisoner(int n, int m, int s) {
		return ((m - 1) + (s - 1)) % n + 1;
	}

	@Test
	void test1() {
		Assertions.assertEquals(1, saveThePrisoner(1, 1, 1));
		Assertions.assertEquals(1, saveThePrisoner(1, 100, 1));

		Assertions.assertEquals(2, saveThePrisoner(2, 2, 1));
		Assertions.assertEquals(1, saveThePrisoner(2, 2, 2));

		Assertions.assertEquals(2, saveThePrisoner(5, 2, 1));
		Assertions.assertEquals(3, saveThePrisoner(5, 2, 2));
		Assertions.assertEquals(4, saveThePrisoner(5, 2, 3));
		Assertions.assertEquals(5, saveThePrisoner(5, 2, 4));
		Assertions.assertEquals(1, saveThePrisoner(5, 2, 5));

		Assertions.assertEquals(4, saveThePrisoner(5, 10, 5));
	}

	@Test
	void test2() {
		Assertions.assertEquals(6, saveThePrisoner(7, 19, 2));
		Assertions.assertEquals(5, saveThePrisoner(7, 19, 1));
		Assertions.assertEquals(4, saveThePrisoner(7, 19, 7));
		Assertions.assertEquals(3, saveThePrisoner(3, 7, 3));
		Assertions.assertEquals(2, saveThePrisoner(3, 6, 3));
		Assertions.assertEquals(3, saveThePrisoner(3, 6, 1));
		Assertions.assertEquals(3, saveThePrisoner(3, 4, 3));
		Assertions.assertEquals(2, saveThePrisoner(3, 3, 3));
		Assertions.assertEquals(1, saveThePrisoner(3, 2, 3));
		Assertions.assertEquals(3, saveThePrisoner(3, 1, 3));
		Assertions.assertEquals(1, saveThePrisoner(3, 3, 2));
		Assertions.assertEquals(3, saveThePrisoner(3, 3, 1));
		Assertions.assertEquals(3, saveThePrisoner(3, 300, 1));
	}

	@Test
	void test3() {
		Assertions.assertEquals(1, saveThePrisoner(2, 3, 1));
		Assertions.assertEquals(2, saveThePrisoner(2, 4, 1));
		Assertions.assertEquals(1, saveThePrisoner(2, 4, 2));
		Assertions.assertEquals(2, saveThePrisoner(2, 6, 1));
		Assertions.assertEquals(1, saveThePrisoner(2, 6, 2));
		Assertions.assertEquals(2, saveThePrisoner(2, 100, 1));
		Assertions.assertEquals(1, saveThePrisoner(2, 100, 2));
	}
}
