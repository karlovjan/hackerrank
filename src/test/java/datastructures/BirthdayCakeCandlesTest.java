package datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.IntConsumer;

import org.junit.jupiter.api.Test;

/**
 * Count how many candles are tallest.
 */
class BirthdayCakeCandlesTest {

	/*
	 * Complete the 'birthdayCakeCandles' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts INTEGER_ARRAY candles as parameter.
	 */

	int birthdayCakeCandles(List<Integer> candles) {
		// Write your code here

		return candles.stream().collect(MaxCounter::new, MaxCounter::accept, MaxCounter::combine).getMaxCount();
	}

	class MaxCounter implements IntConsumer {
		private int max = 0;
		private int count = 0;

		int getMaxCount() {
			return count;
		}

		public void accept(int i) {
			if (i > max) {
				max = i;
				count = 1;
			} else if (max == i) {
				++count;
			}

		}

		void combine(MaxCounter other) {

		}

	}

	@Test
	void tallestCandlesCountTest() {

		int tallestCount = birthdayCakeCandles(List.of(4, 3, 1, 4));

		assertEquals(2, tallestCount); //dve 4

	}
}
