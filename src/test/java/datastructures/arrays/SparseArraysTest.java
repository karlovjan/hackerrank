package datastructures.arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static util.UtilTest.intArrayToString;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * There is a collection of input strings and a collection of query strings.
 * For each query string, determine how many times it occurs in the list of input strings.
 * Return an array of the results.
 */
public class SparseArraysTest {

	private static final SecureRandom random = new SecureRandom();
	private static final String DATA_FOR_RANDOM_STRING = "abcd";

	/**
	 * There is a collection of input strings and a collection of query strings. For each query string,
	 * determine how many times it occurs in the list of input strings.
	 *
	 * @param strings an array of strings to search
	 * @param queries an array of query strings
	 * @return Return an array of the results.
	 */
	static int[] matchingStrings1(String[] strings, String[] queries) {

		int[] result = new int[queries.length];
		int counter;

		for (int i = 0; i < queries.length; i++) {
			counter = 0;
			for (String s : strings) {
				if (queries[i].matches(s)) {
					++counter;
				}
			}

			result[i] = counter;
		}

		return result;
	}

	static int[] matchingStrings2(String[] strings, String[] queries) {

		return Arrays.stream(queries).map(q -> Arrays.stream(strings).filter(s -> s.matches(q)).count())
				.mapToInt(Long::intValue).toArray();

	}

	static int[] matchingStrings3(String[] strings, String[] queries) {

		Map<String, Long> groupedStringsMap = Arrays.stream(strings)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		return Arrays.stream(queries).map(q -> groupedStringsMap.getOrDefault(q, 0L)).mapToInt(Long::intValue)
				.toArray();
	}

	public static String generateRandomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

			sb.append(rndChar);

		}

		return sb.toString();

	}

	/**
	 * generuj stringy obsahujici znaky od 'a' do 'd' a odelce 2 az 4
	 *
	 * @return a random string
	 */
	public static String generateRandomStringInStream() {
		int leftLimit = 'a';
		int rightLimit = 'd';

		int leftTargetStringLength = 2;
		int rightTargetStringLength = 4;

		int randomLength = random.nextInt(rightTargetStringLength);
		if (randomLength < leftTargetStringLength) {
			randomLength = leftTargetStringLength;
		}
		return random.ints(randomLength, leftLimit, rightLimit + 1)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	@Test
	void matchingStrings1Test() {
		assertArrayEquals(new int[] { 2, 1, 0 },
				matchingStrings1(new String[] { "aba", "baba", "aba", "xzxb" }, new String[] { "aba", "xzxb", "ab" }));
	}

	@Test
	void matchingStrings2Test() {
		assertArrayEquals(new int[] { 2, 1, 0 },
				matchingStrings2(new String[] { "aba", "baba", "aba", "xzxb" }, new String[] { "aba", "xzxb", "ab" }));
	}

	@Test
	void matchingStrings3Test() {
		assertArrayEquals(new int[] { 2, 1, 0 },
				matchingStrings3(new String[] { "aba", "baba", "aba", "xzxb" }, new String[] { "aba", "xzxb", "ab" }));
	}

	@Test
	void matchingStrings4Test() {
		//less /proc/cpuinfo
		//bogomips        : 4789.04
		//test time - max  1000*1000 operaci, CPU 2.4GHz == 2.4 miliardy operaci za sekundu na jedno jadro
		// 1000*1000 milion operaci = 1M > x = 1/4789 = 0.2ms
		// 1000*1000 milion operaci = 1M > x = 1/2400 = 0.2ms

		String[] strings = Stream.generate(SparseArraysTest::generateRandomStringInStream).limit(1000)
				.toArray(String[]::new);
		String[] queries = Stream.generate(SparseArraysTest::generateRandomStringInStream).limit(1000)
				.toArray(String[]::new);

		int[] result;

		Instant start = Instant.now();
		result = matchingStrings1(strings, queries);
		Instant finish = Instant.now();
		System.out.printf("test1, result time %d ms - result: %s%n", Duration.between(start, finish).toMillis(),
				intArrayToString(result));

		start = Instant.now();
		result = matchingStrings2(strings, queries);
		finish = Instant.now();
		System.out.printf("test2, result time %d ms - result: %s%n", Duration.between(start, finish).toMillis(),
				intArrayToString(result));

		start = Instant.now();
		result = matchingStrings3(strings, queries);
		finish = Instant.now();
		System.out.printf("test3, result time %d ms - result: %s%n", Duration.between(start, finish).toMillis(),
				intArrayToString(result));

	}

}
