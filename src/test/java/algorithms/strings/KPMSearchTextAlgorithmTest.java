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
public class KPMSearchTextAlgorithmTest {

	List<Integer> kmp(String text, String pattern) {
		if (text == null || pattern == null || text.isEmpty() || pattern.isEmpty() || pattern.length() > text.length()) {
			return new ArrayList<>(0);
		}
		List<Integer> foundList = new ArrayList<>();
		int[] failureTable = generateFailureTable(pattern);
		int n = text.length();
		int m = pattern.length();
		int k = 0; // text index
		int j = 0; //pattern index
		while (k < n) {
			if (pattern.charAt(j) == text.charAt(k)) {
				if (j == m - 1) {
					// match
					foundList.add(k);
					j = failureTable[j - 1];
				} else {
					++j;
					++k;
				}
			} else if(pattern.charAt(j) != text.charAt(k) && j == 0) {
				++k;
			} else {
				j = failureTable[j - 1];
			}
		}

		return foundList;
	}

	private int[] generateFailureTable(String pattern) {
		int[] failureArray = new int[pattern.length()];
		failureArray[0] = 0;
		int i = 0; 
		int j = 1;

		while (j < pattern.length()) {
			if (pattern.charAt(i) == pattern.charAt(j)) {
				failureArray[j] = i + 1;
				++i;
				++j;
			} else if(pattern.charAt(i) != pattern.charAt(j) && i == 0) {
				failureArray[j] = 0;
				++j;
			} else {
				i = failureArray[i - 1];
			}
		}

		return failureArray;
	}

	@Test
	void generateFailureTable_test() {
		Assertions.assertArrayEquals(new int[] {0,0,0,0,1,0,1,2,3}, generateFailureTable("revararev"));
		Assertions.assertArrayEquals(new int[] {0,0,1,1,2,3,2}, generateFailureTable("abaabab"));
	}

	@Test
	void kmp_empty_test() {
		Assertions.assertTrue(kmp(null, null).isEmpty());
		Assertions.assertTrue(kmp("", "").isEmpty());
		Assertions.assertTrue(kmp("a", "b").isEmpty());
		Assertions.assertTrue(kmp("a", "ab").isEmpty());
	}
	
	@Test
	void kmp_test() {
		Assertions.assertIterableEquals(List.of(1, 7, 10), kmp("bababaaababbababbaaa", "abab"));
	}

}
