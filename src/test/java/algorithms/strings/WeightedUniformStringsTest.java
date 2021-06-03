package algorithms.strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/weighted-uniform-string/problem
 * <p>
 * A weighted string is a string of lowercase English letters where each letter has a weight.
 * Character weights are 1 to 26 from a to z.
 * The weight of a string is the sum of the weights of its characters.
 * A uniform string consists of a single character repeated zero or more times.
 * For example, ccc and a are uniform strings, but bcb and cd are not.
 */
public class WeightedUniformStringsTest {

	/**
	 * @param s       the original string. ascii[a-z].
	 * @param queries Each of the next n lines contains an integer queries[i], the weight of a uniform subtring of s
	 *                that may or may not exist.
	 * @return an array of strings that answer the queries [Yes, No]
	 */
	public static List<String> weightedUniformStrings(String s, List<Integer> queries) {

		List<String> result = new ArrayList<>();
		Set<Integer> weights = new HashSet<>();

		int last = convertCharToInt(s.charAt(0));
		int sum = 0;
		int k;
		int i = 0;

		for (Integer query : queries) {

			for (; i < s.length(); i++) {
				k = convertCharToInt(s.charAt(i));
				if (k != last) {

					sum = 0;
				}

				sum += k;
				weights.add(sum);
				last = k;
				if (query.equals(sum)) {
					++i;
					break;
				}
			}

			result.add(weights.contains(query) ? "Yes" : "No");

		}

		return result;
	}

	public static List<String> weightedUniformStrings_wrong(String s, List<Integer> queries) {

		LinkedHashMap<Integer, String> queryResultMap = queries.stream().collect(
				Collectors.toMap(Function.identity(), o -> "No", (oldVal, newVal) -> oldVal, LinkedHashMap::new));

		int last = convertCharToInt(s.charAt(0));
		int sum = 0;
		int counter = 0;
		int k;
		for (int i = 0; i < s.length(); i++) {
			k = convertCharToInt(s.charAt(i));
			if (k != last) {

				sum = 0;
			}

			sum += k;
			if (queryResultMap.containsKey(sum)) {
				queryResultMap.replace(sum, "Yes");
				++counter;
				if (counter == queries.size()) {
					break;
				}
			}
			last = k;
		}

		return new ArrayList<>(queryResultMap.values());
	}

	public static List<String> weightedUniformStrings_slow(String s, List<Integer> queries) {
		List<Integer> identicalSums = new ArrayList<>();

		int last = convertCharToInt(s.charAt(0));
		int sum = 0;
		int k;
		for (int i = 0; i < s.length(); i++) {
			k = convertCharToInt(s.charAt(i));
			if (k != last) {

				sum = 0;
			}

			sum += k;
			identicalSums.add(sum);
			last = k;
		}

		return queries.stream().map(q -> identicalSums.contains(q) ? "Yes" : "No").collect(Collectors.toList());
	}

	private static int convertCharToInt(char c) {
		return Character.getNumericValue(c) - 9;
	}

	@Test
	void test_ha() {
		Assertions.assertIterableEquals(List.of("Yes", "Yes", "Yes", "Yes", "No", "No"),
				weightedUniformStrings("abccddde", List.of(1, 3, 12, 5, 9, 10)));
		Assertions.assertIterableEquals(List.of("Yes", "No", "Yes", "Yes", "No"),
				weightedUniformStrings("aaabbbbcccddd", List.of(9, 7, 8, 12, 5)));
		Assertions.assertIterableEquals(List.of("Yes", "Yes", "Yes"), weightedUniformStrings("a", List.of(1, 1, 1)));
		Assertions
				.assertIterableEquals(List.of("Yes", "Yes", "Yes"), weightedUniformStrings("aaacb", List.of(3, 3, 2)));
	}
}
