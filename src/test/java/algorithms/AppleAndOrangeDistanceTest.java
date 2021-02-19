package algorithms;

//https://www.hackerrank.com/challenges/apple-and-orange/problem?h_r=next-challenge&h_v=zen

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AppleAndOrangeDistanceTest {

	/**
	 * a < s < t < b
	 * @param s starting point of Sam's house location.
	 * @param t ending point of Sam's house location.
	 * @param a an apple tree location on x axis
	 * @param b an orange tree location on x axis
	 * @param apples distances at which each apple falls from the tree.
	 * @param oranges distances at which each orange falls from the tree.
	 * @return apple and orange count on Sam's house
	 */
	static int[] countApplesAndOranges(int s, int t, int a, int b, int[] apples, int[] oranges) {


		int applesOnSamHouse = 0;
		int orangesOnSamHouse = 0;

		if(a < s && s < t && t < b) {


			if(apples != null && apples.length != 0) {
				applesOnSamHouse = countFruits(s, t, a, apples);
			}

			if(oranges != null && oranges.length != 0) {
				orangesOnSamHouse = countFruits(s, t, b, oranges);
			}


		}

		return new int[] {applesOnSamHouse, orangesOnSamHouse};

//		System.out.println(applesOnSamHouse);
		//		System.out.println(orangesOnSamHouse);
	}

	private static int countFruits(int houseStartX, int houseEndX, int treeX, int[] distances) {
		int fruitOnHouseCount = 0;
		int distanceFromTree;

		for (int distance : distances) {
			distanceFromTree = treeX + distance;
			if (distanceFromTree <= houseEndX && distanceFromTree >= houseStartX) {
				++fruitOnHouseCount;
			}
		}
		return fruitOnHouseCount;
	}

	@Test
	void countApplesAndOrangesTest() {

		assertArrayEquals(new int[] {0, 0}, countApplesAndOranges(10, 10, 20, 30, new int[0], new int[0]));
		assertArrayEquals(new int[] {0, 0}, countApplesAndOranges(10, 10, 20, 30, null, null));
		assertArrayEquals(new int[] {0, 0}, countApplesAndOranges(10, 40, 0, 30, new int[0], new int[0]));
		assertArrayEquals(new int[] {0, 0}, countApplesAndOranges(10, 40, 0, 30, new int[]{-1}, null));
		assertArrayEquals(new int[] {0, 0}, countApplesAndOranges(10, 40, 0, 30, new int[]{-1}, new int[0]));
		assertArrayEquals(new int[] {0, 0}, countApplesAndOranges(10, 40, 0, 30, new int[0], new int[]{1}));
		assertArrayEquals(new int[] {1, 1}, countApplesAndOranges(7, 11, 5, 15, new int[]{-2,2,1}, new int[]{5,-6}));
	}
}
