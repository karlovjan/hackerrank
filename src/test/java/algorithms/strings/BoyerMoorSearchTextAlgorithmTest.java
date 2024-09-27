package algorithms.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * edX.com
 * GTx CS1332xIV
 * Data Structures & Algorithms IV: Pattern Matching, Dijkstra's, MST, and
 * Dynamic Programming Algorithms
 */
public class BoyerMoorSearchTextAlgorithmTest {

	List<Integer> boyerMoor(String text, String pattern) {
		if (text == null || pattern == null || text.isEmpty() || pattern.isEmpty() || pattern.length() > text.length()) {
			return new ArrayList<>(0);
		}
		List<Integer> foundList = new ArrayList<>();
		Map<Character, Integer> lastOccurenceTable = generateLastOccurenceTable(pattern);
		int n = text.length();
		int m = pattern.length();
		int i = 0;
		int j;
		int shift;
		// we start searching from a right of the pattern so that i <= n - m
		while (i <= n - m) {
			j = m - 1;
			while (j >= 0 && text.charAt(i + j) == pattern.charAt(j)) {
				--j;
			}

			if (j == -1) {
				// match found at i
				foundList.add(i);
				++i;
			} else {
				shift = lastOccurenceTable.getOrDefault(text.charAt(i + j), -1);
				if (shift < j) {
					i += j - shift;
				} else {
					++i;
				}
			}
		}

		return foundList;
	}

	private Map<Character, Integer> generateLastOccurenceTable(String pattern) {
		Map<Character, Integer> lastOccurenceTable = new HashMap<>(pattern.length());
		char ch;
		for (int i = 0; i < pattern.length(); i++) {
			ch = pattern.charAt(i);
			lastOccurenceTable.put(Character.valueOf(ch), i);
		}

		return lastOccurenceTable;
	}

	@Test
	void boyerMoor_test() {
		Assertions.assertTrue(boyerMoor(null, null).isEmpty());
		Assertions.assertTrue(boyerMoor("", "").isEmpty());
		Assertions.assertTrue(boyerMoor("a", "b").isEmpty());
		Assertions.assertTrue(boyerMoor("a", "ab").isEmpty());
		Assertions.assertIterableEquals(List.of(0), boyerMoor("a", "a"));
		Assertions.assertIterableEquals(List.of(0), boyerMoor("aa", "aa"));
		Assertions.assertIterableEquals(List.of(0), boyerMoor("ab", "a"));
		Assertions.assertIterableEquals(List.of(1), boyerMoor("aab", "ab"));
	}

	@Test
	void boyerMoor_test2() {
		Assertions.assertIterableEquals(List.of(1, 3), boyerMoor("aabab", "ab"));
	}

	@Test
	void boyerMoor_test3() {
		Assertions.assertIterableEquals(List.of(23), boyerMoor("cccccccccccccccccccccccab", "ab"));
		Assertions.assertIterableEquals(List.of(5), boyerMoor("asldfjvozo", "jvo"));
		Assertions.assertIterableEquals(List.of(4,19), boyerMoor("dkoepaplalcmzlklzslpaplakdlsasap", "papla"));
	}
}
