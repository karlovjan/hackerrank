package algorithms;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/migratory-birds/problem
 *
 * Given an array of bird sightings where every element represents a bird type id,
 * determine the id of the most frequently sighted type.
 * If more than 1 type has been spotted that maximum amount, return the smallest of their ids.
 *
 * It is guaranteed that each bird type is 1, 2, 3, 4, or 5.
 *
 *
 * https://www.baeldung.com/java-groupingby-collector
 *
 * https://mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/
 *
 */
public class BirdTypesTest {

	/**
	 *
	 * @param arr list of bird type ids
	 * @return  the lowest type id of the most frequently sighted birds
	 */
	int migratoryBirds(List<Integer> arr) {


		return arr.stream()
				.collect(groupingBy(identity(), counting()))
				.entrySet().stream()
				//				.sorted(Map.Entry.comparingByKey())
				.max(Comparator.comparingLong(Map.Entry::getValue))
				.orElse(Map.entry(Integer.valueOf(-1), Long.valueOf(-1))).getKey();

	}

	int migratoryBirds_java8(List<Integer> arr) {

		return arr.stream()
				.collect(groupingBy(identity(), counting()))
				.entrySet().stream()
				//				.sorted(Map.Entry.comparingByKey())
				.max(Comparator.comparingLong(Map.Entry::getValue))
				.orElse(new AbstractMap.SimpleEntry<>(Integer.valueOf(-1), Long.valueOf(-1))).getKey();

	}

	@Test
	void migratoryBirdsTest() {

		assertEquals(-1, migratoryBirds(List.of()));
		assertEquals(1, migratoryBirds(List.of(1)));
		assertEquals(1, migratoryBirds(List.of(1,1,1,1)));
		assertEquals(1, migratoryBirds(List.of(5,4,4,3,3,3,2,2,2,2,1,1,1,1)));
		assertEquals(3, migratoryBirds(List.of(5,5,4,4,3,3,2,1)));
	}

	@Test
	void migratoryBirds_java8Test() {

		assertEquals(-1, migratoryBirds_java8(List.of()));
		assertEquals(1, migratoryBirds_java8(List.of(1)));
		assertEquals(1, migratoryBirds_java8(List.of(1,1,1,1)));
		assertEquals(1, migratoryBirds_java8(List.of(5,4,4,3,3,3,2,2,2,2,1,1,1,1)));
		assertEquals(3, migratoryBirds(List.of(5,5,4,4,3,3,2,1)));
	}
}
