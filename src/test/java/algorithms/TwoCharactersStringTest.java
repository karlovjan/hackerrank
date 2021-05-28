package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/two-characters/problem
 */
public class TwoCharactersStringTest {

	/**
	 * Given a string, remove characters until the string is made up of any two alternating characters.
	 * When you choose a character to remove, all instances of that character must be removed.
	 * Determine the longest string possible that contains just two alternating letters.
	 *
	 * @param s {1, 1000} ascii[a-z]
	 * @return the length of the longest valid string, or 0 if there are none
	 */
	public static int alternate(String s) {

		List<Character> distChars = splitString(s);
		List<Integer> maxCountList = new ArrayList<>();

		for (int i = 0; i < distChars.size(); i++) {
			for (int j = i + 1; j < distChars.size(); j++) {
				maxCountList.add(countAlternatingChars(
						removeCharsExcept(distChars, s, distChars.get(i), distChars.get(j))));
			}
		}

		return maxCountList.stream().mapToInt(Integer::intValue).max().orElse(0);
	}

	private static Integer countAlternatingChars(String s) {
		char last = Character.MIN_VALUE;
		char actual;
		for (int i = 0; i < s.length(); i++) {
			actual = s.charAt(i);
			if (actual == last) {
				return 0;
			}
			last = actual;
		}
		return s.length();
	}

	private static String removeCharsExcept(List<Character> distChars, String s, char exceptCH1, char exceptCH2) {
		if (distChars.size() == 2) {
			return s;
		}

		Character c;
		for (int i = 0; i < distChars.size(); i++) {
			c = distChars.get(i);
			if (!c.equals(exceptCH1) && !c.equals(exceptCH2)) {
				s = s.replaceAll(String.valueOf(c), "");
			}
		}

		return s;
	}

	private static Map<Character, Integer> groupAndCountString(String s) {
		Map<Character, Integer> groups = new HashMap<>();
		char lastChar = Character.MIN_VALUE;
		char actualChar;
		char nextChar;
		for (int i = 0; i < s.length(); i++) {
			actualChar = s.charAt(i);
			nextChar = i < s.length() - 1 ? s.charAt(i + 1) : Character.MIN_VALUE;
			if (actualChar != nextChar) {

				if (actualChar != lastChar) {

					if (groups.containsKey(actualChar)) {
						groups.put(actualChar, groups.get(actualChar).intValue() + 1);
					} else {
						groups.put(actualChar, 1);
					}

					lastChar = actualChar;
				} else {
					//remove duplicated char
					if (groups.containsKey(lastChar)) {
						groups.put(lastChar, groups.get(lastChar).intValue() - 1);
					}
				}

			}
		}

		return groups;
	}

	private static List<Character> splitString(String s) {
		List<Character> distChars = new ArrayList<>();

		char z;

		while (s.length() > 0) {
			z = s.charAt(0);
			distChars.add(z);
			s = s.replaceAll(String.valueOf(z), "");
		}

		return distChars;
	}

	@Test
	void test1() {
		Assertions.assertEquals(0, alternate("a"));
		Assertions.assertEquals(0, alternate("aaa"));
		Assertions.assertEquals(0, alternate("aaabbbb"));
		Assertions.assertEquals(0, alternate("baaabbbb"));
		Assertions.assertEquals(0, alternate("abbcccbb"));
	}

	@Test
	void test2() {
		Assertions.assertEquals(2, alternate("ab"));
		Assertions.assertEquals(6, alternate("ababab"));

		Assertions.assertEquals(2, alternate("abc")); //bc | ac | ab
		Assertions.assertEquals(2, alternate("abcdef"));

		Assertions.assertEquals(2, alternate("abbcccbbf"));
	}

	@Test
	void test_hackerrank() {
		Assertions.assertEquals(4, alternate("abaacdabd")); //bdbd
		Assertions.assertEquals(5, alternate("beabeefeab")); //babab
	}
}
