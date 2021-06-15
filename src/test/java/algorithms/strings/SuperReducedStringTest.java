package algorithms.strings;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/reduced-string/problem
 */
public class SuperReducedStringTest {

	/**
	 * @param s a string to reduce, s length = {1, 100}, ascii[a-z]
	 * @return the reduced string or 'Empty String'
	 */
	static String superReducedString(String s) {
		//		s.chars() or s.codePoints() since java 9

		String reducedString = s.chars().mapToObj(i -> (char) i)
				.collect(StringBuilder::new, SuperReducedStringTest::reduceAdjacentPairChars,
						(sb1, sb2) -> sb2.append(sb1)).toString();

		return Optional.of(reducedString).filter(c -> !c.isEmpty()).orElse("Empty String");
	}

	private static void reduceAdjacentPairChars(StringBuilder sb, Character c) {
		if (sb.length() == 0 || !c.equals(sb.charAt(sb.length() - 1))) {
			sb.append(c);
		} else {
			sb.replace(sb.length() - 1, sb.length(), "");
		}
	}

	@Test
	void test_ha() {
		Assertions.assertEquals("a", superReducedString("a"));
		Assertions.assertEquals("ab", superReducedString("ab"));
		Assertions.assertEquals("abc", superReducedString("abc"));
		Assertions.assertEquals("b", superReducedString("aab"));
		Assertions.assertEquals("Empty String", superReducedString("aaaa"));
		Assertions.assertEquals("Empty String", superReducedString("abba"));
		Assertions.assertEquals("Empty String", superReducedString("baab"));
		Assertions.assertEquals("Empty String", superReducedString("aa"));
		Assertions.assertEquals("abd", superReducedString("aaabccddd"));
		Assertions.assertEquals("xabd", superReducedString("xaaabccddd"));
		Assertions.assertEquals("abd", superReducedString("xxaaabccddd"));
	}
}
