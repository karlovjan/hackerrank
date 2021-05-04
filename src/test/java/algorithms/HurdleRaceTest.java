package algorithms;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/the-hurdle-race/problem
 */
public class HurdleRaceTest {

	public static int hurdleRace(int k, List<Integer> height) {
		// Write your code here
		int maxHeight = height.stream().mapToInt(Integer::valueOf).max().orElse(0);
		int doses = maxHeight - k;
		return doses > 0 ? doses : 0;
	}

	@Test
	void hurdleRaceTest_all() {
		Assertions.assertEquals(2, hurdleRace(1, List.of(1, 2, 3, 3, 2)));
		Assertions.assertEquals(2, hurdleRace(4, List.of(1, 6, 3, 5, 2)));
		Assertions.assertEquals(0, hurdleRace(7, List.of(2, 5, 4, 5, 2)));
		Assertions.assertEquals(0, hurdleRace(1, List.of(1, 1, 1)));
	}
}
