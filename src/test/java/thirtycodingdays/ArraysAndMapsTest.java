package thirtycodingdays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

class ArraysAndMapsTest {

	@Test
	void reverseArrayJava8_descendingIterator() {

		int[] a = new int[] { 1, 4, 3, 2 };

		Iterable<Integer> iterable = () -> IntStream.of(a).boxed().collect(Collectors.toCollection(ArrayDeque::new))
				.descendingIterator();

		String reverted = StreamSupport.stream(iterable.spliterator(), false).map(String::valueOf)
				.collect(Collectors.joining(" "));

		assertEquals("2 3 4 1", reverted);

	}

	@Test
	void lookUp_phoneBook_dictionary() {
		//https://www.hackerrank.com/challenges/30-dictionaries-and-maps/problem?h_r=next-challenge&h_v=zen

		//immutable
		//Collections.singletonMap("username1", "password1");
		//Collections.emptyMap();

		/*
		Map<String, Integer> map = Stream.of(new Object[][] {
     { "data1", 1 },
     { "data2", 2 },
 }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

		 */

		Map<String, Integer> phoneBook = Map.of("sam", 99912222, "tom", 11122222, "harry", 12299933);

		assertEquals(99912222, phoneBook.get("sam"));
		assertNull(phoneBook.get("edward"));
		assertEquals(12299933, phoneBook.get("harry"));

	}

	@Test
	void lookUp_phoneBook_dictionary2() {


		/*
		Map<String, Integer> map = Stream.of(
  new AbstractMap.SimpleEntry<>("idea", 1),
  new AbstractMap.SimpleEntry<>("mobile", 2))
  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


		Immmutable
		Map<String, Integer> map = Stream.of(
  new AbstractMap.SimpleImmutableEntry<>("idea", 1),
  new AbstractMap.SimpleImmutableEntry<>("mobile", 2))
  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

Map<String, String> map = Map.ofEntries(
  new AbstractMap.SimpleEntry<String, String>("name", "John"),
  new AbstractMap.SimpleEntry<String, String>("city", "budapest"),
  new AbstractMap.SimpleEntry<String, String>("zip", "000000"),
  new AbstractMap.SimpleEntry<String, String>("home", "1231231231")
);

Mutable
Map<String, String> map2 = new HashMap<String, String> (
  Map.ofEntries(
    new AbstractMap.SimpleEntry<String, String>("name", "John"),
    new AbstractMap.SimpleEntry<String, String>("city", "budapest")));


		 */

		Map<String, Integer> phoneBook;

		Stream.Builder<AbstractMap.SimpleEntry<String, Integer>> builder = Stream.builder();

		for (int i = 0; i < 3; i++) {
			String name = "name" + i;
			int phone = 100 + i;
			// Write code here
			builder.add(new AbstractMap.SimpleEntry<>(name, phone));
		}

		phoneBook = builder.build()
				.collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
		assertEquals(100, phoneBook.get("name0"));
		assertNull(phoneBook.get("name10"));
		assertEquals(102, phoneBook.get("name2"));

	}

	@Test
	void lookUp_phoneBook_dictionary_classic() {

		Map<String, Integer> phoneBook = new HashMap<>();

		for (int i = 0; i < 3; i++) {
			String name = "name" + i;
			int phone = 100 + i;
			// Write code here
			phoneBook.put(name, phone);
		}

		assertEquals(100, phoneBook.get("name0"));
		assertNull(phoneBook.get("name10"));
		assertEquals(102, phoneBook.get("name2"));

	}

}
