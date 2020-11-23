package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class UtilTest {

	@Test
	void intArrayToStringTestClasic() {
		int[] testInts = { 1, 2, 2, 2, 2, 2, 2, 2, 22, 3, 2, 22, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3 };
		String expected = "[1, 2, 2, 2, 2, 2, 2, 2, 22, 3, 2, 22, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3]";

		assertEquals(expected, Arrays.toString(testInts));
	}

	@Test
	void intArrayToStringTestStream() {
		int[] testInts = { 1, 2, 2, 2, 2, 2, 2, 2, 22, 3, 2, 22, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3 };
		String expected = "[1,2,2,2,2,2,2,2,22,3,2,22,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3]";

		assertEquals(expected, intArrayToString(testInts));
	}

	public static String intArrayToString(int[] array) {
		return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(",", "[", "]"));
	}
}
