package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/counting-valleys/problem
 * <p>
 * An avid hiker keeps meticulous records of their hikes. During the last hike that took exactly  steps,
 * for every step it was noted if it was an uphill, U, or a downhill, D step.
 * Hikes always start and end at sea level, and each step up or down represents a  unit change in altitude.
 * A mountain is a sequence of consecutive steps above sea level, starting with a step up from sea level and ending with a step down to sea level.
 * A valley is a sequence of consecutive steps below sea level, starting with a step down from sea level and ending with a step up to sea level.
 */
public class CountingValleysTest {

	/**
	 * @param steps the number of steps in the hike.
	 * @param path  characters that describe the path. {U,D}, U== Up, D==Down
	 * @return the number of valleys traversed
	 */
	int countingValleys(int steps, String path) {

		int valleyCount = 0;

		int actualSeaLevel = 0;
		int tmpActualSeaLevel;

		for (char step : path.toCharArray()) {

			tmpActualSeaLevel = actualSeaLevel;

			if (step == 'U') {
				++actualSeaLevel;
			} else {
				--actualSeaLevel;
			}

			if (actualSeaLevel == 0 && tmpActualSeaLevel == -1) {
				++valleyCount;
			}
		}

		return valleyCount;
	}

	int countingValleysStream(String path) {

		return path.chars().map(c -> c == 'U' ? 1 : -1)
				.collect(ValleysCounter::new, ValleysCounter::accept, ValleysCounter::combine)
				.getTraversedValleyCount();
	}

	class ValleysCounter implements IntConsumer {
		private int valleyCount = 0;
		private int actualSeaLevel = 0;

		int getTraversedValleyCount() {
			return valleyCount;
		}

		public void accept(int seaLevel) {
			int lastActualSeaLevel = actualSeaLevel;

			actualSeaLevel += seaLevel;

			if (actualSeaLevel == 0 && lastActualSeaLevel == -1) {
				++valleyCount;
			}
		}

		void combine(ValleysCounter last) {
			//only for calling in collect method of stream, it cannot be null
		}
	}

	int countingValleysStream2(String path) {

		return path.chars().map(c -> c == 'U' ? 1 : -1).boxed().collect(new ValleysCollector());
	}

	class ValleysCollector implements Collector<Integer, Integer, Integer> {

		private int valleyCount = 0;
		private int actualSeaLevel = 0;

		@Override
		public Supplier<Integer> supplier() {
			return () -> actualSeaLevel;
		}

		@Override
		public BiConsumer<Integer, Integer> accumulator() {
			return (notSavedRefOfLastValue, step) -> {

				int lastActualSeaLevel = actualSeaLevel;

				//notSavedRefOfLastValue += step; provede vydtvoreni noveho Integeru a ta puvodni reference je stracena
				actualSeaLevel += step;

				if (actualSeaLevel == 0 && lastActualSeaLevel == -1) {
					++valleyCount;
				}
			};
		}

		@Override
		public BinaryOperator<Integer> combiner() {
			//combiner se spousti jen kdyz spoustim collector paralelne, pak bych jen obe hodnoty secetl
			return (seeLevel1, seeLevel2) -> {
				throw new UnsupportedOperationException("Parallel stream not supported");
			};
		}

		@Override
		public Function<Integer, Integer> finisher() {
			return i -> valleyCount;
		}

		@Override
		public Set<Characteristics> characteristics() {
			//Nesmi byt null
			//return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH)); nevola se finisher metoda, protoze vraci to co ma na vstupu
			return Collections.emptySet();
		}
	}

	@Test
	void countingValleysMethosTest() {
		//If we represent _ as sea level, a step up as /, and a step down as \, the hike can be drawn as:


		/*

		path = UDDDUDUU

_/\      _
   \    /
    \/\/

    The hiker enters and leaves one valley.
		 */

		assertEquals(0, countingValleys(8, "UUUUUUUU"));
		assertEquals(0, countingValleys(8, "UUUDDUUU"));
		assertEquals(0, countingValleys(8, "UUDDUUDD"));
		assertEquals(1, countingValleys(4, "UDDU"));
		assertEquals(1, countingValleys(8, "UDDDUDUU"));

		assertEquals(0, countingValleys(8, "DDDDDDDD"));
		assertEquals(0, countingValleys(8, "DDDDDUUU"));
		assertEquals(1, countingValleys(8, "DDDDUUUU"));
		assertEquals(2, countingValleys(8, "DDUUDDUU"));
		assertEquals(4, countingValleys(8, "DUDUDUDU"));

	}

	@Test
	void countingValleysStreamMethosTest() {

		assertEquals(0, countingValleysStream("UUUUUUUU"));
		assertEquals(0, countingValleysStream("UUUDDUUU"));
		assertEquals(0, countingValleysStream("UUDDUUDD"));
		assertEquals(1, countingValleysStream("UDDU"));
		assertEquals(1, countingValleysStream("UDDDUDUU"));

		assertEquals(0, countingValleysStream("DDDDDDDD"));
		assertEquals(0, countingValleysStream("DDDDDUUU"));
		assertEquals(1, countingValleysStream("DDDDUUUU"));
		assertEquals(2, countingValleysStream("DDUUDDUU"));
		assertEquals(4, countingValleysStream("DUDUDUDU"));

	}

	@Test
	void countingValleysStream2MethosTest() {

		assertEquals(0, countingValleysStream2("UUUUUUUU"));
		assertEquals(0, countingValleysStream2("UUUDDUUU"));
		assertEquals(0, countingValleysStream2("UUDDUUDD"));
		assertEquals(1, countingValleysStream2("UDDU"));
		assertEquals(1, countingValleysStream2("UDDDUDUU"));

		assertEquals(0, countingValleysStream2("DDDDDDDD"));
		assertEquals(0, countingValleysStream2("DDDDDUUU"));
		assertEquals(1, countingValleysStream2("DDDDUUUU"));
		assertEquals(2, countingValleysStream2("DDUUDDUU"));
		assertEquals(4, countingValleysStream2("DUDUDUDU"));

	}
}
