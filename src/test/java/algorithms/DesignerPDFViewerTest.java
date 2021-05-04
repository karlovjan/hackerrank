package algorithms;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/designer-pdf-viewer/problem
 * <p>
 * There is a list of 26 character heights aligned by index to their letters. For example, 'a' is at index 1 and 'z' is at index 25.
 * There will also be a string. Using the letter heights given, determine the area of the rectangle highlight in mm2 assuming all letters are 1mm wide.
 */
public class DesignerPDFViewerTest {

	public static int designerPdfViewer(List<Integer> h, String word) {

		//		letters from [a...z] lowercase [10 ... 35]

		int letter_wide = 1;
		//		int wordMaxHeight = word.chars().map(i -> h.get(i - 10)).max().orElse(0); Since Java 9
		int wordMaxHeight = IntStream.range(0, word.length()).map(i -> Character.getNumericValue(word.charAt(i)))
				.map(p -> h.get(p - 10)).max().orElse(0);
		int wordLength = word.length();

		return wordLength * letter_wide * wordMaxHeight;
	}

	@Test
	void test1() {

		Assertions.assertEquals(9,
				designerPdfViewer(List.of(1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
						"abc"));
		Assertions.assertEquals(28,
				designerPdfViewer(List.of(1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7),
						"zaba"));

	}
}
