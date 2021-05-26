package algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/append-and-delete/problem
 */
public class TransformStringToOtherStringTest {

	/**
	 * You have two strings of lowercase English letters. You can perform two types of operations on the first string:
	 * 1. Append a lowercase English letter to the end of the string.
	 * 2. Delete the last character of the string. Performing this operation on an empty string results in an empty string.
	 *
	 * @param s the initial string. 1<=s<=100, asci[a-z]
	 * @param t the desired final string. 1<=t<=100, asci[a-z]
	 * @param k the exact number of operation on string s. 1<=k<=100
	 * @return can you convert s to t by performing exactly k of the above operations on s? 'YES' or 'NO'
	 */
	public static boolean transform_old(String s, String t, int k) {

		if (s.length() + t.length() <= k) {
			return true;
		}

		StringBuilder sourceSB = new StringBuilder(s);
		int operation = k;
		for (int i = s.length() - 1; i >= 0 || operation > 0; i--, operation--) {

			if (t.startsWith(sourceSB.toString())) {
				return t.length() - sourceSB.length() == operation || sourceSB.length() + t.length() <= operation || (
						t.length() - sourceSB.length() < operation && ((t.length() - sourceSB.length()) % 2 == 0 ?
								operation % 2 == 0 :
								operation % 2 != 0));
			}

			sourceSB.replace(i, i + 1, "");
		}

		return operation >= t.length();
	}

	public static boolean transform(String s, String t, int k) {

		if (s.length() + t.length() <= k) {
			return true;
		}
		int theSameChars = 0;
		while (theSameChars < s.length() && theSameChars < t.length() && s.charAt(theSameChars) == t
				.charAt(theSameChars)) {
			++theSameChars;
		}

		int operation = k - (s.length() - theSameChars);
		return t.length() - theSameChars == operation || theSameChars + t.length() <= operation || (
				t.length() - theSameChars < operation && ((t.length() - theSameChars) % 2 == 0 ?
						operation % 2 == 0 :
						operation % 2 != 0));

	}

	public static String appendAndDelete(String s, String t, int k) {
		return transform(s, t, k) ? "Yes" : "No";
	}

	@Test
	void test_thesame_value() {
		Assertions.assertFalse(transform("a", "a", 1)); //add or delete
		Assertions.assertTrue(transform("a", "a", 2));
		Assertions.assertTrue(transform("a", "a", 3));
		Assertions.assertTrue(transform("a", "a", 4));
		Assertions.assertTrue(transform("a", "a", 5));
		Assertions.assertTrue(transform("a", "a", 99));
		Assertions.assertTrue(transform("a", "a", 100));

		Assertions.assertFalse(transform("ab", "ab", 1));
		Assertions.assertTrue(transform("ab", "ab", 2));
		Assertions.assertFalse(transform("ab", "ab", 3));
		Assertions.assertTrue(transform("ab", "ab", 4));
		Assertions.assertTrue(transform("ab", "ab", 5));

		Assertions.assertFalse(transform("abc", "abc", 1)); //add or delete
		Assertions.assertTrue(transform("abc", "abc", 2)); //delete and add
		Assertions.assertFalse(transform("abc", "abc", 3));
		Assertions.assertTrue(transform("abc", "abc", 4)); //delete and add
		Assertions.assertFalse(transform("abc", "abc", 5));
		Assertions.assertTrue(transform("abc", "abc", 6)); //delete and add
		Assertions.assertTrue(transform("abc", "abc", 10));
		Assertions.assertTrue(transform("abc", "abc", 99));
		Assertions.assertTrue(transform("abc", "abc", 100));
	}

	@Test
	void test_thesame_length_differValue() {
		Assertions.assertFalse(transform("a", "b", 1)); //add or delete
		Assertions.assertTrue(transform("a", "b", 2));
		Assertions.assertTrue(transform("a", "b", 3));
		Assertions.assertTrue(transform("a", "b", 4));
		Assertions.assertTrue(transform("a", "b", 5));
		Assertions.assertTrue(transform("a", "b", 99));
		Assertions.assertTrue(transform("a", "b", 100));

		Assertions.assertFalse(transform("ab", "ad", 1));
		Assertions.assertTrue(transform("ab", "ad", 2));
		Assertions.assertFalse(transform("ab", "de", 2));
		Assertions.assertFalse(transform("ab", "ad", 3));
		Assertions.assertFalse(transform("ab", "de", 3));
		Assertions.assertTrue(transform("ab", "ad", 4));
		Assertions.assertTrue(transform("ab", "de", 4));
		Assertions.assertTrue(transform("ab", "ad", 5));
		Assertions.assertTrue(transform("ab", "de", 5));

		Assertions.assertFalse(transform("abc", "abd", 1)); //add or delete
		Assertions.assertTrue(transform("abc", "abd", 2)); //delete and add
		Assertions.assertFalse(transform("abc", "abd", 3)); //k < t.length + s.length
		Assertions.assertTrue(transform("abc", "abd", 4)); //delete and add
		Assertions.assertFalse(transform("abc", "abd", 5)); //k < t.length + s.length
		Assertions.assertTrue(transform("abc", "abd", 6)); //delete and add
		Assertions.assertTrue(transform("abc", "ade", 6)); //delete and add
		Assertions.assertTrue(transform("abc", "ade", 7)); //delete and add
		Assertions.assertTrue(transform("abc", "def", 10));
		Assertions.assertTrue(transform("abc", "def", 11));
		Assertions.assertTrue(transform("abc", "def", 99));
		Assertions.assertTrue(transform("abc", "def", 100));
	}

	@Test
	void test_different_s_bigger() {
		Assertions.assertTrue(transform("ab", "a", 1));
		Assertions.assertFalse(transform("ab", "a", 2));
		Assertions.assertTrue(transform("ab", "a", 3));
		Assertions.assertTrue(transform("ab", "a", 4));
		Assertions.assertTrue(transform("ab", "a", 5));
		Assertions.assertTrue(transform("ab", "a", 6));

		Assertions.assertFalse(transform("bc", "a", 1));
		Assertions.assertFalse(transform("bc", "a", 2));
		Assertions.assertTrue(transform("bc", "a", 3));
		Assertions.assertTrue(transform("bc", "a", 4));
		Assertions.assertTrue(transform("bc", "a", 5));
		Assertions.assertTrue(transform("bc", "a", 6));
	}

	@Test
	void test_different_t_bigger() {
		Assertions.assertTrue(transform("a", "ab", 1));
		Assertions.assertFalse(transform("a", "ab", 2));
		Assertions.assertTrue(transform("a", "ab", 3));
		Assertions.assertTrue(transform("a", "ab", 4));
		Assertions.assertTrue(transform("a", "ab", 5));
		Assertions.assertTrue(transform("a", "ab", 6));

		Assertions.assertFalse(transform("c", "ab", 1));
		Assertions.assertFalse(transform("c", "ab", 2));
		Assertions.assertTrue(transform("c", "ab", 3));
		Assertions.assertTrue(transform("c", "ab", 4));
		Assertions.assertTrue(transform("c", "ab", 5));
		Assertions.assertTrue(transform("c", "ab", 6));
	}

	@Test
	void test_hackerrank() {
		Assertions.assertTrue(transform("aba", "aba", 7));
		Assertions.assertFalse(transform("ashley", "ash", 2));
		Assertions.assertTrue(transform("hackerhappy", "hackerrank", 9));
		Assertions.assertTrue(transform("abc", "def", 6));
	}
}
