package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/cats-and-a-mouse/problem
 */
public class CatsAndMouseTest {

	private final String CAT_A = "Cat A";
	private final String CAT_B = "Cat B";
	private final String MOUSE_C = "Mouse C";

	String catAndMousePlay(int cat1XPosition, int cat2XPosition, int mouseXPosition) {

		int diffCat1AndMouse = Math.abs(cat1XPosition - mouseXPosition);
		int diffCat2AndMouse = Math.abs(cat2XPosition - mouseXPosition);

		if (diffCat1AndMouse == diffCat2AndMouse) {
			return MOUSE_C;
		}

		return diffCat1AndMouse < diffCat2AndMouse ? CAT_A : CAT_B;

	}

	@Test
	void catAndMousePlayTest() {

		assertEquals(MOUSE_C, catAndMousePlay(1, 1, 1));
		assertEquals(MOUSE_C, catAndMousePlay(100, 100, 100));
		assertEquals(CAT_A, catAndMousePlay(1, 100, 50));
		assertEquals(CAT_A, catAndMousePlay(1, 100, 49));
		assertEquals(CAT_B, catAndMousePlay(1, 100, 51));
		assertEquals(CAT_A, catAndMousePlay(1, 100, 2));
		assertEquals(CAT_B, catAndMousePlay(1, 80, 100));
		assertEquals(CAT_A, catAndMousePlay(100, 2, 80));

		assertEquals(CAT_B, catAndMousePlay(2, 5, 4));
		assertEquals(CAT_B, catAndMousePlay(1, 2, 3));
		assertEquals(MOUSE_C, catAndMousePlay(1, 3, 2));

	}
}
