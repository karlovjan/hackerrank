package datastructures.arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

public class ReverseArrayTest {

	//https://www.techiedelight.com/reverse-sequential-stream-java/

	@Test
	void reverseArray() {

		int[] a = new int[] { 1, 4, 3, 2 };

		int[] reverted = new int[a.length];

		for (int i = 0; i < a.length; i++) {
			reverted[i] = a[a.length - 1 - i];
		}

		assertArrayEquals(new int[] { 2, 3, 4, 1 }, reverted);
	}

	@Test
	void reverseArray_CollectionReverse() {

		List<Integer> a = Arrays.asList(1, 4, 3, 2);

		Collections.reverse(a);

		assertIterableEquals(Arrays.asList(2, 3, 4, 1), a);
	}

	@Test
	void reverseArrayJava8_descendingIterator() {

		int[] a = new int[] { 1, 4, 3, 2 };

		List<Integer> reverted = new ArrayList<>();

		IntStream.of(a).boxed().collect(Collectors.toCollection(ArrayDeque::new)).descendingIterator()
				.forEachRemaining(reverted::add);

		assertIterableEquals(Arrays.asList(2, 3, 4, 1), reverted);

	}

	@Test
	void reverseArrayJava8_descendingIterator2() {

		int[] a = new int[] { 1, 4, 3, 2 };

		Iterable<Integer> iterable = () -> IntStream.of(a).boxed().collect(Collectors.toCollection(ArrayDeque::new))
				.descendingIterator();

		var reverted = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

		assertIterableEquals(Arrays.asList(2, 3, 4, 1), reverted);

	}

	@Test
	void reverseArrayJava8_Collector() {

		int[] a = new int[] { 1, 4, 3, 2 };

		Deque<Integer> reverted = IntStream.of(a).boxed()
				.collect(Collector.of(ArrayDeque::new, ArrayDeque::addFirst, (deq1, deq2) -> deq1));

		assertIterableEquals(Arrays.asList(2, 3, 4, 1), reverted);

	}

}
