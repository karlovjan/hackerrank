package algorithms;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/flatland-space-stations/problem
 * <p>
 * Flatland is a country with a number of cities, some of which have space stations.
 * Cities are numbered consecutively and each has a road of 1km length connecting it to the next city.
 * It is not a circular route, so the first city doesn't connect with the last city.
 * Determine the maximum distance from any city to its nearest space station.
 */
public class FlatlandSpaceStationsTest {

	/**
	 * @param n the number of cities {1,10^5}
	 * @param c the indices of cities with a space station {1, n}
	 * @return the maximum distance any city is from a space station
	 */
	static int flatlandSpaceStations(int n, int[] c) {

		if (n == c.length) {
			return 0;
		}

		Arrays.sort(c);
		int max = 0;
		int start = 0;

		int station;
		for (int j = 0; j < c.length; j++) {
			station = c[j];
			max = Math.max(max, j > 0 ? (station - start) / 2 : station);

			start = station;
		}

		return Math.max(max, n - 1 - c[c.length - 1]);
	}

	@Test
	void test1() {

		Assertions.assertEquals(6, flatlandSpaceStations(20, new int[] { 13, 1, 11, 10, 6 })); // 0 0, 1, 2, 3 4, 4 4
		Assertions.assertEquals(2, flatlandSpaceStations(5, new int[] { 0, 4 })); // 0 0, 1, 2, 3 4, 4 4
		Assertions.assertEquals(1, flatlandSpaceStations(5, new int[] { 0, 3 })); //
		Assertions.assertEquals(1, flatlandSpaceStations(3, new int[] { 1 }));
	}

	@Test
	void test2() {
		// 0 - 1 - 2 - 3
		Assertions.assertEquals(3, flatlandSpaceStations(4, new int[] { 0 }));
		Assertions.assertEquals(2, flatlandSpaceStations(4, new int[] { 1 }));
		Assertions.assertEquals(2, flatlandSpaceStations(4, new int[] { 2 }));
		Assertions.assertEquals(3, flatlandSpaceStations(4, new int[] { 3 }));
	}

	@Test
	void test3() {
		Assertions.assertEquals(0, flatlandSpaceStations(1, new int[] { 0 }));
		Assertions.assertEquals(0, flatlandSpaceStations(6, new int[] { 0, 1, 2, 3, 4, 5 }));
	}

	@Test
	void test4() {
		Assertions.assertEquals(1, flatlandSpaceStations(6, new int[] { 0, 1, 2, 3, 4 }));
		Assertions.assertEquals(1, flatlandSpaceStations(6, new int[] { 0, 2, 4 }));
	}
}
