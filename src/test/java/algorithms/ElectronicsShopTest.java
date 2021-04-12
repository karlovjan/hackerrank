package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import util.StreamUtil;

/*
A person wants to determine the most expensive computer keyboard and USB drive that can be purchased with a give budget.
Given price lists for keyboards and USB drives and a budget, find the cost to buy them.
If it is not possible to buy both items, return -1.
 */
public class ElectronicsShopTest {

	/**
	 * @param keyboards
	 * @param drives
	 * @param b         budget
	 * @return If it is not possible to buy both items, return -1, otherwise return the sum of keyboard and drive
	 */
	int getMoneySpent(int[] keyboards, int[] drives, int b) {
		//		int[][] kd = new int[][]{keyboards, drives};

		return Arrays.stream(keyboards).flatMap(k -> Arrays.stream(drives).map(d1 -> k + d1)).filter(i -> i <= b).max()
				.orElse(-1);
	}

	int getMoneySpentNew(int[] keyboards, int[] drives, int b) {

		return StreamUtil.mergetIntArrays(keyboards, drives, (k, d) -> k + d).filter(i -> i <= b).max().orElse(-1);
	}

	@Test
	void getMoneySpentTest() {
		assertEquals(58, getMoneySpent(new int[] { 40, 50, 60 }, new int[] { 5, 8, 12 }, 60));
		assertEquals(9, getMoneySpent(new int[] { 3, 1 }, new int[] { 5, 2, 8 }, 10));
		assertEquals(-1, getMoneySpent(new int[] { 4 }, new int[] { 5 }, 5));
	}

	@Test
	void getMoneySpentNewTest() {
		assertEquals(58, getMoneySpentNew(new int[] { 40, 50, 60 }, new int[] { 5, 8, 12 }, 60));
		assertEquals(9, getMoneySpentNew(new int[] { 3, 1 }, new int[] { 5, 2, 8 }, 10));
		assertEquals(-1, getMoneySpentNew(new int[] { 4 }, new int[] { 5 }, 5));
	}
}
